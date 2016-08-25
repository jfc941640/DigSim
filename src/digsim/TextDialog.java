package digsim;


import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;

import digsim.Probe;
import digsim.components.Caption;
import digsim.components.CaptionBlack;
import digsim.components.CaptionBlue;
import digsim.components.CaptionGreen;
import digsim.components.CaptionRdP;
import digsim.components.CaptionRed;
import digsim.components.Monostable;
import digsim.components.Oscillator2;
import digsim.components.PLA5x4;
import digsim.components.TraitsCie;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// Referenced classes of package digsim:
//            Caption, CaptionBlack, CaptionRdP, CaptionRed,
//            CaptionBlue, CaptionGreen, Oscillator2, Probe,
//            InputPin, Monostable, PLA5x4, TraitsCie,
//            DigSimFrame

public class TextDialog extends Stage //BorderPane //Dialog
{

    TextDialog(DigSimFrame digsimframe, String s, int i)
    {
//        super(digsimframe, "Entrer votre texte...", false);
        resetAll();
        init("Entrer votre texte...", digsimframe, s, i);
    }

    TextDialog(DigSimFrame digsimframe, Caption caption, int i)
    {
//        super(digsimframe, "Modifier ce texte ''monospaced''", false);
        resetAll();
        ActCaption = caption;
        init("Modifier ce texte ''monospaced''", digsimframe, caption.Text, i);
    }

    TextDialog(DigSimFrame digsimframe, CaptionBlack captionblack, int i)
    {
        //super(digsimframe, "Modifier ce texte écrit en noir", false);
        resetAll();
        ActCaptionBlack = captionblack;
        init("Modifier ce texte écrit en noir", digsimframe, captionblack.Text, i);
    }

	private void resetAll() {
		MyTextField = null;
        Delay = null;
        Stop = null;
        XZone = null;
        YZone = null;
        ZZone = null;
        ETpla5x4 = null;
        OUpla5x4 = null;
        NONpla5x4 = null;
        ActCaption = null;
        ActCaptionBlack = null;
        ActCaptionRdP = null;
        ActCaptionRed = null;
        ActCaptionBlue = null;
        ActCaptionGreen = null;
        ActProbe = null;
        ActOscillator2 = null;
	}

    TextDialog(DigSimFrame digsimframe, CaptionRdP captionrdp, int i)
    {
//        super(digsimframe, "Modifier ce texte écrit en rouge", false);
        resetAll();
        ActCaptionRdP = captionrdp;
        init("Modifier ce texte écrit en rouge", digsimframe, captionrdp.Text, i);
    }

	private void init(String title, DigSimFrame digsimframe, String value, int i) {
		setTitle(title);
		frame = digsimframe;
        DialogID = i;
        text = value;
        TextDialogFont = Font.font("Sans-serif", 13);
        TextDialogFontMetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(TextDialogFont);
        //setFont(TextDialogFont);
        BorderPane bp = new BorderPane();
        bp.setTop(MyTextField = new TextField(value));
        MyTextField.setFont(TextDialogFont);
        FlowPane panel = new FlowPane();
        bp.setBottom(panel);
        
    	addButtons(panel);
    	
        //setBounds(40, 40, 800, 110);
        Group root = new Group();
        Scene scene = new Scene(root, 800, 110);
//        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            public void handle(KeyEvent event) {
//            	keyDown(event);
//            }
//        });
        setScene(scene);
        root.getChildren().add(bp);
        show();
        MyTextField.requestFocus();
	}

	private void addButtons(FlowPane panel) {
		Button btnOK = new Button("OK");
    	btnOK.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
              handleButton(e, btnOK.getText());
           }
       });

        panel.getChildren().add(btnOK);

        Button btnCancel = new Button("Annuler");
    	btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                handleButton(e, btnCancel.getText());
             }
       });
    	
    	panel.getChildren().add(btnCancel);
	}

    TextDialog(DigSimFrame digsimframe, CaptionRed captionred, int i)
    {
//        super(digsimframe, "Modifier ce texte écrit en rouge", false);
        resetAll();
        ActCaptionRed = captionred;
        init("Modifier ce texte écrit en rouge", digsimframe, captionred.Text, i);
    }

    TextDialog(DigSimFrame digsimframe, CaptionBlue captionblue, int i)
    {
//        super(digsimframe, "Modifier ce texte écrit en bleu", false);
        resetAll();
        ActCaptionBlue = captionblue;
        init("Modifier ce texte écrit en bleu", digsimframe, captionblue.Text, i);
    }

    TextDialog(DigSimFrame digsimframe, CaptionGreen captiongreen, int i)
    {
//        super(digsimframe, "Modifier ce texte écrit en vert", false);
        resetAll();
        ActCaptionGreen = captiongreen;
        init("Modifier ce texte écrit en vert", digsimframe, captiongreen.Text, i);
    }

    TextDialog(DigSimFrame digsimframe, Oscillator2 oscillator2, int i)
    {
//        super(digsimframe, "Modifier la période de l'oscillateur", false);
        resetAll();
        ActOscillator2 = oscillator2;
        init("Modifier la période de l'oscillateur", digsimframe,  String.valueOf(ActOscillator2.cycle), i);
    }

    public void paint(GraphicsContext g)
    {
        if(ActOscillator2 != null)
            DrawWithOffset.fillText(g,"Entrer la période de l'oscillateur", 70, 75);
    }

    TextDialog(DigSimFrame digsimframe, Probe probe, int i)
    {
//        super(digsimframe, "Modifier le nom de la sonde", false);
        resetAll();
        ActProbe = probe;
        init("Modifier le nom de la sonde", digsimframe, probe.IPin[0].getName(), i);
    }

    TextDialog(DigSimFrame digsimframe, Monostable monostable, int i)
    {
//        super(digsimframe, "Réglage des paramètres du monostable", false);
		setTitle("Réglage des paramètres du monostable");
        resetAll();
        ActMono = monostable;
		frame = digsimframe;
        DialogID = i;

        TextDialogFont = Font.font("Sans-serif", 13);
        TextDialogFontMetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(TextDialogFont);
        //setFont(TextDialogFont);
        BorderPane bp = new BorderPane();
        FlowPane panel0 = new FlowPane();
        text = Integer.toString(monostable.getDelay());
		panel0.getChildren().add(new Label("a: ATTENTE avant TEMPORISATION (en 1/2 périodes d'horloge)"));
		panel0.getChildren().add(Delay = new TextField(text));
		bp.setTop(panel0);
        FlowPane panel1 = new FlowPane();
        text = Integer.toString(monostable.getStop());
		panel1.getChildren().add(new Label("t: TEMPORISATION (durée de Q=1, en 1/2 périodes d'horloge)"));
		panel1.getChildren().add(Stop = new TextField(text));
		bp.setCenter(panel1);
        FlowPane panel = new FlowPane();
        bp.setBottom(panel);
        
        addButtons(panel);

        //setBounds(40, 40, 800, 110);
        Group root = new Group();
        Scene scene = new Scene(root, 800, 110);
//        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            public void handle(KeyEvent event) {
//            	keyDown(event);
//            }
//        });
        setScene(scene);
        root.getChildren().add(bp);
        show();
        Delay.requestFocus();
    }

    TextDialog(DigSimFrame digsimframe, PLA5x4 pla5x4, int i)
    {
//        super(digsimframe, "Entrée du code hexadécimal des matrices ET, OU et NON du PLA 5x4", false);
		setTitle("Entrée du code hexadécimal des matrices ET, OU et NON du PLA 5x4");
        resetAll();
        ActPLA5x4 = pla5x4;
		frame = digsimframe;
        DialogID = i;

        TextDialogFont = Font.font("Monospaced", 13);
        TextDialogFontMetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(TextDialogFont);
        //setFont(TextDialogFont);
        BorderPane bp = new BorderPane();
        FlowPane panel0 = new FlowPane();
        text = pla5x4.getETpla5x4();
		panel0.getChildren().add(new Label("ET 4e-8s (20 chiffres hexa)"));
		panel0.getChildren().add(ETpla5x4 = new TextField(text));
		bp.setTop(panel0);
        FlowPane panel1 = new FlowPane();
        text = pla5x4.getOUpla5x4();
		panel1.getChildren().add(new Label("OU 8e-4s (8 chiffres hexa)"));
		panel1.getChildren().add(OUpla5x4 = new TextField(text));
        FlowPane panel2 = new FlowPane();
        text = pla5x4.getNONpla5x4();
		panel2.getChildren().add(new Label("NON 4e-4s (1 chiffre hexa)"));
		panel2.getChildren().add(NONpla5x4 = new TextField(text));
		VBox panelV = new VBox();
		panelV.getChildren().add(panel1);
		panelV.getChildren().add(panel2);
		bp.setCenter(panelV);
        FlowPane panel = new FlowPane();
        bp.setBottom(panel);
        
        addButtons(panel);
        //setBounds(40, 40, 400, 180);
        Group root = new Group();
        Scene scene = new Scene(root, 400, 180);
//        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            public void handle(KeyEvent event) {
//            	keyDown(event);
//            }
//        });
        setScene(scene);
        root.getChildren().add(bp);
        show();
        ETpla5x4.requestFocus();
    }

    TextDialog(DigSimFrame digsimframe, TraitsCie traitscie, int i)
    {
//      super(digsimframe, "Dimension de la zone", false);
    	setTitle("Dimension de la zone");
    	resetAll();
    	ActZoneR = traitscie;
		frame = digsimframe;
		DialogID = i;

		TextDialogFont = Font.font("Sans-serif", 13);
		TextDialogFontMetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(TextDialogFont);
		//setFont(TextDialogFont);
		BorderPane bp = new BorderPane();
		FlowPane panel0 = new FlowPane();
		text = Integer.toString(traitscie.getXZone());
		panel0.getChildren().add(new Label("Largeur (x10)"));
		panel0.getChildren().add(XZone = new TextField(text));
		bp.setTop(panel0);
		FlowPane panel1 = new FlowPane();
		text = Integer.toString(traitscie.getYZone());
		panel1.getChildren().add(new Label("Hauteur (x10)"));
		panel1.getChildren().add(YZone = new TextField(text));
		FlowPane panel2 = new FlowPane();
		text = Integer.toString(traitscie.getZZone());
		panel2.getChildren().add(new Label("RVB (000 à 999)"));
		panel2.getChildren().add(ZZone = new TextField(text));
		VBox panelV = new VBox();
		panelV.getChildren().add(panel1);
		panelV.getChildren().add(panel2);
		bp.setCenter(panelV);
		FlowPane panel = new FlowPane();
		bp.setBottom(panel);
      
		addButtons(panel);
		//setBounds(40, 40, 400, 180);
		Group root = new Group();
		Scene scene = new Scene(root, 400, 180);
//      scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//          public void handle(KeyEvent event) {
//          	keyDown(event);
//          }
//      });
		setScene(scene);
		root.getChildren().add(bp);
		show();
		XZone.requestFocus();
    }

    public boolean handleEvent(Event event)
    {
//        if(event.id == 201)
//        {
    		close();
            return frame.action(event, "TEXTDIALOG_Cancel_" + DialogID);
//        } else
//        {
//            return super.handleEvent(event);
//        }
    }

    public boolean action(Event event, Object obj)
    {
//        if(event.target instanceof TextField)
//        {
//        	return handleOK(event);
//        }
        if(true) //event.target instanceof Button)
        {
            String s = (String)obj;
            return handleButton(event, s);
        } else
        {
            return false;
        }
    }

	private boolean handleButton(Event event, String s) {
		if(s.equals("OK"))
		{
			return handleOK(event);
		} else
		{
		    close();
		    return frame.action(event, "TEXTDIALOG_Cancel_" + DialogID);
		}
	}

	private boolean handleOK(Event event) {
		close();
		if(ActCaption != null)
		{
		    ActCaption.Text = MyTextField.getText();
		    return frame.action(event, "TEXTDIALOG_Cancel_" + DialogID);
		}
		if(ActCaptionBlack != null)
		{
		    ActCaptionBlack.Text = MyTextField.getText();
		    return frame.action(event, "TEXTDIALOG_Cancel_" + DialogID);
		}
		if(ActCaptionRdP != null)
		{
		    ActCaptionRdP.Text = MyTextField.getText();
		    return frame.action(event, "TEXTDIALOG_Cancel_" + DialogID);
		}
		if(ActCaptionRed != null)
		{
		    ActCaptionRed.Text = MyTextField.getText();
		    return frame.action(event, "TEXTDIALOG_Cancel_" + DialogID);
		}
		if(ActCaptionBlue != null)
		{
		    ActCaptionBlue.Text = MyTextField.getText();
		    return frame.action(event, "TEXTDIALOG_Cancel_" + DialogID);
		}
		if(ActCaptionGreen != null)
		{
		    ActCaptionGreen.Text = MyTextField.getText();
		    return frame.action(event, "TEXTDIALOG_Cancel_" + DialogID);
		}
		if(ActProbe != null)
		{
		    ActProbe.IPin[0].setName(MyTextField.getText());
		    return frame.action(event, "TEXTDIALOG_Cancel_" + DialogID);
		}
		if(ActOscillator2 != null)
		{
		    ActOscillator2.cycle = Integer.parseInt(MyTextField.getText());
		    if(ActOscillator2.cycle < 1)
		        ActOscillator2.cycle = 1;
		    return frame.action(event, "TEXTDIALOG_Cancel_" + DialogID);
		}
		if(ActMono != null)
		{
		    ActMono.setStop(Integer.parseInt(Stop.getText()));
		    ActMono.setDelay(Integer.parseInt(Delay.getText()));
		    return frame.action(event, "TEXTDIALOG_Cancel_" + DialogID);
		}
		if(ActPLA5x4 != null)
		{
		    ActPLA5x4.setETpla5x4(ETpla5x4.getText());
		    ActPLA5x4.setOUpla5x4(OUpla5x4.getText());
		    ActPLA5x4.setNONpla5x4(NONpla5x4.getText());
		    return frame.action(event, "TEXTDIALOG_Cancel_" + DialogID);
		}
		if(ActZoneR != null)
		{
		    ActZoneR.setXZone(Integer.parseInt(XZone.getText()));
		    ActZoneR.setYZone(Integer.parseInt(YZone.getText()));
		    ActZoneR.setZZone(Integer.parseInt(ZZone.getText()));
		    return frame.action(event, "TEXTDIALOG_Cancel_" + DialogID);
		} else
		{
		    return frame.action(event, "TEXTDIALOG_OK_" + MyTextField.getText() + "_" + DialogID);
		}
	}

    DigSimFrame frame;
    String text;
    protected Font TextDialogFont;
    protected FontMetrics TextDialogFontMetrics;
    TextField MyTextField;
    TextField Delay;
    TextField Stop;
    TextField XZone;
    TextField YZone;
    TextField ZZone;
    TextField ETpla5x4;
    TextField OUpla5x4;
    TextField NONpla5x4;
    Caption ActCaption;
    CaptionBlack ActCaptionBlack;
    CaptionRdP ActCaptionRdP;
    CaptionRed ActCaptionRed;
    CaptionBlue ActCaptionBlue;
    CaptionGreen ActCaptionGreen;
    Probe ActProbe;
    Monostable ActMono;
    PLA5x4 ActPLA5x4;
    TraitsCie ActZoneR;
    Oscillator2 ActOscillator2;
    int DialogID;
}
