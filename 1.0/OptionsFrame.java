//****************************************************************************
// ---- version information ----
//
// OptionsFrame.java     v 1.00 b2
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:           v 1.00b1 01-03-1996
//                            v 1.00b2 07-03-1996 Frame dimension adjusted
// Released in public domain: v 1.00b1 01-03-1996
//
// ---- Description ----
// Java class containing methods for the the options frame.
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

class OptionsFrame extends Frame {
    DigSim applet;
    Scrollbar slider;

    static final int MIN_SPEED = 0;
    static final int MAX_SPEED = 1000;
    static final int PAGE_SIZE = (MAX_SPEED - MIN_SPEED) / 10;
    Checkbox ShortCircuitCheckbox;
    Checkbox LoopCheckbox;
    Checkbox AnalyzerCheckbox;

//----------------------------------------------------------------------------
// The constructor of the Options Frame
//----------------------------------------------------------------------------
    public OptionsFrame(DigSim app) {
        super("DigSim Options");
        applet = app;
        setLayout (new GridLayout(5,1,5,5));

        int SliderVal = 1010 - applet.SimulationSpeed;

        slider = new Scrollbar(Scrollbar.HORIZONTAL, SliderVal, PAGE_SIZE, MIN_SPEED, MAX_SPEED);
        Panel SliderPanel = new Panel();
        SliderPanel.setLayout (new BorderLayout());
        SliderPanel.add ("Center", new Label ("Simulation speed", Label.CENTER));
        SliderPanel.add ("West", new Label ("min", Label.LEFT));
        SliderPanel.add ("East", new Label ("max", Label.RIGHT));
        SliderPanel.add ("South", slider);
        add (SliderPanel);

        Panel CheckBoxPanel1 = new Panel();
        CheckBoxPanel1.setLayout (new BorderLayout());
        CheckBoxPanel1.add ("North", ShortCircuitCheckbox = new Checkbox("Stop simulate at short-circuit"));
        if (applet.StopAtShortCircuit) ShortCircuitCheckbox.setState(true);
        add (CheckBoxPanel1);

        Panel CheckBoxPanel2 = new Panel();
        CheckBoxPanel2.setLayout (new BorderLayout());
        CheckBoxPanel2.add ("North", LoopCheckbox = new Checkbox("Stop simulate at loop"));
        if (applet.StopAtLoop) LoopCheckbox.setState(true);
        add (CheckBoxPanel2);

        Panel CheckBoxPanel3 = new Panel();
        CheckBoxPanel3.setLayout (new BorderLayout());
        CheckBoxPanel3.add ("North", AnalyzerCheckbox = new Checkbox("Auto pop-up analyzer"));
        if (applet.AnalyzerAutoPopUp) AnalyzerCheckbox.setState(true);
        add (CheckBoxPanel3);

        Panel ButtonPanel = new Panel();
        ButtonPanel.setLayout (new FlowLayout());
        ButtonPanel.add (new Button ("OK"));
        ButtonPanel.add (new Button ("Default"));
        ButtonPanel.add (new Button ("Cancel"));
        add (ButtonPanel);

        resize(275, 225);
        show();
        resize(275, 225);
    }

//----------------------------------------------------------------------------
// Handle all events in this option frame.
//----------------------------------------------------------------------------
    public boolean handleEvent(Event ev) {
        if (ev.id == Event.WINDOW_DESTROY) {
            hide();
            applet.MyOptionsFrame = null;
            return true;
        }
        return super.handleEvent(ev);
    }

//----------------------------------------------------------------------------
// Set all sliders and checkboxes to the default values
//----------------------------------------------------------------------------
    public void SetDefaultValues() {
        slider.setValue(1000);
        ShortCircuitCheckbox.setState(true);
        LoopCheckbox.setState(true);
        AnalyzerCheckbox.setState(true);
        repaint();
    }

//----------------------------------------------------------------------------
// Set new values
//----------------------------------------------------------------------------
    public void SetNewValues() {
        int NewSpeed = 1010 - slider.getValue();
        applet.SimulationSpeed = NewSpeed;
        applet.StopAtShortCircuit = ShortCircuitCheckbox.getState();
        applet.StopAtLoop = LoopCheckbox.getState();
        applet.AnalyzerAutoPopUp = AnalyzerCheckbox.getState();
    }

//----------------------------------------------------------------------------
// Handle all actions in this Frame
//----------------------------------------------------------------------------
    public boolean action(Event ev, Object arg) {
        String FileName;
        int s;

        if (ev.target instanceof Button) {
            String label = (String)arg;
            if (arg.equals ("Default")) {
                SetDefaultValues();
                return true;
            } else if (arg.equals ("Cancel")) {
                hide();
                applet.MyOptionsFrame = null;
                return true;
            } else if (arg.equals ("OK")) {
                SetNewValues();
                hide();
                applet.MyOptionsFrame = null;
                return true;
            }
        }
        return false;
    }
}