package digsim.components.rail;

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

public class RailMiniSR extends ElectronicComponent
{

    public RailMiniSR(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 6, 1, 1, 2, 2, 3, 1);
        IPin[0] = new InputPin("s", 2, 0, 0, 1, 0, 0, 0);
        IPin[1] = new InputPin("r", 2, 4, 0, -1, 0, 0, 0);
        IPin[2] = new InputPin("", 2, 2, 0, 0, 0, 0, 0);
        OPin[0] = new OutputPin("", 4, 2, -1, 0, 0, 0, 0);
        OPin[0].setLevel(-1);
        for(int k = 0; k < 2; k++)
            IPin[k].setLevel(-1);

        ComponentName = "Mini SR";
        ClassName = "RailMiniSR";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public RailMiniSR(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        OPin[0].setLevel(-1);
        for(int k = 0; k < 2; k++)
            IPin[k].setLevel(-1);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        RailMiniSR railminisr = new RailMiniSR(this, i, j);
        return railminisr;
    }

    public void SimulateLogic()
    {
        if(IPin[0].getLevel() != -1 && IPin[1].getLevel() != -1 && OPin[0].getLevel() == -1)
            OPin[0].Level = 0;
        if(IPin[0].getLevel() == 5 && IPin[1].getLevel() == 0)
            OPin[0].Level = 5;
        if(IPin[0].getLevel() == 0 && IPin[1].getLevel() == 5)
            OPin[0].Level = 0;
        if(IPin[0].getLevel() == 5 && IPin[1].getLevel() == 5)
            OPin[0].Level = 0;
        if(IPin[2].getLevel() == 0)
            OPin[0].Level = 0;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        DrawOutputPins(g, l, i1, k);
        if(OPin[0].getLevel() == 5)
        {
            g.setFill(MyColor.lightGray);
            DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 2 * k, 2 * k);
            g.setFill(MyColor.red);
            DrawWithOffset.fillOval(g,(l + 1) * k + 3, (i1 + 1) * k + 3, 1 * k + 2, 1 * k + 2);
            g.setStroke(MyColor.darkGray);
            DrawWithOffset.strokeOval(g,(l + 1) * k + 3, (i1 + 1) * k + 3, 1 * k + 2, 1 * k + 2);
        } else
        if(OPin[0].getLevel() == 0)
        {
            g.setFill(MyColor.lightGray);
            DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 2 * k, 2 * k);
            g.setFill(Color.rgb(100, 255, 100));
            DrawWithOffset.fillOval(g,(l + 1) * k + 3, (i1 + 1) * k + 3, 1 * k + 2, 1 * k + 2);
            g.setStroke(MyColor.darkGray);
            DrawWithOffset.strokeOval(g,(l + 1) * k + 3, (i1 + 1) * k + 3, 1 * k + 2, 1 * k + 2);
        }
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,(l + 1) * k, (i1 + 1) * k, 2 * k, 2 * k);
    }
}
