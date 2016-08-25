//****************************************************************************
// ---- version information ----
//
// Wire.java             v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for a Wire
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

class Wire extends ElectronicComponent {
    protected int x1, y1, x2, y2;
    protected int ChangingWire = 0;
    int ActLevel = -1;
    Vector ConnComps1;
    Vector ConnComps2;
    int ReceivedSimulationCycleID = -1;

//----------------------------------------------------------------------------
// The constructor of a new Wire
//----------------------------------------------------------------------------
    public Wire (Pin PinGrid[][], int x, int y) {
        ComponentName = "wire";
        x1 = x; y1 = y;
        ActLevel = -1;
    }

//----------------------------------------------------------------------------
// The constructor of a new Wire, with the specified coords.
// This constructor is used when a Wire is loaded or copied.
//----------------------------------------------------------------------------
    public Wire (Pin PinGrid[][], int rx1, int ry1, int rx2, int ry2) {
        ComponentName = "wire";
        x1 = rx1; y1 = ry1;
        x2 = rx2; y2 = ry2;
        ActLevel = -1;
        PlacePinsHere(PinGrid);
    }

//----------------------------------------------------------------------------
// The constructor of a new Wire, with the specified coords and level
//----------------------------------------------------------------------------
    public Wire (Pin PinGrid[][], int InitLevel, int rx1, int ry1, int rx2, int ry2) {
        ComponentName = "wire";
        x1 = rx1; y1 = ry1;
        x2 = rx2; y2 = ry2;
        ActLevel = InitLevel;
        PlacePinsHere(PinGrid);
    }

//----------------------------------------------------------------------------
// The constructor of a new Wire, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public Wire (ElectronicComponent CompToCopy, int xo, int yo) {
        ComponentName = "wire";
        ActLevel = -1;
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        Wire NewComponent = new Wire(this, xo, yo);
        NewComponent.x1 = x1 - xo;
        NewComponent.y1 = y1 - yo;
        NewComponent.x2 = x2 - xo;
        NewComponent.y2 = y2 - yo;
        CheckPosition();            // Should be OK, but to really be sure

        return NewComponent;
    }

//----------------------------------------------------------------------------
// Check the position of this wire. if there is an error, adjust
// the position
//----------------------------------------------------------------------------
    public void CheckPosition() {
        if (x1 < 1) x1 = 1;
        if (x1 >= DigSim.MaxXPoints) x1 = DigSim.MaxXPoints-1;
        if (y1 < 1) y1 = 1;
        if (y1 >= DigSim.MaxYPoints) y1 = DigSim.MaxYPoints-1;
        if (x2 < 1) x2 = 1;
        if (x2 >= DigSim.MaxXPoints) x2 = DigSim.MaxXPoints-1;
        if (y2 < 1) y2 = 1;
        if (y2 >= DigSim.MaxYPoints) y2 = DigSim.MaxYPoints-1;
    }

//----------------------------------------------------------------------------
// Set up this wire before simulating.
//----------------------------------------------------------------------------
    public void SimulateSetUp(int x, int y, Vector ActComps) {
        // System.out.println ("Simulate Set Up Wire at pos " + x + ", " + y);
        // System.out.println ("xy1 = " + x1 + ", " + y1 + " xy2 = " + x2 + ", " + y2);
        if (x == x1 && y == y1  ) {
            // System.out.println ("WireEnd 1");
            ConnComps1 = ActComps;
        }
        if (x == x2  && y == y2  ) {
            // System.out.println ("WireEnd 2");
            ConnComps2 = ActComps;
        }
    }

//----------------------------------------------------------------------------
// Initialize this wire before simulating.
//----------------------------------------------------------------------------
    public void InitBeforeSimulate() {
        ReceivedSimulationCycleID = -1;
    }

//----------------------------------------------------------------------------
// The wire is receiving a potential.
// 'Send' this potential to the components connected to the other
// wire-end
//----------------------------------------------------------------------------
    public void ReceivePotential(int id, int v, int xr, int yr) {
        int ix;
        // System.out.println ("Wire ReceivePotential( " + ActLevel + " )");
        if (id == ReceivedSimulationCycleID && ActLevel == v) {
            // System.out.println ("*!*!*!*!* ALREADY RECEIVED !*!*!*!*!*");
            // return to avoid a software loop.
            return;
        }
        ActLevel = v;

        ReceivedSimulationCycleID = id;
        // System.out.println ("Check Wireend 1");
        if (x1 == xr && y1 == yr) {
            // System.out.println ("Wireend 1");
            InformComponents(id, ConnComps2, x2, y2);
            return;
        }
        // System.out.println ("Check Wireend 2");
        if (x2 == xr && y2 == yr) {
            // System.out.println ("Wireend 2");
            InformComponents(id, ConnComps1, x1, y1);
            return;
        }
    }

//----------------------------------------------------------------------------
// Inform all components connected at the specified wire-end
//----------------------------------------------------------------------------
    public void InformComponents(int id, Vector ActVector, int x, int y) {
        ElectronicComponent ConnectedComponent;
        int ix;

        SimulateLogic();
        for (ix = 0; ix < ActVector.size(); ix++) {
            ConnectedComponent = (ElectronicComponent) ActVector.elementAt(ix);
            if (ConnectedComponent != this) {
                // System.out.println ("Connected component to other wireend found");
                ConnectedComponent.ReceivePotential(id, ActLevel, x, y);
            }
        }
    }

//----------------------------------------------------------------------------
// Adjust the position of this wire.
//----------------------------------------------------------------------------
    public boolean AdjustPosition (Pin PinGrid[][], int x, int y) {
        int nx1 = x1;
        int ny1 = y1;
        int nx2 = x2;
        int ny2 = y2;

        if ((ChangingWire & 1) == 1) {
            nx1 += x; ny1 += y;
        }
        if ((ChangingWire & 2) == 2) {
            nx2 += x; ny2 += y;
        }
        if (nx1 == nx2 && ny1 == ny2) {
            return false;       // Can't change, wire size would be 0.
        }
        x1 = nx1; y1 = ny1;
        x2 = nx2; y2 = ny2;
        return true;
    }

//----------------------------------------------------------------------------
// Remove both pins from the grid.
//----------------------------------------------------------------------------
    public void RemovePinsGrid(Pin PinGrid[][]) {
        // System.out.println ("Remove pins xy1 = " + x1 + ", " + y1);
        // System.out.println ("Remove pins xy2 = " + x2 + ", " + y2);
        RemovePin(PinGrid[x1][y1]);
        RemovePin(PinGrid[x2][y2]);
    }

//----------------------------------------------------------------------------
// Add both pins to the grid.
//----------------------------------------------------------------------------
    public void RegisterPins(Pin PinGrid[][], int x, int y) {
        if (PinGrid == null) return;
        // System.out.println ("Register pins xy1 = " + x1 + ", " + y1);
        // System.out.println ("Register pins xy2 = " + x2 + ", " + y2);
        RegisterPin(PinGrid[x1][y1]);
        RegisterPin(PinGrid[x2][y2]);
    }

//----------------------------------------------------------------------------
// Add both pins to the grid.
//----------------------------------------------------------------------------
    public void PlacePinsHere(Pin PinGrid[][]) {
        if (PinGrid == null) return;
        RegisterPins(PinGrid, 0, 0);
    }

//----------------------------------------------------------------------------
// The user released the mouse-button when drawing a new wire,
// set the second coord.
//----------------------------------------------------------------------------
    public void Set2ndCoord (Pin PinGrid[][], int x, int y) {
        x2 = x; y2 = y;
    }

//----------------------------------------------------------------------------
// Draw this wire.
//----------------------------------------------------------------------------
    public void draw(Graphics g, int GridXOffset, int GridYOffset, int gs) {
        if (Selected) {
            g.setColor (Color.white);
            if ((ChangingWire & 1) == 1) {
                g.drawRect ((int)((x1 - GridXOffset - 0.25) * gs), (int)((y1 - GridYOffset - 0.25) * gs), gs / 2, gs / 2);
            }
            if ((ChangingWire & 2) == 2) {
                g.drawRect ((int)((x2 - GridXOffset - 0.25) * gs), (int)((y2 - GridYOffset - 0.25) * gs), gs / 2, gs / 2);
            }
        }
        if (ActLevel == 0)  g.setColor (Color.green);
        else if (ActLevel == 5) g.setColor (Color.red);
        else g.setColor (Color.gray);
        g.drawLine ((x1- GridXOffset) * gs, (y1 - GridYOffset) * gs, (x2 - GridXOffset) * gs, (y2 - GridYOffset) * gs);
    }

//----------------------------------------------------------------------------
// Return the sign
//----------------------------------------------------------------------------
    public int sgn(int val) {
        if (val > 0) return 1;
        if (val < 0) return -1;
        return 0;
    }

//----------------------------------------------------------------------------
// This is a sort of line-drawing function, but it doesn't paint pixels at
// the calculated position but it calls a function.
// This is used to determine if the user pointed somewhere in the middle
// of the wire when moving the wire or at placing junctions.
//----------------------------------------------------------------------------
    public boolean CheckIfPointInWire(int a, int b, int c, int d, int x, int y) {
        int u, s, v, i, d1x, d1y, d2x, d2y, m, n;

        u = c - a;
        v = d - b;
        d1x = sgn(u);
        d1y = sgn(v);
        d2x = sgn(u);
        d2y = 0;
        m = Math.abs(u);
        n = Math.abs(v);
        if (m <= n) {
            d2x = 0 ;
            d2y = sgn(v);
            m = Math.abs(v);
            n = Math.abs(u);
     	}
     	s = m / 2;
        for (i = 0; i < m ; i++) {
            if (Math.abs (x - a) < 1 &&
                Math.abs (y - b) < 1) return true;
            s = s + n;
            if (s >=m) {
                s = s - m;
                a = a + d1x;
                b = b + d1y;
            } else {
                a = a + d2x;
                b = b + d2y;
            }
        }
        return false;
    }

//----------------------------------------------------------------------------
// Check if this wire is clicked at the specified coord.
//----------------------------------------------------------------------------
    public boolean CheckIfComponentClicked(int x, int y) {
        // Override ElectronicComponent function.
        // // System.out.println ("CheckIfWireClicked");
        if (x == x1 && y == y1) {
            ChangingWire = 1;
            return true;
        }
        if (x == x2 && y == y2) {
            ChangingWire = 2;
            return true;
        }
        // Check if user clicked somewhere in the middle of the line.
        if (CheckIfPointInWire(x1, y1, x2, y2, x, y)) {
            ChangingWire = 1 | 2;
            return true;
        }
        return false;
    }

//----------------------------------------------------------------------------
// Check if the wire is in the box.
//----------------------------------------------------------------------------
    public boolean CheckIfComponentInSelectBox(int tx1, int ty1, int tx2, int ty2) {
        Selected = false;
        ChangingWire = 0;
        // Wire-end 1 selected?
        if (tx1 <= x1 && x1 <= tx2 && ty1 <= y1 && y1 <= ty2) {
            ChangingWire |= 1;
            Selected = true;
        }
        // Wire-end 2 selected?
        if (tx1 <= x2 && x2 <= tx2 && ty1 <= y2 && y2 <= ty2) {
            ChangingWire |= 2;
            Selected = true;
        }
        return Selected;
    }

//----------------------------------------------------------------------------
// Save this wire.
//----------------------------------------------------------------------------
    public void Save (PrintStream myPrintStream) {
        myPrintStream.println ("describe component Wire");
        myPrintStream.println (" pos " + x1 + " " + y1);
        myPrintStream.println (" pos2 " + x2 + " " + y2);
        myPrintStream.println ("end describe");
    }

//----------------------------------------------------------------------------
// Try to place a junction here. A junction is performed by splitting
// the wire in two pieces.
//----------------------------------------------------------------------------
    public boolean TryPlaceJunction(Schematic ActSchematic, Pin PinGrid[][], int x, int y, int gs) {
        // System.out.println ("Wire TryPlaceJunction");
        if (x == x1 && y == y1) return false; // No need for junction here
        if (x == x2 && y == y2) return false; // No need for junction here
        if (CheckIfPointInWire(x1 * gs, y1 * gs, x2 * gs, y2 * gs, x * gs, y * gs)) {
            // System.out.println ("Exact hit wire!");
            // Split this wire in two parts
            ActSchematic.addComponent (new Wire (PinGrid, ActLevel, x, y, x1, y1));
            RemovePinsGrid(PinGrid);
            x1 = x; y1 = y;
            PlacePinsHere(PinGrid);
            return true;
        }

        return false;
    }
}