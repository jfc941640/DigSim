//****************************************************************************
// ---- version information ----
//
// BCDToSevenSegDecoder.java   v 1.00 b1
// Written by:                 I. van Rienen / E-mail ivr@bart.nl
// URL:                        http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for BCD to 7-seg decoder
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

class BCDToSevenSegDecoder extends IntegratedCircuit {
    static final int LE = 0;
    static final int BI = 1;
    static final int LT = 2;
    static final int A = 3;
    static final int B = 4;
    static final int C = 5;
    static final int D = 6;

    static final int a = 0;
    static final int b = 1;
    static final int c = 2;
    static final int d = 3;
    static final int e = 4;
    static final int f = 5;
    static final int g = 6;
    static final int SegLev[][] = {
        { 5, 5, 5, 5, 5, 5, 0} ,        // 0
        { 0, 5, 5, 0, 0, 0, 0} ,        // 1
        { 5, 5, 0, 5, 5, 0, 5} ,        // 2
        { 5, 5, 5, 5, 0, 0, 5} ,        // 3
        { 0, 5, 5, 0, 0, 5, 5} ,        // 4
        { 5, 0, 5, 5, 0, 5, 5} ,        // 5
        { 5, 0, 5, 5, 5, 5, 5} ,        // 6
        { 5, 5, 5, 0, 0, 0, 0} ,        // 7
        { 5, 5, 5, 5, 5, 5, 5} ,        // 8
        { 5, 5, 5, 5, 0, 5, 5} ,        // 9
        { 5, 5, 5, 0, 5, 5, 5} ,        // A
        { 0, 0, 5, 5, 5, 5, 5} ,        // B
        { 5, 0, 0, 5, 5, 5, 0} ,        // C
        { 0, 5, 5, 5, 5, 0, 5} ,        // D
        { 5, 0, 0, 5, 5, 5, 5} ,        // E
        { 5, 0, 0, 0, 5, 5, 5}   };     // F
    int ActVal = 0;

//----------------------------------------------------------------------------
// The constructor of a new BCD to Seven-Segment decoder
//----------------------------------------------------------------------------
    public BCDToSevenSegDecoder(Pin PinGrid[][], int x, int y) {
        super (x, y, 10, 13 , 3, 1, 4, 11, 7, 7);      // x,y,w,h HitBox x,y,w,h,I,O
        IPin[LE] = new InputPin("LE", 1, 2, 2, 0, 0, 0, ComponentPin.PIN_NEGATIVE); // name, x, y, w, h, inv
        IPin[BI] = new InputPin("BI", 1, 4, 2, 0, 0, 0, ComponentPin.PIN_NEGATIVE); // name, x, y, w, h, inv
        IPin[LT] = new InputPin("LT", 1, 6, 2, 0, 0, 0, ComponentPin.PIN_NEGATIVE); // name, x, y, w, h, inv
        IPin[A] = new InputPin("A", 1, 8, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        IPin[B] = new InputPin("B", 1, 9, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        IPin[C] = new InputPin("C", 1, 10, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        IPin[D] = new InputPin("D", 1, 11, 2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv

        OPin[a] = new OutputPin("a", 9, 3, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        OPin[b] = new OutputPin("b", 9, 4, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        OPin[c] = new OutputPin("c", 9, 5, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        OPin[d] = new OutputPin("d", 9, 6, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        OPin[e] = new OutputPin("e", 9, 7, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        OPin[f] = new OutputPin("f", 9, 8, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv
        OPin[g] = new OutputPin("g", 9, 9, -2, 0, 0, 0, ComponentPin.PIN_NORMAL); // name, x, y, w, h, inv

        ComponentName = "BCD to 7-segment decoder";
        ClassName = "BCDToSevenSegDecoder";
        RegisterPins (PinGrid, x, y);
    }

//----------------------------------------------------------------------------
// The constructor of a new decoder, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public BCDToSevenSegDecoder (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        BCDToSevenSegDecoder NewComponent = new BCDToSevenSegDecoder(this, xo, yo);
        return NewComponent;
    }

//----------------------------------------------------------------------------
// Here is the code that's simulating the component
//----------------------------------------------------------------------------
    public void SimulateLogic() {
        int ix;
        if (IPin[LT].getLevel() == 5) { // Lamp test
            for (ix = 0; ix < 7; ix++) {
                OPin[ix].Level = 5;
            }
            return;
        }
        if (IPin[BI].getLevel() == 5) { // Blank input
            for (ix = 0; ix < 7; ix++) {
                OPin[ix].Level = 0;
            }
            return;
        }
        if (IPin[LE].getLevel() == 5) { // Latch enable
            ActVal = 0;
            if (IPin[A].getLevel() == 5) ActVal += 1;
            if (IPin[B].getLevel() == 5) ActVal += 2;
            if (IPin[C].getLevel() == 5) ActVal += 4;
            if (IPin[D].getLevel() == 5) ActVal += 8;
        }

        for (ix = 0; ix < 7; ix++) {
            OPin[ix].Level = SegLev[ActVal] [ix];
        }
    }
}