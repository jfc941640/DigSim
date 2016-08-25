package digsim.components;

import digsim.InputPin;
import digsim.Pin;
import digsim.ElectronicComponent;

// Referenced classes of package digsim:
//            AndPort, InputPin, Pin, ElectronicComponent

public class TwoAndPort extends AndPort
{

    public TwoAndPort(Pin apin[][], int i, int j)
    {
        super(2, i, j, 2);
        ComponentName = "AND2 = ''Et'' -2 entrees-";
        ClassName = "TwoAndPort";
        IPin[0] = new InputPin("A", 1, 2, 2, 0, 0, 0, 2);
        IPin[1] = new InputPin("B", 1, 4, 2, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
    }

    public TwoAndPort(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        TwoAndPort twoandport = new TwoAndPort(this, i, j);
        return twoandport;
    }
}
