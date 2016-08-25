package digsim.components.essai;

import digsim.DrawWithOffset;
import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.OutputPin;
import digsim.Pin;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, Pin,
//            ElectronicComponent

public class ESSAICOMPOSANT9 extends IntegratedCircuit
{

    public ESSAICOMPOSANT9(Pin apin[][], int i, int j)
    {
        super(i, j, 19, 10, 3, 1, 8, 4, 8, 6);
        IPin[0] = new InputPin("", 4, 6, 0, -1, 0, 0, 0);
        OPin[0] = new OutputPin("", 5, 6, 0, -1, 0, 0, 0);
        IPin[1] = new InputPin("", 6, 6, 0, -1, 0, 0, 0);
        OPin[1] = new OutputPin("", 7, 6, 0, -1, 0, 0, 0);
        IPin[2] = new InputPin("", 8, 6, 0, -1, 0, 0, 0);
        OPin[2] = new OutputPin("", 9, 6, 0, -1, 0, 0, 0);
        IPin[3] = new InputPin("", 10, 6, 0, -1, 0, 0, 0);
        OPin[3] = new OutputPin("", 10, 0, 0, 1, 0, 0, 0);
        IPin[4] = new InputPin("", 9, 0, 0, 1, 0, 0, 0);
        OPin[4] = new OutputPin("", 8, 0, 0, 1, 0, 0, 0);
        IPin[5] = new InputPin("", 7, 0, 0, 1, 0, 0, 0);
        OPin[5] = new OutputPin("", 6, 0, 0, 1, 0, 0, 0);
        IPin[6] = new InputPin("", 5, 0, 0, 1, 0, 0, 0);
        IPin[7] = new InputPin("", 4, 0, 0, 1, 0, 0, 0);
        ComponentName = "Boitier 09";
        ClassName = "ESSAICOMPOSANT9";
        Can_Rotate = false;
        RegisterPins(apin, i, j);
    }

    public ESSAICOMPOSANT9(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        ESSAICOMPOSANT9 essaicomposant9 = new ESSAICOMPOSANT9(this, i, j);
        return essaicomposant9;
    }

    public void SimulateLogic()
    {
        if(IPin[3].getLevel() == 0 || IPin[7].getLevel() == 5)
        {
            if(IPin[0].getLevel() == 0)
                OPin[0].Level = 5;
            else
                OPin[0].Level = 0;
            if(IPin[1].getLevel() == 0)
                OPin[1].Level = 5;
            else
                OPin[1].Level = 0;
            if(IPin[2].getLevel() == 0)
                OPin[2].Level = 5;
            else
                OPin[2].Level = 0;
            if(IPin[4].getLevel() == 0)
                OPin[3].Level = 5;
            else
                OPin[3].Level = 0;
            if(IPin[5].getLevel() == 0)
                OPin[4].Level = 5;
            else
                OPin[4].Level = 0;
            if(IPin[6].getLevel() == 0)
                OPin[5].Level = 5;
            else
                OPin[5].Level = 0;
        }
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        if(IPin[3].getLevel() == 0 || IPin[7].getLevel() == 5)
            DrawWithOffset.fillText(g,">", (l + 3) * k, (i1 + 3) * k + 4);
        DrawWithOffset.fillText(g,">", (l + 3) * k, (i1 + 3) * k + 5);
        DrawWithOffset.fillText(g,"74HC04", (l + 5) * k, (i1 + 3) * k + 5);
    }
}
