//****************************************************************************
// ---- version information ----
//
// InputPin.java         v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for a Input Pin
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

class InputPin extends ComponentPin {
    boolean AlreadyInformed = false;
    int ReceivedSimulationCycleID;
    int LevelChanged = 0;
    boolean Looping = false;

//-----------------------------------------------------------------------------
// Constuct a new pin with the data of PinToCopy
//-----------------------------------------------------------------------------
    public InputPin (InputPin PinToCopy) {
        super(PinToCopy);
    }

//-----------------------------------------------------------------------------
// Construct a new InputPin.
//-----------------------------------------------------------------------------
    public InputPin (String name, int x, int y, int w, int h, int txo, int tyo, int fl) {
        super (name, x, y, w, h, txo, tyo, fl);
    }

//-----------------------------------------------------------------------------
// Initialize the Pin before simulating
//-----------------------------------------------------------------------------
    public void InitBeforeSimulate() {
        ReceivedSimulationCycleID = -1;
        LevelChanged = 0;
        Looping = false;
    }

//-----------------------------------------------------------------------------
// Draw the pin. Mostly of the drawing is done by the parent, ComponentPin,
// But draw a loop-symbool if this pin is looping.
//-----------------------------------------------------------------------------
   public void draw (Graphics g, int x, int y, int gs) {
        super.draw (g, x, y, gs);
        if (Looping) {
            int xp = PinPos.x + x;
            int yp = PinPos.y + y;

            g.setColor (Color.yellow);
            g.drawArc ((xp - 2) * gs, (int)((yp - 1.5) * gs), gs * 2, gs * 2, 0, 270);
            g.drawLine (xp * gs, (int)((yp - 0.5) * gs), (int)((xp + 0.5) * gs), (yp - 1 ) * gs);
            g.drawLine (xp * gs, (int)((yp - 0.5) * gs), (int)((xp - 0.5) * gs), (yp - 1 ) * gs);
        }
    }
}