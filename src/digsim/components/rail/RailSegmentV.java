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

public class RailSegmentV extends ElectronicComponent
{

    public RailSegmentV(Pin apin[][], int i, int j)
    {
        super(i, j, 3, 1, 0, 1, 1, 1, 3, 2);
        Count = 0;
        Train = false;
        COLLISION = false;
        oldH = 0;
        oldT = 0;
        lngr = 40;
        Vitesse = 0;
        NbrW = 3;
        IPin[0] = new InputPin("", 1, 1, 0, 0, 0, 0, 0);
        IPin[1] = new InputPin("", 0, 2, 0, 0, 0, 0, 0);
        IPin[2] = new InputPin("", -1, 3, 1, 0, 0, 0, 0);
        OPin[0] = new OutputPin("", 1, 2, 0, 0, 0, 0, 0);
        OPin[1] = new OutputPin("", 0, 1, 0, 0, 0, 0, 0);
        ComposantFont = Font.font("Sans-serif", 10);
        ComponentName = "Segment de rail V";
        ClassName = "RailSegmentV";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public RailSegmentV(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Count = 0;
        Train = false;
        COLLISION = false;
        oldH = 0;
        oldT = 0;
        lngr = 40;
        Vitesse = 0;
        NbrW = 3;
        ComposantFont = Font.font("Sans-serif", 10);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        RailSegmentV railsegmentv = new RailSegmentV(this, i, j);
        return railsegmentv;
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
        EH = IPin[0].getLevel();
        ET = IPin[1].getLevel();
        if(!Train)
        {
            if(EH == 5)
            {
                Count = -lngr;
                Train = true;
                Horaire = true;
            }
            if(ET == 5)
            {
                Count = lngr;
                Train = true;
                Horaire = false;
            }
        }
        if(H != oldH)
        {
            if(ET == 5)
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
                Count = Count + Vitesse;
            if(Count == lngr)
                SH = 5;
            else
                SH = 0;
            ST = 0;
        }
        if(T != oldT)
        {
            if(EH == 5)
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
                Count = Count - Vitesse;
            if(Count == -lngr)
                ST = 5;
            else
                ST = 0;
            SH = 0;
        }
        OPin[0].setLevel(SH);
        OPin[1].setLevel(ST);
        oldH = H;
        oldT = T;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFont(ComposantFont);
        g.setStroke(MyColor.black);
        g.setFill(MyColor.black);
        DrawWithOffset.fillRect(g,l * k - 1, (i1 + 1) * k, 0 * k + 3, 1 * k + 1);
        DrawWithOffset.fillRect(g,(l + 1) * k - 1, (i1 + 1) * k, 0 * k + 3, 1 * k + 1);
        DrawWithOffset.strokeLine(g,l * k - 2, (i1 + 1) * k, l * k, (i1 + 1) * k);
        DrawWithOffset.strokeLine(g,(l + 1) * k + 2, (i1 + 2) * k, (l + 1) * k, (i1 + 2) * k);
        if(COLLISION)
        {
            g.setFill(MyColor.red);
            DrawWithOffset.fillRect(g,l * k + 1, (i1 + 1) * k + 1, 1 * k - 1, 1 * k - 1);
        }
        if(Train && (!Horaire || Count != lngr) && (Horaire || Count != -lngr))
        {
            for(int j1 = -NbrW; j1 <= NbrW; j1++)
            {
                g.setFill(MyColor.black);
                DrawWithOffset.fillOval(g,(l - 1) * k + 5, ((i1 + 1 + 4) * k - 36) + Count / 10 + 3 * j1, 2 * k, 2 * k);
            }

        }
        if(IPin[2].PinDim.width > 0)
        {
            Vitesse = 10;
            DrawWithOffset.fillText(g,".", l * k + 3, (i1 + 1) * k + 5);
        } else
        if(IPin[2].PinDim.height > 0)
        {
            Vitesse = 20;
            DrawWithOffset.fillText(g,":", l * k + 3, (i1 + 1) * k + 7);
        } else
        if(IPin[2].PinDim.width < 0)
        {
            Vitesse = 40;
            DrawWithOffset.fillText(g,":", l * k + 3, (i1 + 1) * k + 7);
            DrawWithOffset.fillText(g,".", l * k + 3, (i1 + 1) * k + 5);
        } else
        if(IPin[2].PinDim.height < 0)
        {
            Vitesse = 80;
            DrawWithOffset.fillText(g,":", l * k + 3, (i1 + 1) * k + 6);
            DrawWithOffset.fillText(g,":", l * k + 3, (i1 + 1) * k + 8);
        }
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    int Count;
    boolean Train;
    boolean Horaire;
    boolean Rapide;
    boolean COLLISION;
    int EH;
    int H;
    int T;
    int ET;
    int SH;
    int ST;
    int oldH;
    int oldT;
    int lngr;
    int Vitesse;
    int NbrW;
    public Font ComposantFont;
}
