package digsim.components.boitier;

import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.OutputPin;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, Pin,
//            ElectronicComponent

public class Boitier7432 extends IntegratedCircuit
{

    public Boitier7432(Pin apin[][], int i, int j)
    {
        super(i, j, 19, 10, 3, 1, 8, 4, 10, 4);
        IPin[0] = new InputPin("", 4, 5, 0, 0, 0, 0, 0);
        IPin[1] = new InputPin("", 5, 5, 0, 0, 0, 0, 0);
        OPin[0] = new OutputPin("", 6, 5, 0, 0, 0, 0, 0);
        IPin[2] = new InputPin("", 7, 5, 0, 0, 0, 0, 0);
        IPin[3] = new InputPin("", 8, 5, 0, 0, 0, 0, 0);
        OPin[1] = new OutputPin("", 9, 5, 0, 0, 0, 0, 0);
        IPin[4] = new InputPin("", 10, 5, 0, 0, 0, 0, 0);
        OPin[2] = new OutputPin("", 10, 1, 0, 0, 0, 0, 0);
        IPin[5] = new InputPin("", 9, 1, 0, 0, 0, 0, 0);
        IPin[6] = new InputPin("", 8, 1, 0, 0, 0, 0, 0);
        OPin[3] = new OutputPin("", 7, 1, 0, 0, 0, 0, 0);
        IPin[7] = new InputPin("", 6, 1, 0, 0, 0, 0, 0);
        IPin[8] = new InputPin("", 5, 1, 0, 0, 0, 0, 0);
        IPin[9] = new InputPin("", 4, 1, 0, 0, 0, 0, 0);
        for(int k = 0; k < 9; k++)
            IPin[k].setLevel(-1);

        ComponentName = "74HC32";
        ClassName = "Boitier7432";
        Can_Rotate = false;
        RegisterPins(apin, i, j);
    }

    public Boitier7432(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        for(int k = 0; k < 9; k++)
            IPin[k].setLevel(-1);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        Boitier7432 boitier7432 = new Boitier7432(this, i, j);
        return boitier7432;
    }

    public void SimulateLogic()
    {
        if((IPin[4].getLevel() == 0) & (IPin[9].getLevel() == 5))
        {
            if(IPin[0].getLevel() == 5 || IPin[1].getLevel() == 5)
                OPin[0].Level = 5;
            else
                OPin[0].Level = 0;
            if(IPin[2].getLevel() == 5 || IPin[3].getLevel() == 5)
                OPin[1].Level = 5;
            else
                OPin[1].Level = 0;
            if(IPin[5].getLevel() == 5 || IPin[6].getLevel() == 5)
                OPin[2].Level = 5;
            else
                OPin[2].Level = 0;
            if(IPin[7].getLevel() == 5 || IPin[8].getLevel() == 5)
                OPin[3].Level = 5;
            else
                OPin[3].Level = 0;
        }
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        if((IPin[4].getLevel() == 0) & (IPin[9].getLevel() == 5))
            DrawWithOffset.fillText(g,">", (l + 3) * k, (i1 + 3) * k + 4);
        DrawWithOffset.fillText(g,">", (l + 3) * k, (i1 + 3) * k + 5);
        DrawWithOffset.fillText(g,"74HC32", (l + 5) * k, (i1 + 3) * k);
        DrawWithOffset.fillText(g,"4OU2", (l + 5) * k + 3, (i1 + 4) * k);
        DrawWithOffset.fillText(g,"_ _ _ _ _ _ _", (l + 4) * k - 2, (i1 + 1) * k - 3);
        DrawWithOffset.fillText(g,"_ _ _ _ _ _ _", (l + 4) * k - 2, (i1 + 1) * k - 2);
        DrawWithOffset.fillText(g,"_ _ _ _ _ _ _", (l + 4) * k - 2, (i1 + 5) * k);
        DrawWithOffset.fillText(g,"_ _ _ _ _ _ _", (l + 4) * k - 2, (i1 + 5) * k + 1);
    }
}
