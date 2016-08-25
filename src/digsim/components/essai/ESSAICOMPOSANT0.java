package digsim.components.essai;

import java.awt.Point;

import digsim.DrawWithOffset;
import digsim.IntegratedCircuit;
import digsim.MyColor;
import digsim.Pin;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            IntegratedCircuit, MyColor, Pin, ElectronicComponent

public class ESSAICOMPOSANT0 extends IntegratedCircuit
{

    public ESSAICOMPOSANT0(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 4, 4, 0, 0, 0, 0);
        ComponentName = "Generateur de codes";
        ClassName = "ESSAICOMPOSANT0";
        Can_Rotate = false;
        RegisterPins(apin, i, j);
    }

    public ESSAICOMPOSANT0(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        ESSAICOMPOSANT0 essaicomposant0 = new ESSAICOMPOSANT0(this, i, j);
        return essaicomposant0;
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
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 4) * k - 3, (l + 4) * k, (i1 + 4) * k + 3);
        DrawWithOffset.strokeLine(g,(l + 4) * k + 4, (i1 + 4) * k - 3, (l + 4) * k + 4, (i1 + 4) * k + 3);
        g.setStroke(MyColor.blue);
        DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 4) * k + 3, (l + 4) * k + 4, (i1 + 4) * k + 3);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 4) * k - 3, (l + 5) * k, (i1 + 4) * k - 3);
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }
}
