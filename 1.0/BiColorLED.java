//****************************************************************************
// ---- version information ----
//
// BiColorLED.java       v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for a Bi color LED
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

class BiColorLED extends ElectronicComponent {
    final static Color LEDColorOff = Color.gray;

//----------------------------------------------------------------------------
// The constructor of a new bi-color LED
//----------------------------------------------------------------------------
    public BiColorLED (Pin PinGrid[][], int x, int y) {
        super (x, y, 9, 4, 4, 1, 2, 2, 2, 0);  // x,y,w,h HitBox x,y,w,h,I,O
        ComponentName = "Bi-color LED (Light Emitting Diode)";
        ClassName = "Bi-Color LED";
        IPin[0] = new InputPin("A", 1, 2, 3, 0, 0, 0, ComponentPin.PIN_TEXT_INVISIBLE); // name, x, y, w, h, flags
        IPin[1] = new InputPin("B", 8, 2, -2, 0, 0, 0, ComponentPin.PIN_TEXT_INVISIBLE); // name, x, y, w, h, flags
        RegisterPins (PinGrid, x, y);
    }

//----------------------------------------------------------------------------
// The constructor of a bi-color LED, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public BiColorLED (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        BiColorLED NewComponent = new BiColorLED(this, xo, yo);
        return NewComponent;
    }

//----------------------------------------------------------------------------
// Method for drawing this bi-color LED
//----------------------------------------------------------------------------
    public void draw(Graphics g, int xp, int yp, int gs) {
        super.draw (g, xp, yp, gs);
        int x = Pos.x - xp;
        int y = Pos.y - yp;

        // Draw HitBox and Dimension
        // DrawHitBox(g, x, y, gs);

        // Draw all I Pins
        DrawInputPins(g, x, y, gs);

        // Draw body
        if (IPin[0].getLevel() == 5 && IPin[1].getLevel() == 0) {
            g.setColor (Color.red);
        } else if (IPin[0].getLevel() == 0 && IPin[1].getLevel() == 5) {
            g.setColor (Color.green);
        } else {
            g.setColor (LEDColorOff);
        }
        g.fillOval ((x + 4) * gs, (y + 1) * gs, 2 * gs + 1, 2 * gs + 1);
        g.setColor (Color.white);
        g.drawOval ((x + 4) * gs, (y + 1) * gs, 2 * gs, 2 * gs);
    }

//----------------------------------------------------------------------------
// Initialize all inputs before simulating with level -1.
// This prevent the LED is lightning when there's no potential to the pins.
//----------------------------------------------------------------------------
    public void InitBeforeSimulate() {
        IPin[0].InitBeforeSimulate();
        IPin[1].InitBeforeSimulate();
        IPin[0].setLevel (-1);
        IPin[1].setLevel (-1);
    }

}