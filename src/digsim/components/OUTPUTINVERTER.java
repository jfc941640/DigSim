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

public class OUTPUTINVERTER extends IntegratedCircuit
{

    public OUTPUTINVERTER(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 3, 3, 0, 0, 1, 1);
        ActVal = 0;
        IPin[0] = new InputPin("", 5, 3, -2, 0, 0, 0, 0);
        OPin[0] = new OutputPin("", 6, 3, -3, 0, 0, 0, 1);
        ComponentName = "o-- = complementation (inversion) en sortie de composant";
        ClassName = "OUTPUTINVERTER";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public OUTPUTINVERTER(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        ActVal = 0;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        OUTPUTINVERTER outputinverter = new OUTPUTINVERTER(this, i, j);
        return outputinverter;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        if(IPin[0].PinDim.width > 0)
        {
            g.setFill(Color.rgb(192, 192, 192));
            DrawWithOffset.fillOval(g,(l + 2) * k + 2, (i1 + 2) * k + 5, k - 2, k - 2);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeOval(g,(l + 2) * k + 2, (i1 + 2) * k + 5, k - 2, k - 2);
        } else
        if(IPin[0].PinDim.width < 0)
        {
            g.setFill(Color.rgb(192, 192, 192));
            DrawWithOffset.fillOval(g,(l + 3) * k, (i1 + 2) * k + 5, k - 2, k - 2);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeOval(g,(l + 3) * k, (i1 + 2) * k + 5, k - 2, k - 2);
        } else
        if(IPin[0].PinDim.height > 0)
        {
            g.setFill(Color.rgb(192, 192, 192));
            DrawWithOffset.fillOval(g,(l + 2) * k + 5, (i1 + 2) * k + 2, k - 2, k - 2);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeOval(g,(l + 2) * k + 5, (i1 + 2) * k + 2, k - 2, k - 2);
        } else
        if(IPin[0].PinDim.height < 0)
        {
            g.setFill(Color.rgb(192, 192, 192));
            DrawWithOffset.fillOval(g,(l + 2) * k + 5, (i1 + 3) * k, k - 2, k - 2);
            g.setStroke(MyColor.black);
            DrawWithOffset.strokeOval(g,(l + 2) * k + 5, (i1 + 3) * k, k - 2, k - 2);
        }
    }

    public void SimulateLogic()
    {
        OPin[0].Level = IPin[0].getLevel();
    }

    int ActVal;
}
