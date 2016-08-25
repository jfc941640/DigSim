package digsim.components;

import digsim.ElectronicComponent;
import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.OutputPin;
import digsim.Pin;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, Pin, 
//            ElectronicComponent

public class BCDToSevenSegDecoder extends IntegratedCircuit
{

    public BCDToSevenSegDecoder(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 13, 3, 1, 4, 11, 7, 7);
        ActVal = 0;
        IPin[0] = new InputPin("LE", 1, 2, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("BI", 1, 4, 2, 0, 0, 0, 1);
        IPin[2] = new InputPin("LT", 1, 6, 2, 0, 0, 0, 1);
        IPin[3] = new InputPin("A", 1, 8, 2, 0, 0, 0, 0);
        IPin[4] = new InputPin("B", 1, 9, 2, 0, 0, 0, 0);
        IPin[5] = new InputPin("C", 1, 10, 2, 0, 0, 0, 0);
        IPin[6] = new InputPin("D", 1, 11, 2, 0, 0, 0, 0);
        OPin[0] = new OutputPin("a", 9, 3, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("b", 9, 4, -2, 0, 0, 0, 0);
        OPin[2] = new OutputPin("c", 9, 5, -2, 0, 0, 0, 0);
        OPin[3] = new OutputPin("d", 9, 6, -2, 0, 0, 0, 0);
        OPin[4] = new OutputPin("e", 9, 7, -2, 0, 0, 0, 0);
        OPin[5] = new OutputPin("f", 9, 8, -2, 0, 0, 0, 0);
        OPin[6] = new OutputPin("g", 9, 9, -2, 0, 0, 0, 0);
        ComponentName = "Decodeur-latch 7 segments";
        ClassName = "BCDToSevenSegDecoder";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public BCDToSevenSegDecoder(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        ActVal = 0;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        BCDToSevenSegDecoder bcdtosevensegdecoder = new BCDToSevenSegDecoder(this, i, j);
        return bcdtosevensegdecoder;
    }

    public void SimulateLogic()
    {
        if(IPin[2].getLevel() == 5)
        {
            for(int i = 0; i < 7; i++)
                OPin[i].Level = 5;

            return;
        }
        if(IPin[1].getLevel() == 5)
        {
            for(int j = 0; j < 7; j++)
                OPin[j].Level = 0;

            return;
        }
        if(IPin[0].getLevel() == 0)
        {
            ActVal = 0;
            if(IPin[3].getLevel() == 5)
                ActVal++;
            if(IPin[4].getLevel() == 5)
                ActVal += 2;
            if(IPin[5].getLevel() == 5)
                ActVal += 4;
            if(IPin[6].getLevel() == 5)
                ActVal += 8;
        }
        for(int k = 0; k < 7; k++)
            OPin[k].Level = SegLev[ActVal][k];

    }

    static final int LE = 0;
    static final int BI = 1;
    static final int LT = 2;
    static final int A = 3;
    static final int B = 4;
    static final int C = 5;
    static final int D = 6;
    static final int a = 0;
    static final int b = 1;
    static final int c = 2;
    static final int d = 3;
    static final int e = 4;
    static final int f = 5;
    static final int g = 6;
    static final int SegLev[][] = {
        {
            5, 5, 5, 5, 5, 5, 0
        }, {
            0, 5, 5, 0, 0, 0, 0
        }, {
            5, 5, 0, 5, 5, 0, 5
        }, {
            5, 5, 5, 5, 0, 0, 5
        }, {
            0, 5, 5, 0, 0, 5, 5
        }, {
            5, 0, 5, 5, 0, 5, 5
        }, {
            5, 0, 5, 5, 5, 5, 5
        }, {
            5, 5, 5, 0, 0, 0, 0
        }, {
            5, 5, 5, 5, 5, 5, 5
        }, {
            5, 5, 5, 5, 0, 5, 5
        }, {
            5, 5, 5, 0, 5, 5, 5
        }, {
            0, 0, 5, 5, 5, 5, 5
        }, {
            5, 0, 0, 5, 5, 5, 0
        }, {
            0, 5, 5, 5, 5, 0, 5
        }, {
            5, 0, 0, 5, 5, 5, 5
        }, {
            5, 0, 0, 0, 5, 5, 5
        }
    };
    int ActVal;

}