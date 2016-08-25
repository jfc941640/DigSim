package digsim.components;

import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, MyColor,
//            Pin, ElectronicComponent

public class WagonsSimple extends IntegratedCircuit
{

    public WagonsSimple(Pin apin[][], int i, int j)
    {
        super(i, j, 58, 18, 2, 2, 54, 14, 5, 11);
        SwitchClosed = false;
        Maxi = 96;
        InitCtrXx = Math.round((int)(Math.random() * 46D));
        InitCtrYx = 96 - Math.round((int)(Math.random() * 46D));
        CtrXx = InitCtrXx;
        CtrYx = InitCtrYx;
        Tmax = 40;
        Tmin = 20;
        Tpose = 10;
        WaitX = 0;
        WaitY = 0;
        Cle = 0;
        gOver = false;
        Err = false;
        Coll = false;
        PoseX = 0;
        PoseY = 0;
        IPin[0] = new InputPin("", 3, 3, 0, 0, 0, 0, 0);
        IPin[1] = new InputPin("Xg", 9, 18, 0, -2, 0, 0, 0);
        IPin[2] = new InputPin("Xd", 14, 18, 0, -2, 0, 0, 0);
        IPin[3] = new InputPin("Yg", 44, 18, 0, -2, 0, 0, 0);
        IPin[4] = new InputPin("Yd", 49, 18, 0, -2, 0, 0, 0);
        OPin[0] = new OutputPin("A", 22, 18, 0, -2, 0, 0, 0);
        OPin[1] = new OutputPin("B", 23, 18, 0, -2, 0, 0, 0);
        OPin[2] = new OutputPin("C", 32, 18, 0, -2, 0, 0, 0);
        OPin[3] = new OutputPin("D", 33, 18, 0, -2, 0, 0, 0);
        OPin[4] = new OutputPin("E", 25, 18, 0, -2, 0, 0, 0);
        OPin[5] = new OutputPin("F", 26, 18, 0, -2, 0, 0, 0);
        OPin[6] = new OutputPin("G", 35, 18, 0, -2, 0, 0, 0);
        OPin[7] = new OutputPin("H", 36, 18, 0, -2, 0, 0, 0);
        OPin[8] = new OutputPin("I", 28, 18, 0, -2, 0, 0, 0);
        OPin[9] = new OutputPin("J", 29, 18, 0, -2, 0, 0, 0);
        OPin[10] = new OutputPin("K", 30, 18, 0, -2, 0, 0, 0);
        for(int k = 0; k < 5; k++)
            IPin[k].setLevel(-1);

        ComponentName = "Wagonnets simple";
        ClassName = "WagonsSimple";
        Can_Rotate = false;
        RegisterPins(apin, i, j);
    }

    public WagonsSimple(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        SwitchClosed = false;
        Maxi = 96;
        InitCtrXx = Math.round((int)(Math.random() * 46D));
        InitCtrYx = 96 - Math.round((int)(Math.random() * 46D));
        CtrXx = InitCtrXx;
        CtrYx = InitCtrYx;
        Tmax = 40;
        Tmin = 20;
        Tpose = 10;
        WaitX = 0;
        WaitY = 0;
        Cle = 0;
        gOver = false;
        Err = false;
        Coll = false;
        PoseX = 0;
        PoseY = 0;
        for(int k = 0; k < 5; k++)
            IPin[k].setLevel(-1);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        WagonsSimple wagonssimple = new WagonsSimple(this, i, j);
        return wagonssimple;
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
            Xg = 5;
        else
            Xg = 0;
        if(IPin[2].getLevel() == 5)
            Xd = 5;
        else
            Xd = 0;
        if(IPin[3].getLevel() == 5)
            Yg = 5;
        else
            Yg = 0;
        if(IPin[4].getLevel() == 5)
            Yd = 5;
        else
            Yd = 0;
        COMMAND = (8 * Xg + 4 * Xd + 2 * Yg + Yd) / 5;
        if(CtrXx <= 2)
            A = 5;
        else
            A = 0;
        if(CtrXx >= 22 && CtrXx <= 26)
            B = 5;
        else
            B = 0;
        if(CtrXx >= 70 && CtrXx <= 74)
            C = 5;
        else
            C = 0;
        if(CtrXx >= Maxi - 2)
            D = 5;
        else
            D = 0;
        if(CtrYx <= 2)
            E = 5;
        else
            E = 0;
        if(CtrYx >= 22 && CtrYx <= 26)
            F = 5;
        else
            F = 0;
        if(CtrYx >= 70 && CtrYx <= 74)
            G = 5;
        else
            G = 0;
        if(CtrYx >= Maxi - 2)
            H = 5;
        else
            H = 0;
        if(CtrXx >= 36 && CtrXx <= 40 || CtrYx >= 36 && CtrYx <= 40)
            I = 5;
        else
            I = 0;
        if(CtrXx >= 46 && CtrXx <= 50 || CtrYx >= 46 && CtrYx <= 50)
            J = 5;
        else
            J = 0;
        if(CtrXx >= 56 && CtrXx <= 60 || CtrYx >= 56 && CtrYx <= 60)
            K = 5;
        else
            K = 0;
        if(CtrXx >= 25 && CtrXx <= 71 && CtrYx >= 25 && CtrYx <= 71 && CtrXx - CtrYx <= 3 && CtrYx - CtrXx <= 3)
            Coll = true;
        else
            Coll = false;
        if(A == 5 && IPin[2].getOldLevel() == 0 && Xd == 5)
        {
            WaitX = Tmin + Math.round((int)((double)Tmax * Math.random()));
            PoseX = 0;
        }
        if(D == 5 && IPin[1].getOldLevel() == 0 && Xg == 5)
        {
            WaitX = Tmin + Math.round((int)((double)Tmax * Math.random()));
            PoseX = 0;
        }
        if(E == 5 && IPin[4].getOldLevel() == 0 && Yd == 5)
        {
            WaitY = Tmin + Math.round((int)((double)Tmax * Math.random()));
            PoseY = 0;
        }
        if(H == 5 && IPin[3].getOldLevel() == 0 && Yg == 5)
        {
            WaitY = Tmin + Math.round((int)((double)Tmax * Math.random()));
            PoseY = 0;
        }
        if(IPin[2].getOldLevel() == 5 && Xd == 0 && D == 5 || IPin[1].getOldLevel() == 5 && Xg == 0 && A == 5)
            PoseX = 0;
        if(IPin[4].getOldLevel() == 5 && Yd == 0 && H == 5 || IPin[3].getOldLevel() == 5 && Yg == 0 && E == 5)
            PoseY = 0;
        if((IPin[0].getOldLevel() == 0 && IPin[0].getLevel() == 5 || IPin[0].getOldLevel() == 5 && IPin[0].getLevel() == 0) && !Coll)
        {
            IPin[0].OldLevel = IPin[0].getLevel();
            IPin[1].OldLevel = Xg;
            IPin[2].OldLevel = Xd;
            IPin[3].OldLevel = Yg;
            IPin[4].OldLevel = Yd;
            if(WaitX == Tpose)
                PoseX = 1;
            if(WaitY == Tpose)
                PoseY = 1;
            int i;
            if(WaitX > 0)
            {
                WaitX--;
                i = 0;
            } else
            {
                i = 1;
            }
            int j;
            if(WaitY > 0)
            {
                WaitY--;
                j = 0;
            } else
            {
                j = 1;
            }
            switch(COMMAND)
            {
            case 1: // '\001'
                CtrYx = CtrYx + j;
                break;

            case 2: // '\002'
                CtrYx = CtrYx - j;
                break;

            case 3: // '\003'
                Err = true;
                break;

            case 4: // '\004'
                CtrXx = CtrXx + i;
                break;

            case 5: // '\005'
                CtrXx = CtrXx + i;
                CtrYx = CtrYx + j;
                break;

            case 6: // '\006'
                CtrXx = CtrXx + i;
                CtrYx = CtrYx - j;
                break;

            case 7: // '\007'
                CtrXx = CtrXx + i;
                Err = true;
                break;

            case 8: // '\b'
                CtrXx = CtrXx - i;
                break;

            case 9: // '\t'
                CtrXx = CtrXx - i;
                CtrYx = CtrYx + j;
                break;

            case 10: // '\n'
                CtrXx = CtrXx - i;
                CtrYx = CtrYx - j;
                break;

            case 11: // '\013'
                CtrXx = CtrXx - i;
                Err = true;
                break;

            case 12: // '\f'
                Err = true;
                break;

            case 13: // '\r'
                CtrYx = CtrYx + j;
                Err = true;
                break;

            case 14: // '\016'
                CtrYx = CtrYx - j;
                Err = true;
                break;

            case 15: // '\017'
                Err = true;
                break;
            }
        }
        OPin[0].setLevel(A);
        OPin[1].setLevel(B);
        OPin[2].setLevel(C);
        OPin[3].setLevel(D);
        OPin[4].setLevel(E);
        OPin[5].setLevel(F);
        OPin[6].setLevel(G);
        OPin[7].setLevel(H);
        OPin[8].setLevel(I);
        OPin[9].setLevel(J);
        OPin[10].setLevel(K);
        if(CtrXx < 0 || CtrXx > Maxi || CtrYx < 0 || CtrYx > Maxi)
            gOver = true;
        if(CtrXx > Maxi)
            CtrXx = Maxi;
        if(CtrXx < 0)
            CtrXx = 0;
        if(CtrYx > Maxi)
            CtrYx = Maxi;
        if(CtrYx < 0)
            CtrYx = 0;
        Cle = Math.max(IPin[0].getLevel(), Cle);
        if(SwitchClosed && Coll)
        {
            gOver = false;
            Err = false;
            Coll = false;
            PoseX = 0;
            PoseY = 0;
            CtrXx = Math.round((int)(Math.random() * 46D));
            CtrYx = 96 - Math.round((int)(Math.random() * 46D));
            IPin[0].setLevel(0);
            IPin[1].setLevel(0);
            IPin[2].setLevel(0);
            IPin[3].setLevel(0);
            IPin[4].setLevel(0);
        }
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        g.setFill(MyColor.white);
        DrawWithOffset.fillRect(g,(l + 4) * k, (i1 + 4) * k, 50 * k, 10 * k);
        g.setStroke(MyColor.gray);
        DrawWithOffset.strokeRect(g,(l + 4) * k, (i1 + 4) * k, 50 * k, 10 * k);
        g.setStroke(MyColor.blue);
        DrawWithOffset.strokeRect(g,(l + 4) * k - 1, (i1 + 4) * k - 1, 50 * k + 2, 10 * k + 2);
        g.setStroke(MyColor.black);
        byte byte0 = 2;
        DrawWithOffset.strokeLine(g,(l + 4) * k, ((i1 + 5) * k + byte0) - 2, (l + 21) * k, (i1 + 9) * k + byte0);
        DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 13) * k + byte0 + 2, (l + 21) * k, (i1 + 9) * k + byte0);
        DrawWithOffset.strokeLine(g,(l + 21) * k, (i1 + 9) * k + byte0, (l + 37) * k, (i1 + 9) * k + byte0);
        DrawWithOffset.strokeLine(g,(l + 37) * k, (i1 + 9) * k + byte0, (l + 54) * k, ((i1 + 5) * k + byte0) - 2);
        DrawWithOffset.strokeLine(g,(l + 37) * k, (i1 + 9) * k + byte0, (l + 54) * k, (i1 + 13) * k + byte0 + 2);
        byte0 = -2;
        DrawWithOffset.strokeLine(g,(l + 4) * k, ((i1 + 5) * k + byte0) - 2, (l + 21) * k, (i1 + 9) * k + byte0);
        DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 13) * k + byte0 + 2, (l + 21) * k, (i1 + 9) * k + byte0);
        DrawWithOffset.strokeLine(g,(l + 21) * k, (i1 + 9) * k + byte0, (l + 37) * k, (i1 + 9) * k + byte0);
        DrawWithOffset.strokeLine(g,(l + 37) * k, (i1 + 9) * k + byte0, (l + 54) * k, ((i1 + 5) * k + byte0) - 2);
        DrawWithOffset.strokeLine(g,(l + 37) * k, (i1 + 9) * k + byte0, (l + 54) * k, (i1 + 13) * k + byte0 + 2);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeLine(g,(l + 5) * k + 3, (i1 + 5) * k - 5, (l + 5) * k, (i1 + 5) * k + 5);
        DrawWithOffset.strokeLine(g,(l + 5) * k + 2, (i1 + 5) * k - 5, (l + 5) * k - 1, (i1 + 5) * k + 5);
        DrawWithOffset.strokeLine(g,(l + 17) * k + 2, (i1 + 8) * k - 5, (l + 17) * k - 2, (i1 + 8) * k + 5);
        DrawWithOffset.strokeLine(g,(l + 41) * k - 2, (i1 + 8) * k - 5, (l + 41) * k + 2, (i1 + 8) * k + 5);
        DrawWithOffset.strokeLine(g,(l + 53) * k - 3, (i1 + 5) * k - 5, (l + 53) * k, (i1 + 5) * k + 5);
        DrawWithOffset.strokeLine(g,(l + 53) * k - 2, (i1 + 5) * k - 5, (l + 53) * k + 1, (i1 + 5) * k + 5);
        g.setStroke(MyColor.blue);
        DrawWithOffset.strokeLine(g,(l + 5) * k, (i1 + 13) * k - 5, (l + 5) * k + 3, (i1 + 13) * k + 5);
        DrawWithOffset.strokeLine(g,(l + 5) * k - 1, (i1 + 13) * k - 5, (l + 5) * k + 2, (i1 + 13) * k + 5);
        DrawWithOffset.strokeLine(g,(l + 17) * k - 2, (i1 + 10) * k - 5, (l + 17) * k + 2, (i1 + 10) * k + 5);
        DrawWithOffset.strokeLine(g,(l + 41) * k + 2, (i1 + 10) * k - 5, (l + 41) * k - 2, (i1 + 10) * k + 5);
        DrawWithOffset.strokeLine(g,(l + 53) * k, (i1 + 13) * k - 5, (l + 53) * k - 3, (i1 + 13) * k + 5);
        DrawWithOffset.strokeLine(g,(l + 53) * k + 1, (i1 + 13) * k - 5, (l + 53) * k - 2, (i1 + 13) * k + 5);
        g.setStroke(Color.rgb(255, 0, 255));
        DrawWithOffset.strokeLine(g,(l + 24) * k, (i1 + 9) * k - 5, (l + 24) * k, (i1 + 9) * k + 5);
        DrawWithOffset.strokeLine(g,(l + 29) * k, (i1 + 9) * k - 5, (l + 29) * k, (i1 + 9) * k + 5);
        DrawWithOffset.strokeLine(g,(l + 34) * k, (i1 + 9) * k - 5, (l + 34) * k, (i1 + 9) * k + 5);
        g.setFill(MyColor.red);
        DrawWithOffset.fillText(g,"A", (l + 5) * k, (i1 + 7) * k);
        DrawWithOffset.fillText(g,"B", (l + 17) * k, (i1 + 7) * k);
        DrawWithOffset.fillText(g,"C", (l + 41) * k, (i1 + 7) * k);
        DrawWithOffset.fillText(g,"D", (l + 53) * k, (i1 + 7) * k);
        g.setFill(MyColor.blue);
        DrawWithOffset.fillText(g,"E", (l + 5) * k, (i1 + 12) * k);
        DrawWithOffset.fillText(g,"F", (l + 17) * k, (i1 + 12) * k);
        DrawWithOffset.fillText(g,"G", (l + 41) * k, (i1 + 12) * k);
        DrawWithOffset.fillText(g,"H", (l + 53) * k, (i1 + 12) * k);
        g.setFill(Color.rgb(255, 0, 255));
        DrawWithOffset.fillText(g,"I", (l + 24) * k, (i1 + 8) * k);
        DrawWithOffset.fillText(g,"J", (l + 29) * k, (i1 + 8) * k);
        DrawWithOffset.fillText(g,"K", (l + 34) * k, (i1 + 8) * k);
        g.setFill(MyColor.gray);
        DrawWithOffset.fillRect(g,(l + 2) * k, (i1 + 3) * k - 3, k * 1 + 2, k * 1 - 5);
        g.setFill(MyColor.gray);
        DrawWithOffset.fillRect(g,(l + 2) * k + 2, (i1 + 3) * k - 3, k * 1 - 6, k * 1);
        DrawWithOffset.fillRect(g,(l + 3) * k - 2, (i1 + 3) * k - 3, k * 1 - 6, k * 1);
        g.setFill(MyColor.green);
        if(Cle == 0)
            DrawWithOffset.fillText(g,"clé", l * k, (i1 + 3) * k + 2);
        g.setFill(MyColor.red);
        if(gOver)
            DrawWithOffset.fillText(g,"DEPASSEMENT!", (l + 24) * k + 3, (i1 + 11) * k);
        if(Err)
            DrawWithOffset.fillText(g,"Err de commande", (l + 24) * k, (i1 + 5) * k + 4);
        if(Coll)
            DrawWithOffset.fillText(g,"BOUM!", (l + 3) * k + 2 * (CtrXx + CtrYx), (i1 + 3) * k + 4);
        DrawWithOffset.fillText(g,"X=" + Integer.toString(CtrXx), (l + 10) * k, (i1 + 15) * k + 5);
        if(A == 5 && WaitX > 0)
            DrawWithOffset.fillText(g,Integer.toString(WaitX), (l + 2) * k + 4, (i1 + 5) * k + 5);
        if(D == 5 && WaitX > 0)
            DrawWithOffset.fillText(g,Integer.toString(WaitX), (l + 54) * k + 4, (i1 + 5) * k + 5);
        g.setFill(MyColor.blue);
        DrawWithOffset.fillText(g,"Y=" + Integer.toString(CtrYx), (l + 45) * k, (i1 + 15) * k + 5);
        if(E == 5 && WaitY > 0)
            DrawWithOffset.fillText(g,Integer.toString(WaitY), (l + 2) * k + 4, (i1 + 13) * k + 5);
        if(H == 5 && WaitY > 0)
            DrawWithOffset.fillText(g,Integer.toString(WaitY), (l + 54) * k + 4, (i1 + 13) * k + 5);
        if(CtrXx < 32)
        {
            g.setFill(MyColor.red);
            DrawWithOffset.fillRect(g,(l + 4) * k + 1 + 4 * CtrXx, (i1 + 4) * k + 1 + CtrXx, 2 * k - 2, 2 * k - 2);
            g.setStroke(MyColor.blue);
            DrawWithOffset.strokeRect(g,(l + 4) * k + 1 + 4 * CtrXx, (i1 + 4) * k + 1 + CtrXx, 2 * k - 2, 2 * k - 2);
            if(PoseX == 1 && !Coll)
            {
                g.setFill(MyColor.blue);
                DrawWithOffset.fillOval(g,((l + 5) * k - 3) + 4 * CtrXx, ((i1 + 5) * k - 3) + CtrXx, 1 * k, 1 * k);
                g.setStroke(MyColor.yellow);
                DrawWithOffset.strokeOval(g,((l + 5) * k - 4) + 4 * CtrXx, ((i1 + 5) * k - 4) + CtrXx, 1 * k, 1 * k);
            }
        } else
        if(CtrXx >= 32 && CtrXx <= 64)
        {
            g.setFill(MyColor.red);
            DrawWithOffset.fillRect(g,(l + 4) * k + 1 + 4 * CtrXx, (i1 + 8) * k + 1, 2 * k - 2, 2 * k - 2);
            g.setStroke(MyColor.blue);
            DrawWithOffset.strokeRect(g,(l + 4) * k + 1 + 4 * CtrXx, (i1 + 8) * k + 1, 2 * k - 2, 2 * k - 2);
            if(PoseX == 1 && !Coll)
            {
                g.setFill(MyColor.blue);
                DrawWithOffset.fillOval(g,((l + 5) * k - 3) + 4 * CtrXx, (i1 + 9) * k - 3, 1 * k, 1 * k);
                g.setStroke(MyColor.yellow);
                DrawWithOffset.strokeOval(g,((l + 5) * k - 4) + 4 * CtrXx, (i1 + 9) * k - 4, 1 * k, 1 * k);
            }
        } else
        {
            g.setFill(MyColor.red);
            DrawWithOffset.fillRect(g,(l + 4) * k + 1 + 4 * CtrXx, ((i1 + 16) * k + 1) - CtrXx, 2 * k - 2, 2 * k - 2);
            g.setStroke(MyColor.blue);
            DrawWithOffset.strokeRect(g,(l + 4) * k + 1 + 4 * CtrXx, ((i1 + 16) * k + 1) - CtrXx, 2 * k - 2, 2 * k - 2);
            if(PoseX == 1 && !Coll)
            {
                g.setFill(MyColor.blue);
                DrawWithOffset.fillOval(g,((l + 5) * k - 3) + 4 * CtrXx, (i1 + 17) * k - 3 - CtrXx, 1 * k, 1 * k);
                g.setStroke(MyColor.yellow);
                DrawWithOffset.strokeOval(g,((l + 5) * k - 4) + 4 * CtrXx, (i1 + 17) * k - 4 - CtrXx, 1 * k, 1 * k);
            }
        }
        if(CtrYx < 32)
        {
            g.setFill(MyColor.blue);
            DrawWithOffset.fillRect(g,(l + 4) * k + 1 + 4 * CtrYx, ((i1 + 12) * k + 1) - CtrYx, 2 * k - 2, 2 * k - 2);
            g.setStroke(MyColor.red);
            DrawWithOffset.strokeRect(g,(l + 4) * k + 1 + 4 * CtrYx, ((i1 + 12) * k + 1) - CtrYx, 2 * k - 2, 2 * k - 2);
            if(PoseY == 1 && !Coll)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillOval(g,((l + 5) * k - 3) + 4 * CtrYx, (i1 + 13) * k - 3 - CtrYx, 1 * k, 1 * k);
                g.setStroke(MyColor.yellow);
                DrawWithOffset.strokeOval(g,((l + 5) * k - 4) + 4 * CtrYx, (i1 + 13) * k - 4 - CtrYx, 1 * k, 1 * k);
            }
        } else
        if(CtrYx >= 32 && CtrYx <= 64)
        {
            g.setFill(MyColor.blue);
            DrawWithOffset.fillRect(g,(l + 4) * k + 1 + 4 * CtrYx, (i1 + 8) * k + 1, 2 * k - 2, 2 * k - 2);
            g.setStroke(MyColor.red);
            DrawWithOffset.strokeRect(g,(l + 4) * k + 1 + 4 * CtrYx, (i1 + 8) * k + 1, 2 * k - 2, 2 * k - 2);
            if(PoseY == 1 && !Coll)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillOval(g,((l + 5) * k - 3) + 4 * CtrYx, (i1 + 9) * k - 3, 1 * k, 1 * k);
                g.setStroke(MyColor.yellow);
                DrawWithOffset.strokeOval(g,((l + 5) * k - 4) + 4 * CtrYx, (i1 + 9) * k - 4, 1 * k, 1 * k);
            }
        } else
        {
            g.setFill(MyColor.blue);
            DrawWithOffset.fillRect(g,(l + 4) * k + 1 + 4 * CtrYx, i1 * k + 1 + CtrYx, 2 * k - 2, 2 * k - 2);
            g.setStroke(MyColor.red);
            DrawWithOffset.strokeRect(g,(l + 4) * k + 1 + 4 * CtrYx, i1 * k + 1 + CtrYx, 2 * k - 2, 2 * k - 2);
            if(PoseY == 1 && !Coll)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillOval(g,((l + 5) * k - 3) + 4 * CtrYx, ((i1 + 1) * k - 3) + CtrYx, 1 * k, 1 * k);
                g.setStroke(MyColor.yellow);
                DrawWithOffset.strokeOval(g,((l + 5) * k - 4) + 4 * CtrYx, ((i1 + 1) * k - 4) + CtrYx, 1 * k, 1 * k);
            }
        }
        if(Cle == 0)
            g.setFill(MyColor.red);
        else
            g.setFill(MyColor.green);
        DrawWithOffset.fillOval(g,(l + 3) * k + 2, (i1 + 3) * k + 2, 1 * k - 1, 1 * k - 1);
        g.setStroke(MyColor.darkGray);
        DrawWithOffset.strokeOval(g,(l + 3) * k + 2, (i1 + 3) * k + 2, 1 * k - 2, 1 * k - 2);
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    boolean SwitchClosed;
    int Maxi;
    int InitCtrXx;
    int InitCtrYx;
    int CtrXx;
    int CtrYx;
    int Tmax;
    int Tmin;
    int Tpose;
    int WaitX;
    int WaitY;
    int COMMAND;
    int Xd;
    int Xg;
    int Yd;
    int Yg;
    int A;
    int B;
    int C;
    int D;
    int E;
    int F;
    int G;
    int H;
    int I;
    int J;
    int K;
    int Cle;
    boolean gOver;
    boolean Err;
    boolean Coll;
    int PoseX;
    int PoseY;
}
