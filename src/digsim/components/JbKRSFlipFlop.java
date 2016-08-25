package digsim.components;

import digsim.IntegratedCircuit;
import digsim.OutputPin;
import digsim.Pin;
import digsim.ElectronicComponent;
import digsim.InputPin;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, Pin, 
//            ElectronicComponent

public class JbKRSFlipFlop extends IntegratedCircuit
{

    public JbKRSFlipFlop(Pin apin[][], int i, int j)
    {
        super(i, j, 9, 9, 3, 3, 4, 4, 5, 2);
        IPin[0] = new InputPin("J", 1, 4, 2, 0, 0, 0, 8);
        IPin[1] = new InputPin("", 1, 5, 2, 0, 0, 0, 4);
        IPin[2] = new InputPin("K", 1, 6, 2, 0, 0, 0, 64);
        IPin[3] = new InputPin("r", 5, 9, 0, -2, 0, 0, 1);
        IPin[4] = new InputPin("s", 5, -1, 0, 2, 0, 0, 1);
        OPin[0] = new OutputPin("Q", 9, 4, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("Q", 9, 6, -2, 0, 0, 0, 1);
        OPin[0].setLevel(5 * Math.round((int)(Math.random() * 2D)));
        for(int k = 0; k < 5; k++)
            IPin[k].setLevel(-1);

        ComponentName = "FlipFlop JK! ''edge-triggered'' avec entrees asynchrones";
        ClassName = "JbKRSFlipFlop";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public JbKRSFlipFlop(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        OPin[0].setLevel(5 * Math.round((int)(Math.random() * 2D)));
        for(int k = 0; k < 5; k++)
            IPin[k].setLevel(-1);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        JbKRSFlipFlop jbkrsflipflop = new JbKRSFlipFlop(this, i, j);
        return jbkrsflipflop;
    }

    public void SimulateLogic()
    {
        if(IPin[4].getLevel() == 5 && IPin[3].getLevel() == 0)
            OPin[0].Level = OPin[1].Level = 5;
        if(IPin[4].getLevel() == 0 && IPin[3].getLevel() == 5)
            OPin[0].Level = OPin[1].Level = 0;
        if(IPin[4].getLevel() == 5 && IPin[3].getLevel() == 5)
        {
            OPin[0].Level = 5;
            OPin[1].Level = 0;
        }
        int i = IPin[0].getLevel();
        int j = IPin[2].getLevel();
        if(IPin[1].OldLevel == 0 && IPin[1].getLevel() == 5 && IPin[3].getLevel() != 5 && IPin[4].getLevel() != 5)
        {
            if(i == 5 && j == 5)
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
            if(i == 5)
            {
                OPin[0].setLevel(5);
                OPin[1].setLevel(5);
            } else
            if(j == 5)
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
