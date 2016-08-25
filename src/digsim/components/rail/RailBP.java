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

public class RailBP extends ElectronicComponent
{

    public RailBP(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 1, 1, 0, 0, 0, 1);
        OUT = false;
        ComponentName = "Commande manuelle fugitive ''compacte''";
        ClassName = "RailBP";
        Can_Rotate = true;
        OPin[0] = new OutputPin("", 0, 1, 1, 0, 0, 0, 0);
        RegisterPins(apin, i, j);
        OPin[0].setLevel(0);
    }

    public RailBP(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        OUT = false;
        OPin[0].setLevel(0);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        RailBP railbp = new RailBP(this, i, j);
        return railbp;
    }

    public boolean SimMouseDown()
    {
        OUT = true;
        return true;
    }

    public boolean SimMouseUp()
    {
        OUT = false;
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
            g.setFill(Color.rgb(255, 64, 64));
            DrawWithOffset.fillOval(g,l * k + 4, i1 * k + 4, k + 1, k + 1);
        } else
        {
            g.setFill(Color.rgb(64, 64, 255));
            DrawWithOffset.fillOval(g,l * k + 4, i1 * k + 4, k, k);
        }
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,l * k + 4, i1 * k + 4, k, k);
    }

    public void InitBeforeSimulate()
    {
        OPin[0].InitBeforeSimulate();
    }

    boolean OUT;
}
