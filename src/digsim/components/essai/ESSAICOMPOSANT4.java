package digsim.components.essai;

import java.util.Calendar;

import digsim.DrawWithOffset;
import digsim.MyColor;
import digsim.Pin;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            ElectronicComponent, MyColor, Pin

public class ESSAICOMPOSANT4 extends ElectronicComponent
{

    public ESSAICOMPOSANT4(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 0, 0, 5, 2, 0, 0);
        ComponentName = "Pendule";
        ClassName = "ESSAICOMPOSANT4";
        RegisterPins(apin, i, j);
    }

    public ESSAICOMPOSANT4(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        ESSAICOMPOSANT4 essaicomposant4 = new ESSAICOMPOSANT4(this, i, j);
        return essaicomposant4;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFill(MyColor.blue);
        Calendar calendar = Calendar.getInstance();
        hh = calendar.get(11);
        mm = calendar.get(12);
        ss = calendar.get(13);
        String s = Integer.toString(hh);
        if(hh <= 9)
            s = "0" + s;
        String s1 = Integer.toString(mm);
        if(mm <= 9)
            s1 = "0" + s1;
        String s2 = Integer.toString(ss);
        if(ss <= 9)
            s2 = "0" + s2;
        DrawWithOffset.fillText(g,s + ":" + s1 + ":" + s2, (l + 0) * k, (i1 + 1) * k + 5);
    }

    public void SimulateLogic()
    {
    }

    public void Simulate(int i)
    {
        InformConnectedComponents(i);
    }

    public void InitBeforeSimulate()
    {
    }

    int hh;
    int mm;
    int ss;
}
