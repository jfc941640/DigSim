package digsim.components;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Vector;

import digsim.ComponentPin;
import digsim.DrawWithOffset;
import digsim.MyColor;
import digsim.Pin;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            ElectronicComponent, ComponentPin, MyColor, Pin

public class Switch extends ElectronicComponent
{

    public Switch(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 5, 3, 1, 4, 4, 0, 0);
        SwitchClosed = false;
        ALevel = -1;
        BLevel = -1;
        QLevel = -1;
        ComponentName = "Contact R (type repos)";
        ClassName = "Switch";
        Can_Rotate = true;
        APos = new Point(1, 2);
        BPos = new Point(1, 4);
        QPos = new Point(9, 3);
        RegisterPins(apin, i, j);
    }

    public Switch(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        SwitchClosed = false;
        ALevel = -1;
        BLevel = -1;
        QLevel = -1;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Switch switch1 = new Switch(this, i, j);
        switch1.NbRotation = NbRotation;
        switch1.updatePinsPosition();
        return switch1;
    }

    public boolean SimMouseDown()
    {
        SwitchClosed = !SwitchClosed;
        return true;
    }

    public void InformComponents(int i, int j, Vector vector, int k, int l)
    {
        for(int i1 = 0; i1 < vector.size(); i1++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)vector.elementAt(i1);
            if(electroniccomponent != this)
                electroniccomponent.ReceivePotential(i, j, k, l);
        }

    }

    public void ReceivePotential(int i, int j, int k, int l)
    {
        if(k == Pos.x + APos.x && l == Pos.y + APos.y)
        {
            ALevel = j;
            if(!SwitchClosed)
            {
                QLevel = j;
                InformComponents(i, j, ConnCompsQ, Pos.x + QPos.x, Pos.y + QPos.y);
            }
            return;
        }
        if(k == Pos.x + BPos.x && l == Pos.y + BPos.y)
        {
            BLevel = j;
            if(SwitchClosed)
            {
                QLevel = j;
                InformComponents(i, j, ConnCompsQ, Pos.x + QPos.x, Pos.y + QPos.y);
            }
            return;
        }
        if(k == Pos.x + QPos.x && l == Pos.y + QPos.y)
        {
            QLevel = j;
            if(SwitchClosed)
            {
                BLevel = j;
                InformComponents(i, j, ConnCompsB, Pos.x + BPos.x, Pos.y + BPos.y);
            } else
            {
                ALevel = j;
                InformComponents(i, j, ConnCompsA, Pos.x + APos.x, Pos.y + APos.y);
            }
            return;
        } else
        {
            return;
        }
    }

    public void SimulateSetUp(int i, int j, Vector vector)
    {
        if(Pos.x + APos.x == i && Pos.y + APos.y == j)
            ConnCompsA = vector;
        if(Pos.x + BPos.x == i && Pos.y + BPos.y == j)
            ConnCompsB = vector;
        if(Pos.x + QPos.x == i && Pos.y + QPos.y == j)
            ConnCompsQ = vector;
    }

    public void RemovePinsGrid(Pin apin[][])
    {
        RemovePin(apin[Pos.x + APos.x][Pos.y + APos.y]);
        RemovePin(apin[Pos.x + BPos.x][Pos.y + BPos.y]);
        RemovePin(apin[Pos.x + QPos.x][Pos.y + QPos.y]);
    }

    public void RegisterPins(Pin apin[][], int i, int j)
    {
        if(apin == null)
        {
            return;
        } else
        {
            RegisterPin(apin[i + APos.x][j + APos.y]);
            RegisterPin(apin[i + BPos.x][j + BPos.y]);
            RegisterPin(apin[i + QPos.x][j + QPos.y]);
            return;
        }
    }

    public void PlacePinsHere(Pin apin[][])
    {
        if(apin == null)
        {
            return;
        } else
        {
            RegisterPins(apin, Pos.x, Pos.y);
            return;
        }
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setStroke(ComponentPin.PinColor);
        if(NbRotation == 0)
        {
            DrawWithOffset.strokeLine(g,(l + 1) * k, (i1 + 2) * k, (l + 3) * k, (i1 + 2) * k);
            DrawWithOffset.strokeLine(g,(l + 1) * k, (i1 + 4) * k, (l + 3) * k, (i1 + 4) * k);
            DrawWithOffset.strokeLine(g,(l + 7) * k, (i1 + 3) * k, (l + 9) * k, (i1 + 3) * k);
            switch(QLevel)
            {
            case 0: // '\0'
                g.setStroke(MyColor.blue);
                break;

            case 5: // '\005'
                g.setStroke(MyColor.red);
                break;

            default:
                g.setStroke(MyColor.gray);
                break;
            }
            if(SwitchClosed)
            {
                DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 4) * k - 1, (l + 7) * k, (i1 + 3) * k - 1);
                DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 4) * k, (l + 7) * k, (i1 + 3) * k);
            } else
            {
                DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 2) * k - 1, (l + 7) * k, (i1 + 3) * k - 1);
                DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 2) * k, (l + 7) * k, (i1 + 3) * k);
            }
        } else
        if(NbRotation == 1)
        {
            DrawWithOffset.strokeLine(g,(l + 6) * k, (i1 - 1) * k, (l + 6) * k, (i1 + 1) * k);
            DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 - 1) * k, (l + 4) * k, (i1 + 1) * k);
            DrawWithOffset.strokeLine(g,(l + 5) * k, (i1 + 7) * k, (l + 5) * k, (i1 + 5) * k);
            switch(QLevel)
            {
            case 0: // '\0'
                g.setStroke(MyColor.blue);
                break;

            case 5: // '\005'
                g.setStroke(MyColor.red);
                break;

            default:
                g.setStroke(MyColor.gray);
                break;
            }
            if(SwitchClosed)
            {
                DrawWithOffset.strokeLine(g,(l + 4) * k - 1, (i1 + 1) * k, (l + 5) * k - 1, (i1 + 5) * k);
                DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 1) * k, (l + 5) * k, (i1 + 5) * k);
            } else
            {
                DrawWithOffset.strokeLine(g,(l + 6) * k - 1, (i1 + 1) * k, (l + 5) * k - 1, (i1 + 5) * k);
                DrawWithOffset.strokeLine(g,(l + 6) * k, (i1 + 1) * k, (l + 5) * k, (i1 + 5) * k);
            }
        } else
        if(NbRotation == 2)
        {
            DrawWithOffset.strokeLine(g,(l + 9) * k, (i1 + 4) * k, (l + 7) * k, (i1 + 4) * k);
            DrawWithOffset.strokeLine(g,(l + 9) * k, (i1 + 2) * k, (l + 7) * k, (i1 + 2) * k);
            DrawWithOffset.strokeLine(g,(l + 1) * k, (i1 + 3) * k, (l + 3) * k, (i1 + 3) * k);
            switch(QLevel)
            {
            case 0: // '\0'
                g.setStroke(MyColor.blue);
                break;

            case 5: // '\005'
                g.setStroke(MyColor.red);
                break;

            default:
                g.setStroke(MyColor.gray);
                break;
            }
            if(SwitchClosed)
            {
                DrawWithOffset.strokeLine(g,(l + 7) * k, (i1 + 2) * k + 1, (l + 3) * k, (i1 + 3) * k + 1);
                DrawWithOffset.strokeLine(g,(l + 7) * k, (i1 + 2) * k, (l + 3) * k, (i1 + 3) * k);
            } else
            {
                DrawWithOffset.strokeLine(g,(l + 7) * k, (i1 + 4) * k + 1, (l + 3) * k, (i1 + 3) * k + 1);
                DrawWithOffset.strokeLine(g,(l + 7) * k, (i1 + 4) * k, (l + 3) * k, (i1 + 3) * k);
            }
        } else
        if(NbRotation == 3)
        {
            DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 7) * k, (l + 4) * k, (i1 + 5) * k);
            DrawWithOffset.strokeLine(g,(l + 6) * k, (i1 + 7) * k, (l + 6) * k, (i1 + 5) * k);
            DrawWithOffset.strokeLine(g,(l + 5) * k, (i1 + -1) * k, (l + 5) * k, (i1 + 1) * k);
            switch(QLevel)
            {
            case 0: // '\0'
                g.setStroke(MyColor.blue);
                break;

            case 5: // '\005'
                g.setStroke(MyColor.red);
                break;

            default:
                g.setStroke(MyColor.gray);
                break;
            }
            if(SwitchClosed)
            {
                DrawWithOffset.strokeLine(g,(l + 6) * k - 1, (i1 + 5) * k, (l + 5) * k - 1, (i1 + 1) * k);
                DrawWithOffset.strokeLine(g,(l + 6) * k, (i1 + 5) * k, (l + 5) * k, (i1 + 1) * k);
            } else
            {
                DrawWithOffset.strokeLine(g,(l + 4) * k - 1, (i1 + 5) * k, (l + 5) * k - 1, (i1 + 1) * k);
                DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 5) * k, (l + 5) * k, (i1 + 1) * k);
            }
        }
        g.setFill(ComponentColor);
        if(NbRotation == 0)
        {
            DrawWithOffset.fillRect(g,(l + 3) * k, (i1 + 2) * k - k / 3, k / 2, k / 2 + 1);
            DrawWithOffset.fillRect(g,(l + 3) * k, (i1 + 4) * k - k / 3, k / 2, k / 2 + 1);
            DrawWithOffset.fillRect(g,(l + 7) * k, (i1 + 3) * k - k / 3, k / 2, k / 2 + 1);
        } else
        if(NbRotation == 1)
        {
            DrawWithOffset.fillRect(g,(l + 6) * k - k / 4, (i1 + 1) * k, k / 2, k / 2 + 1);
            DrawWithOffset.fillRect(g,(l + 4) * k - k / 4, (i1 + 1) * k, k / 2, k / 2 + 1);
            DrawWithOffset.fillRect(g,(l + 5) * k - k / 4, (i1 + 5) * k, k / 2, k / 2 + 1);
        } else
        if(NbRotation == 2)
        {
            DrawWithOffset.fillRect(g,(l + 7) * k, (i1 + 4) * k - k / 3, k / 2, k / 2 + 1);
            DrawWithOffset.fillRect(g,(l + 7) * k, (i1 + 2) * k - k / 3, k / 2, k / 2 + 1);
            DrawWithOffset.fillRect(g,(l + 3) * k, ((i1 + 3) * k - k / 3) + 1, k / 2, k / 2 + 1);
        } else
        if(NbRotation == 3)
        {
            DrawWithOffset.fillRect(g,(l + 4) * k - k / 4, (i1 + 5) * k, k / 2, k / 2 + 1);
            DrawWithOffset.fillRect(g,(l + 6) * k - k / 4, (i1 + 5) * k, k / 2, k / 2 + 1);
            DrawWithOffset.fillRect(g,(l + 5) * k - k / 4, (i1 + 1) * k, k / 2, k / 2 + 1);
        }
    }

    public void rotate()
    {
        if(Can_Rotate)
        {
            NbRotation = (NbRotation + 1) % 4;
            HitBoxSize = new Dimension(HitBoxSize.height, HitBoxSize.width);
            Dim = new Dimension(Dim.height, Dim.width);
            updatePinsPosition();
        }
    }

    public void updatePinsPosition()
    {
        if(NbRotation == 0)
        {
            APos = new Point(1, 2);
            BPos = new Point(1, 4);
            QPos = new Point(9, 3);
        } else
        if(NbRotation == 1)
        {
            APos = new Point(6, -1);
            BPos = new Point(4, -1);
            QPos = new Point(5, 7);
        } else
        if(NbRotation == 2)
        {
            APos = new Point(9, 4);
            BPos = new Point(9, 2);
            QPos = new Point(1, 3);
        } else
        if(NbRotation == 3)
        {
            APos = new Point(4, 7);
            BPos = new Point(6, 7);
            QPos = new Point(5, -1);
        }
    }

    protected boolean SwitchClosed;
    Vector ConnCompsA;
    Vector ConnCompsB;
    Vector ConnCompsQ;
    int ALevel;
    int BLevel;
    int QLevel;
    protected Point APos;
    protected Point BPos;
    protected Point QPos;
}
