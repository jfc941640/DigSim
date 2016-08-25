package digsim.components;

import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, MyColor,
//            Pin, ElectronicComponent

public class SequencerCell extends IntegratedCircuit
{

    public SequencerCell(Pin apin[][], int i, int j)
    {
        super(i, j, 12, 15, 3, 3, 5, 7, 11, 2);
        Aff = 0;
        IPin[0] = new InputPin("S", 1, 5, 2, 0, 0, 0, 8);
        IPin[1] = new InputPin("S", 1, 6, 2, 0, 0, 0, 8);
        IPin[2] = new InputPin("S", 1, 7, 2, 0, 0, 0, 8);
        IPin[3] = new InputPin("S", 1, 8, 2, 0, 0, 0, 64);
        IPin[10] = new InputPin("S", 1, 9, 2, 0, 0, 0, 64);
        IPin[4] = new InputPin("r", 4, 12, 0, -2, 0, 0, 1);
        IPin[5] = new InputPin("r", 5, 12, 0, -2, 0, 0, 1);
        IPin[6] = new InputPin("r", 6, 12, 0, -2, 0, 0, 1);
        IPin[7] = new InputPin("r", 7, 12, 0, -2, 0, 0, 1);
        IPin[8] = new InputPin("s", 6, -1, 0, 2, 0, 0, 1);
        IPin[9] = new InputPin("", 1, 4, 2, 0, 0, 0, 4);
        OPin[0] = new OutputPin("Q", 10, 5, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("Q", 10, 8, -2, 0, 0, 0, 1);
        OPin[0].setLevel(5 * Math.round((int)(Math.random() * 2D)));
        for(int k = 0; k < 11; k++)
            IPin[k].setLevel(-1);

        ComponentName = "Cellule pour sequenceur cable";
        ClassName = "SequencerCell";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public SequencerCell(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Aff = 0;
        OPin[0].setLevel(5 * Math.round((int)(Math.random() * 2D)));
        for(int k = 0; k < 11; k++)
            IPin[k].setLevel(-1);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        SequencerCell sequencercell = new SequencerCell(this, i, j);
        return sequencercell;
    }

    public void SimulateLogic()
    {
        Aff = 1;
        if(IPin[8].getLevel() == 5)
        {
            s = 5;
            OUT = 5;
        } else
        {
            s = 0;
        }
        if(IPin[4].getLevel() == 5 || IPin[5].getLevel() == 5 || IPin[6].getLevel() == 5 || IPin[7].getLevel() == 5)
        {
            r = 5;
            OUT = 0;
        } else
        {
            r = 0;
        }
        if(IPin[9].OldLevel == 0 && IPin[9].getLevel() == 5 && s == 0 && r == 0)
            if(IPin[0].getLevel() == 5 && IPin[1].getLevel() == 5 && IPin[2].getLevel() == 5 && IPin[3].getLevel() == 5 && IPin[10].getLevel() == 5)
                OUT = 5;
            else
                OUT = OPin[0].getLevel();
        if(s == 5 && r == 5)
        {
            OPin[0].setLevel(5);
            OPin[1].setLevel(0);
        } else
        {
            OPin[0].setLevel(OUT);
            OPin[1].setLevel(OUT);
        }
        IPin[9].OldLevel = IPin[9].getLevel();
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        if(OPin[0].getLevel() == 5 && Aff == 1)
            g.setFill(MyColor.red);
        else
            g.setFill(MyColor.white);
        if(s == 5 && r == 5 && Aff == 1)
            g.setFill(MyColor.pink);
        DrawWithOffset.fillOval(g,(l + 5) * k, (i1 + 5) * k, 1 * k, 1 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,(l + 5) * k, (i1 + 5) * k, 1 * k, 1 * k);
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    int r;
    int s;
    int OUT;
    int Aff;
}
