package digsim;

import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;

import digsim.DigSimFrame;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// Referenced classes of package digsim:
//            Schematic, DigSimFrame, DigSim, SchematicPanel

public class ScriptFrame extends Stage
{

    public ScriptFrame(DigSimFrame digsimframe, int i)
    {
//        super("Script DigSim ''RailWay'' 2012.2.0");
        Script = "# Digsim file version ''RailWay'' 2012.2.0\r\n^\r\n^\r\n#eof Digsim\r\n";
        frame = digsimframe;
        ScriptFrameType = i;
//        setBackground(Color.rgb(200, 200, 200));
        digsimframe.applet.UserWantsPointer();
        ScriptFrameFont = Font.font("Sans-serif", 12);
        ScriptFrameFontMetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(ScriptFrameFont);
//        setFont(ScriptFrameFont);
        BorderPane bp = new BorderPane();
        bp.setPrefSize(500, 500);

        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);
//        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            public void handle(KeyEvent event) {
//            	keyDown(event);
//            }
//        });
        setScene(scene);
        root.getChildren().addAll(bp);

        FlowPane panel = new FlowPane();
        Button button = new Button("      OK      ");
        button.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
	              if(frame != null)
	            	  action(null, null);
//                  close();
               }
           });
        panel.getChildren().add(button);
        bp.setBottom(panel);
        bp.setTop(ScriptFrameTextArea = new TextArea(Script));//, 20, 100, 1));
        ScriptFrameTextArea.setFont(ScriptFrameFont);
//        setBounds(40, 40, 500, 450);
        if(ScriptFrameType == IMPORT)
            ScriptFrameTextArea.setEditable(true);
        else
        if(ScriptFrameType == EXPORT)
            ScriptFrameTextArea.setEditable(false);
//        pack();
        show();
    }

    public void setScript(String s)
    {
        Script = new String(s);
        ScriptFrameTextArea.setText(Script);
    }

    public String getScript()
    {
        return Script;
    }

    public boolean handleEvent(Event event)
    {
//        if(event.id == 201)
//        {
            close();
            frame.applet.MyScriptFrame = null;
            return true;
//        } else
//        {
//            return super.handleEvent(event);
//        }
    }

    public boolean action(Event event, Object obj)
    {
//        if(event.target instanceof Button)
//        {
            if(ScriptFrameType == IMPORT)
            {
                Script = new String(ScriptFrameTextArea.getText());
                if(Script.equals(""))
                {
                    close();
                    frame.applet.MyScriptFrame = null;
                    return false;
                }
                frame.applet.MySchematic.DestroyComponents(frame.applet.PinGrid);
                frame.applet.InitPinGrids();
                frame.applet.MySchematic = new Schematic(frame, frame.applet.PinGrid, Script);
                frame.applet.MySchematicPanel.repaint();
            }
            frame.applet.UserWantsPointer();
            close();
            frame.applet.MyScriptFrame = null;
            return true;
//        } else
//        {
//            return false;
//        }
    }

    DigSimFrame frame;
    TextArea ScriptFrameTextArea;
    protected Font ScriptFrameFont;
    protected FontMetrics ScriptFrameFontMetrics;
    protected String Script;
    int ScriptFrameType;
    static int IMPORT = 0;
    static int EXPORT = 1;

}
