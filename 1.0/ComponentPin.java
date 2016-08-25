//****************************************************************************
// ---- version information ----
//
// ComponentPin.java     v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for a Component Pin
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
import java.lang.InterruptedException;
import java.lang.Integer;
import java.lang.Math;
import java.util.Vector;
import java.io.StreamTokenizer;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;

class ComponentPin {
    protected Vector ConnComps;   // Connected components to this pin
    protected int Level = 0;     // level of this pin.
    protected int OldLevel = 0;  // Old level of this pin.
    protected Point PinPos;       // Position of this pin relative to component
    protected Dimension PinDim;   // Dimension of this pin
    protected String Name;        // Name of this pin
    protected int Flags;          // Flags of this pin
    protected int TextXoffs;      // Text x offset
    protected int TextYoffs;      // Text y offset
    static final Color PinColor = Color.blue;
    static final Color PinTextColor = Color.white;
    static final int PIN_NORMAL = 0x0000;
    static final int PIN_NEGATIVE = 0x0001;
    static final int PIN_TEXT_INVISIBLE = 0x0002;
    static final int PIN_EDGETRIGGERED = 0x0004;
    static final int PIN_NOACTION = 0x0008;
    public Font PinFont;
    public FontMetrics PinFontMetrics;

//----------------------------------------------------------------------------
// The constructor of a new component pin
//----------------------------------------------------------------------------
    public ComponentPin (ComponentPin PinToCopy) {
        Name = PinToCopy.Name;
        PinPos = new Point (PinToCopy.PinPos.x, PinToCopy.PinPos.y);
        PinDim = new Dimension (PinToCopy.PinDim.width, PinToCopy.PinDim.height);
        Flags = PinToCopy.Flags;
        TextXoffs = PinToCopy.TextXoffs;
        TextYoffs = PinToCopy.TextYoffs;
        PinFont = new Font("TimesRoman",Font.PLAIN, 10);
    }

//----------------------------------------------------------------------------
// The constructor of a new component pin with the specified variables
//----------------------------------------------------------------------------
    public ComponentPin (String name, int x, int y, int w, int h, int txo, int tyo, int fl) {
        Name = name;
        TextXoffs = txo;
        TextYoffs = tyo;
        PinPos = new Point(x, y);
        PinDim = new Dimension (w, h);
        Flags = fl;
        PinFont = new Font("TimesRoman",Font.PLAIN, 10);
    }

//----------------------------------------------------------------------------
// Draw this component pin
//----------------------------------------------------------------------------
    public void draw (Graphics g, int x, int y, int gs) {
        double offset = 0;
        int TextWidth, TextHeight;
        g.setColor (PinColor);
        if ((Flags & PIN_NEGATIVE) > 0) {
            if (PinDim.width > 0) { // pin is left of component
                g.drawLine ((x + PinPos.x) * gs, (y + PinPos.y) * gs,
                            (x + PinPos.x + PinDim.width - 1) * gs,
                            (y + PinPos.y + PinDim.height) * gs);
                            g.setColor (ElectronicComponent.ComponentColor);
                g.drawOval ((int)((x + PinPos.x + PinDim.width - 0.875) * gs),
                            (int)((y + PinPos.y - 0.375) * gs), (int)(gs * 0.75), (int)(gs * 0.75));
            } else {                // pin is right of component
                g.drawLine ((x + PinPos.x) * gs, (y + PinPos.y) * gs,
                            (x + PinPos.x + PinDim.width + 1) * gs,
                            (y + PinPos.y + PinDim.height) * gs);
                            g.setColor (ElectronicComponent.ComponentColor);
                g.drawOval ((int)((x + PinPos.x + PinDim.width) * gs + 1),
                            (int)((y + PinPos.y - 0.375) * gs), (int)(gs * 0.75), (int)(gs * 0.75));
            }
        } else {
            g.drawLine ((x + PinPos.x) * gs, (y + PinPos.y) * gs,
                        (x + PinPos.x + PinDim.width) * gs,
                        (y + PinPos.y + PinDim.height) * gs);
        }

        if ((Flags & PIN_EDGETRIGGERED) > 0) {  // Draw >
            offset = 0.5;
            g.setColor (ElectronicComponent.ComponentColor);
            if (PinDim.width > 0) {     // pin is left of component
                g.drawLine ((int)((x + PinPos.x + PinDim.width) * gs), (int)((y + PinPos.y - 0.5) * gs),
                            (int)((x + PinPos.x + PinDim.width + 0.5) * gs), (int)((y + PinPos.y) * gs) );
                g.drawLine ((int)((x + PinPos.x + PinDim.width) * gs), (int)((y + PinPos.y + 0.5) * gs),
                            (int)((x + PinPos.x + PinDim.width + 0.5) * gs), (int)((y + PinPos.y) * gs) );
            } else {                    // pin is right of component
                g.drawLine ((int)((x + PinPos.x + PinDim.width) * gs), (int)((y + PinPos.y - 0.5) * gs),
                            (int)((x + PinPos.x + PinDim.width - 0.5) * gs), (int)((y + PinPos.y) * gs) );
                g.drawLine ((int)((x + PinPos.x + PinDim.width) * gs), (int)((y + PinPos.y + 0.5) * gs),
                            (int)((x + PinPos.x + PinDim.width - 0.5) * gs), (int)((y + PinPos.y) * gs) );

            }
        }

        if ((Flags & PIN_TEXT_INVISIBLE) == 0) {
            PinFontMetrics = g.getFontMetrics(PinFont);
            TextWidth = PinFontMetrics.stringWidth(Name);
            TextHeight = PinFontMetrics.getHeight();
            g.setColor (PinTextColor);
            g.setFont (PinFont);
            if (PinDim.width > 0) {     // pin is left of component
                g.drawString (Name, (int)((x + PinPos.x + PinDim.width + offset + TextXoffs) * gs + 2),
                                    (int)((y + PinPos.y + 0.5 + TextYoffs) * gs));
                if ((Flags & PIN_NEGATIVE) > 0) { // Draw _ above Name
                    g.drawLine ( (int)((x + PinPos.x + PinDim.width + offset + TextXoffs) * gs + 2),
                                 (int)((y + PinPos.y - 0.5 + TextYoffs) * gs ),
                                 (int)((x + PinPos.x + PinDim.width + offset + TextXoffs) * gs + TextWidth ),
                                 (int)((y + PinPos.y - 0.5 + TextYoffs) * gs) );
                }
            } else if (PinDim.width < 0) {  // pin is right of component
                g.drawString (Name, (int)((x + PinPos.x + PinDim.width - offset + TextXoffs) * gs - TextWidth - 1),
                                    (int)((y + PinPos.y + 0.5 + TextYoffs) * gs));
                if ((Flags & PIN_NEGATIVE) > 0) { // Draw _ above Name
                    g.drawLine ( (int)((x + PinPos.x + PinDim.width - offset + TextXoffs) * gs - TextWidth - 1),
                                 (int)((y + PinPos.y - 0.5 + TextYoffs) * gs ),
                                 (int)((x + PinPos.x + PinDim.width - offset + TextXoffs ) * gs ),
                                 (int)((y + PinPos.y - 0.5 + TextYoffs) * gs));
                }
            } else if (PinDim.height < 0) {  // pin is under component
                g.drawString (Name, (int)((x + PinPos.x + TextXoffs) * gs - TextWidth / 2  ),
                                    (int)((y + PinPos.y + PinDim.height + TextYoffs) * gs));
                if ((Flags & PIN_NEGATIVE) > 0) { // Draw _ above Name
                    g.drawLine ( (int)((x + PinPos.x + PinDim.width - offset - 1 + TextXoffs) * gs),
                                 (int)((y + PinPos.y - 0.5 + TextYoffs) * gs - 1),
                                 (int)((x + PinPos.x + PinDim.width - offset + TextXoffs) * gs ),
                                 (int)((y + PinPos.y - 0.5 + TextYoffs) * gs) - 1);
                }
            } else if (PinDim.height > 0) {  // pin is above component
                g.drawString (Name, (int)((x + PinPos.x + TextXoffs) * gs - TextWidth / 2  ),
                                    (int)((y + PinPos.y + PinDim.height + TextYoffs - 1) * gs));
                if ((Flags & PIN_NEGATIVE) > 0) { // Draw _ above Name
                    g.drawLine ( (int)((x + PinPos.x + PinDim.width - offset - 1 + TextXoffs) * gs),
                                 (int)((y + PinPos.y - 0.5 + TextYoffs) * gs - 1),
                                 (int)((x + PinPos.x + PinDim.width - offset + TextXoffs) * gs ),
                                 (int)((y + PinPos.y - 0.5 + TextYoffs) * gs) - 1);
                }
            }
        }
    }

//----------------------------------------------------------------------------
// Get the level of this pin
//----------------------------------------------------------------------------
    public int getLevel() {
        if ((Flags & PIN_NEGATIVE) == 0) {
            return Level;
        } else {
            if (Level == 0) {
                return 5;
            } else {
                return 0;
            }
        }
    }

//----------------------------------------------------------------------------
// Get the old level of this pin
//----------------------------------------------------------------------------
    public int getOldLevel() {
        if ((Flags & PIN_NEGATIVE) == 0) {
            return OldLevel;
        } else {
            if (OldLevel == 0) {
                return 5;
            } else {
                return 0;
            }
        }
    }

//----------------------------------------------------------------------------
// Set the level of this pin
//----------------------------------------------------------------------------
    public void setLevel(int l) {
        OldLevel = Level;
        Level = l;
    }

//----------------------------------------------------------------------------
// Get the name of this pin
//----------------------------------------------------------------------------
    public String getName() {
        return Name;
    }

//----------------------------------------------------------------------------
// Set the name of this pin
//----------------------------------------------------------------------------
    public void setName(String n) {
        Name = n;
    }
}