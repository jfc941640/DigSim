//****************************************************************************
// ---- version information ----
//
// SimpleDialog.java     v 1.00 b3
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:           v 1.00b1 01-03-1996
//                            v 1.00b3 26-03-1996 DialogPanel added
// Released in public domain: v 1.00b1 01-03-1996
//
// ---- Description ----
// Java class containing methods for Simple Dialog.
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

class SimpleDialog extends Frame {
    DigSimFrame frame = null;
    int DialogID;
    String DefaultButton;
    DialogPanel MyDialogPanel = null;
    static final int IMAGE_NONE = 0x00;
    static final int IMAGE_STOP = 0x01;
    static final int IMAGE_WARNING = 0x02;

//----------------------------------------------------------------------------
// The constructor of the SimpleDialog class.
//----------------------------------------------------------------------------
    public SimpleDialog(DigSimFrame f, String name, String capt,
                        String DlgButtons[], int NumB, int DefB, int MyID, int ImageID) {
        super (name);
        int width = 0;

        DialogID = MyID;
        frame = f;

        setLayout(new BorderLayout());
        add("Center", MyDialogPanel = new DialogPanel(f, capt, ImageID));

        Panel control = new Panel () ;
        control.setLayout (new FlowLayout ()) ;
        add ("South", control) ;
        for (int ix = 0; ix < NumB; ix++) {
            control.add (new Button (DlgButtons[ix] )) ;
        }

        if (MyDialogPanel != null) {
            width = MyDialogPanel.HORIZONTAL_GAP + MyDialogPanel.CaptWidth;
        }

        if (width < 200) width = 200;

        if (ImageID != 0) width += 50;
        reshape (200, 100, width, 125);
        show();
        reshape (200, 100, width, 125);

        repaint();
    }

//----------------------------------------------------------------------------
// Paint the Simple Dialog
//----------------------------------------------------------------------------
    public void paint (Graphics g) {
        if (MyDialogPanel != null) {
            MyDialogPanel.repaint();
        }
    }

//-----------------------------------------------------------------------------
// Calculate and return the preferred size of the Dialog
//-----------------------------------------------------------------------------
    public Dimension preferredSize() {
       if (MyDialogPanel != null) {
            return MyDialogPanel.preferredSize();
       } else {
           return new Dimension(200, 125);
       }
    }

//-----------------------------------------------------------------------------
// handle events of this dialog and send the events to the frame.
//-----------------------------------------------------------------------------
    public boolean handleEvent(Event ev) {
        if (ev.id == Event.WINDOW_DESTROY) {
            hide();
            if (frame != null) {
                return frame.action (ev, "SIMPLEDIALOG_"+ DefaultButton + "_" + DialogID);
            }
        }
        return super.handleEvent(ev);
    }

//-----------------------------------------------------------------------------
// handle actions of this dialog and send the actions to the frame
//-----------------------------------------------------------------------------
    public boolean action(Event ev, Object arg) {
        if (ev.target instanceof Button) {
            String label = (String)arg;
            hide();
            if (frame != null) {
                return frame.action (ev, "SIMPLEDIALOG_"+ label + "_" + DialogID);
            }
        }

        return false;
    }
}