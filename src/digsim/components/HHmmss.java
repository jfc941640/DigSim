package digsim.components;


import java.util.Calendar;

import digsim.MyColor;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

// Referenced classes of package digsim:
//            ElectronicComponent, MyColor, Pin

public class HHmmss extends ElectronicComponent
{

    public HHmmss(Pin apin[][], int i, int j)
    {
        super(i, j, 0, 0, 0, 0, 5, 2, 0, 0);
        ComponentName = "Pendule";
        ClassName = "HHmmss";
        RegisterPins(apin, i, j);
    }

    public HHmmss(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        HHmmss hhmmss = new HHmmss(this, i, j);
        return hhmmss;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        Calendar calendar = Calendar.getInstance();
        hh = calendar.get(11);
        mm = calendar.get(12);
        ss = calendar.get(13);
        year = calendar.get(1);
        month = 1 + calendar.get(2);
        date = calendar.get(5);
        g.setFill(MyColor.blue);
        g.setFont(Font.font("Sans-serif", 10));
        String s = Integer.toString(hh);
        if(hh <= 9)
            s = "0" + s;
        String s1 = Integer.toString(mm);
        if(mm <= 9)
            s1 = "0" + s1;
        String s2 = Integer.toString(ss);
        if(ss <= 9)
            s2 = "0" + s2;
        DrawWithOffset.fillText(g,date + "-" + month + "-" + year, (l + 0) * k - 3, (i1 + 1) * k);
        DrawWithOffset.fillText(g,s + ":" + s1 + ":" + s2, (l + 0) * k, (i1 + 2) * k);
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
        Calendar calendar = Calendar.getInstance();
        hh = calendar.get(11);
        mm = calendar.get(12);
        ss = calendar.get(13);
        year = calendar.get(1);
        month = calendar.get(2);
        date = calendar.get(5);
    }

    int hh;
    int mm;
    int ss;
    int year;
    int month;
    int date;
}
