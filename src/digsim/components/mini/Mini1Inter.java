package digsim.components.mini;

import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

// Referenced classes of package digsim:
//            ElectronicComponent, OutputPin, MyColor, Pin

public class Mini1Inter extends ElectronicComponent
{

    public Mini1Inter(Pin apin[][], int i, int j)
    {
        super(i, j, 2, 2, 1, 0, 3, 2, 0, 1);
        MOUSE = false;
        ComponentName = "Switch";
        ClassName = "Mini1Inter";
        Can_Rotate = false;
        OPin[0] = new OutputPin("", 5, 2, -1, 0, 0, 0, 0);
        RegisterPins(apin, i, j);
        OPin[0].setLevel(0);
    }

    public Mini1Inter(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        MOUSE = false;
        OPin[0].setLevel(0);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Mini1Inter mini1inter = new Mini1Inter(this, i, j);
        return mini1inter;
    }

    public boolean SimMouseDown()
    {
        MOUSE = !MOUSE;
        return true;
    }

    public void Simulate(int i)
    {
        if(MOUSE)
            OPin[0].setLevel(5);
        else
            OPin[0].setLevel(0);
        InformConnectedComponents(i);
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFont(Font.font("Sans-serif", 12));
        DrawOutputPins(g, l, i1, k);
        g.setStroke(Color.rgb(224, 224, 224));
        DrawWithOffset.fillRect(g,(l + 1) * k - 2, i1 * k - 4, 3 * k + 4, 3 * k);
        g.setFill(MyColor.black);
        DrawWithOffset.strokeRect(g,(l + 1) * k - 2, i1 * k - 4, 3 * k + 4, 3 * k);
        g.setFill(MyColor.pink);
        DrawWithOffset.fillOval(g,(l + 1) * k + 1, i1 * k + 4, k, k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,(l + 1) * k + 1, i1 * k + 4, k, k);
        DrawWithOffset.strokeLine(g,(l + 1) * k + 1, (i1 + 1) * k + 4, (l + 2) * k + 1, i1 * k + 4);
        g.setFill(MyColor.red);
        DrawWithOffset.fillText(g,"+", (l + 1) * k + 3, i1 * k + 4);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeLine(g,(l + 1) * k + 5, (i1 + 1) * k + 4, (l + 1) * k + 5, (i1 + 2) * k);
        DrawWithOffset.strokeLine(g,(l + 1) * k + 6, (i1 + 2) * k, (l + 2) * k + 4, (i1 + 2) * k);
        g.setFill(MyColor.yellow);
        DrawWithOffset.fillRect(g,(l + 1) * k + 4, (i1 + 2) * k - 1, k - 6, k - 6);
        g.setStroke(MyColor.lightGray);
        DrawWithOffset.strokeRect(g,(l + 1) * k + 4, (i1 + 2) * k - 1, k - 6, k - 6);
        if(MOUSE)
        {
            g.setFill(MyColor.red);
            DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 2) * k - 2, k + 1, k - 6);
            g.setStroke(MyColor.red);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 2) * k, (l + 4) * k + 1, (i1 + 2) * k);
        } else
        {
            g.setFill(MyColor.blue);
            DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 2) * k - 4, k + 1, k - 6);
            g.setStroke(MyColor.blue);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 2) * k, (l + 4) * k + 1, (i1 + 2) * k);
        }
    }

    public void InitBeforeSimulate()
    {
        OPin[0].InitBeforeSimulate();
    }

    boolean MOUSE;
}
