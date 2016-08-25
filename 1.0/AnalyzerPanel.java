//****************************************************************************
// ---- version information ----
//
// AnalyzerPanel.java    v 1.00b2
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:           v 1.00b2 06-03-1996
// Released in public domain: v 1.00b2 06-03-1996
//
// ---- Description ----
// Java class containing methods for the the logic analyzer.
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

class AnalyzerPanel extends Panel {
    static final int CHANNEL_HEIGHT = 50;
    static final int DISPLAY_OFFSET = 5;
    static final int CLOCK_WIDTH = 10;
    static final int LINE_OFFSET = 5;
    static final int BUTTON_X_OFFSET = 5;
    static final int BUTTON_Y_OFFSET = 22;
    static final int BUTTON_CLOCK_NONE = 48;
    static final int BUTTON_CLOCK_NONE_PRESSED = 24;
    static final int BUTTON_CLOCK_UP_PRESSED = 72;
    static final int BUTTON_CLOCK_UP_SELECTED = 96;
    static final int BUTTON_CLOCK_DN_PRESSED = 120;
    static final int BUTTON_CLOCK_DN_SELECTED = 144;
    static final Color TextColor = Color.black;
    static final Color BackGroundColor = Color.black;
    protected Scrollbar myVertical;
    DigSim applet;
    Image OffScreenImage = null;
    Image ImageBuffer = null;
    Image CopyImage = null;
    Graphics cig;
    int ButtonOffset = 0;
    Graphics og;
    Dimension ImageSize;
    protected Font AnalyzerFont;
    protected FontMetrics AnalyzerFontMetrics;
    Vector probes = null;
    boolean ImageButtonsDisabled = false;
    Probe PressedProbe = null;
    Probe DragProbe = null;
    int CurrentCol = 0;
    boolean ImageUpdated = false;
    Frame frame;

//----------------------------------------------------------------------------
// The constructor of the Logic Analyzer Panel
//----------------------------------------------------------------------------
    public AnalyzerPanel(DigSim app, Frame f) {
        applet = app;
        frame = f;

        setLayout( new BorderLayout());
        AnalyzerFont = new Font("TimesRoman",Font.PLAIN, 14);
        AnalyzerFontMetrics = getFontMetrics(AnalyzerFont);

        super.setLayout( new BorderLayout());

        LoadButtonsImage();
        myVertical = new Scrollbar(Scrollbar.VERTICAL);
        add( "East", myVertical);

        ImageSize = new Dimension (400, 250);
        CheckOffScreenImage();
        repaint();
    }

//----------------------------------------------------------------------------
// Adjust scrollbar to current display settings
//----------------------------------------------------------------------------
    public void AdjustScrollbar() {
        String OSName;
        if (probes == null) return;
        int ttl_height = CHANNEL_HEIGHT * probes.size();
        int vis_height = size().height;
        int max_offs = ttl_height - vis_height;
        if (max_offs < 0) max_offs = 0;
        // Determine OS because of scrollbar implementation differences
        // between Win95/NT and other operating systems.
        OSName = System.getProperty("os.name");
        if (OSName.equals ("Windows 95") ||
            OSName.equals ("Windows NT")) {
            myVertical.setValues (myVertical.getValue(), vis_height, 0, max_offs + vis_height);
        } else {
            myVertical.setValues (myVertical.getValue(), vis_height, 0, max_offs);
        }
    }

//----------------------------------------------------------------------------
// Check if there is an off-screen image, and check its size
//----------------------------------------------------------------------------
    public void CheckOffScreenImage() {
        if (OffScreenImage == null) {
            PrepareOffScreenImage();
            return;
        }
        if (size().width > ImageSize.width ||
            size().height > ImageSize.height) {
             ImageSize.width = size().width;
             ImageSize.height = size().height;
             PrepareOffScreenImage();
        }
    }

//----------------------------------------------------------------------------
// Create an off-screen image
//----------------------------------------------------------------------------
    public void PrepareOffScreenImage() {
        OffScreenImage = applet.createImage (ImageSize.width, ImageSize.height);
        og = OffScreenImage.getGraphics();
        og.setFont(AnalyzerFont);

        repaint();
    }

//----------------------------------------------------------------------------
// select an ImageButton
//----------------------------------------------------------------------------
    public void SelectButton(int ButtonType) {
        cig.copyArea (0, ButtonType, 24, 24, 0, -ButtonType);
    }

//----------------------------------------------------------------------------
// There is a button pressed
//----------------------------------------------------------------------------
    public void ButtonPressed (int b) {
        Probe ActProbe = (Probe)probes.elementAt (b);
        PressedProbe = ActProbe;
        if (ActProbe.clockup_probe) {
            ActProbe.clockup_probe = false;
            ActProbe.clockdn_probe = true;
        } else if (ActProbe.clockdn_probe) {
            ActProbe.clockup_probe = false;
            ActProbe.clockdn_probe = false;
        } else {
            ActProbe.clockup_probe = true;
            ActProbe.clockdn_probe = false;
        }
        for (int ix = 0; ix < probes.size(); ix++) {
            if (b != ix) {
                ActProbe = (Probe) probes.elementAt (ix);
                ActProbe.clockup_probe = false;
                ActProbe.clockdn_probe = false;
            }
        }
        repaint();
    }

//----------------------------------------------------------------------------
// User clicked a mousebutton
//----------------------------------------------------------------------------
    public boolean mouseDown(Event event, int x, int y) {
        Probe ActProbe;
        int ix, yo;
        PressedProbe = null;
        DragProbe = null;

        for (ix = 0; ix < probes.size(); ix++) {
            yo = ix * CHANNEL_HEIGHT + BUTTON_Y_OFFSET - myVertical.getValue();
            if ( x >= BUTTON_X_OFFSET && x <= BUTTON_X_OFFSET + 24 &&
                 y >= yo && y <= yo + 24) {
                ButtonPressed(ix);
            }
        }
        for (ix = 0; ix < probes.size(); ix++) {
            ActProbe = (Probe)probes.elementAt (ix);
            if ( x >= ActProbe.ChannelPos.x && y >= ActProbe.ChannelPos.y &&
                 x <= ActProbe.ChannelPos.x + ActProbe.ChannelDim.width &&
                 y <= ActProbe.ChannelPos.y + ActProbe.ChannelDim.height) {
                DragProbe = ActProbe;
                repaint();
                return true;
            }
        }

        return true;
    }

//----------------------------------------------------------------------------
// The user releases the mouse button
//----------------------------------------------------------------------------
    public boolean mouseUp(Event event, int x, int y) {
        if (DragProbe != null) {
            DragProbe = null;
            repaint();
        }
        if (PressedProbe != null) {
            PressedProbe = null;
            repaint();
        }
        return true;
    }

//----------------------------------------------------------------------------
// The user moves the mouse
//----------------------------------------------------------------------------
    public boolean mouseDrag(Event evt, int x, int y) {
        Probe ActProbe;
        int dpix, ix;

        if (DragProbe != null) {
            for (ix = 0; ix < probes.size(); ix++) {
                ActProbe = (Probe)probes.elementAt (ix);
                if ( x >= ActProbe.ChannelPos.x && y >= ActProbe.ChannelPos.y &&
                     x <= ActProbe.ChannelPos.x + ActProbe.ChannelDim.width &&
                     y <= ActProbe.ChannelPos.y + ActProbe.ChannelDim.height) {
                    if (ActProbe != DragProbe) {
                        // System.out.println ("Swap probes");
                        // Swap ActProbe and DragProbe.
                        applet.MySchematic.SwapComponents (ActProbe, DragProbe);
                        repaint();
                        return true;
                    }
                }
            }
        }
        return true;
    }

//----------------------------------------------------------------------------
// Draw the history levels of Probe ActProbe
//----------------------------------------------------------------------------
    public void DrawProbeHistory (Graphics g, Probe ActProbe) {
        g.setColor (Color.green);

        for (int ix = 0; ix < ActProbe.MAX_HISTORY; ix++) {
            if (ix > 0 && ActProbe.LevelHistory[ix - 1] != ActProbe.LevelHistory[ix]) {
                og.drawLine (ActProbe.ChannelPos.x + ix * CLOCK_WIDTH, ActProbe.ChannelPos.y + LINE_OFFSET,
                ActProbe.ChannelPos.x + ix * CLOCK_WIDTH, ActProbe.ChannelPos.y + ActProbe.ChannelDim.height - LINE_OFFSET);
            }
            if (ActProbe.LevelHistory[ix] == 5) {
                g.setColor (Color.green);
                g.drawLine (ActProbe.ChannelPos.x + ix * CLOCK_WIDTH, ActProbe.ChannelPos.y + LINE_OFFSET,
                            ActProbe.ChannelPos.x + (ix + 1 ) * CLOCK_WIDTH, ActProbe.ChannelPos.y + LINE_OFFSET);
            } else {
                g.setColor (Color.green);
                g.drawLine (ActProbe.ChannelPos.x + ix * CLOCK_WIDTH, ActProbe.ChannelPos.y + ActProbe.ChannelDim.height - LINE_OFFSET,
                            ActProbe.ChannelPos.x + (ix + 1) * CLOCK_WIDTH, ActProbe.ChannelPos.y + ActProbe.ChannelDim.height - LINE_OFFSET);
            }
        }
    }

//----------------------------------------------------------------------------
// There are no probes available, display a message
//----------------------------------------------------------------------------
    public void DrawNoProbes(Graphics g) {
        String message = "There are no probes to display.";
        int StringWidth = AnalyzerFontMetrics.stringWidth(message);
        int x = (size().width - StringWidth) / 2;
        g.setColor (Color.black);
        g.drawString (message, x, size().height / 2);
    }

//----------------------------------------------------------------------------
// Draw the empty probes
//----------------------------------------------------------------------------
    public void DrawEmptyProbes(Graphics g) {
        probes = applet.MySchematic.getProbes();
        Probe ActProbe;
        int x, yo, w;
        int MaxTextWidth = 30;
        int SliderVal = myVertical.getValue();

        g.setColor (Color.lightGray);
        g.fillRect (0, 0, ImageSize.width, ImageSize.height);

        if (probes.size() == 0) {
            DrawNoProbes(g);
            return;
        }
        // determine Max. text size
        for (int ix = 0; ix < probes.size(); ix++) {
            ActProbe = (Probe) probes.elementAt (ix);
            w = AnalyzerFontMetrics.stringWidth(ActProbe.IPin[0].getName());
            if (w > MaxTextWidth) MaxTextWidth = w;
        }

        for (int ix = 0; ix < probes.size(); ix++) {
            ActProbe = (Probe) probes.elementAt (ix);
            yo = ix * CHANNEL_HEIGHT - SliderVal;
            g.setColor (TextColor);
            g.drawString (ActProbe.IPin[0].getName(), 5, yo + 16);

            g.setColor (Color.gray);
            g.drawLine (0, yo + CHANNEL_HEIGHT - 2, size().width, yo + CHANNEL_HEIGHT - 2);
            g.setColor (Color.white);
            g.drawLine (0, yo + CHANNEL_HEIGHT - 1, size().width, yo + CHANNEL_HEIGHT - 1);
            ActProbe.ChannelPos.x  = DISPLAY_OFFSET + MaxTextWidth + 5;
            ActProbe.ChannelPos.y  = DISPLAY_OFFSET + yo;
            ActProbe.ChannelDim.width = size().width - ((DISPLAY_OFFSET) + MaxTextWidth + 20);
            ActProbe.ChannelDim.height = CHANNEL_HEIGHT - 2 * DISPLAY_OFFSET;
            g.setColor (BackGroundColor);
            g.fillRect (ActProbe.ChannelPos.x, ActProbe.ChannelPos.y + 1, ActProbe.ChannelDim.width, ActProbe.ChannelDim.height - 1);
            if (ActProbe.clockup_probe) {
                if (ActProbe == PressedProbe) {
                    SelectButton (BUTTON_CLOCK_UP_PRESSED);
                } else {
                    SelectButton (BUTTON_CLOCK_UP_SELECTED);
                }
            } else if (ActProbe.clockdn_probe) {
                if (ActProbe == PressedProbe) {
                    SelectButton (BUTTON_CLOCK_DN_PRESSED);
                } else {
                    SelectButton (BUTTON_CLOCK_DN_SELECTED);
                }

            } else {
                if (ActProbe == PressedProbe) {
                    SelectButton (BUTTON_CLOCK_NONE_PRESSED);
                } else {
                    SelectButton (BUTTON_CLOCK_NONE);
                }
            }
            DrawProbeHistory (g, ActProbe);
            Graphics ibg = ImageBuffer.getGraphics();
            ibg.drawImage (CopyImage, 0, 0, this);
            g.drawImage(ImageBuffer, BUTTON_X_OFFSET, BUTTON_Y_OFFSET + yo, this);
        }

        if (DragProbe != null) {
            g.setColor (Color.red);
            g.drawRect (DragProbe.ChannelPos.x, DragProbe.ChannelPos.y, DragProbe.ChannelDim.width, DragProbe.ChannelDim.height);
        }
    }

//----------------------------------------------------------------------------
// Copy the off-screen image to the screen
//----------------------------------------------------------------------------
    public synchronized void paint (Graphics g) {
    if (OffScreenImage == null) return;
        AdjustScrollbar();
        if (probes != null && size().height >= CHANNEL_HEIGHT * probes.size()) {
            update (g);
            return;
       }
       if (!ImageUpdated) {
//          System.out.println ("update()");
            update(g);
            return;
        }
        g.drawImage (OffScreenImage, 0, 0, this);
    }

//----------------------------------------------------------------------------
// Prepare the off-screen image and copy it to the screen.
//----------------------------------------------------------------------------
    public synchronized void update (Graphics g) {
        AdjustScrollbar();
        CheckOffScreenImage();
        if (OffScreenImage == null) return;
        DrawEmptyProbes (og);
        g.drawImage (OffScreenImage, 0, 0, this);
        ImageUpdated = true;
    }

//----------------------------------------------------------------------------
// Handle the events of the analyzer
//----------------------------------------------------------------------------
    public boolean handleEvent(Event ev) {
        if (ev.target instanceof Scrollbar) {
            repaint();
            return true;
        }
        return super.handleEvent(ev);
    }

//----------------------------------------------------------------------------
// Handle the actions of the analyzer
//----------------------------------------------------------------------------
    public boolean action(Event ev, Object arg) {
        if (ev.target instanceof Button) {
        }
        return false;
    }

//----------------------------------------------------------------------------
// Update the new levels of Probe ActProbe
//----------------------------------------------------------------------------
    public void update(Probe ActProbe) {
        int x = CurrentCol * CLOCK_WIDTH;
        if ( x >= ActProbe.ChannelDim.width) {
            if (ActProbe.clockup_probe &&
                ActProbe.OldLevel == 0 && ActProbe.IPin[0].getLevel() == 5) {
                    CurrentCol = 0;     // Clock trigger
            } else if (ActProbe.clockdn_probe &&
                ActProbe.OldLevel == 5 && ActProbe.IPin[0].getLevel() == 0) {
                    CurrentCol = 0;     // Clock trigger
            }
        }
    }

//----------------------------------------------------------------------------
// Draw a new Level.
//----------------------------------------------------------------------------
    public void drawLevel (Probe ActProbe) {
        int x = CurrentCol * CLOCK_WIDTH;
        if ( x >= ActProbe.ChannelDim.width) return;
        if (CurrentCol == 0) {
            og.setColor (BackGroundColor);
            og.fillRect (ActProbe.ChannelPos.x + x, ActProbe.ChannelPos.y + 1,
                         CLOCK_WIDTH + 1, ActProbe.ChannelDim.height - 1);
        }
        og.setColor (BackGroundColor);
        og.fillRect (ActProbe.ChannelPos.x + x + 1, ActProbe.ChannelPos.y + 1,
                     CLOCK_WIDTH, ActProbe.ChannelDim.height - 1);

        if (CurrentCol > 0 && ActProbe.IPin[0].getLevel() != ActProbe.OldLevel) {
             og.setColor (Color.green);
             og.drawLine (ActProbe.ChannelPos.x + x, ActProbe.ChannelPos.y + LINE_OFFSET,
                          ActProbe.ChannelPos.x + x, ActProbe.ChannelPos.y + ActProbe.ChannelDim.height - LINE_OFFSET);
        }
        if (CurrentCol < ActProbe.MAX_HISTORY) {
            ActProbe.LevelHistory[CurrentCol] = ActProbe.IPin[0].getLevel();
        }
        if (ActProbe.IPin[0].getLevel() == 5) {
            og.setColor (Color.green);
            og.drawLine (ActProbe.ChannelPos.x + x, ActProbe.ChannelPos.y + LINE_OFFSET,
                         ActProbe.ChannelPos.x + x + CLOCK_WIDTH, ActProbe.ChannelPos.y + LINE_OFFSET);
        } else {
             og.setColor (Color.green);
             og.drawLine (ActProbe.ChannelPos.x + x, ActProbe.ChannelPos.y + ActProbe.ChannelDim.height - LINE_OFFSET,
                          ActProbe.ChannelPos.x + x + CLOCK_WIDTH, ActProbe.ChannelPos.y + ActProbe.ChannelDim.height - LINE_OFFSET);
        }

        paint (getGraphics());
    }

//----------------------------------------------------------------------------
// Update the new levels of all probes
//----------------------------------------------------------------------------
    public void update() {
        Probe ActProbe;
        int ix;

        if (probes == null) return;
        for (ix = 0; ix < probes.size(); ix++) {
            ActProbe = (Probe) probes.elementAt (ix);
            update (ActProbe);
        }
        for (ix = 0; ix < probes.size(); ix++) {
            ActProbe = (Probe) probes.elementAt (ix);
            drawLevel (ActProbe);
            ActProbe.OldLevel = ActProbe.IPin[0].getLevel();
        }
        paint(getGraphics());
        CurrentCol++;
    }

//----------------------------------------------------------------------------
// Load the image with the buttons
//----------------------------------------------------------------------------
    public void LoadButtonsImage() {
        Image ButtonsImage;
        MediaTracker tracker = new MediaTracker(this);
        ButtonsImage = applet.getImage(applet.getDocumentBase(), "images/all_analyzer.gif");
        tracker.addImage(ButtonsImage, 0);

        try {
            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
            tracker.waitForAll();
        } catch(Exception e) {
            String message = e.toString();
            String DlgButtons[] = { "OK" };
            SimpleDialog ExceptionDialog = new SimpleDialog(null, "Reading imagebuttons", message, DlgButtons, 1, 0, 0, SimpleDialog.IMAGE_STOP);
            return;
        }
        if (tracker.isErrorAny()) {
            ImageButtonsDisabled = true;
            String message = "Can't read images/all_analyzer.gif ImageButtons will be disabled";
            String DlgButtons[] = { "OK" };
            SimpleDialog ErrorDialog = new SimpleDialog(null, "Error while reading imagebuttons", message, DlgButtons, 1, 0, 0, SimpleDialog.IMAGE_STOP);
            return;
        }
        ImageBuffer = applet.createImage (ButtonsImage.getWidth(this), 24);
        CopyImage = applet.createImage (ButtonsImage.getWidth(this), ButtonsImage.getHeight(this));
        cig = CopyImage.getGraphics();
        cig.drawImage (ButtonsImage, 0, 0, this);
    }
}