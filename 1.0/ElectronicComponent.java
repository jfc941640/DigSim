//****************************************************************************
// ---- version information ----
//
// ElectronicComponent.java   v 1.00 b1
// Written by:                I. van Rienen / E-mail ivr@bart.nl
// URL:                       http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for creating, drawing,
// maintaining and simulating an abstract Electronic Component
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

class ElectronicComponent extends Object {
    protected Point Pos;
    protected Dimension Dim;
    protected String ComponentName;
    protected String ClassName;
    protected Point HitBox;
    protected Dimension HitBoxSize;
    protected InputPin IPin[];
    protected OutputPin OPin[];
    protected int Inputs, Outputs;
    public boolean Selected = false;
    protected static final Color ComponentColor = Color.yellow;
    static final int MAXLOOPS = 100;

//----------------------------------------------------------------------------
// The constructor of a new electronic component
//----------------------------------------------------------------------------
    public ElectronicComponent() {
    }

//----------------------------------------------------------------------------
// The constructor of a new electronic component, with the specified values
//----------------------------------------------------------------------------
    public ElectronicComponent(int x, int y, int w, int h,
                               int xh, int yh, int wh, int hh,
                               int i, int o ) {
        Pos = new Point (x, y);
        Dim = new Dimension (w, h);
        HitBox = new Point (xh, yh);
        HitBoxSize = new Dimension(wh, hh);
        Inputs = i; Outputs = o;
        IPin = new InputPin[i];
        OPin = new OutputPin[o];
     }

//----------------------------------------------------------------------------
// The constructor of a new component, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public ElectronicComponent (ElectronicComponent CompToCopy, int xo, int yo) {
        int ix;
        Pos = new Point (CompToCopy.Pos.x - xo, CompToCopy.Pos.y - yo);
        Dim = new Dimension (CompToCopy.Dim.width, CompToCopy.Dim.height);
        HitBox = new Point (CompToCopy.HitBox.x, CompToCopy.HitBox.y);
        HitBoxSize = new Dimension(CompToCopy.HitBoxSize.width, CompToCopy.HitBoxSize.height);
        Inputs = CompToCopy.Inputs; Outputs = CompToCopy.Outputs;
        ComponentName = CompToCopy.ComponentName;
        ClassName = CompToCopy.ClassName;
        IPin = new InputPin[Inputs];
        OPin = new OutputPin[Outputs];
        for (ix = 0; ix < Inputs; ix++) {
            IPin[ix] = new InputPin(CompToCopy.IPin[ix]);
        }
        for (ix = 0; ix < Outputs; ix++) {
            OPin[ix] = new OutputPin(CompToCopy.OPin[ix]);
        }
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        return this; // Should not occure, But return <this> to avoid problems
    }

//----------------------------------------------------------------------------
// get the name of this component
//----------------------------------------------------------------------------
    public String getName() {
        return ComponentName;
    }

//----------------------------------------------------------------------------
// Init this component before simulating
//----------------------------------------------------------------------------
    public void InitBeforeSimulate() {
        for (int i = 0; i < Inputs; i++) {
            IPin[i].InitBeforeSimulate();
        }

        for (int o = 0; o < Outputs; o++) {
            OPin[o].InitBeforeSimulate();
        }
    }

//----------------------------------------------------------------------------
// The component receives a potential at the input pin at coord xr, yr
//----------------------------------------------------------------------------
    public void ReceivePotential(int id, int v, int xr, int yr) {
        int i, o, ix;
//      System.out.println (ComponentName + " ReceivePotential id = " + id + " Voltage = " + v);

        // Check for short-circuit
        for (o = 0; o < Outputs; o++) {
            // System.out.println ("Check OPin" + o);
            if (xr == OPin[o].PinPos.x + Pos.x && yr == OPin[o].PinPos.y + Pos.y) {
                if (OPin[o].getLevel() != v) {
                    // System.out.println ("Short circuit!");
                    // System.out.println ("pin level = " + OPin[o].Level + " Received = " + v);
                    OPin[o].ShortCircuit = true;
                }
            }
        }

        for (i = 0; i < Inputs; i++) {
            // System.out.println ("Check IPin" + i);
            if (xr == IPin[i].PinPos.x + Pos.x && yr == IPin[i].PinPos.y + Pos.y) {
                if (id == IPin[i].ReceivedSimulationCycleID ) {
                    if (IPin[i].Level == v) {
                       // System.out.println ("*!*!*!*!* Input pin " + i + " ALREADY RECEIVED !*!*!*!*!*");
                        return;
                    } else {
                        IPin[i].LevelChanged++;
                        if (IPin[i].LevelChanged > MAXLOOPS) {
                            // System.out.println ("LOOPING");
                                IPin[1].Looping = true;
                            return;
                        }
                    }
                }
                // System.out.println (ComponentName + " setLevel " + v + " at Input pin #" + i);
                IPin[i].setLevel(v);
                IPin[i].ReceivedSimulationCycleID = id;
                if ( (IPin[i].Flags & ComponentPin.PIN_EDGETRIGGERED) == ComponentPin.PIN_EDGETRIGGERED) {
                    SimulateLogic();
                    InformConnectedComponentsOldLevel(id);
                } else if ( (IPin[i].Flags & ComponentPin.PIN_NOACTION) != ComponentPin.PIN_NOACTION) {
                    SimulateLogic();
                    InformConnectedComponents(id);
                }
                return;
            }
        }
    }

//----------------------------------------------------------------------------
// This code is simulating the component
//----------------------------------------------------------------------------
    public void SimulateLogic() {
    }

//----------------------------------------------------------------------------
// This code is called if the component must perform an action
// every simulation cycle.
//----------------------------------------------------------------------------
    public void Simulate(int id) {
    }

//----------------------------------------------------------------------------
// Inform all components connected to all outputs.
//----------------------------------------------------------------------------
    public void InformConnectedComponents(int id) {
        ElectronicComponent ConnectedComponent;
        int o, ix;

//      System.out.println ("InformConnectedComponents()");
        for (o = 0; o < Outputs; o++) {
            for (ix = 0; ix < OPin[o].ConnComps.size(); ix++) {
                ConnectedComponent = (ElectronicComponent) OPin[o].ConnComps.elementAt(ix);
                if (ConnectedComponent != this) {
//                  System.out.println ("-----");
//                  System.out.println (ComponentName +" Connected component to Output " + o + " found");
//                  System.out.println ("This component will receive: " + OPin[o].getLevel());
//                  System.out.println ("-----");
                    ConnectedComponent.ReceivePotential(id, OPin[o].getLevel(), OPin[o].PinPos.x + Pos.x, OPin[o].PinPos.y + Pos.y);
                }
            }
        }
    }

//----------------------------------------------------------------------------
// Inform all components connected to all outputs with the OLD level
//----------------------------------------------------------------------------
    public void InformConnectedComponentsOldLevel(int id) {
        ElectronicComponent ConnectedComponent;
        int o, ix;

//        System.out.println ("InformConnectedComponentsOldLevel()");
        for (o = 0; o < Outputs; o++) {
            for (ix = 0; ix < OPin[o].ConnComps.size(); ix++) {
                ConnectedComponent = (ElectronicComponent) OPin[o].ConnComps.elementAt(ix);
                if (ConnectedComponent != this) {

//                    System.out.println ("-----");
//                    System.out.println (ComponentName +" Connected component to Output " + o + " found");
//                    System.out.println ("This component will receive: " + OPin[o].getOldLevel());
//                    System.out.println ("-----");
                    ConnectedComponent.ReceivePotential(id, OPin[o].getOldLevel(), OPin[o].PinPos.x + Pos.x, OPin[o].PinPos.y + Pos.y);
                }
            }
        }
    }

//----------------------------------------------------------------------------
// Set up the component before simulating
//----------------------------------------------------------------------------
    public void SimulateSetUp(int x, int y, Vector ActComps) {
        int ix;
        // System.out.println ("SimulateSetUp pos x, y: " + x + ", " + y);
        // Check if this pin is an input
        for (ix = 0; ix < Inputs; ix++) {
            if (Pos.x + IPin[ix].PinPos.x == x &&
                Pos.y + IPin[ix].PinPos.y == y    ) {
                // System.out.println ("Equals IPin " + ix);
                IPin[ix].ConnComps = ActComps;
            }
        }
        // Check if this pin is an output
        for (ix = 0; ix < Outputs; ix++) {
            if (Pos.x + OPin[ix].PinPos.x == x &&
                Pos.y + OPin[ix].PinPos.y == y    ) {
                // System.out.println ("Equals OPin " + ix);
                OPin[ix].ConnComps = ActComps;
            }
        }
    }

//----------------------------------------------------------------------------
// Draw this component
//----------------------------------------------------------------------------
    public void draw(Graphics g, int xp, int yp, int gs) {
        if (Selected) {
            int x = Pos.x - xp;
            int y = Pos.y - yp;
            g.setColor (Color.white);
            g.drawRect ((int)((x + HitBox.x - 0.25) * gs), (int)((y + HitBox.y - 0.25) * gs), gs / 2, gs / 2);
            g.drawRect ((int)((x + HitBox.x + HitBoxSize.width - 0.25) * gs), (int)((y + HitBox.y - 0.25) * gs), gs / 2, gs / 2);
            g.drawRect ((int)((x + HitBox.x - 0.25) * gs), (int)((y + HitBox.y + HitBoxSize.height - 0.25) * gs), gs / 2, gs / 2);
            g.drawRect ((int)((x + HitBox.x + HitBoxSize.width - 0.25) * gs), (int)((y + HitBox.y + HitBoxSize.height - 0.25) * gs), gs / 2, gs / 2);
        }
    }

//----------------------------------------------------------------------------
// Draw the hit box, only used for debugging
//----------------------------------------------------------------------------
    public void DrawHitBox(Graphics g, int x, int y, int gs) {
        // Draw hitbox
        g.setColor (Color.gray);
        g.drawRect ((x + HitBox.x) * gs, (y + HitBox.y) * gs,
                    HitBoxSize.width * gs, HitBoxSize.height * gs) ;
        // Draw Dimension
        g.setColor (Color.pink);
        g.drawRect (x * gs, (y + 1)  * gs,
                    Dim.width * gs, Dim.height * gs);
    }

//----------------------------------------------------------------------------
// Adjust the position of this component
//----------------------------------------------------------------------------
    public boolean AdjustPosition (Pin PinGrid[][], int x, int y) {
        Pos.x += x; Pos.y += y;
        return true;
    }

//----------------------------------------------------------------------------
// Check and modify the location of this component if needed
//----------------------------------------------------------------------------
    public void CheckPosition() {
        if (Pos.x < 0) Pos.x = 0;
        if (Pos.y < 0) Pos.y = 0;
        if (Pos.x > DigSim.MaxXPoints - Dim.width) Pos.x = DigSim.MaxXPoints - Dim.width;
        if (Pos.y > DigSim.MaxYPoints - Dim.height) Pos.y = DigSim.MaxYPoints - Dim.height;
    }

//----------------------------------------------------------------------------
// Check if this component is in the select box
//----------------------------------------------------------------------------
    public boolean CheckIfComponentInSelectBox(int x1, int y1, int x2, int y2) {
        int cx1 = Pos.x + HitBox.x;
        int cy1 = Pos.y + HitBox.y;
        int cx2 = Pos.x + HitBox.x + HitBoxSize.width;
        int cy2 = Pos.y + HitBox.y + HitBoxSize.height;
        // System.out.println ("--------------");
        // System.out.println ("Check xy1 " + x1 + ", " + y1);
        // System.out.println ("Check xy2 " + x2 + ", " + y2);
        // System.out.println ("Check cxy1 " + cx1 + ", " + cy1);
        // System.out.println ("Check cxy2 " + cx2 + ", " + cy2);

        if (x1 <= cx1 && cx2 <= x2 && y1 <= cy1 && cy2 <= y2) {
            Selected = true;
        } else {
            Selected = false;
        }
        return Selected;
    }

//----------------------------------------------------------------------------
// Check if this component is clicked at position x,y
//----------------------------------------------------------------------------
    public boolean CheckIfComponentClicked(int x, int y) {
        if (Pos.x + HitBox.x <= x && x <= Pos.x + HitBox.x + HitBoxSize.width &&
            Pos.y + HitBox.y <= y && y <= Pos.y + HitBox.y + HitBoxSize.height  ) {
                return true;
        }
        return false;
    }

//----------------------------------------------------------------------------
// Register Pin ActPin
//----------------------------------------------------------------------------
    protected void RegisterPin (Pin ActPin) {
        ActPin.AddComponent(this);
    }

//----------------------------------------------------------------------------
// Register all Input- and Output pins
//----------------------------------------------------------------------------
    public void RegisterPins(Pin PinGrid[][], int x, int y) {
        int ix, xp, yp;
        // Register all inputs

        if (PinGrid == null) return;
        for (ix = 0; ix < Inputs; ix++) {
            xp = x + IPin[ix].PinPos.x;
            yp = y + IPin[ix].PinPos.y;
            // System.out.println ("Register input pin pos " + xp + ", " + yp);
            RegisterPin(PinGrid [xp] [yp] );
        }
        // Register all outputs
        for (ix = 0; ix < Outputs; ix++) {
            xp = x + OPin[ix].PinPos.x;
            yp = y + OPin[ix].PinPos.y;
            // System.out.println ("Register output pin pos " + xp + ", " + yp);
            RegisterPin(PinGrid [xp] [yp] );
        }
    }

//----------------------------------------------------------------------------
// remove ActPin
//----------------------------------------------------------------------------
    protected void RemovePin(Pin ActPin) {
        // System.out.println ("Remove pin ");
        ActPin.RemoveComponent(this);
    }

//----------------------------------------------------------------------------
// Remove all pins from the grid
//----------------------------------------------------------------------------
    public void RemovePinsGrid(Pin PinGrid[][]) {
        int ix, xp, yp;
        // Remove all registered inputs
        for (ix = 0; ix < Inputs; ix++) {
            xp = Pos.x + IPin[ix].PinPos.x;
            yp = Pos.y + IPin[ix].PinPos.y;
            // System.out.println ("Remove input pin pos " + xp + ", " + yp);
            RemovePin(PinGrid [xp] [yp] );
        }
        // Remove all registered outputs
        for (ix = 0; ix < Outputs; ix++) {
            xp = Pos.x + OPin[ix].PinPos.x;
            yp = Pos.y + OPin[ix].PinPos.y;
            // System.out.println ("Remove output pin pos " + xp + ", " + yp);
            RemovePin(PinGrid [xp] [yp] );
        }
    }

//----------------------------------------------------------------------------
// Place all pins in the PinGrid
//----------------------------------------------------------------------------
    public void PlacePinsHere(Pin PinGrid[][]) {
        RegisterPins(PinGrid, Pos.x, Pos.y);
    }

//----------------------------------------------------------------------------
// User clicked in this component while simulating
//----------------------------------------------------------------------------
    public boolean SimMouseDown() {
        return false;
    }

//----------------------------------------------------------------------------
// User released mouse button in this component while simulating
//----------------------------------------------------------------------------
    public boolean SimMouseUp() {
        return false;
    }

//----------------------------------------------------------------------------
// Draw all input pins
//----------------------------------------------------------------------------
    public void DrawInputPins(Graphics g, int x, int y, int gs) {
        for (int ix = 0; ix < Inputs; ix++) {
            IPin[ix].draw(g, x, y, gs);
        }
    }

//----------------------------------------------------------------------------
// Draw all output pins
//----------------------------------------------------------------------------
    public void DrawOutputPins(Graphics g, int x, int y, int gs) {
        for (int ix = 0; ix < Outputs; ix++) {
            OPin[ix].draw(g, x, y, gs);
        }
    }

//----------------------------------------------------------------------------
// Save this component
//----------------------------------------------------------------------------
    public void Save (PrintStream myPrintStream) {
        myPrintStream.println ("describe component " + ClassName);
        myPrintStream.println (" pos " + Pos.x + " " + Pos.y);
        myPrintStream.println ("end describe");
    }
}