package digsim;

import java.io.*;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.concurrent.CountDownLatch;
import java.util.function.IntBinaryOperator;

import digsim.DigSimFrame;
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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.Pos;


// Referenced classes of package digsim:
//            MyColor, Schematic, SimpleDialog, Pin,
//            DigSimFrame, AnalyzerFrame, Wire, Vcc,
//            GND, Colorieur, Oscilator, Oscillator2,
//            Oscillateur1s, Oscillateur500ms, Oscillateur100ms, SoundGenerator,
//            AlarmGenerator, Switch, SwitchT, PushButton,
//            Switch2RT, Relay2RT, Buffer, Inverter,
//            TwoAndPort, ThreeAndPort, FourAndPort, TwoOrPort,
//            ThreeOrPort, FourOrPort, TwoXorPort, TwoNandPort,
//            ThreeNandPort, FourNandPort, TwoNorPort, ThreeNorPort,
//            FourNorPort, FiveNorPort, TwoXnorPort, INPUTINVERTER,
//            OUTPUTINVERTER, Multiplexer2to1, Multiplexer4to1, Multiplexer8to1,
//            SRLatch, GatedSRLatch, DLatch, DFlipFlop,
//            JKFlipFlop, JKRSFlipFlop, JbKRSFlipFlop, SequencerCell,
//            EdgeTriggeredTFlipFlop, TFlipFlop, OctalDFlipFlop, OctalDFlipFlopInverter,
//            OctalLatch, ShiftRegister, EightBitSerInShiftReg, EightBitParInShiftReg,
//            BCDToSevenSegDecoder, FullAdder, ThreeToEightLineDecoder, FourBitBinaryCounter,
//            FourBitBinaryUpDownCounter, FourBitBCDUpDownCounter, EightBitBinaryUpDownCounter, Monostable,
//            PLA5x4, TraitsCie, Counterxx161, Counterxx163,
//            CounterBCDxx160, CounterBCDxx162, CtrBcdBinRazSync, CtrBcdBinRazASync,
//            Ctr5BinRazASync, PERCEUSE, ChariotLibre, ChariotXYhbgd,
//            ChariotXY7bits, ChariotXYmixte, ChariotXYPaP, WagonsSimple,
//            WagonsAiguillages, JETON, FEUXCARREFOUR, CLEF,
//            CelluleRC, Comparateur8bits, Comparateur4bits, LED,
//            BICOLORLEDVERTICAL1, BiColorLED, SevenSegmentDisplay, Probe,
//            MULT8x8, ADD8, MAC8x8, Oscillateur2T,
//            FourMUX2to1, Buffer8bits, Transceiver8bits, Buffer2x4bits,
//            Afficheur9bits, TRIGGER, Buffer3S, EmetteurSerie8bits,
//            RetardFixe, RetardPur, RetardRC, RdPtransition,
//            RdPfleche, RdPplace, RdPjeton, CapaC,
//            ResR, Pendule, FourBitShifter, Encodeur8vers3,
//            Encodeur10vers4, Cadre1024x768, Cadre1280x1024, CadreDouble97x69,
//            CadreDouble80x70, DEC8, SBPA, SBA,
//            Boitier7400, Boitier7402, Boitier7404, Boitier7408,
//            Boitier7410, Boitier7411, Boitier7420, Boitier7421,
//            Boitier7427, Boitier7432, Boitier7486, Boitier744002,
//            Boitier744072, Boitier744075, Boitier747266, Boitier74109,
//            PERIODEMETRE, MiniAnalyseur, AnalyseurHorloge, Compteur2fronts,
//            Mono500msNonRe, Mono500msRe, Mono1sRe, RailDroit10Train,
//            RailDroit10, RailDroit20, RailCourbe90d8x8, RailAiguillageDroit10Com,
//            RailAiguillageGauche10Com, RailAiguillageDroit10Auto, RailAiguillageGauche10Auto, RailCroisement,
//            RailMasque1, RailMasque2, RailButoirTrain, RailButoir,
//            RailDroit5Auto, RailDroit10Auto, RailDroit20Auto, RailDroit10AutoLent,
//            RailObliqueDroit, RailObliqueGauche, RailCourbe90d8x8Auto, RailSegmentH,
//            RailSegmentV, RailBoitierCom, RailCourbe45AutoD, RailCourbe45AutoG,
//            RailBP, RailSwitch, RailKlaxon, RailSifflet,
//            RailMiniSR, RailMini2S2R, CodeGen, CodeTest,
//            HHmmss, Trans8toBus, TransBusto8, Mini2BPtempo,
//            Mini1BPtempo, Mini1Inter, Mini1BP, ISOnon,
//            ISOet3, ISOou3, ISOet3non, ISOou3non,
//            ISOoux3non, ISOoux3, RailGestionDirection, RailGestionCanton,
//            MiniBPNOPullUp, MiniBPNOPullDown, MiniBPNFPullUp, MiniBPNFPullDown,
//            MiniSwitchNOPullUp, MiniSwitchNOPullDown, MiniSwitchNFPullUp, MiniSwitchNFPullDown,
//            ESSAICOMPOSANT0, ESSAICOMPOSANT1, ESSAICOMPOSANT2, ESSAICOMPOSANT3,
//            ESSAICOMPOSANT4, ESSAICOMPOSANT5, ESSAICOMPOSANT6, ESSAICOMPOSANT7,
//            ESSAICOMPOSANT8, ESSAICOMPOSANT9, MiniBPNO, MiniBPNF,
//            MiniSwitchNO, MiniSwitchNF, MiniBPNOAvecR, HelpDialog,
//            LoadMem, TextDialog, ExamplesFrame, OptionsFrame,
//            ScriptFrame, SchematicPanel, ControlPanel, StatusPanel,
//            ElectronicComponent, AnalyzerPanel

public class DigSim extends Application
{

    public DigSim()
    {
        MyControlPanel = null;
        MyStatusPanel = null;
        MySchematicPanel = null;
//        killme = null;
        SchematicName = null;
        MySchematic = null;
        message = null;
        GridImage = null;
//        ImageBuffer = null;
        OffScreenWidth = 0;
        OffScreenHeight = 0;
        SchematicPanelPainted = false;
        HelpWanted = false;
        TextFileRequested = null;
        EnableFileOperations = false;
        MyExamplesFrame = null;
        RequestedText = null;
        RequestedTextFileRead = false;
        RequestedTextFileError = false;
        MyOptionsFrame = null;
        MyAnalyzerFrame = null;
        MyScriptFrame = null;
        StopAtShortCircuit = false;
        StopAtLoop = false;
        AnalyzerAutoPopUp = false;
        sound_name = "sounds/ringin.mp3";
        SimulationSpeed = 10;
    }

//    public URL getDocumentBase()
//    {
//        if(SpecialDocumentBase == null)
//            SpecialDocumentBase = super.getDocumentBase();
//        return SpecialDocumentBase;
//    }

    public void initAll()
    {
        //setLayout(new FlowLayout());
    	FlowPane flow = new FlowPane();
    	flow.setVgap(8);
        flow.setHgap(4);
        flow.setPrefWrapLength(300); // preferred width = 300
        flow.getChildren().add(displayb = new Button("Afficher"));

        displayb.setOnAction(new EventHandler<ActionEvent>() {
             public void handle(ActionEvent e) {
                doFrame();
				 MyControlPanel.EnableAllButtons();
            }
        });

        flow.getChildren().add(disposeb = new Button(" Fermer "));
        disposeb.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            	destroyFrame();
           }
       });

        vb.getChildren().add(flow);
        SchematicName = null; //getParameter("schematic");
        String s = null; //getParameter("special_documentbase");
        if(s != null)
            try
            {
                SpecialDocumentBase = new URL(s);
            }
            catch(Exception exception)
            {
                message = exception.toString();
                System.out.println(message + ":Error in processing special_documentbase as a URL");
                SpecialDocumentBase = null;
            }
//        setupScreen();
        s = null;//getParameter("display_type");
        if(s != null)
            DisplayType = s;
        if(DisplayType.equals("monochrome"))
            DisplayColorMap = new MyColor("monochrome");
        else
            DisplayColorMap = new MyColor("color");
        BackGroundColor = MyColor.white;
        s = "true"; //getParameter("fileop");
        if(s != null)
        {
            if(s.equals("true"))
                EnableFileOperations = true;
            else
                EnableFileOperations = false;
        } else
        {
            EnableFileOperations = false;
        }
        s = null; //getParameter("soundfile");
        if(s != null)
            sound_name = new String("sounds/" + s);
        PinGrid = new Pin[MaxXPoints][MaxYPoints];
        InitPinGrids();
        SetUpImages();
        doFrame();

    }

	private void setupScreen() {
		String s;
		s = "400";//getParameter("schematic_width");
        if(s != null)
            MaxXPoints = Integer.parseInt(s);
        s = "300";//getParameter("schematic_height");
        if(s != null)
            MaxYPoints = Integer.parseInt(s);
        OffScreenWidth = 4 * MaxXPoints;
        OffScreenHeight = 3 * MaxYPoints;
	}

	@Override
	public void start(Stage primaryStage) throws Exception
    {
//        if(killme == null)
//        {
        mainStage = primaryStage;
        primaryStage.setTitle("DigSim");
        Group root = new Group();
        setupScreen();
        canvas = new Canvas(OffScreenWidth, OffScreenHeight);
        gc = canvas.getGraphicsContext2D();
        vb = new VBox();
//        vb.getChildren().add(canvas);
        vb.setSpacing(30);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(10, 0, 0, 10));
        root.getChildren().add(vb);
        initAll();
        Scene scene = new Scene(root);//,800,600,true,SceneAntialiasing.DISABLED);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
            	keyDown(event);
            }
        });
     
        primaryStage.setScene(scene);
        primaryStage.show();

            sound = null;

            ClassLoader cl = this.getClass().getClassLoader();
        	URL url = cl.getResource(sound_name);
        	sound = new AudioClip(url.toString());
            coucou = null;
            url = cl.getResource("sounds/coucou.mp3");
            coucou = new AudioClip(url.toString());
            hibou = null;
            url = cl.getResource("sounds/hibou.mp3");
            hibou = new AudioClip(url.toString());
            alarm = null;
            url = cl.getResource("sounds/alarm.mp3");
            alarm = new AudioClip(url.toString());
            traincrash = null;
            url = cl.getResource("sounds/traincrash.mp3");
            traincrash = new AudioClip(url.toString());
            trainklaxon = null;
            url = cl.getResource("sounds/trainklaxon.mp3");
            trainklaxon = new AudioClip(url.toString());
            trainsifflet = null;
            url = cl.getResource("sounds/trainsifflet.mp3");
            trainsifflet = new AudioClip(url.toString());

            //TESTS
//            addComponent("Wire");

            run1();
//    		final Service<Void> calculateService = new Service<Void>() {
//
//    			@Override
//    			protected Task<Void> createTask() {
//    				// TODO Auto-generated method stub
//    				return new Task<Void>() {
//    		        	@Override protected Void call() throws Exception {
//    		        		run1();
//    		        		return null;
//    		        	}
//    				};
//    			}
//    		};
//
//    		calculateService.start();


//            killme = new Thread(this);
//            killme.start();
//        }
    }

    public void stop()
    {
//        killme = null;
    }

    public void destroy()
    {
        if(SimulateRunning())
            SimulateStop();
        if(MyAnalyzerFrame != null)
        {
            MyAnalyzerFrame.close();
            MyAnalyzerFrame = null;
        }
        if(MyExamplesFrame != null)
        {
            MyExamplesFrame.close();
            MyExamplesFrame = null;
        }
        if(MyOptionsFrame != null)
        {
            MyOptionsFrame.close();
            MyOptionsFrame = null;
        }
        if(frame != null)
        {
            frame.dispose();
            frame = null;
        }
    }

    public void run1()
    {
        InputStream inputstream = null;
        Object obj = null;
        Object obj1 = null;
        boolean flag = false;
        boolean flag1 = false;
        try
        {
            Thread.currentThread().setPriority(1);
            if(SchematicName != null)
            {
                ClassLoader cl  = this.getClass().getClassLoader();
                inputstream = cl.getResourceAsStream(SchematicName);
            	//inputstream = (new URL(getDocumentBase(), SchematicName)).openStream();
                if(SchematicName.endsWith(".dig"))
                {
                    MySchematic = new Schematic(PinGrid, inputstream);
                } else
                {
                    BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
                    String s2;
                    String s3;
                    for(s3 = ""; (s2 = bufferedreader.readLine()) != null; s3 = s3 + s2);
                    MySchematic = new Schematic(frame, PinGrid, s3);
                }
            }
        }
        catch(Exception exception)
        {
            String s = exception.toString();
            String as[] = {
                "OK"
            };
            SimpleDialog simpledialog = new SimpleDialog(frame, "Lecture du schéma initial", s, as, 1, 0, 0, 1);
        }
        try
        {
            if(inputstream != null)
                inputstream.close();
        }
        catch(Exception exception1) { }
        repaint();
        if(MySchematic == null)
        {
            MySchematic = new Schematic();
            if (frame != null)
            	frame.setTitle("DigSim ''RailWay'' 2012.2.0 [sans nom] ; penser à nommer et enregistrer votre simulation (fichier, enregistrer sous)");
        } else
        {
        	if (SchematicName != null)
        	{
	            StringTokenizer stringtokenizer = new StringTokenizer(SchematicName, "/");
	            for(int k = 0; k < stringtokenizer.countTokens() - 1; k++)
	                MySchematic.FileDir += stringtokenizer.nextToken();

	            MySchematic.FileName = stringtokenizer.nextToken();
	            frame.setTitle("DigSim ''RailWay'' 2012.2.0 [" + MySchematic.FileName + "] ;  cliquer sur ''enregistrer sous'' pour nommer puis sauvegarder ce schéma");
        	}
        }
        do
        {
        	doTask();
        } while(false);//true);
//        killme = null;
    }

	public void doTask() {
		//            if(killme == null)
		//                break;
		            try
		            {
		                Thread.sleep(SimulationSpeed);
		            }
		            catch(InterruptedException interruptedexception) { }
		            if(TextFileRequested != null)
		            {
		                openExample();
		            }
		            if(MySchematicPanel != null && !SchematicPanelPainted && ImagesReady())
		            {
		                SchematicPanelPainted = true;
		                MySchematicPanel.repaint();
		                if(MyControlPanel != null)
		                {
		                    MyControlPanel.EnableAllButtons();
		                    frame.EnableAllMenus();
		                    StatusMessage("Pour lancer la simulation, cliquez sur l'éclair jaune ou appuyez sur la touche ''ENTREE''...");
		                }
		            }
		            if(MyStatusPanel != null && MyStatusPanel.SimulationRunning)
		                SimulateCycle();
	}

	public void openExample() {
		int i = 0;
		InputStream inputstream = null;
        char c = '\310';
        char ac[] = new char[c];

		String s1 = "";
		try
		{
		    Thread.currentThread().setPriority(1);

		    ClassLoader cl = this.getClass().getClassLoader();
		    inputstream = cl.getResourceAsStream(TextFileRequested);

		    //inputstream = (new URL(super.getDocumentBase(), TextFileRequested)).openStream();
		    do
		    {
		        int j;
		        if((j = inputstream.read()) == -1)
		            break;
		        ac[i++] = (char)j;
		        if(i >= c)
		        {
		            s1 = s1 + new String(ac, 0, c);
		            i = 0;
		        }
		    } while(true);
		    s1 = s1 + new String(ac, 0, i);
		}
		catch(Exception exception2)
		{
		    s1 = s1 + exception2.toString();
		    RequestedText = s1;
		    RequestedTextFileError = true;
		}
		try
		{
		    if(inputstream != null)
		        inputstream.close();
		}
		catch(Exception exception3)
		{
		    s1 = s1 + exception3.toString();
		    RequestedText = s1;
		    RequestedTextFileError = true;
		}
		if(!RequestedTextFileError)
		{
		    RequestedText = s1;
		    RequestedTextFileRead = true;
		}
	}

    public void InitPinGrids()
    {
        for(int i = 0; i < MaxXPoints; i++)
        {
            for(int j = 0; j < MaxYPoints; j++)
                PinGrid[i][j] = new Pin();

        }

    }

    public void repaint()
    {
//    	paint(null);
    }


    public void SetUpImages()
    {
//        ImageBuffer = getImage();
    	gc.clearRect(0, 0, OffScreenWidth, OffScreenHeight);
    	if (GridImage == null)
    	{
//	        GridImage = getImage();
	        Canvas c = new Canvas(OffScreenWidth, OffScreenHeight);
	        GraphicsContext g =  c.getGraphicsContext2D();
	        g.drawImage(GridImage, 0, 0);
	        g.setFill(BackGroundColor);
	        DrawWithOffset.fillRect(g,0, 0, OffScreenWidth, OffScreenHeight);
	        g.setStroke(Color.rgb(25, 255, 25));
	        for(int i = 1; i <= MaxXPoints; i++)
	        {
	            for(int j = 1; j <= MaxYPoints; j++)
	                DrawWithOffset.strokeLine(g,i * 8, j * 8, i * 8, j * 8);

	        }
	        GridImage = getImage(g);
    	}
    }

    public void destroyFrame()
    {
        if(SimulateRunning())
            SimulateStop();
        if(MyAnalyzerFrame != null)
        {
            MyAnalyzerFrame.close();
            MyAnalyzerFrame = null;
        }
        if(MyExamplesFrame != null)
        {
            MyExamplesFrame.close();
            MyExamplesFrame = null;
        }
        if(MyOptionsFrame != null)
        {
            MyOptionsFrame.close();
            MyOptionsFrame = null;
        }
        if(frame != null)
        {
            frame.dispose();
            frame = null;
            displayb.setDisable(false);
            disposeb.setDisable(true);
        }
    }

    void doFrame()
    {
        displayb.setDisable(true);
        disposeb.setDisable(false);
        frame = new DigSimFrame(this);
        frame.EnableAllMenus();

//		 String as1[] = {
//	                "OK"
//	            };
//		SimpleDialog sd = new SimpleDialog(frame, "hello", "question", as1, 1, 0, 0, 1);


//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("path/to/other/view.fxml"), resources);
//        frame.setScene(new Scene(root, 450, 450));
//        frame.show();

    }

//    public boolean action(Event event, Object obj)
//    {
//        if(event.target instanceof Button)
//        {
//            String s = (String)obj;
//            if(s.equals("Afficher"))
//            {
//                doFrame();
//                MyControlPanel.EnableAllButtons();
//            } else
//            if(s.equals(" Fermer "))
//                destroyFrame();
//            return true;
//        } else
//        {
//            return false;
//        }
//    }

    public void SimulateSetUp()
    {
        for(int i = 0; i < MaxXPoints; i++)
        {
            for(int j = 0; j < MaxYPoints; j++)
                if(PinGrid[i][j] != null)
                    PinGrid[i][j].SimulateSetUp(i, j);

        }

    }

    public void SimulateStart()
    {
        StatusMessage("Initialisation de la simulation.");
        MyStatusPanel.repaint();
        if(MyStatusPanel.SimulationInitializeInProgress)
            return;
        if(AnalyzerAutoPopUp && MyAnalyzerFrame == null && MySchematic.ProbesInSchematic())
            MyAnalyzerFrame = new AnalyzerFrame(this);
        MySchematicPanel.SelectSchematic.RemoveAllComponents();
        MyStatusPanel.SimulationInitializeInProgress = true;
        MyStatusPanel.repaint();
        SimulateSetUp();
        MyStatusPanel.SimulationInitializeInProgress = false;
        MyStatusPanel.SimulationRunning = true;
        StatusMessage("Simulation en cours. Pour arrêter, cliquez sur l'éclair jaune ou appuyez sur ''ENTREE''");

        if (useAnimation)
        {
        	launchAnimation();
        }
        else
        {
        	launchService();
        }

    }

    /**
     * Runs the specified {@link Runnable} on the
     * JavaFX application thread and waits for completion.
     *
     * @param action the {@link Runnable} to run
     * @throws NullPointerException if {@code action} is {@code null}
     */
    public static void runAndWait(Runnable action) {
        if (action == null)
            throw new NullPointerException("action");
     
        // run synchronously on JavaFX thread
        if (Platform.isFxApplicationThread()) {
            action.run();
            return;
        }
     
        // queue on JavaFX thread and wait for completion
        final CountDownLatch doneLatch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                action.run();
            } finally {
                doneLatch.countDown();
            }
        });
     
        try {
            doneLatch.await();
        } catch (InterruptedException e) {
            // ignore exception
        }
    }
    
    private void launchService() {
		
		final Service<Void> simulationService = new Service<Void>() {

			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {
		        	@Override protected Void call() throws Exception {
	  		              while (IsSimulating())
			              {
	  		            	  	Thread.sleep(Math.max(50, SimulationSpeed * 10));	  		            	  
		  		            	runAndWait(new Runnable() {
				        		    @Override
				        		    public void run() {		  		          
				        		    	SimulateCycle();
				        		    }
				        		});
			              }
		        		return null;
		        	}
				};
			}
		};

		simulationService.start();
	}

	private void launchAnimation() {
		// animate the property:
        animation = new Timeline(new KeyFrame(Duration.millis(SimulationSpeed * 2), event ->
            {
	            if(MyStatusPanel != null && MyStatusPanel.SimulationRunning)
	                SimulateCycle();
            }
        ));

        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
	}

    public void SimulateStop()
    {
        MyStatusPanel.SimulationRunning = false;
        if (animation != null)
        	animation.stop();
        StatusMessage("La simulation est arrêtée. Vous pouvez modifier votre schéma.");
        MyStatusPanel.repaint();
    }

    public boolean IsSimulating()
    {
        return MyStatusPanel.SimulationRunning || MyStatusPanel.SimulationInitializeInProgress;
    }

    public void SimulateCycle()
    {
        MySchematic.InitBeforeSimulate();
        for(int i = 0; i < 4; i++)
            MySchematic.Simulate();

        if(StopAtShortCircuit)
        {
            ElectronicComponent electroniccomponent = MySchematic.TestShortCircuit();
            if(electroniccomponent != null)
            {
                String as[] = {
                    "OK"
                };
                String s = "Court circuit détecté sur " + electroniccomponent.getName() + " pos. " + electroniccomponent.Pos.x + ", " + electroniccomponent.Pos.y;
                SimpleDialog simpledialog = new SimpleDialog(frame, "Court-circuit détecté", s, as, 1, 0, 0, 1);
                UserWantsSimulate();
                MySchematicPanel.repaint();
                MyStatusPanel.repaint();
                return;
            }
        }
        if(StopAtLoop)
        {
            ElectronicComponent electroniccomponent1 = MySchematic.TestLoop();
            if(electroniccomponent1 != null)
            {
                String as1[] = {
                    "OK"
                };
                String s1 = "Boucle détectée sur le composant  : " + electroniccomponent1.getName() + " pos. " + electroniccomponent1.Pos.x + ", " + electroniccomponent1.Pos.y;
                SimpleDialog simpledialog1 = new SimpleDialog(frame, "Boucle détectée", s1, as1, 1, 0, 0, 1);
                UserWantsSimulate();
                MySchematicPanel.repaint();
                MyStatusPanel.repaint();
                return;
            }
        }
        if(MyAnalyzerFrame != null && MyAnalyzerFrame.MyAnalyzerPanel != null)
            MyAnalyzerFrame.MyAnalyzerPanel.update();
        MySchematicPanel.repaint();
        MyStatusPanel.repaint();
    }

    public void Message()
    {
        if(MySchematic.Modified)
            frame.setTitle("DigSim ''RailWay'' 2012.2.0 [" + MySchematic.FileName + "] ;  câblage modifié ? -> penser à enregistrer régulièrement...");
        MySchematicPanel.repaint();
    }

    public void addComponent(ElectronicComponent electroniccomponent)
    {
        if(MySchematic == null)
            return;
        MySchematicPanel.SelectSchematic.RemoveAllComponents();
        MySchematic.addComponent(electroniccomponent);
        if(!(electroniccomponent instanceof Wire))
            UserWantsPointer();
        MySchematicPanel.repaint();
    }

    public void addComponent(String s)
    {
        if(MySchematicPanel == null)
            return;
        if("Wire".equals(s))
        {
            UserWantsWireDrawing();
            return;
        }
        if("Junction".equals(s))
        {
            UserWantsJunctionDrawing();
            return;
        }
        if("Vcc (niveau logique 1)".equals(s))
            addComponent(((ElectronicComponent) (new Vcc(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Masse (niveau logique 0)".equals(s))
            addComponent(((ElectronicComponent) (new GND(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Colorieur".equals(s))
            addComponent(((ElectronicComponent) (new Colorieur(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Horloge 1 (periode T)".equals(s))
            addComponent(((ElectronicComponent) (new Oscilator(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Horloge de periode ajustable".equals(s))
            addComponent(((ElectronicComponent) (new Oscillator2(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2, MySchematicPanel.Cycle))));
        else
        if("Horloge 1s".equals(s))
            addComponent(((ElectronicComponent) (new Oscillateur1s(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Horloge 500ms".equals(s))
            addComponent(((ElectronicComponent) (new Oscillateur500ms(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Horloge 100ms".equals(s))
            addComponent(((ElectronicComponent) (new Oscillateur100ms(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Source sonore".equals(s))
            addComponent(((ElectronicComponent) (new SoundGenerator(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Alarme".equals(s))
            addComponent(((ElectronicComponent) (new AlarmGenerator(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Contact R (type repos)".equals(s))
            addComponent(((ElectronicComponent) (new Switch(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Contact T (type travail)".equals(s))
            addComponent(((ElectronicComponent) (new SwitchT(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Bouton poussoir".equals(s))
            addComponent(((ElectronicComponent) (new PushButton(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Contact 2RT".equals(s))
            addComponent(((ElectronicComponent) (new Switch2RT(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Relais 2RT".equals(s))
            addComponent(((ElectronicComponent) (new Relay2RT(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Etage tampon (buffer)".equals(s))
            addComponent(((ElectronicComponent) (new Buffer(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Porte_''Non''_(inverseur_complementation)".equals(s))
            addComponent(((ElectronicComponent) (new Inverter(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("AND2 = ''Et'' -2 entrees-".equals(s))
            addComponent(((ElectronicComponent) (new TwoAndPort(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("AND3 = ''Et'' -3 entrees-".equals(s))
            addComponent(((ElectronicComponent) (new ThreeAndPort(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("AND4 = ''Et'' -4 entrees-".equals(s))
            addComponent(((ElectronicComponent) (new FourAndPort(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("OR2 = ''Ou'' -2 entrees-".equals(s))
            addComponent(((ElectronicComponent) (new TwoOrPort(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("OR3 = ''Ou'' -3 entrees-".equals(s))
            addComponent(((ElectronicComponent) (new ThreeOrPort(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("OR4 = ''Ou'' -4 entrees-".equals(s))
            addComponent(((ElectronicComponent) (new FourOrPort(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("XOR2 = ''OuExclusif'' -2 entrees-".equals(s))
            addComponent(((ElectronicComponent) (new TwoXorPort(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("NAND2 = ''Et-Non'' -2 entrees-".equals(s))
            addComponent(((ElectronicComponent) (new TwoNandPort(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("NAND3 = ''Et-Non'' -3 entrees-".equals(s))
            addComponent(((ElectronicComponent) (new ThreeNandPort(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("NAND4 = ''Et-Non'' -4 entrees-".equals(s))
            addComponent(((ElectronicComponent) (new FourNandPort(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("NOR2 = ''Ou-Non'' -2 entrees-".equals(s))
            addComponent(((ElectronicComponent) (new TwoNorPort(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("NOR3 = ''Ou-Non'' -3 entrees-".equals(s))
            addComponent(((ElectronicComponent) (new ThreeNorPort(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("NOR4 = ''Ou-Non'' -4 entrees-".equals(s))
            addComponent(((ElectronicComponent) (new FourNorPort(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("NOR5 = ''Ou-Non'' -5 entrees-".equals(s))
            addComponent(((ElectronicComponent) (new FiveNorPort(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("XNOR2 = ''OuExclusif-Non'' -2 entrees-".equals(s))
            addComponent(((ElectronicComponent) (new TwoXnorPort(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("--o = complementation (inversion) en entree de composant".equals(s))
            addComponent(((ElectronicComponent) (new INPUTINVERTER(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("o-- = complementation (inversion) en sortie de composant".equals(s))
            addComponent(((ElectronicComponent) (new OUTPUTINVERTER(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Multiplexeur 2 vers 1".equals(s))
            addComponent(((ElectronicComponent) (new Multiplexer2to1(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Multiplexeur 4 vers 1 (74HC153)".equals(s))
            addComponent(((ElectronicComponent) (new Multiplexer4to1(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Multiplexeur 8 vers 1 (74HC151)".equals(s))
            addComponent(((ElectronicComponent) (new Multiplexer8to1(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Latch SR".equals(s))
            addComponent(((ElectronicComponent) (new SRLatch(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Latch SRH (a verrouillage)".equals(s))
            addComponent(((ElectronicComponent) (new GatedSRLatch(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Latch D (transparent)".equals(s))
            addComponent(((ElectronicComponent) (new DLatch(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("FlipFlop D ''edge-triggered''".equals(s))
            addComponent(((ElectronicComponent) (new DFlipFlop(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("FlipFlop JK ''edge-triggered''".equals(s))
            addComponent(((ElectronicComponent) (new JKFlipFlop(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("FlipFlop JK ''edge-triggered'' avec entrees asynchrones".equals(s))
            addComponent(((ElectronicComponent) (new JKRSFlipFlop(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("FlipFlop JK! ''edge-triggered'' avec entrees asynchrones".equals(s))
            addComponent(((ElectronicComponent) (new JbKRSFlipFlop(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Cellule pour sequenceur cable".equals(s))
            addComponent(((ElectronicComponent) (new SequencerCell(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("FlipFlop T ''edge-triggered''".equals(s))
            addComponent(((ElectronicComponent) (new EdgeTriggeredTFlipFlop(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Bistable T".equals(s))
            addComponent(((ElectronicComponent) (new TFlipFlop(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Registre de 8 FlipFlop D -PIPO- 3S (74HC273 74HC374)".equals(s))
            addComponent(((ElectronicComponent) (new OctalDFlipFlop(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Registre de 8 FlipFlop D -PIPOinv- 3S (74HC534 564)".equals(s))
            addComponent(((ElectronicComponent) (new OctalDFlipFlopInverter(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
       else
       if("Registre de 8 latches D transparents 3S (74HC373)".equals(s))
            addComponent(((ElectronicComponent) (new OctalLatch(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("ShiftRegister".equals(s))
            addComponent(((ElectronicComponent) (new ShiftRegister(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Registre 8bits -SIPO-SISO- 3S (~74HC164)".equals(s))
            addComponent(((ElectronicComponent) (new EightBitSerInShiftReg(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Registre 8bits -PISO- (74HC165)".equals(s))
            addComponent(((ElectronicComponent) (new EightBitParInShiftReg(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Registre 8bits -SIPO-SISO-PISO-PIPO- 3S".equals(s))
            addComponent(((ElectronicComponent) (new ShiftRegister(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Decodeur-latch 7 segments".equals(s))
            addComponent(((ElectronicComponent) (new BCDToSevenSegDecoder(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Additionneur complet 1 bit".equals(s))
            addComponent(((ElectronicComponent) (new FullAdder(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Decodeur-demultiplexeur 3 vers 8 (74HC138)".equals(s))
            addComponent(((ElectronicComponent) (new ThreeToEightLineDecoder(PinGrid, MySchematicPanel.GridXOffset + 1, MySchematicPanel.GridYOffset + 1))));
        else
        if("Compteur binaire 4 bits".equals(s))
            addComponent(((ElectronicComponent) (new FourBitBinaryCounter(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("CtrDCtr BIN 4bits chargement async a 0 ou 15".equals(s))
            addComponent(((ElectronicComponent) (new FourBitBinaryUpDownCounter(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("CtrDctr DCB 4bits chargement async a 0 ou 9".equals(s))
            addComponent(((ElectronicComponent) (new FourBitBCDUpDownCounter(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("CtrDCtr BIN 8bits chargement async a 0 ou 255".equals(s))
            addComponent(((ElectronicComponent) (new EightBitBinaryUpDownCounter(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Monostable temporise".equals(s))
            addComponent(((ElectronicComponent) (new Monostable(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2, MySchematicPanel.stop, MySchematicPanel.delay))));
        else
        if("PLA 5x4".equals(s))
            addComponent(((ElectronicComponent) (new PLA5x4(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2, MySchematicPanel.etpla5x4, MySchematicPanel.oupla5x4, MySchematicPanel.nonpla5x4))));
        else
        if("Traits et Cie".equals(s))
            addComponent(((ElectronicComponent) (new TraitsCie(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2, MySchematicPanel.xzone, MySchematicPanel.yzone, MySchematicPanel.zzone))));
        else
        if("Ctr BIN 4bits prog -CTR-PIPO- RAZ async (74HC161)".equals(s))
            addComponent(((ElectronicComponent) (new Counterxx161(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Ctr BIN 4bits prog -CTR-PIPO- RAZ sync (74HC163)".equals(s))
            addComponent(((ElectronicComponent) (new Counterxx163(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Ctr DCB 4bits prog -CTR-PIPO- RAZ async (74HC160)".equals(s))
            addComponent(((ElectronicComponent) (new CounterBCDxx160(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Ctr DCB 4bits prog -CTR-PIPO- RAZ sync (74HC162)".equals(s))
            addComponent(((ElectronicComponent) (new CounterBCDxx162(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("CtrDctr BIN-DCB 4bits prog -CTR-PIPO- RAZ sync".equals(s))
            addComponent(((ElectronicComponent) (new CtrBcdBinRazSync(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("CtrDctr BIN-DCB 4bits prog -CTR-PIPO- RAZ async".equals(s))
            addComponent(((ElectronicComponent) (new CtrBcdBinRazASync(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("CtrDctr BIN 5bits prog -CTR-PIPO- RAZ async".equals(s))
            addComponent(((ElectronicComponent) (new Ctr5BinRazASync(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Ram".equals(s))
        {
            DigSimFrame _tmp = frame;
            UserWantsMem(10);
        } else
        if("Rom".equals(s))
        {
            DigSimFrame _tmp1 = frame;
            UserWantsMem(9);
        } else
        if("Ram2".equals(s))
        {
            DigSimFrame _tmp2 = frame;
            UserWantsMem(23);
        } else
        if("Perceuse".equals(s))
            addComponent(((ElectronicComponent) (new PERCEUSE(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Chariot libre".equals(s))
            addComponent(((ElectronicComponent) (new ChariotLibre(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Chariot XY a capteurs h b g d".equals(s))
            addComponent(((ElectronicComponent) (new ChariotXYhbgd(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Chariot XY a sorties numeriques xy".equals(s))
            addComponent(((ElectronicComponent) (new ChariotXY7bits(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Chariot XY a capteurs et sorties numeriques".equals(s))
            addComponent(((ElectronicComponent) (new ChariotXYmixte(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Chariot XY a moteurs pas a pas unipolaires".equals(s))
            addComponent(((ElectronicComponent) (new ChariotXYPaP(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Wagonnets simple".equals(s))
            addComponent(((ElectronicComponent) (new WagonsSimple(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Wagonnets et aiguillages".equals(s))
            addComponent(((ElectronicComponent) (new WagonsAiguillages(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Jeton".equals(s))
            addComponent(((ElectronicComponent) (new JETON(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Feux de carrefour".equals(s))
            addComponent(((ElectronicComponent) (new FEUXCARREFOUR(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Clef".equals(s))
            addComponent(((ElectronicComponent) (new CLEF(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Cellule RC d'initialisation avec bouton Reset".equals(s))
            addComponent(((ElectronicComponent) (new CelluleRC(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Comparateur 8 bits (74HC688)".equals(s))
            addComponent(((ElectronicComponent) (new Comparateur8bits(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Comparateur 4 bits (74HC85)".equals(s))
            addComponent(((ElectronicComponent) (new Comparateur4bits(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("DEL rouge".equals(s))
            addComponent(((ElectronicComponent) (new LED(PinGrid, MyColor.red, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("DEL verte".equals(s))
            addComponent(((ElectronicComponent) (new LED(PinGrid, MyColor.green, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("DEL jaune".equals(s))
            addComponent(((ElectronicComponent) (new LED(PinGrid, MyColor.yellow, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("DEL bleue".equals(s))
            addComponent(((ElectronicComponent) (new LED(PinGrid, MyColor.blue, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("DEL orange".equals(s))
            addComponent(((ElectronicComponent) (new LED(PinGrid, MyColor.orange, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("DEL cyan".equals(s))
            addComponent(((ElectronicComponent) (new LED(PinGrid, MyColor.cyan, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("DEL magenta".equals(s))
            addComponent(((ElectronicComponent) (new LED(PinGrid, MyColor.magenta, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("DEL rose".equals(s))
            addComponent(((ElectronicComponent) (new LED(PinGrid, MyColor.pink, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("DEL jaune-verte-rouge".equals(s))
            addComponent(((ElectronicComponent) (new BICOLORLEDVERTICAL1(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("DEL bleue-verte-rouge".equals(s))
            addComponent(((ElectronicComponent) (new BiColorLED(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Afficheur 7 segments".equals(s))
            addComponent(((ElectronicComponent) (new SevenSegmentDisplay(PinGrid, MyColor.red, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Sonde pour analyseur logique".equals(s))
        {
            addComponent(((ElectronicComponent) (new Probe(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
            updateAnalyzer();
        } else
        if("Multiplieur 8 bits x 8 bits".equals(s))
            addComponent(((ElectronicComponent) (new MULT8x8(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Additionneur 8 bits".equals(s))
            addComponent(((ElectronicComponent) (new ADD8(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Multiplieur-ACcumulateur 8 bits x 8 bits (sorties 3S)".equals(s))
            addComponent(((ElectronicComponent) (new MAC8x8(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Horloge 2 (periode 2T & sorties decalees)".equals(s))
            addComponent(((ElectronicComponent) (new Oscillateur2T(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("4 multiplexeurs 2 vers 1 (74HC157)".equals(s))
            addComponent(((ElectronicComponent) (new FourMUX2to1(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Buffer 8 bits a sorties 3S (74HC541)".equals(s))
            addComponent(((ElectronicComponent) (new Buffer8bits(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Transceiver 8 bits a sorties 3S (74HC245)".equals(s))
            addComponent(((ElectronicComponent) (new Transceiver8bits(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Buffer 2 x 4 bits a sorties 3S (74HC244)".equals(s))
            addComponent(((ElectronicComponent) (new Buffer2x4bits(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Afficheur complement a 2".equals(s))
            addComponent(((ElectronicComponent) (new Afficheur9bits(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Symbole Trigger".equals(s))
            addComponent(((ElectronicComponent) (new TRIGGER(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Buffer 1 bit a sortie 3S".equals(s))
            addComponent(((ElectronicComponent) (new Buffer3S(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Emetteur serie 8bits".equals(s))
            addComponent(((ElectronicComponent) (new EmetteurSerie8bits(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Retard fixe (usage restreint)".equals(s))
            addComponent(((ElectronicComponent) (new RetardFixe(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Retard pur (usage restreint)".equals(s))
            addComponent(((ElectronicComponent) (new RetardPur(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Filtre RC (usage restreint)".equals(s))
            addComponent(((ElectronicComponent) (new RetardRC(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Symbole RdP-Transition".equals(s))
            addComponent(((ElectronicComponent) (new RdPtransition(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Symbole RdP-Fleche".equals(s))
            addComponent(((ElectronicComponent) (new RdPfleche(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Symbole RdP-Place".equals(s))
            addComponent(((ElectronicComponent) (new RdPplace(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Symbole RdP-Jeton".equals(s))
            addComponent(((ElectronicComponent) (new RdPjeton(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Symbole Condensateur".equals(s))
            addComponent(((ElectronicComponent) (new CapaC(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Symbole Resistance".equals(s))
            addComponent(((ElectronicComponent) (new ResR(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Pendule a sortie serie".equals(s))
            addComponent(((ElectronicComponent) (new Pendule(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Decaleur statique 4 bits a sorties 3S (74HC350)".equals(s))
            addComponent(((ElectronicComponent) (new FourBitShifter(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Encodeur prioritaire 8 vers 3 (74HC148)".equals(s))
            addComponent(((ElectronicComponent) (new Encodeur8vers3(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Encodeur prioritaire 10 vers 4 (74HC147)".equals(s))
            addComponent(((ElectronicComponent) (new Encodeur10vers4(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Cadre 1024x768".equals(s))
            addComponent(((ElectronicComponent) (new Cadre1024x768(PinGrid, MySchematicPanel.GridXOffset + 1, MySchematicPanel.GridYOffset + 1))));
        else
        if("Cadre 1280x1024".equals(s))
            addComponent(((ElectronicComponent) (new Cadre1280x1024(PinGrid, MySchematicPanel.GridXOffset + 1, MySchematicPanel.GridYOffset + 1))));
        else
        if("Cadre double 97x69".equals(s))
            addComponent(((ElectronicComponent) (new CadreDouble97x69(PinGrid, MySchematicPanel.GridXOffset + 1, MySchematicPanel.GridYOffset + 1))));
        else
        if("Cadre double 80x70".equals(s))
            addComponent(((ElectronicComponent) (new CadreDouble80x70(PinGrid, MySchematicPanel.GridXOffset + 1, MySchematicPanel.GridYOffset + 1))));
        else
        if("Decaleur statique 19 vers 8 bits (sorties 3S)".equals(s))
            addComponent(((ElectronicComponent) (new DEC8(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("SBPA".equals(s))
            addComponent(((ElectronicComponent) (new SBPA(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("SBA".equals(s))
            addComponent(((ElectronicComponent) (new SBA(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("74HC00".equals(s))
            addComponent(((ElectronicComponent) (new Boitier7400(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("74HC02".equals(s))
            addComponent(((ElectronicComponent) (new Boitier7402(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("74HC04".equals(s))
            addComponent(((ElectronicComponent) (new Boitier7404(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("74HC08".equals(s))
            addComponent(((ElectronicComponent) (new Boitier7408(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("74HC10".equals(s))
            addComponent(((ElectronicComponent) (new Boitier7410(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("74HC11".equals(s))
            addComponent(((ElectronicComponent) (new Boitier7411(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("74HC20".equals(s))
            addComponent(((ElectronicComponent) (new Boitier7420(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("74HC21".equals(s))
            addComponent(((ElectronicComponent) (new Boitier7421(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("74HC27".equals(s))
            addComponent(((ElectronicComponent) (new Boitier7427(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("74HC32".equals(s))
            addComponent(((ElectronicComponent) (new Boitier7432(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("74HC86".equals(s))
            addComponent(((ElectronicComponent) (new Boitier7486(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("74HC4002".equals(s))
            addComponent(((ElectronicComponent) (new Boitier744002(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("74HC4072".equals(s))
            addComponent(((ElectronicComponent) (new Boitier744072(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("74HC4075".equals(s))
            addComponent(((ElectronicComponent) (new Boitier744075(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("74HC7266".equals(s))
            addComponent(((ElectronicComponent) (new Boitier747266(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("74HC7266".equals(s))
            addComponent(((ElectronicComponent) (new Boitier747266(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("74HC109".equals(s))
            addComponent(((ElectronicComponent) (new Boitier74109(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Periodemetre".equals(s))
            addComponent(((ElectronicComponent) (new PERIODEMETRE(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Mini analyseur logique".equals(s))
            addComponent(((ElectronicComponent) (new MiniAnalyseur(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Analyseur logique avec horloge externe".equals(s))
            addComponent(((ElectronicComponent) (new AnalyseurHorloge(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Compteur de fronts".equals(s))
            addComponent(((ElectronicComponent) (new Compteur2fronts(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Monostable 500ms non reenclenchable".equals(s))
            addComponent(((ElectronicComponent) (new Mono500msNonRe(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Monostable 500ms reenclenchable".equals(s))
            addComponent(((ElectronicComponent) (new Mono500msRe(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Monostable 1 s reenclenchable".equals(s))
            addComponent(((ElectronicComponent) (new Mono1sRe(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Rail droit ''10'' avec ''loco''".equals(s))
            addComponent(((ElectronicComponent) (new RailDroit10Train(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Rail droit ''10''".equals(s))
            addComponent(((ElectronicComponent) (new RailDroit10(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Rail droit ''20''".equals(s))
            addComponent(((ElectronicComponent) (new RailDroit20(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Rail courbe 90\260".equals(s))
            addComponent(((ElectronicComponent) (new RailCourbe90d8x8(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Aiguillage ''10'' D".equals(s))
            addComponent(((ElectronicComponent) (new RailAiguillageDroit10Com(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Aiguillage ''10'' G".equals(s))
            addComponent(((ElectronicComponent) (new RailAiguillageGauche10Com(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Aiguillage ''10'' auto D".equals(s))
            addComponent(((ElectronicComponent) (new RailAiguillageDroit10Auto(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Aiguillage ''10'' auto G".equals(s))
            addComponent(((ElectronicComponent) (new RailAiguillageGauche10Auto(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Croisement ''5x5''".equals(s))
            addComponent(((ElectronicComponent) (new RailCroisement(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Masque gris".equals(s))
            addComponent(((ElectronicComponent) (new RailMasque1(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Masque gris clair".equals(s))
            addComponent(((ElectronicComponent) (new RailMasque2(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Rail butoir ''10'' avec ''loco''".equals(s))
            addComponent(((ElectronicComponent) (new RailButoirTrain(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Rail butoir ''10''".equals(s))
            addComponent(((ElectronicComponent) (new RailButoir(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Rail droit ''5'' auto".equals(s))
            addComponent(((ElectronicComponent) (new RailDroit5Auto(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Rail droit ''10'' auto rapide".equals(s))
            addComponent(((ElectronicComponent) (new RailDroit10Auto(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Rail droit ''20'' auto rapide".equals(s))
            addComponent(((ElectronicComponent) (new RailDroit20Auto(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Rail droit ''10'' auto lent".equals(s))
            addComponent(((ElectronicComponent) (new RailDroit10AutoLent(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Rail oblique ''10'' auto D".equals(s))
            addComponent(((ElectronicComponent) (new RailObliqueDroit(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Rail oblique ''10'' auto G".equals(s))
            addComponent(((ElectronicComponent) (new RailObliqueGauche(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Rail courbe 90\260 auto".equals(s))
            addComponent(((ElectronicComponent) (new RailCourbe90d8x8Auto(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Segment de rail H".equals(s))
            addComponent(((ElectronicComponent) (new RailSegmentH(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Segment de rail V".equals(s))
            addComponent(((ElectronicComponent) (new RailSegmentV(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Boitier de commande des rails".equals(s))
            addComponent(((ElectronicComponent) (new RailBoitierCom(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Rail courbe 45\260 auto D".equals(s))
            addComponent(((ElectronicComponent) (new RailCourbe45AutoD(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Rail courbe 45\260 auto G".equals(s))
            addComponent(((ElectronicComponent) (new RailCourbe45AutoG(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Commande manuelle fugitive ''compacte''".equals(s))
            addComponent(((ElectronicComponent) (new RailBP(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Commande manuelle permanente ''compacte''".equals(s))
            addComponent(((ElectronicComponent) (new RailSwitch(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Klaxon".equals(s))
            addComponent(((ElectronicComponent) (new RailKlaxon(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Sifflet".equals(s))
            addComponent(((ElectronicComponent) (new RailSifflet(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Mini SR".equals(s))
            addComponent(((ElectronicComponent) (new RailMiniSR(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Mini SR double".equals(s))
            addComponent(((ElectronicComponent) (new RailMini2S2R(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Generateur de codes".equals(s))
            addComponent(((ElectronicComponent) (new CodeGen(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Testeur de code I-O".equals(s))
            addComponent(((ElectronicComponent) (new CodeTest(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Pendule".equals(s))
            addComponent(((ElectronicComponent) (new HHmmss(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Compacteur de bus unidirectionnel 8 bits".equals(s))
            addComponent(((ElectronicComponent) (new Trans8toBus(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Decompacteur de bus unidirectionnel 8 bits".equals(s))
            addComponent(((ElectronicComponent) (new TransBusto8(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Poussoir double generateur d'impulsions decalees".equals(s))
            addComponent(((ElectronicComponent) (new Mini2BPtempo(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Poussoir simple generateur d'impulsion decalee".equals(s))
            addComponent(((ElectronicComponent) (new Mini1BPtempo(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Switch".equals(s))
            addComponent(((ElectronicComponent) (new Mini1Inter(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Poussoir".equals(s))
            addComponent(((ElectronicComponent) (new Mini1BP(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("NON".equals(s))
            addComponent(((ElectronicComponent) (new ISOnon(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("ET3".equals(s))
            addComponent(((ElectronicComponent) (new ISOet3(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("OU3".equals(s))
            addComponent(((ElectronicComponent) (new ISOou3(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("ET3-NON".equals(s))
            addComponent(((ElectronicComponent) (new ISOet3non(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("OU3-NON".equals(s))
            addComponent(((ElectronicComponent) (new ISOou3non(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("OUX3-NON".equals(s))
            addComponent(((ElectronicComponent) (new ISOoux3non(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("OUX3".equals(s))
            addComponent(((ElectronicComponent) (new ISOoux3(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Module de gestion de direction".equals(s))
            addComponent(((ElectronicComponent) (new RailGestionDirection(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Module de gestion de canton".equals(s))
            addComponent(((ElectronicComponent) (new RailGestionCanton(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("BP NO (T) avec resistance de pull-up".equals(s))
            addComponent(((ElectronicComponent) (new MiniBPNOPullUp(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("BP NO (T) avec resistance de pull-down".equals(s))
            addComponent(((ElectronicComponent) (new MiniBPNOPullDown(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("BP NF (R) avec resistance de pull-up".equals(s))
            addComponent(((ElectronicComponent) (new MiniBPNFPullUp(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("BP NF (R) avec resistance de pull-down".equals(s))
            addComponent(((ElectronicComponent) (new MiniBPNFPullDown(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Contact NO (T) avec resistance de pull-up".equals(s))
            addComponent(((ElectronicComponent) (new MiniSwitchNOPullUp(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Contact NO (T) avec resistance de pull-down".equals(s))
            addComponent(((ElectronicComponent) (new MiniSwitchNOPullDown(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Contact NF (R) avec resistance de pull-up".equals(s))
            addComponent(((ElectronicComponent) (new MiniSwitchNFPullUp(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Contact NF (R) avec resistance de pull-down".equals(s))
            addComponent(((ElectronicComponent) (new MiniSwitchNFPullDown(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Essai Composant 0".equals(s))
            addComponent(((ElectronicComponent) (new ESSAICOMPOSANT0(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Essai Composant 1".equals(s))
            addComponent(((ElectronicComponent) (new ESSAICOMPOSANT1(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Essai Composant 2".equals(s))
            addComponent(((ElectronicComponent) (new ESSAICOMPOSANT2(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Essai Composant 3".equals(s))
            addComponent(((ElectronicComponent) (new ESSAICOMPOSANT3(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Essai Composant 4".equals(s))
            addComponent(((ElectronicComponent) (new ESSAICOMPOSANT4(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Essai Composant 5".equals(s))
            addComponent(((ElectronicComponent) (new ESSAICOMPOSANT5(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Essai Composant 6".equals(s))
            addComponent(((ElectronicComponent) (new ESSAICOMPOSANT6(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Essai Composant 7".equals(s))
            addComponent(((ElectronicComponent) (new ESSAICOMPOSANT7(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Essai Composant 8".equals(s))
            addComponent(((ElectronicComponent) (new ESSAICOMPOSANT8(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Essai Composant 9".equals(s))
            addComponent(((ElectronicComponent) (new ESSAICOMPOSANT9(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("BP NO (normalement ouvert - contact T)".equals(s))
            addComponent(((ElectronicComponent) (new MiniBPNO(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("BP NF (normalement ferme - contact R)".equals(s))
            addComponent(((ElectronicComponent) (new MiniBPNF(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Inter NO (normalement ouvert - contact T)".equals(s))
            addComponent(((ElectronicComponent) (new MiniSwitchNO(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("Inter NF (normalement ferme - contact R)".equals(s))
            addComponent(((ElectronicComponent) (new MiniSwitchNF(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));
        else
        if("BP NO (contact T) avec resistance de pullup".equals(s))
            addComponent(((ElectronicComponent) (new MiniBPNOAvecR(PinGrid, MySchematicPanel.GridXOffset + 2, MySchematicPanel.GridYOffset + 2))));

        MySchematicPanel.WireDrawing = false;
        MySchematicPanel.JunctionDrawing = false;
        StatusMessage("Cliquez sur le corps du nouveau composant ''" + s + "'' qui est apparu en haut et à gauche, et maintenez le bouton enfoncé afin de le déplacer jusqu'à la position souhaitée.");
    }

    public boolean ImagesReady()
    {
        return gc != null;// && GridImage != null && ImageBuffer != null && MySchematic != null;
    }

    public Image getImage()
    {
    	return getImage(gc);
    }

    public Image getImage(GraphicsContext g)
    {
	    SnapshotParameters params = new SnapshotParameters();
	    params.setFill(Color.TRANSPARENT);
	    return g.getCanvas().snapshot(params, null);
    }

    public void StatusMessage(String s)
    {
        MyStatusPanel.StatusMessage(s);
    }

    public boolean SimulateRunning()
    {
        return MyStatusPanel.SimulateRunning();
    }

    public String getAppletInfo()
    {
        return "DigSim : Iwan van Rienen & Patrick Furon";
    }

    public String[][] getParameterInfo()
    {
        String as[][] = {
            {
                "schematic", "String", "Initial schematic to load"
            }, {
                "fileop", "Boolean", "Local file operations enable"
            }, {
                "soundfile", "String", "Local sound file for SoundGenerator"
            }
        };
        return as;
    }

    public void UserWantsPointer()
    {
        HelpWanted = false;
        if(MyControlPanel != null)
        {
            MyControlPanel.SelectButton("Pointer");
            MyControlPanel.UnselectButton("Wire");
            MyControlPanel.UnselectButton("Junction");
            MyControlPanel.UnselectButton("Text");
            MyControlPanel.UnselectButton("Help");
            MyControlPanel.UnselectButton("Reinitialiser");
            MyControlPanel.UnselectButton("Simulate");
            MyControlPanel.UnselectButton("New");
            MyControlPanel.UnselectButton("Open");
            MyControlPanel.UnselectButton("Save");
        }
        if(MySchematicPanel != null)
        {
            MySchematicPanel.WireDrawing = false;
            MySchematicPanel.JunctionDrawing = false;
            StatusMessage("Pour déplacer un composant, placer le pointeur de la souris dessus. Vous pouvez aussi cliquez sur l'éclair jaune ou appuyer sur ''ENTREE'' pour lancer la simulation");
        }
    }

    public void UserWantsWireDrawing()
    {
        if(HelpWanted)
        {
            HelpDialog helpdialog = new HelpDialog(frame, "Fil");
            UserWantsPointer();
            return;
        }
        if(MyControlPanel != null)
        {
            MyControlPanel.UnselectButton("Pointer");
            MyControlPanel.SelectButton("Wire");
            MyControlPanel.UnselectButton("Junction");
            MyControlPanel.UnselectButton("Text");
            MyControlPanel.UnselectButton("Help");
            MyControlPanel.UnselectButton("Reinitialiser");
            MyControlPanel.UnselectButton("Simulate");
            MyControlPanel.UnselectButton("New");
            MyControlPanel.UnselectButton("Open");
            MyControlPanel.UnselectButton("Save");
        }
        if(MySchematicPanel != null)
        {
            StatusMessage("Déplacez la souris jusqu'à la position souhaitée; cliquez et maintenez le bouton de la souris enfoncé pour dessiner un fil.");
            MySchematicPanel.WireDrawing = true;
            MySchematicPanel.JunctionDrawing = false;
            MySchematicPanel.SelectSchematic.RemoveAllComponents();
            MySchematicPanel.repaint();
        }
    }

    public void UserWantsJunctionDrawing()
    {
        if(HelpWanted)
        {
            HelpDialog helpdialog = new HelpDialog(frame, "Jonction");
            UserWantsPointer();
            return;
        }
        if(MyControlPanel != null)
        {
            MyControlPanel.UnselectButton("Pointer");
            MyControlPanel.UnselectButton("Wire");
            MyControlPanel.SelectButton("Junction");
            MyControlPanel.UnselectButton("Text");
            MyControlPanel.UnselectButton("Help");
            MyControlPanel.UnselectButton("Reinitialiser");
            MyControlPanel.UnselectButton("Simulate");
            MyControlPanel.UnselectButton("New");
            MyControlPanel.UnselectButton("Open");
            MyControlPanel.UnselectButton("Save");
        }
        if(MySchematicPanel != null)
        {
            MySchematicPanel.WireDrawing = false;
            MySchematicPanel.JunctionDrawing = true;
            StatusMessage("Déplacez la souris jusqu'à la position souhaitée; cliquez et appuyez sur le bouton de la souris pour placer une jonction.");
            MySchematicPanel.SelectSchematic.RemoveAllComponents();
            MySchematicPanel.repaint();
        }
    }

    public void UserWantsMem(int i)
    {
        Object obj = null;
        if(HelpWanted)
        {
            DigSimFrame _tmp = frame;
            HelpDialog helpdialog;
            if(i == 10)
            {
                helpdialog = new HelpDialog(frame, "RAM1");
            } else
            {
                DigSimFrame _tmp1 = frame;
                HelpDialog helpdialog1;
                if(i == 9)
                {
                    helpdialog1 = new HelpDialog(frame, "PROM");
                } else
                {
                    DigSimFrame _tmp2 = frame;
                    HelpDialog helpdialog2;
                    if(i == 23)
                        helpdialog2 = new HelpDialog(frame, "RAM2");
                }
            }
            UserWantsPointer();
            return;
        }
        if(frame != null)
        {
            if(frame.MyLoadDialog == null)
                frame.MyLoadDialog = new LoadMem(frame, "Programmation de la mémoire", i);
            UserWantsPointer();
        }
    }

    public void UserWantsTextDrawing()
    {
        if(HelpWanted)
        {
            HelpDialog helpdialog = new HelpDialog(frame, "Texte monospaced   (m)");
            UserWantsPointer();
            return;
        }
        if(MyControlPanel != null)
        {
            MyControlPanel.UnselectButton("Pointer");
            MyControlPanel.UnselectButton("Wire");
            MyControlPanel.UnselectButton("Junction");
            MyControlPanel.SelectButton("Text");
            MyControlPanel.UnselectButton("Help");
            MyControlPanel.UnselectButton("Reinitialiser");
            MyControlPanel.UnselectButton("Simulate");
            MyControlPanel.UnselectButton("New");
            MyControlPanel.UnselectButton("Open");
            MyControlPanel.UnselectButton("Save");
        }
        if(frame != null)
        {
            if(frame.MyTextDialog == null)
            {
                DigSimFrame _tmp = frame;
                frame.MyTextDialog = new TextDialog(frame, "", 5);
            }
            StatusMessage("Entrez votre texte.");
            UserWantsPointer();
        }
    }

    public void UserWantsBlackTextDrawing()
    {
        if(HelpWanted)
        {
            HelpDialog helpdialog = new HelpDialog(frame, "Texte noir  (n)");
            UserWantsPointer();
            return;
        }
        if(MyControlPanel != null)
        {
            MyControlPanel.UnselectButton("Pointer");
            MyControlPanel.UnselectButton("Wire");
            MyControlPanel.UnselectButton("Junction");
            MyControlPanel.SelectButton("Text");
            MyControlPanel.UnselectButton("Help");
            MyControlPanel.UnselectButton("Reinitialiser");
            MyControlPanel.UnselectButton("Simulate");
            MyControlPanel.UnselectButton("New");
            MyControlPanel.UnselectButton("Open");
            MyControlPanel.UnselectButton("Save");
        }
        if(frame != null)
        {
            if(frame.MyTextDialog == null)
            {
                DigSimFrame _tmp = frame;
                frame.MyTextDialog = new TextDialog(frame, "", 13);
            }
            StatusMessage("Entrez votre texte.");
            UserWantsPointer();
        }
    }

    public void UserWantsRdPTextDrawing()
    {
        if(HelpWanted)
        {
            HelpDialog helpdialog = new HelpDialog(frame, "Texte gris (RdP)  (g)");
            UserWantsPointer();
            return;
        }
        if(MyControlPanel != null)
        {
            MyControlPanel.UnselectButton("Pointer");
            MyControlPanel.UnselectButton("Wire");
            MyControlPanel.UnselectButton("Junction");
            MyControlPanel.SelectButton("Text");
            MyControlPanel.UnselectButton("Help");
            MyControlPanel.UnselectButton("Reinitialiser");
            MyControlPanel.UnselectButton("Simulate");
            MyControlPanel.UnselectButton("New");
            MyControlPanel.UnselectButton("Open");
            MyControlPanel.UnselectButton("Save");
        }
        if(frame != null)
        {
            if(frame.MyTextDialog == null)
            {
                DigSimFrame _tmp = frame;
                frame.MyTextDialog = new TextDialog(frame, "", 21);
            }
            StatusMessage("Entrez votre texte.");
            UserWantsPointer();
        }
    }

    public void UserWantsRedTextDrawing()
    {
        if(HelpWanted)
        {
            HelpDialog helpdialog = new HelpDialog(frame, "Texte rouge  (r)");
            UserWantsPointer();
            return;
        }
        if(MyControlPanel != null)
        {
            MyControlPanel.UnselectButton("Pointer");
            MyControlPanel.UnselectButton("Wire");
            MyControlPanel.UnselectButton("Junction");
            MyControlPanel.SelectButton("Text");
            MyControlPanel.UnselectButton("Help");
            MyControlPanel.UnselectButton("Reinitialiser");
            MyControlPanel.UnselectButton("Simulate");
            MyControlPanel.UnselectButton("New");
            MyControlPanel.UnselectButton("Open");
            MyControlPanel.UnselectButton("Save");
        }
        if(frame != null)
        {
            if(frame.MyTextDialog == null)
            {
                DigSimFrame _tmp = frame;
                frame.MyTextDialog = new TextDialog(frame, "", 15);
            }
            StatusMessage("Entrez votre texte.");
            UserWantsPointer();
        }
    }

    public void UserWantsBlueTextDrawing()
    {
        if(HelpWanted)
        {
            HelpDialog helpdialog = new HelpDialog(frame, "Texte bleu    (b)");
            UserWantsPointer();
            return;
        }
        if(MyControlPanel != null)
        {
            MyControlPanel.UnselectButton("Pointer");
            MyControlPanel.UnselectButton("Wire");
            MyControlPanel.UnselectButton("Junction");
            MyControlPanel.SelectButton("Text");
            MyControlPanel.UnselectButton("Help");
            MyControlPanel.UnselectButton("Reinitialiser");
            MyControlPanel.UnselectButton("Simulate");
            MyControlPanel.UnselectButton("New");
            MyControlPanel.UnselectButton("Open");
            MyControlPanel.UnselectButton("Save");
        }
        if(frame != null)
        {
            if(frame.MyTextDialog == null)
            {
                DigSimFrame _tmp = frame;
                frame.MyTextDialog = new TextDialog(frame, "", 17);
            }
            StatusMessage("Entrez votre texte.");
            UserWantsPointer();
        }
    }

    public void UserWantsGreenTextDrawing()
    {
        if(HelpWanted)
        {
            HelpDialog helpdialog = new HelpDialog(frame, "Texte vert    (v)");
            UserWantsPointer();
            return;
        }
        if(MyControlPanel != null)
        {
            MyControlPanel.UnselectButton("Pointer");
            MyControlPanel.UnselectButton("Wire");
            MyControlPanel.UnselectButton("Junction");
            MyControlPanel.SelectButton("Text");
            MyControlPanel.UnselectButton("Help");
            MyControlPanel.UnselectButton("Reinitialiser");
            MyControlPanel.UnselectButton("Simulate");
            MyControlPanel.UnselectButton("New");
            MyControlPanel.UnselectButton("Open");
            MyControlPanel.UnselectButton("Save");
        }
        if(frame != null)
        {
            if(frame.MyTextDialog == null)
            {
                DigSimFrame _tmp = frame;
                frame.MyTextDialog = new TextDialog(frame, "", 19);
            }
            StatusMessage("Entrez votre texte.");
            UserWantsPointer();
        }
    }

    public void UserWantsHelp()
    {
        if(MyControlPanel != null)
        {
            MyControlPanel.UnselectButton("Pointer");
            MyControlPanel.UnselectButton("Wire");
            MyControlPanel.UnselectButton("Junction");
            MyControlPanel.UnselectButton("Text");
            MyControlPanel.SelectButton("Help");
            MyControlPanel.UnselectButton("Reinitialiser");
            MyControlPanel.UnselectButton("Simulate");
            MyControlPanel.UnselectButton("New");
            MyControlPanel.UnselectButton("Open");
            MyControlPanel.UnselectButton("Save");
        }
        HelpDialog helpdialog;
        if(HelpWanted)
        {
            helpdialog = new HelpDialog(frame, "Aide");
        } else
        {
            StatusMessage("Choisissez un composant ou un élément du menu pour obtenir de l'aide ou des informations ; après avoir obtenu de l'aide sur un composant, n'oubliez pas de cliquer sur celui-ci.");
            HelpWanted = true;
        }
    }

    public void UserWantsSimulate()
    {
        if(MyControlPanel != null)
        {
            MyControlPanel.UnselectButton("Pointer");
            MyControlPanel.UnselectButton("Wire");
            MyControlPanel.UnselectButton("Junction");
            MyControlPanel.UnselectButton("Text");
            MyControlPanel.UnselectButton("Help");
            MyControlPanel.UnselectButton("Reinitialiser");
            MyControlPanel.SelectButton("Simulate");
            MyControlPanel.UnselectButton("New");
            MyControlPanel.UnselectButton("Open");
            MyControlPanel.UnselectButton("Save");
            MyControlPanel.UnselectButton("Paste");
        }
        if(HelpWanted)
        {
            HelpDialog helpdialog = new HelpDialog(frame, "Simulation");
            UserWantsPointer();
            return;
        }
        if(!IsSimulating())
        {
            if(MyControlPanel != null)
            {
                MyControlPanel.DisableButton("Pointer");
                MyControlPanel.DisableButton("Wire");
                MyControlPanel.DisableButton("Junction");
                MyControlPanel.DisableButton("Text");
                MyControlPanel.DisableButton("New");
                MyControlPanel.DisableButton("Open");
                MyControlPanel.DisableButton("Save");
                MyControlPanel.DisableButton("Paste");
                MyControlPanel.DisableButton("Reinitialiser");
            }
            frame.DisableAllMenus();
            frame.StartMenuItem.setDisable(true);
            frame.StopMenuItem.setDisable(false);
            SimulateStart();
            repaint();
        } else
        {
            if(MyControlPanel != null)
            {
                MyControlPanel.EnableButton("Pointer");
                MyControlPanel.EnableButton("Wire");
                MyControlPanel.EnableButton("Junction");
                MyControlPanel.EnableButton("Text");
                MyControlPanel.EnableButton("New");
                if(EnableFileOperations)
                {
                    MyControlPanel.EnableButton("Open");
                    MyControlPanel.EnableButton("Save");
                }
            }
            MyControlPanel.EnableButton("Reinitialiser");
            frame.EnableAllMenus();
            frame.StopMenuItem.setDisable(true);
            frame.StartMenuItem.setDisable(false);
            SimulateStop();
            UserWantsPointer();
            repaint();
        }
    }

    public void UserWantsReinitialiser()
    {
        if(HelpWanted)
        {
            HelpDialog helpdialog = new HelpDialog(frame, "Reinitialiser");
            UserWantsPointer();
            return;
        }
        if(MyControlPanel != null)
        {
            MyControlPanel.UnselectButton("Pointer");
            MyControlPanel.UnselectButton("Wire");
            MyControlPanel.UnselectButton("Junction");
            MyControlPanel.UnselectButton("Text");
            MyControlPanel.UnselectButton("Help");
            MyControlPanel.SelectButton("Reinitialiser");
            MyControlPanel.UnselectButton("Simulate");
            MyControlPanel.UnselectButton("New");
            MyControlPanel.UnselectButton("Open");
            MyControlPanel.UnselectButton("Save");
            MyControlPanel.UnselectButton("Paste");
        }
        UserWantsReInitialisationSimulation();
    }

    public void UserWantsNewSchematic()
    {
        if(MySchematic == null)
            return;
        if(MyControlPanel != null)
        {
            MyControlPanel.UnselectButton("Pointer");
            MyControlPanel.UnselectButton("Wire");
            MyControlPanel.UnselectButton("Junction");
            MyControlPanel.UnselectButton("Text");
            MyControlPanel.UnselectButton("Help");
            MyControlPanel.UnselectButton("Reinitialiser");
            MyControlPanel.UnselectButton("Simulate");
            MyControlPanel.SelectButton("New");
            MyControlPanel.UnselectButton("Open");
            MyControlPanel.UnselectButton("Save");
        }
        if(HelpWanted)
        {
            HelpDialog helpdialog = new HelpDialog(frame, "Nouveau");
            UserWantsPointer();
            return;
        }
        if(MySchematic.Modified)
        {
            if(frame.NewDialog == null)
            {
                String as[] = {
                    "OK", "Annuler"
                };
                frame.NewDialog = new SimpleDialog(frame, "Création d'une nouvelle simulation", "Vous n'avez pas sauvegardé votre simulation. Abandonnez vous le travail en cours?", as, 2, 1, 2, 2);
                UserWantsPointer();
            }
        } else
        {
            MySchematic.DestroyComponents(PinGrid);
            MySchematic = new Schematic();
            InitPinGrids();
            frame.setTitle("DigSim ''RailWay'' 2012.2.0 [sans nom] ; penser à nommer et enregistrer votre simulation (fichier, enregistrer sous)");
            MySchematic.FileName = null;
            MySchematic.Modified = false;
            MySchematicPanel.repaint();
            UserWantsPointer();
            updateAnalyzer();
        }
    }

    public void UserWantsOpenSchematic()
    {
        if(MySchematic == null)
            return;
        if(MyControlPanel != null)
        {
            MyControlPanel.UnselectButton("Pointer");
            MyControlPanel.UnselectButton("Wire");
            MyControlPanel.UnselectButton("Junction");
            MyControlPanel.UnselectButton("Text");
            MyControlPanel.UnselectButton("Help");
            MyControlPanel.UnselectButton("Reinitialiser");
            MyControlPanel.UnselectButton("Simulate");
            MyControlPanel.UnselectButton("New");
            MyControlPanel.SelectButton("Open");
            MyControlPanel.UnselectButton("Save");
        }
        if(HelpWanted)
        {
            HelpDialog helpdialog = new HelpDialog(frame, "Ouvrir");
            UserWantsPointer();
            return;
        }
        Schematic schematic = frame.DoFileOpenDialog(PinGrid, "Ouvrir une simulation");
        if(schematic != null)
        {
            if(PinGrid != null)
                MySchematic.DestroyComponents(PinGrid);
            MySchematic = schematic;
            MySchematic.PlacePinsHere(PinGrid);
            frame.setTitle("DigSim ''RailWay'' 2012.2.0 [" + MySchematic.FileName + "] ;  importé et non enregistré");
            MySchematicPanel.repaint();
            UserWantsPointer();
            UserWantsReInitialisationSimulation();
        }
    }

    public void UserWantsOpenExample(String s)
    {
        if(MySchematic == null)
            return;
        if(MySchematic.Modified)
        {
            if(frame.OpenExampleDialog == null)
            {
                String as[] = {
                    "OK", "Annuler"
                };
                frame.ExampleFileName = s;
                DigSimFrame _tmp = frame;
                frame.OpenExampleDialog = new SimpleDialog(frame, "Aide spécifique (tests et simulations)", "Vous n'avez pas sauvegardé votre simulation. Abandonnez vous le travail en cours?", as, 2, 1, 7, 2);
                UserWantsPointer();
            }
        } else
        {
            frame.LoadExample(s);
            UserWantsPointer();
        }
    }

    public void UserWantsSaveSchematic()
    {
        if(MySchematic == null)
            return;
        if(MyControlPanel != null)
        {
            MyControlPanel.UnselectButton("Pointer");
            MyControlPanel.UnselectButton("Wire");
            MyControlPanel.UnselectButton("Junction");
            MyControlPanel.UnselectButton("Text");
            MyControlPanel.UnselectButton("Help");
            MyControlPanel.UnselectButton("Reinitialiser");
            MyControlPanel.UnselectButton("Simulate");
            MyControlPanel.UnselectButton("New");
            MyControlPanel.UnselectButton("Open");
            MyControlPanel.SelectButton("Save");
        }
        if(HelpWanted)
        {
            HelpDialog helpdialog = new HelpDialog(frame, "Enregistrer");
            UserWantsPointer();
            return;
        }
        if(MySchematic.FileName != null && MySchematic.FileDir != null)
            frame.DoFileSaveDialog(MySchematic, false, "Sauvegarder le schéma");
        else
            frame.DoFileSaveDialog(MySchematic, true, "Sauvegarder le schéma sous un nouveau nom");
        frame.setTitle("DigSim ''RailWay'' 2012.2.0 [" + MySchematic.FileName + "] ;  ré-enregistré");
        MySchematicPanel.repaint();
        UserWantsPointer();
    }

    public void UserWantsCopySchematic()
    {
        HelpDialog helpdialog;
        if(HelpWanted)
        {
            helpdialog = new HelpDialog(frame, "Copier");
        } else
        {
            MySchematicPanel.Copy();
            frame.PasteMenuItem.setDisable(false);
            MyControlPanel.EnableButton("Paste");
        }
        MyControlPanel.UnselectButton("Copy");
        UserWantsPointer();
    }

    public void UserWantsPasteSchematic()
    {
        HelpDialog helpdialog;
        if(HelpWanted)
            helpdialog = new HelpDialog(frame, "Coller");
        else
            MySchematicPanel.Paste();
        MyControlPanel.UnselectButton("Paste");
        frame.setTitle("DigSim ''RailWay'' 2012.2.0 [" + MySchematic.FileName + "] ;  modifié et non ré-enregistré");
        UserWantsPointer();
        frame.setTitle("DigSim ''RailWay'' 2012.2.0 [" + MySchematic.FileName + "] ;  câblage modifié ? -> penser à enregistrer régulièrement");
    }

    public void UserWantsCutSchematic()
    {
        HelpDialog helpdialog;
        if(HelpWanted)
        {
            helpdialog = new HelpDialog(frame, "Supprimer");
        } else
        {
            MySchematicPanel.Cut();
            frame.CutMenuItem.setDisable(true);
            frame.CopyMenuItem.setDisable(true);
            frame.PasteMenuItem.setDisable(false);
            MyControlPanel.DisableButton("Cut");
            MyControlPanel.DisableButton("Copy");
            MyControlPanel.EnableButton("Paste");
        }
        MyControlPanel.UnselectButton("Cut");
        UserWantsPointer();
        frame.setTitle("DigSim ''RailWay'' 2012.2.0 [" + MySchematic.FileName + "] ;  câblage modifié ? -> penser à enregistrer régulièrement");
    }

    public void UserWantsRotateSchematic()
    {
        HelpDialog helpdialog;
        if(HelpWanted)
            helpdialog = new HelpDialog(frame, "Rotation");
        else
            MySchematicPanel.Rotate();
        UserWantsPointer();
        frame.setTitle("DigSim ''RailWay'' 2012.2.0 [" + MySchematic.FileName + "] ;  câblage modifié ? -> penser à enregistrer régulièrement");
    }

    public void UserWantsOpenExample()
    {
        HelpDialog helpdialog;
        if(HelpWanted)
            helpdialog = new HelpDialog(frame, "Aide spécifique (tests et simulations)");
        else
        if(MyExamplesFrame == null)
            MyExamplesFrame = new ExamplesFrame(this);
    }

    public void UserWantsCopyToSchematic()
    {
        HelpDialog helpdialog;
        if(HelpWanted)
        {
            helpdialog = new HelpDialog(frame, "Copier vers");
        } else
        {
            MySchematicPanel.CopyTo();
            frame.PasteMenuItem.setDisable(false);
            MyControlPanel.EnableButton("Paste");
        }
        UserWantsPointer();
    }

    public void UserWantsPasteFromSchematic()
    {
        HelpDialog helpdialog;
        if(HelpWanted)
            helpdialog = new HelpDialog(frame, "Coller a partir de");
        else
            MySchematicPanel.PasteFrom();
        UserWantsReInitialisationSimulation();
        UserWantsPointer();
        frame.setTitle("DigSim ''RailWay'' 2012.2.0 [" + MySchematic.FileName + "] ;  câblage modifié ? -> penser à enregistrer régulièrement");
    }

    public void UserWantsOptions()
    {
        HelpDialog helpdialog;
        if(HelpWanted)
            helpdialog = new HelpDialog(frame, "Vitesse et options");
        else
        if(MyOptionsFrame == null)
            MyOptionsFrame = new OptionsFrame(this);
        UserWantsPointer();
    }

    public void UserWantsAnalyzer()
    {
        HelpDialog helpdialog;
        if(HelpWanted)
            helpdialog = new HelpDialog(frame, "Analyseur logique");
        else
        if(MyAnalyzerFrame == null)
            MyAnalyzerFrame = new AnalyzerFrame(this);
    }

    public void UserWantsExportAsScript()
    {
        HelpDialog helpdialog;
        if(HelpWanted)
            helpdialog = new HelpDialog(frame, "Exporter");
        else
        if(MyScriptFrame == null)
        {
            MyScriptFrame = new ScriptFrame(frame, ScriptFrame.EXPORT);
            MyScriptFrame.setScript(MySchematic.ExportAsScript());
            MyScriptFrame.show();
            StatusMessage("Copier ce script et sauvegardez le sur votre disque pour un usage ultérieur");
        }
    }

    public void UserWantsImportFromScript()
    {
        if(MySchematic == null)
            return;
        HelpDialog helpdialog;
        if(HelpWanted)
            helpdialog = new HelpDialog(frame, "Importer");
        else
        if(MyScriptFrame == null)
        {
            MyScriptFrame = new ScriptFrame(frame, ScriptFrame.IMPORT);
            MyScriptFrame.show();
            StatusMessage("Coller un script (sans erreur de syntaxe) afin d'afficher un nouveau schéma");
        }
    }

    public void UserWantsReInitialisationSimulation()
    {
        HelpDialog helpdialog;
        if(HelpWanted)
        {
            helpdialog = new HelpDialog(frame, "Reinitialiser");
        } else
        {
            MySchematicPanel.SelectAll();
            MySchematicPanel.Copy();
            MySchematic.DestroyComponents(PinGrid);
            InitPinGrids();
            MySchematicPanel.ReInit();
            MySchematic.SetAllComponentsUnSelected();
            SimulateSetUp();
            SimulateStart();
            if(SimulateRunning())
                SimulateStop();
            MySchematicPanel.repaint();
            MySchematicPanel.CopySchematic = null;
            if (MyAnalyzerFrame != null)
            	MyAnalyzerFrame.close();
            MyAnalyzerFrame = null;
        }
    }

//    public boolean handleEvent(Event event)
//    {
//        return super.handleEvent(event);
//    }

    public void updateAnalyzer()
    {
        if(MyAnalyzerFrame != null)
            MyAnalyzerFrame.repaint();
    }

    public boolean keyDown(KeyEvent event)
    {
    	int i = event.getCharacter().getBytes()[0];
        return keyDown(i, event.isControlDown(), event.isShiftDown());
    }

    public boolean keyDown(int i, boolean isControlDown, boolean isShiftDown) {
//		System.out.println("la touche est:" + i);
        if(!IsSimulating())
        {
            if(i == 109 || i == 77)
            {
                UserWantsTextDrawing();
                return true;
            }
            if(i == 110 || i == 78)
            {
                UserWantsBlackTextDrawing();
                return true;
            }
            if(i == 103 || i == 71)
            {
                UserWantsRdPTextDrawing();
                return true;
            }
            if(i == 114 || i == 82)
            {
                UserWantsRedTextDrawing();
                return true;
            }
            if(i == 98 || i == 66)
            {
                UserWantsBlueTextDrawing();
                return true;
            }
            if(i == 118 || i == 86)
            {
                UserWantsGreenTextDrawing();
                return true;
            }
            if(i == 127 || i == 8)
            {
                UserWantsCutSchematic();
                return true;
            }
            if(i == 112 || i == 32)
            {
                UserWantsPointer();
                return true;
            }
            if(i == 102 || i == 119)
            {
                UserWantsWireDrawing();
                return true;
            }
            if(i == 106 || i == 74)
            {
                UserWantsJunctionDrawing();
                return true;
            }
            if(i == 42 || i == 181 || i == 9)
            {
                UserWantsReInitialisationSimulation();
                return true;
            }
            if(i == 105 || i == 73)
            {
                UserWantsImportFromScript();
                return true;
            }
            if(i == 116 || i == 84)
            {
                UserWantsRotateSchematic();
                return true;
            }
        }
        if(i == 10)
        {
            UserWantsSimulate();
            return true;
        }
        if(i == 111 || i == 79)
        {
            UserWantsOptions();
            return true;
        }
        if(i == 97 || i == 65)
        {
            if(MyAnalyzerFrame == null)
            {
                UserWantsAnalyzer();
            } else
            {
                MyAnalyzerFrame.close();
                MyAnalyzerFrame = null;
            }
            return true;
        }
        if(i == 104 || i == 63)
        {
            UserWantsHelp();
            return true;
        }
        if(i == 101 || i == 69)
        {
            UserWantsExportAsScript();
            return true;
        }
        if(i == 83 || i == 115)
        {
            UserWantsSaveSchematic();
            return true;
        }
        if(isControlDown)
        {
            switch(i)
            {
            case 5: // '\005'
                UserWantsExportAsScript();
                return true;

            case 19: // '\023'
                if(isShiftDown)
                {
                    frame.DoFileSaveDialog(MySchematic, true, "Sauvegarder le schéma sous un nouveau nom");
                    frame.setTitle("DigSim ''RailWay'' 2012.2.0 [" + MySchematic.FileName + "]\260\260\260\260\260\260\260");
                    MySchematicPanel.repaint();
                    return true;
                } else
                {
                    UserWantsSaveSchematic();
                    return true;
                }

            case 109: // 'm'
                if(!IsSimulating())
                    UserWantsTextDrawing();
                return true;

            case 110: // 'n'
                if(!IsSimulating())
                    UserWantsBlackTextDrawing();
                return true;

            case 103: // 'g'
                if(!IsSimulating())
                    UserWantsRdPTextDrawing();
                return true;

            case 114: // 'r'
                if(!IsSimulating())
                    UserWantsRedTextDrawing();
                return true;

            case 98: // 'b'
                if(!IsSimulating())
                    UserWantsBlueTextDrawing();
                return true;

            case 118: // 'v'
                if(!IsSimulating())
                    UserWantsGreenTextDrawing();
                return true;

            case 24: // '\030'
                if(!IsSimulating())
                    UserWantsCutSchematic();
                return true;

            case 3: // '\003'
                if(!IsSimulating())
                    UserWantsCopySchematic();
                return true;

            case 22: // '\026'
                if(!IsSimulating())
                    UserWantsPasteSchematic();
                return true;

            case 18: // '\022'
                if(!IsSimulating())
                    UserWantsRotateSchematic();
                return true;

            case 9: // '\t'
                if(!IsSimulating())
                    UserWantsImportFromScript();
                return true;

            case 1: // '\001'
                if(!IsSimulating())
                    MySchematicPanel.SelectAll();
                return true;

            case 14: // '\016'
                if(!IsSimulating())
                    UserWantsNewSchematic();
                return true;

            case 15: // '\017'
                if(!IsSimulating())
                    UserWantsOpenSchematic();
                return true;

            case 127: // '\177'
                if(!IsSimulating())
                    UserWantsCutSchematic();
                return true;
            }
//            System.out.println("Pas sensible");
            return false;
        } else
        {
//            System.out.println("Pas sensible");
            return false;
        }
	}

    DigSimFrame frame;
    ControlPanel MyControlPanel;
    StatusPanel MyStatusPanel;
    SchematicPanel MySchematicPanel;
    Button displayb;
    Button disposeb;
//    Thread killme;
    String SchematicName;
    public Schematic MySchematic;
    Pin PinGrid[][];
    String message;
    static int MaxXPoints = 100;
    static int MaxYPoints = 100;
    Image GridImage;
//    Image ImageBuffer;
//    Graphics ibg;
    int OffScreenWidth;
    int OffScreenHeight;
    static Color BackGroundColor = null;
    static final int GridStep = 8;
    static final int hgs = 4;
    boolean SchematicPanelPainted;
    boolean HelpWanted;
    String TextFileRequested;
    boolean EnableFileOperations;
    ExamplesFrame MyExamplesFrame;
    String RequestedText;
    boolean RequestedTextFileRead;
    boolean RequestedTextFileError;
    OptionsFrame MyOptionsFrame;
    public AnalyzerFrame MyAnalyzerFrame;
    ScriptFrame MyScriptFrame;
    boolean StopAtShortCircuit;
    boolean StopAtLoop;
    boolean AnalyzerAutoPopUp;
    public static String DisplayType = "color";
    static MyColor DisplayColorMap = null;
    static URL SpecialDocumentBase = null;
    public static AudioClip sound = null;
    public static AudioClip coucou = null;
    public static AudioClip hibou = null;
    public static AudioClip alarm = null;
    public static AudioClip traincrash = null;
    public static AudioClip trainklaxon = null;
    public static AudioClip trainsifflet = null;
    String sound_name;
    int SimulationSpeed;
    Canvas canvas;
    VBox vb;
    GraphicsContext gc;
    Stage mainStage;
    Timeline animation;
    
    static boolean useAnimation = false;
    
	public static void main(String[] args) {
		launch(args);
	}
}
