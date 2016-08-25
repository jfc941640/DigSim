package digsim.components;

import digsim.InputPin;
import digsim.Pin;
import digsim.ElectronicComponent;

// Referenced classes of package digsim:
//            AndPort, InputPin, Pin, ElectronicComponent

public class ThreeNandPort extends AndPort
{

    public ThreeNandPort(Pin apin[][], int i, int j)
    {
        super(3, i, j, 3);
        ComponentName = "NAND3 = ''Et-Non'' -3 entrees-";
        ClassName = "ThreeNandPort";
        IPin[0] = new InputPin("A", 1, 2, 2, 0, 0, 0, 2);
        IPin[1] = new InputPin("B", 1, 3, 2, 0, 0, 0, 2);
        IPin[2] = new InputPin("C", 1, 4, 2, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
    }

    public ThreeNandPort(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        ThreeNandPort threenandport = new ThreeNandPort(this, i, j);
        return threenandport;
    }
}
