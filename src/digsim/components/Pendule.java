package digsim.components;

import java.util.Calendar;

import digsim.InputPin;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DigSim;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, OutputPin, MyColor,
//            DigSim, Pin

public class Pendule extends ElectronicComponent
{

    public Pendule(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 6, 0, 0, 0, 0, 1, 1);
        CtrClics = 0;
        Fa = false;
        Fb = false;
        Fc = false;
        F0 = false;
        F1 = false;
        F2 = false;
        F3 = false;
        F4 = false;
        F5 = false;
        F6 = false;
        F7 = false;
        F8 = false;
        F9 = false;
        F10 = false;
        F11 = false;
        IPin[0] = new InputPin("", -1, 0, 1, 0, 0, 0, 0);
        OPin[0] = new OutputPin("", 7, 1, 0, 0, 0, 0, 0);
        ComponentName = "Pendule a sortie serie";
        ClassName = "Pendule";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
        PenduleFont = Font.font("Sans-serif", FontWeight.BOLD, 10);
    }

    public Pendule(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        CtrClics = 0;
        Fa = false;
        Fb = false;
        Fc = false;
        F0 = false;
        F1 = false;
        F2 = false;
        F3 = false;
        F4 = false;
        F5 = false;
        F6 = false;
        F7 = false;
        F8 = false;
        F9 = false;
        F10 = false;
        F11 = false;
        PenduleFont = Font.font("Sans-serif", FontWeight.BOLD, 10);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Pendule pendule = new Pendule(this, i, j);
        return pendule;
    }

    public boolean SimMouseDown()
    {
        CtrClics = (CtrClics + 1) % (2 * NbreTests + 2);
        return true;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFont(PenduleFont);
        g.setFill(Color.rgb(255, 255, 102));
        DrawWithOffset.fillRect(g,l * k, i1 * k, 6 * k, 2 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,l * k, i1 * k, 6 * k, 2 * k);
        DrawWithOffset.strokeLine(g,(l + 6) * k, (i1 + 1) * k, (l + 7) * k, (i1 + 1) * k);
        DrawWithOffset.strokeLine(g,l * k, i1 * k + 4, l * k + 4, i1 * k);
        DrawWithOffset.strokeLine(g,l * k, i1 * k + 3, l * k + 3, i1 * k);
        DrawWithOffset.strokeLine(g,l * k, i1 * k + 2, l * k + 2, i1 * k);
        DrawWithOffset.strokeLine(g,l * k, i1 * k + 1, l * k + 1, i1 * k);
        Calendar calendar = Calendar.getInstance();
        Hh = calendar.get(11);
        Mm = calendar.get(12);
        Ss = calendar.get(13);
        uu = calendar.get(14);
        int j1 = Hh % 12;
        if(Ss > 20)
        {
            Fa = true;
            Fb = true;
            Fc = true;
            F0 = true;
            F1 = true;
            F2 = true;
            F3 = true;
            F4 = true;
            F5 = true;
            F6 = true;
            F7 = true;
            F8 = true;
            F9 = true;
            F10 = true;
            F11 = true;
        } else
        {
            if(Fa && (Mm == 15 || Mm == 30 || Mm == 45) && Ss == 0)
            {
                Fa = false;
                DigSim.hibou.play();
            }
            if(Fb && (Mm == 30 || Mm == 45) && Ss == 1)
            {
                Fb = false;
                DigSim.hibou.play();
            }
            if(Fc && Mm == 45 && Ss == 2)
            {
                Fc = false;
                DigSim.hibou.play();
            }
            if(F0 && Mm == 0 && Ss == 0 && (j1 >= 1 || j1 == 0))
            {
                DigSim.coucou.play();
                F0 = false;
            }
            if(F1 && Mm == 0 && Ss == 1 && (j1 >= 2 || j1 == 0))
            {
                DigSim.coucou.play();
                F1 = false;
            }
            if(F2 && Mm == 0 && Ss == 2 && (j1 >= 3 || j1 == 0))
            {
                DigSim.coucou.play();
                F2 = false;
            }
            if(F3 && Mm == 0 && Ss == 3 && (j1 >= 4 || j1 == 0))
            {
                DigSim.coucou.play();
                F3 = false;
            }
            if(F4 && Mm == 0 && Ss == 4 && (j1 >= 5 || j1 == 0))
            {
                DigSim.coucou.play();
                F4 = false;
            }
            if(F5 && Mm == 0 && Ss == 5 && (j1 >= 6 || j1 == 0))
            {
                DigSim.coucou.play();
                F5 = false;
            }
            if(F6 && Mm == 0 && Ss == 6 && (j1 >= 7 || j1 == 0))
            {
                DigSim.coucou.play();
                F6 = false;
            }
            if(F7 && Mm == 0 && Ss == 7 && (j1 >= 8 || j1 == 0))
            {
                DigSim.coucou.play();
                F7 = false;
            }
            if(F8 && Mm == 0 && Ss == 8 && (j1 >= 9 || j1 == 0))
            {
                DigSim.coucou.play();
                F8 = false;
            }
            if(F9 && Mm == 0 && Ss == 9 && (j1 >= 10 || j1 == 0))
            {
                DigSim.coucou.play();
                F9 = false;
            }
            if(F10 && Mm == 0 && Ss == 10 && (j1 >= 11 || j1 == 0))
            {
                DigSim.coucou.play();
                F10 = false;
            }
            if(F11 && Mm == 0 && Ss == 11 && j1 == 0)
            {
                DigSim.coucou.play();
                F11 = false;
            }
        }
        NbreTests = 5;
        if(CtrClics / 2 == 1)
        {
            Hh = 0;
            Mm = 57;
        }
        if(CtrClics / 2 == 2)
        {
            Hh = 1;
            Mm = 57;
        }
        if(CtrClics / 2 == 3)
        {
            Hh = 9;
            Mm = 57;
        }
        if(CtrClics / 2 == 4)
        {
            Hh = 19;
            Mm = 57;
        }
        if(CtrClics / 2 == 5)
        {
            Hh = 23;
            Mm = 57;
        }
        if(IPin[0].PinDim.width > 0)
        {
            g.setFill(MyColor.blue);
            mode1quart3quart = true;
            MmAff = Mm;
            HhAff = Hh;
        } else
        if(IPin[0].PinDim.height > 0)
        {
            g.setFill(MyColor.red);
            mode1quart3quart = false;
            MmAff = Mm;
            HhAff = Hh;
        } else
        if(IPin[0].PinDim.width < 0)
        {
            g.setFill(MyColor.black);
            mode1quart3quart = true;
            MmAff = (Mm + 1) % 60;
            if(Mm == 59)
                HhAff = (Hh + 1) % 24;
            else
                HhAff = Hh;
        } else
        if(IPin[0].PinDim.height < 0)
        {
            g.setFill(MyColor.green);
            mode1quart3quart = false;
            MmAff = (Mm + 1) % 60;
            if(Mm == 59)
                HhAff = (Hh + 1) % 24;
            else
                HhAff = Hh;
        }
        Unite = HhAff % 10;
        Dizaine = HhAff / 10;
        Nbre = Unite;
        h3 = h2 = h1 = h0 = 0;
        Reste = Nbre % 2;
        h0 = Reste;
        Nbre = Nbre / 2;
        Reste = Nbre % 2;
        h1 = Reste;
        Nbre = Nbre / 2;
        Reste = Nbre % 2;
        h2 = Reste;
        Nbre = Nbre / 2;
        Reste = Nbre % 2;
        h3 = Reste;
        Nbre = Nbre / 2;
        Nbre = Dizaine;
        H1 = H0 = 0;
        Reste = Nbre % 2;
        H0 = Reste;
        Nbre = Nbre / 2;
        Reste = Nbre % 2;
        H1 = Reste;
        Nbre = Nbre / 2;
        Unite = MmAff % 10;
        Dizaine = MmAff / 10;
        Nbre = Unite;
        m3 = m2 = m1 = m0 = 0;
        Reste = Nbre % 2;
        m0 = Reste;
        Nbre = Nbre / 2;
        Reste = Nbre % 2;
        m1 = Reste;
        Nbre = Nbre / 2;
        Reste = Nbre % 2;
        m2 = Reste;
        Nbre = Nbre / 2;
        Reste = Nbre % 2;
        m3 = Reste;
        Nbre = Nbre / 2;
        Nbre = Dizaine;
        M2 = M1 = M0 = 0;
        Reste = Nbre % 2;
        M0 = Reste;
        Nbre = Nbre / 2;
        Reste = Nbre % 2;
        M1 = Reste;
        Nbre = Nbre / 2;
        Reste = Nbre % 2;
        M2 = Reste;
        Nbre = Nbre / 2;
        Parite = (H1 + H0 + h3 + h2 + h1 + h0 + M2 + M1 + M0 + m3 + m2 + m1 + m0) % 2;
        String s = Integer.toString(Hh);
        if(Hh <= 9)
            s = "0" + s;
        String s4 = Integer.toString(Mm);
        if(Mm <= 9)
            s4 = "0" + s4;
        String s5 = Integer.toString(Ss);
        if(Ss <= 9)
            s5 = "0" + s5;
        if(CtrClics % 2 == 0)
            DrawWithOffset.fillText(g,s + ":" + s4 + ":" + s5, l * k + 3, (i1 + 1) * k + 4);
        else
            DrawWithOffset.fillText(g,"   ARRET", l * k, (i1 + 1) * k + 4);
    }

    public void SimulateLogic()
    {
    }

    public void Simulate(int i)
    {
        InformConnectedComponents(i);
    }

    public void InitBeforeSimulate()
    {
        OPin[0].InitBeforeSimulate();
        if(CtrClics % 2 == 0)
            if(mode1quart3quart)
            {
                if(uu < 250 && Ss < 14)
                    OPin[0].setLevel(5);
                else
                if(uu >= 250 && uu < 750)
                    switch(Ss)
                    {
                    case 0: // '\0'
                        OPin[0].setLevel(5 * m0);
                        break;

                    case 1: // '\001'
                        OPin[0].setLevel(5 * m1);
                        break;

                    case 2: // '\002'
                        OPin[0].setLevel(5 * m2);
                        break;

                    case 3: // '\003'
                        OPin[0].setLevel(5 * m3);
                        break;

                    case 4: // '\004'
                        OPin[0].setLevel(5 * M0);
                        break;

                    case 5: // '\005'
                        OPin[0].setLevel(5 * M1);
                        break;

                    case 6: // '\006'
                        OPin[0].setLevel(5 * M2);
                        break;

                    case 7: // '\007'
                        OPin[0].setLevel(5 * h0);
                        break;

                    case 8: // '\b'
                        OPin[0].setLevel(5 * h1);
                        break;

                    case 9: // '\t'
                        OPin[0].setLevel(5 * h2);
                        break;

                    case 10: // '\n'
                        OPin[0].setLevel(5 * h3);
                        break;

                    case 11: // '\013'
                        OPin[0].setLevel(5 * H0);
                        break;

                    case 12: // '\f'
                        OPin[0].setLevel(5 * H1);
                        break;

                    case 13: // '\r'
                        OPin[0].setLevel(5 * Parite);
                        break;
                    }
                else
                    OPin[0].setLevel(0);
            } else
            {
                switch(Ss)
                {
                case 0: // '\0'
                    OUT = 0;
                    break;

                case 1: // '\001'
                    OUT = 5 * m0;
                    break;

                case 2: // '\002'
                    OUT = 5 * m1;
                    break;

                case 3: // '\003'
                    OUT = 5 * m2;
                    break;

                case 4: // '\004'
                    OUT = 5 * m3;
                    break;

                case 5: // '\005'
                    OUT = 5 * M0;
                    break;

                case 6: // '\006'
                    OUT = 5 * M1;
                    break;

                case 7: // '\007'
                    OUT = 5 * M2;
                    break;

                case 8: // '\b'
                    OUT = 5 * h0;
                    break;

                case 9: // '\t'
                    OUT = 5 * h1;
                    break;

                case 10: // '\n'
                    OUT = 5 * h2;
                    break;

                case 11: // '\013'
                    OUT = 5 * h3;
                    break;

                case 12: // '\f'
                    OUT = 5 * H0;
                    break;

                case 13: // '\r'
                    OUT = 5 * H1;
                    break;

                case 14: // '\016'
                    OUT = 5 * Parite;
                    break;
                }
                if(Ss > 14)
                    OUT = 5;
                OPin[0].setLevel(OUT);
            }
    }

    int Hh;
    int HhAff;
    int H1;
    int H0;
    int h3;
    int h2;
    int h1;
    int h0;
    int Mm;
    int MmAff;
    int M2;
    int M1;
    int M0;
    int m3;
    int m2;
    int m1;
    int m0;
    int Ss;
    int S2;
    int S1;
    int S0;
    int s3;
    int s2;
    int s1;
    int s0;
    int Parite;
    int uu;
    int Nbre;
    int Reste;
    int Unite;
    int Dizaine;
    int OUT;
    int CtrClics;
    int NbreTests;
    boolean mode1quart3quart;
    boolean Fa;
    boolean Fb;
    boolean Fc;
    boolean F0;
    boolean F1;
    boolean F2;
    boolean F3;
    boolean F4;
    boolean F5;
    boolean F6;
    boolean F7;
    boolean F8;
    boolean F9;
    boolean F10;
    boolean F11;
    public Font PenduleFont;
}
