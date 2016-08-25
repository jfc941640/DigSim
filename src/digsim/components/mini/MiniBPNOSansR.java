package digsim.components.mini;

import digsim.ComponentPin;
import digsim.DrawWithOffset;
import digsim.InputPin;
import digsim.OutputPin;
import digsim.Pin;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, OutputPin, ComponentPin,
//            Pin

public class MiniBPNOSansR extends ElectronicComponent
{

    public MiniBPNOSansR(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 0, 0, 4, 4, 3, 1);
        MiniBPNOSansRClosed = true;
        IPin[0] = new InputPin("", 3, 1, -2, 0, 0, 0, 0);
        IPin[1] = new InputPin("", 3, 4, -2, 0, 0, 0, 0);
        IPin[2] = new InputPin("", 3, 0, -2, 0, 0, 0, 0);
        OPin[0] = new OutputPin("", 3, 0, -2, 0, 0, 0, 0);
        ComponentName = "BP NO (contact T) sans resistance de pullup";
        ClassName = "MiniBPNOSansR";
        Can_Rotate = false;
        RegisterPins(apin, i, j);
    }

    public MiniBPNOSansR(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        MiniBPNOSansRClosed = true;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        MiniBPNOSansR minibpnosansr = new MiniBPNOSansR(this, i, j);
        return minibpnosansr;
    }

    public boolean SimMouseDown()
    {
        MiniBPNOSansRClosed = !MiniBPNOSansRClosed;
        return true;
    }

    public void SimulateLogic()
    {
        if(!MiniBPNOSansRClosed)
            OPin[0].setLevel(IPin[1].getLevel());
        else
            OPin[0].setLevel(IPin[2].getLevel());
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        byte byte0 = -1;
        int j1 = 0;
        g.setStroke(ComponentPin.PinColor);
        DrawWithOffset.strokeLine(g,(l + byte0 + 4) * k, (i1 + j1 + 0) * k, (l + byte0 + 4) * k, (i1 + j1 + 1) * k);
        DrawWithOffset.strokeLine(g,(l + byte0 + 4) * k, (i1 + j1 + 3) * k, (l + byte0 + 4) * k, (i1 + j1 + 4) * k);
        DrawWithOffset.strokeLine(g,(l + byte0 + 4) * k - 1, (i1 + j1 + 0) * k, (l + byte0 + 4) * k + 1, (i1 + j1 + 0) * k);
        if(MiniBPNOSansRClosed)
        {
            DrawWithOffset.strokeLine(g,(l + byte0 + 3) * k - 1, (i1 + j1 + 1) * k, (l + byte0 + 4) * k - 1, (i1 + j1 + 3) * k);
            DrawWithOffset.strokeLine(g,(l + byte0 + 3) * k, (i1 + j1 + 1) * k, (l + byte0 + 4) * k, (i1 + j1 + 3) * k);
        } else
        {
            DrawWithOffset.strokeLine(g,(l + byte0 + 4) * k - 1, (i1 + j1 + 1) * k, (l + byte0 + 4) * k - 1, (i1 + j1 + 3) * k);
            DrawWithOffset.strokeLine(g,(l + byte0 + 4) * k, (i1 + j1 + 1) * k, (l + byte0 + 4) * k, (i1 + j1 + 3) * k);
        }
    }

    boolean MiniBPNOSansRClosed;
}
