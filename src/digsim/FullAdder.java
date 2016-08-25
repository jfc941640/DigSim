package digsim;

import digsim.IntegratedCircuit;
import digsim.ElectronicComponent;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, Pin, 
//            ElectronicComponent

public class FullAdder extends IntegratedCircuit
{

    public FullAdder(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 6, 3, 1, 4, 4, 3, 2);
        IPin[0] = new InputPin("Ci", 1, 2, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("x", 1, 3, 2, 0, 0, 0, 0);
        IPin[2] = new InputPin("y", 1, 4, 2, 0, 0, 0, 0);
        OPin[0] = new OutputPin("Co", 9, 4, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("S", 9, 2, -2, 0, 0, 0, 0);
        ComponentName = "Additionneur complet 1 bit";
        ClassName = "FullAdder";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public FullAdder(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        FullAdder fulladder = new FullAdder(this, i, j);
        return fulladder;
    }

    public void SimulateLogic()
    {
        if(IPin[0].getLevel() == 0)
        {
            if(IPin[1].getLevel() == 0)
            {
                if(IPin[2].getLevel() == 0)
                {
                    OPin[0].Level = 0;
                    OPin[1].Level = 0;
                } else
                {
                    OPin[0].Level = 0;
                    OPin[1].Level = 5;
                }
            } else
            if(IPin[2].getLevel() == 0)
            {
                OPin[0].Level = 0;
                OPin[1].Level = 5;
            } else
            {
                OPin[0].Level = 5;
                OPin[1].Level = 0;
            }
        } else
        if(IPin[1].getLevel() == 0)
        {
            if(IPin[2].getLevel() == 0)
            {
                OPin[0].Level = 0;
                OPin[1].Level = 5;
            } else
            {
                OPin[0].Level = 5;
                OPin[1].Level = 0;
            }
        } else
        if(IPin[2].getLevel() == 0)
        {
            OPin[0].Level = 5;
            OPin[1].Level = 0;
        } else
        {
            OPin[0].Level = 5;
            OPin[1].Level = 5;
        }
    }
}
