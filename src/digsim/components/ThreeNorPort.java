package digsim.components;

import digsim.InputPin;
import digsim.Pin;
import digsim.ElectronicComponent;

// Referenced classes of package digsim:
//            OrPort, InputPin, Pin, ElectronicComponent

public class ThreeNorPort extends OrPort
{

    public ThreeNorPort(Pin apin[][], int i, int j)
    {
        super(3, i, j, 3);
        ComponentName = "NOR3 = ''Ou-Non'' -3 entrees-";
        ClassName = "ThreeNorPort";
        IPin[0] = new InputPin("A", 1, 2, 2, 0, 0, 0, 2);
        IPin[1] = new InputPin("B", 1, 3, 2, 0, 0, 0, 2);
        IPin[2] = new InputPin("C", 1, 4, 2, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
    }

    public ThreeNorPort(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        ThreeNorPort threenorport = new ThreeNorPort(this, i, j);
        return threenorport;
    }
}
