//****************************************************************************
// ---- version information ----
//
// EightBitSerInShiftReg.java   v 1.00 b1
// Written by:                  I. van Rienen / E-mail ivr@bart.nl
// URL:                         http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for an Eight bit serial in,
// parallel out shift register
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

class EightBitSerInShiftReg extends IntegratedCircuit {

//----------------------------------------------------------------------------
// The constructor of a new Eight bit serial in parallel out shift register
//----------------------------------------------------------------------------
    public EightBitSerInShiftReg(Pin PinGrid[][], int x, int y) {
        super (x, y, 10, 15, 3, 1, 4, 13, 3, 8);      // x,y,w,h HitBox x,y,w,h,I,O
        IPin[0] = new InputPin("C1", 1, 2, 2, 0, 0, 0, ComponentPin.PIN_EDGETRIGGERED); // name, x, y, w, h, flags
        IPin[1] = new InputPin("R", 1, 4, 2, 0, 0, 0, ComponentPin.PIN_NEGATIVE); // name, x, y, w, h, flags
        IPin[2] = new InputPin("D", 1, 6, 2, 0, 0, 0, ComponentPin.PIN_NOACTION); // name, x, y, w, h, flags

        OPin[0] = new OutputPin("1", 9, 6, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        OPin[1] = new OutputPin("2", 9, 7, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        OPin[2] = new OutputPin("3", 9, 8, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        OPin[3] = new OutputPin("4", 9, 9, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        OPin[4] = new OutputPin("5", 9, 10, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        OPin[5] = new OutputPin("6", 9, 11, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        OPin[6] = new OutputPin("7", 9, 12, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        OPin[7] = new OutputPin("8", 9, 13, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        ComponentName = "8-bit serial in shift register";
        ClassName = "EightBitSerInShiftReg";
        RegisterPins (PinGrid, x, y);
    }

//----------------------------------------------------------------------------
// The constructor of a new shift register, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public EightBitSerInShiftReg (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        EightBitSerInShiftReg NewComponent = new EightBitSerInShiftReg(this, xo, yo);
        return NewComponent;
    }

//----------------------------------------------------------------------------
// Here is the code that's simulating the component
//----------------------------------------------------------------------------
    public void SimulateLogic() {
        int ix;

        if (IPin[0].OldLevel == 0 && IPin[0].getLevel() == 5) {
             // Clock transition Low to High
             for (ix = 7; ix > 0; ix--) {
                OPin[ix].setLevel(OPin[ix - 1].getLevel());
            }
            OPin[0].setLevel (IPin[2].getLevel());
       } else {
            // Nothing happened. Outputs remain unchanged.
            // do setLevel() for modifying OldLevel.
             for (ix = 0; ix < 8; ix++) {
                OPin[ix].setLevel(OPin[ix].getLevel());
            }
        }

        if (IPin[1].getLevel() == 5) {
             // Reset
             for (ix = 0; ix < 8; ix++) {
                OPin[ix].setLevel(0);
            }
        }
        IPin[0].OldLevel = IPin[0].getLevel();
    }

//----------------------------------------------------------------------------
// Inform all components connected to the outputs with the OLD level,
// because this is an edge-triggered component.
//----------------------------------------------------------------------------
    public void Simulate(int id) {
        InformConnectedComponentsOldLevel(id);
    }
}