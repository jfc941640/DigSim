package digsim.components;

import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            ElectronicComponent, OutputPin, MyColor, Pin

public class Colorieur extends ElectronicComponent
{

    public Colorieur(Pin apin[][], int i, int j)
    {
        super(i, j, 1, 1, 1, 1, 0, 0, 0, 1);
        OPin[0] = new OutputPin("Colorieur", 1, 1, 0, 0, 0, 0, 2);
        ComponentName = "Colorieur";
        ClassName = "Colorieur";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public Colorieur(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Colorieur colorieur = new Colorieur(this, i, j);
        colorieur.OPin[0].Level = 0x21e891;
        return colorieur;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawOutputPins(g, l, i1, k);
        if(NbRotation == 0)
        {
            OPin[0].Level = 0x21e88e;
            g.setStroke(MyColor.red);
            DrawWithOffset.fillRect(g,(l + 1) * k - 1, (i1 + 1) * k - 1, k - 5, k - 5);
        } else
        if(NbRotation == 1)
        {
            OPin[0].Level = 0x21e893;
            g.setStroke(MyColor.magenta);
            DrawWithOffset.fillRect(g,(l + 1) * k - 1, (i1 + 1) * k - 1, k - 5, k - 5);
        } else
        if(NbRotation == 2)
        {
            OPin[0].Level = 0x21e894;
            g.setStroke(MyColor.gray);
            DrawWithOffset.fillRect(g,(l + 1) * k - 1, (i1 + 1) * k - 1, k - 5, k - 5);
        } else
        {
            OPin[0].Level = 0x21e890;
            g.setStroke(MyColor.yellow);
            DrawWithOffset.fillRect(g,(l + 1) * k - 1, (i1 + 1) * k - 1, k - 5, k - 5);
        }
    }

    public void Simulate(int i)
    {
        InformConnectedComponents(i);
    }
}
