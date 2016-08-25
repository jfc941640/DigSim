package digsim;

import java.awt.Dimension;
import java.awt.Point;
import java.io.PrintStream;
import java.util.Vector;

import digsim.DigSim;
import digsim.DrawWithOffset;
import digsim.InputPin;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            InputPin, OutputPin, MyColor, DigSim,
//            Pin

public class ElectronicComponent
{

    public ElectronicComponent()
    {
        Selected = false;
        Can_Rotate = false;
        NbRotation = 0;
    }

    public ElectronicComponent(int i, int j, int k, int l, int i1, int j1, int k1,
            int l1, int i2, int j2)
    {
        Selected = false;
        Can_Rotate = false;
        NbRotation = 0;
        Pos = new Point(i, j);
        Dim = new Dimension(k, l);
        HitBox = new Point(i1, j1);
        HitBoxSize = new Dimension(k1, l1);
        Inputs = i2;
        Outputs = j2;
        IPin = new InputPin[i2];
        OPin = new OutputPin[j2];
    }

    public ElectronicComponent(ElectronicComponent electroniccomponent, int i, int j)
    {
        Selected = false;
        Can_Rotate = false;
        NbRotation = 0;
        Pos = new Point(electroniccomponent.Pos.x - i, electroniccomponent.Pos.y - j);
        Dim = new Dimension(electroniccomponent.Dim.width, electroniccomponent.Dim.height);
        HitBox = new Point(electroniccomponent.HitBox.x, electroniccomponent.HitBox.y);
        HitBoxSize = new Dimension(electroniccomponent.HitBoxSize.width, electroniccomponent.HitBoxSize.height);
        Inputs = electroniccomponent.Inputs;
        Outputs = electroniccomponent.Outputs;
        ComponentName = electroniccomponent.ComponentName;
        ClassName = electroniccomponent.ClassName;
        IPin = new InputPin[Inputs];
        OPin = new OutputPin[Outputs];
        for(int k = 0; k < Inputs; k++)
            IPin[k] = new InputPin(electroniccomponent.IPin[k]);

        for(int l = 0; l < Outputs; l++)
            OPin[l] = new OutputPin(electroniccomponent.OPin[l]);

    }

    public ElectronicComponent Copy(int i, int j)
    {
        return this;
    }

    public String getName()
    {
        return ComponentName;
    }

    public void InitBeforeSimulate()
    {
        for(int i = 0; i < Inputs; i++)
            IPin[i].InitBeforeSimulate();

        for(int j = 0; j < Outputs; j++)
            OPin[j].InitBeforeSimulate();

    }

    public void ReceivePotential(int i, int j, int k, int l)
    {
        for(int j1 = 0; j1 < Outputs; j1++)
        {
            if(k != OPin[j1].PinPos.x + Pos.x || l != OPin[j1].PinPos.y + Pos.y)
                continue;
            OPin[j1].ShortCircuit = false;
            if(OPin[j1].getLevel() != j && (OPin[j1].getLevel() == 0 || OPin[j1].getLevel() == 5))
                OPin[j1].ShortCircuit = true;
        }

        for(int i1 = 0; i1 < Inputs; i1++)
            if(k == IPin[i1].PinPos.x + Pos.x && l == IPin[i1].PinPos.y + Pos.y)
            {
                if(i == IPin[i1].ReceivedSimulationCycleID)
                {
                    if(IPin[i1].Level == j)
                        return;
                    IPin[i1].LevelChanged++;
                    if(IPin[i1].LevelChanged > 1000)
                    {
                        IPin[i1].Looping = true;
                        return;
                    }
                }
                IPin[i1].setLevel(j);
                IPin[i1].ReceivedSimulationCycleID = i;
                if((IPin[i1].Flags & 4) == 4)
                {
                    SimulateLogic();
                    InformConnectedComponentsOldLevel(i);
                } else
                if((IPin[i1].Flags & 0x10) == 16)
                {
                    SimulateLogic();
                    InformConnectedComponentsOldLevel(i);
                } else
                if((IPin[i1].Flags & 0x20) == 32)
                {
                    SimulateLogic();
                    InformConnectedComponentsOldLevel(i);
                } else
                if(((IPin[i1].Flags & 8) != 8) & ((IPin[i1].Flags & 0x40) != 64))
                {
                    SimulateLogic();
                    InformConnectedComponents(i);
                }
                return;
            }

    }

    public void SimulateLogic()
    {
    }

    public void Simulate(int i)
    {
    }

    public void InformConnectedComponents(int i)
    {
        for(int j = 0; j < Outputs; j++)
        {
            for(int k = 0; k < OPin[j].ConnComps.size(); k++)
            {
                ElectronicComponent electroniccomponent = (ElectronicComponent)OPin[j].ConnComps.elementAt(k);
                if(electroniccomponent != this && OPin[j].getLevel() != 0x6c9d8 && OPin[j].getLevel() != 0x6ca47)
                    electroniccomponent.ReceivePotential(i, OPin[j].getLevel(), OPin[j].PinPos.x + Pos.x, OPin[j].PinPos.y + Pos.y);
            }

        }

    }

    public void InformConnectedComponentsOldLevel(int i)
    {
        for(int j = 0; j < Outputs; j++)
        {
            for(int k = 0; k < OPin[j].ConnComps.size(); k++)
            {
                ElectronicComponent electroniccomponent = (ElectronicComponent)OPin[j].ConnComps.elementAt(k);
                if(electroniccomponent != this && OPin[j].getLevel() != 0x6c9d8 && OPin[j].getLevel() != 0x6ca47)
                    electroniccomponent.ReceivePotential(i, OPin[j].getOldLevel(), OPin[j].PinPos.x + Pos.x, OPin[j].PinPos.y + Pos.y);
            }

        }

    }

    public void SimulateSetUp(int i, int j, Vector vector)
    {
        for(int k = 0; k < Inputs; k++)
            if(Pos.x + IPin[k].PinPos.x == i && Pos.y + IPin[k].PinPos.y == j)
                IPin[k].ConnComps = vector;

        for(int l = 0; l < Outputs; l++)
            if(Pos.x + OPin[l].PinPos.x == i && Pos.y + OPin[l].PinPos.y == j)
                OPin[l].ConnComps = vector;

    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        if(Selected)
        {
            int l = Pos.x - i;
            int i1 = Pos.y - j;
            g.setStroke(MyColor.red);
            DrawWithOffset.strokeRect(g,(int)(((double)(l + HitBox.x) - 0.25D) * (double)k), (int)(((double)(i1 + HitBox.y) - 0.25D) * (double)k), k / 2, k / 2);
            DrawWithOffset.strokeRect(g,(int)(((double)(l + HitBox.x + HitBoxSize.width) - 0.25D) * (double)k), (int)(((double)(i1 + HitBox.y) - 0.25D) * (double)k), k / 2, k / 2);
            DrawWithOffset.strokeRect(g,(int)(((double)(l + HitBox.x) - 0.25D) * (double)k), (int)(((double)(i1 + HitBox.y + HitBoxSize.height) - 0.25D) * (double)k), k / 2, k / 2);
            DrawWithOffset.strokeRect(g,(int)(((double)(l + HitBox.x + HitBoxSize.width) - 0.25D) * (double)k), (int)(((double)(i1 + HitBox.y + HitBoxSize.height) - 0.25D) * (double)k), k / 2, k / 2);
        }
    }

    public void DrawHitBox(GraphicsContext g, int i, int j, int k)
    {
        g.setStroke(MyColor.gray);
        DrawWithOffset.strokeRect(g,(i + HitBox.x) * k, (j + HitBox.y) * k, HitBoxSize.width * k, HitBoxSize.height * k);
        g.setStroke(MyColor.pink);
        DrawWithOffset.strokeRect(g,i * k, (j + 1) * k, Dim.width * k, Dim.height * k);
    }

    public boolean AdjustPosition(Pin apin[][], int i, int j)
    {
        Pos.x += i;
        Pos.y += j;
        return true;
    }

    public void CheckPosition()
    {
        if(Pos.x < 0)
            Pos.x = 0;
        if(Pos.y < 0)
            Pos.y = 0;
        if(Pos.x > DigSim.MaxXPoints - Dim.width)
            Pos.x = DigSim.MaxXPoints - Dim.width;
        if(Pos.y > DigSim.MaxYPoints - Dim.height)
            Pos.y = DigSim.MaxYPoints - Dim.height;
    }

    public boolean CheckIfComponentInSelectBox(int i, int j, int k, int l)
    {
        int i1 = Pos.x + HitBox.x;
        int j1 = Pos.y + HitBox.y;
        int k1 = Pos.x + HitBox.x + HitBoxSize.width;
        int l1 = Pos.y + HitBox.y + HitBoxSize.height;
        if(i <= i1 && k1 <= k && j <= j1 && l1 <= l)
            Selected = true;
        else
            Selected = false;
        return Selected;
    }

    public boolean CheckIfComponentClicked(int i, int j)
    {
        return Pos.x + HitBox.x <= i && i <= Pos.x + HitBox.x + HitBoxSize.width && Pos.y + HitBox.y <= j && j <= Pos.y + HitBox.y + HitBoxSize.height;
    }

    protected void RegisterPin(Pin pin)
    {
        pin.AddComponent(this);
    }

    public void RegisterPins(Pin apin[][], int i, int j)
    {
        if(apin == null)
            return;
        for(int k = 0; k < Inputs; k++)
        {
            int i1 = i + IPin[k].PinPos.x;
            int k1 = j + IPin[k].PinPos.y;
            RegisterPin(apin[i1][k1]);
        }

        for(int l = 0; l < Outputs; l++)
        {
            int j1 = i + OPin[l].PinPos.x;
            int l1 = j + OPin[l].PinPos.y;
            RegisterPin(apin[j1][l1]);
        }

    }

    protected void RemovePin(Pin pin)
    {
        pin.RemoveComponent(this);
    }

    public void RemovePinsGrid(Pin apin[][])
    {
        for(int i = 0; i < Inputs; i++)
        {
            int k = Pos.x + IPin[i].PinPos.x;
            int i1 = Pos.y + IPin[i].PinPos.y;
            RemovePin(apin[k][i1]);
        }

        for(int j = 0; j < Outputs; j++)
        {
            int l = Pos.x + OPin[j].PinPos.x;
            int j1 = Pos.y + OPin[j].PinPos.y;
            RemovePin(apin[l][j1]);
        }

    }

    public void PlacePinsHere(Pin apin[][])
    {
        RegisterPins(apin, Pos.x, Pos.y);
    }

    public boolean SimMouseDown()
    {
        return false;
    }

    public boolean SimMouseUp()
    {
        return false;
    }

    public void DrawInputPins(GraphicsContext g, int i, int j, int k)
    {
        for(int l = 0; l < Inputs; l++)
            IPin[l].draw(g, i, j, k);

    }

    public void DrawOutputPins(GraphicsContext g, int i, int j, int k)
    {
        for(int l = 0; l < Outputs; l++)
            OPin[l].draw(g, i, j, k);

    }

    public void Save(PrintStream printstream)
    {
        printstream.println("describe component " + ClassName);
        printstream.println(" pos " + Pos.x + " " + Pos.y);
        printstream.println(" rotation " + NbRotation);
        printstream.println("end describe");
    }

    public String ExportAsScript()
    {
        String s = new String("[|" + ClassName + "|pos|" + Pos.x + "|" + Pos.y + "|rotation|" + NbRotation + "|]\r\n^");
        return s;
    }

    public void rotate()
    {
        if(Can_Rotate)
        {
            NbRotation = (NbRotation + 1) % 4;
            HitBoxSize = new Dimension(HitBoxSize.height, HitBoxSize.width);
            Dim = new Dimension(Dim.height, Dim.width);
            for(int i = 0; i < Inputs; i++)
            {
                if(IPin[i].PinDim.height == 0 && IPin[i].PinDim.width < 0)
                {
                    IPin[i].PinPos.x = (HitBox.x + HitBoxSize.width) - (IPin[i].PinPos.y - HitBox.y);
                    IPin[i].PinPos.y = (HitBox.y + HitBoxSize.height) - IPin[i].PinDim.width;
                    IPin[i].PinDim.height = IPin[i].PinDim.width;
                    IPin[i].PinDim.width = 0;
                    continue;
                }
                if(IPin[i].PinDim.height == 0 && IPin[i].PinDim.width > 0)
                {
                    IPin[i].PinPos.x = HitBox.x + HitBoxSize.width + (HitBox.y - IPin[i].PinPos.y);
                    IPin[i].PinPos.y = HitBox.y - IPin[i].PinDim.width;
                    IPin[i].PinDim.height = IPin[i].PinDim.width;
                    IPin[i].PinDim.width = 0;
                    continue;
                }
                if(IPin[i].PinDim.height < 0 && IPin[i].PinDim.width == 0)
                {
                    IPin[i].PinPos.y = HitBox.y + (IPin[i].PinPos.x - HitBox.x);
                    IPin[i].PinPos.x = HitBox.x + IPin[i].PinDim.height;
                    IPin[i].PinDim.width = -1 * IPin[i].PinDim.height;
                    IPin[i].PinDim.height = 0;
                    continue;
                }
                if(IPin[i].PinDim.height > 0 && IPin[i].PinDim.width == 0)
                {
                    IPin[i].PinPos.y = (HitBox.y + IPin[i].PinPos.x) - HitBox.x;
                    IPin[i].PinPos.x = HitBox.x + HitBoxSize.width + IPin[i].PinDim.height;
                    IPin[i].PinDim.width = -1 * IPin[i].PinDim.height;
                    IPin[i].PinDim.height = 0;
                }
            }

            for(int j = 0; j < Outputs; j++)
            {
                if(OPin[j].PinDim.height == 0 && OPin[j].PinDim.width < 0)
                {
                    OPin[j].PinPos.x = (HitBox.x + HitBoxSize.width) - (OPin[j].PinPos.y - HitBox.y);
                    OPin[j].PinPos.y = (HitBox.y + HitBoxSize.height) - OPin[j].PinDim.width;
                    OPin[j].PinDim.height = OPin[j].PinDim.width;
                    OPin[j].PinDim.width = 0;
                    continue;
                }
                if(OPin[j].PinDim.height == 0 && OPin[j].PinDim.width > 0)
                {
                    OPin[j].PinPos.x = HitBox.x + HitBoxSize.width + (HitBox.y - OPin[j].PinPos.y);
                    OPin[j].PinPos.y = HitBox.y - OPin[j].PinDim.width;
                    OPin[j].PinDim.height = OPin[j].PinDim.width;
                    OPin[j].PinDim.width = 0;
                    continue;
                }
                if(OPin[j].PinDim.height < 0 && OPin[j].PinDim.width == 0)
                {
                    OPin[j].PinPos.y = HitBox.y + (OPin[j].PinPos.x - HitBox.x);
                    OPin[j].PinPos.x = HitBox.x + OPin[j].PinDim.height;
                    OPin[j].PinDim.width = -1 * OPin[j].PinDim.height;
                    OPin[j].PinDim.height = 0;
                    continue;
                }
                if(OPin[j].PinDim.height > 0 && OPin[j].PinDim.width == 0)
                {
                    OPin[j].PinPos.y = (HitBox.y + OPin[j].PinPos.x) - HitBox.x;
                    OPin[j].PinPos.x = HitBox.x + HitBoxSize.width + OPin[j].PinDim.height;
                    OPin[j].PinDim.width = -1 * OPin[j].PinDim.height;
                    OPin[j].PinDim.height = 0;
                }
            }

        }
    }

    protected Point Pos;
    protected Dimension Dim;
    protected String ComponentName;
    protected String ClassName;
    protected Point HitBox;
    protected Dimension HitBoxSize;
    public InputPin IPin[];
    protected OutputPin OPin[];
    protected int Inputs;
    protected int Outputs;
    public boolean Selected;
    protected static Color ComponentColor;
    static final int MAXLOOPS = 1000;
    protected boolean Can_Rotate;
    protected int NbRotation;

    static
    {
        ComponentColor = MyColor.black;
    }
}
