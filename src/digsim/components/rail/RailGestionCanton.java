package digsim.components.rail;

import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.OutputPin;
import digsim.Pin;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, Pin,
//            ElectronicComponent

public class RailGestionCanton extends IntegratedCircuit
{

    public RailGestionCanton(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 9, 3, 1, 5, 2, 4, 2);
        IPin[0] = new InputPin("e", 4, 0, 0, 1, 0, 0, 0);
        IPin[1] = new InputPin("1", 7, 0, 0, 1, 0, 0, 0);
        IPin[2] = new InputPin("2", 6, 0, 0, 1, 0, 0, 0);
        IPin[3] = new InputPin("3", 5, 0, 0, 1, 0, 0, 0);
        OPin[0] = new OutputPin("a", 5, 4, 0, -1, 0, 0, 0);
        OPin[1] = new OutputPin("b", 6, 4, 0, -1, 0, 0, 0);
        ComponentName = "Module de gestion de canton";
        ClassName = "RailGestionCanton";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public RailGestionCanton(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        RailGestionCanton railgestioncanton = new RailGestionCanton(this, i, j);
        return railgestioncanton;
    }

    public void SimulateLogic()
    {
        if(IPin[0].getLevel() == 5 && IPin[1].getLevel() == 0 && IPin[2].getLevel() == 0)
            OPin[1].Level = 5;
        else
            OPin[1].Level = 0;
        if(IPin[0].getLevel() == 5 && IPin[1].getLevel() == 0 && (IPin[2].getLevel() == 5 || IPin[3].getLevel() == 0))
            OPin[0].Level = 5;
        else
            OPin[0].Level = 0;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
    }
}
