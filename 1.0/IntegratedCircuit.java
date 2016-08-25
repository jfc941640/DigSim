//****************************************************************************
// ---- version information ----
//
// IntegratedCircuit.java   v 1.00 b1
// Written by:              I. van Rienen / E-mail ivr@bart.nl
// URL:                     http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for a Integrated Circuit
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

class IntegratedCircuit extends ElectronicComponent {

//----------------------------------------------------------------------------
// The constructor of an Integrated Circuit
//----------------------------------------------------------------------------
    public IntegratedCircuit(int x, int y, int w, int h,
                             int xh, int yh, int wh, int hh, int i, int o) {
        super (x, y, w, h, xh, yh, wh, hh, i, o);  // x,y,w,h HitBox x,y,w,h, I,O
    }

//----------------------------------------------------------------------------
// The constructor of a new IC, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public IntegratedCircuit (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        return this; // Should not occure, But return <this> to avoid problems
    }

//----------------------------------------------------------------------------
// Method for drawing this IC
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

        // Draw body
        g.setColor (ComponentColor);
        g.drawRect ((x + HitBox.x) * gs, (y + HitBox.y) * gs, HitBoxSize.width * gs, HitBoxSize.height * gs);
    }

//----------------------------------------------------------------------------
// This code is simulating the IC
//----------------------------------------------------------------------------
    public void SimulateLogic() {}

//----------------------------------------------------------------------------
// The action of this component every cycle
//----------------------------------------------------------------------------
    public void Simulate(int id) {
        super.Simulate(id);
    }

}