package digsim.components;



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

public class EmetteurSerie8bits extends IntegratedCircuit
{

    public EmetteurSerie8bits(Pin apin[][], int i, int j)
    {
        super(i, j, 18, 8, 2, 2, 14, 4, 18, 2);
        Out = 5;
        MemOut = 5;
        SortS = 5;
        ix = 99;
        Mill = 0;
        OldMill = 0;
        Emiss = 0;
        dddd = -1;
        cccc = -1;
        bbbb = -1;
        aaaa = -1;
        pppp = -1;
        mmmm = -1;
        hhhh = -1;
        iiii = -1;
        Mode = -1;
        Mem = new int[8];
        IPin[4] = new InputPin("d", 5, 8, 0, -2, 0, 0, 0);
        IPin[15] = new InputPin("c", 6, 8, 0, -2, 0, 0, 0);
        IPin[17] = new InputPin("b", 7, 8, 0, -2, 0, 0, 0);
        IPin[16] = new InputPin("a", 8, 8, 0, -2, 0, 0, 0);
        IPin[3] = new InputPin("p", 10, 8, 0, -2, 0, 0, 0);
        IPin[2] = new InputPin("m", 11, 8, 0, -2, 0, 0, 0);
        IPin[0] = new InputPin("h", 12, 8, 0, -2, 0, 0, 0);
        IPin[1] = new InputPin("i", 13, 8, 0, -2, 0, 0, 0);
        IPin[5] = new InputPin("H", 0, 4, 2, 0, 0, 0, 4);
        IPin[6] = new InputPin("0", 14, 0, 0, 2, 0, 0, 0);
        IPin[7] = new InputPin("1", 13, 0, 0, 2, 0, 0, 0);
        IPin[8] = new InputPin("2", 12, 0, 0, 2, 0, 0, 0);
        IPin[9] = new InputPin("3", 11, 0, 0, 2, 0, 0, 0);
        IPin[10] = new InputPin("4", 10, 0, 0, 2, 0, 0, 0);
        IPin[11] = new InputPin("5", 9, 0, 0, 2, 0, 0, 0);
        IPin[12] = new InputPin("6", 8, 0, 0, 2, 0, 0, 0);
        IPin[13] = new InputPin("7", 7, 0, 0, 2, 0, 0, 0);
        IPin[14] = new InputPin("St", 4, 0, 0, 2, 0, 0, 4);
        OPin[0] = new OutputPin("Tx", 18, 4, -2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("T", 5, 0, 0, 2, 0, 0, 1);
        for(int k = 0; k < 18; k++)
            IPin[k].setLevel(-1);

        ComponentName = "Emetteur serie 8bits";
        ClassName = "EmetteurSerie8bits";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public EmetteurSerie8bits(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Out = 5;
        MemOut = 5;
        SortS = 5;
        ix = 99;
        Mill = 0;
        OldMill = 0;
        Emiss = 0;
        dddd = -1;
        cccc = -1;
        bbbb = -1;
        aaaa = -1;
        pppp = -1;
        mmmm = -1;
        hhhh = -1;
        iiii = -1;
        Mode = -1;
        Mem = new int[8];
        for(int k = 0; k < 18; k++)
            IPin[k].setLevel(-1);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        EmetteurSerie8bits emetteurserie8bits = new EmetteurSerie8bits(this, i, j);
        return emetteurserie8bits;
    }

    public void SimulateLogic()
    {
        dddd = IPin[4].getLevel();
        cccc = IPin[15].getLevel();
        bbbb = IPin[17].getLevel();
        aaaa = IPin[16].getLevel();
        pppp = IPin[3].getLevel();
        mmmm = IPin[2].getLevel();
        hhhh = IPin[0].getLevel();
        iiii = IPin[1].getLevel();
        Horl = IPin[5].getLevel();
        Start = IPin[14].getLevel();
        Mode = (iiii + 2 * hhhh + 4 * mmmm + 16 * aaaa + 32 * bbbb + 64 * cccc) / 5;
        if(dddd == 5 && ix == 99)
        {
            ix = 0;
            ParTemp = 0;
            Out = 5;
            if(cccc == 5 && aaaa == 5 || bbbb == 5)
                Out = 0;
        }
        if(IPin[14].OldLevel == 0 && Start == 5 && Emiss == 0)
        {
            ix = 0;
            Out = 5;
            ParTemp = 0;
        }
        if(Mode < 8 && IPin[5].OldLevel == 0 && Horl == 5)
        {
            MemOut = SortS;
            if(ix == 0)
            {
                Out = 0;
                Emiss = 5;
            } else
            if(ix > 0 && ix < 9)
            {
                Out = Mem[ix - 1] = IPin[ix + 5].getLevel();
                if(Mem[ix - 1] == 5)
                    ParTemp++;
            } else
            if(ix == 9 && pppp == 5)
                Out = (ParTemp % 2) * 5;
            else
            if(ix >= 9 && ix < 30)
                Out = 5;
            else
            if(ix >= 30)
            {
                Out = 5;
                ix = 98;
            }
            if(ix > 8 && pppp == 0 || ix > 9 && pppp == 5)
                Emiss = 0;
            ix = ix + 1;
        }
        if(Mode == 64 && IPin[5].OldLevel == 0 && Horl == 5)
        {
            MemOut = SortS;
            if(ix == 0)
            {
                Out = 0;
                Emiss = 5;
            }
            if(ix >= 1 && ix < 25)
            {
                int i = Math.abs(ix) / 3;
                if((ix - 1) % 3 == 0)
                    Out = 5;
                if((ix - 1) % 3 == 1)
                {
                    Out = Mem[i] = IPin[i + 6].getLevel();
                    if(Mem[i] == 5)
                        ParTemp++;
                }
                if((ix - 1) % 3 == 2)
                    Out = 0;
            } else
            if(ix >= 25 && ix < 50 && pppp == 0)
            {
                Out = 5;
                ParTemp = 0;
            } else
            if(ix >= 50 && pppp == 0)
            {
                Out = 5;
                ix = 98;
                ParTemp = 0;
            } else
            if(ix == 25 && pppp == 5)
                Out = 5;
            else
            if(ix == 26 && pppp == 5)
                Out = (ParTemp % 2) * 5;
            else
            if(ix == 27 && pppp == 5)
                Out = 0;
            else
            if(ix >= 28 && ix < 50 && pppp == 5)
                Out = 5;
            else
            if(ix >= 50 && pppp == 5)
            {
                Out = 5;
                ix = 98;
            }
            if(ix > 26 && pppp == 0 || ix > 29 && pppp == 5)
                Emiss = 0;
            ix = ix + 1;
        }
        if(Mode == 80)
        {
            if(IPin[14].OldLevel == 0 && Start == 5 && Emiss == 0)
            {
                ix = 0;
                Out = 0;
                ParTemp = 0;
            }
            if(IPin[5].OldLevel == 0 && Horl == 5)
            {
                MemOut = SortS;
                if(ix >= 0 && ix < 24)
                {
                    int j = Math.abs(ix) / 3;
                    if(ix % 3 == 0)
                    {
                        Out = 5;
                        Emiss = 5;
                    }
                    if(ix % 3 == 1)
                    {
                        Out = Mem[j] = IPin[j + 6].getLevel();
                        if(Mem[j] == 5)
                            ParTemp++;
                    }
                    if(ix % 3 == 2)
                        Out = 0;
                } else
                if(ix >= 24 && ix < 50 && pppp == 0)
                {
                    Out = 0;
                    ParTemp = 0;
                } else
                if(ix >= 50 && pppp == 0)
                {
                    Out = 0;
                    ix = 98;
                    ParTemp = 0;
                } else
                if(ix == 24 && pppp == 5)
                    Out = 5;
                else
                if(ix == 25 && pppp == 5)
                    Out = (ParTemp % 2) * 5;
                else
                if(ix == 26 && pppp == 5)
                    Out = 0;
                else
                if(ix >= 27 && ix < 50 && pppp == 0)
                    Out = 0;
                else
                if(ix >= 50 && pppp == 5)
                {
                    Out = 0;
                    ix = 98;
                }
                if(ix > 23 && pppp == 0 || ix > 26 && pppp == 5)
                    Emiss = 0;
                ix = ix + 1;
            }
        }
        if(Mode == 96)
        {
            if(IPin[14].OldLevel == 0 && Start == 5 && Emiss == 0)
            {
                ix = 0;
                Out = 0;
                ParTemp = 0;
            }
            if(IPin[5].OldLevel == 0 && Horl == 5)
            {
                MemOut = SortS;
                if(ix >= 0 && ix < 32)
                {
                    int k = Math.abs(ix) / 4;
                    int l = ix % 4;
                    if(l == 0)
                    {
                        Out = 5;
                        Emiss = 5;
                    }
                    if(l == 1)
                        Out = Mem[k] = IPin[k + 6].getLevel();
                    if(l == 2)
                    {
                        Out = Mem[k] = IPin[k + 6].getLevel();
                        if(Mem[k] == 5)
                            ParTemp++;
                    }
                    if(l == 3)
                        Out = 0;
                } else
                if(ix >= 32 && ix < 50 && pppp == 0)
                {
                    Out = 0;
                    ParTemp = 0;
                } else
                if(ix >= 50 && pppp == 0)
                {
                    Out = 0;
                    ix = 98;
                    ParTemp = 0;
                } else
                if(ix == 32 && pppp == 5)
                    Out = 5;
                else
                if(ix == 33 && pppp == 5)
                    Out = (ParTemp % 2) * 5;
                else
                if(ix == 34 && pppp == 5)
                    Out = (ParTemp % 2) * 5;
                else
                if(ix == 35 && pppp == 5)
                    Out = 0;
                else
                if(ix >= 36 && ix < 50 && pppp == 0)
                    Out = 0;
                else
                if(ix >= 50 && pppp == 5)
                {
                    Out = 0;
                    ix = 98;
                }
                if(ix > 31 && pppp == 0 || ix > 35 && pppp == 5)
                    Emiss = 0;
                ix = ix + 1;
            }
        }
        if(Mode == 1 || Mode == 3)
        {
            if(Out == 0 && MemOut == 0)
                SortS = 5;
            else
            if(Out == 0 && MemOut == 5)
                SortS = 0;
            else
            if(Out == 5 && MemOut == 0)
                SortS = 0;
            else
            if(Out == 5 && MemOut == 5)
                SortS = 5;
        } else
        {
            SortS = Out;
        }
        if(Mode == 0 || Mode == 1 || Mode == 80 || Mode == 96)
            OPin[0].setLevel(SortS);
        if(Mode == 64)
        {
            if(SortS == 0)
                SortS = 5;
            else
                SortS = 0;
            OPin[0].setLevel(SortS);
        }
        if(Mode == 2 || Mode == 3)
            if(Horl == 0 && SortS == 0)
                OPin[0].setLevel(0);
            else
            if(Horl == 5 && SortS == 0)
                OPin[0].setLevel(5);
            else
            if(Horl == 0 && SortS == 5)
                OPin[0].setLevel(5);
            else
            if(Horl == 5 && SortS == 5)
                OPin[0].setLevel(0);
        if(Mode == 4)
            if(SortS == 0)
                OPin[0].setLevel(Horl);
            else
            if(SortS == 5 && OPin[0].getLevel() == 0)
                OPin[0].setLevel(0);
            else
            if(SortS == 5 && OPin[0].getLevel() == 5)
                OPin[0].setLevel(5);
        if(Mode == 5 && IPin[5].OldLevel == 5 && IPin[5].getLevel() == 0)
            if(SortS == 0)
                OPin[0].Level = OPin[0].getLevel();
            else
            if(SortS == 5 && OPin[0].getLevel() == 0)
                OPin[0].setLevel(5);
            else
            if(SortS == 5 && OPin[0].getLevel() == 5)
                OPin[0].setLevel(0);
        if(Mode == 6)
        {
            if(Horl == 0 && SortS == 0)
                Mill = 0;
            else
            if(Horl == 5 && SortS == 0)
                Mill = 5;
            else
            if(Horl == 0 && SortS == 5)
                Mill = 5;
            else
            if(Horl == 5 && SortS == 5)
                Mill = 0;
            if(OldMill == 0 && Mill == 5)
                if(OPin[0].getLevel() == 0)
                    OPin[0].setLevel(5);
                else
                    OPin[0].setLevel(0);
            OldMill = Mill;
        }
        if(Mode == 7)
            if(Horl == 5)
                OPin[0].setLevel(SortS);
            else
                OPin[0].setLevel(0);
        OPin[1].setLevel(Emiss);
        IPin[5].OldLevel = Horl;
        IPin[14].OldLevel = Start;
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        if(OPin[1].getLevel() == 0)
            g.setFill(MyColor.red);
        else
            g.setFill(MyColor.white);
        DrawWithOffset.fillOval(g,(l + 2) * k + 2, (i1 + 2) * k + 2, 1 * k - 4, 1 * k - 4);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,(l + 2) * k + 2, (i1 + 2) * k + 2, 1 * k - 4, 1 * k - 4);
        int j1 = (l + 4) * k - 1;
        int k1 = (i1 + 5) * k - 4;
        if(IPin[0].PinDim.height != 0)
        {
            g.setFill(MyColor.blue);
            if(Mode == 0)
                DrawWithOffset.fillText(g,"     Codage NRZ     ", j1, k1);
            if(Mode == 1)
                DrawWithOffset.fillText(g,"     Codage NRZI    ", j1, k1);
            if(Mode == 4)
                DrawWithOffset.fillText(g,"Code Mark Inversion ", j1, k1);
            if(Mode == 5)
                DrawWithOffset.fillText(g,"  Code transition   ", j1, k1);
            if(Mode == 6)
                DrawWithOffset.fillText(g,"Delay Mode (Miller) ", j1, k1);
            if(Mode == 7)
                DrawWithOffset.fillText(g,"     Codage RZ      ", j1, k1);
            g.setStroke(MyColor.red);
            if(Mode == 2)
                DrawWithOffset.fillText(g,"Manchester (biphasé)", j1, k1);
            if(Mode == 3)
                DrawWithOffset.fillText(g,"Biphasé différentiel", j1, k1);
            if(Mode == 64)
                DrawWithOffset.fillText(g,"    Type MM53200    ", j1, k1);
            if(Mode == 80)
                DrawWithOffset.fillText(g,"Largeur d'impulsion ", j1, k1);
            if(Mode == 96)
                DrawWithOffset.fillText(g,"    Code  1/4 3/4   ", j1, k1);
        }
    }

    int Out;
    int MemOut;
    int SortS;
    int ix;
    int ParTemp;
    int Mem[];
    int Mill;
    int OldMill;
    int Emiss;
    int Horl;
    int Start;
    int dddd;
    int cccc;
    int bbbb;
    int aaaa;
    int pppp;
    int mmmm;
    int hhhh;
    int iiii;
    int Mode;
}
