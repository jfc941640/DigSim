package digsim.components;

import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.MyColor;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

// Referenced classes of package digsim:
//            ElectronicComponent, MyColor, Pin

public class CadreDouble80x70 extends ElectronicComponent
{

    public CadreDouble80x70(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 157, 108, 0, 0, 0, 0);
        ComponentName = "Cadre double 80x70";
        ClassName = "CadreDouble80x70";
        RegisterPins(apin, i, j);
        CadreFont2 = Font.font("Sans-serif", 10);
    }

    public CadreDouble80x70(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        CadreFont2 = Font.font("Sans-serif", 10);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        CadreDouble80x70 cadredouble80x70 = new CadreDouble80x70(this, i, j);
        return cadredouble80x70;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = -i + 1;
        int i1 = -j + 1;
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeRect(g,l * k - 4, i1 * k - 4, 157 * k, 108 * k);
        DrawWithOffset.strokeRect(g,(l + 38) * k - 4, i1 * k - 4, 81 * k, 71 * k);
        g.setStroke(MyColor.blue);
        DrawWithOffset.strokeRect(g,l * k - 5, i1 * k - 5, 157 * k + 2, 108 * k + 2);
        DrawWithOffset.strokeRect(g,(l + 38) * k - 5, i1 * k - 5, 81 * k + 2, 71 * k + 2);
        g.setFont(CadreFont2);
        g.setFill(MyColor.blue);
        DrawWithOffset.fillText(g,"Auteur", (l + 5) * k + 2, (i1 + 1) * k + 0);
    }

    public Font CadreFont2;
}
