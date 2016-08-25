package digsim.components;

import digsim.OutputPin;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            ElectronicComponent, OutputPin

public class LogicPortAndInverter extends ElectronicComponent
{

    public LogicPortAndInverter(int i, int j, int k, int l)
    {
        super(j, k, 10, 6, 3, 1, 4, 4, i, 2);
        OPin[0] = new OutputPin(" ", 9, 2, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin(" ", 9, 4, -2, 0, 0, 0, 1);
    }

    public LogicPortAndInverter(ElectronicComponent electroniccomponent, int i, int j)
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
