package digsim;

import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;

import digsim.DigSimFrame;
import digsim.MyColor;
import digsim.Pin;
import digsim.TextDialog;
import digsim.components.Caption;
import digsim.components.CaptionBlack;
import digsim.components.CaptionBlue;
import digsim.components.CaptionGreen;
import digsim.components.CaptionRdP;
import digsim.components.CaptionRed;
import digsim.components.LoadMem;
import digsim.components.Monostable;
import digsim.components.Oscillator2;
import digsim.components.PLA5x4;
import digsim.components.Ram;
import digsim.components.Ram2;
import digsim.components.Rom;
import digsim.components.TraitsCie;
import digsim.ElectronicComponent;
import javafx.event.Event;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

// Referenced classes of package digsim:
//            Schematic, ElectronicComponent, Wire, Caption,
//            TextDialog, CaptionBlack, CaptionRdP, CaptionRed,
//            CaptionBlue, CaptionGreen, Probe, Monostable,
//            PLA5x4, TraitsCie, Oscillator2, Rom,
//            LoadMem, Ram, Ram2, HelpDialog,
//            DigSim, DigSimFrame, ControlPanel, Pin,
//            MyColor

public class SchematicPanel extends ScrollPane
{

    public SchematicPanel(DigSim digsim)
    {
        NewWire = null;
        ComponentPressed = null;
        PrevSelectedComponent = null;
        OldMouseX = 0;
        OldMouseY = 0;
        WireDrawing = false;
        JunctionDrawing = false;
        GridXOffset = 0;
        GridYOffset = 0;
        Cycle = 11;
        stop = 23;
        delay = 7;
        xzone = 161;
        yzone = 100;
        zzone = -1;
        etpla5x4 = "00 00 00 00 00 00 00 00 00 00";
        oupla5x4 = "00 00 00 00";
        nonpla5x4 = "0";
        SelectBox = false;
        CopySchematic = null;
        AutoJunction = true;
        applet = digsim;
        SelectSchematic = new Schematic();
        SchematicFont = Font.font("Serif", 20);
        SchematicFontMetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(SchematicFont);
//        setFont(SchematicFont);
//        super.setLayout(new BorderLayout());
//        myVertical = new Scrollbar(1);
//        add("East", myVertical);
//        myHorizontal = new Scrollbar(0);
//        add("South", myHorizontal);
        setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
//        setPrefSize(800, 600);
        Canvas c = new Canvas(applet.canvas.getWidth(),applet.canvas.getHeight());
        gc = c.getGraphicsContext2D();       
        gc.setFont(SchematicFont);
        c.setOnMousePressed(event -> {
            int x = (int)event.getX(), y = (int)event.getY();
            mouseDown(event, x, y);
        });

        c.setOnMouseReleased(event -> {
            int x = (int)event.getX(), y = (int)event.getY();
            mouseUp(event, x, y);
        });

        c.setOnMouseDragged(event -> {
            int x = (int)event.getX(), y = (int)event.getY();
            mouseDrag(event, x, y);
        });

        repaint();
        setContent(c);
    }

    public boolean handleEvent(Event event)
    {
label0:
//        {
//            if(event.target instanceof Scrollbar)
//            {
                repaint();
//                return true;
//            }
//            Event _tmp = event;
//            if(event.id != 401)
//            {
//                Event _tmp1 = event;
//                if(event.id != 403)
//                    break label0;
//            }
//            applet.keyDown(event, event.key);
//            return true;
//        }
//        super.handleEvent(event);
        return false;
    }

    public void Cut()
    {
        applet.MySchematic.RemoveSameElements(applet.PinGrid, SelectSchematic);
        CopySchematic = new Schematic(SelectSchematic);
        SelectSchematic.RemoveAllComponents();
        applet.updateAnalyzer();
        repaint();
    }

    public void Copy()
    {
        CopySchematic = new Schematic(SelectSchematic);
    }

    public void Paste()
    {
        if(CopySchematic != null)
        {
            SelectSchematic.RemoveAllComponents();
            SelectSchematic = applet.MySchematic.PasteSchematic(applet.PinGrid, CopySchematic, GridXOffset + 1, GridYOffset + 1);
            applet.frame.CutMenuItem.setDisable(false);
            applet.frame.CopyMenuItem.setDisable(false);
            if(applet.EnableFileOperations)
                applet.frame.CopyDiskMenuItem.setDisable(false);
            applet.MyControlPanel.EnableButton("Cut");
            applet.MyControlPanel.EnableButton("Copy");
            applet.updateAnalyzer();
            repaint();
        }
    }

    public void ReInit()
    {
        if(CopySchematic != null)
        {
            SelectSchematic.RemoveAllComponents();
            SelectSchematic = applet.MySchematic.PasteReInit(applet.PinGrid, CopySchematic, GridXOffset, GridYOffset);
            applet.frame.CutMenuItem.setDisable(false);
            applet.frame.CopyMenuItem.setDisable(false);
            if(applet.EnableFileOperations)
                applet.frame.CopyDiskMenuItem.setDisable(false);
            applet.MyControlPanel.EnableButton("Cut");
            applet.MyControlPanel.EnableButton("Copy");
            applet.updateAnalyzer();
            repaint();
        }
    }

    public void Rotate()
    {
        int i = 0;
        do
        {
            if(i >= SelectSchematic.Components.size())
                break;
            ElectronicComponent electroniccomponent = (ElectronicComponent)SelectSchematic.Components.elementAt(i);
            if(electroniccomponent.Selected)
            {
                electroniccomponent.RemovePinsGrid(applet.PinGrid);
                electroniccomponent.rotate();
                electroniccomponent.PlacePinsHere(applet.PinGrid);
                break;
            }
            i++;
        } while(true);
        applet.updateAnalyzer();
        repaint();
    }

    public void CopyTo()
    {
        applet.frame.DoFileSaveDialog(SelectSchematic, true, "Copie de la sélection vers...");
    }

    public void PasteFrom()
    {
        Schematic schematic = applet.frame.DoFileOpenDialog(applet.PinGrid, "Collage d'un schéma dans la simulation...");
        if(schematic != null)
        {
            SelectSchematic.RemoveAllComponents();
            SelectSchematic = applet.MySchematic.PasteSchematic(applet.PinGrid, schematic, GridXOffset, GridYOffset);
            applet.frame.CutMenuItem.setDisable(false);
            applet.frame.CopyMenuItem.setDisable(false);
            if(applet.EnableFileOperations)
                applet.frame.CopyDiskMenuItem.setDisable(false);
            applet.MyControlPanel.EnableButton("Cut");
            applet.MyControlPanel.EnableButton("Copy");
            applet.updateAnalyzer();
            repaint();
        }
    }

    public void SelectAll()
    {
        SelectSchematic = new Schematic(applet.MySchematic);
        if(SelectSchematic.size() > 0)
        {
            SelectSchematic.SetAllComponentsSelected();
            applet.frame.CutMenuItem.setDisable(false);
            applet.frame.CopyMenuItem.setDisable(false);
            applet.frame.PasteMenuItem.setDisable(false);
            applet.frame.RotateMenuItem.setDisable(false);
            if(applet.EnableFileOperations)
                applet.frame.CopyDiskMenuItem.setDisable(false);
            applet.MyControlPanel.EnableButton("Cut");
            applet.MyControlPanel.EnableButton("Copy");
            applet.MyControlPanel.EnableButton("Paste");
        } else
        {
            applet.frame.CutMenuItem.setDisable(true);
            applet.frame.CopyMenuItem.setDisable(true);
            applet.frame.PasteMenuItem.setDisable(true);
            applet.frame.RotateMenuItem.setDisable(true);
            if(applet.EnableFileOperations)
                applet.frame.CopyDiskMenuItem.setDisable(true);
            applet.MyControlPanel.DisableButton("Cut");
            applet.MyControlPanel.DisableButton("Copy");
            applet.MyControlPanel.DisableButton("Paste");
        }
        repaint();
    }

    public void CheckDimensions()
    {
        int i = (int) getPrefWidth();
        int j = (int) getPrefHeight();
        int k = i / 8 - 1;
        int l = j / 8 - 1;
        int i1 = (k + GridXOffset) - applet.MaxXPoints - 1;
        int j1 = (l + GridYOffset) - applet.MaxYPoints - 1;
        if(i1 > 0)
        {
            GridXOffset -= i1;
            if(GridXOffset < 0)
                GridXOffset = 0;
//            myHorizontal.setValue(GridXOffset);
//            getHbarPolicy().se
        }
        if(j1 > 0)
        {
            GridYOffset -= j1;
            if(GridYOffset < 0)
                GridYOffset = 0;
//            myVertical.setValue(GridYOffset);
        }
    }

    public void AdjustScrollbars()
    {
//        GridXOffset = myHorizontal.getValue();
//        GridYOffset = myVertical.getValue();
        int i = (int)getPrefWidth() / 8 - 1;
        int j = (int)getPrefHeight() / 8 - 1;
        String s = System.getProperty("os.name");
        if(s.equals("Windows 95") || s.equals("Windows 98") || s.equals("Windows XP") || s.equals("Windows NT"))
        {
//            myVertical.setValues(GridYOffset, j, 0, applet.MaxYPoints + 1);
//            myHorizontal.setValues(GridXOffset, i, 0, applet.MaxXPoints + 1);
        } else
        {
//            myVertical.setValues(GridYOffset, j, 0, (applet.MaxYPoints + 1) - j);
//            myHorizontal.setValues(GridXOffset, i, 0, (applet.MaxXPoints + 1) - i);
        }
    }

    public void DrawBorder(GraphicsContext g)
    {
        g.setStroke(BorderColor);
        DrawWithOffset.strokeRect(g,-GridXOffset * 8 + 1, -GridYOffset * 8 + 1, (applet.MaxXPoints - 1) * 4, (applet.MaxYPoints - 1) * 3);
        g.setStroke(applet.BackGroundColor);
        DrawWithOffset.strokeLine(g,(applet.MaxXPoints - GridXOffset) * 8, 0, (applet.MaxXPoints - GridXOffset) * 4, (applet.MaxYPoints - GridYOffset) * 3);
        DrawWithOffset.strokeLine(g,0, (applet.MaxYPoints - GridYOffset) * 8, (applet.MaxXPoints - GridXOffset) * 4, (applet.MaxYPoints - GridYOffset) * 3);
    }

    public void TellNotReady(GraphicsContext g)
    {
        String s = "Bienvenue sur DigSim ''RailWay'' 2012.2.0";
        String s1 = "Chargement de l'applet...";
        String s2 = "R e s p i r e z . . .";
        String s3 = "http://patrick.furon.free.fr";
        g.setFill(Color.rgb(255, 220, 0));
        DrawWithOffset.fillRect(g,0, 0, getPrefWidth(), getPrefHeight());
        g.setFill(Color.rgb(0, 0, 192));
        StatusMessage("Bienvenue sur ''DigSim''.");
        DrawWithOffset.fillText(g,s, getPrefWidth() / 2 - SchematicFontMetrics.computeStringWidth(s) / 2, getPrefHeight() / 2 - 12);
        DrawWithOffset.fillText(g,s3, getPrefWidth() - SchematicFontMetrics.computeStringWidth(s3) - 30, getPrefHeight() - 30);
        DrawWithOffset.fillText(g,s1, getPrefWidth() / 2 - SchematicFontMetrics.computeStringWidth(s1) / 2, getPrefHeight() / 2 + 12);
        g.setFill(Color.rgb(0, 0, 192));
        DrawWithOffset.fillText(g,s2, getPrefWidth() / 2 - SchematicFontMetrics.computeStringWidth(s2) / 2, getPrefHeight() / 2 + 36);
    }

    public void repaint()
    {
    	try{
        	update(gc);
    	}
    	catch (Exception e) {
			System.out.println("Error... " + e.toString());
		}
    }

    public synchronized void paint(GraphicsContext g)
    {
        CheckDimensions();
        if(applet.OffScreenWidth < getPrefWidth() || applet.OffScreenHeight < getPrefHeight())
            update(g);
        if(applet.ImagesReady())
        {
        	g.clearRect(0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight());
            g.drawImage(applet.getImage(), 0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight(), 0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight());//applet.ImageBuffer, 0, 0);
        }
        else
            TellNotReady(g);

    }

    public synchronized void update(GraphicsContext g)
    {
        CheckDimensions();
        if(applet.OffScreenWidth < getPrefWidth() || applet.OffScreenHeight < getPrefHeight())
        {
            applet.OffScreenHeight = (int)getPrefHeight();
            applet.OffScreenWidth = (int)getPrefWidth();
            applet.SetUpImages();
        }
        AdjustScrollbars();
        if(applet.ImagesReady())
        {
        	applet.gc.clearRect(0, 0, applet.gc.getCanvas().getWidth(), applet.gc.getCanvas().getHeight());
            applet.gc.drawImage(applet.GridImage, 0, 0);
            
//			DrawWithOffset.strokeLine(applet.gc,100, 100, 600, 100);
//            applet.gc.setFill(Color.BLACK);
//            applet.gc.setTextBaseline(VPos.TOP);
//			DrawWithOffset.fillText(applet.gc,"\u2192", 100, 100);
//            applet.gc.setTextBaseline(VPos.CENTER);
//			DrawWithOffset.fillText(applet.gc,"\u2192", 200, 100);
//            applet.gc.setTextBaseline(VPos.BASELINE);
//			DrawWithOffset.fillText(applet.gc,"\u2192", 300, 100);
//            applet.gc.setTextBaseline(VPos.BOTTOM);
//			DrawWithOffset.fillText(applet.gc,"\u2192", 400, 100);

            
            DrawBorder(applet.gc);
            DrawJunctions(applet.PinGrid, applet.gc, GridXOffset, GridYOffset, 8);
            if (applet.MySchematic != null)
            	applet.MySchematic.draw(applet.gc, GridXOffset, GridYOffset, 8);
            if(NewWire != null)
                NewWire.draw(applet.gc, GridXOffset, GridYOffset, 8);
            if(SelectBox)
                DrawSelectBox();
        	g.clearRect(0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight());
            g.drawImage(applet.getImage(), 0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight(), 0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight());//applet.ImageBuffer, 0, 0);
        } else
        {
            TellNotReady(g);
        }
    }

    public void DrawJunctions(Pin apin[][], GraphicsContext g, int i, int j, int k)
    {
        int j2 = ((int)getPrefWidth() / 8 + i) - 1;
        int k2 = ((int)getPrefHeight() / 8 + j) - 1;
        if(j2 >= applet.MaxXPoints)
        {
            j2 = applet.MaxXPoints - 1;
        }
        if(k2 >= applet.MaxYPoints)
        {
            k2 = applet.MaxYPoints - 1;
        }
        g.setStroke(JunctionColor);
        for(int l = i; l < j2; l++)
        {
            for(int j1 = j; j1 < k2; j1++)
            {
                int l1 = apin[l][j1].Components.size();
                if(l1 == 2 && ((ElectronicComponent)apin[l][j1].Components.elementAt(0) instanceof Wire) && ((ElectronicComponent)apin[l][j1].Components.elementAt(1) instanceof Wire) || l1 > 2)
                    DrawWithOffset.strokeRect(g,(l - i) * k - 1, (j1 - j) * k - 1, 2, 2);
            }

        }

        g.setFill(MyColor.yellow);
        for(int i1 = i; i1 < j2; i1++)
        {
            for(int k1 = j; k1 < k2; k1++)
            {
                int i2 = apin[i1][k1].Components.size();
                if(i2 == 2 && ((ElectronicComponent)apin[i1][k1].Components.elementAt(0) instanceof Wire) && ((ElectronicComponent)apin[i1][k1].Components.elementAt(1) instanceof Wire) || i2 > 2)
                    DrawWithOffset.fillRect(g,(i1 - i) * k, (k1 - j) * k, 1, 1);
            }

        }

    }

    public void DrawSelectBox()
    {
        int i = Math.abs(SelectBoxX2 - SelectBoxX1);
        int j = Math.abs(SelectBoxY2 - SelectBoxY1);
        int k = SelectBoxX1 >= SelectBoxX2 ? SelectBoxX2 : SelectBoxX1;
        int l = SelectBoxY1 >= SelectBoxY2 ? SelectBoxY2 : SelectBoxY1;
        applet.gc.setStroke(MyColor.red);
        DrawWithOffset.strokeRect(applet.gc,k, l, i, j);
        applet.gc.setFont(Font.font("Serif", 10));
        if(i >= 1 || j > 1)
        {
            applet.gc.setStroke(MyColor.white);
            DrawWithOffset.fillRect(applet.gc,7, 9, 50, 10);
            applet.gc.setStroke(MyColor.magenta);
            DrawWithOffset.fillText(applet.gc,String.valueOf((10 * i) / 8) + ", " + String.valueOf((10 * j) / 8), 7, 17);
        }
    }

    public int GetXCoord(int i)
    {
        int j = (i + 4) / 8 + GridXOffset;
        if(j < 1)
            j = 1;
        if(j >= applet.MaxXPoints)
        {
            j = applet.MaxXPoints - 1;
        }
        return j;
    }

    public int GetYCoord(int i)
    {
        int j = (i + 4) / 8 + GridYOffset;
        if(j < 1)
            j = 1;
        if(j >= applet.MaxYPoints)
        {
            j = applet.MaxYPoints - 1;
        }
        return j;
    }

    public void WireMouseDown(int i, int j)
    {
        StatusMessage("Déplacer jusqu'à obtenir le fil souhaité, puis relâcher le bouton de la souris.");
        NewWire = new Wire(applet.PinGrid, GetXCoord(i), GetYCoord(j));
        NewWire.Set2ndCoord(applet.PinGrid, GetXCoord(i), GetYCoord(j));
    }

    public void JunctionMouseDown(int i, int j)
    {
        if(!applet.MySchematic.PlaceJunction(applet.PinGrid, GetXCoord(i), GetYCoord(j), 8))
        {
            StatusMessage("Désolé, une jonction ne peut pas être placée ici. Essayez de nouveau.");
        } else
        {
            StatusMessage("Votre jonction est mise. Vous pouvez en placer une autre.");
            repaint();
        }
    }

    public void WireMouseDrag(int i, int j)
    {
        StatusMessage("Relâcher le bouton de la souris pour placer le fil dans cette position. Une jonction sera automatiquement réalisée si le point d'arrivée est un fil ou une extrémité ''entrée-sortie'' d'un composant.");
        if(NewWire != null)
        {
            NewWire.Set2ndCoord(applet.PinGrid, GetXCoord(i), GetYCoord(j));
            repaint();
        }
    }

    public void WireMouseUp(int i, int j)
    {
        if(NewWire != null)
        {
            if(NewWire.x1 == GetXCoord(i) && NewWire.y1 == GetYCoord(j))
            {
                StatusMessage("Désolé, impossible de placer un fil; sa longueur doit être au moins égale à un carreau de la grille.");
                NewWire = null;
                return;
            }
            StatusMessage("Choisir la position du bout du prochain fil, puis enfoncer le bouton de la souris.");
            NewWire.Set2ndCoord(applet.PinGrid, GetXCoord(i), GetYCoord(j));
            applet.addComponent(NewWire);
            NewWire.CheckPosition();
            NewWire.PlacePinsHere(applet.PinGrid);
            if(AutoJunction)
            {
                applet.MySchematic.PlaceJunction(applet.PinGrid, NewWire.x1, NewWire.y1, 8);
                applet.MySchematic.PlaceJunction(applet.PinGrid, NewWire.x2, NewWire.y2, 8);
            }
            NewWire = null;
            repaint();
        }
    }

    public void MouseDownSimulateRunning(int i, int j)
    {
        ComponentPressed = applet.MySchematic.CheckIfComponentClicked(GetXCoord(i), GetYCoord(j));
        if(ComponentPressed != null)
        {
            if(!ComponentPressed.SimMouseDown())
                StatusMessage("Il faut arrêter la simulation avant de déplacer un composant. Appuyer sur l'éclair jaune ou la touche ''ENTREE'').");
        } else
        {
            StatusMessage("Il n'y a pas de composant ici. Essayer de nouveau SVP.");
        }
    }

    public void MouseUpSimulateRunning(int i, int j)
    {
        if(ComponentPressed != null)
            ComponentPressed.SimMouseUp();
    }

    public void MouseDragSimulateRunning(int i, int j)
    {
    }

    public synchronized boolean mouseDown(MouseEvent event, int i, int j)
    {
    	boolean isDoubleClic = (event.getClickCount() == 2);
    	boolean isShiftDown = event.isShiftDown();
        SelectBox = false;
        if(applet.SimulateRunning())
        {
            MouseDownSimulateRunning(i, j);
            return true;
        }
        if(WireDrawing)
        {
            WireMouseDown(i, j);
            applet.MySchematic.Modified = true;
            applet.Message();
            return true;
        }
        if(JunctionDrawing)
        {
            JunctionMouseDown(i, j);
            applet.MySchematic.Modified = true;
            applet.Message();
            return true;
        }
        ElectronicComponent electroniccomponent = applet.MySchematic.CheckIfComponentClicked(GetXCoord(i), GetYCoord(j));
        if(electroniccomponent != null)
        {
            if(isDoubleClic && (electroniccomponent instanceof Caption))
            {
                Caption caption = (Caption)electroniccomponent;
                if(applet.frame.MyTextChangeDialog == null)
                {
                    DigSimFrame _tmp = applet.frame;
                    applet.frame.MyTextChangeDialog = new TextDialog(applet.frame, caption, 6);
                }
            }
            if(isDoubleClic && (electroniccomponent instanceof CaptionBlack))
            {
                CaptionBlack captionblack = (CaptionBlack)electroniccomponent;
                if(applet.frame.MyTextChangeDialog == null)
                {
                    DigSimFrame _tmp1 = applet.frame;
                    applet.frame.MyTextChangeDialog = new TextDialog(applet.frame, captionblack, 14);
                }
            }
            if(isDoubleClic && (electroniccomponent instanceof CaptionRdP))
            {
                CaptionRdP captionrdp = (CaptionRdP)electroniccomponent;
                if(applet.frame.MyTextChangeDialog == null)
                {
                    DigSimFrame _tmp2 = applet.frame;
                    applet.frame.MyTextChangeDialog = new TextDialog(applet.frame, captionrdp, 22);
                }
            }
            if(isDoubleClic && (electroniccomponent instanceof CaptionRed))
            {
                CaptionRed captionred = (CaptionRed)electroniccomponent;
                if(applet.frame.MyTextChangeDialog == null)
                {
                    DigSimFrame _tmp3 = applet.frame;
                    applet.frame.MyTextChangeDialog = new TextDialog(applet.frame, captionred, 16);
                }
            }
            if(isDoubleClic && (electroniccomponent instanceof CaptionBlue))
            {
                CaptionBlue captionblue = (CaptionBlue)electroniccomponent;
                if(applet.frame.MyTextChangeDialog == null)
                {
                    DigSimFrame _tmp4 = applet.frame;
                    applet.frame.MyTextChangeDialog = new TextDialog(applet.frame, captionblue, 6);
                }
            }
            if(isDoubleClic && (electroniccomponent instanceof CaptionGreen))
            {
                CaptionGreen captiongreen = (CaptionGreen)electroniccomponent;
                if(applet.frame.MyTextChangeDialog == null)
                {
                    DigSimFrame _tmp5 = applet.frame;
                    applet.frame.MyTextChangeDialog = new TextDialog(applet.frame, captiongreen, 6);
                }
            }
            if(isDoubleClic && (electroniccomponent instanceof Probe))
            {
                Probe probe = (Probe)electroniccomponent;
                if(applet.frame.MyTextProbeChangeDialog == null)
                {
                    DigSimFrame _tmp6 = applet.frame;
                    applet.frame.MyTextProbeChangeDialog = new TextDialog(applet.frame, probe, 8);
                }
            }
            if(isDoubleClic && (electroniccomponent instanceof Monostable) && applet.frame.MyMonoChangeDialog == null)
            {
                DigSimFrame _tmp7 = applet.frame;
                applet.frame.MyMonoChangeDialog = new TextDialog(applet.frame, (Monostable)electroniccomponent, 11);
            }
            if(isDoubleClic && (electroniccomponent instanceof PLA5x4) && applet.frame.MyPLA5x4ChangeDialog == null)
            {
                DigSimFrame _tmp8 = applet.frame;
                applet.frame.MyPLA5x4ChangeDialog = new TextDialog(applet.frame, (PLA5x4)electroniccomponent, 24);
            }
            if(isDoubleClic && (electroniccomponent instanceof TraitsCie) && applet.frame.MyZoneRChangeDialog == null)
            {
                DigSimFrame _tmp9 = applet.frame;
                applet.frame.MyZoneRChangeDialog = new TextDialog(applet.frame, (TraitsCie)electroniccomponent, 25);
            }
            if(isDoubleClic && (electroniccomponent instanceof Oscillator2) && applet.frame.MyTextChangeOscillator2Dialog == null)
            {
                DigSimFrame _tmp10 = applet.frame;
                applet.frame.MyTextChangeOscillator2Dialog = new TextDialog(applet.frame, (Oscillator2)electroniccomponent, 12);
            }
            if(isDoubleClic && (electroniccomponent instanceof Rom) && applet.frame.MyLoadDialog == null)
            {
                DigSimFrame _tmp11 = applet.frame;
                applet.frame.MyLoadDialog = new LoadMem(applet.frame, (Rom)electroniccomponent, 9);
            }
            if(isDoubleClic && (electroniccomponent instanceof Ram) && applet.frame.MyLoadDialog == null)
            {
                DigSimFrame _tmp12 = applet.frame;
                applet.frame.MyLoadDialog = new LoadMem(applet.frame, (Ram)electroniccomponent, 10);
            }
            if(isDoubleClic && (electroniccomponent instanceof Ram2) && applet.frame.MyLoadDialog == null)
            {
                DigSimFrame _tmp13 = applet.frame;
                applet.frame.MyLoadDialog = new LoadMem(applet.frame, (Ram2)electroniccomponent, 23);
            }
            PrevSelectedComponent = electroniccomponent;
            if(SelectSchematic.size() > 0 && !SelectSchematic.InSchematic(electroniccomponent))
            {
                if(isShiftDown)
                {
                    electroniccomponent.Selected = true;
                    SelectSchematic.addComponent(electroniccomponent);
                } else
                {
                    SelectSchematic.RemoveAllComponents();
                }
                repaint();
            }
            if(SelectSchematic.size() > 0)
            {
                SelectSchematic.RemovePinsGrid(applet.PinGrid);
                if(SelectSchematic.size() > 1)
                {
                    StatusMessage("Déplacer l'ensemble jusqu'à la position souhaitée, puis relâcher le bouton de la souris. Si vous avez sélectionné une partie du schéma, les connexions seront étirées et conservées.");
                } else
                {
                    ElectronicComponent electroniccomponent1 = (ElectronicComponent)SelectSchematic.Components.elementAt(0);
                    StatusMessage("Déplacez ''" + electroniccomponent1.getName() + "'' jusqu'à la position souhaitée, puis relâcher le bouton de la souris. Vous pouvez aussi obtenir de l'aide sur ce composant en cliquant sur ''?''");
                }
            } else
            {
                electroniccomponent.Selected = true;
                SelectSchematic.addComponent(electroniccomponent);
                StatusMessage("Déplacez ''" + electroniccomponent.getName() + "'' jusqu'à la position souhaitée, puis relâcher le bouton de la souris. Vous pouvez aussi obtenir de l'aide sur ce composant en cliquant sur ''?''");
                electroniccomponent.RemovePinsGrid(applet.PinGrid);
                repaint();
            }
            OldMouseX = i;
            OldMouseY = j;
        } else
        {
            SelectSchematic.RemoveAllComponents();
            SelectBox = true;
            SelectBoxX1 = SelectBoxX2 = i;
            SelectBoxY1 = SelectBoxY2 = j;
            repaint();
        }
        applet.MySchematic.Modified = true;
        applet.Message();
        return true;
    }

    public synchronized boolean mouseUp(Event event, int i, int j)
    {
        if(applet.HelpWanted)
        {
            ElectronicComponent electroniccomponent;
            HelpDialog helpdialog;
            if((electroniccomponent = applet.MySchematic.CheckIfComponentClicked(GetXCoord(i), GetYCoord(j))) != null)
                helpdialog = new HelpDialog(applet.frame, electroniccomponent.ComponentName);
            else
                helpdialog = new HelpDialog(applet.frame, "Schematic");
            return true;
        }
        if(applet.SimulateRunning())
        {
            MouseUpSimulateRunning(i, j);
            return true;
        }
        if(WireDrawing)
        {
            WireMouseUp(i, j);
            return true;
        }
        if(JunctionDrawing)
            return true;
        if(SelectSchematic.size() > 0)
        {
            SelectSchematic.CheckPosition();
            SelectSchematic.PlacePinsHere(applet.PinGrid);
            StatusMessage("Prêt... Sélectionner un autre composant, réaliser des connexions ou aller au menu. Vous pouvez aussi lancer la simulation (éclair jaune ou touche ENTREE).");
            repaint();
        } else
        if(SelectBox)
        {
            SelectBox = false;
            applet.MySchematic.CheckIfComponentsInSelectBox(SelectSchematic, GetXCoord(SelectBoxX1), GetYCoord(SelectBoxY1), GetXCoord(SelectBoxX2), GetYCoord(SelectBoxY2));
//            if(SelectSchematic.size() > 0)
                repaint();
        }
        if(SelectSchematic.size() > 0)
        {
            applet.frame.CutMenuItem.setDisable(false);
            applet.frame.CopyMenuItem.setDisable(false);
            if(applet.EnableFileOperations)
                applet.frame.CopyDiskMenuItem.setDisable(false);
            applet.MyControlPanel.EnableButton("Cut");
            applet.MyControlPanel.EnableButton("Copy");
        } else
        {
            applet.frame.CutMenuItem.setDisable(true);
            applet.frame.CopyMenuItem.setDisable(true);
            if(applet.EnableFileOperations)
                applet.frame.CopyDiskMenuItem.setDisable(true);
            applet.MyControlPanel.DisableButton("Cut");
            applet.MyControlPanel.DisableButton("Copy");
        }
        return true;
    }

    public synchronized boolean mouseDrag(Event event, int i, int j)
    {
        if(i == -1 || j == -1)
            return true;
        if(applet.SimulateRunning())
        {
            MouseDragSimulateRunning(i, j);
            return true;
        }
        if(WireDrawing)
        {
            WireMouseDrag(i, j);
            return true;
        }
        if(JunctionDrawing)
            return true;
        if(SelectSchematic.size() > 0)
        {
            int k = (i - OldMouseX) / 8;
            int l = (j - OldMouseY) / 8;
            if(k != 0 || l != 0)
            {
                OldMouseX = i - (i - OldMouseX) % 8;
                OldMouseY = j - (j - OldMouseY) % 8;
                SelectSchematic.AdjustPosition(applet.PinGrid, k, l);
                applet.MySchematic.Modified = true;
                repaint();
            }
        } else
        if(SelectBox)
        {
            SelectBoxX2 = i;
            SelectBoxY2 = j;
            repaint();
        }
        return true;
    }

    public void StatusMessage(String s)
    {
        applet.StatusMessage(s);
    }

//    protected Scrollbar myHorizontal;
//    protected Scrollbar myVertical;
    DigSim applet;
    static final int GridStep = 8;
    static final int hgs = 4;
    Wire NewWire;
    ElectronicComponent ComponentPressed;
    ElectronicComponent PrevSelectedComponent;
    int OldMouseX;
    int OldMouseY;
    boolean WireDrawing;
    boolean JunctionDrawing;
    protected Font SchematicFont;
    protected FontMetrics SchematicFontMetrics;
    static Color BorderColor;
    int GridXOffset;
    int GridYOffset;
    int Cycle;
    int stop;
    int delay;
    int xzone;
    int yzone;
    int zzone;
    String etpla5x4;
    String oupla5x4;
    String nonpla5x4;
    boolean SelectBox;
    int SelectBoxX1;
    int SelectBoxY1;
    int SelectBoxX2;
    int SelectBoxY2;
    Schematic SelectSchematic;
    Schematic CopySchematic;
    static Color JunctionColor;
    boolean AutoJunction;
    GraphicsContext gc;

    static
    {
        BorderColor = MyColor.red;
        JunctionColor = MyColor.gray;
    }
}
