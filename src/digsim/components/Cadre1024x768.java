package digsim.components;

import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.MyColor;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

// Referenced classes of package digsim:
//            ElectronicComponent, MyColor, Pin

public class Cadre1024x768 extends ElectronicComponent
{

    public Cadre1024x768(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 124, 78, 0, 0, 0, 0);
        ComponentName = "Cadre 1024x768";
        ClassName = "Cadre1024x768";
        RegisterPins(apin, i, j);
        CadreFont1 = Font.font("Sans-serif", 10);
    }

    public Cadre1024x768(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        CadreFont1 = Font.font("Sans-serif", 10);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Cadre1024x768 cadre1024x768 = new Cadre1024x768(this, i, j);
        return cadre1024x768;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = -i + 1;
        int i1 = -j + 1;
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeRect(g,l * k - 4, i1 * k - 4, 124 * k, 78 * k);
        g.setStroke(MyColor.blue);
        DrawWithOffset.strokeRect(g,l * k - 5, i1 * k - 5, 124 * k + 2, 78 * k + 2);
        g.setFont(CadreFont1);
        g.setFill(MyColor.blue);
        DrawWithOffset.fillText(g,"Auteur", (l + 5) * k + 2, (i1 + 1) * k + 0);
    }

    public Font CadreFont1;
}
