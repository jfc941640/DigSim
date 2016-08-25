package digsim.components;


import java.text.DecimalFormat;

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

public class MAC8x8 extends IntegratedCircuit
{

    public MAC8x8(Pin apin[][], int i, int j)
    {
        super(i, j, 12, 14, 3, 1, 10, 25, 45, 19);
        InX = 0;
        InY = 0;
        Prod = 0;
        Acc = 0;
        Sub = 0;
        Rnd = 0;
        df1 = new DecimalFormat("#0.00");
        df2 = new DecimalFormat("#0.0000");
        IPin[0] = new InputPin("0", 1, 2, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("1", 1, 3, 2, 0, 0, 0, 0);
        IPin[2] = new InputPin("2", 1, 4, 2, 0, 0, 0, 0);
        IPin[3] = new InputPin("3", 1, 5, 2, 0, 0, 0, 0);
        IPin[4] = new InputPin("4", 1, 6, 2, 0, 0, 0, 0);
        IPin[5] = new InputPin("5", 1, 7, 2, 0, 0, 0, 0);
        IPin[6] = new InputPin("6", 1, 8, 2, 0, 0, 0, 0);
        IPin[7] = new InputPin("7", 1, 9, 2, 0, 0, 0, 0);
        IPin[8] = new InputPin("0", 1, 14, 2, 0, 0, 0, 0);
        IPin[9] = new InputPin("1", 1, 15, 2, 0, 0, 0, 0);
        IPin[10] = new InputPin("2", 1, 16, 2, 0, 0, 0, 0);
        IPin[11] = new InputPin("3", 1, 17, 2, 0, 0, 0, 0);
        IPin[12] = new InputPin("4", 1, 18, 2, 0, 0, 0, 0);
        IPin[13] = new InputPin("5", 1, 19, 2, 0, 0, 0, 0);
        IPin[14] = new InputPin("6", 1, 20, 2, 0, 0, 0, 0);
        IPin[15] = new InputPin("7", 1, 21, 2, 0, 0, 0, 0);
        IPin[16] = new InputPin("X", 1, 11, 2, 0, 0, 0, 4);
        IPin[17] = new InputPin("Y", 1, 12, 2, 0, 0, 0, 4);
        IPin[18] = new InputPin("Z", 15, 24, -2, 0, 0, 0, 4);
        IPin[19] = new InputPin("+/-", 4, 28, 0, -2, 0, 0, 0);
        IPin[20] = new InputPin("Acc", 6, 28, 0, -2, 0, 0, 0);
        IPin[21] = new InputPin("Prl", 10, 28, 0, -2, 0, 0, 0);
        IPin[22] = new InputPin("r", 12, 28, 0, -2, 0, 0, 1);
        IPin[23] = new InputPin("OE", 15, 22, -2, 0, 0, 0, 1);
        IPin[43] = new InputPin("Rnd", 8, 28, 0, -2, 0, 0, 0);
        IPin[44] = new InputPin("a", 8, 0, 0, 1, 0, 0, 0);
        IPin[24] = new InputPin("", 15, 2, -2, 0, 0, 0, 0);
        IPin[25] = new InputPin("", 15, 3, -2, 0, 0, 0, 0);
        IPin[26] = new InputPin("", 15, 4, -2, 0, 0, 0, 0);
        IPin[27] = new InputPin("", 15, 5, -2, 0, 0, 0, 0);
        IPin[28] = new InputPin("", 15, 6, -2, 0, 0, 0, 0);
        IPin[29] = new InputPin("", 15, 7, -2, 0, 0, 0, 0);
        IPin[30] = new InputPin("", 15, 8, -2, 0, 0, 0, 0);
        IPin[31] = new InputPin("", 15, 9, -2, 0, 0, 0, 0);
        IPin[32] = new InputPin("", 15, 10, -2, 0, 0, 0, 0);
        IPin[33] = new InputPin("", 15, 11, -2, 0, 0, 0, 0);
        IPin[34] = new InputPin("", 15, 12, -2, 0, 0, 0, 0);
        IPin[35] = new InputPin("", 15, 13, -2, 0, 0, 0, 0);
        IPin[36] = new InputPin("", 15, 14, -2, 0, 0, 0, 0);
        IPin[37] = new InputPin("", 15, 15, -2, 0, 0, 0, 0);
        IPin[38] = new InputPin("", 15, 16, -2, 0, 0, 0, 0);
        IPin[39] = new InputPin("", 15, 17, -2, 0, 0, 0, 0);
        IPin[40] = new InputPin("", 15, 18, -2, 0, 0, 0, 0);
        IPin[41] = new InputPin("", 15, 19, -2, 0, 0, 0, 0);
        IPin[42] = new InputPin("", 15, 20, -2, 0, 0, 0, 0);
        OPin[0] = new OutputPin("0", 15, 2, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("1", 15, 3, -2, 0, 0, 0, 0);
        OPin[2] = new OutputPin("2", 15, 4, -2, 0, 0, 0, 0);
        OPin[3] = new OutputPin("3", 15, 5, -2, 0, 0, 0, 0);
        OPin[4] = new OutputPin("4", 15, 6, -2, 0, 0, 0, 0);
        OPin[5] = new OutputPin("5", 15, 7, -2, 0, 0, 0, 0);
        OPin[6] = new OutputPin("6", 15, 8, -2, 0, 0, 0, 0);
        OPin[7] = new OutputPin("7", 15, 9, -2, 0, 0, 0, 0);
        OPin[8] = new OutputPin("8", 15, 10, -2, 0, 0, 0, 0);
        OPin[9] = new OutputPin("9", 15, 11, -2, 0, 0, 0, 0);
        OPin[10] = new OutputPin("10", 15, 12, -2, 0, 0, 0, 0);
        OPin[11] = new OutputPin("11", 15, 13, -2, 0, 0, 0, 0);
        OPin[12] = new OutputPin("12", 15, 14, -2, 0, 0, 0, 0);
        OPin[13] = new OutputPin("13", 15, 15, -2, 0, 0, 0, 0);
        OPin[14] = new OutputPin("14", 15, 16, -2, 0, 0, 0, 0);
        OPin[15] = new OutputPin("15", 15, 17, -2, 0, 0, 0, 0);
        OPin[16] = new OutputPin("16", 15, 18, -2, 0, 0, 0, 0);
        OPin[17] = new OutputPin("17", 15, 19, -2, 0, 0, 0, 0);
        OPin[18] = new OutputPin("18", 15, 20, -2, 0, 0, 0, 0);
        for(int k = 0; k < 19; k++)
            OPin[k].setLevel(0x6c9d8);

        for(int l = 0; l < 45; l++)
            IPin[l].setLevel(-1);

        IPin[23].setLevel(5);
        ComponentName = "Multiplieur-ACcumulateur 8 bits x 8 bits (sorties 3S)";
        ClassName = "MAC8x8";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public MAC8x8(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        InX = 0;
        InY = 0;
        Prod = 0;
        Acc = 0;
        Sub = 0;
        Rnd = 0;
        df1 = new DecimalFormat("#0.00");
        df2 = new DecimalFormat("#0.0000");
        for(int k = 0; k < 19; k++)
            OPin[k].setLevel(0x6c9d8);

        for(int l = 0; l < 45; l++)
            IPin[l].setLevel(-1);

        IPin[23].setLevel(5);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        MAC8x8 mac8x8 = new MAC8x8(this, i, j);
        return mac8x8;
    }

    public void SimulateLogic()
    {
        if(IPin[16].OldLevel == 0 && IPin[16].getLevel() == 5)
        {
            for(int i = 0; i < 8; i++)
                MemX[i] = IPin[i].getLevel();

            InX = 0;
            if(MemX[0] == 5)
                InX++;
            if(MemX[1] == 5)
                InX += 2;
            if(MemX[2] == 5)
                InX += 4;
            if(MemX[3] == 5)
                InX += 8;
            if(MemX[4] == 5)
                InX += 16;
            if(MemX[5] == 5)
                InX += 32;
            if(MemX[6] == 5)
                InX += 64;
            if(MemX[7] == 5)
                InX -= 128;
        }
        if(IPin[17].OldLevel == 0 && IPin[17].getLevel() == 5)
        {
            for(int j = 0; j < 8; j++)
                MemY[j] = IPin[j + 8].getLevel();

            InY = 0;
            if(MemY[0] == 5)
                InY++;
            if(MemY[1] == 5)
                InY += 2;
            if(MemY[2] == 5)
                InY += 4;
            if(MemY[3] == 5)
                InY += 8;
            if(MemY[4] == 5)
                InY += 16;
            if(MemY[5] == 5)
                InY += 32;
            if(MemY[6] == 5)
                InY += 64;
            if(MemY[7] == 5)
                InY -= 128;
        }
        if(IPin[16].OldLevel == 0 && IPin[16].getLevel() == 5 || IPin[17].OldLevel == 0 && IPin[17].getLevel() == 5)
        {
            Acc = IPin[20].getLevel();
            Sub = IPin[19].getLevel();
        }
        if(IPin[18].OldLevel == 0 && IPin[18].getLevel() == 5 && IPin[21].getLevel() == 5 && IPin[23].getLevel() == 0)
        {
            for(int k = 0; k < 19; k++)
                MemZ[k] = IPin[k + 24].getLevel();

            MemP = 0;
            if(MemZ[0] == 5)
                MemP++;
            if(MemZ[1] == 5)
                MemP += 2;
            if(MemZ[2] == 5)
                MemP += 4;
            if(MemZ[3] == 5)
                MemP += 8;
            if(MemZ[4] == 5)
                MemP += 16;
            if(MemZ[5] == 5)
                MemP += 32;
            if(MemZ[6] == 5)
                MemP += 64;
            if(MemZ[7] == 5)
                MemP += 128;
            if(MemZ[8] == 5)
                MemP += 256;
            if(MemZ[9] == 5)
                MemP += 512;
            if(MemZ[10] == 5)
                MemP += 1024;
            if(MemZ[11] == 5)
                MemP += 2048;
            if(MemZ[12] == 5)
                MemP += 4096;
            if(MemZ[13] == 5)
                MemP += 8192;
            if(MemZ[14] == 5)
                MemP += 16384;
            if(MemZ[15] == 5)
                MemP += 32768;
            if(MemZ[16] == 5)
                MemP += 0x10000;
            if(MemZ[17] == 5)
                MemP += 0x20000;
            if(MemZ[18] == 5)
                MemP -= 0x40000;
        }
        Prod = InX * InY;
        if(IPin[22].getLevel() == 5)
            MemP = 0;
        if(IPin[18].OldLevel == 0 && IPin[18].getLevel() == 5 && IPin[21].getLevel() == 0 && IPin[22].getLevel() == 0)
            if(Acc == 5 && Sub == 5)
                MemP = Prod + MemP;
            else
            if(Acc == 5 && Sub == 0)
                MemP = Prod - MemP;
            else
            if(Acc == 0)
                MemP = Prod;
        if(MemP < 0xfffc0000)
            MemP = 0xfffc0000;
        if(MemP > 0x3ffff)
            MemP = 0x3ffff;
        OutZ = MemP;
        if(IPin[43].getLevel() == 5)
            OutZ = 256 * (int)Math.round((1.0D * (double)OutZ) / 256D);
        if(IPin[23].getLevel() == 5)
        {
            if((OutZ & 1) == 1)
                OPin[0].setLevel(5);
            else
                OPin[0].setLevel(0);
            if((OutZ & 2) == 2)
                OPin[1].setLevel(5);
            else
                OPin[1].setLevel(0);
            if((OutZ & 4) == 4)
                OPin[2].setLevel(5);
            else
                OPin[2].setLevel(0);
            if((OutZ & 8) == 8)
                OPin[3].setLevel(5);
            else
                OPin[3].setLevel(0);
            if((OutZ & 0x10) == 16)
                OPin[4].setLevel(5);
            else
                OPin[4].setLevel(0);
            if((OutZ & 0x20) == 32)
                OPin[5].setLevel(5);
            else
                OPin[5].setLevel(0);
            if((OutZ & 0x40) == 64)
                OPin[6].setLevel(5);
            else
                OPin[6].setLevel(0);
            if((OutZ & 0x80) == 128)
                OPin[7].setLevel(5);
            else
                OPin[7].setLevel(0);
            if((OutZ & 0x100) == 256)
                OPin[8].setLevel(5);
            else
                OPin[8].setLevel(0);
            if((OutZ & 0x200) == 512)
                OPin[9].setLevel(5);
            else
                OPin[9].setLevel(0);
            if((OutZ & 0x400) == 1024)
                OPin[10].setLevel(5);
            else
                OPin[10].setLevel(0);
            if((OutZ & 0x800) == 2048)
                OPin[11].setLevel(5);
            else
                OPin[11].setLevel(0);
            if((OutZ & 0x1000) == 4096)
                OPin[12].setLevel(5);
            else
                OPin[12].setLevel(0);
            if((OutZ & 0x2000) == 8192)
                OPin[13].setLevel(5);
            else
                OPin[13].setLevel(0);
            if((OutZ & 0x4000) == 16384)
                OPin[14].setLevel(5);
            else
                OPin[14].setLevel(0);
            if((OutZ & 0x8000) == 32768)
                OPin[15].setLevel(5);
            else
                OPin[15].setLevel(0);
            if((OutZ & 0x10000) == 0x10000)
                OPin[16].setLevel(5);
            else
                OPin[16].setLevel(0);
            if((OutZ & 0x20000) == 0x20000)
                OPin[17].setLevel(5);
            else
                OPin[17].setLevel(0);
            if((OutZ & 0x40000) == 0x40000)
                OPin[18].setLevel(5);
            else
                OPin[18].setLevel(0);
        } else
        {
            for(int l = 0; l < 19; l++)
                OPin[l].setLevel(0x6c9d8);

        }
        IPin[16].OldLevel = IPin[16].getLevel();
        IPin[17].OldLevel = IPin[17].getLevel();
        IPin[18].OldLevel = IPin[18].getLevel();
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        String s;
        String s1;
        String s2;
        String s3;
        String s4;
        if(IPin[44].getLevel() == 5)
        {
            if(InX < 0)
                s = String.valueOf(df1.format((1.0D * (double)InX) / 128D));
            else
                s = " " + String.valueOf(df1.format((1.0D * (double)InX) / 128D));
            if(InY < 0)
                s1 = String.valueOf(df1.format((1.0D * (double)InY) / 128D));
            else
                s1 = " " + String.valueOf(df1.format((1.0D * (double)InY) / 128D));
            if(MemP < 0)
                s2 = String.valueOf(df2.format((1.0D * (double)MemP) / 16384D));
            else
                s2 = " " + String.valueOf(df2.format((1.0D * (double)MemP) / 16384D));
            if(Prod < 0)
                s3 = String.valueOf(df2.format((1.0D * (double)Prod) / 16384D));
            else
                s3 = " " + String.valueOf(df2.format((1.0D * (double)Prod) / 16384D));
            if(OutZ < 0)
                s4 = String.valueOf(df1.format((1.0D * (double)OutZ) / 16384D));
            else
                s4 = " " + String.valueOf(df1.format((1.0D * (double)OutZ) / 16384D));
        } else
        {
            if(InX < 0)
                s = String.valueOf(InX);
            else
                s = " " + String.valueOf(InX);
            if(InY < 0)
                s1 = String.valueOf(InY);
            else
                s1 = " " + String.valueOf(InY);
            if(MemP < 0)
                s2 = String.valueOf(MemP);
            else
                s2 = " " + String.valueOf(MemP);
            if(Prod < 0)
                s3 = String.valueOf(Prod);
            else
                s3 = " " + String.valueOf(Prod);
            if(OutZ < 0)
                s4 = String.valueOf(OutZ);
            else
                s4 = " " + String.valueOf(OutZ);
        }
        if(IPin[0].PinDim.width > 0)
        {
            g.setFill(MyColor.blue);
            DrawWithOffset.fillText(g,"MAC", (l + 4) * k, (i1 + 2) * k + 3);
            DrawWithOffset.fillText(g,"z=" + s2, (l + 4) * k + 2, (i1 + 24) * k - 4);
            if(IPin[43].getLevel() == 5)
                DrawWithOffset.fillText(g,"\u2192" + s4, (l + 4) * k + 2, (i1 + 25) * k - 4);
            g.setFill(MyColor.red);
            DrawWithOffset.fillText(g,"x=" + s, (l + 5) * k, (i1 + 6) * k);
            DrawWithOffset.fillText(g,"y=" + s1, (l + 5) * k, (i1 + 18) * k);
            DrawWithOffset.fillText(g,"x.y=" + s3, (l + 4) * k + 1, (i1 + 10) * k + 2);
        } else
        if(IPin[0].PinDim.height > 0)
        {
            g.setFill(MyColor.blue);
            DrawWithOffset.fillText(g,"MAC", (l + 25) * k, (i1 + 5) * k);
            DrawWithOffset.fillText(g,"z=" + s2, (l + 12) * k, (i1 + 8) * k + 2);
            if(IPin[43].getLevel() == 5)
                DrawWithOffset.fillText(g,"\u2192" + s4, (l + 12) * k, (i1 + 9) * k + 2);
            g.setFill(MyColor.red);
            DrawWithOffset.fillText(g,"x=" + s, (l + 19) * k + 5, (i1 + 4) * k - 4);
            DrawWithOffset.fillText(g,"y=" + s1, (l + 7) * k + 5, (i1 + 4) * k - 4);
            DrawWithOffset.fillText(g,"x.y=" + s3, (l + 12) * k, (i1 + 6) * k + 2);
        } else
        if(IPin[0].PinDim.width < 0)
        {
            g.setFill(MyColor.blue);
            DrawWithOffset.fillText(g,"MAC", (l + 4) * k, (i1 + 25) * k + 5);
            DrawWithOffset.fillText(g,"z=" + s2, (l + 4) * k + 2, (i1 + 4) * k + 3);
            if(IPin[43].getLevel() == 5)
                DrawWithOffset.fillText(g,"\u2192" + s4, (l + 4) * k + 2, (i1 + 6) * k + 3);
            g.setFill(MyColor.red);
            DrawWithOffset.fillText(g,"x=" + s, (l + 6) * k - 3, (i1 + 20) * k);
            DrawWithOffset.fillText(g,"y=" + s1, (l + 6) * k - 3, (i1 + 9) * k);
            DrawWithOffset.fillText(g,"x.y=" + s3, (l + 5) * k - 3, (i1 + 14) * k + 5);
        } else
        if(IPin[0].PinDim.height < 0)
        {
            g.setFill(MyColor.blue);
            DrawWithOffset.fillText(g,"MAC", (l + 3) * k + 3, (i1 + 5) * k);
            DrawWithOffset.fillText(g,"z=" + s2, (l + 10) * k, (i1 + 4) * k - 4);
            if(IPin[43].getLevel() == 5)
                DrawWithOffset.fillText(g,"\u2192" + s4, (l + 10) * k, (i1 + 5) * k - 4);
            g.setFill(MyColor.red);
            DrawWithOffset.fillText(g,"x=" + s, (l + 4) * k, (i1 + 9) * k + 3);
            DrawWithOffset.fillText(g,"y=" + s1, (l + 16) * k, (i1 + 9) * k + 3);
            DrawWithOffset.fillText(g,"x.y=" + s3, (l + 10) * k, (i1 + 7) * k - 4);
        }
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    int InX;
    int InY;
    int Prod;
    int MemX[] = {
        0, 0, 0, 0, 0, 0, 0, 0
    };
    int MemY[] = {
        0, 0, 0, 0, 0, 0, 0, 0
    };
    int MemZ[] = {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0
    };
    int MemP;
    int OutZ;
    int Acc;
    int Sub;
    int Rnd;
    DecimalFormat df1;
    DecimalFormat df2;
}
