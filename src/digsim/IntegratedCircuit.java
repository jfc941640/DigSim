package digsim;



import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            ElectronicComponent

public class IntegratedCircuit extends ElectronicComponent
{

    public IntegratedCircuit(int i, int j, int k, int l, int i1, int j1, int k1,
            int l1, int i2, int j2)
    {
        super(i, j, k, l, i1, j1, k1, l1, i2, j2);
    }

    public IntegratedCircuit(ElectronicComponent electroniccomponent, int i, int j)
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
        g.setFill(Color.rgb(255, 255, 102));
        DrawWithOffset.fillRect(g,(l + HitBox.x) * k, (i1 + HitBox.y) * k, HitBoxSize.width * k, HitBoxSize.height * k);
        DrawInputPins(g, l, i1, k);
        DrawOutputPins(g, l, i1, k);
        g.setStroke(ComponentColor);
        DrawWithOffset.strokeRect(g,(l + HitBox.x) * k, (i1 + HitBox.y) * k, HitBoxSize.width * k, HitBoxSize.height * k);
    }

    public void SimulateLogic()
    {
    }

    public void Simulate(int i)
    {
        super.Simulate(i);
    }
}
