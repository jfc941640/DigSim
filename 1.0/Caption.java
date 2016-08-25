//****************************************************************************
// ---- version information ----
//
// Caption.java          v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for creating a caption in the schematic
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
import java.io.PrintStream;

class Caption extends ElectronicComponent {
    static final Color CaptionColor = Color.white;
    public String Text;
    public Font CaptionFont;

//----------------------------------------------------------------------------
// The constructor of a new Caption.
//----------------------------------------------------------------------------
    public Caption (int x, int y, String text) {
        super (x, y, 1, 1, 0, 1, 1, 1, 0, 0);      // x,y,w,h HitBox x,y,w,h,I,O
        Text = text;
        ComponentName = "Text";
        ClassName = "Caption";
        CaptionFont = new Font("TimesRoman",Font.PLAIN, 16);
    }

//----------------------------------------------------------------------------
// The constructor of a new Caption, which is a copy of CompToCopy
//----------------------------------------------------------------------------
    public Caption (ElectronicComponent CompToCopy, int xo, int yo) {
        super (CompToCopy, xo, yo);
    }

//----------------------------------------------------------------------------
// Method for copying this component.
//----------------------------------------------------------------------------
    public ElectronicComponent Copy(int xo, int yo) {
        Caption NewComponent = new Caption(this, xo, yo);
        NewComponent.Text = Text;
        NewComponent.CaptionFont = CaptionFont;
        return NewComponent;
    }

//----------------------------------------------------------------------------
// Draw the caption.
//----------------------------------------------------------------------------
    public void draw(Graphics g, int xp, int yp, int gs) {
        FontMetrics CaptionFontMetrics;
        int x = Pos.x - xp;
        int y = Pos.y - yp;

        g.setFont (CaptionFont);
        CaptionFontMetrics = g.getFontMetrics();
        int FontHeight = CaptionFontMetrics.getHeight();
        int FontWidth = CaptionFontMetrics.stringWidth(Text);
        HitBoxSize.width = Dim.width = FontWidth / gs + 1;
        HitBoxSize.height = Dim.height = FontHeight / gs;

        super.draw (g, xp, yp, gs);

        // Draw HitBox
        // DrawHitBox(g, x, y, gs);

        g.setColor (CaptionColor);
        g.drawString (Text, x * gs, y * gs + FontHeight);
    }

//----------------------------------------------------------------------------
// Save this caption
//----------------------------------------------------------------------------
   public void Save (PrintStream myPrintStream) {
        myPrintStream.println ("describe component Caption");
        myPrintStream.println (" pos " + Pos.x + " " + Pos.y);
        myPrintStream.println (" Text " + Text);
        myPrintStream.println ("end describe");
    }


}