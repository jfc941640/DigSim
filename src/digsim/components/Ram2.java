package digsim.components;


import java.io.PrintStream;

import digsim.InputPin;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            Mem, InputPin, OutputPin, MyColor,
//            Pin, ElectronicComponent

public class Ram2 extends Mem
{

    public Ram2(Pin apin[][], int i, int j, String s)
    {
        super(i, j, 14, 13, 3, 1, 12, 9, 17, 8);
        IPin[0] = new InputPin("a", 1, 2, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("b", 1, 3, 2, 0, 0, 0, 0);
        IPin[2] = new InputPin("c", 1, 4, 2, 0, 0, 0, 0);
        IPin[3] = new InputPin("d", 1, 5, 2, 0, 0, 0, 0);
        IPin[4] = new InputPin("e", 1, 6, 2, 0, 0, 0, 0);
        IPin[15] = new InputPin("OE", 1, 9, 2, 0, 0, 0, 1);
        IPin[5] = new InputPin("   WR", 1, 8, 1, 0, 0, 0, 16);
        IPin[16] = new InputPin("", 1, 8, 2, 0, 0, 0, 1);
        IPin[14] = new InputPin("CE", 1, 7, 2, 0, 0, 0, 1);
        IPin[6] = new InputPin("0", 13, 12, 0, -2, 0, 0, 0);
        IPin[7] = new InputPin("1", 12, 12, 0, -2, 0, 0, 0);
        IPin[8] = new InputPin("2", 11, 12, 0, -2, 0, 0, 0);
        IPin[9] = new InputPin("3", 10, 12, 0, -2, 0, 0, 0);
        IPin[10] = new InputPin("4", 9, 12, 0, -2, 0, 0, 0);
        IPin[11] = new InputPin("5", 8, 12, 0, -2, 0, 0, 0);
        IPin[12] = new InputPin("6", 7, 12, 0, -2, 0, 0, 0);
        IPin[13] = new InputPin("7", 6, 12, 0, -2, 0, 0, 0);
        OPin[0] = new OutputPin("0", 13, 12, 0, -2, 0, 0, 0);
        OPin[1] = new OutputPin("1", 12, 12, 0, -2, 0, 0, 0);
        OPin[2] = new OutputPin("2", 11, 12, 0, -2, 0, 0, 0);
        OPin[3] = new OutputPin("3", 10, 12, 0, -2, 0, 0, 0);
        OPin[4] = new OutputPin("4", 9, 12, 0, -2, 0, 0, 0);
        OPin[5] = new OutputPin("5", 8, 12, 0, -2, 0, 0, 0);
        OPin[6] = new OutputPin("6", 7, 12, 0, -2, 0, 0, 0);
        OPin[7] = new OutputPin("7", 6, 12, 0, -2, 0, 0, 0);
        for(int k = 0; k < 8; k++)
            OPin[k].setLevel(0x6c9d8);

        for(int l = 0; l < 17; l++)
            IPin[l].setLevel(-1);

        IPin[5].setLevel(5);
        IPin[14].setLevel(5);
        IPin[15].setLevel(5);
        ComponentName = "RAM2";
        ClassName = "Ram2";
        Can_Rotate = false;
        RegisterPins(apin, i, j);
        UpdateMemory(s);
    }

    public Ram2(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        for(int k = 0; k < 8; k++)
            OPin[k].setLevel(0x6c9d8);

        for(int l = 0; l < 17; l++)
            IPin[l].setLevel(-1);

        IPin[5].setLevel(5);
        IPin[14].setLevel(5);
        IPin[15].setLevel(5);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Ram2 ram2 = new Ram2(this, i, j);
        ram2.Tab = Tab;
        return ram2;
    }

    public void Save(PrintStream printstream)
    {
        String s = new String();
        for(int i = 0; i < 32; i++)
            s = s + Tab[i] + " ";

        printstream.println("describe component Ram2");
        printstream.println(" pos " + Pos.x + " " + Pos.y);
        printstream.println(" rotation " + NbRotation);
        printstream.println(" Mem " + s);
        printstream.println("end describe");
    }

    public String ExportAsScript()
    {
        String s = new String();
        for(int i = 0; i < 32; i++)
            s = s + Tab[i] + " ";

        String s1 = new String("[|Ram2|pos|" + Pos.x + "|" + Pos.y + "|rotation|" + NbRotation + "|Mem|" + s + "|]\r\n^");
        return s1;
    }

    public void SimulateLogic()
    {
        int i = 0;
        int k = 0;
        if(IPin[14].getLevel() == 0)
        {
            for(int l = 0; l < 8; l++)
                OPin[l].setLevel(0x6c9d8);

            return;
        }
        if(IPin[0].getLevel() == 5)
            k++;
        if(IPin[1].getLevel() == 5)
            k += 2;
        if(IPin[2].getLevel() == 5)
            k += 4;
        if(IPin[3].getLevel() == 5)
            k += 8;
        if(IPin[4].getLevel() == 5)
            k += 16;
        if(IPin[5].getOldLevel() == 0 && IPin[5].getLevel() == 5)
        {
            if(IPin[6].getLevel() == 5)
                i++;
            if(IPin[7].getLevel() == 5)
                i += 2;
            if(IPin[8].getLevel() == 5)
                i += 4;
            if(IPin[9].getLevel() == 5)
                i += 8;
            if(IPin[10].getLevel() == 5)
                i += 16;
            if(IPin[11].getLevel() == 5)
                i += 32;
            if(IPin[12].getLevel() == 5)
                i += 64;
            if(IPin[13].getLevel() == 5)
                i += 128;
            Tab[k] = i;
        } else
        if(IPin[15].getOldLevel() == 5)
        {
            int j = Tab[k];
            if((j & 1) == 1)
                OPin[0].setLevel(5);
            else
                OPin[0].setLevel(0);
            if((j & 2) == 2)
                OPin[1].setLevel(5);
            else
                OPin[1].setLevel(0);
            if((j & 4) == 4)
                OPin[2].setLevel(5);
            else
                OPin[2].setLevel(0);
            if((j & 8) == 8)
                OPin[3].setLevel(5);
            else
                OPin[3].setLevel(0);
            if((j & 0x10) == 16)
                OPin[4].setLevel(5);
            else
                OPin[4].setLevel(0);
            if((j & 0x20) == 32)
                OPin[5].setLevel(5);
            else
                OPin[5].setLevel(0);
            if((j & 0x40) == 64)
                OPin[6].setLevel(5);
            else
                OPin[6].setLevel(0);
            if((j & 0x80) == 128)
                OPin[7].setLevel(5);
            else
                OPin[7].setLevel(0);
        }
        IPin[5].OldLevel = IPin[5].getLevel();
        if(IPin[15].getLevel() == 0 || IPin[14].getLevel() == 0)
        {
            for(int i1 = 0; i1 < 8; i1++)
                OPin[i1].setLevel(0x6c9d8);

            return;
        } else
        {
            return;
        }
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        super.draw(g, i, j, k);
        g.setFill(MyColor.blue);
        DrawWithOffset.fillText(g,"RAM 32*8", ((l + HitBoxSize.width) - 5) * k, ((i1 + HitBoxSize.height) - 3) * k);
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }
}
