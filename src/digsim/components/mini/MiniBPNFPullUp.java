package digsim.components.mini;



import digsim.ComponentPin;
import digsim.DrawWithOffset;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

// Referenced classes of package digsim:
//            ElectronicComponent, OutputPin, MyColor, ComponentPin,
//            Pin

public class MiniBPNFPullUp extends ElectronicComponent
{

    public MiniBPNFPullUp(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 0, 0, 4, 8, 0, 4);
        OUT = true;
        OPin[0] = new OutputPin("", 3, 5, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("", 3, 1, -2, 0, 0, 0, 0);
        OPin[2] = new OutputPin("", 3, 8, -2, 0, 0, 0, 0);
        OPin[3] = new OutputPin("", 3, 9, -2, 0, 0, 0, 0);
        ComponentName = "BP NF (R) avec resistance de pull-up";
        ClassName = "MiniBPNFPullUp";
        Can_Rotate = false;
        RegisterPins(apin, i, j);
    }

    public MiniBPNFPullUp(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        OUT = true;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        MiniBPNFPullUp minibpnfpullup = new MiniBPNFPullUp(this, i, j);
        return minibpnfpullup;
    }

    public boolean SimMouseDown()
    {
        OUT = false;
        return true;
    }

    public boolean SimMouseUp()
    {
        OUT = true;
        return true;
    }

    public void Simulate(int i)
    {
        if(!OUT)
            OPin[0].Level = 5;
        else
            OPin[0].Level = 0;
        OPin[1].Level = 5;
        OPin[2].Level = 0;
        OPin[3].Level = 0;
        InformConnectedComponents(i);
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFont(Font.font("Sans-serif", 12));
        g.setFill(MyColor.lightGray);
        DrawWithOffset.fillRect(g,(l + 3) * k - 3, (i1 + 2) * k, 1 * k - 2, 2 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,(l + 3) * k - 3, (i1 + 2) * k, 1 * k - 2, 2 * k);
        DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 1) * k, (l + 3) * k, (i1 + 2) * k);
        DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 4) * k, (l + 3) * k, (i1 + 6) * k);
        g.setFill(MyColor.blue);
        DrawWithOffset.fillRect(g,(l + 2) * k + 1, (i1 + 9) * k + 2, k * 2 - 1, k / 2 - 3);
        DrawWithOffset.fillRect(g,(l + 2) * k + 4, (i1 + 9) * k + 4, k * 1 + 1, k / 2 - 3);
        DrawWithOffset.fillRect(g,(l + 2) * k + 7, (i1 + 9) * k + 6, k * 1 - 5, k / 2 - 3);
        g.setFill(MyColor.pink);
        DrawWithOffset.fillOval(g,(l + 1) * k + 4, i1 * k + 4, k, k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,(l + 1) * k + 4, i1 * k + 4, k, k);
        DrawWithOffset.strokeLine(g,(l + 1) * k + 4, (i1 + 1) * k + 4, (l + 2) * k + 4, i1 * k + 4);
        g.setFill(MyColor.red);
        DrawWithOffset.fillText(g,"+", l * k + 5, (i1 + 2) * k - 3);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeLine(g,(l + 2) * k + 4, (i1 + 1) * k, (l + 3) * k, (i1 + 1) * k);
        g.setStroke(ComponentPin.PinColor);
        DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 5) * k, (l + 3) * k, (i1 + 6) * k);
        DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 8) * k - 2, (l + 3) * k, (i1 + 9) * k + 2);
        DrawWithOffset.strokeLine(g,(l + 3) * k - 1, (i1 + 5) * k, (l + 3) * k + 1, (i1 + 5) * k);
        if(!OUT)
        {
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 7) * k, (l + 3) * k + 4, (i1 + 7) * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 7) * k - 4, (l + 2) * k, (i1 + 8) * k - 4);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 7) * k - 4, (l + 2) * k + 2, (i1 + 7) * k - 6);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 8) * k - 4, (l + 2) * k + 2, (i1 + 8) * k - 2);
        } else
        {
            DrawWithOffset.strokeLine(g,(l + 2) * k - 4, (i1 + 7) * k, (l + 3) * k, (i1 + 7) * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k - 4, (i1 + 7) * k - 4, (l + 2) * k - 4, (i1 + 8) * k - 4);
            DrawWithOffset.strokeLine(g,(l + 2) * k - 4, (i1 + 7) * k - 4, (l + 2) * k - 2, (i1 + 7) * k - 6);
            DrawWithOffset.strokeLine(g,(l + 2) * k - 4, (i1 + 8) * k - 4, (l + 2) * k - 2, (i1 + 8) * k - 2);
        }
        if(!OUT)
        {
            DrawWithOffset.strokeLine(g,(l + 4) * k + 1, (i1 + 6) * k, (l + 3) * k + 1, (i1 + 8) * k);
            DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 6) * k, (l + 3) * k, (i1 + 8) * k);
        } else
        {
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 6) * k, (l + 3) * k + 1, (i1 + 8) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 6) * k, (l + 3) * k, (i1 + 8) * k);
        }
    }

    public void InitBeforeSimulate()
    {
        OPin[0].InitBeforeSimulate();
    }

    boolean OUT;
}
