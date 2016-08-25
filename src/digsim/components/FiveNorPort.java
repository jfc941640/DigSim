package digsim.components;

import digsim.InputPin;
import digsim.Pin;
import digsim.ElectronicComponent;

// Referenced classes of package digsim:
//            OrPort, InputPin, Pin, ElectronicComponent

public class FiveNorPort extends OrPort
{

    public FiveNorPort(Pin apin[][], int i, int j)
    {
        super(5, i, j, 3);
        ComponentName = "NOR5 = ''Ou-Non'' -5 entrees-";
        ClassName = "FiveNorPort";
        IPin[0] = new InputPin("A", 1, 1, 2, 0, 0, 0, 2);
        IPin[1] = new InputPin("B", 1, 2, 2, 0, 0, 0, 2);
        IPin[2] = new InputPin("C", 1, 3, 2, 0, 0, 0, 2);
        IPin[3] = new InputPin("D", 1, 4, 2, 0, 0, 0, 2);
        IPin[4] = new InputPin("E", 1, 5, 2, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
    }

    public FiveNorPort(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        FiveNorPort fivenorport = new FiveNorPort(this, i, j);
        return fivenorport;
    }
}
