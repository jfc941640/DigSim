package digsim.components;

import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            ElectronicComponent, OutputPin, MyColor, Pin

public class Oscillateur2T extends ElectronicComponent
{

    public Oscillateur2T(Pin apin[][], int i, int j)
    {
        super(i, j, 8, 6, 1, 1, 4, 4, 0, 2);
        ComponentName = "Horloge 2 (periode 2T & sorties decalees)";
        ClassName = "Oscillateur2T";
        Can_Rotate = true;
        OPin[0] = new OutputPin("", 7, 2, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("", 7, 4, -2, 0, 0, 0, 0);
        X = Math.round((int)(Math.random() * 4D));
        if(X == 0)
        {
            Q2 = 0;
            Q1 = 0;
        } else
        if(X == 1)
        {
            Q2 = 0;
            Q1 = 5;
        } else
        if(X == 2)
        {
            Q2 = 5;
            Q1 = 0;
        } else
        if(X == 3)
        {
            Q2 = 5;
            Q1 = 5;
        }
        RegisterPins(apin, i, j);
    }

    public Oscillateur2T(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        X = Math.round((int)(Math.random() * 4D));
        if(X == 0)
        {
            Q2 = 0;
            Q1 = 0;
        } else
        if(X == 1)
        {
            Q2 = 0;
            Q1 = 5;
        } else
        if(X == 2)
        {
            Q2 = 5;
            Q1 = 0;
        } else
        if(X == 3)
        {
            Q2 = 5;
            Q1 = 5;
        }
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Oscillateur2T oscillateur2t = new Oscillateur2T(this, i, j);
        return oscillateur2t;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setStroke(Color.rgb(255, 255, 102));
        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, k * 4, k * 4);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeLine(g,(l + 2) * k - 2, (i1 + 1) * k + 5, (l + 3) * k - 2, (i1 + 1) * k + 5);
        g.setStroke(MyColor.blue);
        DrawWithOffset.strokeLine(g,(l + 3) * k - 2, (i1 + 2) * k + 5, (l + 4) * k - 2, (i1 + 2) * k + 5);
        g.setStroke(MyColor.lightGray);
        DrawWithOffset.strokeLine(g,(l + 2) * k - 2, (i1 + 1) * k + 5, (l + 2) * k - 2, (i1 + 2) * k + 5);
        DrawWithOffset.strokeLine(g,(l + 3) * k - 2, (i1 + 1) * k + 5, (l + 3) * k - 2, (i1 + 2) * k + 5);
        DrawWithOffset.strokeLine(g,(l + 4) * k - 2, (i1 + 1) * k + 5, (l + 4) * k - 2, (i1 + 2) * k + 5);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeLine(g,(l + 2) * k + 2, (i1 + 3) * k + 5, (l + 3) * k + 2, (i1 + 3) * k + 5);
        g.setStroke(MyColor.blue);
        DrawWithOffset.strokeLine(g,(l + 3) * k + 2, (i1 + 4) * k + 5, (l + 4) * k + 2, (i1 + 4) * k + 5);
        g.setStroke(MyColor.lightGray);
        DrawWithOffset.strokeLine(g,(l + 2) * k + 2, (i1 + 3) * k + 5, (l + 2) * k + 2, (i1 + 4) * k + 5);
        DrawWithOffset.strokeLine(g,(l + 3) * k + 2, (i1 + 3) * k + 5, (l + 3) * k + 2, (i1 + 4) * k + 5);
        DrawWithOffset.strokeLine(g,(l + 4) * k + 2, (i1 + 3) * k + 5, (l + 4) * k + 2, (i1 + 4) * k + 5);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,(l + 1) * k, (i1 + 1) * k, k * 4, k * 4);
        DrawOutputPins(g, l, i1, k);
        DrawInputPins(g, l, i1, k);
    }

    public void SimulateLogic()
    {
    }

    public void Simulate(int i)
    {
        InformConnectedComponents(i);
    }

    public void InitBeforeSimulate()
    {
        OPin[0].InitBeforeSimulate();
        OPin[1].InitBeforeSimulate();
        if(Q2 == 0 && Q1 == 0)
        {
            Q2 = 0;
            Q1 = 5;
            OPin[0].Level = 0;
            OPin[1].Level = 0;
        } else
        if(Q2 == 0 && Q1 == 5)
        {
            Q2 = 5;
            Q1 = 0;
            OPin[0].Level = 5;
            OPin[1].Level = 0;
        } else
        if(Q2 == 5 && Q1 == 0)
        {
            Q2 = 5;
            Q1 = 5;
            OPin[0].Level = 5;
            OPin[1].Level = 5;
        } else
        if(Q2 == 5 && Q1 == 5)
        {
            Q2 = 0;
            Q1 = 0;
            OPin[0].Level = 0;
            OPin[1].Level = 5;
        }
    }

    int Q1;
    int Q2;
    int X;
}
