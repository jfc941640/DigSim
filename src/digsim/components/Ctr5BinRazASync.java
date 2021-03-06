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

public class Ctr5BinRazASync extends IntegratedCircuit
{

    public Ctr5BinRazASync(Pin apin[][], int i, int j)
    {
        super(i, j, 12, 14, 3, 1, 5, 11, 11, 6);
        Aff = 0;
        Init = Math.round((int)(Math.random() * 16D));
        IPin[0] = new InputPin("A", 1, 2, 2, 0, 0, 0, 8);
        IPin[1] = new InputPin("B", 1, 3, 2, 0, 0, 0, 8);
        IPin[2] = new InputPin("C", 1, 4, 2, 0, 0, 0, 8);
        IPin[3] = new InputPin("D", 1, 5, 2, 0, 0, 0, 8);
        IPin[4] = new InputPin("E", 1, 6, 2, 0, 0, 0, 8);
        IPin[5] = new InputPin("L", 5, 14, 0, -2, 0, 0, 64);
        IPin[6] = new InputPin("r", 7, 14, 0, -2, 0, 0, 1);
        IPin[7] = new InputPin("U/D", 1, 10, 2, 0, 0, 0, 8);
        IPin[8] = new InputPin("ENT", 1, 9, 2, 0, 0, 0, 8);
        IPin[9] = new InputPin("", 1, 7, 2, 0, 0, 0, 4);
        IPin[10] = new InputPin("ENP", 1, 8, 2, 0, 0, 0, 8);
        OPin[0] = new OutputPin("QA", 10, 2, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("QB", 10, 3, -2, 0, 0, 0, 0);
        OPin[2] = new OutputPin("QC", 10, 4, -2, 0, 0, 0, 0);
        OPin[3] = new OutputPin("QD", 10, 5, -2, 0, 0, 0, 0);
        OPin[4] = new OutputPin("QE", 10, 6, -2, 0, 0, 0, 0);
        OPin[5] = new OutputPin("TC", 10, 9, -2, 0, 0, 0, 0);
        Count = Init;
        for(int k = 0; k < 11; k++)
            IPin[k].setLevel(-1);

        ComponentName = "CtrDctr BIN 5bits prog -CTR-PIPO- RAZ async";
        ClassName = "Ctr5BinRazASync";
        Can_Rotate = false;
        RegisterPins(apin, i, j);
    }

    public Ctr5BinRazASync(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Aff = 0;
        Init = Math.round((int)(Math.random() * 16D));
        Count = Init;
        for(int k = 0; k < 11; k++)
            IPin[k].setLevel(-1);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        Ctr5BinRazASync ctr5binrazasync = new Ctr5BinRazASync(this, i, j);
        return ctr5binrazasync;
    }

    public void SimulateLogic()
    {
        Aff = 1;
        if(IPin[8].getLevel() == 5 && IPin[7].getLevel() == 5 && Count == 31 || IPin[8].getLevel() == 5 && IPin[7].getLevel() == 0 && Count == 0)
            OPin[5].setLevel(5);
        else
            OPin[5].setLevel(0);
        if(IPin[6].getLevel() == 5)
            Count = 0;
        if(IPin[9].getOldLevel() == 0 && IPin[9].getLevel() == 5)
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
                    if(IPin[4].getLevel() == 5)
                        i += 16;
                    Count = i;
                } else
                {
                    Count = 0;
                }
            } else
            if(IPin[7].getLevel() == 5 && IPin[8].getLevel() == 5 && IPin[10].getLevel() == 5 && ENP == 5 && ENT == 5 && UpDown == 5)
            {
                Count++;
                if(Count < 0 || Count > 31)
                    Count = 0;
            } else
            if(IPin[7].getLevel() == 0 && IPin[8].getLevel() == 5 && IPin[10].getLevel() == 5 && ENP == 5 && ENT == 5 && UpDown == 0)
            {
                Count--;
                if(Count < 0 || Count > 31)
                    Count = 31;
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
        if((Count & 0x10) == 16)
            OPin[4].setLevel(5);
        else
            OPin[4].setLevel(0);
        IPin[9].OldLevel = IPin[9].getLevel();
    }

    public void InitBeforeSimulate()
    {
        UpDown = IPin[7].getLevel();
        ENP = IPin[8].getLevel();
        ENT = IPin[10].getLevel();
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
        if(IPin[0].PinDim.width > 0)
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
    int UpDown;
}
