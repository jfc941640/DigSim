package digsim.components;

import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.InputPin;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, OutputPin, MyColor,
//            Pin

public class AnalyseurHorloge extends ElectronicComponent
{

    public AnalyseurHorloge(Pin apin[][], int i, int j)
    {
        super(i, j, 9, 6, 0, 1, 1, 2, 2, 1);
        Clic = false;
        DimMin = 20;
        NbDiz = 10;
        DimTab = NbDiz * DimMin;
        InitAff = 8;
        ModeAff = InitAff;
        Timing = "";
        Timing0 = "";
        Timing1 = "";
        ComponentName = "Analyseur logique avec horloge externe";
        ClassName = "AnalyseurHorloge";
        Can_Rotate = false;
        IPin[0] = new InputPin("", 0, 0, 0, 1, 1, 0, 0);
        IPin[1] = new InputPin("", -1, 3, 1, 0, 0, 0, 0);
        OPin[0] = new OutputPin("", 0, 2, 0, 1, 1, 0, 0);
        RegisterPins(apin, i, j);
        REG = new int[DimTab];
        for(int k = 0; k < DimTab; k++)
            REG[k] = 0;

        IPin[0].setLevel(0);
        IPin[1].setLevel(0);
        OPin[0].Level = IPin[0].getLevel();
        AnalyseurHorlogeFont = Font.font("Monospaced", 14);
    }

    public AnalyseurHorloge(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Clic = false;
        DimMin = 20;
        NbDiz = 10;
        DimTab = NbDiz * DimMin;
        InitAff = 8;
        ModeAff = InitAff;
        Timing = "";
        Timing0 = "";
        Timing1 = "";
        REG = new int[DimTab];
        for(int k = 0; k < DimTab; k++)
            REG[k] = 0;

        IPin[0].setLevel(0);
        IPin[1].setLevel(0);
        OPin[0].Level = IPin[0].getLevel();
        AnalyseurHorlogeFont = Font.font("Monospaced", 14);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        AnalyseurHorloge analyseurhorloge = new AnalyseurHorloge(this, i, j);
        return analyseurhorloge;
    }

    public void SimulateLogic()
    {
        OPin[0].Level = IPin[0].getLevel();
        if(IPin[0].getOldLevel() == 0 && IPin[0].getLevel() == 5 || IPin[0].getOldLevel() == 5 && IPin[0].getLevel() == 0)
        {
            INPUT = IPin[1].getLevel();
            for(int i = DimTab - 1; i > 0; i--)
                REG[i] = REG[i - 1];

            REG[0] = INPUT;
        }
        IPin[0].OldLevel = IPin[0].getLevel();
        IPin[1].OldLevel = IPin[1].getLevel();
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
        g.setFont(AnalyseurHorlogeFont);
        g.setFill(Color.rgb(255, 255, 102));
        DrawWithOffset.fillRect(g,l * k - 3, (i1 + 1) * k + 1, 1 * k + 1, 2 * k - 2);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,l * k - 3, (i1 + 1) * k + 1, 1 * k + 1, 2 * k - 2);
        DrawWithOffset.strokeLine(g,l * k, i1 * k, l * k, (i1 + 1) * k);
        DrawWithOffset.strokeLine(g,(l - 1) * k, (i1 + 3) * k, l * k - 2, (i1 + 3) * k - 3);
        DrawWithOffset.strokeLine(g,(l - 1) * k, (i1 + 3) * k, l * k - 2, (i1 + 3) * k - 2);
        DrawWithOffset.strokeLine(g,(l - 1) * k, (i1 + 3) * k, l * k - 2, (i1 + 3) * k - 1);
        if(IPin[0].getLevel() == 5)
            g.setStroke(MyColor.red);
        else
            g.setStroke(MyColor.blue);
        DrawWithOffset.strokeLine(g,l * k - 3, (i1 + 1) * k + 1, l * k, (i1 + 1) * k + 6);
        DrawWithOffset.strokeLine(g,l * k + 3, (i1 + 1) * k + 1, l * k, (i1 + 1) * k + 6);
        if(Clic)
        {
            ModeAff = 2 * ModeAff;
            if(ModeAff > 2 * NbDiz)
                ModeAff = 1;
            Clic = false;
        }
        DimAff = DimMin * ModeAff;
        g.setFill(Color.rgb(250, 250, 250));
        DrawWithOffset.fillRect(g,(l + 1) * k - 1, (i1 + 1) * k + 1, (DimAff / 2) * k + 2, 2 * k - 2);
        g.setStroke(Color.rgb(230, 230, 230));
        DrawWithOffset.strokeRect(g,(l + 1) * k - 1, (i1 + 1) * k + 1, (DimAff / 2) * k + 2, 2 * k - 3);
        DrawWithOffset.strokeLine(g,(l + 1) * k - 1, (i1 + 3) * k - 1, (l + DimAff / 2 + 1) * k - 1, (i1 + 3) * k - 1);
        g.setStroke(MyColor.black);
        Timing = "";
        Timing1 = "";
        Timing0 = "";
        for(int j1 = 0; j1 < DimAff / 2; j1++)
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
        DrawWithOffset.fillText(g,Timing1, (l + 1) * k, (i1 + 1) * k - 1);
        DrawWithOffset.fillText(g,Timing1, (l + 1) * k, (i1 + 1) * k);
        g.setFill(Color.rgb(0, 0, 192));
        DrawWithOffset.fillText(g,Timing0, (l + 1) * k, (i1 + 2) * k + 1);
        DrawWithOffset.fillText(g,Timing0, (l + 1) * k, (i1 + 2) * k + 2);
        g.setStroke(MyColor.black);
        for(int k1 = 0; k1 <= DimAff / 4; k1++)
            DrawWithOffset.strokeLine(g,(l + 2 * k1 + 1) * k, (i1 + 3) * k - 2, (l + 2 * k1 + 1) * k, (i1 + 3) * k - 2);

        g.setStroke(MyColor.lightGray);
        for(int l1 = 0; l1 <= DimAff / 20; l1++)
            DrawWithOffset.strokeLine(g,(l + 10 * l1 + 1) * k, (i1 + 1) * k + 1, (l + 10 * l1 + 1) * k, (i1 + 3) * k - 1);

        for(int i2 = 0; i2 <= DimAff / 40; i2++)
        {
            g.setStroke(MyColor.darkGray);
            DrawWithOffset.strokeLine(g,(l + 20 * i2 + 1) * k, (i1 + 1) * k + 1, (l + 20 * i2 + 1) * k, (i1 + 3) * k - 1);
        }

    }

    boolean Clic;
    int REG[];
    int DimMin;
    int NbDiz;
    int DimTab;
    int Longueur;
    int Somme;
    int INPUT;
    int DimAff;
    int InitAff;
    int ModeAff;
    int Hauteur;
    String Timing;
    String Timing0;
    String Timing1;
    public Font AnalyseurHorlogeFont;
}