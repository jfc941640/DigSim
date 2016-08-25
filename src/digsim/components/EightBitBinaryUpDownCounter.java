package digsim.components;



import digsim.DrawWithOffset;
import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, MyColor,
//            Pin, ElectronicComponent

public class EightBitBinaryUpDownCounter extends IntegratedCircuit
{

    public EightBitBinaryUpDownCounter(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 11, 3, 1, 4, 9, 3, 8);
        Aff = 0;
        Init = Math.round((int)(Math.random() * 256D));
        IPin[0] = new InputPin("", 1, 2, 2, 0, 0, 0, 4);
        IPin[1] = new InputPin("r", 5, 12, 0, -2, 0, 0, 1);
        IPin[2] = new InputPin("U/D", 1, 4, 2, 0, 0, 0, 0);
        OPin[0] = new OutputPin("A", 9, 2, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("B", 9, 3, -2, 0, 0, 0, 0);
        OPin[2] = new OutputPin("C", 9, 4, -2, 0, 0, 0, 0);
        OPin[3] = new OutputPin("D", 9, 5, -2, 0, 0, 0, 0);
        OPin[4] = new OutputPin("E", 9, 6, -2, 0, 0, 0, 0);
        OPin[5] = new OutputPin("F", 9, 7, -2, 0, 0, 0, 0);
        OPin[6] = new OutputPin("G", 9, 8, -2, 0, 0, 0, 0);
        OPin[7] = new OutputPin("H", 9, 9, -2, 0, 0, 0, 0);
        Count = Init;
        for(int k = 0; k < 3; k++)
            IPin[k].setLevel(-1);

        ComponentName = "CtrDctr Bin 8bits chargement async a 0 ou 255";
        ClassName = "EightBitBinaryUpDownCounter";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public EightBitBinaryUpDownCounter(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Aff = 0;
        Init = Math.round((int)(Math.random() * 256D));
        Count = Init;
        for(int k = 0; k < 3; k++)
            IPin[k].setLevel(-1);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        EightBitBinaryUpDownCounter eightbitbinaryupdowncounter = new EightBitBinaryUpDownCounter(this, i, j);
        return eightbitbinaryupdowncounter;
    }

    public void SimulateLogic()
    {
        Aff = 1;
        if(IPin[1].getLevel() == 5 && IPin[2].getLevel() == 0)
            Count = 255;
        else
        if(IPin[1].getLevel() == 5 && IPin[2].getLevel() == 5)
            Count = 0;
        else
        if(IPin[0].getOldLevel() == 0 && IPin[0].getLevel() == 5 && IPin[2].getLevel() == 0)
        {
            Count--;
            if(Count < 0 || Count > 255)
                Count = 255;
        } else
        if(IPin[0].getOldLevel() == 0 && IPin[0].getLevel() == 5 && IPin[2].getLevel() == 5)
        {
            Count++;
            if(Count < 0 || Count > 255)
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
        if((Count & 0x10) == 16)
            OPin[4].setLevel(5);
        else
            OPin[4].setLevel(0);
        if((Count & 0x20) == 32)
            OPin[5].setLevel(5);
        else
            OPin[5].setLevel(0);
        if((Count & 0x40) == 64)
            OPin[6].setLevel(5);
        else
            OPin[6].setLevel(0);
        if((Count & 0x80) == 128)
            OPin[7].setLevel(5);
        else
            OPin[7].setLevel(0);
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
            DrawWithOffset.fillText(g,"" + Integer.toString(Count), (l + 3) * k + 2, (i1 + 9) * k - 3);
        else
        if(IPin[0].PinDim.height > 0)
            DrawWithOffset.fillText(g,"" + Integer.toString(Count), (l + 3) * k + 2, (i1 + 2) * k + 1);
        else
        if(IPin[0].PinDim.width < 0)
            DrawWithOffset.fillText(g,"" + Integer.toString(Count), (l + 4) * k + 7, (i1 + 3) * k + 4);
        else
        if(IPin[0].PinDim.height < 0)
            DrawWithOffset.fillText(g,"" + Integer.toString(Count), (l + 10) * k + 1, (i1 + 5) * k - 1);
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    int Count;
    int Aff;
    int Init;
}
