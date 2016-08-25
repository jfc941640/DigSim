//****************************************************************************
// ---- version information ----
//
// Schematic.java        v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for creating and handling a schematic
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
import java.util.Vector;
import java.io.StreamTokenizer;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.net.URL;
import java.lang.Math;

class Schematic {
    protected Vector Components;
    boolean Modified = false;
    String FileDir = null;
    String FileName = null;
    int SimulateCycleID = 0;

//----------------------------------------------------------------------------
// The Schematic constructor
//----------------------------------------------------------------------------
    public Schematic() {
        Components = new Vector();
    }

//----------------------------------------------------------------------------
// The Schematic constructor, called to copy an existing schematic
//----------------------------------------------------------------------------
    public Schematic(Schematic SchematicToCopy) {
        ElectronicComponent TempComponent;

        Components = new Vector();

        for (int ix = 0; ix < SchematicToCopy.size(); ix++) {
            TempComponent = (ElectronicComponent)SchematicToCopy.Components.elementAt(ix);
            Components.addElement (TempComponent);
        }
    }

//----------------------------------------------------------------------------
// The Schematic constructor, used to Paste
//----------------------------------------------------------------------------
    public Schematic PasteSchematic(Pin PinGrid[][], Schematic CopySchematic, int Xoff, int Yoff) {
        ElectronicComponent TempComponent, NewComponent;
        Schematic NewSelectSchematic = new Schematic();
        int ix;
        int MinX = -1, MinY = -1;
        int WireXMin, WireYMin;
        Wire TempWire;
        Modified = true;

        // System.out.println ("PasteSchematic");
        // First search for left-top component in CopySchematic
        for (ix = 0; ix < CopySchematic.size(); ix++) {
            TempComponent = (ElectronicComponent)CopySchematic.Components.elementAt(ix);
            if ((TempComponent instanceof Wire)) {
                TempWire = (Wire)TempComponent;
                WireXMin = Math.min(TempWire.x1, TempWire.x2);
                WireYMin = Math.min(TempWire.y1, TempWire.y2);
                if (MinX == -1 || WireXMin < MinX) {
                    MinX = WireXMin - 1;
                }
                if (MinY == -1 || WireYMin < MinY) {
                    MinY = WireYMin - 1;
                }
            } else {
                if (MinX == -1 || TempComponent.Pos.x < MinX) {
                    MinX = TempComponent.Pos.x;
                }
                if (MinY == -1 || TempComponent.Pos.y < MinY) {
                    MinY = TempComponent.Pos.y;
                }
            }
        }
        // System.out.println ("MinXY= " + MinX + ", " + MinY);
        // Now copy all components with Negative offset MinXY
        for (ix = 0; ix < CopySchematic.size(); ix++) {
            // System.out.println ("Copy component");
            TempComponent = (ElectronicComponent)CopySchematic.Components.elementAt(ix);
            NewComponent = TempComponent.Copy(MinX - Xoff , MinY - Yoff);
            NewComponent.PlacePinsHere(PinGrid); // Register all new IO pins
            TempComponent.Selected = false;
            NewComponent.Selected = true;
            if ((TempComponent instanceof Wire)) {
                TempWire = (Wire)NewComponent;
                TempWire.ChangingWire = 1 | 2;
            }
            addComponent (NewComponent);
            NewSelectSchematic.addComponent (NewComponent);
        }
        return NewSelectSchematic;
    }

//----------------------------------------------------------------------------
// Make all components selected
//----------------------------------------------------------------------------
    public void SetAllComponentsSelected() {
        ElectronicComponent TempComponent;
        Wire TempWire;

        for (int ix = 0; ix < Components.size(); ix++) {
            TempComponent = (ElectronicComponent)Components.elementAt(ix);
            TempComponent.Selected = true;
            if ((TempComponent instanceof Wire)) {
                TempWire = (Wire)TempComponent;
                TempWire.ChangingWire = 1 | 2;
            }
        }
    }

//----------------------------------------------------------------------------
// Remove the Same Components in both schematics
//----------------------------------------------------------------------------
    public void RemoveSameElements(Pin PinGrid[][], Schematic ActSchematic) {
        ElectronicComponent TempComponent;
        Modified = true;

        for (int ix = 0; ix < ActSchematic.size(); ix++) {
            TempComponent = (ElectronicComponent)ActSchematic.Components.elementAt(ix);
            if (Components.contains(TempComponent)) {
                TempComponent.RemovePinsGrid(PinGrid);
                Components.removeElement(TempComponent);
            }
        }
    }

//----------------------------------------------------------------------------
// Destroy all components in this schematic
//----------------------------------------------------------------------------
    public void DestroyComponents(Pin PinGrid[][]) {
        ElectronicComponent TempComponent;
        Modified = true;

        for (int ix = 0; ix < Components.size(); ix++) {
            TempComponent = (ElectronicComponent)Components.elementAt(ix);
            TempComponent.RemovePinsGrid(PinGrid);
        }
        Components.removeAllElements();
    }

//----------------------------------------------------------------------------
// add an electronic component
//----------------------------------------------------------------------------
    public void addComponent (ElectronicComponent NewComponent) {
        Modified = true;
        Components.addElement(NewComponent);
    }

//----------------------------------------------------------------------------
// draw this schematic
//----------------------------------------------------------------------------
    public void draw(Graphics g, int x, int y, int GridStep) {
        ElectronicComponent TempComponent;

        for (int ix = 0; ix < Components.size(); ix++) {
            TempComponent = (ElectronicComponent)Components.elementAt(ix);
            TempComponent.draw(g, x, y, GridStep);
        }
    }

//----------------------------------------------------------------------------
// return the total number of components in this schematic
//----------------------------------------------------------------------------
    public int size() {
        return Components.size();
    }

//----------------------------------------------------------------------------
// Remove all components in this schematic
//----------------------------------------------------------------------------
    public void RemoveAllComponents() {
        ElectronicComponent TempComponent;
        Modified = true;

        for (int ix = 0; ix < Components.size(); ix++) {
            TempComponent = (ElectronicComponent)Components.elementAt(ix);
            TempComponent.Selected = false;
        }
        Components.removeAllElements();
    }

//----------------------------------------------------------------------------
// Check if the specified component is a member of the schematic
//----------------------------------------------------------------------------
    public boolean InSchematic(ElectronicComponent ActComponent) {
        return Components.contains(ActComponent);
    }

//----------------------------------------------------------------------------
// Check if there are component(s) in the schematic, and if so, put them
// in the SelectSchematic.
//----------------------------------------------------------------------------
    public void CheckIfComponentsInSelectBox(Schematic SelectSchematic, int x1, int y1, int x2, int y2) {
        ElectronicComponent TempComponent;

        int swap;
        if (x1 > x2) {
            swap = x1;
            x1 = x2;
            x2 = swap;
        }
        if (y1 > y2) {
            swap = y1;
            y1 = y2;
            y2 = swap;
        }

        for (int ix = 0; ix < Components.size(); ix++) {
            TempComponent = (ElectronicComponent)Components.elementAt(ix);
            if (TempComponent.CheckIfComponentInSelectBox(x1, y1, x2, y2)) {
//              System.out.println ("Component added to SelectSchematic");
                SelectSchematic.addComponent(TempComponent);
            }
        }
    }

//----------------------------------------------------------------------------
// Adjust the position of all components
//----------------------------------------------------------------------------
    public void AdjustPosition (Pin PinGrid[][], int x, int y) {
        ElectronicComponent TempComponent;

        for (int ix = 0; ix < Components.size(); ix++) {
            TempComponent = (ElectronicComponent)Components.elementAt(ix);
            TempComponent.AdjustPosition (PinGrid, x, y);
        }
    }

//----------------------------------------------------------------------------
// Remove all components from the PinGrid
//----------------------------------------------------------------------------
    public void RemovePinsGrid(Pin PinGrid[][]) {
        ElectronicComponent TempComponent;

        for (int ix = 0; ix < Components.size(); ix++) {
            TempComponent = (ElectronicComponent)Components.elementAt(ix);
            TempComponent.RemovePinsGrid(PinGrid);
        }
    }

//----------------------------------------------------------------------------
// Check if the position of all components are legal, and if not,
// adjust them.
//----------------------------------------------------------------------------
    public void CheckPosition() {
        ElectronicComponent TempComponent;

        for (int ix = 0; ix < Components.size(); ix++) {
            TempComponent = (ElectronicComponent)Components.elementAt(ix);
            TempComponent.CheckPosition();
        }
    }

//----------------------------------------------------------------------------
// Place all pins of all components in the PinGrid.
//----------------------------------------------------------------------------
    public void PlacePinsHere(Pin PinGrid[][]) {
        ElectronicComponent TempComponent;

        for (int ix = 0; ix < Components.size(); ix++) {
            TempComponent = (ElectronicComponent)Components.elementAt(ix);
            TempComponent.PlacePinsHere(PinGrid);
        }
    }

//----------------------------------------------------------------------------
// Check if there is an Component body at position x, y which could be
// clicked
//----------------------------------------------------------------------------
    public ElectronicComponent CheckIfComponentClicked(int x, int y) {
        ElectronicComponent TempComponent;

        for (int ix = 0; ix < Components.size(); ix++) {
            TempComponent = (ElectronicComponent)Components.elementAt(ix);
            if (TempComponent.CheckIfComponentClicked(x, y)) {

                return TempComponent;
            }
        }
        return null;    // there's no component clicked.
    }

//----------------------------------------------------------------------------
// Do a simulation cycle
//----------------------------------------------------------------------------
    public void Simulate() {
        ElectronicComponent TempComponent;
//      System.out.println ("Schematic.Simulate()");

        for (int ix = 0; ix < Components.size(); ix++) {
            TempComponent = (ElectronicComponent)Components.elementAt(ix);
            SimulateCycleID++;
            if (SimulateCycleID == -1) SimulateCycleID++;
            TempComponent.Simulate(SimulateCycleID);
        }
//      System.out.println ("Schematic.Simulate() ready.");
    }

//----------------------------------------------------------------------------
// Initialize components before a simulation cycle
//----------------------------------------------------------------------------
    public void InitBeforeSimulate() {
        ElectronicComponent TempComponent;
//        System.out.println ("Schematic.InitBeforeSimulate()");

        for (int ix = 0; ix < Components.size(); ix++) {
            TempComponent = (ElectronicComponent)Components.elementAt(ix);
            TempComponent.InitBeforeSimulate();
        }
//        System.out.println ("Schematic.InitBeforeSimulate() ready.");
    }

//----------------------------------------------------------------------------
// Convert a String to a Color
//----------------------------------------------------------------------------
    protected Color StringToColor(String clr) {
        if ("red".equals(clr)) {
            return Color.red;
        } else if ("green".equals(clr)) {
            return Color.green;
        } else if ("blue".equals(clr)) {
            return Color.blue;
        } else if ("yellow".equals(clr)) {
            return Color.yellow;
        } else if ("pink".equals(clr)) {
            return Color.pink;
        } else if ("black".equals(clr)) {
            return Color.black;
        } else if ("white".equals(clr)) {
            return Color.white;
        } else if ("gray".equals(clr)) {
            return Color.gray;
        }
        return Color.red;   // Unknown color
    }

//----------------------------------------------------------------------------
// Load a component from a file
//----------------------------------------------------------------------------
    public boolean LoadComponent (Pin PinGrid[][], InputStream is, StreamTokenizer st) throws IOException, FileFormatException {
        int XPos = 0, YPos = 0;
        int XPos2 = 0, YPos2 = 0;
        String ComponentName = st.sval;
        ElectronicComponent NewComponent;
        Color ComponentColor = Color.red;
        String Text = "";
        int c;
        // System.out.println ("Load component [" + st.sval + "]" );
scan:
        while (true) {
            switch (st.nextToken()) {
            case StreamTokenizer.TT_EOL:
                break;
            case StreamTokenizer.TT_WORD:
                if ("pos".equals(st.sval)) {
                    if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
                        XPos = (int)st.nval;
                        if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
                            YPos = (int)st.nval;
                        }
                    }
                    // Skip other words on position line
                    while (st.ttype != StreamTokenizer.TT_EOL &&
                      st.ttype != StreamTokenizer.TT_EOF) {
                        st.nextToken();
                    }
                    // System.out.println ("Component position: " + XPos + ", " + YPos);
                } else if ("color".equals(st.sval)) {
                    if (st.nextToken() == StreamTokenizer.TT_WORD) {
                        ComponentColor = StringToColor(st.sval);
                    }
                } else if ("Text".equals(st.sval)) {
                    // Read the rest of this line as Text
                    Text = "";
                    while ((c = is.read()) != StreamTokenizer.TT_EOL) {
                        // System.out.println ("Read:" + c);
                        Text += (char)c;
                    }
                } else if ("pos2".equals(st.sval)) {
                    if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
                        XPos2 = (int)st.nval;
                        if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
                            YPos2 = (int)st.nval;
                        }
                    }
                    // Skip other words on position line
                    while (st.ttype != StreamTokenizer.TT_EOL &&
                      st.ttype != StreamTokenizer.TT_EOF) {
                        st.nextToken();
                    }
                    // System.out.println ("Component position2: " + XPos2 + ", " + YPos2);

                } else if ("end".equals(st.sval)) {
                    if (st.nextToken() == StreamTokenizer.TT_WORD) {
                        if ("describe".equals(st.sval)) {
                            // System.out.println ("End describe");
                            if ("Vcc".equals(ComponentName)) {
                                addComponent(new Vcc(PinGrid, XPos, YPos));
                            } else if ("GND".equals(ComponentName)) {
                                addComponent(new GND(PinGrid, XPos, YPos));
                            } else if ("Wire".equals(ComponentName)) {
                                addComponent(new Wire(PinGrid, XPos, YPos, XPos2, YPos2));

                            } else if ("Buffer".equals(ComponentName)) {
                                addComponent(new Buffer(PinGrid, XPos, YPos));
                            } else if ("Inverter".equals(ComponentName)) {
                                addComponent(new Inverter(PinGrid, XPos, YPos));
                            } else if ("TwoAndPort".equals(ComponentName)) {
                                addComponent(new TwoAndPort(PinGrid, XPos, YPos));
                            } else if ("ThreeAndPort".equals(ComponentName)) {
                                addComponent(new ThreeAndPort(PinGrid, XPos, YPos));
                            } else if ("TwoNandPort".equals(ComponentName)) {
                                addComponent(new TwoNandPort(PinGrid, XPos, YPos));
                            } else if ("ThreeNandPort".equals(ComponentName)) {
                                addComponent(new ThreeNandPort(PinGrid, XPos, YPos));
                            } else if ("TwoOrPort".equals(ComponentName)) {
                                addComponent(new TwoOrPort(PinGrid, XPos, YPos));
                            } else if ("ThreeOrPort".equals(ComponentName)) {
                                addComponent(new ThreeOrPort(PinGrid, XPos, YPos));
                            } else if ("TwoNorPort".equals(ComponentName)) {
                                addComponent(new TwoNorPort(PinGrid, XPos, YPos));
                            } else if ("ThreeNorPort".equals(ComponentName)) {
                                addComponent(new ThreeNorPort(PinGrid, XPos, YPos));
                            } else if ("TwoXorPort".equals(ComponentName)) {
                                addComponent(new TwoXorPort(PinGrid, XPos, YPos));
                            } else if ("TwoXnorPort".equals(ComponentName)) {
                                addComponent(new TwoXnorPort(PinGrid, XPos, YPos));

                            } else if ("Switch".equals(ComponentName)) {
                                addComponent(new Switch(PinGrid, XPos, YPos));
                            } else if ("PushButton".equals(ComponentName)) {
                                addComponent(new PushButton(PinGrid, XPos, YPos));

                            } else if ("SRLatch".equals(ComponentName)) {
                                addComponent(new SRLatch(PinGrid, XPos, YPos));
                            } else if ("GatedSRLatch".equals(ComponentName)) {
                                addComponent(new GatedSRLatch(PinGrid, XPos, YPos));
                            } else if ("DLatch".equals(ComponentName)) {
                                addComponent(new DLatch(PinGrid, XPos, YPos));
                            } else if ("DFlipFlop".equals(ComponentName)) {
                                addComponent(new DFlipFlop(PinGrid, XPos, YPos));
                            } else if ("JKFlipFlop".equals(ComponentName)) {
                                addComponent(new JKFlipFlop(PinGrid, XPos, YPos));
                            } else if ("EdgeTriggeredTFlipFlop".equals(ComponentName)) {
                                addComponent(new EdgeTriggeredTFlipFlop(PinGrid, XPos, YPos));
                            } else if ("TFlipFlop".equals(ComponentName)) {
                                addComponent(new TFlipFlop(PinGrid, XPos, YPos));
                            } else if ("OctalDFlipFlop".equals(ComponentName)) {
                                addComponent(new OctalDFlipFlop(PinGrid, XPos, YPos));
                            } else if ("OctalLatch".equals(ComponentName)) {
                                addComponent(new OctalLatch(PinGrid, XPos, YPos));

                            } else if ("Oscilator".equals(ComponentName)) {
                                addComponent(new Oscilator(PinGrid, XPos, YPos));
                            } else if ("BCDToSevenSegDecoder".equals(ComponentName)) {
                                addComponent(new BCDToSevenSegDecoder(PinGrid, XPos, YPos));
                            } else if ("ThreeToEightLineDecoder".equals(ComponentName)) {
                                addComponent(new ThreeToEightLineDecoder(PinGrid, XPos, YPos));
                            } else if ("FourBitBinaryCounter".equals(ComponentName)) {
                                addComponent(new FourBitBinaryCounter(PinGrid, XPos, YPos));
                            } else if ("EightBitSerInShiftReg".equals(ComponentName)) {
                                addComponent(new EightBitSerInShiftReg(PinGrid, XPos, YPos));
                            } else if ("EightBitParInShiftReg".equals(ComponentName)) {
                                addComponent(new EightBitParInShiftReg(PinGrid, XPos, YPos));

                            } else if ("Caption".equals(ComponentName)) {
                                addComponent(new Caption(XPos, YPos, Text));

                            } else if ("LED".equals(ComponentName)) {
                                addComponent(new LED(PinGrid, ComponentColor, XPos, YPos));
                            } else if ("BiColorLED".equals(ComponentName)) {
                                addComponent(new BiColorLED(PinGrid, XPos, YPos));
                            } else if ("SevenSegmentDisplay".equals(ComponentName)) {
                                addComponent(new SevenSegmentDisplay(PinGrid, ComponentColor, XPos, YPos));
                            } else if ("Probe".equals(ComponentName)) {
                                addComponent(new Probe(PinGrid, XPos, YPos, Text, false, false));
                            } else if ("ProbeUp".equals(ComponentName)) {
                                addComponent(new Probe(PinGrid, XPos, YPos, Text, true, false));
                            } else if ("ProbeDn".equals(ComponentName)) {
                                addComponent(new Probe(PinGrid, XPos, YPos, Text, false, true));

                            } else {
                                return false;   // Unknown component
                            }
                            return true;
                        }
                    }
                }
                break;

            default:
                break scan;
            }
        }
        return true;
    }

//----------------------------------------------------------------------------
// Create a schematic by parsing an input stream
//----------------------------------------------------------------------------
    Schematic (Pin PinGrid[][], InputStream is) throws IOException, FileFormatException {
        this();
        int FileVersionHigh = 0, FileVersionLow = 0;
        String message;
        boolean SchematicCorrupt = false;
        StreamTokenizer st = new StreamTokenizer(is);

        // System.out.println ("Schematic open");
        st.eolIsSignificant(true);
        st.commentChar('#');
scan:
        while (true) {
            switch (st.nextToken()) {
            case StreamTokenizer.TT_EOL:
                break;
            case StreamTokenizer.TT_WORD:
                if ("version".equals(st.sval)) {
                    if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
                        FileVersionHigh = (int)st.nval;
                        if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
                            FileVersionLow = (int)st.nval;
                        }
                    }
                    // Skip other words on version line
                    while (st.ttype != StreamTokenizer.TT_EOL &&
                      st.ttype != StreamTokenizer.TT_EOF) {
                        st.nextToken();
                    }
                    // System.out.println ("File version " + FileVersionHigh + "." + FileVersionLow);
                } else if ("describe".equals(st.sval)) {
                    if (st.nextToken() == StreamTokenizer.TT_WORD) {
                        if ("component".equals(st.sval)) {
                            if (st.nextToken() == StreamTokenizer.TT_WORD) {
//                              System.out.println ("Describe component [" + st.sval + "]" );
                                if (!LoadComponent (PinGrid, is, st)) {
                                    SchematicCorrupt = true;
                                }
                            }
                        }
                    }
                } else {
                    SchematicCorrupt = true;
                }
                break;
            default:
                break scan;
            }
        }
        is.close();
        if (st.ttype != StreamTokenizer.TT_EOF) {
            throw new FileFormatException(st.toString());
        }
        Modified = false;
        if (SchematicCorrupt) {
            String DlgButtons[] = { "OK" };
            message = "Unknown data in schematic. Schematic could be corrupt.";
            SimpleDialog ExceptionDialog = new SimpleDialog(null, "Loading schematic", message, DlgButtons, 1, 0, 0, SimpleDialog.IMAGE_WARNING);
        }
    }

//----------------------------------------------------------------------------
// Save the schematic to the specified output stream.
//----------------------------------------------------------------------------
    public void Save (FileOutputStream os) throws IOException, FileFormatException {
        ElectronicComponent TempComponent;
        PrintStream myPrintStream = new PrintStream(os);
        // System.out.println ("Schematic() save");
        myPrintStream.println ("# Digsim file");
        myPrintStream.println ("version 1 0");

        for (int ix = 0; ix < Components.size(); ix++) {
            TempComponent = (ElectronicComponent)Components.elementAt(ix);
            TempComponent.Save (myPrintStream);
        }

        os.close();
    }

//----------------------------------------------------------------------------
// Plave a junction at the specified Position, if possible.
// A junction is made by splitting a Wire in two parts.
//----------------------------------------------------------------------------
    public boolean PlaceJunction(Pin PinGrid[][], int x, int y, int gs) {
        ElectronicComponent TempComponent;
        Wire TempWire;
        boolean JunctionPlaced = false;
        // System.out.println ("Place junction pos " + x + ", " + y);

        for (int ix = 0; ix < Components.size(); ix++) {
            TempComponent = (ElectronicComponent)Components.elementAt(ix);
            if (TempComponent instanceof Wire) {
                TempWire = (Wire)TempComponent;
                if (TempWire.TryPlaceJunction(this, PinGrid, x, y, gs)) {
                    JunctionPlaced = true;
                }
            }
        }
        return JunctionPlaced;
    }

//----------------------------------------------------------------------------
// Test if there occured a Short circuit in this schematic
//----------------------------------------------------------------------------
    public ElectronicComponent TestShortCircuit() {
        ElectronicComponent TempComponent;
        int o;

        for (int ix = 0; ix < Components.size(); ix++) {
            TempComponent = (ElectronicComponent)Components.elementAt(ix);
            for (o = 0; o < TempComponent.Outputs; o++) {
                if (TempComponent.OPin[o].ShortCircuit) return TempComponent;
            }
        }
        return null;
    }

//----------------------------------------------------------------------------
// Test if there occured a Loop in this schematic
//----------------------------------------------------------------------------
    public ElectronicComponent TestLoop() {
        ElectronicComponent TempComponent;
        int i;

        for (int ix = 0; ix < Components.size(); ix++) {
            TempComponent = (ElectronicComponent)Components.elementAt(ix);
            for (i = 0; i < TempComponent.Inputs; i++) {
                if (TempComponent.IPin[i].Looping) return TempComponent;
            }
        }
        return null;
    }

//----------------------------------------------------------------------------
// Get a vector with all analyzer probes in this schematic
//----------------------------------------------------------------------------
    public Vector getProbes() {
        ElectronicComponent TempComponent;
        Vector v = new Vector();

        for (int ix = 0; ix < Components.size(); ix++) {
            TempComponent = (ElectronicComponent)Components.elementAt(ix);
            if (TempComponent instanceof Probe) {
                v.addElement (TempComponent);
            }
        }
        return v;
    }

//----------------------------------------------------------------------------
// Swap two components in the schematic.
// It's used in the analyzer when the user swaps two probes.
//----------------------------------------------------------------------------
    public void SwapComponents(ElectronicComponent c1, ElectronicComponent c2) {
        int ix1 = Components.indexOf(c1);
        int ix2 = Components.indexOf(c2);
        if (ix1 == -1 || ix2 == -1) return;
        Components.setElementAt(c1, ix2);
        Components.setElementAt(c2, ix1);
    }

//----------------------------------------------------------------------------
// Check if there are probes in this schematic
//----------------------------------------------------------------------------
    public boolean ProbesInSchematic() {
        ElectronicComponent TempComponent;

        for (int ix = 0; ix < Components.size(); ix++) {
            TempComponent = (ElectronicComponent)Components.elementAt(ix);
            if (TempComponent instanceof Probe) {
                return true;
            }
        }
        return false;
    }
}