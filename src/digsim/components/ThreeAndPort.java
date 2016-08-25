package digsim.components;

import digsim.InputPin;
import digsim.Pin;
import digsim.ElectronicComponent;

// Referenced classes of package digsim:
//            AndPort, InputPin, Pin, ElectronicComponent

public class ThreeAndPort extends AndPort
{

    public ThreeAndPort(Pin apin[][], int i, int j)
    {
        super(3, i, j, 2);
        ComponentName = "AND3 = ''Et'' -3 entrees-";
        ClassName = "ThreeAndPort";
        IPin[0] = new InputPin("A", 1, 2, 2, 0, 0, 0, 2);
        IPin[1] = new InputPin("B", 1, 3, 2, 0, 0, 0, 2);
        IPin[2] = new InputPin("C", 1, 4, 2, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
    }

    public ThreeAndPort(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        ThreeAndPort threeandport = new ThreeAndPort(this, i, j);
        return threeandport;
    }
}
