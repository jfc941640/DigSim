package digsim.components;

import digsim.ElectronicComponent;
import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.OutputPin;
import digsim.Pin;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, Pin, 
//            ElectronicComponent

public class EightBitSerInShiftReg extends IntegratedCircuit
{

    public EightBitSerInShiftReg(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 15, 3, 1, 4, 13, 4, 8);
        OUT = new int[8];
        IPin[0] = new InputPin("", 1, 2, 2, 0, 0, 0, 4);
        IPin[1] = new InputPin("r", 1, 4, 2, 0, 0, 0, 1);
        IPin[2] = new InputPin("D", 1, 6, 2, 0, 0, 0, 8);
        IPin[3] = new InputPin("OE", 9, 4, -2, 0, 0, 0, 1);
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

        for(int l = 0; l < 4; l++)
            IPin[l].setLevel(-1);

        IPin[3].setLevel(5);
        for(int i1 = 0; i1 < 8; i1++)
            OPin[i1].setLevel(0x6c9d8);

        ComponentName = "Registre 8bits -SIPO-SISO- 3S (~74HC164)";
        ClassName = "EightBitSerInShiftReg";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public EightBitSerInShiftReg(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        OUT = new int[8];
        for(int k = 0; k < 8; k++)
            OUT[k] = 5 * Math.round((int)(Math.random() * 2D));

        for(int l = 0; l < 4; l++)
            IPin[l].setLevel(-1);

        IPin[3].setLevel(5);
        for(int i1 = 0; i1 < 8; i1++)
            OPin[i1].setLevel(0x6c9d8);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        EightBitSerInShiftReg eightbitserinshiftreg = new EightBitSerInShiftReg(this, i, j);
        return eightbitserinshiftreg;
    }

    public void SimulateLogic()
    {
        if(IPin[1].getLevel() == 5)
        {
            for(int i = 0; i < 8; i++)
                OUT[i] = 0;

        }
        if(IPin[0].OldLevel == 0 && IPin[0].getLevel() == 5 && IPin[1].getLevel() == 0)
        {
            for(int j = 7; j > 0; j--)
                OUT[j] = OUT[j - 1];

            OUT[0] = IPin[2].getLevel();
        } else
        {
            for(int k = 0; k < 8; k++)
                OPin[k].setLevel(OUT[k]);

        }
        if(IPin[3].getLevel() == 0)
        {
            for(int l = 0; l < 8; l++)
                OPin[l].setLevel(0x6c9d8);

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
        IPin[0].OldLevel = IPin[0].getLevel();
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    int OUT[];
}
