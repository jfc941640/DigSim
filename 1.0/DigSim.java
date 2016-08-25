//****************************************************************************
// ---- version information ----
//
// DigSim.java           v 1.00 b3
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:           v 1.00b1 01-03-1996
//                            v 1.00b2 07-03-1996 AnalyzerPanel added
//                            v 1.00b3 26-03-1996 Scrollbar bug fixed
//                                                SimpleDialog bug fixed
// Released in public domain: v 1.00b1 01-03-1996
//
// ---- Description ----
// Java program for simulating digital schematics.
//
// This program and the Java source is in the public domain.
// Permission to use, copy, modify, and distribute this software
// and its documentation for NON-COMMERCIAL purposes and
// without fee is hereby granted.
//
//    Copyright 1996
//
//    Iwan van Rienen
//    Joan Maetsuyckerstr. 145
//    2593 ZG  The Hague
//    The Netherlands
//
// I am not responsible for any bugs in this program and
// possible damage to hard- or software when using this program.
//****************************************************************************
import java.applet.Applet;
import java.awt.*;
import java.lang.InterruptedException;
import java.lang.Integer;
import java.lang.Math;
import java.util.Vector;
import java.io.StreamTokenizer;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.awt.image.*;

public class DigSim extends Applet implements Runnable {
    DigSimFrame frame;
    ControlPanel MyControlPanel = null;
    StatusPanel MyStatusPanel = null;
    SchematicPanel MySchematicPanel = null;
    Button displayb, disposeb;
    Thread killme = null;
    String SchematicName = null;
    Schematic MySchematic = null;
    Pin PinGrid[][];
    String message = null;
    static final int MaxXPoints = 100, MaxYPoints = 100;
    Image GridImage = null;
    Image ImageBuffer = null;
    Graphics ibg;
    int OffScreenWidth = 0, OffScreenHeight = 0;
    static final Color BackGroundColor = Color.black;
    static final Color GridColor = Color.green;
    static final int GridStep = 8;
    static final int hgs = GridStep / 2;
    boolean SchematicPanelPainted = false;
    boolean HelpWanted = false;
    String TextFileRequested = null;
    boolean EnableFileOperations = false;
    ExamplesFrame MyExamplesFrame = null;
    String RequestedText = null;
    boolean RequestedTextFileRead = false;
    boolean RequestedTextFileError = false;
    OptionsFrame MyOptionsFrame = null;
    AnalyzerFrame MyAnalyzerFrame = null;
    int SimulationSpeed = 10;
    boolean StopAtShortCircuit = true;
    boolean StopAtLoop = true;
    boolean AnalyzerAutoPopUp = true;

//----------------------------------------------------------------------------
// Initialize a new Digital simulator
//----------------------------------------------------------------------------
    public void init() {
        String temp;

        setLayout(new FlowLayout());
        add(displayb = new Button("Display simulator"));
        add(disposeb = new Button("Dispose simulator"));
        OffScreenWidth = 400;
        OffScreenHeight = 300;
        // System.out.println ("OffScreenWidth,Height = " + OffScreenWidth + ", " + OffScreenHeight);
        SchematicName = getParameter("schematic");
        temp = getParameter("fileop");
        if (temp != null) {
            if (temp.equals ("true")) {
                EnableFileOperations = true;
            } else {
                EnableFileOperations = false;
            }
        } else {
            EnableFileOperations = false;
        }

        PinGrid = new Pin[MaxXPoints][MaxYPoints];
        InitPinGrids();
        SetUpImages();

        doFrame();
        StatusMessage("Please wait.");
    }

//----------------------------------------------------------------------------
// Called when program starts
//----------------------------------------------------------------------------
    public void start() {
        if(killme == null) {
            killme = new Thread(this);
            killme.start();
        }
    }

//----------------------------------------------------------------------------
// Called when program stops
//----------------------------------------------------------------------------
    public void stop() {
        killme = null;
    }

//----------------------------------------------------------------------------
// This is the main thread, it handels the loading of the initial
// schematic, the loading of requested files and triggering simulation
// cycles.
//----------------------------------------------------------------------------
    public void run() {
        InputStream is = null;
        String message = null;
        int BUFSIZE = 100;
        char Buf[];
        int BufPtr = 0;
        int BytesRead = 0;
        int c;
        String Result;

        Buf = new char [BUFSIZE];

        try {
            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
            if (SchematicName != null) {
                // System.out.println ("Load schematic");
                is = new URL(getDocumentBase(), SchematicName).openStream();
                MySchematic = new Schematic (PinGrid, is);
            }
        } catch(Exception e) {
            message = e.toString();
            String DlgButtons[] = { "OK" };
            SimpleDialog ExceptionDialog = new SimpleDialog(frame, "Reading inital schematic", message, DlgButtons, 1, 0, 0, SimpleDialog.IMAGE_STOP);
        }

        try {
            if (is != null)
                is.close();
        } catch(Exception e) {
        }
        repaint();

        if (MySchematic == null) {
            // System.out.println ("Start with empty schematic");
            MySchematic = new Schematic();
            frame.setTitle("Digital Simulator [untitled]");
        } else {
            frame.setTitle("Digital Simulator [" + SchematicName + "]");
            MySchematic.FileDir = "";
            MySchematic.FileName = SchematicName;
        }

        while (killme != null) {

            try {
                Thread.sleep(SimulationSpeed);
            } catch (InterruptedException e){}

            //------------------------------------------------------------
            if (TextFileRequested != null) {
                // System.out.println ("TextFile requested");
                BufPtr = 0;
                Result =  "";
                try {
                    Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
                    is = new URL(getDocumentBase(), TextFileRequested).openStream();

                    while ( (c = is.read()) != -1) {
                        Buf[BufPtr++] = (char) c;
                        if (BufPtr >= BUFSIZE) {
                            Result += new String (Buf, 0, BUFSIZE);
                            BufPtr = 0;
                        }
                    }
                    Result += new String (Buf, 0, BufPtr);

                } catch(Exception e) {
                    Result += e.toString();
                    RequestedText = Result;
                    RequestedTextFileError = true;
                }

                try {
                    if (is != null) {
                        is.close();
                    }
                } catch(Exception e) {
                    Result += e.toString();
                    RequestedText = Result;
                    RequestedTextFileError = true;
                }
                if (!RequestedTextFileError) {
                    RequestedText = Result;
                    RequestedTextFileRead = true;
                }

            }
            //------------------------------------------------------------

            if (!SchematicPanelPainted) {
                if (ImagesReady()) {
                    SchematicPanelPainted = true;
                    MySchematicPanel.repaint();

                    if (MyControlPanel != null) {
                        MyControlPanel.EnableAllButtons();
                        frame.EnableAllMenus();
                        StatusMessage("DigSim initialized. Make a choice or select a component.");
                    }
                }
            }
            if (MyStatusPanel.SimulationRunning) {
                SimulateCycle();
            }

        }
        killme = null;
    }

//----------------------------------------------------------------------------
// Fill 2-dimensional PinGrid array with new Pins
// The PinGrid is used to determine where components are placed in the
// schematic, and before simulation it's used to set up a linked list
// of all components connected to eachother.
//----------------------------------------------------------------------------
    public void InitPinGrids() {
        int x, y;
        // System.out.println ("Initialise PinGrid.");
        for (x = 0; x < MaxXPoints; x++) {
            for (y = 0; y < MaxYPoints; y++) {
               PinGrid[x][y] = new Pin();
            }
        }
        // System.out.println ("Initialise ready.");
    }

//----------------------------------------------------------------------------
// Preapare an off-screen ImageBuffer for some double-buffering,
// and prepare an image with the Grid used for fast redrawing.
// When the user resizes the Simulator frame this function is called again.
//----------------------------------------------------------------------------
    public void SetUpImages() {
        int x, y;
        // Set up big imagebuffer for off-screen painting.
        ImageBuffer = createImage(OffScreenWidth, OffScreenHeight);
        ibg = ImageBuffer.getGraphics();
        // Set up a grid image for fast repainting.
        GridImage = createImage(OffScreenWidth, OffScreenHeight);
        Graphics g = GridImage.getGraphics();
        g.setColor (BackGroundColor);
        g.fillRect (0, 0, OffScreenWidth, OffScreenHeight);
        g.setColor (GridColor);
        for (x = 1; x <= MaxXPoints; x++) {
            for (y = 1; y <= MaxYPoints; y++) {
                g.drawLine (x * GridStep, y * GridStep, x * GridStep, y * GridStep);
            }
        }
    }

//----------------------------------------------------------------------------
// The user want to dispose the simulator
//----------------------------------------------------------------------------
    public void destroyFrame() {
        if (SimulateRunning()) {
            SimulateStop();
        }
        if (MyAnalyzerFrame != null) {
             MyAnalyzerFrame.dispose();
             MyAnalyzerFrame = null;
        }
        if (MyExamplesFrame != null) {
            MyExamplesFrame.dispose();
            MyExamplesFrame = null;
        }
        if (MyOptionsFrame != null) {
            MyOptionsFrame.dispose();
            MyOptionsFrame = null;
        }
        if (frame != null) {
            frame.dispose();
            frame = null;
            displayb.enable(true);
            disposeb.enable(false);
        }
    }

//----------------------------------------------------------------------------
// Called to show and initialize the main simulator frame
//----------------------------------------------------------------------------
    void doFrame() {
        displayb.enable(false);
        disposeb.enable(true);
        frame = new DigSimFrame(this);
        frame.EnableAllMenus();
    }

//----------------------------------------------------------------------------
// Handle the two buttons in the applet.
//----------------------------------------------------------------------------
    public boolean action(Event ev, Object arg) {
        if (ev.target instanceof Button) {
            String label = (String)arg;
            if (label.equals("Display simulator")) {
                doFrame();
                MyControlPanel.EnableAllButtons();
            } else if (label.equals("Dispose simulator")) {
                destroyFrame();
            }
            return true;
        }
        return false;
    }

//----------------------------------------------------------------------------
// Set up the digital simulator before simulating.
//----------------------------------------------------------------------------
    public void SimulateSetUp() {
        // System.out.println ("Simulate Set up");
        int x, y;
        for (x = 0; x < MaxXPoints; x++) {
            for (y = 0; y < MaxYPoints; y++) {
                if (PinGrid[x][y] != null) {
                    PinGrid[x][y].SimulateSetUp(x, y);
                }
            }
        }
        // System.out.println ("Simulate Set Up ready");
    }

//----------------------------------------------------------------------------
// The user wants to start simulating. Set up everthing, and load
// the analyzer if there are probes in the schematic
//----------------------------------------------------------------------------
    public void SimulateStart() {
        StatusMessage("Please wait. Initializing simulation.");
        MyStatusPanel.repaint();
        if (MyStatusPanel.SimulationInitializeInProgress) {
            return;
        } else {
            if (AnalyzerAutoPopUp && MyAnalyzerFrame == null) {
                if (MySchematic.ProbesInSchematic()) {
                    MyAnalyzerFrame = new AnalyzerFrame(this);
                }
            }
            MySchematicPanel.SelectSchematic.RemoveAllComponents();
            MyStatusPanel.SimulationInitializeInProgress = true;
            MyStatusPanel.repaint();
            SimulateSetUp();
            MyStatusPanel.SimulationInitializeInProgress = false;
            MyStatusPanel.SimulationRunning = true;
            StatusMessage("Simulation running. press 'Simulate' button again to stop or press a component in the schematic");
    //      MyStatusPanel.repaint();
        }
    }

//----------------------------------------------------------------------------
// Stop the siumulation process
//----------------------------------------------------------------------------
    public void SimulateStop() {
            MyStatusPanel.SimulationRunning = false;
            StatusMessage("Simulation stoppped. Make a choice above or change the schematic.");
            MyStatusPanel.repaint();
    }

//----------------------------------------------------------------------------
// Determine if the program is simulating
//----------------------------------------------------------------------------
    public boolean IsSimulating() {
        return (MyStatusPanel.SimulationRunning || MyStatusPanel.SimulationInitializeInProgress);
    }

//----------------------------------------------------------------------------
// Simulate one clockcycle. Check for short-circuit and loops
// and redraw schematic and analyzer after the Simulate cycle.
//----------------------------------------------------------------------------
    public void SimulateCycle() {
        ElectronicComponent TempComponent;

        MySchematic.InitBeforeSimulate();
        for (int ix = 0; ix < 4; ix++) {
            MySchematic.Simulate();
        }
        if (StopAtShortCircuit) {
            TempComponent = MySchematic.TestShortCircuit();
            if (TempComponent != null) {
                String DlgButtons[] = { "OK" };
                String message = "Short circuit detected at " + TempComponent.getName() + " pos. " + TempComponent.Pos.x + ", "  + TempComponent.Pos.y ;
                SimpleDialog ExceptionDialog = new SimpleDialog(frame, "Short circuit detected", message, DlgButtons, 1, 0, 0, SimpleDialog.IMAGE_STOP);
                UserWantsSimulate();
                MySchematicPanel.repaint();
                MyStatusPanel.repaint();
                return;
            }
        }
        if (StopAtLoop) {
            TempComponent = MySchematic.TestLoop();
            if (TempComponent != null) {
                String DlgButtons[] = { "OK" };
                String message = "Loop detected at " + TempComponent.getName() + " pos. " + TempComponent.Pos.x + ", "  + TempComponent.Pos.y ;
                SimpleDialog ExceptionDialog = new SimpleDialog(frame, "Loop detected", message, DlgButtons, 1, 0, 0, SimpleDialog.IMAGE_STOP);
                UserWantsSimulate();
                MySchematicPanel.repaint();
                MyStatusPanel.repaint();
                return;
            }
        }
        if (MyAnalyzerFrame != null && MyAnalyzerFrame.MyAnalyzerPanel != null) {
            MyAnalyzerFrame.MyAnalyzerPanel.update();
        }

        MySchematicPanel.repaint();
        MyStatusPanel.repaint();
    }

//----------------------------------------------------------------------------
// Add an Electronic Component to the schematic
//----------------------------------------------------------------------------
    public void addComponent (ElectronicComponent NewComponent) {
        if (MySchematic == null) return;
        MySchematicPanel.SelectSchematic.RemoveAllComponents();
        MySchematic.addComponent(NewComponent);
        if (!(NewComponent instanceof Wire)) {
            UserWantsPointer();
        }
        MySchematicPanel.repaint();
    }

//----------------------------------------------------------------------------
// Add an Electronic Component with name ComponentChoice to the schematic.
//----------------------------------------------------------------------------
    public void addComponent(String ComponentChoice) {
        if (MySchematicPanel == null) return;
        if ("Wire".equals(ComponentChoice)) {
            UserWantsWireDrawing();
            return;
        } else if ("Junction".equals(ComponentChoice)) {
            UserWantsJunctionDrawing();
            return;
        } else if ("Vcc".equals(ComponentChoice)) {
            addComponent(new Vcc(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("GND".equals(ComponentChoice)) {
            addComponent(new GND(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset ));
        } else if ("Switch".equals(ComponentChoice)) {
            addComponent(new Switch(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("Push button".equals(ComponentChoice)) {
            addComponent(new PushButton(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));

        } else if ("Buffer".equals(ComponentChoice)) {
            addComponent(new Buffer(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("Inverter".equals(ComponentChoice)) {
            addComponent(new Inverter(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("2-input AND port".equals(ComponentChoice)) {
            addComponent(new TwoAndPort(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("3-input AND port".equals(ComponentChoice)) {
            addComponent(new ThreeAndPort(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("2-input OR port".equals(ComponentChoice)) {
            addComponent(new TwoOrPort(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("3-input OR port".equals(ComponentChoice)) {
            addComponent(new ThreeOrPort(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("2-input XOR port".equals(ComponentChoice)) {
            addComponent(new TwoXorPort(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));

        } else if ("2-input NAND port".equals(ComponentChoice)) {
            addComponent(new TwoNandPort(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("3-input NAND port".equals(ComponentChoice)) {
            addComponent(new ThreeNandPort(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("2-input NOR port".equals(ComponentChoice)) {
            addComponent(new TwoNorPort(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("3-input NOR port".equals(ComponentChoice)) {
            addComponent(new ThreeNorPort(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("2-input XNOR port".equals(ComponentChoice)) {
            addComponent(new TwoXnorPort(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));

        } else if ("SR-latch".equals(ComponentChoice)) {
            addComponent(new SRLatch(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("Gated SR-latch".equals(ComponentChoice)) {
            addComponent(new GatedSRLatch(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("D-latch".equals(ComponentChoice)) {
            addComponent(new DLatch(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("D-flipflop".equals(ComponentChoice)) {
            addComponent(new DFlipFlop(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("JK-flipflop".equals(ComponentChoice)) {
            addComponent(new JKFlipFlop(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("Edge-triggered T-flipflop".equals(ComponentChoice)) {
            addComponent(new EdgeTriggeredTFlipFlop(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("T-flipflop".equals(ComponentChoice)) {
            addComponent(new TFlipFlop(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("Octal D-flipflop".equals(ComponentChoice)) {
            addComponent(new OctalDFlipFlop(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("Octal latch".equals(ComponentChoice)) {
            addComponent(new OctalLatch(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));

        } else if ("Red LED".equals(ComponentChoice)) {
            addComponent(new LED(PinGrid, Color.red, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("Green LED".equals(ComponentChoice)) {
            addComponent(new LED(PinGrid, Color.green, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("Yellow LED".equals(ComponentChoice)) {
            addComponent(new LED(PinGrid, Color.yellow, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset ));
        } else if ("Bi-color LED".equals(ComponentChoice)) {
            addComponent(new BiColorLED(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("7-segment display".equals(ComponentChoice)) {
            addComponent(new SevenSegmentDisplay(PinGrid, Color.red, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));

        } else if ("Oscilator".equals(ComponentChoice)) {
            addComponent(new Oscilator(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("Analyzer probe".equals(ComponentChoice)) {
            addComponent(new Probe(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
            updateAnalyzer();
        } else if ("BCD to 7-segment decoder".equals(ComponentChoice)) {
            addComponent(new BCDToSevenSegDecoder(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("3- to 8-line decoder".equals(ComponentChoice)) {
            addComponent(new ThreeToEightLineDecoder(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("4-bit binary counter".equals(ComponentChoice)) {
            addComponent(new FourBitBinaryCounter(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("8-bit serial in shift register".equals(ComponentChoice)) {
            addComponent(new EightBitSerInShiftReg(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        } else if ("8-bit parallel in shift register".equals(ComponentChoice)) {
            addComponent(new EightBitParInShiftReg(PinGrid, MySchematicPanel.GridXOffset, MySchematicPanel.GridYOffset));
        }

        MySchematicPanel.WireDrawing = false;
        MySchematicPanel.JunctionDrawing = false;
        StatusMessage("Click and hold the mouse button in the body of the new " + ComponentChoice + " to move it.");
    }

//----------------------------------------------------------------------------
// Determine if all Simulator images are ready.
//----------------------------------------------------------------------------
    public boolean ImagesReady() {
        return (ibg == null || GridImage == null || ImageBuffer == null || MySchematic == null) ?  false : true;
    }

//----------------------------------------------------------------------------
// Show a message to the user
//----------------------------------------------------------------------------
    public void StatusMessage(String msg) {
        MyStatusPanel.StatusMessage(msg);
    }

//----------------------------------------------------------------------------
// Determine if the simulation is running
//----------------------------------------------------------------------------
    public boolean SimulateRunning() {
        return MyStatusPanel.SimulateRunning();
    }

//----------------------------------------------------------------------------
// return applet info for use in appletviewer
//----------------------------------------------------------------------------
    public String getAppletInfo() {
        return ("DigSim v 0.01 (c) 1996 Iwan van Rienen\nE-mail: ivr@bart.nl\nHomepage: http://www.bart.nl/~ivr/");
    }

//----------------------------------------------------------------------------
// Show all parameters
//----------------------------------------------------------------------------
    public String[][] getParameterInfo() {
        String pinfo[][] = {
            { "schematic",  "String",   "Initial schematic to load"} ,
            { "fileop",     "Boolean",  "Local file operations enable"} };
        return pinfo;
    }

//----------------------------------------------------------------------------
// Enable the pointer in the control panel
//----------------------------------------------------------------------------
    public void UserWantsPointer() {
        HelpWanted = false;
        if (MyControlPanel != null) {
            MyControlPanel.SelectButton ("Pointer");
            MyControlPanel.UnselectButton ("Wire");
            MyControlPanel.UnselectButton ("Junction");
            MyControlPanel.UnselectButton ("Text");
            MyControlPanel.UnselectButton ("Help");
            MyControlPanel.UnselectButton ("Simulate");
            MyControlPanel.UnselectButton ("New");
            MyControlPanel.UnselectButton ("Open");
            MyControlPanel.UnselectButton ("Save");
        }
        if (MySchematicPanel != null) {
            MySchematicPanel.WireDrawing = false;
            MySchematicPanel.JunctionDrawing = false;
            StatusMessage("Move the cursor to a component body or wire-end and press a mouse button.");
        }
   }

//----------------------------------------------------------------------------
// Enable the Wire-drawing button in the control panel
//----------------------------------------------------------------------------
    public void UserWantsWireDrawing() {
        if (HelpWanted) {
            HelpDialog MyHelpDialog = new HelpDialog(frame, "Wire");
            UserWantsPointer();
            return;
        }

        if (MyControlPanel != null) {
            MyControlPanel.UnselectButton ("Pointer");
            MyControlPanel.SelectButton ("Wire");
            MyControlPanel.UnselectButton ("Junction");
            MyControlPanel.UnselectButton ("Text");
            MyControlPanel.UnselectButton ("Help");
            MyControlPanel.UnselectButton ("Simulate");
            MyControlPanel.UnselectButton ("New");
            MyControlPanel.UnselectButton ("Open");
            MyControlPanel.UnselectButton ("Save");
        }
        if (MySchematicPanel != null) {
            StatusMessage("Move the mouse to the desired position, click and hold the mouse button to draw a wire.");
            MySchematicPanel.WireDrawing = true;
            MySchematicPanel.JunctionDrawing = false;
            MySchematicPanel.SelectSchematic.RemoveAllComponents();
            MySchematicPanel.repaint();
        }
    }

//----------------------------------------------------------------------------
// Enable the Junction place button in the control panel
//----------------------------------------------------------------------------
    public void UserWantsJunctionDrawing() {
        if (HelpWanted) {
            HelpDialog MyHelpDialog = new HelpDialog(frame, "Junction");
            UserWantsPointer();
            return;
        }

        if (MyControlPanel != null) {
            MyControlPanel.UnselectButton ("Pointer");
            MyControlPanel.UnselectButton ("Wire");
            MyControlPanel.SelectButton ("Junction");
            MyControlPanel.UnselectButton ("Text");
            MyControlPanel.UnselectButton ("Help");
            MyControlPanel.UnselectButton ("Simulate");
            MyControlPanel.UnselectButton ("New");
            MyControlPanel.UnselectButton ("Open");
            MyControlPanel.UnselectButton ("Save");
        }
        if (MySchematicPanel != null) {
            MySchematicPanel.WireDrawing = false;
            MySchematicPanel.JunctionDrawing = true;
            StatusMessage("Move the mouse to the desired position, and click the mouse button to add a junction.");
            MySchematicPanel.SelectSchematic.RemoveAllComponents();
            MySchematicPanel.repaint();
        }
   }

//----------------------------------------------------------------------------
// Enable the Text-drawing button in the control panel
//----------------------------------------------------------------------------
    public void UserWantsTextDrawing() {
        if (HelpWanted) {
            HelpDialog MyHelpDialog = new HelpDialog(frame, "Text");
            UserWantsPointer();
            return;
        }
        if (MyControlPanel != null) {
            MyControlPanel.UnselectButton ("Pointer");
            MyControlPanel.UnselectButton ("Wire");
            MyControlPanel.UnselectButton ("Junction");
            MyControlPanel.SelectButton ("Text");
            MyControlPanel.UnselectButton ("Help");
            MyControlPanel.UnselectButton ("Simulate");
            MyControlPanel.UnselectButton ("New");
            MyControlPanel.UnselectButton ("Open");
            MyControlPanel.UnselectButton ("Save");
        }
        if (frame != null) {
            if (frame.MyTextDialog == null) {
                frame.MyTextDialog = new TextDialog(frame, "", frame.NEWTEXTDIALOG_ID);
            }
            StatusMessage("Please type a new text.");
            UserWantsPointer();
        }
    }

//----------------------------------------------------------------------------
// Enable the Help button in the control panel
//----------------------------------------------------------------------------
    public void UserWantsHelp() {
        if (MyControlPanel != null) {
            MyControlPanel.UnselectButton ("Pointer");
            MyControlPanel.UnselectButton ("Wire");
            MyControlPanel.UnselectButton ("Junction");
            MyControlPanel.UnselectButton ("Text");
            MyControlPanel.SelectButton ("Help");
            MyControlPanel.UnselectButton ("Simulate");
            MyControlPanel.UnselectButton ("New");
            MyControlPanel.UnselectButton ("Open");
            MyControlPanel.UnselectButton ("Save");
        }
        if (HelpWanted) {
            HelpDialog MyHelpDialog = new HelpDialog(frame, "Help");
        } else {
            StatusMessage ("Choose a component, menu or button to get help about it.");
            HelpWanted = true;
        }
    }

//----------------------------------------------------------------------------
// User wants to start / stop the simulation.
//----------------------------------------------------------------------------
    public void UserWantsSimulate() {
        if (MyControlPanel != null) {
            MyControlPanel.UnselectButton ("Pointer");
            MyControlPanel.UnselectButton ("Wire");
            MyControlPanel.UnselectButton ("Junction");
            MyControlPanel.UnselectButton ("Text");
            MyControlPanel.UnselectButton ("Help");
            MyControlPanel.SelectButton ("Simulate");
            MyControlPanel.UnselectButton ("New");
            MyControlPanel.UnselectButton ("Open");
            MyControlPanel.UnselectButton ("Save");
        }
        if (HelpWanted) {
            HelpDialog MyHelpDialog = new HelpDialog(frame, "Simulate");
            UserWantsPointer();
            return;
        }
        if (!IsSimulating()) {
            if (MyControlPanel != null) {
                MyControlPanel.DisableButton ("Pointer");
                MyControlPanel.DisableButton ("Wire");
                MyControlPanel.DisableButton ("Junction");
                MyControlPanel.DisableButton ("Text");
                MyControlPanel.DisableButton ("New");
                MyControlPanel.DisableButton ("Open");
                MyControlPanel.DisableButton ("Save");
            }
            frame.DisableAllMenus();
            frame.StartMenuItem.disable();
            frame.StopMenuItem.enable();
            SimulateStart();
            repaint();
        } else {
            if (MyControlPanel != null) {
                MyControlPanel.EnableButton ("Pointer");
                MyControlPanel.EnableButton ("Wire");
                MyControlPanel.EnableButton ("Junction");
                MyControlPanel.EnableButton ("Text");
                MyControlPanel.EnableButton ("New");
                if (EnableFileOperations) {
                    MyControlPanel.EnableButton ("Open");
                    MyControlPanel.EnableButton ("Save");
                }
            }
            frame.EnableAllMenus();
            frame.StopMenuItem.disable();
            frame.StartMenuItem.enable();
            SimulateStop();
            UserWantsPointer();
            repaint();
        }
    }

//----------------------------------------------------------------------------
// User pressed the 'new' button or menuitem
//----------------------------------------------------------------------------
    public void UserWantsNewSchematic() {
        if (MySchematic == null) return;
        if (MyControlPanel != null) {
            MyControlPanel.UnselectButton ("Pointer");
            MyControlPanel.UnselectButton ("Wire");
            MyControlPanel.UnselectButton ("Junction");
            MyControlPanel.UnselectButton ("Text");
            MyControlPanel.UnselectButton ("Help");
            MyControlPanel.UnselectButton ("Simulate");
            MyControlPanel.SelectButton ("New");
            MyControlPanel.UnselectButton ("Open");
            MyControlPanel.UnselectButton ("Save");
        }
        if (HelpWanted) {
            HelpDialog MyHelpDialog = new HelpDialog(frame, "New");
            UserWantsPointer();
            return;
        } else {
            if (MySchematic.Modified) {
                if (frame.NewDialog == null) {
                    String DlgButtons[] = {"OK",  "Cancel"};
                    frame.NewDialog = new SimpleDialog(frame, "New schematic", "Discard changes?", DlgButtons, 2, 1, frame.NEWDIALOG_ID, SimpleDialog.IMAGE_WARNING);
                    UserWantsPointer();
                }
            } else {
                MySchematic.DestroyComponents(PinGrid); // Destroy schematic and remove components from grid.
                frame.setTitle ("Digital Simulator [untitled]");
                MySchematic.FileName = null;
                MySchematic.Modified = false;
                MySchematicPanel.repaint();
                UserWantsPointer();
                updateAnalyzer();
            }
        }
    }

//----------------------------------------------------------------------------
// User pressed the 'Open' button or menuitem.
//----------------------------------------------------------------------------
    public void UserWantsOpenSchematic() {
        Schematic OpenSchematic;
        if (MySchematic == null) return;
        if (MyControlPanel != null) {
            MyControlPanel.UnselectButton ("Pointer");
            MyControlPanel.UnselectButton ("Wire");
            MyControlPanel.UnselectButton ("Junction");
            MyControlPanel.UnselectButton ("Text");
            MyControlPanel.UnselectButton ("Help");
            MyControlPanel.UnselectButton ("Simulate");
            MyControlPanel.UnselectButton ("New");
            MyControlPanel.SelectButton ("Open");
            MyControlPanel.UnselectButton ("Save");
        }
        if (HelpWanted) {
            HelpDialog MyHelpDialog = new HelpDialog(frame, "Open");
            UserWantsPointer();
            return;
        } else {
            if (MySchematic.Modified) {
                if (frame.OpenDialog == null) {
                    String DlgButtons[] = {"OK",  "Cancel"};
                    frame.OpenDialog = new SimpleDialog(frame, "Open schematic", "Discard changes?", DlgButtons, 2, 1, frame.OPENDIALOG_ID, SimpleDialog.IMAGE_WARNING);
                    UserWantsPointer();
                }
            } else {
                OpenSchematic = frame.DoFileOpenDialog(PinGrid, "Open schematic");
                if (OpenSchematic != null) {
                    MySchematic = OpenSchematic;
                    frame.setTitle("Digital Simulator [" + MySchematic.FileName + "]");
                    MySchematicPanel.repaint();
                }
                UserWantsPointer();
            }
        }
    }

//----------------------------------------------------------------------------
// User clicked an Example (FileName)
//----------------------------------------------------------------------------
    public void UserWantsOpenExample(String FileName) {
        if (MySchematic == null) return;
        if (MySchematic.Modified) {
            if (frame.OpenExampleDialog == null) {
                String DlgButtons[] = {"OK",  "Cancel"};
                frame.ExampleFileName = FileName;
                frame.OpenExampleDialog = new SimpleDialog(frame, "Open example", "Discard changes?", DlgButtons, 2, 1, frame.OPENEXAMPLE_ID, SimpleDialog.IMAGE_WARNING);
                UserWantsPointer();
            }
        } else {
            frame.LoadExample(FileName);
            UserWantsPointer();
        }
    }

//----------------------------------------------------------------------------
// User pressed the 'save' button in the control panel.
//----------------------------------------------------------------------------
    public void UserWantsSaveSchematic() {
        if (MySchematic == null) return;
        if (MyControlPanel != null) {
            MyControlPanel.UnselectButton ("Pointer");
            MyControlPanel.UnselectButton ("Wire");
            MyControlPanel.UnselectButton ("Junction");
            MyControlPanel.UnselectButton ("Text");
            MyControlPanel.UnselectButton ("Help");
            MyControlPanel.UnselectButton ("Simulate");
            MyControlPanel.UnselectButton ("New");
            MyControlPanel.UnselectButton ("Open");
            MyControlPanel.SelectButton ("Save");
        }
        if (HelpWanted) {
            HelpDialog MyHelpDialog = new HelpDialog(frame, "Save");
            UserWantsPointer();
            return;
        } else {
            if (MySchematic.FileName != null &&
                MySchematic.FileDir != null) {
                frame.DoFileSaveDialog(MySchematic, false, "Save schematic"); // save
            } else {
                frame.DoFileSaveDialog(MySchematic, false, "Save schematic as"); // save as
            }
            frame.setTitle("Digital Simulator [" + MySchematic.FileName + "]");
            MySchematicPanel.repaint();
            UserWantsPointer();
            return;
        }
    }

//----------------------------------------------------------------------------
// User pressed the 'copy' button or menuitem.
//----------------------------------------------------------------------------
    public void UserWantsCopySchematic() {
        if (HelpWanted) {
            HelpDialog MyHelpDialog = new HelpDialog(frame, "Copy");
        } else {
            MySchematicPanel.Copy();
            frame.PasteMenuItem.enable();
            MyControlPanel.EnableButton ("Paste");
        }
        MyControlPanel.UnselectButton ("Copy");
        UserWantsPointer();
    }

//----------------------------------------------------------------------------
// User pressed the 'paste' button or menuitem.
//----------------------------------------------------------------------------
    public void UserWantsPasteSchematic() {
        if (HelpWanted) {
            HelpDialog MyHelpDialog = new HelpDialog(frame, "Paste");
        } else {
            MySchematicPanel.Paste();
        }
        MyControlPanel.UnselectButton ("Paste");
        UserWantsPointer();
    }

//----------------------------------------------------------------------------
// User pressed the 'cut' button or menuitem.
//----------------------------------------------------------------------------
    public void UserWantsCutSchematic() {
        if (HelpWanted) {
            HelpDialog MyHelpDialog = new HelpDialog(frame, "Cut");
        } else {
            MySchematicPanel.Cut();
            frame.CutMenuItem.disable();
            frame.CopyMenuItem.disable();
            frame.PasteMenuItem.enable();
            MyControlPanel.DisableButton ("Cut");
            MyControlPanel.DisableButton ("Copy");
            MyControlPanel.EnableButton ("Paste");
        }
        MyControlPanel.UnselectButton ("Cut");
        UserWantsPointer();
    }

//----------------------------------------------------------------------------
// User pressed the 'exampes' menuitem
//----------------------------------------------------------------------------
    public void UserWantsOpenExample() {
        if (HelpWanted) {
            HelpDialog MyHelpDialog = new HelpDialog(frame, "Open example");
        } else {
            if (MyExamplesFrame == null) {
                MyExamplesFrame = new ExamplesFrame(this);
            }
        }
    }

//----------------------------------------------------------------------------
// User pressed the 'copy to' menuitem.
//----------------------------------------------------------------------------
    public void UserWantsCopyToSchematic() {
         if (HelpWanted) {
            HelpDialog MyHelpDialog = new HelpDialog(frame, "CopyTo");
        } else {
            MySchematicPanel.CopyTo();
            frame.PasteMenuItem.enable();
            MyControlPanel.EnableButton ("Paste");
        }
        UserWantsPointer();
   }

//----------------------------------------------------------------------------
// User pressed the 'paste from' menuitem.
//----------------------------------------------------------------------------
    public void UserWantsPasteFromSchematic() {
        if (HelpWanted) {
            HelpDialog MyHelpDialog = new HelpDialog(frame, "PasteFrom");
        } else {
            MySchematicPanel.PasteFrom();
        }
        UserWantsPointer();
   }

//----------------------------------------------------------------------------
// User wants the options window
//----------------------------------------------------------------------------
    public void UserWantsOptions() {
        if (HelpWanted) {
            HelpDialog MyHelpDialog = new HelpDialog(frame, "Options");
        } else {
             if (MyOptionsFrame == null) {
                MyOptionsFrame = new OptionsFrame(this);
            }
       }
       UserWantsPointer();
    }

//----------------------------------------------------------------------------
// User wants the logic analyzer
//----------------------------------------------------------------------------
    public void UserWantsAnalyzer() {
        if (HelpWanted) {
            HelpDialog MyHelpDialog = new HelpDialog(frame, "Analyzer");
        } else {
             if (MyAnalyzerFrame == null) {
                MyAnalyzerFrame = new AnalyzerFrame(this);
            }
       }
    }

//----------------------------------------------------------------------------
// Update the analyzer data after a simulation cycle.
//----------------------------------------------------------------------------
    public void updateAnalyzer() {
        if (MyAnalyzerFrame != null) {
            MyAnalyzerFrame.repaint();
        }
    }
}