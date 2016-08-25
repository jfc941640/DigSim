package digsim.components;

import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, MyColor,
//            Pin, ElectronicComponent

public class FourBitBCDUpDownCounter extends IntegratedCircuit
{

    public FourBitBCDUpDownCounter(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 7, 3, 1, 4, 5, 3, 4);
        Aff = 0;
        Init = Math.round((int)(Math.random() * 16D));
        Count = Init;
        IPin[0] = new InputPin("", 1, 2, 2, 0, 0, 0, 4);
        IPin[1] = new InputPin("r", 5, 8, 0, -2, 0, 0, 1);
        IPin[2] = new InputPin("U/D", 1, 4, 2, 0, 0, 0, 0);
        OPin[0] = new OutputPin("a", 9, 2, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("b", 9, 3, -2, 0, 0, 0, 0);
        OPin[2] = new OutputPin("c", 9, 4, -2, 0, 0, 0, 0);
        OPin[3] = new OutputPin("d", 9, 5, -2, 0, 0, 0, 0);
        for(int k = 0; k < 3; k++)
            IPin[k].setLevel(-1);

        ComponentName = "CtrDctr DCB 4bits chargement async a 0 ou 9";
        ClassName = "FourBitBCDUpDownCounter";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public FourBitBCDUpDownCounter(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Aff = 0;
        Init = Math.round((int)(Math.random() * 16D));
        Count = Init;
        for(int k = 0; k < 3; k++)
            IPin[k].setLevel(-1);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        FourBitBCDUpDownCounter fourbitbcdupdowncounter = new FourBitBCDUpDownCounter(this, i, j);
        return fourbitbcdupdowncounter;
    }

    public void SimulateLogic()
    {
        Aff = 1;
        if(IPin[1].getLevel() == 5 && IPin[2].getLevel() == 0)
            Count = 9;
        else
        if(IPin[1].getLevel() == 5 && IPin[2].getLevel() == 5)
            Count = 0;
        else
        if(IPin[0].getOldLevel() == 0 && IPin[0].getLevel() == 5 && IPin[2].getLevel() == 0)
        {
            Count--;
            if(Count < 0 || Count > 9)
                Count = 9;
        } else
        if(IPin[0].getOldLevel() == 0 && IPin[0].getLevel() == 5 && IPin[2].getLevel() == 5)
        {
            Count++;
            if(Count < 0 || Count > 9)
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
        IPin[0].OldLevel = IPin[0].getLevel();
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
            DrawWithOffset.fillText(g,"" + Integer.toString(Count), (l + 3) * k + 2, (i1 + 6) * k - 1);
        else
        if(IPin[0].PinDim.height > 0)
            DrawWithOffset.fillText(g,"" + Integer.toString(Count), (l + 6) * k + 4, (i1 + 3) * k + 1);
        else
        if(IPin[0].PinDim.width < 0)
            DrawWithOffset.fillText(g,"" + Integer.toString(Count), (l + 5) * k + 5, (i1 + 2) * k + 1);
        else
        if(IPin[0].PinDim.height < 0)
            DrawWithOffset.fillText(g,"" + Integer.toString(Count), (l + 3) * k + 2, (i1 + 4) * k + 1);
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    int Aff;
    int Init;
    int Count;
}
