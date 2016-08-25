package digsim.components;

import java.io.PrintStream;

import digsim.DigSim;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.InputPin;
import digsim.MyColor;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, DigSim, MyColor,
//            Pin

public class LED extends ElectronicComponent
{

    public LED(Pin apin[][], Color color, int i, int j)
    {
        super(i, j, 9, 4, 4, 1, 2, 2, 2, 0);
        LEDColorOn = color;
        if(DigSim.DisplayType.equals("monochrome"))
            LEDColorOff = Color.WHITE;
        ComponentName = "LED";
        ClassName = "LED";
        Can_Rotate = true;
        IPin[0] = new InputPin("Anode", 1, 2, 3, 0, 0, 0, 2);
        IPin[1] = new InputPin("Kathode", 8, 2, -2, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
    }

    public LED(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        LED led = new LED(this, i, j);
        led.LEDColorOn = LEDColorOn;
        return led;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        g.setFill(MyColor.red);
        g.setFont(Font.font("Serif", 10));
        if(IPin[0].PinDim.width > 0)
            DrawWithOffset.fillText(g,"+", (l + IPin[0].PinPos.x + 2) * k, (i1 + IPin[0].PinPos.y + 1) * k);
        else
        if(IPin[0].PinDim.width < 0)
            DrawWithOffset.fillText(g,"+", ((l + IPin[0].PinPos.x) - 3) * k + 4, (i1 + IPin[0].PinPos.y) * k);
        else
        if(IPin[0].PinDim.height > 0)
            DrawWithOffset.fillText(g,"+", ((l + IPin[0].PinPos.x) - 1) * k, (i1 + IPin[0].PinPos.y + 3) * k);
        else
        if(IPin[0].PinDim.height < 0)
            DrawWithOffset.fillText(g,"+", (l + IPin[0].PinPos.x) * k + 4, ((i1 + IPin[0].PinPos.y) - 2) * k);
        if(IPin[0].getLevel() == 5 && IPin[1].getLevel() == 0)
            g.setFill(LEDColorOn);
        else
            g.setFill(LEDColorOff);
        DrawWithOffset.fillOval(g,(l + 4) * k, (i1 + 1) * k, 2 * k, 2 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,(l + 4) * k, (i1 + 1) * k, 2 * k, 2 * k);
        g.setStroke(LEDColorOn);
        DrawWithOffset.strokeOval(g,(l + 4) * k + 1, (i1 + 1) * k + 1, 2 * k - 2, 2 * k - 2);
    }

    public void Save(PrintStream printstream)
    {
        printstream.println("describe component LED");
        printstream.println(" pos " + Pos.x + " " + Pos.y);
        printstream.println(" rotation " + NbRotation);
        if(LEDColorOn == MyColor.red)
            printstream.println(" color red");
        else
        if(LEDColorOn == MyColor.green)
            printstream.println(" color green");
        else
        if(LEDColorOn == MyColor.yellow)
            printstream.println(" color yellow");
        else
        if(LEDColorOn == MyColor.blue)
            printstream.println(" color blue ");
        else
        if(LEDColorOn == MyColor.orange)
            printstream.println(" color orange ");
        else
        if(LEDColorOn == MyColor.cyan)
            printstream.println(" color cyan ");
        else
        if(LEDColorOn == MyColor.magenta)
            printstream.println(" color magenta ");
        else
        if(LEDColorOn == MyColor.pink)
            printstream.println(" color pink ");
        printstream.println("end describe");
    }

    public String ExportAsScript()
    {
        String s = null;
        if(LEDColorOn == MyColor.red)
            s = new String("|color|red|");
        else
        if(LEDColorOn == MyColor.green)
            s = new String("|color|green|");
        else
        if(LEDColorOn == MyColor.yellow)
            s = new String("|color|yellow|");
        else
        if(LEDColorOn == MyColor.blue)
            s = new String("|color|blue|");
        else
        if(LEDColorOn == MyColor.orange)
            s = new String("|color|orange|");
        else
        if(LEDColorOn == MyColor.cyan)
            s = new String("|color|cyan|");
        else
        if(LEDColorOn == MyColor.magenta)
            s = new String("|color|magenta|");
        else
        if(LEDColorOn == MyColor.pink)
            s = new String("|color|pink|");
        String s1 = new String("[|LED|pos|" + Pos.x + "|" + Pos.y + "|rotation|" + NbRotation + s + "]\r\n^");
        return s1;
    }

    public void InitBeforeSimulate()
    {
        IPin[0].InitBeforeSimulate();
        IPin[1].InitBeforeSimulate();
    }

    protected Color LEDColorOn;
    static Color LEDColorOff;

    static
    {
        LEDColorOff = MyColor.white;
    }
}
