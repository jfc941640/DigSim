package digsim.components;

import digsim.InputPin;
import digsim.Pin;
import digsim.ElectronicComponent;

// Referenced classes of package digsim:
//            AndPort, InputPin, Pin, ElectronicComponent

public class FourAndPort extends AndPort
{

    public FourAndPort(Pin apin[][], int i, int j)
    {
        super(4, i, j, 2);
        ComponentName = "AND4 = ''Et'' -4 entrees-";
        ClassName = "FourAndPort";
        IPin[0] = new InputPin("A", 1, 1, 2, 0, 0, 0, 2);
        IPin[1] = new InputPin("B", 1, 2, 2, 0, 0, 0, 2);
        IPin[2] = new InputPin("C", 1, 4, 2, 0, 0, 0, 2);
        IPin[3] = new InputPin("D", 1, 5, 2, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
    }

    public FourAndPort(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        FourAndPort fourandport = new FourAndPort(this, i, j);
        return fourandport;
    }
}
