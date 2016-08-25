package digsim.components;

import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, MyColor,
//            Pin, ElectronicComponent

public class FourBitBinaryCounter extends IntegratedCircuit
{

    public FourBitBinaryCounter(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 7, 3, 1, 4, 5, 1, 4);
        Aff = 0;
        Init = Math.round((int)(Math.random() * 16D));
        Count = Init;
        IPin[0] = new InputPin("", 1, 3, 2, 0, 0, 0, 4);
        OPin[0] = new OutputPin("A", 9, 2, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("B", 9, 3, -2, 0, 0, 0, 0);
        OPin[2] = new OutputPin("C", 9, 4, -2, 0, 0, 0, 0);
        OPin[3] = new OutputPin("D", 9, 5, -2, 0, 0, 0, 0);
        IPin[0].setLevel(0);
        ComponentName = "Compteur binaire 4 bits";
        ClassName = "FourBitBinaryCounter";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public FourBitBinaryCounter(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Aff = 0;
        Init = Math.round((int)(Math.random() * 16D));
        Count = Init;
        IPin[0].setLevel(0);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        FourBitBinaryCounter fourbitbinarycounter = new FourBitBinaryCounter(this, i, j);
        return fourbitbinarycounter;
    }

    public void SimulateLogic()
    {
        Aff = 1;
        if(IPin[0].getOldLevel() == 0 && IPin[0].getLevel() == 5)
        {
            Count++;
            if(Count < 0 || Count > 15)
                Count = 0;
        }
        Qa = Qb = Qc = Qd = 0;
        if((Count & 1) == 1)
            Qa = 5;
        if((Count & 2) == 2)
            Qb = 5;
        if((Count & 4) == 4)
            Qc = 5;
        if((Count & 8) == 8)
            Qd = 5;
        OPin[0].setLevel(Qa);
        OPin[1].setLevel(Qb);
        OPin[2].setLevel(Qc);
        OPin[3].setLevel(Qd);
        IPin[0].OldLevel = IPin[0].getLevel();
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        if(Aff == 1)
            g.setFill(MyColor.red);
        else
            g.setFill(Color.rgb(240, 240, 240));
        if(NbRotation == 0)
            DrawWithOffset.fillText(g,"" + Integer.toString(Count), (l + 3) * k + 2, (i1 + 6) * k - 1);
        else
        if(NbRotation == 1)
            DrawWithOffset.fillText(g,"" + Integer.toString(Count), (l + 6) * k + 4, (i1 + 3) * k + 1);
        else
        if(NbRotation == 2)
            DrawWithOffset.fillText(g,"" + Integer.toString(Count), (l + 5) * k + 5, (i1 + 2) * k + 1);
        else
        if(NbRotation == 3)
            DrawWithOffset.fillText(g,"" + Integer.toString(Count), (l + 3) * k + 2, (i1 + 4) * k + 1);
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    int Aff;
    int Init;
    int Count;
    int Qa;
    int Qb;
    int Qc;
    int Qd;
}
