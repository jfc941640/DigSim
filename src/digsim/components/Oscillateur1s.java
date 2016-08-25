package digsim.components;


import java.util.Calendar;

import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

// Referenced classes of package digsim:
//            ElectronicComponent, OutputPin, MyColor, Pin

public class Oscillateur1s extends ElectronicComponent
{

    public Oscillateur1s(Pin apin[][], int i, int j)
    {
        super(i, j, 8, 6, 1, 1, 4, 4, 0, 1);
        ComponentName = "Horloge 1s";
        ClassName = "Oscillateur1s";
        Can_Rotate = true;
        OPin[0] = new OutputPin("Q", 7, 3, -2, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
        OPin[0].Level = 0;
        FonteComposant = Font.font("Serif", 10);
    }

    public Oscillateur1s(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        OPin[0].Level = 0;
        FonteComposant = Font.font("Serif", 10);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Oscillateur1s oscillateur1s = new Oscillateur1s(this, i, j);
        return oscillateur1s;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawOutputPins(g, l, i1, k);
        g.setFont(FonteComposant);
        g.setFill(Color.rgb(255, 255, 102));
        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, k * 4, k * 4);
        g.setStroke(MyColor.lightGray);
        DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k, (l + 2) * k, (i1 + 4) * k);
        DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 2) * k, (l + 3) * k, (i1 + 4) * k);
        DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 2) * k, (l + 4) * k, (i1 + 4) * k);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k, (l + 3) * k, (i1 + 2) * k);
        if(Temps == 0)
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k - 1, (l + 3) * k, (i1 + 2) * k - 1);
        g.setStroke(MyColor.blue);
        DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 4) * k, (l + 4) * k, (i1 + 4) * k);
        if(Temps == 1)
            DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 4) * k + 1, (l + 4) * k, (i1 + 4) * k + 1);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,(l + 1) * k, (i1 + 1) * k, k * 4, k * 4);
        Calendar calendar = Calendar.getInstance();
        Millis = calendar.get(14);
        Temps = Math.round((int)Millis / 500) % 2;
        g.setFill(MyColor.black);
        DrawWithOffset.fillText(g,"1 s", (l + 2) * k + 3, (i1 + 3) * k + 5);
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
        OPin[0].InitBeforeSimulate();
        OPin[0].Level = 5 * Temps;
    }

    double Millis;
    int Temps;
    public Font FonteComposant;
}
