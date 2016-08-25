package digsim.components;

import digsim.InputPin;
import digsim.Pin;
import digsim.ElectronicComponent;

// Referenced classes of package digsim:
//            AndPort, InputPin, Pin, ElectronicComponent

public class TwoNandPort extends AndPort
{

    public TwoNandPort(Pin apin[][], int i, int j)
    {
        super(2, i, j, 3);
        ComponentName = "NAND2 = ''Et-Non'' -2 entrees-";
        ClassName = "TwoNandPort";
        IPin[0] = new InputPin("A", 1, 2, 2, 0, 0, 0, 2);
        IPin[1] = new InputPin("B", 1, 4, 2, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
    }

    public TwoNandPort(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        TwoNandPort twonandport = new TwoNandPort(this, i, j);
        return twonandport;
    }
}
