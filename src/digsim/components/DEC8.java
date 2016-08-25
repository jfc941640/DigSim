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

public class DEC8 extends IntegratedCircuit
{

    public DEC8(Pin apin[][], int i, int j)
    {
        super(i, j, 11, 32, 3, 1, 5, 21, 24, 8);
        Decal = 0;
        Validation = 0;
        OUT = new int[8];
        IPin[0] = new InputPin("0", 1, 2, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("1", 1, 3, 2, 0, 0, 0, 0);
        IPin[2] = new InputPin("2", 1, 4, 2, 0, 0, 0, 0);
        IPin[3] = new InputPin("3", 1, 5, 2, 0, 0, 0, 0);
        IPin[4] = new InputPin("4", 1, 6, 2, 0, 0, 0, 0);
        IPin[5] = new InputPin("5", 1, 7, 2, 0, 0, 0, 0);
        IPin[6] = new InputPin("6", 1, 8, 2, 0, 0, 0, 0);
        IPin[7] = new InputPin("7", 1, 9, 2, 0, 0, 0, 0);
        IPin[8] = new InputPin("8", 1, 10, 2, 0, 0, 0, 0);
        IPin[9] = new InputPin("9", 1, 11, 2, 0, 0, 0, 0);
        IPin[10] = new InputPin("10", 1, 12, 2, 0, 0, 0, 0);
        IPin[11] = new InputPin("11", 1, 13, 2, 0, 0, 0, 0);
        IPin[12] = new InputPin("12", 1, 14, 2, 0, 0, 0, 0);
        IPin[13] = new InputPin("13", 1, 15, 2, 0, 0, 0, 0);
        IPin[14] = new InputPin("14", 1, 16, 2, 0, 0, 0, 0);
        IPin[15] = new InputPin("15", 1, 17, 2, 0, 0, 0, 0);
        IPin[16] = new InputPin("16", 1, 18, 2, 0, 0, 0, 0);
        IPin[17] = new InputPin("17", 1, 19, 2, 0, 0, 0, 0);
        IPin[18] = new InputPin("18", 1, 20, 2, 0, 0, 0, 0);
        IPin[19] = new InputPin("OE", 10, 20, -2, 0, 0, 0, 1);
        IPin[20] = new InputPin("a", 7, 24, 0, -2, 0, 0, 0);
        IPin[21] = new InputPin("b", 6, 24, 0, -2, 0, 0, 0);
        IPin[22] = new InputPin("c", 5, 24, 0, -2, 0, 0, 0);
        IPin[23] = new InputPin("d", 4, 24, 0, -2, 0, 0, 0);
        OPin[0] = new OutputPin("0", 10, 8, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("1", 10, 9, -2, 0, 0, 0, 0);
        OPin[2] = new OutputPin("2", 10, 10, -2, 0, 0, 0, 0);
        OPin[3] = new OutputPin("3", 10, 11, -2, 0, 0, 0, 0);
        OPin[4] = new OutputPin("4", 10, 12, -2, 0, 0, 0, 0);
        OPin[5] = new OutputPin("5", 10, 13, -2, 0, 0, 0, 0);
        OPin[6] = new OutputPin("6", 10, 14, -2, 0, 0, 0, 0);
        OPin[7] = new OutputPin("7", 10, 15, -2, 0, 0, 0, 0);
        for(int k = 0; k < 8; k++)
            OPin[k].setLevel(0x6c9d8);

        IPin[19].setLevel(5);
        ComponentName = "Decaleur statique 19 vers 8 bits (sorties 3S)";
        ClassName = "DEC8";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public DEC8(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Decal = 0;
        Validation = 0;
        OUT = new int[8];
        for(int k = 0; k < 8; k++)
            OPin[k].setLevel(0x6c9d8);

        IPin[19].setLevel(5);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        DEC8 dec8 = new DEC8(this, i, j);
        return dec8;
    }

    public void SimulateLogic()
    {
        if(IPin[23].getLevel() == 0 && IPin[22].getLevel() == 0 && IPin[21].getLevel() == 0 && IPin[20].getLevel() == 0)
        {
            Decal = 0;
            OUT[0] = IPin[0 + Decal].getLevel();
            OUT[1] = IPin[1 + Decal].getLevel();
            OUT[2] = IPin[2 + Decal].getLevel();
            OUT[3] = IPin[3 + Decal].getLevel();
            OUT[4] = IPin[4 + Decal].getLevel();
            OUT[5] = IPin[5 + Decal].getLevel();
            OUT[6] = IPin[6 + Decal].getLevel();
            OUT[7] = IPin[7 + Decal].getLevel();
        } else
        if(IPin[23].getLevel() == 0 && IPin[22].getLevel() == 0 && IPin[21].getLevel() == 0 && IPin[20].getLevel() == 5)
        {
            Decal = 1;
            OUT[0] = IPin[0 + Decal].getLevel();
            OUT[1] = IPin[1 + Decal].getLevel();
            OUT[2] = IPin[2 + Decal].getLevel();
            OUT[3] = IPin[3 + Decal].getLevel();
            OUT[4] = IPin[4 + Decal].getLevel();
            OUT[5] = IPin[5 + Decal].getLevel();
            OUT[6] = IPin[6 + Decal].getLevel();
            OUT[7] = IPin[7 + Decal].getLevel();
        } else
        if(IPin[23].getLevel() == 0 && IPin[22].getLevel() == 0 && IPin[21].getLevel() == 5 && IPin[20].getLevel() == 0)
        {
            Decal = 2;
            OUT[0] = IPin[0 + Decal].getLevel();
            OUT[1] = IPin[1 + Decal].getLevel();
            OUT[2] = IPin[2 + Decal].getLevel();
            OUT[3] = IPin[3 + Decal].getLevel();
            OUT[4] = IPin[4 + Decal].getLevel();
            OUT[5] = IPin[5 + Decal].getLevel();
            OUT[6] = IPin[6 + Decal].getLevel();
            OUT[7] = IPin[7 + Decal].getLevel();
        } else
        if(IPin[23].getLevel() == 0 && IPin[22].getLevel() == 0 && IPin[21].getLevel() == 5 && IPin[20].getLevel() == 5)
        {
            Decal = 3;
            OUT[0] = IPin[0 + Decal].getLevel();
            OUT[1] = IPin[1 + Decal].getLevel();
            OUT[2] = IPin[2 + Decal].getLevel();
            OUT[3] = IPin[3 + Decal].getLevel();
            OUT[4] = IPin[4 + Decal].getLevel();
            OUT[5] = IPin[5 + Decal].getLevel();
            OUT[6] = IPin[6 + Decal].getLevel();
            OUT[7] = IPin[7 + Decal].getLevel();
        } else
        if(IPin[23].getLevel() == 0 && IPin[22].getLevel() == 5 && IPin[21].getLevel() == 0 && IPin[20].getLevel() == 0)
        {
            Decal = 4;
            OUT[0] = IPin[0 + Decal].getLevel();
            OUT[1] = IPin[1 + Decal].getLevel();
            OUT[2] = IPin[2 + Decal].getLevel();
            OUT[3] = IPin[3 + Decal].getLevel();
            OUT[4] = IPin[4 + Decal].getLevel();
            OUT[5] = IPin[5 + Decal].getLevel();
            OUT[6] = IPin[6 + Decal].getLevel();
            OUT[7] = IPin[7 + Decal].getLevel();
        } else
        if(IPin[23].getLevel() == 0 && IPin[22].getLevel() == 5 && IPin[21].getLevel() == 0 && IPin[20].getLevel() == 5)
        {
            Decal = 5;
            OUT[0] = IPin[0 + Decal].getLevel();
            OUT[1] = IPin[1 + Decal].getLevel();
            OUT[2] = IPin[2 + Decal].getLevel();
            OUT[3] = IPin[3 + Decal].getLevel();
            OUT[4] = IPin[4 + Decal].getLevel();
            OUT[5] = IPin[5 + Decal].getLevel();
            OUT[6] = IPin[6 + Decal].getLevel();
            OUT[7] = IPin[7 + Decal].getLevel();
        } else
        if(IPin[23].getLevel() == 0 && IPin[22].getLevel() == 5 && IPin[21].getLevel() == 5 && IPin[20].getLevel() == 0)
        {
            Decal = 6;
            OUT[0] = IPin[0 + Decal].getLevel();
            OUT[1] = IPin[1 + Decal].getLevel();
            OUT[2] = IPin[2 + Decal].getLevel();
            OUT[3] = IPin[3 + Decal].getLevel();
            OUT[4] = IPin[4 + Decal].getLevel();
            OUT[5] = IPin[5 + Decal].getLevel();
            OUT[6] = IPin[6 + Decal].getLevel();
            OUT[7] = IPin[7 + Decal].getLevel();
        } else
        if(IPin[23].getLevel() == 0 && IPin[22].getLevel() == 5 && IPin[21].getLevel() == 5 && IPin[20].getLevel() == 5)
        {
            Decal = 7;
            OUT[0] = IPin[0 + Decal].getLevel();
            OUT[1] = IPin[1 + Decal].getLevel();
            OUT[2] = IPin[2 + Decal].getLevel();
            OUT[3] = IPin[3 + Decal].getLevel();
            OUT[4] = IPin[4 + Decal].getLevel();
            OUT[5] = IPin[5 + Decal].getLevel();
            OUT[6] = IPin[6 + Decal].getLevel();
            OUT[7] = IPin[7 + Decal].getLevel();
        } else
        if(IPin[23].getLevel() == 5 && IPin[22].getLevel() == 0 && IPin[21].getLevel() == 0 && IPin[20].getLevel() == 0)
        {
            Decal = 8;
            OUT[0] = IPin[0 + Decal].getLevel();
            OUT[1] = IPin[1 + Decal].getLevel();
            OUT[2] = IPin[2 + Decal].getLevel();
            OUT[3] = IPin[3 + Decal].getLevel();
            OUT[4] = IPin[4 + Decal].getLevel();
            OUT[5] = IPin[5 + Decal].getLevel();
            OUT[6] = IPin[6 + Decal].getLevel();
            OUT[7] = IPin[7 + Decal].getLevel();
        } else
        if(IPin[23].getLevel() == 5 && IPin[22].getLevel() == 0 && IPin[21].getLevel() == 0 && IPin[20].getLevel() == 5)
        {
            Decal = 9;
            OUT[0] = IPin[0 + Decal].getLevel();
            OUT[1] = IPin[1 + Decal].getLevel();
            OUT[2] = IPin[2 + Decal].getLevel();
            OUT[3] = IPin[3 + Decal].getLevel();
            OUT[4] = IPin[4 + Decal].getLevel();
            OUT[5] = IPin[5 + Decal].getLevel();
            OUT[6] = IPin[6 + Decal].getLevel();
            OUT[7] = IPin[7 + Decal].getLevel();
        } else
        if(IPin[23].getLevel() == 5 && IPin[22].getLevel() == 0 && IPin[21].getLevel() == 5 && IPin[20].getLevel() == 0)
        {
            Decal = 10;
            OUT[0] = IPin[0 + Decal].getLevel();
            OUT[1] = IPin[1 + Decal].getLevel();
            OUT[2] = IPin[2 + Decal].getLevel();
            OUT[3] = IPin[3 + Decal].getLevel();
            OUT[4] = IPin[4 + Decal].getLevel();
            OUT[5] = IPin[5 + Decal].getLevel();
            OUT[6] = IPin[6 + Decal].getLevel();
            OUT[7] = IPin[7 + Decal].getLevel();
        } else
        {
            Decal = 11;
            OUT[0] = IPin[0 + Decal].getLevel();
            OUT[1] = IPin[1 + Decal].getLevel();
            OUT[2] = IPin[2 + Decal].getLevel();
            OUT[3] = IPin[3 + Decal].getLevel();
            OUT[4] = IPin[4 + Decal].getLevel();
            OUT[5] = IPin[5 + Decal].getLevel();
            OUT[6] = IPin[6 + Decal].getLevel();
            OUT[7] = IPin[7 + Decal].getLevel();
        }
        if(IPin[19].getLevel() == 0)
        {
            Validation = 0;
            for(int i = 0; i < 8; i++)
                OPin[i].setLevel(0x6c9d8);

        } else
        {
            Validation = 5;
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
        DrawInputPins(g, l, i1, k);
        if(IPin[0].PinDim.width > 0 && Validation == 5)
        {
            g.setStroke(MyColor.pink);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 2 + Decal) * k, (l + 8) * k - 1, (i1 + 8) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 3 + Decal) * k, (l + 8) * k - 1, (i1 + 9) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 4 + Decal) * k, (l + 8) * k - 1, (i1 + 10) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 5 + Decal) * k, (l + 8) * k - 1, (i1 + 11) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 6 + Decal) * k, (l + 8) * k - 1, (i1 + 12) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 7 + Decal) * k, (l + 8) * k - 1, (i1 + 13) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 8 + Decal) * k, (l + 8) * k - 1, (i1 + 14) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 9 + Decal) * k, (l + 8) * k - 1, (i1 + 15) * k);
            g.setFill(MyColor.red);
            if(IPin[9].getLevel() == 5)
                DrawWithOffset.fillText(g,"\u2192", (l + 5) * k - 5, (i1 + 2) * k);
        } else
        if(IPin[0].PinDim.height > 0 && Validation == 5)
        {
            g.setStroke(MyColor.pink);
            DrawWithOffset.strokeLine(g,((l + 23) - Decal) * k, (i1 + 1) * k + 1, (l + 17) * k, (i1 + 6) * k - 1);
            DrawWithOffset.strokeLine(g,((l + 22) - Decal) * k, (i1 + 1) * k + 1, (l + 16) * k, (i1 + 6) * k - 1);
            DrawWithOffset.strokeLine(g,((l + 21) - Decal) * k, (i1 + 1) * k + 1, (l + 15) * k, (i1 + 6) * k - 1);
            DrawWithOffset.strokeLine(g,((l + 20) - Decal) * k, (i1 + 1) * k + 1, (l + 14) * k, (i1 + 6) * k - 1);
            DrawWithOffset.strokeLine(g,((l + 19) - Decal) * k, (i1 + 1) * k + 1, (l + 13) * k, (i1 + 6) * k - 1);
            DrawWithOffset.strokeLine(g,((l + 18) - Decal) * k, (i1 + 1) * k + 1, (l + 12) * k, (i1 + 6) * k - 1);
            DrawWithOffset.strokeLine(g,((l + 17) - Decal) * k, (i1 + 1) * k + 1, (l + 11) * k, (i1 + 6) * k - 1);
            DrawWithOffset.strokeLine(g,((l + 16) - Decal) * k, (i1 + 1) * k + 1, (l + 10) * k, (i1 + 6) * k - 1);
            g.setFill(MyColor.red);
            if(IPin[9].getLevel() == 5)
                DrawWithOffset.fillText(g,"\u2193", (l + 11) * k + 2, (i1 + 4) * k - 5);
        } else
        if(IPin[0].PinDim.width < 0 && Validation == 5)
        {
            g.setStroke(MyColor.pink);
            DrawWithOffset.strokeLine(g,(l + 8) * k - 1, ((i1 + 21) - Decal) * k, (l + 3) * k + 1, (i1 + 15) * k);
            DrawWithOffset.strokeLine(g,(l + 8) * k - 1, ((i1 + 20) - Decal) * k, (l + 3) * k + 1, (i1 + 14) * k);
            DrawWithOffset.strokeLine(g,(l + 8) * k - 1, ((i1 + 19) - Decal) * k, (l + 3) * k + 1, (i1 + 13) * k);
            DrawWithOffset.strokeLine(g,(l + 8) * k - 1, ((i1 + 18) - Decal) * k, (l + 3) * k + 1, (i1 + 12) * k);
            DrawWithOffset.strokeLine(g,(l + 8) * k - 1, ((i1 + 17) - Decal) * k, (l + 3) * k + 1, (i1 + 11) * k);
            DrawWithOffset.strokeLine(g,(l + 8) * k - 1, ((i1 + 16) - Decal) * k, (l + 3) * k + 1, (i1 + 10) * k);
            DrawWithOffset.strokeLine(g,(l + 8) * k - 1, ((i1 + 15) - Decal) * k, (l + 3) * k + 1, (i1 + 9) * k);
            DrawWithOffset.strokeLine(g,(l + 8) * k - 1, ((i1 + 14) - Decal) * k, (l + 3) * k + 1, (i1 + 8) * k);
            g.setFill(MyColor.red);
            if(IPin[9].getLevel() == 5)
                DrawWithOffset.fillText(g,"\u2190", (l + 4) * k + 5, (i1 + 10) * k);
        } else
        if(IPin[0].PinDim.height < 0 && Validation == 5)
        {
            g.setStroke(MyColor.pink);
            DrawWithOffset.strokeLine(g,(l + 4 + Decal) * k, (i1 + 6) * k - 1, (l + 10) * k, (i1 + 1) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 5 + Decal) * k, (i1 + 6) * k - 1, (l + 11) * k, (i1 + 1) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 6 + Decal) * k, (i1 + 6) * k - 1, (l + 12) * k, (i1 + 1) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 7 + Decal) * k, (i1 + 6) * k - 1, (l + 13) * k, (i1 + 1) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 8 + Decal) * k, (i1 + 6) * k - 1, (l + 14) * k, (i1 + 1) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 9 + Decal) * k, (i1 + 6) * k - 1, (l + 15) * k, (i1 + 1) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 10 + Decal) * k, (i1 + 6) * k - 1, (l + 16) * k, (i1 + 1) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 11 + Decal) * k, (i1 + 6) * k - 1, (l + 17) * k, (i1 + 1) * k + 1);
            g.setFill(MyColor.red);
            if(IPin[9].getLevel() == 5)
                DrawWithOffset.fillText(g,"\u2191", (l + 3) * k + 2, (i1 + 4) * k - 5);
        }
    }

    int Decal;
    int OUT[];
    int Validation;
}
