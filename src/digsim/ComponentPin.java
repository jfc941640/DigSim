package digsim;


import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Vector;
import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;

import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            ElectronicComponent, MyColor

public class ComponentPin
{

    public ComponentPin(ComponentPin componentpin)
    {
        Level = -1;
        OldLevel = -1;
        Name = componentpin.Name;
        PinPos = new Point(componentpin.PinPos.x, componentpin.PinPos.y);
        PinDim = new Dimension(componentpin.PinDim.width, componentpin.PinDim.height);
        Flags = componentpin.Flags;
        TextXoffs = componentpin.TextXoffs;
        TextYoffs = componentpin.TextYoffs;
        PinFont = Font.font("Sans-serif", 9);
    }

    public ComponentPin(String s, int i, int j, int k, int l, int i1, int j1,
            int k1)
    {
        Level = -1;
        OldLevel = -1;
        Name = s;
        TextXoffs = i1;
        TextYoffs = j1;
        PinPos = new Point(i, j);
        PinDim = new Dimension(k, l);
        Flags = k1;
        PinFont = Font.font("Sans-serif", 9);
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        byte byte0 = 0;
        g.setStroke(PinColor);
        if((Flags & 1) > 0 || (Flags & 0x40) > 0)
        {
            if(PinDim.height == 0)
            {
                if(PinDim.width > 0)
                {
                    DrawWithOffset.strokeLine(g,(i + PinPos.x) * k, (j + PinPos.y) * k, ((i + PinPos.x + PinDim.width) - 1) * k + 1, (j + PinPos.y + PinDim.height) * k);
                    g.setStroke(ElectronicComponent.ComponentColor);
                    DrawWithOffset.strokeOval(g,((i + PinPos.x + PinDim.width) - 1) * k + 2, (j + PinPos.y) * k - 3, k - 2, k - 2);
                } else
                {
                    DrawWithOffset.strokeLine(g,(i + PinPos.x) * k, (j + PinPos.y) * k, (i + PinPos.x + PinDim.width + 1) * k - 1, (j + PinPos.y + PinDim.height) * k);
                    g.setStroke(ElectronicComponent.ComponentColor);
                    DrawWithOffset.strokeOval(g,(i + PinPos.x + PinDim.width) * k, (j + PinPos.y) * k - 3, k - 2, k - 2);
                }
            } else
            if(PinDim.height < 0)
            {
                DrawWithOffset.strokeLine(g,(i + PinPos.x) * k, (j + PinPos.y) * k, (i + PinPos.x) * k, (j + PinPos.y + PinDim.height + 1) * k - 1);
                g.setStroke(ElectronicComponent.ComponentColor);
                DrawWithOffset.strokeOval(g,(i + PinPos.x) * k - 3, (j + PinPos.y + PinDim.height) * k, k - 2, k - 2);
            } else
            {
                DrawWithOffset.strokeLine(g,(i + PinPos.x) * k, (j + PinPos.y) * k, (i + PinPos.x) * k, ((j + PinPos.y + PinDim.height) - 1) * k + 1);
                g.setStroke(ElectronicComponent.ComponentColor);
                DrawWithOffset.strokeOval(g,(i + PinPos.x) * k - 3, ((j + PinPos.y + PinDim.height) - 1) * k + 2, k - 2, k - 2);
            }
        } else
        {
            DrawWithOffset.strokeLine(g,(i + PinPos.x) * k, (j + PinPos.y) * k, (i + PinPos.x + PinDim.width) * k, (j + PinPos.y + PinDim.height) * k);
        }
        if((Flags & 4) > 0)
        {
            byte0 = 4;
            g.setStroke(ElectronicComponent.ComponentColor);
            if(PinDim.width > 0)
            {
                DrawWithOffset.strokeLine(g,(i + PinPos.x + PinDim.width) * k + 4, (j + PinPos.y) * k, (i + PinPos.x + PinDim.width) * k, (j + PinPos.y) * k - 4);
                DrawWithOffset.strokeLine(g,(i + PinPos.x + PinDim.width) * k + 4, (j + PinPos.y) * k, (i + PinPos.x + PinDim.width) * k, (j + PinPos.y) * k + 4);
            } else
            if(PinDim.width < 0)
            {
                DrawWithOffset.strokeLine(g,(i + PinPos.x + PinDim.width) * k, (j + PinPos.y) * k - 4, (i + PinPos.x + PinDim.width) * k - 4, (j + PinPos.y) * k);
                DrawWithOffset.strokeLine(g,(i + PinPos.x + PinDim.width) * k, (j + PinPos.y) * k + 4, (i + PinPos.x + PinDim.width) * k - 4, (j + PinPos.y) * k);
            } else
            if(PinDim.height < 0)
            {
                DrawWithOffset.strokeLine(g,(i + PinPos.x) * k, (j + PinPos.y + PinDim.height) * k - 4, (i + PinPos.x) * k - 4, (j + PinPos.y + PinDim.height) * k);
                DrawWithOffset.strokeLine(g,(i + PinPos.x) * k, (j + PinPos.y + PinDim.height) * k - 4, (i + PinPos.x) * k + 4, (j + PinPos.y + PinDim.height) * k);
            } else
            if(PinDim.height > 0)
            {
                DrawWithOffset.strokeLine(g,(i + PinPos.x) * k, (j + PinPos.y + PinDim.height) * k + 4, (i + PinPos.x) * k - 4, (j + PinPos.y + PinDim.height) * k);
                DrawWithOffset.strokeLine(g,(i + PinPos.x) * k, (j + PinPos.y + PinDim.height) * k + 4, (i + PinPos.x) * k + 4, (j + PinPos.y + PinDim.height) * k);
            }
        }
        if((Flags & 0x20) > 0)
        {
            byte0 = 4;
            g.setStroke(ElectronicComponent.ComponentColor);
            if(PinDim.width > 0)
            {
                DrawWithOffset.strokeLine(g,(i + PinPos.x) * k + 9, (j + PinPos.y) * k - 3, (i + PinPos.x) * k + 9, (j + PinPos.y) * k + 3);
                DrawWithOffset.strokeLine(g,(i + PinPos.x) * k + 10, (j + PinPos.y) * k - 2, (i + PinPos.x) * k + 10, (j + PinPos.y) * k + 2);
            } else
            if(PinDim.width < 0)
            {
                DrawWithOffset.strokeLine(g,((i + PinPos.x) - 2) * k + 7, (j + PinPos.y) * k - 3, ((i + PinPos.x) - 2) * k + 7, (j + PinPos.y) * k + 3);
                DrawWithOffset.strokeLine(g,((i + PinPos.x) - 2) * k + 6, (j + PinPos.y) * k - 2, ((i + PinPos.x) - 2) * k + 6, (j + PinPos.y) * k + 2);
            } else
            if(PinDim.height < 0)
            {
                DrawWithOffset.strokeLine(g,(i + PinPos.x) * k - 3, ((j + PinPos.y) - 2) * k + 7, (i + PinPos.x) * k + 3, ((j + PinPos.y) - 2) * k + 7);
                DrawWithOffset.strokeLine(g,(i + PinPos.x) * k - 2, ((j + PinPos.y) - 2) * k + 6, (i + PinPos.x) * k + 2, ((j + PinPos.y) - 2) * k + 6);
            } else
            if(PinDim.height > 0)
            {
                DrawWithOffset.strokeLine(g,(i + PinPos.x) * k - 3, (j + PinPos.y) * k + 9, (i + PinPos.x) * k + 3, (j + PinPos.y) * k + 9);
                DrawWithOffset.strokeLine(g,(i + PinPos.x) * k - 2, (j + PinPos.y) * k + 10, (i + PinPos.x) * k + 2, (j + PinPos.y) * k + 10);
            }
        }
        if((Flags & 2) == 0)
        {
        	PinFontMetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(PinFont);
            int l = (int) PinFontMetrics.computeStringWidth(Name);
            int i1 = (int) PinFontMetrics.getLineHeight();
            g.setFill(PinTextColor);
            g.setFont(PinFont);
            if(PinDim.width > 0)
                DrawWithOffset.fillText(g,Name, (i + PinPos.x + PinDim.width + TextXoffs) * k + 2 + byte0, (j + PinPos.y + TextYoffs) * k + 4);
            else
            if(PinDim.width < 0)
                DrawWithOffset.fillText(g,Name, (i + PinPos.x + PinDim.width + TextXoffs) * k - l - byte0 - 1, (j + PinPos.y + TextYoffs) * k + 4);
            else
            if(PinDim.height > 0)
                DrawWithOffset.fillText(g,Name, (i + PinPos.x + TextXoffs) * k - l / 2, (j + PinPos.y + PinDim.height + 1 + TextYoffs) * k + byte0);
            else
            if(PinDim.height < 0)
                DrawWithOffset.fillText(g,Name, (i + PinPos.x + TextXoffs) * k - l / 2, ((j + PinPos.y + PinDim.height) - TextYoffs) * k);
        }
    }

    public int getLevel()
    {
        if(((Flags & 1) == 0) & ((Flags & 0x40) == 0))
            return Level;
        if(Level == 5)
            return 0;
        if(Level == 0)
            return 5;
        else
            return Level;
    }

    public int getOldLevel()
    {
        if(((Flags & 1) == 0) & ((Flags & 0x40) == 0))
            return OldLevel;
        if(OldLevel == 5)
            return 0;
        if(OldLevel == 0)
            return 5;
        else
            return OldLevel;
    }

    public void setLevel(int i)
    {
        OldLevel = Level;
        Level = i;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String s)
    {
        Name = s;
    }

    protected Vector ConnComps;
    public int Level;
    public int OldLevel;
    public Point PinPos;
    public Dimension PinDim;
    protected String Name;
    protected int Flags;
    protected int TextXoffs;
    protected int TextYoffs;
    public static Color PinColor;
    static Color PinTextColor;
    static final int PIN_NORMAL = 0;
    static final int PIN_NEGATIVE = 1;
    static final int PIN_TEXT_INVISIBLE = 2;
    static final int PIN_EDGETRIGGERED = 4;
    static final int PIN_NOACTION = 8;
    static final int PIN_EDGETRIGMASQUEE = 16;
    static final int PIN_EDGETRIGGEREDGRAS = 32;
    static final int PIN_NEGATIVE_NOACTION = 64;
    public Font PinFont;
    public FontMetrics PinFontMetrics;

    static
    {
        PinColor = MyColor.black;
        PinTextColor = MyColor.darkGray;
    }
}
