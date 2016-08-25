import java.applet.Applet;
//****************************************************************************
// ---- version information ----
//
// Switch.java           v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for a Switch
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
import java.awt.*;
import java.util.Vector;

class Switch extends ElectronicComponent {
    boolean SwitchClosed = false;
    Vector ConnCompsA;  // Connected components to pin A
    Vector ConnCompsB;  // Connected components to pin B
    Vector ConnCompsQ;  // Connected components to pin Q
    int ALevel = -1;
    int BLevel = -1;
    int QLevel = -1;

//----------------------------------------------------------------------------
// The constructor of a new Switch
//----------------------------------------------------------------------------
    public Switch (Pin PinGrid[][], int x, int y) {
        super (x, y, 10, 5, 3, 1, 4, 4, 0, 0);      // x,y,w,h HitBox x,y,w,h,I,O
        ComponentName = "Switch";
        ClassName = "Switch";
        RegisterPins (PinGrid, x, y);
    }

//----------------------------------------------------------------------------
// The constructor of a new Switch, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public Switch (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        Switch NewComponent = new Switch(this, xo, yo);
        return NewComponent;
    }

//----------------------------------------------------------------------------
// The user clicked this button. Toggle the position
//----------------------------------------------------------------------------
    public boolean SimMouseDown() {
        SwitchClosed = !SwitchClosed;
        return true;
    }

//----------------------------------------------------------------------------
// Inform all components at the specified position
//----------------------------------------------------------------------------
    public void InformComponents(int id, int ActLevel, Vector ActVector, int x, int y) {
        ElectronicComponent ConnectedComponent;
        for (int ix = 0; ix < ActVector.size(); ix++) {
            ConnectedComponent = (ElectronicComponent) ActVector.elementAt(ix);
            if (ConnectedComponent != this) {
                // System.out.println ("Connected component to switch found");
                ConnectedComponent.ReceivePotential(id, ActLevel, x, y);
            }
        }
    }

//----------------------------------------------------------------------------
// The switch receives a potential v at pos xr, yr
//----------------------------------------------------------------------------
    public void ReceivePotential(int id, int v, int xr, int yr) {
        int ix;
        // System.out.println ("Switch ReceivePotential()");

       if (xr == Pos.x + 1 && yr == Pos.y + 2) {
           // System.out.println ("Switch pin A");
           ALevel = v;
           if (!SwitchClosed) {
               QLevel = v;
               InformComponents(id, v, ConnCompsQ, Pos.x + 9, Pos.y + 3);
           }
           return;
       }
       if (xr == Pos.x + 1 && yr == Pos.y + 4) {
            // System.out.println ("Switch pin B");
            BLevel = v;
            if (SwitchClosed) {
                QLevel = v;
                InformComponents(id, v, ConnCompsQ, Pos.x + 9, Pos.y + 3);
            }
            return;
        }
        if (xr == Pos.x + 9 && yr == Pos.y + 3) {
            // System.out.println ("Switch pin Q");
            QLevel = v;
            if (SwitchClosed) {
                BLevel = v;
                InformComponents(id, v, ConnCompsB, Pos.x + 1, Pos.y + 4);
            } else {
                ALevel = v;
                InformComponents(id, v, ConnCompsA, Pos.x + 1, Pos.y + 2);
            }
            return;
        }
    }

//----------------------------------------------------------------------------
// Set up the Switch before simulating
//----------------------------------------------------------------------------
    public void SimulateSetUp(int x, int y, Vector ActComps) {
        // System.out.println ("Simulate Set Up switch pos " + x + ", " + y);
        if (Pos.x + 1 == x && Pos.y + 2 == y) {
            // System.out.println ("Pin A");
            ConnCompsA = ActComps;
        }
        if (Pos.x + 1 == x && Pos.y + 4 == y) {
            // System.out.println ("Pin B");
            ConnCompsB = ActComps;
        }
        if (Pos.x +9 == x && Pos.y + 3 == y) {
            // System.out.println ("Pin Q");
            ConnCompsQ = ActComps;
        }
    }

//----------------------------------------------------------------------------
// Remove all pins from PinGrid
//----------------------------------------------------------------------------
    public void RemovePinsGrid(Pin PinGrid[][]) {
        // System.out.println ("Switch RemovePinsGrid()");
        RemovePin(PinGrid[ Pos.x + 1] [Pos.y + 2]);
        RemovePin(PinGrid[ Pos.x + 1] [Pos.y + 4]);
        RemovePin(PinGrid[ Pos.x + 9] [Pos.y + 3]);
    }

//----------------------------------------------------------------------------
// Register all Pins
//----------------------------------------------------------------------------
    public void RegisterPins(Pin PinGrid[][], int x, int y) {
        if (PinGrid == null) return;
        // System.out.println ("Switch RegisterPins()");
        RegisterPin(PinGrid[x + 1][y + 2]);
        RegisterPin(PinGrid[x + 1][y + 4]);
        RegisterPin(PinGrid[x + 9][y + 3]);
    }

//----------------------------------------------------------------------------
// Register all Pins in PinGrid
//----------------------------------------------------------------------------
    public void PlacePinsHere(Pin PinGrid[][]) {
        if (PinGrid == null) return;
        RegisterPins(PinGrid, Pos.x, Pos.y);
    }

//----------------------------------------------------------------------------
// Draw this switch
//----------------------------------------------------------------------------
    public void draw(Graphics g, int xp, int yp, int gs) {
        super.draw (g, xp, yp, gs);
        int x = Pos.x - xp;
        int y = Pos.y - yp;

        // Draw HitBox
        // DrawHitBox(g, x, y, gs);

        // Draw pins
        g.setColor (ComponentPin.PinColor);
        g.drawLine ((x + 1) * gs, (y + 2) * gs, (x + 3) * gs, (y + 2) * gs);          // A
        g.drawLine ((x + 1) * gs, (y + 4) * gs, (x + 3) * gs, (y + 4) * gs);           // B
        g.drawLine ((x + 8) * gs, (y + 3) * gs, (x + 9) * gs, (y + 3) * gs);    // Q
        // Draw Component
        g.setColor (ComponentColor);
        g.fillRect ((x + 3) * gs, (y + 2) * gs - gs / 3, gs / 2, gs / 2 + 1);
        g.fillRect ((x + 3) * gs, (y + 4) * gs - gs / 3, gs / 2, gs / 2 + 1);
        g.fillRect ((int)((x + 7.5) * gs), (y + 3) * gs - gs / 3 + 1, gs/2, gs/2);
        switch (QLevel) {
            case 0:
                g.setColor (Color.green);
                break;
            case 5:
                g.setColor (Color.red);
                break;
            default:
                g.setColor (Color.gray);
                break;
        }
        if (SwitchClosed) {
            g.drawLine ((x + 3) * gs, (y + 4) * gs, (x + 8) * gs, (y + 3) * gs);
        } else {
            g.drawLine ((x + 3) * gs, (y + 2) * gs, (x + 8) * gs, (y + 3) * gs);
        }
    }
}