package digsim.components;

import digsim.InputPin;
import digsim.Pin;
import digsim.ElectronicComponent;

// Referenced classes of package digsim:
//            OrPort, InputPin, Pin, ElectronicComponent

public class TwoNorPort extends OrPort
{

    public TwoNorPort(Pin apin[][], int i, int j)
    {
        super(2, i, j, 3);
        ComponentName = "NOR2 = ''Ou-Non'' -2 entrees-";
        ClassName = "TwoNorPort";
        IPin[0] = new InputPin("A", 1, 2, 2, 0, 0, 0, 2);
        IPin[1] = new InputPin("B", 1, 4, 2, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
    }

    public TwoNorPort(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        TwoNorPort twonorport = new TwoNorPort(this, i, j);
        return twonorport;
    }
}
