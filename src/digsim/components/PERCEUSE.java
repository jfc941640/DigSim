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

public class PERCEUSE extends IntegratedCircuit
{

    public PERCEUSE(Pin apin[][], int i, int j)
    {
        super(i, j, 19, 31, 2, 2, 15, 27, 10, 8);
        SwitchClosed = false;
        Count = 0;
        Count2 = 0;
        ClefPresente = 0;
        MaxCount2 = 8;
        ProfondeurMax = 0;
        MaxMax = 0;
        EnButee = 0;
        DedansPlaque = 0;
        DedansCylindre = 0;
        DedansSphere = 0;
        DedansCube = 0;
        Dedans = 0;
        OKpourPosePlaque = 0;
        OKpourPoseCube = 0;
        OKpourPoseCylindre = 0;
        OKpourPoseSphere = 0;
        ContactCale = 69;
        ContactPlaque = 53;
        ContactCube = 38;
        ContactCylindre = 22;
        ContactSphere = 6;
        Marge = 0;
        IPin[0] = new InputPin("", 6, 3, 0, 0, 0, 0, 0);
        IPin[1] = new InputPin("ALIM.", 0, 5, 2, 0, 0, 0, 0);
        IPin[2] = new InputPin("Rotation", 0, 7, 2, 0, 0, 0, 0);
        IPin[3] = new InputPin("Montée", 0, 8, 2, 0, 0, 0, 0);
        IPin[4] = new InputPin("Descente", 0, 9, 2, 0, 0, 0, 0);
        IPin[9] = new InputPin("Vitesse", 0, 10, 2, 0, 0, 0, 0);
        IPin[5] = new InputPin("Plaque", 0, 11, 2, 0, 0, 0, 0);
        IPin[6] = new InputPin("Cube", 0, 12, 2, 0, 0, 0, 0);
        IPin[7] = new InputPin("Cylindre", 0, 13, 2, 0, 0, 0, 0);
        IPin[8] = new InputPin("Sphère", 0, 14, 2, 0, 0, 0, 0);
        OPin[0] = new OutputPin("Q0", 19, 17, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("Q1", 19, 18, -2, 0, 0, 0, 0);
        OPin[2] = new OutputPin("Q2", 19, 19, -2, 0, 0, 0, 0);
        OPin[3] = new OutputPin("Q3", 19, 20, -2, 0, 0, 0, 0);
        OPin[4] = new OutputPin("Q4", 19, 21, -2, 0, 0, 0, 0);
        OPin[5] = new OutputPin("Q5", 19, 22, -2, 0, 0, 0, 0);
        OPin[6] = new OutputPin("Q6", 19, 23, -2, 0, 0, 0, 0);
        OPin[7] = new OutputPin("Q7", 19, 24, -2, 0, 0, 0, 0);
        for(int k = 0; k < 10; k++)
            IPin[k].setLevel(-1);

        ComponentName = "Perceuse";
        ClassName = "PERCEUSE";
        Can_Rotate = false;
        RegisterPins(apin, i, j);
    }

    public PERCEUSE(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        SwitchClosed = false;
        Count = 0;
        Count2 = 0;
        ClefPresente = 0;
        MaxCount2 = 8;
        ProfondeurMax = 0;
        MaxMax = 0;
        EnButee = 0;
        DedansPlaque = 0;
        DedansCylindre = 0;
        DedansSphere = 0;
        DedansCube = 0;
        Dedans = 0;
        OKpourPosePlaque = 0;
        OKpourPoseCube = 0;
        OKpourPoseCylindre = 0;
        OKpourPoseSphere = 0;
        ContactCale = 69;
        ContactPlaque = 53;
        ContactCube = 38;
        ContactCylindre = 22;
        ContactSphere = 6;
        Marge = 0;
        for(int k = 0; k < 10; k++)
            IPin[k].setLevel(-1);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        PERCEUSE perceuse = new PERCEUSE(this, i, j);
        return perceuse;
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
        if(IPin[5].getOldLevel() == 0 && IPin[5].getLevel() == 5 && IPin[6].getLevel() == 0 && IPin[7].getLevel() == 0 && IPin[8].getLevel() == 0 && Count < ContactPlaque - Marge && Dedans == 0)
        {
            ProfondeurMax = 0;
            OKpourPosePlaque = 1;
            OKpourPoseCube = 0;
            OKpourPoseCylindre = 0;
            OKpourPoseSphere = 0;
        }
        if(IPin[6].getOldLevel() == 0 && IPin[6].getLevel() == 5 && IPin[5].getLevel() == 0 && IPin[7].getLevel() == 0 && IPin[8].getLevel() == 0 && Count < ContactCube - Marge && Dedans == 0)
        {
            ProfondeurMax = 0;
            OKpourPosePlaque = 0;
            OKpourPoseCube = 1;
            OKpourPoseCylindre = 0;
            OKpourPoseSphere = 0;
        }
        if(IPin[7].getOldLevel() == 0 && IPin[7].getLevel() == 5 && IPin[5].getLevel() == 0 && IPin[6].getLevel() == 0 && IPin[8].getLevel() == 0 && Count < ContactCylindre - Marge && Dedans == 0)
        {
            ProfondeurMax = 0;
            OKpourPosePlaque = 0;
            OKpourPoseCube = 0;
            OKpourPoseCylindre = 1;
            OKpourPoseSphere = 0;
        }
        if(IPin[8].getOldLevel() == 0 && IPin[8].getLevel() == 5 && IPin[5].getLevel() == 0 && IPin[6].getLevel() == 0 && IPin[7].getLevel() == 0 && Count < ContactSphere - Marge && Dedans == 0)
        {
            ProfondeurMax = 0;
            OKpourPosePlaque = 0;
            OKpourPoseCube = 0;
            OKpourPoseCylindre = 0;
            OKpourPoseSphere = 1;
        }
        if(IPin[5].getLevel() == 0 && IPin[6].getLevel() == 0 && IPin[7].getLevel() == 0 && IPin[8].getLevel() == 0)
        {
            ProfondeurMax = 0;
            OKpourPosePlaque = 0;
            OKpourPoseCube = 0;
            OKpourPoseCylindre = 0;
            OKpourPoseSphere = 0;
        }
        if(IPin[0].getOldLevel() == 0 && IPin[0].getLevel() == 5)
        {
            IPin[0].OldLevel = IPin[0].getLevel();
            IPin[5].OldLevel = IPin[5].getLevel();
            IPin[6].OldLevel = IPin[6].getLevel();
            IPin[7].OldLevel = IPin[7].getLevel();
            IPin[8].OldLevel = IPin[8].getLevel();
            if(IPin[1].getLevel() == 5 && IPin[3].getLevel() == 5 && IPin[4].getLevel() == 0 && Count > 0)
            {
                Count--;
                if(IPin[9].getLevel() == 5 && Count > 3)
                    Count = Count - 3;
                if(Count < 0 || Count > MaxCount)
                    Count = 0;
            } else
            if(IPin[1].getLevel() == 5 && IPin[3].getLevel() == 0 && IPin[4].getLevel() == 5 && Count < MaxCount && EnButee == 0)
            {
                Count++;
                if(IPin[9].getLevel() == 5 && Count < MaxCount - 2)
                    Count = Count + 2;
                if(Count < 0 || Count > MaxCount)
                    Count = MaxCount;
            }
            ProfondeurMax = Math.max(ProfondeurMax, Count);
            MaxMax = Math.max(ProfondeurMax, MaxMax);
        }
        if((Count & 1) == 1)
            OPin[0].setLevel(5);
        else
            OPin[0].setLevel(0);
        if((Count & 2) == 2)
            OPin[1].setLevel(5);
        else
            OPin[1].setLevel(0);
        if((Count & 4) == 4)
            OPin[2].setLevel(5);
        else
            OPin[2].setLevel(0);
        if((Count & 8) == 8)
            OPin[3].setLevel(5);
        else
            OPin[3].setLevel(0);
        if((Count & 0x10) == 16)
            OPin[4].setLevel(5);
        else
            OPin[4].setLevel(0);
        if((Count & 0x20) == 32)
            OPin[5].setLevel(5);
        else
            OPin[5].setLevel(0);
        if((Count & 0x40) == 64)
            OPin[6].setLevel(5);
        else
            OPin[6].setLevel(0);
        if((Count & 0x80) == 128)
            OPin[7].setLevel(5);
        else
            OPin[7].setLevel(0);
        if(SwitchClosed)
        {
            Count = 0;
            Count2 = 0;
            DedansPlaque = 0;
            DedansCylindre = 0;
            DedansSphere = 0;
            DedansCube = 0;
            Dedans = 0;
            OKpourPosePlaque = 0;
            OKpourPoseCube = 0;
            OKpourPoseCylindre = 0;
            OKpourPoseSphere = 0;
        }
        ClefPresente = Math.max(IPin[0].getLevel(), ClefPresente);
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        EnButee = 0;
        if(IPin[5].getLevel() == 5 && IPin[6].getLevel() == 0 && IPin[7].getLevel() == 0 && IPin[8].getLevel() == 0 && DedansCube == 0 && DedansCylindre == 0 && DedansSphere == 0 && OKpourPosePlaque == 1 || DedansPlaque == 1)
        {
            g.setFill(Color.rgb(0, 0, 128));
            DrawWithOffset.fillRect(g,(l + 7) * k, (i1 + 23) * k, 4 * k + 1, 1 * k);
            g.setFill(Color.rgb(0, 0, 220));
            DrawWithOffset.fillRect(g,(l + 7) * k, (i1 + 23) * k + 3, 4 * k + 1, 1 * k - 6);
            if(Count >= ContactPlaque - Marge)
            {
                DedansPlaque = 1;
                Dedans = 1;
                OKpourPosePlaque = 0;
            } else
            if(IPin[5].getLevel() == 0)
            {
                DedansPlaque = 0;
                Dedans = 0;
            }
            if(Count >= Math.max(ProfondeurMax, ContactPlaque) && IPin[2].getLevel() == 0 || Count >= ContactPlaque + 73)
                EnButee = 1;
            MaxCount = ContactPlaque + 73;
        } else
        if(IPin[5].getLevel() == 0 && IPin[6].getLevel() == 5 && IPin[7].getLevel() == 0 && IPin[8].getLevel() == 0 && DedansPlaque == 0 && DedansCylindre == 0 && DedansSphere == 0 && OKpourPoseCube == 1 || DedansCube == 1)
        {
            g.setFill(Color.rgb(0, 128, 0));
            DrawWithOffset.fillRect(g,(l + 8) * k, (i1 + 22) * k, 2 * k + 1, 2 * k);
            if(Count >= ContactCube - Marge)
            {
                DedansCube = 1;
                Dedans = 1;
                OKpourPoseCube = 0;
            } else
            if(IPin[6].getLevel() == 0)
            {
                DedansCube = 0;
                Dedans = 0;
            }
            if(Count >= Math.max(ProfondeurMax, ContactCube) && IPin[2].getLevel() == 0 || Count >= ContactCube + 73)
                EnButee = 1;
            MaxCount = ContactCube + 73;
        } else
        if(IPin[5].getLevel() == 0 && IPin[6].getLevel() == 0 && IPin[7].getLevel() == 5 && IPin[8].getLevel() == 0 && DedansPlaque == 0 && DedansCube == 0 && DedansSphere == 0 && OKpourPoseCylindre == 1 || DedansCylindre == 1)
        {
            g.setFill(MyColor.blue);
            DrawWithOffset.fillRect(g,(l + 8) * k, (i1 + 21) * k, 2 * k + 1, 3 * k);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeLine(g,(l + 8) * k, (i1 + 23) * k + 2, (l + 10) * k, (i1 + 23) * k + 2);
            DrawWithOffset.strokeLine(g,(l + 8) * k, (i1 + 22) * k - 2, (l + 10) * k, (i1 + 22) * k - 2);
            if(Count >= ContactCylindre - Marge)
            {
                DedansCylindre = 1;
                Dedans = 1;
                OKpourPoseCylindre = 0;
            } else
            if(IPin[7].getLevel() == 0)
            {
                DedansCylindre = 0;
                Dedans = 0;
            }
            if(Count >= Math.max(ProfondeurMax, ContactCylindre) && IPin[2].getLevel() == 0 || Count >= ContactCylindre + 73)
                EnButee = 1;
            MaxCount = ContactCylindre + 73;
        } else
        if(IPin[5].getLevel() == 0 && IPin[6].getLevel() == 0 && IPin[7].getLevel() == 0 && IPin[8].getLevel() == 5 && DedansPlaque == 0 && DedansCube == 0 && DedansCylindre == 0 && OKpourPoseSphere == 1 || DedansSphere == 1)
        {
            g.setFill(MyColor.red);
            DrawWithOffset.fillOval(g,(l + 7) * k, (i1 + 20) * k, 4 * k, 4 * k);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeOval(g,(l + 7) * k, (i1 + 20) * k, 4 * k, 4 * k);
            g.setFill(MyColor.black);
            DrawWithOffset.fillRect(g,(l + 9) * k - 1, (i1 + 21) * k - 1, 1 * k - 5, 1 * k - 7);
            DrawWithOffset.fillRect(g,(l + 9) * k - 1, (i1 + 22) * k - 1, 1 * k - 5, 1 * k - 7);
            DrawWithOffset.fillRect(g,(l + 9) * k - 1, (i1 + 23) * k - 1, 1 * k - 5, 1 * k - 7);
            if(Count >= ContactSphere - Marge)
            {
                DedansSphere = 1;
                Dedans = 1;
                OKpourPoseSphere = 0;
            } else
            if(IPin[8].getLevel() == 0)
            {
                DedansSphere = 0;
                Dedans = 0;
            }
            if(Count >= Math.max(ProfondeurMax, ContactSphere) && IPin[2].getLevel() == 0 || Count >= ContactSphere + 73)
                EnButee = 1;
            MaxCount = ContactSphere + 73;
        } else
        {
            if(Count >= Math.max(ProfondeurMax, ContactCale) && IPin[2].getLevel() == 0 || Count >= ContactCale + 73)
                EnButee = 1;
            MaxCount = ContactCale + 73;
        }
        g.setFill(MyColor.gray);
        DrawWithOffset.fillRect(g,(l + 6) * k, (i1 + 24) * k, 6 * k, 1 * k);
        g.setFill(MyColor.darkGray);
        DrawWithOffset.fillRect(g,(l + 2) * k, (i1 + 25) * k, 15 * k + 1, 4 * k);
        if(Count < ProfondeurMax)
        {
            g.setStroke(Color.rgb(255, 255, 102));
            DrawWithOffset.strokeLine(g,(l + 9) * k - 1, (i1 + 20) * k, (l + 9) * k - 1, (i1 + 19) * k + 5 + (int)(0.5D * (double)ProfondeurMax - 1.0D));
            DrawWithOffset.strokeLine(g,(l + 9) * k, (i1 + 20) * k, (l + 9) * k, (i1 + 19) * k + 5 + (int)(0.5D * (double)ProfondeurMax));
            DrawWithOffset.strokeLine(g,(l + 9) * k + 1, (i1 + 20) * k, (l + 9) * k + 1, (i1 + 19) * k + 5 + (int)(0.5D * (double)ProfondeurMax - 1.0D));
        }
        g.setStroke(Color.rgb(255, 255, 102));
        if(MaxMax >= 70)
            DrawWithOffset.strokeLine(g,(l + 9) * k, (i1 + 24) * k, (l + 9) * k, (i1 + 19) * k + 5 + (int)(0.5D * (double)MaxMax));
        if(MaxMax > 71)
        {
            DrawWithOffset.strokeLine(g,(l + 9) * k - 1, (i1 + 24) * k, (l + 9) * k - 1, (i1 + 19) * k + 6 + (int)(0.5D * (double)MaxMax - 2D));
            DrawWithOffset.strokeLine(g,(l + 9) * k + 1, (i1 + 24) * k, (l + 9) * k + 1, (i1 + 19) * k + 6 + (int)(0.5D * (double)MaxMax - 2D));
        }
        g.setFill(MyColor.red);
        DrawWithOffset.fillRect(g,(l + 8) * k, (i1 + 4) * k + (int)(0.5D * (double)Count), 2 * k, 10 * k);
        DrawWithOffset.fillRect(g,(l + 8) * k + 3, (i1 + 4) * k + (int)(0.5D * (double)Count), 1 * k + 2, 11 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,(l + 8) * k, (i1 + 4) * k + (int)(0.5D * (double)Count), 2 * k, 10 * k);
        DrawWithOffset.strokeRect(g,(l + 8) * k + 3, (i1 + 14) * k + (int)(0.5D * (double)Count), 1 * k + 2, 1 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeLine(g,(l + 9) * k - 1, (i1 + 14) * k + (int)(0.5D * (double)Count), (l + 9) * k - 1, ((i1 + 19) * k + 5 + (int)(0.5D * (double)Count)) - 1);
        DrawWithOffset.strokeLine(g,(l + 9) * k, (i1 + 14) * k + (int)(0.5D * (double)Count), (l + 9) * k, (i1 + 19) * k + 5 + (int)(0.5D * (double)Count));
        DrawWithOffset.strokeLine(g,(l + 9) * k + 1, (i1 + 14) * k + (int)(0.5D * (double)Count), (l + 9) * k + 1, ((i1 + 19) * k + 5 + (int)(0.5D * (double)Count)) - 1);
        if(IPin[2].getOldLevel() == 0 || IPin[1].getOldLevel() == 0)
        {
            g.setStroke(MyColor.lightGray);
            DrawWithOffset.strokeLine(g,(l + 9) * k - 1, (i1 + 19) * k + 3 + (int)(0.5D * (double)Count), (l + 9) * k + 1, (i1 + 19) * k + (int)(0.5D * (double)Count) + 1);
            DrawWithOffset.strokeLine(g,(l + 9) * k - 1, (i1 + 18) * k + 3 + (int)(0.5D * (double)Count), (l + 9) * k + 1, (i1 + 18) * k + (int)(0.5D * (double)Count) + 1);
            DrawWithOffset.strokeLine(g,(l + 9) * k - 1, (i1 + 17) * k + 3 + (int)(0.5D * (double)Count), (l + 9) * k + 1, (i1 + 17) * k + (int)(0.5D * (double)Count) + 1);
            g.setFill(MyColor.black);
            DrawWithOffset.fillRect(g,(l + 8) * k + 11, (i1 + 14) * k + 3 + (int)(0.5D * (double)Count), 1 * k - 5, 1 * k - 5);
        }
        g.setFill(MyColor.darkGray);
        DrawWithOffset.fillRect(g,(l + 5) * k, (i1 + 2) * k, 10 * k, 2 * k);
        DrawWithOffset.fillRect(g,(l + 11) * k, (i1 + 4) * k, 2 * k, 2 * k - 3);
        DrawWithOffset.fillRect(g,(l + 13) * k, (i1 + 2) * k, 2 * k + 3, 25 * k);
        DrawWithOffset.fillRect(g,(l + 7) * k, (i1 + 4) * k, 4 * k, 10 * k - 3);
        Count2 = Count2 + 3;
        if(Count2 < 0 || Count2 > MaxCount2)
            Count2 = 0;
        if(ClefPresente == 0)
            Count2 = 0;
        if(IPin[2].getLevel() == 5 && IPin[1].getLevel() == 5)
        {
            g.setStroke(MyColor.lightGray);
            DrawWithOffset.strokeLine(g,(l + 9) * k - 1, (((i1 + 19) * k + 3) - Count2) + (int)(0.5D * (double)Count), (l + 9) * k + 1, ((i1 + 19) * k - Count2) + (int)(0.5D * (double)Count) + 1);
            DrawWithOffset.strokeLine(g,(l + 9) * k - 1, (((i1 + 18) * k + 3) - Count2) + (int)(0.5D * (double)Count), (l + 9) * k + 1, ((i1 + 18) * k - Count2) + (int)(0.5D * (double)Count) + 1);
            DrawWithOffset.strokeLine(g,(l + 9) * k - 1, (((i1 + 17) * k + 3) - Count2) + (int)(0.5D * (double)Count), (l + 9) * k + 1, ((i1 + 17) * k - Count2) + (int)(0.5D * (double)Count) + 1);
            g.setFill(MyColor.darkGray);
            DrawWithOffset.fillRect(g,((l + 8) * k + 11) - Count2, (i1 + 14) * k + 3 + (int)(0.5D * (double)Count), 1 * k - 5, 1 * k - 5);
        }
        DrawInputPins(g, l, i1, k);
        g.setFill(MyColor.white);
        DrawWithOffset.fillRect(g,(l + 11) * k - 2, (i1 + 3) * k - 6, 2 * k + 4, 1 * k + 3);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,(l + 11) * k - 2, (i1 + 3) * k - 6, 2 * k + 4, 1 * k + 3);
        g.setFill(MyColor.red);
        DrawWithOffset.fillText(g,"" + Integer.toString(Count), (l + 11) * k, (i1 + 4) * k - 4);
        g.setFill(MyColor.white);
        DrawWithOffset.fillRect(g,(l + 11) * k - 2, (i1 + 4) * k - 2, 2 * k + 4, 1 * k + 3);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,(l + 11) * k - 2, (i1 + 4) * k - 2, 2 * k + 4, 1 * k + 3);
        g.setFill(MyColor.blue);
        DrawWithOffset.fillText(g,"" + Integer.toString(ProfondeurMax), (l + 11) * k, (i1 + 5) * k);
        g.setFill(MyColor.gray);
        DrawWithOffset.fillRect(g,(l + 5) * k, (i1 + 3) * k - 3, k * 1 + 2, k * 1 - 5);
        g.setFill(MyColor.gray);
        DrawWithOffset.fillRect(g,(l + 5) * k + 2, (i1 + 3) * k - 3, k * 1 - 6, k * 1);
        DrawWithOffset.fillRect(g,(l + 6) * k - 2, (i1 + 3) * k - 3, k * 1 - 6, k * 1);
        g.setFill(MyColor.red);
        DrawWithOffset.fillText(g,"clé?", (l + 3) * k, (i1 + 3) * k + 2);
        if(EnButee == 1)
            g.setFill(MyColor.red);
        else
        if(IPin[1].getLevel() == 0 || ClefPresente == 0)
            g.setFill(MyColor.gray);
        else
            g.setFill(MyColor.yellow);
        DrawWithOffset.fillOval(g,(l + 6) * k + 5, (i1 + 2) * k + 4, 1 * k - 2, 1 * k - 2);
        g.setStroke(MyColor.darkGray);
        DrawWithOffset.strokeOval(g,(l + 6) * k + 5, (i1 + 2) * k + 4, 1 * k - 2, 1 * k - 2);
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    boolean SwitchClosed;
    int Count;
    int Count2;
    int ClefPresente;
    int MaxCount;
    int MaxCount2;
    int ProfondeurMax;
    int MaxMax;
    int EnButee;
    int DedansPlaque;
    int DedansCylindre;
    int DedansSphere;
    int DedansCube;
    int Dedans;
    int OKpourPosePlaque;
    int OKpourPoseCube;
    int OKpourPoseCylindre;
    int OKpourPoseSphere;
    int ContactCale;
    int ContactPlaque;
    int ContactCube;
    int ContactCylindre;
    int ContactSphere;
    int Marge;
    static Color LEDColorOff;

    static
    {
        LEDColorOff = MyColor.blue;
    }
}
