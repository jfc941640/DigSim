package digsim.components;

import java.util.Date;

import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DigSim;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, MyColor,
//            DigSim, Pin, ElectronicComponent

public class FEUXCARREFOUR extends IntegratedCircuit
{

    public FEUXCARREFOUR(Pin apin[][], int i, int j)
    {
        super(i, j, 26, 25, 2, 2, 22, 21, 13, 4);
        poidsA = 2;
        poidsB = 4;
        poidsC = 3;
        poidsD = 5;
        SommePoids = poidsA + poidsB + poidsC + poidsD;
        TrafficMin = 5;
        TrafficMOY = 22;
        TRAFFICMAX = 220;
        Trafficnul = 0;
        LongueurCycle = 1600;
        Cycle = 0;
        OrangeMax = 20;
        rA = -1;
        oA = -1;
        vA = -1;
        rB = -1;
        oB = -1;
        vB = -1;
        rC = -1;
        oC = -1;
        vC = -1;
        rD = -1;
        oD = -1;
        vD = -1;
        minCTRa = 1;
        maxCTRa = 140;
        minCTRc = 1;
        maxCTRc = 140;
        minCTRd = 18;
        maxCTRd = 174;
        minCTRb = 19;
        maxCTRb = 170;
        CNTa = 0;
        CNTb = 0;
        CNTc = 0;
        CNTd = 0;
        CTRa1 = minCTRa;
        CTRa2 = minCTRa;
        CTRa3 = minCTRa;
        CTRa4 = minCTRa;
        CTRb = minCTRb;
        CTRc1 = minCTRc;
        CTRc2 = minCTRc;
        CTRc3 = minCTRc;
        CTRc4 = minCTRc;
        CTRd = minCTRd;
        CESTFINI = false;
        Bruitage = true;
        VOITa1 = false;
        VOITa2 = false;
        VOITa3 = false;
        VOITa4 = false;
        VOITb = false;
        VOITc1 = false;
        VOITc2 = false;
        VOITc3 = false;
        VOITc4 = false;
        VOITd = false;
        AdnsX = false;
        BdnsX = false;
        CdnsX = false;
        DdnsXdroite = false;
        DdnsXBas = false;
        DprndA = false;
        AprndA = false;
        DprndC = false;
        CprndC = false;
        BprndC = false;
        Acoll = false;
        Ccoll = false;
        PbNO = false;
        PbSU = false;
        PbSO = false;
        Bilan = true;
        Click = false;
        STOPa = false;
        ARRa = 0;
        TotAttBcleMACHa = 0L;
        STOPa1 = false;
        ARRa1 = 0;
        TotAttBcleMACHa1 = 0L;
        STOPa2 = false;
        ARRa2 = 0;
        TotAttBcleMACHa2 = 0L;
        STOPa3 = false;
        ARRa3 = 0;
        TotAttBcleMACHa3 = 0L;
        STOPa4 = false;
        ARRa4 = 0;
        TotAttBcleMACHa4 = 0L;
        STOPb = false;
        ARRb = 0;
        TotAttBcleMACHb = 0L;
        STOPc = false;
        ARRc = 0;
        TotAttBcleMACHc = 0L;
        STOPc3 = false;
        ARRc3 = 0;
        TotAttBcleMACHc3 = 0L;
        STOPc4 = false;
        ARRc4 = 0;
        TotAttBcleMACHc4 = 0L;
        STOPc1 = false;
        ARRc1 = 0;
        TotAttBcleMACHc1 = 0L;
        STOPc2 = false;
        ARRc2 = 0;
        TotAttBcleMACHc2 = 0L;
        STOPd = false;
        ARRd = 0;
        TotAttBcleMACHd = 0L;
        FinCycles = false;
        NbBcles = 0L;
        SwitchClosed = false;
        flag = "0";
        TestVertA = false;
        TestVertB = false;
        TestVertC = false;
        TestVertD = false;
        TestOrange = false;
        TestRougeA = false;
        TestRougeB = false;
        TestRougeC = false;
        TestRougeD = false;
        DureeoA = 0;
        DureeoB = 0;
        DureeoC = 0;
        DureeoD = 0;
        DureevA = 0;
        DureevB = 0;
        DureevC = 0;
        DureevD = 0;
        DureerA = 0;
        DureerB = 0;
        DureerC = 0;
        DureerD = 0;
        NbOrangA = 0;
        NbOrangB = 0;
        NbOrangC = 0;
        NbOrangD = 0;
        NbVertA = 0;
        NbVertB = 0;
        NbVertC = 0;
        NbVertD = 0;
        NbRougeA = 0;
        NbRougeB = 0;
        NbRougeC = 0;
        NbRougeD = 0;
        DureeMinV = 999;
        DureeMin0 = 999;
        DureeMinR = 999;
        ConflitoA = false;
        ConflitoB = false;
        ConflitoC = false;
        ConflitoD = false;
        ConflitVO = 0;
        FeuxEteints = 0;
        NbreTests = 20;
        loin = 37;
        proche = 42;
        arrive = 50;
        DistMin = 22;
        marge = 2;
        IPin[0] = new InputPin("rA", 0, 3, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("oA", 0, 4, 2, 0, 0, 0, 0);
        IPin[2] = new InputPin("vA", 0, 5, 2, 0, 0, 0, 0);
        IPin[3] = new InputPin("rB", 0, 7, 2, 0, 0, 0, 0);
        IPin[4] = new InputPin("oB", 0, 8, 2, 0, 0, 0, 0);
        IPin[5] = new InputPin("vB", 0, 9, 2, 0, 0, 0, 0);
        IPin[6] = new InputPin("rC", 0, 11, 2, 0, 0, 0, 0);
        IPin[7] = new InputPin("oC", 0, 12, 2, 0, 0, 0, 0);
        IPin[8] = new InputPin("vC", 0, 13, 2, 0, 0, 0, 0);
        IPin[9] = new InputPin("rD", 0, 15, 2, 0, 0, 0, 0);
        IPin[10] = new InputPin("oD", 0, 16, 2, 0, 0, 0, 0);
        IPin[11] = new InputPin("vD", 0, 17, 2, 0, 0, 0, 0);
        IPin[12] = new InputPin("", 8, 7, 0, 0, 0, 0, 0);
        OPin[0] = new OutputPin("A", 0, 19, 2, 0, 0, 0, 0);
        OPin[1] = new OutputPin("B", 0, 20, 2, 0, 0, 0, 0);
        OPin[2] = new OutputPin("C", 0, 21, 2, 0, 0, 0, 0);
        OPin[3] = new OutputPin("D", 0, 22, 2, 0, 0, 0, 0);
        ComponentName = "Feux de carrefour";
        ClassName = "FEUXCARREFOUR";
        Can_Rotate = false;
        RegisterPins(apin, i, j);
        VITINIT2 = (int)(Math.random() * 2D);
        VITINIT1 = (int)(Math.random() * 2D);
        CHRONOzero = 0L;
    }

    public FEUXCARREFOUR(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        poidsA = 2;
        poidsB = 4;
        poidsC = 3;
        poidsD = 5;
        SommePoids = poidsA + poidsB + poidsC + poidsD;
        TrafficMin = 5;
        TrafficMOY = 22;
        TRAFFICMAX = 220;
        Trafficnul = 0;
        LongueurCycle = 1600;
        Cycle = 0;
        OrangeMax = 20;
        rA = -1;
        oA = -1;
        vA = -1;
        rB = -1;
        oB = -1;
        vB = -1;
        rC = -1;
        oC = -1;
        vC = -1;
        rD = -1;
        oD = -1;
        vD = -1;
        minCTRa = 1;
        maxCTRa = 140;
        minCTRc = 1;
        maxCTRc = 140;
        minCTRd = 18;
        maxCTRd = 174;
        minCTRb = 19;
        maxCTRb = 170;
        CNTa = 0;
        CNTb = 0;
        CNTc = 0;
        CNTd = 0;
        CTRa1 = minCTRa;
        CTRa2 = minCTRa;
        CTRa3 = minCTRa;
        CTRa4 = minCTRa;
        CTRb = minCTRb;
        CTRc1 = minCTRc;
        CTRc2 = minCTRc;
        CTRc3 = minCTRc;
        CTRc4 = minCTRc;
        CTRd = minCTRd;
        CESTFINI = false;
        Bruitage = true;
        VOITa1 = false;
        VOITa2 = false;
        VOITa3 = false;
        VOITa4 = false;
        VOITb = false;
        VOITc1 = false;
        VOITc2 = false;
        VOITc3 = false;
        VOITc4 = false;
        VOITd = false;
        AdnsX = false;
        BdnsX = false;
        CdnsX = false;
        DdnsXdroite = false;
        DdnsXBas = false;
        DprndA = false;
        AprndA = false;
        DprndC = false;
        CprndC = false;
        BprndC = false;
        Acoll = false;
        Ccoll = false;
        PbNO = false;
        PbSU = false;
        PbSO = false;
        Bilan = true;
        Click = false;
        STOPa = false;
        ARRa = 0;
        TotAttBcleMACHa = 0L;
        STOPa1 = false;
        ARRa1 = 0;
        TotAttBcleMACHa1 = 0L;
        STOPa2 = false;
        ARRa2 = 0;
        TotAttBcleMACHa2 = 0L;
        STOPa3 = false;
        ARRa3 = 0;
        TotAttBcleMACHa3 = 0L;
        STOPa4 = false;
        ARRa4 = 0;
        TotAttBcleMACHa4 = 0L;
        STOPb = false;
        ARRb = 0;
        TotAttBcleMACHb = 0L;
        STOPc = false;
        ARRc = 0;
        TotAttBcleMACHc = 0L;
        STOPc3 = false;
        ARRc3 = 0;
        TotAttBcleMACHc3 = 0L;
        STOPc4 = false;
        ARRc4 = 0;
        TotAttBcleMACHc4 = 0L;
        STOPc1 = false;
        ARRc1 = 0;
        TotAttBcleMACHc1 = 0L;
        STOPc2 = false;
        ARRc2 = 0;
        TotAttBcleMACHc2 = 0L;
        STOPd = false;
        ARRd = 0;
        TotAttBcleMACHd = 0L;
        FinCycles = false;
        NbBcles = 0L;
        SwitchClosed = false;
        flag = "0";
        TestVertA = false;
        TestVertB = false;
        TestVertC = false;
        TestVertD = false;
        TestOrange = false;
        TestRougeA = false;
        TestRougeB = false;
        TestRougeC = false;
        TestRougeD = false;
        DureeoA = 0;
        DureeoB = 0;
        DureeoC = 0;
        DureeoD = 0;
        DureevA = 0;
        DureevB = 0;
        DureevC = 0;
        DureevD = 0;
        DureerA = 0;
        DureerB = 0;
        DureerC = 0;
        DureerD = 0;
        NbOrangA = 0;
        NbOrangB = 0;
        NbOrangC = 0;
        NbOrangD = 0;
        NbVertA = 0;
        NbVertB = 0;
        NbVertC = 0;
        NbVertD = 0;
        NbRougeA = 0;
        NbRougeB = 0;
        NbRougeC = 0;
        NbRougeD = 0;
        DureeMinV = 999;
        DureeMin0 = 999;
        DureeMinR = 999;
        ConflitoA = false;
        ConflitoB = false;
        ConflitoC = false;
        ConflitoD = false;
        ConflitVO = 0;
        FeuxEteints = 0;
        NbreTests = 20;
        loin = 37;
        proche = 42;
        arrive = 50;
        DistMin = 22;
        marge = 2;
        VITINIT2 = (int)(Math.random() * 2D);
        VITINIT1 = (int)(Math.random() * 2D);
        CHRONOzero = 0L;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        FEUXCARREFOUR feuxcarrefour = new FEUXCARREFOUR(this, i, j);
        return feuxcarrefour;
    }

    public boolean SimMouseDown()
    {
        SwitchClosed = true;
        return true;
    }

    public boolean SimMouseUp()
    {
        SwitchClosed = false;
        return true;
    }

    public void SimulateLogic()
    {
        if(IPin[12].getOldLevel() == 0 && IPin[12].getLevel() == 5 || IPin[12].getOldLevel() == 5 && IPin[12].getLevel() == 0)
        {
            IPin[12].OldLevel = IPin[12].getLevel();
            if(!FinCycles)
                if(NbBcles == 1L)
                {
                    if(CHRONOzero == 0L)
                        CHRONOzero = (new Date()).getTime();
                    Cycle = 1;
                    PROBa12 = TRAFFICMAX;
                    PROBb = TRAFFICMAX;
                    PROBc34 = TRAFFICMAX;
                    PROBd = TRAFFICMAX;
                    PROBa34 = PROBa12 / 2;
                    PROBc12 = PROBc34 / 2;
                } else
                if(NbBcles == (long)(1 * LongueurCycle))
                {
                    Cycle = 2;
                    PROBa12 = TrafficMOY;
                    PROBb = TrafficMin;
                    PROBc34 = TrafficMOY;
                    PROBd = TrafficMin;
                    PROBa34 = PROBa12 / 2;
                    PROBc12 = PROBc34 / 2;
                } else
                if(NbBcles == (long)(2 * LongueurCycle))
                {
                    Cycle = 3;
                    PROBa12 = TrafficMin;
                    PROBb = TrafficMin;
                    PROBc34 = TrafficMin;
                    PROBd = TRAFFICMAX;
                    PROBa34 = PROBa12 / 2;
                    PROBc12 = PROBc34 / 2;
                } else
                if(NbBcles == (long)(3 * LongueurCycle))
                {
                    Cycle = 4;
                    PROBa12 = TrafficMOY;
                    PROBb = TrafficMOY;
                    PROBc34 = TrafficMOY;
                    PROBd = TrafficMOY;
                    PROBa34 = PROBa12 / 2;
                    PROBc12 = PROBc34 / 2;
                } else
                if(NbBcles == (long)(4 * LongueurCycle))
                {
                    Cycle = 5;
                    PROBa12 = TrafficMin;
                    PROBb = TrafficMOY;
                    PROBc34 = TrafficMOY;
                    PROBd = TrafficMOY;
                    PROBa34 = PROBa12 / 2;
                    PROBc12 = PROBc34 / 2;
                } else
                if(NbBcles == (long)(5 * LongueurCycle))
                {
                    Cycle = 6;
                    PROBa12 = TrafficMOY;
                    PROBb = TrafficMOY;
                    PROBc34 = TrafficMOY;
                    PROBd = TrafficMin;
                    PROBa34 = PROBa12 / 2;
                    PROBc12 = PROBc34 / 2;
                } else
                if(NbBcles == (long)(6 * LongueurCycle))
                {
                    Cycle = 7;
                    PROBa12 = TrafficMOY;
                    PROBb = TrafficMin;
                    PROBc34 = TRAFFICMAX;
                    PROBd = TrafficMin;
                    PROBa34 = PROBa12 / 2;
                    PROBc12 = PROBc34 / 2;
                } else
                if(NbBcles == (long)(7 * LongueurCycle))
                {
                    Cycle = 8;
                    PROBa12 = TRAFFICMAX;
                    PROBb = TrafficMin;
                    PROBc34 = TrafficMOY;
                    PROBd = TrafficMin;
                    PROBa34 = PROBa12 / 2;
                    PROBc12 = PROBc34 / 2;
                } else
                if(NbBcles == (long)(8 * LongueurCycle))
                {
                    Cycle = 9;
                    PROBa12 = TRAFFICMAX;
                    PROBb = TrafficMin;
                    PROBc34 = TRAFFICMAX;
                    PROBd = TrafficMin;
                    PROBa34 = PROBa12 / 2;
                    PROBc12 = PROBc34 / 2;
                } else
                if(NbBcles == (long)(9 * LongueurCycle))
                {
                    Cycle = 10;
                    PROBa12 = TrafficMOY;
                    PROBb = TrafficMOY;
                    PROBc34 = TrafficMOY;
                    PROBd = TrafficMOY;
                    PROBa34 = TrafficMOY;
                    PROBc12 = TrafficMOY;
                } else
                if(NbBcles == (long)(10 * LongueurCycle))
                {
                    Cycle = 11;
                    PROBa12 = Trafficnul;
                    PROBb = TrafficMOY;
                    PROBc34 = Trafficnul;
                    PROBd = TrafficMOY;
                    PROBa34 = PROBa12 / 2;
                    PROBc12 = PROBc34 / 2;
                } else
                if(NbBcles == (long)(11 * LongueurCycle))
                {
                    Cycle = 12;
                    PROBa12 = TRAFFICMAX;
                    PROBb = Trafficnul;
                    PROBc34 = TRAFFICMAX;
                    PROBd = Trafficnul;
                    PROBa34 = PROBa12 / 2;
                    PROBc12 = PROBc34 / 2;
                } else
                if(NbBcles == (long)(12 * LongueurCycle))
                {
                    Cycle = 13;
                    PROBa12 = Trafficnul;
                    PROBb = TrafficMin;
                    PROBc34 = TRAFFICMAX;
                    PROBd = Trafficnul;
                    PROBa34 = PROBa12 / 2;
                    PROBc12 = PROBc34 / 2;
                } else
                if(NbBcles == (long)(13 * LongueurCycle))
                {
                    Cycle = 14;
                    PROBa12 = TrafficMOY;
                    PROBb = TrafficMOY;
                    PROBc34 = TrafficMOY;
                    PROBd = TrafficMOY;
                    PROBa34 = PROBa12 / 2;
                    PROBc12 = PROBc34 / 2;
                } else
                if(NbBcles == (long)(14 * LongueurCycle))
                {
                    Cycle = 15;
                    PROBa12 = TrafficMOY;
                    PROBb = TRAFFICMAX;
                    PROBc34 = TRAFFICMAX;
                    PROBd = Trafficnul;
                    PROBa34 = PROBa12 / 2;
                    PROBc12 = PROBc34 / 2;
                } else
                if(NbBcles == (long)(15 * LongueurCycle))
                {
                    Cycle = 16;
                    PROBa12 = Trafficnul;
                    PROBb = TrafficMOY;
                    PROBc34 = TrafficMOY;
                    PROBd = Trafficnul;
                    PROBa34 = PROBa12 / 2;
                    PROBc12 = PROBc34 / 2;
                } else
                if(NbBcles == (long)(16 * LongueurCycle))
                {
                    Cycle = 17;
                    PROBa12 = TrafficMOY;
                    PROBb = TRAFFICMAX;
                    PROBc34 = Trafficnul;
                    PROBd = TRAFFICMAX;
                    PROBa34 = PROBa12 / 2;
                    PROBc12 = PROBc34 / 2;
                } else
                if(NbBcles == (long)(17 * LongueurCycle))
                {
                    Cycle = 18;
                    PROBa12 = Trafficnul;
                    PROBb = TrafficMOY;
                    PROBc34 = Trafficnul;
                    PROBd = TrafficMOY;
                    PROBa34 = PROBa12 / 2;
                    PROBc12 = PROBc34 / 2;
                } else
                if(NbBcles == (long)(18 * LongueurCycle))
                {
                    Cycle = 19;
                    PROBa12 = TrafficMOY;
                    PROBb = Trafficnul;
                    PROBc34 = TRAFFICMAX;
                    PROBd = TRAFFICMAX;
                    PROBa34 = PROBa12 / 2;
                    PROBc12 = PROBc34 / 2;
                } else
                if(NbBcles == (long)(19 * LongueurCycle))
                {
                    Cycle = 20;
                    PROBa12 = Trafficnul;
                    PROBb = Trafficnul;
                    PROBc34 = TrafficMOY;
                    PROBd = TrafficMOY;
                    PROBa34 = PROBa12 / 2;
                    PROBc12 = PROBc34 / 2;
                } else
                if(NbBcles > (long)(NbreTests * LongueurCycle))
                {
                    Cycle = 99;
                    FinCycles = true;
                    PROBa12 = (int)(2D + Math.random() * 500D);
                    PROBb = (int)(2D + Math.random() * 500D);
                    PROBc34 = (int)(2D + Math.random() * 500D);
                    PROBd = (int)(2D + Math.random() * 500D);
                    PROBa34 = (int)(2D + Math.random() * 50D);
                    PROBc12 = (int)(2D + Math.random() * 50D);
                }
            if((!PbSU && !PbNO && !PbSU && !PbSO && !Acoll && !Ccoll) || SwitchClosed)
            {
                if(!VOITb && Math.random() * 1000D < (double)PROBb)
                {
                    VOITb = true;
                    VITb = (int)(Math.random() * 2D);
                }
                if(VOITb)
                    if(CTRb < loin)
                        CTRb = CTRb + 1 + VITb;
                    else
                    if(CTRb >= arrive - marge)
                        CTRb = CTRb + 1 + VITb;
                    else
                    if(CTRb >= loin && CTRb < proche)
                    {
                        if(rB == 5 || oB == 5)
                            CTRb = CTRb + 1;
                        else
                            CTRb = CTRb + 1 + VITb;
                    } else
                    if(CTRb >= proche && CTRb < arrive - marge)
                        if(rB == 5 && oB == 0 && vB == 0)
                            CTRb = CTRb;
                        else
                        if(vB == 5 && rB == 0 && oB == 0)
                            CTRb = CTRb + 1 + VITb;
                        else
                        if(oB == 5 && rB == 0 && vB == 0)
                            CTRb = CTRb + 1;
                if(CTRb > maxCTRb || CTRb < minCTRb)
                {
                    CTRb = minCTRb;
                    VOITb = false;
                    BprndC = false;
                }
                if(CTRb >= proche && CTRb < arrive + 20)
                    OPin[1].setLevel(5);
                else
                    OPin[1].setLevel(0);
                if(!VOITd && Math.random() * 1000D < (double)PROBd)
                {
                    VOITd = true;
                    VITd = (int)(Math.random() * 2D);
                    DIRECTd = (int)(Math.random() * 3D);
                }
                if(VOITd)
                    if(CTRd < loin)
                        CTRd = CTRd + 1 + VITd;
                    else
                    if(CTRd >= arrive - marge)
                        CTRd = CTRd + 1 + VITd;
                    else
                    if(CTRd >= loin && CTRd < proche)
                    {
                        if(rD == 5 || oD == 5)
                            CTRd = CTRd + 1;
                        else
                            CTRd = CTRd + 1 + VITd;
                    } else
                    if(CTRd >= proche && CTRd < arrive - marge)
                        if(rD == 5 && oD == 0 && vD == 0)
                            CTRd = CTRd;
                        else
                        if(vD == 5 && rD == 0 && oD == 0)
                            CTRd = CTRd + 1 + VITd;
                        else
                        if(oD == 5 && rD == 0 && vD == 0)
                            CTRd = CTRd + 1;
                if(CTRd > maxCTRd || CTRd < minCTRd)
                {
                    CTRd = minCTRd;
                    VOITd = false;
                    DprndA = false;
                    DprndC = false;
                }
                if(CTRd >= proche && CTRd < arrive + 20)
                    OPin[3].setLevel(5);
                else
                    OPin[3].setLevel(0);
                if(!VOITa1 && Math.random() * 1000D < (double)PROBa12)
                {
                    VOITa1 = true;
                    VITa1 = (int)(Math.random() * 2D);
                }
                if(VOITa1)
                    if(CTRa1 < loin)
                        CTRa1 = CTRa1 + 2 + VITa1;
                    else
                    if(CTRa1 >= arrive - marge)
                        CTRa1 = CTRa1 + 2 + VITa1;
                    else
                    if(CTRa1 >= loin && CTRa1 < proche)
                    {
                        if(rA == 5 || oA == 5)
                            CTRa1 = CTRa1 + 1 + VITa1;
                        else
                            CTRa1 = CTRa1 + 2 + VITa1;
                    } else
                    if(CTRa1 >= proche && CTRa1 < arrive - marge)
                        if(rA == 5 && oA == 0 && vA == 0)
                            CTRa1 = CTRa1;
                        else
                        if(vA == 5 && rA == 0 && oA == 0)
                            CTRa1 = CTRa1 + 2 + VITa1;
                        else
                        if(oA == 5 && rA == 0 && vA == 0)
                            CTRa1 = CTRa1 + 1;
                if(!VOITa2 && Math.random() * 1000D < (double)PROBa12)
                {
                    VOITa2 = true;
                    VITa2 = (int)(Math.random() * 2D);
                    CAMIONa2 = (int)(Math.random() * 2D);
                }
                if(VOITa2)
                    if(CTRa2 < loin)
                        CTRa2 = CTRa2 + 2 + VITa2;
                    else
                    if(CTRa2 >= arrive - marge)
                        CTRa2 = CTRa2 + 2 + VITa2;
                    else
                    if(CTRa2 >= loin && CTRa2 < proche)
                    {
                        if(rA == 5 || oA == 5)
                            CTRa2 = CTRa2 + 1 + VITa2;
                        else
                            CTRa2 = CTRa2 + 2 + VITa2;
                    } else
                    if(CTRa2 >= proche && CTRa2 < arrive - marge)
                        if(rA == 5 && oA == 0 && vA == 0)
                            CTRa2 = CTRa2;
                        else
                        if(vA == 5 && rA == 0 && oA == 0)
                            CTRa2 = CTRa2 + 2 + VITa2;
                        else
                        if(oA == 5 && rA == 0 && vA == 0)
                            CTRa2 = CTRa2 + 1;
                if(!VOITa3 && Math.random() * 1000D < (double)PROBa34)
                {
                    VOITa3 = true;
                    VITa3 = (int)(Math.random() * 4D);
                }
                if(VOITa3)
                    if(CTRa3 < loin)
                        CTRa3 = CTRa3 + 2 + VITa3;
                    else
                    if(CTRa3 >= arrive - marge)
                        CTRa3 = CTRa3 + 2 + VITa3;
                    else
                    if(CTRa3 >= loin && CTRa3 < proche)
                    {
                        if(rA == 5 || oA == 5)
                            CTRa3 = CTRa3 + 1 + VITa3;
                        else
                            CTRa3 = CTRa3 + 2 + VITa3;
                    } else
                    if(CTRa3 >= proche && CTRa3 < arrive - marge)
                        if(rA == 5 && oA == 0 && vA == 0)
                            CTRa3 = CTRa3;
                        else
                        if(vA == 5 && rA == 0 && oA == 0)
                            CTRa3 = CTRa3 + 2 + VITa3;
                        else
                        if(oA == 5 && rA == 0 && vA == 0)
                            CTRa3 = CTRa3 + 1;
                if(!VOITa4 && Math.random() * 1000D < (double)PROBa34)
                {
                    VOITa4 = true;
                    VITa4 = (int)(Math.random() * 3D);
                }
                if(VOITa4)
                    if(CTRa4 < loin)
                        CTRa4 = CTRa4 + 2 + VITa4;
                    else
                    if(CTRa4 >= arrive - marge)
                        CTRa4 = CTRa4 + 2 + VITa4;
                    else
                    if(CTRa4 >= loin && CTRa4 < proche)
                    {
                        if(rA == 5 || oA == 5)
                            CTRa4 = CTRa4 + 1 + VITa4;
                        else
                            CTRa4 = CTRa4 + 2 + VITa4;
                    } else
                    if(CTRa4 >= proche && CTRa4 < arrive - marge)
                        if(rA == 5 && oA == 0 && vA == 0)
                            CTRa4 = CTRa4;
                        else
                        if(vA == 5 && rA == 0 && oA == 0)
                            CTRa4 = CTRa4 + 2 + VITa4;
                        else
                        if(oA == 5 && rA == 0 && vA == 0)
                            CTRa4 = CTRa4 + 1;
                if(CTRa2 >= CTRa1 && CTRa2 - CTRa1 < DistMin + 2)
                    CTRa1 = CTRa2 - DistMin - 1;
                if(CTRa2 < CTRa1 && CTRa1 - CTRa2 < DistMin + 2)
                    CTRa2 = CTRa1 - DistMin - 1;
                if(CTRa4 >= CTRa3 && CTRa4 - CTRa3 < DistMin + 2)
                    CTRa3 = CTRa4 - DistMin - 1;
                if(CTRa4 < CTRa3 && CTRa3 - CTRa4 < DistMin + 2)
                    CTRa4 = CTRa3 - DistMin - 1;
                if(CTRa1 > maxCTRa || CTRa1 < minCTRa)
                {
                    CTRa1 = minCTRa;
                    VOITa1 = false;
                    AprndA = false;
                }
                if(CTRa2 > maxCTRa || CTRa2 < minCTRa)
                {
                    CTRa2 = minCTRa;
                    VOITa2 = false;
                    AprndA = false;
                    CAMIONa2 = (int)(Math.random() * 2D);
                }
                if(CTRa3 > maxCTRa || CTRa3 < minCTRa)
                {
                    CTRa3 = minCTRa;
                    VOITa3 = false;
                    AprndA = false;
                }
                if(CTRa4 > maxCTRa || CTRa4 < minCTRa)
                {
                    CTRa4 = minCTRa;
                    VOITa4 = false;
                    AprndA = false;
                }
                if(CTRa1 > proche && CTRa1 < arrive + 10 || CTRa2 > proche && CTRa2 < arrive + 10 || CTRa3 > proche && CTRa3 < arrive + 10 || CTRa4 > proche && CTRa4 < arrive + 10)
                    OPin[0].setLevel(5);
                else
                    OPin[0].setLevel(0);
                if(!VOITc1 && Math.random() * 1000D < (double)PROBc12)
                {
                    VOITc1 = true;
                    VITc1 = (int)(Math.random() * 4D);
                }
                if(VOITc1)
                    if(CTRc1 < loin)
                        CTRc1 = CTRc1 + 2 + VITc1;
                    else
                    if(CTRc1 >= arrive - marge)
                        CTRc1 = CTRc1 + 2 + VITc1;
                    else
                    if(CTRc1 >= loin && CTRc1 < proche)
                    {
                        if(rC == 5 || oC == 5)
                            CTRc1 = CTRc1 + 1 + VITc1;
                        else
                            CTRc1 = CTRc1 + 2 + VITc1;
                    } else
                    if(CTRc1 >= proche && CTRc1 < arrive - marge)
                        if(rC == 5 && oC == 0 && vC == 0)
                            CTRc1 = CTRc1;
                        else
                        if(vC == 5 && rC == 0 && oC == 0)
                            CTRc1 = CTRc1 + 2 + VITc1;
                        else
                        if(oC == 5 && rC == 0 && vC == 0)
                            CTRc1 = CTRc1 + 1;
                if(!VOITc2 && Math.random() * 1000D < (double)PROBc12)
                {
                    VOITc2 = true;
                    VITc2 = (int)(Math.random() * 3D);
                }
                if(VOITc2)
                    if(CTRc2 < loin)
                        CTRc2 = CTRc2 + 2 + VITc2;
                    else
                    if(CTRc2 >= arrive - marge)
                        CTRc2 = CTRc2 + 2 + VITc2;
                    else
                    if(CTRc2 >= loin && CTRc2 < proche)
                    {
                        if(rC == 5 || oC == 5)
                            CTRc2 = CTRc2 + 1 + VITc2;
                        else
                            CTRc2 = CTRc2 + 2 + VITc2;
                    } else
                    if(CTRc2 >= proche && CTRc2 < arrive - marge)
                        if(rC == 5 && oC == 0 && vC == 0)
                            CTRc2 = CTRc2;
                        else
                        if(vC == 5 && rC == 0 && oC == 0)
                            CTRc2 = CTRc2 + 2 + VITc2;
                        else
                        if(oC == 5 && rC == 0 && vC == 0)
                            CTRc2 = CTRc2 + 1;
                if(!VOITc3 && Math.random() * 1000D < (double)PROBc34)
                {
                    VOITc3 = true;
                    VITc3 = (int)(Math.random() * 2D);
                }
                if(VOITc3)
                    if(CTRc3 < loin)
                        CTRc3 = CTRc3 + 2 + VITc3;
                    else
                    if(CTRc3 >= arrive - marge)
                        CTRc3 = CTRc3 + 2 + VITc3;
                    else
                    if(CTRc3 >= loin && CTRc3 < proche)
                    {
                        if(rC == 5 || oC == 5)
                            CTRc3 = CTRc3 + 1 + VITc3;
                        else
                            CTRc3 = CTRc3 + 2 + VITc3;
                    } else
                    if(CTRc3 >= proche && CTRc3 < arrive - marge)
                        if(rC == 5 && oC == 0 && vC == 0)
                            CTRc3 = CTRc3;
                        else
                        if(vC == 5 && rC == 0 && oC == 0)
                            CTRc3 = CTRc3 + 2 + VITc3;
                        else
                        if(oC == 5 && rC == 0 && vC == 0)
                            CTRc3 = CTRc3 + 1;
                if(!VOITc4 && Math.random() * 1000D < (double)PROBc34)
                {
                    VOITc4 = true;
                    VITc4 = (int)(Math.random() * 2D);
                    CAMIONc4 = (int)(Math.random() * 2D);
                }
                if(VOITc4)
                    if(CTRc4 < loin)
                        CTRc4 = CTRc4 + 2 + VITc4;
                    else
                    if(CTRc4 >= arrive - marge)
                        CTRc4 = CTRc4 + 2 + VITc4;
                    else
                    if(CTRc4 >= loin && CTRc4 < proche)
                    {
                        if(rC == 5 || oC == 5)
                            CTRc4 = CTRc4 + 1 + VITc4;
                        else
                            CTRc4 = CTRc4 + 2 + VITc4;
                    } else
                    if(CTRc4 >= proche && CTRc4 < arrive - marge)
                        if(rC == 5 && oC == 0 && vC == 0)
                            CTRc4 = CTRc4;
                        else
                        if(vC == 5 && rC == 0 && oC == 0)
                            CTRc4 = CTRc4 + 2 + VITc4;
                        else
                        if(oC == 5 && rC == 0 && vC == 0)
                            CTRc4 = CTRc4 + 1;
                if(CTRc2 >= CTRc1 && CTRc2 - CTRc1 < DistMin + 2)
                    CTRc1 = CTRc2 - DistMin - 1;
                if(CTRc2 < CTRc1 && CTRc1 - CTRc2 < DistMin + 2)
                    CTRc2 = CTRc1 - DistMin - 1;
                if(CTRc4 >= CTRc3 && CTRc4 - CTRc3 < DistMin + 2)
                    CTRc3 = CTRc4 - DistMin - 1;
                if(CTRc4 < CTRc3 && CTRc3 - CTRc4 < DistMin + 2)
                    CTRc4 = CTRc3 - DistMin - 1;
                if(CTRc1 > maxCTRc || CTRc1 < minCTRc)
                {
                    CTRc1 = minCTRc;
                    VOITc1 = false;
                    CprndC = false;
                }
                if(CTRc2 > maxCTRc || CTRc2 < minCTRc)
                {
                    CTRc2 = minCTRc;
                    VOITc2 = false;
                    CprndC = false;
                }
                if(CTRc3 > maxCTRc || CTRc3 < minCTRc)
                {
                    CTRc3 = minCTRc;
                    VOITc3 = false;
                    CprndC = false;
                }
                if(CTRc4 > maxCTRc || CTRc4 < minCTRc)
                {
                    CTRc4 = minCTRc;
                    VOITc4 = false;
                    Ccoll = false;
                    CprndC = false;
                }
                if(CTRc1 >= proche && CTRc1 < arrive + 10 || CTRc2 >= proche && CTRc2 < arrive + 10 || CTRc3 >= proche && CTRc3 < arrive + 10 || CTRc4 >= proche && CTRc4 < arrive + 10)
                    OPin[2].setLevel(5);
                else
                    OPin[2].setLevel(0);
            }
            if(DprndA && (CTRa1 > arrive || CTRa2 > arrive) && CTRa1 > CTRa2 && CTRd > CTRa1 - 15 && CTRd - CTRa1 < 5)
                CTRa1 = CTRd - 5;
            if(DprndA && (CTRa1 > arrive || CTRa2 > arrive) && CTRa2 > CTRa1 && CTRd > CTRa2 - 15 && CTRd - CTRa2 < 5)
                CTRa2 = CTRd - 5;
            if(DprndC && (CTRa3 > arrive || CTRc3 > arrive) && CTRc3 > CTRc4 && CTRd > CTRc3 && CTRd - CTRc3 < 65)
                CTRc3 = CTRd - 65;
            if(DprndC && (CTRa4 > arrive || CTRc4 > arrive) && CTRc4 > CTRc3 && CTRd > CTRc4 && CTRd - CTRc4 < 65)
                CTRc4 = CTRd - 65;
            if(BprndC && (CTRc1 > arrive || CTRc2 > arrive) && CTRc1 > CTRc2 && CTRb > CTRc1 && CTRb - CTRc1 < 8)
                CTRc1 = CTRb - 8;
            if(BprndC && (CTRc1 > arrive || CTRc2 > arrive) && CTRc2 > CTRc1 && CTRb > CTRc2 && CTRb - CTRc2 < 8)
                CTRc2 = CTRb - 8;
            if(BprndC && CTRc1 > arrive + 47 && (CTRc1 > CTRc2 || CTRc2 < arrive) && CTRc1 - CTRb < 47 && CTRc1 - CTRb > 0)
                CTRb = CTRc1 - 46;
            if(BprndC && CTRc2 > arrive + 47 && (CTRc2 > CTRc1 || CTRc1 < arrive) && CTRc2 - CTRb < 47 && CTRc2 - CTRb > 0)
                CTRb = CTRc2 - 46;
            if(!Acoll && DprndA && CTRa1 > 100 && (CTRa1 > CTRa2 || CTRa2 < arrive) && CTRa1 - CTRd < 50 && CTRa1 - CTRd > 0)
            {
                CTRd = CTRa1 - 49;
                flag = "1";
            }
            if(!Acoll && DprndA && CTRa2 > 100 && (CTRa2 > CTRa1 || CTRa1 < arrive) && CTRa2 - CTRd < 50 && CTRa2 - CTRd > 0)
            {
                CTRd = CTRa2 - 49;
                flag = "2";
            }
            if(VOITb)
            {
                if(CTRb >= 116)
                {
                    CTRb = minCTRb;
                    VOITb = false;
                }
                if(CTRb > arrive + 2 && CTRb < 80)
                    BdnsX = true;
                else
                    BdnsX = false;
                if(CTRb >= 55 && CTRb < 116)
                    BprndC = true;
                else
                    BprndC = false;
            }
            if(VOITd)
                if(DIRECTd == 0)
                {
                    if(CTRd >= 158)
                    {
                        CTRd = minCTRd;
                        VOITd = false;
                    }
                    if(CTRd > arrive + 2 && CTRd < 78)
                        DdnsXdroite = true;
                    else
                        DdnsXdroite = false;
                    if(CTRd > 76 && CTRd < 130)
                        DdnsXBas = true;
                    else
                        DdnsXBas = false;
                } else
                if(DIRECTd == 1)
                {
                    if(CTRd > arrive + 2 && CTRd < 78)
                        DdnsXdroite = true;
                    else
                        DdnsXdroite = false;
                    if(CTRd > 76 && CTRd < 136)
                        DdnsXBas = true;
                    else
                        DdnsXBas = false;
                    if(CTRd >= 110 && CTRd < 175)
                        DprndC = true;
                    else
                        DprndC = false;
                } else
                if(DIRECTd == 2)
                {
                    if(CTRd >= 120)
                    {
                        CTRd = minCTRd;
                        VOITd = false;
                    }
                    if(CTRd > arrive + 2 && CTRd < 78)
                        DdnsXdroite = true;
                    else
                        DdnsXdroite = false;
                    if(CTRd >= 54 && CTRd < 120)
                        DprndA = true;
                    else
                        DprndA = false;
                }
            if(CTRa1 > arrive + 2 && CTRa1 < 95 || CTRa2 > arrive + 2 && CTRa2 < 95 || CTRa3 > arrive + 2 && CTRa3 < 95 || CTRa4 > arrive + 2 && CTRa4 < 95)
                AdnsX = true;
            else
                AdnsX = false;
            if(CTRa1 >= 80 && CTRa1 < 120 || CTRa2 >= 80 && CTRa2 < 120 || CTRa3 >= 80 && CTRa3 < 120 || CTRa4 >= 80 && CTRa4 < 120)
                AprndA = true;
            else
                AprndA = false;
            if(CTRc1 > arrive + 2 && CTRc1 < 95 || CTRc2 > arrive + 2 && CTRc2 < 95 || CTRc3 > arrive + 2 && CTRc3 < 95 || CTRc4 > arrive + 2 && CTRc4 < 95)
                CdnsX = true;
            else
                CdnsX = false;
            if(CTRc1 >= 95 && CTRc1 < 120 || CTRc2 >= 95 && CTRc2 < 120 || CTRc3 >= 95 && CTRc3 < 120 || CTRc4 >= 95 && CTRc4 < 120)
                CprndC = true;
            else
                CprndC = false;
            if(BdnsX && CdnsX && (CTRc1 > 65 || CTRc2 > 65 || CTRc3 > 65 || CTRc4 > 65))
            {
                PbSU = true;
                Bilan = false;
            }
            if(AdnsX && DdnsXdroite && (CTRa1 > 65 || CTRa2 > 65 || CTRa3 > 65 || CTRa4 > 65) && CTRd < 100)
            {
                PbNO = true;
                Bilan = false;
            }
            if(AdnsX && DdnsXBas && (CTRa1 > 65 || CTRa2 > 65 || CTRa3 > 65 || CTRa4 > 65) && CTRd < 100)
            {
                PbNO = true;
                Bilan = false;
            }
            if(AdnsX && DdnsXBas && (CTRa1 > 65 || CTRa2 > 65 || CTRa3 > 65 || CTRa4 > 65) && CTRd < 100)
            {
                PbNO = true;
                Bilan = false;
            }
            if(BdnsX && DdnsXBas && DIRECTd != 0 && CTRd > 98)
            {
                PbSU = true;
                Bilan = false;
            }
            if(CdnsX && DdnsXBas && CTRd < 120)
            {
                PbSU = true;
                Bilan = false;
            }
            if(AprndA && DprndA && (CTRa1 - CTRd < 43 && CTRa1 - CTRd > 0 || CTRa2 - CTRd < 43 && CTRa2 - CTRd > 0))
            {
                Acoll = true;
                Bilan = false;
            }
            if(CprndC && DprndC && (CTRd - CTRc3 < 58 || CTRd - CTRc4 < 58))
            {
                Ccoll = true;
                Bilan = false;
            }
            if(CprndC && BprndC && (CTRb - CTRc1 < 2 && CTRb - CTRc1 > 0 || CTRb - CTRc2 < 2 && CTRb - CTRc2 > 0))
            {
                Ccoll = true;
                Bilan = false;
            }
            if(CprndC && BprndC && CTRb < 60 && (CTRc3 - CTRb < 43 && CTRc3 - CTRb > 0 || CTRc4 - CTRb < 43 && CTRc4 - CTRb > 0))
            {
                Ccoll = true;
                Bilan = false;
            }
            if(DprndC && BprndC && (CTRb < 90) & (CTRd - CTRb < 20))
            {
                Ccoll = true;
                Bilan = false;
            }
            if(NbBcles > 10L && oA + oB + oC + oD == 0)
                TestOrange = true;
            if(TestOrange)
            {
                if(vA == 5)
                    DureeoA = 0;
                if(oA == 5)
                {
                    DureeoA = DureeoA + 1;
                    NbOrangA = NbOrangA + 1;
                }
                if(vB == 5)
                    DureeoB = 0;
                if(oB == 5)
                {
                    DureeoB = DureeoB + 1;
                    NbOrangB = NbOrangB + 1;
                }
                if(vC == 5)
                    DureeoC = 0;
                if(oC == 5)
                {
                    DureeoC = DureeoC + 1;
                    NbOrangC = NbOrangC + 1;
                }
                if(vD == 5)
                    DureeoD = 0;
                if(oD == 5)
                {
                    DureeoD = DureeoD + 1;
                    NbOrangD = NbOrangD + 1;
                }
                if(NbOrangA > 1 && rA == 5 && DureeoA != 0)
                    DureeMin0 = Math.min(DureeMin0, DureeoA);
                if(NbOrangB > 1 && rB == 5 && DureeoB != 0)
                    DureeMin0 = Math.min(DureeMin0, DureeoB);
                if(NbOrangC > 1 && rC == 5 && DureeoC != 0)
                    DureeMin0 = Math.min(DureeMin0, DureeoC);
                if(NbOrangD > 1 && rD == 5 && DureeoD != 0)
                    DureeMin0 = Math.min(DureeMin0, DureeoD);
            }
            if(NbBcles > 10L && vA == 5)
                TestRougeA = true;
            if(NbBcles > 10L && vB == 5)
                TestRougeB = true;
            if(NbBcles > 10L && vC == 5)
                TestRougeC = true;
            if(NbBcles > 10L && vD == 5)
                TestRougeD = true;
            if(TestRougeA)
            {
                if(oA == 5)
                    DureerA = 0;
                if(rA == 5)
                {
                    DureerA = DureerA + 1;
                    NbRougeA = NbRougeA + 1;
                }
                if(NbRougeA > 2 && vA == 5 && DureerA != 0)
                    DureeMinR = Math.min(DureeMinR, DureerA);
            }
            if(TestRougeB)
            {
                if(oB == 5)
                    DureerB = 0;
                if(rB == 5)
                {
                    DureerB = DureerB + 1;
                    NbRougeB = NbRougeB + 1;
                }
                if(NbRougeB > 2 && vB == 5 && DureerB != 0)
                    DureeMinR = Math.min(DureeMinR, DureerB);
            }
            if(TestRougeC)
            {
                if(oC == 5)
                    DureerC = 0;
                if(rC == 5)
                {
                    DureerC = DureerC + 1;
                    NbRougeC = NbRougeC + 1;
                }
                if(NbRougeC > 2 && vC == 5 && DureerC != 0)
                    DureeMinR = Math.min(DureeMinR, DureerC);
            }
            if(TestRougeD)
            {
                if(oD == 5)
                    DureerD = 0;
                if(rD == 5)
                {
                    DureerD = DureerD + 1;
                    NbRougeD = NbRougeD + 1;
                }
                if(NbRougeD > 2 && vD == 5 && DureerD != 0)
                    DureeMinR = Math.min(DureeMinR, DureerD);
            }
            if(NbBcles > 10L && rA == 5)
                TestVertA = true;
            if(NbBcles > 10L && rB == 5)
                TestVertB = true;
            if(NbBcles > 10L && rC == 5)
                TestVertC = true;
            if(NbBcles > 10L && rD == 5)
                TestVertD = true;
            if(TestVertA)
            {
                if(rA == 5)
                    DureevA = 0;
                if(vA == 5)
                {
                    DureevA = DureevA + 1;
                    NbVertA = NbVertA + 1;
                }
                if(NbVertA > 1 && oA == 5 && DureevA != 0)
                    DureeMinV = Math.min(DureeMinV, DureevA);
            }
            if(TestVertB)
            {
                if(rB == 5)
                    DureevB = 0;
                if(vB == 5)
                {
                    DureevB = DureevB + 1;
                    NbVertB = NbVertB + 1;
                }
                if(NbVertB > 1 && oB == 5 && DureevB != 0)
                    DureeMinV = Math.min(DureeMinV, DureevB);
            }
            if(TestVertC)
            {
                if(rC == 5)
                    DureevC = 0;
                if(vC == 5)
                {
                    DureevC = DureevC + 1;
                    NbVertC = NbVertC + 1;
                }
                if(NbVertC > 1 && oC == 5 && DureevC != 0)
                    DureeMinV = Math.min(DureeMinV, DureevC);
            }
            if(TestVertD)
            {
                if(rD == 5)
                    DureevD = 0;
                if(vD == 5)
                {
                    DureevD = DureevD + 1;
                    NbVertD = NbVertD + 1;
                }
                if(NbVertD > 1 && oD == 5 && DureevD != 0)
                    DureeMinV = Math.min(DureeMinV, DureevD);
            }
            if(!FinCycles)
            {
                NbBcles = NbBcles + 1L;
                if(CTRd >= loin && CTRd < proche)
                    nbreBOUCLESd = NbBcles;
                if(CTRd >= proche && CTRd <= arrive && rD == 5)
                {
                    STOPd = true;
                    AttMACHd = NbBcles - nbreBOUCLESd;
                    TotAttBcleMACHd++;
                }
                if(CTRd > arrive && STOPd)
                {
                    ARRd = ARRd + 1;
                    STOPd = false;
                    TMoyMACHd = TotAttBcleMACHd / (long)ARRd;
                    AttMACHd = 0L;
                }
                if(CTRb >= loin && CTRb < proche)
                    nbreBOUCLESb = NbBcles;
                if(CTRb >= proche && CTRb <= arrive && rB == 5)
                {
                    STOPb = true;
                    AttMACHb = NbBcles - nbreBOUCLESb;
                    TotAttBcleMACHb++;
                }
                if(CTRb > arrive && STOPb)
                {
                    ARRb = ARRb + 1;
                    STOPb = false;
                    TMoyMACHb = TotAttBcleMACHb / (long)ARRb;
                    AttMACHb = 0L;
                }
                if(CTRa1 >= loin && CTRa1 < proche)
                    nbreBOUCLESa1 = NbBcles;
                if(CTRa1 >= proche && CTRa1 <= arrive && rA == 5)
                {
                    STOPa1 = true;
                    AttMACHa2 = 0L;
                    AttMACHa1 = NbBcles - nbreBOUCLESa1;
                    TotAttBcleMACHa++;
                    TotAttBcleMACHa1++;
                }
                if(CTRa1 > arrive && STOPa1)
                {
                    ARRa1 = ARRa1 + 1;
                    STOPa1 = false;
                    TMoyMACHa1 = TotAttBcleMACHa1 / (long)ARRa1;
                    AttMACHa1 = 0L;
                }
                if(CTRa2 >= loin && CTRa2 < proche)
                    nbreBOUCLESa2 = NbBcles;
                if(CTRa2 >= proche && CTRa2 <= arrive && rA == 5)
                {
                    STOPa2 = true;
                    AttMACHa1 = 0L;
                    AttMACHa2 = NbBcles - nbreBOUCLESa2;
                    TotAttBcleMACHa++;
                    TotAttBcleMACHa2++;
                }
                if(CTRa2 > arrive && STOPa2)
                {
                    ARRa2 = ARRa2 + 1;
                    STOPa2 = false;
                    TMoyMACHa2 = TotAttBcleMACHa2 / (long)ARRa2;
                    AttMACHa2 = 0L;
                }
                if(CTRa3 >= loin && CTRa3 < proche)
                    nbreBOUCLESa3 = NbBcles;
                if(CTRa3 >= proche && CTRa3 <= arrive && rA == 5)
                {
                    STOPa3 = true;
                    AttMACHa4 = 0L;
                    AttMACHa3 = NbBcles - nbreBOUCLESa3;
                    TotAttBcleMACHa++;
                    TotAttBcleMACHa3++;
                }
                if(CTRa3 > arrive && STOPa3)
                {
                    ARRa3 = ARRa3 + 1;
                    STOPa3 = false;
                    TMoyMACHa3 = TotAttBcleMACHa3 / (long)ARRa3;
                    AttMACHa3 = 0L;
                }
                if(CTRa4 >= loin && CTRa4 < proche)
                    nbreBOUCLESa4 = NbBcles;
                if(CTRa4 >= proche && CTRa4 <= arrive && rA == 5)
                {
                    STOPa4 = true;
                    AttMACHa3 = 0L;
                    AttMACHa4 = NbBcles - nbreBOUCLESa4;
                    TotAttBcleMACHa++;
                    TotAttBcleMACHa4++;
                }
                if(CTRa4 > arrive && STOPa4)
                {
                    ARRa4 = ARRa4 + 1;
                    STOPa4 = false;
                    TMoyMACHa4 = TotAttBcleMACHa4 / (long)ARRa4;
                    AttMACHa4 = 0L;
                }
                AttMACHa = AttMACHa1 + AttMACHa2 + AttMACHa3 + AttMACHa4;
                ARRa = (ARRa1 + ARRa2 + ARRa3 + ARRa4) / 2;
                if(CTRc3 >= loin && CTRc3 < proche)
                    nbreBOUCLESc3 = NbBcles;
                if(CTRc3 >= proche && CTRc3 <= arrive && rC == 5)
                {
                    STOPc3 = true;
                    AttMACHc4 = 0L;
                    AttMACHc3 = NbBcles - nbreBOUCLESc3;
                    TotAttBcleMACHc++;
                    TotAttBcleMACHc3++;
                }
                if(CTRc3 > arrive && STOPc3)
                {
                    ARRc3 = ARRc3 + 1;
                    STOPc3 = false;
                    TMoyMACHc3 = TotAttBcleMACHc3 / (long)ARRc3;
                    AttMACHc3 = 0L;
                }
                if(CTRc4 >= loin && CTRc4 < proche)
                    nbreBOUCLESc4 = NbBcles;
                if(CTRc4 >= proche && CTRc4 <= arrive && rC == 5)
                {
                    STOPc4 = true;
                    AttMACHc3 = 0L;
                    AttMACHc4 = NbBcles - nbreBOUCLESc4;
                    TotAttBcleMACHc++;
                    TotAttBcleMACHc4++;
                }
                if(CTRc4 > arrive && STOPc4)
                {
                    ARRc4 = ARRc4 + 1;
                    STOPc4 = false;
                    TMoyMACHc4 = TotAttBcleMACHc4 / (long)ARRc4;
                    AttMACHc4 = 0L;
                }
                if(CTRc2 >= loin && CTRc2 < proche)
                    nbreBOUCLESc2 = NbBcles;
                if(CTRc2 >= proche && CTRc2 <= arrive && rC == 5)
                {
                    STOPc2 = true;
                    AttMACHc1 = 0L;
                    AttMACHc2 = NbBcles - nbreBOUCLESc2;
                    TotAttBcleMACHc++;
                    TotAttBcleMACHc2++;
                }
                if(CTRc2 > arrive && STOPc2)
                {
                    ARRc2 = ARRc2 + 1;
                    STOPc2 = false;
                    TMoyMACHc2 = TotAttBcleMACHc2 / (long)ARRc2;
                    AttMACHc2 = 0L;
                }
                if(CTRc1 >= loin && CTRc1 < proche)
                    nbreBOUCLESc1 = NbBcles;
                if(CTRc1 >= proche && CTRc1 <= arrive && rC == 5)
                {
                    STOPc1 = true;
                    AttMACHc2 = 0L;
                    AttMACHc1 = NbBcles - nbreBOUCLESc1;
                    TotAttBcleMACHc++;
                    TotAttBcleMACHc1++;
                }
                if(CTRc1 > arrive && STOPc1)
                {
                    ARRc1 = ARRc1 + 1;
                    STOPc1 = false;
                    TMoyMACHc1 = TotAttBcleMACHc1 / (long)ARRc1;
                    AttMACHc1 = 0L;
                }
                AttMACHc = AttMACHc3 + AttMACHc4 + AttMACHc2 + AttMACHc1;
                ARRc = (ARRc1 + ARRc2 + ARRc3 + ARRc4) / 2;
            }
        }
        if(!CESTFINI)
        {
            if(OPin[1].getOldLevel() == 5 && OPin[1].getLevel() == 0)
                CNTb++;
            OPin[1].OldLevel = OPin[1].getLevel();
        }
        if(!CESTFINI)
        {
            if(OPin[3].getOldLevel() == 5 && OPin[3].getLevel() == 0)
                CNTd++;
            OPin[3].OldLevel = OPin[3].getLevel();
        }
        if(!CESTFINI)
        {
            if(OPin[0].getOldLevel() == 5 && OPin[0].getLevel() == 0)
                CNTa++;
            OPin[0].OldLevel = OPin[0].getLevel();
        }
        if(!CESTFINI)
        {
            if(OPin[2].getOldLevel() == 5 && OPin[2].getLevel() == 0)
                CNTc++;
            OPin[2].OldLevel = OPin[2].getLevel();
        }
        rA = IPin[0].getLevel();
        oA = IPin[1].getLevel();
        vA = IPin[2].getLevel();
        rB = IPin[3].getLevel();
        oB = IPin[4].getLevel();
        vB = IPin[5].getLevel();
        rC = IPin[6].getLevel();
        oC = IPin[7].getLevel();
        vC = IPin[8].getLevel();
        rD = IPin[9].getLevel();
        oD = IPin[10].getLevel();
        vD = IPin[11].getLevel();
        if(SwitchClosed)
        {
            Click = true;
            NbBcles = 0L;
            Bilan = false;
            Bruitage = false;
            Acoll = false;
            Ccoll = false;
            AdnsX = false;
            BdnsX = false;
            CdnsX = false;
            DdnsXdroite = false;
            DdnsXBas = false;
            DdnsXBas = false;
            DprndA = false;
            AprndA = false;
            DprndC = false;
            CprndC = false;
            BprndC = false;
            PbNO = false;
            PbSU = false;
            PbSO = false;
            PbSU = false;
            TotAttBcleMACHa1 = 0L;
            nbreBOUCLESa1 = 0L;
            ARRa1 = 0;
            TotAttBcleMACHa2 = 0L;
            nbreBOUCLESa2 = 0L;
            ARRa2 = 0;
            TotAttBcleMACHa3 = 0L;
            nbreBOUCLESa3 = 0L;
            ARRa3 = 0;
            TotAttBcleMACHa3 = 0L;
            nbreBOUCLESa3 = 0L;
            ARRa3 = 0;
            TotAttBcleMACHb = 0L;
            nbreBOUCLESb = 0L;
            ARRb = 0;
            TotAttBcleMACHc1 = 0L;
            nbreBOUCLESc1 = 0L;
            ARRc1 = 0;
            TotAttBcleMACHc2 = 0L;
            nbreBOUCLESc2 = 0L;
            ARRc2 = 0;
            TotAttBcleMACHc3 = 0L;
            nbreBOUCLESc3 = 0L;
            ARRc3 = 0;
            TotAttBcleMACHc4 = 0L;
            nbreBOUCLESc4 = 0L;
            ARRc4 = 0;
            TotAttBcleMACHd = 0L;
            nbreBOUCLESd = 0L;
            ARRd = 0;
            CNTa = 0;
            CNTb = 0;
            CNTc = 0;
            CNTd = 0;
            TMoyMACHa1 = 0L;
            TMoyMACHa2 = 0L;
            TMoyMACHa3 = 0L;
            TMoyMACHa4 = 0L;
            TMoyMACHc4 = 0L;
            TMoyMACHc3 = 0L;
            TMoyMACHc2 = 0L;
            TMoyMACHc1 = 0L;
            TMoyMACHa = 0L;
            TMoyMACHb = 0L;
            TMoyMACHc = 0L;
            TMoyMACHd = 0L;
        }
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFont(Font.font("Serif", 10));
        g.setFill(Color.rgb(200, 200, 200));
        DrawWithOffset.fillRect(g,(l + 4) * k, (i1 + 2) * k, 20 * k, 21 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,(l + 4) * k, (i1 + 2) * k, 20 * k, 21 * k);
        g.setFill(MyColor.gray);
        DrawWithOffset.fillRect(g,(l + 4) * k, (i1 + 2) * k, 8 * k, 7 * k);
        DrawWithOffset.fillRect(g,(l + 16) * k, (i1 + 2) * k, 8 * k, 7 * k);
        DrawWithOffset.fillRect(g,(l + 4) * k, (i1 + 16) * k, 8 * k, 7 * k);
        DrawWithOffset.fillRect(g,(l + 16) * k, (i1 + 16) * k, 8 * k, 7 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,(l + 4) * k, (i1 + 2) * k, 8 * k, 7 * k);
        DrawWithOffset.strokeRect(g,(l + 16) * k, (i1 + 2) * k, 8 * k, 7 * k);
        DrawWithOffset.strokeRect(g,(l + 4) * k, (i1 + 16) * k, 8 * k, 7 * k);
        DrawWithOffset.strokeRect(g,(l + 16) * k, (i1 + 16) * k, 8 * k, 7 * k);
        DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 2) * k, (l + 4) * k, (i1 + 23) * k);
        g.setStroke(MyColor.white);
        byte byte0 = 4;
        DrawWithOffset.strokeLine(g,(l + 16) * k + byte0, (i1 + 9) * k + 5, (l + 16) * k + byte0, (i1 + 12) * k);
        DrawWithOffset.strokeLine(g,(l + 15) * k - 6, (i1 + 16) * k + byte0, (l + 16) * k - 2, (i1 + 16) * k + byte0);
        DrawWithOffset.strokeLine(g,(l + 12) * k - byte0, (i1 + 13) * k, (l + 12) * k - byte0, (i1 + 16) * k - 5);
        DrawWithOffset.strokeLine(g,(l + 12) * k + 2, (i1 + 9) * k - byte0, (l + 14) * k - 2, (i1 + 9) * k - byte0);
        g.setStroke(MyColor.white);
        DrawWithOffset.strokeLine(g,(l + 4) * k + 1, (i1 + 12) * k + 4, (l + 24) * k - 1, (i1 + 12) * k + 4);
        g.setFill(MyColor.darkGray);
        DrawWithOffset.fillOval(g,(l + 5) * k, (i1 + 6) * k, k * 2 - 4, k * 2 - 4);
        g.setFill(MyColor.gray);
        DrawWithOffset.fillOval(g,(l + 5) * k + 3, (i1 + 6) * k + 3, k * 1 - 1, k * 1 - 1);
        g.setFill(MyColor.darkGray);
        DrawWithOffset.fillRect(g,(l + 6) * k + 2, (i1 + 7) * k - 3, k * 2, k * 1 - 5);
        DrawWithOffset.fillRect(g,(l + 7) * k + 2, (i1 + 7) * k - 3, k * 1 - 6, k * 1);
        DrawWithOffset.fillRect(g,(l + 8) * k - 2, (i1 + 7) * k - 3, k * 1 - 6, k * 1);
        g.setStroke(MyColor.red);
        g.setFill(MyColor.red);
        DrawWithOffset.fillRect(g,(l + 14) * k + 3, (i1 + 7) * k + 5, 1 * k + 1, 1 * k + 1);
        DrawWithOffset.strokeOval(g,(l + 14) * k + 2, (i1 + 7) * k + 4, 1 * k + 2, 1 * k + 2);
        g.setFill(MyColor.white);
        DrawWithOffset.fillRect(g,(l + 14) * k + 4, (i1 + 7) * k + 8, 1 * k - 1, 0 * k + 3);
        g.setFill(MyColor.white);
        DrawWithOffset.fillRect(g,(l + 16) * k + 3, (i1 + 19) * k + 3, 1 * k, 1 * k + 1);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeOval(g,(l + 16) * k + 2, (i1 + 19) * k + 2, 1 * k + 2, 1 * k + 2);
        DrawWithOffset.strokeOval(g,(l + 16) * k + 1, (i1 + 19) * k + 1, 1 * k + 4, 1 * k + 4);
        g.setFill(MyColor.black);
        DrawWithOffset.fillRect(g,(l + 16) * k + 4, (i1 + 19) * k + 5, 1 * k - 2, 0 * k + 2);
        DrawWithOffset.fillRect(g,(l + 16) * k + 8, (i1 + 19) * k + 5, 0 * k + 2, 1 * k - 2);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeLine(g,(l + 16) * k + 3, (i1 + 19) * k + 3, (l + 16) * k + 11, (i1 + 19) * k + 11);
        DrawWithOffset.strokeLine(g,(l + 16) * k + 4, (i1 + 19) * k + 3, (l + 16) * k + 12, (i1 + 19) * k + 11);
        g.setFill(MyColor.white);
        DrawWithOffset.fillRect(g,(l + 6) * k + 2, (i1 + 16) * k + 3, 1 * k, 1 * k + 1);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeOval(g,(l + 6) * k, (i1 + 16) * k + 2, 1 * k + 2, 1 * k + 2);
        DrawWithOffset.strokeOval(g,(l + 6) * k - 1, (i1 + 16) * k + 1, 1 * k + 4, 1 * k + 4);
        g.setFill(MyColor.black);
        DrawWithOffset.fillRect(g,(l + 6) * k + 2, (i1 + 16) * k + 5, 1 * k - 2, 0 * k + 2);
        DrawWithOffset.fillRect(g,(l + 6) * k + 6, (i1 + 16) * k + 5, 0 * k + 2, 1 * k - 2);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeLine(g,(l + 6) * k + 9, (i1 + 16) * k + 3, (l + 6) * k + 1, (i1 + 16) * k + 11);
        DrawWithOffset.strokeLine(g,(l + 6) * k + 10, (i1 + 16) * k + 3, (l + 6) * k + 2, (i1 + 16) * k + 11);
        g.setFill(MyColor.white);
        DrawWithOffset.fillRect(g,(l + 19) * k + 4, (i1 + 7) * k + 5, 1 * k, 1 * k + 1);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeOval(g,(l + 19) * k + 2, (i1 + 7) * k + 4, 1 * k + 2, 1 * k + 2);
        DrawWithOffset.strokeOval(g,(l + 19) * k + 1, (i1 + 7) * k + 3, 1 * k + 4, 1 * k + 4);
        g.setFill(MyColor.black);
        DrawWithOffset.fillRect(g,(l + 19) * k + 6, (i1 + 7) * k + 11, 1 * k - 3, 0 * k + 2);
        DrawWithOffset.fillRect(g,(l + 19) * k + 5, (i1 + 7) * k + 6, 0 * k + 2, 1 * k - 2);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeLine(g,(l + 19) * k + 11, (i1 + 7) * k + 5, (l + 19) * k + 2, (i1 + 7) * k + 13);
        DrawWithOffset.strokeLine(g,(l + 19) * k + 12, (i1 + 7) * k + 5, (l + 19) * k + 3, (i1 + 7) * k + 13);
        if(rA == 5)
            g.setFill(MyColor.red);
        else
        if(rA == 0)
            g.setFill(MyColor.darkGray);
        else
            g.setFill(MyColor.white);
        DrawWithOffset.fillOval(g,(l + 16) * k, (i1 + 8) * k, 1 * k, 1 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,(l + 16) * k, (i1 + 8) * k, 1 * k, 1 * k);
        if(oA == 5)
            g.setFill(MyColor.orange);
        else
        if(oA == 0)
            g.setFill(MyColor.darkGray);
        else
            g.setFill(MyColor.white);
        DrawWithOffset.fillOval(g,(l + 17) * k, (i1 + 8) * k, 1 * k, 1 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,(l + 17) * k, (i1 + 8) * k, 1 * k, 1 * k);
        if(vA == 5)
            g.setFill(MyColor.green);
        else
        if(vA == 0)
            g.setFill(MyColor.darkGray);
        else
            g.setFill(MyColor.white);
        DrawWithOffset.fillOval(g,(l + 18) * k, (i1 + 8) * k, 1 * k, 1 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,(l + 18) * k, (i1 + 8) * k, 1 * k, 1 * k);
        if(rB == 5)
            g.setFill(MyColor.red);
        else
        if(rB == 0)
            g.setFill(MyColor.darkGray);
        else
            g.setFill(MyColor.white);
        DrawWithOffset.fillOval(g,(l + 16) * k, (i1 + 16) * k, 1 * k, 1 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,(l + 16) * k, (i1 + 16) * k, 1 * k, 1 * k);
        if(oB == 5)
            g.setFill(MyColor.orange);
        else
        if(oB == 0)
            g.setFill(MyColor.darkGray);
        else
            g.setFill(MyColor.white);
        DrawWithOffset.fillOval(g,(l + 16) * k, (i1 + 17) * k, 1 * k, 1 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,(l + 16) * k, (i1 + 17) * k, 1 * k, 1 * k);
        if(vB == 5)
            g.setFill(MyColor.green);
        else
        if(vB == 0)
            g.setFill(MyColor.darkGray);
        else
            g.setFill(MyColor.white);
        DrawWithOffset.fillOval(g,(l + 16) * k, (i1 + 18) * k, 1 * k, 1 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,(l + 16) * k, (i1 + 18) * k, 1 * k, 1 * k);
        if(rC == 5)
            g.setFill(MyColor.red);
        else
        if(rC == 0)
            g.setFill(MyColor.darkGray);
        else
            g.setFill(MyColor.white);
        DrawWithOffset.fillOval(g,(l + 11) * k, (i1 + 16) * k, 1 * k, 1 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,(l + 11) * k, (i1 + 16) * k, 1 * k, 1 * k);
        if(oC == 5)
            g.setFill(MyColor.orange);
        else
        if(oC == 0)
            g.setFill(MyColor.darkGray);
        else
            g.setFill(MyColor.white);
        DrawWithOffset.fillOval(g,(l + 10) * k, (i1 + 16) * k, 1 * k, 1 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,(l + 10) * k, (i1 + 16) * k, 1 * k, 1 * k);
        if(vC == 5)
            g.setFill(MyColor.green);
        else
        if(vC == 0)
            g.setFill(MyColor.darkGray);
        else
            g.setFill(MyColor.white);
        DrawWithOffset.fillOval(g,(l + 9) * k, (i1 + 16) * k, 1 * k, 1 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,(l + 9) * k, (i1 + 16) * k, 1 * k, 1 * k);
        if(rD == 5)
            g.setFill(MyColor.red);
        else
        if(rD == 0)
            g.setFill(MyColor.darkGray);
        else
            g.setFill(MyColor.white);
        DrawWithOffset.fillOval(g,(l + 11) * k, (i1 + 8) * k, 1 * k, 1 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,(l + 11) * k, (i1 + 8) * k, 1 * k, 1 * k);
        if(oD == 5)
            g.setFill(MyColor.orange);
        else
        if(oD == 0)
            g.setFill(MyColor.darkGray);
        else
            g.setFill(MyColor.white);
        DrawWithOffset.fillOval(g,(l + 11) * k, (i1 + 7) * k, 1 * k, 1 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,(l + 11) * k, (i1 + 7) * k, 1 * k, 1 * k);
        if(vD == 5)
            g.setFill(MyColor.green);
        else
        if(vD == 0)
            g.setFill(MyColor.darkGray);
        else
            g.setFill(MyColor.white);
        DrawWithOffset.fillOval(g,(l + 11) * k, (i1 + 6) * k, 1 * k, 1 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,(l + 11) * k, (i1 + 6) * k, 1 * k, 1 * k);
        g.setFill(MyColor.red);
        if(PbNO)
            DrawWithOffset.fillRect(g,(l + 12) * k + 1, (i1 + 9) * k + 1, 2 * k - 1, (3 * k + 5) - 2);
        if(PbSO)
            DrawWithOffset.fillRect(g,(l + 12) * k + 1, (i1 + 12) * k + 5, 2 * k - 1, (3 * k + 5) - 2);
        if(PbSU)
            DrawWithOffset.fillRect(g,(l + 12) * k + 1, (i1 + 12) * k + 5, 4 * k - 1, (3 * k + 5) - 2);
        if(Acoll)
            DrawWithOffset.fillRect(g,(l + 4) * k + 1, (i1 + 9) * k + 1, 8 * k, (3 * k + 5) - 2);
        if(Ccoll)
            DrawWithOffset.fillRect(g,(l + 16) * k, (i1 + 12) * k + 5, 8 * k, (3 * k + 5) - 2);
        if(Bruitage && !Bilan)
        {
            DigSim.sound.play();
            Bruitage = false;
        }
        if(VOITb)
        {
            int j1 = CTRb;
            if(VITb == 1)
                g.setFill(MyColor.black);
            else
                g.setFill(MyColor.blue);
            if(j1 < 50)
                DrawWithOffset.fillOval(g,(l + 15) * k - 6, ((i1 + 2) * k - j1) + 160, 1 * k + 2, 1 * k + 2);
            else
            if(j1 >= 50 && j1 < 72)
                DrawWithOffset.fillOval(g,((l + 15) * k + j1) - 50 - 6, ((i1 + 2) * k - j1) + 160, 1 * k + 2, 1 * k + 2);
            else
                DrawWithOffset.fillOval(g,((l + 15) * k + j1) - 50 - 6, ((i1 + 2) * k + 100) - 12, 1 * k + 2, 1 * k + 2);
            j1 -= 3;
            if(VITb == 1)
                g.setFill(MyColor.white);
            else
                g.setFill(MyColor.red);
            if(j1 < 50)
                DrawWithOffset.fillOval(g,(l + 15) * k - 6, ((i1 + 2) * k - j1) + 160, 1 * k + 2, 1 * k + 2);
            else
            if(j1 >= 50 && j1 < 72)
                DrawWithOffset.fillOval(g,((l + 15) * k + j1) - 50 - 6, ((i1 + 2) * k - j1) + 160, 1 * k + 2, 1 * k + 2);
            else
                DrawWithOffset.fillOval(g,((l + 15) * k + j1) - 50 - 6, ((i1 + 2) * k + 100) - 12, 1 * k + 2, 1 * k + 2);
            j1 -= 3;
            if(VITb == 1)
                g.setFill(MyColor.black);
            else
                g.setFill(MyColor.blue);
            if(j1 < 50)
                DrawWithOffset.fillOval(g,(l + 15) * k - 6, ((i1 + 2) * k - j1) + 160, 1 * k + 2, 1 * k + 2);
            else
            if(j1 >= 50 && j1 < 72)
                DrawWithOffset.fillOval(g,((l + 15) * k + j1) - 50 - 6, ((i1 + 2) * k - j1) + 160, 1 * k + 2, 1 * k + 2);
            else
                DrawWithOffset.fillOval(g,((l + 15) * k + j1) - 50 - 6, ((i1 + 2) * k + 100) - 12, 1 * k + 2, 1 * k + 2);
            j1 -= 3;
            if(j1 < 50)
                DrawWithOffset.fillOval(g,(l + 15) * k - 6, ((i1 + 2) * k - j1) + 160, 1 * k + 2, 1 * k + 2);
            else
            if(j1 >= 50 && j1 < 72)
                DrawWithOffset.fillOval(g,((l + 15) * k + j1) - 50 - 6, ((i1 + 2) * k - j1) + 160, 1 * k + 2, 1 * k + 2);
            else
                DrawWithOffset.fillOval(g,((l + 15) * k + j1) - 50 - 6, ((i1 + 2) * k + 100) - 12, 1 * k + 2, 1 * k + 2);
            j1 -= 3;
            if(j1 < 50)
                DrawWithOffset.fillOval(g,(l + 15) * k - 6, ((i1 + 2) * k - j1) + 160, 1 * k + 2, 1 * k + 2);
            else
            if(j1 >= 50 && j1 < 72)
                DrawWithOffset.fillOval(g,((l + 15) * k + j1) - 50 - 6, ((i1 + 2) * k - j1) + 160, 1 * k + 2, 1 * k + 2);
            else
                DrawWithOffset.fillOval(g,((l + 15) * k + j1) - 50 - 6, ((i1 + 2) * k + 100) - 12, 1 * k + 2, 1 * k + 2);
            j1 -= 3;
            if(j1 < 50)
                DrawWithOffset.fillOval(g,(l + 15) * k - 6, ((i1 + 2) * k - j1) + 160, 1 * k + 2, 1 * k + 2);
            else
            if(j1 >= 50 && j1 < 72)
                DrawWithOffset.fillOval(g,((l + 15) * k + j1) - 50 - 6, ((i1 + 2) * k - j1) + 160, 1 * k + 2, 1 * k + 2);
            else
                DrawWithOffset.fillOval(g,((l + 15) * k + j1) - 50 - 6, ((i1 + 2) * k + 100) - 12, 1 * k + 2, 1 * k + 2);
            j1 -= 3;
            if(j1 < 50)
                DrawWithOffset.fillOval(g,(l + 15) * k - 6, ((i1 + 2) * k - j1) + 160, 1 * k + 2, 1 * k + 2);
            else
            if(j1 >= 50 && j1 < 72)
                DrawWithOffset.fillOval(g,((l + 15) * k + j1) - 50 - 6, ((i1 + 2) * k - j1) + 160, 1 * k + 2, 1 * k + 2);
            else
                DrawWithOffset.fillOval(g,((l + 15) * k + j1) - 50 - 6, ((i1 + 2) * k + 100) - 12, 1 * k + 2, 1 * k + 2);
            if(CTRb < 71 && NbBcles % 13L > 7L)
            {
                g.setFill(MyColor.yellow);
                DrawWithOffset.fillOval(g,(l + 15) * k, ((i1 + 2) * k - j1) + 164, 0 * k + 4, 0 * k + 4);
            }
        }
        if(VOITd)
            if(DIRECTd == 0)
            {
                int k1 = CTRd;
                if(VITd == 1)
                    g.setFill(MyColor.black);
                else
                    g.setFill(MyColor.blue);
                DrawWithOffset.fillOval(g,(l + 13) * k - 5, (i1 + 2) * k + k1, 1 * k + 2, 1 * k + 2);
                if(VITd == 1)
                    g.setFill(MyColor.yellow);
                else
                    g.setFill(MyColor.red);
                DrawWithOffset.fillOval(g,(l + 13) * k - 5, ((i1 + 2) * k + k1) - 3, 1 * k + 2, 1 * k + 2);
                if(VITd == 1)
                    g.setFill(MyColor.black);
                else
                    g.setFill(MyColor.blue);
                DrawWithOffset.fillOval(g,(l + 13) * k - 5, ((i1 + 2) * k + k1) - 6, 1 * k + 2, 1 * k + 2);
                DrawWithOffset.fillOval(g,(l + 13) * k - 5, ((i1 + 2) * k + k1) - 9, 1 * k + 2, 1 * k + 2);
                DrawWithOffset.fillOval(g,(l + 13) * k - 5, ((i1 + 2) * k + k1) - 12, 1 * k + 2, 1 * k + 2);
                DrawWithOffset.fillOval(g,(l + 13) * k - 5, ((i1 + 2) * k + k1) - 15, 1 * k + 2, 1 * k + 2);
                DrawWithOffset.fillOval(g,(l + 13) * k - 5, ((i1 + 2) * k + k1) - 18, 1 * k + 2, 1 * k + 2);
            } else
            if(DIRECTd == 1)
            {
                int l1 = CTRd;
                if(VITd == 1)
                    g.setFill(MyColor.black);
                else
                    g.setFill(MyColor.blue);
                if(l1 < 90)
                    DrawWithOffset.fillOval(g,(l + 13) * k - 5, (i1 + 2) * k + CTRd, 1 * k + 2, 1 * k + 2);
                else
                if(l1 >= 90 && l1 < 100)
                    DrawWithOffset.fillOval(g,((l + 13) * k + l1) - 95, (i1 + 2) * k + l1, 1 * k + 2, 1 * k + 2);
                else
                    DrawWithOffset.fillOval(g,((l + 13) * k + l1) - 95, (i1 + 2) * k + 100, 1 * k + 2, 1 * k + 2);
                l1 -= 3;
                if(VITd == 1)
                    g.setFill(MyColor.white);
                else
                    g.setFill(MyColor.red);
                if(l1 < 90)
                    DrawWithOffset.fillOval(g,(l + 13) * k - 5, (i1 + 2) * k + l1, 1 * k + 2, 1 * k + 2);
                else
                if(l1 >= 90 && l1 < 100)
                    DrawWithOffset.fillOval(g,((l + 13) * k + l1) - 95, (i1 + 2) * k + l1, 1 * k + 2, 1 * k + 2);
                else
                    DrawWithOffset.fillOval(g,((l + 13) * k + l1) - 95, (i1 + 2) * k + 100, 1 * k + 2, 1 * k + 2);
                l1 -= 3;
                if(VITd == 1)
                    g.setFill(MyColor.black);
                else
                    g.setFill(MyColor.blue);
                if(l1 < 90)
                    DrawWithOffset.fillOval(g,(l + 13) * k - 5, (i1 + 2) * k + l1, 1 * k + 2, 1 * k + 2);
                else
                if(l1 >= 90 && l1 < 100)
                    DrawWithOffset.fillOval(g,((l + 13) * k + l1) - 95, (i1 + 2) * k + l1, 1 * k + 2, 1 * k + 2);
                else
                    DrawWithOffset.fillOval(g,((l + 13) * k + l1) - 95, (i1 + 2) * k + 100, 1 * k + 2, 1 * k + 2);
                l1 -= 3;
                if(l1 < 90)
                    DrawWithOffset.fillOval(g,(l + 13) * k - 5, (i1 + 2) * k + l1, 1 * k + 2, 1 * k + 2);
                else
                if(l1 >= 90 && l1 < 100)
                    DrawWithOffset.fillOval(g,((l + 13) * k + l1) - 95, (i1 + 2) * k + l1, 1 * k + 2, 1 * k + 2);
                else
                    DrawWithOffset.fillOval(g,((l + 13) * k + l1) - 95, (i1 + 2) * k + 100, 1 * k + 2, 1 * k + 2);
                l1 -= 3;
                if(l1 < 90)
                    DrawWithOffset.fillOval(g,(l + 13) * k - 5, (i1 + 2) * k + l1, 1 * k + 2, 1 * k + 2);
                else
                if(l1 >= 90 && l1 < 100)
                    DrawWithOffset.fillOval(g,((l + 13) * k + l1) - 95, (i1 + 2) * k + l1, 1 * k + 2, 1 * k + 2);
                else
                    DrawWithOffset.fillOval(g,((l + 13) * k + l1) - 95, (i1 + 2) * k + 100, 1 * k + 2, 1 * k + 2);
                l1 -= 3;
                if(l1 < 90)
                    DrawWithOffset.fillOval(g,(l + 13) * k - 5, (i1 + 2) * k + l1, 1 * k + 2, 1 * k + 2);
                else
                if(l1 >= 90 && l1 < 100)
                    DrawWithOffset.fillOval(g,((l + 13) * k + l1) - 95, (i1 + 2) * k + l1, 1 * k + 2, 1 * k + 2);
                else
                    DrawWithOffset.fillOval(g,((l + 13) * k + l1) - 95, (i1 + 2) * k + 100, 1 * k + 2, 1 * k + 2);
                l1 -= 3;
                if(l1 < 90)
                    DrawWithOffset.fillOval(g,(l + 13) * k - 5, (i1 + 2) * k + l1, 1 * k + 2, 1 * k + 2);
                else
                if(l1 >= 90 && l1 < 100)
                    DrawWithOffset.fillOval(g,((l + 13) * k + l1) - 95, (i1 + 2) * k + l1, 1 * k + 2, 1 * k + 2);
                else
                    DrawWithOffset.fillOval(g,((l + 13) * k + l1) - 95, (i1 + 2) * k + 100, 1 * k + 2, 1 * k + 2);
                if(CTRd < 105 && NbBcles % 11L > 6L)
                {
                    g.setFill(MyColor.yellow);
                    DrawWithOffset.fillOval(g,(l + 13) * k, ((i1 + 2) * k + CTRd) - 16, 0 * k + 4, 0 * k + 4);
                }
            } else
            if(DIRECTd == 2)
            {
                int i2 = CTRd;
                if(i2 < 115)
                {
                    if(VITd == 1)
                        g.setFill(MyColor.black);
                    else
                        g.setFill(MyColor.blue);
                    if(i2 < 50)
                        DrawWithOffset.fillOval(g,(l + 13) * k - 5, (i1 + 2) * k + i2, 1 * k + 2, 1 * k + 2);
                    else
                    if(i2 >= 50 && i2 < 60)
                        DrawWithOffset.fillOval(g,((l + 13) * k - i2) + 45, (i1 + 2) * k + i2, 1 * k + 2, 1 * k + 2);
                    else
                        DrawWithOffset.fillOval(g,((l + 13) * k - i2) + 45, (i1 + 2) * k + 60, 1 * k + 2, 1 * k + 2);
                    i2 -= 3;
                    if(VITd == 1)
                        g.setFill(MyColor.white);
                    else
                        g.setFill(MyColor.red);
                    if(i2 < 50)
                        DrawWithOffset.fillOval(g,(l + 13) * k - 5, (i1 + 2) * k + i2, 1 * k + 2, 1 * k + 2);
                    else
                    if(i2 >= 50 && i2 < 60)
                        DrawWithOffset.fillOval(g,((l + 13) * k - i2) + 45, (i1 + 2) * k + i2, 1 * k + 2, 1 * k + 2);
                    else
                        DrawWithOffset.fillOval(g,((l + 13) * k - i2) + 45, (i1 + 2) * k + 60, 1 * k + 2, 1 * k + 2);
                }
                i2 -= 3;
                if(VITd == 1)
                    g.setFill(MyColor.black);
                else
                    g.setFill(MyColor.blue);
                if(i2 < 50)
                    DrawWithOffset.fillOval(g,(l + 13) * k - 5, (i1 + 2) * k + i2, 1 * k + 2, 1 * k + 2);
                else
                if(i2 >= 50 && i2 < 60)
                    DrawWithOffset.fillOval(g,((l + 13) * k - i2) + 45, (i1 + 2) * k + i2, 1 * k + 2, 1 * k + 2);
                else
                    DrawWithOffset.fillOval(g,((l + 13) * k - i2) + 45, (i1 + 2) * k + 60, 1 * k + 2, 1 * k + 2);
                i2 -= 3;
                if(i2 < 50)
                    DrawWithOffset.fillOval(g,(l + 13) * k - 5, (i1 + 2) * k + i2, 1 * k + 2, 1 * k + 2);
                else
                if(i2 >= 50 && i2 < 60)
                    DrawWithOffset.fillOval(g,((l + 13) * k - i2) + 45, (i1 + 2) * k + i2, 1 * k + 2, 1 * k + 2);
                else
                    DrawWithOffset.fillOval(g,((l + 13) * k - i2) + 45, (i1 + 2) * k + 60, 1 * k + 2, 1 * k + 2);
                i2 -= 3;
                if(i2 < 50)
                    DrawWithOffset.fillOval(g,(l + 13) * k - 5, (i1 + 2) * k + i2, 1 * k + 2, 1 * k + 2);
                else
                if(i2 >= 50 && i2 < 60)
                    DrawWithOffset.fillOval(g,((l + 13) * k - i2) + 45, (i1 + 2) * k + i2, 1 * k + 2, 1 * k + 2);
                else
                    DrawWithOffset.fillOval(g,((l + 13) * k - i2) + 45, (i1 + 2) * k + 60, 1 * k + 2, 1 * k + 2);
                i2 -= 3;
                if(i2 < 50)
                    DrawWithOffset.fillOval(g,(l + 13) * k - 5, (i1 + 2) * k + i2, 1 * k + 2, 1 * k + 2);
                else
                if(i2 >= 50 && i2 < 60)
                    DrawWithOffset.fillOval(g,((l + 13) * k - i2) + 45, (i1 + 2) * k + i2, 1 * k + 2, 1 * k + 2);
                else
                    DrawWithOffset.fillOval(g,((l + 13) * k - i2) + 45, (i1 + 2) * k + 60, 1 * k + 2, 1 * k + 2);
                i2 -= 3;
                if(i2 < 50)
                    DrawWithOffset.fillOval(g,(l + 13) * k - 5, (i1 + 2) * k + i2, 1 * k + 2, 1 * k + 2);
                else
                if(i2 >= 50 && i2 < 60)
                    DrawWithOffset.fillOval(g,((l + 13) * k - i2) + 45, (i1 + 2) * k + i2, 1 * k + 2, 1 * k + 2);
                else
                    DrawWithOffset.fillOval(g,((l + 13) * k - i2) + 45, (i1 + 2) * k + 60, 1 * k + 2, 1 * k + 2);
                if(CTRd < 71 && NbBcles % 11L > 6L)
                {
                    g.setFill(MyColor.yellow);
                    DrawWithOffset.fillOval(g,(l + 13) * k - 4, ((i1 + 2) * k + CTRd) - 16, 0 * k + 4, 0 * k + 4);
                }
            }
        if(VOITa1)
        {
            if(VITa1 == 1)
                g.setFill(MyColor.red);
            else
                g.setFill(MyColor.black);
            DrawWithOffset.fillRect(g,(l + 22) * k - CTRa1, (i1 + 9) * k + 7, 2 * k, 0 * k + 5);
            DrawWithOffset.fillRect(g,((l + 22) * k - CTRa1) + 4, (i1 + 9) * k + 3, 2 * k - 4, 0 * k + 6);
            if(VITa1 == 1)
                g.setFill(MyColor.black);
            else
                g.setFill(MyColor.blue);
            DrawWithOffset.fillOval(g,((l + 22) * k - CTRa1) + 2, (i1 + 9) * k + 10, 1 * k - 4, 1 * k - 4);
            DrawWithOffset.fillOval(g,((l + 22) * k - CTRa1) + 11, (i1 + 9) * k + 10, 1 * k - 4, 1 * k - 4);
            g.setFill(MyColor.white);
            DrawWithOffset.fillRect(g,((l + 22) * k - CTRa1) + 5, (i1 + 9) * k + 3 + 1, 0 * k + 4, 0 * k + 3);
            DrawWithOffset.fillRect(g,((l + 22) * k - CTRa1) + 10, (i1 + 9) * k + 3 + 1, 0 * k + 4, 0 * k + 3);
        }
        if(VOITa2)
        {
            if(VITa2 == 1)
                g.setFill(MyColor.black);
            else
                g.setFill(MyColor.blue);
            DrawWithOffset.fillRect(g,(l + 22) * k - CTRa2, (i1 + 9) * k + 7, 2 * k, 0 * k + 5);
            DrawWithOffset.fillRect(g,((l + 22) * k - CTRa2) + 4, (i1 + 9) * k + 2, 2 * k - 4, 0 * k + 6);
            if(CAMIONa2 == 1)
                DrawWithOffset.fillRect(g,(l + 24) * k - CTRa2, (i1 + 9) * k + 2, 1 * k - 4, 1 * k + 2);
            if(VITa2 == 1)
                g.setFill(MyColor.red);
            else
                g.setFill(MyColor.darkGray);
            DrawWithOffset.fillOval(g,((l + 22) * k - CTRa2) + 2, (i1 + 9) * k + 10, 1 * k - 4, 1 * k - 4);
            DrawWithOffset.fillOval(g,((l + 22) * k - CTRa2) + 11, (i1 + 9) * k + 10, 1 * k - 4, 1 * k - 4);
            if(CAMIONa2 == 1)
                DrawWithOffset.fillOval(g,((l + 22) * k - CTRa2) + 16, (i1 + 9) * k + 10, 1 * k - 4, 1 * k - 4);
            g.setFill(MyColor.white);
            DrawWithOffset.fillRect(g,((l + 22) * k - CTRa2) + 5, (i1 + 9) * k + 3, 0 * k + 4, 0 * k + 4);
            if(CAMIONa2 == 1)
            {
                DrawWithOffset.fillRect(g,((l + 22) * k - CTRa2) + 10, (i1 + 9) * k + 3, 0 * k + 4, 0 * k + 4);
                DrawWithOffset.fillRect(g,((l + 22) * k - CTRa2) + 10 + 5, (i1 + 9) * k + 3, 0 * k + 4, 0 * k + 4);
            }
        }
        if(VOITa3)
        {
            if(VITa3 == 2)
                g.setFill(MyColor.red);
            else
            if(VITa3 == 1)
                g.setFill(MyColor.darkGray);
            else
                g.setFill(MyColor.cyan);
            DrawWithOffset.fillRect(g,(l + 22) * k - CTRa3, (i1 + 11) * k + 4, 2 * k, 0 * k + 5);
            DrawWithOffset.fillRect(g,((l + 22) * k - CTRa3) + 4, (i1 + 11) * k, 2 * k - 4, 0 * k + 6);
            if(VITa3 == 2)
                g.setFill(MyColor.blue);
            else
            if(VITa3 == 1)
                g.setFill(MyColor.red);
            else
                g.setFill(MyColor.black);
            DrawWithOffset.fillOval(g,((l + 22) * k - CTRa3) + 2, (i1 + 11) * k + 7, 1 * k - 4, 1 * k - 4);
            DrawWithOffset.fillOval(g,((l + 22) * k - CTRa3) + 11, (i1 + 11) * k + 7, 1 * k - 4, 1 * k - 4);
            g.setFill(MyColor.white);
            DrawWithOffset.fillRect(g,((l + 22) * k - CTRa3) + 5, (i1 + 11) * k + 1, 0 * k + 4, 0 * k + 3);
            DrawWithOffset.fillRect(g,((l + 22) * k - CTRa3) + 10, (i1 + 11) * k + 1, 0 * k + 4, 0 * k + 3);
        }
        if(VOITa4)
        {
            if(VITa4 == 2)
                g.setFill(MyColor.black);
            else
            if(VITa4 == 1)
                g.setFill(MyColor.orange);
            else
                g.setFill(MyColor.magenta);
            DrawWithOffset.fillRect(g,(l + 22) * k - CTRa4, (i1 + 11) * k + 4, 2 * k, 0 * k + 5);
            DrawWithOffset.fillRect(g,((l + 22) * k - CTRa4) + 4, (i1 + 11) * k - 1, 2 * k - 4, 0 * k + 6);
            if(VITa4 == 2)
                g.setFill(MyColor.red);
            else
            if(VITa4 == 1)
                g.setFill(MyColor.blue);
            else
                g.setFill(MyColor.black);
            DrawWithOffset.fillOval(g,((l + 22) * k - CTRa4) + 2, (i1 + 11) * k + 7, 1 * k - 4, 1 * k - 4);
            DrawWithOffset.fillOval(g,((l + 22) * k - CTRa4) + 11, (i1 + 11) * k + 7, 1 * k - 4, 1 * k - 4);
            g.setFill(MyColor.white);
            DrawWithOffset.fillRect(g,((l + 22) * k - CTRa4) + 5, (i1 + 11) * k, 0 * k + 4, 0 * k + 4);
            DrawWithOffset.fillRect(g,((l + 22) * k - CTRa4) + 10, (i1 + 11) * k, 0 * k + 4, 0 * k + 4);
        }
        if(VOITc1)
        {
            if(VITc1 == 2)
                g.setFill(MyColor.red);
            else
            if(VITc1 == 1)
                g.setFill(MyColor.darkGray);
            else
                g.setFill(MyColor.cyan);
            DrawWithOffset.fillRect(g,(l + 4) * k + CTRc1, (i1 + 12) * k + 11, 2 * k, 0 * k + 5);
            DrawWithOffset.fillRect(g,(l + 4) * k + CTRc1, (i1 + 12) * k + 3 + 4, 2 * k - 4, 0 * k + 6);
            if(VITc1 == 2)
                g.setFill(MyColor.blue);
            else
            if(VITc1 == 1)
                g.setFill(MyColor.red);
            else
                g.setFill(MyColor.black);
            DrawWithOffset.fillOval(g,(l + 4) * k + CTRc1 + 1, (i1 + 12) * k + 14, 1 * k - 4, 1 * k - 4);
            DrawWithOffset.fillOval(g,(l + 4) * k + CTRc1 + 10, (i1 + 12) * k + 14, 1 * k - 4, 1 * k - 4);
            g.setFill(MyColor.white);
            DrawWithOffset.fillRect(g,(l + 4) * k + CTRc1 + 2, (i1 + 12) * k + 8, 0 * k + 4, 0 * k + 3);
            DrawWithOffset.fillRect(g,(l + 4) * k + CTRc1 + 7, (i1 + 12) * k + 8, 0 * k + 4, 0 * k + 3);
        }
        if(VOITc2)
        {
            if(VITc2 == 2)
                g.setFill(MyColor.black);
            else
            if(VITc2 == 1)
                g.setFill(MyColor.orange);
            else
                g.setFill(MyColor.magenta);
            DrawWithOffset.fillRect(g,(l + 4) * k + CTRc2, (i1 + 12) * k + 11, 2 * k, 0 * k + 5);
            DrawWithOffset.fillRect(g,(l + 4) * k + CTRc2, (i1 + 12) * k + 6, 2 * k - 4, 0 * k + 6);
            if(VITc2 == 2)
                g.setFill(MyColor.red);
            else
            if(VITc2 == 1)
                g.setFill(MyColor.blue);
            else
                g.setFill(MyColor.black);
            DrawWithOffset.fillOval(g,(l + 4) * k + CTRc2 + 1, (i1 + 12) * k + 14, 1 * k - 4, 1 * k - 4);
            DrawWithOffset.fillOval(g,(l + 4) * k + CTRc2 + 10, (i1 + 12) * k + 14, 1 * k - 4, 1 * k - 4);
            g.setFill(MyColor.white);
            DrawWithOffset.fillRect(g,(l + 4) * k + CTRc2 + 2, (i1 + 12) * k + 3 + 4, 0 * k + 4, 0 * k + 4);
            DrawWithOffset.fillRect(g,(l + 4) * k + CTRc2 + 7, (i1 + 12) * k + 3 + 4, 0 * k + 4, 0 * k + 4);
        }
        if(VOITc3)
        {
            if(VITc3 == 1)
                g.setFill(MyColor.red);
            else
                g.setFill(MyColor.black);
            DrawWithOffset.fillRect(g,(l + 4) * k + CTRc3, (i1 + 14) * k + 8, 2 * k, 0 * k + 5);
            DrawWithOffset.fillRect(g,(l + 4) * k + CTRc3, (i1 + 14) * k + 4, 2 * k - 4, 0 * k + 6);
            if(VITc3 == 1)
                g.setFill(MyColor.black);
            else
                g.setFill(MyColor.blue);
            DrawWithOffset.fillOval(g,(l + 4) * k + CTRc3 + 1, (i1 + 14) * k + 11, 1 * k - 4, 1 * k - 4);
            DrawWithOffset.fillOval(g,(l + 4) * k + CTRc3 + 10, (i1 + 14) * k + 11, 1 * k - 4, 1 * k - 4);
            g.setFill(MyColor.white);
            DrawWithOffset.fillRect(g,(l + 4) * k + CTRc3 + 2, (i1 + 14) * k + 5, 0 * k + 4, 0 * k + 3);
            DrawWithOffset.fillRect(g,(l + 4) * k + CTRc3 + 7, (i1 + 14) * k + 5, 0 * k + 4, 0 * k + 3);
        }
        if(VOITc4)
        {
            if(VITc4 == 1)
                g.setFill(MyColor.black);
            else
                g.setFill(MyColor.blue);
            DrawWithOffset.fillRect(g,(l + 4) * k + CTRc4, (i1 + 14) * k + 8, 2 * k, 0 * k + 5);
            DrawWithOffset.fillRect(g,(l + 4) * k + CTRc4, ((i1 + 14) * k - 1) + 4, 2 * k - 4, 0 * k + 6);
            if(CAMIONc4 == 1)
                DrawWithOffset.fillRect(g,((l + 4) * k + CTRc4) - 4, ((i1 + 14) * k + 8) - 5, 1 * k - 4, 1 * k + 2);
            if(VITc4 == 1)
                g.setFill(MyColor.red);
            else
                g.setFill(MyColor.darkGray);
            DrawWithOffset.fillOval(g,(l + 4) * k + CTRc4 + 1, (i1 + 14) * k + 11, 1 * k - 4, 1 * k - 4);
            DrawWithOffset.fillOval(g,(l + 4) * k + CTRc4 + 10, (i1 + 14) * k + 11, 1 * k - 4, 1 * k - 4);
            if(CAMIONc4 == 1)
                DrawWithOffset.fillOval(g,((l + 4) * k + CTRc4) - 4, (i1 + 14) * k + 11, 1 * k - 4, 1 * k - 4);
            g.setFill(MyColor.white);
            if(CAMIONc4 == 1)
            {
                DrawWithOffset.fillRect(g,(l + 4) * k + CTRc4 + 2, (i1 + 14) * k + 4, 0 * k + 4, 0 * k + 4);
                DrawWithOffset.fillRect(g,((l + 4) * k + CTRc4 + 2) - 5, (i1 + 14) * k + 4, 0 * k + 4, 0 * k + 4);
            }
            DrawWithOffset.fillRect(g,(l + 4) * k + CTRc4 + 7, (i1 + 14) * k + 4, 0 * k + 4, 0 * k + 4);
        }
        g.setFill(Color.rgb(185, 185, 185));
        DrawWithOffset.fillRect(g,(l + 22) * k - 6, (i1 + 9) * k + 1, 2 * k + 6, 7 * k - 1);
        DrawWithOffset.fillRect(g,(l + 13) * k - 6, (i1 + 21) * k - 5, 3 * k + 6, 2 * k + 5);
        DrawWithOffset.fillRect(g,(l + 4) * k + 1, (i1 + 9) * k + 1, 2 * k, 7 * k - 1);
        DrawWithOffset.fillRect(g,(l + 13) * k - 6, (i1 + 2) * k + 1, 3 * k + 6, 3 * k - 3);
        g.setFill(MyColor.black);
        DrawWithOffset.fillText(g," A" + Integer.toString(poidsA), (l + 22) * k - 3, (i1 + 11) * k - 6);
        DrawWithOffset.fillText(g," B" + Integer.toString(poidsB), (l + 13) * k + 7, (i1 + 22) * k + 6);
        DrawWithOffset.fillText(g," C" + Integer.toString(poidsC), (l + 5) * k - 8, (i1 + 15) * k + 6);
        DrawWithOffset.fillText(g," D" + Integer.toString(poidsD), (l + 13) * k - 6, (i1 + 4) * k - 6);
        g.setFill(MyColor.blue);
        DrawWithOffset.fillText(g,"cy", (l + 4) * k + 5, (i1 + 18) * k + 2);
        DrawWithOffset.fillText(g,Integer.toString(ARRa), (l + 4) * k + 5, (i1 + 19) * k + 4);
        DrawWithOffset.fillText(g,Integer.toString(ARRb), (l + 4) * k + 5, (i1 + 20) * k + 4);
        DrawWithOffset.fillText(g,Integer.toString(ARRc), (l + 4) * k + 5, (i1 + 21) * k + 4);
        DrawWithOffset.fillText(g,Integer.toString(ARRd), (l + 4) * k + 5, (i1 + 22) * k + 4);
        g.setFill(MyColor.black);
        DrawWithOffset.fillText(g,"tm", (l + 7) * k + 5, (i1 + 18) * k + 2);
        DrawWithOffset.fillText(g,Integer.toString((int)Math.max(TMoyMACHa1, TMoyMACHa2)), (l + 7) * k + 5, (i1 + 19) * k + 4);
        DrawWithOffset.fillText(g,Integer.toString((int)TMoyMACHb), (l + 7) * k + 5, (i1 + 20) * k + 4);
        DrawWithOffset.fillText(g,Integer.toString((int)Math.max(TMoyMACHc3, TMoyMACHc4)), (l + 7) * k + 5, (i1 + 21) * k + 4);
        DrawWithOffset.fillText(g,Integer.toString((int)TMoyMACHd), (l + 7) * k + 5, (i1 + 22) * k + 4);
        DrawWithOffset.fillText(g,"ti", (l + 18) * k, (i1 + 18) * k + 2);
        DrawWithOffset.fillText(g,Integer.toString((int)((double)AttMACHa / 2D)), (l + 18) * k, (i1 + 19) * k + 4);
        DrawWithOffset.fillText(g,Integer.toString((int)((double)AttMACHb / 1.0D)), (l + 18) * k, (i1 + 20) * k + 4);
        DrawWithOffset.fillText(g,Integer.toString((int)((double)AttMACHc / 2D)), (l + 18) * k, (i1 + 21) * k + 4);
        DrawWithOffset.fillText(g,Integer.toString((int)((double)AttMACHd / 1.0D)), (l + 18) * k, (i1 + 22) * k + 4);
        g.setFill(MyColor.blue);
        DrawWithOffset.fillText(g,"nb", (l + 21) * k, (i1 + 18) * k + 2);
        DrawWithOffset.fillText(g,Integer.toString(CNTa), (l + 21) * k, (i1 + 19) * k + 4);
        DrawWithOffset.fillText(g,Integer.toString(CNTb), (l + 21) * k, (i1 + 20) * k + 4);
        DrawWithOffset.fillText(g,Integer.toString(CNTc), (l + 21) * k, (i1 + 21) * k + 4);
        DrawWithOffset.fillText(g,Integer.toString(CNTd), (l + 21) * k, (i1 + 22) * k + 4);
        g.setFill(MyColor.black);
        DrawWithOffset.fillText(g,"Test : " + Integer.toString((int)(((NbBcles / (long)NbreTests) * 100L) / (long)LongueurCycle)) + "%", (l + 4) * k + 5, (i1 + 4) * k - 6);
        if(Bilan)
        {
            if(!CESTFINI && CHRONOzero != 0L)
                CHRONO = (new Date()).getTime();
            long l2 = (CHRONO - CHRONOzero) / 60000L;
            DrawWithOffset.fillText(g,"T : " + String.valueOf(l2) + " min", (l + 4) * k + 5, (i1 + 5) * k - 6);
        }
        IndPerf = (int)(0.25D * (double)SommePoids * Math.min((10000D * (double)CNTa) / ((double)poidsA * ((double)((long)ARRa * Math.max(TMoyMACHa1, TMoyMACHa2)) + 0.5D * (double)AttMACHa)), Math.min((10000D * (double)CNTb) / ((double)poidsB * ((double)((long)ARRb * TMoyMACHb) + 1.0D * (double)AttMACHb)), Math.min((10000D * (double)CNTc) / ((double)poidsC * ((double)((long)ARRc * Math.max(TMoyMACHc3, TMoyMACHc4)) + 0.5D * (double)AttMACHc)), (10000D * (double)CNTd) / ((double)poidsD * ((double)((long)ARRd * TMoyMACHd) + 1.0D * (double)AttMACHd))))));
        if(!Click && DureeMinR != 999 && DureeMin0 != 999 && DureeMinV != 999)
        {
            g.setFill(MyColor.black);
            DrawWithOffset.fillText(g,"R min : " + String.valueOf(0.5D * (double)DureeMinR), (l + 16) * k + 5, (i1 + 5) * k - 6);
            g.setFill(MyColor.red);
            DrawWithOffset.fillText(g,"R", (l + 16) * k + 5, (i1 + 5) * k - 6);
            g.setFill(MyColor.black);
            DrawWithOffset.fillText(g,"O min : " + String.valueOf(0.5D * (double)DureeMin0), (l + 16) * k + 5, (i1 + 6) * k - 6);
            g.setFill(MyColor.orange);
            DrawWithOffset.fillText(g,"O", (l + 16) * k + 5, (i1 + 6) * k - 6);
            g.setFill(MyColor.black);
            DrawWithOffset.fillText(g,"V min : " + String.valueOf(0.5D * (double)DureeMinV), (l + 16) * k + 5, (i1 + 7) * k - 6);
            g.setFill(MyColor.green);
            DrawWithOffset.fillText(g,"V", (l + 16) * k + 5, (i1 + 7) * k - 6);
            if(IndPerf > 0 && ConflitVO != 3 && !ConflitoA && !ConflitoB && !ConflitoC && !ConflitoD)
            {
                g.setFill(MyColor.black);
                DrawWithOffset.fillText(g,"IPe : " + String.valueOf(IndPerf), (l + 16) * k + 5, (i1 + 4) * k - 6);
            }
            if(FinCycles)
            {
                if(Bilan && !ConflitoA && !ConflitoB && !ConflitoC && !ConflitoD && ConflitVO != 3)
                {
                    if(!CESTFINI)
                        DigSim.alarm.play();
                    CESTFINI = true;
                }
                if(CESTFINI)
                {
                    g.setFill(MyColor.yellow);
                    DrawWithOffset.fillRect(g,(l + 2) * k, (i1 + 23) * k, 22 * k, 1 * k + 3);
                    g.setStroke(MyColor.black);
                    DrawWithOffset.strokeRect(g,(l + 2) * k, (i1 + 23) * k, 22 * k, 1 * k + 3);
                    g.setFill(MyColor.red);
                    DrawWithOffset.fillText(g,"Indice de performance : " + String.valueOf(IndPerf), (l + 4) * k + 2, (i1 + 24) * k + 1);
                }
            }
        }
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    public void InitBeforeSimulate()
    {
        IPin[0].InitBeforeSimulate();
        IPin[1].InitBeforeSimulate();
        IPin[2].InitBeforeSimulate();
        IPin[3].InitBeforeSimulate();
        IPin[4].InitBeforeSimulate();
        IPin[5].InitBeforeSimulate();
        IPin[6].InitBeforeSimulate();
        IPin[7].InitBeforeSimulate();
        IPin[8].InitBeforeSimulate();
        IPin[9].InitBeforeSimulate();
        IPin[10].InitBeforeSimulate();
        IPin[11].InitBeforeSimulate();
    }

    static Color LEDColorOff;
    int poidsA;
    int poidsB;
    int poidsC;
    int poidsD;
    int SommePoids;
    int TrafficMin;
    int TrafficMOY;
    int TRAFFICMAX;
    int Trafficnul;
    int LongueurCycle;
    int Cycle;
    int OrangeMax;
    int rA;
    int oA;
    int vA;
    int rB;
    int oB;
    int vB;
    int rC;
    int oC;
    int vC;
    int rD;
    int oD;
    int vD;
    int minCTRa;
    int maxCTRa;
    int minCTRc;
    int maxCTRc;
    int minCTRd;
    int maxCTRd;
    int minCTRb;
    int maxCTRb;
    int CNTa;
    int CNTb;
    int CNTc;
    int CNTd;
    int CTRa1;
    int CTRa2;
    int CTRa3;
    int CTRa4;
    int CTRb;
    int CTRc1;
    int CTRc2;
    int CTRc3;
    int CTRc4;
    int CTRd;
    int PROBa12;
    int PROBa34;
    int PROBc12;
    int PROBc34;
    int PROBd;
    int PROBb;
    boolean CESTFINI;
    int IndPerf;
    boolean Bruitage;
    boolean VOITa1;
    boolean VOITa2;
    boolean VOITa3;
    boolean VOITa4;
    boolean VOITb;
    boolean VOITc1;
    boolean VOITc2;
    boolean VOITc3;
    boolean VOITc4;
    boolean VOITd;
    int VITa1;
    int VITa2;
    int VITa3;
    int VITa4;
    int CAMIONa2;
    int VITc1;
    int VITc2;
    int VITc3;
    int VITc4;
    int CAMIONc4;
    int VITd;
    int DIRECTd;
    int VITb;
    int VITINIT2;
    int VITINIT1;
    boolean AdnsX;
    boolean BdnsX;
    boolean CdnsX;
    boolean DdnsXdroite;
    boolean DdnsXBas;
    boolean DprndA;
    boolean AprndA;
    boolean DprndC;
    boolean CprndC;
    boolean BprndC;
    boolean Acoll;
    boolean Ccoll;
    boolean PbNO;
    boolean PbSU;
    boolean PbSO;
    boolean Bilan;
    boolean Click;
    boolean STOPa;
    int ARRa;
    long TMoyMACHa;
    long nbreBOUCLESa;
    long TotAttBcleMACHa;
    long AttMACHa;
    boolean STOPa1;
    int ARRa1;
    long TMoyMACHa1;
    long nbreBOUCLESa1;
    long TotAttBcleMACHa1;
    long AttMACHa1;
    boolean STOPa2;
    int ARRa2;
    long TMoyMACHa2;
    long nbreBOUCLESa2;
    long TotAttBcleMACHa2;
    long AttMACHa2;
    boolean STOPa3;
    int ARRa3;
    long TMoyMACHa3;
    long nbreBOUCLESa3;
    long TotAttBcleMACHa3;
    long AttMACHa3;
    boolean STOPa4;
    int ARRa4;
    long TMoyMACHa4;
    long nbreBOUCLESa4;
    long TotAttBcleMACHa4;
    long AttMACHa4;
    boolean STOPb;
    int ARRb;
    long TMoyMACHb;
    long nbreBOUCLESb;
    long TotAttBcleMACHb;
    long AttMACHb;
    boolean STOPc;
    int ARRc;
    long TMoyMACHc;
    long nbreBOUCLESc;
    long TotAttBcleMACHc;
    long AttMACHc;
    boolean STOPc3;
    int ARRc3;
    long TMoyMACHc3;
    long nbreBOUCLESc3;
    long TotAttBcleMACHc3;
    long AttMACHc3;
    boolean STOPc4;
    int ARRc4;
    long TMoyMACHc4;
    long nbreBOUCLESc4;
    long TotAttBcleMACHc4;
    long AttMACHc4;
    boolean STOPc1;
    int ARRc1;
    long TMoyMACHc1;
    long nbreBOUCLESc1;
    long TotAttBcleMACHc1;
    long AttMACHc1;
    boolean STOPc2;
    int ARRc2;
    long TMoyMACHc2;
    long nbreBOUCLESc2;
    long TotAttBcleMACHc2;
    long AttMACHc2;
    boolean STOPd;
    int ARRd;
    long TMoyMACHd;
    long nbreBOUCLESd;
    long TotAttBcleMACHd;
    long AttMACHd;
    boolean FinCycles;
    long NbBcles;
    boolean SwitchClosed;
    String flag;
    boolean TestVertA;
    boolean TestVertB;
    boolean TestVertC;
    boolean TestVertD;
    boolean TestOrange;
    boolean TestRougeA;
    boolean TestRougeB;
    boolean TestRougeC;
    boolean TestRougeD;
    int DureeoA;
    int DureeoB;
    int DureeoC;
    int DureeoD;
    int DureevA;
    int DureevB;
    int DureevC;
    int DureevD;
    int DureerA;
    int DureerB;
    int DureerC;
    int DureerD;
    int NbOrangA;
    int NbOrangB;
    int NbOrangC;
    int NbOrangD;
    int NbVertA;
    int NbVertB;
    int NbVertC;
    int NbVertD;
    int NbRougeA;
    int NbRougeB;
    int NbRougeC;
    int NbRougeD;
    int DureeMinV;
    int DureeMin0;
    int DureeMinR;
    boolean ConflitoA;
    boolean ConflitoB;
    boolean ConflitoC;
    boolean ConflitoD;
    int ConflitVO;
    int FeuxEteints;
    long CHRONO;
    long CHRONOzero;
    int NbreTests;
    int loin;
    int proche;
    int arrive;
    int DistMin;
    int marge;

    static
    {
        LEDColorOff = MyColor.gray;
    }
}
