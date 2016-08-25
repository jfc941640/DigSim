package digsim.components;

import java.awt.Dimension;

import digsim.MyColor;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            ElectronicComponent, MyColor, Pin

public class ResR extends ElectronicComponent
{

    public ResR(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 4, 3, 0, 2, 0, 0);
        ComponentName = "Symbole Resistance";
        ClassName = "ResR";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public ResR(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        ResR resr = new ResR(this, i, j);
        resr.NbRotation = NbRotation;
        return resr;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        if(NbRotation == 0)
        {
            g.setStroke(MyColor.lightGray);
            DrawWithOffset.fillRect(g,(l + 4) * k - 3, (i1 + 3) * k, 1 * k - 2, 2 * k);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeRect(g,(l + 4) * k - 3, (i1 + 3) * k, 1 * k - 2, 2 * k);
            DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 5) * k, (l + 4) * k, (i1 + 6) * k);
            DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 2) * k, (l + 4) * k, (i1 + 3) * k);
        }
        if(NbRotation == 1)
        {
            g.setStroke(MyColor.lightGray);
            DrawWithOffset.fillRect(g,(l + 4) * k, (i1 + 3) * k - 3, 2 * k, 1 * k - 2);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeRect(g,(l + 4) * k, (i1 + 3) * k - 3, 2 * k, 1 * k - 2);
            DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 3) * k, (l + 3) * k, (i1 + 3) * k);
            DrawWithOffset.strokeLine(g,(l + 7) * k, (i1 + 3) * k, (l + 6) * k, (i1 + 3) * k);
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
