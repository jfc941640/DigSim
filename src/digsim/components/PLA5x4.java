package digsim.components;

import java.io.PrintStream;

import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, MyColor,
//            Pin, ElectronicComponent

public class PLA5x4 extends IntegratedCircuit
{

    public PLA5x4(Pin apin[][], int i, int j, String s, String s1, String s2)
    {
        super(i, j, 5, 8, 3, 1, 3, 6, 6, 4);
        IPin[0] = new InputPin("a", 2, 2, 1, 0, 0, 0, 0);
        IPin[1] = new InputPin("b", 2, 3, 1, 0, 0, 0, 0);
        IPin[2] = new InputPin("c", 2, 4, 1, 0, 0, 0, 0);
        IPin[3] = new InputPin("d", 2, 5, 1, 0, 0, 0, 0);
        IPin[4] = new InputPin("e", 2, 6, 1, 0, 0, 0, 0);
        IPin[5] = new InputPin("CE", 7, 6, -1, 0, 0, 0, 1);
        OPin[0] = new OutputPin("0", 7, 2, -1, 0, 0, 0, 0);
        OPin[1] = new OutputPin("1", 7, 3, -1, 0, 0, 0, 0);
        OPin[2] = new OutputPin("2", 7, 4, -1, 0, 0, 0, 0);
        OPin[3] = new OutputPin("3", 7, 5, -1, 0, 0, 0, 0);
        for(int k = 0; k < 4; k++)
            OPin[k].setLevel(0x6c9d8);

        IPin[5].setLevel(5);
        ComponentName = "PLA 5x4";
        ClassName = "PLA5x4";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
        ET = s;
        OU = s1;
        NON = s2;
    }

    public PLA5x4(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        for(int k = 0; k < 4; k++)
            OPin[k].setLevel(0x6c9d8);

        IPin[5].setLevel(5);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        PLA5x4 pla5x4 = new PLA5x4(this, i, j);
        pla5x4.ET = ET;
        pla5x4.OU = OU;
        pla5x4.NON = NON;
        return pla5x4;
    }

    public String getETpla5x4()
    {
        return ET;
    }

    public void setETpla5x4(String s)
    {
        ET = s;
    }

    public String getOUpla5x4()
    {
        return OU;
    }

    public void setOUpla5x4(String s)
    {
        OU = s;
    }

    public String getNONpla5x4()
    {
        return NON;
    }

    public void setNONpla5x4(String s)
    {
        NON = s;
    }

    public void SimulateLogic()
    {
        boolean flag;
        if(IPin[0].getLevel() == 5)
            flag = true;
        else
            flag = false;
        boolean flag1;
        if(IPin[1].getLevel() == 5)
            flag1 = true;
        else
            flag1 = false;
        boolean flag2;
        if(IPin[2].getLevel() == 5)
            flag2 = true;
        else
            flag2 = false;
        boolean flag3;
        if(IPin[3].getLevel() == 5)
            flag3 = true;
        else
            flag3 = false;
        boolean flag4;
        if(IPin[4].getLevel() == 5)
            flag4 = true;
        else
            flag4 = false;
        boolean flag5 = flag;
        boolean flag6 = flag1;
        boolean flag7 = flag2;
        boolean flag8 = flag3;
        boolean flag9 = flag4;
        if(IPin[5].getLevel() == 5)
        {
            if(flag5)
                OPin[0].setLevel(5);
            else
                OPin[0].setLevel(0);
            if(flag6)
                OPin[1].setLevel(5);
            else
                OPin[1].setLevel(0);
            if(flag7)
                OPin[2].setLevel(5);
            else
                OPin[2].setLevel(0);
            if(flag8)
                OPin[3].setLevel(5);
            else
                OPin[3].setLevel(0);
            if(flag9)
            {
                OPin[0].setLevel(5);
                OPin[1].setLevel(5);
                OPin[2].setLevel(5);
                OPin[3].setLevel(5);
            }
        } else
        {
            OPin[0].setLevel(0x6c9d8);
            OPin[1].setLevel(0x6c9d8);
            OPin[2].setLevel(0x6c9d8);
            OPin[3].setLevel(0x6c9d8);
        }
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFont(Font.font("Serif", FontWeight.BOLD, 10));
        g.setFill(MyColor.blue);
        if(NbRotation == 0)
        {
            DrawWithOffset.fillText(g,"P", (l + 4) * k + 1, (i1 + 3) * k + 2);
            DrawWithOffset.fillText(g,"L", (l + 4) * k + 1, (i1 + 4) * k + 2);
            DrawWithOffset.fillText(g,"A", (l + 4) * k + 1, (i1 + 5) * k + 2);
        } else
        if(NbRotation == 1)
            DrawWithOffset.fillText(g,"PLA", (l + 5) * k, (i1 + 3) * k + 0);
        else
        if(NbRotation == 2)
        {
            DrawWithOffset.fillText(g,"P", (l + 4) * k + 1, (i1 + 3) * k + 6);
            DrawWithOffset.fillText(g,"L", (l + 4) * k + 1, (i1 + 4) * k + 6);
            DrawWithOffset.fillText(g,"A", (l + 4) * k + 1, (i1 + 5) * k + 6);
        } else
        {
            DrawWithOffset.fillText(g,"PLA", (l + 4) * k + 4, (i1 + 3) * k + 0);
        }
        g.setFont(Font.font("Serif", 10));
        g.setStroke(MyColor.red);
    }

    public void Save(PrintStream printstream)
    {
        printstream.println("describe component PLA5x4");
        printstream.println(" pos " + Pos.x + " " + Pos.y);
        printstream.println(" rotation " + NbRotation);
        printstream.println(" xpla " + ET);
        printstream.println(" ypla " + OU);
        printstream.println(" zpla " + NON);
        printstream.println("end describe");
    }

    public String ExportAsScript()
    {
        String s = new String("[|PLA5x4|pos|" + Pos.x + "|" + Pos.y + "|rotation|" + NbRotation + "|xpla|" + ET + "|ypla|" + OU + "|zpla|" + NON + "|]\r\n^");
        return s;
    }

    String ET;
    String OU;
    String NON;
    int R;
    int V;
    int B;
}
