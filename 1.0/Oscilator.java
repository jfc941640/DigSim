//****************************************************************************
// ---- version information ----
//
// Oscilator.java        v 1.00 b3
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:           v 1.00b1 01-03-1996
//                            v 1.00b3 01-03-1996 Short circuit init bug fixed
// Released in public domain: v 1.00b1 01-03-1996
//
// ---- Description ----
// Java class containing methods for creating and simulating a Oscilator
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

class Oscilator extends ElectronicComponent {

//----------------------------------------------------------------------------
// The constructor of a the oscilator
//----------------------------------------------------------------------------
    public Oscilator (Pin PinGrid[][], int x, int y) {
        super (x, y, 8, 6, 1, 1, 4, 4, 0, 1);  // x,y,w,h HitBox x,y,w,h,I,O
        ComponentName = "Oscilator";
        ClassName = "Oscilator";
        OPin[0] = new OutputPin("Q", 7, 3, -2, 0, 0, 0, ComponentPin.PIN_TEXT_INVISIBLE); // name, x, y, w, h, flags
        RegisterPins (PinGrid, x, y);
    }

//----------------------------------------------------------------------------
// The constructor of a new Oscilator, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public Oscilator (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        Oscilator NewComponent = new Oscilator(this, xo, yo);
        return NewComponent;
    }

//----------------------------------------------------------------------------
// Method for drawing this oscilator
//----------------------------------------------------------------------------
    public void draw(Graphics g, int xp, int yp, int gs) {
        super.draw (g, xp, yp, gs);
        int x = Pos.x - xp;
        int y = Pos.y - yp;

        // Draw HitBox and Dimension
        // DrawHitBox(g, x, y, gs);

        // Draw O Pins
        DrawOutputPins(g, x, y, gs);

        // Draw body
        g.setColor (Color.gray);
        g.drawLine ((x + 2) * gs, (y + 2) * gs, (x + 2) * gs, (y + 4) * gs);
        g.drawLine ((x + 3) * gs, (y + 2) * gs, (x + 3) * gs, (y + 4) * gs);
        g.drawLine ((x + 4) * gs, (y + 2) * gs, (x + 4) * gs, (y + 4) * gs);
        g.setColor (Color.red);
        g.drawLine ((x + 2) * gs, (y + 2) * gs, (x + 3) * gs, (y + 2) * gs);
        g.setColor (Color.green);
        g.drawLine ((x + 3) * gs, (y + 4) * gs, (x + 4) * gs, (y + 4) * gs);
        g.setColor (ComponentColor);
        g.drawRect ((x + 1) * gs, (y + 1) * gs, gs * 4, gs * 4);
    }

//----------------------------------------------------------------------------
// This method is simulating the component
//----------------------------------------------------------------------------
    public void SimulateLogic() {
    }

//----------------------------------------------------------------------------
// Inform all connected components to this oscilator
//----------------------------------------------------------------------------
    public void Simulate(int id) {
//       System.out.println ("Oscilator simulate()");
//       System.out.println ("Oscilator Simulate() ID= " + id);
        InformConnectedComponents(id);
    }

//----------------------------------------------------------------------------
// Init the oscilator before a simulation cycle, toggle the output.
//----------------------------------------------------------------------------
    public void InitBeforeSimulate() {
        OPin[0].InitBeforeSimulate();
        if (OPin[0].getLevel() == 0) {
            OPin[0].Level = 5;
         } else {
            OPin[0].Level = 0;
        }
    }
}