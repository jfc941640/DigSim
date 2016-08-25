package digsim.components.mini;

import java.awt.Graphics;
import java.awt.Point;

import digsim.ComponentPin;
import digsim.DrawWithOffset;
import digsim.InputPin;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, OutputPin, MyColor,
//            ComponentPin, Pin

public class MiniBPNOAvecR extends ElectronicComponent
{

    public MiniBPNOAvecR(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 0, 0, 4, 8, 3, 1);
        MiniBPNOAvecRClosed = true;
        IPin[0] = new InputPin("", 3, 1, -2, 0, 0, 0, 0);
        IPin[1] = new InputPin("", 3, 9, -2, 0, 0, 0, 0);
        IPin[2] = new InputPin("", 3, 5, -2, 0, 0, 0, 0);
        OPin[0] = new OutputPin("", 3, 5, -2, 0, 0, 0, 0);
        ComponentName = "BP NO (contact T) avec resistance de pullup";
        ClassName = "MiniBPNOAvecR";
        Can_Rotate = false;
        RegisterPins(apin, i, j);
    }

    public MiniBPNOAvecR(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        MiniBPNOAvecRClosed = true;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        MiniBPNOAvecR minibpnoavecr = new MiniBPNOAvecR(this, i, j);
        return minibpnoavecr;
    }

    public boolean SimMouseDown()
    {
        MiniBPNOAvecRClosed = false;
        return true;
    }

    public boolean SimMouseUp()
    {
        MiniBPNOAvecRClosed = true;
        return true;
    }

    public void SimulateLogic()
    {
        if(!MiniBPNOAvecRClosed)
            OPin[0].setLevel(IPin[1].getLevel());
        else
            OPin[0].setLevel(IPin[0].getLevel());
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setStroke(MyColor.lightGray);
        DrawWithOffset.fillRect(g,(l + 3) * k - 3, (i1 + 2) * k, 1 * k - 2, 2 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,(l + 3) * k - 3, (i1 + 2) * k, 1 * k - 2, 2 * k);
        DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 1) * k, (l + 3) * k, (i1 + 2) * k);
        DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 4) * k, (l + 3) * k, (i1 + 6) * k);
        g.setStroke(ComponentPin.PinColor);
        DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 5) * k, (l + 3) * k, (i1 + 6) * k);
        DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 8) * k, (l + 3) * k, (i1 + 9) * k);
        DrawWithOffset.strokeLine(g,(l + 3) * k - 1, (i1 + 5) * k, (l + 3) * k + 1, (i1 + 5) * k);
        if(MiniBPNOAvecRClosed)
        {
            DrawWithOffset.strokeLine(g,(l + 1) * k, (i1 + 7) * k, (l + 3) * k - 4, (i1 + 7) * k);
            DrawWithOffset.strokeLine(g,(l + 1) * k, (i1 + 7) * k - 4, (l + 1) * k, (i1 + 8) * k - 4);
            DrawWithOffset.strokeLine(g,(l + 1) * k, (i1 + 7) * k - 4, (l + 2) * k - 6, (i1 + 7) * k - 6);
            DrawWithOffset.strokeLine(g,(l + 1) * k, (i1 + 8) * k - 4, (l + 2) * k - 6, (i1 + 8) * k - 2);
        } else
        {
            DrawWithOffset.strokeLine(g,(l + 2) * k - 4, (i1 + 7) * k, (l + 3) * k, (i1 + 7) * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k - 4, (i1 + 7) * k - 4, (l + 2) * k - 4, (i1 + 8) * k - 4);
            DrawWithOffset.strokeLine(g,(l + 2) * k - 4, (i1 + 7) * k - 4, (l + 2) * k - 2, (i1 + 7) * k - 6);
            DrawWithOffset.strokeLine(g,(l + 2) * k - 4, (i1 + 8) * k - 4, (l + 2) * k - 2, (i1 + 8) * k - 2);
        }
        if(MiniBPNOAvecRClosed)
        {
            DrawWithOffset.strokeLine(g,(l + 2) * k - 1, (i1 + 6) * k, (l + 3) * k - 1, (i1 + 8) * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 6) * k, (l + 3) * k, (i1 + 8) * k);
        } else
        {
            DrawWithOffset.strokeLine(g,(l + 3) * k - 1, (i1 + 6) * k, (l + 3) * k - 1, (i1 + 8) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 6) * k, (l + 3) * k, (i1 + 8) * k);
        }
    }

    boolean MiniBPNOAvecRClosed;
}
