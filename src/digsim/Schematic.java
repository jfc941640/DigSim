package digsim;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Vector;

import digsim.DigSimFrame;
import digsim.FileFormatException;
import digsim.FullAdder;
import digsim.MyColor;
import digsim.Pin;
import digsim.components.*;
import digsim.components.ISO.ISOet3;
import digsim.components.ISO.ISOet3non;
import digsim.components.ISO.ISOnon;
import digsim.components.ISO.ISOou3;
import digsim.components.ISO.ISOou3non;
import digsim.components.ISO.ISOoux3;
import digsim.components.ISO.ISOoux3non;
import digsim.components.boitier.Boitier7400;
import digsim.components.boitier.Boitier7402;
import digsim.components.boitier.Boitier7404;
import digsim.components.boitier.Boitier7408;
import digsim.components.boitier.Boitier7410;
import digsim.components.boitier.Boitier74109;
import digsim.components.boitier.Boitier7411;
import digsim.components.boitier.Boitier7420;
import digsim.components.boitier.Boitier7421;
import digsim.components.boitier.Boitier7427;
import digsim.components.boitier.Boitier7432;
import digsim.components.boitier.Boitier744002;
import digsim.components.boitier.Boitier744072;
import digsim.components.boitier.Boitier744075;
import digsim.components.boitier.Boitier747266;
import digsim.components.boitier.Boitier7486;
import digsim.components.essai.ESSAICOMPOSANT0;
import digsim.components.essai.ESSAICOMPOSANT1;
import digsim.components.essai.ESSAICOMPOSANT2;
import digsim.components.essai.ESSAICOMPOSANT3;
import digsim.components.essai.ESSAICOMPOSANT4;
import digsim.components.essai.ESSAICOMPOSANT5;
import digsim.components.essai.ESSAICOMPOSANT6;
import digsim.components.essai.ESSAICOMPOSANT7;
import digsim.components.essai.ESSAICOMPOSANT8;
import digsim.components.essai.ESSAICOMPOSANT9;
import digsim.components.mini.Mini1BP;
import digsim.components.mini.Mini1BPtempo;
import digsim.components.mini.Mini1Inter;
import digsim.components.mini.Mini2BPtempo;
import digsim.components.mini.MiniAnalyseur;
import digsim.components.mini.MiniBPNF;
import digsim.components.mini.MiniBPNFPullDown;
import digsim.components.mini.MiniBPNFPullUp;
import digsim.components.mini.MiniBPNO;
import digsim.components.mini.MiniBPNOAvecR;
import digsim.components.mini.MiniBPNOPullDown;
import digsim.components.mini.MiniBPNOPullUp;
import digsim.components.mini.MiniSwitchNF;
import digsim.components.mini.MiniSwitchNFPullDown;
import digsim.components.mini.MiniSwitchNFPullUp;
import digsim.components.mini.MiniSwitchNO;
import digsim.components.mini.MiniSwitchNOPullDown;
import digsim.components.mini.MiniSwitchNOPullUp;
import digsim.components.rail.RailAiguillageDroit10Auto;
import digsim.components.rail.RailAiguillageDroit10Com;
import digsim.components.rail.RailAiguillageGauche10Auto;
import digsim.components.rail.RailAiguillageGauche10Com;
import digsim.components.rail.RailBP;
import digsim.components.rail.RailBoitierCom;
import digsim.components.rail.RailButoir;
import digsim.components.rail.RailButoirTrain;
import digsim.components.rail.RailCourbe45AutoD;
import digsim.components.rail.RailCourbe45AutoG;
import digsim.components.rail.RailCourbe90d8x8;
import digsim.components.rail.RailCourbe90d8x8Auto;
import digsim.components.rail.RailCroisement;
import digsim.components.rail.RailDroit10;
import digsim.components.rail.RailDroit10Auto;
import digsim.components.rail.RailDroit10AutoLent;
import digsim.components.rail.RailDroit10Train;
import digsim.components.rail.RailDroit20;
import digsim.components.rail.RailDroit20Auto;
import digsim.components.rail.RailDroit5Auto;
import digsim.components.rail.RailGestionCanton;
import digsim.components.rail.RailGestionDirection;
import digsim.components.rail.RailKlaxon;
import digsim.components.rail.RailMasque1;
import digsim.components.rail.RailMasque2;
import digsim.components.rail.RailMini2S2R;
import digsim.components.rail.RailMiniSR;
import digsim.components.rail.RailObliqueDroit;
import digsim.components.rail.RailObliqueGauche;
import digsim.components.rail.RailSegmentH;
import digsim.components.rail.RailSegmentV;
import digsim.components.rail.RailSifflet;
import digsim.components.rail.RailSwitch;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            ElectronicComponent, Wire, FileFormatException, SimpleDialog,
//            ScriptFrame, Vcc, GND, Colorieur,
//            Buffer, Inverter, TwoAndPort, ThreeAndPort,
//            FourAndPort, TwoNandPort, ThreeNandPort, FourNandPort,
//            TwoOrPort, ThreeOrPort, FourOrPort, TwoNorPort,
//            ThreeNorPort, FourNorPort, FiveNorPort, TwoXorPort,
//            TwoXnorPort, Switch, SwitchT, PushButton,
//            SRLatch, GatedSRLatch, DLatch, DFlipFlop,
//            JKFlipFlop, JKRSFlipFlop, JbKRSFlipFlop, EdgeTriggeredTFlipFlop,
//            TFlipFlop, OctalDFlipFlop, OctalDFlipFlopInverter, OctalLatch,
//            Oscilator, Oscillator2, Oscillateur1s, Oscillateur500ms,
//            Oscillateur100ms, BCDToSevenSegDecoder, FullAdder, ThreeToEightLineDecoder,
//            FourBitBinaryCounter, FourBitBinaryUpDownCounter, FourBitBCDUpDownCounter, EightBitBinaryUpDownCounter,
//            Monostable, PLA5x4, TraitsCie, ShiftRegister,
//            CounterBCDxx160, Counterxx161, CounterBCDxx162, Counterxx163,
//            Rom, Ram, EightBitSerInShiftReg, EightBitParInShiftReg,
//            Multiplexer2to1, Multiplexer4to1, Multiplexer8to1, SequencerCell,
//            BICOLORLEDVERTICAL1, CtrBcdBinRazSync, CtrBcdBinRazASync, Ctr5BinRazASync,
//            PERCEUSE, INPUTINVERTER, OUTPUTINVERTER, CLEF,
//            CelluleRC, Comparateur8bits, Comparateur4bits, JETON,
//            FEUXCARREFOUR, Caption, CaptionBlack, CaptionRed,
//            CaptionRdP, CaptionBlue, CaptionGreen, LED,
//            BiColorLED, SevenSegmentDisplay, Probe, SoundGenerator,
//            AlarmGenerator, Switch2RT, Relay2RT, MULT8x8,
//            ADD8, MAC8x8, Oscillateur2T, FourMUX2to1,
//            Buffer8bits, Transceiver8bits, Buffer2x4bits, ChariotXYhbgd,
//            ChariotXY7bits, ChariotXYmixte, ChariotXYPaP, WagonsSimple,
//            ChariotLibre, Afficheur9bits, TRIGGER, WagonsAiguillages,
//            Buffer3S, EmetteurSerie8bits, RetardFixe, RetardPur,
//            RetardRC, Ram2, RdPtransition, RdPfleche,
//            RdPplace, RdPjeton, CapaC, ResR,
//            FourBitShifter, Pendule, Encodeur8vers3, Encodeur10vers4,
//            Boitier7404, Boitier7408, Boitier7411, Boitier7421,
//            Boitier7432, Boitier744075, Boitier744072, Boitier7400,
//            Boitier7410, Boitier7420, Boitier7402, Boitier7427,
//            Boitier744002, Boitier7486, Boitier747266, Boitier74109,
//            Cadre1024x768, Cadre1280x1024, CadreDouble97x69, CadreDouble80x70,
//            DEC8, SBPA, SBA, MiniBPNOPullUp,
//            MiniBPNOPullDown, MiniBPNFPullUp, MiniBPNFPullDown, MiniSwitchNOPullUp,
//            MiniSwitchNOPullDown, MiniSwitchNFPullUp, MiniSwitchNFPullDown, MiniBPNOAvecR,
//            MiniBPNO, MiniBPNF, MiniSwitchNO, MiniSwitchNF,
//            CodeGen, CodeTest, HHmmss, Trans8toBus,
//            TransBusto8, Mini2BPtempo, Mini1BPtempo, Mini1BP,
//            Mini1Inter, ISOnon, ISOet3, ISOou3,
//            ISOet3non, ISOou3non, ISOoux3, ISOoux3non,
//            RailGestionDirection, RailGestionCanton, RailObliqueDroit, RailObliqueGauche,
//            RailDroit10Train, RailDroit10, RailDroit20, RailCourbe90d8x8,
//            RailAiguillageDroit10Com, RailAiguillageGauche10Com, RailAiguillageDroit10Auto, RailAiguillageGauche10Auto,
//            RailCroisement, RailMasque1, RailMasque2, RailButoir,
//            RailButoirTrain, RailDroit5Auto, RailDroit10Auto, RailDroit20Auto,
//            RailDroit10AutoLent, RailCourbe90d8x8Auto, RailSegmentH, RailSegmentV,
//            RailBoitierCom, RailCourbe45AutoD, RailCourbe45AutoG, RailBP,
//            RailSwitch, RailKlaxon, RailSifflet, RailMiniSR,
//            RailMini2S2R, PERIODEMETRE, MiniAnalyseur, AnalyseurHorloge,
//            Compteur2fronts, Mono500msNonRe, Mono500msRe, Mono1sRe,
//            ESSAICOMPOSANT0, ESSAICOMPOSANT1, ESSAICOMPOSANT2, ESSAICOMPOSANT3,
//            ESSAICOMPOSANT4, ESSAICOMPOSANT5, ESSAICOMPOSANT6, ESSAICOMPOSANT7,
//            ESSAICOMPOSANT8, ESSAICOMPOSANT9, MyColor, DigSimFrame,
//            DigSim, OutputPin, InputPin, Pin

public class Schematic
{

    public Schematic()
    {
        Modified = false;
        FileDir = null;
        FileName = null;
        SimulateCycleID = 0;
        Components = new Vector();
    }

    public Schematic(Schematic schematic)
    {
        Modified = false;
        FileDir = null;
        FileName = null;
        SimulateCycleID = 0;
        Components = new Vector();
        for(int i = 0; i < schematic.size(); i++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)schematic.Components.elementAt(i);
            Components.addElement(electroniccomponent);
        }

    }

    public Schematic PasteSchematic(Pin apin[][], Schematic schematic, int i, int j)
    {
        Schematic schematic1 = new Schematic();
        int i1 = -1;
        int j1 = -1;
        Modified = true;
        for(int k = 0; k < schematic.size(); k++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)schematic.Components.elementAt(k);
            if(electroniccomponent instanceof Wire)
            {
                Wire wire = (Wire)electroniccomponent;
                int k1 = Math.min(wire.x1, wire.x2);
                int l1 = Math.min(wire.y1, wire.y2);
                if(i1 == -1 || k1 < i1)
                    i1 = k1 - 1;
                if(j1 == -1 || l1 < j1)
                    j1 = l1 - 1;
                continue;
            }
            if(i1 == -1 || electroniccomponent.Pos.x < i1)
                i1 = electroniccomponent.Pos.x;
            if(j1 == -1 || electroniccomponent.Pos.y < j1)
                j1 = electroniccomponent.Pos.y;
        }

        for(int l = 0; l < schematic.size(); l++)
        {
            ElectronicComponent electroniccomponent1 = (ElectronicComponent)schematic.Components.elementAt(l);
            ElectronicComponent electroniccomponent2 = electroniccomponent1.Copy(i1 - i - 1, j1 - j - 1);
            electroniccomponent2.Can_Rotate = electroniccomponent1.Can_Rotate;
            electroniccomponent2.NbRotation = electroniccomponent1.NbRotation;
            electroniccomponent2.PlacePinsHere(apin);
            electroniccomponent1.Selected = false;
            electroniccomponent2.Selected = true;
            if(electroniccomponent1 instanceof Wire)
            {
                Wire wire1 = (Wire)electroniccomponent2;
                wire1.ChangingWire = 3;
            }
            addComponent(electroniccomponent2);
            schematic1.addComponent(electroniccomponent2);
        }

        return schematic1;
    }

    public Schematic PasteReInit(Pin apin[][], Schematic schematic, int i, int j)
    {
        Schematic schematic1 = new Schematic();
        byte byte0 = -1;
        byte byte1 = -1;
        Modified = true;
        for(int k = 0; k < schematic.size(); k++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)schematic.Components.elementAt(k);
            ElectronicComponent electroniccomponent1 = electroniccomponent.Copy(byte0 + 1, byte1 + 1);
            electroniccomponent1.Can_Rotate = electroniccomponent.Can_Rotate;
            electroniccomponent1.NbRotation = electroniccomponent.NbRotation;
            electroniccomponent1.PlacePinsHere(apin);
            electroniccomponent.Selected = false;
            electroniccomponent1.Selected = true;
            if(electroniccomponent instanceof Wire)
            {
                Wire wire = (Wire)electroniccomponent1;
                wire.ChangingWire = 3;
            }
            addComponent(electroniccomponent1);
            schematic1.addComponent(electroniccomponent1);
        }

        return schematic1;
    }

    public void SetAllComponentsSelected()
    {
        for(int i = 0; i < Components.size(); i++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)Components.elementAt(i);
            electroniccomponent.Selected = true;
            if(electroniccomponent instanceof Wire)
            {
                Wire wire = (Wire)electroniccomponent;
                wire.ChangingWire = 3;
            }
        }

    }

    public void SetAllComponentsUnSelected()
    {
        for(int i = 0; i < Components.size(); i++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)Components.elementAt(i);
            electroniccomponent.Selected = false;
            if(electroniccomponent instanceof Wire)
            {
                Wire wire = (Wire)electroniccomponent;
                wire.ChangingWire = 3;
            }
        }

    }

    public void RemoveSameElements(Pin apin[][], Schematic schematic)
    {
        Modified = true;
        for(int i = 0; i < schematic.size(); i++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)schematic.Components.elementAt(i);
            if(Components.contains(electroniccomponent))
            {
                electroniccomponent.RemovePinsGrid(apin);
                electroniccomponent.RemovePinsGrid(apin);
                electroniccomponent.RemovePinsGrid(apin);
                Components.removeElement(electroniccomponent);
            }
        }

    }

    public void DestroyComponents(Pin apin[][])
    {
        Modified = true;
        for(int i = 0; i < Components.size(); i++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)Components.elementAt(i);
            electroniccomponent.RemovePinsGrid(apin);
            electroniccomponent.RemovePinsGrid(apin);
            electroniccomponent.RemovePinsGrid(apin);
        }

        Components.removeAllElements();
    }

    public void addComponent(ElectronicComponent electroniccomponent)
    {
        Modified = true;
        Components.addElement(electroniccomponent);
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        for(int l = 0; l < Components.size(); l++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)Components.elementAt(l);
            electroniccomponent.draw(g, i, j, k);
        }

    }

    public int size()
    {
        return Components.size();
    }

    public void RemoveAllComponents()
    {
        Modified = true;
        for(int i = 0; i < Components.size(); i++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)Components.elementAt(i);
            electroniccomponent.Selected = false;
        }

        Components.removeAllElements();
    }

    public boolean InSchematic(ElectronicComponent electroniccomponent)
    {
        return Components.contains(electroniccomponent);
    }

    public void CheckIfComponentsInSelectBox(Schematic schematic, int i, int j, int k, int l)
    {
        if(i > k)
        {
            int i1 = i;
            i = k;
            k = i1;
        }
        if(j > l)
        {
            int j1 = j;
            j = l;
            l = j1;
        }
        for(int k1 = 0; k1 < Components.size(); k1++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)Components.elementAt(k1);
            if(electroniccomponent.CheckIfComponentInSelectBox(i, j, k, l))
                schematic.addComponent(electroniccomponent);
        }

    }

    public void AdjustPosition(Pin apin[][], int i, int j)
    {
        for(int k = 0; k < Components.size(); k++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)Components.elementAt(k);
            electroniccomponent.AdjustPosition(apin, i, j);
        }

    }

    public void RemovePinsGrid(Pin apin[][])
    {
        for(int i = 0; i < Components.size(); i++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)Components.elementAt(i);
            electroniccomponent.RemovePinsGrid(apin);
            electroniccomponent.RemovePinsGrid(apin);
            electroniccomponent.RemovePinsGrid(apin);
        }

    }

    public void CheckPosition()
    {
        for(int i = 0; i < Components.size(); i++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)Components.elementAt(i);
            electroniccomponent.CheckPosition();
        }

    }

    public void PlacePinsHere(Pin apin[][])
    {
        for(int i = 0; i < Components.size(); i++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)Components.elementAt(i);
            electroniccomponent.PlacePinsHere(apin);
        }

    }

    public ElectronicComponent CheckIfComponentClicked(int i, int j)
    {
        for(int k = 0; k < Components.size(); k++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)Components.elementAt(k);
            if(electroniccomponent.CheckIfComponentClicked(i, j))
                return electroniccomponent;
        }

        return null;
    }

    public void Simulate()
    {
        for(int i = 0; i < Components.size(); i++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)Components.elementAt(i);
            SimulateCycleID++;
            if(SimulateCycleID == -1)
                SimulateCycleID++;
            electroniccomponent.Simulate(SimulateCycleID);
        }

    }

    public void InitBeforeSimulate()
    {
        for(int i = 0; i < Components.size(); i++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)Components.elementAt(i);
            electroniccomponent.InitBeforeSimulate();
        }

    }

    protected Color StringToColor(String s)
    {
        if("red".equals(s))
            return MyColor.red;
        if("green".equals(s))
            return MyColor.green;
        if("blue".equals(s))
            return MyColor.blue;
        if("orange".equals(s))
            return MyColor.orange;
        if("yellow".equals(s))
            return MyColor.yellow;
        if("pink".equals(s))
            return MyColor.pink;
        if("black".equals(s))
            return MyColor.black;
        if("white".equals(s))
            return MyColor.white;
        if("gray".equals(s))
            return MyColor.gray;
        if("cyan".equals(s))
            return MyColor.cyan;
        if("magenta".equals(s))
            return MyColor.magenta;
        if("ligthGray".equals(s))
            return MyColor.lightGray;
        if("darkGray".equals(s))
            return MyColor.darkGray;
        else
            return MyColor.red;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean LoadComponent(Pin[][] arrpin, InputStream inputStream, StreamTokenizer streamTokenizer) throws IOException, FileFormatException {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 1;
        int n7 = 1;
        int n8 = 10;
        int n9 = 10;
        int n10 = 10;
        int n11 = 10;
        String string = "F";
        String string2 = "F";
        String string3 = "F";
        String string4 = streamTokenizer.sval;
        Color color = MyColor.red;
        String string5 = "";
        block4 : do {
            switch (streamTokenizer.nextToken()) {
                case 10: {
                    continue block4;
                }
                case -3: {
                    int n12;
                    if ("pos".equals(streamTokenizer.sval)) {
                        if (streamTokenizer.nextToken() == -2) {
                            n = (int)streamTokenizer.nval;
                            if (streamTokenizer.nextToken() == -2) {
                                n2 = (int)streamTokenizer.nval;
                            }
                        }
                        do {
                            if (streamTokenizer.ttype == 10 || streamTokenizer.ttype == -1) continue block4;
                            streamTokenizer.nextToken();
                        } while (true);
                    }
                    if ("rotation".equals(streamTokenizer.sval)) {
                        if (streamTokenizer.nextToken() == -2) {
                            n5 = (int)streamTokenizer.nval;
                        }
                        do {
                            if (streamTokenizer.ttype == 10 || streamTokenizer.ttype == -1) continue block4;
                            streamTokenizer.nextToken();
                        } while (true);
                    }
                    if ("color".equals(streamTokenizer.sval)) {
                        if (streamTokenizer.nextToken() != -3) continue block4;
                        color = this.StringToColor(streamTokenizer.sval);
                        continue block4;
                    }
                    if ("Text".equals(streamTokenizer.sval)) {
                        string5 = "";
                        do {
                            if ((n12 = inputStream.read()) == 10) continue block4;
                            if (n12 == 13) continue;
                            string5 = string5 + (char)n12;
                        } while (true);
                    }
                    if ("cycle".equals(streamTokenizer.sval)) {
                        if (streamTokenizer.nextToken() == -2) {
                            n6 = (int)streamTokenizer.nval;
                        }
                        do {
                            if (streamTokenizer.ttype == 10 || streamTokenizer.ttype == -1) continue block4;
                            streamTokenizer.nextToken();
                        } while (true);
                    }
                    if ("delay".equals(streamTokenizer.sval)) {
                        if (streamTokenizer.nextToken() == -2) {
                            n7 = (int)streamTokenizer.nval;
                        }
                        do {
                            if (streamTokenizer.ttype == 10 || streamTokenizer.ttype == -1) continue block4;
                            streamTokenizer.nextToken();
                        } while (true);
                    }
                    if ("stop".equals(streamTokenizer.sval)) {
                        if (streamTokenizer.nextToken() == -2) {
                            n8 = (int)streamTokenizer.nval;
                        }
                        do {
                            if (streamTokenizer.ttype == 10 || streamTokenizer.ttype == -1) continue block4;
                            streamTokenizer.nextToken();
                        } while (true);
                    }
                    if ("xrect".equals(streamTokenizer.sval)) {
                        if (streamTokenizer.nextToken() == -2) {
                            n9 = (int)streamTokenizer.nval;
                        }
                        do {
                            if (streamTokenizer.ttype == 10 || streamTokenizer.ttype == -1) continue block4;
                            streamTokenizer.nextToken();
                        } while (true);
                    }
                    if ("yrect".equals(streamTokenizer.sval)) {
                        if (streamTokenizer.nextToken() == -2) {
                            n10 = (int)streamTokenizer.nval;
                        }
                        do {
                            if (streamTokenizer.ttype == 10 || streamTokenizer.ttype == -1) continue block4;
                            streamTokenizer.nextToken();
                        } while (true);
                    }
                    if ("zrect".equals(streamTokenizer.sval)) {
                        if (streamTokenizer.nextToken() == -2) {
                            n11 = (int)streamTokenizer.nval;
                        }
                        do {
                            if (streamTokenizer.ttype == 10 || streamTokenizer.ttype == -1) continue block4;
                            streamTokenizer.nextToken();
                        } while (true);
                    }
                    if ("xpla".equals(streamTokenizer.sval)) {
                        if (streamTokenizer.nextToken() == -3) {
                            string = streamTokenizer.sval;
                        }
                        do {
                            if (streamTokenizer.ttype == 10 || streamTokenizer.ttype == -1) continue block4;
                            streamTokenizer.nextToken();
                        } while (true);
                    }
                    if ("ypla".equals(streamTokenizer.sval)) {
                        if (streamTokenizer.nextToken() == -3) {
                            string2 = streamTokenizer.sval;
                        }
                        do {
                            if (streamTokenizer.ttype == 10 || streamTokenizer.ttype == -1) continue block4;
                            streamTokenizer.nextToken();
                        } while (true);
                    }
                    if ("zpla".equals(streamTokenizer.sval)) {
                        if (streamTokenizer.nextToken() == -3) {
                            string3 = streamTokenizer.sval;
                        }
                        do {
                            if (streamTokenizer.ttype == 10 || streamTokenizer.ttype == -1) continue block4;
                            streamTokenizer.nextToken();
                        } while (true);
                    }
                    if ("Mem".equals(streamTokenizer.sval)) {
                        string5 = "";
                        do {
                            if ((n12 = inputStream.read()) == 10) continue block4;
                            if (n12 == 13) continue;
                            string5 = string5 + (char)n12;
                        } while (true);
                    }
                    if ("pos2".equals(streamTokenizer.sval)) {
                        if (streamTokenizer.nextToken() == -2) {
                            n3 = (int)streamTokenizer.nval;
                            if (streamTokenizer.nextToken() == -2) {
                                n4 = (int)streamTokenizer.nval;
                            }
                        }
                        do {
                            if (streamTokenizer.ttype == 10 || streamTokenizer.ttype == -1) continue block4;
                            streamTokenizer.nextToken();
                        } while (true);
                    }
                    if ("end".equals(streamTokenizer.sval) && streamTokenizer.nextToken() == -3 && "describe".equals(streamTokenizer.sval)) return this.LoadComponent(arrpin, string4, color, n, n2, n5, n3, n4, n6, n7, n8, n9, n10, n11, string, string2, string3, string5);
                    continue block4;
                }
            }
            break;
        } while (true);
        return true;
    }    Schematic(Pin apin[][], InputStream inputstream)
        throws IOException, FileFormatException
    {
        Modified = false;
        FileDir = null;
        FileName = null;
        SimulateCycleID = 0;
        Components = new Vector();
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        StreamTokenizer streamtokenizer = new StreamTokenizer(inputstream);
        streamtokenizer.eolIsSignificant(true);
        streamtokenizer.commentChar(35);
        do
            switch(streamtokenizer.nextToken())
            {
            case -3:
                if("version".equals(streamtokenizer.sval))
                {
                    if(streamtokenizer.nextToken() == -2)
                    {
                        int i = (int)streamtokenizer.nval;
                        int j;
                        if(streamtokenizer.nextToken() == -2)
                            j = (int)streamtokenizer.nval;
                    }
                    while(streamtokenizer.ttype != 10 && streamtokenizer.ttype != -1)
                        streamtokenizer.nextToken();
                } else
                if("describe".equals(streamtokenizer.sval))
                {
                    if(streamtokenizer.nextToken() == -3 && "component".equals(streamtokenizer.sval) && streamtokenizer.nextToken() == -3 && !LoadComponent(apin, inputstream, streamtokenizer))
                        flag2 = true;
                } else
                {
                    flag2 = true;
                }
                break;

            default:
                inputstream.close();
                if(streamtokenizer.ttype != -1)
                    throw new FileFormatException(streamtokenizer.toString());
                Modified = false;
                if(flag2)
                {
                    String as[] = {
                        "OK"
                    };
                    String s = "Unknown data in schematic. Schematic could be corrupt.";
                    SimpleDialog simpledialog = new SimpleDialog(null, "Loading schematic", s, as, 1, 0, 0, 2);
                } else
                {
                    RemovePinsGrid(apin);
                    PlacePinsHere(apin);
                }
                return;

            case 10: // '\n'
                break;
            }
        while(true);
    }

    Schematic(DigSimFrame digsimframe, Pin apin[][], String s)
    {
        Modified = false;
        FileDir = null;
        FileName = null;
        SimulateCycleID = 0;
        Components = new Vector();
        ImportFromScript(digsimframe, apin, s);
        RemovePinsGrid(apin);
        PlacePinsHere(apin);
        Modified = false;
    }

    public void Save(FileOutputStream fileoutputstream)
        throws IOException, FileFormatException
    {
        PrintStream printstream = new PrintStream(fileoutputstream);
        printstream.println("# Digsim file");
        printstream.println("version ''RailWay'' 2012.2.0");
        for(int i = 0; i < Components.size(); i++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)Components.elementAt(i);
            electroniccomponent.Save(printstream);
        }

        fileoutputstream.close();
    }

    public void SaveAsScript(FileOutputStream fileoutputstream)
        throws IOException, FileFormatException
    {
        PrintStream printstream = new PrintStream(fileoutputstream);
        printstream.print(ExportAsScript());
        fileoutputstream.close();
    }

    public String ExportAsScript()
    {
        String s = new String("# Digsim file version ''RailWay'' 2012.2.0\r\n^\r\n^");
        for(int i = 0; i < Components.size(); i++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)Components.elementAt(i);
            s = s + electroniccomponent.ExportAsScript();
        }

        s = s + "\r\n# eof Digsim";
        return s;
    }

    public void ImportFromScript(DigSimFrame digsimframe, Pin apin[][], String s)
    {
        String s1 = "";
        Color color = MyColor.red;
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        int i1 = 0;
        int j1 = 1;
        int k1 = 1;
        int l1 = 10;
        int i2 = 10;
        int j2 = 10;
        int k2 = 10;
        String s2 = "F";
        String s3 = "F";
        String s4 = "F";
        String s5 = "";
        String s6 = new String("");
        String s7 = new String("");
        StringTokenizer stringtokenizer = new StringTokenizer(s, "\r\n^");
        if(!stringtokenizer.nextToken().startsWith("# Digsim file"))
        {
            s7 = "Désolé, ce n'est pas un fichier script DigSim. Il est possible que ce soit un fichier avec l'extension .dig !\r\n";
            DisplayScriptErrors(digsimframe, s7);
            return;
        }
        while(stringtokenizer.hasMoreTokens())
        {
            String s8 = new String(stringtokenizer.nextToken());
            if(!s8.equals(""))
                if(!s8.equals(" "));
            if(s8.startsWith("["))
            {
                StringTokenizer stringtokenizer1 = new StringTokenizer(s8, "|");
                String s9 = new String();
                s9 = stringtokenizer1.nextToken();
                if(stringtokenizer1.hasMoreTokens())
                    s1 = stringtokenizer1.nextToken();
                else
                    s7 = new String(s7 + "Not able to find name of component\r\n");
                if(stringtokenizer1.hasMoreTokens())
                    s9 = stringtokenizer1.nextToken();
                if("pos".equals(s9))
                {
                    try
                    {
                        if(stringtokenizer1.hasMoreTokens())
                            i = Integer.parseInt(stringtokenizer1.nextToken());
                        if(stringtokenizer1.hasMoreTokens())
                            j = Integer.parseInt(stringtokenizer1.nextToken());
                    }
                    catch(NumberFormatException numberformatexception)
                    {
                        s7 = new String(s7 + "Not able to find position of component " + s1 + "\r\n");
                    }
                    if(stringtokenizer1.hasMoreTokens())
                        s9 = stringtokenizer1.nextToken();
                }
                if("pos2".equals(s9))
                {
                    try
                    {
                        if(stringtokenizer1.hasMoreTokens())
                            k = Integer.parseInt(stringtokenizer1.nextToken());
                        if(stringtokenizer1.hasMoreTokens())
                            l = Integer.parseInt(stringtokenizer1.nextToken());
                    }
                    catch(NumberFormatException numberformatexception1)
                    {
                        s7 = new String(s7 + "Not able to find position of component " + s1 + "\r\n");
                    }
                    if(stringtokenizer1.hasMoreTokens())
                        s9 = stringtokenizer1.nextToken();
                }
                if("rotation".equals(s9))
                {
                    try
                    {
                        if(stringtokenizer1.hasMoreTokens())
                            i1 = Integer.parseInt(stringtokenizer1.nextToken());
                    }
                    catch(NumberFormatException numberformatexception2)
                    {
                        s7 = new String(s7 + "Not able to find rotation of component " + s1 + "\r\n");
                    }
                    if(stringtokenizer1.hasMoreTokens())
                        s9 = stringtokenizer1.nextToken();
                }
                if("color".equals(s9))
                {
                    if(stringtokenizer1.hasMoreTokens())
                        color = StringToColor(stringtokenizer1.nextToken());
                    else
                        s7 = new String(s7 + "Not able to find color of component " + s1 + "\r\n");
                    if(stringtokenizer1.hasMoreTokens())
                        s9 = stringtokenizer1.nextToken();
                }
                if("Text".equals(s9))
                {
                    if(stringtokenizer1.hasMoreTokens())
                        s5 = stringtokenizer1.nextToken();
                    else
                        s7 = new String(s7 + "Not able to find value of text of component " + s1 + "\r\n");
                    if(stringtokenizer1.hasMoreTokens())
                        s9 = stringtokenizer1.nextToken();
                }
                if("cycle".equals(s9))
                {
                    try
                    {
                        if(stringtokenizer1.hasMoreTokens())
                            j1 = Integer.parseInt(stringtokenizer1.nextToken());
                    }
                    catch(NumberFormatException numberformatexception3)
                    {
                        s7 = new String(s7 + "Not able to find value of cycle of Oscillator2\r\n");
                    }
                    if(stringtokenizer1.hasMoreTokens())
                        s9 = stringtokenizer1.nextToken();
                }
                if("stop".equals(s9))
                {
                    try
                    {
                        if(stringtokenizer1.hasMoreTokens())
                            l1 = Integer.parseInt(stringtokenizer1.nextToken());
                    }
                    catch(NumberFormatException numberformatexception4)
                    {
                        s7 = new String(s7 + "Not able to find stop value of Monostable\r\n");
                    }
                    if(stringtokenizer1.hasMoreTokens())
                        s9 = stringtokenizer1.nextToken();
                }
                if("delay".equals(s9))
                {
                    try
                    {
                        if(stringtokenizer1.hasMoreTokens())
                            k1 = Integer.parseInt(stringtokenizer1.nextToken());
                    }
                    catch(NumberFormatException numberformatexception5)
                    {
                        s7 = new String(s7 + "Not able to find delay value of Monostable\r\n");
                    }
                    if(stringtokenizer1.hasMoreTokens())
                        s9 = stringtokenizer1.nextToken();
                }
                if("xpla".equals(s9))
                {
                    try
                    {
                        if(stringtokenizer1.hasMoreTokens())
                            s2 = stringtokenizer1.nextToken();
                    }
                    catch(NumberFormatException numberformatexception6)
                    {
                        s7 = new String(s7 + "Not able to find largeur value of PLA5x4\r\n");
                    }
                    if(stringtokenizer1.hasMoreTokens())
                        s9 = stringtokenizer1.nextToken();
                }
                if("ypla".equals(s9))
                {
                    try
                    {
                        if(stringtokenizer1.hasMoreTokens())
                            s3 = stringtokenizer1.nextToken();
                    }
                    catch(NumberFormatException numberformatexception7)
                    {
                        s7 = new String(s7 + "Not able to find hauteur value of PLA5x4\r\n");
                    }
                    if(stringtokenizer1.hasMoreTokens())
                        s9 = stringtokenizer1.nextToken();
                }
                if("zpla".equals(s9))
                {
                    try
                    {
                        if(stringtokenizer1.hasMoreTokens())
                            s4 = stringtokenizer1.nextToken();
                    }
                    catch(NumberFormatException numberformatexception8)
                    {
                        s7 = new String(s7 + "Not able to find largeur value of PLA5x4\r\n");
                    }
                    if(stringtokenizer1.hasMoreTokens())
                        s9 = stringtokenizer1.nextToken();
                }
                if("xrect".equals(s9))
                {
                    try
                    {
                        if(stringtokenizer1.hasMoreTokens())
                            i2 = Integer.parseInt(stringtokenizer1.nextToken());
                    }
                    catch(NumberFormatException numberformatexception9)
                    {
                        s7 = new String(s7 + "Not able to find largeur value of Zone Rectangle\r\n");
                    }
                    if(stringtokenizer1.hasMoreTokens())
                        s9 = stringtokenizer1.nextToken();
                }
                if("yrect".equals(s9))
                {
                    try
                    {
                        if(stringtokenizer1.hasMoreTokens())
                            j2 = Integer.parseInt(stringtokenizer1.nextToken());
                    }
                    catch(NumberFormatException numberformatexception10)
                    {
                        s7 = new String(s7 + "Not able to find hauteur value of Zone Rectangle\r\n");
                    }
                    if(stringtokenizer1.hasMoreTokens())
                        s9 = stringtokenizer1.nextToken();
                }
                if("zrect".equals(s9))
                {
                    try
                    {
                        if(stringtokenizer1.hasMoreTokens())
                            k2 = Integer.parseInt(stringtokenizer1.nextToken());
                    }
                    catch(NumberFormatException numberformatexception11)
                    {
                        s7 = new String(s7 + "Not able to find largeur value of Zone Rectangle\r\n");
                    }
                    if(stringtokenizer1.hasMoreTokens())
                        s9 = stringtokenizer1.nextToken();
                }
                if("Mem".equals(s9))
                {
                    if(stringtokenizer1.hasMoreTokens())
                    {
                        String s10 = new String(stringtokenizer1.nextToken());
                        s5 = "";
                        for(int l2 = 0; l2 < s10.length(); l2++)
                            if(s10.charAt(l2) != '\r')
                            {
                                char c = s10.charAt(l2);
                                s5 = s5 + c;
                            } else
                            {
                                l2--;
                            }

                    } else
                    {
                        s7 = new String(s7 + "Not able to find Memory value of Ram or Rom\r\n");
                    }
                    if(stringtokenizer1.hasMoreTokens())
                        s9 = stringtokenizer1.nextToken();
                }
                if("]".equals(s9) || "] ".equals(s9) || "]  ".equals(s9))
                {
                    if(!LoadComponent(apin, s1, color, i, j, i1, k, l, j1, k1, l1, i2, j2, k2, s2, s3, s4, s5))
                        s7 = new String(s7 + "Can not load component " + s1 + "\r\n");
                } else
                {
                    s7 = new String(s7 + "\260  Un composant sera manquant car une ligne de ce script n'est pas correcte. Noter que la description de tous les composants doit commencer par ^[| et se terminer par |] \r\n");
                }
            }
            if(s8.startsWith("\r\n# eof Digsim"))
                break;
            if(!s8.startsWith("\r\n# eof Digsim") && !s8.startsWith("[") && stringtokenizer.hasMoreTokens())
                s7 = new String("\260\260 Un composant sera manquant car sa description ne commence pas de la bonne manière. Vérifiez que la description de tous les composants commence bien par: ^[| \r\n");
            s6 = new String(s6 + s7);
            s7 = new String("");
        }
        if(!s6.equals(""))
            DisplayScriptErrors(digsimframe, s6);
    }

    private void DisplayScriptErrors(DigSimFrame digsimframe, String s)
    {
        digsimframe.applet.MyScriptFrame = new ScriptFrame(digsimframe, ScriptFrame.EXPORT);
        digsimframe.applet.MyScriptFrame.setScript("ATTENTION! Problème de conversion en schéma; ce script est corrompu ou modifié!\r\n\r\n" + s);
        digsimframe.applet.MyScriptFrame.show();
    }

    public boolean LoadComponent(Pin apin[][], String s, Color color, int i, int j, int k, int l,
            int i1, int j1, int k1, int l1, int i2, int j2, int k2,
            String s1, String s2, String s3, String s4)
    {
        if("Vcc".equals(s))
        {
            Vcc vcc = new Vcc(apin, i, j);
            for(int l2 = 0; l2 < k; l2++)
                vcc.rotate();

            addComponent(vcc);
        } else
        if("GND".equals(s))
        {
            GND gnd = new GND(apin, i, j);
            addComponent(gnd);
        } else
        if("Colorieur".equals(s))
        {
            Colorieur colorieur = new Colorieur(apin, i, j);
            for(int i3 = 0; i3 < k; i3++)
                colorieur.rotate();

            addComponent(colorieur);
        } else
        if("Wire".equals(s))
            addComponent(new Wire(apin, i, j, l, i1));
        else
        if("Buffer".equals(s))
        {
            Buffer buffer = new Buffer(apin, i, j);
            for(int j3 = 0; j3 < k; j3++)
                buffer.rotate();

            addComponent(buffer);
        } else
        if("Inverter".equals(s))
        {
            Inverter inverter = new Inverter(apin, i, j);
            for(int k3 = 0; k3 < k; k3++)
                inverter.rotate();

            addComponent(inverter);
        } else
        if("TwoAndPort".equals(s))
        {
            TwoAndPort twoandport = new TwoAndPort(apin, i, j);
            for(int l3 = 0; l3 < k; l3++)
                twoandport.rotate();

            addComponent(twoandport);
        } else
        if("ThreeAndPort".equals(s))
        {
            ThreeAndPort threeandport = new ThreeAndPort(apin, i, j);
            for(int i4 = 0; i4 < k; i4++)
                threeandport.rotate();

            addComponent(threeandport);
        } else
        if("FourAndPort".equals(s))
        {
            FourAndPort fourandport = new FourAndPort(apin, i, j);
            for(int j4 = 0; j4 < k; j4++)
                fourandport.rotate();

            addComponent(fourandport);
        } else
        if("TwoNandPort".equals(s))
        {
            TwoNandPort twonandport = new TwoNandPort(apin, i, j);
            for(int k4 = 0; k4 < k; k4++)
                twonandport.rotate();

            addComponent(twonandport);
        } else
        if("ThreeNandPort".equals(s))
        {
            ThreeNandPort threenandport = new ThreeNandPort(apin, i, j);
            for(int l4 = 0; l4 < k; l4++)
                threenandport.rotate();

            addComponent(threenandport);
        } else
        if("FourNandPort".equals(s))
        {
            FourNandPort fournandport = new FourNandPort(apin, i, j);
            for(int i5 = 0; i5 < k; i5++)
                fournandport.rotate();

            addComponent(fournandport);
        } else
        if("TwoOrPort".equals(s))
        {
            TwoOrPort twoorport = new TwoOrPort(apin, i, j);
            for(int j5 = 0; j5 < k; j5++)
                twoorport.rotate();

            addComponent(twoorport);
        } else
        if("ThreeOrPort".equals(s))
        {
            ThreeOrPort threeorport = new ThreeOrPort(apin, i, j);
            for(int k5 = 0; k5 < k; k5++)
                threeorport.rotate();

            addComponent(threeorport);
        } else
        if("FourOrPort".equals(s))
        {
            FourOrPort fourorport = new FourOrPort(apin, i, j);
            for(int l5 = 0; l5 < k; l5++)
                fourorport.rotate();

            addComponent(fourorport);
        } else
        if("TwoNorPort".equals(s))
        {
            TwoNorPort twonorport = new TwoNorPort(apin, i, j);
            for(int i6 = 0; i6 < k; i6++)
                twonorport.rotate();

            addComponent(twonorport);
        } else
        if("ThreeNorPort".equals(s))
        {
            ThreeNorPort threenorport = new ThreeNorPort(apin, i, j);
            for(int j6 = 0; j6 < k; j6++)
                threenorport.rotate();

            addComponent(threenorport);
        } else
        if("FourNorPort".equals(s))
        {
            FourNorPort fournorport = new FourNorPort(apin, i, j);
            for(int k6 = 0; k6 < k; k6++)
                fournorport.rotate();

            addComponent(fournorport);
        } else
        if("FiveNorPort".equals(s))
        {
            FiveNorPort fivenorport = new FiveNorPort(apin, i, j);
            for(int l6 = 0; l6 < k; l6++)
                fivenorport.rotate();

            addComponent(fivenorport);
        } else
        if("TwoXorPort".equals(s))
        {
            TwoXorPort twoxorport = new TwoXorPort(apin, i, j);
            for(int i7 = 0; i7 < k; i7++)
                twoxorport.rotate();

            addComponent(twoxorport);
        } else
        if("TwoXnorPort".equals(s))
        {
            TwoXnorPort twoxnorport = new TwoXnorPort(apin, i, j);
            for(int j7 = 0; j7 < k; j7++)
                twoxnorport.rotate();

            addComponent(twoxnorport);
        } else
        if("Switch".equals(s))
        {
            Switch switch1 = new Switch(apin, i, j);
            for(int k7 = 0; k7 < k; k7++)
                switch1.rotate();

            addComponent(switch1);
        } else
        if("SwitchT".equals(s))
        {
            SwitchT switcht = new SwitchT(apin, i, j);
            for(int l7 = 0; l7 < k; l7++)
                switcht.rotate();

            addComponent(switcht);
        } else
        if("PushButton".equals(s))
        {
            PushButton pushbutton = new PushButton(apin, i, j);
            for(int i8 = 0; i8 < k; i8++)
                pushbutton.rotate();

            addComponent(pushbutton);
        } else
        if("SRLatch".equals(s))
        {
            SRLatch srlatch = new SRLatch(apin, i, j);
            for(int j8 = 0; j8 < k; j8++)
                srlatch.rotate();

            addComponent(srlatch);
        } else
        if("GatedSRLatch".equals(s))
        {
            GatedSRLatch gatedsrlatch = new GatedSRLatch(apin, i, j);
            for(int k8 = 0; k8 < k; k8++)
                gatedsrlatch.rotate();

            addComponent(gatedsrlatch);
        } else
        if("DLatch".equals(s))
        {
            DLatch dlatch = new DLatch(apin, i, j);
            for(int l8 = 0; l8 < k; l8++)
                dlatch.rotate();

            addComponent(dlatch);
        } else
        if("DFlipFlop".equals(s))
        {
            DFlipFlop dflipflop = new DFlipFlop(apin, i, j);
            for(int i9 = 0; i9 < k; i9++)
                dflipflop.rotate();

            addComponent(dflipflop);
        } else
        if("JKFlipFlop".equals(s))
        {
            JKFlipFlop jkflipflop = new JKFlipFlop(apin, i, j);
            for(int j9 = 0; j9 < k; j9++)
                jkflipflop.rotate();

            addComponent(jkflipflop);
        } else
        if("JKRSFlipFlop".equals(s))
        {
            JKRSFlipFlop jkrsflipflop = new JKRSFlipFlop(apin, i, j);
            for(int k9 = 0; k9 < k; k9++)
                jkrsflipflop.rotate();

            addComponent(jkrsflipflop);
        } else
        if("JbKRSFlipFlop".equals(s))
        {
            JbKRSFlipFlop jbkrsflipflop = new JbKRSFlipFlop(apin, i, j);
            for(int l9 = 0; l9 < k; l9++)
                jbkrsflipflop.rotate();

            addComponent(jbkrsflipflop);
        } else
        if("EdgeTriggeredTFlipFlop".equals(s))
        {
            EdgeTriggeredTFlipFlop edgetriggeredtflipflop = new EdgeTriggeredTFlipFlop(apin, i, j);
            for(int i10 = 0; i10 < k; i10++)
                edgetriggeredtflipflop.rotate();

            addComponent(edgetriggeredtflipflop);
        } else
        if("TFlipFlop".equals(s))
        {
            TFlipFlop tflipflop = new TFlipFlop(apin, i, j);
            for(int j10 = 0; j10 < k; j10++)
                tflipflop.rotate();

            addComponent(tflipflop);
        } else
        if("OctalDFlipFlop".equals(s))
        {
            OctalDFlipFlop octaldflipflop = new OctalDFlipFlop(apin, i, j);
            for(int k10 = 0; k10 < k; k10++)
                octaldflipflop.rotate();

            addComponent(octaldflipflop);
        } else
        if("OctalDFlipFlopInverter".equals(s))
        {
            OctalDFlipFlopInverter octaldflipflopinverter = new OctalDFlipFlopInverter(apin, i, j);
            for(int l10 = 0; l10 < k; l10++)
                octaldflipflopinverter.rotate();

            addComponent(octaldflipflopinverter);
        } else
        if("OctalLatch".equals(s))
        {
            OctalLatch octallatch = new OctalLatch(apin, i, j);
            for(int i11 = 0; i11 < k; i11++)
                octallatch.rotate();

            addComponent(octallatch);
        } else
        if("Oscilator".equals(s))
        {
            Oscilator oscilator = new Oscilator(apin, i, j);
            for(int j11 = 0; j11 < k; j11++)
                oscilator.rotate();

            addComponent(oscilator);
        } else
        if("Oscillator2".equals(s))
        {
            Oscillator2 oscillator2 = new Oscillator2(apin, i, j, j1);
            for(int k11 = 0; k11 < k; k11++)
                oscillator2.rotate();

            addComponent(oscillator2);
        } else
        if("Oscillateur1s".equals(s))
        {
            Oscillateur1s oscillateur1s = new Oscillateur1s(apin, i, j);
            for(int l11 = 0; l11 < k; l11++)
                oscillateur1s.rotate();

            addComponent(oscillateur1s);
        } else
        if("Oscillateur500ms".equals(s))
        {
            Oscillateur500ms oscillateur500ms = new Oscillateur500ms(apin, i, j);
            for(int i12 = 0; i12 < k; i12++)
                oscillateur500ms.rotate();

            addComponent(oscillateur500ms);
        } else
        if("Oscillateur100ms".equals(s))
        {
            Oscillateur100ms oscillateur100ms = new Oscillateur100ms(apin, i, j);
            for(int j12 = 0; j12 < k; j12++)
                oscillateur100ms.rotate();

            addComponent(oscillateur100ms);
        } else
        if("BCDToSevenSegDecoder".equals(s))
        {
            BCDToSevenSegDecoder bcdtosevensegdecoder = new BCDToSevenSegDecoder(apin, i, j);
            for(int k12 = 0; k12 < k; k12++)
                bcdtosevensegdecoder.rotate();

            addComponent(bcdtosevensegdecoder);
        } else
        if("FullAdder".equals(s))
        {
            FullAdder fulladder = new FullAdder(apin, i, j);
            for(int l12 = 0; l12 < k; l12++)
                fulladder.rotate();

            addComponent(fulladder);
        } else
        if("ThreeToEightLineDecoder".equals(s))
        {
            ThreeToEightLineDecoder threetoeightlinedecoder = new ThreeToEightLineDecoder(apin, i, j);
            for(int i13 = 0; i13 < k; i13++)
                threetoeightlinedecoder.rotate();

            addComponent(threetoeightlinedecoder);
        } else
        if("FourBitBinaryCounter".equals(s))
        {
            FourBitBinaryCounter fourbitbinarycounter = new FourBitBinaryCounter(apin, i, j);
            for(int j13 = 0; j13 < k; j13++)
                fourbitbinarycounter.rotate();

            addComponent(fourbitbinarycounter);
        } else
        if("FourBitBinaryUpDownCounter".equals(s))
        {
            FourBitBinaryUpDownCounter fourbitbinaryupdowncounter = new FourBitBinaryUpDownCounter(apin, i, j);
            for(int k13 = 0; k13 < k; k13++)
                fourbitbinaryupdowncounter.rotate();

            addComponent(fourbitbinaryupdowncounter);
        } else
        if("FourBitBCDUpDownCounter".equals(s))
        {
            FourBitBCDUpDownCounter fourbitbcdupdowncounter = new FourBitBCDUpDownCounter(apin, i, j);
            for(int l13 = 0; l13 < k; l13++)
                fourbitbcdupdowncounter.rotate();

            addComponent(fourbitbcdupdowncounter);
        } else
        if("EightBitBinaryUpDownCounter".equals(s))
        {
            EightBitBinaryUpDownCounter eightbitbinaryupdowncounter = new EightBitBinaryUpDownCounter(apin, i, j);
            for(int i14 = 0; i14 < k; i14++)
                eightbitbinaryupdowncounter.rotate();

            addComponent(eightbitbinaryupdowncounter);
        } else
        if("Monostable".equals(s))
        {
            Monostable monostable = new Monostable(apin, i, j, l1, k1);
            for(int j14 = 0; j14 < k; j14++)
                monostable.rotate();

            addComponent(monostable);
        } else
        if("PLA5x4".equals(s))
        {
            PLA5x4 pla5x4 = new PLA5x4(apin, i, j, s1, s2, s3);
            for(int k14 = 0; k14 < k; k14++)
                pla5x4.rotate();

            addComponent(pla5x4);
        } else
        if("TraitsCie".equals(s))
        {
            TraitsCie traitscie = new TraitsCie(apin, i, j, i2, j2, k2);
            for(int l14 = 0; l14 < k; l14++)
                traitscie.rotate();

            addComponent(traitscie);
        } else
        if("ShiftRegister".equals(s))
        {
            ShiftRegister shiftregister = new ShiftRegister(apin, i, j);
            for(int i15 = 0; i15 < k; i15++)
                shiftregister.rotate();

            addComponent(shiftregister);
        } else
        if("CounterBCDxx160".equals(s))
        {
            CounterBCDxx160 counterbcdxx160 = new CounterBCDxx160(apin, i, j);
            for(int j15 = 0; j15 < k; j15++)
                counterbcdxx160.rotate();

            addComponent(counterbcdxx160);
        } else
        if("Counterxx161".equals(s))
        {
            Counterxx161 counterxx161 = new Counterxx161(apin, i, j);
            for(int k15 = 0; k15 < k; k15++)
                counterxx161.rotate();

            addComponent(counterxx161);
        } else
        if("CounterBCDxx162".equals(s))
        {
            CounterBCDxx162 counterbcdxx162 = new CounterBCDxx162(apin, i, j);
            for(int l15 = 0; l15 < k; l15++)
                counterbcdxx162.rotate();

            addComponent(counterbcdxx162);
        } else
        if("Counterxx163".equals(s))
        {
            Counterxx163 counterxx163 = new Counterxx163(apin, i, j);
            for(int i16 = 0; i16 < k; i16++)
                counterxx163.rotate();

            addComponent(counterxx163);
        } else
        if("Rom".equals(s))
        {
            Rom rom = new Rom(apin, i, j, s4);
            for(int j16 = 0; j16 < k; j16++)
                rom.rotate();

            addComponent(rom);
        } else
        if("Ram".equals(s))
        {
            Ram ram = new Ram(apin, i, j, s4);
            for(int k16 = 0; k16 < k; k16++)
                ram.rotate();

            addComponent(ram);
        } else
        if("EightBitSerInShiftReg".equals(s))
        {
            EightBitSerInShiftReg eightbitserinshiftreg = new EightBitSerInShiftReg(apin, i, j);
            for(int l16 = 0; l16 < k; l16++)
                eightbitserinshiftreg.rotate();

            addComponent(eightbitserinshiftreg);
        } else
        if("EightBitParInShiftReg".equals(s))
        {
            EightBitParInShiftReg eightbitparinshiftreg = new EightBitParInShiftReg(apin, i, j);
            for(int i17 = 0; i17 < k; i17++)
                eightbitparinshiftreg.rotate();

            addComponent(eightbitparinshiftreg);
        } else
        if("Multiplexer2to1".equals(s))
        {
            Multiplexer2to1 multiplexer2to1 = new Multiplexer2to1(apin, i, j);
            for(int j17 = 0; j17 < k; j17++)
                multiplexer2to1.rotate();

            addComponent(multiplexer2to1);
        } else
        if("Multiplexer4to1".equals(s))
        {
            Multiplexer4to1 multiplexer4to1 = new Multiplexer4to1(apin, i, j);
            for(int k17 = 0; k17 < k; k17++)
                multiplexer4to1.rotate();

            addComponent(multiplexer4to1);
        } else
        if("Multiplexer8to1".equals(s))
        {
            Multiplexer8to1 multiplexer8to1 = new Multiplexer8to1(apin, i, j);
            for(int l17 = 0; l17 < k; l17++)
                multiplexer8to1.rotate();

            addComponent(multiplexer8to1);
        } else
        if("SequencerCell".equals(s))
        {
            SequencerCell sequencercell = new SequencerCell(apin, i, j);
            for(int i18 = 0; i18 < k; i18++)
                sequencercell.rotate();

            addComponent(sequencercell);
        } else
        if("BICOLORLEDVERTICAL1".equals(s))
        {
            BICOLORLEDVERTICAL1 bicolorledvertical1 = new BICOLORLEDVERTICAL1(apin, i, j);
            for(int j18 = 0; j18 < k; j18++)
                bicolorledvertical1.rotate();

            addComponent(bicolorledvertical1);
        } else
        if("CtrBcdBinRazSync".equals(s))
        {
            CtrBcdBinRazSync ctrbcdbinrazsync = new CtrBcdBinRazSync(apin, i, j);
            for(int k18 = 0; k18 < k; k18++)
                ctrbcdbinrazsync.rotate();

            addComponent(ctrbcdbinrazsync);
        } else
        if("CtrBcdBinRazASync".equals(s))
        {
            CtrBcdBinRazASync ctrbcdbinrazasync = new CtrBcdBinRazASync(apin, i, j);
            for(int l18 = 0; l18 < k; l18++)
                ctrbcdbinrazasync.rotate();

            addComponent(ctrbcdbinrazasync);
        } else
        if("Ctr5BinRazASync".equals(s))
        {
            Ctr5BinRazASync ctr5binrazasync = new Ctr5BinRazASync(apin, i, j);
            for(int i19 = 0; i19 < k; i19++)
                ctr5binrazasync.rotate();

            addComponent(ctr5binrazasync);
        } else
        if("PERCEUSE".equals(s))
        {
            PERCEUSE perceuse = new PERCEUSE(apin, i, j);
            for(int j19 = 0; j19 < k; j19++)
                perceuse.rotate();

            addComponent(perceuse);
        } else
        if("INPUTINVERTER".equals(s))
        {
            INPUTINVERTER inputinverter = new INPUTINVERTER(apin, i, j);
            for(int k19 = 0; k19 < k; k19++)
                inputinverter.rotate();

            addComponent(inputinverter);
        } else
        if("OUTPUTINVERTER".equals(s))
        {
            OUTPUTINVERTER outputinverter = new OUTPUTINVERTER(apin, i, j);
            for(int l19 = 0; l19 < k; l19++)
                outputinverter.rotate();

            addComponent(outputinverter);
        } else
        if("CLEF".equals(s))
        {
            CLEF clef = new CLEF(apin, i, j);
            for(int i20 = 0; i20 < k; i20++)
                clef.rotate();

            addComponent(clef);
        } else
        if("CelluleRC".equals(s))
        {
            CelluleRC cellulerc = new CelluleRC(apin, i, j);
            for(int j20 = 0; j20 < k; j20++)
                cellulerc.rotate();

            addComponent(cellulerc);
        } else
        if("Comparateur8bits".equals(s))
        {
            Comparateur8bits comparateur8bits = new Comparateur8bits(apin, i, j);
            for(int k20 = 0; k20 < k; k20++)
                comparateur8bits.rotate();

            addComponent(comparateur8bits);
        } else
        if("Comparateur4bits".equals(s))
        {
            Comparateur4bits comparateur4bits = new Comparateur4bits(apin, i, j);
            for(int l20 = 0; l20 < k; l20++)
                comparateur4bits.rotate();

            addComponent(comparateur4bits);
        } else
        if("JETON".equals(s))
        {
            JETON jeton = new JETON(apin, i, j);
            for(int i21 = 0; i21 < k; i21++)
                jeton.rotate();

            addComponent(jeton);
        } else
        if("FEUXCARREFOUR".equals(s))
        {
            FEUXCARREFOUR feuxcarrefour = new FEUXCARREFOUR(apin, i, j);
            for(int j21 = 0; j21 < k; j21++)
                feuxcarrefour.rotate();

            addComponent(feuxcarrefour);
        } else
        if("Caption".equals(s))
        {
            Caption caption = new Caption(i, j, s4);
            for(int k21 = 0; k21 < k; k21++)
                caption.rotate();

            addComponent(caption);
        } else
        if("CaptionBlack".equals(s))
        {
            CaptionBlack captionblack = new CaptionBlack(i, j, s4);
            for(int l21 = 0; l21 < k; l21++)
                captionblack.rotate();

            addComponent(captionblack);
        } else
        if("CaptionRed".equals(s))
        {
            CaptionRed captionred = new CaptionRed(i, j, s4);
            for(int i22 = 0; i22 < k; i22++)
                captionred.rotate();

            addComponent(captionred);
        } else
        if("CaptionRdP".equals(s))
        {
            CaptionRdP captionrdp = new CaptionRdP(i, j, s4);
            for(int j22 = 0; j22 < k; j22++)
                captionrdp.rotate();

            addComponent(captionrdp);
        } else
        if("CaptionBlue".equals(s))
        {
            CaptionBlue captionblue = new CaptionBlue(i, j, s4);
            for(int k22 = 0; k22 < k; k22++)
                captionblue.rotate();

            addComponent(captionblue);
        } else
        if("CaptionGreen".equals(s))
        {
            CaptionGreen captiongreen = new CaptionGreen(i, j, s4);
            for(int l22 = 0; l22 < k; l22++)
                captiongreen.rotate();

            addComponent(captiongreen);
        } else
        if("LED".equals(s))
        {
            LED led = new LED(apin, color, i, j);
            for(int i23 = 0; i23 < k; i23++)
                led.rotate();

            addComponent(led);
        } else
        if("BiColorLED".equals(s))
        {
            BiColorLED bicolorled = new BiColorLED(apin, i, j);
            for(int j23 = 0; j23 < k; j23++)
                bicolorled.rotate();

            addComponent(bicolorled);
        } else
        if("SevenSegmentDisplay".equals(s))
        {
            SevenSegmentDisplay sevensegmentdisplay = new SevenSegmentDisplay(apin, color, i, j);
            for(int k23 = 0; k23 < k; k23++)
                sevensegmentdisplay.rotate();

            addComponent(sevensegmentdisplay);
        } else
        if("Probe".equals(s))
        {
            Probe probe = new Probe(apin, i, j, s4, false, false);
            for(int l23 = 0; l23 < k; l23++)
                probe.rotate();

            addComponent(probe);
        } else
        if("ProbeUp".equals(s))
        {
            Probe probe1 = new Probe(apin, i, j, s4, true, false);
            for(int i24 = 0; i24 < k; i24++)
                probe1.rotate();

            addComponent(probe1);
        } else
        if("ProbeDn".equals(s))
        {
            Probe probe2 = new Probe(apin, i, j, s4, false, true);
            for(int j24 = 0; j24 < k; j24++)
                probe2.rotate();

            addComponent(probe2);
        } else
        if("SoundGenerator".equals(s))
        {
            SoundGenerator soundgenerator = new SoundGenerator(apin, i, j);
            for(int k24 = 0; k24 < k; k24++)
                soundgenerator.rotate();

            addComponent(soundgenerator);
        } else
        if("AlarmGenerator".equals(s))
        {
            AlarmGenerator alarmgenerator = new AlarmGenerator(apin, i, j);
            for(int l24 = 0; l24 < k; l24++)
                alarmgenerator.rotate();

            addComponent(alarmgenerator);
        } else
        if("Switch2RT".equals(s))
        {
            Switch2RT switch2rt = new Switch2RT(apin, i, j);
            for(int i25 = 0; i25 < k; i25++)
                switch2rt.rotate();

            addComponent(switch2rt);
        } else
        if("Relay2RT".equals(s))
        {
            Relay2RT relay2rt = new Relay2RT(apin, i, j);
            for(int j25 = 0; j25 < k; j25++)
                relay2rt.rotate();

            addComponent(relay2rt);
        } else
        if("MULT8x8".equals(s))
        {
            MULT8x8 mult8x8 = new MULT8x8(apin, i, j);
            for(int k25 = 0; k25 < k; k25++)
                mult8x8.rotate();

            addComponent(mult8x8);
        } else
        if("ADD8".equals(s))
        {
            ADD8 add8 = new ADD8(apin, i, j);
            for(int l25 = 0; l25 < k; l25++)
                add8.rotate();

            addComponent(add8);
        } else
        if("MAC8x8".equals(s))
        {
            MAC8x8 mac8x8 = new MAC8x8(apin, i, j);
            for(int i26 = 0; i26 < k; i26++)
                mac8x8.rotate();

            addComponent(mac8x8);
        } else
        if("Oscillateur2T".equals(s))
        {
            Oscillateur2T oscillateur2t = new Oscillateur2T(apin, i, j);
            for(int j26 = 0; j26 < k; j26++)
                oscillateur2t.rotate();

            addComponent(oscillateur2t);
        } else
        if("FourMUX2to1".equals(s))
        {
            FourMUX2to1 fourmux2to1 = new FourMUX2to1(apin, i, j);
            for(int k26 = 0; k26 < k; k26++)
                fourmux2to1.rotate();

            addComponent(fourmux2to1);
        } else
        if("Buffer8bits".equals(s))
        {
            Buffer8bits buffer8bits = new Buffer8bits(apin, i, j);
            for(int l26 = 0; l26 < k; l26++)
                buffer8bits.rotate();

            addComponent(buffer8bits);
        } else
        if("Transceiver8bits".equals(s))
        {
            Transceiver8bits transceiver8bits = new Transceiver8bits(apin, i, j);
            for(int i27 = 0; i27 < k; i27++)
                transceiver8bits.rotate();

            addComponent(transceiver8bits);
        } else
        if("Buffer2x4bits".equals(s))
        {
            Buffer2x4bits buffer2x4bits = new Buffer2x4bits(apin, i, j);
            for(int j27 = 0; j27 < k; j27++)
                buffer2x4bits.rotate();

            addComponent(buffer2x4bits);
        } else
        if("ChariotXYhbgd".equals(s))
        {
            ChariotXYhbgd chariotxyhbgd = new ChariotXYhbgd(apin, i, j);
            for(int k27 = 0; k27 < k; k27++)
                chariotxyhbgd.rotate();

            addComponent(chariotxyhbgd);
        } else
        if("ChariotXY7bits".equals(s))
        {
            ChariotXY7bits chariotxy7bits = new ChariotXY7bits(apin, i, j);
            for(int l27 = 0; l27 < k; l27++)
                chariotxy7bits.rotate();

            addComponent(chariotxy7bits);
        } else
        if("ChariotXYmixte".equals(s))
        {
            ChariotXYmixte chariotxymixte = new ChariotXYmixte(apin, i, j);
            for(int i28 = 0; i28 < k; i28++)
                chariotxymixte.rotate();

            addComponent(chariotxymixte);
        } else
        if("ChariotXYPaP".equals(s))
        {
            ChariotXYPaP chariotxypap = new ChariotXYPaP(apin, i, j);
            for(int j28 = 0; j28 < k; j28++)
                chariotxypap.rotate();

            addComponent(chariotxypap);
        } else
        if("WagonsSimple".equals(s))
        {
            WagonsSimple wagonssimple = new WagonsSimple(apin, i, j);
            for(int k28 = 0; k28 < k; k28++)
                wagonssimple.rotate();

            addComponent(wagonssimple);
        } else
        if("ChariotLibre".equals(s))
        {
            ChariotLibre chariotlibre = new ChariotLibre(apin, i, j);
            for(int l28 = 0; l28 < k; l28++)
                chariotlibre.rotate();

            addComponent(chariotlibre);
        } else
        if("Afficheur9bits".equals(s))
        {
            Afficheur9bits afficheur9bits = new Afficheur9bits(apin, i, j);
            for(int i29 = 0; i29 < k; i29++)
                afficheur9bits.rotate();

            addComponent(afficheur9bits);
        } else
        if("TRIGGER".equals(s))
        {
            TRIGGER trigger = new TRIGGER(apin, i, j);
            for(int j29 = 0; j29 < k; j29++)
                trigger.rotate();

            addComponent(trigger);
        } else
        if("WagonsAiguillages".equals(s))
        {
            WagonsAiguillages wagonsaiguillages = new WagonsAiguillages(apin, i, j);
            for(int k29 = 0; k29 < k; k29++)
                wagonsaiguillages.rotate();

            addComponent(wagonsaiguillages);
        } else
        if("Buffer3S".equals(s))
        {
            Buffer3S buffer3s = new Buffer3S(apin, i, j);
            for(int l29 = 0; l29 < k; l29++)
                buffer3s.rotate();

            addComponent(buffer3s);
        } else
        if("EmetteurSerie8bits".equals(s))
        {
            EmetteurSerie8bits emetteurserie8bits = new EmetteurSerie8bits(apin, i, j);
            for(int i30 = 0; i30 < k; i30++)
                emetteurserie8bits.rotate();

            addComponent(emetteurserie8bits);
        } else
        if("RetardFixe".equals(s))
        {
            RetardFixe retardfixe = new RetardFixe(apin, i, j);
            for(int j30 = 0; j30 < k; j30++)
                retardfixe.rotate();

            addComponent(retardfixe);
        } else
        if("RetardPur".equals(s))
        {
            RetardPur retardpur = new RetardPur(apin, i, j);
            for(int k30 = 0; k30 < k; k30++)
                retardpur.rotate();

            addComponent(retardpur);
        } else
        if("RetardRC".equals(s))
        {
            RetardRC retardrc = new RetardRC(apin, i, j);
            for(int l30 = 0; l30 < k; l30++)
                retardrc.rotate();

            addComponent(retardrc);
        } else
        if("Ram2".equals(s))
        {
            Ram2 ram2 = new Ram2(apin, i, j, s4);
            for(int i31 = 0; i31 < k; i31++)
                ram2.rotate();

            addComponent(ram2);
        } else
        if("RdPtransition".equals(s))
        {
            RdPtransition rdptransition = new RdPtransition(apin, i, j);
            for(int j31 = 0; j31 < k; j31++)
                rdptransition.rotate();

            addComponent(rdptransition);
        } else
        if("RdPfleche".equals(s))
        {
            RdPfleche rdpfleche = new RdPfleche(apin, i, j);
            for(int k31 = 0; k31 < k; k31++)
                rdpfleche.rotate();

            addComponent(rdpfleche);
        } else
        if("RdPplace".equals(s))
        {
            RdPplace rdpplace = new RdPplace(apin, i, j);
            for(int l31 = 0; l31 < k; l31++)
                rdpplace.rotate();

            addComponent(rdpplace);
        } else
        if("RdPjeton".equals(s))
        {
            RdPjeton rdpjeton = new RdPjeton(apin, i, j);
            for(int i32 = 0; i32 < k; i32++)
                rdpjeton.rotate();

            addComponent(rdpjeton);
        } else
        if("CapaC".equals(s))
        {
            CapaC capac = new CapaC(apin, i, j);
            for(int j32 = 0; j32 < k; j32++)
                capac.rotate();

            addComponent(capac);
        } else
        if("ResR".equals(s))
        {
            ResR resr = new ResR(apin, i, j);
            for(int k32 = 0; k32 < k; k32++)
                resr.rotate();

            addComponent(resr);
        } else
        if("FourBitShifter".equals(s))
        {
            FourBitShifter fourbitshifter = new FourBitShifter(apin, i, j);
            for(int l32 = 0; l32 < k; l32++)
                fourbitshifter.rotate();

            addComponent(fourbitshifter);
        } else
        if("Pendule".equals(s))
        {
            Pendule pendule = new Pendule(apin, i, j);
            for(int i33 = 0; i33 < k; i33++)
                pendule.rotate();

            addComponent(pendule);
        } else
        if("Encodeur8vers3".equals(s))
        {
            Encodeur8vers3 encodeur8vers3 = new Encodeur8vers3(apin, i, j);
            for(int j33 = 0; j33 < k; j33++)
                encodeur8vers3.rotate();

            addComponent(encodeur8vers3);
        } else
        if("Encodeur10vers4".equals(s))
        {
            Encodeur10vers4 encodeur10vers4 = new Encodeur10vers4(apin, i, j);
            for(int k33 = 0; k33 < k; k33++)
                encodeur10vers4.rotate();

            addComponent(encodeur10vers4);
        } else
        if("Boitier7404".equals(s))
        {
            Boitier7404 boitier7404 = new Boitier7404(apin, i, j);
            for(int l33 = 0; l33 < k; l33++)
                boitier7404.rotate();

            addComponent(boitier7404);
        } else
        if("Boitier7408".equals(s))
        {
            Boitier7408 boitier7408 = new Boitier7408(apin, i, j);
            for(int i34 = 0; i34 < k; i34++)
                boitier7408.rotate();

            addComponent(boitier7408);
        } else
        if("Boitier7411".equals(s))
        {
            Boitier7411 boitier7411 = new Boitier7411(apin, i, j);
            for(int j34 = 0; j34 < k; j34++)
                boitier7411.rotate();

            addComponent(boitier7411);
        } else
        if("Boitier7421".equals(s))
        {
            Boitier7421 boitier7421 = new Boitier7421(apin, i, j);
            for(int k34 = 0; k34 < k; k34++)
                boitier7421.rotate();

            addComponent(boitier7421);
        } else
        if("Boitier7432".equals(s))
        {
            Boitier7432 boitier7432 = new Boitier7432(apin, i, j);
            for(int l34 = 0; l34 < k; l34++)
                boitier7432.rotate();

            addComponent(boitier7432);
        } else
        if("Boitier744075".equals(s))
        {
            Boitier744075 boitier744075 = new Boitier744075(apin, i, j);
            for(int i35 = 0; i35 < k; i35++)
                boitier744075.rotate();

            addComponent(boitier744075);
        } else
        if("Boitier744072".equals(s))
        {
            Boitier744072 boitier744072 = new Boitier744072(apin, i, j);
            for(int j35 = 0; j35 < k; j35++)
                boitier744072.rotate();

            addComponent(boitier744072);
        } else
        if("Boitier7400".equals(s))
        {
            Boitier7400 boitier7400 = new Boitier7400(apin, i, j);
            for(int k35 = 0; k35 < k; k35++)
                boitier7400.rotate();

            addComponent(boitier7400);
        } else
        if("Boitier7410".equals(s))
        {
            Boitier7410 boitier7410 = new Boitier7410(apin, i, j);
            for(int l35 = 0; l35 < k; l35++)
                boitier7410.rotate();

            addComponent(boitier7410);
        } else
        if("Boitier7420".equals(s))
        {
            Boitier7420 boitier7420 = new Boitier7420(apin, i, j);
            for(int i36 = 0; i36 < k; i36++)
                boitier7420.rotate();

            addComponent(boitier7420);
        } else
        if("Boitier7402".equals(s))
        {
            Boitier7402 boitier7402 = new Boitier7402(apin, i, j);
            for(int j36 = 0; j36 < k; j36++)
                boitier7402.rotate();

            addComponent(boitier7402);
        } else
        if("Boitier7427".equals(s))
        {
            Boitier7427 boitier7427 = new Boitier7427(apin, i, j);
            for(int k36 = 0; k36 < k; k36++)
                boitier7427.rotate();

            addComponent(boitier7427);
        } else
        if("Boitier744002".equals(s))
        {
            Boitier744002 boitier744002 = new Boitier744002(apin, i, j);
            for(int l36 = 0; l36 < k; l36++)
                boitier744002.rotate();

            addComponent(boitier744002);
        } else
        if("Boitier7486".equals(s))
        {
            Boitier7486 boitier7486 = new Boitier7486(apin, i, j);
            for(int i37 = 0; i37 < k; i37++)
                boitier7486.rotate();

            addComponent(boitier7486);
        } else
        if("Boitier747266".equals(s))
        {
            Boitier747266 boitier747266 = new Boitier747266(apin, i, j);
            for(int j37 = 0; j37 < k; j37++)
                boitier747266.rotate();

            addComponent(boitier747266);
        } else
        if("Boitier74109".equals(s))
        {
            Boitier74109 boitier74109 = new Boitier74109(apin, i, j);
            for(int k37 = 0; k37 < k; k37++)
                boitier74109.rotate();

            addComponent(boitier74109);
        } else
        if("Cadre1024x768".equals(s))
        {
            Cadre1024x768 cadre1024x768 = new Cadre1024x768(apin, i, j);
            for(int l37 = 0; l37 < k; l37++)
                cadre1024x768.rotate();

            addComponent(cadre1024x768);
        } else
        if("Cadre1280x1024".equals(s))
        {
            Cadre1280x1024 cadre1280x1024 = new Cadre1280x1024(apin, i, j);
            for(int i38 = 0; i38 < k; i38++)
                cadre1280x1024.rotate();

            addComponent(cadre1280x1024);
        } else
        if("CadreDouble97x69".equals(s))
        {
            CadreDouble97x69 cadredouble97x69 = new CadreDouble97x69(apin, i, j);
            for(int j38 = 0; j38 < k; j38++)
                cadredouble97x69.rotate();

            addComponent(cadredouble97x69);
        } else
        if("CadreDouble80x70".equals(s))
        {
            CadreDouble80x70 cadredouble80x70 = new CadreDouble80x70(apin, i, j);
            for(int k38 = 0; k38 < k; k38++)
                cadredouble80x70.rotate();

            addComponent(cadredouble80x70);
        } else
        if("DEC8".equals(s))
        {
            DEC8 dec8 = new DEC8(apin, i, j);
            for(int l38 = 0; l38 < k; l38++)
                dec8.rotate();

            addComponent(dec8);
        } else
        if("SBPA".equals(s))
        {
            SBPA sbpa = new SBPA(apin, i, j);
            for(int i39 = 0; i39 < k; i39++)
                sbpa.rotate();

            addComponent(sbpa);
        } else
        if("SBA".equals(s))
        {
            SBA sba = new SBA(apin, i, j);
            for(int j39 = 0; j39 < k; j39++)
                sba.rotate();

            addComponent(sba);
        } else
        if("MiniBPNOPullUp".equals(s))
        {
            MiniBPNOPullUp minibpnopullup = new MiniBPNOPullUp(apin, i, j);
            for(int k39 = 0; k39 < k; k39++)
                minibpnopullup.rotate();

            addComponent(minibpnopullup);
        } else
        if("MiniBPNOPullDown".equals(s))
        {
            MiniBPNOPullDown minibpnopulldown = new MiniBPNOPullDown(apin, i, j);
            for(int l39 = 0; l39 < k; l39++)
                minibpnopulldown.rotate();

            addComponent(minibpnopulldown);
        } else
        if("MiniBPNFPullUp".equals(s))
        {
            MiniBPNFPullUp minibpnfpullup = new MiniBPNFPullUp(apin, i, j);
            for(int i40 = 0; i40 < k; i40++)
                minibpnfpullup.rotate();

            addComponent(minibpnfpullup);
        } else
        if("MiniBPNFPullDown".equals(s))
        {
            MiniBPNFPullDown minibpnfpulldown = new MiniBPNFPullDown(apin, i, j);
            for(int j40 = 0; j40 < k; j40++)
                minibpnfpulldown.rotate();

            addComponent(minibpnfpulldown);
        } else
        if("MiniSwitchNOPullUp".equals(s))
        {
            MiniSwitchNOPullUp miniswitchnopullup = new MiniSwitchNOPullUp(apin, i, j);
            for(int k40 = 0; k40 < k; k40++)
                miniswitchnopullup.rotate();

            addComponent(miniswitchnopullup);
        } else
        if("MiniSwitchNOPullDown".equals(s))
        {
            MiniSwitchNOPullDown miniswitchnopulldown = new MiniSwitchNOPullDown(apin, i, j);
            for(int l40 = 0; l40 < k; l40++)
                miniswitchnopulldown.rotate();

            addComponent(miniswitchnopulldown);
        } else
        if("MiniSwitchNFPullUp".equals(s))
        {
            MiniSwitchNFPullUp miniswitchnfpullup = new MiniSwitchNFPullUp(apin, i, j);
            for(int i41 = 0; i41 < k; i41++)
                miniswitchnfpullup.rotate();

            addComponent(miniswitchnfpullup);
        } else
        if("MiniSwitchNFPullDown".equals(s))
        {
            MiniSwitchNFPullDown miniswitchnfpulldown = new MiniSwitchNFPullDown(apin, i, j);
            for(int j41 = 0; j41 < k; j41++)
                miniswitchnfpulldown.rotate();

            addComponent(miniswitchnfpulldown);
        } else
        if("MiniBPNOAvecR".equals(s))
        {
            MiniBPNOAvecR minibpnoavecr = new MiniBPNOAvecR(apin, i, j);
            for(int k41 = 0; k41 < k; k41++)
                minibpnoavecr.rotate();

            addComponent(minibpnoavecr);
        } else
        if("MiniBPNO".equals(s))
        {
            MiniBPNO minibpno = new MiniBPNO(apin, i, j);
            for(int l41 = 0; l41 < k; l41++)
                minibpno.rotate();

            addComponent(minibpno);
        } else
        if("MiniBPNF".equals(s))
        {
            MiniBPNF minibpnf = new MiniBPNF(apin, i, j);
            for(int i42 = 0; i42 < k; i42++)
                minibpnf.rotate();

            addComponent(minibpnf);
        } else
        if("MiniSwitchNO".equals(s))
        {
            MiniSwitchNO miniswitchno = new MiniSwitchNO(apin, i, j);
            for(int j42 = 0; j42 < k; j42++)
                miniswitchno.rotate();

            addComponent(miniswitchno);
        } else
        if("MiniSwitchNF".equals(s))
        {
            MiniSwitchNF miniswitchnf = new MiniSwitchNF(apin, i, j);
            for(int k42 = 0; k42 < k; k42++)
                miniswitchnf.rotate();

            addComponent(miniswitchnf);
        } else
        if("CodeGen".equals(s))
        {
            CodeGen codegen = new CodeGen(apin, i, j);
            for(int l42 = 0; l42 < k; l42++)
                codegen.rotate();

            addComponent(codegen);
        } else
        if("CodeTest".equals(s))
        {
            CodeTest codetest = new CodeTest(apin, i, j);
            for(int i43 = 0; i43 < k; i43++)
                codetest.rotate();

            addComponent(codetest);
        } else
        if("HHmmss".equals(s))
        {
            HHmmss hhmmss = new HHmmss(apin, i, j);
            for(int j43 = 0; j43 < k; j43++)
                hhmmss.rotate();

            addComponent(hhmmss);
        } else
        if("Trans8toBus".equals(s))
        {
            Trans8toBus trans8tobus = new Trans8toBus(apin, i, j);
            for(int k43 = 0; k43 < k; k43++)
                trans8tobus.rotate();

            addComponent(trans8tobus);
        } else
        if("TransBusto8".equals(s))
        {
            TransBusto8 transbusto8 = new TransBusto8(apin, i, j);
            for(int l43 = 0; l43 < k; l43++)
                transbusto8.rotate();

            addComponent(transbusto8);
        } else
        if("Mini2BPtempo".equals(s))
        {
            Mini2BPtempo mini2bptempo = new Mini2BPtempo(apin, i, j);
            for(int i44 = 0; i44 < k; i44++)
                mini2bptempo.rotate();

            addComponent(mini2bptempo);
        } else
        if("Mini1BPtempo".equals(s))
        {
            Mini1BPtempo mini1bptempo = new Mini1BPtempo(apin, i, j);
            for(int j44 = 0; j44 < k; j44++)
                mini1bptempo.rotate();

            addComponent(mini1bptempo);
        } else
        if("Mini1BP".equals(s))
        {
            Mini1BP mini1bp = new Mini1BP(apin, i, j);
            for(int k44 = 0; k44 < k; k44++)
                mini1bp.rotate();

            addComponent(mini1bp);
        } else
        if("Mini1Inter".equals(s))
        {
            Mini1Inter mini1inter = new Mini1Inter(apin, i, j);
            for(int l44 = 0; l44 < k; l44++)
                mini1inter.rotate();

            addComponent(mini1inter);
        } else
        if("ISOnon".equals(s))
        {
            ISOnon isonon = new ISOnon(apin, i, j);
            for(int i45 = 0; i45 < k; i45++)
                isonon.rotate();

            addComponent(isonon);
        } else
        if("ISOet3".equals(s))
        {
            ISOet3 isoet3 = new ISOet3(apin, i, j);
            for(int j45 = 0; j45 < k; j45++)
                isoet3.rotate();

            addComponent(isoet3);
        } else
        if("ISOou3".equals(s))
        {
            ISOou3 isoou3 = new ISOou3(apin, i, j);
            for(int k45 = 0; k45 < k; k45++)
                isoou3.rotate();

            addComponent(isoou3);
        } else
        if("ISOet3non".equals(s))
        {
            ISOet3non isoet3non = new ISOet3non(apin, i, j);
            for(int l45 = 0; l45 < k; l45++)
                isoet3non.rotate();

            addComponent(isoet3non);
        } else
        if("ISOou3non".equals(s))
        {
            ISOou3non isoou3non = new ISOou3non(apin, i, j);
            for(int i46 = 0; i46 < k; i46++)
                isoou3non.rotate();

            addComponent(isoou3non);
        } else
        if("ISOoux3".equals(s))
        {
            ISOoux3 isooux3 = new ISOoux3(apin, i, j);
            for(int j46 = 0; j46 < k; j46++)
                isooux3.rotate();

            addComponent(isooux3);
        } else
        if("ISOoux3non".equals(s))
        {
            ISOoux3non isooux3non = new ISOoux3non(apin, i, j);
            for(int k46 = 0; k46 < k; k46++)
                isooux3non.rotate();

            addComponent(isooux3non);
        } else
        if("RailGestionDirection".equals(s))
        {
            RailGestionDirection railgestiondirection = new RailGestionDirection(apin, i, j);
            for(int l46 = 0; l46 < k; l46++)
                railgestiondirection.rotate();

            addComponent(railgestiondirection);
        } else
        if("RailGestionCanton".equals(s))
        {
            RailGestionCanton railgestioncanton = new RailGestionCanton(apin, i, j);
            for(int i47 = 0; i47 < k; i47++)
                railgestioncanton.rotate();

            addComponent(railgestioncanton);
        } else
        if("RailObliqueDroit".equals(s))
        {
            RailObliqueDroit railobliquedroit = new RailObliqueDroit(apin, i, j);
            for(int j47 = 0; j47 < k; j47++)
                railobliquedroit.rotate();

            addComponent(railobliquedroit);
        } else
        if("RailObliqueGauche".equals(s))
        {
            RailObliqueGauche railobliquegauche = new RailObliqueGauche(apin, i, j);
            for(int k47 = 0; k47 < k; k47++)
                railobliquegauche.rotate();

            addComponent(railobliquegauche);
        } else
        if("RailDroit10Train".equals(s))
        {
            RailDroit10Train raildroit10train = new RailDroit10Train(apin, i, j);
            for(int l47 = 0; l47 < k; l47++)
                raildroit10train.rotate();

            addComponent(raildroit10train);
        } else
        if("RailDroit10".equals(s))
        {
            RailDroit10 raildroit10 = new RailDroit10(apin, i, j);
            for(int i48 = 0; i48 < k; i48++)
                raildroit10.rotate();

            addComponent(raildroit10);
        } else
        if("RailDroit20".equals(s))
        {
            RailDroit20 raildroit20 = new RailDroit20(apin, i, j);
            for(int j48 = 0; j48 < k; j48++)
                raildroit20.rotate();

            addComponent(raildroit20);
        } else
        if("RailCourbe90d8x8".equals(s))
        {
            RailCourbe90d8x8 railcourbe90d8x8 = new RailCourbe90d8x8(apin, i, j);
            for(int k48 = 0; k48 < k; k48++)
                railcourbe90d8x8.rotate();

            addComponent(railcourbe90d8x8);
        } else
        if("RailAiguillageDroit10Com".equals(s))
        {
            RailAiguillageDroit10Com railaiguillagedroit10com = new RailAiguillageDroit10Com(apin, i, j);
            for(int l48 = 0; l48 < k; l48++)
                railaiguillagedroit10com.rotate();

            addComponent(railaiguillagedroit10com);
        } else
        if("RailAiguillageGauche10Com".equals(s))
        {
            RailAiguillageGauche10Com railaiguillagegauche10com = new RailAiguillageGauche10Com(apin, i, j);
            for(int i49 = 0; i49 < k; i49++)
                railaiguillagegauche10com.rotate();

            addComponent(railaiguillagegauche10com);
        } else
        if("RailAiguillageDroit10Auto".equals(s))
        {
            RailAiguillageDroit10Auto railaiguillagedroit10auto = new RailAiguillageDroit10Auto(apin, i, j);
            for(int j49 = 0; j49 < k; j49++)
                railaiguillagedroit10auto.rotate();

            addComponent(railaiguillagedroit10auto);
        } else
        if("RailAiguillageGauche10Auto".equals(s))
        {
            RailAiguillageGauche10Auto railaiguillagegauche10auto = new RailAiguillageGauche10Auto(apin, i, j);
            for(int k49 = 0; k49 < k; k49++)
                railaiguillagegauche10auto.rotate();

            addComponent(railaiguillagegauche10auto);
        } else
        if("RailCroisement".equals(s))
        {
            RailCroisement railcroisement = new RailCroisement(apin, i, j);
            for(int l49 = 0; l49 < k; l49++)
                railcroisement.rotate();

            addComponent(railcroisement);
        } else
        if("RailMasque1".equals(s))
        {
            RailMasque1 railmasque1 = new RailMasque1(apin, i, j);
            for(int i50 = 0; i50 < k; i50++)
                railmasque1.rotate();

            addComponent(railmasque1);
        } else
        if("RailMasque2".equals(s))
        {
            RailMasque2 railmasque2 = new RailMasque2(apin, i, j);
            for(int j50 = 0; j50 < k; j50++)
                railmasque2.rotate();

            addComponent(railmasque2);
        } else
        if("RailButoir".equals(s))
        {
            RailButoir railbutoir = new RailButoir(apin, i, j);
            for(int k50 = 0; k50 < k; k50++)
                railbutoir.rotate();

            addComponent(railbutoir);
        } else
        if("RailButoirTrain".equals(s))
        {
            RailButoirTrain railbutoirtrain = new RailButoirTrain(apin, i, j);
            for(int l50 = 0; l50 < k; l50++)
                railbutoirtrain.rotate();

            addComponent(railbutoirtrain);
        } else
        if("RailDroit5Auto".equals(s))
        {
            RailDroit5Auto raildroit5auto = new RailDroit5Auto(apin, i, j);
            for(int i51 = 0; i51 < k; i51++)
                raildroit5auto.rotate();

            addComponent(raildroit5auto);
        } else
        if("RailDroit10Auto".equals(s))
        {
            RailDroit10Auto raildroit10auto = new RailDroit10Auto(apin, i, j);
            for(int j51 = 0; j51 < k; j51++)
                raildroit10auto.rotate();

            addComponent(raildroit10auto);
        } else
        if("RailDroit20Auto".equals(s))
        {
            RailDroit20Auto raildroit20auto = new RailDroit20Auto(apin, i, j);
            for(int k51 = 0; k51 < k; k51++)
                raildroit20auto.rotate();

            addComponent(raildroit20auto);
        } else
        if("RailDroit10AutoLent".equals(s))
        {
            RailDroit10AutoLent raildroit10autolent = new RailDroit10AutoLent(apin, i, j);
            for(int l51 = 0; l51 < k; l51++)
                raildroit10autolent.rotate();

            addComponent(raildroit10autolent);
        } else
        if("RailCourbe90d8x8Auto".equals(s))
        {
            RailCourbe90d8x8Auto railcourbe90d8x8auto = new RailCourbe90d8x8Auto(apin, i, j);
            for(int i52 = 0; i52 < k; i52++)
                railcourbe90d8x8auto.rotate();

            addComponent(railcourbe90d8x8auto);
        } else
        if("RailSegmentH".equals(s))
        {
            RailSegmentH railsegmenth = new RailSegmentH(apin, i, j);
            for(int j52 = 0; j52 < k; j52++)
                railsegmenth.rotate();

            addComponent(railsegmenth);
        } else
        if("RailSegmentV".equals(s))
        {
            RailSegmentV railsegmentv = new RailSegmentV(apin, i, j);
            for(int k52 = 0; k52 < k; k52++)
                railsegmentv.rotate();

            addComponent(railsegmentv);
        } else
        if("RailBoitierCom".equals(s))
        {
            RailBoitierCom railboitiercom = new RailBoitierCom(apin, i, j);
            for(int l52 = 0; l52 < k; l52++)
                railboitiercom.rotate();

            addComponent(railboitiercom);
        } else
        if("RailCourbe45AutoD".equals(s))
        {
            RailCourbe45AutoD railcourbe45autod = new RailCourbe45AutoD(apin, i, j);
            for(int i53 = 0; i53 < k; i53++)
                railcourbe45autod.rotate();

            addComponent(railcourbe45autod);
        } else
        if("RailCourbe45AutoG".equals(s))
        {
            RailCourbe45AutoG railcourbe45autog = new RailCourbe45AutoG(apin, i, j);
            for(int j53 = 0; j53 < k; j53++)
                railcourbe45autog.rotate();

            addComponent(railcourbe45autog);
        } else
        if("RailBP".equals(s))
        {
            RailBP railbp = new RailBP(apin, i, j);
            for(int k53 = 0; k53 < k; k53++)
                railbp.rotate();

            addComponent(railbp);
        } else
        if("RailSwitch".equals(s))
        {
            RailSwitch railswitch = new RailSwitch(apin, i, j);
            for(int l53 = 0; l53 < k; l53++)
                railswitch.rotate();

            addComponent(railswitch);
        } else
        if("RailKlaxon".equals(s))
        {
            RailKlaxon railklaxon = new RailKlaxon(apin, i, j);
            for(int i54 = 0; i54 < k; i54++)
                railklaxon.rotate();

            addComponent(railklaxon);
        } else
        if("RailSifflet".equals(s))
        {
            RailSifflet railsifflet = new RailSifflet(apin, i, j);
            for(int j54 = 0; j54 < k; j54++)
                railsifflet.rotate();

            addComponent(railsifflet);
        } else
        if("RailMiniSR".equals(s))
        {
            RailMiniSR railminisr = new RailMiniSR(apin, i, j);
            for(int k54 = 0; k54 < k; k54++)
                railminisr.rotate();

            addComponent(railminisr);
        } else
        if("RailMini2S2R".equals(s))
        {
            RailMini2S2R railmini2s2r = new RailMini2S2R(apin, i, j);
            for(int l54 = 0; l54 < k; l54++)
                railmini2s2r.rotate();

            addComponent(railmini2s2r);
        } else
        if("PERIODEMETRE".equals(s))
        {
            PERIODEMETRE periodemetre = new PERIODEMETRE(apin, i, j);
            for(int i55 = 0; i55 < k; i55++)
                periodemetre.rotate();

            addComponent(periodemetre);
        } else
        if("MiniAnalyseur".equals(s))
        {
            MiniAnalyseur minianalyseur = new MiniAnalyseur(apin, i, j);
            for(int j55 = 0; j55 < k; j55++)
                minianalyseur.rotate();

            addComponent(minianalyseur);
        } else
        if("AnalyseurHorloge".equals(s))
        {
            AnalyseurHorloge analyseurhorloge = new AnalyseurHorloge(apin, i, j);
            for(int k55 = 0; k55 < k; k55++)
                analyseurhorloge.rotate();

            addComponent(analyseurhorloge);
        } else
        if("Compteur2fronts".equals(s))
        {
            Compteur2fronts compteur2fronts = new Compteur2fronts(apin, i, j);
            for(int l55 = 0; l55 < k; l55++)
                compteur2fronts.rotate();

            addComponent(compteur2fronts);
        } else
        if("Mono500msNonRe".equals(s))
        {
            Mono500msNonRe mono500msnonre = new Mono500msNonRe(apin, i, j);
            for(int i56 = 0; i56 < k; i56++)
                mono500msnonre.rotate();

            addComponent(mono500msnonre);
        } else
        if("Mono500msRe".equals(s))
        {
            Mono500msRe mono500msre = new Mono500msRe(apin, i, j);
            for(int j56 = 0; j56 < k; j56++)
                mono500msre.rotate();

            addComponent(mono500msre);
        } else
        if("Mono1sRe".equals(s))
        {
            Mono1sRe mono1sre = new Mono1sRe(apin, i, j);
            for(int k56 = 0; k56 < k; k56++)
                mono1sre.rotate();

            addComponent(mono1sre);
        } else
        if("ESSAICOMPOSANT0".equals(s))
        {
            ESSAICOMPOSANT0 essaicomposant0 = new ESSAICOMPOSANT0(apin, i, j);
            for(int l56 = 0; l56 < k; l56++)
                essaicomposant0.rotate();

            addComponent(essaicomposant0);
        } else
        if("ESSAICOMPOSANT1".equals(s))
        {
            ESSAICOMPOSANT1 essaicomposant1 = new ESSAICOMPOSANT1(apin, i, j);
            for(int i57 = 0; i57 < k; i57++)
                essaicomposant1.rotate();

            addComponent(essaicomposant1);
        } else
        if("ESSAICOMPOSANT2".equals(s))
        {
            ESSAICOMPOSANT2 essaicomposant2 = new ESSAICOMPOSANT2(apin, i, j);
            for(int j57 = 0; j57 < k; j57++)
                essaicomposant2.rotate();

            addComponent(essaicomposant2);
        } else
        if("ESSAICOMPOSANT3".equals(s))
        {
            ESSAICOMPOSANT3 essaicomposant3 = new ESSAICOMPOSANT3(apin, i, j);
            for(int k57 = 0; k57 < k; k57++)
                essaicomposant3.rotate();

            addComponent(essaicomposant3);
        } else
        if("ESSAICOMPOSANT4".equals(s))
        {
            ESSAICOMPOSANT4 essaicomposant4 = new ESSAICOMPOSANT4(apin, i, j);
            for(int l57 = 0; l57 < k; l57++)
                essaicomposant4.rotate();

            addComponent(essaicomposant4);
        } else
        if("ESSAICOMPOSANT5".equals(s))
        {
            ESSAICOMPOSANT5 essaicomposant5 = new ESSAICOMPOSANT5(apin, i, j);
            for(int i58 = 0; i58 < k; i58++)
                essaicomposant5.rotate();

            addComponent(essaicomposant5);
        } else
        if("ESSAICOMPOSANT6".equals(s))
        {
            ESSAICOMPOSANT6 essaicomposant6 = new ESSAICOMPOSANT6(apin, i, j);
            for(int j58 = 0; j58 < k; j58++)
                essaicomposant6.rotate();

            addComponent(essaicomposant6);
        } else
        if("ESSAICOMPOSANT7".equals(s))
        {
            ESSAICOMPOSANT7 essaicomposant7 = new ESSAICOMPOSANT7(apin, i, j);
            for(int k58 = 0; k58 < k; k58++)
                essaicomposant7.rotate();

            addComponent(essaicomposant7);
        } else
        if("ESSAICOMPOSANT8".equals(s))
        {
            ESSAICOMPOSANT8 essaicomposant8 = new ESSAICOMPOSANT8(apin, i, j);
            for(int l58 = 0; l58 < k; l58++)
                essaicomposant8.rotate();

            addComponent(essaicomposant8);
        } else
        if("ESSAICOMPOSANT9".equals(s))
        {
            ESSAICOMPOSANT9 essaicomposant9 = new ESSAICOMPOSANT9(apin, i, j);
            for(int i59 = 0; i59 < k; i59++)
                essaicomposant9.rotate();

            addComponent(essaicomposant9);
        } else
        {
            return false;
        }
        return true;
    }

    public boolean PlaceJunction(Pin apin[][], int i, int j, int k)
    {
        boolean flag = false;
        for(int l = 0; l < Components.size(); l++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)Components.elementAt(l);
            if(!(electroniccomponent instanceof Wire))
                continue;
            Wire wire = (Wire)electroniccomponent;
            if(wire.TryPlaceJunction(this, apin, i, j, k))
                flag = true;
        }

        return flag;
    }

    public ElectronicComponent TestShortCircuit()
    {
        for(int j = 0; j < Components.size(); j++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)Components.elementAt(j);
            for(int i = 0; i < electroniccomponent.Outputs; i++)
                if(electroniccomponent.OPin[i].ShortCircuit)
                    return electroniccomponent;

        }

        return null;
    }

    public ElectronicComponent TestLoop()
    {
        for(int j = 0; j < Components.size(); j++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)Components.elementAt(j);
            for(int i = 0; i < electroniccomponent.Inputs; i++)
                if(electroniccomponent.IPin[i].Looping)
                    return electroniccomponent;

        }

        return null;
    }

    public Vector getProbes()
    {
        Vector vector = new Vector();
        for(int i = 0; i < Components.size(); i++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)Components.elementAt(i);
            if(electroniccomponent instanceof Probe)
                vector.addElement(electroniccomponent);
        }

        return vector;
    }

    public void SwapComponents(ElectronicComponent electroniccomponent, ElectronicComponent electroniccomponent1)
    {
        int i = Components.indexOf(electroniccomponent);
        int j = Components.indexOf(electroniccomponent1);
        if(i == -1 || j == -1)
        {
            return;
        } else
        {
            Components.setElementAt(electroniccomponent, j);
            Components.setElementAt(electroniccomponent1, i);
            return;
        }
    }

    public boolean ProbesInSchematic()
    {
        for(int i = 0; i < Components.size(); i++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)Components.elementAt(i);
            if(electroniccomponent instanceof Probe)
                return true;
        }

        return false;
    }

    protected Vector Components;
    boolean Modified;
    String FileDir;
    String FileName;
    int SimulateCycleID;
}
