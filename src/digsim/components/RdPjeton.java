package digsim.components;



import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            ElectronicComponent, Pin

public class RdPjeton extends ElectronicComponent
{

    public RdPjeton(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 4, 3, 0, 1, 0, 0);
        ComponentName = "Symbole RdP-Jeton";
        ClassName = "RdPjeton";
        Can_Rotate = false;
    }

    public RdPjeton(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        RdPjeton rdpjeton = new RdPjeton(this, i, j);
        return rdpjeton;
    }

    public void SimulateLogic()
    {
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFill(Color.rgb(200, 0, 0));
        DrawWithOffset.fillOval(g,(l + 3) * k + 6, (i1 + 3) * k + 1, 1 * k - 5, 1 * k - 5);
        g.setStroke(Color.rgb(200, 0, 0));
        DrawWithOffset.strokeOval(g,(l + 3) * k + 6, (i1 + 3) * k + 1, 1 * k - 5, 1 * k - 5);
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }
}
