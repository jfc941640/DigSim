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
import javafx.scene.text.FontWeight;

// Referenced classes of package digsim:
//            ElectronicComponent, MyColor

public class CaptionBlack extends ElectronicComponent
{

    public CaptionBlack(int i, int j, String s)
    {
        super(i, j, 1, 1, 0, 1, 1, 1, 0, 0);
        Text = s;
        ComponentName = "Texte noir  (n)";
        ClassName = "CaptionBlack";
        CaptionBlackFont = Font.font("Sans-serif", 10);
    }

    public CaptionBlack(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        CaptionBlack captionblack = new CaptionBlack(this, i, j);
        captionblack.Text = Text;
        captionblack.CaptionBlackFont = CaptionBlackFont;
        return captionblack;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFont(CaptionBlackFont);
        FontMetrics fontmetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(CaptionBlackFont);
        int j1 = (int) fontmetrics.getLineHeight();
        int k1 = (int) fontmetrics.computeStringWidth(Text);
        HitBoxSize.width = Dim.width = k1 / k;
        HitBoxSize.height = Dim.height = j1 / k;
        super.draw(g, i, j, k);
        g.setFill(CaptionBlackColor);
        DrawWithOffset.fillText(g,Text, l * k + 2, i1 * k + 16);
    }

    public void Save(PrintStream printstream)
    {
        printstream.println("describe component CaptionBlack");
        printstream.println(" pos " + Pos.x + " " + Pos.y);
        printstream.println(" Text " + Text);
        printstream.println("end describe");
    }

    public String ExportAsScript()
    {
        String s = new String("[|CaptionBlack|pos|" + Pos.x + "|" + Pos.y + "|Text|" + Text + "|]\r\n^");
        return s;
    }

    static Color CaptionBlackColor;
    public String Text;
    public Font CaptionBlackFont;

    static
    {
        CaptionBlackColor = MyColor.black;
    }
}
