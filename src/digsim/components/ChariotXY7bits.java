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

public class ChariotXY7bits extends IntegratedCircuit
{

    public ChariotXY7bits(Pin apin[][], int i, int j)
    {
        super(i, j, 25, 25, 2, 2, 21, 21, 6, 14);
        SwitchClosed = false;
        Maxi = 120;
        InitCountHB = Math.round((int)(Math.random() * (double)Maxi));
        InitCountGD = Math.round((int)(Math.random() * (double)Maxi));
        DeltaHG = Math.random();
        DeltaHD = Math.random();
        DeltaBG = Math.random();
        DeltaBD = Math.random();
        Delta = 0.69999999999999996D + 0.40000000000000002D * Math.random();
        ClefPresente = 0;
        Marge = 0;
        GamOv = false;
        ErrCom = false;
        Piece = 0;
        IPin[0] = new InputPin("", 3, 3, 0, 0, 0, 0, 0);
        IPin[1] = new InputPin("H", 3, 25, 0, -2, 0, 0, 0);
        IPin[2] = new InputPin("B", 4, 25, 0, -2, 0, 0, 0);
        IPin[3] = new InputPin("G", 5, 25, 0, -2, 0, 0, 0);
        IPin[4] = new InputPin("D", 6, 25, 0, -2, 0, 0, 0);
        IPin[5] = new InputPin("P", 7, 25, 0, -2, 0, 0, 0);
        OPin[0] = new OutputPin("0", 15, 25, 0, -2, 0, 0, 0);
        OPin[1] = new OutputPin("x", 14, 25, 0, -2, 0, 0, 0);
        OPin[2] = new OutputPin("x", 13, 25, 0, -2, 0, 0, 0);
        OPin[3] = new OutputPin("x", 12, 25, 0, -2, 0, 0, 0);
        OPin[4] = new OutputPin("x", 11, 25, 0, -2, 0, 0, 0);
        OPin[5] = new OutputPin("x", 10, 25, 0, -2, 0, 0, 0);
        OPin[6] = new OutputPin("6", 9, 25, 0, -2, 0, 0, 0);
        OPin[7] = new OutputPin("0", 22, 25, 0, -2, 0, 0, 0);
        OPin[8] = new OutputPin("y", 21, 25, 0, -2, 0, 0, 0);
        OPin[9] = new OutputPin("y", 20, 25, 0, -2, 0, 0, 0);
        OPin[10] = new OutputPin("y", 19, 25, 0, -2, 0, 0, 0);
        OPin[11] = new OutputPin("y", 18, 25, 0, -2, 0, 0, 0);
        OPin[12] = new OutputPin("y", 17, 25, 0, -2, 0, 0, 0);
        OPin[13] = new OutputPin("6", 16, 25, 0, -2, 0, 0, 0);
        CountHB = InitCountHB;
        CountGD = InitCountGD;
        for(int k = 0; k < 6; k++)
            IPin[k].setLevel(-1);

        if(DeltaHG < 0.5D)
            HGlent = 0;
        else
            HGlent = 1;
        if(DeltaHD < 0.5D)
            HDlent = 0;
        else
            HDlent = 1;
        if(DeltaBG < 0.5D)
            BGlent = 0;
        else
            BGlent = 1;
        if(DeltaBD < 0.5D)
            BDlent = 0;
        else
            BDlent = 1;
        ComponentName = "Chariot XY a sorties numeriques xy";
        ClassName = "ChariotXY7bits";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public ChariotXY7bits(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        SwitchClosed = false;
        Maxi = 120;
        InitCountHB = Math.round((int)(Math.random() * (double)Maxi));
        InitCountGD = Math.round((int)(Math.random() * (double)Maxi));
        DeltaHG = Math.random();
        DeltaHD = Math.random();
        DeltaBG = Math.random();
        DeltaBD = Math.random();
        Delta = 0.69999999999999996D + 0.40000000000000002D * Math.random();
        ClefPresente = 0;
        Marge = 0;
        GamOv = false;
        ErrCom = false;
        Piece = 0;
        CountHB = InitCountHB;
        CountGD = InitCountGD;
        for(int k = 0; k < 6; k++)
            IPin[k].setLevel(-1);

        if(DeltaHG < 0.5D)
            HGlent = 0;
        else
            HGlent = 1;
        if(DeltaHD < 0.5D)
            HDlent = 0;
        else
            HDlent = 1;
        if(DeltaBG < 0.5D)
            BGlent = 0;
        else
            BGlent = 1;
        if(DeltaBD < 0.5D)
            BDlent = 0;
        else
            BDlent = 1;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        ChariotXY7bits chariotxy7bits = new ChariotXY7bits(this, i, j);
        return chariotxy7bits;
    }

    public boolean SimMouseDown()
    {
        SwitchClosed = true;
        return true;
    }

    public boolean SimMouseUp()
    {
        SwitchClosed = false;
        return true;
    }

    public void SimulateLogic()
    {
        if(IPin[1].getLevel() == 5)
            H = 5;
        else
            H = 0;
        if(IPin[2].getLevel() == 5)
            B = 5;
        else
            B = 0;
        if(IPin[3].getLevel() == 5)
            G = 5;
        else
            G = 0;
        if(IPin[4].getLevel() == 5)
            D = 5;
        else
            D = 0;
        HBGD = (8 * H + 4 * B + 2 * G + D) / 5;
        if(CountHB == 0)
            h = 5;
        else
            h = 0;
        if(CountHB == Maxi)
            b = 5;
        else
            b = 0;
        if(CountGD == 0)
            g = 5;
        else
            g = 0;
        if(CountGD == Maxi)
            d = 5;
        else
            d = 0;
        hg = h * g;
        hd = h * d;
        bg = b * g;
        bd = b * d;
        if(IPin[0].getOldLevel() == 0 && IPin[0].getLevel() == 5)
        {
            IPin[0].OldLevel = IPin[0].getLevel();
            switch(HBGD)
            {
            case 0: // '\0'
            default:
                break;

            case 1: // '\001'
                CountGD++;
                break;

            case 2: // '\002'
                CountGD--;
                break;

            case 3: // '\003'
                ErrCom = true;
                break;

            case 4: // '\004'
                CountHB++;
                break;

            case 5: // '\005'
                if(BDlent == 0)
                {
                    if(Math.random() < Delta)
                        CountHB++;
                    CountGD++;
                    break;
                }
                if(Math.random() < Delta)
                    CountGD++;
                CountHB++;
                break;

            case 6: // '\006'
                if(BGlent == 0)
                {
                    if(Math.random() < Delta)
                        CountHB++;
                    CountGD--;
                    break;
                }
                if(Math.random() < Delta)
                    CountGD--;
                CountHB++;
                break;

            case 7: // '\007'
                ErrCom = true;
                break;

            case 8: // '\b'
                CountHB--;
                break;

            case 9: // '\t'
                if(HDlent == 0)
                {
                    if(Math.random() < Delta)
                        CountHB--;
                    CountGD++;
                    break;
                }
                if(Math.random() < Delta)
                    CountGD++;
                CountHB--;
                break;

            case 10: // '\n'
                if(HGlent == 0)
                {
                    if(Math.random() < Delta)
                        CountHB--;
                    CountGD--;
                    break;
                }
                if(Math.random() < Delta)
                    CountGD--;
                CountHB--;
                break;

            case 11: // '\013'
                ErrCom = true;
                break;

            case 12: // '\f'
                ErrCom = true;
                break;

            case 13: // '\r'
                ErrCom = true;
                break;

            case 14: // '\016'
                ErrCom = true;
                break;

            case 15: // '\017'
                ErrCom = true;
                break;
            }
        }
        if(CountHB < 0 || CountHB > Maxi || CountGD < 0 || CountGD > Maxi)
            GamOv = true;
        OutX = CountGD;
        if((OutX & 1) == 1)
            OPin[0].setLevel(5);
        else
            OPin[0].setLevel(0);
        if((OutX & 2) == 2)
            OPin[1].setLevel(5);
        else
            OPin[1].setLevel(0);
        if((OutX & 4) == 4)
            OPin[2].setLevel(5);
        else
            OPin[2].setLevel(0);
        if((OutX & 8) == 8)
            OPin[3].setLevel(5);
        else
            OPin[3].setLevel(0);
        if((OutX & 0x10) == 16)
            OPin[4].setLevel(5);
        else
            OPin[4].setLevel(0);
        if((OutX & 0x20) == 32)
            OPin[5].setLevel(5);
        else
            OPin[5].setLevel(0);
        if((OutX & 0x40) == 64)
            OPin[6].setLevel(5);
        else
            OPin[6].setLevel(0);
        OutY = Maxi - CountHB;
        if((OutY & 1) == 1)
            OPin[7].setLevel(5);
        else
            OPin[7].setLevel(0);
        if((OutY & 2) == 2)
            OPin[8].setLevel(5);
        else
            OPin[8].setLevel(0);
        if((OutY & 4) == 4)
            OPin[9].setLevel(5);
        else
            OPin[9].setLevel(0);
        if((OutY & 8) == 8)
            OPin[10].setLevel(5);
        else
            OPin[10].setLevel(0);
        if((OutY & 0x10) == 16)
            OPin[11].setLevel(5);
        else
            OPin[11].setLevel(0);
        if((OutY & 0x20) == 32)
            OPin[12].setLevel(5);
        else
            OPin[12].setLevel(0);
        if((OutY & 0x40) == 64)
            OPin[13].setLevel(5);
        else
            OPin[13].setLevel(0);
        ClefPresente = Math.max(IPin[0].getLevel(), ClefPresente);
        Piece = IPin[5].getLevel();
        if(SwitchClosed)
        {
            CountHB = 120;
            CountGD = 0;
            DeltaHG = Math.random();
            DeltaHD = Math.random();
            DeltaBG = Math.random();
            DeltaBD = Math.random();
            Delta = 0.69999999999999996D + 0.40000000000000002D * Math.random();
            GamOv = false;
            ErrCom = false;
            Piece = 0;
            if(DeltaHG < 0.5D)
                HGlent = 0;
            else
                HGlent = 1;
            if(DeltaHD < 0.5D)
                HDlent = 0;
            else
                HDlent = 1;
            if(DeltaBG < 0.5D)
                BGlent = 0;
            else
                BGlent = 1;
            if(DeltaBD < 0.5D)
                BDlent = 0;
            else
                BDlent = 1;
        }
    }

    public void draw(GraphicsContext g1, int i, int j, int k)
    {
        super.draw(g1, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g1, l, i1, k);
        g1.setFill(MyColor.white);
        DrawWithOffset.fillRect(g1,(l + 4) * k, (i1 + 4) * k, 17 * k, 17 * k);
        g1.setStroke(MyColor.blue);
        DrawWithOffset.strokeRect(g1,(l + 4) * k, (i1 + 4) * k, 17 * k, 17 * k);
        DrawWithOffset.strokeRect(g1,(l + 4) * k - 1, (i1 + 4) * k - 1, 17 * k + 2, 17 * k + 2);
        g1.setFill(MyColor.gray);
        DrawWithOffset.fillRect(g1,(l + 2) * k, (i1 + 3) * k - 3, k * 1 + 2, k * 1 - 5);
        g1.setFill(MyColor.gray);
        DrawWithOffset.fillRect(g1,(l + 2) * k + 2, (i1 + 3) * k - 3, k * 1 - 6, k * 1);
        DrawWithOffset.fillRect(g1,(l + 3) * k - 2, (i1 + 3) * k - 3, k * 1 - 6, k * 1);
        g1.setFill(MyColor.red);
        if(IPin[1].PinDim.height < 0 || IPin[1].PinDim.width > 0)
        {
            DrawWithOffset.fillText(g1,"x=" + Integer.toString(OutX), (l + 10) * k, (i1 + 3) * k + 3);
            DrawWithOffset.fillText(g1,"y=" + Integer.toString(OutY), (l + 14) * k, (i1 + 3) * k + 3);
        } else
        if(IPin[1].PinDim.height > 0 || IPin[1].PinDim.width < 0)
        {
            DrawWithOffset.fillText(g1,"x=" + Integer.toString(OutX), (l + 10) * k, (i1 + 22) * k + 3);
            DrawWithOffset.fillText(g1,"y=" + Integer.toString(OutY), (l + 14) * k, (i1 + 22) * k + 3);
        }
        if(ClefPresente == 0)
            DrawWithOffset.fillText(g1,"clé", l * k, (i1 + 3) * k + 2);
        if(GamOv)
            DrawWithOffset.fillText(g1,"GAME OVER", (l + 9) * k, (i1 + 13) * k);
        if(ErrCom)
            DrawWithOffset.fillText(g1,"Erreur de commande", (l + 8) * k, (i1 + 14) * k + 4);
        g1.setFill(MyColor.lightGray);
        DrawWithOffset.fillText(g1,Integer.toString((int)Math.min(100D, 100D * Delta)), (l + 21) * k, (i1 + 3) * k + 3);
        g1.setStroke(MyColor.lightGray);
        g1.setFill(MyColor.red);
        DrawWithOffset.fillRect(g1,(l + 4) * k + 1 + CountGD, (i1 + 4) * k + 1 + CountHB, 2 * k - 2, 2 * k - 2);
        g1.setStroke(MyColor.darkGray);
        DrawWithOffset.strokeRect(g1,(l + 4) * k + 1 + CountGD, (i1 + 4) * k + 1 + CountHB, 2 * k - 2, 2 * k - 2);
        if(Piece == 5)
        {
            g1.setFill(MyColor.blue);
            DrawWithOffset.fillOval(g1,((l + 5) * k - 3) + CountGD, ((i1 + 5) * k - 3) + CountHB, 1 * k, 1 * k);
            g1.setStroke(MyColor.yellow);
            DrawWithOffset.strokeOval(g1,((l + 5) * k - 4) + CountGD, ((i1 + 5) * k - 4) + CountHB, 1 * k, 1 * k);
        }
        if(ClefPresente == 0)
            g1.setFill(MyColor.red);
        else
            g1.setFill(MyColor.green);
        DrawWithOffset.fillOval(g1,(l + 3) * k + 2, (i1 + 3) * k + 2, 1 * k - 1, 1 * k - 1);
        g1.setStroke(MyColor.darkGray);
        DrawWithOffset.strokeOval(g1,(l + 3) * k + 2, (i1 + 3) * k + 2, 1 * k - 2, 1 * k - 2);
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    boolean SwitchClosed;
    int Maxi;
    int InitCountHB;
    int InitCountGD;
    int CountHB;
    int CountGD;
    int OutX;
    int OutY;
    int HBGD;
    int H;
    int D;
    int B;
    int G;
    int h;
    int b;
    int d;
    int g;
    int hd;
    int hg;
    int bg;
    int bd;
    double DeltaHG;
    double DeltaHD;
    double DeltaBG;
    double DeltaBD;
    double Delta;
    int HGlent;
    int HDlent;
    int BGlent;
    int BDlent;
    int ClefPresente;
    int Marge;
    boolean GamOv;
    boolean ErrCom;
    int Piece;
}
