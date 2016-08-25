package digsim.components;

import digsim.IntegratedCircuit;
import digsim.OutputPin;
import digsim.Pin;
import digsim.ElectronicComponent;
import digsim.InputPin;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, Pin, 
//            ElectronicComponent

public class TFlipFlop extends IntegratedCircuit
{

    public TFlipFlop(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 6, 3, 1, 4, 4, 1, 2);
        IPin[0] = new InputPin("T", 1, 3, 2, 0, 0, 0, 4);
        OPin[0] = new OutputPin("Q", 9, 2, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("Q", 9, 4, -2, 0, 0, 0, 1);
        int k = 5 * Math.round((int)(Math.random() * 2D));
        IPin[0].setLevel(5);
        OPin[0].setLevel(k);
        OPin[1].setLevel(k);
        if(OPin[0].getLevel() == 0)
        {
            OPin[0].setLevel(5);
            OPin[1].setLevel(5);
        } else
        {
            OPin[0].setLevel(0);
            OPin[1].setLevel(0);
        }
        IPin[0].OldLevel = IPin[0].getLevel();
        ComponentName = "Bistable T";
        ClassName = "TFlipFlop";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public TFlipFlop(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        int k = 5 * Math.round((int)(Math.random() * 2D));
        IPin[0].setLevel(5);
        OPin[0].setLevel(k);
        OPin[1].setLevel(k);
        if(OPin[0].getLevel() == 0)
        {
            OPin[0].setLevel(5);
            OPin[1].setLevel(5);
        } else
        {
            OPin[0].setLevel(0);
            OPin[1].setLevel(0);
        }
        IPin[0].OldLevel = IPin[0].getLevel();
    }

    public ElectronicComponent Copy(int i, int j)
    {
        TFlipFlop tflipflop = new TFlipFlop(this, i, j);
        return tflipflop;
    }

    public void SimulateLogic()
    {
        if(IPin[0].getOldLevel() == 0 && IPin[0].getLevel() == 5)
            if(OPin[0].getLevel() == 0)
            {
                OPin[0].setLevel(5);
                OPin[1].setLevel(5);
            } else
            {
                OPin[0].setLevel(0);
                OPin[1].setLevel(0);
            }
        IPin[0].OldLevel = IPin[0].getLevel();
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }
}
