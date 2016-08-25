//****************************************************************************
// ---- version information ----
//
// Probe.java            v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for a Logic Analyzer Probe
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

class Probe extends ElectronicComponent {
    Point ChannelPos;
    Dimension ChannelDim;
    int LevelHistory[];
    static final int MAX_HISTORY = 100;
    boolean clockup_probe = false;
    boolean clockdn_probe = false;
    int OldLevel;

//----------------------------------------------------------------------------
// The constructor of a new analyzer probe
//----------------------------------------------------------------------------
    public Probe (Pin PinGrid[][], int x, int y) {
        super (x, y, 4, 6, 1, 1, 2, 4, 1, 0);      // x,y,w,h HitBox x,y,w,h, I,O
        ChannelPos = new Point(0, 0);
        ChannelDim = new Dimension(0, 0);
        LevelHistory = new int[MAX_HISTORY];
        ComponentName = "Probe";
        ClassName = "Probe";
        IPin[0] = new InputPin("Probe", 2, 5, 0, -2, 0, -1, ComponentPin.PIN_NORMAL); // name, x, y, w, h, txo, tyo, flags
        RegisterPins (PinGrid, x, y);
    }

//----------------------------------------------------------------------------
// The constructor of a new analyzer probe with the specified variables
//----------------------------------------------------------------------------
    public Probe (Pin PinGrid[][], int x, int y, String text, boolean up, boolean dn) {
        super (x, y, 4, 6, 1, 1, 2, 4, 1, 0);      // x,y,w,h HitBox x,y,w,h, I,O
        clockup_probe = up;
        clockdn_probe = dn;
        ChannelPos = new Point(0, 0);
        ChannelDim = new Dimension(0, 0);
        LevelHistory = new int[MAX_HISTORY];
        ComponentName = "Probe";
        ClassName = "Probe";
        IPin[0] = new InputPin(text, 2, 5, 0, -2, 0, -1, ComponentPin.PIN_NORMAL); // name, x, y, w, h, txo, tyo, flags
        RegisterPins (PinGrid, x, y);
    }

//----------------------------------------------------------------------------
// The constructor of a new Probe, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public Probe (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
        ChannelPos = new Point(0, 0);
        ChannelDim = new Dimension(0, 0);
        LevelHistory = new int[MAX_HISTORY];
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        Probe NewComponent = new Probe(this, xo, yo);
        return NewComponent;
    }

//----------------------------------------------------------------------------
// Method for Drawing this probe in the schematic
//----------------------------------------------------------------------------
    public void draw(Graphics g, int xp, int yp, int gs) {
        super.draw (g, xp, yp, gs);
        int x = Pos.x - xp;
        int y = Pos.y - yp;

        // Draw HitBox and Dimension
        // DrawHitBox(g, x, y, gs);

        // Draw all I Pins
        DrawInputPins(g, x, y, gs);

        // Draw the body
        g.setColor (ComponentColor);
        g.drawOval ((int)((x + 1.5) * gs) , (y + 2) * gs, gs, gs);
        g.drawLine ((int)((x + 1.5) * gs), (y + 3) * gs,
                    (int)((x + 2.5) * gs), (y + 2) * gs );

    }

//----------------------------------------------------------------------------
// Method for saving the probe
//----------------------------------------------------------------------------
   public void Save (PrintStream myPrintStream) {
        if (clockup_probe) {
            myPrintStream.println ("describe component ProbeUp");
        } else if (clockdn_probe) {
            myPrintStream.println ("describe component ProbeDn");
        } else  {
            myPrintStream.println ("describe component Probe");
        }

        myPrintStream.println (" pos " + Pos.x + " " + Pos.y);
        myPrintStream.println (" Text " + IPin[0].getName());
        myPrintStream.println ("end describe");
    }
}