//****************************************************************************
// ---- version information ----
//
// AnalyzerFrame.java    v 1.00b2
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:           v 1.00b1 01-03-1996
//                            v 1.00b2 06-03-1996 Sun image update bug fixed
//                                     07-03-1996 Linux Image offset bug fixed
//                                                (AnalyzerPanel added)
// Released in public domain: v 1.00b1 01-03-1996
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

class AnalyzerFrame extends Frame {
    DigSim applet;
    AnalyzerPanel MyAnalyzerPanel = null;

//----------------------------------------------------------------------------
// The constructor of the Logic Analyzer Frame
//----------------------------------------------------------------------------
    public AnalyzerFrame(DigSim app) {
        super("Logic analyzer");
        applet = app;

        setLayout(new BorderLayout());
        add("Center", MyAnalyzerPanel = new AnalyzerPanel(app, this));

        resize(400, 250);
        show();
        resize(400, 250);
        repaint();
    }

//----------------------------------------------------------------------------
// Paint the Analyzer
//----------------------------------------------------------------------------
    public void paint (Graphics g) {
        if (MyAnalyzerPanel != null) {
            MyAnalyzerPanel.repaint();
        }
    }

//----------------------------------------------------------------------------
// Handle the events of the analyzer
//----------------------------------------------------------------------------
    public boolean handleEvent(Event ev) {
        if (ev.id == Event.WINDOW_DESTROY) {
            hide();
            applet.MyAnalyzerFrame = null;
            return true;
        }
        return super.handleEvent(ev);
    }

}