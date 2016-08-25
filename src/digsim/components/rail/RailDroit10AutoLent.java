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

public class RailDroit10AutoLent extends ElectronicComponent
{

    public RailDroit10AutoLent(Pin apin[][], int i, int j)
    {
        super(i, j, 15, 1, 1, 1, 8, 1, 2, 2);
        Count = 0;
        Train = false;
        COLLISION = false;
        oldH = 0;
        oldT = 0;
        lngr = 40;
        NbrW = 3;
        IPin[0] = new InputPin("", 0, 1, 1, 0, 0, 0, 0);
        IPin[1] = new InputPin("", 10, 2, -1, 0, 0, 0, 0);
        OPin[0] = new OutputPin("", 10, 1, -1, 0, 0, 0, 0);
        OPin[1] = new OutputPin("", 0, 2, 1, 0, 0, 0, 0);
        ComposantFont = Font.font("Sans-serif", 10);
        ComponentName = "Rail droit ''10'' auto lent";
        ClassName = "RailDroit10AutoLent";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public RailDroit10AutoLent(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Count = 0;
        Train = false;
        COLLISION = false;
        oldH = 0;
        oldT = 0;
        lngr = 40;
        NbrW = 3;
        ComposantFont = Font.font("Sans-serif", 10);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        RailDroit10AutoLent raildroit10autolent = new RailDroit10AutoLent(this, i, j);
        return raildroit10autolent;
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
                if(!Rapide)
                    Count = Count + 1;
                else
                    Count = Count + 2;
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
                if(!Rapide)
                    Count = Count - 1;
                else
                    Count = Count - 2;
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
        DrawInputPins(g, l, i1, k);
        DrawOutputPins(g, l, i1, k);
        if(IPin[0].PinDim.width > 0)
        {
            Rapide = false;
            g.setFill(MyColor.lightGray);
            for(int j1 = 2; j1 < 6; j1++)
                DrawWithOffset.fillRect(g,l * k + 11 * j1, i1 * k + 5, 0 * k + 4, 2 * k - 1);

            g.setFill(MyColor.black);
            DrawWithOffset.fillRect(g,l * k, (i1 + 1) * k - 1, 10 * k + 1, 0 * k + 3);
            DrawWithOffset.fillRect(g,l * k, (i1 + 2) * k - 1, 10 * k + 1, 0 * k + 3);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeLine(g,(l + 10) * k, (i1 + 1) * k, (l + 10) * k, (i1 + 1) * k - 2);
            DrawWithOffset.strokeLine(g,l * k, (i1 + 2) * k, l * k, (i1 + 2) * k + 2);
            if(COLLISION)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 5) * k - 4, (i1 + 1) * k, 1 * k + 1, 1 * k + 1);
            }
            if(Train && (!Horaire || Count != lngr) && (Horaire || Count != -lngr))
            {
                for(int k1 = -NbrW; k1 <= NbrW; k1++)
                {
                    g.setFill(MyColor.black);
                    DrawWithOffset.fillOval(g,(l + 4) * k + Count + 3 * k1, i1 * k + 5, 2 * k, 2 * k);
                }

            }
            DrawWithOffset.fillText(g,".", (l + 5) * k - 1, (i1 + 1) * k + 5);
        } else
        if(IPin[0].PinDim.height > 0)
        {
            Rapide = false;
            g.setFill(MyColor.lightGray);
            for(int l1 = 2; l1 < 6; l1++)
                DrawWithOffset.fillRect(g,l * k + 5, i1 * k + 11 * l1, 2 * k - 1, 0 * k + 4);

            g.setFill(MyColor.black);
            DrawWithOffset.fillRect(g,(l + 1) * k - 1, i1 * k, 0 * k + 3, 10 * k + 1);
            DrawWithOffset.fillRect(g,(l + 2) * k - 1, i1 * k, 0 * k + 3, 10 * k + 1);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeLine(g,(l + 1) * k - 2, i1 * k, (l + 1) * k, i1 * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k + 2, (i1 + 10) * k, (l + 2) * k, (i1 + 10) * k);
            if(COLLISION)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 5) * k - 4, 1 * k + 1, 1 * k + 1);
            }
            if(Train && (!Horaire || Count != lngr) && (Horaire || Count != -lngr))
            {
                for(int i2 = -NbrW; i2 <= NbrW; i2++)
                {
                    g.setFill(MyColor.black);
                    DrawWithOffset.fillOval(g,l * k + 5, (i1 + 4) * k + Count + 3 * i2, 2 * k, 2 * k);
                }

            }
            DrawWithOffset.fillText(g,".", (l + 1) * k + 3, (i1 + 5) * k + 1);
        } else
        if(IPin[0].PinDim.width < 0)
        {
            Rapide = true;
            g.setFill(MyColor.lightGray);
            for(int j2 = 2; j2 < 6; j2++)
                DrawWithOffset.fillRect(g,l * k + 11 * j2, i1 * k + 5, 0 * k + 4, 2 * k - 1);

            g.setFill(MyColor.black);
            DrawWithOffset.fillRect(g,l * k, (i1 + 1) * k - 1, 10 * k + 1, 0 * k + 3);
            DrawWithOffset.fillRect(g,l * k, (i1 + 2) * k - 1, 10 * k + 1, 0 * k + 3);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeLine(g,(l + 10) * k, (i1 + 1) * k, (l + 10) * k, (i1 + 1) * k - 2);
            DrawWithOffset.strokeLine(g,l * k, (i1 + 2) * k, l * k, (i1 + 2) * k + 2);
            if(COLLISION)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 5) * k - 4, (i1 + 1) * k, 1 * k + 1, 1 * k + 1);
            }
            if(Train && (!Horaire || Count != lngr) && (Horaire || Count != -lngr))
            {
                for(int k2 = -NbrW; k2 <= NbrW; k2++)
                {
                    g.setFill(MyColor.black);
                    DrawWithOffset.fillOval(g,(l + 4) * k - Count - 3 * k2, i1 * k + 5, 2 * k, 2 * k);
                }

            }
            DrawWithOffset.fillText(g,"..", (l + 5) * k - 3, (i1 + 1) * k + 5);
        } else
        if(IPin[0].PinDim.height < 0)
        {
            Rapide = true;
            g.setFill(MyColor.lightGray);
            for(int l2 = 2; l2 < 6; l2++)
                DrawWithOffset.fillRect(g,l * k + 5, i1 * k + 11 * l2, 2 * k - 1, 0 * k + 4);

            g.setFill(MyColor.black);
            DrawWithOffset.fillRect(g,(l + 1) * k - 1, i1 * k, 0 * k + 3, 10 * k + 1);
            DrawWithOffset.fillRect(g,(l + 2) * k - 1, i1 * k, 0 * k + 3, 10 * k + 1);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeLine(g,(l + 1) * k - 2, i1 * k, (l + 1) * k, i1 * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k + 2, (i1 + 10) * k, (l + 2) * k, (i1 + 10) * k);
            if(COLLISION)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 5) * k - 4, 1 * k + 1, 1 * k + 1);
            }
            if(Train && (!Horaire || Count != lngr) && (Horaire || Count != -lngr))
            {
                for(int i3 = -NbrW; i3 <= NbrW; i3++)
                {
                    g.setFill(MyColor.black);
                    DrawWithOffset.fillOval(g,l * k + 5, (i1 + 4) * k - Count - 3 * i3, 2 * k, 2 * k - 1);
                }

            }
            DrawWithOffset.fillText(g,":", (l + 1) * k + 3, (i1 + 5) * k + 3);
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
    int NbrW;
    public Font ComposantFont;
}
