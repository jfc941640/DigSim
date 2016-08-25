//****************************************************************************
// ---- version information ----
//
// SchematicPanel.java   v 1.00 b3
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:           v 1.00b1 01-03-1996
//                            v 1.00b3 26-03-1996 Scrollbar bug fixed on other platforms than Win95/NT
// Released in public domain: v 1.00b1 01-03-1996
// Released in public domain:
//
// ---- Description ----
// Java class containing methods for creating and handling the main
// schematic panel.
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

class SchematicPanel extends Panel {
    protected Scrollbar myHorizontal;
    protected Scrollbar myVertical;
    DigSim applet;
    static final int GridStep = 8;
    static final int hgs = GridStep / 2;
    Wire NewWire = null;
    ElectronicComponent ComponentPressed = null;
    ElectronicComponent PrevSelectedComponent = null;
    int OldMouseX = 0, OldMouseY = 0;
    boolean WireDrawing = false;
    boolean JunctionDrawing = false;
    protected Font SchematicFont;
    protected FontMetrics SchematicFontMetrics;
    static final Color BorderColor = Color.red;
    int GridXOffset = 0, GridYOffset = 0;
    boolean SelectBox = false;
    int SelectBoxX1, SelectBoxY1;
    int SelectBoxX2, SelectBoxY2;
    Schematic SelectSchematic;
    Schematic CopySchematic = null;
    static final Color JunctionColor = Color.white;
    boolean AutoJunction = true;

//----------------------------------------------------------------------------
// The constructor of a new Schematic Panel.
//----------------------------------------------------------------------------
    public SchematicPanel(DigSim app) {
        applet = app;
        SelectSchematic = new Schematic();
        setLayout( new BorderLayout());
        SchematicFont = new Font("TimesRoman",Font.PLAIN, 20);
        SchematicFontMetrics = getFontMetrics(SchematicFont);
        setFont(SchematicFont);
        super.setLayout( new BorderLayout());
        myVertical = new Scrollbar(Scrollbar.VERTICAL);
        add( "East", myVertical);
        myHorizontal = new Scrollbar(Scrollbar.HORIZONTAL);
        add( "South", myHorizontal);
        repaint();
    }

//----------------------------------------------------------------------------
// handle the Events in this Panel.
//----------------------------------------------------------------------------
    public boolean handleEvent(Event e) {
        if (e.target instanceof Scrollbar) {
//          System.out.println ("Scrollbar hit!");
            repaint();
            return true;
        }
        super.handleEvent(e);
        return false;
    }

//----------------------------------------------------------------------------
// Cut the selected components from the schematic.
//----------------------------------------------------------------------------
    public void Cut() {
        applet.MySchematic.RemoveSameElements(applet.PinGrid, SelectSchematic);
        CopySchematic = new Schematic(SelectSchematic);
        SelectSchematic.RemoveAllComponents();
        applet.updateAnalyzer();
        repaint();
    }

//----------------------------------------------------------------------------
// Copy the selected components to a buffer
//----------------------------------------------------------------------------
    public void Copy() {
        CopySchematic = new Schematic(SelectSchematic);
    }

//----------------------------------------------------------------------------
// Paste the components in the buffer to the schematic.
//----------------------------------------------------------------------------
    public void Paste() {
        if (CopySchematic != null) {    // Should not be null
            SelectSchematic.RemoveAllComponents();  // remove old selected components
            SelectSchematic = applet.MySchematic.PasteSchematic(applet.PinGrid, CopySchematic, GridXOffset, GridYOffset);
            applet.frame.CutMenuItem.enable();
            applet.frame.CopyMenuItem.enable();
            if (applet.EnableFileOperations) {
                applet.frame.CopyDiskMenuItem.enable();
            }
            applet.MyControlPanel.EnableButton ("Cut");
            applet.MyControlPanel.EnableButton ("Copy");
            applet.updateAnalyzer();
            repaint();
        }
    }

//----------------------------------------------------------------------------
// Copy the selected components to disk.
//----------------------------------------------------------------------------
    public void CopyTo() {
        applet.frame.DoFileSaveDialog(SelectSchematic, true, "Copy to");
    }

//----------------------------------------------------------------------------
// Load components from disk and put it in the copy buffer.
//----------------------------------------------------------------------------
    public void PasteFrom() {
        Schematic LoadSchematic;

        LoadSchematic = applet.frame.DoFileOpenDialog(null, "Paste from");
        if (LoadSchematic != null) {
            SelectSchematic.RemoveAllComponents();  // remove old selected components
            SelectSchematic = applet.MySchematic.PasteSchematic(applet.PinGrid, LoadSchematic, GridXOffset, GridYOffset);

            applet.frame.CutMenuItem.enable();
            applet.frame.CopyMenuItem.enable();
            if (applet.EnableFileOperations) {
                applet.frame.CopyDiskMenuItem.enable();
            }
            applet.MyControlPanel.EnableButton ("Cut");
            applet.MyControlPanel.EnableButton ("Copy");
            applet.updateAnalyzer();
            repaint();
        }
    }

//----------------------------------------------------------------------------
// Select all components in the schematic
//----------------------------------------------------------------------------
    public void SelectAll() {
        SelectSchematic = new Schematic(applet.MySchematic);
        if (SelectSchematic.size() > 0) {
            SelectSchematic.SetAllComponentsSelected();
            applet.frame.CutMenuItem.enable();
            applet.frame.CopyMenuItem.enable();
            applet.frame.PasteMenuItem.enable();
            if (applet.EnableFileOperations) {
                applet.frame.CopyDiskMenuItem.enable();
            }
            applet.MyControlPanel.EnableButton ("Cut");
            applet.MyControlPanel.EnableButton ("Copy");
            applet.MyControlPanel.EnableButton ("Paste");
        } else {
            applet.frame.CutMenuItem.disable();
            applet.frame.CopyMenuItem.disable();
            applet.frame.PasteMenuItem.disable();
            if (applet.EnableFileOperations) {
                applet.frame.CopyDiskMenuItem.disable();
            }
            applet.MyControlPanel.DisableButton ("Cut");
            applet.MyControlPanel.DisableButton ("Copy");
            applet.MyControlPanel.DisableButton ("Paste");
        }
        repaint();
    }

//----------------------------------------------------------------------------
// Check the dimensions of this Panel and adjust scrollbars if needed
//----------------------------------------------------------------------------
    public void CheckDimensions() {
        int width = size().width;
        int height = size().height;

//      System.out.println ("SchematicPanel CheckDimensions()");
        int VisibleXGridPoints = (width / applet.GridStep) - 1;
        int VisibleYGridPoints = (height / applet.GridStep) - 1;
        int XOverlap = VisibleXGridPoints + GridXOffset - applet.MaxXPoints - 1;
        int YOverlap = VisibleYGridPoints + GridYOffset - applet.MaxYPoints - 1;
//      System.out.println ("XOverlap: " + XOverlap );
//      System.out.println ("YOverlap: " + YOverlap );
        if (XOverlap > 0) {
            GridXOffset -= XOverlap;
            if (GridXOffset < 0) GridXOffset = 0; // should not occure
            myHorizontal.setValue(GridXOffset);
        }
        if (YOverlap > 0) {
            GridYOffset -= YOverlap;
            if (GridYOffset < 0) GridYOffset = 0; // should not occure
            myVertical.setValue(GridYOffset);
        }
    }

//----------------------------------------------------------------------------
// Set new values of the Scrollbars according to the visible area
//----------------------------------------------------------------------------
    public void AdjustScrollbars() {
        String OSName;
        GridXOffset = myHorizontal.getValue();
        GridYOffset = myVertical.getValue();
//      System.out.println ("Scrollbar XY offset = " + GridXOffset + ", " + GridYOffset);
        int VisibleXGridPoints = (size().width / applet.GridStep) - 1;
        int VisibleYGridPoints = (size().height / applet.GridStep) - 1;
//      System.out.println ("Visible XY in this panel = " + VisibleXGridPoints + ", " + VisibleYGridPoints);

        // Determine OS because of scrollbar implementation differences
        // between Win95/NT and other operating systems.
        OSName = System.getProperty("os.name");
        if (OSName.equals ("Windows 95") ||
            OSName.equals ("Windows NT")) {
            myVertical.setValues(GridYOffset, VisibleYGridPoints, 0, applet.MaxYPoints + 1);
            myHorizontal.setValues(GridXOffset, VisibleXGridPoints, 0, applet.MaxXPoints + 1);
        } else {
            myVertical.setValues(GridYOffset, VisibleYGridPoints, 0, applet.MaxYPoints + 1 - VisibleYGridPoints);
            myHorizontal.setValues(GridXOffset, VisibleXGridPoints, 0, applet.MaxXPoints + 1 - VisibleXGridPoints);
        }
    }

//----------------------------------------------------------------------------
// Draw a border around the schematic.
//----------------------------------------------------------------------------
    public void DrawBorder (Graphics g) {
        g.setColor (BorderColor);
        g.drawRect ( (-GridXOffset) * GridStep + GridStep / 2, (-GridYOffset) * GridStep + GridStep / 2,
                (applet.MaxXPoints -1 ) * GridStep , (applet.MaxYPoints -1 ) * GridStep );
        // Delete the grid points behind right- and under bottom border
        g.setColor (applet.BackGroundColor);
        // draw line behind right border
        g.drawLine ((applet.MaxXPoints - GridXOffset) * GridStep, 0,
                    (applet.MaxXPoints - GridXOffset) * GridStep,
                    (applet.MaxYPoints - GridYOffset) * GridStep);
        // draw line under bottom border
        g.drawLine (0,(applet.MaxYPoints - GridYOffset) * GridStep,
                    (applet.MaxXPoints - GridXOffset) * GridStep,
                    (applet.MaxYPoints - GridYOffset) * GridStep);

    }

//----------------------------------------------------------------------------
// Display a message when Images are not yet ready.
//----------------------------------------------------------------------------
    public void TellNotReady (Graphics g) {
        String msg = "Please wait, I'm preparing the image";
        g.setColor (Color.green);
        g.fillRect (0, 0, size().width, size().height);
        g.setColor (Color.black);
        g.drawString(msg, size().width / 2 - SchematicFontMetrics.stringWidth(msg) / 2,
                          size().height / 2);
    }

//----------------------------------------------------------------------------
// Paint the ImageBuffer
//----------------------------------------------------------------------------
    public synchronized void paint(Graphics g) {
        CheckDimensions();
        if (applet.OffScreenWidth < size().width ||
            applet.OffScreenHeight < size().height) {
                update(g);
        }
        if (applet.ImagesReady())  {
            g.drawImage (applet.ImageBuffer, 0, 0, this);
        } else {
            // System.out.println ("update Not ready");
            TellNotReady (g);
        }
    }


//----------------------------------------------------------------------------
// Redraw the whole schematic and put it to the screen
//----------------------------------------------------------------------------
    public synchronized void update(Graphics g) {
        CheckDimensions();
        if (applet.OffScreenWidth < size().width ||
            applet.OffScreenHeight < size().height) {
                // System.out.println ("Image need resize");
                applet.OffScreenHeight = size().height;
                applet.OffScreenWidth = size().width;
                applet.SetUpImages();
        }
        AdjustScrollbars();
        if (applet.ImagesReady())  {
            applet.ibg.drawImage (applet.GridImage, 0, 0, this);
            DrawBorder(applet.ibg);

            applet.MySchematic.draw(applet.ibg, GridXOffset, GridYOffset, GridStep);
            if (NewWire != null) {
                NewWire.draw(applet.ibg, GridXOffset, GridYOffset, GridStep);  // Draw new Wire prototype
            }
            if (SelectBox) DrawSelectBox();
            DrawJunctions (applet.PinGrid, applet.ibg, GridXOffset, GridYOffset, GridStep);
            g.drawImage (applet.ImageBuffer, 0, 0, this);
        } else {
            // System.out.println ("paint Not ready");
            TellNotReady (g);
        }
    }

//----------------------------------------------------------------------------
// Draw all junctions
//----------------------------------------------------------------------------
    public void DrawJunctions(Pin PinGrid[][], Graphics g, int xo, int yo, int gs) {
        int x, y, cCnt;

        // calculate end x,y
        int xe = (size().width / applet.GridStep) + xo - 1;
        int ye = (size().height / applet.GridStep) + yo - 1 ;

        // Should not occure, but make sure we don't exceed array dimension
        if (xe >= applet.MaxXPoints) xe = applet.MaxXPoints - 1;
        if (ye >= applet.MaxYPoints) ye = applet.MaxYPoints - 1;

        // Paint any junctions in visible area
        g.setColor (JunctionColor);
        for (x = xo; x < xe; x++) {
            for (y = yo; y < ye; y++) {
                cCnt = PinGrid[x][y].Components.size();
                if ((( cCnt == 2) &&
                   ((ElectronicComponent)PinGrid[x][y].Components.elementAt(0)) instanceof Wire &&
                   ((ElectronicComponent)PinGrid[x][y].Components.elementAt(1)) instanceof Wire) ||
                   cCnt > 2) {
                        g.drawLine ( (x - xo) * gs - 1, (y - yo) * gs,
                                     (x - xo) * gs + 1, (y - yo) * gs );
                        g.drawLine ( (x - xo) * gs, (y - yo) * gs - 1,
                                     (x - xo) * gs, (y - yo) * gs + 1 );
                }
            }
        }
    }

//----------------------------------------------------------------------------
// Draw the select box
//----------------------------------------------------------------------------
    public void DrawSelectBox () {
        int w = Math.abs (SelectBoxX2 - SelectBoxX1);
        int h = Math.abs (SelectBoxY2 - SelectBoxY1);
        int x = (SelectBoxX1 < SelectBoxX2) ? SelectBoxX1 : SelectBoxX2;
        int y = (SelectBoxY1 < SelectBoxY2) ? SelectBoxY1 : SelectBoxY2;
        applet.ibg.setColor (Color.white);
        applet.ibg.drawRect (x, y, w, h);
    }

//----------------------------------------------------------------------------
// Get the XCoord of the mouse pointer
//----------------------------------------------------------------------------
    public int GetXCoord(int x) {
        int xc = ((x + hgs) / GridStep) + GridXOffset;
        if (xc < 1) xc = 1;
        if (xc >= applet.MaxXPoints) xc = applet.MaxXPoints - 1;
        return xc;
    }

//----------------------------------------------------------------------------
// Get the YCoord of the mouse pointer
//----------------------------------------------------------------------------
    public int GetYCoord(int y) {
        int yc = ((y + hgs) / GridStep) + GridYOffset;
        if (yc < 1) yc = 1;
        if (yc >= applet.MaxYPoints) yc = applet.MaxYPoints - 1;
        return yc;
    }

//----------------------------------------------------------------------------
// User wants to draw a new wire
//----------------------------------------------------------------------------
    public void WireMouseDown (int x, int y) {
        StatusMessage("Move to the desired end position and release the mouse button.");
        NewWire = new Wire (applet.PinGrid, GetXCoord(x), GetYCoord(y));
        NewWire.Set2ndCoord(applet.PinGrid, GetXCoord(x), GetYCoord(y)); // Avoid (0, 0) proto drawing
    }

//----------------------------------------------------------------------------
// User wants to place a new junction
//----------------------------------------------------------------------------
    public void JunctionMouseDown (int x, int y) {
        if (!applet.MySchematic.PlaceJunction(applet.PinGrid, GetXCoord(x), GetYCoord(y), GridStep)) {
            StatusMessage("Sorry, can't place a junction here. Try again.");
        } else {
            StatusMessage("Click again to add another junction.");
            repaint();
        }
    }

//----------------------------------------------------------------------------
// Select the 2nd coord of the new Wire
//----------------------------------------------------------------------------
    public void WireMouseDrag (int x, int y) {
        StatusMessage("Release mouse button to add the wire here.");
        if (NewWire != null) {
            NewWire.Set2ndCoord(applet.PinGrid, GetXCoord(x), GetYCoord(y));
            repaint();
        }
    }

//----------------------------------------------------------------------------
// User released the mouse-button at wire drawing
//----------------------------------------------------------------------------
    public void WireMouseUp (int x, int y) {
        if (NewWire != null) {
            if (NewWire.x1 == GetXCoord(x) && NewWire.y1 == GetYCoord(y)) {
                StatusMessage("Sorry - can't place wire. Length must be minimal one gridpoint.");
                NewWire = null;
                return;
            }
            StatusMessage("Choose a position for the next wire and press the mouse button.");

            NewWire.Set2ndCoord(applet.PinGrid, GetXCoord(x), GetYCoord(y));
            applet.addComponent(NewWire);
            NewWire.CheckPosition();
            NewWire.PlacePinsHere(applet.PinGrid);
            if (AutoJunction) {
                applet.MySchematic.PlaceJunction(applet.PinGrid, NewWire.x1, NewWire.y1, GridStep);
                applet.MySchematic.PlaceJunction(applet.PinGrid, NewWire.x2, NewWire.y2, GridStep);
            }

            NewWire = null;
            repaint();
        }
    }

//----------------------------------------------------------------------------
// User clicked the mouse when the simulation is running.
// Check if there is an interactive component such as a Switch or Push Button
//----------------------------------------------------------------------------
    public void MouseDownSimulateRunning(int x, int y) {
        ComponentPressed = applet.MySchematic.CheckIfComponentClicked(GetXCoord(x), GetYCoord(y));
        if (ComponentPressed != null) {
            if (!ComponentPressed.SimMouseDown()) {
                StatusMessage("Sorry, cannot move component while simulating. Press 'simulate' again to stop.");
            }
        } else {
            StatusMessage("Can't find a component here. Try again please.");
        }
    }

//----------------------------------------------------------------------------
// User released the mouse button when the simulation is running.
// Check if there is an interactive component such as a Switch or Push Button
//----------------------------------------------------------------------------
    public void MouseUpSimulateRunning(int x, int y) {
        if (ComponentPressed != null) {
            ComponentPressed.SimMouseUp();
        }
    }

//----------------------------------------------------------------------------
// User dragged the mouse when the simulation is running.
//----------------------------------------------------------------------------
    public void MouseDragSimulateRunning(int x, int y) {
    }

//----------------------------------------------------------------------------
// User clicked a mouse button
//----------------------------------------------------------------------------
    public synchronized boolean mouseDown(Event evt, int x, int y) {
        ElectronicComponent SelectedComponent;
        ElectronicComponent TempComponent;

        SelectBox = false;
        if (applet.SimulateRunning()) {
            MouseDownSimulateRunning(x, y);
            return true;
        }
        if (WireDrawing) {
            WireMouseDown(x, y);
            return true;
        }
        if (JunctionDrawing) {
            JunctionMouseDown(x, y);
            return true;
        }

        SelectedComponent = applet.MySchematic.CheckIfComponentClicked(GetXCoord(x), GetYCoord(y));
        if (SelectedComponent != null) {
            if (evt.clickCount == 2 && SelectedComponent instanceof Caption && SelectedComponent == PrevSelectedComponent) {
                Caption ChangeCaption = (Caption)SelectedComponent;
                if (applet.frame.MyTextChangeDialog == null) {
                    applet.frame.MyTextChangeDialog = new TextDialog(applet.frame, ChangeCaption, applet.frame.CHANGETEXTDIALOG_ID);
                }
            }
            if (evt.clickCount == 2 && SelectedComponent instanceof Probe && SelectedComponent == PrevSelectedComponent) {
                Probe ChangeProbe = (Probe)SelectedComponent;
                if (applet.frame.MyTextProbeChangeDialog == null) {
                    applet.frame.MyTextProbeChangeDialog = new TextDialog(applet.frame, ChangeProbe, applet.frame.CHANGETEXTPROBEDIALOG_ID);
                }
            }
            PrevSelectedComponent = SelectedComponent;
            if (SelectSchematic.size() > 0 && !SelectSchematic.InSchematic(SelectedComponent)) {
                // The user selected another component which is not in already selected component list.
                if (evt.shiftDown()) {
                    SelectedComponent.Selected = true;
                    SelectSchematic.addComponent(SelectedComponent);
                } else {
                    SelectSchematic.RemoveAllComponents();
                }
                repaint();
            }
            if (SelectSchematic.size() > 0) {
                SelectSchematic.RemovePinsGrid(applet.PinGrid);
                if (SelectSchematic.size() > 1) {
                    StatusMessage("Move all components to the desired position and release the mouse button.");
                } else {
                    TempComponent = (ElectronicComponent) SelectSchematic.Components.elementAt(0);
                    StatusMessage("Move this " + TempComponent.getName() +
                                  " to the desired position and release the mouse button.");
                }
            } else {
                SelectedComponent.Selected = true;
                SelectSchematic.addComponent (SelectedComponent);
                StatusMessage("Move this " + SelectedComponent.getName() +
                              " to the desired position and release the mouse button.");
                SelectedComponent.RemovePinsGrid(applet.PinGrid);
                repaint();
            }
            OldMouseX = x; OldMouseY = y;
        } else {
            SelectSchematic.RemoveAllComponents();
            SelectBox = true;
            SelectBoxX1 = SelectBoxX2 = x;
            SelectBoxY1 = SelectBoxY2 = y;
            repaint();
        }

        return true;
    }

//----------------------------------------------------------------------------
// User released the mouse button
//----------------------------------------------------------------------------
    public synchronized boolean mouseUp(Event evt, int x, int y) {
        ElectronicComponent HelpComponent;

        if (applet.HelpWanted) {
            if ((HelpComponent = applet.MySchematic.CheckIfComponentClicked(GetXCoord(x), GetYCoord(y))) != null) {
                HelpDialog MyHelpDialog = new HelpDialog(applet.frame, HelpComponent.ComponentName);
            } else {
                HelpDialog MyHelpDialog = new HelpDialog(applet.frame, "Schematic");
            }

            return true;
        }

        if (applet.SimulateRunning()) {
            MouseUpSimulateRunning(x, y);
            return true;
        }
        if (WireDrawing) {
            WireMouseUp(x, y);
            return true;
        }
        if (JunctionDrawing) return true;

        if (SelectSchematic.size() > 0) {
            SelectSchematic.CheckPosition();
            SelectSchematic.PlacePinsHere (applet.PinGrid);
            StatusMessage("Ready. Select another component or make a choice.");
            repaint();
        } else if (SelectBox) {
            SelectBox = false;
            applet.MySchematic.CheckIfComponentsInSelectBox(SelectSchematic,
                            GetXCoord(SelectBoxX1), GetYCoord(SelectBoxY1),
                            GetXCoord(SelectBoxX2), GetYCoord(SelectBoxY2)  );
            if (SelectSchematic.size() > 0) {
                repaint();
            }
        }
        if (SelectSchematic.size() > 0) {
            applet.frame.CutMenuItem.enable();
            applet.frame.CopyMenuItem.enable();
            if (applet.EnableFileOperations) {
                applet.frame.CopyDiskMenuItem.enable();
            }
            applet.MyControlPanel.EnableButton ("Cut");
            applet.MyControlPanel.EnableButton ("Copy");
        } else {
            applet.frame.CutMenuItem.disable();
            applet.frame.CopyMenuItem.disable();
            if (applet.EnableFileOperations) {
                applet.frame.CopyDiskMenuItem.disable();
            }
            applet.MyControlPanel.DisableButton ("Cut");
            applet.MyControlPanel.DisableButton ("Copy");
        }
        return true;
    }

//----------------------------------------------------------------------------
// User dragged the mouse (mouse button is down)
//----------------------------------------------------------------------------
    public synchronized boolean mouseDrag(Event evt, int x, int y) {
        int xChange, yChange;
        if (x == -1 || y == -1) return true;
        if (applet.SimulateRunning()) {
            MouseDragSimulateRunning(x, y);
            return true;
        }
        if (WireDrawing) {
            WireMouseDrag(x, y);
            return true;
        }
        if (JunctionDrawing) return true;

        if (SelectSchematic.size() > 0) {
            xChange = (x - OldMouseX) / GridStep;
            yChange = (y - OldMouseY) / GridStep;
            if (xChange != 0 || yChange != 0) {
                OldMouseX = x - (x - OldMouseX) % GridStep;
                OldMouseY = y - (y - OldMouseY) % GridStep;
                SelectSchematic.AdjustPosition (applet.PinGrid, xChange, yChange );
                applet.MySchematic.Modified = true;
                repaint();
            }
        } else if (SelectBox) {
            SelectBoxX2 = x; SelectBoxY2 = y;
            repaint();
        }

        return true;
    }

//----------------------------------------------------------------------------
// Display a status message in the status panel
//----------------------------------------------------------------------------
    public void StatusMessage(String msg) {
        applet.StatusMessage(msg);
    }


}