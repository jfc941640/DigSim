package digsim.components;

import digsim.ElectronicComponent;
import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.OutputPin;
import digsim.Pin;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, Pin, 
//            ElectronicComponent

public class DLatch extends IntegratedCircuit
{

    public DLatch(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 6, 3, 1, 4, 4, 2, 2);
        IPin[0] = new InputPin("D", 1, 2, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("LE", 1, 4, 2, 0, 0, 0, 0);
        OPin[0] = new OutputPin("Q", 9, 2, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("Q", 9, 4, -2, 0, 0, 0, 1);
        int k = 5 * Math.round((int)(Math.random() * 2D));
        OPin[0].setLevel(k);
        OPin[1].setLevel(k);
        for(int l = 0; l < 2; l++)
            IPin[l].setLevel(-1);

        ComponentName = "Latch D (transparent)";
        ClassName = "DLatch";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public DLatch(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        int k = 5 * Math.round((int)(Math.random() * 2D));
        OPin[0].setLevel(k);
        OPin[1].setLevel(k);
        for(int l = 0; l < 2; l++)
            IPin[l].setLevel(-1);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        DLatch dlatch = new DLatch(this, i, j);
        return dlatch;
    }

    public void SimulateLogic()
    {
        if(IPin[1].getLevel() == 5)
            OPin[0].Level = OPin[1].Level = IPin[0].getLevel();
    }
}
