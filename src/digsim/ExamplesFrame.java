package digsim;


import java.io.DataInputStream;
import java.io.StringBufferInputStream;
import java.util.Vector;

import digsim.Example;
import digsim.SimpleDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// Referenced classes of package digsim:
//            Example, SimpleDialog, DigSim

public class ExamplesFrame extends Stage
{

    public ExamplesFrame(DigSim digsim)
    {
//        super("Exemples de DigSim");
        WaitMessage = "Téléchargement des exemples.";
        applet = digsim;
//        setBackground(Color.rgb(200, 200, 200));
        Button button = new Button("OK");
        button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
                int i = l.getSelectionModel().getSelectedIndex();
                if(i == -1)
                {
//                    return true;
                } else
                {
                    String s = getFileName(l.getSelectionModel().getSelectedItem());
                    //setVisible(false);
                    close();
                    applet.UserWantsOpenExample(s);
                    applet.MyExamplesFrame = null;
//                    return true;
                }


			}
		});
        Button button1 = new Button("Annuler");
        button.setDisable(true);
        ExamplesVector = new Vector();
        FlowPane panel = new FlowPane();
        c = new ListView<String>();
        items =FXCollections.observableArrayList (
        	    "La liste des simulations");
        c.setItems(items);
        panel.getChildren().add(c);
        panel.getChildren().add(button);
        panel.getChildren().add(button1);
        GridPane panel1 = new GridPane();
//        panel1.setLayout(new GridLayout(0, 1));
        l = new ListView<String>();//30, false);
        itemsMsg =FXCollections.observableArrayList (
        	    WaitMessage);
        l.setItems(itemsMsg);
        panel1.getChildren().add(l);
        BorderPane bp = new BorderPane();
        bp.setTop(panel1);
        bp.setBottom(panel);
        bp.setPrefSize(600, 600);
        show();
        LoadExamples();
        ShowCategorys();
        if(items.size() > 0)
            ShowExamples(items.get(0));
        l.getSelectionModel().select(0);
        button.setDisable(false);
        panel.layout();

        Group root = new Group();
        Scene scene = new Scene(root, 600, 600);
//        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            public void handle(KeyEvent event) {
//            	keyDown(event);
//            }
//        });
        setScene(scene);
        root.getChildren().addAll(bp);

    }

    public void ShowExamples(String s)
    {
        if(ExamplesVector.size() == 0)
            return;
        itemsMsg.clear();
        //l.getItem().delItems(0, l.getItem().length() - 1);
        for(int i = 0; i < ExamplesVector.size(); i++)
        {
            Example example = (Example)ExamplesVector.elementAt(i);
            if(s.equals(example.getType()) || s.equals("La liste des simulations"))
                //l.addItem(example.getDescription());
            	itemsMsg.add(example.getDescription());
        }

    }

    public void ShowCategorys()
    {
        if(ExamplesVector.size() == 0)
            return;
        for(int i = 0; i < ExamplesVector.size(); i++)
        {
            Example example = (Example)ExamplesVector.elementAt(i);
            boolean flag = false;
            for(int j = 0; j < items.size(); j++)
                if(items.get(j).equals(example.getType()))
                    flag = true;

            if(!flag)
                items.add(example.getType());
        }

    }

    public void LoadExamples()
    {
        String s1 = null;
        String s2 = null;
        String s3 = null;
        while(applet.TextFileRequested != null)
            try
            {
                Thread.currentThread();
                Thread.sleep(250L);
            }
            catch(Exception exception) { }
        applet.RequestedTextFileRead = false;
        applet.RequestedTextFileError = false;
        applet.TextFileRequested = "examples/_description.txt";
        show();
        do
            try
            {
//                Thread.currentThread();
//                Thread.sleep(250L);

            	applet.openExample();

            }
            catch(Exception exception1) { }
        while(!applet.RequestedTextFileRead && !applet.RequestedTextFileError);
        if(applet.RequestedText == null || applet.RequestedTextFileError)
        {
            applet.TextFileRequested = null;
            String as[] = {
                "OK"
            };
            String s = "Impossible de lire le fichier examples/_description.txt";
            SimpleDialog simpledialog = new SimpleDialog(null, "Chargement des exemples", s, as, 1, 0, 0, 1);
            return;
        }
        String s4 = applet.RequestedText;
        applet.TextFileRequested = null;
        StringBufferInputStream stringbufferinputstream = new StringBufferInputStream(s4);
        DataInputStream datainputstream = new DataInputStream(stringbufferinputstream);
        do
        {
            try
            {
                s1 = null;
                s2 = null;
                s3 = null;
                s1 = datainputstream.readLine();
                if(s1 != null)
                    s2 = datainputstream.readLine();
                if(s1 != null && s2 != null)
                    s3 = datainputstream.readLine();
            }
            catch(Exception exception2) { }
            if(s1 != null && s2 != null && s3 != null)
            {
                Example example = new Example(s1, s2, s3);
                ExamplesVector.addElement(example);
            }
        } while(s1 != null && s2 != null && s3 != null);
    }

    public boolean handleEvent(Event event)
    {
//        if(event.id == 201)
//        {
            //setVisible(false);
        	close();
            applet.MyExamplesFrame = null;
            return true;
//        } else
//        {
//            return super.handleEvent(event);
//        }
    }

    public String getFileName(String s)
    {
        for(int i = 0; i < ExamplesVector.size(); i++)
        {
            Example example = (Example)ExamplesVector.elementAt(i);
            if(s.equals(example.getDescription()))
                return example.getLocation();
        }

        return null;
    }

    public boolean action(Event event, Object obj)
    {
        if(true)//event.target instanceof Button)
        {
            String s2 = (String)obj;
            if(s2.equals("Annuler"))
            {
                //setVisible(false);
            	close();
                applet.MyExamplesFrame = null;
                return true;
            }
            if(s2.equals("OK"))
            {
            }
        } else
        {
            if(true)//event.target instanceof Choice)
            {
                ShowExamples((String)obj);
                return true;
            }
            if(true)//event.target instanceof java.awt.List)
                if(WaitMessage.equals((String)obj))
                {
                    return true;
                } else
                {
                    String s1 = getFileName((String)obj);
                    //setVisible(false);
                    close();
                    applet.UserWantsOpenExample(s1);
                    applet.MyExamplesFrame = null;
                    return true;
                }
        }
        return false;
    }

    DigSim applet;
    ListView<String> c;
    ListView<String> l;
    Vector ExamplesVector;
    String WaitMessage;
    ObservableList<String> items;
    ObservableList<String> itemsMsg;
}
