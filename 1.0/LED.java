//****************************************************************************
// ---- version information ----
//
// LED.java              v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for a LED
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
import java.io.PrintStream;

class LED extends ElectronicComponent {
    protected Color LEDColorOn;
    final static Color LEDColorOff = Color.gray;

//----------------------------------------------------------------------------
// The constructor of a new LED
//----------------------------------------------------------------------------
    public LED (Pin PinGrid[][], Color MyColor, int x, int y) {
        super (x, y, 9, 4, 4, 1, 2, 2, 2, 0);  // x,y,w,h HitBox x,y,w,h,I,O
        LEDColorOn = MyColor;
        ComponentName = "LED";
        IPin[0] = new InputPin("Anode", 1, 2, 3, 0, 0, 0, ComponentPin.PIN_TEXT_INVISIBLE); // name, x, y, w, h, flags
        IPin[1] = new InputPin("Kathode", 8, 2, -2, 0, 0, 0, ComponentPin.PIN_TEXT_INVISIBLE); // name, x, y, w, h, flags
        RegisterPins (PinGrid, x, y);
    }

//----------------------------------------------------------------------------
// The constructor of a new LED, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public LED (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        LED NewComponent = new LED(this, xo, yo);
        NewComponent.LEDColorOn = LEDColorOn;
        return NewComponent;
    }

//----------------------------------------------------------------------------
// Method for drawing this LED.
//----------------------------------------------------------------------------
    public void draw(Graphics g, int xp, int yp, int gs) {
        super.draw (g, xp, yp, gs);
        int x = Pos.x - xp;
        int y = Pos.y - yp;

        // Draw HitBox and Dimension
        // DrawHitBox(g, x, y, gs);

        // Draw all I Pins
        DrawInputPins(g, x, y, gs);

        // Draw plus
        g.setColor (Color.red);
        g.drawLine ((x + 3) * gs - gs / 4, (y + 2) * gs + gs / 2, (x + 3) * gs + gs / 4, (y + 2) * gs + gs / 2);
        g.drawLine ((x + 3)  * gs , (y + 2) * gs + gs / 4, (x + 3) * gs, (y + 3) * gs - gs / 4);
        // Draw body
        if (IPin[0].getLevel() == 5 && IPin[1].getLevel() == 0) {
            g.setColor (LEDColorOn);
        } else {
            g.setColor (LEDColorOff);
        }
        g.fillOval ((x + 4) * gs, (y + 1) * gs, 2 * gs + 1, 2 * gs + 1);
        g.setColor (LEDColorOn);
        g.drawOval ((x + 4) * gs, (y + 1) * gs, 2 * gs, 2 * gs);
    }

//----------------------------------------------------------------------------
// Save this LED
//----------------------------------------------------------------------------
    public void Save (PrintStream myPrintStream) {
        myPrintStream.println ("describe component LED");
        myPrintStream.println (" pos " + Pos.x + " " + Pos.y);
        if (LEDColorOn == Color.red) {
            myPrintStream.println (" color red");
        } else if (LEDColorOn == Color.green) {
            myPrintStream.println (" color green");
        } else if (LEDColorOn == Color.yellow) {
            myPrintStream.println (" color yellow");
        }

        myPrintStream.println ("end describe");
    }

//----------------------------------------------------------------------------
// Initialize the input pins of this LED, preventing the LED is lightning
// when there's no power.
//----------------------------------------------------------------------------
    public void InitBeforeSimulate() {
        IPin[0].InitBeforeSimulate();
        IPin[1].InitBeforeSimulate();
        IPin[0].setLevel (-1);
        IPin[1].setLevel (-1);
    }

}