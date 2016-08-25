package digsim;

import java.awt.Dimension;
import java.awt.Point;
import java.io.PrintStream;

import com.sun.javafx.tk.Toolkit;

import digsim.InputPin;
import digsim.Pin;
import digsim.ElectronicComponent;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, Pin

public class Probe extends ElectronicComponent
{

    public Probe(Pin apin[][], int i, int j)
    {
        super(i, j, 4, 6, 1, 1, 2, 4, 1, 0);
        k = Toolkit.getToolkit();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        largeurEcran = (int) bounds.getWidth();
        hauteurEcran = (int) bounds.getHeight();
        MAX_HISTORY = (int)(141F + (float)((largeurEcran - 1024) / 6));
        clockup_probe = false;
        clockdn_probe = false;
        cut_probe = false;
        ChannelPos = new Point(0, 0);
        ChannelDim = new Dimension(0, 0);
        LevelHistory = new int[MAX_HISTORY];
        ComponentName = "Sonde pour analyseur logique";
        ClassName = "Probe";
        IPin[0] = new InputPin("Sonde", 2, 5, 0, -1, 0, 1, 0);
        RegisterPins(apin, i, j);
    }

    public Probe(Pin apin[][], int i, int j, String s, boolean flag, boolean flag1)
    {
        super(i, j, 4, 6, 1, 1, 2, 4, 1, 0);
        k = Toolkit.getToolkit();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        largeurEcran = (int) bounds.getWidth();
        hauteurEcran = (int) bounds.getHeight();
        MAX_HISTORY = (int)(141F + (float)((largeurEcran - 1024) / 6));
        clockup_probe = false;
        clockdn_probe = false;
        cut_probe = false;
        clockup_probe = flag;
        clockdn_probe = flag1;
        ChannelPos = new Point(0, 0);
        ChannelDim = new Dimension(0, 0);
        LevelHistory = new int[MAX_HISTORY];
        ComponentName = "Probe";
        ClassName = "Probe";
        IPin[0] = new InputPin(s, 2, 5, 0, -1, 0, 1, 0);
        RegisterPins(apin, i, j);
    }

    public Probe(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        k = Toolkit.getToolkit();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        largeurEcran = (int) bounds.getWidth();
        hauteurEcran = (int) bounds.getHeight();
        MAX_HISTORY = (int)(141F + (float)((largeurEcran - 1024) / 6));
        clockup_probe = false;
        clockdn_probe = false;
        cut_probe = false;
        ChannelPos = new Point(0, 0);
        ChannelDim = new Dimension(0, 0);
        LevelHistory = new int[MAX_HISTORY];
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Probe probe = new Probe(this, i, j);
        return probe;
    }

    public void draw(GraphicsContext g, int i, int j, int l)
    {
        super.draw(g, i, j, l);
        int i1 = Pos.x - i;
        int j1 = Pos.y - j;
        DrawInputPins(g, i1, j1, l);
        g.setFill(Color.rgb(255, 215, 0));
        DrawWithOffset.fillOval(g,(i1 + 1) * l + 2, (j1 + 3) * l, l + 4, l);
        g.setStroke(ComponentColor);
        DrawWithOffset.strokeOval(g,(i1 + 1) * l + 2, (j1 + 3) * l, l + 4, l);
        g.setFill(ComponentColor);
        DrawWithOffset.fillText(g,"S", (i1 + 1) * l + 5, (j1 + 4) * l);
    }

    public void Save(PrintStream printstream)
    {
        if(clockup_probe)
            printstream.println("describe component ProbeUp");
        else
        if(clockdn_probe)
            printstream.println("describe component ProbeDn");
        else
            printstream.println("describe component Probe");
        printstream.println(" pos " + Pos.x + " " + Pos.y);
        printstream.println(" rotation " + NbRotation);
        printstream.println(" Text " + IPin[0].getName());
        printstream.println("end describe");
    }

    public String ExportAsScript()
    {
        String s = null;
        if(clockup_probe)
            s = new String("ProbeUp");
        else
        if(clockdn_probe)
            s = new String("ProbeDn");
        else
            s = new String("Probe");
        String s1 = new String("[|" + s + "|pos|" + Pos.x + "|" + Pos.y + "|rotation|" + NbRotation + "|Text|" + IPin[0].getName() + "|]\r\n^");
        return s1;
    }

    public Point ChannelPos;
    public Dimension ChannelDim;
    public int LevelHistory[];
    Toolkit k;
    Dimension tailleEcran;
    int largeurEcran;
    int hauteurEcran;
    public int MAX_HISTORY;
    public boolean clockup_probe;
    public boolean clockdn_probe;
    public boolean cut_probe;
    public int OldLevel;
}
