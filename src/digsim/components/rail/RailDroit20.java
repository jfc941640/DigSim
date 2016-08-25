package digsim.components.rail;

import digsim.InputPin;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DigSim;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, OutputPin, DigSim,
//            MyColor, Pin

public class RailDroit20 extends ElectronicComponent
{

    public RailDroit20(Pin apin[][], int i, int j)
    {
        super(i, j, 15, 1, 1, 1, 18, 1, 4, 2);
        Count = 0;
        Train = false;
        Bout = false;
        COLLISION = false;
        oldH = 0;
        oldT = 0;
        lngr = 80;
        NbrW = 3;
        IPin[0] = new InputPin("", 0, 1, 1, 0, 0, 0, 0);
        IPin[1] = new InputPin("", 20, 2, -1, 0, 0, 0, 0);
        IPin[2] = new InputPin("", 2, 3, 0, -1, 0, 0, 0);
        IPin[3] = new InputPin("", 1, 4, 0, -2, 0, 0, 0);
        OPin[0] = new OutputPin("", 20, 1, -1, 0, 0, 0, 0);
        OPin[1] = new OutputPin("", 0, 2, 1, 0, 0, 0, 0);
        ComponentName = "Rail droit ''20''";
        ClassName = "RailDroit20";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public RailDroit20(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Count = 0;
        Train = false;
        Bout = false;
        COLLISION = false;
        oldH = 0;
        oldT = 0;
        lngr = 80;
        NbrW = 3;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        RailDroit20 raildroit20 = new RailDroit20(this, i, j);
        return raildroit20;
    }

    public boolean SimMouseDown()
    {
        Count = 0;
        Train = !Train;
        Bout = false;
        return true;
    }

    public void SimulateLogic()
    {
        EH = IPin[0].getLevel();
        H = IPin[2].getLevel();
        T = IPin[3].getLevel();
        ET = IPin[1].getLevel();
        if(!Train)
        {
            if(EH == 5)
            {
                Count = -lngr;
                Train = true;
                Bout = false;
                Horaire = true;
            }
            if(ET == 5)
            {
                Count = lngr;
                Train = true;
                Bout = false;
                Horaire = false;
            }
        }
        char c = '\310';
        if(H != oldH)
        {
            if(ET == 5)
            {
                COLLISION = true;
                DigSim.traincrash.play();
            }
            if(Train)
            {
                Count++;
                Horaire = true;
            }
        }
        if(Horaire)
        {
            if(!Bout && Count >= lngr)
            {
                Train = false;
                Count++;
                SH = 5;
            } else
            {
                SH = 0;
            }
            ST = 0;
        }
        if(H == 0 && Count >= c)
        {
            Bout = true;
            Train = false;
        }
        if(T != oldT)
        {
            if(EH == 5)
            {
                COLLISION = true;
                DigSim.traincrash.play();
            }
            if(Train)
            {
                Count--;
                Horaire = false;
            }
        }
        if(!Horaire)
        {
            if(!Bout && Count <= -lngr)
            {
                Train = false;
                Count--;
                ST = 5;
            } else
            {
                ST = 0;
            }
            SH = 0;
        }
        if(T == 0 && Count <= -c)
        {
            Bout = true;
            Train = false;
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
        DrawInputPins(g, l, i1, k);
        DrawOutputPins(g, l, i1, k);
        if(IPin[0].PinDim.width > 0)
        {
            g.setFill(MyColor.lightGray);
            for(int j1 = 2; j1 < 12; j1++)
                DrawWithOffset.fillRect(g,l * k + 12 * j1, i1 * k + 5, 0 * k + 4, 2 * k - 1);

            g.setStroke(MyColor.black);
            g.setFill(MyColor.black);
            DrawWithOffset.fillRect(g,l * k, (i1 + 1) * k - 1, 20 * k + 1, 0 * k + 3);
            DrawWithOffset.fillRect(g,l * k, (i1 + 2) * k - 1, 20 * k + 1, 0 * k + 3);
            DrawWithOffset.strokeLine(g,(l + 20) * k, (i1 + 1) * k, (l + 20) * k, (i1 + 1) * k - 2);
            DrawWithOffset.strokeLine(g,l * k, (i1 + 2) * k, l * k, (i1 + 2) * k + 2);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k, (l + 2) * k, (i1 + 1) * k);
            DrawWithOffset.strokeLine(g,(l + 1) * k - 1, (i1 + 2) * k, (l + 1) * k - 1, (i1 + 4) * k);
            if(COLLISION)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 9) * k - 4, (i1 + 1) * k, 1 * k + 1, 1 * k + 1);
            }
            if(Train)
            {
                for(int k1 = -NbrW; k1 <= NbrW; k1++)
                {
                    g.setFill(MyColor.black);
                    DrawWithOffset.fillOval(g,(l + 8) * k + Count + 3 * k1, i1 * k + 5, 2 * k, 2 * k);
                }

            }
        } else
        if(IPin[0].PinDim.height > 0)
        {
            g.setFill(MyColor.lightGray);
            for(int l1 = 2; l1 < 12; l1++)
                DrawWithOffset.fillRect(g,l * k + 5, i1 * k + 12 * l1, 2 * k - 1, 0 * k + 4);

            g.setStroke(MyColor.black);
            g.setFill(MyColor.black);
            DrawWithOffset.fillRect(g,(l + 1) * k - 1, i1 * k, 0 * k + 3, 20 * k + 1);
            DrawWithOffset.fillRect(g,(l + 2) * k - 1, i1 * k, 0 * k + 3, 20 * k + 1);
            DrawWithOffset.strokeLine(g,(l + 1) * k - 2, i1 * k, (l + 1) * k, i1 * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k + 2, (i1 + 20) * k, (l + 2) * k, (i1 + 20) * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k, (l + 1) * k, (i1 + 2) * k);
            DrawWithOffset.strokeLine(g,(l - 1) * k, (i1 + 1) * k - 1, (l + 1) * k, (i1 + 1) * k - 1);
            if(COLLISION)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 9) * k - 4, 1 * k + 1, 1 * k + 1);
            }
            if(Train)
            {
                for(int i2 = -NbrW; i2 <= NbrW; i2++)
                {
                    g.setFill(MyColor.black);
                    DrawWithOffset.fillOval(g,l * k + 5, (i1 + 8) * k + Count + 3 * i2, 2 * k, 2 * k);
                }

            }
        } else
        if(IPin[0].PinDim.width < 0)
        {
            g.setFill(MyColor.lightGray);
            for(int j2 = 2; j2 < 12; j2++)
                DrawWithOffset.fillRect(g,l * k + 12 * j2, i1 * k + 5, 0 * k + 4, 2 * k - 1);

            g.setStroke(MyColor.black);
            g.setFill(MyColor.black);
            DrawWithOffset.fillRect(g,l * k, (i1 + 1) * k - 1, 20 * k + 1, 0 * k + 3);
            DrawWithOffset.fillRect(g,l * k, (i1 + 2) * k - 1, 20 * k + 1, 0 * k + 3);
            DrawWithOffset.strokeLine(g,(l + 20) * k, (i1 + 1) * k, (l + 20) * k, (i1 + 1) * k - 2);
            DrawWithOffset.strokeLine(g,l * k, (i1 + 2) * k, l * k, (i1 + 2) * k + 2);
            DrawWithOffset.strokeLine(g,(l + 18) * k, (i1 + 2) * k, (l + 18) * k, (i1 + 1) * k);
            DrawWithOffset.strokeLine(g,(l + 19) * k + 1, (i1 - 1) * k, (l + 19) * k + 1, (i1 + 1) * k);
            if(COLLISION)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 9) * k - 4, (i1 + 1) * k, 1 * k + 1, 1 * k + 1);
            }
            if(Train)
            {
                for(int k2 = -NbrW; k2 <= NbrW; k2++)
                {
                    g.setFill(MyColor.black);
                    DrawWithOffset.fillOval(g,(l + 8) * k - Count - 3 * k2, i1 * k + 5, 2 * k, 2 * k);
                }

            }
        } else
        if(IPin[0].PinDim.height < 0)
        {
            g.setFill(MyColor.lightGray);
            for(int l2 = 2; l2 < 12; l2++)
                DrawWithOffset.fillRect(g,l * k + 5, i1 * k + 12 * l2, 2 * k - 1, 0 * k + 4);

            g.setStroke(MyColor.black);
            g.setFill(MyColor.black);
            DrawWithOffset.fillRect(g,(l + 1) * k - 1, i1 * k, 0 * k + 3, 20 * k + 1);
            DrawWithOffset.fillRect(g,(l + 2) * k - 1, i1 * k, 0 * k + 3, 20 * k + 1);
            DrawWithOffset.strokeLine(g,(l + 1) * k - 2, i1 * k, (l + 1) * k, i1 * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k + 2, (i1 + 20) * k, (l + 2) * k, (i1 + 20) * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 18) * k, (l + 1) * k, (i1 + 18) * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 19) * k + 1, (l + 4) * k, (i1 + 19) * k + 1);
            if(COLLISION)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 9) * k - 4, 1 * k + 1, 1 * k + 1);
            }
            if(Train)
            {
                for(int i3 = -NbrW; i3 <= NbrW; i3++)
                {
                    g.setFill(MyColor.black);
                    DrawWithOffset.fillOval(g,l * k + 5, (i1 + 8) * k - Count - 3 * i3, 2 * k, 2 * k - 1);
                }

            }
        }
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    int Count;
    boolean Train;
    boolean Horaire;
    boolean Bout;
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
}
