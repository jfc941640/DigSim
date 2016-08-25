package digsim.components.mini;


import java.util.Date;

import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;

// Referenced classes of package digsim:
//            ElectronicComponent, OutputPin, MyColor, Pin

public class Mini1BPtempo extends ElectronicComponent
{

    public Mini1BPtempo(Pin apin[][], int i, int j)
    {
        super(i, j, 2, 2, 1, 0, 3, 2, 0, 1);
        MOUSE = 0;
        oldMOUSE = 0;
        CHRONO2 = 0L;
        INCREMENT = 100L;
        PULSE = 600L;
        MaxTEMPO = 1 + Math.round((int)(Math.random() * 100D));
        TEMPO = 1 + Math.round((int)(Math.random() * (double)MaxTEMPO));
        ComponentName = "Poussoir simple generateur d'impulsion decalee";
        ClassName = "Mini1BPtempo";
        Can_Rotate = false;
        OPin[0] = new OutputPin("", 5, 2, -1, 0, 0, 0, 0);
        RegisterPins(apin, i, j);
        OPin[0].setLevel(0);
    }

    public Mini1BPtempo(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        MOUSE = 0;
        oldMOUSE = 0;
        CHRONO2 = 0L;
        INCREMENT = 100L;
        PULSE = 600L;
        MaxTEMPO = 1 + Math.round((int)(Math.random() * 100D));
        TEMPO = 1 + Math.round((int)(Math.random() * (double)MaxTEMPO));
        OPin[0].setLevel(0);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Mini1BPtempo mini1bptempo = new Mini1BPtempo(this, i, j);
        return mini1bptempo;
    }

    public boolean SimMouseDown()
    {
        if(CHRONO2 > INCREMENT * (long)TEMPO + PULSE)
            if(MOUSE == 5)
                MOUSE = 0;
            else
                MOUSE = 5;
        return true;
    }

    public void Simulate(int i)
    {
        if(oldMOUSE != MOUSE)
        {
            TEMPO = 1 + Math.round((int)(Math.random() * (double)MaxTEMPO));
            TEMPS2zero = (new Date()).getTime();
        }
        TEMPS2 = (new Date()).getTime();
        CHRONO2 = TEMPS2 - TEMPS2zero;
        if(CHRONO2 < INCREMENT * (long)TEMPO)
            OPin[0].Level = 0;
        else
        if(CHRONO2 >= INCREMENT * (long)TEMPO && CHRONO2 < INCREMENT * (long)TEMPO + PULSE)
            OPin[0].Level = 5;
        else
            OPin[0].Level = 0;
        oldMOUSE = MOUSE;
        InformConnectedComponents(i);
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFont(Font.font("Sans-serif", 12));
        DrawOutputPins(g, l, i1, k);
        g.setFill(Color.rgb(224, 224, 224));
        DrawWithOffset.fillRect(g,(l + 1) * k - 2, i1 * k - 4, 3 * k + 4, 3 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,(l + 1) * k - 2, i1 * k - 4, 3 * k + 4, 3 * k);
        g.setFill(MyColor.pink);
        DrawWithOffset.fillOval(g,(l + 1) * k + 1, i1 * k + 4, k, k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,(l + 1) * k + 1, i1 * k + 4, k, k);
        DrawWithOffset.strokeLine(g,(l + 1) * k + 1, (i1 + 1) * k + 4, (l + 2) * k + 1, i1 * k + 4);
        g.setFill(MyColor.red);
        DrawWithOffset.fillText(g,"+", (l + 1) * k + 3, i1 * k + 4);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeLine(g,(l + 1) * k + 5, (i1 + 1) * k + 4, (l + 1) * k + 5, (i1 + 2) * k);
        DrawWithOffset.strokeLine(g,(l + 1) * k + 6, (i1 + 2) * k, (l + 2) * k + 4, (i1 + 2) * k);
        g.setFill(MyColor.yellow);
        DrawWithOffset.fillRect(g,(l + 1) * k + 4, (i1 + 2) * k - 1, k - 6, k - 6);
        g.setStroke(MyColor.lightGray);
        DrawWithOffset.strokeRect(g,(l + 1) * k + 4, (i1 + 2) * k - 1, k - 6, k - 6);
        if(CHRONO2 > INCREMENT * (long)TEMPO && CHRONO2 < INCREMENT * (long)TEMPO + PULSE)
        {
            g.setFill(MyColor.red);
            DrawWithOffset.fillRect(g,(l + 3) * k - 1, (i1 + 2) * k - 4, k - 5, k - 6);
            DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 2) * k - 2, k + 1, k - 6);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 2) * k, (l + 4) * k + 1, (i1 + 2) * k);
        } else
        if(CHRONO2 > INCREMENT * (long)TEMPO)
        {
            g.setFill(MyColor.darkGray);
            DrawWithOffset.fillRect(g,(l + 3) * k - 1, (i1 + 2) * k - 6, k - 5, k - 6);
            DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 2) * k - 4, k + 1, k - 6);
            g.setStroke(MyColor.blue);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 2) * k, (l + 4) * k + 1, (i1 + 2) * k);
        } else
        {
            g.setFill(MyColor.blue);
            DrawWithOffset.fillRect(g,(l + 3) * k - 1, (i1 + 2) * k - 6, k - 5, k - 6);
            DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 2) * k - 4, k + 1, k - 6);
            DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 2) * k, (l + 4) * k + 1, (i1 + 2) * k);
        }
        Angle = (int)Math.min(360L, (360L * CHRONO2) / (INCREMENT * (long)TEMPO + PULSE));
        if(Angle != 360)
        {
            if(CHRONO2 < INCREMENT * (long)TEMPO)
                g.setFill(MyColor.gray);
            else
                g.setFill(MyColor.white);
            DrawWithOffset.fillArc(g,(l + 3) * k + 1, i1 * k - 3, k * 1, k * 1, 90, -Angle, ArcType.OPEN);
        } else
        {
            g.setFill(Color.rgb(224, 224, 224));
            DrawWithOffset.fillArc(g,(l + 3) * k + 1, i1 * k - 3, k * 1, k * 1, 90, -360, ArcType.OPEN);
        }
        g.setStroke(MyColor.lightGray);
        DrawWithOffset.strokeArc(g,(l + 3) * k + 1, i1 * k - 3, k * 1, k * 1, 90, -360, ArcType.OPEN);
    }

    public void InitBeforeSimulate()
    {
        OPin[0].InitBeforeSimulate();
    }

    int MOUSE;
    int oldMOUSE;
    long TEMPS2;
    long TEMPS2zero;
    long CHRONO2;
    long INCREMENT;
    long PULSE;
    int Angle;
    int MaxTEMPO;
    int TEMPO;
}
