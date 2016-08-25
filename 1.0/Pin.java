//****************************************************************************
// ---- version information ----
//
// Pin.java              v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class Pin, used in PinGrid and contains ElectronicComponent
// Used to make a PinGrid[][]
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

class Pin {
    Vector Components;

// ---------------------------------------------------------------------------
// The constructor of the Pin class.
// ---------------------------------------------------------------------------
    public Pin () {
        Components = new Vector();
    }

// ---------------------------------------------------------------------------
// add Components to this Pin
// ---------------------------------------------------------------------------
    public void AddComponent (ElectronicComponent ActComponent) {
        Components.addElement(ActComponent);
    }

// ---------------------------------------------------------------------------
// Remove components from this Pin
// ---------------------------------------------------------------------------
    public void RemoveComponent (ElectronicComponent ActComponent) {
        for (int ix = 0; ix < Components.size(); ix++) {
            if (Components.elementAt(ix) == ActComponent) {
                Components.removeElementAt(ix);
                // System.out.println ("Component found and removed from grid.");
                return;
            }
        }
    // System.out.println ("**** Error - can't remove component from grid");
    }

// ---------------------------------------------------------------------------
// Set all components up before simulating. this is done once
// before simulating and will set up the linked-lists between the components
// ---------------------------------------------------------------------------
    public void SimulateSetUp(int x, int y) {
        int ix;
        ElectronicComponent ActComponent;

        for (ix = 0; ix < Components.size(); ix++) {
            ActComponent = (ElectronicComponent)Components.elementAt(ix);
            ActComponent.SimulateSetUp(x, y, Components);
        }
    }

}