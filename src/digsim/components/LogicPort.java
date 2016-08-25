package digsim.components;

import java.awt.Graphics;
import java.awt.Point;

import digsim.ElectronicComponent;
import digsim.OutputPin;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            ElectronicComponent, OutputPin

public class LogicPort extends ElectronicComponent
{

    public LogicPort(int i, int j, int k, int l)
    {
        super(j, k, 10, 6, 3, 1, 4, 4, i, 1);
        Can_Rotate = true;
        OPin[0] = new OutputPin("", 9, 3, -2, 0, 0, 0, l);
    }

    public LogicPort(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        return this;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        DrawOutputPins(g, l, i1, k);
    }

    public void SimulateLogic()
    {
    }
}
