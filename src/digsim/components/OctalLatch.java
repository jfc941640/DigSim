package digsim.components;

import digsim.IntegratedCircuit;
import digsim.OutputPin;
import digsim.Pin;
import digsim.ElectronicComponent;
import digsim.InputPin;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, Pin, 
//            ElectronicComponent

public class OctalLatch extends IntegratedCircuit
{

    public OctalLatch(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 13, 3, 1, 4, 11, 10, 8);
        OUT = new int[8];
        IPin[0] = new InputPin("LE", 1, 2, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("D0", 1, 4, 2, 0, 0, 0, 0);
        IPin[2] = new InputPin("1", 1, 5, 2, 0, 0, 0, 0);
        IPin[3] = new InputPin("2", 1, 6, 2, 0, 0, 0, 0);
        IPin[4] = new InputPin("3", 1, 7, 2, 0, 0, 0, 0);
        IPin[5] = new InputPin("4", 1, 8, 2, 0, 0, 0, 0);
        IPin[6] = new InputPin("5", 1, 9, 2, 0, 0, 0, 0);
        IPin[7] = new InputPin("6", 1, 10, 2, 0, 0, 0, 0);
        IPin[8] = new InputPin("7", 1, 11, 2, 0, 0, 0, 0);
        IPin[9] = new InputPin("OE", 9, 2, -2, 0, 0, 0, 1);
        OPin[0] = new OutputPin("Q0", 9, 4, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("1", 9, 5, -2, 0, 0, 0, 0);
        OPin[2] = new OutputPin("2", 9, 6, -2, 0, 0, 0, 0);
        OPin[3] = new OutputPin("3", 9, 7, -2, 0, 0, 0, 0);
        OPin[4] = new OutputPin("4", 9, 8, -2, 0, 0, 0, 0);
        OPin[5] = new OutputPin("5", 9, 9, -2, 0, 0, 0, 0);
        OPin[6] = new OutputPin("6", 9, 10, -2, 0, 0, 0, 0);
        OPin[7] = new OutputPin("7", 9, 11, -2, 0, 0, 0, 0);
        for(int k = 0; k < 8; k++)
            OUT[k] = 5 * Math.round((int)(Math.random() * 2D));

        for(int l = 0; l < 10; l++)
            IPin[l].setLevel(-1);

        IPin[9].setLevel(5);
        for(int i1 = 0; i1 < 8; i1++)
            OPin[i1].setLevel(0x6c9d8);

        ComponentName = "Registre de 8 latches D transparents 3S (74HC373)";
        ClassName = "OctalLatch";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public OctalLatch(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        OUT = new int[8];
        for(int k = 0; k < 8; k++)
            OUT[k] = 5 * Math.round((int)(Math.random() * 2D));

        for(int l = 0; l < 10; l++)
            IPin[l].setLevel(-1);

        IPin[9].setLevel(5);
        for(int i1 = 0; i1 < 8; i1++)
            OPin[i1].setLevel(0x6c9d8);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        OctalLatch octallatch = new OctalLatch(this, i, j);
        return octallatch;
    }

    public void SimulateLogic()
    {
        if(IPin[0].getLevel() == 0)
        {
            for(int i = 0; i < 8; i++)
                OUT[i] = IPin[1 + i].getLevel();

        }
        if(IPin[9].getLevel() == 0)
        {
            for(int j = 0; j < 8; j++)
                OPin[j].setLevel(0x6c9d8);

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
    }

    int OUT[];
}
