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

public class JETON extends ElectronicComponent
{

    public JETON(Pin apin[][], int i, int j)
    {
        super(i, j, 8, 8, 1, 1, 6, 6, 2, 0);
        ComponentName = "Jeton";
        ClassName = "JETON";
        Can_Rotate = true;
        IPin[0] = new InputPin("A", 8, 4, -1, 0, 0, 0, 2);
        IPin[1] = new InputPin("B", 8, 4, -1, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
        IPin[0].setLevel(0);
        IPin[1].setLevel(0);
    }

    public JETON(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        IPin[0].setLevel(0);
        IPin[1].setLevel(0);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        JETON jeton = new JETON(this, i, j);
        return jeton;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        if(IPin[0].getLevel() == 5)
        {
            g.setFill(MyColor.red);
            DrawWithOffset.fillOval(g,(l + 3) * k + 4, (i1 + 3) * k + 4, k + 1, k + 1);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeOval(g,(l + 3) * k + 4, (i1 + 3) * k + 4, k, k);
        } else
        {
            g.setStroke(Color.rgb(160, 160, 255));
            DrawWithOffset.strokeOval(g,(l + 3) * k + 4, (i1 + 3) * k + 4, k, k);
        }
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
