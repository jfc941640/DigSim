//****************************************************************************
// ---- version information ----
//
// OutputPin.java        v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for an Output Pin
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

class OutputPin extends ComponentPin {
    boolean ShortCircuit = false;

//-----------------------------------------------------------------------------
// Constuct a new pin with the data of PinToCopy
//-----------------------------------------------------------------------------
    public OutputPin (OutputPin PinToCopy) {
        super(PinToCopy);
    }

//-----------------------------------------------------------------------------
// The constructor of OutputPin.
//-----------------------------------------------------------------------------
    public OutputPin (String name, int x, int y, int w, int h, int txo, int tyo, int fl) {
        super (name, x, y, w, h, txo, tyo, fl);
    }

//-----------------------------------------------------------------------------
// Draw the output pin.
// Mostly of the drawing is done by ComponentPin,
// But if there is an short circuit it will be drawed by this function.
//-----------------------------------------------------------------------------
    public void draw (Graphics g, int x, int y, int gs) {
        super.draw (g, x, y, gs);
        if (ShortCircuit) {
            int xp = PinPos.x + x;
            int yp = PinPos.y + y;

            g.setColor (Color.yellow);
            g.drawLine (xp * gs, yp * gs, (xp + 1) * gs, (yp  - 1) * gs);
            g.drawLine ((xp + 1) * gs, (yp  - 1) * gs, (xp + 1) * gs, (int)((yp  - 0.5) * gs));
            g.drawLine ((xp + 1) * gs, (int)((yp  - 0.5) * gs), (xp + 2) * gs, (int)((yp  - 1.5) * gs));
            g.drawLine (xp * gs, yp * gs, (int)((xp + 0.25) * gs), (yp) * gs);
            g.drawLine (xp * gs, yp * gs, xp * gs, (int)((yp - 0.25) * gs));
        }
    }

    public void InitBeforeSimulate() {
        ShortCircuit = false;
    }
}