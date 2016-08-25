package digsim.components.boitier;

import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.OutputPin;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            IntegratedCircuit, OutputPin, InputPin, Pin,
//            ElectronicComponent

public class Boitier744002 extends IntegratedCircuit
{

    public Boitier744002(Pin apin[][], int i, int j)
    {
        super(i, j, 19, 10, 3, 1, 8, 4, 12, 2);
        OPin[0] = new OutputPin("", 4, 5, 0, 0, 0, 0, 0);
        IPin[0] = new InputPin("", 5, 5, 0, 0, 0, 0, 0);
        IPin[1] = new InputPin("", 6, 5, 0, 0, 0, 0, 0);
        IPin[2] = new InputPin("", 7, 5, 0, 0, 0, 0, 0);
        IPin[3] = new InputPin("", 8, 5, 0, 0, 0, 0, 0);
        IPin[4] = new InputPin("", 9, 5, 0, 0, 0, 0, 0);
        IPin[5] = new InputPin("", 10, 5, 0, 0, 0, 0, 0);
        IPin[6] = new InputPin("", 10, 1, 0, 0, 0, 0, 0);
        IPin[7] = new InputPin("", 9, 1, 0, 0, 0, 0, 0);
        IPin[8] = new InputPin("", 8, 1, 0, 0, 0, 0, 0);
        IPin[9] = new InputPin("", 7, 1, 0, 0, 0, 0, 0);
        IPin[10] = new InputPin("", 6, 1, 0, 0, 0, 0, 0);
        OPin[1] = new OutputPin("", 5, 1, 0, 0, 0, 0, 0);
        IPin[11] = new InputPin("", 4, 1, 0, 0, 0, 0, 0);
        for(int k = 0; k < 11; k++)
            IPin[k].setLevel(-1);

        ComponentName = "74HC4002";
        ClassName = "Boitier744002";
        Can_Rotate = false;
        RegisterPins(apin, i, j);
    }

    public Boitier744002(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        for(int k = 0; k < 11; k++)
            IPin[k].setLevel(-1);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        Boitier744002 boitier744002 = new Boitier744002(this, i, j);
        return boitier744002;
    }

    public void SimulateLogic()
    {
        if((IPin[5].getLevel() == 0) & (IPin[11].getLevel() == 5))
        {
            if(IPin[0].getLevel() == 5 || IPin[1].getLevel() == 5 || IPin[2].getLevel() == 5 || IPin[3].getLevel() == 5)
                OPin[0].Level = 0;
            else
                OPin[0].Level = 5;
            if(IPin[7].getLevel() == 5 || IPin[8].getLevel() == 5 || IPin[9].getLevel() == 5 || IPin[10].getLevel() == 5)
                OPin[1].Level = 0;
            else
                OPin[1].Level = 5;
        }
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        if((IPin[5].getLevel() == 0) & (IPin[11].getLevel() == 5))
            DrawWithOffset.fillText(g,">", (l + 3) * k, (i1 + 3) * k + 4);
        DrawWithOffset.fillText(g,">", (l + 3) * k, (i1 + 3) * k + 5);
        DrawWithOffset.fillText(g,"74HC4002", (l + 4) * k + 5, (i1 + 3) * k);
        DrawWithOffset.fillText(g,"2NOR4", (l + 5) * k + 1, (i1 + 4) * k);
        DrawWithOffset.fillText(g,"_ _ _ _ _ _ _", (l + 4) * k - 2, (i1 + 1) * k - 3);
        DrawWithOffset.fillText(g,"_ _ _ _ _ _ _", (l + 4) * k - 2, (i1 + 1) * k - 2);
        DrawWithOffset.fillText(g,"_ _ _ _ _ _ _", (l + 4) * k - 2, (i1 + 5) * k);
        DrawWithOffset.fillText(g,"_ _ _ _ _ _ _", (l + 4) * k - 2, (i1 + 5) * k + 1);
    }
}
