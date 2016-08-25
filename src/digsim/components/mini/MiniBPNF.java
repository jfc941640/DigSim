package digsim.components.mini;

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

public class MiniBPNF extends ElectronicComponent
{

    public MiniBPNF(Pin apin[][], int i, int j)
    {
        super(i, j, 10, 5, 2, 1, 2, 2, 0, 0);
        MiniBPNFClosed = true;
        ALevel = -1;
        QLevel = -1;
        ComponentName = "BP NF (normalement ferme - contact R)";
        ClassName = "MiniBPNF";
        Can_Rotate = true;
        APos = new Point(1, 1);
        QPos = new Point(5, 1);
        RegisterPins(apin, i, j);
    }

    public MiniBPNF(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        MiniBPNFClosed = true;
        ALevel = -1;
        QLevel = -1;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        MiniBPNF minibpnf = new MiniBPNF(this, i, j);
        minibpnf.NbRotation = NbRotation;
        minibpnf.updatePinsPosition();
        return minibpnf;
    }

    public boolean SimMouseDown()
    {
        MiniBPNFClosed = false;
        return true;
    }

    public boolean SimMouseUp()
    {
        MiniBPNFClosed = true;
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
            if(MiniBPNFClosed)
            {
                QLevel = j;
                InformComponents(i, j, ConnCompsQ, Pos.x + QPos.x, Pos.y + QPos.y);
            }
            return;
        }
        if(k == Pos.x + QPos.x && l == Pos.y + QPos.y)
        {
            QLevel = j;
            if(MiniBPNFClosed)
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
        if(Pos.x + QPos.x == i && Pos.y + QPos.y == j)
            ConnCompsQ = vector;
    }

    public void RemovePinsGrid(Pin apin[][])
    {
        RemovePin(apin[Pos.x + APos.x][Pos.y + APos.y]);
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
            DrawWithOffset.strokeLine(g,(l + 1) * k, (i1 + 1) * k, (l + 2) * k, (i1 + 1) * k);
            DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 1) * k, (l + 5) * k, (i1 + 1) * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 1) * k - 5, (l + 2) * k, (i1 + 1) * k);
            if(MiniBPNFClosed)
            {
                DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 2) * k - 4 - 5, (l + 3) * k, (i1 + 3) * k);
                DrawWithOffset.strokeLine(g,(l + 3) * k - 4, (i1 + 3) * k, (l + 4) * k - 4, (i1 + 3) * k);
                DrawWithOffset.strokeLine(g,(l + 3) * k - 6, (i1 + 3) * k - 2, (l + 3) * k - 4, (i1 + 3) * k);
                DrawWithOffset.strokeLine(g,(l + 4) * k - 4, (i1 + 3) * k, (l + 4) * k - 2, (i1 + 3) * k - 2);
            } else
            {
                DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 1) * k - 5, (l + 3) * k, (i1 + 3) * k - 4);
                DrawWithOffset.strokeLine(g,(l + 3) * k - 4, (i1 + 3) * k - 4, (l + 4) * k - 4, (i1 + 3) * k - 4);
                DrawWithOffset.strokeLine(g,(l + 3) * k - 6, (i1 + 3) * k - 6, (l + 3) * k - 4, (i1 + 3) * k - 4);
                DrawWithOffset.strokeLine(g,(l + 4) * k - 4, (i1 + 3) * k - 4, (l + 4) * k - 2, (i1 + 3) * k - 6);
            }
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
            if(MiniBPNFClosed)
            {
                DrawWithOffset.strokeLine(g,(l + 2) * k - 2, (i1 + 1) * k - 4, (l + 4) * k, (i1 + 1) * k - 1);
                DrawWithOffset.strokeLine(g,(l + 2) * k - 2, (i1 + 1) * k - 3, (l + 4) * k, (i1 + 1) * k);
            } else
            {
                DrawWithOffset.strokeLine(g,(l + 2) * k + 1, i1 * k - 2, (l + 4) * k, (i1 + 1) * k - 1);
                DrawWithOffset.strokeLine(g,(l + 2) * k + 1, i1 * k - 1, (l + 4) * k, (i1 + 1) * k);
            }
        } else
        if(NbRotation == 1)
        {
            DrawWithOffset.strokeLine(g,(l + 4) * k, i1 * k, (l + 4) * k, (i1 + 1) * k);
            DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 3) * k, (l + 4) * k, (i1 + 4) * k);
            DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 1) * k, (l + 4) * k + 5, (i1 + 1) * k);
            if(MiniBPNFClosed)
            {
                DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k, ((l + 4) * k - 4) + 5, (i1 + 2) * k);
                DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k - 4, (l + 2) * k, (i1 + 3) * k - 4);
                DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k - 4, (l + 3) * k - 6, (i1 + 2) * k - 6);
                DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 3) * k - 4, (l + 3) * k - 6, (i1 + 3) * k - 2);
            } else
            {
                DrawWithOffset.strokeLine(g,(l + 3) * k - 4, (i1 + 2) * k, (l + 4) * k + 5, (i1 + 2) * k);
                DrawWithOffset.strokeLine(g,(l + 3) * k - 4, (i1 + 2) * k - 4, (l + 3) * k - 4, (i1 + 3) * k - 4);
                DrawWithOffset.strokeLine(g,(l + 3) * k - 4, (i1 + 2) * k - 4, (l + 3) * k - 2, (i1 + 2) * k - 6);
                DrawWithOffset.strokeLine(g,(l + 3) * k - 4, (i1 + 3) * k - 4, (l + 3) * k - 2, (i1 + 3) * k - 2);
            }
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
            if(MiniBPNFClosed)
            {
                DrawWithOffset.strokeLine(g,(l + 4) * k + 4, (i1 + 1) * k - 2, (l + 4) * k - 1, (i1 + 3) * k);
                DrawWithOffset.strokeLine(g,(l + 4) * k + 5, (i1 + 1) * k - 2, (l + 4) * k, (i1 + 3) * k);
            } else
            {
                DrawWithOffset.strokeLine(g,(l + 5) * k + 2, (i1 + 1) * k + 1, (l + 4) * k - 1, (i1 + 3) * k);
                DrawWithOffset.strokeLine(g,(l + 5) * k + 3, (i1 + 1) * k + 1, (l + 4) * k, (i1 + 3) * k);
            }
        }
    }

    public void rotate()
    {
        if(Can_Rotate)
        {
            NbRotation = (NbRotation + 1) % 2;
            HitBoxSize = new Dimension(HitBoxSize.height, HitBoxSize.width);
            Dim = new Dimension(Dim.height, Dim.width);
            updatePinsPosition();
        }
    }

    public void updatePinsPosition()
    {
        if(NbRotation == 0)
        {
            APos = new Point(1, 1);
            QPos = new Point(5, 1);
        } else
        if(NbRotation == 1)
        {
            APos = new Point(4, 0);
            QPos = new Point(4, 4);
        }
    }

    boolean MiniBPNFClosed;
    Vector ConnCompsA;
    Vector ConnCompsQ;
    int ALevel;
    int QLevel;
    protected Point APos;
    protected Point QPos;
}
