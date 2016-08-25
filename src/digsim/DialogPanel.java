package digsim;

import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.net.URL;

import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;

import digsim.DigSimFrame;
import digsim.MyColor;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// Referenced classes of package digsim:
//            MyColor, DigSimFrame, DigSim

public class DialogPanel extends Pane
    //implements ImageObserver
{

    public DialogPanel(DigSimFrame digsimframe, String s, int i)
    {
        frame = null;
        ImageToDisplay = null;
        imgw = 0;
        imgh = 0;
        frame = digsimframe;
        Capt = s;
        getImage(i);
//        setLayout(new BorderLayout());
        SimpleDialogFont = Font.font("Serif", 13);
        SimpleDialogFontMetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(SimpleDialogFont);
//        super.setLayout(new BorderLayout());
        CaptWidth = (int) SimpleDialogFontMetrics.computeStringWidth(s);
//        setBackground(Color.rgb(200, 200, 200));
//        repaint();

        Canvas canvas = new Canvas(50 + CaptWidth, 125);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.rgb(200, 200, 200));
        gc.fill();
        gc.setFont(SimpleDialogFont);
        getChildren().add(canvas);
        repaint();

    }

    public void repaint()
    {
    	paint(gc);
    }


    public void paint(GraphicsContext g)
    {
        if(ImageToDisplay != null)
        {
            g.drawImage(ImageToDisplay, 20, 10);
            g.setFill(MyColor.black);
            int i = ((int)getWidth() - CaptWidth) / 2 + imgw + 20;
            if(i < imgw + 50)
                i = imgw + 50;
            DrawWithOffset.fillText(g,Capt, i, 30);
        } else
        {
            g.setFill(MyColor.black);
            int j = ((int)getWidth() - CaptWidth) / 2;
            DrawWithOffset.fillText(g,Capt, j, 30);
        }
    }

    public void getImage(int i)
    {
        String s = null;
        switch(i)
        {
        case 1: // '\001'
            s = "images/stop.gif";
            break;

        case 2: // '\002'
            s = "images/warning.gif";
            break;

        case 0: // '\0'
        default:
            return;
        }
        if(frame != null)
        {
            //ImageToDisplay = frame.applet.getImage(frame.applet.getDocumentBase(), s);
            ClassLoader cl = this.getClass().getClassLoader();
            ImageToDisplay = new Image(cl.getResourceAsStream(s));;
        }
    }

    public synchronized boolean imageUpdate(Image image, int i, int j, int k, int l, int i1)
    {
        boolean flag = true;
        boolean flag1 = false;
        long l1 = 0L;
        if((i & 1) != 0)
        {
            imgw = l;
            flag1 = true;
        }
        if((i & 2) != 0)
        {
            imgh = i1;
            flag1 = true;
        }
        if((i & 0x30) != 0)
        {
            flag1 = true;
            flag = false;
        } else
        if((i & 8) != 0)
        {
            flag1 = true;
            l1 = 100L;
        }
        if((i & 0x40) != 0)
            flag = false;
//        if(flag1)
//            repaint(l1);
        return flag;
    }

    DigSimFrame frame;
    static final int HORIZONTAL_GAP = 50;
    protected Font SimpleDialogFont;
    protected FontMetrics SimpleDialogFontMetrics;
    String Capt;
    int CaptWidth;
    Image ImageToDisplay;
    int imgw;
    int imgh;
    static final long updateRate = 100L;
    GraphicsContext gc;
}
