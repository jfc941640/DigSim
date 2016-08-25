package digsim.components.rail;

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

public class RailGestionDirection extends IntegratedCircuit
{

    public RailGestionDirection(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 10, 3, 1, 8, 2, 4, 3);
        IPin[0] = new InputPin("", 4, 0, 0, 1, 0, 0, 0);
        IPin[1] = new InputPin("", 5, 0, 0, 1, 0, 0, 0);
        IPin[2] = new InputPin("", 9, 0, 0, 1, 0, 0, 0);
        IPin[3] = new InputPin("", 10, 0, 0, 1, 0, 0, 0);
        OPin[0] = new OutputPin("a", 6, 4, 0, -1, 0, 0, 0);
        OPin[1] = new OutputPin("b", 7, 4, 0, -1, 0, 0, 0);
        OPin[2] = new OutputPin("s", 8, 4, 0, -1, 0, 0, 0);
        ComponentName = "Module de gestion de direction";
        ClassName = "RailGestionDirection";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public RailGestionDirection(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        RailGestionDirection railgestiondirection = new RailGestionDirection(this, i, j);
        return railgestiondirection;
    }

    public void SimulateLogic()
    {
        if(IPin[0].getLevel() == 5 || IPin[1].getLevel() == 5)
            Voie1 = true;
        else
            Voie1 = false;
        if(IPin[2].getLevel() == 5 || IPin[3].getLevel() == 5)
            Voie0 = true;
        else
            Voie0 = false;
        if(Voie1 && !Voie0 || !Voie1 && Voie0)
        {
            if(IPin[0].getLevel() == 5 || IPin[2].getLevel() == 5)
                OPin[0].Level = 5;
            else
                OPin[0].Level = 0;
            if(IPin[1].getLevel() == 5 || IPin[3].getLevel() == 5)
                OPin[1].Level = 5;
            else
                OPin[1].Level = 0;
            if(Voie1)
                OPin[2].Level = 5;
            else
                OPin[2].Level = 0;
        } else
        {
            OPin[0].Level = 0;
            OPin[1].Level = 0;
        }
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        g.setFill(MyColor.black);
        if(IPin[0].PinDim.height > 0)
        {
            DrawWithOffset.fillText(g,"\u2190", (l + 4) * k, (i1 + 2) * k);
            DrawWithOffset.fillText(g,"\u2192", (l + 9) * k, (i1 + 2) * k);
        } else
        if(IPin[0].PinDim.width < 0)
        {
            DrawWithOffset.fillText(g,"\u2191", (l + 4) * k + 1, (i1 + 3) * k);
            DrawWithOffset.fillText(g,"\u2193", (l + 4) * k + 1, (i1 + 8) * k);
        } else
        if(IPin[0].PinDim.height < 0)
        {
            DrawWithOffset.fillText(g,"\u2190", (l + 4) * k, (i1 + 3) * k);
            DrawWithOffset.fillText(g,"\u2192", (l + 9) * k, (i1 + 3) * k);
        } else
        if(IPin[0].PinDim.width > 0)
        {
            DrawWithOffset.fillText(g,"\u2191", (l + 3) * k + 3, (i1 + 3) * k);
            DrawWithOffset.fillText(g,"\u2193", (l + 3) * k + 3, (i1 + 8) * k);
        }
    }

    boolean Voie1;
    boolean Voie0;
}
