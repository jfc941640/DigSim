package digsim.components.rail;

import digsim.InputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, Pin

public class RailMasque2 extends ElectronicComponent
{

    public RailMasque2(Pin apin[][], int i, int j)
    {
        super(i, j, 6, 6, 0, 0, 5, 5, 1, 0);
        IPin[0] = new InputPin("", 0, 0, 1, 0, 0, 0, 0);
        ComponentName = "Masque gris clair";
        ClassName = "RailMasque2";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public RailMasque2(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        RailMasque2 railmasque2 = new RailMasque2(this, i, j);
        return railmasque2;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setStroke(Color.rgb(192, 192, 192));
        if(IPin[0].PinDim.width > 0)
            DrawWithOffset.fillRect(g,l * k + 1, (i1 + 1) * k + 1, 5 * k - 1, 3 * k - 1);
        else
        if(IPin[0].PinDim.height > 0)
            DrawWithOffset.fillRect(g,(l + 1) * k + 1, i1 * k + 1, 3 * k - 1, 5 * k - 1);
        else
        if(IPin[0].PinDim.width < 0)
        {
            double ai[] = {
                l * k - 4, (l + 4) * k - 4, (l + 6) * k - 4, (l + 2) * k - 4
            };
            double ai2[] = {
                (i1 + 4) * k - 4, i1 * k - 4, (i1 + 2) * k - 4, (i1 + 6) * k - 4
            };
            int j1 = ai.length;
            DrawWithOffset.fillPolygon(g,ai, ai2, j1);
        } else
        if(IPin[0].PinDim.height < 0)
        {
            double ai1[] = {
                l * k - 4, (l + 2) * k - 4, (l + 6) * k - 4, (l + 4) * k - 4
            };
            double ai3[] = {
                (i1 + 2) * k - 4, i1 * k - 4, (i1 + 4) * k - 4, (i1 + 6) * k - 4
            };
            int k1 = ai1.length;
            DrawWithOffset.fillPolygon(g,ai1, ai3, k1);
        }
    }
}
