//****************************************************************************
// ---- version information ----
//
// GND.java              v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for a logic GND
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

class GND extends ElectronicComponent {

//----------------------------------------------------------------------------
// The constructor of a GND pin
//----------------------------------------------------------------------------
    public GND(Pin PinGrid[][], int x, int y) {
        super (x, y, 4, 4, 1, 1, 2, 3, 0, 1);      // x,y,w,h HitBox x,y,w,h, I,O
        OPin[0] = new OutputPin("GND", 2, 1, 0, 2, 0, 0, ComponentPin.PIN_TEXT_INVISIBLE); // name, x, y, w, h, Flags
        OPin[0].Level = 0;
        ComponentName = "GND";
        ClassName = "GND";
        RegisterPins (PinGrid, x, y);
    }

//----------------------------------------------------------------------------
// The constructor of a new GND pin, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public GND (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        GND NewComponent = new GND(this, xo, yo);
        NewComponent.OPin[0].Level = 0;

        return NewComponent;
    }

//----------------------------------------------------------------------------
// Method for drawing this GND pin
//----------------------------------------------------------------------------
    public void draw(Graphics g, int xp, int yp, int gs) {
        super.draw (g, xp, yp, gs);
        int x = Pos.x - xp;
        int y = Pos.y - yp;

        // Draw HitBox and Dimension
        // DrawHitBox(g, x, y, gs);

        // Draw The OutputPin
        DrawOutputPins(g, x, y, gs);

        // Draw the body
        g.setColor (Color.gray);
        g.fillRect ((x + 1) * gs, (y + 3) * gs, gs * 2 + 1, gs / 2 - 1);
    }

//----------------------------------------------------------------------------
// Method for Simulating this component. All components connected to this
// GND pin will be informed.
//----------------------------------------------------------------------------
    public void Simulate(int id) {
//      System.out.println ("GND simulate()");
//      System.out.println ("GND Simulate() ID= " + id);
        InformConnectedComponents(id);
    }

}