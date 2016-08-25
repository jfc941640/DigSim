package digsim.components;

import digsim.MyColor;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            ElectronicComponent, MyColor, Pin

public class TRIGGER extends ElectronicComponent
{

    public TRIGGER(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 4, 4, 1, 1, 0, 0);
        ComponentName = "Symbole Trigger";
        ClassName = "TRIGGER";
        Can_Rotate = false;
        RegisterPins(apin, i, j);
    }

    public TRIGGER(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        TRIGGER trigger = new TRIGGER(this, i, j);
        return trigger;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setStroke(MyColor.black);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 4) * k - 3, (l + 4) * k, (i1 + 4) * k + 3);
        DrawWithOffset.strokeLine(g,(l + 4) * k + 4, (i1 + 4) * k - 3, (l + 4) * k + 4, (i1 + 4) * k + 3);
        g.setStroke(MyColor.blue);
        DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 4) * k + 3, (l + 4) * k + 4, (i1 + 4) * k + 3);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 4) * k - 3, (l + 5) * k, (i1 + 4) * k - 3);
    }
}
