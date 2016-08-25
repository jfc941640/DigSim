package digsim.components;

import java.io.PrintStream;

import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;

import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.MyColor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

// Referenced classes of package digsim:
//            ElectronicComponent, MyColor

public class CaptionGreen extends ElectronicComponent
{

    public CaptionGreen(int i, int j, String s)
    {
        super(i, j, 1, 1, 0, 1, 1, 1, 0, 0);
        Text = s;
        ComponentName = "TexteVert";
        ClassName = "CaptionGreen";
        CaptionGreenFont = Font.font("Sans-serif", 10);
    }

    public CaptionGreen(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        CaptionGreen captiongreen = new CaptionGreen(this, i, j);
        captiongreen.Text = Text;
        captiongreen.CaptionGreenFont = CaptionGreenFont;
        return captiongreen;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFont(CaptionGreenFont);
        FontMetrics fontmetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(CaptionGreenFont);
        int j1 = (int) fontmetrics.getLineHeight();
        int k1 = (int) fontmetrics.computeStringWidth(Text);
        HitBoxSize.width = Dim.width = k1 / k;
        HitBoxSize.height = Dim.height = j1 / k;
        super.draw(g, i, j, k);
        g.setFill(Color.rgb(0, 192, 0));
        DrawWithOffset.fillText(g,Text, l * k + 2, i1 * k + 16);
    }

    public void Save(PrintStream printstream)
    {
        printstream.println("describe component CaptionGreen");
        printstream.println(" pos " + Pos.x + " " + Pos.y);
        printstream.println(" Text " + Text);
        printstream.println("end describe");
    }

    public String ExportAsScript()
    {
        String s = new String("[|CaptionGreen|pos|" + Pos.x + "|" + Pos.y + "|Text|" + Text + "|]\r\n^");
        return s;
    }

    static Color CaptionGreenColor;
    public String Text;
    public Font CaptionGreenFont;

    static
    {
        CaptionGreenColor = MyColor.green;
    }
}
