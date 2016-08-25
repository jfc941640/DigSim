package digsim;

import java.awt.Dimension;

import digsim.DialogPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// Referenced classes of package digsim:
//            DialogPanel, DigSimFrame

public class SimpleDialog extends Stage
{

    public SimpleDialog(DigSimFrame digsimframe, String s, String s1, String buttonsText[], int buttonsNb, int j, int dialogId,
            int imageId)
    {
        //super(s);
        frame = null;
        MyDialogPanel = null;
        int i1 = 0;
        DialogID = dialogId;
        frame = digsimframe;
//        setBackground(Color.rgb(200, 200, 200));
        BorderPane bp = new BorderPane();
        bp.setCenter(MyDialogPanel = new DialogPanel(digsimframe, s1, imageId));
        FlowPane panel = new FlowPane();
        for(int j1 = 0; j1 < buttonsNb; j1++)
        {
        	Button btn = new Button(buttonsText[j1]);
        	btn.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
	              if(frame != null)
	            	  frame.action(e, "SIMPLEDIALOG_" + btn.getText() + "_" + DialogID);
                  close();
               }
           });

            panel.getChildren().add(btn);
        }

        bp.setBottom(panel);
        if(MyDialogPanel != null)
        {
            i1 = 50 + MyDialogPanel.CaptWidth;
        }
        if(i1 < 200)
            i1 = 200;
        if(imageId != 0)
            i1 += 50;
//        setBounds(40, 40, i1 + 150, 125);
//        show();
        repaint();

      Group root = new Group();
      Scene scene = new Scene(root, i1 + 150, 175);
//      scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//          public void handle(KeyEvent event) {
//          	keyDown(event);
//          }
//      });
      setScene(scene);
      root.getChildren().add(bp);
      show();

    }

    public void repaint()
    {
    	paint(null);  //OK
    }


    public void paint(GraphicsContext g)
    {
        if(MyDialogPanel != null)
            MyDialogPanel.repaint();
    }

//    public Dimension preferredSize()
//    {
//        if(MyDialogPanel != null)
//            return MyDialogPanel.preferredSize();
//        else
//            return new Dimension(200, 125);
//    }

//    public boolean handleEvent(Event event)
//    {
//        if(event.id == 201)
//        {
//            setVisible(false);
//            if(frame != null)
//                return frame.action(event, "SIMPLEDIALOG_" + DefaultButton + "_" + DialogID);
//        }
//        return super.handleEvent(event);
//    }
//
//    public boolean action(Event event, Object obj)
//    {
//        if(event.target instanceof Button)
//        {
//            String s = (String)obj;
//            setVisible(false);
//            if(frame != null)
//                return frame.action(event, "SIMPLEDIALOG_" + s + "_" + DialogID);
//        }
//        return false;
//    }

    DigSimFrame frame;
    int DialogID;
    String DefaultButton;
    DialogPanel MyDialogPanel;
    static final int IMAGE_NONE = 0;
    static final int IMAGE_STOP = 1;
    static final int IMAGE_WARNING = 2;
}
