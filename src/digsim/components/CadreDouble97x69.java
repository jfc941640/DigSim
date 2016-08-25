package digsim.components;

import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.MyColor;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

// Referenced classes of package digsim:
//            ElectronicComponent, MyColor, Pin

public class CadreDouble97x69 extends ElectronicComponent
{

    public CadreDouble97x69(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 157, 108, 0, 0, 0, 0);
        ComponentName = "Cadre double 97x69";
        ClassName = "CadreDouble97x69";
        RegisterPins(apin, i, j);
        CadreFont2 = Font.font("Sans-serif", 10);
    }

    public CadreDouble97x69(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        CadreFont2 = Font.font("Sans-serif", 10);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        CadreDouble97x69 cadredouble97x69 = new CadreDouble97x69(this, i, j);
        return cadredouble97x69;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = -i + 1;
        int i1 = -j + 1;
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeRect(g,l * k - 4, i1 * k - 4, 157 * k, 108 * k);
        DrawWithOffset.strokeRect(g,(l + 26) * k - 4, i1 * k - 4, 98 * k, 70 * k);
        g.setStroke(MyColor.blue);
        DrawWithOffset.strokeRect(g,l * k - 5, i1 * k - 5, 157 * k + 2, 108 * k + 2);
        DrawWithOffset.strokeRect(g,(l + 26) * k - 5, i1 * k - 5, 98 * k + 2, 70 * k + 2);
        g.setFont(CadreFont2);
        g.setFill(MyColor.blue);
        DrawWithOffset.fillText(g,"Auteur", (l + 5) * k + 2, (i1 + 1) * k + 0);
    }

    public Font CadreFont2;
}
