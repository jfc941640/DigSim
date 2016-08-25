package digsim.components;

import digsim.InputPin;
import digsim.Pin;
import digsim.ElectronicComponent;

// Referenced classes of package digsim:
//            AndPort, InputPin, Pin, ElectronicComponent

public class FourNandPort extends AndPort
{

    public FourNandPort(Pin apin[][], int i, int j)
    {
        super(4, i, j, 3);
        ComponentName = "NAND4 = ''Et-Non'' -4 entrees-";
        ClassName = "FourNandPort";
        IPin[0] = new InputPin("A", 1, 1, 2, 0, 0, 0, 2);
        IPin[1] = new InputPin("B", 1, 2, 2, 0, 0, 0, 2);
        IPin[2] = new InputPin("C", 1, 4, 2, 0, 0, 0, 2);
        IPin[3] = new InputPin("C", 1, 5, 2, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
    }

    public FourNandPort(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        FourNandPort fournandport = new FourNandPort(this, i, j);
        return fournandport;
    }
}
