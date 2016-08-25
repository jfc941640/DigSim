package digsim.components;

import digsim.InputPin;
import digsim.MyColor;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, MyColor, Pin

public class PERIODEMETRE extends ElectronicComponent
{

    public PERIODEMETRE(Pin apin[][], int i, int j)
    {
        super(i, j, 2, 2, 1, 1, 18, 15, 3, 0);
        CtrA = 0;
        CtrA0 = 0;
        CtrA1 = 0;
        MemCtrA = 0;
        MemCtrA0 = 0;
        MemCtrA1 = 0;
        SomCtrA = 0;
        SomCtrA0 = 0;
        SomCtrA1 = 0;
        NbrCyclA = 0;
        CtrB = 0;
        CtrB0 = 0;
        CtrB1 = 0;
        MemCtrB = 0;
        MemCtrB0 = 0;
        MemCtrB1 = 0;
        SomCtrB = 0;
        SomCtrB0 = 0;
        SomCtrB1 = 0;
        NbrCyclB = 0;
        CtrAB = 0;
        MemCtrAB = 0;
        SomCtrAB = 0;
        NbrCyclAB = 0;
        VoieA = -1;
        VoieB = -1;
        OldVoieA = -1;
        OldVoieB = -1;
        Horloge = -1;
        OldHorloge = -1;
        NbreClics = 0;
        Clic = false;
        SignalA = false;
        SignalB = false;
        DepartA = false;
        DepartB = false;
        SgnPhaseAB = 0;
        IPin[0] = new InputPin("", 1, 2, 0, 0, 0, 0, 0);
        IPin[1] = new InputPin("", 1, 15, 0, 0, 0, 0, 0);
        IPin[2] = new InputPin("", 1, 9, 0, 0, 0, 0, 0);
        IPin[0].setLevel(-1);
        IPin[1].setLevel(-1);
        IPin[2].setLevel(-1);
        FonteComposant = Font.font("Serif", 10);
        FonteInit = Font.font("Sans-serif", FontWeight.BOLD, 12);
        ComponentName = "Periodemetre";
        ClassName = "PERIODEMETRE";
        Can_Rotate = false;
        RegisterPins(apin, i, j);
    }

    public PERIODEMETRE(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        CtrA = 0;
        CtrA0 = 0;
        CtrA1 = 0;
        MemCtrA = 0;
        MemCtrA0 = 0;
        MemCtrA1 = 0;
        SomCtrA = 0;
        SomCtrA0 = 0;
        SomCtrA1 = 0;
        NbrCyclA = 0;
        CtrB = 0;
        CtrB0 = 0;
        CtrB1 = 0;
        MemCtrB = 0;
        MemCtrB0 = 0;
        MemCtrB1 = 0;
        SomCtrB = 0;
        SomCtrB0 = 0;
        SomCtrB1 = 0;
        NbrCyclB = 0;
        CtrAB = 0;
        MemCtrAB = 0;
        SomCtrAB = 0;
        NbrCyclAB = 0;
        VoieA = -1;
        VoieB = -1;
        OldVoieA = -1;
        OldVoieB = -1;
        Horloge = -1;
        OldHorloge = -1;
        NbreClics = 0;
        Clic = false;
        SignalA = false;
        SignalB = false;
        DepartA = false;
        DepartB = false;
        SgnPhaseAB = 0;
        IPin[0].setLevel(-1);
        IPin[1].setLevel(-1);
        IPin[2].setLevel(-1);
        FonteComposant = Font.font("Serif", 10);
        FonteInit = Font.font("Sans-serif", FontWeight.BOLD, 12);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        PERIODEMETRE periodemetre = new PERIODEMETRE(this, i, j);
        return periodemetre;
    }

    public boolean SimMouseDown()
    {
        Clic = !Clic;
        return true;
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
        VoieA = IPin[0].getLevel();
        VoieB = IPin[1].getLevel();
        if(!SignalA && VoieA == 5)
        {
            SignalA = true;
            OldVoieA = VoieA;
        }
        if(!SignalB && VoieB == 5)
        {
            SignalB = true;
            OldVoieB = VoieB;
        }
        if(DepartA && (OldHorloge == 0 && Horloge == 5 || OldHorloge == 5 && Horloge == 0))
        {
            CtrA++;
            if(VoieA == 0)
                CtrA0++;
            if(VoieA == 5)
                CtrA1++;
        }
        if(DepartB && (OldHorloge == 0 && Horloge == 5 || OldHorloge == 5 && Horloge == 0))
        {
            CtrB++;
            if(VoieB == 0)
                CtrB0++;
            if(VoieB == 5)
                CtrB1++;
        }
        if(SgnPhaseAB == 1 && VoieB == 0 && (OldHorloge == 0 && Horloge == 5 || OldHorloge == 5 && Horloge == 0))
            CtrAB++;
        else
        if(SgnPhaseAB == 1 && VoieB == 5)
        {
            MemCtrAB = CtrAB;
            SomCtrAB = SomCtrAB + CtrAB;
            NbrCyclAB++;
            CtrAB = 0;
            SgnPhaseAB = 0;
        }
        if(SgnPhaseAB == -1 && VoieA == 0 && (OldHorloge == 0 && Horloge == 5 || OldHorloge == 5 && Horloge == 0))
            CtrAB--;
        else
        if(SgnPhaseAB == -1 && VoieA == 5)
        {
            MemCtrAB = CtrAB;
            SomCtrAB = SomCtrAB + CtrAB;
            NbrCyclAB++;
            CtrAB = 0;
            SgnPhaseAB = 0;
        }
        if(SignalA && OldVoieA == 0 && VoieA == 5)
        {
            MemCtrA = CtrA;
            MemCtrA0 = CtrA0;
            MemCtrA1 = CtrA1;
            SomCtrA = SomCtrA + CtrA;
            SomCtrA0 = SomCtrA0 + CtrA0;
            SomCtrA1 = SomCtrA1 + CtrA1;
            CtrA = 0;
            CtrA0 = 0;
            CtrA1 = 0;
            NbrCyclA++;
            DepartA = true;
        }
        if(SignalB && OldVoieB == 0 && VoieB == 5)
        {
            MemCtrB = CtrB;
            MemCtrB0 = CtrB0;
            MemCtrB1 = CtrB1;
            SomCtrB = SomCtrB + CtrB;
            SomCtrB0 = SomCtrB0 + CtrB0;
            SomCtrB1 = SomCtrB1 + CtrB1;
            CtrB = 0;
            CtrB0 = 0;
            CtrB1 = 0;
            NbrCyclB++;
            DepartB = true;
        }
        if(SignalA && SignalB && OldVoieA == 0 && VoieA == 5 && VoieB == 0)
        {
            SgnPhaseAB = 1;
            CtrAB = 0;
        } else
        if(SignalA && SignalB && OldVoieB == 0 && VoieB == 5 && VoieA == 0)
        {
            SgnPhaseAB = -1;
            CtrAB = 0;
        }
        if(Clic)
        {
            NbreClics++;
            Clic = false;
            if(NbreClics >= 2)
                NbreClics = 0;
        }
        OldHorloge = Horloge;
        OldVoieA = VoieA;
        OldVoieB = VoieB;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        g.setFill(Color.rgb(240, 240, 240));
        DrawWithOffset.fillRect(g,(l + 1) * k - 7, (i1 + 1) * k - 7, 19 * k + 3, 16 * k + 6);
        g.setFill(MyColor.lightGray);
        DrawWithOffset.fillRect(g,(l + 1) * k - 7, (i1 + 1) * k - 7, 2 * k + 3, 16 * k + 6);
        g.setStroke(MyColor.gray);
        DrawWithOffset.strokeRect(g,(l + 1) * k - 7, (i1 + 1) * k - 7, 19 * k + 3, 16 * k + 6);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,(l + 1) * k - 8, (i1 + 1) * k - 8, 19 * k + 5, 16 * k + 8);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeLine(g,(l + 2) * k + 4, (i1 + 1) * k - 6, (l + 2) * k + 4, (i1 + 17) * k - 2);
        g.setFont(FonteComposant);
        g.setFill(MyColor.darkGray);
        DrawWithOffset.fillText(g,"A", l * k + 5, (i1 + 2) * k - 5);
        DrawWithOffset.fillText(g,"Gnd", l * k + 1, (i1 + 9) * k - 5);
        DrawWithOffset.fillText(g,"B", l * k + 5, (i1 + 16) * k + 5);
        if(VoieA == 0)
        {
            g.setFill(MyColor.blue);
            DrawWithOffset.fillOval(g,l * k + 5, (i1 + 1) * k + 5, 1 * k - 1, 1 * k - 1);
        } else
        if(VoieA == 5)
        {
            g.setFill(MyColor.red);
            DrawWithOffset.fillOval(g,l * k + 5, (i1 + 1) * k + 5, 1 * k - 1, 1 * k - 1);
        }
        if(VoieB == 0)
        {
            g.setFill(MyColor.blue);
            DrawWithOffset.fillOval(g,l * k + 5, (i1 + 14) * k + 5, 1 * k - 1, 1 * k - 1);
        } else
        if(VoieB == 5)
        {
            g.setFill(MyColor.red);
            DrawWithOffset.fillOval(g,l * k + 5, (i1 + 14) * k + 5, 1 * k - 1, 1 * k - 1);
        }
        if(IPin[2].getLevel() == 0)
        {
            g.setFill(MyColor.blue);
            DrawWithOffset.fillOval(g,l * k + 5, (i1 + 8) * k + 5, 1 * k - 1, 1 * k - 1);
        }
        g.setStroke(MyColor.darkGray);
        DrawWithOffset.strokeOval(g,l * k + 4, (i1 + 1) * k + 4, 1 * k, 1 * k);
        DrawWithOffset.strokeOval(g,l * k + 5, (i1 + 1) * k + 5, 1 * k - 2, 1 * k - 2);
        DrawWithOffset.strokeOval(g,l * k + 4, (i1 + 14) * k + 4, 1 * k, 1 * k);
        DrawWithOffset.strokeOval(g,l * k + 5, (i1 + 14) * k + 5, 1 * k - 2, 1 * k - 2);
        DrawWithOffset.strokeOval(g,l * k + 4, (i1 + 8) * k + 4, 1 * k, 1 * k);
        DrawWithOffset.strokeOval(g,l * k + 5, (i1 + 8) * k + 5, 1 * k - 2, 1 * k - 2);
        g.setFill(MyColor.yellow);
        DrawWithOffset.fillRect(g,(l + 18) * k - 9, (i1 + 1) * k - 6, 2 * k + 5, 1 * k + 1);
        g.setStroke(MyColor.gray);
        DrawWithOffset.strokeRect(g,(l + 18) * k - 10, (i1 + 1) * k - 7, 2 * k + 6, 1 * k + 2);
        g.setFill(MyColor.red);
        DrawWithOffset.fillText(g,"INIT", (l + 17) * k, (i1 + 2) * k - 6);
        if(NbreClics == 0 && NbrCyclA == 0 && NbrCyclB == 0)
        {
            g.setFont(FonteInit);
            g.setFill(MyColor.black);
            DrawWithOffset.fillText(g,"Périodemètre ", (l + 5) * k + 5, (i1 + 4) * k);
            g.setFont(FonteComposant);
            DrawWithOffset.fillText(g,"En attente de signal ", (l + 5) * k + 5, (i1 + 7) * k - 7);
            DrawWithOffset.fillText(g,"sur voies A et/ou B ", (l + 5) * k + 5, (i1 + 8) * k - 5);
            DrawWithOffset.fillText(g,"Cliquer sur ''INIT'' pour lancer ", (l + 3) * k, (i1 + 11) * k);
            DrawWithOffset.fillText(g,"un nouveau cycle de mesures ", (l + 3) * k + 5, (i1 + 12) * k + 2);
            if(IPin[2].getLevel() == -1 && Horloge == 5)
                DrawWithOffset.fillText(g,"La masse n'est pas connectée", (l + 3) * k + 5, (i1 + 15) * k);
        } else
        if(NbreClics == 0 && (NbrCyclA >= 1 || NbrCyclB >= 1))
        {
            g.setFont(FonteComposant);
            g.setFill(MyColor.black);
            DrawWithOffset.fillText(g,"Voie A :", (l + 2) * k + 8, (i1 + 2) * k - 5);
            if(VoieA == -1)
                DrawWithOffset.fillText(g,"pas de signal", (l + 7) * k + 7, (i1 + 2) * k - 5);
            else
                DrawWithOffset.fillText(g,"cycle n\260 " + String.valueOf(NbrCyclA), (l + 7) * k + 7, (i1 + 2) * k - 5);
            DrawWithOffset.fillText(g,"Voie B :", (l + 2) * k + 8, (i1 + 13) * k - 5);
            if(VoieB == -1)
                DrawWithOffset.fillText(g,"pas de signal", (l + 7) * k + 7, (i1 + 13) * k - 5);
            else
                DrawWithOffset.fillText(g,"cycle n\260 " + String.valueOf(NbrCyclB), (l + 7) * k + 7, (i1 + 13) * k - 5);
            DrawWithOffset.fillText(g,"Comparaison des voies A et B", (l + 2) * k + 8, (i1 + 8) * k - 5);
            DrawWithOffset.fillText(g,"TA ", (l + 4) * k, (i1 + 3) * k - 3);
            DrawWithOffset.fillText(g,"0", (l + 4) * k + 4, (i1 + 4) * k - 3);
            DrawWithOffset.fillText(g,"1", (l + 4) * k + 4, (i1 + 5) * k - 3);
            DrawWithOffset.fillText(g," \u03B7a", (l + 4) * k, (i1 + 6) * k - 3);
            DrawWithOffset.fillText(g,"TB ", (l + 4) * k, (i1 + 14) * k - 4);
            DrawWithOffset.fillText(g,"0", (l + 4) * k + 4, (i1 + 15) * k - 4);
            DrawWithOffset.fillText(g,"1", (l + 4) * k + 4, (i1 + 16) * k - 4);
            DrawWithOffset.fillText(g," \u03B7b", (l + 4) * k, (i1 + 17) * k - 4);
        }
        if(NbreClics == 0)
        {
            if(MemCtrA != 0)
            {
                g.setFill(MyColor.red);
                double d = Math.floor((1000D * (double)MemCtrA1) / (double)MemCtrA + 0.5D) / 10D;
                DrawWithOffset.fillText(g,String.valueOf((double)MemCtrA / 2D), (l + 8) * k, (i1 + 3) * k - 3);
                DrawWithOffset.fillText(g,String.valueOf((double)MemCtrA0 / 2D), (l + 8) * k, (i1 + 4) * k - 3);
                DrawWithOffset.fillText(g,String.valueOf((double)MemCtrA1 / 2D), (l + 8) * k, (i1 + 5) * k - 3);
                if(d <= 100D)
                    DrawWithOffset.fillText(g,String.valueOf(d) + "%", (l + 8) * k, (i1 + 6) * k - 3);
                else
                    DrawWithOffset.fillText(g,"erreur", (l + 8) * k, (i1 + 6) * k - 3);
            }
            if(SomCtrA != 0)
            {
                g.setFill(MyColor.blue);
                double d1 = Math.floor((100D * (double)SomCtrA) / (2D * (double)(NbrCyclA - 1)) + 0.5D) / 100D;
                double d5 = Math.floor((100D * (double)SomCtrA0) / (2D * (double)(NbrCyclA - 1)) + 0.5D) / 100D;
                double d8 = Math.floor((100D * (double)SomCtrA1) / (2D * (double)(NbrCyclA - 1)) + 0.5D) / 100D;
                double d11 = Math.floor((1000D * (double)SomCtrA1) / (double)SomCtrA + 0.5D) / 10D;
                DrawWithOffset.fillText(g,String.valueOf(d1), (l + 14) * k, (i1 + 3) * k - 3);
                DrawWithOffset.fillText(g,String.valueOf(d5), (l + 14) * k, (i1 + 4) * k - 3);
                DrawWithOffset.fillText(g,String.valueOf(d8), (l + 14) * k, (i1 + 5) * k - 3);
                DrawWithOffset.fillText(g,String.valueOf(d11) + "%", (l + 14) * k, (i1 + 6) * k - 3);
            }
        }
        if(NbreClics == 0)
        {
            if(MemCtrB != 0)
            {
                g.setFill(MyColor.red);
                double d2 = Math.floor((1000D * (double)MemCtrB1) / (double)MemCtrB + 0.5D) / 10D;
                DrawWithOffset.fillText(g,String.valueOf((double)MemCtrB / 2D), (l + 8) * k, (i1 + 14) * k - 4);
                DrawWithOffset.fillText(g,String.valueOf((double)MemCtrB0 / 2D), (l + 8) * k, (i1 + 15) * k - 4);
                DrawWithOffset.fillText(g,String.valueOf((double)MemCtrB1 / 2D), (l + 8) * k, (i1 + 16) * k - 4);
                if(d2 <= 100D)
                    DrawWithOffset.fillText(g,String.valueOf(d2) + "%", (l + 8) * k, (i1 + 17) * k - 4);
                else
                    DrawWithOffset.fillText(g,"erreur", (l + 8) * k, (i1 + 12) * k - 3);
            }
            if(SomCtrB != 0)
            {
                g.setFill(MyColor.blue);
                double d3 = Math.floor((100D * (double)SomCtrB) / (2D * (double)(NbrCyclB - 1)) + 0.5D) / 100D;
                double d6 = Math.floor((100D * (double)SomCtrB0) / (2D * (double)(NbrCyclB - 1)) + 0.5D) / 100D;
                double d9 = Math.floor((100D * (double)SomCtrB1) / (2D * (double)(NbrCyclB - 1)) + 0.5D) / 100D;
                double d12 = Math.floor((1000D * (double)SomCtrB1) / (double)SomCtrB + 0.5D) / 10D;
                DrawWithOffset.fillText(g,String.valueOf(d3), (l + 14) * k, (i1 + 14) * k - 4);
                DrawWithOffset.fillText(g,String.valueOf(d6), (l + 14) * k, (i1 + 15) * k - 4);
                DrawWithOffset.fillText(g,String.valueOf(d9), (l + 14) * k, (i1 + 16) * k - 4);
                DrawWithOffset.fillText(g,String.valueOf(d12) + "%", (l + 14) * k, (i1 + 17) * k - 4);
            }
        }
        if(NbreClics == 0 && MemCtrA != 0 && MemCtrB != 0)
        {
            double d4 = Math.floor((1000D * (double)MemCtrA) / (double)MemCtrB + 0.5D) / 1000D;
            double d7 = Math.floor((1000D * (double)MemCtrB) / (double)MemCtrA + 0.5D) / 1000D;
            double d10 = Math.floor(1000D * ((1.0D * (double)SomCtrA * (double)(NbrCyclB - 1)) / (1.0D * (double)SomCtrB * (double)(NbrCyclA - 1))) + 0.5D) / 1000D;
            double d13 = Math.floor(1000D * ((1.0D * (double)SomCtrB * (double)(NbrCyclA - 1)) / (1.0D * (double)SomCtrA * (double)(NbrCyclB - 1))) + 0.5D) / 1000D;
            if(d10 >= 1.0D)
            {
                g.setFill(MyColor.black);
                DrawWithOffset.fillText(g,"TA/TB", (l + 4) * k - 5, (i1 + 9) * k - 3);
                g.setFill(MyColor.red);
                DrawWithOffset.fillText(g,String.valueOf(d4), (l + 8) * k, (i1 + 9) * k - 3);
                g.setFill(MyColor.blue);
                DrawWithOffset.fillText(g,String.valueOf(d10), (l + 14) * k, (i1 + 9) * k - 3);
            } else
            if(d10 < 1.0D)
            {
                g.setFill(MyColor.black);
                DrawWithOffset.fillText(g,"TB/TA", (l + 4) * k - 5, (i1 + 9) * k - 3);
                g.setFill(MyColor.red);
                DrawWithOffset.fillText(g,String.valueOf(d7), (l + 8) * k, (i1 + 9) * k - 3);
                g.setFill(MyColor.blue);
                DrawWithOffset.fillText(g,String.valueOf(d13), (l + 14) * k, (i1 + 9) * k - 3);
            }
            if(MemCtrA == MemCtrB)
            {
                g.setFill(MyColor.black);
                DrawWithOffset.fillText(g,"\u2191B-\u2191A", (l + 4) * k - 4, (i1 + 10) * k - 3);
                DrawWithOffset.fillText(g,"\u03C6B/A", (l + 4) * k - 3, (i1 + 11) * k - 3);
                g.setFill(MyColor.red);
                if(MemCtrAB < 0)
                    MemCtrAB = MemCtrA + MemCtrAB;
                double d14 = Math.floor(100D * (((double)(-MemCtrAB) * 360D) / (double)MemCtrA) + 0.5D) / 100D;
                DrawWithOffset.fillText(g,String.valueOf((double)MemCtrAB / 2D), (l + 8) * k, (i1 + 10) * k - 3);
                DrawWithOffset.fillText(g,String.valueOf(d14) + "\260", (l + 8) * k, (i1 + 11) * k - 3);
                g.setFill(MyColor.blue);
                if((1.0D * (double)SomCtrAB) / (double)(NbrCyclAB - 1) < 0.0D)
                {
                    double d15 = Math.floor(100D * (double)(SomCtrA / (NbrCyclA - 1) + SomCtrAB / NbrCyclAB) + 0.5D) / 100D;
                    double d17 = Math.floor(100D * ((-d15 * 360D) / (double)(SomCtrA / (NbrCyclA - 1))) + 0.5D) / 100D;
                    DrawWithOffset.fillText(g,String.valueOf(d15 / 2D), (l + 14) * k, (i1 + 10) * k - 3);
                    DrawWithOffset.fillText(g,String.valueOf(d17) + "\260", (l + 14) * k, (i1 + 11) * k - 3);
                } else
                {
                    double d16 = Math.floor(100D * (double)(SomCtrAB / NbrCyclAB) + 0.5D) / 100D;
                    double d18 = Math.floor(100D * ((-d16 * 360D) / (double)(SomCtrA / (NbrCyclA - 1))) + 0.5D) / 100D;
                    DrawWithOffset.fillText(g,String.valueOf(d16 / 2D), (l + 14) * k, (i1 + 10) * k - 3);
                    DrawWithOffset.fillText(g,String.valueOf(d18) + "\260", (l + 14) * k, (i1 + 11) * k - 3);
                }
            }
        }
        if(NbreClics == 1)
        {
            g.setFont(FonteInit);
            g.setFill(MyColor.red);
            DrawWithOffset.fillRect(g,(l + 1) * k - 7, (i1 + 1) * k - 7, 19 * k + 3, 16 * k + 7);
            g.setStroke(MyColor.gray);
            DrawWithOffset.strokeRect(g,(l + 1) * k - 7, (i1 + 1) * k - 7, 19 * k + 3, 16 * k + 7);
            g.setFill(MyColor.yellow);
            DrawWithOffset.fillText(g,"INITIALISATION", (l + 5) * k + 5, (i1 + 9) * k + 2);
            CtrA = 0;
            CtrA0 = 0;
            CtrA1 = 0;
            MemCtrA = 0;
            MemCtrA0 = 0;
            MemCtrA1 = 0;
            SomCtrA = 0;
            SomCtrA0 = 0;
            SomCtrA1 = 0;
            NbrCyclA = 0;
            CtrB = 0;
            CtrB0 = 0;
            CtrB1 = 0;
            MemCtrB = 0;
            MemCtrB0 = 0;
            MemCtrB1 = 0;
            SomCtrB = 0;
            SomCtrB0 = 0;
            SomCtrB1 = 0;
            NbrCyclB = 0;
            CtrAB = 0;
            MemCtrAB = 0;
            SomCtrAB = 0;
            NbrCyclAB = 0;
            VoieA = -1;
            VoieB = -1;
            OldVoieA = -1;
            OldVoieB = -1;
            Horloge = -1;
            OldHorloge = -1;
            NbreClics = 0;
            SignalA = false;
            SignalB = false;
            DepartA = false;
            DepartB = false;
            SgnPhaseAB = 0;
        }
    }

    int CtrA;
    int CtrA0;
    int CtrA1;
    int MemCtrA;
    int MemCtrA0;
    int MemCtrA1;
    int SomCtrA;
    int SomCtrA0;
    int SomCtrA1;
    int NbrCyclA;
    int CtrB;
    int CtrB0;
    int CtrB1;
    int MemCtrB;
    int MemCtrB0;
    int MemCtrB1;
    int SomCtrB;
    int SomCtrB0;
    int SomCtrB1;
    int NbrCyclB;
    int CtrAB;
    int MemCtrAB;
    int SomCtrAB;
    int NbrCyclAB;
    int VoieA;
    int VoieB;
    int OldVoieA;
    int OldVoieB;
    int Horloge;
    int OldHorloge;
    int NbreClics;
    boolean Clic;
    boolean SignalA;
    boolean SignalB;
    boolean DepartA;
    boolean DepartB;
    int SgnPhaseAB;
    public Font FonteComposant;
    public Font FonteInit;
    public Font FonteMono;
}
