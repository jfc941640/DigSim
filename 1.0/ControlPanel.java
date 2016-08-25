//****************************************************************************
// ---- version information ----
//
// ControlPanel.java     v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for a creating and maintaining the
// Controlpanel which contaings the imagebuttons
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

class ControlPanel extends Canvas {

    Vector buttons;
    DigSim applet;
    Image ImageBuffer = null;
    Image CopyImage = null;
    Graphics cig;
    int ButtonOffset = 0;
    ImageButton PressedButton = null;
    boolean ImageButtonsDisabled = false;

//----------------------------------------------------------------------------
// The constructor of the control panel.
//----------------------------------------------------------------------------
    public ControlPanel(DigSim app) {
        applet = app;
        buttons = new Vector();
        LoadButtonsImage();
        buttons.addElement (new ImageButton ("New", 0));
        buttons.addElement (new ImageButton ("Open", 24));
        buttons.addElement (new ImageButton ("Save", 48));
        buttons.addElement (new ImageButton ("Cut", 80));
        buttons.addElement (new ImageButton ("Copy", 104));
        buttons.addElement (new ImageButton ("Paste", 128));
        buttons.addElement (new ImageButton ("Pointer", 160));
        buttons.addElement (new ImageButton ("Wire", 184));
        buttons.addElement (new ImageButton ("Junction", 208));
        buttons.addElement (new ImageButton ("Text", 232));
        buttons.addElement (new ImageButton ("Simulate", 264));
        buttons.addElement (new ImageButton ("Help", 296));
        if (ImageButtonsDisabled) {
            resize (size().width, 36);
        } else {
            resize (size().width, 4);
        }
     }

//----------------------------------------------------------------------------
// Calculate the preferred size of the control panel
//----------------------------------------------------------------------------
    public Dimension preferredSize() {
        // System.out.println ("ControlPanel preferredSize()");
        int w = applet.size().width;
        if (ImageButtonsDisabled) {
            return new Dimension(w, 4);
        }
        return new Dimension(w, 36);
    }

//----------------------------------------------------------------------------
// Get the imagebutton with the specified Name
//----------------------------------------------------------------------------
    public ImageButton getButton(String Name) {
        ImageButton TempButton;

        for (int ix = 0; ix < buttons.size(); ix++) {
            TempButton = (ImageButton) buttons.elementAt(ix);
            if (TempButton.getName().equals(Name)) {
                return TempButton;
            }
        }
        return null;
    }

//----------------------------------------------------------------------------
// Enable the button with the specified name
//----------------------------------------------------------------------------
    public void EnableButton(String Name) {
        ImageButton TempButton = getButton (Name);
        if (TempButton != null) TempButton.Enable();
        repaint();
    }

//----------------------------------------------------------------------------
// Disable the button with the specified name
//----------------------------------------------------------------------------
    public void DisableButton(String Name) {
        ImageButton TempButton = getButton (Name);
        if (TempButton != null) TempButton.Disable();
        repaint();
    }

//----------------------------------------------------------------------------
// Select the button with the specified name
//----------------------------------------------------------------------------
    public void SelectButton(String Name) {
        ImageButton TempButton = getButton (Name);
        if (TempButton != null) TempButton.Select();
        repaint();
    }

//----------------------------------------------------------------------------
// Unselect the button with the specified name
//----------------------------------------------------------------------------
    public void UnselectButton(String Name) {
        ImageButton TempButton = getButton (Name);
        if (TempButton != null) TempButton.Unselect();
        repaint();
    }

//----------------------------------------------------------------------------
// Enable all buttons in the control panel
//----------------------------------------------------------------------------
     public void EnableAllButtons() {
        EnableButton("Pointer");
        EnableButton("Wire");
        EnableButton("Junction");
        EnableButton("Text");
        EnableButton("Help");
        EnableButton("Simulate");
        EnableButton("New");
        if (applet.EnableFileOperations) {
            EnableButton("Open");
            EnableButton("Save");
        }
        SelectButton("Pointer");
    }

//----------------------------------------------------------------------------
// Draw all imagebuttons
//----------------------------------------------------------------------------
    public void DrawButtons(Graphics g) {
        ButtonOffset = (size().width - CopyImage.getWidth(this) ) / 2; // Center buttons
        ImageButton TempButton;

        for (int ix = 0; ix < buttons.size(); ix++) {
            TempButton = (ImageButton) buttons.elementAt(ix);
            TempButton.Draw(cig);
        }

        Graphics ibg = ImageBuffer.getGraphics();
        ibg.drawImage (CopyImage, 0, 0, this);
        g.drawImage(ImageBuffer, ButtonOffset, 4, this);
    }

//----------------------------------------------------------------------------
// The user pressed a mouse button
//----------------------------------------------------------------------------
    public boolean mouseDown(Event event, int x, int y) {
        ImageButton TempButton;
         x -= ButtonOffset;
         y -= 4;

        for (int ix = 0; ix < buttons.size(); ix++) {
            TempButton = (ImageButton) buttons.elementAt(ix);
            if (TempButton.CheckIfPressed (x, y)) {
                PressedButton = TempButton;
                repaint();
                return true;
            }
        }
        return true;
    }

//----------------------------------------------------------------------------
// The user released a mouse button
//----------------------------------------------------------------------------
    public boolean mouseUp(Event event, int x, int y) {
        if (PressedButton != null) {
            PressedButton.Select();
            repaint();
            ButtonPressed(PressedButton.getName());
            PressedButton = null;
        }
        return true;
    }

//----------------------------------------------------------------------------
// Draw a new off-screen image and copy it to the screen
//----------------------------------------------------------------------------
    public synchronized void update (Graphics g) {
        g.setColor (Color.gray);
        g.drawLine (0, 0, size().width, 0);
        if (!ImageButtonsDisabled) g.drawLine (0, 30, size().width, 30);
        g.setColor (Color.white);
        g.drawLine (0, 1, size().width, 1);
        if (!ImageButtonsDisabled) g.drawLine (0, 31, size().width, 31);

        if (CopyImage != null && !ImageButtonsDisabled) DrawButtons(g);
    }

//----------------------------------------------------------------------------
// Draw a new off-screen image and copy it to the screen
//----------------------------------------------------------------------------
    public synchronized void paint (Graphics g) {
        update (g);
    }

//----------------------------------------------------------------------------
// Load the master image containing all image buttons
//----------------------------------------------------------------------------
    public void LoadButtonsImage() {
        Image ButtonsImage;
        MediaTracker tracker = new MediaTracker(this);
        ButtonsImage = applet.getImage(applet.getDocumentBase(), "images/allbuttons.gif");
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
            String message = "Can't read images/allbuttons.gif ImageButtons will be disabled";
            String DlgButtons[] = { "OK" };
            SimpleDialog ErrorDialog = new SimpleDialog(null, "Error while reading imagebuttons", message, DlgButtons, 1, 0, 0, SimpleDialog.IMAGE_STOP);
            return;
        }
        ImageBuffer = applet.createImage (ButtonsImage.getWidth(this), 24);
        CopyImage = applet.createImage (ButtonsImage.getWidth(this), ButtonsImage.getHeight(this));
        cig = CopyImage.getGraphics();
        cig.drawImage (ButtonsImage, 0, 0, this);
   }

//----------------------------------------------------------------------------
// The user pressed a button.
//----------------------------------------------------------------------------
    public void ButtonPressed(String PressedButton) {
        if (ImageButtonsDisabled) return;

        if (PressedButton.equals("Pointer")) {
            applet.UserWantsPointer();
        } else if (PressedButton.equals("Wire")) {
            applet.UserWantsWireDrawing();
        } else if (PressedButton.equals("Junction")) {
            applet.UserWantsJunctionDrawing();
        } else if (PressedButton.equals("Text")) {
            applet.UserWantsTextDrawing();
        } else if (PressedButton.equals("Help")) {
            applet.UserWantsHelp();
        } else if (PressedButton.equals("Simulate")) {
            applet.UserWantsSimulate();
        } else if (PressedButton.equals("New")) {
            applet.UserWantsNewSchematic();
        } else if (PressedButton.equals("Open")) {
            applet.UserWantsOpenSchematic();
        } else if (PressedButton.equals("Save")) {
            applet.UserWantsSaveSchematic();
        } else if (PressedButton.equals("Copy")) {
            applet.UserWantsCopySchematic();
        } else if (PressedButton.equals("Paste")) {
            applet.UserWantsPasteSchematic();
        } else if (PressedButton.equals("Cut")) {
            applet.UserWantsCutSchematic();
        }
    }
}