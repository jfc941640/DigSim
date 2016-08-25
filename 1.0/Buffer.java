//****************************************************************************
// ---- version information ----
//
// Buffer.java           v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for a Buffer
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

class Buffer extends LogicPort {

//----------------------------------------------------------------------------
// The constructor of a buffer
//----------------------------------------------------------------------------
    public Buffer(Pin PinGrid[][], int x, int y) {
        super(1, x, y, ComponentPin.PIN_TEXT_INVISIBLE);
        ComponentName = "buffer";
        ClassName = "Buffer";
        IPin[0] = new InputPin("A", 1, 3, 2, 0, 0, 0, ComponentPin.PIN_TEXT_INVISIBLE);
        RegisterPins (PinGrid, x, y);
    }

//----------------------------------------------------------------------------
// The constructor of a new buffer, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public Buffer (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        Buffer NewComponent = new Buffer(this, xo, yo);
        return NewComponent;
    }

//----------------------------------------------------------------------------
// Method for drawing this buffer
//----------------------------------------------------------------------------
    public void draw(Graphics g, int xp, int yp, int gs) {
        super.draw (g, xp, yp, gs);
        int x = Pos.x - xp;
        int y = Pos.y - yp;

        // Draw HitBox
        // DrawHitBox(g, x, y, gs);

        // Draw the body
        g.setColor (ComponentColor);
        g.drawLine((x + 3) * gs, (y + 1) * gs, (x + 3) * gs, (y + 5) * gs);
        g.drawLine((x + 3) * gs, (y + 1) * gs, (x + 7) * gs, (y + 3) * gs);
        g.drawLine((x + 3) * gs, (y + 5) * gs, (x + 7) * gs, (y + 3) * gs);
    }

//----------------------------------------------------------------------------
// This method is simulating the component
//----------------------------------------------------------------------------
    public void SimulateLogic() {
        OPin[0].Level = IPin[0].getLevel();
    }
}