package digsim.components;

import java.awt.Dimension;

import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.MyColor;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            ElectronicComponent, MyColor, Pin

public class CapaC extends ElectronicComponent
{

    public CapaC(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 3, 4, 2, 1, 0, 0);
        ComponentName = "Symbole Condensateur";
        ClassName = "CapaC";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public CapaC(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        CapaC capac = new CapaC(this, i, j);
        capac.NbRotation = NbRotation;
        return capac;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        if(NbRotation == 0)
        {
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeRect(g,(l + 3) * k, (i1 + 4) * k + 2, 2 * k, 0 * k + 1);
            DrawWithOffset.strokeRect(g,(l + 3) * k, (i1 + 5) * k - 2, 2 * k, 0 * k + 1);
            DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 5) * k - 2, (l + 4) * k, (i1 + 6) * k);
            DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 3) * k, (l + 4) * k, (i1 + 4) * k + 2);
        }
        if(NbRotation == 1)
        {
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeRect(g,(l + 3) * k + 2, (i1 + 4) * k, 0 * k + 1, 2 * k);
            DrawWithOffset.strokeRect(g,(l + 4) * k - 2, (i1 + 4) * k, 0 * k + 1, 2 * k);
            DrawWithOffset.strokeLine(g,(l + 4) * k - 2, (i1 + 5) * k, (l + 5) * k, (i1 + 5) * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 5) * k, (l + 3) * k + 2, (i1 + 5) * k);
        }
    }

    public void rotate()
    {
        if(Can_Rotate)
        {
            NbRotation = (NbRotation + 1) % 2;
            HitBoxSize = new Dimension(HitBoxSize.height, HitBoxSize.width);
            Dim = new Dimension(Dim.height, Dim.width);
        }
    }
}
