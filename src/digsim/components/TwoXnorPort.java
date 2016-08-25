package digsim.components;

import digsim.InputPin;
import digsim.Pin;
import digsim.ElectronicComponent;

// Referenced classes of package digsim:
//            XorPort, InputPin, Pin, ElectronicComponent

public class TwoXnorPort extends XorPort
{

    public TwoXnorPort(Pin apin[][], int i, int j)
    {
        super(2, i, j, 3);
        ComponentName = "XNOR2 = ''OuExclusif-Non'' -2 entrees-";
        ClassName = "TwoXnorPort";
        IPin[0] = new InputPin("A", 1, 2, 2, 0, 0, 0, 2);
        IPin[1] = new InputPin("B", 1, 4, 2, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
    }

    public TwoXnorPort(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        TwoXnorPort twoxnorport = new TwoXnorPort(this, i, j);
        return twoxnorport;
    }
}
