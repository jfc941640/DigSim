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

public class RetardRC extends IntegratedCircuit
{

    public RetardRC(Pin apin[][], int i, int j)
    {
        super(i, j, 4, 4, 3, 1, 2, 2, 1, 1);
        Count = 0;
        Ctr = 0;
        Maxi = 0;
        Init = 2 * Math.round((int)(1.0D + Math.random() * 9D));
        IPin[0] = new InputPin("", 2, 2, 1, 0, 0, 0, 32);
        OPin[0] = new OutputPin("", 6, 2, -1, 0, 0, 0, 0);
        IPin[0].setLevel(0);
        ComponentName = "Filtre RC (usage restreint)";
        ClassName = "RetardRC";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
        Maxi = Init;
    }

    public RetardRC(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Count = 0;
        Ctr = 0;
        Maxi = 0;
        Init = 2 * Math.round((int)(1.0D + Math.random() * 9D));
        IPin[0].setLevel(0);
        Maxi = Init;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        RetardRC retardrc = new RetardRC(this, i, j);
        return retardrc;
    }

    public void SimulateLogic()
    {
        if(2 * Count >= Maxi - 1 && Ctr == 5)
            OPin[0].setLevel(5);
        if(2 * Count <= Maxi + 1 && Ctr == 0)
            OPin[0].setLevel(0);
    }

    public void InitBeforeSimulate()
    {
        if(IPin[0].getLevel() == 5)
            Ctr = 5;
        else
            Ctr = 0;
        OPin[0].InitBeforeSimulate();
        IPin[0].InitBeforeSimulate();
        if(Ctr == 5 && Count < Maxi)
            Count++;
        if(Ctr == 0 && Count > 0)
            Count--;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        g.setFill(MyColor.red);
        DrawWithOffset.fillText(g,"" + Integer.toString(Maxi / 2), (l + 4) * k - 1, (i1 + 2) * k + 4);
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    int Count;
    int Ctr;
    int Maxi;
    int Init;
}
