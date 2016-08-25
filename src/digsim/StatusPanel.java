package digsim;

import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;

import digsim.MyColor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

// Referenced classes of package digsim:
//            DigSim, MyColor

public class StatusPanel extends BorderPane
{

    public StatusPanel(DigSim digsim)
    {
        ActMessage = "Pour lancer la simulation, cliquez sur l'éclair jaune ou appuyez sur la touche ''ENTREE''.";
        SimulationInitializeInProgress = false;
        SimulationRunning = false;
        StatusLedOn = false;
        applet = digsim;
        StatusFont = Font.font("Serif", 12);
//        setFont(StatusFont);
        StatusFontMetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(StatusFont);
        Canvas c = new Canvas(applet.canvas.getWidth(),20);
        gc = c.getGraphicsContext2D();
        gc.setFont(StatusFont);
        repaint();
        getChildren().add(c);
    }

    public boolean SimulateRunning()
    {
        return SimulationRunning | SimulationInitializeInProgress;
    }


//    public Dimension preferredSize()
//    {
//        int i = applet.size().width;
//        byte byte0 = 20;
//        return new Dimension(i, byte0);
//    }

    public void DrawStatusLED(GraphicsContext g)
    {
        StatusLedOn = !StatusLedOn;
        double i = applet.canvas.getWidth() - 20;
        if(SimulationRunning && StatusLedOn)
            g.setFill(MyColor.blue);
        else
        if(SimulationInitializeInProgress && StatusLedOn)
            g.setFill(MyColor.red);
        else
            g.setFill(MyColor.yellow);
        DrawWithOffset.fillOval(g,i + 3, 3, 15, 15);
    }

    public void repaint()
    {
    	paint(gc);
    }


    public void paint(GraphicsContext g)
    {
//        g.setStroke(BackGroundColor);
//        DrawWithOffset.fillRect(g,0, 0, size().width - 20, 20);
//        g.setStroke(TextColor);
//        DrawWithOffset.fillText(g,ActMessage, 2, StatusFontMetrics.getHeight() - 2);
//        g.setStroke(MyColor.black);
//        DrawWithOffset.fillRect(g,size().width - 20, 0, 20, 20);
//        g.setStroke(BackGroundColor);
//        DrawWithOffset.strokeRect(g,size().width - 20, 0, 20, 20);
//        DrawStatusLED(g);
    	g.clearRect(0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight());
        g.setFill(BackGroundColor);
        DrawWithOffset.fillRect(g,0, 0, g.getCanvas().getWidth() - 20, 20);
        g.setFill(TextColor);
        DrawWithOffset.fillText(g,ActMessage, 2, 15 - 2);
        g.setFill(MyColor.black);
        DrawWithOffset.fillRect(g,g.getCanvas().getWidth() - 20, 0, 20, 20);
        g.setStroke(BackGroundColor);
        DrawWithOffset.strokeRect(g,g.getCanvas().getWidth(), 0, 20, 20);
        DrawStatusLED(g);
    }

    public void StatusMessage(String s)
    {
        ActMessage = s;
        repaint();
    }

    DigSim applet;
    public static final int STATUSPANEL_HEIGHT = 20;
    protected Font StatusFont;
    protected FontMetrics StatusFontMetrics;
    protected static Color BackGroundColor;
    protected static Color TextColor;
    protected String ActMessage;
    protected static final int STATUSLEDS_WIDTH = 20;
    boolean SimulationInitializeInProgress;
    boolean SimulationRunning;
    boolean StatusLedOn;
    GraphicsContext gc;

    static
    {
        BackGroundColor = MyColor.yellow;
        TextColor = MyColor.blue;
    }
}
