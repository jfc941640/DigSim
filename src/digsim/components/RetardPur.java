package digsim.components;

import java.awt.Graphics;
import java.awt.Point;

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

public class RetardPur extends IntegratedCircuit
{

    public RetardPur(Pin apin[][], int i, int j)
    {
        super(i, j, 4, 4, 3, 1, 2, 2, 1, 1);
        Retard = Math.round((int)(1.0D + Math.random() * 9D));
        MEM = new int[Retard + 1];
        IPin[0] = new InputPin("", 2, 2, 1, 0, 0, 0, 32);
        OPin[0] = new OutputPin("", 6, 2, -1, 0, 0, 0, 0);
        IPin[0].setLevel(0);
        ComponentName = "Retard pur (usage restreint)";
        ClassName = "RetardPur";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
        for(int k = 0; k <= Retard; k++)
            MEM[k] = 0;

    }

    public RetardPur(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Retard = Math.round((int)(1.0D + Math.random() * 9D));
        MEM = new int[Retard + 1];
        for(int k = 0; k <= Retard; k++)
            MEM[k] = 0;

        IPin[0].setLevel(0);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        RetardPur retardpur = new RetardPur(this, i, j);
        return retardpur;
    }

    public void SimulateLogic()
    {
        OPin[0].setLevel(MEM[Retard]);
    }

    public void InitBeforeSimulate()
    {
        OPin[0].InitBeforeSimulate();
        IPin[0].InitBeforeSimulate();
        MEM[0] = IPin[0].getLevel();
        for(int i = Retard; i > 0; i--)
            MEM[i] = MEM[i - 1];

    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        g.setFill(MyColor.blue);
        DrawWithOffset.fillText(g,"" + Integer.toString(Retard), (l + 4) * k - 1, (i1 + 2) * k + 4);
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    int Retard;
    int MEM[];
}
