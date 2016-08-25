package digsim;

import java.awt.Dimension;

import com.sun.javafx.tk.Toolkit;

import javafx.event.Event;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

// Referenced classes of package digsim:
//            DigSim

public class OptionsFrame extends Stage
{

    public OptionsFrame(DigSim digsim)
    {
//        super("Options (touche o )");
        applet = digsim;
        GridPane g = new GridPane();
        g.setHgap(5);
        g.setVgap(5);
        g.getColumnConstraints().setAll(
                //new ColumnConstraints(75, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE),
                new ColumnConstraints(75, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE)
                );
        g.getColumnConstraints().get(0).setHgrow(Priority.ALWAYS);
        //panel.getColumnConstraints().get(1).setHgrow(Priority.ALWAYS);

        g.getRowConstraints().setAll(
                new RowConstraints(25, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE),
                new RowConstraints(25, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE),
                new RowConstraints(25, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE),
                new RowConstraints(25, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE),
                new RowConstraints(25, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
        g.getRowConstraints().get(0).setVgrow(Priority.NEVER);
        g.getRowConstraints().get(1).setVgrow(Priority.NEVER);
        g.getRowConstraints().get(1).setVgrow(Priority.NEVER);
        g.getRowConstraints().get(1).setVgrow(Priority.NEVER);
        g.getRowConstraints().get(2).setVgrow(Priority.ALWAYS);

        //setBackground(Color.rgb(200, 200, 200));
//        setLayout(new GridLayout(5, 1, 5, 5));
        int i = applet.SimulationSpeed;
        slider = new Slider(0, 100, i);
        slider.setBlockIncrement(10);
        BorderPane panel = new BorderPane();
        panel.setCenter(new Label("Coeff. de ralentissement : " + Integer.toString(i)));//, 1
        panel.setLeft(new Label("min"));//, 0
        panel.setRight(new Label("max"));//, 2
        panel.setBottom(slider);
        GridPane.setConstraints(panel, 0, 0);
        g.getChildren().add(panel);
        BorderPane panel1 = new BorderPane();
        panel1.setTop(ShortCircuitCheckbox = new CheckBox("Arrêter la simulation en cas de court-circuit"));
        if(applet.StopAtShortCircuit)
            ShortCircuitCheckbox.setSelected(true);
        GridPane.setConstraints(panel1, 1, 0);
        g.getChildren().add(panel1);
        BorderPane panel2 = new BorderPane();
        panel2.setTop(LoopCheckbox = new CheckBox("Arrêter la simulation en cas de boucle"));
        if(applet.StopAtLoop)
            LoopCheckbox.setSelected(true);
        GridPane.setConstraints(panel2, 2, 0);
        g.getChildren().add(panel2);
        BorderPane panel3 = new BorderPane();
        panel3.getChildren().add(AnalyzerCheckbox = new CheckBox("Lancement auto. de l'analyseur logique"));
        if(applet.AnalyzerAutoPopUp)
            AnalyzerCheckbox.setSelected(true);
        GridPane.setConstraints(panel3, 3, 0);
        g.getChildren().add(panel3);
        FlowPane panel4 = new FlowPane();
        panel4.getChildren().add(new Button("OK"));
        panel4.getChildren().add(new Button("Défaut"));
        panel4.getChildren().add(new Button("Annuler"));
        GridPane.setConstraints(panel4, 4, 0);
        g.getChildren().add(panel4);
        Toolkit toolkit = Toolkit.getToolkit();
        Rectangle2D dimension = Screen.getPrimary().getVisualBounds();
        double f = dimension.getWidth();
        double f1 = dimension.getHeight();

        Group root = new Group();
        Scene scene = new Scene(root, 845, 200);
        setScene(scene);
        root.getChildren().add(g);

        if(f <= 800F)
            g.setPrefSize(255, 210);
        else
        if(f == 1024F)
        {
            setX(800);
            setY(20);
            g.setPrefSize(224, 210);
        }
        else
        if(f < 1200F)
        {
            setX(900);
            setY(20);
            g.setPrefSize(255, 210);
        }
        else
        {
            setX(1024);
            setY(20);
            g.setPrefSize(255, 210);
        }
        show();
    }

    public boolean handleEvent(Event event)
    {
//        if(event.id == 201)
//        {
            close();
            applet.MyOptionsFrame = null;
            return true;
//        } else
//        {
//            return super.handleEvent(event);
//        }
    }

    public void SetDefaultValues()
    {
        slider.setValue(10);
        ShortCircuitCheckbox.setSelected(false);
        LoopCheckbox.setSelected(false);
        AnalyzerCheckbox.setSelected(false);
//        repaint();
    }

    public void SetNewValues()
    {
        int i = (int)slider.getValue();
        applet.SimulationSpeed = i;
        applet.StopAtShortCircuit = ShortCircuitCheckbox.isSelected();
        applet.StopAtLoop = LoopCheckbox.isSelected();
        applet.AnalyzerAutoPopUp = AnalyzerCheckbox.isSelected();
//        repaint();
    }

    public boolean action(Event event, Object obj)
    {
//        if(event.target instanceof Button)
//        {
            String s = (String)obj;
            if(obj.equals("Défaut"))
            {
                SetDefaultValues();
                return true;
            }
            if(obj.equals("Annuler"))
            {
                close();
                applet.MyOptionsFrame = null;
                return true;
            }
            if(obj.equals("OK"))
            {
                SetNewValues();
                close();
                applet.MyOptionsFrame = null;
                return true;
            }
//        }
        return false;
    }

    DigSim applet;
    Slider slider;
    static final int MIN_SPEED = 0;
    static final int MAX_SPEED = 100;
    static final int PAGE_SIZE = 10;
    CheckBox ShortCircuitCheckbox;
    CheckBox LoopCheckbox;
    CheckBox AnalyzerCheckbox;
}
