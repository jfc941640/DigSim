package digsim.components;

import digsim.InputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, Pin

public class MasqueJaune1 extends ElectronicComponent
{

    public MasqueJaune1(Pin apin[][], int i, int j)
    {
        super(i, j, 1, 1, 0, 0, 1, 1, 1, 0);
        IPin[0] = new InputPin("", 0, 0, 1, 0, 0, 0, 0);
        ComponentName = "MasqueJaune1";
        ClassName = "MasqueJaune1";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public MasqueJaune1(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        MasqueJaune1 masquejaune1 = new MasqueJaune1(this, i, j);
        return masquejaune1;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setStroke(Color.rgb(255, 255, 102));
        if(IPin[0].PinDim.width > 0)
            DrawWithOffset.fillRect(g,l * k + 1, i1 * k + 1, 1 * k - 1, 1 * k - 1);
        else
        if(IPin[0].PinDim.height > 0)
            DrawWithOffset.fillRect(g,l * k + 1, i1 * k - 1, 1 * k - 1, 0 * k + 3);
        else
        if(IPin[0].PinDim.width < 0)
            DrawWithOffset.fillRect(g,l * k - 1, i1 * k + 1, 0 * k + 3, 1 * k - 1);
        else
        if(IPin[0].PinDim.height < 0)
            DrawWithOffset.fillRect(g,l * k - 1, i1 * k - 1, 0 * k + 3, 0 * k + 3);
    }
}
