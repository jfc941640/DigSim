package digsim.components;

import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.MyColor;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

// Referenced classes of package digsim:
//            ElectronicComponent, MyColor, Pin

public class Cadre1280x1024 extends ElectronicComponent
{

    public Cadre1280x1024(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 157, 108, 0, 0, 0, 0);
        ComponentName = "Cadre 1280x1024";
        ClassName = "Cadre1280x1024";
        RegisterPins(apin, i, j);
        CadreFont2 = Font.font("Sans-serif", 10);
    }

    public Cadre1280x1024(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        CadreFont2 = Font.font("Sans-serif", 10);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Cadre1280x1024 cadre1280x1024 = new Cadre1280x1024(this, i, j);
        return cadre1280x1024;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = -i + 1;
        int i1 = -j + 1;
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeRect(g,l * k - 4, i1 * k - 4, 157 * k, 108 * k);
        g.setStroke(MyColor.blue);
        DrawWithOffset.strokeRect(g,l * k - 5, i1 * k - 5, 157 * k + 2, 108 * k + 2);
        g.setFont(CadreFont2);
        g.setFill(MyColor.blue);
        DrawWithOffset.fillText(g,"Auteur", (l + 5) * k + 2, (i1 + 1) * k + 0);
    }

    public Font CadreFont2;
}
