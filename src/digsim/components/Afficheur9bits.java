package digsim.components;

import java.text.DecimalFormat;

import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.MyColor;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, MyColor, Pin,
//            ElectronicComponent

public class Afficheur9bits extends IntegratedCircuit
{

    public Afficheur9bits(Pin apin[][], int i, int j)
    {
        super(i, j, 3, 10, 3, 1, 4, 10, 10, 0);
        InputA = 0;
        df = new DecimalFormat("0.00");
        ff = new DecimalFormat("000");
        IPin[0] = new InputPin("0", 1, 2, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("", 1, 3, 2, 0, 0, 0, 0);
        IPin[2] = new InputPin("", 1, 4, 2, 0, 0, 0, 0);
        IPin[3] = new InputPin("", 1, 5, 2, 0, 0, 0, 0);
        IPin[4] = new InputPin("", 1, 6, 2, 0, 0, 0, 0);
        IPin[5] = new InputPin("", 1, 7, 2, 0, 0, 0, 0);
        IPin[6] = new InputPin("", 1, 8, 2, 0, 0, 0, 0);
        IPin[7] = new InputPin("", 1, 9, 2, 0, 0, 0, 0);
        IPin[8] = new InputPin("8", 1, 10, 2, 0, 0, 0, 0);
        IPin[9] = new InputPin("a", 5, 12, 0, -1, 0, 0, 0);
        ComponentName = "Afficheur complement a 2";
        ClassName = "Afficheur9bits";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
        AffFontIntV = Font.font("Sans-serif", FontWeight.BOLD, 15);
        AffFontFracV = Font.font("Sans-serif", FontWeight.BOLD, 12);
        AffFontIntH = Font.font("Sans-serif", FontWeight.BOLD, 28);
        AffFontFracH = Font.font("Sans-serif", FontWeight.BOLD, 28);
        IPin[9].setLevel(0);
    }

    public Afficheur9bits(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        InputA = 0;
        df = new DecimalFormat("0.00");
        ff = new DecimalFormat("000");
        AffFontIntV = Font.font("Sans-serif", FontWeight.BOLD, 15);
        AffFontFracV = Font.font("Sans-serif", FontWeight.BOLD, 12);
        AffFontIntH = Font.font("Sans-serif", FontWeight.BOLD, 28);
        AffFontFracH = Font.font("Sans-serif", FontWeight.BOLD, 28);
        IPin[9].setLevel(0);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Afficheur9bits afficheur9bits = new Afficheur9bits(this, i, j);
        return afficheur9bits;
    }

    public void SimulateLogic()
    {
        InputA = 0;
        if(IPin[0].getLevel() == 5)
            InputA++;
        if(IPin[1].getLevel() == 5)
            InputA += 2;
        if(IPin[2].getLevel() == 5)
            InputA += 4;
        if(IPin[3].getLevel() == 5)
            InputA += 8;
        if(IPin[4].getLevel() == 5)
            InputA += 16;
        if(IPin[5].getLevel() == 5)
            InputA += 32;
        if(IPin[6].getLevel() == 5)
            InputA += 64;
        if(IPin[7].getLevel() == 5)
            InputA += 128;
        if(IPin[8].getLevel() == 5)
            InputA -= 256;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        if(IPin[9].getLevel() == 0)
        {
            g.setFill(MyColor.blue);
            if(InputA < 0)
            {
                if(IPin[0].PinDim.width > 0)
                {
                    g.setFont(AffFontIntV);
                    DrawWithOffset.fillText(g,String.valueOf(ff.format(InputA)), (l + 4) * k - 6, (i1 + 6) * k + 5);
                } else
                if(IPin[0].PinDim.height > 0)
                {
                    g.setFont(AffFontIntH);
                    DrawWithOffset.fillText(g,String.valueOf(ff.format(InputA)), (l + 4) * k, (i1 + 4) * k + 2);
                } else
                if(IPin[0].PinDim.width < 0)
                {
                    g.setFont(AffFontIntV);
                    DrawWithOffset.fillText(g,String.valueOf(ff.format(InputA)), (l + 3) * k + 3, (i1 + 6) * k + 5);
                } else
                if(IPin[0].PinDim.height < 0)
                {
                    g.setFont(AffFontIntH);
                    DrawWithOffset.fillText(g,String.valueOf(ff.format(InputA)), (l + 4) * k, (i1 + 4) * k + 2);
                }
            } else
            if(IPin[0].PinDim.width > 0)
            {
                g.setFont(AffFontIntV);
                DrawWithOffset.fillText(g," " + String.valueOf(ff.format(InputA)), (l + 4) * k - 6, (i1 + 6) * k + 5);
            } else
            if(IPin[0].PinDim.height > 0)
            {
                g.setFont(AffFontIntH);
                DrawWithOffset.fillText(g," " + String.valueOf(ff.format(InputA)), (l + 4) * k, (i1 + 4) * k + 2);
            } else
            if(IPin[0].PinDim.width < 0)
            {
                g.setFont(AffFontIntV);
                DrawWithOffset.fillText(g," " + String.valueOf(ff.format(InputA)), (l + 3) * k + 3, (i1 + 6) * k + 5);
            } else
            if(IPin[0].PinDim.height < 0)
            {
                g.setFont(AffFontIntH);
                DrawWithOffset.fillText(g," " + String.valueOf(ff.format(InputA)), (l + 4) * k, (i1 + 4) * k + 2);
            }
        } else
        {
            g.setFill(MyColor.blue);
            DinputA = (1.0D * (double)InputA) / 128D;
            if(DinputA < 0.0D)
            {
                if(IPin[0].PinDim.width > 0)
                {
                    g.setFont(AffFontFracV);
                    DrawWithOffset.fillText(g,String.valueOf(df.format(DinputA)), (l + 4) * k - 6, (i1 + 6) * k + 5);
                } else
                if(IPin[0].PinDim.height > 0)
                {
                    g.setFont(AffFontFracH);
                    DrawWithOffset.fillText(g,String.valueOf(df.format(DinputA)), (l + 4) * k, (i1 + 4) * k + 2);
                } else
                if(IPin[0].PinDim.width < 0)
                {
                    g.setFont(AffFontFracV);
                    DrawWithOffset.fillText(g,String.valueOf(df.format(DinputA)), (l + 3) * k + 3, (i1 + 6) * k + 5);
                } else
                if(IPin[0].PinDim.height < 0)
                {
                    g.setFont(AffFontFracH);
                    DrawWithOffset.fillText(g,String.valueOf(df.format(DinputA)), (l + 4) * k, (i1 + 4) * k + 2);
                }
            } else
            if(IPin[0].PinDim.width > 0)
            {
                g.setFont(AffFontFracV);
                DrawWithOffset.fillText(g," " + String.valueOf(df.format(DinputA)), (l + 4) * k - 6, (i1 + 6) * k + 5);
            } else
            if(IPin[0].PinDim.height > 0)
            {
                g.setFont(AffFontFracH);
                DrawWithOffset.fillText(g," " + String.valueOf(df.format(DinputA)), (l + 4) * k, (i1 + 4) * k + 2);
            } else
            if(IPin[0].PinDim.width < 0)
            {
                g.setFont(AffFontFracV);
                DrawWithOffset.fillText(g," " + String.valueOf(df.format(DinputA)), (l + 3) * k + 3, (i1 + 6) * k + 5);
            } else
            if(IPin[0].PinDim.height < 0)
            {
                g.setFont(AffFontFracH);
                DrawWithOffset.fillText(g," " + String.valueOf(df.format(DinputA)), (l + 4) * k, (i1 + 4) * k + 2);
            }
        }
    }

    int InputA;
    double DinputA;
    DecimalFormat df;
    DecimalFormat ff;
    public Font AffFontIntV;
    public Font AffFontFracV;
    public Font AffFontIntH;
    public Font AffFontFracH;
}