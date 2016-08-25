package digsim.components;

import java.io.PrintStream;

import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            ElectronicComponent, OutputPin, MyColor, Pin

public class Oscillator2 extends ElectronicComponent
{

    public Oscillator2(Pin apin[][], int i, int j, int k)
    {
        super(i, j, 8, 6, 1, 1, 4, 4, 0, 1);
        cycle = 1;
        currentcycle = 1;
        ComponentName = "Horloge de periode ajustable";
        ClassName = "Oscillator2";
        Can_Rotate = true;
        OPin[0] = new OutputPin(Integer.toString(k), 7, 3, -2, 0, 0, 0, 0);
        cycle = k;
        currentcycle = 1;
        OPin[0].Level = 5 * Math.round((int)(Math.random() * 2D));
        RegisterPins(apin, i, j);
    }

    public Oscillator2(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        cycle = 1;
        currentcycle = 1;
        cycle = c;
        currentcycle = 1;
        OPin[0].Level = 5 * Math.round((int)(Math.random() * 2D));
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Oscillator2 oscillator2 = new Oscillator2(this, i, j);
        oscillator2.cycle = cycle;
        return oscillator2;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawOutputPins(g, l, i1, k);
        g.setFill(Color.rgb(255, 255, 102));
        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, k * 4, k * 4);
        g.setStroke(MyColor.lightGray);
        DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k, (l + 2) * k, (i1 + 4) * k);
        DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 2) * k, (l + 3) * k, (i1 + 4) * k);
        DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 2) * k, (l + 4) * k, (i1 + 4) * k);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k, (l + 3) * k, (i1 + 2) * k);
        if(OPin[0].getLevel() == 5)
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k - 1, (l + 3) * k, (i1 + 2) * k - 1);
        g.setStroke(MyColor.blue);
        DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 4) * k, (l + 4) * k, (i1 + 4) * k);
        if(OPin[0].getLevel() == 0)
            DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 4) * k - 1, (l + 4) * k, (i1 + 4) * k - 1);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,(l + 1) * k, (i1 + 1) * k, k * 4, k * 4);
        g.setFill(MyColor.blue);
        DrawWithOffset.fillText(g,"P=" + Integer.toString(cycle), (((l - 2) + HitBoxSize.width) - 1) * k, ((i1 + 3 + HitBoxSize.height) - 2) * k);
    }

    public void SimulateLogic()
    {
    }

    public void Simulate(int i)
    {
        InformConnectedComponents(i);
    }

    public void InitBeforeSimulate()
    {
        OPin[0].InitBeforeSimulate();
        if(cycle == currentcycle)
        {
            if(OPin[0].getLevel() == 0)
                OPin[0].Level = 5;
            else
                OPin[0].Level = 0;
            currentcycle = 1;
        } else
        {
            currentcycle++;
        }
    }

    public void Save(PrintStream printstream)
    {
        printstream.println("describe component Oscillator2");
        printstream.println(" pos " + Pos.x + " " + Pos.y);
        printstream.println(" rotation " + NbRotation);
        printstream.println(" cycle " + cycle);
        printstream.println("end describe");
    }

    public String ExportAsScript()
    {
        String s = new String("[|Oscillator2|pos|" + Pos.x + "|" + Pos.y + "|rotation|" + NbRotation + "|cycle|" + cycle + "|]\r\n^");
        return s;
    }

    public int cycle;
    int currentcycle;
    int c;
}
