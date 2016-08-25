package digsim.components;

import java.io.PrintStream;

import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, MyColor,
//            Pin, ElectronicComponent

public class Monostable extends IntegratedCircuit
{

    public Monostable(Pin apin[][], int i, int j, int k, int l)
    {
        super(i, j, 9, 9, 3, 1, 4, 5, 4, 2);
        sortie = 0;
        entree = 0;
        reset = 0;
        IPin[0] = new InputPin("T", 1, 2, 2, 0, 0, 0, 4);
        IPin[1] = new InputPin("", 5, 8, 0, -2, 0, 0, 16);
        IPin[3] = new InputPin("r", 5, 8, 0, -2, 0, 0, 1);
        IPin[2] = new InputPin("#", 1, 5, 2, 0, 0, 0, 0);
        OPin[0] = new OutputPin("Q", 9, 2, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("Q", 9, 5, -2, 0, 0, 0, 1);
        for(int i1 = 0; i1 < 4; i1++)
            IPin[i1].setLevel(0);

        IPin[0].OldLevel = 0;
        OPin[0].setLevel(0);
        OPin[1].setLevel(0);
        ComponentName = "Monostable temporise";
        ClassName = "Monostable";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
        stop = k;
        delay = l;
    }

    public Monostable(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        sortie = 0;
        entree = 0;
        reset = 0;
        for(int k = 0; k < 4; k++)
            IPin[k].setLevel(0);

        IPin[0].OldLevel = 0;
        OPin[0].setLevel(0);
        OPin[1].setLevel(0);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Monostable monostable = new Monostable(this, i, j);
        monostable.stop = stop;
        monostable.delay = delay;
        return monostable;
    }

    public int getStop()
    {
        return stop;
    }

    public void setStop(int i)
    {
        stop = i;
    }

    public int getDelay()
    {
        return delay;
    }

    public void setDelay(int i)
    {
        delay = i;
    }

    public void SimulateLogic()
    {
        entree = IPin[0].getLevel();
        reset = IPin[1].getLevel();
        if(IPin[0].getOldLevel() == 0 && entree == 5 && reset == 5 || IPin[1].getOldLevel() == 0 && reset == 5 && entree == 5)
            if(current <= 0 && sortie == 0)
                current = stop + delay;
            else
            if(IPin[2].getLevel() == 5 && sortie == 5)
                current = stop;
        if(reset == 0)
        {
            sortie = 0;
            current = 0;
        }
        if(current == 0)
            sortie = 0;
        else
        if(current == stop)
            sortie = 5;
        OPin[0].setLevel(sortie);
        OPin[1].setLevel(sortie);
        IPin[0].OldLevel = entree;
        IPin[1].OldLevel = reset;
    }

    public void InitBeforeSimulate()
    {
        OPin[0].InitBeforeSimulate();
        OPin[1].InitBeforeSimulate();
        IPin[0].InitBeforeSimulate();
        IPin[1].InitBeforeSimulate();
        IPin[2].InitBeforeSimulate();
        if(current > 0)
            current--;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFill(MyColor.red);
        DrawWithOffset.fillText(g,"t" + Integer.toString(stop), ((l + HitBoxSize.width) - 1) * k + 3, ((i1 + HitBoxSize.height) - 1) * k + 4);
        g.setFill(MyColor.blue);
        DrawWithOffset.fillText(g,"a" + Integer.toString(delay), ((l + HitBoxSize.width) - 1) * k + 3, ((i1 + HitBoxSize.height) - 1) * k - 4);
        g.setStroke(MyColor.orange);
        DrawWithOffset.strokeRect(g,(l + 6) * k + 1, (i1 + 3) * k - 3, k - 6, 2 * k - 1);
        if(current >= 0)
        {
            if(current >= stop && delay != 0)
            {
                g.setFill(MyColor.blue);
                DrawWithOffset.fillRect(g,(l + 6) * k + 1, (i1 + 3) * k - 3, k - 5, 2 * k - Math.abs((20 * (current - stop)) / delay));
            }
            if(current <= stop && sortie == 5 && stop != 0)
            {
                g.setFill(MyColor.blue);
                DrawWithOffset.fillRect(g,(l + 6) * k + 1, (i1 + 3) * k - 3, k - 5, 2 * k);
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 6) * k + 1, ((i1 + 3) * k - 3) + Math.abs((20 * current) / stop), k - 5, 2 * k - Math.abs((20 * current) / stop));
            }
        }
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    public void Save(PrintStream printstream)
    {
        printstream.println("describe component Monostable");
        printstream.println(" pos " + Pos.x + " " + Pos.y);
        printstream.println(" rotation " + NbRotation);
        printstream.println(" stop " + stop);
        printstream.println(" delay " + delay);
        printstream.println("end describe");
    }

    public String ExportAsScript()
    {
        String s = new String("[|Monostable|pos|" + Pos.x + "|" + Pos.y + "|rotation|" + NbRotation + "|stop|" + stop + "|delay|" + delay + "|]\r\n^");
        return s;
    }

    int stop;
    int delay;
    int current;
    int sortie;
    int entree;
    int reset;
}
