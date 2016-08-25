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

public class ChariotXYhbgd extends IntegratedCircuit
{

    public ChariotXYhbgd(Pin apin[][], int i, int j)
    {
        super(i, j, 25, 25, 2, 2, 21, 21, 6, 4);
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
        ComponentName = "Chariot XY a capteurs h b g d";
        ClassName = "ChariotXYhbgd";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public ChariotXYhbgd(ElectronicComponent electroniccomponent, int i, int j)
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
        GameOver = false;
        ErreurCommande = false;
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
        ChariotXYhbgd chariotxyhbgd = new ChariotXYhbgd(this, i, j);
        return chariotxyhbgd;
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
        if(IPin[0].getOldLevel() == 0 && IPin[0].getLevel() == 5 || IPin[0].getOldLevel() == 5 && IPin[0].getLevel() == 0)
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
                ErreurCommande = true;
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
                ErreurCommande = true;
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
            GameOver = false;
            ErreurCommande = false;
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
        if(ClefPresente == 0)
            DrawWithOffset.fillText(g1,"clé", l * k, (i1 + 3) * k + 2);
        if(GameOver)
            DrawWithOffset.fillText(g1,"GAME OVER", (l + 9) * k, (i1 + 13) * k);
        if(ErreurCommande)
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
    boolean GameOver;
    boolean ErreurCommande;
    int Piece;
}
