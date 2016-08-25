package digsim.components;


import java.text.DecimalFormat;

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

public class ADD8 extends IntegratedCircuit
{

    public ADD8(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 21, 3, 1, 5, 20, 18, 9);
        InX = 0;
        InY = 0;
        OutS = 0;
        S1 = 0;
        df1 = new DecimalFormat("#0.00");
        ADDSUB = "";
        IPin[0] = new InputPin("0", 1, 3, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("1", 1, 4, 2, 0, 0, 0, 0);
        IPin[2] = new InputPin("2", 1, 5, 2, 0, 0, 0, 0);
        IPin[3] = new InputPin("3", 1, 6, 2, 0, 0, 0, 0);
        IPin[4] = new InputPin("4", 1, 7, 2, 0, 0, 0, 0);
        IPin[5] = new InputPin("5", 1, 8, 2, 0, 0, 0, 0);
        IPin[6] = new InputPin("6", 1, 9, 2, 0, 0, 0, 0);
        IPin[7] = new InputPin("7", 1, 10, 2, 0, 0, 0, 0);
        IPin[8] = new InputPin("0", 1, 12, 2, 0, 0, 0, 0);
        IPin[9] = new InputPin("1", 1, 13, 2, 0, 0, 0, 0);
        IPin[10] = new InputPin("2", 1, 14, 2, 0, 0, 0, 0);
        IPin[11] = new InputPin("3", 1, 15, 2, 0, 0, 0, 0);
        IPin[12] = new InputPin("4", 1, 16, 2, 0, 0, 0, 0);
        IPin[13] = new InputPin("5", 1, 17, 2, 0, 0, 0, 0);
        IPin[14] = new InputPin("6", 1, 18, 2, 0, 0, 0, 0);
        IPin[15] = new InputPin("7", 1, 19, 2, 0, 0, 0, 0);
        IPin[16] = new InputPin("a", 4, 22, 0, -1, 0, 0, 0);
        IPin[17] = new InputPin("+/-", 6, 23, 0, -2, 0, 0, 0);
        OPin[0] = new OutputPin("0", 10, 7, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("1", 10, 8, -2, 0, 0, 0, 0);
        OPin[2] = new OutputPin("2", 10, 9, -2, 0, 0, 0, 0);
        OPin[3] = new OutputPin("3", 10, 10, -2, 0, 0, 0, 0);
        OPin[4] = new OutputPin("4", 10, 11, -2, 0, 0, 0, 0);
        OPin[5] = new OutputPin("5", 10, 12, -2, 0, 0, 0, 0);
        OPin[6] = new OutputPin("6", 10, 13, -2, 0, 0, 0, 0);
        OPin[7] = new OutputPin("7", 10, 14, -2, 0, 0, 0, 0);
        OPin[8] = new OutputPin("8", 10, 15, -2, 0, 0, 0, 0);
        ComponentName = "Additionneur 8 bits";
        ClassName = "ADD8";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
        IPin[16].setLevel(0);
    }

    public ADD8(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        InX = 0;
        InY = 0;
        OutS = 0;
        S1 = 0;
        df1 = new DecimalFormat("#0.00");
        ADDSUB = "";
        IPin[16].setLevel(0);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        ADD8 add8 = new ADD8(this, i, j);
        return add8;
    }

    public void SimulateLogic()
    {
        InX = 0;
        if(IPin[0].getLevel() == 5)
            InX++;
        if(IPin[1].getLevel() == 5)
            InX += 2;
        if(IPin[2].getLevel() == 5)
            InX += 4;
        if(IPin[3].getLevel() == 5)
            InX += 8;
        if(IPin[4].getLevel() == 5)
            InX += 16;
        if(IPin[5].getLevel() == 5)
            InX += 32;
        if(IPin[6].getLevel() == 5)
            InX += 64;
        if(IPin[7].getLevel() == 5)
            InX -= 128;
        InY = 0;
        if(IPin[8].getLevel() == 5)
            InY++;
        if(IPin[9].getLevel() == 5)
            InY += 2;
        if(IPin[10].getLevel() == 5)
            InY += 4;
        if(IPin[11].getLevel() == 5)
            InY += 8;
        if(IPin[12].getLevel() == 5)
            InY += 16;
        if(IPin[13].getLevel() == 5)
            InY += 32;
        if(IPin[14].getLevel() == 5)
            InY += 64;
        if(IPin[15].getLevel() == 5)
            InY -= 128;
        if(IPin[17].getLevel() == 5)
        {
            OutS = InX + InY;
            ADDSUB = "ADD";
        } else
        {
            OutS = InX - InY;
            ADDSUB = "SUB";
        }
        if((OutS & 1) == 1)
            OPin[0].setLevel(5);
        else
            OPin[0].setLevel(0);
        if((OutS & 2) == 2)
            OPin[1].setLevel(5);
        else
            OPin[1].setLevel(0);
        if((OutS & 4) == 4)
            OPin[2].setLevel(5);
        else
            OPin[2].setLevel(0);
        if((OutS & 8) == 8)
            OPin[3].setLevel(5);
        else
            OPin[3].setLevel(0);
        if((OutS & 0x10) == 16)
            OPin[4].setLevel(5);
        else
            OPin[4].setLevel(0);
        if((OutS & 0x20) == 32)
            OPin[5].setLevel(5);
        else
            OPin[5].setLevel(0);
        if((OutS & 0x40) == 64)
            OPin[6].setLevel(5);
        else
            OPin[6].setLevel(0);
        if((OutS & 0x80) == 128)
            OPin[7].setLevel(5);
        else
            OPin[7].setLevel(0);
        if((OutS & 0x100) == 256)
            OPin[8].setLevel(5);
        else
            OPin[8].setLevel(0);
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        if(IPin[16].getLevel() == 0)
        {
            if(IPin[0].PinDim.width > 0)
            {
                g.setFill(MyColor.blue);
                DrawWithOffset.fillText(g,ADDSUB, (l + 4) * k + 2, (i1 + 12) * k - 5);
                g.setFill(MyColor.red);
                DrawWithOffset.fillText(g,"x=" + String.valueOf(InX), (l + 4) * k, (i1 + 6) * k);
                DrawWithOffset.fillText(g,"y=" + String.valueOf(InY), (l + 4) * k, (i1 + 17) * k);
                if(IPin[17].getLevel() == 0)
                    DrawWithOffset.fillText(g,"x-y:" + String.valueOf(OutS), (l + 3) * k + 1, (i1 + 2) * k + 1);
                else
                    DrawWithOffset.fillText(g,"x+y:" + String.valueOf(OutS), (l + 3) * k + 1, (i1 + 2) * k + 1);
            } else
            if(IPin[0].PinDim.height > 0)
            {
                g.setFill(MyColor.blue);
                DrawWithOffset.fillText(g,ADDSUB, (l + 20) * k, (i1 + 4) * k + 3);
                g.setFill(MyColor.red);
                DrawWithOffset.fillText(g,"x=" + String.valueOf(InX), (l + 16) * k, (i1 + 4) * k - 4);
                DrawWithOffset.fillText(g,"y=" + String.valueOf(InY), (l + 8) * k, (i1 + 4) * k - 4);
                if(IPin[17].getLevel() == 0)
                    DrawWithOffset.fillText(g,"x-y:" + String.valueOf(OutS), (l + 11) * k - 1, (i1 + 4) * k + 4);
                else
                    DrawWithOffset.fillText(g,"x+y:" + String.valueOf(OutS), (l + 11) * k - 1, (i1 + 4) * k + 4);
            } else
            if(IPin[0].PinDim.width < 0)
            {
                g.setFill(MyColor.blue);
                DrawWithOffset.fillText(g,ADDSUB, (l + 4) * k, (i1 + 12) * k - 3);
                g.setFill(MyColor.red);
                DrawWithOffset.fillText(g,"x=" + String.valueOf(InX), (l + 4) * k - 5, (i1 + 17) * k);
                DrawWithOffset.fillText(g,"y=" + String.valueOf(InY), (l + 4) * k - 5, (i1 + 6) * k);
                if(IPin[17].getLevel() == 0)
                    DrawWithOffset.fillText(g,"x-y:" + String.valueOf(OutS), (l + 3) * k + 1, (i1 + 21) * k - 2);
                else
                    DrawWithOffset.fillText(g,"x+y:" + String.valueOf(OutS), (l + 3) * k + 1, (i1 + 21) * k - 2);
            } else
            if(IPin[0].PinDim.height < 0)
            {
                g.setFill(MyColor.blue);
                DrawWithOffset.fillText(g,ADDSUB, (l + 3) * k + 3, (i1 + 4) * k + 3);
                g.setFill(MyColor.red);
                DrawWithOffset.fillText(g,"x=" + String.valueOf(InX), (l + 8) * k, (i1 + 4) * k + 5);
                DrawWithOffset.fillText(g,"y=" + String.valueOf(InY), (l + 16) * k, (i1 + 4) * k + 5);
                if(IPin[17].getLevel() == 0)
                    DrawWithOffset.fillText(g,"x-y:" + String.valueOf(OutS), (l + 11) * k, (i1 + 4) * k - 4);
                else
                    DrawWithOffset.fillText(g,"x+y:" + String.valueOf(OutS), (l + 11) * k, (i1 + 4) * k - 4);
            }
        } else
        if(IPin[0].PinDim.width > 0)
        {
            g.setFill(MyColor.blue);
            DrawWithOffset.fillText(g,ADDSUB, (l + 4) * k + 2, (i1 + 12) * k - 5);
            g.setFill(MyColor.red);
            DrawWithOffset.fillText(g,"x=" + String.valueOf(df1.format((1.0D * (double)InX) / 128D)), (l + 4) * k, (i1 + 6) * k);
            DrawWithOffset.fillText(g,"y=" + String.valueOf(df1.format((1.0D * (double)InY) / 128D)), (l + 4) * k, (i1 + 17) * k);
            if(IPin[17].getLevel() == 0)
                DrawWithOffset.fillText(g,"x-y:" + String.valueOf(df1.format((1.0D * (double)OutS) / 128D)), (l + 3) * k + 1, (i1 + 2) * k + 1);
            else
                DrawWithOffset.fillText(g,"x+y:" + String.valueOf(df1.format((1.0D * (double)OutS) / 128D)), (l + 3) * k + 1, (i1 + 2) * k + 1);
        } else
        if(IPin[0].PinDim.height > 0)
        {
            g.setFill(MyColor.blue);
            DrawWithOffset.fillText(g,ADDSUB, (l + 20) * k, (i1 + 4) * k + 3);
            g.setFill(MyColor.red);
            DrawWithOffset.fillText(g,"x=" + String.valueOf(df1.format((1.0D * (double)InX) / 128D)), (l + 16) * k, (i1 + 4) * k - 4);
            DrawWithOffset.fillText(g,"y=" + String.valueOf(df1.format((1.0D * (double)InY) / 128D)), (l + 8) * k, (i1 + 4) * k - 4);
            if(IPin[17].getLevel() == 0)
                DrawWithOffset.fillText(g,"x-y=" + String.valueOf(df1.format((1.0D * (double)OutS) / 128D)), (l + 11) * k - 1, (i1 + 4) * k + 4);
            else
                DrawWithOffset.fillText(g,"x+y=" + String.valueOf(df1.format((1.0D * (double)OutS) / 128D)), (l + 11) * k - 1, (i1 + 4) * k + 4);
        } else
        if(IPin[0].PinDim.width < 0)
        {
            g.setFill(MyColor.blue);
            DrawWithOffset.fillText(g,ADDSUB, (l + 4) * k, (i1 + 12) * k - 3);
            g.setFill(MyColor.red);
            DrawWithOffset.fillText(g,"x=" + String.valueOf(df1.format((1.0D * (double)InX) / 128D)), (l + 4) * k - 5, (i1 + 17) * k);
            DrawWithOffset.fillText(g,"y=" + String.valueOf(df1.format((1.0D * (double)InY) / 128D)), (l + 4) * k - 5, (i1 + 6) * k);
            if(IPin[17].getLevel() == 0)
                DrawWithOffset.fillText(g,"x-y:" + String.valueOf(df1.format((1.0D * (double)OutS) / 128D)), (l + 3) * k + 1, (i1 + 21) * k - 2);
            else
                DrawWithOffset.fillText(g,"x+y:" + String.valueOf(df1.format((1.0D * (double)OutS) / 128D)), (l + 3) * k + 1, (i1 + 21) * k - 2);
        } else
        if(IPin[0].PinDim.height < 0)
        {
            g.setFill(MyColor.blue);
            DrawWithOffset.fillText(g,ADDSUB, (l + 3) * k + 3, (i1 + 4) * k + 3);
            g.setFill(MyColor.red);
            DrawWithOffset.fillText(g,"x=" + String.valueOf(df1.format((1.0D * (double)InX) / 128D)), (l + 8) * k, (i1 + 4) * k + 5);
            DrawWithOffset.fillText(g,"y=" + String.valueOf(df1.format((1.0D * (double)InY) / 128D)), (l + 16) * k, (i1 + 4) * k + 5);
            if(IPin[17].getLevel() == 0)
                DrawWithOffset.fillText(g,"x-y=" + String.valueOf(df1.format((1.0D * (double)OutS) / 128D)), (l + 11) * k, (i1 + 4) * k - 4);
            else
                DrawWithOffset.fillText(g,"x+y=" + String.valueOf(df1.format((1.0D * (double)OutS) / 128D)), (l + 11) * k, (i1 + 4) * k - 4);
        }
    }

    int InX;
    int InY;
    int OutS;
    int S1;
    DecimalFormat df1;
    String ADDSUB;
}