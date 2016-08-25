//****************************************************************************
// ---- version information ----
//
// ThreeNorPort.java     v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for a 3-input NOR port
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

class ThreeNorPort extends OrPort {

//----------------------------------------------------------------------------
// The constructor of a 3-input NOR port
//----------------------------------------------------------------------------
    public ThreeNorPort(Pin PinGrid[][], int x, int y) {
        super(3, x, y, ComponentPin.PIN_TEXT_INVISIBLE | ComponentPin.PIN_NEGATIVE);
        ComponentName = "3-input NOR port";
        ClassName = "ThreeNorPort";
        IPin[0] = new InputPin("A", 1, 2, 2, 0, 0, 0, ComponentPin.PIN_TEXT_INVISIBLE);
        IPin[1] = new InputPin("B", 1, 3, 2, 0, 0, 0, ComponentPin.PIN_TEXT_INVISIBLE);
        IPin[2] = new InputPin("C", 1, 4, 2, 0, 0, 0, ComponentPin.PIN_TEXT_INVISIBLE);
        RegisterPins (PinGrid, x, y);
    }

//----------------------------------------------------------------------------
// The constructor of a new 3-input NOR port, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public ThreeNorPort (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        ThreeNorPort NewComponent = new ThreeNorPort(this, xo, yo);
        return NewComponent;
    }
}