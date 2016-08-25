package digsim.components;

import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

// Referenced classes of package digsim:
//            ElectronicComponent, OutputPin, MyColor, Pin

public class Vcc extends ElectronicComponent
{

    public Vcc(Pin apin[][], int i, int j)
    {
        super(i, j, 4, 4, 1, 3, 1, 1, 0, 1);
        OPin[0] = new OutputPin("Vcc", 2, 5, 0, -1, 0, 0, 2);
        OPin[0].Level = 5;
        ComponentName = "Vcc (niveau logique 1)";
        ClassName = "Vcc";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public Vcc(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Vcc vcc = new Vcc(this, i, j);
        vcc.OPin[0].Level = 5;
        return vcc;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFont(Font.font("Sans-serif", 12));
        DrawOutputPins(g, l, i1, k);
        if(OPin[0].PinDim.width > 0)
        {
            g.setFill(MyColor.pink);
            DrawWithOffset.fillOval(g,(l + 1) * k, (i1 + 3) * k + 4, k, k);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeOval(g,(l + 1) * k, (i1 + 3) * k + 4, k, k);
            DrawWithOffset.strokeLine(g,(l + 1) * k, (i1 + 4) * k + 4, (l + 2) * k, (i1 + 3) * k + 4);
            g.setFill(MyColor.red);
            DrawWithOffset.fillText(g,"+", (l + 2) * k + 1, (i1 + 5) * k - 3);
            DrawWithOffset.strokeLine(g,l * k, (i1 + 4) * k, (l + 1) * k, (i1 + 4) * k);
        } else
        if(OPin[0].PinDim.width < 0)
        {
            g.setFill(MyColor.pink);
            DrawWithOffset.fillOval(g,(l + 1) * k, (i1 + 2) * k + 4, k, k);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeOval(g,(l + 1) * k, (i1 + 2) * k + 4, k, k);
            DrawWithOffset.strokeLine(g,(l + 1) * k, (i1 + 3) * k + 4, (l + 2) * k, (i1 + 2) * k + 4);
            g.setFill(MyColor.red);
            DrawWithOffset.fillText(g,"+", l * k + 1, (i1 + 4) * k - 3);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 3) * k, (l + 3) * k, (i1 + 3) * k);
        } else
        if(OPin[0].PinDim.height > 0)
        {
            g.setFill(MyColor.pink);
            DrawWithOffset.fillOval(g,l * k + 4, (i1 + 3) * k, k, k);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeOval(g,l * k + 4, (i1 + 3) * k, k, k);
            DrawWithOffset.strokeLine(g,l * k + 4, (i1 + 4) * k, (l + 1) * k + 4, (i1 + 3) * k);
            g.setFill(MyColor.red);
            DrawWithOffset.fillText(g,"+", l * k + 5, (i1 + 5) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 1) * k, (i1 + 2) * k, (l + 1) * k, (i1 + 3) * k);
        } else
        if(OPin[0].PinDim.height < 0)
        {
            g.setFill(MyColor.pink);
            DrawWithOffset.fillOval(g,(l + 1) * k + 4, (i1 + 3) * k, k, k);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeOval(g,(l + 1) * k + 4, (i1 + 3) * k, k, k);
            DrawWithOffset.strokeLine(g,(l + 1) * k + 4, (i1 + 4) * k, (l + 2) * k + 4, (i1 + 3) * k);
            g.setFill(MyColor.red);
            DrawWithOffset.fillText(g,"+", (l + 1) * k + 5, (i1 + 3) * k + 1);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 4) * k, (l + 2) * k, (i1 + 5) * k);
        }
    }

    public void Simulate(int i)
    {
        InformConnectedComponents(i);
    }
}
