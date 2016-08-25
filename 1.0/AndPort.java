//****************************************************************************
// ---- version information ----
//
// AndPort.java          v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for an And port
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

class AndPort extends LogicPort {

//----------------------------------------------------------------------------
// The constructor of a general AND port
//----------------------------------------------------------------------------
    public AndPort(int i, int x, int y, int fl) {
        super(i, x, y, fl);
    }

//----------------------------------------------------------------------------
// The constructor of a new general AND port, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public AndPort (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        return this; // Should not occure, But return <this> to avoid problems
    }

//----------------------------------------------------------------------------
// Draw this AND port
//----------------------------------------------------------------------------
    public void draw(Graphics g, int xp, int yp, int gs) {
        super.draw (g, xp, yp, gs);
        int x = Pos.x - xp;
        int y = Pos.y - yp;

        // Draw the body
        g.setColor (ComponentColor);
        g.drawLine((x + 3) * gs, (y + 1) * gs, (x + 3) * gs, (y + 5) * gs);
        g.drawLine((x + 3) * gs, (y + 1) * gs, (x + 5) * gs, (y + 1) * gs);
        g.drawLine((x + 3) * gs, (y + 5) * gs, (x + 5) * gs, (y + 5) * gs);
        g.drawArc ((x + 3) * gs, (y + 1) * gs, gs * 4, gs * 4 , -90, 180);
    }

//----------------------------------------------------------------------------
// This method is simulating the component
//----------------------------------------------------------------------------
    public void SimulateLogic() {
        int ix = 0;
        int Result = 5;
        for (ix = 0; ix < Inputs; ix++) {
            if (IPin[ix].getLevel() != 5) {
                Result = 0;
            }
        }
        OPin[0].Level = Result;
    }
}