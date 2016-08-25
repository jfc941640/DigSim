package digsim.components;

import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, MyColor,
//            Pin, ElectronicComponent

public class Buffer8bits extends IntegratedCircuit
{

    public Buffer8bits(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 13, 3, 1, 4, 10, 10, 8);
        OUT = new int[8];
        IPin[0] = new InputPin("D0", 1, 2, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("1", 1, 3, 2, 0, 0, 0, 0);
        IPin[2] = new InputPin("2", 1, 4, 2, 0, 0, 0, 0);
        IPin[3] = new InputPin("3", 1, 5, 2, 0, 0, 0, 0);
        IPin[4] = new InputPin("4", 1, 6, 2, 0, 0, 0, 0);
        IPin[5] = new InputPin("5", 1, 7, 2, 0, 0, 0, 0);
        IPin[6] = new InputPin("6", 1, 8, 2, 0, 0, 0, 0);
        IPin[7] = new InputPin("7", 1, 9, 2, 0, 0, 0, 0);
        IPin[8] = new InputPin("OE", 4, 13, 0, -2, 0, 0, 1);
        IPin[9] = new InputPin("OE", 6, 13, 0, -2, 0, 0, 1);
        OPin[0] = new OutputPin("Q0", 9, 2, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("1", 9, 3, -2, 0, 0, 0, 0);
        OPin[2] = new OutputPin("2", 9, 4, -2, 0, 0, 0, 0);
        OPin[3] = new OutputPin("3", 9, 5, -2, 0, 0, 0, 0);
        OPin[4] = new OutputPin("4", 9, 6, -2, 0, 0, 0, 0);
        OPin[5] = new OutputPin("5", 9, 7, -2, 0, 0, 0, 0);
        OPin[6] = new OutputPin("6", 9, 8, -2, 0, 0, 0, 0);
        OPin[7] = new OutputPin("7", 9, 9, -2, 0, 0, 0, 0);
        for(int k = 0; k < 8; k++)
            OPin[k].setLevel(0x6c9d8);

        IPin[8].setLevel(5);
        IPin[9].setLevel(5);
        ComponentName = "Buffer 8 bits a sorties 3S (74HC541)";
        ClassName = "Buffer8bits";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public Buffer8bits(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        OUT = new int[8];
        for(int k = 0; k < 8; k++)
            OPin[k].setLevel(0x6c9d8);

        IPin[8].setLevel(5);
        IPin[9].setLevel(5);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Buffer8bits buffer8bits = new Buffer8bits(this, i, j);
        return buffer8bits;
    }

    public void SimulateLogic()
    {
        for(int i = 0; i < 8; i++)
            OUT[i] = IPin[i].getLevel();

        if(IPin[8].getLevel() == 0 || IPin[9].getLevel() == 0)
        {
            for(int j = 0; j < 8; j++)
                OPin[j].setLevel(0x6c9d8);

        } else
        {
            OPin[0].setLevel(OUT[0]);
            OPin[1].setLevel(OUT[1]);
            OPin[2].setLevel(OUT[2]);
            OPin[3].setLevel(OUT[3]);
            OPin[4].setLevel(OUT[4]);
            OPin[5].setLevel(OUT[5]);
            OPin[6].setLevel(OUT[6]);
            OPin[7].setLevel(OUT[7]);
        }
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFill(MyColor.red);
        if(IPin[0].PinDim.width > 0)
        {
            if(IPin[8].getLevel() == 5 && IPin[9].getLevel() == 5)
                DrawWithOffset.fillText(g,"\u2192", (l + 5) * k - 5, (i1 + 7) * k - 5);
        } else
        if(IPin[0].PinDim.height > 0)
        {
            if(IPin[8].getLevel() == 5 && IPin[9].getLevel() == 5)
                DrawWithOffset.fillText(g,"\u2193", (l + 8) * k + 2, (i1 + 4) * k - 5);
        } else
        if(IPin[0].PinDim.width < 0)
        {
            if(IPin[8].getLevel() == 5 && IPin[9].getLevel() == 5)
                DrawWithOffset.fillText(g,"\u2190", (l + 4) * k + 5, (i1 + 7) * k - 3);
        } else
        if(IPin[0].PinDim.height < 0 && IPin[8].getLevel() == 5 && IPin[9].getLevel() == 5)
            DrawWithOffset.fillText(g,"\u2191", (l + 7) * k + 2, (i1 + 4) * k - 5);
    }

    int OUT[];
}
