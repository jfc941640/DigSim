package digsim.components.mini;

import digsim.InputPin;
import digsim.MyColor;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, MyColor, Pin

public class MiniAnalyseur extends ElectronicComponent
{

    public MiniAnalyseur(Pin apin[][], int i, int j)
    {
        super(i, j, 9, 6, 1, 2, 0, 0, 1, 0);
        Clic = false;
        TailleMin = 10;
        NbreDizaines = 10;
        TailleTableau = 2 * NbreDizaines * TailleMin;
        InitModeAffTresCourt = 1;
        InitModeAffCourt = 2;
        InitModeAffLong = 4;
        InitModeAffTresLong = 8;
        ModeAffTresCourt = InitModeAffTresCourt;
        ModeAffCourt = InitModeAffCourt;
        ModeAffLong = InitModeAffLong;
        ModeAffTresLong = InitModeAffTresLong;
        Timing0 = "";
        Timing1 = "";
        ComponentName = "Mini analyseur logique";
        ClassName = "MiniAnalyseur";
        Can_Rotate = true;
        IPin[0] = new InputPin("", 0, 3, 0, 0, 0, 0, 0);
        RegisterPins(apin, i, j);
        REG = new int[TailleTableau];
        for(int k = 0; k < TailleTableau; k++)
            REG[k] = 0;

        IPin[0].setLevel(0);
        MiniAnalyseurFont = Font.font("Monospaced", 14);
    }

    public MiniAnalyseur(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Clic = false;
        TailleMin = 10;
        NbreDizaines = 10;
        TailleTableau = 2 * NbreDizaines * TailleMin;
        InitModeAffTresCourt = 1;
        InitModeAffCourt = 2;
        InitModeAffLong = 4;
        InitModeAffTresLong = 8;
        ModeAffTresCourt = InitModeAffTresCourt;
        ModeAffCourt = InitModeAffCourt;
        ModeAffLong = InitModeAffLong;
        ModeAffTresLong = InitModeAffTresLong;
        Timing0 = "";
        Timing1 = "";
        REG = new int[TailleTableau];
        for(int k = 0; k < TailleTableau; k++)
            REG[k] = 0;

        IPin[0].setLevel(0);
        MiniAnalyseurFont = Font.font("Monospaced", 14);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        MiniAnalyseur minianalyseur = new MiniAnalyseur(this, i, j);
        return minianalyseur;
    }

    public void InitBeforeSimulate()
    {
        if(Horloge == 0)
            Horloge = 5;
        else
            Horloge = 0;
    }

    public void SimulateLogic()
    {
        if(OldHorloge == 0 && Horloge == 5 || OldHorloge == 5 && Horloge == 0)
        {
            INPUT = IPin[0].getLevel();
            for(int i = TailleTableau - 1; i > 0; i--)
                REG[i] = REG[i - 1];

            REG[0] = INPUT;
        }
        IPin[0].OldLevel = IPin[0].getLevel();
        OldHorloge = Horloge;
    }

    public boolean SimMouseDown()
    {
        Clic = !Clic;
        return true;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFont(MiniAnalyseurFont);
        if(NbRotation == 0)
        {
            if(Clic)
            {
                ModeAffTresCourt++;
                if(ModeAffTresCourt > 2 * NbreDizaines)
                    ModeAffTresCourt = 1;
                Clic = false;
            }
            TailleAff = TailleMin * ModeAffTresCourt;
        } else
        if(NbRotation == 1)
        {
            if(Clic)
            {
                ModeAffCourt++;
                if(ModeAffCourt > 2 * NbreDizaines)
                    ModeAffCourt = 1;
                Clic = false;
            }
            TailleAff = TailleMin * ModeAffCourt;
        } else
        if(NbRotation == 2)
        {
            if(Clic)
            {
                ModeAffLong++;
                if(ModeAffLong > 2 * NbreDizaines)
                    ModeAffLong = 1;
                Clic = false;
            }
            TailleAff = TailleMin * ModeAffLong;
        } else
        if(NbRotation == 3)
        {
            if(Clic)
            {
                ModeAffTresLong++;
                if(ModeAffTresLong > 2 * NbreDizaines)
                    ModeAffTresLong = 1;
                Clic = false;
            }
            TailleAff = TailleMin * ModeAffTresLong;
        }
        g.setStroke(Color.rgb(250, 250, 250));
        DrawWithOffset.fillRect(g,(l + 1) * k - 1, (i1 + 2) * k + 1, TailleAff * k + 2, 1 * k - 2);
        g.setStroke(Color.rgb(230, 230, 230));
        DrawWithOffset.strokeLine(g,(l + 1) * k - 1, (i1 + 3) * k - 1, (l + TailleAff + 1) * k - 1, (i1 + 3) * k - 1);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeLine(g,l * k, (i1 + 3) * k, (l + 1) * k, (i1 + 3) * k - 5);
        DrawWithOffset.strokeLine(g,l * k, (i1 + 3) * k, (l + 1) * k, (i1 + 3) * k - 4);
        DrawWithOffset.strokeLine(g,l * k, (i1 + 3) * k, (l + 1) * k, (i1 + 3) * k - 3);
        g.setStroke(MyColor.black);
        Timing1 = "";
        Timing0 = "";
        for(int j1 = 0; j1 < TailleAff; j1++)
            if(REG[j1] == 5)
            {
                Timing1 = "_" + Timing1;
                Timing0 = " " + Timing0;
            } else
            {
                Timing0 = "_" + Timing0;
                Timing1 = " " + Timing1;
            }

        g.setFill(Color.rgb(192, 0, 0));
        DrawWithOffset.fillText(g,Timing1, (l + 1) * k, (i1 + 2) * k - 1);
        DrawWithOffset.fillText(g,Timing1, (l + 1) * k, (i1 + 2) * k - 0);
        g.setFill(Color.rgb(0, 0, 192));
        DrawWithOffset.fillText(g,Timing0, (l + 1) * k, (i1 + 2) * k + 2);
        DrawWithOffset.fillText(g,Timing0, (l + 1) * k, (i1 + 2) * k + 3);
        g.setStroke(MyColor.black);
        for(int k1 = 0; k1 <= TailleAff / 2; k1++)
            DrawWithOffset.strokeLine(g,(l + 2 * k1 + 1) * k, (i1 + 3) * k - 1, (l + 2 * k1 + 1) * k, (i1 + 3) * k - 1);

        g.setStroke(MyColor.lightGray);
        for(int l1 = 0; l1 <= TailleAff / 10; l1++)
            DrawWithOffset.strokeLine(g,(l + 10 * l1 + 1) * k, (i1 + 2) * k + 1, (l + 10 * l1 + 1) * k, (i1 + 3) * k - 1);

        for(int i2 = 0; i2 <= TailleAff / 20; i2++)
        {
            g.setStroke(MyColor.darkGray);
            DrawWithOffset.strokeLine(g,(l + 20 * i2 + 1) * k, (i1 + 2) * k + 1, (l + 20 * i2 + 1) * k, (i1 + 3) * k - 1);
        }

        g.setFill(MyColor.yellow);
        DrawWithOffset.fillRect(g,(l + 1) * k - 1, (i1 + 2) * k + 1, 0 * k + 2, 0 * k + 2);
    }

    boolean Clic;
    int Horloge;
    int OldHorloge;
    int REG[];
    int TailleMin;
    int NbreDizaines;
    int TailleTableau;
    int Longueur;
    int Somme;
    int INPUT;
    int TailleAff;
    int InitModeAffTresCourt;
    int InitModeAffCourt;
    int InitModeAffLong;
    int InitModeAffTresLong;
    int ModeAffTresCourt;
    int ModeAffCourt;
    int ModeAffLong;
    int ModeAffTresLong;
    int Hauteur;
    String Timing0;
    String Timing1;
    public Font MiniAnalyseurFont;
}
