package digsim.components;

import digsim.InputPin;
import digsim.MyColor;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, MyColor, Pin

public class RdPtransition extends ElectronicComponent
{

    public RdPtransition(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 3, 3, 2, 2, 1, 0);
        ComponentName = "Symbole RdP-Transition";
        ClassName = "RdPtransition";
        Can_Rotate = true;
        IPin[0] = new InputPin("", 1, 4, 2, 0, 0, 0, 0);
        RegisterPins(apin, i, j);
    }

    public RdPtransition(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        RdPtransition rdptransition = new RdPtransition(this, i, j);
        return rdptransition;
    }

    public void SimulateLogic()
    {
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        if(IPin[0].PinDim.width > 0)
        {
            g.setStroke(MyColor.black);
            if(IPin[0].getLevel() == 5)
                g.setStroke(MyColor.red);
            else
                g.setStroke(MyColor.blue);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 4) * k, (l + 5) * k - 1, (i1 + 4) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 4) * k - 1, (l + 5) * k - 1, (i1 + 4) * k - 1);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 4) * k + 1, (l + 5) * k - 1, (i1 + 4) * k + 1);
        } else
        if(IPin[0].PinDim.height < 0)
        {
            g.setStroke(MyColor.black);
            if(IPin[0].getLevel() == 5)
                g.setStroke(MyColor.red);
            else
                g.setStroke(MyColor.blue);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 5, (i1 + 3) * k + 4, (l + 5) * k - 4, (i1 + 5) * k - 5);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 3) * k + 4, (l + 5) * k - 4, (i1 + 5) * k - 4);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 3) * k + 5, (l + 5) * k - 5, (i1 + 5) * k - 4);
        } else
        if(IPin[0].PinDim.width < 0)
        {
            g.setStroke(MyColor.black);
            if(IPin[0].getLevel() == 5)
                g.setStroke(MyColor.red);
            else
                g.setStroke(MyColor.blue);
            DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 3) * k + 1, (l + 4) * k, (i1 + 5) * k - 1);
            DrawWithOffset.strokeLine(g,(l + 4) * k - 1, (i1 + 3) * k + 1, (l + 4) * k - 1, (i1 + 5) * k - 1);
            DrawWithOffset.strokeLine(g,(l + 4) * k + 1, (i1 + 3) * k + 1, (l + 4) * k + 1, (i1 + 5) * k - 1);
        } else
        if(IPin[0].PinDim.height > 0)
        {
            g.setStroke(MyColor.black);
            if(IPin[0].getLevel() == 5)
                g.setStroke(MyColor.red);
            else
                g.setStroke(MyColor.blue);
            DrawWithOffset.strokeLine(g,(l + 5) * k - 5, (i1 + 3) * k + 4, (l + 3) * k + 4, (i1 + 5) * k - 5);
            DrawWithOffset.strokeLine(g,(l + 5) * k - 4, (i1 + 3) * k + 4, (l + 3) * k + 4, (i1 + 5) * k - 4);
            DrawWithOffset.strokeLine(g,(l + 5) * k - 4, (i1 + 3) * k + 5, (l + 3) * k + 5, (i1 + 5) * k - 4);
        }
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }
}
