package digsim.components;

import digsim.IntegratedCircuit;
import digsim.OutputPin;
import digsim.Pin;
import digsim.ElectronicComponent;
import digsim.InputPin;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, Pin, 
//            ElectronicComponent

public class SRLatch extends IntegratedCircuit
{

    public SRLatch(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 6, 3, 1, 4, 4, 2, 2);
        IPin[0] = new InputPin("s", 1, 2, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("r", 1, 4, 2, 0, 0, 0, 0);
        OPin[0] = new OutputPin("Q", 9, 2, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("Q", 9, 4, -2, 0, 0, 0, 1);
        int k = 5 * Math.round((int)(Math.random() * 2D));
        OPin[0].setLevel(k);
        OPin[1].setLevel(k);
        for(int l = 0; l < 2; l++)
            IPin[l].setLevel(-1);

        ComponentName = "Latch SR";
        ClassName = "SRLatch";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public SRLatch(ElectronicComponent electroniccomponent, int i, int j)
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
        SRLatch srlatch = new SRLatch(this, i, j);
        return srlatch;
    }

    public void SimulateLogic()
    {
        if(IPin[0].getLevel() == 5 && IPin[1].getLevel() == 0)
            OPin[0].Level = OPin[1].Level = 5;
        if(IPin[0].getLevel() == 0 && IPin[1].getLevel() == 5)
            OPin[0].Level = OPin[1].Level = 0;
        if(IPin[0].getLevel() == 5 && IPin[1].getLevel() == 5)
        {
            OPin[0].Level = 5;
            OPin[1].Level = 0;
        }
    }
}
