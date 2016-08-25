//****************************************************************************
// ---- version information ----
//
// FourBitBinaryCounter.java      v 1.00 b1
// Written by:                    I. van Rienen / E-mail ivr@bart.nl
// URL:                           http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for creating and maintaining a
// 4-bit binary counter
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

class FourBitBinaryCounter extends IntegratedCircuit {
    int Count = 0;

//----------------------------------------------------------------------------
// The constructor of a new 4-bit binary counter
//----------------------------------------------------------------------------
    public FourBitBinaryCounter(Pin PinGrid[][], int x, int y) {
        super (x, y, 10, 7 , 3, 1, 4, 5, 1, 4);      // x,y,w,h HitBox x,y,w,h,I,O
        IPin[0] = new InputPin("C", 1, 3, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv

        OPin[0] = new OutputPin("A", 9, 2, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        OPin[1] = new OutputPin("B", 9, 3, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        OPin[2] = new OutputPin("C", 9, 4, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        OPin[3] = new OutputPin("D", 9, 5, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv

        ComponentName = "4-bit binary counter";
        ClassName = "FourBitBinaryCounter";
        RegisterPins (PinGrid, x, y);
    }

//----------------------------------------------------------------------------
// The constructor of a new counter, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public FourBitBinaryCounter (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        FourBitBinaryCounter NewComponent = new FourBitBinaryCounter(this, xo, yo);
        return NewComponent;
    }

//----------------------------------------------------------------------------
// Here is the code that's simulating the component
//----------------------------------------------------------------------------
    public void SimulateLogic() {
         if (IPin[0].getOldLevel() == 0 && IPin[0].getLevel() == 5) {
            Count++;
            if (Count < 0 || Count > 15) Count = 0;
        }
        if ((Count & 0x01) == 0x01) {
            OPin[0].setLevel (5);
        } else {
            OPin[0].setLevel (0);
        }
        if ((Count & 0x02) == 0x02) {
            OPin[1].setLevel (5);
        } else {
            OPin[1].setLevel (0);
        }
        if ((Count & 0x04) == 0x04) {
            OPin[2].setLevel (5);
        } else {
            OPin[2].setLevel (0);
        }
        if ((Count & 0x08) == 0x08) {
            OPin[3].setLevel (5);
        } else {
            OPin[3].setLevel (0);
        }
        IPin[0].OldLevel = IPin[0].getLevel();
    }
}