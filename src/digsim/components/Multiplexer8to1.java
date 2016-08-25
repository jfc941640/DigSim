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

public class Multiplexer8to1 extends IntegratedCircuit
{

    public Multiplexer8to1(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 17, 3, 1, 4, 15, 12, 2);
        Aff = 0;
        ActVal = 0;
        Adresse = 0;
        Validation = 0;
        IPin[0] = new InputPin("0", 1, 2, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("1", 1, 3, 2, 0, 0, 0, 0);
        IPin[2] = new InputPin("2", 1, 4, 2, 0, 0, 0, 0);
        IPin[3] = new InputPin("3", 1, 5, 2, 0, 0, 0, 0);
        IPin[4] = new InputPin("4", 1, 6, 2, 0, 0, 0, 0);
        IPin[5] = new InputPin("5", 1, 7, 2, 0, 0, 0, 0);
        IPin[6] = new InputPin("6", 1, 8, 2, 0, 0, 0, 0);
        IPin[7] = new InputPin("7", 1, 9, 2, 0, 0, 0, 0);
        IPin[8] = new InputPin("a", 1, 13, 2, 0, 0, 0, 0);
        IPin[9] = new InputPin("b", 1, 14, 2, 0, 0, 0, 0);
        IPin[10] = new InputPin("c", 1, 15, 2, 0, 0, 0, 0);
        IPin[11] = new InputPin("G", 1, 11, 2, 0, 0, 0, 1);
        OPin[0] = new OutputPin("Y", 9, 13, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("Y", 9, 15, -2, 0, 0, 0, 1);
        ComponentName = "Multiplexeur 8 vers 1 (74HC151)";
        ClassName = "Multiplexer8to1";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public Multiplexer8to1(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Aff = 0;
        ActVal = 0;
        Adresse = 0;
        Validation = 0;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Multiplexer8to1 multiplexer8to1 = new Multiplexer8to1(this, i, j);
        return multiplexer8to1;
    }

    public void SimulateLogic()
    {
        Aff = 1;
        Adresse = 0;
        if(IPin[8].getLevel() == 5)
            Adresse++;
        if(IPin[9].getLevel() == 5)
            Adresse += 2;
        if(IPin[10].getLevel() == 5)
            Adresse += 4;
        if(IPin[11].getLevel() == 0)
        {
            OPin[0].Level = 0;
            OPin[1].Level = 0;
            Validation = 0;
            return;
        }
        if(IPin[11].getLevel() == 5)
        {
            Validation = 5;
            ActVal = 0;
            if(IPin[8].getLevel() == 5)
                ActVal++;
            if(IPin[9].getLevel() == 5)
                ActVal += 2;
            if(IPin[10].getLevel() == 5)
                ActVal += 4;
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
        if(Aff == 1)
        {
            if(Validation == 5)
                g.setFill(MyColor.red);
            else
                g.setFill(MyColor.blue);
            if(IPin[0].PinDim.width > 0)
            {
                DrawWithOffset.fillText(g,"" + Integer.toString(Adresse), (l + 5) * k - 3, (i1 + 15) * k - 5);
                if(Validation == 5)
                {
                    g.setStroke(MyColor.pink);
                    DrawWithOffset.strokeLine(g,(l + 3) * k + 1, (i1 + 2 + Adresse) * k, (l + 7) * k - 1, (i1 + 13) * k);
                }
            } else
            if(IPin[0].PinDim.height > 0)
            {
                DrawWithOffset.fillText(g,"" + Integer.toString(Adresse), (l + 5) * k - 2, (i1 + 4) * k - 3);
                if(Validation == 5)
                {
                    g.setStroke(MyColor.pink);
                    DrawWithOffset.strokeLine(g,((l + 17) - Adresse) * k, (i1 + 1) * k + 1, (l + 6) * k, (i1 + 5) * k - 1);
                }
            } else
            if(IPin[0].PinDim.width < 0)
            {
                DrawWithOffset.fillText(g,"" + Integer.toString(Adresse), (l + 5) * k - 1, (i1 + 4) * k - 5);
                if(Validation == 5)
                {
                    g.setStroke(MyColor.pink);
                    DrawWithOffset.strokeLine(g,(l + 7) * k - 1, ((i1 + 15) - Adresse) * k, (l + 3) * k + 1, (i1 + 4) * k);
                }
            } else
            if(IPin[0].PinDim.height < 0)
            {
                DrawWithOffset.fillText(g,"" + Integer.toString(Adresse), (l + 16) * k - 2, (i1 + 4) * k - 5);
                if(Validation == 5)
                {
                    g.setStroke(MyColor.pink);
                    DrawWithOffset.strokeLine(g,(l + 4 + Adresse) * k, (i1 + 5) * k - 1, (l + 15) * k, (i1 + 1) * k + 1);
                }
            }
        }
    }

    int Aff;
    int ActVal;
    int Adresse;
    int Validation;
}
