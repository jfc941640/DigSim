//****************************************************************************
// ---- version information ----
//
// ThreeToEightLineDecoder.java   v 1.00 b1
// Written by:                    I. van Rienen / E-mail ivr@bart.nl
// URL:                           http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for creating and maintaining a
// 3-to 8-line decoder/multiplexer, inverting (74hct138)
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

class ThreeToEightLineDecoder extends IntegratedCircuit {

//----------------------------------------------------------------------------
// The constructor of a new 3- to 8-line decoder
//----------------------------------------------------------------------------
    public ThreeToEightLineDecoder(Pin PinGrid[][], int x, int y) {
        super (x, y, 10, 11 , 3, 1, 4, 9, 6, 8);      // x,y,w,h HitBox x,y,w,h,I,O
        IPin[0] = new InputPin("0", 1, 2, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        IPin[1] = new InputPin("1", 1, 3, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        IPin[2] = new InputPin("2", 1, 4, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv

        IPin[3] = new InputPin("EN1", 1, 7, 2, 0, 0, 0, ComponentPin.PIN_NEGATIVE | ComponentPin.PIN_TEXT_INVISIBLE); // name, x, y, w, h, inv
        IPin[4] = new InputPin("EN2", 1, 8, 2, 0, 0, 0, ComponentPin.PIN_NEGATIVE | ComponentPin.PIN_TEXT_INVISIBLE); // name, x, y, w, h, inv
        IPin[5] = new InputPin("EN3", 1, 9, 2, 0, 0, 0, ComponentPin.PIN_TEXT_INVISIBLE); // name, x, y, w, h, inv

        OPin[0] = new OutputPin("0", 9, 2, -2, 0, 0, 0, ComponentPin.PIN_NEGATIVE); // name, x, y, w, h, inv
        OPin[1] = new OutputPin("1", 9, 3, -2, 0, 0, 0, ComponentPin.PIN_NEGATIVE); // name, x, y, w, h, inv
        OPin[2] = new OutputPin("2", 9, 4, -2, 0, 0, 0, ComponentPin.PIN_NEGATIVE); // name, x, y, w, h, inv
        OPin[3] = new OutputPin("3", 9, 5, -2, 0, 0, 0, ComponentPin.PIN_NEGATIVE); // name, x, y, w, h, inv
        OPin[4] = new OutputPin("4", 9, 6, -2, 0, 0, 0, ComponentPin.PIN_NEGATIVE); // name, x, y, w, h, inv
        OPin[5] = new OutputPin("5", 9, 7, -2, 0, 0, 0, ComponentPin.PIN_NEGATIVE); // name, x, y, w, h, inv
        OPin[6] = new OutputPin("6", 9, 8, -2, 0, 0, 0, ComponentPin.PIN_NEGATIVE); // name, x, y, w, h, inv
        OPin[7] = new OutputPin("7", 9, 9, -2, 0, 0, 0, ComponentPin.PIN_NEGATIVE); // name, x, y, w, h, inv

        ComponentName = "3- to 8-line decoder";
        ClassName = "ThreeToEightLineDecoder";
        RegisterPins (PinGrid, x, y);
    }

//----------------------------------------------------------------------------
// The constructor of a new decoder, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public ThreeToEightLineDecoder (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        ThreeToEightLineDecoder NewComponent = new ThreeToEightLineDecoder(this, xo, yo);
        return NewComponent;
    }

//----------------------------------------------------------------------------
// Here is the code that's simulating the component
//----------------------------------------------------------------------------
    public void SimulateLogic() {
        int ix, o = 0;
        if (IPin[3].getLevel() == 0 ||  // EN1
            IPin[4].getLevel() == 0 ||  // EN2
            IPin[5].getLevel() == 0 ) { // EN3
            // IC NOT enabled, All outputs high.
            for (ix = 0; ix < 8; ix++) {
                OPin[ix].Level = 0;
            }
            return;
        }
        if (IPin[0].getLevel() == 5) o |= 1;
        if (IPin[1].getLevel() == 5) o |= 2;
        if (IPin[2].getLevel() == 5) o |= 4;
        for (ix = 0; ix < 8; ix++) {
            if (ix != o) {
                OPin[ix].Level = 0;
            } else {
                OPin[ix].Level = 5;
            }
        }
    }

//----------------------------------------------------------------------------
// Do some additional drawing
//----------------------------------------------------------------------------
    public void draw(Graphics g, int xp, int yp, int gs) {
        super.draw (g, xp, yp, gs);
        int x = Pos.x - xp;
        int y = Pos.y - yp;

        // Draw the body
        g.setColor (ComponentColor);
        g.drawLine ((x + 3) * gs, (int)((y + 6.5) * gs), (x + 5) * gs, (int)((y + 6.5) * gs));
        g.drawLine ((x + 5) * gs, (int)((y + 6.5) * gs), (x + 5) * gs, (int)((y + 9.5) * gs));
        g.drawLine ((x + 3) * gs, (int)((y + 9.5) * gs), (x + 5) * gs, (int)((y + 9.5) * gs));
    }
}