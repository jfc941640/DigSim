//****************************************************************************
// ---- version information ----
//
// OctalLatch.java       v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for an octal Latch
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

class OctalLatch extends IntegratedCircuit {

//----------------------------------------------------------------------------
// The constructor of a new Octal Latch
//----------------------------------------------------------------------------
    public OctalLatch(Pin PinGrid[][], int x, int y) {
        super (x, y, 10, 13, 3, 1, 4, 11, 9, 8);      // x,y,w,h HitBox x,y,w,h,I,O
        IPin[0] = new InputPin("EN", 1, 2, 2, 0, 0, 0, ComponentPin.PIN_NEGATIVE); // name, x, y, w, h, flags
        IPin[1] = new InputPin("1", 1, 4, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        IPin[2] = new InputPin("2", 1, 5, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        IPin[3] = new InputPin("3", 1, 6, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        IPin[4] = new InputPin("4", 1, 7, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        IPin[5] = new InputPin("5", 1, 8, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        IPin[6] = new InputPin("6", 1, 9, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        IPin[7] = new InputPin("7", 1, 10, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        IPin[8] = new InputPin("8", 1, 11, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags

        OPin[0] = new OutputPin("1", 9, 4, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        OPin[1] = new OutputPin("2", 9, 5, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        OPin[2] = new OutputPin("3", 9, 6, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        OPin[3] = new OutputPin("4", 9, 7, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        OPin[4] = new OutputPin("5", 9, 8, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        OPin[5] = new OutputPin("6", 9, 9, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        OPin[6] = new OutputPin("7", 9, 10, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        OPin[7] = new OutputPin("8", 9, 11, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        ComponentName = "Octal latch";
        ClassName = "OctalLatch";
        RegisterPins (PinGrid, x, y);
    }

//----------------------------------------------------------------------------
// The constructor of a new octal Latch, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public OctalLatch (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        OctalLatch NewComponent = new OctalLatch(this, xo, yo);
        return NewComponent;
    }

//----------------------------------------------------------------------------
// Here is the code that's simulating the component
//----------------------------------------------------------------------------
    public void SimulateLogic() {
        int ix;

        if (IPin[0].getLevel() == 5) {
            // Enable
             for (ix = 0; ix < 8; ix++) {
                OPin[ix].setLevel(IPin[1 + ix].getLevel());
            }
        }
    }
}