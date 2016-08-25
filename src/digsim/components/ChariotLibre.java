package digsim.components;

import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.InputPin;
import digsim.MyColor;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, MyColor, Pin

public class ChariotLibre extends ElectronicComponent
{

    public ChariotLibre(Pin apin[][], int i, int j)
    {
        super(i, j, 6, 6, 2, 2, 6, 6, 5, 0);
        SwitchClosed = false;
        Gain = 2;
        Marge = 0;
        ErreurCommande = false;
        Piece = 0;
        IPin[0] = new InputPin("H", 3, 9, 0, -1, 0, 0, 0);
        IPin[1] = new InputPin("B", 4, 9, 0, -1, 0, 0, 0);
        IPin[2] = new InputPin("G", 5, 9, 0, -1, 0, 0, 0);
        IPin[3] = new InputPin("D", 6, 9, 0, -1, 0, 0, 0);
        IPin[4] = new InputPin("P", 7, 9, 0, -1, 0, 0, 0);
        CountHB = 0;
        CountGD = 0;
        for(int k = 0; k < 5; k++)
            IPin[k].setLevel(-1);

        ComponentName = "Chariot libre";
        ClassName = "ChariotLibre";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public ChariotLibre(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        SwitchClosed = false;
        Gain = 2;
        Marge = 0;
        ErreurCommande = false;
        Piece = 0;
        CountHB = 0;
        CountGD = 0;
        for(int k = 0; k < 5; k++)
            IPin[k].setLevel(-1);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        ChariotLibre chariotlibre = new ChariotLibre(this, i, j);
        return chariotlibre;
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

    public void InitBeforeSimulate()
    {
        if(Horloge == 0)
            Horloge = 5;
        else
            Horloge = 0;
    }

    public void SimulateLogic()
    {
        if(IPin[0].getLevel() == 5)
            H = 5;
        else
            H = 0;
        if(IPin[1].getLevel() == 5)
            B = 5;
        else
            B = 0;
        if(IPin[2].getLevel() == 5)
            G = 5;
        else
            G = 0;
        if(IPin[3].getLevel() == 5)
            D = 5;
        else
            D = 0;
        HBGD = (8 * H + 4 * B + 2 * G + D) / 5;
        if(OldHorloge == 0 && Horloge == 5)
            switch(HBGD)
            {
            case 1: // '\001'
                CountGD++;
                break;

            case 2: // '\002'
                CountGD--;
                break;

            case 3: // '\003'
                CountGD = 0;
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
                CountGD = 0;
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
                CountGD = 0;
                break;

            case 12: // '\f'
                CountHB = 0;
                break;

            case 13: // '\r'
                CountHB = 0;
                break;

            case 14: // '\016'
                CountHB = 0;
                break;

            case 15: // '\017'
                CountHB = 0;
                CountGD = 0;
                break;
            }
        OldHorloge = Horloge;
        CountGDgain = CountGD * Gain;
        CountHBgain = CountHB * Gain;
        Piece = IPin[4].getLevel();
        if(SwitchClosed)
        {
            CountHB = 0;
            CountGD = 0;
            Piece = 0;
        }
    }

    public void draw(GraphicsContext g1, int i, int j, int k)
    {
        super.draw(g1, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g1, l, i1, k);
        g1.setStroke(MyColor.gray);
        if(IPin[0].PinDim.width > 0)
            DrawWithOffset.strokeRect(g1,(l + 2) * k + 1, (i1 + 2) * k, 1 * k, 6 * k);
        else
        if(IPin[0].PinDim.height > 0)
            DrawWithOffset.strokeRect(g1,(l + 2) * k, (i1 + 2) * k + 1, 6 * k, 1 * k);
        else
        if(IPin[0].PinDim.width < 0)
            DrawWithOffset.strokeRect(g1,(l + 7) * k - 1, (i1 + 2) * k, 1 * k, 6 * k);
        else
        if(IPin[0].PinDim.height < 0)
            DrawWithOffset.strokeRect(g1,(l + 2) * k, (i1 + 7) * k - 1, 6 * k, 1 * k);
        g1.setFill(MyColor.red);
        if(CountGD != 0 || CountHB != 0)
        {
            DrawWithOffset.fillText(g1,"x:" + Integer.toString(CountGD), (l + 4) * k + 2, (i1 + 5) * k);
            DrawWithOffset.fillText(g1,"y:" + Integer.toString(-CountHB), (l + 4) * k + 2, (i1 + 6) * k);
        }
        g1.setFill(MyColor.blue);
        DrawWithOffset.fillRect(g1,(l + 4) * k + 1 + CountGDgain, (i1 + 4) * k + 1 + CountHBgain, 2 * k - 2, 2 * k - 2);
        g1.setStroke(MyColor.darkGray);
        DrawWithOffset.strokeRect(g1,(l + 4) * k + 1 + CountGDgain, (i1 + 4) * k + 1 + CountHBgain, 2 * k - 2, 2 * k - 2);
        if(Piece == 5)
        {
            g1.setFill(MyColor.red);
            DrawWithOffset.fillRect(g1,((l + 5) * k - 3) + CountGDgain, ((i1 + 5) * k - 3) + CountHBgain, 1 * k, 1 * k);
            g1.setStroke(MyColor.yellow);
            DrawWithOffset.strokeRect(g1,((l + 5) * k - 4) + CountGDgain, ((i1 + 5) * k - 4) + CountHBgain, 1 * k, 1 * k);
        }
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    boolean SwitchClosed;
    int CountHB;
    int CountGD;
    int CountHBgain;
    int CountGDgain;
    int HBGD;
    int Gain;
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
    int Marge;
    boolean ErreurCommande;
    int Piece;
    int Horloge;
    int OldHorloge;
}
