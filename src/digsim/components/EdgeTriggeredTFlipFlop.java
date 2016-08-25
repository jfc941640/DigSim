package digsim.components;

import digsim.ElectronicComponent;
import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.OutputPin;
import digsim.Pin;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, Pin, 
//            ElectronicComponent

public class EdgeTriggeredTFlipFlop extends IntegratedCircuit
{

    public EdgeTriggeredTFlipFlop(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 6, 3, 1, 4, 4, 2, 2);
        IPin[0] = new InputPin("T", 1, 2, 2, 0, 0, 0, 8);
        IPin[1] = new InputPin("", 1, 4, 2, 0, 0, 0, 4);
        OPin[0] = new OutputPin("Q", 9, 2, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("Q", 9, 4, -2, 0, 0, 0, 1);
        for(int k = 0; k < 2; k++)
            IPin[k].setLevel(-1);

        OPin[0].setLevel(5 * Math.round((int)(Math.random() * 2D)));
        ComponentName = "FlipFlop T ''edge-triggered''";
        ClassName = "EdgeTriggeredTFlipFlop";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public EdgeTriggeredTFlipFlop(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        for(int k = 0; k < 2; k++)
            IPin[k].setLevel(-1);

        OPin[0].setLevel(5 * Math.round((int)(Math.random() * 2D)));
    }

    public ElectronicComponent Copy(int i, int j)
    {
        EdgeTriggeredTFlipFlop edgetriggeredtflipflop = new EdgeTriggeredTFlipFlop(this, i, j);
        return edgetriggeredtflipflop;
    }

    public void SimulateLogic()
    {
        if(IPin[1].getOldLevel() == 0 && IPin[1].getLevel() == 5)
        {
            if(IPin[0].getLevel() == 5)
                if(OPin[0].getLevel() == 0)
                {
                    OPin[0].setLevel(5);
                    OPin[1].setLevel(5);
                } else
                {
                    OPin[0].setLevel(0);
                    OPin[1].setLevel(0);
                }
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
