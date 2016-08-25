package digsim.components.rail;

import digsim.InputPin;
import digsim.MyColor;
import digsim.Pin;
import digsim.DigSim;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, DigSim, MyColor,
//            Pin

public class RailSifflet extends ElectronicComponent
{

    public RailSifflet(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 1, 1, 0, 0, 1, 0);
        OUT = false;
        oldOUT = false;
        ComponentName = "Sifflet";
        ClassName = "RailSifflet";
        Can_Rotate = true;
        IPin[0] = new InputPin("", 0, 1, 1, 0, 0, 0, 0);
        RegisterPins(apin, i, j);
        IPin[0].setLevel(0);
        IPin[0].OldLevel = 0;
    }

    public RailSifflet(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        OUT = false;
        oldOUT = false;
        IPin[0].setLevel(0);
        IPin[0].OldLevel = 0;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        RailSifflet railsifflet = new RailSifflet(this, i, j);
        return railsifflet;
    }

    public boolean SimMouseDown()
    {
        OUT = true;
        return true;
    }

    public boolean SimMouseUp()
    {
        OUT = false;
        return true;
    }

    public void SimulateLogic()
    {
        if(IPin[0].getLevel() == 5 && IPin[0].OldLevel == 0 || !oldOUT && OUT)
            DigSim.trainsifflet.play();
        IPin[0].OldLevel = IPin[0].getLevel();
        oldOUT = OUT;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        g.setFill(MyColor.gray);
        DrawWithOffset.fillRect(g,l * k + 4, i1 * k + 4, k + 1, k + 1);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,l * k + 4, i1 * k + 4, k, k);
        g.setFill(MyColor.white);
        DrawWithOffset.fillText(g,"s", (l + 1) * k - 2, (i1 + 1) * k + 3);
    }

    public void Simulate(int i)
    {
        InformConnectedComponents(i);
    }

    boolean OUT;
    boolean oldOUT;
}
