package digsim.components;

import digsim.InputPin;
import digsim.MyColor;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, MyColor, Pin

public class TraitRougeV extends ElectronicComponent
{

    public TraitRougeV(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 0, 0, 0, 0, 1, 0);
        IPin[0] = new InputPin("", 0, 0, 1, 0, 0, 0, 0);
        ComponentName = "TraitRougeV";
        ClassName = "TraitRougeV";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public TraitRougeV(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        TraitRougeV traitrougev = new TraitRougeV(this, i, j);
        return traitrougev;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setStroke(MyColor.red);
        if(IPin[0].PinDim.width > 0)
            DrawWithOffset.strokeLine(g,l * k, i1 * k, l * k, (i1 + 1) * k);
        else
        if(IPin[0].PinDim.height > 0)
            DrawWithOffset.strokeLine(g,l * k + 1, i1 * k, l * k + 1, (i1 + 1) * k);
        else
        if(IPin[0].PinDim.width < 0)
            DrawWithOffset.strokeLine(g,l * k + 2, i1 * k, l * k + 2, (i1 + 1) * k);
        else
        if(IPin[0].PinDim.height < 0)
        {
            DrawWithOffset.strokeLine(g,l * k - 1, i1 * k - 1, l * k - 1, (i1 + 1) * k + 1);
            DrawWithOffset.strokeLine(g,l * k, i1 * k - 1, l * k, (i1 + 1) * k + 1);
            DrawWithOffset.strokeLine(g,l * k + 1, i1 * k - 1, l * k + 1, (i1 + 1) * k + 1);
        }
    }
}
