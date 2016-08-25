package digsim.components;

import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, MyColor,
//            Pin, ElectronicComponent

public class Counterxx163 extends IntegratedCircuit
{

    public Counterxx163(Pin apin[][], int i, int j)
    {
        super(i, j, 12, 14, 3, 1, 5, 11, 9, 5);
        Aff = 0;
        Init = Math.round((int)(Math.random() * 16D));
        IPin[0] = new InputPin("A", 1, 2, 2, 0, 0, 0, 8);
        IPin[1] = new InputPin("B", 1, 3, 2, 0, 0, 0, 8);
        IPin[2] = new InputPin("C", 1, 4, 2, 0, 0, 0, 8);
        IPin[3] = new InputPin("D", 1, 5, 2, 0, 0, 0, 8);
        IPin[4] = new InputPin("", 1, 7, 2, 0, 0, 0, 4);
        IPin[5] = new InputPin("L", 5, 14, 0, -2, 0, 0, 64);
        IPin[6] = new InputPin("R", 7, 14, 0, -2, 0, 0, 64);
        IPin[7] = new InputPin("ENP", 1, 8, 2, 0, 0, 0, 8);
        IPin[8] = new InputPin("ENT", 1, 9, 2, 0, 0, 0, 8);
        OPin[0] = new OutputPin("QA", 10, 2, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("QB", 10, 3, -2, 0, 0, 0, 0);
        OPin[2] = new OutputPin("QC", 10, 4, -2, 0, 0, 0, 0);
        OPin[3] = new OutputPin("QD", 10, 5, -2, 0, 0, 0, 0);
        OPin[4] = new OutputPin("T15", 10, 9, -2, 0, 0, 0, 0);
        Count = Init;
        for(int k = 0; k < 9; k++)
            IPin[k].setLevel(-1);

        ComponentName = "Ctr BIN 4bits prog -CTR-PIPO- RAZ sync (74HC163)";
        ClassName = "Counterxx163";
        Can_Rotate = false;
        RegisterPins(apin, i, j);
    }

    public Counterxx163(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Aff = 0;
        Init = Math.round((int)(Math.random() * 16D));
        Count = Init;
        for(int k = 0; k < 9; k++)
            IPin[k].setLevel(-1);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        Counterxx163 counterxx163 = new Counterxx163(this, i, j);
        return counterxx163;
    }

    public void SimulateLogic()
    {
        Aff = 1;
        if(Count == 15 && IPin[8].getLevel() == 5)
            OPin[4].setLevel(5);
        else
        if(Count != 15 || IPin[8].getLevel() == 0)
            OPin[4].setLevel(0);
        if(IPin[4].getOldLevel() == 0 && IPin[4].getLevel() == 5)
            if(IPin[5].getLevel() == 5)
            {
                if(IPin[6].getLevel() == 0)
                {
                    int i = 0;
                    if(IPin[0].getLevel() == 5)
                        i++;
                    if(IPin[1].getLevel() == 5)
                        i += 2;
                    if(IPin[2].getLevel() == 5)
                        i += 4;
                    if(IPin[3].getLevel() == 5)
                        i += 8;
                    Count = i;
                } else
                {
                    Count = 0;
                }
            } else
            if(IPin[6].getLevel() == 5)
                Count = 0;
            else
            if(IPin[7].getLevel() == 5 && IPin[8].getLevel() == 5 && ENP == 5 && ENT == 5)
            {
                Count++;
                if(Count < 0 || Count > 15)
                    Count = 0;
            }
        if((Count & 1) == 1)
            OPin[0].setLevel(5);
        else
            OPin[0].setLevel(0);
        if((Count & 2) == 2)
            OPin[1].setLevel(5);
        else
            OPin[1].setLevel(0);
        if((Count & 4) == 4)
            OPin[2].setLevel(5);
        else
            OPin[2].setLevel(0);
        if((Count & 8) == 8)
            OPin[3].setLevel(5);
        else
            OPin[3].setLevel(0);
        IPin[4].OldLevel = IPin[4].getLevel();
    }

    public void InitBeforeSimulate()
    {
        ENP = IPin[7].getLevel();
        ENT = IPin[8].getLevel();
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        if(Aff == 1)
            g.setFill(MyColor.red);
        else
            g.setFill(Color.rgb(240, 240, 240));
        DrawWithOffset.fillText(g,"" + Integer.toString(Count), (l + 5) * k - 1, (i1 + 4) * k);
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    int Count;
    int Aff;
    int Init;
    int ENP;
    int ENT;
}
