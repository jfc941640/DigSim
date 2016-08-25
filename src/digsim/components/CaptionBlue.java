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

public class CaptionBlue extends ElectronicComponent
{

    public CaptionBlue(int i, int j, String s)
    {
        super(i, j, 1, 1, 0, 1, 1, 1, 0, 0);
        Text = s;
        ComponentName = "TexteBleu";
        ClassName = "CaptionBlue";
        CaptionBlueFont = Font.font("Sans-serif", 10);
    }

    public CaptionBlue(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        CaptionBlue captionblue = new CaptionBlue(this, i, j);
        captionblue.Text = Text;
        captionblue.CaptionBlueFont = CaptionBlueFont;
        return captionblue;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFont(CaptionBlueFont);
        FontMetrics fontmetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(CaptionBlueFont);
        int j1 = (int) fontmetrics.getLineHeight();
        int k1 = (int) fontmetrics.computeStringWidth(Text);
        HitBoxSize.width = Dim.width = k1 / k;
        HitBoxSize.height = Dim.height = j1 / k;
        super.draw(g, i, j, k);
        g.setFill(CaptionBlueColor);
        DrawWithOffset.fillText(g,Text, l * k + 2, i1 * k + 16);
    }

    public void Save(PrintStream printstream)
    {
        printstream.println("describe component CaptionBlue");
        printstream.println(" pos " + Pos.x + " " + Pos.y);
        printstream.println(" Text " + Text);
        printstream.println("end describe");
    }

    public String ExportAsScript()
    {
        String s = new String("[|CaptionBlue|pos|" + Pos.x + "|" + Pos.y + "|Text|" + Text + "|]\r\n^");
        return s;
    }

    static Color CaptionBlueColor;
    public String Text;
    public Font CaptionBlueFont;

    static
    {
        CaptionBlueColor = MyColor.blue;
    }
}
