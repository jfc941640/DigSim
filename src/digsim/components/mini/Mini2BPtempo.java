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

public class Mini2BPtempo extends ElectronicComponent
{

    public Mini2BPtempo(Pin apin[][], int i, int j)
    {
        super(i, j, 2, 2, 1, 0, 3, 3, 0, 2);
        MOUSE = 0;
        oldMOUSE = 0;
        CHRONO2 = 0L;
        INCREMENT = 100L;
        PULSE = 600L;
        DecalSortie = -2 + Math.round((int)(Math.random() * 5D));
        MaxTEMPO = 10 + Math.round((int)(Math.random() * 90D));
        TEMPO = 1 + Math.round((int)(Math.random() * (double)MaxTEMPO));
        ComponentName = "Poussoir double generateur d'impulsions decalees";
        ClassName = "Mini2BPtempo";
        Can_Rotate = false;
        OPin[0] = new OutputPin("", 5, 2, -1, 0, 0, 0, 0);
        OPin[1] = new OutputPin("", 5, 3, -1, 0, 0, 0, 0);
        RegisterPins(apin, i, j);
        OPin[0].setLevel(0);
    }

    public Mini2BPtempo(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        MOUSE = 0;
        oldMOUSE = 0;
        CHRONO2 = 0L;
        INCREMENT = 100L;
        PULSE = 600L;
        DecalSortie = -2 + Math.round((int)(Math.random() * 5D));
        MaxTEMPO = 10 + Math.round((int)(Math.random() * 90D));
        TEMPO = 1 + Math.round((int)(Math.random() * (double)MaxTEMPO));
        OPin[0].setLevel(0);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Mini2BPtempo mini2bptempo = new Mini2BPtempo(this, i, j);
        return mini2bptempo;
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
            DecalSortie = -1 + Math.round((int)(Math.random() * 3D));
            TEMPO = 1 + Math.round((int)(Math.random() * (double)MaxTEMPO));
            TEMPS1zero = TEMPS2zero = (new Date()).getTime();
        }
        TEMPS1 = (new Date()).getTime();
        TEMPS2 = (new Date()).getTime();
        CHRONO1 = TEMPS1 - TEMPS1zero;
        CHRONO2 = TEMPS2 - TEMPS2zero;
        if(DecalSortie <= -1)
        {
            if(CHRONO1 < INCREMENT)
                OPin[0].Level = 0;
            else
            if(CHRONO1 >= INCREMENT && CHRONO1 < INCREMENT + PULSE)
                OPin[0].Level = 5;
            else
                OPin[0].Level = 0;
            if(CHRONO2 < INCREMENT * (long)TEMPO)
                OPin[1].Level = 0;
            else
            if(CHRONO2 >= INCREMENT * (long)TEMPO && CHRONO2 < INCREMENT * (long)TEMPO + PULSE)
                OPin[1].Level = 5;
            else
                OPin[1].Level = 0;
        }
        if(DecalSortie == 0)
            if(CHRONO2 < INCREMENT * (long)TEMPO)
            {
                OPin[0].Level = 0;
                OPin[1].Level = 0;
            } else
            if(CHRONO2 >= INCREMENT * (long)TEMPO && CHRONO2 < INCREMENT * (long)TEMPO + PULSE)
            {
                OPin[0].Level = 5;
                OPin[1].Level = 5;
            } else
            {
                OPin[0].Level = 0;
                OPin[1].Level = 0;
            }
        if(DecalSortie >= 1)
        {
            if(CHRONO1 < INCREMENT)
                OPin[1].Level = 0;
            else
            if(CHRONO1 >= INCREMENT && CHRONO1 < INCREMENT + PULSE)
                OPin[1].Level = 5;
            else
                OPin[1].Level = 0;
            if(CHRONO2 < INCREMENT * (long)TEMPO)
                OPin[0].Level = 0;
            else
            if(CHRONO2 >= INCREMENT * (long)TEMPO && CHRONO2 < INCREMENT * (long)TEMPO + PULSE)
                OPin[0].Level = 5;
            else
                OPin[0].Level = 0;
        }
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
        DrawWithOffset.fillRect(g,(l + 1) * k - 2, i1 * k - 4, 3 * k + 4, 4 * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,(l + 1) * k - 2, i1 * k - 4, 3 * k + 4, 4 * k);
        g.setFill(MyColor.pink);
        DrawWithOffset.fillOval(g,(l + 1) * k + 1, i1 * k + 4, k, k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,(l + 1) * k + 1, i1 * k + 4, k, k);
        DrawWithOffset.strokeLine(g,(l + 1) * k + 1, (i1 + 1) * k + 4, (l + 2) * k + 1, i1 * k + 4);
        g.setFill(MyColor.red);
        DrawWithOffset.fillText(g,"+", (l + 1) * k + 3, i1 * k + 4);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeLine(g,(l + 1) * k + 5, (i1 + 1) * k + 4, (l + 1) * k + 5, (i1 + 3) * k);
        DrawWithOffset.strokeLine(g,(l + 1) * k + 6, (i1 + 2) * k, (l + 2) * k + 4, (i1 + 2) * k);
        DrawWithOffset.strokeLine(g,(l + 1) * k + 6, (i1 + 3) * k, (l + 2) * k + 4, (i1 + 3) * k);
        g.setFill(MyColor.yellow);
        DrawWithOffset.fillRect(g,(l + 1) * k + 4, (i1 + 2) * k - 1, k - 6, k - 6);
        g.setStroke(MyColor.lightGray);
        DrawWithOffset.strokeRect(g,(l + 1) * k + 4, (i1 + 2) * k - 1, k - 6, k - 6);
        if(DecalSortie <= -1)
        {
            if(CHRONO2 > INCREMENT && CHRONO2 < INCREMENT + PULSE)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 3) * k - 1, (i1 + 2) * k - 4, k - 5, k - 6);
                DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 2) * k - 2, k + 1, k - 6);
                g.setStroke(MyColor.red);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 2) * k, (l + 4) * k + 1, (i1 + 2) * k);
            } else
            if(CHRONO2 > INCREMENT)
            {
                g.setFill(MyColor.darkGray);
                DrawWithOffset.fillRect(g,(l + 3) * k - 1, (i1 + 2) * k - 6, k - 5, k - 6);
                DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 2) * k - 4, k + 1, k - 6);
                g.setStroke(MyColor.darkGray);
                g.setStroke(MyColor.blue);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 2) * k, (l + 4) * k + 1, (i1 + 2) * k);
            } else
            {
                g.setFill(MyColor.blue);
                DrawWithOffset.fillRect(g,(l + 3) * k - 1, (i1 + 2) * k - 6, k - 5, k - 6);
                DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 2) * k - 4, k + 1, k - 6);
                g.setStroke(MyColor.blue);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 2) * k, (l + 4) * k + 1, (i1 + 2) * k);
            }
            if(CHRONO2 > INCREMENT * (long)TEMPO && CHRONO2 < INCREMENT * (long)TEMPO + PULSE)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 3) * k - 1, (i1 + 3) * k - 4, k - 5, k - 6);
                DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 3) * k - 2, k + 1, k - 6);
                g.setStroke(MyColor.red);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 3) * k, (l + 4) * k + 1, (i1 + 3) * k);
            } else
            if(CHRONO2 > INCREMENT * (long)TEMPO)
            {
                g.setFill(MyColor.darkGray);
                DrawWithOffset.fillRect(g,(l + 3) * k - 1, (i1 + 3) * k - 6, k - 5, k - 6);
                DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 3) * k - 4, k + 1, k - 6);
                g.setStroke(MyColor.darkGray);
                g.setStroke(MyColor.blue);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 3) * k, (l + 4) * k + 1, (i1 + 3) * k);
            } else
            {
                g.setFill(MyColor.blue);
                DrawWithOffset.fillRect(g,(l + 3) * k - 1, (i1 + 3) * k - 6, k - 5, k - 6);
                DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 3) * k - 4, k + 1, k - 6);
                g.setStroke(MyColor.blue);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 3) * k, (l + 4) * k + 1, (i1 + 3) * k);
            }
        }
        if(DecalSortie == 0)
            if(CHRONO2 > INCREMENT * (long)TEMPO && CHRONO2 < INCREMENT * (long)TEMPO + PULSE)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 3) * k - 1, (i1 + 2) * k - 4, k - 5, k - 6);
                DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 2) * k - 2, k + 1, k - 6);
                DrawWithOffset.fillRect(g,(l + 3) * k - 1, (i1 + 3) * k - 4, k - 5, k - 6);
                DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 3) * k - 2, k + 1, k - 6);
                g.setStroke(MyColor.red);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 2) * k, (l + 4) * k + 1, (i1 + 2) * k);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 3) * k, (l + 4) * k + 1, (i1 + 3) * k);
            } else
            if(CHRONO2 > INCREMENT * (long)TEMPO)
            {
                g.setFill(MyColor.darkGray);
                DrawWithOffset.fillRect(g,(l + 3) * k - 1, (i1 + 2) * k - 6, k - 5, k - 6);
                DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 2) * k - 4, k + 1, k - 6);
                DrawWithOffset.fillRect(g,(l + 3) * k - 1, (i1 + 3) * k - 6, k - 5, k - 6);
                DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 3) * k - 4, k + 1, k - 6);
                g.setStroke(MyColor.blue);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 2) * k, (l + 4) * k + 1, (i1 + 2) * k);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 3) * k, (l + 4) * k + 1, (i1 + 3) * k);
            } else
            {
                g.setFill(MyColor.blue);
                DrawWithOffset.fillRect(g,(l + 3) * k - 1, (i1 + 2) * k - 6, k - 5, k - 6);
                DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 2) * k - 4, k + 1, k - 6);
                DrawWithOffset.fillRect(g,(l + 3) * k - 1, (i1 + 3) * k - 6, k - 5, k - 6);
                DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 3) * k - 4, k + 1, k - 6);
                g.setStroke(MyColor.blue);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 2) * k, (l + 4) * k + 1, (i1 + 2) * k);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 3) * k, (l + 4) * k + 1, (i1 + 3) * k);
            }
        if(DecalSortie >= 1)
        {
            if(CHRONO2 > INCREMENT * (long)TEMPO && CHRONO2 < INCREMENT * (long)TEMPO + PULSE)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 3) * k - 1, (i1 + 2) * k - 4, k - 5, k - 6);
                DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 2) * k - 2, k + 1, k - 6);
                g.setStroke(MyColor.red);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 2) * k, (l + 4) * k + 1, (i1 + 2) * k);
            } else
            if(CHRONO2 > INCREMENT * (long)TEMPO)
            {
                g.setFill(MyColor.darkGray);
                DrawWithOffset.fillRect(g,(l + 3) * k - 1, (i1 + 2) * k - 6, k - 5, k - 6);
                DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 2) * k - 4, k + 1, k - 6);
                g.setStroke(MyColor.darkGray);
                g.setStroke(MyColor.blue);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 2) * k, (l + 4) * k + 1, (i1 + 2) * k);
            } else
            {
                g.setFill(MyColor.blue);
                DrawWithOffset.fillRect(g,(l + 3) * k - 1, (i1 + 2) * k - 6, k - 5, k - 6);
                DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 2) * k - 4, k + 1, k - 6);
                g.setStroke(MyColor.blue);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 2) * k, (l + 4) * k + 1, (i1 + 2) * k);
            }
            if(CHRONO2 > INCREMENT && CHRONO2 < INCREMENT + PULSE)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillRect(g,(l + 3) * k - 1, (i1 + 3) * k - 4, k - 5, k - 6);
                DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 3) * k - 2, k + 1, k - 6);
                g.setStroke(MyColor.red);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 3) * k, (l + 4) * k + 1, (i1 + 3) * k);
            } else
            if(CHRONO2 > INCREMENT)
            {
                g.setFill(MyColor.darkGray);
                DrawWithOffset.fillRect(g,(l + 3) * k - 1, (i1 + 3) * k - 6, k - 5, k - 6);
                DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 3) * k - 4, k + 1, k - 6);
                g.setStroke(MyColor.blue);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 3) * k, (l + 4) * k + 1, (i1 + 3) * k);
            } else
            {
                g.setFill(MyColor.blue);
                DrawWithOffset.fillRect(g,(l + 3) * k - 1, (i1 + 3) * k - 6, k - 5, k - 6);
                DrawWithOffset.fillRect(g,(l + 3) * k - 4, (i1 + 3) * k - 4, k + 1, k - 6);
                g.setStroke(MyColor.blue);
                DrawWithOffset.strokeLine(g,(l + 3) * k + 4, (i1 + 3) * k, (l + 4) * k + 1, (i1 + 3) * k);
            }
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
    long TEMPS1;
    long TEMPS1zero;
    long TEMPS2;
    long TEMPS2zero;
    long CHRONO1;
    long CHRONO2;
    long INCREMENT;
    long PULSE;
    int Angle;
    int DecalSortie;
    int MaxTEMPO;
    int TEMPO;
}
