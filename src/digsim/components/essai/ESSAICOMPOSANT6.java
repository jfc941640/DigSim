package digsim.components.essai;

import digsim.DrawWithOffset;
import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, MyColor,
//            Pin, ElectronicComponent

public class ESSAICOMPOSANT6 extends IntegratedCircuit
{

    public ESSAICOMPOSANT6(Pin apin[][], int i, int j)
    {
        super(i, j, 3, 10, 3, 1, 0, 9, 1, 8);
        InputX = 0;
        HighZ = false;
        IPin[0] = new InputPin("", 2, 5, 1, 0, 0, 0, 0);
        OPin[0] = new OutputPin("0", 4, 2, -1, 0, 0, 0, 0);
        OPin[1] = new OutputPin("", 4, 3, -1, 0, 0, 0, 0);
        OPin[2] = new OutputPin("", 4, 4, -1, 0, 0, 0, 0);
        OPin[3] = new OutputPin("", 4, 5, -1, 0, 0, 0, 0);
        OPin[4] = new OutputPin("", 4, 6, -1, 0, 0, 0, 0);
        OPin[5] = new OutputPin("", 4, 7, -1, 0, 0, 0, 0);
        OPin[6] = new OutputPin("", 4, 8, -1, 0, 0, 0, 0);
        OPin[7] = new OutputPin("7", 4, 9, -1, 0, 0, 0, 0);
        ComponentName = "Essai Composant 6";
        ClassName = "ESSAICOMPOSANT6";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public ESSAICOMPOSANT6(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        InputX = 0;
        HighZ = false;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        ESSAICOMPOSANT6 essaicomposant6 = new ESSAICOMPOSANT6(this, i, j);
        return essaicomposant6;
    }

    public void SimulateLogic()
    {
        if(IPin[0].getLevel() == 0x6c9d8)
        {
            OPin[0].setLevel(888);
            OPin[1].setLevel(888);
            OPin[2].setLevel(888);
            OPin[3].setLevel(888);
            OPin[4].setLevel(888);
            OPin[5].setLevel(888);
            OPin[6].setLevel(888);
            OPin[7].setLevel(888);
        } else
        {
            InputX = IPin[0].getLevel() - 0x6c660;
            int i = InputX % 2;
            InputX = InputX / 2;
            int j = InputX % 2;
            InputX = InputX / 2;
            int k = InputX % 2;
            InputX = InputX / 2;
            int l = InputX % 2;
            InputX = InputX / 2;
            int i1 = InputX % 2;
            InputX = InputX / 2;
            int j1 = InputX % 2;
            InputX = InputX / 2;
            int k1 = InputX % 2;
            InputX = InputX / 2;
            int l1 = InputX % 2;
            InputX = InputX / 2;
            OPin[0].setLevel(5 * i);
            OPin[1].setLevel(5 * j);
            OPin[2].setLevel(5 * k);
            OPin[3].setLevel(5 * l);
            OPin[4].setLevel(5 * i1);
            OPin[5].setLevel(5 * j1);
            OPin[6].setLevel(5 * k1);
            OPin[7].setLevel(5 * l1);
        }
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        if(NbRotation == 0)
        {
            if(HighZ)
                g.setStroke(MyColor.gray);
            else
                g.setStroke(MyColor.darkGray);
            DrawWithOffset.fillRect(g,(l + 2) * k, (i1 + 5) * k - 3, k, k - 1);
            g.setStroke(MyColor.orange);
            double ai[] = {
                (l + 2) * k + 1, (l + 2) * k + 8, (l + 2) * k + 1
            };
            double ai4[] = {
                (i1 + 5) * k - 3, (i1 + 5) * k, (i1 + 5) * k + 3
            };
            DrawWithOffset.strokePolygon(g,ai, ai4, ai.length);
        } else
        if(NbRotation == 1)
        {
            if(HighZ)
                g.setStroke(MyColor.gray);
            else
                g.setStroke(MyColor.darkGray);
            DrawWithOffset.fillRect(g,(l + 8) * k - 3, i1 * k, k - 1, k);
            g.setStroke(MyColor.orange);
            double ai1[] = {
                (l + 8) * k - 3, (l + 8) * k + 3, (l + 8) * k
            };
            double ai5[] = {
                i1 * k + 1, i1 * k + 1, i1 * k + 8
            };
            DrawWithOffset.strokePolygon(g,ai1, ai5, ai1.length);
        } else
        if(NbRotation == 2)
        {
            if(HighZ)
                g.setStroke(MyColor.gray);
            else
                g.setStroke(MyColor.darkGray);
            DrawWithOffset.fillRect(g,(l + 3) * k + 1, (i1 + 6) * k - 3, k, k - 1);
            g.setStroke(MyColor.orange);
            double ai2[] = {
                (l + 3) * k + 7, (l + 3) * k, (l + 3) * k + 7
            };
            double ai6[] = {
                (i1 + 6) * k - 3, (i1 + 6) * k, (i1 + 6) * k + 3
            };
            DrawWithOffset.strokePolygon(g,ai2, ai6, ai2.length);
        } else
        {
            if(HighZ)
                g.setStroke(MyColor.gray);
            else
                g.setStroke(MyColor.darkGray);
            DrawWithOffset.fillRect(g,(l + 7) * k - 3, (i1 + 1) * k + 1, k - 1, k);
            g.setStroke(MyColor.orange);
            double ai3[] = {
                (l + 7) * k - 3, (l + 7) * k + 3, (l + 7) * k
            };
            double ai7[] = {
                (i1 + 1) * k + 7, (i1 + 1) * k + 7, (i1 + 1) * k
            };
            DrawWithOffset.strokePolygon(g,ai3, ai7, ai3.length);
        }
    }

    int InputX;
    boolean HighZ;
}
