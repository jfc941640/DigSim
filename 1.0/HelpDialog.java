//****************************************************************************
// ---- version information ----
//
// HelpDialog.java       v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for creating a Help Dialog.
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
import java.lang.InterruptedException;
import java.io.StreamTokenizer;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.URL;

class HelpDialog extends Dialog {
    protected Font HelpDialogFont;
    protected FontMetrics HelpDialogFontMetrics;
    String Capt;
    int CaptWidth;
    DigSimFrame frame;
    TextArea HelpTextArea;

//----------------------------------------------------------------------------
// The constructor of the Help Dialog.
//----------------------------------------------------------------------------
    public HelpDialog(DigSimFrame f, String HelpFileName) {
        super (f, "Help: " + HelpFileName, false);
        String FileName;
        frame = f;
        f.applet.UserWantsPointer();
        FileName = HelpFileName.replace(' ', '_');
        FileName = FileName.replace('-', '_');
        HelpDialogFont = new Font("TimesRoman",Font.PLAIN, 14);
        HelpDialogFontMetrics = getFontMetrics(HelpDialogFont);
        setFont(HelpDialogFont);
        add ("North", (HelpTextArea = new TextArea ("Please wait, reading help file", 10, 40)));
        HelpTextArea.setEditable (false);
        Panel control = new Panel () ;
        control.setLayout (new FlowLayout ()) ;
        add ("South", control) ;
        control.add (new Button ("OK")) ;
        reshape (200, 100, 400, 250);

        while (frame.applet.TextFileRequested != null) {
            // File loading in progress for another process.
            // wait a while.
            // System.out.println ("waiting on other process...");
            try {
                Thread.currentThread().sleep (250);
            } catch(Exception e) { }
        }

        // System.out.println ("Request TextFile");
        frame.applet.RequestedTextFileRead = false;
        frame.applet.RequestedTextFileError = false;
        frame.applet.TextFileRequested = "help/" + FileName + ".help";
        show();
        do {
            // System.out.println ("waiting on data...");
            try {
                Thread.currentThread().sleep (250);
            } catch(Exception e) { }
        } while (!frame.applet.RequestedTextFileRead && !frame.applet.RequestedTextFileError);

        HelpTextArea.setText (frame.applet.RequestedText);
        frame.applet.TextFileRequested = null;
    }

//----------------------------------------------------------------------------
// paint the help dialog
//----------------------------------------------------------------------------
    public void paint(Graphics g) {
    }

//----------------------------------------------------------------------------
// handle the events of this dialog
//----------------------------------------------------------------------------
    public boolean handleEvent(Event ev) {
        if (ev.id == Event.WINDOW_DESTROY) {
            hide();
            return true;
        }
        return super.handleEvent(ev);
    }

//----------------------------------------------------------------------------
// handle the actions of this dialog
//----------------------------------------------------------------------------
    public boolean action(Event ev, Object arg) {
        if (ev.target instanceof Button) {
            hide();
            return true;
        }

        return false;
    }

}