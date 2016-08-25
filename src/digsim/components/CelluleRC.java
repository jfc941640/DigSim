package digsim.components;

import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.InputPin;
import digsim.IntegratedCircuit;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            IntegratedCircuit, InputPin, OutputPin, MyColor,
//            Pin, ElectronicComponent

public class CelluleRC extends IntegratedCircuit
{

    public CelluleRC(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 7, 2, 0, 8, 9, 1, 1);
        Count = 0;
        SwitchClosed = false;
        MaxCount = 700;
        IPin[0] = new InputPin("", 8, -1, 0, 1, 0, 0, 0);
        OPin[0] = new OutputPin("", 11, 4, -1, 0, 0, 0, 0);
        ComponentName = "Cellule RC d'initialisation avec bouton Reset";
        ClassName = "CelluleRC";
        Can_Rotate = false;
        RegisterPins(apin, i, j);
    }

    public CelluleRC(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Count = 0;
        SwitchClosed = false;
        MaxCount = 700;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        CelluleRC cellulerc = new CelluleRC(this, i, j);
        return cellulerc;
    }

    public boolean SimMouseDown()
    {
        SwitchClosed = true;
        return true;
    }

    public boolean SimMouseUp()
    {
        SwitchClosed = false;
        return true;
    }

    public void SimulateLogic()
    {
        if(IPin[0].getLevel() == 0)
        {
            Count = Count - Math.abs(1 + 10 * (1 - Count / MaxCount));
            if(Count <= 0)
                Count = 0;
        }
        if(SwitchClosed)
        {
            Count = Count - 100;
            if(Count <= 0)
                Count = 0;
        } else
        if(!SwitchClosed && IPin[0].getLevel() == 5)
        {
            Count = Count + Math.abs(1 + 10 * (1 - Count / MaxCount));
            if(Count >= MaxCount)
                Count = MaxCount;
        }
        if(Count > MaxCount / 2)
            OPin[0].setLevel(5);
        else
            OPin[0].setLevel(0);
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeOval(g,(l + 4) * k - 2, (i1 + 5) * k, 1 * k - 4, 1 * k - 4);
        DrawWithOffset.strokeOval(g,(l + 4) * k - 2, (i1 + 7) * k - 4, 1 * k - 4, 1 * k - 4);
        if(!SwitchClosed)
        {
            DrawWithOffset.fillRect(g,(l + 3) * k, (i1 + 5) * k, 1 * k - 5, 2 * k);
            DrawWithOffset.fillRect(g,(l + 3) * k - 2, (i1 + 5) * k + 5, 1 * k - 5, 1 * k - 2);
        } else
        {
            DrawWithOffset.fillRect(g,(l + 3) * k + 4, (i1 + 5) * k, 1 * k - 5, 2 * k);
            DrawWithOffset.fillRect(g,((l + 3) * k + 4) - 2, (i1 + 5) * k + 5, 1 * k - 5, 1 * k - 2);
            if(IPin[0].getLevel() == 5)
            {
                g.setStroke(MyColor.red);
                DrawWithOffset.strokeLine(g,(l + 5) * k, (i1 + 4) * k, (l + 6) * k, (i1 + 4) * k);
                DrawWithOffset.strokeLine(g,(l + 5) * k, (i1 + 4) * k, (l + 5) * k + 1, (i1 + 4) * k + 1);
                DrawWithOffset.strokeLine(g,(l + 5) * k, (i1 + 4) * k, (l + 5) * k + 1, (i1 + 4) * k - 1);
            }
        }
        g.setStroke(Color.rgb(Math.abs((255 * Count) / MaxCount), 0, Math.abs(255 - (255 * Count) / MaxCount)));
        DrawWithOffset.fillRect(g,(l + 7) * k + 1, ((i1 + 6) * k - Math.abs((5 * Count) / MaxCount)) + 4, k * 2 - 1, (k * 1 - 5) + Math.abs((5 * Count) / MaxCount));
        DrawWithOffset.strokeLine(g,(l + 8) * k, (i1 + 3) * k, (l + 8) * k, (i1 + 5) * k + 5);
        DrawWithOffset.strokeLine(g,(l + 6) * k + 3, (i1 + 4) * k, (l + 10) * k, (i1 + 4) * k);
        DrawWithOffset.strokeOval(g,(l + 8) * k - 1, (i1 + 4) * k - 2, 1 * k - 6, 1 * k - 4);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeLine(g,(l + 8) * k, (i1 - 0) * k, (l + 8) * k, (i1 + 1) * k);
        if(SwitchClosed)
            g.setStroke(MyColor.blue);
        DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 4) * k, (l + 4) * k, (i1 + 5) * k);
        DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 4) * k, (l + 4) * k + 3, (i1 + 4) * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,(l + 8) * k - 3, (i1 + 1) * k, 1 * k - 2, 2 * k);
        DrawWithOffset.strokeRect(g,(l + 5) * k - 5, (i1 + 4) * k - 3, 2 * k, 1 * k - 2);
        g.setStroke(MyColor.blue);
        DrawWithOffset.fillRect(g,(l + 3) * k, (i1 + 7) * k + 5, k * 2 + 1, k / 2 - 3);
        DrawWithOffset.fillRect(g,(l + 3) * k + 3, (i1 + 7) * k + 7, k * 1 + 3, k / 2 - 3);
        DrawWithOffset.fillRect(g,(l + 3) * k + 6, (i1 + 7) * k + 9, k * 1 - 3, k / 2 - 3);
        g.setStroke(MyColor.blue);
        DrawWithOffset.fillRect(g,(l + 7) * k, (i1 + 7) * k + 5, k * 2 + 1, k / 2 - 3);
        DrawWithOffset.fillRect(g,(l + 7) * k + 3, (i1 + 7) * k + 7, k * 1 + 3, k / 2 - 3);
        DrawWithOffset.fillRect(g,(l + 7) * k + 6, (i1 + 7) * k + 9, k * 1 - 3, k / 2 - 3);
        g.setStroke(MyColor.black);
        DrawWithOffset.fillRect(g,(l + 7) * k, (i1 + 6) * k - 3, k * 1 - 6, k * 1);
        DrawWithOffset.fillRect(g,(l + 9) * k - 1, (i1 + 6) * k - 3, k * 1 - 6, k * 1);
        DrawWithOffset.fillRect(g,(l + 8) * k - 4, (i1 + 5) * k + 5, k * 2 - 7, k * 1 - 5);
        DrawWithOffset.fillRect(g,(l + 7) * k, (i1 + 6) * k + 5, k * 2 + 1, k * 1 - 6);
        DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 7) * k, (l + 4) * k, (i1 + 7) * k + 5);
        DrawWithOffset.strokeLine(g,(l + 8) * k, (i1 + 6) * k + 5, (l + 8) * k, (i1 + 7) * k + 5);
        g.setFill(MyColor.black);
        DrawWithOffset.fillText(g,"R", (l + 9) * k, (i1 + 3) * k - 5);
        DrawWithOffset.fillText(g,"r", (l + 5) * k + 3, (i1 + 3) * k);
        DrawWithOffset.fillText(g,"C", (l + 6) * k, (i1 + 7) * k - 2);
        DrawWithOffset.fillText(g,"BP", (l + 2) * k + 3, (i1 + 4) * k + 4);
        g.setStroke(MyColor.red);
        if(Count < MaxCount && IPin[0].getLevel() == 5)
        {
            DrawWithOffset.strokeLine(g,(l + 8) * k, (i1 + 1) * k + 4, (l + 8) * k, (i1 + 2) * k + 3);
            DrawWithOffset.strokeLine(g,(l + 8) * k, (i1 + 2) * k + 3, (l + 8) * k - 1, ((i1 + 2) * k + 3) - 1);
            DrawWithOffset.strokeLine(g,(l + 8) * k, (i1 + 2) * k + 3, (l + 8) * k + 1, ((i1 + 2) * k + 3) - 1);
        }
        if(Count > 0 && IPin[0].getLevel() == 0)
        {
            DrawWithOffset.strokeLine(g,(l + 8) * k, (i1 + 1) * k + 4, (l + 8) * k, (i1 + 2) * k + 3);
            DrawWithOffset.strokeLine(g,(l + 8) * k, (i1 + 1) * k + 3, (l + 8) * k - 1, (i1 + 1) * k + 3 + 1);
            DrawWithOffset.strokeLine(g,(l + 8) * k, (i1 + 1) * k + 3, (l + 8) * k + 1, (i1 + 1) * k + 3 + 1);
        }
    }

    int Count;
    boolean SwitchClosed;
    int MaxCount;
}
