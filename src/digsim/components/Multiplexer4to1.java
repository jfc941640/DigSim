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

public class Multiplexer4to1 extends IntegratedCircuit
{

    public Multiplexer4to1(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 10, 3, 1, 4, 5, 7, 2);
        ActVal = 0;
        Adresse = 0;
        Validation = 0;
        IPin[0] = new InputPin("0", 1, 2, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("1", 1, 3, 2, 0, 0, 0, 0);
        IPin[2] = new InputPin("2", 1, 4, 2, 0, 0, 0, 0);
        IPin[3] = new InputPin("3", 1, 5, 2, 0, 0, 0, 0);
        IPin[4] = new InputPin("a", 5, 8, 0, -2, 0, 0, 0);
        IPin[5] = new InputPin("b", 4, 8, 0, -2, 0, 0, 0);
        IPin[6] = new InputPin("G", 6, 8, 0, -2, 0, 0, 1);
        OPin[0] = new OutputPin("Y", 9, 2, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("Y", 9, 4, -2, 0, 0, 0, 1);
        ComponentName = "Multiplexeur 4 vers 1 (74HC153)";
        ClassName = "Multiplexer4to1";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public Multiplexer4to1(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        ActVal = 0;
        Adresse = 0;
        Validation = 0;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Multiplexer4to1 multiplexer4to1 = new Multiplexer4to1(this, i, j);
        return multiplexer4to1;
    }

    public void SimulateLogic()
    {
        Adresse = 0;
        if(IPin[4].getLevel() == 5)
            Adresse++;
        if(IPin[5].getLevel() == 5)
            Adresse += 2;
        ActVal = 0;
        if(IPin[4].getLevel() == 5)
            ActVal++;
        if(IPin[5].getLevel() == 5)
            ActVal += 2;
        if(IPin[6].getLevel() == 0)
        {
            OPin[0].Level = 0;
            OPin[1].Level = 0;
            Validation = 0;
            return;
        }
        if(IPin[6].getLevel() == 5)
        {
            Validation = 5;
            ActVal = 0;
            if(IPin[4].getLevel() == 5)
                ActVal++;
            if(IPin[5].getLevel() == 5)
                ActVal += 2;
        }
        if(IPin[ActVal].Level == 0)
        {
            OPin[0].Level = 0;
            OPin[1].Level = 0;
        } else
        {
            OPin[0].Level = 5;
            OPin[1].Level = 5;
        }
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        g.setStroke(MyColor.pink);
        if(IPin[0].PinDim.width > 0 && Validation == 5)
            DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 2 + Adresse) * k, (l + 7) * k - 1, (i1 + 2) * k);
        else
        if(IPin[0].PinDim.height > 0 && Validation == 5)
            DrawWithOffset.strokeLine(g,((l + 7) - Adresse) * k, (i1 + 1) * k + 1, (l + 7) * k, (i1 + 5) * k - 1);
        else
        if(IPin[0].PinDim.width < 0 && Validation == 5)
            DrawWithOffset.strokeLine(g,(l + 7) * k - 1, ((i1 + 5) - Adresse) * k, (l + 3) * k + 1, (i1 + 5) * k);
        else
        if(IPin[0].PinDim.height < 0 && Validation == 5)
            DrawWithOffset.strokeLine(g,(l + 4 + Adresse) * k, (i1 + 5) * k - 1, (l + 4) * k, (i1 + 1) * k + 1);
    }

    int ActVal;
    int Adresse;
    int Validation;
}
