//****************************************************************************
// ---- version information ----
//
// EightBitParInShiftReg.java   v 1.00 b1
// Written by:                  I. van Rienen / E-mail ivr@bart.nl
// URL:                         http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for an Eight bit parallel in,
// serial out shift register
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

class EightBitParInShiftReg extends IntegratedCircuit {
    int Mem[];

//----------------------------------------------------------------------------
// The constructor of a new Eight bit parallel in serial out shift register
//----------------------------------------------------------------------------
    public EightBitParInShiftReg(Pin PinGrid[][], int x, int y) {
        super (x, y, 10, 17, 3, 1, 4, 15, 11, 2);      // x,y,w,h HitBox x,y,w,h,I,O
        Mem = new int[8];
        IPin[0] = new InputPin("CLK", 1, 2, 2, 0, 0, 0, ComponentPin.PIN_EDGETRIGGERED); // name, x, y, w, h, flags
        IPin[1] = new InputPin("CLR", 1, 4, 2, 0, 0, 0, ComponentPin.PIN_NEGATIVE); // name, x, y, w, h, flags
        IPin[2] = new InputPin("LD", 1, 6, 2, 0, 0, 0, ComponentPin.PIN_NEGATIVE); // name, x, y, w, h, flags
        IPin[3] = new InputPin("1", 1, 8, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        IPin[4] = new InputPin("2", 1, 9, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        IPin[5] = new InputPin("3", 1, 10, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        IPin[6] = new InputPin("4", 1, 11, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        IPin[7] = new InputPin("5", 1, 12, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        IPin[8] = new InputPin("6", 1, 13, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        IPin[9] = new InputPin("7", 1, 14, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        IPin[10] = new InputPin("8", 1, 15, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags

        OPin[0] = new OutputPin("Q", 9, 13, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        OPin[1] = new OutputPin("Q", 9, 15, -2, 0, 0, 0, ComponentPin.PIN_NEGATIVE); // name, x, y, w, h, flags
        ComponentName = "8-bit parallel in shift register";
        ClassName = "EightBitParInShiftReg";
        RegisterPins (PinGrid, x, y);
    }

//----------------------------------------------------------------------------
// The constructor of a new shift register, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public EightBitParInShiftReg (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
        Mem = new int[8];
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        EightBitParInShiftReg NewComponent = new EightBitParInShiftReg(this, xo, yo);
        return NewComponent;
    }

//----------------------------------------------------------------------------
// Here is the code that's simulating the component
//----------------------------------------------------------------------------
    public void SimulateLogic() {
        int ix;

        if (IPin[2].getLevel() == 5) { // Load
            for (ix = 0; ix < 8 ; ix++) {
                Mem[ix] = IPin[ix + 3].getLevel();
            }
        }
        if (IPin[1].getLevel() == 5) { // Reset
             // Reset
             for (ix = 0; ix < 8; ix++) {
                Mem[ix] = 0;
            }
        }

        if (IPin[0].OldLevel == 0 && IPin[0].getLevel() == 5) {
            // Clock transition Low to High, shift Mem
            for (ix = 7; ix > 0; ix--) {
                Mem [ix] = Mem[ix-1];
            }
            Mem[0] = 0;
        }
        OPin[0].setLevel(Mem[7]);
        OPin[1].setLevel(Mem[7]);

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