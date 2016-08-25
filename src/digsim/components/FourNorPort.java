package digsim.components;

import digsim.InputPin;
import digsim.Pin;
import digsim.ElectronicComponent;

// Referenced classes of package digsim:
//            OrPort, InputPin, Pin, ElectronicComponent

public class FourNorPort extends OrPort
{

    public FourNorPort(Pin apin[][], int i, int j)
    {
        super(4, i, j, 3);
        ComponentName = "NOR4 = ''Ou-Non'' -4 entrees-";
        ClassName = "FourNorPort";
        IPin[0] = new InputPin("A", 1, 1, 2, 0, 0, 0, 2);
        IPin[1] = new InputPin("B", 1, 2, 2, 0, 0, 0, 2);
        IPin[2] = new InputPin("C", 1, 4, 2, 0, 0, 0, 2);
        IPin[3] = new InputPin("D", 1, 5, 2, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
    }

    public FourNorPort(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        FourNorPort fournorport = new FourNorPort(this, i, j);
        return fournorport;
    }
}
