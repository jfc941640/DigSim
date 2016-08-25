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

public class Comparateur8bits extends IntegratedCircuit
{

    public Comparateur8bits(Pin apin[][], int i, int j)
    {
        super(i, j, 12, 14, 3, 1, 4, 18, 17, 1);
        InputA = 0;
        InputB = 0;
        IPin[0] = new InputPin("0", 1, 2, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("1", 1, 3, 2, 0, 0, 0, 0);
        IPin[2] = new InputPin("2", 1, 4, 2, 0, 0, 0, 0);
        IPin[3] = new InputPin("3", 1, 5, 2, 0, 0, 0, 0);
        IPin[4] = new InputPin("4", 1, 6, 2, 0, 0, 0, 0);
        IPin[5] = new InputPin("5", 1, 7, 2, 0, 0, 0, 0);
        IPin[6] = new InputPin("6", 1, 8, 2, 0, 0, 0, 0);
        IPin[7] = new InputPin("7", 1, 9, 2, 0, 0, 0, 0);
        IPin[8] = new InputPin("0", 1, 11, 2, 0, 0, 0, 0);
        IPin[9] = new InputPin("1", 1, 12, 2, 0, 0, 0, 0);
        IPin[10] = new InputPin("2", 1, 13, 2, 0, 0, 0, 0);
        IPin[11] = new InputPin("3", 1, 14, 2, 0, 0, 0, 0);
        IPin[12] = new InputPin("4", 1, 15, 2, 0, 0, 0, 0);
        IPin[13] = new InputPin("5", 1, 16, 2, 0, 0, 0, 0);
        IPin[14] = new InputPin("6", 1, 17, 2, 0, 0, 0, 0);
        IPin[15] = new InputPin("7", 1, 18, 2, 0, 0, 0, 0);
        IPin[16] = new InputPin("E", 5, 21, 0, -2, 0, 0, 1);
        OPin[0] = new OutputPin("=", 9, 10, -2, 0, 0, 0, 1);
        IPin[16].setLevel(5);
        ComponentName = "Comparateur 8 bits (74HC688)";
        ClassName = "Comparateur8bits";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public Comparateur8bits(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        InputA = 0;
        InputB = 0;
        IPin[16].setLevel(5);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Comparateur8bits comparateur8bits = new Comparateur8bits(this, i, j);
        return comparateur8bits;
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
        if(IPin[4].getLevel() == 5)
            InputA += 16;
        if(IPin[5].getLevel() == 5)
            InputA += 32;
        if(IPin[6].getLevel() == 5)
            InputA += 64;
        if(IPin[7].getLevel() == 5)
            InputA += 128;
        InputB = 0;
        if(IPin[8].getLevel() == 5)
            InputB++;
        if(IPin[9].getLevel() == 5)
            InputB += 2;
        if(IPin[10].getLevel() == 5)
            InputB += 4;
        if(IPin[11].getLevel() == 5)
            InputB += 8;
        if(IPin[12].getLevel() == 5)
            InputB += 16;
        if(IPin[13].getLevel() == 5)
            InputB += 32;
        if(IPin[14].getLevel() == 5)
            InputB += 64;
        if(IPin[15].getLevel() == 5)
            InputB += 128;
        if(InputA == InputB && IPin[16].getLevel() == 5)
            OPin[0].Level = 5;
        else
            OPin[0].Level = 0;
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
