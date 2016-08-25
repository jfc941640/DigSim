package digsim;

import java.util.Vector;

import com.sun.javafx.tk.Toolkit;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

// Referenced classes of package digsim:
//            AnalyzerPanel, DigSim, Schematic, SchematicPanel

public class AnalyzerFrame extends Stage
{

    public AnalyzerFrame(DigSim digsim)
    {
        //super("Analyseur logique (raccourci clavier : a )");
        MyAnalyzerPanel = null;
        applet = digsim;
//        BorderPane bp = new BorderPane();
//        bp.setCenter(MyAnalyzerPanel = new AnalyzerPanel(digsim, this));
//        Toolkit toolkit = Toolkit.getToolkit();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//        Dimension dimension = toolkit.getScreenSize();
//        int i = dimension.width;
//        int j = dimension.height;
        Vector vector = applet.MySchematic.getProbes();
        int k = 50 * vector.size();
//        setBounds(0, j - 70 - k, i, 50 + k);
        double y = primaryScreenBounds.getHeight()-70-k;
        setX(primaryScreenBounds.getMinX());
        setY(y);
        setWidth(primaryScreenBounds.getWidth());
        setHeight(50+k);

        Group root = new Group();
        Scene scene = new Scene(root);//, primaryScreenBounds.getWidth(), 50+k);
//        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            public void handle(KeyEvent event) {
//            	keyDown(event);
//            }
//        });
        setScene(scene);
        root.getChildren().addAll(MyAnalyzerPanel = new AnalyzerPanel(digsim, this));//bp);

        show();
    }

	public void repaint() {
		paint(null);
	}

	public void paint(GraphicsContext g)
    {
        if(MyAnalyzerPanel != null)
            MyAnalyzerPanel.repaint();
    }

    public boolean handleEvent(Event event)
    {
//        if(event.id == 201)
//        {
            close();
            applet.MyAnalyzerFrame = null;
            return true;
//        } else
//        {
//            return super.handleEvent(event);
//        }
    }

    DigSim applet;
    public AnalyzerPanel MyAnalyzerPanel;
    SchematicPanel MySchematicPanel;
}