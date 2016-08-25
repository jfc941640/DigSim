//****************************************************************************
// ---- version information ----
//
// StatusPanel.java      v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for creating and maintaining a
// Status panel.
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

class StatusPanel extends Panel {
    DigSim applet;
    public static final int STATUSPANEL_HEIGHT = 20;
    protected Font StatusFont;
    protected FontMetrics StatusFontMetrics;
    protected static final Color BackGroundColor = Color.green;
    protected static final Color TextColor = Color.black;
    protected String ActMessage = "Please wait";
    protected static final int STATUSLEDS_WIDTH = 20;
    boolean SimulationInitializeInProgress = false;
    boolean SimulationRunning = false;
    boolean StatusLedOn = false;

//----------------------------------------------------------------------------
// The constructor of the status panel
//----------------------------------------------------------------------------
    public StatusPanel(DigSim app) {
        applet = app;
        setLayout( new BorderLayout());
        StatusFont = new Font("TimesRoman",Font.PLAIN,12);
        setFont(StatusFont);
        StatusFontMetrics = getFontMetrics(StatusFont);
        repaint();
    }

//----------------------------------------------------------------------------
// Check if the simulation is running
//----------------------------------------------------------------------------
    public boolean SimulateRunning() {
        return (SimulationRunning | SimulationInitializeInProgress);
    }

//----------------------------------------------------------------------------
// Calculate the preferred size of this panel
//----------------------------------------------------------------------------
    public Dimension preferredSize() {
        // System.out.println ("StatusPanel preferredSize()");
        int w = applet.size().width;
        int h = STATUSPANEL_HEIGHT;
        return new Dimension(w, h);
    }

//----------------------------------------------------------------------------
// Draw the status LED
//----------------------------------------------------------------------------
    public void DrawStatusLED(Graphics g) {
        StatusLedOn = !StatusLedOn;
       int LedX = size().width - STATUSLEDS_WIDTH;
        if (SimulationRunning && StatusLedOn) {
            g.setColor (Color.green);
        } else if (SimulationInitializeInProgress && StatusLedOn) {
            g.setColor (Color.red);
        } else {
            g.setColor (Color.gray);
        }
        g.fillOval (LedX + 3, 3, 15, 15);
    }

//----------------------------------------------------------------------------
// Draw the status panel
//----------------------------------------------------------------------------
    public void paint(Graphics g) {
        g.setColor(BackGroundColor);
        g.fillRect(0, 0, size().width - STATUSLEDS_WIDTH, 20);
        g.setColor(TextColor);
        g.drawString(ActMessage, 2, StatusFontMetrics.getHeight() - 2);
        g.setColor(Color.black);
        g.fillRect (size().width - STATUSLEDS_WIDTH, 0, STATUSLEDS_WIDTH, 20);
        g.setColor(BackGroundColor);
        g.drawRect (size().width - STATUSLEDS_WIDTH, 0, STATUSLEDS_WIDTH, 20);
        DrawStatusLED(g);
    }

//----------------------------------------------------------------------------
// Display the message
//----------------------------------------------------------------------------
    public void StatusMessage(String msg) {
        ActMessage = msg;
        repaint();
    }

}