//****************************************************************************
// ---- version information ----
//
// ImageButton.java      v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for creating and maintaining an ImageButton
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
import java.awt.image.*;
import java.util.Vector;

class ImageButton {
    protected int Xpos;
    protected boolean selected;
    protected boolean pressed;
    protected boolean enabled;
    protected String Name;

//----------------------------------------------------------------------------
// Construct a new ImageButton with the specified name.
//----------------------------------------------------------------------------
    public ImageButton (String name, int x) {
        Xpos = x;
        selected = false;
        pressed = false;
        enabled = false;
        Name = name;
    }

//----------------------------------------------------------------------------
// return the name of this ImageButton.
//----------------------------------------------------------------------------
    public String getName () {
        return Name;
    }

//----------------------------------------------------------------------------
// Check if this button is pressed
//----------------------------------------------------------------------------
    public boolean CheckIfPressed (int x, int y) {
        if (y < 0 || y > 24) return false;
        x -= Xpos;
        if (x < 0 || x > 24) return false;
        if (enabled) {
            pressed = true;
            return true;
        }
        return false;
    }

//----------------------------------------------------------------------------
// Draw this button
//----------------------------------------------------------------------------
    public void Draw(Graphics g) {
        int Yoffset;

        if (!enabled) {
            Yoffset = 24;
        } else if (pressed) {
            Yoffset = 72;
        } else if (selected) {
            Yoffset = 96;
        } else {
            Yoffset = 48;
        }
        g.copyArea (Xpos, Yoffset, 24, 24, 0, -Yoffset);
    }

//----------------------------------------------------------------------------
// Select the button
//----------------------------------------------------------------------------
    public void Select() {
        if (enabled) {
            pressed = false;
            selected = true;
        }
    }

//----------------------------------------------------------------------------
// Unselect the button
//----------------------------------------------------------------------------
    public void Unselect() {
        pressed = false;
        selected = false;
    }

//----------------------------------------------------------------------------
// Enable the button
//----------------------------------------------------------------------------
public void Enable() {
        pressed = false;
        enabled = true;
    }

//----------------------------------------------------------------------------
// Disable the button
//----------------------------------------------------------------------------
    public void Disable() {
        pressed = false;
        enabled = false;
    }
}