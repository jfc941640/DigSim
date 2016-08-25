//****************************************************************************
// ---- version information ----
//
// ExamplesFrame.java    v 1.00 b2
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:           v 1.00b1 01-03-1996
//                            v 1.00b2 07-03-1996 Linux choise bug fixed
//                                                Window size adjusted
// Released in public domain: v 1.00b1 01-03-1996
//
// ---- Description ----
// Java class containing methods for the the examples frame.
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
import java.io.StringBufferInputStream;
import java.io.DataInputStream;

class ExamplesFrame extends Frame {
    DigSim applet;
    Choice c;
    List l;
    Vector ExamplesVector;
    String WaitMessage = "Please wait, loading available examples.";

//----------------------------------------------------------------------------
// The constructor of a new examples frame.
//----------------------------------------------------------------------------
    public ExamplesFrame( DigSim app) {
        super("DigSim examples");
        applet = app;
        Button OKButton = new Button ("OK");
        Button CancelButton = new Button ("Cancel");
        OKButton.disable();

        ExamplesVector = new Vector();

        Panel p1 = new Panel();
        p1.setLayout (new FlowLayout());
        c = new Choice();
        c.addItem ("all");
        p1.add (c);
        p1.add (OKButton);
        p1.add (CancelButton);
        Panel p2 = new Panel();
        p2.setLayout (new GridLayout(0, 1));
        l = new List (10, false);
        l. addItem (WaitMessage);
        p2.add (l);

        setLayout(new BorderLayout());
        add ("North", p2);
        add ("South", p1);

        resize(400, 275);
        show();
        resize(400, 275);
        LoadExamples();
        ShowCategorys();
        if (c.countItems() > 0) {
            ShowExamples(c.getItem(0));
        }
        l.select(0);
        OKButton.enable();
        p1.layout();

    }

//----------------------------------------------------------------------------
// Show all available examples in the specified category
//----------------------------------------------------------------------------
    public void ShowExamples (String Category) {
        Example TempExample;
        int ix;

        if (ExamplesVector.size() == 0) return; // No examples available
        l.delItems (0, l.countItems() - 1);
        for (ix = 0; ix < ExamplesVector.size(); ix++) {
            TempExample =  (Example) ExamplesVector.elementAt(ix);
            if (Category.equals (TempExample.getType()) || Category.equals("all")) {
                l.addItem (TempExample.getDescription());
            }
        }
    }

//----------------------------------------------------------------------------
// Show all available categorys
//----------------------------------------------------------------------------
    public void ShowCategorys() {
        Example TempExample;
        boolean exist;
        int ix, cl;

        if (ExamplesVector.size() == 0) return; // No examples available
        for (ix = 0; ix < ExamplesVector.size(); ix++) {
            TempExample =  (Example) ExamplesVector.elementAt(ix);
            exist = false;
            for (cl = 0; cl < c.countItems(); cl++) {
                if (c.getItem (cl).equals (TempExample.getType())) {
                    exist = true;
                }
            }
            if (!exist) {
                c.addItem (TempExample.getType());
            }
        }
    }

//----------------------------------------------------------------------------
// Load a description of all available examples
//----------------------------------------------------------------------------
    public void LoadExamples() {
        String message;
        String ExampleType = null;
        String ExampleDescription = null;
        String ExampleLocation = null;
        Example NewExample;

        while (applet.TextFileRequested != null) {
            // File loading in progress for another process.
            // wait a while.
            // System.out.println ("waiting on other process...");
            try {
                Thread.currentThread().sleep (250);
            } catch(Exception e) { }
        }
        applet.RequestedTextFileRead = false;
        applet.RequestedTextFileError = false;
        applet.TextFileRequested = "examples/description.txt";
        show();
        do {
            // System.out.println ("waiting on data...");
            try {
                Thread.currentThread().sleep (250);
            } catch(Exception e) { }
        } while (!applet.RequestedTextFileRead && !applet.RequestedTextFileError);

        if (applet.RequestedText == null || applet.RequestedTextFileError) {
            applet.TextFileRequested = null;
            String DlgButtons[] = { "OK" };
            message = "Can't read file examples/description.txt";
            SimpleDialog ExceptionDialog = new SimpleDialog(null, "Loading examples", message, DlgButtons, 1, 0, 0, SimpleDialog.IMAGE_STOP);
            return;
        }
        String PlainText = applet.RequestedText;
        applet.TextFileRequested = null;
        StringBufferInputStream sis = new StringBufferInputStream(PlainText);
        DataInputStream dis = new DataInputStream (sis);
        do {
            try {
                ExampleType = null;
                ExampleDescription = null;
                ExampleLocation = null;

                ExampleType = dis.readLine();
                if (ExampleType != null) {
                    ExampleDescription = dis.readLine();
                }
                if (ExampleType != null && ExampleDescription != null) {
                    ExampleLocation = dis.readLine();
                }
            } catch(Exception e) {
            }
            if (ExampleType != null && ExampleDescription != null && ExampleLocation != null) {
                NewExample = new Example (ExampleType, ExampleDescription, ExampleLocation);
                ExamplesVector.addElement(NewExample);
            }
        } while (ExampleType != null && ExampleDescription != null && ExampleLocation != null);

    }

//----------------------------------------------------------------------------
// Handle the events in this frame.
//----------------------------------------------------------------------------
    public boolean handleEvent(Event ev) {
        if (ev.id == Event.WINDOW_DESTROY) {
            hide();
            applet.MyExamplesFrame = null;
            return true;
        }
        return super.handleEvent(ev);
    }

//----------------------------------------------------------------------------
// Get the filename of the example
//----------------------------------------------------------------------------
    public String getFileName(String example) {
        Example TempExample;
        int ix;

        for (ix = 0; ix < ExamplesVector.size(); ix++) {
            TempExample =  (Example) ExamplesVector.elementAt(ix);
            if (example.equals (TempExample.getDescription())) {
                return TempExample.getLocation();
            }
        }
        return null;
    }

//----------------------------------------------------------------------------
// Handle all actions in this frame
//----------------------------------------------------------------------------
    public boolean action(Event ev, Object arg) {
        String FileName;
        int s;

        // System.out.println ("Action");
        if (ev.target instanceof Button) {
            String label = (String)arg;
            if (label.equals ("Cancel")) {
                hide();
                applet.MyExamplesFrame = null;
                return true;
            } else if (label.equals ("OK")) {
                s = l.getSelectedIndex();
                if (s == -1) return true;
                FileName = getFileName(l.getSelectedItem());
                hide();
                applet.UserWantsOpenExample(FileName);
                applet.MyExamplesFrame = null;
                return true;
            }
        } else if (ev.target instanceof Choice) {
            ShowExamples((String)arg);
            return true;
        } else if (ev.target instanceof List) {
            if (WaitMessage.equals ((String)arg)) return true;
            FileName = getFileName((String)arg);
            hide();
            applet.UserWantsOpenExample(FileName);
            applet.MyExamplesFrame = null;
            return true;
        }
        return false;
    }
}