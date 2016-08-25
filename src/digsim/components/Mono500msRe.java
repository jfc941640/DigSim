package digsim.components;

import java.util.Date;

import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, MyColor,
//            Pin, ElectronicComponent

public class Mono500msRe extends IntegratedCircuit
{

    public Mono500msRe(Pin apin[][], int i, int j)
    {
        super(i, j, 7, 6, 1, 1, 3, 2, 1, 1);
        OUT = 0;
        IPin[0] = new InputPin("", -1, 2, 2, 0, 0, 0, 4);
        OPin[0] = new OutputPin("", 6, 2, -2, 0, 0, 0, 1);
        ComponentName = "Monostable 500ms reenclenchable";
        ClassName = "Mono500msRe";
        RegisterPins(apin, i, j);
        Can_Rotate = true;
        Mono500msReFont = Font.font("Sans-serif", FontWeight.BOLD, 9);
    }

    public Mono500msRe(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        OUT = 0;
        Mono500msReFont = Font.font("Sans-serif", FontWeight.BOLD, 9);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Mono500msRe mono500msre = new Mono500msRe(this, i, j);
        return mono500msre;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFont(Mono500msReFont);
        g.setFill(MyColor.red);
        if(IPin[0].PinDim.width > 0)
            DrawWithOffset.fillText(g,"1/2s", (l + 1) * k + 6, (i1 + 2) * k + 4);
        else
        if(IPin[0].PinDim.height > 0)
            DrawWithOffset.fillText(g,"1/2s", (l + 1) * k, (i1 + 2) * k + 8);
        else
        if(IPin[0].PinDim.width < 0)
            DrawWithOffset.fillText(g,"1/2s", (l + 1) * k + 2, (i1 + 2) * k + 4);
        else
        if(IPin[0].PinDim.height < 0)
            DrawWithOffset.fillText(g,"1/2s", (l + 1) * k, (i1 + 2) * k + 8);
    }

    public void SimulateLogic()
    {
        if(IPin[0].getOldLevel() == 0 && IPin[0].getLevel() == 5)
        {
            CHRONOzero = (new Date()).getTime();
            OUT = 5;
            OPin[0].setLevel(OUT);
        }
        CHRONO = (new Date()).getTime();
        if(CHRONO - CHRONOzero < 450L)
            OUT = 5;
        else
            OUT = 0;
        OPin[0].setLevel(OUT);
        IPin[0].OldLevel = IPin[0].getLevel();
    }

    public void InitBeforeSimulate()
    {
    }

    public void Simulate(int i)
    {
    }

    long CHRONO;
    long CHRONOzero;
    int OUT;
    public Font Mono500msReFont;
}
