package digsim.components.rail;

import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            ElectronicComponent, OutputPin, MyColor, Pin

public class RailSwitch extends ElectronicComponent
{

    public RailSwitch(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 1, 1, 0, 0, 0, 1);
        OUT = false;
        ComponentName = "Commande manuelle permanente ''compacte''";
        ClassName = "RailSwitch";
        Can_Rotate = true;
        OPin[0] = new OutputPin("", 0, 1, 1, 0, 0, 0, 0);
        RegisterPins(apin, i, j);
        OPin[0].setLevel(0);
    }

    public RailSwitch(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        OUT = false;
        OPin[0].setLevel(0);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        RailSwitch railswitch = new RailSwitch(this, i, j);
        return railswitch;
    }

    public boolean SimMouseDown()
    {
        OUT = !OUT;
        return true;
    }

    public void Simulate(int i)
    {
        if(OUT)
            OPin[0].Level = 5;
        else
            OPin[0].Level = 0;
        InformConnectedComponents(i);
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawOutputPins(g, l, i1, k);
        if(OUT)
        {
            g.setStroke(Color.rgb(255, 64, 64));
            DrawWithOffset.fillRect(g,l * k + 4, i1 * k + 4, k + 1, k + 1);
        } else
        {
            g.setStroke(Color.rgb(64, 64, 255));
            DrawWithOffset.fillRect(g,l * k + 4, i1 * k + 4, k, k);
        }
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,l * k + 4, i1 * k + 4, k, k);
    }

    public void InitBeforeSimulate()
    {
        OPin[0].InitBeforeSimulate();
    }

    boolean OUT;
}
