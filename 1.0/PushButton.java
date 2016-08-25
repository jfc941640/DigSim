//****************************************************************************
// ---- version information ----
//
// PushButton.java       v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for a Push button
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

class PushButton extends Switch {

//----------------------------------------------------------------------------
// The constructor of a push button
//----------------------------------------------------------------------------
    public PushButton (Pin PinGrid[][], int x, int y) {
        super(PinGrid, x, y);
        ComponentName = "push button";
        ClassName = "PushButton";
        HitBox.y--;
        HitBoxSize.height++;
    }

//----------------------------------------------------------------------------
// The constructor of a new push button, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public PushButton (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        PushButton NewComponent = new PushButton(this, xo, yo);
        return NewComponent;
    }

//----------------------------------------------------------------------------
// The user clicked on the button.
//----------------------------------------------------------------------------
    public boolean SimMouseDown() {
        SwitchClosed = true;
        return true;
    }

//----------------------------------------------------------------------------
// The user release the mouse button
//----------------------------------------------------------------------------
    public boolean SimMouseUp() {
        SwitchClosed = false;
        return true;
    }

//----------------------------------------------------------------------------
// Draw this push button
//----------------------------------------------------------------------------
    public void draw(Graphics g, int xp, int yp, int gs) {
        super.draw (g, xp, yp, gs);
        int x = Pos.x - xp;
        int y = Pos.y - yp;
        int Yadd = 0;
        // Draw component
        g.setColor (ComponentColor);
        if (SwitchClosed) {
            g.drawLine ((x + 5) * gs, (y) * gs, (x + 5) * gs, (int)((y + 3.5 ) * gs));
            g.drawLine ((x + 6) * gs, (y) * gs, (x + 6) * gs, (int)((y + 3.25) * gs));
            Yadd = 1;
        } else {
            g.drawLine ((x + 5) * gs, (y) * gs, (x + 5) * gs, (int)((y + 2.25) * gs));
            g.drawLine ((x + 6) * gs, (y) * gs, (x + 6) * gs, (int)((y + 2.5) * gs));
        }
        g.drawLine ((int)((x + 4.5) * gs), (y) * gs, (int)((x + 6.5) * gs), (y ) * gs);
        g.drawLine ((int)((x + 4.5) * gs), (int)((y + 1.5 + Yadd) * gs), (int)((x + 6.5) * gs), (int)((y + 1.5 + Yadd) * gs));
        g.drawLine ((int)((x + 4.5) * gs), (int)((y + 1.5 + Yadd) * gs), (int)((x + 5.5) * gs), (int)((y + .5 + Yadd) * gs));
        g.drawLine ((int)((x + 6.5) * gs), (int)((y + 1.5 + Yadd) * gs), (int)((x + 5.5) * gs), (int)((y + .5 + Yadd) * gs));
    }
}