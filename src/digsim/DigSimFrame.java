package digsim;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Vector;

import com.sun.javafx.tk.Toolkit;

import digsim.DigSim;
import digsim.Schematic;
import digsim.components.Caption;
import digsim.components.CaptionBlack;
import digsim.components.CaptionBlue;
import digsim.components.CaptionGreen;
import digsim.components.CaptionRdP;
import digsim.components.CaptionRed;
import digsim.components.LoadMem;
import digsim.components.Ram;
import digsim.components.Ram2;
import digsim.components.Rom;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

// Referenced classes of package digsim:
//            ControlPanel, SchematicPanel, StatusPanel, Caption,
//            CaptionBlack, CaptionRdP, CaptionRed, CaptionBlue,
//            CaptionGreen, Rom, Ram, Ram2,
//            Schematic, SimpleDialog, HelpDialog, DigSim,
//            TextDialog, LoadMem, Pin

public class DigSimFrame extends Stage
{

    public DigSimFrame(DigSim digsim)
    {
        //super("DigSim ''RailWay'' 2012.2.0");
        ExitDialog = null;
        NewDialog = null;
        OpenDialog = null;
        OpenExampleDialog = null;
        MyTextDialog = null;
        MyTextChangeDialog = null;
        MyTextProbeChangeDialog = null;
        MyLoadDialog = null;
        MyMonoChangeDialog = null;
        MyPLA5x4ChangeDialog = null;
        MyZoneRChangeDialog = null;
        MyTextChangeOscillator2Dialog = null;
        SaveFileDirectory = null;
        SaveFileName = null;
        ExampleFileName = null;
        MenuItemsToDisable = new Vector<MenuItem>();
        applet = digsim;
        BorderPane bp = new BorderPane();
        applet.MyControlPanel = new ControlPanel(digsim);
//        applet.MyControlPanel.setPrefHeight(24);
        bp.setBottom(applet.MyStatusPanel = new StatusPanel(digsim));
        VBox vb = new VBox();
//        vb.setFillWidth(true);
        applet.MySchematicPanel = new SchematicPanel(digsim);
        //ScrollPane sp = applet.MySchematicPanel;
//        VBox.setVgrow(applet.MySchematicPanel, Priority.ALWAYS);
//        vb.setPrefHeight(Double.MAX_VALUE);
//        vb.getChildren().add(applet.MySchematicPanel);
        bp.setCenter(applet.MySchematicPanel);//vb);
        bp.setPrefHeight(applet.MaxYPoints * 3 + 100);
//        applet.MySchematicPanel.setFitToWidth(true);
//        applet.MySchematicPanel.setFitToHeight(true);

//        bp.setPrefSize(800, 600);
        RegisterComponentNames();

        Group root = new Group();
        Scene scene = new Scene(root);// 500, 500,true,SceneAntialiasing.DISABLED);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
            	keyDown(event);
            }
        });
        setScene(scene);
        MenuBar menuBar = DigSimMenuBar();
        
        BorderPane topPane = new BorderPane();
        topPane.setTop(menuBar);
        topPane.setCenter(applet.MyControlPanel);
        bp.setTop(topPane);
        root.getChildren().addAll(bp);
        
        DisableAllMenus();
//        setFont(Font.font("Sans-serif", 12));
//        Toolkit toolkit = Toolkit.getDefaultToolkit();
//        Dimension dimension = toolkit.getScreenSize();
//        float f = dimension.width;
//        float f1 = dimension.height;
//        if(f1 <= 600F)
//            setSize(dimension.width, dimension.height);
//        else
//        if(f1 == 768F)
//            setSize(800, 600);
//        else
//            setSize(1024, 768);
        setMaximized(true);
        show();

    }

    public void DisableAllMenus()
    {
        for(int i = 0; i < MenuItemsToDisable.size(); i++)
        {
            MenuItem menuitem = (MenuItem)MenuItemsToDisable.elementAt(i);
            menuitem.setDisable(true);
        }

    }

    public void EnableAllMenus()
    {
        for(int i = 0; i < MenuItemsToDisable.size(); i++)
        {
            MenuItem menuitem = (MenuItem)MenuItemsToDisable.elementAt(i);
            menuitem.setDisable(false);
        }

        CutMenuItem.setDisable(true);
        CopyMenuItem.setDisable(true);
        RotateMenuItem.setDisable(false);
        applet.MyControlPanel.DisableButton("Cut");
        applet.MyControlPanel.DisableButton("Copy");
        if(applet.MySchematicPanel.CopySchematic != null && applet.MySchematicPanel.CopySchematic.size() > 0)
        {
            PasteMenuItem.setDisable(false);
            applet.MyControlPanel.EnableButton("Paste");
        } else
        {
            PasteMenuItem.setDisable(true);
            applet.MyControlPanel.DisableButton("Paste");
        }
    }

    public void RegisterComponentNames()
    {
        AvailableComponents = new Vector<String>();
        AvailableComponents.addElement("Wire");
        AvailableComponents.addElement("Junction");
        AvailableComponents.addElement("Vcc (niveau logique 1)");
        AvailableComponents.addElement("Masse (niveau logique 0)");
        AvailableComponents.addElement("Colorieur");
        AvailableComponents.addElement("Contact R (type repos)");
        AvailableComponents.addElement("Contact T (type travail)");
        AvailableComponents.addElement("Bouton poussoir");
        AvailableComponents.addElement("Etage tampon (buffer)");
        AvailableComponents.addElement("Porte_''Non''_(inverseur_complementation)");
        AvailableComponents.addElement("AND2 = ''Et'' -2 entrees-");
        AvailableComponents.addElement("AND3 = ''Et'' -3 entrees-");
        AvailableComponents.addElement("AND4 = ''Et'' -4 entrees-");
        AvailableComponents.addElement("NAND2 = ''Et-Non'' -2 entrees-");
        AvailableComponents.addElement("NAND3 = ''Et-Non'' -3 entrees-");
        AvailableComponents.addElement("NAND4 = ''Et-Non'' -4 entrees-");
        AvailableComponents.addElement("OR2 = ''Ou'' -2 entrees-");
        AvailableComponents.addElement("OR3 = ''Ou'' -3 entrees-");
        AvailableComponents.addElement("OR4 = ''Ou'' -4 entrees-");
        AvailableComponents.addElement("NOR2 = ''Ou-Non'' -2 entrees-");
        AvailableComponents.addElement("NOR3 = ''Ou-Non'' -3 entrees-");
        AvailableComponents.addElement("NOR4 = ''Ou-Non'' -4 entrees-");
        AvailableComponents.addElement("NOR5 = ''Ou-Non'' -5 entrees-");
        AvailableComponents.addElement("XOR2 = ''OuExclusif'' -2 entrees-");
        AvailableComponents.addElement("XNOR2 = ''OuExclusif-Non'' -2 entrees-");
        AvailableComponents.addElement("Latch SR");
        AvailableComponents.addElement("Latch SRH (a verrouillage)");
        AvailableComponents.addElement("Latch D (transparent)");
        AvailableComponents.addElement("FlipFlop D ''edge-triggered''");
        AvailableComponents.addElement("Bistable T");
        AvailableComponents.addElement("FlipFlop JK ''edge-triggered''");
        AvailableComponents.addElement("FlipFlop JK ''edge-triggered'' avec entrees asynchrones");
        AvailableComponents.addElement("FlipFlop JK! ''edge-triggered'' avec entrees asynchrones");
        AvailableComponents.addElement("FlipFlop T ''edge-triggered''");
        AvailableComponents.addElement("Registre de 8 FlipFlop D -PIPO- 3S (74HC273 74HC374)");
        AvailableComponents.addElement("Registre de 8 FlipFlop D -PIPOinv- 3S (74HC534 564)");
        AvailableComponents.addElement("Registre de 8 latches D transparents 3S (74HC373)");
        AvailableComponents.addElement("DEL rouge");
        AvailableComponents.addElement("DEL verte");
        AvailableComponents.addElement("DEL jaune");
        AvailableComponents.addElement("DEL bleue");
        AvailableComponents.addElement("DEL orange");
        AvailableComponents.addElement("DEL cyan");
        AvailableComponents.addElement("DEL magenta");
        AvailableComponents.addElement("DEL rose");
        AvailableComponents.addElement("DEL bleue-verte-rouge");
        AvailableComponents.addElement("Afficheur 7 segments");
        AvailableComponents.addElement("Horloge de periode ajustable");
        AvailableComponents.addElement("Horloge 1 (periode T)");
        AvailableComponents.addElement("Sonde pour analyseur logique");
        AvailableComponents.addElement("Decodeur-latch 7 segments");
        AvailableComponents.addElement("Additionneur complet 1 bit");
        AvailableComponents.addElement("Decodeur-demultiplexeur 3 vers 8 (74HC138)");
        AvailableComponents.addElement("Compteur binaire 4 bits");
        AvailableComponents.addElement("CtrDCtr BIN 4bits chargement async a 0 ou 15");
        AvailableComponents.addElement("CtrDctr DCB 4bits chargement async a 0 ou 9");
        AvailableComponents.addElement("CtrDCtr BIN 8bits chargement async a 0 ou 255");
        AvailableComponents.addElement("Registre 8bits -SIPO-SISO- 3S (~74HC164)");
        AvailableComponents.addElement("Registre 8bits -PISO- (74HC165)");
        AvailableComponents.addElement("Multiplexeur 2 vers 1");
        AvailableComponents.addElement("Multiplexeur 4 vers 1 (74HC153)");
        AvailableComponents.addElement("Multiplexeur 8 vers 1 (74HC151)");
        AvailableComponents.addElement("Encodeur prioritaire 8 vers 3 (74HC148)");
        AvailableComponents.addElement("Encodeur prioritaire 10 vers 4 (74HC147)");
        AvailableComponents.addElement("Ctr DCB 4bits prog -CTR-PIPO- RAZ async (74HC160)");
        AvailableComponents.addElement("Ctr BIN 4bits prog -CTR-PIPO- RAZ async (74HC161)");
        AvailableComponents.addElement("Ctr DCB 4bits prog -CTR-PIPO- RAZ sync (74HC162)");
        AvailableComponents.addElement("Ctr BIN 4bits prog -CTR-PIPO- RAZ sync (74HC163)");
        AvailableComponents.addElement("PROM 32*8 (sorties 3S)");
        AvailableComponents.addElement("RAM1 32*8 (sorties 3S & E/S separees)");
        AvailableComponents.addElement("RAM2 32*8 (sorties 3S & E/S communes)");
        AvailableComponents.addElement("Registre 8bits -SIPO-SISO-PISO-PIPO- 3S");
        AvailableComponents.addElement("Monostable temporise");
        AvailableComponents.addElement("PLA 5x4");
        AvailableComponents.addElement("Traits et Cie");
        AvailableComponents.addElement("Cellule pour sequenceur cable");
        AvailableComponents.addElement("DEL jaune-verte-rouge");
        AvailableComponents.addElement("CtrDctr BIN-DCB 4bits prog -CTR-PIPO- RAZ sync");
        AvailableComponents.addElement("CtrDctr BIN-DCB 4bits prog -CTR-PIPO- RAZ async");
        AvailableComponents.addElement("CtrDctr BIN 5bits prog -CTR-PIPO- RAZ async");
        AvailableComponents.addElement("Feux de carrefour");
        AvailableComponents.addElement("Perceuse");
        AvailableComponents.addElement("Chariot XY a capteurs h b g d");
        AvailableComponents.addElement("Chariot XY a sorties numeriques xy");
        AvailableComponents.addElement("Chariot XY a capteurs et sorties numeriques");
        AvailableComponents.addElement("Chariot XY a moteurs pas a pas unipolaires");
        AvailableComponents.addElement("Wagonnets simple");
        AvailableComponents.addElement("Wagonnets et aiguillages");
        AvailableComponents.addElement("Jeton");
        AvailableComponents.addElement("--o = complementation (inversion) en entree de composant");
        AvailableComponents.addElement("o-- = complementation (inversion) en sortie de composant");
        AvailableComponents.addElement("Clef");
        AvailableComponents.addElement("Cellule RC d'initialisation avec bouton Reset");
        AvailableComponents.addElement("Comparateur 8 bits (74HC688)");
        AvailableComponents.addElement("Comparateur 4 bits (74HC85)");
        AvailableComponents.addElement("Source sonore");
        AvailableComponents.addElement("Alarme");
        AvailableComponents.addElement("Contact 2RT");
        AvailableComponents.addElement("Relais 2RT");
        AvailableComponents.addElement("Multiplieur 8 bits x 8 bits");
        AvailableComponents.addElement("Additionneur 8 bits");
        AvailableComponents.addElement("Multiplieur-ACcumulateur 8 bits x 8 bits (sorties 3S)");
        AvailableComponents.addElement("Horloge 2 (periode 2T & sorties decalees)");
        AvailableComponents.addElement("4 multiplexeurs 2 vers 1 (74HC157)");
        AvailableComponents.addElement("Buffer 8 bits a sorties 3S (74HC541)");
        AvailableComponents.addElement("Transceiver 8 bits a sorties 3S (74HC245)");
        AvailableComponents.addElement("Buffer 2 x 4 bits a sorties 3S (74HC244)");
        AvailableComponents.addElement("Chariot libre");
        AvailableComponents.addElement("Afficheur complement a 2");
        AvailableComponents.addElement("Buffer 1 bit a sortie 3S");
        AvailableComponents.addElement("Emetteur serie 8bits");
        AvailableComponents.addElement("Retard fixe (usage restreint)");
        AvailableComponents.addElement("Filtre RC (usage restreint)");
        AvailableComponents.addElement("Retard pur (usage restreint)");
        AvailableComponents.addElement("Symbole RdP-Transition");
        AvailableComponents.addElement("Symbole RdP-Fleche");
        AvailableComponents.addElement("Symbole RdP-Place");
        AvailableComponents.addElement("Symbole RdP-Jeton");
        AvailableComponents.addElement("Symbole Trigger");
        AvailableComponents.addElement("Symbole Condensateur");
        AvailableComponents.addElement("Symbole Resistance");
        AvailableComponents.addElement("Cadre 1024x768");
        AvailableComponents.addElement("Cadre 1280x1024");
        AvailableComponents.addElement("Cadre double 97x69");
        AvailableComponents.addElement("Cadre double 80x70");
        AvailableComponents.addElement("Decaleur statique 19 vers 8 bits (sorties 3S)");
        AvailableComponents.addElement("SBPA");
        AvailableComponents.addElement("SBA");
        AvailableComponents.addElement("74HC00");
        AvailableComponents.addElement("74HC02");
        AvailableComponents.addElement("74HC04");
        AvailableComponents.addElement("74HC08");
        AvailableComponents.addElement("74HC10");
        AvailableComponents.addElement("74HC11");
        AvailableComponents.addElement("74HC20");
        AvailableComponents.addElement("74HC21");
        AvailableComponents.addElement("74HC27");
        AvailableComponents.addElement("74HC32");
        AvailableComponents.addElement("74HC4002");
        AvailableComponents.addElement("74HC4072");
        AvailableComponents.addElement("74HC4075");
        AvailableComponents.addElement("74HC86");
        AvailableComponents.addElement("74HC7266");
        AvailableComponents.addElement("74HC109");
        AvailableComponents.addElement("Decaleur statique 4 bits a sorties 3S (74HC350)");
        AvailableComponents.addElement("Pendule a sortie serie");
        AvailableComponents.addElement("Horloge 1s");
        AvailableComponents.addElement("Horloge 500ms");
        AvailableComponents.addElement("Horloge 100ms");
        AvailableComponents.addElement("Mini SR");
        AvailableComponents.addElement("Mini SR double");
        AvailableComponents.addElement("Periodemetre");
        AvailableComponents.addElement("Mini analyseur logique");
        AvailableComponents.addElement("Analyseur logique avec horloge externe");
        AvailableComponents.addElement("Compteur de fronts");
        AvailableComponents.addElement("Monostable 500ms non reenclenchable");
        AvailableComponents.addElement("Monostable 500ms reenclenchable");
        AvailableComponents.addElement("Monostable 1 s reenclenchable");
        AvailableComponents.addElement("Rail droit ''10'' avec ''loco''");
        AvailableComponents.addElement("Rail droit ''10''");
        AvailableComponents.addElement("Rail droit ''20''");
        AvailableComponents.addElement("Rail courbe 90\260");
        AvailableComponents.addElement("Aiguillage ''10'' D");
        AvailableComponents.addElement("Aiguillage ''10'' G");
        AvailableComponents.addElement("Aiguillage ''10'' auto D");
        AvailableComponents.addElement("Aiguillage ''10'' auto G");
        AvailableComponents.addElement("Croisement ''5x5''");
        AvailableComponents.addElement("Masque gris");
        AvailableComponents.addElement("Masque gris clair");
        AvailableComponents.addElement("Rail butoir ''10'' avec ''loco''");
        AvailableComponents.addElement("Rail butoir ''10''");
        AvailableComponents.addElement("Rail droit ''5'' auto");
        AvailableComponents.addElement("Rail droit ''10'' auto rapide");
        AvailableComponents.addElement("Rail droit ''20'' auto rapide");
        AvailableComponents.addElement("Rail droit ''10'' auto lent");
        AvailableComponents.addElement("Rail oblique ''10'' auto D");
        AvailableComponents.addElement("Rail oblique ''10'' auto G");
        AvailableComponents.addElement("Rail courbe 90\260 auto");
        AvailableComponents.addElement("Segment de rail H");
        AvailableComponents.addElement("Segment de rail V");
        AvailableComponents.addElement("Boitier de commande des rails");
        AvailableComponents.addElement("Rail courbe 45\260 auto D");
        AvailableComponents.addElement("Rail courbe 45\260 auto G");
        AvailableComponents.addElement("Commande manuelle fugitive ''compacte''");
        AvailableComponents.addElement("Commande manuelle permanente ''compacte''");
        AvailableComponents.addElement("Klaxon");
        AvailableComponents.addElement("Sifflet");
        AvailableComponents.addElement("BP NO (T) avec resistance de pull-up");
        AvailableComponents.addElement("BP NO (T) avec resistance de pull-down");
        AvailableComponents.addElement("BP NF (R) avec resistance de pull-up");
        AvailableComponents.addElement("BP NF (R) avec resistance de pull-down");
        AvailableComponents.addElement("Contact NO (T) avec resistance de pull-up");
        AvailableComponents.addElement("Contact NO (T) avec resistance de pull-down");
        AvailableComponents.addElement("Contact NF (R) avec resistance de pull-up");
        AvailableComponents.addElement("Contact NF (R) avec resistance de pull-down");
        AvailableComponents.addElement("Generateur de codes");
        AvailableComponents.addElement("Testeur de code I-O");
        AvailableComponents.addElement("Pendule");
        AvailableComponents.addElement("Compacteur de bus unidirectionnel 8 bits");
        AvailableComponents.addElement("Decompacteur de bus unidirectionnel 8 bits");
        AvailableComponents.addElement("Poussoir double generateur d'impulsions decalees");
        AvailableComponents.addElement("Poussoir simple generateur d'impulsion decalee");
        AvailableComponents.addElement("Poussoir");
        AvailableComponents.addElement("Switch");
        AvailableComponents.addElement("NON");
        AvailableComponents.addElement("ET3");
        AvailableComponents.addElement("OU3");
        AvailableComponents.addElement("ET3-NON");
        AvailableComponents.addElement("OU3-NON");
        AvailableComponents.addElement("OUX3-NON");
        AvailableComponents.addElement("OUX3");
        AvailableComponents.addElement("Module de gestion de direction");
        AvailableComponents.addElement("Module de gestion de canton");
        AvailableComponents.addElement("BP NO (normalement ouvert - contact T)");
        AvailableComponents.addElement("BP NF (normalement ferme - contact R)");
        AvailableComponents.addElement("BP NO (contact T) avec resistance de pullup");
        AvailableComponents.addElement("Inter NO (normalement ouvert - contact T)");
        AvailableComponents.addElement("Inter NF (normalement ferme - contact R)");
        AvailableComponents.addElement("Essai Composant 0");
        AvailableComponents.addElement("Essai Composant 1");
        AvailableComponents.addElement("Essai Composant 2");
        AvailableComponents.addElement("Essai Composant 3");
        AvailableComponents.addElement("Essai Composant 4");
        AvailableComponents.addElement("Essai Composant 5");
        AvailableComponents.addElement("Essai Composant 6");
        AvailableComponents.addElement("Essai Composant 7");
        AvailableComponents.addElement("Essai Composant 8");
        AvailableComponents.addElement("Essai Composant 9");
    }

    public MenuBar DigSimMenuBar()
    {
        MyMenuBar = new MenuBar();
        FileMenu = new Menu("Fichier");

        /*
         *  MenuItem add = getMenuItem("Shuffle",
            new ImageView(new Image("menusample/new.png")));
        add.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                shuffle();
                vbox.setVisible(true);
            }
        });
         */
        FileMenu.getItems().add(getMenuItem("Au sujet du menu ''Fichier''"));
        FileMenu.getItems().add (new SeparatorMenuItem());
        FileMenu.getItems().add (new SeparatorMenuItem());
        MenuItem menuitem;
        MenuItemsToDisable.addElement(menuitem = getMenuItem(new String("Nouveau                                          Ctrl+N")));
        FileMenu.getItems().add(menuitem);
        FileMenu.getItems().add (new SeparatorMenuItem());
        if(applet.EnableFileOperations)
        {
            MenuItemsToDisable.addElement(menuitem = getMenuItem("Ouvrir une simulation (.txt)              Ctrl+O"));
            FileMenu.getItems().add(menuitem);
            MenuItemsToDisable.addElement(menuitem = getMenuItem("Enregistrer sous... (.txt)                  Ctrl+Shift+S"));
            FileMenu.getItems().add(menuitem);
            MenuItemsToDisable.addElement(menuitem = getMenuItem("Enregistrer               (.txt)                 Ctrl+S"));
            FileMenu.getItems().add(menuitem);
        } else
        {
            FileMenu.getItems().add(menuitem = getMenuItem("Ouvrir une simulation (.txt)              Ctrl+O"));
            menuitem.setDisable(true);
            FileMenu.getItems().add(menuitem = getMenuItem("Enregistrer sous... (.txt)                  Ctrl+Shift+S"));
            menuitem.setDisable(true);
            FileMenu.getItems().add(menuitem = getMenuItem("Enregistrer               (.txt)                 Ctrl+S"));
            menuitem.setDisable(true);
        }
        FileMenu.getItems().add (new SeparatorMenuItem());
        if(applet.EnableFileOperations)
        {
            MenuItemsToDisable.addElement(menuitem = getMenuItem("Importer (obsolète)                          Ctrl+I, i"));
            FileMenu.getItems().add(menuitem);
            MenuItemsToDisable.addElement(menuitem = getMenuItem("Exporter (obsolète)                          Ctrl+E, e"));
            FileMenu.getItems().add(menuitem);
        } else
        {
            FileMenu.getItems().add(menuitem = getMenuItem("Importer (obsolète)                          Ctrl+I, i"));
            menuitem.setDisable(true);
            FileMenu.getItems().add(menuitem = getMenuItem("Exporter (obsolète)                          Ctrl+E, e"));
            menuitem.setDisable(true);
        }
        FileMenu.getItems().add (new SeparatorMenuItem());

        MenuItemsToDisable.addElement(menuitem = getMenuItem("Aide spécifique (tests et simulations)"));
        FileMenu.getItems().add(menuitem);
        FileMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem(new String("Réinitialiser la simulation                   *, Tab")));
        FileMenu.getItems().add(menuitem);
        FileMenu.getItems().add (new SeparatorMenuItem());
        FileMenu.getItems().add(getMenuItem("Fermer la fenêtre de simulation"));
        FileMenu.getItems().add(getMenuItem("Quitter"));
        MyMenuBar.getMenus().add(FileMenu);
        EditMenu = new Menu("Edit");
        EditMenu.getItems().add(getMenuItem("Au sujet du menu ''Edit''"));
        EditMenu.getItems().add (new SeparatorMenuItem());
        EditMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(CutMenuItem = getMenuItem("Supprimer                Ctrl+X, Suppr"));
        EditMenu.getItems().add(CutMenuItem);
        MenuItemsToDisable.addElement(CopyMenuItem = getMenuItem("Copier                      Ctrl+C"));
        EditMenu.getItems().add(CopyMenuItem);
        MenuItemsToDisable.addElement(PasteMenuItem = getMenuItem("Coller                       Ctrl+V"));
        EditMenu.getItems().add(PasteMenuItem);
        MenuItemsToDisable.addElement(RotateMenuItem = getMenuItem("Rotation                   Ctrl+R, t"));
        EditMenu.getItems().add(RotateMenuItem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Tout sélectionner     Ctrl+A"));
        EditMenu.getItems().add(menuitem);
        CutMenuItem.setDisable(true);
        CopyMenuItem.setDisable(true);
        PasteMenuItem.setDisable(true);
        EditMenu.getItems().add (new SeparatorMenuItem());
        if(applet.EnableFileOperations)
        {
            MenuItemsToDisable.addElement(CopyDiskMenuItem = getMenuItem("Enregistrer la sélection vers..."));
            EditMenu.getItems().add(CopyDiskMenuItem);
            MenuItemsToDisable.addElement(PasteDiskMenuItem = getMenuItem("Coller à partir de... "));
            EditMenu.getItems().add(PasteDiskMenuItem);
        } else
        {
            EditMenu.getItems().add(CopyDiskMenuItem = getMenuItem("Enregistrer la sélection vers..."));
            CopyDiskMenuItem.setDisable(true);
            EditMenu.getItems().add(PasteDiskMenuItem = getMenuItem("Coller à partir de... "));
            PasteDiskMenuItem.setDisable(true);
        }
        MyMenuBar.getMenus().add(EditMenu);
        WiringMenu = new Menu("Câblage");
        WiringMenu.getItems().add(getMenuItem("Au sujet du menu ''Câblage''"));
        WiringMenu.getItems().add (new SeparatorMenuItem());
        WiringMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Fil de liaison         f ou w"));
        WiringMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Jonction               j"));
        WiringMenu.getItems().add(menuitem);
        WiringMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Vcc (niveau logique 1)"));
        WiringMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Masse (niveau logique 0)"));
        WiringMenu.getItems().add(menuitem);
        WiringMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Compacteur de bus unidirectionnel 8 bits"));
        WiringMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Decompacteur de bus unidirectionnel 8 bits"));
        WiringMenu.getItems().add(menuitem);
        WiringMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Contact R (type repos)"));
        WiringMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Contact T (type travail)"));
        WiringMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Bouton poussoir"));
        WiringMenu.getItems().add(menuitem);
        WiringMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Switch"));
        WiringMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Poussoir"));
        WiringMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Poussoir simple generateur d'impulsion decalee"));
        WiringMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Poussoir double generateur d'impulsions decalees"));
        WiringMenu.getItems().add(menuitem);
        WiringMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Contact NO (T) avec resistance de pull-up"));
        WiringMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Contact NF (R) avec resistance de pull-up"));
        WiringMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Contact NO (T) avec resistance de pull-down"));
        WiringMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Contact NF (R) avec resistance de pull-down"));
        WiringMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("BP NO (T) avec resistance de pull-up"));
        WiringMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("BP NF (R) avec resistance de pull-up"));
        WiringMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("BP NO (T) avec resistance de pull-down"));
        WiringMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("BP NF (R) avec resistance de pull-down"));
        WiringMenu.getItems().add(menuitem);
        WiringMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Contact 2RT"));
        WiringMenu.getItems().add(menuitem);
        WiringMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Relais 2RT"));
        WiringMenu.getItems().add(menuitem);
        MyMenuBar.getMenus().add(WiringMenu);
        GatesMenu = new Menu("Portes");
        GatesMenu.getItems().add(getMenuItem("Au sujet du menu ''Portes''"));
        GatesMenu.getItems().add (new SeparatorMenuItem());
        GatesMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Etage tampon (buffer)"));
        GatesMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Buffer 1 bit a sortie 3S"));
        GatesMenu.getItems().add(menuitem);
        GatesMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Porte_''Non''_(inverseur_complementation)"));
        GatesMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("--o = complementation (inversion) en entree de composant"));
        GatesMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("o-- = complementation (inversion) en sortie de composant"));
        GatesMenu.getItems().add(menuitem);
        GatesMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("AND2 = ''Et'' -2 entrees-"));
        GatesMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("AND3 = ''Et'' -3 entrees-"));
        GatesMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("AND4 = ''Et'' -4 entrees-"));
        GatesMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("NAND2 = ''Et-Non'' -2 entrees-"));
        GatesMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("NAND3 = ''Et-Non'' -3 entrees-"));
        GatesMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("NAND4 = ''Et-Non'' -4 entrees-"));
        GatesMenu.getItems().add(menuitem);
        GatesMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("OR2 = ''Ou'' -2 entrees-"));
        GatesMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("OR3 = ''Ou'' -3 entrees-"));
        GatesMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("OR4 = ''Ou'' -4 entrees-"));
        GatesMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("NOR2 = ''Ou-Non'' -2 entrees-"));
        GatesMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("NOR3 = ''Ou-Non'' -3 entrees-"));
        GatesMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("NOR4 = ''Ou-Non'' -4 entrees-"));
        GatesMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("NOR5 = ''Ou-Non'' -5 entrees-"));
        GatesMenu.getItems().add(menuitem);
        GatesMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("XOR2 = ''OuExclusif'' -2 entrees-"));
        GatesMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("XNOR2 = ''OuExclusif-Non'' -2 entrees-"));
        GatesMenu.getItems().add(menuitem);
        MyMenuBar.getMenus().add(GatesMenu);
        ISOMenu = new Menu("ISO");
        ISOMenu.getItems().add(getMenuItem("Au sujet du menu ''ISO''"));
        ISOMenu.getItems().add (new SeparatorMenuItem());
        ISOMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("NON"));
        ISOMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("ET3"));
        ISOMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("OU3"));
        ISOMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("ET3-NON"));
        ISOMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("OU3-NON"));
        ISOMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("OUX3"));
        ISOMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("OUX3-NON"));
        ISOMenu.getItems().add(menuitem);
        MyMenuBar.getMenus().add(ISOMenu);
        DILMenu = new Menu("DIL");
        DILMenu.getItems().add(getMenuItem("Au sujet du menu ''DIL''"));
        DILMenu.getItems().add (new SeparatorMenuItem());
        DILMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("74HC00"));
        DILMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("74HC02"));
        DILMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("74HC04"));
        DILMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("74HC08"));
        DILMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("74HC10"));
        DILMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("74HC11"));
        DILMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("74HC20"));
        DILMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("74HC21"));
        DILMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("74HC27"));
        DILMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("74HC32"));
        DILMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("74HC86"));
        DILMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("74HC4002"));
        DILMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("74HC4072"));
        DILMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("74HC4075"));
        DILMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("74HC7266"));
        DILMenu.getItems().add(menuitem);
        DILMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("74HC109"));
        DILMenu.getItems().add(menuitem);
        MyMenuBar.getMenus().add(DILMenu);
        CombinationalMenu = new Menu("Combinatoire");
        CombinationalMenu.getItems().add(getMenuItem("Au sujet du menu ''Combinatoire''"));
        CombinationalMenu.getItems().add (new SeparatorMenuItem());
        CombinationalMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Multiplexeur 2 vers 1"));
        CombinationalMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("4 multiplexeurs 2 vers 1 (74HC157)"));
        CombinationalMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Multiplexeur 4 vers 1 (74HC153)"));
        CombinationalMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Multiplexeur 8 vers 1 (74HC151)"));
        CombinationalMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Decaleur statique 4 bits a sorties 3S (74HC350)"));
        CombinationalMenu.getItems().add(menuitem);
        CombinationalMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Decodeur-demultiplexeur 3 vers 8 (74HC138)"));
        CombinationalMenu.getItems().add(menuitem);
        CombinationalMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Encodeur prioritaire 8 vers 3 (74HC148)"));
        CombinationalMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Encodeur prioritaire 10 vers 4 (74HC147)"));
        CombinationalMenu.getItems().add(menuitem);
        CombinationalMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Comparateur 4 bits (74HC85)"));
        CombinationalMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Comparateur 8 bits (74HC688)"));
        CombinationalMenu.getItems().add(menuitem);
        CombinationalMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Decodeur-latch 7 segments"));
        CombinationalMenu.getItems().add(menuitem);
        CombinationalMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("PROM 32*8 (sorties 3S)"));
        CombinationalMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("RAM1 32*8 (sorties 3S & E/S separees)"));
        CombinationalMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("RAM2 32*8 (sorties 3S & E/S communes)"));
        CombinationalMenu.getItems().add(menuitem);
        CombinationalMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Buffer 2 x 4 bits a sorties 3S (74HC244)"));
        CombinationalMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Transceiver 8 bits a sorties 3S (74HC245)"));
        CombinationalMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Buffer 8 bits a sorties 3S (74HC541)"));
        CombinationalMenu.getItems().add(menuitem);
        CombinationalMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Additionneur complet 1 bit"));
        CombinationalMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Additionneur 8 bits"));
        CombinationalMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Multiplieur 8 bits x 8 bits"));
        CombinationalMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Multiplieur-ACcumulateur 8 bits x 8 bits (sorties 3S)"));
        CombinationalMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Decaleur statique 19 vers 8 bits (sorties 3S)"));
        CombinationalMenu.getItems().add(menuitem);
        MyMenuBar.getMenus().add(CombinationalMenu);
        BiStableMenu = new Menu("Bascules");
        BiStableMenu.getItems().add(getMenuItem("Au sujet du menu ''Bascules''"));
        BiStableMenu.getItems().add (new SeparatorMenuItem());
        BiStableMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Latch SR"));
        BiStableMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Latch SRH (a verrouillage)"));
        BiStableMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Latch D (transparent)"));
        BiStableMenu.getItems().add(menuitem);
        BiStableMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("FlipFlop D ''edge-triggered''"));
        BiStableMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("FlipFlop T ''edge-triggered''"));
        BiStableMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("FlipFlop JK ''edge-triggered''"));
        BiStableMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("FlipFlop JK ''edge-triggered'' avec entrees asynchrones"));
        BiStableMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("FlipFlop JK! ''edge-triggered'' avec entrees asynchrones"));
        BiStableMenu.getItems().add(menuitem);
        BiStableMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Bistable T"));
        BiStableMenu.getItems().add(menuitem);
        BiStableMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Cellule pour sequenceur cable"));
        BiStableMenu.getItems().add(menuitem);
        MyMenuBar.getMenus().add(BiStableMenu);
        CountersRegistersMenu = new Menu("Compteurs, registres");
        CountersRegistersMenu.getItems().add(getMenuItem("Au sujet du menu ''Compteurs & registres''"));
        CountersRegistersMenu.getItems().add (new SeparatorMenuItem());
        CountersRegistersMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Compteur binaire 4 bits"));
        CountersRegistersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("CtrDCtr BIN 4bits chargement async a 0 ou 15"));
        CountersRegistersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("CtrDctr DCB 4bits chargement async a 0 ou 9"));
        CountersRegistersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("CtrDCtr BIN 8bits chargement async a 0 ou 255"));
        CountersRegistersMenu.getItems().add(menuitem);
        CountersRegistersMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Ctr DCB 4bits prog -CTR-PIPO- RAZ async (74HC160)"));
        CountersRegistersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Ctr BIN 4bits prog -CTR-PIPO- RAZ async (74HC161)"));
        CountersRegistersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Ctr DCB 4bits prog -CTR-PIPO- RAZ sync (74HC162)"));
        CountersRegistersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Ctr BIN 4bits prog -CTR-PIPO- RAZ sync (74HC163)"));
        CountersRegistersMenu.getItems().add(menuitem);
        CountersRegistersMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("CtrDctr BIN-DCB 4bits prog -CTR-PIPO- RAZ async"));
        CountersRegistersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("CtrDctr BIN-DCB 4bits prog -CTR-PIPO- RAZ sync"));
        CountersRegistersMenu.getItems().add(menuitem);
        CountersRegistersMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("CtrDctr BIN 5bits prog -CTR-PIPO- RAZ async"));
        CountersRegistersMenu.getItems().add(menuitem);
        CountersRegistersMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Registre de 8 latches D transparents 3S (74HC373)"));
        CountersRegistersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Registre de 8 FlipFlop D -PIPO- 3S (74HC273 74HC374)"));
        CountersRegistersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Registre de 8 FlipFlop D -PIPOinv- 3S (74HC534 564)"));
        CountersRegistersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Registre 8bits -PISO- (74HC165)"));
        CountersRegistersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Registre 8bits -SIPO-SISO- 3S (~74HC164)"));
        CountersRegistersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Registre 8bits -SIPO-SISO-PISO-PIPO- 3S"));
        CountersRegistersMenu.getItems().add(menuitem);
        CountersRegistersMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("PROM 32*8 (sorties 3S)"));
        CountersRegistersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("RAM1 32*8 (sorties 3S & E/S separees)"));
        CountersRegistersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("RAM2 32*8 (sorties 3S & E/S communes)"));
        CountersRegistersMenu.getItems().add(menuitem);
        MyMenuBar.getMenus().add(CountersRegistersMenu);
        DisplayMenu = new Menu("Affichage");
        DisplayMenu.getItems().add(getMenuItem("Au sujet du menu ''Affichage''"));
        DisplayMenu.getItems().add (new SeparatorMenuItem());
        DisplayMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("DEL cyan"));
        DisplayMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("DEL magenta"));
        DisplayMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("DEL bleue"));
        DisplayMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("DEL verte"));
        DisplayMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("DEL jaune"));
        DisplayMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("DEL orange"));
        DisplayMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("DEL rouge"));
        DisplayMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("DEL rose"));
        DisplayMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("DEL bleue-verte-rouge"));
        DisplayMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("DEL jaune-verte-rouge"));
        DisplayMenu.getItems().add(menuitem);
        DisplayMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Jeton"));
        DisplayMenu.getItems().add(menuitem);
        DisplayMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Afficheur 7 segments"));
        DisplayMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Decodeur-latch 7 segments"));
        DisplayMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Afficheur complement a 2"));
        DisplayMenu.getItems().add(menuitem);
        MyMenuBar.getMenus().add(DisplayMenu);
        OthersMenu = new Menu("Divers");
        OthersMenu.getItems().add(getMenuItem("Au sujet du menu ''Divers''"));
        OthersMenu.getItems().add (new SeparatorMenuItem());
        OthersMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Horloge 1 (periode T)"));
        OthersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Horloge 2 (periode 2T & sorties decalees)"));
        OthersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Horloge de periode ajustable"));
        OthersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Cellule RC d'initialisation avec bouton Reset"));
        OthersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Monostable temporise"));
        OthersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("SBPA"));
        OthersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("SBA"));
        OthersMenu.getItems().add(menuitem);
        OthersMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Horloge 100ms"));
        OthersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Horloge 500ms"));
        OthersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Horloge 1s"));
        OthersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Monostable 500ms non reenclenchable"));
        OthersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Monostable 500ms reenclenchable"));
        OthersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Monostable 1 s reenclenchable"));
        OthersMenu.getItems().add(menuitem);
        OthersMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Retard fixe (usage restreint)"));
        OthersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Retard pur (usage restreint)"));
        OthersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Filtre RC (usage restreint)"));
        OthersMenu.getItems().add(menuitem);
        OthersMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Source sonore"));
        OthersMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Alarme"));
        OthersMenu.getItems().add(menuitem);
        OthersMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Pendule"));
        OthersMenu.getItems().add(menuitem);
        MyMenuBar.getMenus().add(OthersMenu);
        TexteMenu = new Menu("Texte");
        TexteMenu.getItems().add(getMenuItem("Au sujet du menu ''Texte''"));
        TexteMenu.getItems().add (new SeparatorMenuItem());
        TexteMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Texte monospaced   (m)"));
        TexteMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Texte noir  (n)"));
        TexteMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Texte rouge  (r)"));
        TexteMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Texte bleu    (b)"));
        TexteMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Texte vert    (v)"));
        TexteMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Texte gris (RdP)  (g)"));
        TexteMenu.getItems().add(menuitem);
        TexteMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Symbole RdP-Place"));
        TexteMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Symbole RdP-Jeton"));
        TexteMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Symbole RdP-Transition"));
        TexteMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Symbole RdP-Fleche"));
        TexteMenu.getItems().add(menuitem);
        TexteMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Symbole Trigger"));
        TexteMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Symbole Condensateur"));
        TexteMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Symbole Resistance"));
        TexteMenu.getItems().add(menuitem);
        TexteMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Cadre 1024x768"));
        TexteMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Cadre 1280x1024"));
        TexteMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Cadre double 97x69"));
        TexteMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Cadre double 80x70"));
        TexteMenu.getItems().add(menuitem);
        TexteMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Traits et Cie"));
        TexteMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Colorieur"));
        TexteMenu.getItems().add(menuitem);
        MyMenuBar.getMenus().add(TexteMenu);
        OutilsMenu = new Menu("Outils");
        OutilsMenu.getItems().add(getMenuItem("Au sujet du menu ''Outils''"));
        OutilsMenu.getItems().add (new SeparatorMenuItem());
        OutilsMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Sonde pour analyseur logique"));
        OutilsMenu.getItems().add(menuitem);
        OutilsMenu.getItems().add(getMenuItem("Analyseur logique                               a"));
        OutilsMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Analyseur logique avec horloge externe"));
        OutilsMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Mini analyseur logique"));
        OutilsMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Compteur de fronts"));
        OutilsMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Periodemetre"));
        OutilsMenu.getItems().add(menuitem);
        OutilsMenu.getItems().add (new SeparatorMenuItem());
        OutilsMenu.getItems().add(getMenuItem("Vitesse de simulation et options          o"));
        MyMenuBar.getMenus().add(OutilsMenu);
        RailsTrainMenu = new Menu("Rails & train");
        RailsTrainMenu.getItems().add(getMenuItem("Au sujet du menu ''Rails & train''"));
        RailsTrainMenu.getItems().add (new SeparatorMenuItem());
        RailsTrainMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Rail droit ''10'' avec ''loco''"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Rail droit ''10''"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Rail droit ''20''"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Rail butoir ''10'' avec ''loco''"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Rail butoir ''10''"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Aiguillage ''10'' G"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Aiguillage ''10'' D"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Rail courbe 90\260"));
        RailsTrainMenu.getItems().add(menuitem);
        RailsTrainMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Rail droit ''10'' auto rapide"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Rail droit ''20'' auto rapide"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Rail droit ''10'' auto lent"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Rail droit ''5'' auto"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Rail oblique ''10'' auto D"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Rail oblique ''10'' auto G"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Segment de rail H"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Segment de rail V"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Aiguillage ''10'' auto G"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Aiguillage ''10'' auto D"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Rail courbe 90\260 auto"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Rail courbe 45\260 auto G"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Rail courbe 45\260 auto D"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Croisement ''5x5''"));
        RailsTrainMenu.getItems().add(menuitem);
        RailsTrainMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Commande manuelle fugitive ''compacte''"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Commande manuelle permanente ''compacte''"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Klaxon"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Sifflet"));
        RailsTrainMenu.getItems().add(menuitem);
        RailsTrainMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Mini SR"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Mini SR double"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Boitier de commande des rails"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Module de gestion de canton"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Module de gestion de direction"));
        RailsTrainMenu.getItems().add(menuitem);
        RailsTrainMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Masque gris"));
        RailsTrainMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Masque gris clair"));
        RailsTrainMenu.getItems().add(menuitem);
        MyMenuBar.getMenus().add(RailsTrainMenu);
        ApplicationsMenu = new Menu("Appl.");
        ApplicationsMenu.getItems().add(getMenuItem("Au sujet du menu ''Applications''"));
        ApplicationsMenu.getItems().add (new SeparatorMenuItem());
        ApplicationsMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Emetteur serie 8bits"));
        ApplicationsMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Pendule a sortie serie"));
        ApplicationsMenu.getItems().add(menuitem);
        ApplicationsMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Feux de carrefour"));
        ApplicationsMenu.getItems().add(menuitem);
        ApplicationsMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Perceuse"));
        ApplicationsMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Chariot libre"));
        ApplicationsMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Chariot XY a capteurs h b g d"));
        ApplicationsMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Chariot XY a sorties numeriques xy"));
        ApplicationsMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Chariot XY a capteurs et sorties numeriques"));
        ApplicationsMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Chariot XY a moteurs pas a pas unipolaires"));
        ApplicationsMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Wagonnets simple"));
        ApplicationsMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Wagonnets et aiguillages"));
        ApplicationsMenu.getItems().add(menuitem);
        ApplicationsMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Clef"));
        ApplicationsMenu.getItems().add(menuitem);
        MyMenuBar.getMenus().add(ApplicationsMenu);
        SimulateMenu = new Menu("Opt.");
        SimulateMenu.getItems().add(getMenuItem("Au sujet du menu ''Options''"));
        SimulateMenu.getItems().add (new SeparatorMenuItem());
        SimulateMenu.getItems().add (new SeparatorMenuItem());
        StartMenuItem = getMenuItem("Lancer la simulation                           Entrée");
        StartMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                action(null, "Lancer la simulation                           Entrée");
            }
        });
        SimulateMenu.getItems().add(StartMenuItem);
        StopMenuItem = getMenuItem("Arrêter la simulation                            Entrée");
        StopMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                action(null, "Arrêter la simulation                            Entrée");
            }
        });
        SimulateMenu.getItems().add(StopMenuItem);
        StopMenuItem.setDisable(true);
        SimulateMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem(new String("Réinitialiser la simulation                     *, Tab")));
        SimulateMenu.getItems().add(menuitem);
        SimulateMenu.getItems().add (new SeparatorMenuItem());
        SimulateMenu.getItems().add(getMenuItem("Analyseur logique                               a"));
        SimulateMenu.getItems().add(getMenuItem("Vitesse de simulation et options          o"));
        MyMenuBar.getMenus().add(SimulateMenu);
        HelpMenu = new Menu("?");
        HelpMenu.getItems().add(getMenuItem("Au sujet du menu ''Aide''"));
        HelpMenu.getItems().add (new SeparatorMenuItem());
        HelpMenu.getItems().add (new SeparatorMenuItem());
        HelpMenu.getItems().add(getMenuItem("Aide                                      h, ?"));
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Aide spécifique (tests et simulations)"));
        HelpMenu.getItems().add(menuitem);
        HelpMenu.getItems().add (new SeparatorMenuItem());
        HelpMenu.getItems().add(getMenuItem("Au sujet de DigSim"));
        HelpMenu.getItems().add(getMenuItem("Classpath (obsolète)"));
        HelpMenu.getItems().add(getMenuItem("Foire aux questions (FAQ)"));
        HelpMenu.getItems().add(getMenuItem("Raccourcis clavier"));
        MyMenuBar.getMenus().add(HelpMenu);
        HelpMenu.getItems().add (new SeparatorMenuItem());
        HelpMenu.getItems().add (new SeparatorMenuItem());
        if(applet.EnableFileOperations)
        {
            MenuItemsToDisable.addElement(menuitem = getMenuItem("Importer (obsolète)                          Ctrl+I, i"));
            HelpMenu.getItems().add(menuitem);
            MenuItemsToDisable.addElement(menuitem = getMenuItem("Exporter (obsolète)                          Ctrl+E, e"));
            HelpMenu.getItems().add(menuitem);
        } else
        {
            HelpMenu.getItems().add(menuitem = getMenuItem("Importer (obsolète)                          Ctrl+I, i"));
            menuitem.setDisable(true);
            HelpMenu.getItems().add(menuitem = getMenuItem("Exporter (obsolète)                          Ctrl+E, e"));
            menuitem.setDisable(true);
        }
//        MyMenuBar.setHelpMenu(HelpMenu);
        ConstructionMenu = new Menu(" ");
        ConstructionMenu.getItems().add(getMenuItem("Au sujet du menu ''En construction''"));
        ConstructionMenu.getItems().add (new SeparatorMenuItem());
        ConstructionMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Essai Composant 0"));
        ConstructionMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Essai Composant 1"));
        ConstructionMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Essai Composant 2"));
        ConstructionMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Essai Composant 3"));
        ConstructionMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Essai Composant 4"));
        ConstructionMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Essai Composant 5"));
        ConstructionMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Essai Composant 6"));
        ConstructionMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Essai Composant 7"));
        ConstructionMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Essai Composant 8"));
        ConstructionMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Essai Composant 9"));
        ConstructionMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("PLA 5x4"));
        ConstructionMenu.getItems().add(menuitem);
        ConstructionMenu.getItems().add (new SeparatorMenuItem());
        ConstructionMenu.getItems().add (new SeparatorMenuItem());
        MyMenuBar.getMenus().add(ConstructionMenu);
        PersoMenu = new Menu(" ");
        PersoMenu.getItems().add(getMenuItem("Au sujet du menu ''Perso & tests''"));
        PersoMenu.getItems().add (new SeparatorMenuItem());
        PersoMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("BP NO (normalement ouvert - contact T)"));
        PersoMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("BP NF (normalement ferme - contact R)"));
        PersoMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Inter NO (normalement ouvert - contact T)"));
        PersoMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Inter NF (normalement ferme - contact R)"));
        PersoMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("BP NO (contact T) avec resistance de pullup"));
        PersoMenu.getItems().add(menuitem);
        PersoMenu.getItems().add (new SeparatorMenuItem());
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Generateur de codes"));
        PersoMenu.getItems().add(menuitem);
        MenuItemsToDisable.addElement(menuitem = getMenuItem("Testeur de code I-O"));
        PersoMenu.getItems().add(menuitem);
        MyMenuBar.getMenus().add(PersoMenu);
        return MyMenuBar;
    }

	private MenuItem getMenuItem(String menuItemText) {
		MenuItem menuitem = new MenuItem(menuItemText);
        menuitem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                action(null, menuItemText);
            }
        });
		return menuitem;
	}

    public boolean IsComponentName(String s)
    {
        for(int i = 0; i < AvailableComponents.size(); i++)
            if(AvailableComponents.elementAt(i).equals(s))
                return true;

        return false;
    }

    public boolean handleEvent(Event event)
    {
label0:
        {
            if(false)//event.id == 201)
            {
                applet.destroyFrame();
                return true;
            }
            Event _tmp = event;
            if(false);//event.id != 401)
            {
                Event _tmp1 = event;
//                if(event.id != 403)
//                    break label0;
            }
            KeyEvent ev = (KeyEvent)event;
            applet.keyDown(ev.getCharacter().getBytes()[0], ev.isControlDown(), ev.isShiftDown());
            return true;
        }
//        return super.handleEvent(event);
    }

    public boolean keyDown(KeyEvent event)
    {
    	int i = event.getCode().impl_getCode();
        return applet.keyDown(i, event.isControlDown(), event.isShiftDown());
    }

    public boolean SimpleDialogHandler(String s)
    {
        String s1 = s.substring("SIMPLEDIALOG_".length());
        String s2 = s1.substring(0, s1.indexOf("_"));
        int i = Integer.parseInt(s1.substring(s1.indexOf("_") + 1, s1.length()));
        switch(i)
        {
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        default:
            break;

        case 1: // '\001'
            ExitDialog = null;
            if(s2.equals("OK"))
            {
                if(applet.SimulateRunning())
                    applet.SimulateStop();
                return true;
            }
            if(s2.equals("Annuler"))
                return true;
            break;

        case 2: // '\002'
            NewDialog = null;
            if(s2.equals("OK"))
            {
                applet.MySchematic.DestroyComponents(applet.PinGrid);
                setTitle("DigSim ''RailWay'' 2012.2.0 [sans nom] ; penser à nommer et enregistrer votre simulation (fichier, enregistrer sous)");
                applet.MySchematic.FileName = null;
                applet.MySchematic.Modified = false;
                applet.MySchematicPanel.repaint();
                return true;
            }
            if(s2.equals("Annuler"))
                return true;
            break;

        case 3: // '\003'
            OpenDialog = null;
            if(s2.equals("OK"))
            {
                Schematic schematic = DoFileOpenDialog(applet.PinGrid, "Ouvrir une simulation");
                if(schematic != null)
                {
                    applet.MySchematic = schematic;
                    setTitle("DigSim ''RailWay'' 2012.2.0 [" + schematic.FileName + "]..");
                    applet.MySchematicPanel.repaint();
                    return true;
                }
                break;
            }
            if(s2.equals("Annuler"))
                return true;
            break;

        case 7: // '\007'
            OpenExampleDialog = null;
            if(s2.equals("OK"))
            {
                LoadExample(ExampleFileName);
                return true;
            }
            if(s2.equals("Annuler"))
                return true;
            break;
        }
        return false;
    }

    public boolean TextDialogHandler(String s)
    {
        String s1 = s.substring("TEXTDIALOG_".length());
        String s2 = s1.substring(0, s1.indexOf("_"));
        Object obj = null;
        int i = Integer.parseInt(s1.substring(s1.lastIndexOf("_") + 1, s1.length()));
        switch(i)
        {
        case 7: // '\007'
        case 9: // '\t'
        case 10: // '\n'
        case 23: // '\027'
        default:
            break;

        case 5: // '\005'
            MyTextDialog.close();
            MyTextDialog = null;
            if(s2.equals("OK"))
            {
                String s3 = s1.substring(s1.indexOf("_") + 1, s1.lastIndexOf("_"));
                if(s3.length() > 0)
                {
                    applet.MySchematic.addComponent(new Caption(applet.MySchematicPanel.GridXOffset + 2, applet.MySchematicPanel.GridYOffset + 2, s3));
                    applet.MySchematicPanel.repaint();
                }
                return true;
            }
            if(s2.equals("Annuler"))
                return true;
            break;

        case 13: // '\r'
            MyTextDialog.close();
            MyTextDialog = null;
            if(s2.equals("OK"))
            {
                String s4 = s1.substring(s1.indexOf("_") + 1, s1.lastIndexOf("_"));
                if(s4.length() > 0)
                {
                    applet.MySchematic.addComponent(new CaptionBlack(applet.MySchematicPanel.GridXOffset + 2, applet.MySchematicPanel.GridYOffset + 2, s4));
                    applet.MySchematicPanel.repaint();
                }
                return true;
            }
            if(s2.equals("Annuler"))
                return true;
            break;

        case 21: // '\025'
            MyTextDialog.close();
            MyTextDialog = null;
            if(s2.equals("OK"))
            {
                String s5 = s1.substring(s1.indexOf("_") + 1, s1.lastIndexOf("_"));
                if(s5.length() > 0)
                {
                    applet.MySchematic.addComponent(new CaptionRdP(applet.MySchematicPanel.GridXOffset + 2, applet.MySchematicPanel.GridYOffset + 2, s5));
                    applet.MySchematicPanel.repaint();
                }
                return true;
            }
            if(s2.equals("Annuler"))
                return true;
            break;

        case 15: // '\017'
            MyTextDialog.close();
            MyTextDialog = null;
            if(s2.equals("OK"))
            {
                String s6 = s1.substring(s1.indexOf("_") + 1, s1.lastIndexOf("_"));
                if(s6.length() > 0)
                {
                    applet.MySchematic.addComponent(new CaptionRed(applet.MySchematicPanel.GridXOffset + 2, applet.MySchematicPanel.GridYOffset + 2, s6));
                    applet.MySchematicPanel.repaint();
                }
                return true;
            }
            if(s2.equals("Annuler"))
                return true;
            break;

        case 17: // '\021'
            MyTextDialog.close();
            MyTextDialog = null;
            if(s2.equals("OK"))
            {
                String s7 = s1.substring(s1.indexOf("_") + 1, s1.lastIndexOf("_"));
                if(s7.length() > 0)
                {
                    applet.MySchematic.addComponent(new CaptionBlue(applet.MySchematicPanel.GridXOffset + 2, applet.MySchematicPanel.GridYOffset + 2, s7));
                    applet.MySchematicPanel.repaint();
                }
                return true;
            }
            if(s2.equals("Annuler"))
                return true;
            break;

        case 19: // '\023'
            MyTextDialog.close();
            MyTextDialog = null;
            if(s2.equals("OK"))
            {
                String s8 = s1.substring(s1.indexOf("_") + 1, s1.lastIndexOf("_"));
                if(s8.length() > 0)
                {
                    applet.MySchematic.addComponent(new CaptionGreen(applet.MySchematicPanel.GridXOffset + 2, applet.MySchematicPanel.GridYOffset + 2, s8));
                    applet.MySchematicPanel.repaint();
                }
                return true;
            }
            if(s2.equals("Annuler"))
                return true;
            break;

        case 6: // '\006'
            MyTextChangeDialog.close();
            MyTextChangeDialog = null;
            if(s2.equals("OK"))
                return true;
            if(s2.equals("Annuler"))
            {
                applet.MySchematicPanel.repaint();
                return true;
            }
            break;

        case 14: // '\016'
            MyTextChangeDialog.close();
            MyTextChangeDialog = null;
            if(s2.equals("OK"))
                return true;
            if(s2.equals("Annuler"))
            {
                applet.MySchematicPanel.repaint();
                return true;
            }
            break;

        case 22: // '\026'
            MyTextChangeDialog.close();
            MyTextChangeDialog = null;
            if(s2.equals("OK"))
                return true;
            if(s2.equals("Annuler"))
            {
                applet.MySchematicPanel.repaint();
                return true;
            }
            break;

        case 16: // '\020'
            MyTextChangeDialog.close();
            MyTextChangeDialog = null;
            if(s2.equals("OK"))
                return true;
            if(s2.equals("Annuler"))
            {
                applet.MySchematicPanel.repaint();
                return true;
            }
            break;

        case 18: // '\022'
            MyTextChangeDialog.close();
            MyTextChangeDialog = null;
            if(s2.equals("OK"))
                return true;
            if(s2.equals("Annuler"))
            {
                applet.MySchematicPanel.repaint();
                return true;
            }
            break;

        case 20: // '\024'
            MyTextChangeDialog.close();
            MyTextChangeDialog = null;
            if(s2.equals("OK"))
                return true;
            if(s2.equals("Annuler"))
            {
                applet.MySchematicPanel.repaint();
                return true;
            }
            break;

        case 11: // '\013'
            MyMonoChangeDialog.close();
            MyMonoChangeDialog = null;
            if(s2.equals("OK"))
                return true;
            if(s2.equals("Annuler"))
            {
                applet.MySchematicPanel.repaint();
                return true;
            }
            break;

        case 24: // '\030'
            MyPLA5x4ChangeDialog.close();
            MyPLA5x4ChangeDialog = null;
            if(s2.equals("OK"))
                return true;
            if(s2.equals("Annuler"))
            {
                applet.MySchematicPanel.repaint();
                return true;
            }
            break;

        case 25: // '\031'
            MyZoneRChangeDialog.close();
            MyZoneRChangeDialog = null;
            if(s2.equals("OK"))
                return true;
            if(s2.equals("Annuler"))
            {
                applet.MySchematicPanel.repaint();
                return true;
            }
            break;

        case 12: // '\f'
            MyTextChangeOscillator2Dialog.close();
            MyTextChangeOscillator2Dialog = null;
            if(s2.equals("OK"))
                return true;
            if(s2.equals("Annuler"))
            {
                applet.MySchematicPanel.repaint();
                return true;
            }
            break;

        case 8: // '\b'
            MyTextProbeChangeDialog.close();
            MyTextProbeChangeDialog = null;
            applet.MySchematicPanel.repaint();
            applet.updateAnalyzer();
            return true;
        }
        return false;
    }

    public boolean LoadMemHandler(String s)
    {
        Object obj = null;
        String s4 = s.substring("LOADMEM_".length());
        String s5 = s4.substring(0, s4.indexOf("_"));
        int i = Integer.parseInt(s4.substring(s4.lastIndexOf("_") + 1, s4.length()));
        switch(i)
        {
        default:
            break;

        case 9: // '\t'
            MyLoadDialog.close();
            MyLoadDialog = null;
            if(s5.equals("OK"))
            {
                String s1 = s4.substring(s4.indexOf("_") + 1, s4.lastIndexOf("_"));
                if(s1.length() > 0)
                {
                    applet.MySchematic.addComponent(new Rom(applet.PinGrid, applet.MySchematicPanel.GridXOffset + 2, applet.MySchematicPanel.GridYOffset + 2, s1));
                    applet.MySchematicPanel.repaint();
                }
                return true;
            }
            if(s5.equals("Annuler"))
            {
                applet.MySchematicPanel.repaint();
                return true;
            }
            break;

        case 10: // '\n'
            MyLoadDialog.close();
            MyLoadDialog = null;
            if(s5.equals("OK"))
            {
                String s2 = s4.substring(s4.indexOf("_") + 1, s4.lastIndexOf("_"));
                if(s2.length() > 0)
                {
                    applet.MySchematic.addComponent(new Ram(applet.PinGrid, applet.MySchematicPanel.GridXOffset + 2, applet.MySchematicPanel.GridYOffset + 2, s2));
                    applet.MySchematicPanel.repaint();
                }
                return true;
            }
            if(s5.equals("Annuler"))
            {
                applet.MySchematicPanel.repaint();
                return true;
            }
            break;

        case 23: // '\027'
            MyLoadDialog.close();
            MyLoadDialog = null;
            if(s5.equals("OK"))
            {
                String s3 = s4.substring(s4.indexOf("_") + 1, s4.lastIndexOf("_"));
                if(s3.length() > 0)
                {
                    applet.MySchematic.addComponent(new Ram2(applet.PinGrid, applet.MySchematicPanel.GridXOffset + 2, applet.MySchematicPanel.GridYOffset + 2, s3));
                    applet.MySchematicPanel.repaint();
                }
                return true;
            }
            if(s5.equals("Annuler"))
            {
                applet.MySchematicPanel.repaint();
                return true;
            } else
            {
                return true;
            }
        }
        return false;
    }

    public Schematic DoFileOpenDialog(Pin apin[][], String s)
    {
        Schematic schematic = null;
        FileChooser filedialog = new FileChooser();//this, s, 0);
        filedialog.setTitle(s);
        ClassLoader cl = this.getClass().getClassLoader();
    	URL url = cl.getResource("examples");
		try {
			File defaultDirectory = new File(url.toURI()); 
			filedialog.setInitialDirectory(defaultDirectory);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
        File file = filedialog.showOpenDialog(applet.mainStage);
        //String fileExt = FilenameUtils.getExtension(file.getPath());
        if (file == null)
        	return null;
        String s1 = file.getParent();
        String s2 = file.getName();
        String filePath = file.getPath();
        if(s1 != null && s2 != null)
        {
            if(s2.endsWith(".dig") || s2.endsWith(".sim"))
            {
                FileInputStream fileinputstream;
                try
                {
                    fileinputstream = new FileInputStream(filePath);
                    schematic = new Schematic(apin, fileinputstream);
                }
                catch(Exception exception)
                {
                    String s4 = exception.toString();
                    String as[] = {
                        "OK"
                    };
                    SimpleDialog simpledialog = new SimpleDialog(this, s, s4, as, 1, 0, 0, 1);
                    applet.MySchematicPanel.repaint();
                    return null;
                }
                try
                {
                    if(fileinputstream != null)
                        fileinputstream.close();
                }
                catch(Exception exception1) { }
            } else
            {
                String s5 = "";
                try
                {
                    FileReader filereader = new FileReader(filePath);
                    BufferedReader bufferedreader = new BufferedReader(filereader);
                    String s3;
                    while((s3 = bufferedreader.readLine()) != null)
                        s5 = s5 + s3;
                    schematic = new Schematic(this, apin, s5);
                    bufferedreader.close();
                }
                catch(Exception exception2)
                {
                    String s6 = exception2.toString();
                    String as1[] = {
                        "OK"
                    };
                    SimpleDialog simpledialog1 = new SimpleDialog(this, s, s6, as1, 1, 0, 0, 1);
                    applet.MySchematicPanel.repaint();
                    return null;
                }
            }
            schematic.FileDir = s1;
            schematic.FileName = s2;
            schematic.Modified = false;
            applet.updateAnalyzer();
        }
        return schematic;
    }

    public void LoadExample(String s)
    {
        applet.MySchematic.DestroyComponents(applet.PinGrid);
        InputStream inputstream;
        try
        {
            //inputstream = (new URL(applet.getDocumentBase(), s)).openStream();
            //inputstream = url.openStream();

            ClassLoader cl = this.getClass().getClassLoader();
            inputstream = cl.getResourceAsStream(s);

            applet.MySchematic = new Schematic(applet.PinGrid, inputstream);
        }
        catch(Exception exception)
        {
            String s1 = exception.toString();
            String as[] = {
                "OK"
            };
            SimpleDialog simpledialog = new SimpleDialog(this, "Ouvrir une simulation", s1, as, 1, 0, 0, 1);
            applet.MySchematicPanel.repaint();
            return;
        }
        try
        {
            if(inputstream != null)
                inputstream.close();
        }
        catch(Exception exception1) { }
        applet.MySchematic.FileDir = "";
        applet.MySchematic.FileName = s;
        applet.MySchematic.Modified = false;
        setTitle("DigSim ''RailWay'' 2012.2.0 [" + s + "] ; pour enregistrer cette simulation : menu fichier, enregistrer sous)");
        applet.MySchematicPanel.repaint();
        applet.updateAnalyzer();
    }

    public void dispose()
    {
    	close();
    }

    public void DoFileSaveDialog(Schematic schematic, boolean flag, String s)
    {
        if(flag)
        {
        	FileChooser filedialog = new FileChooser();//this, s, 1);
        	filedialog.setTitle(s);
            File file = filedialog.showSaveDialog(applet.mainStage);
            if (file == null)
            	return;
            SaveFileDirectory = file.getParent() + "\\";
            SaveFileName = file.getName();
        } else
        {
            SaveFileDirectory = schematic.FileDir;
            SaveFileName = schematic.FileName;
        }
        if(SaveFileDirectory != null && SaveFileName != null)
        {
            if(SaveFileName.endsWith(".*.*"))
                SaveFileName = SaveFileName.substring(0, SaveFileName.length() - 4);
            if(SaveFileName.endsWith(".txt.txt"))
                SaveFileName = SaveFileName.substring(0, SaveFileName.length() - 4);
            schematic.FileDir = SaveFileDirectory;
            schematic.FileName = SaveFileName;
            FileOutputStream fileoutputstream;
            if(SaveFileName.endsWith(".dig"))
                try
                {
                    fileoutputstream = new FileOutputStream(SaveFileDirectory + SaveFileName);
                    schematic.Save(fileoutputstream);
                }
                catch(Exception exception)
                {
                    String s1 = exception.toString();
                    String as[] = {
                        "OK"
                    };
                    SimpleDialog simpledialog = new SimpleDialog(this, s, s1, as, 1, 0, 0, 1);
                    return;
                }
            else
            if(SaveFileName.endsWith(".txt"))
                try
                {
                    fileoutputstream = new FileOutputStream(SaveFileDirectory + SaveFileName);
                    schematic.SaveAsScript(fileoutputstream);
                }
                catch(Exception exception1)
                {
                    String s2 = exception1.toString();
                    String as1[] = {
                        "OK"
                    };
                    SimpleDialog simpledialog1 = new SimpleDialog(this, s, s2, as1, 1, 0, 0, 1);
                    return;
                }
            else
                try
                {
                    fileoutputstream = new FileOutputStream(SaveFileDirectory + SaveFileName + ".txt");
                    schematic.SaveAsScript(fileoutputstream);
                }
                catch(Exception exception2)
                {
                    String s3 = exception2.toString();
                    String as2[] = {
                        "OK"
                    };
                    SimpleDialog simpledialog2 = new SimpleDialog(this, s, s3, as2, 1, 0, 0, 1);
                    return;
                }
            try
            {
                if(fileoutputstream != null)
                    fileoutputstream.close();
            }
            catch(Exception exception3) { }
            schematic.Modified = false;
        }
    }

    public boolean action(Event event, Object obj)
    {
        String s = (String)obj;
        if(s.startsWith("SIMPLEDIALOG_"))
            return SimpleDialogHandler(s);
        if(s.startsWith("TEXTDIALOG_"))
            return TextDialogHandler(s);
        if(s.startsWith("LOADMEM_"))
            return LoadMemHandler(s);
        if(true)//event.target instanceof MenuItem)
        {
            HelpDialog helpdialog;
            if(s.equals("Ouvrir une simulation (.txt)              Ctrl+O"))
                applet.UserWantsOpenSchematic();
            else
            if(s.equals("Aide spécifique (tests et simulations)"))
                applet.UserWantsOpenExample();
            else
            if(s.equals("Enregistrer               (.txt)                 Ctrl+S"))
            {
                if(SaveFileName != null)
                {
                    applet.UserWantsSaveSchematic();
                } else
                {
                    DoFileSaveDialog(applet.MySchematic, true, "Sauvegarder le schéma sous un nouveau nom");
                    this.setTitle("DigSim ''RailWay'' 2012.2.0 [" + applet.MySchematic.FileName + "] ;  enregistré");
                    applet.MySchematicPanel.repaint();
                }
            } else
            if(s.equals("Enregistrer sous... (.txt)                  Ctrl+Shift+S"))
            {
                if(applet.HelpWanted)
                {
                    helpdialog = new HelpDialog(this, "Enregistrer sous");
                } else
                {
                    DoFileSaveDialog(applet.MySchematic, true, "Sauvegarder le schéma sous un nouveau nom");
                    setTitle("DigSim ''RailWay'' 2012.2.0 [" + applet.MySchematic.FileName + "] ;  enregistré");
                    applet.MySchematicPanel.repaint();
                }
            } else
            if(s.equals("Fermer la fenêtre de simulation"))
            {
                if(applet.HelpWanted)
                    helpdialog = new HelpDialog(this, "Fermer la fenetre");
                else
                    applet.destroyFrame();
            } else
            if(s.equals("Nouveau                                          Ctrl+N"))
                applet.UserWantsNewSchematic();
            else
            if(s.equals("Importer (obsolète)                          Ctrl+I, i"))
                applet.UserWantsImportFromScript();
            else
            if(s.equals("Exporter (obsolète)                          Ctrl+E, e"))
                applet.UserWantsExportAsScript();
            else
            if(s.equals("Quitter"))
            {
                if(applet.HelpWanted)
                    helpdialog = new HelpDialog(this, "Quitter");
                else
                if(ExitDialog == null)
                {
                    String as[] = {
                        "OK", "Annuler"
                    };
                    ExitDialog = new SimpleDialog(this, "Quitter DigSim", "Voulez vous quitter DigSim?", as, 2, 1, 1, 2);
                }
            } else
            if(s.equals("Texte monospaced   (m)"))
                applet.UserWantsTextDrawing();
            else
            if(s.equals("Texte noir  (n)"))
                applet.UserWantsBlackTextDrawing();
            else
            if(s.equals("Texte gris (RdP)  (g)"))
                applet.UserWantsRdPTextDrawing();
            else
            if(s.equals("Texte rouge  (r)"))
                applet.UserWantsRedTextDrawing();
            else
            if(s.equals("Texte bleu    (b)"))
                applet.UserWantsBlueTextDrawing();
            else
            if(s.equals("Texte vert    (v)"))
                applet.UserWantsGreenTextDrawing();
            else
            if(s.equals("PROM 32*8 (sorties 3S)"))
                applet.UserWantsMem(9);
            else
            if(s.equals("RAM1 32*8 (sorties 3S & E/S separees)"))
                applet.UserWantsMem(10);
            else
            if(s.equals("RAM2 32*8 (sorties 3S & E/S communes)"))
                applet.UserWantsMem(23);
            else
            if(s.equals("Au sujet de DigSim"))
                helpdialog = new HelpDialog(this, "Au sujet de DigSim");
            else
            if(s.equals("Raccourcis clavier"))
                helpdialog = new HelpDialog(this, "Raccourcis clavier");
            else
            if(s.equals("Classpath (obsolète)"))
                helpdialog = new HelpDialog(this, "CLASSPATH");
            else
            if(s.equals("Foire aux questions (FAQ)"))
                helpdialog = new HelpDialog(this, "FAQ");
            else
            if(s.equals("Au sujet du menu ''Fichier''"))
                helpdialog = new HelpDialog(this, "Au sujet du menu Fichiers");
            else
            if(s.equals("Au sujet du menu ''Local''"))
                helpdialog = new HelpDialog(this, "Au sujet du menu Local");
            else
            if(s.equals("Au sujet du menu ''Edit''"))
                helpdialog = new HelpDialog(this, "Au sujet du menu Edit");
            else
            if(s.equals("Au sujet du menu ''Câblage''"))
                helpdialog = new HelpDialog(this, "Au sujet du menu Cablage");
            else
            if(s.equals("Au sujet du menu ''Portes''"))
                helpdialog = new HelpDialog(this, "Au sujet du menu Portes");
            else
            if(s.equals("Au sujet du menu ''ISO''"))
                helpdialog = new HelpDialog(this, "Au sujet du menu ISO");
            else
            if(s.equals("Au sujet du menu ''DIL''"))
                helpdialog = new HelpDialog(this, "Au sujet du menu DIL");
            else
            if(s.equals("Au sujet du menu ''Combinatoire''"))
                helpdialog = new HelpDialog(this, "Au sujet du menu Combinatoire");
            else
            if(s.equals("Au sujet du menu ''Bascules''"))
                helpdialog = new HelpDialog(this, "Au sujet du menu Bascules");
            else
            if(s.equals("Au sujet du menu ''Compteurs & registres''"))
                helpdialog = new HelpDialog(this, "Au sujet du menu Compteurs Registres");
            else
            if(s.equals("Au sujet du menu ''Affichage''"))
                helpdialog = new HelpDialog(this, "Au sujet du menu Affichage");
            else
            if(s.equals("Au sujet du menu ''Applications''"))
                helpdialog = new HelpDialog(this, "Au sujet du menu Applications");
            else
            if(s.equals("Au sujet du menu ''Rails & train''"))
                helpdialog = new HelpDialog(this, "Au sujet du menu Rails Train");
            else
            if(s.equals("Au sujet du menu ''Divers''"))
                helpdialog = new HelpDialog(this, "Au sujet du menu Divers");
            else
            if(s.equals("Au sujet du menu ''Texte''"))
                helpdialog = new HelpDialog(this, "Au sujet du menu Texte");
            else
            if(s.equals("Au sujet du menu ''Outils''"))
                helpdialog = new HelpDialog(this, "Au sujet du menu Outils");
            else
            if(s.equals("Au sujet du menu ''Options''"))
                helpdialog = new HelpDialog(this, "Au sujet du menu Options");
            else
            if(s.equals("Au sujet du menu ''Aide''"))
                helpdialog = new HelpDialog(this, "Au sujet du menu Aide");
            else
            if(s.equals("Au sujet du menu ''Perso & tests''"))
                helpdialog = new HelpDialog(this, "Au sujet du menu Perso & tests");
            else
            if(s.equals("Au sujet du menu ''En construction''"))
                helpdialog = new HelpDialog(this, "Au sujet du menu En construction");
            else
            if(s.equals("Aide                                      h, ?"))
                applet.UserWantsHelp();
            else
            if(s.equals("Supprimer                Ctrl+X, Suppr"))
                applet.UserWantsCutSchematic();
            else
            if(s.equals("Copier                      Ctrl+C"))
                applet.UserWantsCopySchematic();
            else
            if(s.equals("Coller                       Ctrl+V"))
                applet.UserWantsPasteSchematic();
            else
            if(s.equals("Rotation                   Ctrl+R, t"))
                applet.UserWantsRotateSchematic();
            else
            if(s.equals("Enregistrer la sélection vers..."))
                applet.UserWantsCopyToSchematic();
            else
            if(s.equals("Coller à partir de... "))
            {
                applet.UserWantsPasteFromSchematic();
            } else
            {
                if(s.equals("Jonction               j"))
                {
                    applet.addComponent("Junction");
                    return true;
                }
                if(s.equals("Fil de liaison         f ou w"))
                {
                    applet.addComponent("Wire");
                    return true;
                }
                HelpDialog helpdialog1;
                if(s.equals("Tout sélectionner     Ctrl+A"))
                {
                    if(applet.HelpWanted)
                        helpdialog1 = new HelpDialog(this, "Tout selectionner");
                    else
                        applet.MySchematicPanel.SelectAll();
                } else
                if(IsComponentName(s))
                {
                    if(applet.HelpWanted)
                        helpdialog1 = new HelpDialog(this, s);
                    else
                        applet.addComponent(s);
                } else
                if("Lancer la simulation                           Entrée".equals(obj))
                {
                    if(applet.HelpWanted)
                        helpdialog1 = new HelpDialog(this, "Demarrer");
                    else
                        applet.UserWantsSimulate();
                } else
                if("Arrêter la simulation                            Entrée".equals(obj))
                {
                    if(applet.HelpWanted)
                        helpdialog1 = new HelpDialog(this, "Arreter");
                    else
                        applet.UserWantsSimulate();
                } else
                if("Analyseur logique                               a".equals(obj))
                    applet.UserWantsAnalyzer();
                else
                if("Vitesse de simulation et options          o".equals(obj))
                    applet.UserWantsOptions();
                else
                if(s.equals("Réinitialiser la simulation                   *, Tab"))
                    applet.UserWantsReInitialisationSimulation();
                else
                if(s.equals("Réinitialiser la simulation                     *, Tab"))
                    applet.UserWantsReInitialisationSimulation();
            }
            return true;
        } else
        {
            return false;
        }
    }

    Vector<String> AvailableComponents;
    public DigSim applet;
    Menu FileMenu;
    Menu EditMenu;
    Menu LocalFilesMenu;
    Menu WiringMenu;
    Menu GatesMenu;
    Menu ISOMenu;
    Menu DILMenu;
    Menu BiStableMenu;
    Menu DisplayMenu;
    Menu OthersMenu;
    Menu CombinationalMenu;
    Menu CountersRegistersMenu;
    Menu ApplicationsMenu;
    Menu RailsTrainMenu;
    Menu ConstructionMenu;
    Menu PersoMenu;
    Menu OutilsMenu;
    Menu TexteMenu;
    Menu SimulateMenu;
    Menu OptionsMenu;
    Menu HelpMenu;
    MenuBar MyMenuBar;
    MenuItem CutMenuItem;
    MenuItem CopyMenuItem;
    MenuItem PasteMenuItem;
    MenuItem RotateMenuItem;
    MenuItem CopyDiskMenuItem;
    MenuItem PasteDiskMenuItem;
    MenuItem StartMenuItem;
    MenuItem StopMenuItem;
    SimpleDialog ExitDialog;
    SimpleDialog NewDialog;
    SimpleDialog OpenDialog;
    SimpleDialog OpenExampleDialog;
    TextDialog MyTextDialog;
    TextDialog MyTextChangeDialog;
    TextDialog MyTextProbeChangeDialog;
    LoadMem MyLoadDialog;
    TextDialog MyMonoChangeDialog;
    TextDialog MyPLA5x4ChangeDialog;
    TextDialog MyZoneRChangeDialog;
    TextDialog MyTextChangeOscillator2Dialog;
    Vector<MenuItem> MenuItemsToDisable;
    static final int EXITDIALOG_ID = 1;
    static final int NEWDIALOG_ID = 2;
    static final int OPENDIALOG_ID = 3;
    static final int NEWTEXTDIALOG_ID = 5;
    static final int CHANGETEXTDIALOG_ID = 6;
    static final int OPENEXAMPLE_ID = 7;
    static final int CHANGETEXTPROBEDIALOG_ID = 8;
    static final int ROM_ID = 9;
    static final int RAM_ID = 10;
    static final int CHANGEMONOSTABLEDIALOG_ID = 11;
    static final int CHANGETEXTOSCILLATEUR2DIALOG_ID = 12;
    static final int NEWBLACKTEXTDIALOG_ID = 13;
    static final int CHANGEBLACKTEXTDIALOG_ID = 14;
    static final int NEWREDTEXTDIALOG_ID = 15;
    static final int CHANGEREDTEXTDIALOG_ID = 16;
    static final int NEWBLUETEXTDIALOG_ID = 17;
    static final int CHANGEBLUETEXTDIALOG_ID = 18;
    static final int NEWGREENTEXTDIALOG_ID = 19;
    static final int CHANGEGREENTEXTDIALOG_ID = 20;
    static final int NEWRDPTEXTDIALOG_ID = 21;
    static final int CHANGERDPTEXTDIALOG_ID = 22;
    static final int RAM2_ID = 23;
    static final int CHANGEPLA5x4DIALOG_ID = 24;
    static final int CHANGETraitsCieDIALOG_ID = 25;
    String SaveFileDirectory;
    String SaveFileName;
    String ExampleFileName;
}
