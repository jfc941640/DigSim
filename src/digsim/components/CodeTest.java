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

public class CodeTest extends ElectronicComponent
{

    public CodeTest(Pin apin[][], int i, int j)
    {
        super(i, j, 2, 2, 1, 0, 2, 2, 1, 0);
        IPin[0] = new InputPin("", 0, 1, 1, 0, 0, 0, 0);
        ComponentName = "Testeur de code I-O";
        ClassName = "CodeTest";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public CodeTest(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        CodeTest codetest = new CodeTest(this, i, j);
        return codetest;
    }

    public void SimulateLogic()
    {
        entree = IPin[0].getLevel();
    }

    public void InitBeforeSimulate()
    {
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        g.setFill(MyColor.blue);
        g.setFont(Font.font("Serif", 10));
        DrawWithOffset.fillText(g,Integer.toString(entree), (l + 1) * k + 4, (i1 + 1) * k + 4);
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    int entree;
}
