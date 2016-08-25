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

public class ChariotXYPaP extends IntegratedCircuit
{

    public ChariotXYPaP(Pin apin[][], int i, int j)
    {
        super(i, j, 25, 25, 2, 2, 21, 21, 10, 1);
        SwitchClosed = false;
        Maxi = 120;
        InitCountY = 120;
        InitCountX = 0;
        ClefPresente = 0;
        Marge = 0;
        Piece = 0;
        IPin[0] = new InputPin("", 3, 3, 0, 0, 0, 0, 0);
        IPin[1] = new InputPin("P", 5, 25, 0, -2, 0, 0, 0);
        IPin[2] = new InputPin("Dx", 13, 25, 0, -2, 0, 0, 0);
        IPin[3] = new InputPin("Cx", 11, 25, 0, -2, 0, 0, 0);
        IPin[4] = new InputPin("Bx", 9, 25, 0, -2, 0, 0, 0);
        IPin[5] = new InputPin("Ax", 7, 25, 0, -2, 0, 0, 0);
        IPin[6] = new InputPin("Dy", 21, 25, 0, -2, 0, 0, 0);
        IPin[7] = new InputPin("Cy", 19, 25, 0, -2, 0, 0, 0);
        IPin[8] = new InputPin("By", 17, 25, 0, -2, 0, 0, 0);
        IPin[9] = new InputPin("Ay", 15, 25, 0, -2, 0, 0, 0);
        OPin[0] = new OutputPin("z", 4, 25, 0, -2, 0, 0, 1);
        CountY = InitCountY;
        CountXmod = CountX % 8;
        CountX = InitCountX;
        CountYmod = CountY % 8;
        DCBAx = DCBAy = OldDCBAx = OldDCBAy = 0;
        for(int k = 0; k < 10; k++)
            IPin[k].setLevel(-1);

        ComponentName = "Chariot XY a moteurs pas a pas unipolaires";
        ClassName = "ChariotXYPaP";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public ChariotXYPaP(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        SwitchClosed = false;
        Maxi = 120;
        InitCountY = 120;
        InitCountX = 0;
        ClefPresente = 0;
        Marge = 0;
        Piece = 0;
        CountY = InitCountY;
        CountXmod = CountX % 8;
        CountX = InitCountX;
        CountYmod = CountY % 8;
        DCBAx = DCBAy = OldDCBAx = OldDCBAy = 1;
        for(int k = 0; k < 10; k++)
            IPin[k].setLevel(-1);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        ChariotXYPaP chariotxypap = new ChariotXYPaP(this, i, j);
        return chariotxypap;
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
        DCBAx = (8 * IPin[2].getLevel() + 4 * IPin[3].getLevel() + 2 * IPin[4].getLevel() + IPin[5].getLevel()) / 5;
        DCBAy = (8 * IPin[6].getLevel() + 4 * IPin[7].getLevel() + 2 * IPin[8].getLevel() + IPin[9].getLevel()) / 5;
        if(IPin[0].getOldLevel() == 0 && IPin[0].getLevel() == 5)
        {
            IPin[0].OldLevel = IPin[0].getLevel();
            IPin[2].OldLevel = IPin[2].getLevel();
            IPin[3].OldLevel = IPin[3].getLevel();
            IPin[4].OldLevel = IPin[4].getLevel();
            IPin[5].OldLevel = IPin[5].getLevel();
            IPin[6].OldLevel = IPin[6].getLevel();
            IPin[7].OldLevel = IPin[7].getLevel();
            IPin[8].OldLevel = IPin[8].getLevel();
            IPin[9].OldLevel = IPin[9].getLevel();
            CountXmod = CountX % 8;
            CountYmod = (Maxi - CountY) % 8;
            switch(CountXmod)
            {
            case 0: // '\0'
                switch(DCBAx)
                {
                case 2: // '\002'
                    CountX = CountX + 2;
                    break;

                case 8: // '\b'
                    CountX = CountX - 2;
                    break;

                case 3: // '\003'
                    CountX++;
                    break;

                case 9: // '\t'
                    CountX--;
                    break;
                }
                break;

            case 1: // '\001'
                switch(DCBAx)
                {
                case 6: // '\006'
                    CountX = CountX + 2;
                    break;

                case 9: // '\t'
                    CountX = CountX - 2;
                    break;

                case 2: // '\002'
                    CountX++;
                    break;

                case 1: // '\001'
                    CountX--;
                    break;
                }
                break;

            case 2: // '\002'
                switch(DCBAx)
                {
                case 4: // '\004'
                    CountX = CountX + 2;
                    break;

                case 1: // '\001'
                    CountX = CountX - 2;
                    break;

                case 6: // '\006'
                    CountX++;
                    break;

                case 3: // '\003'
                    CountX--;
                    break;
                }
                break;

            case 3: // '\003'
                switch(DCBAx)
                {
                case 12: // '\f'
                    CountX = CountX + 2;
                    break;

                case 3: // '\003'
                    CountX = CountX - 2;
                    break;

                case 4: // '\004'
                    CountX++;
                    break;

                case 2: // '\002'
                    CountX--;
                    break;
                }
                break;

            case 4: // '\004'
                switch(DCBAx)
                {
                case 8: // '\b'
                    CountX = CountX + 2;
                    break;

                case 2: // '\002'
                    CountX = CountX - 2;
                    break;

                case 12: // '\f'
                    CountX++;
                    break;

                case 6: // '\006'
                    CountX--;
                    break;
                }
                break;

            case 5: // '\005'
                switch(DCBAx)
                {
                case 9: // '\t'
                    CountX = CountX + 2;
                    break;

                case 6: // '\006'
                    CountX = CountX - 2;
                    break;

                case 8: // '\b'
                    CountX++;
                    break;

                case 4: // '\004'
                    CountX--;
                    break;
                }
                break;

            case 6: // '\006'
                switch(DCBAx)
                {
                case 1: // '\001'
                    CountX = CountX + 2;
                    break;

                case 4: // '\004'
                    CountX = CountX - 2;
                    break;

                case 9: // '\t'
                    CountX++;
                    break;

                case 12: // '\f'
                    CountX--;
                    break;
                }
                break;

            case 7: // '\007'
                switch(DCBAx)
                {
                case 3: // '\003'
                    CountX = CountX + 2;
                    break;

                case 12: // '\f'
                    CountX = CountX - 2;
                    break;

                case 1: // '\001'
                    CountX++;
                    break;

                case 8: // '\b'
                    CountX--;
                    break;
                }
                break;
            }
            switch(CountYmod)
            {
            default:
                break;

            case 0: // '\0'
                switch(DCBAy)
                {
                case 2: // '\002'
                    CountY = CountY - 2;
                    break;

                case 8: // '\b'
                    CountY = CountY + 2;
                    break;

                case 3: // '\003'
                    CountY--;
                    break;

                case 9: // '\t'
                    CountY++;
                    break;
                }
                break;

            case 1: // '\001'
                switch(DCBAy)
                {
                case 6: // '\006'
                    CountY = CountY - 2;
                    break;

                case 9: // '\t'
                    CountY = CountY + 2;
                    break;

                case 2: // '\002'
                    CountY--;
                    break;

                case 1: // '\001'
                    CountY++;
                    break;
                }
                break;

            case 2: // '\002'
                switch(DCBAy)
                {
                case 4: // '\004'
                    CountY = CountY - 2;
                    break;

                case 1: // '\001'
                    CountY = CountY + 2;
                    break;

                case 6: // '\006'
                    CountY--;
                    break;

                case 3: // '\003'
                    CountY++;
                    break;
                }
                break;

            case 3: // '\003'
                switch(DCBAy)
                {
                case 12: // '\f'
                    CountY = CountY - 2;
                    break;

                case 3: // '\003'
                    CountY = CountY + 2;
                    break;

                case 4: // '\004'
                    CountY--;
                    break;

                case 2: // '\002'
                    CountY++;
                    break;
                }
                break;

            case 4: // '\004'
                switch(DCBAy)
                {
                case 8: // '\b'
                    CountY = CountY - 2;
                    break;

                case 2: // '\002'
                    CountY = CountY + 2;
                    break;

                case 12: // '\f'
                    CountY--;
                    break;

                case 6: // '\006'
                    CountY++;
                    break;
                }
                break;

            case 5: // '\005'
                switch(DCBAy)
                {
                case 9: // '\t'
                    CountY = CountY - 2;
                    break;

                case 6: // '\006'
                    CountY = CountY + 2;
                    break;

                case 8: // '\b'
                    CountY--;
                    break;

                case 4: // '\004'
                    CountY++;
                    break;
                }
                break;

            case 6: // '\006'
                switch(DCBAy)
                {
                case 1: // '\001'
                    CountY = CountY - 2;
                    break;

                case 4: // '\004'
                    CountY = CountY + 2;
                    break;

                case 9: // '\t'
                    CountY--;
                    break;

                case 12: // '\f'
                    CountY++;
                    break;
                }
                break;

            case 7: // '\007'
                switch(DCBAy)
                {
                case 3: // '\003'
                    CountY = CountY - 2;
                    break;

                case 12: // '\f'
                    CountY = CountY + 2;
                    break;

                case 1: // '\001'
                    CountY--;
                    break;

                case 8: // '\b'
                    CountY++;
                    break;
                }
                break;
            }
            OldDCBAx = DCBAx;
            OldDCBAy = DCBAy;
        }
        if(CountY == Maxi && CountX == 0)
            OPin[0].setLevel(5);
        else
            OPin[0].setLevel(0);
        if(CountY < 0)
            CountY = 0;
        if(CountX < 0)
            CountX = 0;
        if(CountY > Maxi)
            CountY = Maxi;
        if(CountX > Maxi)
            CountX = Maxi;
        ClefPresente = Math.max(IPin[0].getLevel(), ClefPresente);
        Piece = IPin[1].getLevel();
        if(SwitchClosed)
        {
            CountY = 120;
            CountX = 0;
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
        if(IPin[1].PinDim.height < 0 || IPin[1].PinDim.width > 0)
        {
            g1.setFill(MyColor.red);
            DrawWithOffset.fillText(g1,"x=" + Integer.toString(CountX), (l + 9) * k, (i1 + 3) * k + 3);
            DrawWithOffset.fillText(g1,"y=" + Integer.toString(Maxi - CountY), (l + 13) * k, (i1 + 3) * k + 3);
            g1.setFill(MyColor.white);
            DrawWithOffset.fillOval(g1,(l + 7) * k + 2, (i1 + 2) * k + 2, 2 * k - 4, 2 * k - 4);
            DrawWithOffset.fillOval(g1,(l + 16) * k + 2, (i1 + 2) * k + 2, 2 * k - 4, 2 * k - 4);
            g1.setStroke(MyColor.black);
            DrawWithOffset.strokeOval(g1,(l + 7) * k + 2, (i1 + 2) * k + 2, 2 * k - 4, 2 * k - 4);
            DrawWithOffset.strokeOval(g1,(l + 16) * k + 2, (i1 + 2) * k + 2, 2 * k - 4, 2 * k - 4);
            g1.setStroke(MyColor.blue);
            switch(CountXmod)
            {
            case 0: // '\0'
                DrawWithOffset.strokeLine(g1,(l + 8) * k, (i1 + 3) * k, (l + 8) * k, (i1 + 3) * k - 5);
                break;

            case 1: // '\001'
                DrawWithOffset.strokeLine(g1,(l + 8) * k, (i1 + 3) * k, (l + 8) * k + 3, (i1 + 3) * k - 3);
                break;

            case 2: // '\002'
                DrawWithOffset.strokeLine(g1,(l + 8) * k, (i1 + 3) * k, (l + 8) * k + 5, (i1 + 3) * k);
                break;

            case 3: // '\003'
                DrawWithOffset.strokeLine(g1,(l + 8) * k, (i1 + 3) * k, (l + 8) * k + 3, (i1 + 3) * k + 3);
                break;

            case 4: // '\004'
                DrawWithOffset.strokeLine(g1,(l + 8) * k, (i1 + 3) * k, (l + 8) * k, (i1 + 3) * k + 5);
                break;

            case 5: // '\005'
                DrawWithOffset.strokeLine(g1,(l + 8) * k, (i1 + 3) * k, (l + 8) * k - 3, (i1 + 3) * k + 3);
                break;

            case 6: // '\006'
                DrawWithOffset.strokeLine(g1,(l + 8) * k, (i1 + 3) * k, (l + 8) * k - 5, (i1 + 3) * k);
                break;

            case 7: // '\007'
                DrawWithOffset.strokeLine(g1,(l + 8) * k, (i1 + 3) * k, (l + 8) * k - 3, (i1 + 3) * k - 3);
                break;
            }
            switch(CountYmod)
            {
            case 0: // '\0'
                DrawWithOffset.strokeLine(g1,(l + 17) * k, (i1 + 3) * k, (l + 17) * k, (i1 + 3) * k - 5);
                break;

            case 1: // '\001'
                DrawWithOffset.strokeLine(g1,(l + 17) * k, (i1 + 3) * k, (l + 17) * k + 3, (i1 + 3) * k - 3);
                break;

            case 2: // '\002'
                DrawWithOffset.strokeLine(g1,(l + 17) * k, (i1 + 3) * k, (l + 17) * k + 5, (i1 + 3) * k);
                break;

            case 3: // '\003'
                DrawWithOffset.strokeLine(g1,(l + 17) * k, (i1 + 3) * k, (l + 17) * k + 3, (i1 + 3) * k + 3);
                break;

            case 4: // '\004'
                DrawWithOffset.strokeLine(g1,(l + 17) * k, (i1 + 3) * k, (l + 17) * k, (i1 + 3) * k + 5);
                break;

            case 5: // '\005'
                DrawWithOffset.strokeLine(g1,(l + 17) * k, (i1 + 3) * k, (l + 17) * k - 3, (i1 + 3) * k + 3);
                break;

            case 6: // '\006'
                DrawWithOffset.strokeLine(g1,(l + 17) * k, (i1 + 3) * k, (l + 17) * k - 5, (i1 + 3) * k);
                break;

            case 7: // '\007'
                DrawWithOffset.strokeLine(g1,(l + 17) * k, (i1 + 3) * k, (l + 17) * k - 3, (i1 + 3) * k - 3);
                break;
            }
        } else
        if(IPin[1].PinDim.height > 0 || IPin[1].PinDim.width < 0)
        {
            g1.setFill(MyColor.red);
            DrawWithOffset.fillText(g1,"x=" + Integer.toString(CountX), (l + 9) * k, (i1 + 22) * k + 3);
            DrawWithOffset.fillText(g1,"y=" + Integer.toString(Maxi - CountY), (l + 13) * k, (i1 + 22) * k + 3);
            g1.setFill(MyColor.white);
            DrawWithOffset.fillOval(g1,(l + 7) * k + 2, (i1 + 21) * k + 2, 2 * k - 4, 2 * k - 4);
            DrawWithOffset.fillOval(g1,(l + 16) * k + 2, (i1 + 21) * k + 2, 2 * k - 4, 2 * k - 4);
            g1.setStroke(MyColor.black);
            DrawWithOffset.strokeOval(g1,(l + 7) * k + 2, (i1 + 21) * k + 2, 2 * k - 4, 2 * k - 4);
            DrawWithOffset.strokeOval(g1,(l + 16) * k + 2, (i1 + 21) * k + 2, 2 * k - 4, 2 * k - 4);
            g1.setStroke(MyColor.blue);
            switch(CountXmod)
            {
            case 0: // '\0'
                DrawWithOffset.strokeLine(g1,(l + 8) * k, (i1 + 22) * k, (l + 8) * k, (i1 + 22) * k - 5);
                break;

            case 1: // '\001'
                DrawWithOffset.strokeLine(g1,(l + 8) * k, (i1 + 22) * k, (l + 8) * k + 3, (i1 + 22) * k - 3);
                break;

            case 2: // '\002'
                DrawWithOffset.strokeLine(g1,(l + 8) * k, (i1 + 22) * k, (l + 8) * k + 5, (i1 + 22) * k);
                break;

            case 3: // '\003'
                DrawWithOffset.strokeLine(g1,(l + 8) * k, (i1 + 22) * k, (l + 8) * k + 3, (i1 + 22) * k + 3);
                break;

            case 4: // '\004'
                DrawWithOffset.strokeLine(g1,(l + 8) * k, (i1 + 22) * k, (l + 8) * k, (i1 + 22) * k + 5);
                break;

            case 5: // '\005'
                DrawWithOffset.strokeLine(g1,(l + 8) * k, (i1 + 22) * k, (l + 8) * k - 3, (i1 + 22) * k + 3);
                break;

            case 6: // '\006'
                DrawWithOffset.strokeLine(g1,(l + 8) * k, (i1 + 22) * k, (l + 8) * k - 5, (i1 + 22) * k);
                break;

            case 7: // '\007'
                DrawWithOffset.strokeLine(g1,(l + 8) * k, (i1 + 22) * k, (l + 8) * k - 3, (i1 + 22) * k - 3);
                break;
            }
            switch(CountYmod)
            {
            case 0: // '\0'
                DrawWithOffset.strokeLine(g1,(l + 17) * k, (i1 + 22) * k, (l + 17) * k, (i1 + 22) * k - 5);
                break;

            case 1: // '\001'
                DrawWithOffset.strokeLine(g1,(l + 17) * k, (i1 + 22) * k, (l + 17) * k + 3, (i1 + 22) * k - 3);
                break;

            case 2: // '\002'
                DrawWithOffset.strokeLine(g1,(l + 17) * k, (i1 + 22) * k, (l + 17) * k + 5, (i1 + 22) * k);
                break;

            case 3: // '\003'
                DrawWithOffset.strokeLine(g1,(l + 17) * k, (i1 + 22) * k, (l + 17) * k + 3, (i1 + 22) * k + 3);
                break;

            case 4: // '\004'
                DrawWithOffset.strokeLine(g1,(l + 17) * k, (i1 + 22) * k, (l + 17) * k, (i1 + 22) * k + 5);
                break;

            case 5: // '\005'
                DrawWithOffset.strokeLine(g1,(l + 17) * k, (i1 + 22) * k, (l + 17) * k - 3, (i1 + 22) * k + 3);
                break;

            case 6: // '\006'
                DrawWithOffset.strokeLine(g1,(l + 17) * k, (i1 + 22) * k, (l + 17) * k - 5, (i1 + 22) * k);
                break;

            case 7: // '\007'
                DrawWithOffset.strokeLine(g1,(l + 17) * k, (i1 + 22) * k, (l + 17) * k - 3, (i1 + 22) * k - 3);
                break;
            }
        }
        if(ClefPresente == 0)
            DrawWithOffset.fillText(g1,"clé", l * k, (i1 + 3) * k + 2);
        g1.setStroke(MyColor.lightGray);
        g1.setFill(MyColor.red);
        DrawWithOffset.fillRect(g1,(l + 4) * k + 1 + CountX, (i1 + 4) * k + 1 + CountY, 2 * k - 2, 2 * k - 2);
        g1.setStroke(MyColor.darkGray);
        DrawWithOffset.strokeRect(g1,(l + 4) * k + 1 + CountX, (i1 + 4) * k + 1 + CountY, 2 * k - 2, 2 * k - 2);
        if(Piece == 5)
        {
            g1.setFill(MyColor.blue);
            DrawWithOffset.fillOval(g1,((l + 5) * k - 3) + CountX, ((i1 + 5) * k - 3) + CountY, 1 * k, 1 * k);
            g1.setStroke(MyColor.yellow);
            DrawWithOffset.strokeOval(g1,((l + 5) * k - 4) + CountX, ((i1 + 5) * k - 4) + CountY, 1 * k, 1 * k);
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
    int InitCountY;
    int InitCountX;
    int CountY;
    int CountX;
    int CountXmod;
    int CountYmod;
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
    int bd;
    int bg;
    int DCBAx;
    int DCBAy;
    int OldDCBAx;
    int OldDCBAy;
    int HGlent;
    int HDlent;
    int BGlent;
    int BDlent;
    int ClefPresente;
    int Marge;
    int Piece;
}
