//****************************************************************************
// ---- version information ----
//
// SRLatch.java          v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for a Set / Reset latch
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

class SRLatch extends IntegratedCircuit {

//----------------------------------------------------------------------------
// The constructor of a SR latch
//----------------------------------------------------------------------------
    public SRLatch(Pin PinGrid[][], int x, int y) {
        super (x, y, 10, 6, 3, 1, 4, 4, 2, 2);      // x,y,w,h HitBox x,y,w,h,I,O
        IPin[0] = new InputPin("S", 1, 2, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        IPin[1] = new InputPin("R", 1, 4, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        OPin[0] = new OutputPin("Q", 9, 2, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        OPin[1] = new OutputPin("Q", 9, 4, -2, 0, 0, 0, ComponentPin.PIN_NEGATIVE); // name, x, y, w, h, inv
        ComponentName = "SR-latch";
        ClassName = "SRLatch";
        RegisterPins (PinGrid, x, y);
    }

//----------------------------------------------------------------------------
// The constructor of a new SR latch, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public SRLatch (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        SRLatch NewComponent = new SRLatch(this, xo, yo);
        return NewComponent;
    }

//----------------------------------------------------------------------------
// This method is simulating the component
//----------------------------------------------------------------------------
    public void SimulateLogic() {
        if (IPin[0].getLevel() == 5) { // set
            OPin[0].Level = OPin[1].Level = 5;
        }
        if (IPin[1].getLevel() == 5) { // reset
            OPin[0].Level = OPin[1].Level = 0;
        }
    }
}