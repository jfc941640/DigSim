package digsim.components;

import java.util.Vector;

import digsim.MyColor;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            ElectronicComponent, MyColor, Pin

public class Relay2RT extends ElectronicComponent
{

    public Relay2RT(Pin apin[][], int i, int j)
    {
        super(i, j, 9, 12, 4, 2, 8, 10, 0, 0);
        SwitchClosed = false;
        CoilPotential = 0;
        ALevel = -1;
        BLevel = -1;
        CLevel = -1;
        DLevel = -1;
        WLevel = -1;
        XLevel = -1;
        YLevel = -1;
        ZLevel = -1;
        PLevel = -1;
        QLevel = -1;
        ComponentName = "Relais 2RT";
        ClassName = "Relay2RT";
        RegisterPins(apin, i, j);
    }

    public Relay2RT(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        SwitchClosed = false;
        CoilPotential = 0;
        ALevel = -1;
        BLevel = -1;
        CLevel = -1;
        DLevel = -1;
        WLevel = -1;
        XLevel = -1;
        YLevel = -1;
        ZLevel = -1;
        PLevel = -1;
        QLevel = -1;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Relay2RT relay2rt = new Relay2RT(this, i, j);
        return relay2rt;
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
        if(k == Pos.x + HitBox.x + 1 && l == Pos.y + HitBox.y + 12)
            PLevel = j;
        if(k == Pos.x + HitBox.x + 7 && l == Pos.y + HitBox.y + 12)
            QLevel = j;
        int i1 = Math.abs(PLevel - QLevel);
        if(i1 == 0)
            SwitchClosed = false;
        if(i1 == 5)
            SwitchClosed = true;
        if(k == (Pos.x + HitBox.x) - 2 && l == Pos.y + HitBox.y + 2)
        {
            if(SwitchClosed)
                InformComponents(i, j, ConnCompsW, Pos.x + HitBox.x + 10, Pos.y + HitBox.y + 2);
            return;
        }
        if(k == (Pos.x + HitBox.x) - 2 && l == Pos.y + HitBox.y + 4)
        {
            if(SwitchClosed)
                InformComponents(i, j, ConnCompsX, Pos.x + HitBox.x + 10, Pos.y + HitBox.y + 4);
            return;
        }
        if(k == (Pos.x + HitBox.x) - 2 && l == Pos.y + HitBox.y + 6)
        {
            if(!SwitchClosed)
                InformComponents(i, j, ConnCompsY, Pos.x + HitBox.x + 10, Pos.y + HitBox.y + 6);
            return;
        }
        if(k == (Pos.x + HitBox.x) - 2 && l == Pos.y + HitBox.y + 8)
        {
            if(!SwitchClosed)
                InformComponents(i, j, ConnCompsZ, Pos.x + HitBox.x + 10, Pos.y + HitBox.y + 8);
            return;
        }
        if(k == Pos.x + HitBox.x + 10 && l == Pos.y + HitBox.y + 2)
        {
            if(SwitchClosed)
                InformComponents(i, j, ConnCompsA, (Pos.x + HitBox.x) - 2, Pos.y + HitBox.y + 2);
            return;
        }
        if(k == Pos.x + HitBox.x + 10 && l == Pos.y + HitBox.y + 4)
        {
            if(SwitchClosed)
                InformComponents(i, j, ConnCompsB, (Pos.x + HitBox.x) - 2, Pos.y + HitBox.y + 4);
            return;
        }
        if(k == Pos.x + HitBox.x + 10 && l == Pos.y + HitBox.y + 6)
        {
            if(!SwitchClosed)
                InformComponents(i, j, ConnCompsC, (Pos.x + HitBox.x) - 2, Pos.y + HitBox.y + 6);
            return;
        }
        if(k == Pos.x + HitBox.x + 10 && l == Pos.y + HitBox.y + 8)
        {
            if(!SwitchClosed)
                InformComponents(i, j, ConnCompsD, (Pos.x + HitBox.x) - 2, Pos.y + HitBox.y + 8);
            return;
        } else
        {
            return;
        }
    }

    public void SimulateSetUp(int i, int j, Vector vector)
    {
        if((Pos.x + HitBox.x) - 2 == i && Pos.y + HitBox.y + 2 == j)
            ConnCompsA = vector;
        if((Pos.x + HitBox.x) - 2 == i && Pos.y + HitBox.y + 4 == j)
            ConnCompsB = vector;
        if((Pos.x + HitBox.x) - 2 == i && Pos.y + HitBox.y + 6 == j)
            ConnCompsC = vector;
        if((Pos.x + HitBox.x) - 2 == i && Pos.y + HitBox.y + 8 == j)
            ConnCompsD = vector;
        if(Pos.x + HitBox.x + 10 == i && Pos.y + HitBox.y + 2 == j)
            ConnCompsW = vector;
        if(Pos.x + HitBox.x + 10 == i && Pos.y + HitBox.y + 4 == j)
            ConnCompsX = vector;
        if(Pos.x + HitBox.x + 10 == i && Pos.y + HitBox.y + 6 == j)
            ConnCompsY = vector;
        if(Pos.x + HitBox.x + 10 == i && Pos.y + HitBox.y + 8 == j)
            ConnCompsZ = vector;
    }

    public void RemovePinsGrid(Pin apin[][])
    {
        RemovePin(apin[(Pos.x + HitBox.x) - 2][Pos.y + HitBox.y + 2]);
        RemovePin(apin[(Pos.x + HitBox.x) - 2][Pos.y + HitBox.y + 4]);
        RemovePin(apin[(Pos.x + HitBox.x) - 2][Pos.y + HitBox.y + 6]);
        RemovePin(apin[(Pos.x + HitBox.x) - 2][Pos.y + HitBox.y + 8]);
        RemovePin(apin[Pos.x + HitBox.x + 10][Pos.y + HitBox.y + 2]);
        RemovePin(apin[Pos.x + HitBox.x + 10][Pos.y + HitBox.y + 4]);
        RemovePin(apin[Pos.x + HitBox.x + 10][Pos.y + HitBox.y + 6]);
        RemovePin(apin[Pos.x + HitBox.x + 10][Pos.y + HitBox.y + 8]);
        RemovePin(apin[Pos.x + HitBox.x + 1][Pos.y + HitBox.y + 12]);
        RemovePin(apin[Pos.x + HitBox.x + 7][Pos.y + HitBox.y + 12]);
    }

    public void RegisterPins(Pin apin[][], int i, int j)
    {
        if(apin == null)
        {
            return;
        } else
        {
            RegisterPin(apin[(i + HitBox.x) - 2][j + HitBox.y + 2]);
            RegisterPin(apin[(i + HitBox.x) - 2][j + HitBox.y + 4]);
            RegisterPin(apin[(i + HitBox.x) - 2][j + HitBox.y + 6]);
            RegisterPin(apin[(i + HitBox.x) - 2][j + HitBox.y + 8]);
            RegisterPin(apin[i + HitBox.x + 10][j + HitBox.y + 2]);
            RegisterPin(apin[i + HitBox.x + 10][j + HitBox.y + 4]);
            RegisterPin(apin[i + HitBox.x + 10][j + HitBox.y + 6]);
            RegisterPin(apin[i + HitBox.x + 10][j + HitBox.y + 8]);
            RegisterPin(apin[i + HitBox.x + 1][j + HitBox.y + 12]);
            RegisterPin(apin[i + HitBox.x + 7][j + HitBox.y + 12]);
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
        g.setFill(Color.rgb(255, 255, 102));
        DrawWithOffset.fillRect(g,(l + HitBox.x) * k, (i1 + HitBox.y) * k, HitBoxSize.width * k, HitBoxSize.height * k);
        g.setStroke(ComponentColor);
        DrawWithOffset.strokeRect(g,(l + HitBox.x) * k, (i1 + HitBox.y) * k, HitBoxSize.width * k, HitBoxSize.height * k);
        DrawWithOffset.strokeLine(g,((l + HitBox.x) - 2) * k, (i1 + HitBox.y + 2) * k, (l + HitBox.x + 2) * k, (i1 + HitBox.y + 2) * k);
        DrawWithOffset.strokeLine(g,(l + HitBox.x + 6) * k, (i1 + HitBox.y + 2) * k, (l + HitBox.x + 10) * k, (i1 + HitBox.y + 2) * k);
        DrawWithOffset.strokeLine(g,((l + HitBox.x) - 2) * k, (i1 + HitBox.y + 4) * k, (l + HitBox.x + 2) * k, (i1 + HitBox.y + 4) * k);
        DrawWithOffset.strokeLine(g,(l + HitBox.x + 6) * k, (i1 + HitBox.y + 4) * k, (l + HitBox.x + 10) * k, (i1 + HitBox.y + 4) * k);
        DrawWithOffset.strokeLine(g,((l + HitBox.x) - 2) * k, (i1 + HitBox.y + 6) * k, (l + HitBox.x + 2) * k, (i1 + HitBox.y + 6) * k);
        DrawWithOffset.strokeLine(g,(l + HitBox.x + 6) * k, (i1 + HitBox.y + 6) * k, (l + HitBox.x + 10) * k, (i1 + HitBox.y + 6) * k);
        DrawWithOffset.strokeLine(g,((l + HitBox.x) - 2) * k, (i1 + HitBox.y + 8) * k, (l + HitBox.x + 2) * k, (i1 + HitBox.y + 8) * k);
        DrawWithOffset.strokeLine(g,(l + HitBox.x + 6) * k, (i1 + HitBox.y + 8) * k, (l + HitBox.x + 10) * k, (i1 + HitBox.y + 8) * k);
        g.setFill(MyColor.black);
        DrawWithOffset.fillRect(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 2) * k - k / 3, k / 2, k / 2 + 1);
        DrawWithOffset.fillRect(g,(l + HitBox.x + 6) * k, (i1 + HitBox.y + 2) * k - k / 3, k / 2, k / 2 + 1);
        DrawWithOffset.fillRect(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 4) * k - k / 3, k / 2, k / 2 + 1);
        DrawWithOffset.fillRect(g,(l + HitBox.x + 6) * k, (i1 + HitBox.y + 4) * k - k / 3, k / 2, k / 2 + 1);
        DrawWithOffset.fillRect(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 6) * k - k / 3, k / 2, k / 2 + 1);
        DrawWithOffset.fillRect(g,(l + HitBox.x + 6) * k, (i1 + HitBox.y + 6) * k - k / 3, k / 2, k / 2 + 1);
        DrawWithOffset.fillRect(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 8) * k - k / 3, k / 2, k / 2 + 1);
        DrawWithOffset.fillRect(g,(l + HitBox.x + 6) * k, (i1 + HitBox.y + 8) * k - k / 3, k / 2, k / 2 + 1);
        g.setStroke(MyColor.gray);
        if(!SwitchClosed)
        {
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 1) * k + 1, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 1) * k + 1);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 1) * k + 2, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 1) * k + 2);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 1) * k + 3, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 1) * k + 3);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 3) * k + 1, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 3) * k + 1);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 3) * k + 2, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 3) * k + 2);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 3) * k + 3, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 3) * k + 3);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 6) * k + 2, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 6) * k + 2);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 6) * k + 3, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 6) * k + 3);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 6) * k + 4, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 6) * k + 4);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 8) * k + 2, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 8) * k + 2);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 8) * k + 3, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 8) * k + 3);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 8) * k + 4, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 8) * k + 4);
        }
        if(SwitchClosed)
        {
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 7) * k - 1, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 7) * k - 1);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 7) * k - 2, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 7) * k - 2);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 7) * k - 3, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 7) * k - 3);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 9) * k - 1, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 9) * k - 1);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 9) * k - 2, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 9) * k - 2);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 9) * k - 3, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 9) * k - 3);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 2) * k - 2, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 2) * k - 2);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 2) * k - 3, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 2) * k - 3);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 2) * k - 4, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 2) * k - 4);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 4) * k - 2, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 4) * k - 2);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 4) * k - 3, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 4) * k - 3);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + 2) * k, (i1 + HitBox.y + 4) * k - 4, (l + HitBox.x + 6) * k + 3, (i1 + HitBox.y + 4) * k - 4);
        }
        for(int j1 = 1; j1 < 6; j1 += 2)
        {
            if(SwitchClosed)
                g.setStroke(MyColor.red);
            if(!SwitchClosed)
                g.setStroke(MyColor.blue);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + j1) * k, (i1 + HitBox.y + 10) * k, (l + HitBox.x + j1 + 1) * k, (i1 + HitBox.y + 9) * k);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + j1) * k, (i1 + HitBox.y + 10) * k - 1, (l + HitBox.x + j1 + 1) * k, (i1 + HitBox.y + 9) * k - 1);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + j1 + 1) * k, (i1 + HitBox.y + 9) * k, (l + HitBox.x + j1 + 2) * k, (i1 + HitBox.y + 10) * k);
            DrawWithOffset.strokeLine(g,(l + HitBox.x + j1 + 1) * k + 1, (i1 + HitBox.y + 9) * k, (l + HitBox.x + j1 + 2) * k + 1, (i1 + HitBox.y + 10) * k);
            g.setStroke(MyColor.black);
            if(j1 == 1)
                DrawWithOffset.strokeLine(g,(l + HitBox.x + j1) * k, (i1 + HitBox.y + 10) * k, (l + HitBox.x + j1) * k, (i1 + HitBox.y + 12) * k);
            if(j1 == 5)
            {
                j1 += 2;
                DrawWithOffset.strokeLine(g,(l + HitBox.x + j1) * k, (i1 + HitBox.y + 10) * k, (l + HitBox.x + j1) * k, (i1 + HitBox.y + 12) * k);
            }
        }

    }

    public void Simulate(int i)
    {
        InformConnectedComponents(i);
    }

    boolean SwitchClosed;
    int CoilPotential;
    Vector ConnCompsA;
    Vector ConnCompsB;
    Vector ConnCompsC;
    Vector ConnCompsD;
    Vector ConnCompsW;
    Vector ConnCompsX;
    Vector ConnCompsY;
    Vector ConnCompsZ;
    Vector ConnCompsP;
    Vector ConnCompsQ;
    int ALevel;
    int BLevel;
    int CLevel;
    int DLevel;
    int WLevel;
    int XLevel;
    int YLevel;
    int ZLevel;
    int PLevel;
    int QLevel;
}
