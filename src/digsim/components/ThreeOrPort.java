package digsim.components;

import digsim.InputPin;
import digsim.Pin;
import digsim.ElectronicComponent;

// Referenced classes of package digsim:
//            OrPort, InputPin, Pin, ElectronicComponent

public class ThreeOrPort extends OrPort
{

    public ThreeOrPort(Pin apin[][], int i, int j)
    {
        super(3, i, j, 2);
        ComponentName = "OR3 = ''Ou'' -3 entrees-";
        ClassName = "ThreeOrPort";
        IPin[0] = new InputPin("A", 1, 2, 2, 0, 0, 0, 2);
        IPin[1] = new InputPin("B", 1, 3, 2, 0, 0, 0, 2);
        IPin[2] = new InputPin("C", 1, 4, 2, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
    }

    public ThreeOrPort(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        ThreeOrPort threeorport = new ThreeOrPort(this, i, j);
        return threeorport;
    }
}
