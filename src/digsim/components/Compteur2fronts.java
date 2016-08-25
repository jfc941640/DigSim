package digsim.components;

import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.InputPin;
import digsim.MyColor;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, MyColor, Pin

public class Compteur2fronts extends ElectronicComponent
{

    public Compteur2fronts(Pin apin[][], int i, int j)
    {
        super(i, j, 2, 2, 2, 2, 0, 0, 2, 0);
        SwitchClosed = false;
        IPin[0] = new InputPin("", 2, 3, 0, 0, 0, 0, 0);
        IPin[1] = new InputPin("", 2, 3, 1, 0, 0, 0, 0);
        CtrUp = 0;
        CtrDown = 0;
        IPin[0].setLevel(0);
        FonteComposant = Font.font("Sans-serif", 10);
        ComponentName = "Compteur de fronts";
        ClassName = "Compteur2fronts";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public Compteur2fronts(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        SwitchClosed = false;
        CtrUp = 0;
        CtrDown = 0;
        IPin[0].setLevel(0);
        FonteComposant = Font.font("Sans-serif", 10);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Compteur2fronts compteur2fronts = new Compteur2fronts(this, i, j);
        return compteur2fronts;
    }

    public boolean SimMouseDown()
    {
        SwitchClosed = true;
        return true;
    }

    public boolean SimMouseUp()
    {
        SwitchClosed = false;
        return true;
    }

    public void SimulateLogic()
    {
        if(IPin[0].getOldLevel() == 0 && IPin[0].getLevel() == 5 && CtrUp < 9999)
            CtrUp++;
        if(IPin[0].getOldLevel() == 5 && IPin[0].getLevel() == 0 && CtrDown < 9999)
            CtrDown++;
        IPin[0].OldLevel = IPin[0].getLevel();
        if(SwitchClosed)
        {
            CtrUp = 0;
            CtrDown = 0;
        }
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        if(IPin[1].PinDim.width > 0)
        {
            if(CtrUp > 9999)
                CtrUp = 9999;
            g.setFont(FonteComposant);
            g.setFill(MyColor.red);
            DrawWithOffset.fillText(g,"" + Integer.toString(CtrUp), (l + 1) * k - 1, (i1 + 2) * k);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeLine(g,(l + 1) * k - 3, (i1 + 2) * k + 1, (l + 3) * k + 3, (i1 + 2) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k + 1, (l + 2) * k, (i1 + 3) * k);
        } else
        if(IPin[1].PinDim.height > 0)
        {
            if(CtrDown > 9999)
                CtrDown = 9999;
            g.setFont(FonteComposant);
            g.setFill(MyColor.blue);
            DrawWithOffset.fillText(g,"" + Integer.toString(CtrDown), (l + 1) * k - 1, (i1 + 2) * k);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeLine(g,(l + 1) * k - 3, (i1 + 2) * k + 1, (l + 3) * k + 3, (i1 + 2) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k + 6, (l + 2) * k, (i1 + 3) * k);
            DrawWithOffset.strokeOval(g,(l + 2) * k - 2, (i1 + 2) * k + 2, 1 * k - 4, 1 * k - 4);
        } else
        if(IPin[1].PinDim.width < 0)
        {
            if(CtrUp > 99)
                CtrUp = 99;
            g.setFont(FonteComposant);
            g.setFill(MyColor.red);
            DrawWithOffset.fillText(g,"" + Integer.toString(CtrUp), (l + 1) * k + 4, (i1 + 2) * k);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeLine(g,(l + 1) * k + 3, (i1 + 2) * k + 1, (l + 3) * k - 3, (i1 + 2) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k + 1, (l + 2) * k, (i1 + 3) * k);
        } else
        if(IPin[1].PinDim.height < 0)
        {
            if(CtrDown > 99)
                CtrDown = 99;
            g.setFont(FonteComposant);
            g.setFill(MyColor.blue);
            DrawWithOffset.fillText(g,"" + Integer.toString(CtrDown), (l + 1) * k + 4, (i1 + 2) * k);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeLine(g,(l + 1) * k + 3, (i1 + 2) * k + 1, (l + 3) * k - 3, (i1 + 2) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k + 6, (l + 2) * k, (i1 + 3) * k);
            DrawWithOffset.strokeOval(g,(l + 2) * k - 2, (i1 + 2) * k + 2, 1 * k - 4, 1 * k - 4);
        }
    }

    boolean SwitchClosed;
    int CtrUp;
    int CtrDown;
    int Qa;
    int Qb;
    int Qc;
    int Qd;
    public Font FonteComposant;
}
