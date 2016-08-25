package digsim.components;

import digsim.IntegratedCircuit;
import digsim.OutputPin;
import digsim.Pin;
import digsim.ElectronicComponent;
import digsim.InputPin;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, Pin, 
//            ElectronicComponent

public class GatedSRLatch extends IntegratedCircuit
{

    public GatedSRLatch(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 6, 3, 1, 4, 4, 3, 2);
        IPin[0] = new InputPin("s", 1, 2, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("r", 1, 4, 2, 0, 0, 0, 0);
        IPin[2] = new InputPin("H", 1, 3, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("r", 1, 4, 2, 0, 0, 0, 0);
        OPin[0] = new OutputPin("Q", 9, 2, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("Q", 9, 4, -2, 0, 0, 0, 1);
        int k = 5 * Math.round((int)(Math.random() * 2D));
        OPin[0].setLevel(k);
        OPin[1].setLevel(k);
        for(int l = 0; l < 3; l++)
            IPin[l].setLevel(-1);

        ComponentName = "Latch SRH (a verrouillage)";
        ClassName = "GatedSRLatch";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public GatedSRLatch(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        int k = 5 * Math.round((int)(Math.random() * 2D));
        OPin[0].setLevel(k);
        OPin[1].setLevel(k);
        for(int l = 0; l < 3; l++)
            IPin[l].setLevel(-1);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        GatedSRLatch gatedsrlatch = new GatedSRLatch(this, i, j);
        return gatedsrlatch;
    }

    public void SimulateLogic()
    {
        if(IPin[2].getLevel() == 5)
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
}
