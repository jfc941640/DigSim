package digsim.components.ISO;

import digsim.InputPin;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, OutputPin, MyColor,
//            Pin

public class ISOoux3 extends ElectronicComponent
{

    public ISOoux3(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 6, 1, 1, 2, 2, 3, 1);
        IPin[0] = new InputPin("", 0, 1, 1, 0, 0, 0, 0);
        IPin[1] = new InputPin("", 0, 2, 1, 0, 0, 0, 0);
        IPin[2] = new InputPin("", 0, 3, 1, 0, 0, 0, 0);
        OPin[0] = new OutputPin("", 4, 2, -1, 0, 0, 0, 0);
        ComponentName = "OUX3";
        ClassName = "ISOoux3";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public ISOoux3(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        ISOoux3 isooux3 = new ISOoux3(this, i, j);
        return isooux3;
    }

    public void SimulateLogic()
    {
        Total = IPin[0].getLevel() + IPin[1].getLevel() + IPin[2].getLevel();
        if(Total == 5 || Total == 15)
            OPin[0].setLevel(5);
        else
            OPin[0].setLevel(0);
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        DrawOutputPins(g, l, i1, k);
        if(IPin[0].PinDim.height > 0 || IPin[0].PinDim.height < 0)
        {
            g.setStroke(Color.rgb(255, 255, 102));
            DrawWithOffset.fillRect(g,(l + 1) * k - 3, (i1 + 1) * k, 2 * k + 6, 2 * k);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeRect(g,(l + 1) * k - 3, (i1 + 1) * k, 2 * k + 6, 2 * k);
        } else
        if(IPin[0].PinDim.width < 0 || IPin[0].PinDim.width > 0)
        {
            g.setStroke(Color.rgb(255, 255, 102));
            DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k - 3, 2 * k, 2 * k + 6);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeRect(g,(l + 1) * k, (i1 + 1) * k - 3, 2 * k, 2 * k + 6);
        }
        g.setStroke(MyColor.black);
        DrawWithOffset.fillText(g,"=1", (l + 1) * k + 4, (i1 + 2) * k + 4);
    }

    int Total;
}
