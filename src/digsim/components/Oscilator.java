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

public class Oscilator extends ElectronicComponent
{

    public Oscilator(Pin apin[][], int i, int j)
    {
        super(i, j, 8, 6, 1, 1, 4, 4, 0, 1);
        ComponentName = "Horloge 1 (periode T)";
        ClassName = "Oscilator";
        Can_Rotate = true;
        OPin[0] = new OutputPin("Q", 7, 3, -2, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
        OPin[0].Level = 5 * Math.round((int)(Math.random() * 2D));
    }

    public Oscilator(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        OPin[0].Level = 5 * Math.round((int)(Math.random() * 2D));
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Oscilator oscilator = new Oscilator(this, i, j);
        return oscilator;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawOutputPins(g, l, i1, k);
        g.setFill(Color.rgb(255, 255, 102));
        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, k * 4, k * 4);
        g.setStroke(MyColor.lightGray);
        DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k, (l + 2) * k, (i1 + 4) * k);
        DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 2) * k, (l + 3) * k, (i1 + 4) * k);
        DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 2) * k, (l + 4) * k, (i1 + 4) * k);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k, (l + 3) * k, (i1 + 2) * k);
        g.setStroke(MyColor.blue);
        DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 4) * k, (l + 4) * k, (i1 + 4) * k);
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
        OPin[0].InitBeforeSimulate();
        if(OPin[0].getLevel() == 0)
            OPin[0].Level = 5;
        else
            OPin[0].Level = 0;
    }
}
