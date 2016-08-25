package digsim.components;

import digsim.IntegratedCircuit;
import digsim.OutputPin;
import digsim.Pin;
import digsim.ElectronicComponent;
import digsim.InputPin;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, Pin, 
//            ElectronicComponent

public class JKFlipFlop extends IntegratedCircuit
{

    public JKFlipFlop(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 6, 3, 1, 4, 4, 3, 2);
        IPin[0] = new InputPin("J", 1, 2, 2, 0, 0, 0, 8);
        IPin[1] = new InputPin("", 1, 3, 2, 0, 0, 0, 4);
        IPin[2] = new InputPin("K", 1, 4, 2, 0, 0, 0, 8);
        OPin[0] = new OutputPin("Q", 9, 2, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("Q", 9, 4, -2, 0, 0, 0, 1);
        for(int k = 0; k < 3; k++)
            IPin[k].setLevel(-1);

        OPin[0].setLevel(5 * Math.round((int)(Math.random() * 2D)));
        ComponentName = "FlipFlop JK ''edge-triggered''";
        ClassName = "JKFlipFlop";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public JKFlipFlop(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        for(int k = 0; k < 3; k++)
            IPin[k].setLevel(-1);

        OPin[0].setLevel(5 * Math.round((int)(Math.random() * 2D)));
    }

    public ElectronicComponent Copy(int i, int j)
    {
        JKFlipFlop jkflipflop = new JKFlipFlop(this, i, j);
        return jkflipflop;
    }

    public void SimulateLogic()
    {
        if(IPin[1].OldLevel == 0 && IPin[1].getLevel() == 5)
        {
            if(IPin[0].getLevel() == 5 && IPin[2].getLevel() == 5)
            {
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
            if(IPin[0].getLevel() == 5)
            {
                OPin[0].setLevel(5);
                OPin[1].setLevel(5);
            } else
            if(IPin[2].getLevel() == 5)
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
