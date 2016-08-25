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

public class SBA extends ElectronicComponent
{

    public SBA(Pin apin[][], int i, int j)
    {
        super(i, j, 8, 6, 1, 1, 4, 4, 0, 1);
        ComponentName = "SBA";
        ClassName = "SBA";
        Can_Rotate = true;
        OPin[0] = new OutputPin("Q", 6, 3, -1, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
        OPin[0].Level = 5 * Math.round((int)(Math.random() * 2D));
    }

    public SBA(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        OPin[0].Level = 5 * Math.round((int)(Math.random() * 2D));
    }

    public ElectronicComponent Copy(int i, int j)
    {
        SBA sba = new SBA(this, i, j);
        return sba;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawOutputPins(g, l, i1, k);
        g.setFill(Color.rgb(255, 255, 102));
        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, k * 4, k * 4);
        g.setFill(MyColor.blue);
        DrawWithOffset.fillText(g,"SBA", (l + 2) * k - 2, (i1 + 3) * k + 4);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,(l + 1) * k, (i1 + 1) * k, k * 4, k * 4);
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
        sb = Math.round((int)(Math.random() * 2D));
        OPin[0].InitBeforeSimulate();
        OPin[0].Level = 5 * sb;
    }

    int sb;
}
