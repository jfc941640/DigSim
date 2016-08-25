package digsim.components;

import java.awt.Graphics;
import java.awt.Point;

import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, Pin,
//            ElectronicComponent

public class RetardFixe extends IntegratedCircuit
{

    public RetardFixe(Pin apin[][], int i, int j)
    {
        super(i, j, 4, 4, 3, 1, 2, 2, 1, 1);
        Count5 = 0;
        Count0 = 0;
        MemCnt5 = 0;
        MemCnt0 = 0;
        Retard = 1;
        out = 0;
        IPin[0] = new InputPin("", 2, 2, 1, 0, 0, 0, 32);
        OPin[0] = new OutputPin("", 6, 2, -1, 0, 0, 0, 0);
        IPin[0].setLevel(0);
        ComponentName = "Retard fixe (usage restreint)";
        ClassName = "RetardFixe";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public RetardFixe(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Count5 = 0;
        Count0 = 0;
        MemCnt5 = 0;
        MemCnt0 = 0;
        Retard = 1;
        out = 0;
        out = 0;
        IPin[0].setLevel(0);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        RetardFixe retardfixe = new RetardFixe(this, i, j);
        return retardfixe;
    }

    public void SimulateLogic()
    {
        if(IPin[0].getOldLevel() == 0 && IPin[0].getLevel() == 5)
            Count5 = Retard;
        if(IPin[0].getOldLevel() == 5 && IPin[0].getLevel() == 0)
            Count0 = Retard;
        if(MemCnt5 == 1 && Count5 == 0)
            OPin[0].setLevel(5);
        if(MemCnt0 == 1 && Count0 == 0)
            OPin[0].setLevel(0);
        IPin[0].OldLevel = IPin[0].getLevel();
    }

    public void InitBeforeSimulate()
    {
        OPin[0].InitBeforeSimulate();
        IPin[0].InitBeforeSimulate();
        MemCnt5 = Count5;
        MemCnt0 = Count0;
        if(Count5 >= 0)
            Count5--;
        if(Count0 >= 0)
            Count0--;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        DrawWithOffset.fillText(g,"\u03B4t", (l + 4) * k - 3, (i1 + 2) * k + 4);
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    int Count5;
    int Count0;
    int MemCnt5;
    int MemCnt0;
    int Retard;
    int out;
}
