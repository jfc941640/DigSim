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

public class ESSAICOMPOSANT5 extends IntegratedCircuit
{

    public ESSAICOMPOSANT5(Pin apin[][], int i, int j)
    {
        super(i, j, 3, 10, 3, 1, 0, 9, 8, 1);
        InputX = 0;
        HighZ = false;
        IPin[0] = new InputPin("0", 2, 2, 1, 0, 0, 0, 0);
        IPin[1] = new InputPin("", 2, 3, 1, 0, 0, 0, 0);
        IPin[2] = new InputPin("", 2, 4, 1, 0, 0, 0, 0);
        IPin[3] = new InputPin("", 2, 5, 1, 0, 0, 0, 0);
        IPin[4] = new InputPin("", 2, 6, 1, 0, 0, 0, 0);
        IPin[5] = new InputPin("", 2, 7, 1, 0, 0, 0, 0);
        IPin[6] = new InputPin("", 2, 8, 1, 0, 0, 0, 0);
        IPin[7] = new InputPin("7", 2, 9, 1, 0, 0, 0, 0);
        OPin[0] = new OutputPin("", 4, 5, -1, 0, 0, 0, 0);
        ComponentName = "Essai Composant 5";
        ClassName = "ESSAICOMPOSANT5";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public ESSAICOMPOSANT5(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        InputX = 0;
        HighZ = false;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        ESSAICOMPOSANT5 essaicomposant5 = new ESSAICOMPOSANT5(this, i, j);
        return essaicomposant5;
    }

    public void SimulateLogic()
    {
        int i = IPin[0].getLevel();
        int j = IPin[1].getLevel();
        int k = IPin[2].getLevel();
        int l = IPin[3].getLevel();
        int i1 = IPin[4].getLevel();
        int j1 = IPin[5].getLevel();
        int k1 = IPin[6].getLevel();
        int l1 = IPin[7].getLevel();
        if(i == 888 || j == 888 || k == 888 || l == 888 || i1 == 888 || j1 == 888 || k1 == 888 || l1 == 888)
        {
            HighZ = true;
            OPin[0].setLevel(0x6c9d8);
        } else
        {
            HighZ = false;
            InputX = 0;
            if(i == 5)
                InputX++;
            if(j == 5)
                InputX += 2;
            if(k == 5)
                InputX += 4;
            if(l == 5)
                InputX += 8;
            if(i1 == 5)
                InputX += 16;
            if(j1 == 5)
                InputX += 32;
            if(k1 == 5)
                InputX += 64;
            if(l1 == 5)
                InputX += 128;
            OPin[0].setLevel(0x6c660 + InputX);
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
            DrawWithOffset.fillRect(g,(l + 3) * k + 1, (i1 + 5) * k - 3, k, k - 1);
            g.setStroke(MyColor.orange);
            double ai[] = {
                (l + 3) * k + 1, (l + 3) * k + 8, (l + 3) * k + 1
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
            DrawWithOffset.fillRect(g,(l + 8) * k - 3, (i1 + 1) * k + 1, k - 1, k);
            g.setStroke(MyColor.orange);
            double ai1[] = {
                (l + 8) * k - 3, (l + 8) * k + 3, (l + 8) * k
            };
            double ai5[] = {
                (i1 + 1) * k + 1, (i1 + 1) * k + 1, (i1 + 1) * k + 8
            };
            DrawWithOffset.strokePolygon(g,ai1, ai5, ai1.length);
        } else
        if(NbRotation == 2)
        {
            if(HighZ)
                g.setStroke(MyColor.gray);
            else
                g.setStroke(MyColor.darkGray);
            DrawWithOffset.fillRect(g,(l + 2) * k, (i1 + 6) * k - 3, k, k - 1);
            g.setStroke(MyColor.orange);
            double ai2[] = {
                (l + 2) * k + 7, (l + 2) * k, (l + 2) * k + 7
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
            DrawWithOffset.fillRect(g,(l + 7) * k - 3, i1 * k, k - 1, k);
            g.setStroke(MyColor.orange);
            double ai3[] = {
                (l + 7) * k - 3, (l + 7) * k + 3, (l + 7) * k
            };
            double ai7[] = {
                i1 * k + 7, i1 * k + 7, i1 * k
            };
            DrawWithOffset.strokePolygon(g,ai3, ai7, ai3.length);
        }
    }

    int InputX;
    boolean HighZ;
}
