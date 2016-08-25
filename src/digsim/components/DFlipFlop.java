package digsim.components;

import digsim.ElectronicComponent;
import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.OutputPin;
import digsim.Pin;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, Pin, 
//            ElectronicComponent

public class DFlipFlop extends IntegratedCircuit
{

    public DFlipFlop(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 6, 3, 1, 4, 4, 2, 2);
        IPin[0] = new InputPin("D", 1, 2, 2, 0, 0, 0, 8);
        IPin[1] = new InputPin("", 1, 4, 2, 0, 0, 0, 4);
        OPin[0] = new OutputPin("Q", 9, 2, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("Q", 9, 4, -2, 0, 0, 0, 1);
        OPin[0].setLevel(5 * Math.round((int)(Math.random() * 2D)));
        for(int k = 0; k < 2; k++)
            IPin[k].setLevel(-1);

        ComponentName = "FlipFlop D ''edge-triggered''";
        ClassName = "DFlipFlop";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public DFlipFlop(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        OPin[0].setLevel(5 * Math.round((int)(Math.random() * 2D)));
        for(int k = 0; k < 2; k++)
            IPin[k].setLevel(-1);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        DFlipFlop dflipflop = new DFlipFlop(this, i, j);
        return dflipflop;
    }

    public void SimulateLogic()
    {
        if(IPin[1].OldLevel == 0 && IPin[1].getLevel() == 5)
        {
            OPin[0].setLevel(IPin[0].getLevel());
            OPin[1].setLevel(IPin[0].getLevel());
        } else
        {
            OPin[0].setLevel(OPin[0].getLevel());
            OPin[1].setLevel(OPin[0].getLevel());
        }
        IPin[1].OldLevel = IPin[1].getLevel();
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }
}
