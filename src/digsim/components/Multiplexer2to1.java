package digsim.components;

import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, MyColor,
//            Pin, ElectronicComponent

public class Multiplexer2to1 extends IntegratedCircuit
{

    public Multiplexer2to1(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 9, 3, 1, 4, 4, 3, 2);
        ActVal = 0;
        IPin[0] = new InputPin("0", 1, 2, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("1", 1, 4, 2, 0, 0, 0, 0);
        IPin[2] = new InputPin("a", 5, 7, 0, -2, 0, 0, 0);
        OPin[0] = new OutputPin("Y", 9, 2, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("Y", 9, 4, -2, 0, 0, 0, 1);
        ComponentName = "Multiplexeur 2 vers 1";
        ClassName = "Multiplexer2to1";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public Multiplexer2to1(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        ActVal = 0;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Multiplexer2to1 multiplexer2to1 = new Multiplexer2to1(this, i, j);
        return multiplexer2to1;
    }

    public void SimulateLogic()
    {
        ActVal = 0;
        if(IPin[2].getLevel() == 5)
            ActVal++;
        if(IPin[ActVal].Level == 0)
        {
            OPin[0].Level = 0;
            OPin[1].Level = 0;
        } else
        {
            OPin[0].Level = 5;
            OPin[1].Level = 5;
        }
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        g.setStroke(MyColor.pink);
        if(IPin[0].PinDim.width > 0)
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 2 + 2 * ActVal) * k, (l + 7) * k - 1, (i1 + 2) * k);
        else
        if(IPin[0].PinDim.height > 0)
            DrawWithOffset.strokeLine(g,((l + 6) - 2 * ActVal) * k, (i1 + 1) * k + 1, (l + 6) * k, (i1 + 5) * k - 1);
        else
        if(IPin[0].PinDim.width < 0)
            DrawWithOffset.strokeLine(g,(l + 7) * k - 1, ((i1 + 4) - 2 * ActVal) * k, (l + 3) * k + 1, (i1 + 4) * k);
        else
        if(IPin[0].PinDim.height < 0)
            DrawWithOffset.strokeLine(g,(l + 4 + 2 * ActVal) * k, (i1 + 5) * k - 1, (l + 4) * k, (i1 + 1) * k + 1);
    }

    int ActVal;
}
