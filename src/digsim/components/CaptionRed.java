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

public class CaptionRed extends ElectronicComponent
{

    public CaptionRed(int i, int j, String s)
    {
        super(i, j, 1, 1, 0, 1, 1, 1, 0, 0);
        Text = s;
        ComponentName = "TexteRouge";
        ClassName = "CaptionRed";
        CaptionRedFont = Font.font("Sans-serif", 10);
    }

    public CaptionRed(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        CaptionRed captionred = new CaptionRed(this, i, j);
        captionred.Text = Text;
        captionred.CaptionRedFont = CaptionRedFont;
        return captionred;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFont(CaptionRedFont);
        FontMetrics fontmetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(CaptionRedFont);
        int j1 = (int) fontmetrics.getLineHeight();
        int k1 = (int) fontmetrics.computeStringWidth(Text);
        HitBoxSize.width = Dim.width = k1 / k;
        HitBoxSize.height = Dim.height = j1 / k;
        super.draw(g, i, j, k);
        g.setFill(CaptionRedColor);
        DrawWithOffset.fillText(g,Text, l * k + 2, i1 * k + 16);
    }

    public void Save(PrintStream printstream)
    {
        printstream.println("describe component CaptionRed");
        printstream.println(" pos " + Pos.x + " " + Pos.y);
        printstream.println(" Text " + Text);
        printstream.println("end describe");
    }

    public String ExportAsScript()
    {
        String s = new String("[|CaptionRed|pos|" + Pos.x + "|" + Pos.y + "|Text|" + Text + "|]\r\n^");
        return s;
    }

    static Color CaptionRedColor;
    public String Text;
    public Font CaptionRedFont;

    static
    {
        CaptionRedColor = MyColor.red;
    }
}
