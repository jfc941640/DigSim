//****************************************************************************
// ---- version information ----
//
// LogicPort.java        v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for a Logic Port
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

class LogicPort extends ElectronicComponent {

//----------------------------------------------------------------------------
// The constructor of a new Logic Port
//----------------------------------------------------------------------------
    public LogicPort(int i, int x, int y, int fl) {
        super (x, y, 10, 6, 3, 1, 4, 4, i, 1);      // x,y,w,h HitBox x,y,w,h, I,O
        OPin[0] = new OutputPin("Q", 9, 3, -2, 0, 0, 0, fl);  // name, x, y, w, h, txo, tyo, inverted
    }

//----------------------------------------------------------------------------
// The constructor of a new Logic Port, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public LogicPort (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        return this; // Should not occure, But return <this> to avoid problems
    }

//----------------------------------------------------------------------------
// Method for drawing this component.
//----------------------------------------------------------------------------
    public void draw(Graphics g, int xp, int yp, int gs) {
        super.draw (g, xp, yp, gs);
        int x = Pos.x - xp;
        int y = Pos.y - yp;
        // Draw HitBox and Dimension
        // DrawHitBox(g, x, y, gs);

        // Draw all IO Pins
        DrawInputPins(g, x, y, gs);
        DrawOutputPins(g, x, y, gs);
    }

//----------------------------------------------------------------------------
// This method is simulating the component
//----------------------------------------------------------------------------
    public void SimulateLogic() {}
}