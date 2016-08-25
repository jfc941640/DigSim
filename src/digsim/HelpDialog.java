package digsim;

import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;

import digsim.DigSimFrame;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// Referenced classes of package digsim:
//            DigSimFrame, DigSim

public class HelpDialog extends Stage
{

    public HelpDialog(DigSimFrame digsimframe, String s)
    {
//        super(digsimframe, "Aide : " + s, false);
        frame = digsimframe;
        digsimframe.applet.UserWantsPointer();
        String s1 = s.replace(' ', '_');
        s1 = s1.replace('-', '_');
        HelpDialogFont = Font.font("Monospaced", 13);
        HelpDialogFontMetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(HelpDialogFont);
//        setFont(HelpDialogFont);

        Group root = new Group();
        Scene scene = new Scene(root, 680, 430);
        setScene(scene);
        BorderPane bp = new BorderPane();
        root.getChildren().add(bp);

        FlowPane panel = new FlowPane();
        Button button = new Button("OK");
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                close();
             }
         });
        panel.getChildren().add(button);
        bp.setBottom(panel);
        bp.setTop(HelpTextArea = new TextArea("Lecture du fichier d'aide"));//, 20, 100));
        HelpTextArea.setEditable(true);
//        setBounds(40, 40, 680, 430);
        while(frame.applet.TextFileRequested != null)
            try
            {
                Thread.currentThread();
                Thread.sleep(250L);
            }
            catch(Exception exception) { }
        frame.applet.RequestedTextFileRead = false;
        frame.applet.RequestedTextFileError = false;
        frame.applet.TextFileRequested = "help/" + s1 + ".txt";
        frame.applet.openExample();
        show();
//        do
//            try
//            {
//                Thread.currentThread();
//                Thread.sleep(250L);
//            }
//            catch(Exception exception1) { }
//        while(!frame.applet.RequestedTextFileRead && !frame.applet.RequestedTextFileError);
        HelpTextArea.setText(frame.applet.RequestedText);
        frame.applet.TextFileRequested = null;
    }

    public void paint(GraphicsContext g)
    {
    }

    public boolean handleEvent(Event event)
    {
//        if(event.id == 201)
//        {
//            setVisible(false);
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
//            setVisible(false);
            return true;
//        } else
//        {
//            return false;
//        }
    }

    protected Font HelpDialogFont;
    protected FontMetrics HelpDialogFontMetrics;
    String Capt;
    int CaptWidth;
    DigSimFrame frame;
    TextArea HelpTextArea;
}
