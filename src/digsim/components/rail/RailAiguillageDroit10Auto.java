package digsim.components.rail;

import digsim.InputPin;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DigSim;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, OutputPin, DigSim,
//            MyColor, Pin

public class RailAiguillageDroit10Auto extends ElectronicComponent
{

    public RailAiguillageDroit10Auto(Pin apin[][], int i, int j)
    {
        super(i, j, 15, 5, 1, 1, 8, 3, 4, 3);
        Count = 0;
        CountXY = 0;
        Train = false;
        COLLISION = false;
        Aiguill = false;
        Manuel = false;
        oldH = 0;
        oldT = 0;
        lngr = 40;
        NbrW = 3;
        IPin[0] = new InputPin("", 0, 3, 1, 0, 0, 0, 0);
        IPin[1] = new InputPin("", 10, 4, -1, 0, 0, 0, 0);
        IPin[2] = new InputPin("", 0, 1, 1, 0, 0, 0, 0);
        IPin[3] = new InputPin("", 6, 6, 0, -2, 0, 0, 1);
        OPin[0] = new OutputPin("", 10, 3, -1, 0, 0, 0, 0);
        OPin[1] = new OutputPin("", 0, 4, 1, 0, 0, 0, 0);
        OPin[2] = new OutputPin("", 0, 2, 1, 0, 0, 0, 0);
        ComposantFont = Font.font("Sans-serif", 10);
        IPin[3].setLevel(-1);
        AIG = OldAIG = 5 * Math.round((int)(Math.random() * 2D));
        if(AIG == 5)
            Aiguill = true;
        ComponentName = "Aiguillage ''10'' auto D";
        ClassName = "RailAiguillageDroit10Auto";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public RailAiguillageDroit10Auto(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Count = 0;
        CountXY = 0;
        Train = false;
        COLLISION = false;
        Aiguill = false;
        Manuel = false;
        oldH = 0;
        oldT = 0;
        lngr = 40;
        NbrW = 3;
        ComposantFont = Font.font("Sans-serif", 10);
        IPin[3].setLevel(-1);
        AIG = OldAIG = 5 * Math.round((int)(Math.random() * 2D));
        if(AIG == 5)
            Aiguill = true;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        RailAiguillageDroit10Auto railaiguillagedroit10auto = new RailAiguillageDroit10Auto(this, i, j);
        return railaiguillagedroit10auto;
    }

    public boolean SimMouseDown()
    {
        if(!Train)
        {
            Aiguill = !Aiguill;
            Manuel = true;
        }
        return true;
    }

    public void InitBeforeSimulate()
    {
        if(Train && Horaire)
        {
            if(H == 0)
                H = 5;
            else
                H = 0;
            T = 0;
        }
        if(Train && !Horaire)
        {
            if(T == 0)
                T = 5;
            else
                T = 0;
            H = 0;
        }
    }

    public void SimulateLogic()
    {
        EHdroit = IPin[0].getLevel();
        ETcommun = IPin[1].getLevel();
        EHcourbe = IPin[2].getLevel();
        AIG = IPin[3].getLevel();
        if(AIG != OldAIG)
            Manuel = false;
        OldAIG = AIG;
        if(!Manuel && !Train)
            if(AIG == 5)
                Aiguill = false;
            else
            if(AIG == 0)
                Aiguill = true;
        if(EHdroit == 5 && !Train)
            Aiguill = false;
        if(EHcourbe == 5 && !Train)
            Aiguill = true;
        if(!Train)
        {
            if(ETcommun == 5 && !Train)
            {
                Count = lngr;
                Train = true;
                Horaire = false;
            }
            if(!Train && (EHdroit == 5 || EHcourbe == 5))
            {
                Count = -lngr;
                Train = true;
                Horaire = true;
            }
        }
        if(!Aiguill && T != oldT)
        {
            if(EHdroit == 5 || EHcourbe == 5)
            {
                COLLISION = true;
                DigSim.traincrash.play();
            }
            if(T == 0 && Count <= -lngr && Train)
            {
                Count = 0;
                Train = false;
            }
            if(Train && Count > -lngr)
                Count = Count - 2;
            if(Count == -lngr)
                STdroit = 5;
            else
                STdroit = 0;
            SHcom = 0;
        }
        if(Aiguill && T != oldT)
        {
            if(EHdroit == 5 || EHcourbe == 5)
            {
                COLLISION = true;
                DigSim.traincrash.play();
            }
            if(T == 0 && Count <= -lngr && Train)
            {
                Count = 0;
                Train = false;
            }
            if(Train && Count > -lngr)
                Count = Count - 2;
            if(Count == -lngr)
                STcourbe = 5;
            else
                STcourbe = 0;
            SHcom = 0;
        }
        if(H != oldH)
        {
            if(ETcommun == 5 || !Aiguill && EHcourbe == 5 || Aiguill && EHdroit == 5)
            {
                COLLISION = true;
                DigSim.traincrash.play();
            }
            if(H == 0 && Count >= lngr && Train)
            {
                Count = 0;
                Train = false;
            }
            if(Train && Count < lngr)
                Count = Count + 2;
            if(Count == lngr)
                SHcom = 5;
            else
                SHcom = 0;
            STdroit = 0;
            STcourbe = 0;
        }
        OPin[0].setLevel(SHcom);
        OPin[1].setLevel(STdroit);
        OPin[2].setLevel(STcourbe);
        oldH = H;
        oldT = T;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFont(ComposantFont);
        if(IPin[0].PinDim.width > 0)
        {
            g.setFill(MyColor.lightGray);
            for(int j1 = 2; j1 < 6; j1++)
                DrawWithOffset.fillRect(g,l * k + 11 * j1, (i1 + 2) * k + 5, 0 * k + 4, 2 * k - 1);

            DrawWithOffset.fillRect(g,l * k + 44, (i1 + 2) * k + 2, 0 * k + 4, 2 * k - 1);
            DrawWithOffset.fillRect(g,l * k + 33, i1 * k + 12, 0 * k + 4, 2 * k - 1);
            DrawWithOffset.fillRect(g,l * k + 22, i1 * k + 7, 0 * k + 4, 2 * k - 1);
            DrawInputPins(g, l, i1, k);
            DrawOutputPins(g, l, i1, k);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeLine(g,(l + 10) * k, (i1 + 3) * k - 1, (l + 8) * k - 5, (i1 + 3) * k - 1);
            DrawWithOffset.strokeLine(g,(l + 10) * k, (i1 + 3) * k, (l + 8) * k - 5, (i1 + 3) * k);
            DrawWithOffset.strokeLine(g,(l + 10) * k, (i1 + 3) * k + 1, (l + 8) * k - 4, (i1 + 3) * k + 1);
            DrawWithOffset.strokeArc(g,(l + 2) * k + 3, (i1 - 9) * k - 1, k * 12, k * 12, 270, -35, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 2) * k + 3, (i1 - 9) * k + 1, k * 12, k * 12, 270, -35, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 2) * k + 3, (i1 - 9) * k, k * 12, k * 12, 270, -35, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 4) * k - 2, (i1 + 1) * k - 1, k * 12, k * 12, 90, -35, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 4) * k - 2, (i1 + 1) * k + 1, k * 12, k * 12, 90, -35, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 4) * k - 2, (i1 + 1) * k, k * 12, k * 12, 90, -35, ArcType.OPEN);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 1) * k - 1, l * k, (i1 + 1) * k - 1);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 1) * k, l * k, (i1 + 1) * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 1) * k + 1, l * k, (i1 + 1) * k + 1);
            DrawWithOffset.strokeLine(g,l * k, (i1 + 2) * k, l * k, (i1 + 2) * k + 2);
            DrawWithOffset.strokeArc(g,(l - 5) * k - 1, (i1 + 2) * k - 2, k * 12, k * 12, 82, -30, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 5) * k - 1, (i1 + 2) * k - 1, k * 12, k * 12, 90, -35, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 5) * k - 1, (i1 + 2) * k, k * 12, k * 12, 90, -35, ArcType.OPEN);
            DrawWithOffset.strokeLine(g,(l + 5) * k - 2, (i1 + 3) * k + 3, (l + 5) * k + 4, (i1 + 3) * k + 4);
            DrawWithOffset.strokeLine(g,(l + 5) * k - 1, (i1 + 3) * k + 4, (l + 5) * k + 4, (i1 + 3) * k + 5);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k - 1, l * k, (i1 + 2) * k - 1);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k, l * k, (i1 + 2) * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k + 1, l * k, (i1 + 2) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 5) * k - 2, (i1 + 3) * k - 1, (l + 5) * k + 1, (i1 + 3) * k - 1);
            DrawWithOffset.strokeLine(g,(l + 5) * k - 1, (i1 + 3) * k, (l + 5) * k + 4, (i1 + 3) * k);
            DrawWithOffset.strokeLine(g,(l + 5) * k + 1, (i1 + 3) * k + 1, (l + 5) * k + 4, (i1 + 3) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 3) * k - 1, l * k, (i1 + 3) * k - 1);
            DrawWithOffset.strokeLine(g,(l + 4) * k + 3, (i1 + 3) * k, l * k, (i1 + 3) * k);
            DrawWithOffset.strokeLine(g,(l + 4) * k + 5, (i1 + 3) * k + 1, l * k, (i1 + 3) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 10) * k, (i1 + 3) * k, (l + 10) * k, (i1 + 3) * k - 2);
            DrawWithOffset.fillRect(g,l * k, (i1 + 4) * k - 1, 10 * k + 1, 0 * k + 3);
            DrawWithOffset.strokeLine(g,l * k, (i1 + 4) * k, l * k, (i1 + 4) * k + 2);
            DrawWithOffset.strokeLine(g,(l + 5) * k - 3, (i1 + 3) * k + 3, (l + 4) * k - 8, (i1 + 3) * k + 3);
            DrawWithOffset.strokeLine(g,(l + 5) * k - 4, (i1 + 3) * k + 5, (l + 4) * k - 8, (i1 + 3) * k + 5);
            DrawWithOffset.strokeLine(g,(l + 5) * k + 2, (i1 + 2) * k + 5, (l + 5) * k - 10, (i1 + 2) * k - 2);
            DrawWithOffset.strokeLine(g,(l + 5) * k - 2, (i1 + 2) * k + 6, (l + 5) * k - 13, (i1 + 2) * k);
            if(!Aiguill)
            {
                DrawWithOffset.strokeLine(g,(l + 5) * k + 5, (i1 + 3) * k + 1, (l + 7) * k + 3, (i1 + 3) * k + 1);
                DrawWithOffset.strokeLine(g,(l + 5) * k + 5, (i1 + 3) * k + 5, (l + 7) * k + 2, (i1 + 3) * k + 5);
                g.setStroke(MyColor.blue);
                DrawWithOffset.strokeLine(g,(l + 5) * k, (i1 + 5) * k - 5, (l + 6) * k - 6, (i1 + 5) * k - 5);
                DrawWithOffset.strokeLine(g,(l + 5) * k, (i1 + 5) * k - 4, (l + 6) * k, (i1 + 5) * k - 4);
                DrawWithOffset.strokeLine(g,(l + 5) * k, (i1 + 5) * k - 3, (l + 6) * k - 6, (i1 + 5) * k - 3);
            } else
            {
                DrawWithOffset.strokeLine(g,(l + 5) * k + 5, (i1 + 3) * k + 1, (l + 7) * k + 2, (i1 + 3) * k + 3);
                DrawWithOffset.strokeLine(g,(l + 5) * k + 5, (i1 + 3) * k + 5, (l + 7) * k + 3, (i1 + 3) * k + 7);
                g.setStroke(MyColor.red);
                DrawWithOffset.strokeLine(g,(l + 6) * k + 6, (i1 + 5) * k - 5, (l + 7) * k, (i1 + 5) * k - 5);
                DrawWithOffset.strokeLine(g,(l + 6) * k, (i1 + 5) * k - 4, (l + 7) * k, (i1 + 5) * k - 4);
                DrawWithOffset.strokeLine(g,(l + 6) * k + 6, (i1 + 5) * k - 3, (l + 7) * k, (i1 + 5) * k - 3);
            }
            if(COLLISION)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 5) * k - 4, (i1 + 3) * k, 1 * k + 1, 1 * k + 1);
            }
            g.setFill(MyColor.black);
            if(Train && (!Horaire || Count != lngr) && (Horaire || Count != -lngr))
            {
                for(int k1 = -NbrW; k1 <= NbrW; k1++)
                {
                    if(!Aiguill)
                    {
                        DrawWithOffset.fillOval(g,(l + 4) * k + Count + 3 * k1, (i1 + 2) * k + 5, 2 * k, 2 * k);
                        continue;
                    }
                    byte byte0 = -18;
                    byte byte4 = 15;
                    if(Count > byte4)
                    {
                        DrawWithOffset.fillOval(g,(l + 4) * k + Count + 3 * k1, (i1 + 2) * k + 5, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte0 && Count <= byte0 + 2)
                    {
                        CountXY = 8 - (int)(0.20000000000000001D * (double)k1 + ((double)Count * 16D) / (1.0D * (double)(byte4 - byte0)));
                        DrawWithOffset.fillOval(g,(l + 4) * k + Count + 3 * k1, ((i1 + 2) * k + 5) - CountXY, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte0 + 2 && Count <= byte0 + 5)
                    {
                        CountXY = 8 - (int)(0.5D * (double)k1 + ((double)Count * 16D) / (1.0D * (double)(byte4 - byte0)));
                        DrawWithOffset.fillOval(g,(l + 4) * k + Count + 3 * k1, ((i1 + 2) * k + 5) - CountXY, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte0 + 5 && Count <= byte0 + 10)
                    {
                        CountXY = 8 - (int)(1.0D * (double)k1 + ((double)Count * 16D) / (1.0D * (double)(byte4 - byte0)));
                        DrawWithOffset.fillOval(g,(l + 4) * k + Count + 3 * k1, ((i1 + 2) * k + 5) - CountXY, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte0 + 10 && Count <= byte4 - 10)
                    {
                        CountXY = 8 - (int)(1.5D * (double)k1 + ((double)Count * 16D) / (1.0D * (double)(byte4 - byte0)));
                        DrawWithOffset.fillOval(g,(l + 4) * k + Count + 3 * k1, ((i1 + 2) * k + 5) - CountXY, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte4 - 10 && Count <= byte4 - 5)
                    {
                        CountXY = 8 - (int)(1.0D * (double)k1 + ((double)Count * 16D) / (1.0D * (double)(byte4 - byte0)));
                        DrawWithOffset.fillOval(g,(l + 4) * k + Count + 3 * k1, ((i1 + 2) * k + 5) - CountXY, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte4 - 5 && Count <= byte4 - 2)
                    {
                        CountXY = 8 - (int)(0.5D * (double)k1 + ((double)Count * 16D) / (1.0D * (double)(byte4 - byte0)));
                        DrawWithOffset.fillOval(g,(l + 4) * k + Count + 3 * k1, ((i1 + 2) * k + 5) - CountXY, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte4 - 2 && Count <= byte4)
                    {
                        CountXY = 8 - (int)(0.20000000000000001D * (double)k1 + ((double)Count * 16D) / (1.0D * (double)(byte4 - byte0)));
                        DrawWithOffset.fillOval(g,(l + 4) * k + Count + 3 * k1, ((i1 + 2) * k + 5) - CountXY, 2 * k, 2 * k);
                    } else
                    {
                        DrawWithOffset.fillOval(g,(l + 4) * k + Count + 3 * k1, i1 * k + 5, 2 * k, 2 * k);
                    }
                }

            }
            DrawWithOffset.fillText(g,"..", (l + 9) * k + 2, (i1 + 3) * k + 5);
        } else
        if(IPin[0].PinDim.height > 0)
        {
            g.setFill(MyColor.lightGray);
            for(int l1 = 2; l1 < 6; l1++)
                DrawWithOffset.fillRect(g,l * k + 5, i1 * k + 11 * l1, 2 * k - 1, 0 * k + 4);

            DrawWithOffset.fillRect(g,(l + 2) * k + 2, i1 * k + 22, 2 * k + 1, 0 * k + 4);
            DrawWithOffset.fillRect(g,l * k + 12, i1 * k + 33, 2 * k + 1, 0 * k + 4);
            DrawWithOffset.fillRect(g,l * k + 7, i1 * k + 44, 2 * k + 1, 0 * k + 4);
            DrawInputPins(g, l, i1, k);
            DrawOutputPins(g, l, i1, k);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeLine(g,(l + 2) * k - 1, (i1 + 10) * k, (l + 2) * k - 1, (i1 + 7) * k - 1);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 10) * k, (l + 2) * k, (i1 + 7) * k - 2);
            DrawWithOffset.strokeLine(g,(l + 2) * k + 1, (i1 + 10) * k, (l + 2) * k + 1, (i1 + 7) * k - 3);
            DrawWithOffset.strokeArc(g,(l + 2) * k - 1, (i1 + 2) * k - 2, k * 12, k * 12, 180, -35, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 2) * k, (i1 + 2) * k - 2, k * 12, k * 12, 180, -35, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 2) * k + 1, (i1 + 2) * k - 2, k * 12, k * 12, 185, -30, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 8) * k, (i1 - 5) * k, k * 12, k * 12, -8, -35, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 8) * k + 1, (i1 - 5) * k, k * 12, k * 12, -8, -35, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 8) * k + 2, (i1 - 5) * k, k * 12, k * 12, -8, -38, ArcType.OPEN);
            DrawWithOffset.strokeLine(g,(l + 4) * k - 1, i1 * k, (l + 4) * k - 1, (i1 + 2) * k);
            DrawWithOffset.strokeLine(g,(l + 4) * k, i1 * k, (l + 4) * k, (i1 + 2) * k);
            DrawWithOffset.strokeLine(g,(l + 4) * k + 1, i1 * k, (l + 4) * k + 1, (i1 + 2) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k, i1 * k, (l + 3) * k - 2, i1 * k);
            DrawWithOffset.strokeLine(g,(l + 1) * k + 5, (i1 + 5) * k - 2, (l + 1) * k + 5, (i1 + 5) * k + 2);
            DrawWithOffset.strokeLine(g,(l + 1) * k + 4, (i1 + 5) * k - 2, (l + 1) * k + 4, (i1 + 5) * k + 3);
            DrawWithOffset.strokeArc(g,(l - 6) * k - 5, (i1 - 8) * k - 7, k * 10, k * 15, -45, 20, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 6) * k - 4, (i1 - 8) * k - 7, k * 10, k * 15, -45, 20, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 6) * k - 3, (i1 - 8) * k - 7, k * 10, k * 15, -45, 20, ArcType.OPEN);
            DrawWithOffset.strokeLine(g,(l + 3) * k - 1, i1 * k, (l + 3) * k - 1, (i1 + 2) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k, i1 * k, (l + 3) * k, (i1 + 2) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, i1 * k, (l + 3) * k + 1, (i1 + 2) * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k - 1, (i1 + 5) * k + 1, (l + 2) * k - 1, (i1 + 5) * k + 6);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 5) * k - 1, (l + 2) * k, (i1 + 5) * k + 5);
            DrawWithOffset.strokeLine(g,(l + 2) * k + 1, (i1 + 5) * k - 3, (l + 2) * k + 1, (i1 + 5) * k + 3);
            DrawWithOffset.strokeLine(g,(l + 2) * k + 1, (i1 + 4) * k, (l + 2) * k + 1, i1 * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 4) * k + 2, (l + 2) * k, i1 * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k - 1, (i1 + 4) * k + 3, (l + 2) * k - 1, i1 * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 10) * k - 1, (l + 2) * k + 2, (i1 + 10) * k);
            DrawWithOffset.fillRect(g,(l + 1) * k - 1, i1 * k, 0 * k + 3, 10 * k + 1);
            DrawWithOffset.strokeLine(g,(l + 1) * k, i1 * k, (l + 1) * k - 2, i1 * k);
            DrawWithOffset.strokeLine(g,(l + 1) * k + 3, (i1 + 5) * k - 4, (l + 1) * k + 3, (i1 + 4) * k - 8);
            DrawWithOffset.strokeLine(g,(l + 1) * k + 5, (i1 + 5) * k - 3, (l + 1) * k + 5, (i1 + 4) * k - 8);
            DrawWithOffset.strokeLine(g,(l + 3) * k - 7, (i1 + 5) * k - 4, (l + 3) * k - 1, (i1 + 5) * k - 14);
            DrawWithOffset.strokeLine(g,(l + 3) * k - 5, (i1 + 5) * k - 2, (l + 3) * k + 2, (i1 + 5) * k - 13);
            if(!Aiguill)
            {
                DrawWithOffset.strokeLine(g,(l + 2) * k - 1, (i1 + 7) * k - 2, (l + 2) * k - 1, (i1 + 5) * k + 3);
                DrawWithOffset.strokeLine(g,(l + 1) * k + 3, (i1 + 7) * k - 2, (l + 1) * k + 4, (i1 + 5) * k + 3);
                g.setStroke(MyColor.blue);
                DrawWithOffset.strokeLine(g,(l + 1) * k - 5, (i1 + 5) * k, (l + 1) * k - 5, (i1 + 6) * k - 6);
                DrawWithOffset.strokeLine(g,(l + 1) * k - 4, (i1 + 5) * k, (l + 1) * k - 4, (i1 + 6) * k);
                DrawWithOffset.strokeLine(g,(l + 1) * k - 3, (i1 + 5) * k, (l + 1) * k - 3, (i1 + 6) * k - 6);
            } else
            {
                DrawWithOffset.strokeLine(g,(l + 2) * k - 3, (i1 + 7) * k - 2, (l + 2) * k - 1, (i1 + 5) * k + 3);
                DrawWithOffset.strokeLine(g,(l + 1) * k + 2, (i1 + 7) * k - 5, (l + 1) * k + 4, (i1 + 5) * k + 3);
                g.setStroke(MyColor.red);
                DrawWithOffset.strokeLine(g,(l + 1) * k - 5, (i1 + 6) * k + 6, (l + 1) * k - 5, (i1 + 7) * k);
                DrawWithOffset.strokeLine(g,(l + 1) * k - 4, (i1 + 6) * k, (l + 1) * k - 4, (i1 + 7) * k);
                DrawWithOffset.strokeLine(g,(l + 1) * k - 3, (i1 + 6) * k + 6, (l + 1) * k - 3, (i1 + 7) * k);
            }
            if(COLLISION)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 5) * k - 4, 1 * k + 1, 1 * k + 1);
            }
            g.setFill(MyColor.black);
            if(Train)
            {
                for(int i2 = -NbrW; i2 <= NbrW; i2++)
                {
                    if(!Aiguill)
                    {
                        DrawWithOffset.fillOval(g,l * k + 5, ((i1 + 4) * k + Count) - 3 * i2, 2 * k, 2 * k);
                        continue;
                    }
                    byte byte1 = -30;
                    byte byte5 = 10;
                    if(Count < byte1)
                    {
                        DrawWithOffset.fillOval(g,(l + 2) * k + 5, ((i1 + 4) * k + Count) - 3 * i2, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte1 && Count <= byte1 + 2)
                    {
                        CountXY = -5 + (int)(0.20000000000000001D * (double)i2 - ((double)Count * 16D) / (1.0D * (double)(byte5 - byte1)));
                        DrawWithOffset.fillOval(g,(l + 2) * k + CountXY, ((i1 + 4) * k + Count) - 3 * i2, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte1 + 2 && Count <= byte1 + 5)
                    {
                        CountXY = -5 + (int)(0.5D * (double)i2 - ((double)Count * 16D) / (1.0D * (double)(byte5 - byte1)));
                        DrawWithOffset.fillOval(g,(l + 2) * k + CountXY, ((i1 + 4) * k + Count) - 3 * i2, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte1 + 5 && Count <= byte1 + 10)
                    {
                        CountXY = -5 + (int)(1.0D * (double)i2 - ((double)Count * 16D) / (1.0D * (double)(byte5 - byte1)));
                        DrawWithOffset.fillOval(g,(l + 2) * k + CountXY, ((i1 + 4) * k + Count) - 3 * i2, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte1 + 10 && Count <= byte5 - 10)
                    {
                        CountXY = -5 + (int)(1.5D * (double)i2 - ((double)Count * 16D) / (1.0D * (double)(byte5 - byte1)));
                        DrawWithOffset.fillOval(g,(l + 2) * k + CountXY, ((i1 + 4) * k + Count) - 3 * i2, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte5 - 10 && Count <= byte5 - 5)
                    {
                        CountXY = -5 + (int)(1.0D * (double)i2 - ((double)Count * 16D) / (1.0D * (double)(byte5 - byte1)));
                        DrawWithOffset.fillOval(g,(l + 2) * k + CountXY, ((i1 + 4) * k + Count) - 3 * i2, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte5 - 5 && Count <= byte5 - 2)
                    {
                        CountXY = -5 + (int)(0.5D * (double)i2 - ((double)Count * 16D) / (1.0D * (double)(byte5 - byte1)));
                        DrawWithOffset.fillOval(g,(l + 2) * k + CountXY, ((i1 + 4) * k + Count) - 3 * i2, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte5 - 2 && Count <= byte5)
                    {
                        CountXY = -5 + (int)(0.20000000000000001D * (double)i2 - ((double)Count * 16D) / (1.0D * (double)(byte5 - byte1)));
                        DrawWithOffset.fillOval(g,(l + 2) * k + CountXY, ((i1 + 4) * k + Count) - 3 * i2, 2 * k, 2 * k);
                    } else
                    {
                        DrawWithOffset.fillOval(g,l * k + 5, ((i1 + 4) * k + Count) - 3 * i2, 2 * k, 2 * k);
                    }
                }

            }
            DrawWithOffset.fillText(g,":", (l + 1) * k + 3, (i1 + 9) * k + 4);
        } else
        if(IPin[0].PinDim.width < 0)
        {
            g.setFill(MyColor.lightGray);
            for(int j2 = 2; j2 < 6; j2++)
                DrawWithOffset.fillRect(g,l * k + 11 * j2, i1 * k + 5, 0 * k + 4, 2 * k - 1);

            DrawWithOffset.fillRect(g,l * k + 55, (i1 + 2) * k + 2, 0 * k + 4, 2 * k + 1);
            DrawWithOffset.fillRect(g,l * k + 44, i1 * k + 12, 0 * k + 4, 2 * k + 1);
            DrawWithOffset.fillRect(g,l * k + 33, i1 * k + 7, 0 * k + 4, 2 * k + 1);
            DrawInputPins(g, l, i1, k);
            DrawOutputPins(g, l, i1, k);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeLine(g,l * k, (i1 + 2) * k - 1, (l + 3) * k + 2, (i1 + 2) * k - 1);
            DrawWithOffset.strokeLine(g,l * k, (i1 + 2) * k, (l + 3) * k + 3, (i1 + 2) * k);
            DrawWithOffset.strokeLine(g,l * k, (i1 + 2) * k + 1, (l + 3) * k + 1, (i1 + 2) * k + 1);
            DrawWithOffset.strokeArc(g,(l - 4) * k + 2, (i1 + 2) * k, k * 12, k * 12, 90, -35, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 4) * k + 2, (i1 + 2) * k + 1, k * 12, k * 12, 90, -35, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 4) * k + 2, (i1 + 2) * k + 2, k * 12, k * 12, 90, -35, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 3) * k, (i1 - 8) * k + 2, k * 12, k * 12, 262, -35, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 3) * k, (i1 - 8) * k + 1, k * 12, k * 12, 270, -35, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 3) * k, (i1 - 8) * k, k * 12, k * 12, 270, -35, ArcType.OPEN);
            DrawWithOffset.strokeLine(g,(l + 8) * k, (i1 + 4) * k - 1, (l + 10) * k, (i1 + 4) * k - 1);
            DrawWithOffset.strokeLine(g,(l + 8) * k, (i1 + 4) * k, (l + 10) * k, (i1 + 4) * k);
            DrawWithOffset.strokeLine(g,(l + 8) * k, (i1 + 4) * k + 1, (l + 10) * k, (i1 + 4) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 10) * k, (i1 + 3) * k, (l + 10) * k, (i1 + 3) * k - 2);
            DrawWithOffset.strokeLine(g,(l + 4) * k + 5, (i1 + 1) * k + 4 + 1, (l + 4) * k + 10, (i1 + 1) * k + 3 + 1);
            DrawWithOffset.strokeLine(g,(l + 4) * k + 5, (i1 + 1) * k + 3 + 1, (l + 4) * k + 10, (i1 + 1) * k + 4 + 1);
            DrawWithOffset.strokeArc(g,(l + 3) * k + 7, ((i1 + 2) - 8) * k - 5, k * 15, k * 10, 225, 20, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 3) * k + 7, ((i1 + 2) - 8) * k - 4, k * 15, k * 10, 225, 20, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 3) * k + 7, ((i1 + 2) - 8) * k - 3, k * 15, k * 10, 225, 20, ArcType.OPEN);
            DrawWithOffset.strokeLine(g,(l + 8) * k, (i1 + 3) * k - 1, (l + 10) * k, (i1 + 3) * k - 1);
            DrawWithOffset.strokeLine(g,(l + 8) * k, (i1 + 3) * k, (l + 10) * k, (i1 + 3) * k);
            DrawWithOffset.strokeLine(g,(l + 8) * k, (i1 + 3) * k + 1, (l + 10) * k, (i1 + 3) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 5) * k - 3, (i1 + 2) * k + 1, (l + 5) * k + 4, (i1 + 2) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 5) * k - 5, (i1 + 2) * k, (l + 5) * k + 2, (i1 + 2) * k);
            DrawWithOffset.strokeLine(g,(l + 5) * k - 6, (i1 + 2) * k - 1, (l + 5) * k - 1, (i1 + 2) * k - 1);
            DrawWithOffset.strokeLine(g,(l + 6) * k, (i1 + 2) * k + 1, (l + 10) * k, (i1 + 2) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 6) * k - 2, (i1 + 2) * k, (l + 10) * k, (i1 + 2) * k);
            DrawWithOffset.strokeLine(g,(l + 6) * k - 3, (i1 + 2) * k - 1, (l + 10) * k, (i1 + 2) * k - 1);
            DrawWithOffset.strokeLine(g,(l + 10) * k - 1, (i1 + 1) * k, (l + 10) * k, (i1 + 1) * k - 2);
            DrawWithOffset.strokeLine(g,l * k, (i1 + 1) * k - 1, (l + 10) * k, (i1 + 1) * k - 1);
            DrawWithOffset.strokeLine(g,l * k, (i1 + 1) * k, (l + 10) * k, (i1 + 1) * k);
            DrawWithOffset.strokeLine(g,l * k, (i1 + 1) * k + 1, (l + 10) * k, (i1 + 1) * k + 1);
            DrawWithOffset.strokeLine(g,l * k, (i1 + 2) * k, l * k, (i1 + 2) * k + 2);
            DrawWithOffset.strokeLine(g,(l + 5) * k + 4, (i1 + 1) * k + 3, (l + 6) * k + 8, (i1 + 1) * k + 3);
            DrawWithOffset.strokeLine(g,(l + 5) * k + 3, (i1 + 1) * k + 5, (l + 6) * k + 8, (i1 + 1) * k + 5);
            DrawWithOffset.strokeLine(g,(l + 5) * k + 1, (i1 + 3) * k - 5, (l + 5) * k + 13, (i1 + 3) * k + 2);
            DrawWithOffset.strokeLine(g,(l + 5) * k + 4, (i1 + 3) * k - 7, (l + 5) * k + 15, (i1 + 3) * k - 1);
            if(!Aiguill)
            {
                DrawWithOffset.strokeLine(g,(l + 3) * k + 2, (i1 + 2) * k - 1, (l + 5) * k - 3, (i1 + 2) * k - 1);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 2, (i1 + 1) * k + 3, (l + 5) * k - 3, (i1 + 1) * k + 4);
                g.setStroke(MyColor.blue);
                DrawWithOffset.strokeLine(g,(l + 4) * k + 6, (i1 + 1) * k - 5, (l + 5) * k, (i1 + 1) * k - 5);
                DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 1) * k - 4, (l + 5) * k, (i1 + 1) * k - 4);
                DrawWithOffset.strokeLine(g,(l + 4) * k + 6, (i1 + 1) * k - 3, (l + 5) * k, (i1 + 1) * k - 3);
            } else
            {
                DrawWithOffset.strokeLine(g,(l + 3) * k + 2, (i1 + 2) * k - 3, (l + 5) * k - 3, (i1 + 2) * k - 1);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 1) * k + 2, (l + 5) * k - 3, (i1 + 1) * k + 4);
                g.setStroke(MyColor.red);
                DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 1) * k - 5, (l + 4) * k - 6, (i1 + 1) * k - 5);
                DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 1) * k - 4, (l + 4) * k, (i1 + 1) * k - 4);
                DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 1) * k - 3, (l + 4) * k - 6, (i1 + 1) * k - 3);
            }
            if(COLLISION)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 5) * k - 4, (i1 + 1) * k, 1 * k + 1, 1 * k + 1);
            }
            g.setFill(MyColor.black);
            if(Train)
            {
                for(int k2 = -NbrW; k2 <= NbrW; k2++)
                {
                    if(!Aiguill)
                    {
                        DrawWithOffset.fillOval(g,(l + 4) * k - Count - 3 * k2, i1 * k + 5, 2 * k, 2 * k);
                        continue;
                    }
                    byte byte2 = -20;
                    byte byte6 = 10;
                    if(Count < byte2)
                    {
                        DrawWithOffset.fillOval(g,(l + 4) * k - Count - 3 * k2, (i1 + 2) * k + 5, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte2 && Count <= byte2 + 2)
                    {
                        CountXY = 5 + (int)(0.20000000000000001D * (double)k2 + ((double)Count * 16D) / (1.0D * (double)(byte6 - byte2)));
                        DrawWithOffset.fillOval(g,(l + 4) * k - Count - 3 * k2, (i1 + 2) * k - CountXY, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte2 + 2 && Count <= byte2 + 5)
                    {
                        CountXY = 5 + (int)(0.5D * (double)k2 + ((double)Count * 16D) / (1.0D * (double)(byte6 - byte2)));
                        DrawWithOffset.fillOval(g,(l + 4) * k - Count - 3 * k2, (i1 + 2) * k - CountXY, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte2 + 5 && Count <= byte2 + 10)
                    {
                        CountXY = 5 + (int)(1.0D * (double)k2 + ((double)Count * 16D) / (1.0D * (double)(byte6 - byte2)));
                        DrawWithOffset.fillOval(g,(l + 4) * k - Count - 3 * k2, (i1 + 2) * k - CountXY, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte2 + 10 && Count <= byte6 - 10)
                    {
                        CountXY = 5 + (int)(1.5D * (double)k2 + ((double)Count * 16D) / (1.0D * (double)(byte6 - byte2)));
                        DrawWithOffset.fillOval(g,(l + 4) * k - Count - 3 * k2, (i1 + 2) * k - CountXY, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte6 - 10 && Count <= byte6 - 5)
                    {
                        CountXY = 5 + (int)(1.0D * (double)k2 + ((double)Count * 16D) / (1.0D * (double)(byte6 - byte2)));
                        DrawWithOffset.fillOval(g,(l + 4) * k - Count - 3 * k2, (i1 + 2) * k - CountXY, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte6 - 5 && Count <= byte6 - 2)
                    {
                        CountXY = 5 + (int)(0.5D * (double)k2 + ((double)Count * 16D) / (1.0D * (double)(byte6 - byte2)));
                        DrawWithOffset.fillOval(g,(l + 4) * k - Count - 3 * k2, (i1 + 2) * k - CountXY, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte6 - 2 && Count <= byte6)
                    {
                        CountXY = 5 + (int)(0.20000000000000001D * (double)k2 + ((double)Count * 16D) / (1.0D * (double)(byte6 - byte2)));
                        DrawWithOffset.fillOval(g,(l + 4) * k - Count - 3 * k2, (i1 + 2) * k - CountXY, 2 * k, 2 * k);
                    } else
                    {
                        DrawWithOffset.fillOval(g,(l + 4) * k - Count - 3 * k2, i1 * k + 5, 2 * k, 2 * k);
                    }
                }

            }
            DrawWithOffset.fillText(g,"..", l * k + 2, (i1 + 1) * k + 5);
        } else
        if(IPin[0].PinDim.height < 0)
        {
            g.setFill(MyColor.lightGray);
            for(int l2 = 2; l2 < 6; l2++)
                DrawWithOffset.fillRect(g,(l + 2) * k + 5, i1 * k + 11 * l2, 2 * k - 1, 0 * k + 4);

            DrawWithOffset.fillRect(g,(l + 2) * k + 2, i1 * k + 33, 2 * k - 1, 0 * k + 4);
            DrawWithOffset.fillRect(g,l * k + 12, i1 * k + 44, 2 * k - 1, 0 * k + 4);
            DrawWithOffset.fillRect(g,l * k + 7, i1 * k + 55, 2 * k - 1, 0 * k + 4);
            DrawInputPins(g, l, i1, k);
            DrawOutputPins(g, l, i1, k);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeLine(g,(l + 3) * k - 1, i1 * k, (l + 3) * k - 1, (i1 + 3) * k + 2);
            DrawWithOffset.strokeLine(g,(l + 3) * k, i1 * k, (l + 3) * k, (i1 + 3) * k + 3);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, i1 * k, (l + 3) * k + 1, (i1 + 3) * k + 1);
            DrawWithOffset.strokeArc(g,(l - 9) * k - 2, (i1 - 4) * k + 2, k * 12, k * 12, 0, -35, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 9) * k - 1, (i1 - 4) * k + 2, k * 12, k * 12, 0, -35, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 9) * k, (i1 - 4) * k + 2, k * 12, k * 12, 0, -35, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 1) * k - 2, (i1 + 3) * k, k * 12, k * 12, 170, -28, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 1) * k - 1, (i1 + 3) * k, k * 12, k * 12, 170, -28, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 1) * k, (i1 + 3) * k, k * 12, k * 12, 170, -28, ArcType.OPEN);
            DrawWithOffset.strokeLine(g,(l + 1) * k - 1, (i1 + 8) * k, (l + 1) * k - 1, (i1 + 10) * k);
            DrawWithOffset.strokeLine(g,(l + 1) * k, (i1 + 8) * k, (l + 1) * k, (i1 + 10) * k);
            DrawWithOffset.strokeLine(g,(l + 1) * k + 1, (i1 + 8) * k, (l + 1) * k + 1, (i1 + 10) * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 10) * k, (l + 2) * k + 2, (i1 + 10) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 4) * k + 5, (l + 3) * k + 3, (i1 + 4) * k + 10);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 3, (i1 + 4) * k + 5, (l + 3) * k + 4, (i1 + 4) * k + 10);
            DrawWithOffset.strokeArc(g,(l + 2) * k - 5, (i1 + 3) * k + 7, k * 10, k * 15, 135, 20, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 2) * k - 4, (i1 + 3) * k + 7, k * 10, k * 15, 135, 20, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 2) * k - 3, (i1 + 3) * k + 7, k * 10, k * 15, 135, 20, ArcType.OPEN);
            DrawWithOffset.strokeLine(g,(l + 2) * k - 1, (i1 + 8) * k, (l + 2) * k - 1, (i1 + 10) * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 8) * k, (l + 2) * k, (i1 + 10) * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k + 1, (i1 + 8) * k, (l + 2) * k + 1, (i1 + 10) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k - 1, (i1 + 5) * k - 3, (l + 3) * k - 1, (i1 + 5) * k + 4);
            DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 5) * k - 5, (l + 3) * k, (i1 + 5) * k + 2);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 5) * k - 6, (l + 3) * k + 1, (i1 + 5) * k - 1);
            DrawWithOffset.strokeLine(g,(l + 3) * k - 1, (i1 + 6) * k, (l + 3) * k - 1, (i1 + 10) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 6) * k - 2, (l + 3) * k, (i1 + 10) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 6) * k - 3, (l + 3) * k + 1, (i1 + 10) * k);
            DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 10) * k - 1, (l + 4) * k + 2, (i1 + 10) * k);
            DrawWithOffset.fillRect(g,(l + 4) * k - 1, i1 * k, 0 * k + 3, 10 * k + 1);
            DrawWithOffset.strokeLine(g,(l + 3) * k, i1 * k, (l + 3) * k - 2, i1 * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 3, (i1 + 5) * k + 3, (l + 3) * k + 3, (i1 + 6) * k + 8);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 5, (i1 + 5) * k + 4, (l + 3) * k + 5, (i1 + 6) * k + 8);
            DrawWithOffset.strokeLine(g,(l + 2) * k + 5, (i1 + 5) * k + 1, (l + 2) * k - 2, (i1 + 5) * k + 13);
            DrawWithOffset.strokeLine(g,(l + 2) * k + 7, (i1 + 5) * k + 4, (l + 2) * k + 1, (i1 + 5) * k + 15);
            if(!Aiguill)
            {
                DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 3) * k + 2, (l + 3) * k + 1, (i1 + 5) * k - 3);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 5, (i1 + 3) * k + 2, (l + 3) * k + 4, (i1 + 5) * k - 3);
                g.setStroke(MyColor.blue);
                DrawWithOffset.strokeLine(g,(l + 5) * k - 5, (i1 + 4) * k + 6, (l + 5) * k - 5, (i1 + 5) * k);
                DrawWithOffset.strokeLine(g,(l + 5) * k - 4, (i1 + 4) * k, (l + 5) * k - 4, (i1 + 5) * k);
                DrawWithOffset.strokeLine(g,(l + 5) * k - 3, (i1 + 4) * k + 6, (l + 5) * k - 3, (i1 + 5) * k);
            } else
            {
                DrawWithOffset.strokeLine(g,(l + 3) * k + 3, (i1 + 3) * k + 2, (l + 3) * k - 0, (i1 + 5) * k - 1);
                DrawWithOffset.strokeArc(g,(l - 8) * k, (i1 - 4) * k + 1, k * 12, k * 12, -12, -15, ArcType.OPEN);
                g.setStroke(MyColor.red);
                DrawWithOffset.strokeLine(g,(l + 5) * k - 5, (i1 + 3) * k, (l + 5) * k - 5, (i1 + 4) * k - 6);
                DrawWithOffset.strokeLine(g,(l + 5) * k - 4, (i1 + 3) * k, (l + 5) * k - 4, (i1 + 4) * k);
                DrawWithOffset.strokeLine(g,(l + 5) * k - 3, (i1 + 3) * k, (l + 5) * k - 3, (i1 + 4) * k - 6);
            }
            if(COLLISION)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 3) * k, (i1 + 5) * k - 4, 1 * k + 1, 1 * k + 1);
            }
            g.setFill(MyColor.black);
            if(Train && (!Horaire || Count != lngr) && (Horaire || Count != -lngr))
            {
                for(int i3 = -NbrW; i3 <= NbrW; i3++)
                {
                    if(!Aiguill)
                    {
                        DrawWithOffset.fillOval(g,(l + 2) * k + 5, ((i1 + 4) * k - Count) + 3 * i3, 2 * k, 2 * k);
                        continue;
                    }
                    byte byte3 = -20;
                    byte byte7 = 10;
                    if(Count < byte3)
                    {
                        DrawWithOffset.fillOval(g,l * k + 5, ((i1 + 4) * k - Count) + 3 * i3, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte3 && Count <= byte3 + 2)
                    {
                        CountXY = 10 + (int)(-0.20000000000000001D * (double)i3 + ((double)Count * 16D) / (1.0D * (double)(byte7 - byte3)));
                        DrawWithOffset.fillOval(g,l * k + 5 + CountXY, ((i1 + 4) * k - Count) + 3 * i3, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte3 + 2 && Count <= byte3 + 5)
                    {
                        CountXY = 10 + (int)(-0.5D * (double)i3 + ((double)Count * 16D) / (1.0D * (double)(byte7 - byte3)));
                        DrawWithOffset.fillOval(g,l * k + 5 + CountXY, ((i1 + 4) * k - Count) + 3 * i3, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte3 + 5 && Count <= byte3 + 10)
                    {
                        CountXY = 10 + (int)(-1D * (double)i3 + ((double)Count * 16D) / (1.0D * (double)(byte7 - byte3)));
                        DrawWithOffset.fillOval(g,l * k + 5 + CountXY, ((i1 + 4) * k - Count) + 3 * i3, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte3 + 10 && Count <= byte7 - 10)
                    {
                        CountXY = 10 + (int)(-1.5D * (double)i3 + ((double)Count * 16D) / (1.0D * (double)(byte7 - byte3)));
                        DrawWithOffset.fillOval(g,l * k + 5 + CountXY, ((i1 + 4) * k - Count) + 3 * i3, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte7 - 10 && Count <= byte7 - 5)
                    {
                        CountXY = 10 + (int)(-1D * (double)i3 + ((double)Count * 16D) / (1.0D * (double)(byte7 - byte3)));
                        DrawWithOffset.fillOval(g,l * k + 5 + CountXY, ((i1 + 4) * k - Count) + 3 * i3, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte7 - 5 && Count <= byte7 - 2)
                    {
                        CountXY = 10 + (int)(-0.5D * (double)i3 + ((double)Count * 16D) / (1.0D * (double)(byte7 - byte3)));
                        DrawWithOffset.fillOval(g,l * k + 5 + CountXY, ((i1 + 4) * k - Count) + 3 * i3, 2 * k, 2 * k);
                        continue;
                    }
                    if(Count >= byte7 - 2 && Count <= byte7)
                    {
                        CountXY = 10 + (int)(-0.20000000000000001D * (double)i3 + ((double)Count * 16D) / (1.0D * (double)(byte7 - byte3)));
                        DrawWithOffset.fillOval(g,l * k + 5 + CountXY, ((i1 + 4) * k - Count) + 3 * i3, 2 * k, 2 * k);
                    } else
                    {
                        DrawWithOffset.fillOval(g,(l + 2) * k + 5, ((i1 + 4) * k - Count) + 3 * i3, 2 * k, 2 * k);
                    }
                }

            }
            DrawWithOffset.fillText(g,":", (l + 3) * k + 3, i1 * k + 6);
        }
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    int Count;
    int CountXY;
    boolean Train;
    boolean Horaire;
    boolean COLLISION;
    boolean Aiguill;
    boolean Manuel;
    int H;
    int T;
    int EHdroit;
    int ETcommun;
    int EHcourbe;
    int SHcom;
    int STcourbe;
    int STdroit;
    int AIG;
    int OldAIG;
    int oldH;
    int oldT;
    int lngr;
    int NbrW;
    public Font ComposantFont;
}
