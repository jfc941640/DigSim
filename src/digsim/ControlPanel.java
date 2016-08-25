package digsim;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

// Referenced classes of package digsim:
//            ImageButton, SimpleDialog, HelpDialog, DigSim,
//            MyColor

public class ControlPanel extends FlowPane
{

    public ControlPanel(DigSim digsim)
    {
//    	super();
//        ImageBuffer = null;
//        CopyImage = null;
//        ButtonOffset = 0;
//        PressedButton = null;
        ImageButtonsDisabled = false;
        applet = digsim;
        
        ClassLoader cl = this.getClass().getClassLoader();
        
        final ToggleGroup group = new ToggleGroup();
              	
		getChildren().add(btnNew = buildToggleButton(cl, "images/new.png", group, (ActionEvent event) -> {applet.UserWantsNewSchematic();}));
		getChildren().add(btnOpen = buildToggleButton(cl, "images/open.png", group, (ActionEvent event) -> {applet.UserWantsOpenSchematic();}));
		getChildren().add(btnSave = buildToggleButton(cl, "images/save.png", group, (ActionEvent event) -> {applet.UserWantsSaveSchematic();}));
		getChildren().add(btnCut = buildToggleButton(cl, "images/cut.png", group, (ActionEvent event) -> {applet.UserWantsCutSchematic();}));
		getChildren().add(btnCopy = buildToggleButton(cl, "images/copy.png", group, (ActionEvent event) -> {applet.UserWantsCopySchematic();}));
		getChildren().add(btnPaste = buildToggleButton(cl, "images/paste.png", group, (ActionEvent event) -> {applet.UserWantsPasteSchematic();}));        		
		getChildren().add(btnSelect = buildToggleButton(cl, "images/select.png", group, (ActionEvent event) -> {
	        HelpDialog helpdialog;
	        if(applet.HelpWanted)
                helpdialog = new HelpDialog(applet.frame, "Fleche");
			else
			  applet.UserWantsPointer();							
			}));
		getChildren().add(btnWire = buildToggleButton(cl, "images/wire.png", group, (ActionEvent event) -> {applet.UserWantsWireDrawing();}));
		getChildren().add(btnJunction = buildToggleButton(cl, "images/junction.png", group, (ActionEvent event) -> {applet.UserWantsJunctionDrawing();}));
		getChildren().add(btnText = buildToggleButton(cl, "images/text.png", group, (ActionEvent event) -> {applet.UserWantsTextDrawing();}));
		getChildren().add(btnReinitialize = buildToggleButton(cl, "images/reinitialize.png", group, (ActionEvent event) -> {applet.UserWantsReinitialiser();}));
		getChildren().add(btnSimulate = buildToggleButton(cl, "images/simulate.png", group, (ActionEvent event) -> {applet.UserWantsSimulate();}));
		getChildren().add(btnHelp = buildToggleButton(cl, "images/help.png", group, (ActionEvent event) -> {applet.UserWantsHelp();}));

//		btnNew.setSelected(true);
		
        setPrefHeight(24);
//        buttons = new Vector<ImageButton>();
//        LoadButtonsImage();
////        setBackground(Color.rgb(200, 200, 200));
//        buttons.addElement(new ImageButton("New", 0));
//        buttons.addElement(new ImageButton("Open", 24));
//        buttons.addElement(new ImageButton("Save", 48));
//        buttons.addElement(new ImageButton("Cut", 80));
//        buttons.addElement(new ImageButton("Copy", 104));
//        buttons.addElement(new ImageButton("Paste", 128));
//        buttons.addElement(new ImageButton("Pointer", 160));
//        buttons.addElement(new ImageButton("Wire", 184));
//        buttons.addElement(new ImageButton("Junction", 208));
//        buttons.addElement(new ImageButton("Text", 232));
//        buttons.addElement(new ImageButton("Reinitialiser", 264));
//        buttons.addElement(new ImageButton("Simulate", 288));
//        buttons.addElement(new ImageButton("Help", 320));
//        if(ImageButtonsDisabled)
//            setSize((int)getWidth(), 36);
//        else
//            setSize((int)getWidth(), 4);

        setWidth(800);//applet.frame.getWidth());
        setHeight(60);//36);
//        gc = getGraphicsContext2D();
        
//        setOnMousePressed(event -> {
//            int x = (int)event.getX(), y = (int)event.getY();
//            mouseDown(event, x, y);
//        });
//
//        setOnMouseReleased(event -> {
//            int x = (int)event.getX(), y = (int)event.getY();
//            mouseUp(event, x, y);
//        });

//        repaint();

    }

	private ToggleButton buildToggleButton(ClassLoader cl, String image, ToggleGroup group, EventHandler<ActionEvent> eventHandler) {
//		final ToggleButton toggle      = new ToggleButton();
//        final Image unselected  = new Image(cl.getResourceAsStream("images/new.png"));
//        final Image selected = new Image(cl.getResourceAsStream(image));
//        final ImageView    toggleImage = new ImageView();
//        toggle.setGraphic(toggleImage);
//        toggleImage.imageProperty().bind(Bindings
//          .when(toggle.selectedProperty())
//            .then(selected)
//            .otherwise(unselected)
//        );
        
		
		final ToggleButton toggle = new ToggleButton("", new ImageView(new Image(cl.getResourceAsStream(image))));
		toggle.setToggleGroup(group);
		toggle.setOnAction(eventHandler);
		return toggle;

	}

//    public Dimension preferredSize()
//    {
//        int i = applet.(int)getWidth();
//        if(ImageButtonsDisabled)
//            return new Dimension(i, 4);
//        else
//            return new Dimension(i, 36);
//    }

    public ToggleButton getButton(String s)
    {
    	switch(s)
    	{
    		case "New" : return btnNew;
    		case "Open" : return btnOpen;
    		case "Save" : return btnSave;
    		case "Cut" : return btnCut;
    		case "Copy" : return btnCopy;
    		case "Paste" : return btnPaste;
    		case "Pointer" : return btnSelect;
    		case "Wire" : return btnWire;
    		case "Junction" : return btnJunction;
    		case "Text" : return btnText;
    		case "Reinitialiser" : return btnReinitialize;
    		case "Simulate" : return btnSimulate;
    		case "Help" : return btnHelp;
    	}
//        for(int i = 0; i < buttons.size(); i++)
//        {
//            ImageButton imagebutton = (ImageButton)buttons.elementAt(i);
//            if(imagebutton.getName().equals(s))
//                return imagebutton;
//        }

        return null;
    }

    public void EnableButton(String s)
    {
    	ToggleButton imagebutton = getButton(s);
        if(imagebutton != null)
            imagebutton.setDisable(false);
//        repaint();
    }

    public void DisableButton(String s)
    {
    	ToggleButton imagebutton = getButton(s);
        if(imagebutton != null)
            imagebutton.setDisable(true);
//        repaint();
    }

    public void SelectButton(String s)
    {
    	ToggleButton imagebutton = getButton(s);
        if(imagebutton != null)
            imagebutton.setSelected(true);
//        repaint();
    }

    public void UnselectButton(String s)
    {
    	ToggleButton imagebutton = getButton(s);
        if(imagebutton != null)
            imagebutton.setSelected(false);
//        repaint();
    }

    public void EnableAllButtons()
    {
        EnableButton("Pointer");
        EnableButton("Wire");
        EnableButton("Junction");
        EnableButton("Text");
        EnableButton("Help");
        EnableButton("Reinitialiser");
        EnableButton("Simulate");
        EnableButton("New");
        if(applet.EnableFileOperations)
        {
            EnableButton("Open");
            EnableButton("Save");
        }
        SelectButton("Pointer");
    }

//    public void DrawButtons(GraphicsContext g)
//    {
//        ButtonOffset = ((int)getWidth() - (int)CopyImage.getWidth()) / 2;
//        for(int i = 0; i < buttons.size(); i++)
//        {
//            ImageButton imagebutton = (ImageButton)buttons.elementAt(i);
//            imagebutton.Draw(g);//cig);
//        }
//
////        GraphicsContext g1 = ImageBuffer.getGraphics();
////        g1.drawImage(CopyImage, 0, 0);
////        g.drawImage(ImageBuffer, ButtonOffset, 4);
////        g.drawImage(CopyImage, ButtonOffset, 4);
//    }

//    public boolean mouseDown(Event event, int i, int j)
//    {
//        i -= ButtonOffset;
//        j -= 4;
//        for(int k = 0; k < buttons.size(); k++)
//        {
//            ImageButton imagebutton = (ImageButton)buttons.elementAt(k);
//            if(imagebutton.CheckIfPressed(i, j))
//            {
//                PressedButton = imagebutton;
//                repaint();
//                return true;
//            }
//        }
//
//        return true;
//    }
//
//    public boolean mouseUp(Event event, int i, int j)
//    {
//        if(PressedButton != null)
//        {
//            PressedButton.Select();
//            repaint();
//            ButtonPressed(PressedButton.getName());
//            PressedButton = null;
//        }
//        return true;
//    }
//
//    public synchronized void update(GraphicsContext g)
//    {
////        g.setStroke(MyColor.gray);
////        DrawWithOffset.strokeLine(g,0, 0, (int)getWidth(), 0);
////        if(!ImageButtonsDisabled)
////            DrawWithOffset.strokeLine(g,0, 30, (int)getWidth(), 30);
////        g.setStroke(MyColor.white);
////        DrawWithOffset.strokeLine(g,0, 1, (int)getWidth(), 1);
////        if(!ImageButtonsDisabled)
////            DrawWithOffset.strokeLine(g,0, 31, (int)getWidth(), 31);
////        if(CopyImage != null && !ImageButtonsDisabled)
////            DrawButtons(g);
//    }
//
//    public void repaint()
//    {
//    	paint(gc);
//    }
//
//
//    public synchronized void paint(GraphicsContext g)
//    {
//        update(g);
//    }
//
//    public void LoadButtonsImage()
//    {
////        MediaTracker mediatracker = new MediaTracker(this);
//
//        //Image image = applet.getImage(applet.getDocumentBase(), "images/allbuttons.gif");
//        ClassLoader cl = this.getClass().getClassLoader();
////        URL url = cl.getResource("images/allbuttons.gif");
//        Image image = new Image(cl.getResourceAsStream("images/allbuttons.gif"));// Toolkit.getToolkit().createImage(url);
//
//        //Image image = new Image(getClass().getClassLoader().getResource("images/allbuttons.gif"));
////        mediatracker.addImage(image, 0);
////        try
////        {
////            mediatracker.waitForAll();
////        }
////        catch(Exception exception)
////        {
////            String s1 = exception.toString();
////            String as1[] = {
////                "OK"
////            };
////            SimpleDialog simpledialog1 = new SimpleDialog(null, "Lecture de l'image des boutons", s1, as1, 1, 0, 0, 1);
////            return;
////        }
////        if(mediatracker.isErrorAny())
////        {
////            ImageButtonsDisabled = true;
////            String s = "Ne peut lire images/allbuttons.gif ImageButtons will be disabled";
////            String as[] = {
////                "OK"
////            };
////            SimpleDialog simpledialog = new SimpleDialog(null, "Erreur pendant la lecture de l'image des boutons", s, as, 1, 0, 0, 1);
////            return;
////        } else
////        {
//            ImageBuffer = image;//applet.createImage(image.getWidth(), 24);
//            CopyImage = image;//applet.createImage(image.getWidth(), image.getHeight());
////            cig = CopyImage.getGraphics();
////            cig.drawImage(image, 0, 0);
////            getGraphicsContext2D().drawImage(image, image.getWidth(), image.getHeight());
////            update(getGraphicsContext2D());
//            return;
////        }
//    }

//    public void ButtonPressed(String s)
//    {
//        if(ImageButtonsDisabled)
//            return;
//        HelpDialog helpdialog;
//        if(s.equals("Pointer"))
//        {
//            if(applet.HelpWanted)
//                helpdialog = new HelpDialog(applet.frame, "Fleche");
//            else
//                applet.UserWantsPointer();
//        } else
//        if(s.equals("Wire"))
//            applet.UserWantsWireDrawing();
//        else
//        if(s.equals("Junction"))
//            applet.UserWantsJunctionDrawing();
//        else
//        if(s.equals("Text"))
//            applet.UserWantsTextDrawing();
//        else
//        if(s.equals("Help"))
//            applet.UserWantsHelp();
//        else
//        if(s.equals("Reinitialiser"))
//            applet.UserWantsReinitialiser();
//        else
//        if(s.equals("Simulate"))
//            applet.UserWantsSimulate();
//        else
//        if(s.equals("New"))
//            applet.UserWantsNewSchematic();
//        else
//        if(s.equals("Open"))
//            applet.UserWantsOpenSchematic();
//        else
//        if(s.equals("Save"))
//            applet.UserWantsSaveSchematic();
//        else
//        if(s.equals("Copy"))
//            applet.UserWantsCopySchematic();
//        else
//        if(s.equals("Paste"))
//            applet.UserWantsPasteSchematic();
//        else
//        if(s.equals("Cut"))
//            applet.UserWantsCutSchematic();
//    }

//    Vector<ImageButton> buttons;
    DigSim applet;
//    Image ImageBuffer;
//    Image CopyImage;
//    GraphicsContext cig;
//    int ButtonOffset;
//    ImageButton PressedButton;
    boolean ImageButtonsDisabled;
//    GraphicsContext gc;

  	ToggleButton btnNew;
  	ToggleButton btnOpen;
  	ToggleButton btnSave;
  	ToggleButton btnCut;
  	ToggleButton btnCopy;
  	ToggleButton btnPaste;
  	ToggleButton btnSelect;
  	ToggleButton btnWire;
  	ToggleButton btnJunction;
  	ToggleButton btnText;
  	ToggleButton btnReinitialize;
  	ToggleButton btnSimulate;
  	ToggleButton btnHelp;
}
