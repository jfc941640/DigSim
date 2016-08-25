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

public class ThreeToEightLineDecoder extends IntegratedCircuit
{

    public ThreeToEightLineDecoder(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 11, 3, 1, 4, 9, 6, 8);
        Adresse = 0;
        Validation = 0;
        ValidDec = 0;
        Aff = 0;
        IPin[0] = new InputPin("a", 1, 2, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("b", 1, 3, 2, 0, 0, 0, 0);
        IPin[2] = new InputPin("c", 1, 4, 2, 0, 0, 0, 0);
        IPin[3] = new InputPin("G", 1, 7, 2, 0, 0, 0, 1);
        IPin[4] = new InputPin("G", 1, 8, 2, 0, 0, 0, 1);
        IPin[5] = new InputPin("G", 1, 9, 2, 0, 0, 0, 0);
        OPin[0] = new OutputPin("0", 9, 2, -2, 0, 0, 0, 1);
        OPin[1] = new OutputPin("1", 9, 3, -2, 0, 0, 0, 1);
        OPin[2] = new OutputPin("2", 9, 4, -2, 0, 0, 0, 1);
        OPin[3] = new OutputPin("3", 9, 5, -2, 0, 0, 0, 1);
        OPin[4] = new OutputPin("4", 9, 6, -2, 0, 0, 0, 1);
        OPin[5] = new OutputPin("5", 9, 7, -2, 0, 0, 0, 1);
        OPin[6] = new OutputPin("6", 9, 8, -2, 0, 0, 0, 1);
        OPin[7] = new OutputPin("7", 9, 9, -2, 0, 0, 0, 1);
        ComponentName = "Decodeur-demultiplexeur 3 vers 8 (74HC138)";
        ClassName = "ThreeToEightLineDecoder";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public ThreeToEightLineDecoder(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Adresse = 0;
        Validation = 0;
        ValidDec = 0;
        Aff = 0;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        ThreeToEightLineDecoder threetoeightlinedecoder = new ThreeToEightLineDecoder(this, i, j);
        return threetoeightlinedecoder;
    }

    public void SimulateLogic()
    {
        Aff = 1;
        boolean flag = false;
        boolean flag1 = false;
        if(IPin[3].getLevel() == 5 && IPin[4].getLevel() == 5 || IPin[3].getLevel() == 5 && IPin[5].getLevel() == 5 || IPin[4].getLevel() == 5 && IPin[5].getLevel() == 5)
            ValidDec = 5;
        else
            ValidDec = 0;
        Adresse = 0;
        if(IPin[0].getLevel() == 5)
            Adresse++;
        if(IPin[1].getLevel() == 5)
            Adresse += 2;
        if(IPin[2].getLevel() == 5)
            Adresse += 4;
        if(IPin[3].getLevel() == 0 || IPin[4].getLevel() == 0 || IPin[5].getLevel() == 0)
        {
            Validation = 0;
            for(int i = 0; i < 8; i++)
                OPin[i].Level = 0;

        } else
        {
            Validation = 5;
            for(int j = 0; j < 8; j++)
                if(j == Adresse)
                    OPin[j].Level = 5;
                else
                    OPin[j].Level = 0;

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
                g.setStroke(MyColor.red);
            else
                g.setStroke(MyColor.blue);
            if(IPin[0].PinDim.width > 0)
            {
                DrawWithOffset.fillText(g,"" + Integer.toString(Adresse), (l + 5) * k - 3, (i1 + 4) * k - 5);
                if(ValidDec == 5)
                {
                    g.setStroke(MyColor.pink);
                    DrawWithOffset.strokeLine(g,(l + 7) * k - 1, (i1 + 2 + Adresse) * k, (l + 4) * k, (i1 + 8) * k);
                }
            } else
            if(IPin[0].PinDim.height > 0)
            {
                DrawWithOffset.fillText(g,"" + Integer.toString(Adresse), (l + 10) * k, (i1 + 4) * k - 4);
                if(ValidDec == 5)
                {
                    g.setStroke(MyColor.pink);
                    DrawWithOffset.strokeLine(g,((l + 11) - Adresse) * k, (i1 + 5) * k - 1, (l + 5) * k, (i1 + 2) * k);
                }
            } else
            if(IPin[0].PinDim.width < 0)
            {
                DrawWithOffset.fillText(g,"" + Integer.toString(Adresse), (l + 5) * k - 1, (i1 + 9) * k - 5);
                if(ValidDec == 5)
                {
                    g.setStroke(MyColor.pink);
                    DrawWithOffset.strokeLine(g,(l + 3) * k + 1, ((i1 + 9) - Adresse) * k, (l + 6) * k, (i1 + 3) * k);
                }
            } else
            if(IPin[0].PinDim.height < 0)
            {
                DrawWithOffset.fillText(g,"" + Integer.toString(Adresse), (l + 5) * k - 2, (i1 + 4) * k - 4);
                if(ValidDec == 5)
                {
                    g.setStroke(MyColor.pink);
                    DrawWithOffset.strokeLine(g,(l + 4 + Adresse) * k, (i1 + 1) * k + 1, (l + 10) * k, (i1 + 4) * k);
                }
            }
        }
    }

    int Adresse;
    int Validation;
    int ValidDec;
    int Aff;
}
