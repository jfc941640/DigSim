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

public class ChariotXYmixte extends IntegratedCircuit
{

    public ChariotXYmixte(Pin apin[][], int i, int j)
    {
        super(i, j, 25, 25, 2, 2, 21, 21, 6, 20);
        SwitchClosed = false;
        Maxi = 120;
        InitCountHB = Math.round((int)(Math.random() * (double)Maxi));
        InitCountGD = Math.round((int)(Math.random() * (double)Maxi));
        ClefPresente = 0;
        Marge = 0;
        GameOver = false;
        ErreurCommande = false;
        Piece = 0;
        IPin[0] = new InputPin("", 3, 3, 0, 0, 0, 0, 0);
        IPin[1] = new InputPin("H", 8, 25, 0, -2, 0, 0, 0);
        IPin[2] = new InputPin("B", 9, 25, 0, -2, 0, 0, 0);
        IPin[3] = new InputPin("G", 10, 25, 0, -2, 0, 0, 0);
        IPin[4] = new InputPin("D", 11, 25, 0, -2, 0, 0, 0);
        IPin[5] = new InputPin("P", 12, 25, 0, -2, 0, 0, 0);
        OPin[0] = new OutputPin("h", 14, 25, 0, -2, 0, 0, 0);
        OPin[1] = new OutputPin("b", 15, 25, 0, -2, 0, 0, 0);
        OPin[2] = new OutputPin("g", 16, 25, 0, -2, 0, 0, 0);
        OPin[3] = new OutputPin("d", 17, 25, 0, -2, 0, 0, 0);
        OPin[4] = new OutputPin("x0", 24, 4, -1, 0, 0, 0, 0);
        OPin[5] = new OutputPin("", 24, 5, -1, 0, 0, 0, 0);
        OPin[6] = new OutputPin("", 24, 6, -1, 0, 0, 0, 0);
        OPin[7] = new OutputPin("", 24, 7, -1, 0, 0, 0, 0);
        OPin[8] = new OutputPin("", 24, 8, -1, 0, 0, 0, 0);
        OPin[9] = new OutputPin("", 24, 9, -1, 0, 0, 0, 0);
        OPin[10] = new OutputPin("", 24, 10, -1, 0, 0, 0, 0);
        OPin[11] = new OutputPin("x7", 24, 11, -1, 0, 0, 0, 0);
        OPin[12] = new OutputPin("y0", 24, 14, -1, 0, 0, 0, 0);
        OPin[13] = new OutputPin("", 24, 15, -1, 0, 0, 0, 0);
        OPin[14] = new OutputPin("", 24, 16, -1, 0, 0, 0, 0);
        OPin[15] = new OutputPin("", 24, 17, -1, 0, 0, 0, 0);
        OPin[16] = new OutputPin("", 24, 18, -1, 0, 0, 0, 0);
        OPin[17] = new OutputPin("", 24, 19, -1, 0, 0, 0, 0);
        OPin[18] = new OutputPin("", 24, 20, -1, 0, 0, 0, 0);
        OPin[19] = new OutputPin("y7", 24, 21, -1, 0, 0, 0, 0);
        CountHB = InitCountHB;
        CountGD = InitCountGD;
        for(int k = 0; k < 6; k++)
            IPin[k].setLevel(-1);

        ComponentName = "Chariot XY a capteurs et sorties numeriques";
        ClassName = "ChariotXYmixte";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public ChariotXYmixte(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        SwitchClosed = false;
        Maxi = 120;
        InitCountHB = Math.round((int)(Math.random() * (double)Maxi));
        InitCountGD = Math.round((int)(Math.random() * (double)Maxi));
        ClefPresente = 0;
        Marge = 0;
        GameOver = false;
        ErreurCommande = false;
        Piece = 0;
        CountHB = InitCountHB;
        CountGD = InitCountGD;
        for(int k = 0; k < 6; k++)
            IPin[k].setLevel(-1);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        ChariotXYmixte chariotxymixte = new ChariotXYmixte(this, i, j);
        return chariotxymixte;
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
            case 1: // '\001'
                CountGD++;
                break;

            case 2: // '\002'
                CountGD--;
                break;

            case 3: // '\003'
                ErreurCommande = true;
                break;

            case 4: // '\004'
                CountHB++;
                break;

            case 5: // '\005'
                CountHB++;
                CountGD++;
                break;

            case 6: // '\006'
                CountHB++;
                CountGD--;
                break;

            case 7: // '\007'
                ErreurCommande = true;
                break;

            case 8: // '\b'
                CountHB--;
                break;

            case 9: // '\t'
                CountHB--;
                CountGD++;
                break;

            case 10: // '\n'
                CountHB--;
                CountGD--;
                break;

            case 11: // '\013'
                ErreurCommande = true;
                break;

            case 12: // '\f'
                ErreurCommande = true;
                break;

            case 13: // '\r'
                ErreurCommande = true;
                break;

            case 14: // '\016'
                ErreurCommande = true;
                break;

            case 15: // '\017'
                ErreurCommande = true;
                break;
            }
        }
        OPin[0].setLevel(h);
        OPin[1].setLevel(b);
        OPin[2].setLevel(g);
        OPin[3].setLevel(d);
        if(CountHB < 0 || CountHB > Maxi || CountGD < 0 || CountGD > Maxi)
            GameOver = true;
        OutX = CountGD;
        if((OutX & 1) == 1)
            OPin[4].setLevel(5);
        else
            OPin[4].setLevel(0);
        if((OutX & 2) == 2)
            OPin[5].setLevel(5);
        else
            OPin[5].setLevel(0);
        if((OutX & 4) == 4)
            OPin[6].setLevel(5);
        else
            OPin[6].setLevel(0);
        if((OutX & 8) == 8)
            OPin[7].setLevel(5);
        else
            OPin[7].setLevel(0);
        if((OutX & 0x10) == 16)
            OPin[8].setLevel(5);
        else
            OPin[8].setLevel(0);
        if((OutX & 0x20) == 32)
            OPin[9].setLevel(5);
        else
            OPin[9].setLevel(0);
        if((OutX & 0x40) == 64)
            OPin[10].setLevel(5);
        else
            OPin[10].setLevel(0);
        if((OutX & 0x80) == 128)
            OPin[11].setLevel(5);
        else
            OPin[11].setLevel(0);
        OutY = Maxi - CountHB;
        if((OutY & 1) == 1)
            OPin[12].setLevel(5);
        else
            OPin[12].setLevel(0);
        if((OutY & 2) == 2)
            OPin[13].setLevel(5);
        else
            OPin[13].setLevel(0);
        if((OutY & 4) == 4)
            OPin[14].setLevel(5);
        else
            OPin[14].setLevel(0);
        if((OutY & 8) == 8)
            OPin[15].setLevel(5);
        else
            OPin[15].setLevel(0);
        if((OutY & 0x10) == 16)
            OPin[16].setLevel(5);
        else
            OPin[16].setLevel(0);
        if((OutY & 0x20) == 32)
            OPin[17].setLevel(5);
        else
            OPin[17].setLevel(0);
        if((OutY & 0x40) == 64)
            OPin[18].setLevel(5);
        else
            OPin[18].setLevel(0);
        if((OutY & 0x80) == 128)
            OPin[19].setLevel(5);
        else
            OPin[19].setLevel(0);
        if(CountHB < 0)
            CountHB = 0;
        if(CountGD < 0)
            CountGD = 0;
        if(CountHB > Maxi)
            CountHB = Maxi;
        if(CountGD > Maxi)
            CountGD = Maxi;
        ClefPresente = Math.max(IPin[0].getLevel(), ClefPresente);
        Piece = IPin[5].getLevel();
        if(SwitchClosed)
        {
            CountHB = 120;
            CountGD = 0;
            GameOver = false;
            ErreurCommande = false;
            Piece = 0;
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
        if(ErreurCommande || GameOver)
            DrawWithOffset.fillText(g1,"ERREUR", (l + 9) * k, (i1 + 13) * k);
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
    int HGlent;
    int HDlent;
    int BGlent;
    int BDlent;
    int ClefPresente;
    int Marge;
    boolean GameOver;
    boolean ErreurCommande;
    int Piece;
}
