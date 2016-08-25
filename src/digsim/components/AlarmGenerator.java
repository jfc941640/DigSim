package digsim.components;

import java.util.Calendar;

import digsim.DigSim;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.InputPin;
import digsim.MyColor;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, MyColor, DigSim,
//            Pin

public class AlarmGenerator extends ElectronicComponent
{

    public AlarmGenerator(Pin apin[][], int i, int j)
    {
        super(i, j, 8, 8, 3, 3, 2, 4, 1, 0);
        PeriodeLampe = 500;
        ComponentName = "Alarme";
        ClassName = "AlarmGenerator";
        Can_Rotate = false;
        IPin[0] = new InputPin("E", 1, 5, 2, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
        IPin[0].setLevel(0);
        IPin[0].OldLevel = 0;
    }

    public AlarmGenerator(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        PeriodeLampe = 500;
        IPin[0].setLevel(0);
        IPin[0].OldLevel = 0;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        AlarmGenerator alarmgenerator = new AlarmGenerator(this, i, j);
        return alarmgenerator;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        Calendar calendar = Calendar.getInstance();
        DrawInputPins(g, l, i1, k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,(l + 3) * k, (i1 + 4) * k, 1 * k, 2 * k);
        g.setStroke(MyColor.blue);
        DrawWithOffset.strokeLine(g,(l + 3) * k + 5, (i1 + 6) * k, (l + 3) * k + 5, (i1 + 7) * k);
        DrawWithOffset.fillRect(g,(l + 2) * k + 5, (i1 + 7) * k, k * 2 + 1, k / 2 - 3);
        DrawWithOffset.fillRect(g,(l + 2) * k + 3 + 5, (i1 + 7) * k + 2, k * 1 + 3, k / 2 - 3);
        DrawWithOffset.fillRect(g,(l + 2) * k + 6 + 5, (i1 + 7) * k + 4, k * 1 - 3, k / 2 - 3);
        DrawWithOffset.fillRect(g,(l + 3) * k, (i1 + 4) * k, 1 * k, 2 * k);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 4) * k, (l + 5) * k, (i1 + 3) * k);
        DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 6) * k, (l + 5) * k, (i1 + 7) * k);
        DrawWithOffset.strokeLine(g,(l + 5) * k, (i1 + 3) * k, (l + 5) * k, (i1 + 7) * k);
        if((IPin[0].getLevel() == 5) & (calendar.get(14) % PeriodeLampe < PeriodeLampe / 2))
        {
            g.setFill(MyColor.red);
            DrawWithOffset.fillRect(g,(l + 3) * k, (i1 + 4) * k, 1 * k, 2 * k);
        }
    }

    public void SimulateLogic()
    {
        if(IPin[0].getLevel() == 5 && IPin[0].OldLevel == 0)
            DigSim.alarm.play();
        IPin[0].OldLevel = IPin[0].getLevel();
    }

    public void Simulate(int i)
    {
        InformConnectedComponents(i);
    }

    int PeriodeLampe;
}