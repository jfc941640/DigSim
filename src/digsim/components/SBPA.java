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

public class SBPA extends IntegratedCircuit
{

    public SBPA(Pin apin[][], int i, int j)
    {
        super(i, j, 9, 6, 1, 1, 5, 4, 2, 1);
        NbBits = 6;
        Timing = "";
        ComponentName = "SBPA";
        ClassName = "SBPA";
        Can_Rotate = false;
        IPin[0] = new InputPin("", 1, 3, 0, 0, 0, 0, 0);
        IPin[1] = new InputPin("", 0, 3, 1, 0, 0, 0, 0);
        OPin[0] = new OutputPin("", 7, 3, -1, 0, 0, 0, 0);
        RegisterPins(apin, i, j);
        REG = new int[10];
        for(int k = 0; k < NbBits; k++)
            REG[k] = 5 * Math.round((int)(Math.random() * 2D));

        Somme = 0;
        int l;
        for(l = 0; l < NbBits; l++)
            Somme = Somme + REG[l];

        if(Somme == 0)
        {
            REG[l] = 5 * Math.round((int)(Math.random() * 2D));
            REG[0] = 5;
        }
        OPin[0].setLevel(5 * Math.round((int)(Math.random() * 2D)));
        IPin[0].Level = 0;
    }

    public SBPA(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        NbBits = 6;
        Timing = "";
        REG = new int[10];
        for(int k = 0; k < NbBits; k++)
            REG[k] = 5 * Math.round((int)(Math.random() * 2D));

        Somme = 0;
        int l;
        for(l = 0; l < NbBits; l++)
            Somme = Somme + REG[l];

        if(Somme == 0)
        {
            REG[l] = 5 * Math.round((int)(Math.random() * 2D));
            REG[0] = 5;
        }
        OPin[0].setLevel(5 * Math.round((int)(Math.random() * 2D)));
        IPin[0].Level = 0;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        SBPA sbpa = new SBPA(this, i, j);
        return sbpa;
    }

    public void InitBeforeSimulate()
    {
        IPin[0].InitBeforeSimulate();
        if(IPin[0].getLevel() == 0)
            IPin[0].Level = 5;
        else
            IPin[0].Level = 0;
    }

    public void SimulateLogic()
    {
        if((IPin[1].getLevel() == 0) & (IPin[1].getOldLevel() == 5))
        {
            NbBits = 6 + Math.round((int)(Math.random() * 2D));
            for(int i = 0; i < NbBits; i++)
                REG[i] = 5 * Math.round((int)(Math.random() * 2D));

            Somme = 0;
            int j;
            for(j = 0; j < NbBits; j++)
                Somme = Somme + REG[j];

            if(Somme == 0)
            {
                REG[j] = 5 * Math.round((int)(Math.random() * 2D));
                REG[0] = 5;
            }
            OPin[0].setLevel(5 * Math.round((int)(Math.random() * 2D)));
        }
        if(NbBits == 7)
            Timing = "";
        else
            Timing = " ";
        for(int k = 0; k < NbBits; k++)
            if(REG[k] == 5)
                Timing = Timing + "1";
            else
                Timing = Timing + "0";

        if((IPin[1].getLevel() == 5) & (IPin[0].getOldLevel() == 0) && IPin[0].getLevel() == 5)
        {
            if(REG[NbBits - 1] == REG[NbBits - 2])
                INPUT = 0;
            else
                INPUT = 5;
            for(int l = NbBits - 1; l > 0; l--)
                REG[l] = REG[l - 1];

            REG[0] = INPUT;
            OPin[0].setLevel(REG[NbBits - 1]);
        }
        IPin[0].OldLevel = IPin[0].getLevel();
        IPin[1].OldLevel = IPin[1].getLevel();
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawOutputPins(g, l, i1, k);
        g.setFill(MyColor.blue);
        DrawWithOffset.fillText(g,"SBPA " + Integer.toString(NbBits), (l + 2) * k - 5, (i1 + 3) * k - 1);
        DrawWithOffset.fillText(g,Timing, (l + 1) * k + 3, (i1 + 4) * k + 1);
    }

    int REG[];
    int NbBits;
    int Longueur;
    int Somme;
    int INPUT;
    String Timing;
}
