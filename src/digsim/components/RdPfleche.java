package digsim.components;

import java.awt.Graphics;
import java.awt.Point;

import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.MyColor;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, MyColor, Pin,
//            ElectronicComponent

public class RdPfleche extends IntegratedCircuit
{

    public RdPfleche(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 4, 4, 0, 0, 1, 0);
        ComponentName = "Symbole RdP-Fleche";
        ClassName = "RdPfleche";
        Can_Rotate = true;
        IPin[0] = new InputPin("", 4, 4, 0, 0, 0, 0, 0);
        RegisterPins(apin, i, j);
    }

    public RdPfleche(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        RdPfleche rdpfleche = new RdPfleche(this, i, j);
        return rdpfleche;
    }

    public void SimulateLogic()
    {
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setStroke(MyColor.black);
        if(NbRotation == 3)
        {
            DrawWithOffset.strokeLine(g,(l + 4) * k - 4, (i1 + 4) * k - 4, (l + 4) * k, (i1 + 4) * k);
            DrawWithOffset.strokeLine(g,(l + 4) * k - 4, (i1 + 4) * k + 4, (l + 4) * k, (i1 + 4) * k);
            DrawWithOffset.strokeLine(g,(l + 4) * k - 4, ((i1 + 4) * k - 4) + 1, (l + 4) * k, (i1 + 4) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 4) * k - 4, ((i1 + 4) * k + 4) - 1, (l + 4) * k, (i1 + 4) * k - 1);
        } else
        if(NbRotation == 0)
        {
            DrawWithOffset.strokeLine(g,(l + 4) * k - 4, (i1 + 4) * k - 4, (l + 4) * k, (i1 + 4) * k);
            DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 4) * k, (l + 4) * k + 4, (i1 + 4) * k - 4);
            DrawWithOffset.strokeLine(g,((l + 4) * k - 4) + 1, (i1 + 4) * k - 4, (l + 4) * k + 1, (i1 + 4) * k);
            DrawWithOffset.strokeLine(g,(l + 4) * k - 1, (i1 + 4) * k, ((l + 4) * k + 4) - 1, (i1 + 4) * k - 4);
        } else
        if(NbRotation == 1)
        {
            DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 4) * k, (l + 4) * k + 4, (i1 + 4) * k + 4);
            DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 4) * k, (l + 4) * k + 4, (i1 + 4) * k - 4);
            DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 4) * k - 1, (l + 4) * k + 4, ((i1 + 4) * k + 4) - 1);
            DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 4) * k + 1, (l + 4) * k + 4, ((i1 + 4) * k - 4) + 1);
        } else
        if(NbRotation == 2)
        {
            DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 4) * k, (l + 4) * k + 4, (i1 + 4) * k + 4);
            DrawWithOffset.strokeLine(g,(l + 4) * k - 4, (i1 + 4) * k + 4, (l + 4) * k, (i1 + 4) * k);
            DrawWithOffset.strokeLine(g,(l + 4) * k - 1, (i1 + 4) * k, ((l + 4) * k + 4) - 1, (i1 + 4) * k + 4);
            DrawWithOffset.strokeLine(g,((l + 4) * k - 4) + 1, (i1 + 4) * k + 4, (l + 4) * k + 1, (i1 + 4) * k);
        }
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }
}
