package digsim.components;

import java.awt.Point;

import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.OutputPin;
import digsim.Pin;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, Pin,
//            ElectronicComponent

public class Encodeur10vers4 extends IntegratedCircuit
{

    public Encodeur10vers4(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 12, 3, 1, 4, 10, 9, 4);
        Output = 0;
        IPin[0] = new InputPin("1", 1, 2, 2, 0, 0, 0, 1);
        IPin[1] = new InputPin("2", 1, 3, 2, 0, 0, 0, 1);
        IPin[2] = new InputPin("3", 1, 4, 2, 0, 0, 0, 1);
        IPin[3] = new InputPin("4", 1, 5, 2, 0, 0, 0, 1);
        IPin[4] = new InputPin("5", 1, 6, 2, 0, 0, 0, 1);
        IPin[5] = new InputPin("6", 1, 7, 2, 0, 0, 0, 1);
        IPin[6] = new InputPin("7", 1, 8, 2, 0, 0, 0, 1);
        IPin[7] = new InputPin("8", 1, 9, 2, 0, 0, 0, 1);
        IPin[8] = new InputPin("9", 1, 10, 2, 0, 0, 0, 1);
        OPin[0] = new OutputPin("A", 9, 4, -2, 0, 0, 0, 1);
        OPin[1] = new OutputPin("B", 9, 5, -2, 0, 0, 0, 1);
        OPin[2] = new OutputPin("C", 9, 6, -2, 0, 0, 0, 1);
        OPin[3] = new OutputPin("D", 9, 7, -2, 0, 0, 0, 1);
        ComponentName = "Encodeur prioritaire 10 vers 4 (74HC147)";
        ClassName = "Encodeur10vers4";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public Encodeur10vers4(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Output = 0;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Encodeur10vers4 encodeur10vers4 = new Encodeur10vers4(this, i, j);
        return encodeur10vers4;
    }

    public void SimulateLogic()
    {
        if(IPin[8].getLevel() == 5)
            Output = 9;
        else
        if(IPin[7].getLevel() == 5)
            Output = 8;
        else
        if(IPin[6].getLevel() == 5)
            Output = 7;
        else
        if(IPin[5].getLevel() == 5)
            Output = 6;
        else
        if(IPin[4].getLevel() == 5)
            Output = 5;
        else
        if(IPin[3].getLevel() == 5)
            Output = 4;
        else
        if(IPin[2].getLevel() == 5)
            Output = 3;
        else
        if(IPin[1].getLevel() == 5)
            Output = 2;
        else
        if(IPin[0].getLevel() == 5)
            Output = 1;
        else
            Output = 0;
        if((Output & 1) == 1)
            OPin[0].setLevel(5);
        else
            OPin[0].setLevel(0);
        if((Output & 2) == 2)
            OPin[1].setLevel(5);
        else
            OPin[1].setLevel(0);
        if((Output & 4) == 4)
            OPin[2].setLevel(5);
        else
            OPin[2].setLevel(0);
        if((Output & 8) == 8)
            OPin[3].setLevel(5);
        else
            OPin[3].setLevel(0);
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
    }

    int Output;
}
