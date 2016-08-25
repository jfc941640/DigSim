//****************************************************************************
// ---- version information ----
//
// Vcc.java              v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for a Vcc
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

class Vcc extends ElectronicComponent {

//----------------------------------------------------------------------------
// The constructor of a new Vcc Pin.
//----------------------------------------------------------------------------
    public Vcc(Pin PinGrid[][], int x, int y) {
        super (x, y, 4, 6, 1, 1, 2, 4, 0, 1);      // x,y,w,h HitBox x,y,w,h, I,O
        OPin[0] = new OutputPin("Vcc", 2, 5, 0, -2, 0, 0, ComponentPin.PIN_TEXT_INVISIBLE); // name, x, y, w, h, Flags
        OPin[0].Level = 5;
        ComponentName = "Vcc";
        ClassName = "Vcc";
        RegisterPins (PinGrid, x, y);
    }

//----------------------------------------------------------------------------
// The constructor of a new Vcc pin, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public Vcc (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        Vcc NewComponent = new Vcc(this, xo, yo);
        NewComponent.OPin[0].Level = 5;
        return NewComponent;
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

        // Draw The OutputPin
        DrawOutputPins(g, x, y, gs);

        // Draw the body
        g.setColor (ComponentColor);
        g.drawOval ((int)((x + 1.5) * gs) , (y + 2) * gs, gs, gs);
        g.drawLine ((int)((x + 1.5) * gs), (y + 3) * gs,
                    (int)((x + 2.5) * gs), (y + 2) * gs );

        // Draw plus sign
        g.setColor (Color.red);
        g.drawLine ((int)((x + 1.75) * gs), (int)((y + 1.5) * gs),
                    (int)((x + 2.25) * gs), (int)((y + 1.5) * gs));
        g.drawLine ((int)((x + 2) * gs), (int)((y + 1.25) * gs),
                    (int)((x + 2) * gs), (int)((y + 1.75) * gs));
    }

//----------------------------------------------------------------------------
// Here is the code that's simulating the component
// All connected components to this Vcc pin will be informed
//----------------------------------------------------------------------------
    public void Simulate(int id) {
//      System.out.println ("Vcc Simulate() ID= " + id);
        InformConnectedComponents(id);
    }

}