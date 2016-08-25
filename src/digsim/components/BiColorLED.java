package digsim.components;

import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.InputPin;
import digsim.MyColor;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, MyColor, Pin

public class BiColorLED extends ElectronicComponent
{

    public BiColorLED(Pin apin[][], int i, int j)
    {
        super(i, j, 3, 5, 2, 1, 2, 2, 2, 0);
        ComponentName = "LED bicolore";
        ClassName = "BiColorLED";
        Can_Rotate = true;
        IPin[0] = new InputPin("A", 1, 2, 1, 0, 0, 0, 2);
        IPin[1] = new InputPin("B", 5, 2, -1, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
    }

    public BiColorLED(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        BiColorLED bicolorled = new BiColorLED(this, i, j);
        return bicolorled;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        if(IPin[0].getLevel() == 5 && IPin[1].getLevel() == 0)
            g.setFill(MyColor.red);
        else
        if(IPin[0].getLevel() == 0 && IPin[1].getLevel() == 5)
            g.setFill(MyColor.green);
        else
            g.setFill(LEDColorOff);
        DrawWithOffset.fillOval(g,(l + 2) * k, (i1 + 1) * k, 2 * k, 2 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,(l + 2) * k, (i1 + 1) * k, 2 * k, 2 * k);
    }

    public void InitBeforeSimulate()
    {
        IPin[0].InitBeforeSimulate();
        IPin[1].InitBeforeSimulate();
    }

    static Color LEDColorOff;

    static
    {
        LEDColorOff = MyColor.blue;
    }
}