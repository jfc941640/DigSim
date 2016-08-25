//****************************************************************************
// ---- version information ----
//
// SevenSegementDisplay.java     v 1.00 b1
// Written by:                   I. van Rienen / E-mail ivr@bart.nl
// URL:                          http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for a 7-segment display with common kathode.
// Segment description:
//       a
//      ----
//   f |    | b
//     | g  |
//      ----
//   e |    | c
//     |    |
//      ---- .
//       d
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

class SevenSegmentDisplay extends IntegratedCircuit {
    protected Color LEDColorOn;
    final static Color LEDColorOff = Color.darkGray;
    static final int seg_a = 0;
    static final int seg_b = 1;
    static final int seg_c = 2;
    static final int seg_d = 3;
    static final int seg_e = 4;
    static final int seg_f = 5;
    static final int seg_g = 6;
    static final int seg_dp = 7;
    static final int ck = 8;

//----------------------------------------------------------------------------
// The constructor of a new 7-segment display
//----------------------------------------------------------------------------
    public SevenSegmentDisplay(Pin PinGrid[][], Color MyColor, int x, int y) {
        super (x, y, 12, 12, 3, 1, 6, 8, 9, 0);      // x,y,w,h HitBox x,y,w,h,I,O
        LEDColorOn = MyColor;
        IPin[seg_a] = new InputPin("a", 1, 2, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        IPin[seg_b] = new InputPin("b", 1, 3, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        IPin[seg_c] = new InputPin("c", 1, 4, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        IPin[seg_d] = new InputPin("d", 1, 5, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        IPin[seg_e] = new InputPin("e", 1, 6, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        IPin[seg_f] = new InputPin("f", 1, 7, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        IPin[seg_g] = new InputPin("g", 1, 8, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        IPin[seg_dp] = new InputPin("dp", 11, 8, -2, 0, 0, 0, ComponentPin.PIN_TEXT_INVISIBLE); // name, x, y, w, h, inv
        IPin[ck] = new InputPin("CK", 6, 11, 0, -2, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        ComponentName = "7-segment display";
        ClassName = "SevenSegmentDisplay";
        RegisterPins (PinGrid, x, y);
    }

//----------------------------------------------------------------------------
// The constructor of a new 7-seg display, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public SevenSegmentDisplay (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        SevenSegmentDisplay NewComponent = new SevenSegmentDisplay(this, xo, yo);
        NewComponent.LEDColorOn = LEDColorOn;
        return NewComponent;
    }

//----------------------------------------------------------------------------
// Here is the code that's simulating the component
// It's empty because this is not an active component
//----------------------------------------------------------------------------
    public void SimulateLogic() {
    }

//----------------------------------------------------------------------------
// Save this 7-segment display
//----------------------------------------------------------------------------
    public void Save (PrintStream myPrintStream) {
        myPrintStream.println ("describe component SevenSegmentDisplay");
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
// Draw the display.
//----------------------------------------------------------------------------
    public void draw(Graphics g, int xp, int yp, int gs) {
        int x = Pos.x - xp;
        int y = Pos.y - yp;

        // clear background
        g.setColor (Color.black);
        g.fillRect ((x + HitBox.x) * gs, (y + HitBox.y) * gs, HitBoxSize.width * gs, HitBoxSize.height * gs);

        // Draw segment a
        if (IPin[seg_a].getLevel() == 5 && IPin[ck].getLevel() == 0)  {
            g.setColor (LEDColorOn);
        } else {
            g.setColor (LEDColorOff);
        }
        g.fillRect ( (int)((x + 4.5) * gs + 1), (int)((y + 1.25) * gs), (int)(gs * 2.75), gs / 2);

        // Draw segment b
        if (IPin[seg_b].getLevel() == 5 && IPin[ck].getLevel() == 0)  {
            g.setColor (LEDColorOn);
        } else {
            g.setColor (LEDColorOff);
        }
        g.fillRect ( (int)((x + 7.5) * gs), (int)((y + 1.5) * gs), gs / 2, gs * 3 - 1);

        // Draw segment c
        if (IPin[seg_c].getLevel() == 5 && IPin[ck].getLevel() == 0)  {
            g.setColor (LEDColorOn);
        } else {
            g.setColor (LEDColorOff);
        }
        g.fillRect ( (int)((x + 7.5) * gs), (int)((y + 4.5) * gs + 1), gs / 2, gs * 3 - 1);

        // Draw segment d
        if (IPin[seg_d].getLevel() == 5 && IPin[ck].getLevel() == 0)  {
            g.setColor (LEDColorOn);
        } else {
            g.setColor (LEDColorOff);
        }
        g.fillRect ( (int)((x + 4.5) * gs + 1), (int)((y + 7.25) * gs), (int)(gs * 2.75), gs / 2);

        // Draw segment e
        if (IPin[seg_e].getLevel() == 5 && IPin[ck].getLevel() == 0)  {
            g.setColor (LEDColorOn);
        } else {
            g.setColor (LEDColorOff);
        }
        g.fillRect ( (int)((x + 4) * gs), (int)((y + 4.5) * gs + 1), gs / 2, gs * 3 - 1);

        // Draw segment f
        if (IPin[seg_f].getLevel() == 5 && IPin[ck].getLevel() == 0)  {
            g.setColor (LEDColorOn);
        } else {
            g.setColor (LEDColorOff);
        }
        g.fillRect ( (int)((x + 4) * gs), (int)((y + 1.5) * gs), gs / 2, gs * 3 - 1);

        // Draw segment g
        if (IPin[seg_g].getLevel() == 5 && IPin[ck].getLevel() == 0)  {
            g.setColor (LEDColorOn);
        } else {
            g.setColor (LEDColorOff);
        }
        g.fillRect ( (int)((x + 4.5) * gs + 1), (int)((y + 4.25) * gs), (int)(gs * 2.75), gs / 2);

        // Draw dp
        if (IPin[seg_dp].getLevel() == 5 && IPin[ck].getLevel() == 0)  {
            g.setColor (LEDColorOn);
        } else {
            g.setColor (LEDColorOff);
        }
        g.fillOval ( (int)((x + 8) * gs + 1), (int)((y + 7.75) * gs), (int)(gs / 1.5 + 1), (int)(gs / 1.5 + 1));

        // Integrated Circuit draw pins and outline
        super.draw (g, xp, yp, gs);
    }

//----------------------------------------------------------------------------
// Initialize all inputs before simulating.
// It prevents the LEDs will continue light.
//----------------------------------------------------------------------------
    public void InitBeforeSimulate() {
        for (int ix = 0; ix < 9 ; ix++) {
            IPin[ix].InitBeforeSimulate();
            IPin[ix].setLevel (-1);
        }
    }

}