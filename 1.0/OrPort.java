//****************************************************************************
// ---- version information ----
//
// OrPort.java           v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for a Or port
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

class OrPort extends LogicPort {

//----------------------------------------------------------------------------
// The constructor of a new OR port
//----------------------------------------------------------------------------
    public OrPort(int i, int x, int y, int fl) {
        super(i, x, y, fl);
    }

//----------------------------------------------------------------------------
// The constructor of a new OR port, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public OrPort (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        return this; // Should not occure, But return <this> to avoid problems
    }

//----------------------------------------------------------------------------
// Method for drawing this OR port
//----------------------------------------------------------------------------
    public void draw(Graphics g, int xp, int yp, int gs) {
        super.draw (g, xp, yp, gs);
        int x = Pos.x - xp;
        int y = Pos.y - yp;

        // Draw the body
        g.setColor (ComponentColor);
        g.drawArc ((x + 2) * gs, (y + 1) * gs, gs * 1, gs * 4 , -90, 180);
        g.drawArc ((x - 3) * gs, (y + 1) * gs, (int)(gs * 11.5), gs * 12 , 42, 49);
        g.drawArc ((x - 3) * gs, (y - 7) * gs, (int)(gs * 11.5), gs * 12 , -42, -49);
    }

//----------------------------------------------------------------------------
// This method is simulating the component
//----------------------------------------------------------------------------
    public void SimulateLogic() {
        int ix = 0;
        int Result = 0;
        for (ix = 0; ix < Inputs; ix++) {
            if (IPin[ix].getLevel() == 5) {
                Result = 5;
            }
        }
        OPin[0].Level = Result;
    }
}