//****************************************************************************
// ---- version information ----
//
// JKFlipFlop.java       v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for a JK-flip flop
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

class JKFlipFlop extends IntegratedCircuit {

//----------------------------------------------------------------------------
// The constructor of a JK flip flop
//----------------------------------------------------------------------------
    public JKFlipFlop(Pin PinGrid[][], int x, int y) {
        super (x, y, 10, 6, 3, 1, 4, 4, 3, 2);      // x,y,w,h HitBox x,y,w,h,I,O
        IPin[0] = new InputPin("J", 1, 2, 2, 0, 0, 0, ComponentPin.PIN_NOACTION); // name, x, y, w, h, flags
        IPin[1] = new InputPin("C", 1, 3, 2, 0, 0, 0, ComponentPin.PIN_EDGETRIGGERED); // name, x, y, w, h, flags
        IPin[2] = new InputPin("K", 1, 4, 2, 0, 0, 0, ComponentPin.PIN_NOACTION); // name, x, y, w, h, flags
        OPin[0] = new OutputPin("Q", 9, 2, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, flags
        OPin[1] = new OutputPin("Q", 9, 4, -2, 0, 0, 0, ComponentPin.PIN_NEGATIVE); // name, x, y, w, h, flags
        ComponentName = "JK-flipflop";
        ClassName = "JKFlipFlop";
        RegisterPins (PinGrid, x, y);
    }

//----------------------------------------------------------------------------
// The constructor of a new JK flip flop, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public JKFlipFlop (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        JKFlipFlop NewComponent = new JKFlipFlop(this, xo, yo);
        return NewComponent;
    }

//----------------------------------------------------------------------------
// This method is simulating the component
//----------------------------------------------------------------------------
    public void SimulateLogic() {
        if (IPin[1].OldLevel == 0 && IPin[1].getLevel() == 5) {
            // System.out.println ("**** Edge triggered");
            // Clock transition Low to High
             if (IPin[0].getLevel() == 5 && IPin[2].getLevel() == 5) { // JK
                // Toggle output
                if (OPin[0].getLevel() == 0) {
                    OPin[0].setLevel(5);
                    OPin[1].setLevel(5);
                } else {
                    OPin[0].setLevel(0);
                    OPin[1].setLevel(0);
                }
            } else if (IPin[0].getLevel() == 5) {       // J
                    OPin[0].setLevel(5);
                    OPin[1].setLevel(5);
            } else if (IPin[2].getLevel() == 5) {       // K
                    OPin[0].setLevel(0);
                    OPin[1].setLevel(0);
            } // Outputs remain unchanged if J == K == 0
        } else {
           // Nothing happened. Outputs remain unchanged.
            // do setLevel() for modifying OldLevel.
            OPin[0].setLevel (OPin[0].getLevel());
            OPin[1].setLevel (OPin[0].getLevel());
        }

        IPin[1].OldLevel = IPin[1].getLevel();
    }

    public void Simulate(int id) {
        InformConnectedComponentsOldLevel(id);
    }

}