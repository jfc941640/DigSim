package digsim.components;

import digsim.InputPin;
import digsim.Pin;
import digsim.ElectronicComponent;

// Referenced classes of package digsim:
//            OrPort, InputPin, Pin, ElectronicComponent

public class TwoOrPort extends OrPort
{

    public TwoOrPort(Pin apin[][], int i, int j)
    {
        super(2, i, j, 2);
        ComponentName = "OR2 = ''Ou'' -2 entrees-";
        ClassName = "TwoOrPort";
        IPin[0] = new InputPin("A", 1, 2, 2, 0, 0, 0, 2);
        IPin[1] = new InputPin("B", 1, 4, 2, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
    }

    public TwoOrPort(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        TwoOrPort twoorport = new TwoOrPort(this, i, j);
        return twoorport;
    }
}
