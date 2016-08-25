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

public class CaptionRdP extends ElectronicComponent
{

    public CaptionRdP(int i, int j, String s)
    {
        super(i, j, 1, 1, 0, 1, 1, 1, 0, 0);
        Text = s;
        ComponentName = "Texte gris (RdP)  (g)";
        ClassName = "CaptionRdP";
        CaptionRdPFont = Font.font("Sans-serif", 10);
    }

    public CaptionRdP(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        CaptionRdP captionrdp = new CaptionRdP(this, i, j);
        captionrdp.Text = Text;
        captionrdp.CaptionRdPFont = CaptionRdPFont;
        return captionrdp;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFont(CaptionRdPFont);
        FontMetrics fontmetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(CaptionRdPFont);
        int j1 = (int) fontmetrics.getLineHeight();
        int k1 = (int) fontmetrics.computeStringWidth(Text);
        HitBoxSize.width = Dim.width = k1 / k;
        HitBoxSize.height = Dim.height = j1 / k;
        super.draw(g, i, j, k);
        g.setFill(CaptionRdPColor);
        DrawWithOffset.fillText(g,Text, l * k - 1, i1 * k + 20);
    }

    public void Save(PrintStream printstream)
    {
        printstream.println("describe component CaptionRdP");
        printstream.println(" pos " + Pos.x + " " + Pos.y);
        printstream.println(" Text " + Text);
        printstream.println("end describe");
    }

    public String ExportAsScript()
    {
        String s = new String("[|CaptionRdP|pos|" + Pos.x + "|" + Pos.y + "|Text|" + Text + "|]\r\n^");
        return s;
    }

    static Color CaptionRdPColor;
    public String Text;
    public Font CaptionRdPFont;

    static
    {
        CaptionRdPColor = MyColor.darkGray;
    }
}
