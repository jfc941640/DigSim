package digsim.components;

import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            ElectronicComponent, OutputPin, MyColor, Pin

public class CLEF extends ElectronicComponent
{

    public CLEF(Pin apin[][], int i, int j)
    {
        super(i, j, 8, 6, 1, 1, 3, 3, 0, 1);
        ComponentName = "Clef";
        ClassName = "CLEF";
        Can_Rotate = false;
        OPin[0] = new OutputPin("Q", 4, 3, 0, 0, 0, 0, 2);
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

    public CLEF(ElectronicComponent electroniccomponent, int i, int j)
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
        CLEF clef = new CLEF(this, i, j);
        return clef;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawOutputPins(g, l, i1, k);
        g.setFill(MyColor.blue);
        DrawWithOffset.fillOval(g,(l + 1) * k, (i1 + 2) * k, k * 2 - 4, k * 2 - 4);
        g.setFill(Color.rgb(255, 0, 0));
        DrawWithOffset.fillOval(g,(l + 1) * k + 3, (i1 + 2) * k + 3, k * 1 - 1, k * 1 - 1);
        g.setFill(MyColor.blue);
        DrawWithOffset.fillRect(g,(l + 2) * k + 2, (i1 + 3) * k - 3, k * 2, k * 1 - 5);
        DrawWithOffset.fillRect(g,(l + 3) * k + 2, (i1 + 3) * k - 3, k * 1 - 6, k * 1);
        DrawWithOffset.fillRect(g,(l + 4) * k - 2, (i1 + 3) * k - 3, k * 1 - 6, k * 1);
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
        if(Q2 == 0 && Q1 == 0)
        {
            Q2 = 0;
            Q1 = 5;
            OPin[0].Level = 0;
        } else
        if(Q2 == 0 && Q1 == 5)
        {
            Q2 = 5;
            Q1 = 0;
            OPin[0].Level = 5;
        } else
        if(Q2 == 5 && Q1 == 0)
        {
            Q2 = 5;
            Q1 = 5;
            OPin[0].Level = 5;
        } else
        if(Q2 == 5 && Q1 == 5)
        {
            Q2 = 0;
            Q1 = 0;
            OPin[0].Level = 0;
        }
    }

    int Q1;
    int Q2;
    int X;
}
