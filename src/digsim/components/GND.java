package digsim.components;

import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            ElectronicComponent, OutputPin, MyColor, Pin

public class GND extends ElectronicComponent
{

    public GND(Pin apin[][], int i, int j)
    {
        super(i, j, 4, 2, 1, 1, 2, 2, 0, 1);
        OPin[0] = new OutputPin("GND", 2, 1, 0, 1, 0, 0, 2);
        OPin[0].Level = 0;
        ComponentName = "Masse (niveau logique 0)";
        ClassName = "GND";
        RegisterPins(apin, i, j);
    }

    public GND(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        GND gnd = new GND(this, i, j);
        gnd.OPin[0].Level = 0;
        return gnd;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawOutputPins(g, l, i1, k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k, (l + 2) * k, (i1 + 2) * k + 2);
        g.setFill(MyColor.blue);
        DrawWithOffset.fillRect(g,(l + 1) * k + 1, (i1 + 2) * k + 2, k * 2 - 1, k / 2 - 3);
        DrawWithOffset.fillRect(g,(l + 1) * k + 4, (i1 + 2) * k + 4, k * 1 + 1, k / 2 - 3);
        DrawWithOffset.fillRect(g,(l + 1) * k + 7, (i1 + 2) * k + 6, k * 1 - 5, k / 2 - 3);
    }

    public void Simulate(int i)
    {
        InformConnectedComponents(i);
    }
}
