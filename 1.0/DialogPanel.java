//****************************************************************************
// ---- version information ----
//
// DialogPanel.java      v 1.00 b3
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:           v 1.00b3 26-03-1996
// Released in public domain: v 1.00b3 26-03-1996
//
// ---- Description ----
// Java class containing methods for DialogPanel.
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
import java.awt.image.*;
import sun.awt.image.URLImageSource;

class DialogPanel extends Panel implements ImageObserver {
    DigSimFrame frame = null;
    static final int HORIZONTAL_GAP = 50;
    protected Font SimpleDialogFont;
    protected FontMetrics SimpleDialogFontMetrics;
    String Capt;
    int CaptWidth;
    Image ImageToDisplay = null;
    int imgw = 0, imgh = 0;
    static final long updateRate = 100;

//----------------------------------------------------------------------------
// The constructor of the SimpleDialog class.
//----------------------------------------------------------------------------
    public DialogPanel (DigSimFrame f, String capt, int ImageID) {
        frame = f;
        Capt = capt;
        getImage (ImageID);

        setLayout( new BorderLayout());
        SimpleDialogFont = new Font("TimesRoman",Font.PLAIN, 14);
        SimpleDialogFontMetrics = getFontMetrics(SimpleDialogFont);
        setFont(SimpleDialogFont);
        super.setLayout( new BorderLayout());
        CaptWidth = SimpleDialogFontMetrics.stringWidth(capt);
        repaint();
    }

//-----------------------------------------------------------------------------
// Calculate and return the preferred size of the Dialog
//-----------------------------------------------------------------------------
    public Dimension preferredSize() {
        return new Dimension(HORIZONTAL_GAP + CaptWidth, 125);
    }

//-----------------------------------------------------------------------------
// Draw the message, and if available, the image
//-----------------------------------------------------------------------------
    public void paint(Graphics g) {
        if (ImageToDisplay != null) {
            g.drawImage (ImageToDisplay, 20, 10, this);
            g.setColor (Color.black);
            int x = (size().width - CaptWidth) / 2 + imgw + 20;
            if (x < imgw + 50) x = imgw + 50;
            g.drawString (Capt, x, 30);
        } else {
            g.setColor (Color.black);
            int x = (size().width - CaptWidth) / 2;
            g.drawString (Capt, x, 30);
        }
    }

//-----------------------------------------------------------------------------
// Load an image from the specified URL
//-----------------------------------------------------------------------------
    public void getImage (int ImageID) {
        String FileName = null;
        switch (ImageID) {
            case SimpleDialog.IMAGE_STOP:
                FileName = "images/stop.gif";
                break;
            case SimpleDialog.IMAGE_WARNING:
                FileName = "images/warning.gif";
                break;
            case SimpleDialog.IMAGE_NONE:
            default:
                return;
        }
        if (frame != null) {
            ImageToDisplay = frame.applet.getImage(frame.applet.getDocumentBase(), FileName);
        }
    }

//-----------------------------------------------------------------------------
// There is new data available for the picture
//-----------------------------------------------------------------------------
    public synchronized boolean imageUpdate(Image img, int infoflags,
					    int x, int y, int w, int h) {
        boolean ret = true;
        boolean dopaint = false;
        long updatetime = 0;

        if ((infoflags & WIDTH) != 0) {
            imgw = w;
            dopaint = true;
        }
        if ((infoflags & HEIGHT) != 0) {
            imgh = h;
            dopaint = true;
        }
        if ((infoflags & (FRAMEBITS | ALLBITS)) != 0) {
            dopaint = true;
            ret = false;
        } else if ((infoflags & SOMEBITS) != 0) {
            dopaint = true;
            updatetime = updateRate;
        }
        if ((infoflags & ERROR) != 0) {
            ret = false;
        }
        if (dopaint) {
            repaint(updatetime);
        }
        return ret;
    }
}