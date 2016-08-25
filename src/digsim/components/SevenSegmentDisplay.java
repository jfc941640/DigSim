package digsim.components;


import java.io.PrintStream;

import digsim.DigSim;
import digsim.DrawWithOffset;
import digsim.IntegratedCircuit;
import digsim.MyColor;
import digsim.Pin;
import digsim.ElectronicComponent;
import digsim.InputPin;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, DigSim, MyColor,
//            Pin, ElectronicComponent

public class SevenSegmentDisplay extends IntegratedCircuit
{

    public SevenSegmentDisplay(Pin apin[][], Color color, int i, int j)
    {
        super(i, j, 12, 12, 3, 1, 6, 8, 9, 0);
        LEDColorOn = Color.YELLOW;
        if(DigSim.DisplayType.equals("monochrome"))
            LEDColorOff = Color.DARKGRAY;
        if(DigSim.DisplayType.equals("monochrome"))
            LEDColorOn = Color.WHITE;
        IPin[0] = new InputPin("a", 1, 2, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("b", 1, 3, 2, 0, 0, 0, 0);
        IPin[2] = new InputPin("c", 1, 4, 2, 0, 0, 0, 0);
        IPin[3] = new InputPin("d", 1, 5, 2, 0, 0, 0, 0);
        IPin[4] = new InputPin("e", 1, 6, 2, 0, 0, 0, 0);
        IPin[5] = new InputPin("f", 1, 7, 2, 0, 0, 0, 0);
        IPin[6] = new InputPin("g", 1, 8, 2, 0, 0, 0, 0);
        IPin[7] = new InputPin("dp", 11, 8, -2, 0, 0, 0, 2);
        IPin[8] = new InputPin("ck", 6, 11, 0, -2, 0, 0, 0);
        ComponentName = "Afficheur 7 segments";
        ClassName = "SevenSegmentDisplay";
        RegisterPins(apin, i, j);
    }

    public SevenSegmentDisplay(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        SevenSegmentDisplay sevensegmentdisplay = new SevenSegmentDisplay(this, i, j);
        sevensegmentdisplay.LEDColorOn = LEDColorOn;
        return sevensegmentdisplay;
    }

    public void SimulateLogic()
    {
    }

    public void Save(PrintStream printstream)
    {
        printstream.println("describe component SevenSegmentDisplay");
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
        String s1 = new String("[|SevenSegmentDisplay|pos|" + Pos.x + "|" + Pos.y + "|rotation|" + NbRotation + s + "]\r\n^");
        return s1;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        super.draw(g, i, j, k);
        g.setFill(MyColor.black);
        DrawWithOffset.fillRect(g,(l + HitBox.x) * k, (i1 + HitBox.y) * k, HitBoxSize.width * k, HitBoxSize.height * k);
        if(IPin[0].getLevel() == 5 && IPin[8].getLevel() == 0)
            g.setFill(LEDColorOn);
        else
            g.setFill(LEDColorOff);
        DrawWithOffset.fillRect(g,(int)(((double)l + 4.5D) * (double)k + 1.0D), (int)(((double)i1 + 1.25D) * (double)k), (int)((double)k * 2.75D), k / 2);
        if(IPin[1].getLevel() == 5 && IPin[8].getLevel() == 0)
            g.setFill(LEDColorOn);
        else
            g.setFill(LEDColorOff);
        DrawWithOffset.fillRect(g,(int)(((double)l + 7.5D) * (double)k), (int)(((double)i1 + 1.5D) * (double)k), k / 2, k * 3 - 1);
        if(IPin[2].getLevel() == 5 && IPin[8].getLevel() == 0)
            g.setFill(LEDColorOn);
        else
            g.setFill(LEDColorOff);
        DrawWithOffset.fillRect(g,(int)(((double)l + 7.5D) * (double)k), (int)(((double)i1 + 4.5D) * (double)k + 1.0D), k / 2, k * 3 - 1);
        if(IPin[3].getLevel() == 5 && IPin[8].getLevel() == 0)
            g.setFill(LEDColorOn);
        else
            g.setFill(LEDColorOff);
        DrawWithOffset.fillRect(g,(int)(((double)l + 4.5D) * (double)k + 1.0D), (int)(((double)i1 + 7.25D) * (double)k), (int)((double)k * 2.75D), k / 2);
        if(IPin[4].getLevel() == 5 && IPin[8].getLevel() == 0)
            g.setFill(LEDColorOn);
        else
            g.setFill(LEDColorOff);
        DrawWithOffset.fillRect(g,(l + 4) * k, (int)(((double)i1 + 4.5D) * (double)k + 1.0D), k / 2, k * 3 - 1);
        if(IPin[5].getLevel() == 5 && IPin[8].getLevel() == 0)
            g.setFill(LEDColorOn);
        else
            g.setFill(LEDColorOff);
        DrawWithOffset.fillRect(g,(l + 4) * k, (int)(((double)i1 + 1.5D) * (double)k), k / 2, k * 3 - 1);
        if(IPin[6].getLevel() == 5 && IPin[8].getLevel() == 0)
            g.setFill(LEDColorOn);
        else
            g.setFill(LEDColorOff);
        DrawWithOffset.fillRect(g,(int)(((double)l + 4.5D) * (double)k + 1.0D), (int)(((double)i1 + 4.25D) * (double)k), (int)((double)k * 2.75D), k / 2);
        if(IPin[7].getLevel() == 5 && IPin[8].getLevel() == 0)
            g.setFill(LEDColorOn);
        else
            g.setFill(LEDColorOff);
        DrawWithOffset.fillOval(g,(l + 8) * k + 1, (int)(((double)i1 + 7.75D) * (double)k), (int)((double)k / 1.5D + 1.0D), (int)((double)k / 1.5D + 1.0D));
        g.setFill(MyColor.darkGray);
        DrawWithOffset.fillText(g,"a", (l + 3) * k + 2, (i1 + 2) * k + 3);
        DrawWithOffset.fillText(g,"b", (l + 3) * k + 2, (i1 + 3) * k + 3);
        DrawWithOffset.fillText(g,"c", (l + 3) * k + 2, (i1 + 4) * k + 3);
        DrawWithOffset.fillText(g,"d", (l + 3) * k + 2, (i1 + 5) * k + 3);
        DrawWithOffset.fillText(g,"e", (l + 3) * k + 2, (i1 + 6) * k + 3);
        DrawWithOffset.fillText(g,"f", (l + 3) * k + 2, (i1 + 7) * k + 3);
        DrawWithOffset.fillText(g,"g", (l + 3) * k + 2, (i1 + 8) * k + 3);
        DrawWithOffset.fillText(g,"K", (l + 5) * k + 5, (i1 + 9) * k - 1);
    }

    public void InitBeforeSimulate()
    {
        for(int i = 0; i < 9; i++)
            IPin[i].InitBeforeSimulate();

    }

    protected Color LEDColorOn;
    static Color LEDColorOff;
    static final int seg_a = 0;
    static final int seg_b = 1;
    static final int seg_c = 2;
    static final int seg_d = 3;
    static final int seg_e = 4;
    static final int seg_f = 5;
    static final int seg_g = 6;
    static final int seg_dp = 7;
    static final int ck = 8;

    static
    {
        LEDColorOff = MyColor.darkGray;
    }
}
