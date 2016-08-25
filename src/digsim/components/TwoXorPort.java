package digsim.components;

import digsim.InputPin;
import digsim.Pin;
import digsim.ElectronicComponent;

// Referenced classes of package digsim:
//            XorPort, InputPin, Pin, ElectronicComponent

public class TwoXorPort extends XorPort
{

    public TwoXorPort(Pin apin[][], int i, int j)
    {
        super(2, i, j, 2);
        ComponentName = "XOR2 = ''OuExclusif'' -2 entrees-";
        ClassName = "TwoXorPort";
        IPin[0] = new InputPin("A", 1, 2, 2, 0, 0, 0, 2);
        IPin[1] = new InputPin("B", 1, 4, 2, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
    }

    public TwoXorPort(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        TwoXorPort twoxorport = new TwoXorPort(this, i, j);
        return twoxorport;
    }
}
