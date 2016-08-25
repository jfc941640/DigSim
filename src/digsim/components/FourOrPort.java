package digsim.components;

import digsim.InputPin;
import digsim.Pin;
import digsim.ElectronicComponent;

// Referenced classes of package digsim:
//            OrPort, InputPin, Pin, ElectronicComponent

public class FourOrPort extends OrPort
{

    public FourOrPort(Pin apin[][], int i, int j)
    {
        super(4, i, j, 2);
        ComponentName = "OR4 = ''Ou'' -4 entrees-";
        ClassName = "FourOrPort";
        IPin[0] = new InputPin("A", 1, 1, 2, 0, 0, 0, 2);
        IPin[1] = new InputPin("B", 1, 2, 2, 0, 0, 0, 2);
        IPin[2] = new InputPin("C", 1, 4, 2, 0, 0, 0, 2);
        IPin[3] = new InputPin("D", 1, 5, 2, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
    }

    public FourOrPort(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        FourOrPort fourorport = new FourOrPort(this, i, j);
        return fourorport;
    }
}
