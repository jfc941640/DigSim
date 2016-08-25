package digsim.components;

import digsim.ElectronicComponent;
import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.OutputPin;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, Pin,
//            ElectronicComponent

public class Comparateur4bits extends IntegratedCircuit
{

    public Comparateur4bits(Pin apin[][], int i, int j)
    {
        super(i, j, 12, 14, 3, 1, 6, 14, 11, 3);
        InputA = 0;
        InputB = 0;
        IPin[0] = new InputPin("a0", 1, 6, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("a1", 1, 7, 2, 0, 0, 0, 0);
        IPin[2] = new InputPin("a2", 1, 8, 2, 0, 0, 0, 0);
        IPin[3] = new InputPin("a3", 1, 9, 2, 0, 0, 0, 0);
        IPin[4] = new InputPin("b0", 1, 11, 2, 0, 0, 0, 0);
        IPin[5] = new InputPin("b1", 1, 12, 2, 0, 0, 0, 0);
        IPin[6] = new InputPin("b2", 1, 13, 2, 0, 0, 0, 0);
        IPin[7] = new InputPin("b3", 1, 14, 2, 0, 0, 0, 0);
        IPin[8] = new InputPin("Ia<b", 1, 2, 2, 0, 0, 0, 0);
        IPin[9] = new InputPin("Ia=b", 1, 3, 2, 0, 0, 0, 0);
        IPin[10] = new InputPin("Ia>b", 1, 4, 2, 0, 0, 0, 0);
        OPin[0] = new OutputPin("Qa<b", 11, 12, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("Qa=b", 11, 13, -2, 0, 0, 0, 0);
        OPin[2] = new OutputPin("Qa>b", 11, 14, -2, 0, 0, 0, 0);
        ComponentName = "Comparateur 4 bits (74HC85)";
        ClassName = "Comparateur4bits";
        Can_Rotate = false;
        RegisterPins(apin, i, j);
    }

    public Comparateur4bits(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        InputA = 0;
        InputB = 0;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Comparateur4bits comparateur4bits = new Comparateur4bits(this, i, j);
        return comparateur4bits;
    }

    public void SimulateLogic()
    {
        InputA = 0;
        if(IPin[0].getLevel() == 5)
            InputA++;
        if(IPin[1].getLevel() == 5)
            InputA += 2;
        if(IPin[2].getLevel() == 5)
            InputA += 4;
        if(IPin[3].getLevel() == 5)
            InputA += 8;
        InputB = 0;
        if(IPin[4].getLevel() == 5)
            InputB++;
        if(IPin[5].getLevel() == 5)
            InputB += 2;
        if(IPin[6].getLevel() == 5)
            InputB += 4;
        if(IPin[7].getLevel() == 5)
            InputB += 8;
        if(InputA < InputB)
        {
            OPin[0].Level = 5;
            OPin[1].Level = 0;
            OPin[2].Level = 0;
        }
        if(InputA > InputB)
        {
            OPin[0].Level = 0;
            OPin[1].Level = 0;
            OPin[2].Level = 5;
        }
        if(InputA == InputB)
        {
            if(IPin[9].getLevel() == 5)
            {
                OPin[0].Level = 0;
                OPin[1].Level = 5;
                OPin[2].Level = 0;
            }
            if(IPin[8].getLevel() == 0 && IPin[9].getLevel() == 0 && IPin[10].getLevel() == 5)
            {
                OPin[0].Level = 0;
                OPin[1].Level = 0;
                OPin[2].Level = 5;
            }
            if(IPin[8].getLevel() == 5 && IPin[9].getLevel() == 0 && IPin[10].getLevel() == 0)
            {
                OPin[0].Level = 5;
                OPin[1].Level = 0;
                OPin[2].Level = 0;
            }
            if(IPin[8].getLevel() == 5 && IPin[9].getLevel() == 0 && IPin[10].getLevel() == 5)
            {
                OPin[0].Level = 0;
                OPin[1].Level = 0;
                OPin[2].Level = 0;
            }
            if(IPin[8].getLevel() == 0 && IPin[9].getLevel() == 0 && IPin[10].getLevel() == 0)
            {
                OPin[0].Level = 5;
                OPin[1].Level = 0;
                OPin[2].Level = 5;
            }
        }
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
    }

    int InputA;
    int InputB;
}
