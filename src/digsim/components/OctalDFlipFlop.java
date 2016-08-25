package digsim.components;

import digsim.IntegratedCircuit;
import digsim.OutputPin;
import digsim.Pin;
import digsim.ElectronicComponent;
import digsim.InputPin;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, Pin, 
//            ElectronicComponent

public class OctalDFlipFlop extends IntegratedCircuit
{

    public OctalDFlipFlop(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 15, 3, 1, 4, 13, 11, 8);
        OUT = new int[8];
        IPin[0] = new InputPin("r", 1, 4, 2, 0, 0, 0, 1);
        IPin[1] = new InputPin("", 1, 2, 2, 0, 0, 0, 4);
        IPin[2] = new InputPin("D0", 1, 6, 2, 0, 0, 0, 8);
        IPin[3] = new InputPin("1", 1, 7, 2, 0, 0, 0, 8);
        IPin[4] = new InputPin("2", 1, 8, 2, 0, 0, 0, 8);
        IPin[5] = new InputPin("3", 1, 9, 2, 0, 0, 0, 8);
        IPin[6] = new InputPin("4", 1, 10, 2, 0, 0, 0, 8);
        IPin[7] = new InputPin("5", 1, 11, 2, 0, 0, 0, 8);
        IPin[8] = new InputPin("6", 1, 12, 2, 0, 0, 0, 8);
        IPin[9] = new InputPin("7", 1, 13, 2, 0, 0, 0, 8);
        IPin[10] = new InputPin("OE", 9, 4, -2, 0, 0, 0, 1);
        OPin[0] = new OutputPin("Q0", 9, 6, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("1", 9, 7, -2, 0, 0, 0, 0);
        OPin[2] = new OutputPin("2", 9, 8, -2, 0, 0, 0, 0);
        OPin[3] = new OutputPin("3", 9, 9, -2, 0, 0, 0, 0);
        OPin[4] = new OutputPin("4", 9, 10, -2, 0, 0, 0, 0);
        OPin[5] = new OutputPin("5", 9, 11, -2, 0, 0, 0, 0);
        OPin[6] = new OutputPin("6", 9, 12, -2, 0, 0, 0, 0);
        OPin[7] = new OutputPin("7", 9, 13, -2, 0, 0, 0, 0);
        for(int k = 0; k < 8; k++)
            OUT[k] = 5 * Math.round((int)(Math.random() * 2D));

        for(int l = 0; l < 11; l++)
            IPin[l].setLevel(-1);

        IPin[10].setLevel(5);
        for(int i1 = 0; i1 < 8; i1++)
            OPin[i1].setLevel(0x6c9d8);

        ComponentName = "Registre de 8 FlipFlop D -PIPO- 3S (74HC273 74HC374)";
        ClassName = "OctalDFlipFlop";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public OctalDFlipFlop(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        OUT = new int[8];
        for(int k = 0; k < 8; k++)
            OUT[k] = 5 * Math.round((int)(Math.random() * 2D));

        for(int l = 0; l < 11; l++)
            IPin[l].setLevel(-1);

        IPin[10].setLevel(5);
        for(int i1 = 0; i1 < 8; i1++)
            OPin[i1].setLevel(0x6c9d8);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        OctalDFlipFlop octaldflipflop = new OctalDFlipFlop(this, i, j);
        return octaldflipflop;
    }

    public void SimulateLogic()
    {
        if(IPin[0].getLevel() == 5)
        {
            for(int i = 0; i < 8; i++)
                OUT[i] = 0;

        }
        if(IPin[1].OldLevel == 0 && IPin[1].getLevel() == 5 && IPin[0].getLevel() == 0)
        {
            for(int j = 0; j < 8; j++)
                OUT[j] = IPin[2 + j].getLevel();

        }
        if(IPin[10].getLevel() == 0)
        {
            for(int k = 0; k < 8; k++)
                OPin[k].setLevel(0x6c9d8);

        } else
        {
            OPin[0].setLevel(OUT[0]);
            OPin[1].setLevel(OUT[1]);
            OPin[2].setLevel(OUT[2]);
            OPin[3].setLevel(OUT[3]);
            OPin[4].setLevel(OUT[4]);
            OPin[5].setLevel(OUT[5]);
            OPin[6].setLevel(OUT[6]);
            OPin[7].setLevel(OUT[7]);
        }
        IPin[1].OldLevel = IPin[1].getLevel();
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    int OUT[];
}
