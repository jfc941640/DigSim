package digsim.components;

import digsim.ElectronicComponent;
import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.OutputPin;
import digsim.Pin;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, Pin, 
//            ElectronicComponent

public class EightBitParInShiftReg extends IntegratedCircuit
{

    public EightBitParInShiftReg(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 17, 3, 1, 4, 15, 12, 2);
        Mem = new int[8];
        IPin[0] = new InputPin("", 1, 2, 2, 0, 0, 0, 4);
        IPin[1] = new InputPin("r", 1, 4, 2, 0, 0, 0, 1);
        IPin[2] = new InputPin("ld", 1, 6, 2, 0, 0, 0, 1);
        IPin[3] = new InputPin("D0", 1, 8, 2, 0, 0, 0, 8);
        IPin[4] = new InputPin("1", 1, 9, 2, 0, 0, 0, 8);
        IPin[5] = new InputPin("2", 1, 10, 2, 0, 0, 0, 8);
        IPin[6] = new InputPin("3", 1, 11, 2, 0, 0, 0, 8);
        IPin[7] = new InputPin("4", 1, 12, 2, 0, 0, 0, 8);
        IPin[8] = new InputPin("5", 1, 13, 2, 0, 0, 0, 8);
        IPin[9] = new InputPin("6", 1, 14, 2, 0, 0, 0, 8);
        IPin[10] = new InputPin("7", 1, 15, 2, 0, 0, 0, 8);
        IPin[11] = new InputPin("G", 1, 3, 2, 0, 0, 0, 1);
        OPin[0] = new OutputPin("Q7", 9, 13, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("Q7", 9, 15, -2, 0, 0, 0, 1);
        for(int k = 0; k < 8; k++)
            Mem[k] = 5 * Math.round((int)(Math.random() * 2D));

        for(int l = 0; l < 12; l++)
            IPin[l].setLevel(-1);

        ComponentName = "Registre 8bits -PISO- (74HC165)";
        ClassName = "EightBitParInShiftReg";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public EightBitParInShiftReg(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Mem = new int[8];
        for(int k = 0; k < 8; k++)
            Mem[k] = 5 * Math.round((int)(Math.random() * 2D));

        for(int l = 0; l < 12; l++)
            IPin[l].setLevel(-1);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        EightBitParInShiftReg eightbitparinshiftreg = new EightBitParInShiftReg(this, i, j);
        return eightbitparinshiftreg;
    }

    public void SimulateLogic()
    {
        if(IPin[2].getLevel() == 5)
        {
            for(int i = 0; i < 8; i++)
                Mem[i] = IPin[i + 3].getLevel();

        }
        if(IPin[1].getLevel() == 5)
        {
            for(int j = 0; j < 8; j++)
                Mem[j] = 0;

        }
        if(IPin[0].OldLevel == 0 && IPin[0].getLevel() == 5 && IPin[11].getLevel() == 5)
        {
            for(int k = 7; k > 0; k--)
                Mem[k] = Mem[k - 1];

            Mem[0] = 0;
        }
        OPin[0].setLevel(Mem[7]);
        OPin[1].setLevel(Mem[7]);
        IPin[0].OldLevel = IPin[0].getLevel();
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    int Mem[];
}
