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

public class Rom extends Mem
{

    public Rom(Pin apin[][], int i, int j, String s)
    {
        super(i, j, 14, 11, 3, 1, 12, 9, 7, 8);
        IPin[0] = new InputPin("a", 1, 2, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("b", 1, 3, 2, 0, 0, 0, 0);
        IPin[2] = new InputPin("c", 1, 4, 2, 0, 0, 0, 0);
        IPin[3] = new InputPin("d", 1, 5, 2, 0, 0, 0, 0);
        IPin[6] = new InputPin("e", 1, 6, 2, 0, 0, 0, 0);
        IPin[4] = new InputPin("OE", 1, 9, 2, 0, 0, 0, 1);
        IPin[5] = new InputPin("CE", 1, 7, 2, 0, 0, 0, 1);
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

        for(int l = 0; l < 7; l++)
            IPin[l].setLevel(-1);

        IPin[4].setLevel(5);
        IPin[5].setLevel(5);
        ComponentName = "PROM";
        ClassName = "Rom";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
        UpdateMemory(s);
    }

    public Rom(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        for(int k = 0; k < 8; k++)
            OPin[k].setLevel(0x6c9d8);

        for(int l = 0; l < 7; l++)
            IPin[l].setLevel(-1);

        IPin[4].setLevel(5);
        IPin[5].setLevel(5);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Rom rom = new Rom(this, i, j);
        rom.Tab = Tab;
        return rom;
    }

    public void Save(PrintStream printstream)
    {
        String s = new String();
        for(int i = 0; i < 32; i++)
            s = s + Tab[i] + " ";

        printstream.println("describe component Rom");
        printstream.println(" pos " + Pos.x + " " + Pos.y);
        printstream.println(" rotation " + NbRotation);
        printstream.println(" Mem " + s);
        printstream.println("end\tdescribe");
    }

    public String ExportAsScript()
    {
        String s = new String();
        for(int i = 0; i < 32; i++)
            s = s + Tab[i] + " ";

        String s1 = new String("[|Rom|pos|" + Pos.x + "|" + Pos.y + "|rotation|" + NbRotation + "|Mem|" + s + "|]\r\n^");
        return s1;
    }

    public void SimulateLogic()
    {
        if(IPin[5].getLevel() == 0 || IPin[4].getLevel() == 0)
        {
            for(int i = 0; i < 8; i++)
                OPin[i].setLevel(0x6c9d8);

            return;
        }
        Address = 0;
        if(IPin[0].getLevel() == 5)
            Address++;
        if(IPin[1].getLevel() == 5)
            Address += 2;
        if(IPin[2].getLevel() == 5)
            Address += 4;
        if(IPin[3].getLevel() == 5)
            Address += 8;
        if(IPin[6].getLevel() == 5)
            Address += 16;
        Output = Tab[Address];
        Q0 = Q1 = Q2 = Q3 = Q4 = Q5 = Q6 = Q7 = 0;
        if((Output & 1) == 1)
            Q0 = 5;
        if((Output & 2) == 2)
            Q1 = 5;
        if((Output & 4) == 4)
            Q2 = 5;
        if((Output & 8) == 8)
            Q3 = 5;
        if((Output & 0x10) == 16)
            Q4 = 5;
        if((Output & 0x20) == 32)
            Q5 = 5;
        if((Output & 0x40) == 64)
            Q6 = 5;
        if((Output & 0x80) == 128)
            Q7 = 5;
        OPin[0].setLevel(Q0);
        OPin[1].setLevel(Q1);
        OPin[2].setLevel(Q2);
        OPin[3].setLevel(Q3);
        OPin[4].setLevel(Q4);
        OPin[5].setLevel(Q5);
        OPin[6].setLevel(Q6);
        OPin[7].setLevel(Q7);
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        super.draw(g, i, j, k);
        g.setFill(MyColor.blue);
        DrawWithOffset.fillText(g,"PROM 32*8", ((l + HitBoxSize.width) - 5) * k, ((i1 + HitBoxSize.height) - 3) * k);
    }

    int Q0;
    int Q1;
    int Q2;
    int Q3;
    int Q4;
    int Q5;
    int Q6;
    int Q7;
    int Output;
    int Address;
}
