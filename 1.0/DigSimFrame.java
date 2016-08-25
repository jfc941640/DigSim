//****************************************************************************
// ---- version information ----
//
// DigSimFrame.java      v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for the Main simulator frame,
// which contains three panels:
//
// - The imagebuttons.
// - The schematic panel.
// - The status panel.
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
import java.awt.*;
import java.util.Vector;
import java.lang.Integer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.io.InputStream;

class DigSimFrame extends Frame {
    Vector AvailableComponents;
    DigSim applet;
    Menu FileMenu;
    Menu EditMenu;
    Menu PassiveMenu;
    Menu PortMenu;
    Menu BiStableMenu;
    Menu DisplayMenu;
    Menu SpecialMenu;
    Menu SimulateMenu;
    Menu HelpMenu;
    MenuBar MyMenuBar;
    MenuItem CutMenuItem;
    MenuItem CopyMenuItem;
    MenuItem PasteMenuItem;
    MenuItem CopyDiskMenuItem;
    MenuItem PasteDiskMenuItem;
    MenuItem StartMenuItem;
    MenuItem StopMenuItem;
    SimpleDialog ExitDialog = null;
    SimpleDialog NewDialog = null;
    SimpleDialog OpenDialog = null;
    SimpleDialog OpenExampleDialog = null;
    TextDialog MyTextDialog = null;
    TextDialog MyTextChangeDialog = null;
    TextDialog MyTextProbeChangeDialog = null;
    Vector MenuItemsToDisable;

    static final int EXITDIALOG_ID = 1;
    static final int NEWDIALOG_ID = 2;
    static final int OPENDIALOG_ID = 3;
    static final int NEWTEXTDIALOG_ID = 5;
    static final int CHANGETEXTDIALOG_ID = 6;
    static final int OPENEXAMPLE_ID = 7;
    static final int CHANGETEXTPROBEDIALOG_ID = 8;

    String SaveFileDirectory = null;
    String SaveFileName = null;

    String ExampleFileName = null;

//----------------------------------------------------------------------------
// Set up the Simulator Frame with three Panels.
//----------------------------------------------------------------------------
    public DigSimFrame( DigSim app) {
        super("Digital Simulator");
        MenuItemsToDisable = new Vector();
        applet = app;
        setLayout(new BorderLayout());
        add("North", applet.MyControlPanel = new ControlPanel(app));
        add("Center", applet.MySchematicPanel = new SchematicPanel(app));
        add("South", applet.MyStatusPanel = new StatusPanel(app));
        RegisterComponentNames();
        setMenuBar(DigSimMenuBar());
        DisableAllMenus();
        resize(500, 400);
        show();
        resize(500, 400);
    }

//----------------------------------------------------------------------------
// Disable all menus in the MenuItemsToDisable vector
// when simulation is in progress
//----------------------------------------------------------------------------
    public void DisableAllMenus() {
        MenuItem TempMenuItem;

        for (int ix = 0; ix < MenuItemsToDisable.size(); ix++) {
            TempMenuItem = (MenuItem)MenuItemsToDisable.elementAt(ix);
            TempMenuItem.disable();
        }
    }

//----------------------------------------------------------------------------
// Enable all menus in the MenuItemsToDisable vector after simulating.
//----------------------------------------------------------------------------
    public void EnableAllMenus() {
         MenuItem TempMenuItem;

        for (int ix = 0; ix < MenuItemsToDisable.size(); ix++) {
            TempMenuItem = (MenuItem)MenuItemsToDisable.elementAt(ix);
            TempMenuItem.enable();
        }

        CutMenuItem.disable(); // After simulate no components are selected
        CopyMenuItem.disable();
        applet.MyControlPanel.DisableButton ("Cut");
        applet.MyControlPanel.DisableButton ("Copy");

        if (applet.MySchematicPanel.CopySchematic != null && applet.MySchematicPanel.CopySchematic.size() > 0) { // Components in copy buffer?
            PasteMenuItem.enable();
            applet.MyControlPanel.EnableButton ("Paste");
        } else {
            PasteMenuItem.disable();
            applet.MyControlPanel.DisableButton ("Paste");
        }
    }

//----------------------------------------------------------------------------
// Register all component names. It's used to lookup a component name
// when the user wants to add a component.
//----------------------------------------------------------------------------
    public void RegisterComponentNames() {
        AvailableComponents = new Vector();
        AvailableComponents.addElement("Wire");
        AvailableComponents.addElement("Junction");
        AvailableComponents.addElement("Vcc");
        AvailableComponents.addElement("GND");
        AvailableComponents.addElement("Switch");
        AvailableComponents.addElement("Push button");

        AvailableComponents.addElement("Buffer");
        AvailableComponents.addElement("Inverter");
        AvailableComponents.addElement("2-input AND port");
        AvailableComponents.addElement("3-input AND port");
        AvailableComponents.addElement("2-input OR port");
        AvailableComponents.addElement("3-input OR port");
        AvailableComponents.addElement("2-input XOR port");

        AvailableComponents.addElement("2-input NAND port");
        AvailableComponents.addElement("3-input NAND port");
        AvailableComponents.addElement("2-input NOR port");
        AvailableComponents.addElement("3-input NOR port");
        AvailableComponents.addElement("2-input XNOR port");

        AvailableComponents.addElement("SR-latch");
        AvailableComponents.addElement("Gated SR-latch");
        AvailableComponents.addElement("D-latch");

        AvailableComponents.addElement("D-flipflop");
        AvailableComponents.addElement("T-flipflop");
        AvailableComponents.addElement("JK-flipflop");
        AvailableComponents.addElement("Edge-triggered T-flipflop");
        AvailableComponents.addElement("Octal D-flipflop");
        AvailableComponents.addElement("Octal latch");

        AvailableComponents.addElement("Red LED");
        AvailableComponents.addElement("Green LED");
        AvailableComponents.addElement("Yellow LED");
        AvailableComponents.addElement("Bi-color LED");
        AvailableComponents.addElement("7-segment display");

        AvailableComponents.addElement("Oscilator");
        AvailableComponents.addElement("Analyzer probe");
        AvailableComponents.addElement("BCD to 7-segment decoder");
        AvailableComponents.addElement("3- to 8-line decoder");
        AvailableComponents.addElement("4-bit binary counter");
        AvailableComponents.addElement("8-bit serial in shift register");
        AvailableComponents.addElement("8-bit parallel in shift register");
    }

//----------------------------------------------------------------------------
// Set up all menus.
//----------------------------------------------------------------------------
    public MenuBar DigSimMenuBar() {
        MenuItem mi;
        MyMenuBar = new MenuBar();
        FileMenu = new Menu("File",false);
        MenuItemsToDisable.addElement (mi = new MenuItem ("New") );
        FileMenu.add(mi);
        FileMenu.addSeparator();
        MenuItemsToDisable.addElement (mi = new MenuItem ("Open example") );
        FileMenu.add(mi);
        if (applet.EnableFileOperations) {
            MenuItemsToDisable.addElement (mi = new MenuItem ("Open file") );
            FileMenu.add(mi);
            MenuItemsToDisable.addElement (mi = new MenuItem ("Save") );
            FileMenu.add(mi);
            MenuItemsToDisable.addElement (mi = new MenuItem ("Save as") );
            FileMenu.add(mi);
        } else {
            FileMenu.add(mi = new MenuItem ("Open"));
            mi.disable();
            FileMenu.add(mi = new MenuItem ("Save"));
            mi.disable();
            FileMenu.add(mi = new MenuItem ("Save as"));
            mi.disable();
        }
        FileMenu.addSeparator();
        FileMenu.add(new MenuItem("Close"));
        FileMenu.add(new MenuItem("Exit"));
        MyMenuBar.add(FileMenu);

        EditMenu = new Menu("Edit",false);
        MenuItemsToDisable.addElement (CutMenuItem = new MenuItem ("Cut") );
        EditMenu.add(CutMenuItem);
        MenuItemsToDisable.addElement (CopyMenuItem = new MenuItem ("Copy") );
        EditMenu.add(CopyMenuItem);
        MenuItemsToDisable.addElement (PasteMenuItem = new MenuItem ("Paste") );
        EditMenu.add(PasteMenuItem);
        MenuItemsToDisable.addElement (mi = new MenuItem ("Select All") );
        EditMenu.add(mi);
        EditMenu.addSeparator();
        if (applet.EnableFileOperations) {
            MenuItemsToDisable.addElement (CopyDiskMenuItem = new MenuItem ("Copy to") );
            EditMenu.add(CopyDiskMenuItem);
            MenuItemsToDisable.addElement (PasteDiskMenuItem = new MenuItem ("Paste from") );
            EditMenu.add(PasteDiskMenuItem);
        } else {
            EditMenu.add (CopyDiskMenuItem = new MenuItem ("Copy to") );
            CopyDiskMenuItem.disable();
            EditMenu.add (PasteDiskMenuItem = new MenuItem ("Paste from") );
            PasteDiskMenuItem.disable();
        }

        CutMenuItem.disable();
        CopyMenuItem.disable();
        PasteMenuItem.disable();
        CopyDiskMenuItem.disable();
        MyMenuBar.add(EditMenu);

        PassiveMenu = new Menu("Passive",false);
        MenuItemsToDisable.addElement (mi = new MenuItem ("Wire") );
        PassiveMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("Junction") );
        PassiveMenu.add(mi);
        PassiveMenu.addSeparator();
        MenuItemsToDisable.addElement (mi = new MenuItem ("Vcc") );
        PassiveMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("GND") );
        PassiveMenu.add(mi);
        PassiveMenu.addSeparator();
        MenuItemsToDisable.addElement (mi = new MenuItem ("Switch") );
        PassiveMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("Push button") );
        PassiveMenu.add(mi);
        MyMenuBar.add(PassiveMenu);

        PortMenu = new Menu("Ports",false);
        MenuItemsToDisable.addElement (mi = new MenuItem ("Buffer") );
        PortMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("Inverter") );
        PortMenu.add(mi);
        PortMenu.addSeparator();
        MenuItemsToDisable.addElement (mi = new MenuItem ("2-input AND port") );
        PortMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("3-input AND port") );
        PortMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("2-input OR port") );
        PortMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("3-input OR port") );
        PortMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("2-input XOR port") );
        PortMenu.add(mi);
        PortMenu.addSeparator();
        MenuItemsToDisable.addElement (mi = new MenuItem ("2-input NAND port") );
        PortMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("3-input NAND port") );
        PortMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("2-input NOR port") );
        PortMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("3-input NOR port") );
        PortMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("2-input XNOR port") );
        PortMenu.add(mi);
        MyMenuBar.add(PortMenu);

        BiStableMenu = new Menu("Bi-stable",false);
        MenuItemsToDisable.addElement (mi = new MenuItem ("SR-latch") );
        BiStableMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("Gated SR-latch") );
        BiStableMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("D-latch") );
        BiStableMenu.add(mi);
        BiStableMenu.addSeparator();
        MenuItemsToDisable.addElement (mi = new MenuItem ("D-flipflop") );
        BiStableMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("T-flipflop") );
        BiStableMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("JK-flipflop") );
        BiStableMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("Edge-triggered T-flipflop") );
        BiStableMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("Octal D-flipflop") );
        BiStableMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("Octal latch") );
        BiStableMenu.add(mi);
        MyMenuBar.add(BiStableMenu);

        DisplayMenu = new Menu("Display",false);
        MenuItemsToDisable.addElement (mi = new MenuItem ("Red LED") );
        DisplayMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("Green LED") );
        DisplayMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("Yellow LED") );
        DisplayMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("Bi-color LED") );
        DisplayMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("7-segment display") );
        DisplayMenu.add(mi);
        MyMenuBar.add(DisplayMenu);

        SpecialMenu = new Menu("Special",false);
        MenuItemsToDisable.addElement (mi = new MenuItem ("Oscilator") );
        SpecialMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("BCD to 7-segment decoder") );
        SpecialMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("3- to 8-line decoder") );
        SpecialMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("4-bit binary counter") );
        SpecialMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("8-bit serial in shift register") );
        SpecialMenu.add(mi);
        MenuItemsToDisable.addElement (mi = new MenuItem ("8-bit parallel in shift register") );
        SpecialMenu.add(mi);

        MenuItemsToDisable.addElement (mi = new MenuItem ("Analyzer probe") );
        SpecialMenu.add(mi);
        SpecialMenu.addSeparator();
        MenuItemsToDisable.addElement (mi = new MenuItem ("Text") );
        SpecialMenu.add(mi);
        MyMenuBar.add(SpecialMenu);

        SimulateMenu = new Menu("Simulate",false);
        SimulateMenu.add(StartMenuItem = new MenuItem("Start"));
        SimulateMenu.add(StopMenuItem = new MenuItem("Stop"));
        StopMenuItem.disable();
        SimulateMenu.addSeparator();
        SimulateMenu.add(new MenuItem("Show analyzer"));
        SimulateMenu.addSeparator();
        MenuItemsToDisable.addElement (mi = new MenuItem ("Options") );
        SimulateMenu.add(mi);
        MyMenuBar.add(SimulateMenu);

        HelpMenu = new Menu("Help",false);
        HelpMenu.add(new MenuItem("Help"));
        HelpMenu.add(new MenuItem("About DigSim"));
        HelpMenu.add(new MenuItem("Frequently asked questions"));
        MyMenuBar.add(HelpMenu);
        MyMenuBar.setHelpMenu(HelpMenu);

        return(MyMenuBar);
    }

//----------------------------------------------------------------------------
// Check if 'label' is a component name
//----------------------------------------------------------------------------
    public boolean IsComponentName(String label) {
        for (int ix = 0; ix < AvailableComponents.size(); ix++) {
            if (AvailableComponents.elementAt(ix).equals(label)) return true;
        }
        return false;
    }

//----------------------------------------------------------------------------
// Handle events
//----------------------------------------------------------------------------
    public boolean handleEvent(Event ev) {
        if (ev.id == Event.WINDOW_DESTROY) {
            //System.out.println("destroy window");
            applet.destroyFrame();
            return true;
        }
        return super.handleEvent(ev);
    }

//----------------------------------------------------------------------------
// Handle all events in the Simple Dialogs.
//----------------------------------------------------------------------------
    public boolean SimpleDialogHandler(String label) {
        String strip = label.substring("SIMPLEDIALOG_".length());
        String Command = strip.substring(0, strip.indexOf("_"));
        int ID = Integer.parseInt(strip.substring (strip.indexOf("_") + 1, strip.length()));
        Schematic OpenSchematic;

        switch (ID) {
            case EXITDIALOG_ID:
                ExitDialog = null;
                if (Command.equals("OK")) {
                    if (applet.SimulateRunning()) {
                        applet.SimulateStop();
                    }
                    System.exit(1);
                    return true;
                } else if (Command.equals("Cancel")) {
                    return true;
                }
                break;

            case NEWDIALOG_ID:
                NewDialog = null;
                if (Command.equals("OK")) {
                    applet.MySchematic.DestroyComponents(applet.PinGrid); // Destroy schematic and remove components from grid.
                    setTitle ("Digital Simulator [untitled]");
                    applet.MySchematic.FileName = null;
                    applet.MySchematic.Modified = false;
                    applet.MySchematicPanel.repaint();
                    return true;
                } else if (Command.equals("Cancel")) {
                    return true;
                }
                break;

            case OPENDIALOG_ID:
                OpenDialog = null;
                if (Command.equals("OK")) {
                    OpenSchematic = DoFileOpenDialog(applet.PinGrid, "Open schematic");
                    if (OpenSchematic != null) {
                        applet.MySchematic = OpenSchematic;
                        setTitle("Digital Simulator [" + OpenSchematic.FileName + "]");
                        applet.MySchematicPanel.repaint();
                        return true;
                    }
                } else if (Command.equals("Cancel")) {
                    return true;
                }
                break;

            case OPENEXAMPLE_ID:
                OpenExampleDialog = null;
                if (Command.equals("OK")) {
                    LoadExample(ExampleFileName);
                    return true;
                } else if (Command.equals("Cancel")) {
                    return true;
                }
                break;

        }
        return false;
    }

//----------------------------------------------------------------------------
// Handle all events in the TextDialogs.
//----------------------------------------------------------------------------
    public boolean TextDialogHandler(String label) {
        // System.out.println ("TextDialogHandler text [" + label + "]");
        String strip = label.substring("TEXTDIALOG_".length());
        // System.out.println ("strip = [" + strip + "]");
        String Command = strip.substring(0, strip.indexOf("_"));
        String Text = strip.substring (strip.indexOf("_") + 1, strip.lastIndexOf("_"));
        int ID = Integer.parseInt(strip.substring (strip.lastIndexOf("_") + 1, strip.length()));
        // System.out.println ("Command: " + Command + " ID = " + ID);

        switch (ID) {
            case NEWTEXTDIALOG_ID:
                MyTextDialog = null;
                if (Command.equals("OK")) {
                    if (Text.length() > 0) {
                        applet.MySchematic.addComponent(new Caption(applet.MySchematicPanel.GridXOffset + 1, applet.MySchematicPanel.GridYOffset, Text));
                        applet.MySchematicPanel.repaint();
                    }
                    return true;
                } else if (Command.equals("Cancel")) {
                    return true;
                }
                break;

            case CHANGETEXTDIALOG_ID:
                MyTextChangeDialog = null;
                if (Command.equals("OK")) {
                    return true;
                } else if (Command.equals("Cancel")) {
                    applet.MySchematicPanel.repaint();
                    return true;
                }
                break;

            case CHANGETEXTPROBEDIALOG_ID:
                MyTextProbeChangeDialog = null;
                applet.MySchematicPanel.repaint();
                applet.updateAnalyzer();
                return true;
        }

        return false;
    }

//----------------------------------------------------------------------------
// Show a file-open dialog
//----------------------------------------------------------------------------
    public Schematic DoFileOpenDialog(Pin PinGrid[][], String capt) {
        Schematic NewSchematic = null;
        FileInputStream  is;

        FileDialog FileOpenDialog = new FileDialog (this, capt, FileDialog.LOAD);
        FileOpenDialog.show();
        String Directory = FileOpenDialog.getDirectory();
        String FileName = FileOpenDialog.getFile();

        if (Directory != null && FileName != null) {
            if (PinGrid != null) {
                applet.MySchematic.DestroyComponents(applet.PinGrid); // Destroy old schematic and remove components from grid.
            }
            try {
                is = new FileInputStream(Directory + FileName);
                NewSchematic = new Schematic (PinGrid, is);
            } catch(Exception e) {
                String message = e.toString();
                String DlgButtons[] = { "OK" };
                SimpleDialog ExceptionDialog = new SimpleDialog(this, capt, message, DlgButtons, 1, 0, 0, SimpleDialog.IMAGE_STOP);
                applet.MySchematicPanel.repaint();
                return null;
            }
            try {
                if (is != null)
                    is.close();
            } catch(Exception e) {
            }
            NewSchematic.FileDir = Directory;
            NewSchematic.FileName = FileName;
            NewSchematic.Modified = false;
            applet.updateAnalyzer();
        }

        return NewSchematic;
    }

//----------------------------------------------------------------------------
// Load an example from the specified URL.
//----------------------------------------------------------------------------
    public void LoadExample(String FileName) {
        InputStream  is;
        applet.MySchematic.DestroyComponents(applet.PinGrid); // Destroy old schematic and remove components from grid.
        try {
            is = new URL (applet.getDocumentBase(), FileName).openStream();
            applet.MySchematic = new Schematic (applet.PinGrid, is);
        } catch(Exception e) {
            String message = e.toString();
            String DlgButtons[] = { "OK" };
            SimpleDialog ExceptionDialog = new SimpleDialog(this, "Open schematic", message, DlgButtons, 1, 0, 0, SimpleDialog.IMAGE_STOP);
            applet.MySchematicPanel.repaint();
            return;
        }
        try {
            if (is != null)
                is.close();
        } catch(Exception e) {
        }
        applet.MySchematic.FileDir = "";
        applet.MySchematic.FileName = FileName;
        applet.MySchematic.Modified = false;
        setTitle("Digital Simulator [" + FileName + "]");
        applet.MySchematicPanel.repaint();
        applet.updateAnalyzer();

    }

//----------------------------------------------------------------------------
// Show a save-as dialog
//----------------------------------------------------------------------------
    public void DoFileSaveDialog(Schematic ActSchematic, boolean SaveAs, String capt) {
        FileDialog FileSaveDialog = null;
        FileOutputStream os;

        if (SaveAs) {
            FileSaveDialog = new FileDialog (this, capt, FileDialog.SAVE);
            FileSaveDialog.show();
            SaveFileDirectory = FileSaveDialog.getDirectory();
            SaveFileName = FileSaveDialog.getFile();
        } else {
            SaveFileDirectory = ActSchematic.FileDir;
            SaveFileName = ActSchematic.FileName;
        }

        if (SaveFileDirectory != null && SaveFileName != null) {
            if (SaveFileName.endsWith(".*.*")) {
                // Java/win95 bug???
                SaveFileName = SaveFileName.substring(0, SaveFileName.length() - 4);
            }

            ActSchematic.FileDir = SaveFileDirectory;
            ActSchematic.FileName = SaveFileName;
            // System.out.println ("SaveFileDirectory: " + SaveFileDirectory );
            // System.out.println ("SaveFileName: " + SaveFileName );
            try {
                os = new FileOutputStream(SaveFileDirectory + SaveFileName);
                ActSchematic.Save (os);
            } catch(Exception e) {
                String message = e.toString();
                String DlgButtons[] = { "OK" };
                SimpleDialog ExceptionDialog = new SimpleDialog(this, capt, message, DlgButtons, 1, 0, 0, SimpleDialog.IMAGE_STOP);
                return;
            }
            try {
                if (os != null)
                    os.close();
            } catch(Exception e) {
            }
            ActSchematic.Modified = false;
        }
    }

//----------------------------------------------------------------------------
// Handle actions in this frame.
//----------------------------------------------------------------------------
    public boolean action(Event ev, Object arg) {
        String label = (String)arg;
        // System.out.println (label);
        if (label.startsWith("SIMPLEDIALOG_")) {
            return SimpleDialogHandler(label);
        } else if (label.startsWith("TEXTDIALOG_")) {
            return TextDialogHandler(label);
        } else if(ev.target instanceof MenuItem) {
            if (label.equals("Open file")) {
                applet.UserWantsOpenSchematic();
            } else if (label.equals("Open example")) {
                applet.UserWantsOpenExample();
            } else if (label.equals("Save")) {
                applet.UserWantsSaveSchematic();
            } else if (label.equals("Save as")) {
                if (applet.HelpWanted) {
                    HelpDialog MyHelpDialog = new HelpDialog(this, "Save as");
                } else {
                    DoFileSaveDialog(applet.MySchematic, true, "Save schematic"); // save as
                    setTitle("Digital Simulator [" + applet.MySchematic.FileName + "]");
                    applet.MySchematicPanel.repaint();
                }
            } else if (label.equals("Close")) {
                if (applet.HelpWanted) {
                    HelpDialog MyHelpDialog = new HelpDialog(this, "Close");
                } else {
                    applet.destroyFrame();
                }
            } else if (label.equals("New")) {
                applet.UserWantsNewSchematic();
            } else if (label.equals("Exit")) {
                if (applet.HelpWanted) {
                    HelpDialog MyHelpDialog = new HelpDialog(this, "Exit");
                } else {
                    if (ExitDialog == null) {
                        String DlgButtons[] = {"OK",  "Cancel"};
                        ExitDialog = new SimpleDialog(this, "Exit DigSim", "Are you sure you want to exit?", DlgButtons, 2, 1, EXITDIALOG_ID, SimpleDialog.IMAGE_WARNING);
                    }
                }
            } else if (label.equals("Text")) {
                applet.UserWantsTextDrawing();
            } else if (label.equals("About DigSim")) {
                    HelpDialog MyHelpDialog = new HelpDialog(this, "About DigSim");
            } else if (label.equals("Frequently asked questions" )) {
                    HelpDialog MyFAQDialog = new HelpDialog(this, "FAQ");
            } else if (label.equals("Help")) {
                applet.UserWantsHelp();
            } else if (label.equals("Cut")) {
                applet.UserWantsCutSchematic();
            } else if (label.equals("Copy")) {
                applet.UserWantsCopySchematic();
            } else if (label.equals("Paste")) {
                applet.UserWantsPasteSchematic();
            } else if (label.equals("Copy to")) {
                applet.UserWantsCopyToSchematic();
            } else if (label.equals("Paste from")) {
                applet.UserWantsPasteFromSchematic();
            } else if (label.equals("Select All")) {
                if (applet.HelpWanted) {
                    HelpDialog MyHelpDialog = new HelpDialog(this, "Select All");
                } else {
                    applet.MySchematicPanel.SelectAll();
                }
            } else if (IsComponentName(label)) {
                if (applet.HelpWanted) {
                    HelpDialog MyHelpDialog = new HelpDialog(this, label);
                } else {
                    applet.addComponent(label);
                }
            } else if ("Start".equals(arg)) {
               if (applet.HelpWanted) {
                    HelpDialog MyHelpDialog = new HelpDialog(this, "Start");
                } else {
                    applet.UserWantsSimulate();
                }
            } else if ("Stop".equals(arg)) {
               if (applet.HelpWanted) {
                    HelpDialog MyHelpDialog = new HelpDialog(this, "Stop");
                } else {
                    applet.UserWantsSimulate();
                }
            } else if ("Show analyzer".equals(arg)) {
                applet.UserWantsAnalyzer();

            } else if ("Options".equals(arg)) {
                applet.UserWantsOptions();
            }
            return true;
        }
        return false;
    }
}