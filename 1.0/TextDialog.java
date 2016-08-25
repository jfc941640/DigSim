//****************************************************************************
// ---- version information ----
//
// TextDialog.java       v 1.00 b2
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:           v 1.00b1 01-03-1996
//                            v 1.00b2 07-03-1996 Untrusted windows reshape() bug fixed
// Released in public domain: v 1.00b1 01-03-1996
//
// ---- Description ----
// Java class containing methods for a Text Dialog.
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

class TextDialog extends Dialog {
    DigSimFrame frame;
    String text;
    protected Font TextDialogFont;
    protected FontMetrics TextDialogFontMetrics;
    TextField MyTextField = null;
    Caption ActCaption = null;
    Probe ActProbe = null;
    int DialogID;

//----------------------------------------------------------------------------
// The constructor of a new Text Dialog
//----------------------------------------------------------------------------
    TextDialog (DigSimFrame f, String Text, int MyID) {
        super (f, "Enter new text", false);
        frame = f;
        DialogID = MyID;
        text = Text;
        TextDialogFont = new Font("TimesRoman",Font.PLAIN, 14);
        TextDialogFontMetrics = getFontMetrics(TextDialogFont);
        setFont(TextDialogFont);
        setLayout(new BorderLayout());
        add ("North", MyTextField = new TextField(Text)) ;
        Panel control = new Panel () ;
        control.setLayout (new FlowLayout ()) ;
        add ("South", control) ;
        control.add (new Button ("OK"));
        control.add (new Button ("Cancel"));
        reshape (200, 100, 200, 125);
        show();
        reshape (200, 100, 200, 125);
        MyTextField.requestFocus();
    }

//----------------------------------------------------------------------------
// The constructor of a new Text Dialog used for changing a caption.
//----------------------------------------------------------------------------
    TextDialog (DigSimFrame f, Caption capt, int MyID) {
        super (f, "Change text", false);
        ActCaption = capt;
        frame = f;
        DialogID = MyID;
        text = capt.Text;
        TextDialogFont = new Font("TimesRoman",Font.PLAIN, 14);
        TextDialogFontMetrics = getFontMetrics(TextDialogFont);
        setFont(TextDialogFont);
        setLayout(new BorderLayout());
        add ("North", MyTextField = new TextField(capt.Text)) ;
        Panel control = new Panel () ;
        control.setLayout (new FlowLayout ()) ;
        add ("South", control) ;
        control.add (new Button ("OK"));
        control.add (new Button ("Cancel"));
        reshape (200, 100, 200, 125);
        show();
        reshape (200, 100, 200, 125);
        MyTextField.requestFocus();
    }

//----------------------------------------------------------------------------
// The constructor of a new Text Dialog used for changing an analyzer probe
//----------------------------------------------------------------------------
    TextDialog (DigSimFrame f, Probe probe, int MyID) {
        super (f, "Change probe name", false);
        ActProbe = probe;
        frame = f;
        DialogID = MyID;
        text = probe.IPin[0].getName();
        TextDialogFont = new Font("TimesRoman",Font.PLAIN, 14);
        TextDialogFontMetrics = getFontMetrics(TextDialogFont);
        setFont(TextDialogFont);
        setLayout(new BorderLayout());
        add ("North", MyTextField = new TextField(text)) ;
        Panel control = new Panel () ;
        control.setLayout (new FlowLayout ()) ;
        add ("South", control) ;
        control.add (new Button ("OK"));
        control.add (new Button ("Cancel"));
        reshape (200, 100, 200, 125);
        show();
        reshape (200, 100, 200, 125);
        MyTextField.requestFocus();
    }

//----------------------------------------------------------------------------
// Paint this dialog
//----------------------------------------------------------------------------
    public void paint(Graphics g) {
    }

//----------------------------------------------------------------------------
// Handle events of this text dialog
//----------------------------------------------------------------------------
    public boolean handleEvent(Event ev) {
        if (ev.id == Event.WINDOW_DESTROY) {
            hide();
            return frame.action (ev, "TEXTDIALOG_Cancel_" + DialogID);
        }
        return super.handleEvent(ev);
    }

//----------------------------------------------------------------------------
// Handle actions of this text dialog
//----------------------------------------------------------------------------
    public boolean action(Event ev, Object arg) {
        if (ev.target instanceof TextField) {
                hide();
                if (ActCaption != null) {
                    // Change existing text
                    ActCaption.Text = MyTextField.getText();
                    return frame.action (ev, "TEXTDIALOG_Cancel_" + DialogID); // No new text
                } else if (ActProbe != null) {
                    ActProbe.IPin[0].setName (MyTextField.getText());
                    return frame.action (ev, "TEXTDIALOG_Cancel_" + DialogID); // No new text
                } else {
                    return frame.action (ev, "TEXTDIALOG_OK_" + MyTextField.getText() + "_" + DialogID);
                }
        } else if (ev.target instanceof Button) {
            String label = (String)arg;
            if (label.equals("OK")) {
                 hide();
                if (ActCaption != null) {
                    // Change existing text
                    ActCaption.Text = MyTextField.getText();
                    return frame.action (ev, "TEXTDIALOG_Cancel_" + DialogID); // No new text
                } else if (ActProbe != null) {
                    ActProbe.IPin[0].setName (MyTextField.getText());
                    return frame.action (ev, "TEXTDIALOG_Cancel_" + DialogID); // No new text
                } else {
                    return frame.action (ev, "TEXTDIALOG_OK_" + MyTextField.getText() + "_" + DialogID);
                }
            } else {
                hide();
                return frame.action (ev, "TEXTDIALOG_Cancel_" + DialogID);
            }
        }

        return false;
    }
}