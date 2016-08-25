package digsim.components.rail;

import digsim.InputPin;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DigSim;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, OutputPin, DigSim,
//            MyColor, Pin

public class RailCroisement extends ElectronicComponent
{

    public RailCroisement(Pin apin[][], int i, int j)
    {
        super(i, j, 5, 5, 1, 1, 3, 3, 4, 4);
        Count = 0;
        TrainH = false;
        TrainV = false;
        COLLISION = false;
        Bruitage = true;
        oldH = 0;
        oldT = 0;
        lngr = 20;
        NbrW = 3;
        IPin[0] = new InputPin("", 0, 2, 1, 0, 0, 0, 0);
        IPin[1] = new InputPin("", 5, 3, -1, 0, 0, 0, 0);
        IPin[2] = new InputPin("", 2, 5, 0, -1, 0, 0, 0);
        IPin[3] = new InputPin("", 3, 0, 0, 1, 0, 0, 0);
        OPin[0] = new OutputPin("", 5, 2, -1, 0, 0, 0, 0);
        OPin[1] = new OutputPin("", 0, 3, 1, 0, 0, 0, 0);
        OPin[2] = new OutputPin("", 2, 0, 0, 1, 0, 0, 0);
        OPin[3] = new OutputPin("", 3, 5, 0, -1, 0, 0, 0);
        ComposantFont = Font.font("Sans-serif", 10);
        ComponentName = "Croisement ''5x5''";
        ClassName = "RailCroisement";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public RailCroisement(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Count = 0;
        TrainH = false;
        TrainV = false;
        COLLISION = false;
        Bruitage = true;
        oldH = 0;
        oldT = 0;
        lngr = 20;
        NbrW = 3;
        ComposantFont = Font.font("Sans-serif", 10);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        RailCroisement railcroisement = new RailCroisement(this, i, j);
        return railcroisement;
    }

    public void InitBeforeSimulate()
    {
        if((TrainH || TrainV) && Horaire)
        {
            if(H == 0)
                H = 5;
            else
                H = 0;
            T = 0;
            if(EThor == 5 || ETver == 5)
            {
                COLLISION = true;
                DigSim.traincrash.play();
            }
        }
        if((TrainH || TrainV) && !Horaire)
        {
            if(T == 0)
                T = 5;
            else
                T = 0;
            H = 0;
            if(EHhor == 5 || EHver == 5)
            {
                COLLISION = true;
                DigSim.traincrash.play();
            }
        }
    }

    public void SimulateLogic()
    {
        EHhor = IPin[0].getLevel();
        EThor = IPin[1].getLevel();
        EHver = IPin[3].getLevel();
        ETver = IPin[2].getLevel();
        if(!TrainH && !TrainV)
        {
            if(EHhor == 5)
            {
                Count = -lngr;
                TrainH = true;
                Horaire = true;
            }
            if(EThor == 5)
            {
                Count = lngr;
                TrainH = true;
                Horaire = false;
            }
        }
        if(H != oldH && Horaire)
        {
            if(H == 0 && Count >= lngr && TrainH)
            {
                Count = 0;
                TrainH = false;
            }
            if(TrainH && Count < lngr)
                Count = Count + Vitesse;
            if(TrainH && Count == lngr)
                SHhor = 5;
            else
                SHhor = 0;
            SThor = 0;
        }
        if(T != oldT && !Horaire)
        {
            if(T == 0 && Count <= -lngr && TrainH)
            {
                Count = 0;
                TrainH = false;
            }
            if(TrainH && Count > -lngr)
                Count = Count - Vitesse;
            if(TrainH && Count == -lngr)
                SThor = 5;
            else
                SThor = 0;
            SHhor = 0;
        }
        OPin[0].setLevel(SHhor);
        OPin[1].setLevel(SThor);
        if(!TrainV && !TrainH)
        {
            if(EHver == 5)
            {
                Count = -lngr;
                TrainV = true;
                Horaire = true;
            }
            if(ETver == 5)
            {
                Count = lngr;
                TrainV = true;
                Horaire = false;
            }
        }
        if(H != oldH && Horaire)
        {
            if(H == 0 && Count >= lngr && TrainV)
            {
                Count = 0;
                TrainV = false;
            }
            if(TrainV && Count < lngr)
                Count = Count + Vitesse;
            if(TrainV && Count == lngr)
                SHver = 5;
            else
                SHver = 0;
            STver = 0;
        }
        if(T != oldT && !Horaire)
        {
            if(T == 0 && Count <= -lngr && TrainV)
            {
                Count = 0;
                TrainV = false;
            }
            if(TrainV && Count > -lngr)
                Count = Count - Vitesse;
            if(TrainV && Count == -lngr)
                STver = 5;
            else
                STver = 0;
            SHver = 0;
        }
        OPin[3].setLevel(SHver);
        OPin[2].setLevel(STver);
        if(TrainH && TrainV || TrainH && (EHver == 5 || ETver == 5) || TrainV && (EHhor == 5 || EThor == 5))
        {
            TrainH = false;
            TrainV = false;
            COLLISION = true;
            DigSim.traincrash.play();
            DigSim.traincrash.play();
        }
        oldH = H;
        oldT = T;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFont(ComposantFont);
        DrawInputPins(g, l, i1, k);
        DrawOutputPins(g, l, i1, k);
        g.setFill(MyColor.black);
        DrawWithOffset.fillRect(g,l * k, (i1 + 2) * k - 1, 5 * k + 1, 0 * k + 3);
        DrawWithOffset.fillRect(g,l * k, (i1 + 3) * k - 1, 5 * k + 1, 0 * k + 3);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeLine(g,(l + 5) * k, (i1 + 2) * k, (l + 5) * k, (i1 + 2) * k - 2);
        DrawWithOffset.strokeLine(g,l * k, (i1 + 3) * k, l * k, (i1 + 3) * k + 2);
        DrawWithOffset.fillRect(g,(l + 2) * k - 1, i1 * k, 0 * k + 3, 5 * k + 1);
        DrawWithOffset.fillRect(g,(l + 3) * k - 1, i1 * k, 0 * k + 3, 5 * k + 1);
        DrawWithOffset.strokeLine(g,(l + 2) * k - 2, i1 * k, (l + 2) * k, i1 * k);
        DrawWithOffset.strokeLine(g,(l + 3) * k + 2, (i1 + 5) * k, (l + 3) * k, (i1 + 5) * k);
        g.setStroke(MyColor.white);
        DrawWithOffset.strokeLine(g,(l + 2) * k + 2, (i1 + 3) * k - 1, (l + 2) * k + 2, (i1 + 3) * k + 1);
        DrawWithOffset.strokeLine(g,(l + 2) * k + 2, (i1 + 2) * k - 1, (l + 2) * k + 2, (i1 + 2) * k + 1);
        DrawWithOffset.strokeLine(g,(l + 3) * k - 2, (i1 + 3) * k - 1, (l + 3) * k - 2, (i1 + 3) * k + 1);
        DrawWithOffset.strokeLine(g,(l + 3) * k - 2, (i1 + 2) * k - 1, (l + 3) * k - 2, (i1 + 2) * k + 1);
        DrawWithOffset.strokeLine(g,(l + 3) * k - 1, (i1 + 2) * k + 2, (l + 3) * k + 1, (i1 + 2) * k + 2);
        DrawWithOffset.strokeLine(g,(l + 2) * k - 1, (i1 + 2) * k + 2, (l + 2) * k + 1, (i1 + 2) * k + 2);
        DrawWithOffset.strokeLine(g,(l + 3) * k - 1, (i1 + 3) * k - 2, (l + 3) * k + 1, (i1 + 3) * k - 2);
        DrawWithOffset.strokeLine(g,(l + 2) * k - 1, (i1 + 3) * k - 2, (l + 2) * k + 1, (i1 + 3) * k - 2);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,(l + 2) * k + 3, (i1 + 2) * k + 3, 0 * k + 2, 0 * k + 2);
        if(IPin[0].PinDim.width > 0)
        {
            Vitesse = 1;
            if(COLLISION)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 2) * k, (i1 + 2) * k, 1 * k + 1, 1 * k + 1);
            }
            if(TrainH && (!Horaire || Count != lngr) && (Horaire || Count != -lngr))
            {
                for(int j1 = -NbrW; j1 <= NbrW; j1++)
                {
                    g.setFill(MyColor.black);
                    DrawWithOffset.fillOval(g,((l + 4) * k + Count) - 20 - 3 * j1, (i1 + 1) * k + 5, 2 * k, 2 * k);
                }

            }
            if(TrainV && (!Horaire || Count != lngr) && (Horaire || Count != -lngr))
            {
                for(int k1 = -NbrW; k1 <= NbrW; k1++)
                {
                    g.setFill(MyColor.black);
                    DrawWithOffset.fillOval(g,(l + 1) * k + 5, ((i1 + 4) * k + Count) - 20 - 3 * k1, 2 * k, 2 * k - 1);
                }

            }
            DrawWithOffset.fillText(g,".", (l + 4) * k, (i1 + 2) * k + 5);
        } else
        if(IPin[0].PinDim.height > 0)
        {
            Vitesse = 2;
            if(COLLISION)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 2) * k, (i1 + 2) * k, 1 * k + 1, 1 * k + 1);
            }
            if(TrainH && (!Horaire || Count != lngr) && (Horaire || Count != -lngr))
            {
                for(int l1 = -NbrW; l1 <= NbrW; l1++)
                {
                    g.setFill(MyColor.black);
                    DrawWithOffset.fillOval(g,(l + 1) * k + 5, ((i1 + 4) * k + Count) - 20 - 3 * l1, 2 * k, 2 * k - 1);
                }

            }
            if(TrainV && (!Horaire || Count != lngr) && (Horaire || Count != -lngr))
            {
                for(int i2 = -NbrW; i2 <= NbrW; i2++)
                {
                    g.setFill(MyColor.black);
                    DrawWithOffset.fillOval(g,(l + 4) * k - Count - 20 - 3 * i2, (i1 + 1) * k + 5, 2 * k, 2 * k);
                }

            }
            DrawWithOffset.fillText(g,"..", (l + 4) * k, (i1 + 2) * k + 5);
        } else
        if(IPin[0].PinDim.width < 0)
        {
            Vitesse = 4;
            if(COLLISION)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 2) * k, (i1 + 2) * k, 1 * k + 1, 1 * k + 1);
            }
            if(TrainH && (!Horaire || Count != lngr) && (Horaire || Count != -lngr))
            {
                for(int j2 = -NbrW; j2 <= NbrW; j2++)
                {
                    g.setFill(MyColor.black);
                    DrawWithOffset.fillOval(g,(l + 4) * k - Count - 20 - 3 * j2, (i1 + 1) * k + 5, 2 * k, 2 * k);
                }

            }
            if(TrainV && (!Horaire || Count != lngr) && (Horaire || Count != -lngr))
            {
                for(int k2 = -NbrW; k2 <= NbrW; k2++)
                {
                    g.setFill(MyColor.black);
                    DrawWithOffset.fillOval(g,(l + 1) * k + 5, (i1 + 4) * k - Count - 20 - 3 * k2, 2 * k, 2 * k - 1);
                }

            }
            DrawWithOffset.fillText(g,"...", (l + 4) * k - 2, (i1 + 2) * k + 5);
        } else
        if(IPin[0].PinDim.height < 0)
        {
            Vitesse = 8;
            if(COLLISION)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 2) * k, (i1 + 2) * k, 1 * k + 1, 1 * k + 1);
            }
            if(TrainH && (!Horaire || Count != lngr) && (Horaire || Count != -lngr))
            {
                for(int l2 = -NbrW; l2 <= NbrW; l2++)
                {
                    g.setFill(MyColor.black);
                    DrawWithOffset.fillOval(g,(l + 1) * k + 5, (i1 + 4) * k - Count - 20 - 3 * l2, 2 * k, 2 * k - 1);
                }

            }
            if(TrainV && (!Horaire || Count != lngr) && (Horaire || Count != -lngr))
            {
                for(int i3 = -NbrW; i3 <= NbrW; i3++)
                {
                    g.setFill(MyColor.black);
                    DrawWithOffset.fillOval(g,((l + 4) * k + Count) - 20 - 3 * i3, (i1 + 1) * k + 5, 2 * k, 2 * k);
                }

            }
            DrawWithOffset.fillText(g,"....", (l + 4) * k - 4, (i1 + 2) * k + 5);
        }
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    int Count;
    boolean TrainH;
    boolean TrainV;
    boolean Horaire;
    boolean COLLISION;
    boolean Bruitage;
    int Vitesse;
    int H;
    int T;
    int EHhor;
    int EThor;
    int SHhor;
    int SThor;
    int EHver;
    int ETver;
    int SHver;
    int STver;
    int oldH;
    int oldT;
    int lngr;
    int NbrW;
    public Font ComposantFont;
}
