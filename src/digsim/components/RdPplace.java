package digsim.components;



import digsim.InputPin;
import digsim.MyColor;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, MyColor, Pin

public class RdPplace extends ElectronicComponent
{

    public RdPplace(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 3, 3, 2, 2, 1, 0);
        ComponentName = "Symbole RdP-Place";
        ClassName = "RdPplace";
        Can_Rotate = false;
        IPin[0] = new InputPin("", 1, 4, 0, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
    }

    public RdPplace(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        RdPplace rdpplace = new RdPplace(this, i, j);
        return rdpplace;
    }

    public void SimulateLogic()
    {
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFill(Color.rgb(224, 224, 224));
        DrawWithOffset.fillOval(g,(l + 3) * k, (i1 + 3) * k, 2 * k, 2 * k);
        if(IPin[0].getLevel() == 5)
        {
            g.setStroke(MyColor.red);
            DrawWithOffset.strokeOval(g,(l + 3) * k, (i1 + 3) * k, 2 * k, 2 * k);
            DrawWithOffset.strokeOval(g,(l + 3) * k - 1, (i1 + 3) * k - 1, 2 * k + 2, 2 * k + 2);
        } else
        {
            g.setStroke(MyColor.blue);
            DrawWithOffset.strokeOval(g,(l + 3) * k, (i1 + 3) * k, 2 * k, 2 * k);
        }
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }
}
