package digsim.components;

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

public class FourBitShifter extends IntegratedCircuit
{

    public FourBitShifter(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 20, 3, 1, 4, 9, 10, 4);
        Decal = 0;
        Validation = 0;
        OUT = new int[4];
        IPin[0] = new InputPin("0", 1, 2, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("1", 1, 3, 2, 0, 0, 0, 0);
        IPin[2] = new InputPin("2", 1, 4, 2, 0, 0, 0, 0);
        IPin[3] = new InputPin("3", 1, 5, 2, 0, 0, 0, 0);
        IPin[4] = new InputPin("4", 1, 6, 2, 0, 0, 0, 0);
        IPin[5] = new InputPin("5", 1, 7, 2, 0, 0, 0, 0);
        IPin[6] = new InputPin("6", 1, 8, 2, 0, 0, 0, 0);
        IPin[7] = new InputPin("a", 5, 12, 0, -2, 0, 0, 0);
        IPin[8] = new InputPin("b", 4, 12, 0, -2, 0, 0, 0);
        IPin[9] = new InputPin("OE", 6, 12, 0, -2, 0, 0, 1);
        OPin[0] = new OutputPin("0", 9, 4, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("1", 9, 5, -2, 0, 0, 0, 0);
        OPin[2] = new OutputPin("2", 9, 6, -2, 0, 0, 0, 0);
        OPin[3] = new OutputPin("3", 9, 7, -2, 0, 0, 0, 0);
        for(int k = 0; k < 4; k++)
            OPin[k].setLevel(0x6c9d8);

        IPin[9].setLevel(5);
        ComponentName = "Decaleur statique 4 bits a sorties 3S (74HC350)";
        ClassName = "FourBitShifter";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public FourBitShifter(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Decal = 0;
        Validation = 0;
        OUT = new int[4];
        for(int k = 0; k < 4; k++)
            OPin[k].setLevel(0x6c9d8);

        IPin[9].setLevel(5);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        FourBitShifter fourbitshifter = new FourBitShifter(this, i, j);
        return fourbitshifter;
    }

    public void SimulateLogic()
    {
        if(IPin[8].getLevel() == 0 && IPin[7].getLevel() == 0)
        {
            OUT[0] = IPin[0].getLevel();
            OUT[1] = IPin[1].getLevel();
            OUT[2] = IPin[2].getLevel();
            OUT[3] = IPin[3].getLevel();
            Decal = 0;
        } else
        if(IPin[8].getLevel() == 0 && IPin[7].getLevel() == 5)
        {
            OUT[0] = IPin[1].getLevel();
            OUT[1] = IPin[2].getLevel();
            OUT[2] = IPin[3].getLevel();
            OUT[3] = IPin[4].getLevel();
            Decal = 1;
        } else
        if(IPin[8].getLevel() == 5 && IPin[7].getLevel() == 0)
        {
            OUT[0] = IPin[2].getLevel();
            OUT[1] = IPin[3].getLevel();
            OUT[2] = IPin[4].getLevel();
            OUT[3] = IPin[5].getLevel();
            Decal = 2;
        } else
        if(IPin[8].getLevel() == 5 && IPin[7].getLevel() == 5)
        {
            OUT[0] = IPin[3].getLevel();
            OUT[1] = IPin[4].getLevel();
            OUT[2] = IPin[5].getLevel();
            OUT[3] = IPin[6].getLevel();
            Decal = 3;
        }
        if(IPin[9].getLevel() == 0)
        {
            Validation = 0;
            for(int i = 0; i < 4; i++)
                OPin[i].setLevel(0x6c9d8);

        } else
        {
            Validation = 5;
            OPin[0].setLevel(OUT[0]);
            OPin[1].setLevel(OUT[1]);
            OPin[2].setLevel(OUT[2]);
            OPin[3].setLevel(OUT[3]);
        }
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        if(IPin[0].PinDim.width > 0 && Validation == 5)
        {
            g.setStroke(MyColor.pink);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 2 + Decal) * k, (l + 7) * k - 1, (i1 + 4) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 3 + Decal) * k, (l + 7) * k - 1, (i1 + 5) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 4 + Decal) * k, (l + 7) * k - 1, (i1 + 6) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 5 + Decal) * k, (l + 7) * k - 1, (i1 + 7) * k);
            g.setFill(MyColor.red);
            if(IPin[9].getLevel() == 5)
                DrawWithOffset.fillText(g,"\u2192", (l + 5) * k - 5, (i1 + 2) * k);
        } else
        if(IPin[0].PinDim.height > 0 && Validation == 5)
        {
            g.setStroke(MyColor.pink);
            DrawWithOffset.strokeLine(g,((l + 11) - Decal) * k, (i1 + 1) * k + 1, (l + 9) * k, (i1 + 5) * k - 1);
            DrawWithOffset.strokeLine(g,((l + 10) - Decal) * k, (i1 + 1) * k + 1, (l + 8) * k, (i1 + 5) * k - 1);
            DrawWithOffset.strokeLine(g,((l + 9) - Decal) * k, (i1 + 1) * k + 1, (l + 7) * k, (i1 + 5) * k - 1);
            DrawWithOffset.strokeLine(g,((l + 8) - Decal) * k, (i1 + 1) * k + 1, (l + 6) * k, (i1 + 5) * k - 1);
            g.setFill(MyColor.red);
            if(IPin[9].getLevel() == 5)
                DrawWithOffset.fillText(g,"\u2193", (l + 11) * k + 2, (i1 + 4) * k - 5);
        } else
        if(IPin[0].PinDim.width < 0 && Validation == 5)
        {
            g.setStroke(MyColor.pink);
            DrawWithOffset.strokeLine(g,(l + 7) * k - 1, ((i1 + 9) - Decal) * k, (l + 3) * k + 1, (i1 + 7) * k);
            DrawWithOffset.strokeLine(g,(l + 7) * k - 1, ((i1 + 8) - Decal) * k, (l + 3) * k + 1, (i1 + 6) * k);
            DrawWithOffset.strokeLine(g,(l + 7) * k - 1, ((i1 + 7) - Decal) * k, (l + 3) * k + 1, (i1 + 5) * k);
            DrawWithOffset.strokeLine(g,(l + 7) * k - 1, ((i1 + 6) - Decal) * k, (l + 3) * k + 1, (i1 + 4) * k);
            g.setFill(MyColor.red);
            if(IPin[9].getLevel() == 5)
                DrawWithOffset.fillText(g,"\u2190", (l + 4) * k + 5, (i1 + 10) * k);
        } else
        if(IPin[0].PinDim.height < 0 && Validation == 5)
        {
            g.setStroke(MyColor.pink);
            DrawWithOffset.strokeLine(g,(l + 4 + Decal) * k, (i1 + 5) * k - 1, (l + 6) * k, (i1 + 1) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 5 + Decal) * k, (i1 + 5) * k - 1, (l + 7) * k, (i1 + 1) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 6 + Decal) * k, (i1 + 5) * k - 1, (l + 8) * k, (i1 + 1) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 7 + Decal) * k, (i1 + 5) * k - 1, (l + 9) * k, (i1 + 1) * k + 1);
            g.setFill(MyColor.red);
            if(IPin[9].getLevel() == 5)
                DrawWithOffset.fillText(g,"\u2191", (l + 3) * k + 2, (i1 + 4) * k - 5);
        }
    }

    int Decal;
    int OUT[];
    int Validation;
}
