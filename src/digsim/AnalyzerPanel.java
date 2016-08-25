package digsim;

import java.net.URL;
import java.util.Vector;

import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// Referenced classes of package digsim:
//            Probe, SimpleDialog, DigSim, Schematic,
//            MyColor, InputPin, AnalyzerFrame

public class AnalyzerPanel extends ScrollPane
{

    public AnalyzerPanel(DigSim digsim, Stage frame1)
    {
        OffScreenImage = null;
        ImageBuffer = null;
        CopyImage = null;
        ButtonOffset = 0;
        probes = null;
        ImageButtonsDisabled = false;
        reset = true;
        PressedProbe = null;
        DragProbe = null;
        CurrentCol = 0;
        ImageUpdated = false;
        SigCycle = 0;
        MyAnalyzerFrame = null;
        applet = digsim;
        frame = frame1;
        BorderPane bp = new BorderPane();
        AnalyzerFont = Font.font("Serif", 13);
        AnalyzerFontMetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(AnalyzerFont);
//        setContent(bp);
        LoadButtonsImage();
        setVbarPolicy(ScrollBarPolicy.ALWAYS);
//        setPrefSize(applet.frame.getWidth() - 50, 250);
        setWidth(applet.frame.getWidth());
//        myVertical = new Scrollbar(1);
//        add("East", myVertical);
//        ImageSize = new Dimension(1024, 250);
        CheckOffScreenImage();
        
        ClassLoader cl = this.getClass().getClassLoader();
        
        final ToggleGroup group = new ToggleGroup();
        FlowPane flowPane = new FlowPane(); 
        flowPane.getChildren().add(buildToggleButton(cl, "images/down.png", group, (ActionEvent event) -> {ButtonPressed(0);}));
        flowPane.getChildren().add(buildToggleButton(cl, "images/none.png", group, (ActionEvent event) -> {ButtonPressed(0);}));
        flowPane.getChildren().add(buildToggleButton(cl, "images/up.png", group, (ActionEvent event) -> {ButtonPressed(0);}));
        bp.setLeft(flowPane);
        bp.setCenter(og.getCanvas());
        //setContent(bp);
        setContent(og.getCanvas());
        repaint();
    }
    
	private ToggleButton buildToggleButton(ClassLoader cl, String image, ToggleGroup group, EventHandler<ActionEvent> eventHandler) {
//		final ToggleButton toggle      = new ToggleButton();
//        final Image unselected  = new Image(cl.getResourceAsStream("images/new.png"));
//        final Image selected = new Image(cl.getResourceAsStream(image));
//        final ImageView    toggleImage = new ImageView();
//        toggle.setGraphic(toggleImage);
//        toggleImage.imageProperty().bind(Bindings
//          .when(toggle.selectedProperty())
//            .then(selected)
//            .otherwise(unselected)
//        );
        
		
		final ToggleButton toggle = new ToggleButton("", new ImageView(new Image(cl.getResourceAsStream(image))));
		toggle.setToggleGroup(group);
		toggle.setOnAction(eventHandler);
		return toggle;

	}

//    public void AdjustScrollbar()
//    {
//        if(probes == null)
//            return;
//        int i = 50 * probes.size();
//        int j = (int)getHeight();
//        int k = i - j;
//        if(k < 0)
//            k = 0;
//        String s = System.getProperty("os.name");
//        if(s.equals("Windows 95") || s.equals("Windows 98") || s.equals("Windows NT") || s.equals("Windows XP"))
//            myVertical.setValues(myVertical.getValue(), j, 0, k + j);
//        else
//            myVertical.setValues(myVertical.getValue(), j, 0, k);
//    }

    public void CheckOffScreenImage()
    {
        if(OffScreenImage == null)
        {
            PrepareOffScreenImage();
            return;
        }
//        if((int)getWidth() > getPrefWidth() || (int)getHeight() > getPrefHeight())
//        {
//            getPrefWidth() = (int)getWidth();
//            getPrefHeight() = (int)getHeight();
//            PrepareOffScreenImage();
//        }
    }

    public void PrepareOffScreenImage()
    {
//        og = OffScreenImage.getGraphics();
    	Canvas oc = new Canvas(applet.frame.getWidth(), 300);
    	og = oc.getGraphicsContext2D();
        og.setFont(AnalyzerFont);
        OffScreenImage = applet.getImage(og);//(getPrefWidth(), getPrefHeight());
        ImageBuffer = OffScreenImage;
        repaint();
    }

    public void SelectButton(int i)
    {
//        cig.copyArea(0, i, 24, 24, 0, -i);
    }

    public void ButtonPressed(int i)
    {
        Probe probe = (Probe)probes.elementAt(i);
        PressedProbe = probe;
        probe.cut_probe = true;
        if(probe.clockup_probe)
        {
            probe.clockup_probe = false;
            probe.clockdn_probe = true;
        } else
        if(probe.clockdn_probe)
        {
            probe.clockup_probe = false;
            probe.clockdn_probe = false;
        } else
        {
            probe.clockup_probe = true;
            probe.clockdn_probe = false;
        }
        for(int j = 0; j < probes.size(); j++)
            if(i != j)
            {
                Probe probe1 = (Probe)probes.elementAt(j);
                probe1.clockup_probe = false;
                probe1.clockdn_probe = false;
            }

        repaint();
    }

    public boolean mouseDown(Event event, int i, int j)
    {
        PressedProbe = null;
        DragProbe = null;
        for(int k = 0; k < probes.size(); k++)
        {
//            int i1 = (k * 50 + 24) - myVertical.getValue();
//            if(i >= 5 && i <= 29 && j >= i1 && j <= i1 + 24)
//                ButtonPressed(k);
        }

        for(int l = 0; l < probes.size(); l++)
        {
            Probe probe = (Probe)probes.elementAt(l);
            if(i >= probe.ChannelPos.x && j >= probe.ChannelPos.y && i <= probe.ChannelPos.x + probe.ChannelDim.width && j <= probe.ChannelPos.y + probe.ChannelDim.height)
            {
                DragProbe = probe;
                repaint();
                return true;
            }
        }

        return true;
    }

    public boolean mouseUp(Event event, int i, int j)
    {
        if(DragProbe != null)
        {
            DragProbe = null;
            repaint();
        }
        if(PressedProbe != null)
        {
            PressedProbe = null;
            repaint();
        }
        return true;
    }

    public boolean mouseDrag(Event event, int i, int j)
    {
        if(DragProbe != null)
        {
            for(int k = 0; k < probes.size(); k++)
            {
                Probe probe = (Probe)probes.elementAt(k);
                if(i >= probe.ChannelPos.x && j >= probe.ChannelPos.y && i <= probe.ChannelPos.x + probe.ChannelDim.width && j <= probe.ChannelPos.y + probe.ChannelDim.height && probe != DragProbe)
                {
                    applet.MySchematic.SwapComponents(probe, DragProbe);
                    repaint();
                    return true;
                }
            }

        }
        return true;
    }

    public void DrawProbeHistory(GraphicsContext g, Probe probe)
    {
        g.setStroke(Color.BLUE);
        int j;
        if(SigCycle > (getWidth() - 50) / 6)
        {
            if(SigCycle < probe.MAX_HISTORY)
                j = SigCycle - ((int)getWidth() - 50) / 6;
            else
                j = 0;
        } else
        {
            j = 0;
        }
        int i = 0;
        for(; j < probe.MAX_HISTORY; j++)
        {
            if(j > 0 && i > 0 && probe.LevelHistory[j - 1] != probe.LevelHistory[j])
                DrawWithOffset.strokeLine(g,probe.ChannelPos.x + i * 6, probe.ChannelPos.y + 5, probe.ChannelPos.x + i * 6, (probe.ChannelPos.y + probe.ChannelDim.height) - 5);
            if(probe.LevelHistory[j] == 5)
                DrawWithOffset.strokeLine(g,probe.ChannelPos.x + i * 6, probe.ChannelPos.y + 5, probe.ChannelPos.x + (i + 1) * 6, probe.ChannelPos.y + 5);
            else
                DrawWithOffset.strokeLine(g,probe.ChannelPos.x + i * 6, (probe.ChannelPos.y + probe.ChannelDim.height) - 5, probe.ChannelPos.x + (i + 1) * 6, (probe.ChannelPos.y + probe.ChannelDim.height) - 5);
            i++;
        }

    }

    public void DrawNoProbes(GraphicsContext g)
    {
        String s = "L'analyseur logique n'a pas de sondes";
        double i = AnalyzerFontMetrics.computeStringWidth(s);
        double j = (getWidth() - i) / 2;
        g.setFill(MyColor.black);
        DrawWithOffset.fillText(g,s, j, getHeight() / 2);
    }

    public void DrawEmptyProbes(GraphicsContext g)
    {
        probes = applet.MySchematic.getProbes();
        int i;
        boolean flag = false; i = 0;
        boolean flag1 = false;
        int i2 = 140;
        int j2 = (int)getVvalue();
        g.setFill(MyColor.pink);
        DrawWithOffset.fillRect(g,0, 0, getPrefWidth(), getPrefHeight());
        if(probes.size() == 0)
        {
            DrawNoProbes(g);
            return;
        }
        for(int j1 = 0; j1 < probes.size(); j1++)
        {
            Probe probe = (Probe)probes.elementAt(j1);
            int l = (int)AnalyzerFontMetrics.computeStringWidth(probe.IPin[0].getName());
            if(l > i2)
                i2 = l;
        }

        for(int k1 = 0; k1 < probes.size(); k1++)
        {
            Probe probe1 = (Probe)probes.elementAt(k1);
            int j = k1 * 50 - j2;
            g.setFill(TextColor);
            DrawWithOffset.fillText(g,probe1.IPin[0].getName(), 5, j + 16);
            g.setStroke(MyColor.gray);
            DrawWithOffset.strokeLine(g,0, (j + 50) - 2, (int)getWidth(), (j + 50) - 2);
            g.setStroke(MyColor.white);
            DrawWithOffset.strokeLine(g,0, (j + 50) - 1, (int)getWidth(), (j + 50) - 1);
            probe1.ChannelPos.x = 12 + i2 + 5;
            probe1.ChannelPos.y = 12 + j + 10;
            probe1.ChannelDim.width = (int)getWidth() - (12 + i2 + 20);
            probe1.ChannelDim.height = 26;
            g.setFill(BackGroundColor);
            DrawWithOffset.fillRect(g,probe1.ChannelPos.x, probe1.ChannelPos.y + 1, probe1.ChannelDim.width, probe1.ChannelDim.height - 1);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeRect(g,probe1.ChannelPos.x, probe1.ChannelPos.y + 1, probe1.ChannelDim.width, probe1.ChannelDim.height - 1);
            g.setStroke(BackGroundColor);
            if(probe1.clockup_probe)
            {
                if(probe1 == PressedProbe)
                    SelectButton(72);
                else
                    SelectButton(96);
            } else
            if(probe1.clockdn_probe)
            {
                if(probe1 == PressedProbe)
                    SelectButton(120);
                else
                    SelectButton(144);
            } else
            if(probe1 == PressedProbe)
                SelectButton(24);
            else
                SelectButton(48);
            DrawProbeHistory(g, probe1);
            Canvas canvas1 = new Canvas(ImageBuffer.getWidth(), ImageBuffer.getHeight());
            GraphicsContext g1 = canvas1.getGraphicsContext2D();
            g1.drawImage(CopyImage, 0, 0);
            ImageBuffer = applet.getImage(g1);
            g.drawImage(ImageBuffer, 5, 24 + j);
            j = i * 50 - j2;
            g.setStroke(Color.BLACK);
            g.setFill(Color.BLACK);
            int i1 = i2 + 17;
            DrawWithOffset.strokeLine(g,i1, j + 5 + k1 * 50, (int)getWidth(), j + 5 + k1 * 50);
            for(int l1 = 0; l1 < ((int)getWidth() - 40) / 12; l1++)
            {
                DrawWithOffset.strokeLine(g,i1 + l1 * 6 * 2, j + 3 + k1 * 50, i1 + l1 * 6 * 2, j + 7 + k1 * 50);
                int k;
                if(SigCycle * 6 + 40 < (int)getWidth())
                    k = l1;
                else
                    k = (l1 + SigCycle / 2) - ((int)getWidth() - i1) / 12;
                DrawWithOffset.fillText(g,"o", (i1 - 3) + 10 * l1 * 6 * 2, j + 9 + k1 * 50);
                DrawWithOffset.fillText(g,String.valueOf(k) + 0, (i1 - 3 - 4) + 10 * l1 * 6 * 2, j + 20 + k1 * 50);
                DrawWithOffset.fillText(g,"'", (i1 - 3) + 62 + 10 * l1 * 6 * 2, j + 17 + k1 * 50);
            }

        }

        if(DragProbe != null)
        {
            g.setStroke(MyColor.red);
            DrawWithOffset.strokeRect(g,DragProbe.ChannelPos.x, DragProbe.ChannelPos.y, DragProbe.ChannelDim.width, DragProbe.ChannelDim.height);
        }
    }

    public void repaint()
    {
    	paint(og);
    }

    public synchronized void paint(GraphicsContext g)
    {
        if(OffScreenImage == null)
            return;
//        AdjustScrollbar();
        if(probes != null && (int)getHeight() >= 50 * probes.size())
        {
            update(g);
            return;
        }
        if(!ImageUpdated)
        {
            update(g);
            return;
        } else
        {
            g.drawImage(OffScreenImage, 0, 0);
            return;
        }
    }

    public synchronized void update(GraphicsContext g)
    {
//        AdjustScrollbar();
        CheckOffScreenImage();
        if(OffScreenImage == null)
        {
            return;
        } else
        {
            DrawEmptyProbes(og);
//            g.drawImage(OffScreenImage, 0, 0);
            ImageUpdated = true;
            return;
        }
    }

    public boolean handleEvent(Event event)
    {
//        if(event.target instanceof Scrollbar)
//        {
            repaint();
            return true;
//        } else
//        {
//            return super.handleEvent(event);
//        }
    }

//    public boolean action(Event event, Object obj)
//    {
//        if(!(event.target instanceof Button));
//        return false;
//    }

    public void update(Probe probe)
    {
        if(SigCycle < probe.MAX_HISTORY)
        {
            probe.LevelHistory[SigCycle] = probe.IPin[0].getLevel();
        } else
        {
            for(int i = 0; i < probe.MAX_HISTORY - 1; i++)
                probe.LevelHistory[i] = probe.LevelHistory[i + 1];

        }
        probe.LevelHistory[probe.MAX_HISTORY - 1] = probe.IPin[0].getLevel();
    }

    public void drawLevel(Probe probe)
    {
        int i = CurrentCol * 6;
        if(i >= probe.ChannelDim.width)
            return;
        if(CurrentCol == 0)
        {
            og.setFill(BackGroundColor);
            DrawWithOffset.fillRect(og,probe.ChannelPos.x + i, probe.ChannelPos.y + 1, 7, probe.ChannelDim.height - 1);
        }
        og.setFill(BackGroundColor);
        DrawWithOffset.fillRect(og,probe.ChannelPos.x + i + 1, probe.ChannelPos.y + 1, 6, probe.ChannelDim.height - 1);
        if(CurrentCol > 0 && probe.IPin[0].getLevel() != probe.OldLevel)
        {
            og.setStroke(MyColor.red);
            DrawWithOffset.strokeLine(og,probe.ChannelPos.x + i, probe.ChannelPos.y + 5, probe.ChannelPos.x + i, (probe.ChannelPos.y + probe.ChannelDim.height) - 5);
        }
        if(CurrentCol < probe.MAX_HISTORY)
            probe.LevelHistory[CurrentCol] = probe.IPin[0].getLevel();
        if(probe.IPin[0].getLevel() == 5)
        {
            og.setStroke(MyColor.red);
            DrawWithOffset.strokeLine(og,probe.ChannelPos.x + i, probe.ChannelPos.y + 5, probe.ChannelPos.x + i + 6, probe.ChannelPos.y + 5);
        } else
        {
            og.setStroke(MyColor.red);
            DrawWithOffset.strokeLine(og,probe.ChannelPos.x + i, (probe.ChannelPos.y + probe.ChannelDim.height) - 5, probe.ChannelPos.x + i + 6, (probe.ChannelPos.y + probe.ChannelDim.height) - 5);
        }
        paint(og);
    }

    public void update()
    {
        if(probes == null)
            return;
        if(reset)
        {
            for(int i = 0; i < probes.size(); i++)
            {
                Probe probe = (Probe)probes.elementAt(i);
                probe.cut_probe = false;
                for(int k = 0; k < probe.MAX_HISTORY; k++)
                    probe.LevelHistory[k] = 0;

            }

            SigCycle = 0;
            DrawEmptyProbes(og);
            reset = false;
        }
        for(int j = 0; j < probes.size(); j++)
        {
            Probe probe1 = (Probe)probes.elementAt(j);
            update(probe1);
        }

        paint(og);
        SigCycle++;
    }

    public void LoadButtonsImage()
    {
//        MediaTracker mediatracker = new MediaTracker(this);
        //Image image = applet.getImage(applet.getDocumentBase(), "images/all_analyzer.gif");
        ClassLoader cl = this.getClass().getClassLoader();
//        URL url = cl.getResource("images/all_analyzer.gif");
        Image image = new Image(cl.getResourceAsStream("images/all_analyzer.gif"));

//        mediatracker.addImage(image, 0);
//        try
//        {
//            mediatracker.waitForAll();
//        }
//        catch(Exception exception)
//        {
//            String s1 = exception.toString();
//            String as1[] = {
//                "OK"
//            };
//            SimpleDialog simpledialog1 = new SimpleDialog(null, "Lecture de l'image des boutons", s1, as1, 1, 0, 0, 1);
//            return;
//        }
//        if(mediatracker.isErrorAny())
//        {
//            ImageButtonsDisabled = true;
//            String s = "Ne peut lire: images/all_analyzer.gif ; ImageButtons will be disabled";
//            String as[] = {
//                "OK"
//            };
//            SimpleDialog simpledialog = new SimpleDialog(null, "Erreur pendant la lecture de l'image des boutons", s, as, 1, 0, 0, 1);
//            return;
//        } else
//        {
//            ImageBuffer = new Image( ((int) image.getWidth(), 24);
//            CopyImage = applet.createImage(image.getWidth(), image.getHeight());
//            cig = CopyImage.getGraphics();
//            cig.drawImage(image, 0, 0);
            return;
//        }
    }

    static final int CHANNEL_HEIGHT = 50;
    static final int DISPLAY_OFFSET = 12;
    static final int CLOCK_WIDTH = 6;
    static final int LINE_OFFSET = 5;
    static final int BUTTON_X_OFFSET = 5;
    static final int BUTTON_Y_OFFSET = 24;
    static final int BUTTON_CLOCK_NONE = 48;
    static final int BUTTON_CLOCK_NONE_PRESSED = 24;
    static final int BUTTON_CLOCK_UP_PRESSED = 72;
    static final int BUTTON_CLOCK_UP_SELECTED = 96;
    static final int BUTTON_CLOCK_DN_PRESSED = 120;
    static final int BUTTON_CLOCK_DN_SELECTED = 144;
    static Color TextColor;
    static Color BackGroundColor;
//    protected Scrollbar myVertical;
    DigSim applet;
    Image OffScreenImage;
    Image ImageBuffer;
    Image CopyImage;
//    GraphicsContext cig;
    int ButtonOffset;
    GraphicsContext og;
//    Dimension ImageSize;
    protected Font AnalyzerFont;
    protected FontMetrics AnalyzerFontMetrics;
    Vector probes;
    boolean ImageButtonsDisabled;
    boolean reset;
    Probe PressedProbe;
    Probe DragProbe;
    int CurrentCol;
    boolean ImageUpdated;
    int SigCycle;
    Stage frame;
    AnalyzerFrame MyAnalyzerFrame;

    static
    {
        TextColor = MyColor.black;
        BackGroundColor = MyColor.white;
    }
}