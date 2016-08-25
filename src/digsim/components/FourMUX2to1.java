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

public class FourMUX2to1 extends IntegratedCircuit
{

    public FourMUX2to1(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 21, 3, 1, 4, 10, 10, 4);
        Decal = 0;
        Validation = 0;
        IPin[0] = new InputPin("0", 1, 2, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("0", 1, 3, 2, 0, 0, 0, 0);
        IPin[2] = new InputPin("0", 1, 4, 2, 0, 0, 0, 0);
        IPin[3] = new InputPin("0", 1, 5, 2, 0, 0, 0, 0);
        IPin[4] = new InputPin("1", 1, 7, 2, 0, 0, 0, 0);
        IPin[5] = new InputPin("1", 1, 8, 2, 0, 0, 0, 0);
        IPin[6] = new InputPin("1", 1, 9, 2, 0, 0, 0, 0);
        IPin[7] = new InputPin("1", 1, 10, 2, 0, 0, 0, 0);
        IPin[8] = new InputPin("a", 5, 13, 0, -2, 0, 0, 0);
        IPin[9] = new InputPin("G", 6, 13, 0, -2, 0, 0, 1);
        OPin[0] = new OutputPin("Y", 9, 5, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("Y", 9, 6, -2, 0, 0, 0, 0);
        OPin[2] = new OutputPin("Y", 9, 7, -2, 0, 0, 0, 0);
        OPin[3] = new OutputPin("Y", 9, 8, -2, 0, 0, 0, 0);
        ComponentName = "4 multiplexeurs 2 vers 1 (74HC157)";
        ClassName = "FourMUX2to1";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public FourMUX2to1(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Decal = 0;
        Validation = 0;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        FourMUX2to1 fourmux2to1 = new FourMUX2to1(this, i, j);
        return fourmux2to1;
    }

    public void SimulateLogic()
    {
        if(IPin[9].getLevel() == 0)
        {
            OPin[0].Level = 0;
            OPin[1].Level = 0;
            OPin[2].Level = 0;
            OPin[3].Level = 0;
            Validation = 0;
        } else
        if(IPin[8].getLevel() == 0 && IPin[9].getLevel() == 5)
        {
            OPin[0].Level = IPin[0].getLevel();
            OPin[1].Level = IPin[1].getLevel();
            OPin[2].Level = IPin[2].getLevel();
            OPin[3].Level = IPin[3].getLevel();
            Decal = 0;
            Validation = 5;
        } else
        if(IPin[8].getLevel() == 5 && IPin[9].getLevel() == 5)
        {
            OPin[0].Level = IPin[4].getLevel();
            OPin[1].Level = IPin[5].getLevel();
            OPin[2].Level = IPin[6].getLevel();
            OPin[3].Level = IPin[7].getLevel();
            Decal = 5;
            Validation = 5;
        }
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        if(IPin[9].getLevel() == 5)
        {
            g.setStroke(MyColor.pink);
            if(IPin[0].PinDim.width > 0 && Validation == 5)
            {
                DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 2 + Decal) * k, (l + 7) * k - 1, (i1 + 5) * k);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 3 + Decal) * k, (l + 7) * k - 1, (i1 + 6) * k);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 4 + Decal) * k, (l + 7) * k - 1, (i1 + 7) * k);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 5 + Decal) * k, (l + 7) * k - 1, (i1 + 8) * k);
            } else
            if(IPin[0].PinDim.height > 0 && Validation == 5)
            {
                DrawWithOffset.strokeLine(g,((l + 12) - Decal) * k, (i1 + 1) * k + 1, (l + 9) * k, (i1 + 5) * k - 1);
                DrawWithOffset.strokeLine(g,((l + 11) - Decal) * k, (i1 + 1) * k + 1, (l + 8) * k, (i1 + 5) * k - 1);
                DrawWithOffset.strokeLine(g,((l + 10) - Decal) * k, (i1 + 1) * k + 1, (l + 7) * k, (i1 + 5) * k - 1);
                DrawWithOffset.strokeLine(g,((l + 9) - Decal) * k, (i1 + 1) * k + 1, (l + 6) * k, (i1 + 5) * k - 1);
            } else
            if(IPin[0].PinDim.width < 0 && Validation == 5)
            {
                DrawWithOffset.strokeLine(g,(l + 7) * k - 1, ((i1 + 10) - Decal) * k, (l + 3) * k + 1, (i1 + 7) * k);
                DrawWithOffset.strokeLine(g,(l + 7) * k - 1, ((i1 + 9) - Decal) * k, (l + 3) * k + 1, (i1 + 6) * k);
                DrawWithOffset.strokeLine(g,(l + 7) * k - 1, ((i1 + 8) - Decal) * k, (l + 3) * k + 1, (i1 + 5) * k);
                DrawWithOffset.strokeLine(g,(l + 7) * k - 1, ((i1 + 7) - Decal) * k, (l + 3) * k + 1, (i1 + 4) * k);
            } else
            if(IPin[0].PinDim.height < 0 && Validation == 5)
            {
                DrawWithOffset.strokeLine(g,(l + 4 + Decal) * k, (i1 + 5) * k - 1, (l + 7) * k, (i1 + 1) * k + 1);
                DrawWithOffset.strokeLine(g,(l + 5 + Decal) * k, (i1 + 5) * k - 1, (l + 8) * k, (i1 + 1) * k + 1);
                DrawWithOffset.strokeLine(g,(l + 6 + Decal) * k, (i1 + 5) * k - 1, (l + 9) * k, (i1 + 1) * k + 1);
                DrawWithOffset.strokeLine(g,(l + 7 + Decal) * k, (i1 + 5) * k - 1, (l + 10) * k, (i1 + 1) * k + 1);
            }
        }
    }

    int Decal;
    int Validation;
}
