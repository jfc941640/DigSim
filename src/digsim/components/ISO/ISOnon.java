package digsim.components.ISO;

import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, MyColor,
//            Pin, ElectronicComponent

public class ISOnon extends IntegratedCircuit
{

    public ISOnon(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 6, 1, 1, 1, 0, 1, 1);
        IPin[0] = new InputPin("", 0, 1, 1, 0, 0, 0, 0);
        OPin[0] = new OutputPin("", 3, 1, -1, 0, 0, 0, 1);
        ComponentName = "NON";
        ClassName = "ISOnon";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public ISOnon(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        ISOnon isonon = new ISOnon(this, i, j);
        return isonon;
    }

    public void SimulateLogic()
    {
        if(IPin[0].getLevel() == 5)
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
            DrawWithOffset.fillRect(g,(l + 1) * k - 4, (i1 + 1) * k, 1 * k, 1 * k);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeRect(g,(l + 1) * k - 4, (i1 + 1) * k, 1 * k, 1 * k);
            g.setStroke(MyColor.black);
            DrawWithOffset.fillText(g,"1", (l + 0) * k + 6, (i1 + 2) * k);
        } else
        if(IPin[0].PinDim.width < 0 || IPin[0].PinDim.width > 0)
        {
            g.setStroke(Color.rgb(255, 255, 102));
            DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k - 4, 1 * k, 1 * k);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeRect(g,(l + 1) * k, (i1 + 1) * k - 4, 1 * k, 1 * k);
            g.setStroke(MyColor.black);
            DrawWithOffset.fillText(g,"1", (l + 1) * k + 2, (i1 + 1) * k + 4);
        }
    }
}
