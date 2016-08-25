package digsim;

import java.io.PrintStream;
import java.util.Vector;

import digsim.MyColor;
import digsim.Pin;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            ElectronicComponent, DigSim, MyColor, Schematic,
//            Pin

public class Wire extends ElectronicComponent
{

    public Wire(Pin apin[][], int i, int j)
    {
        ChangingWire = 0;
        ActLevel = -1;
        ReceivedSimulationCycleID = -1;
        ComponentName = "Fil";
        x1 = i;
        y1 = j;
        ActLevel = -1;
    }

    public Wire(Pin apin[][], int i, int j, int k, int l)
    {
        ChangingWire = 0;
        ActLevel = -1;
        ReceivedSimulationCycleID = -1;
        ComponentName = "Fil";
        x1 = i;
        y1 = j;
        x2 = k;
        y2 = l;
        ActLevel = -1;
        PlacePinsHere(apin);
    }

    public Wire(Pin apin[][], int i, int j, int k, int l, int i1)
    {
        ChangingWire = 0;
        ActLevel = -1;
        ReceivedSimulationCycleID = -1;
        ComponentName = "Fil";
        x1 = j;
        y1 = k;
        x2 = l;
        y2 = i1;
        ActLevel = i;
        PlacePinsHere(apin);
    }

    public Wire(ElectronicComponent electroniccomponent, int i, int j)
    {
        ChangingWire = 0;
        ActLevel = -1;
        ReceivedSimulationCycleID = -1;
        ComponentName = "Fil";
        ActLevel = -1;
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Wire wire = new Wire(this, i, j);
        wire.x1 = x1 - i;
        wire.y1 = y1 - j;
        wire.x2 = x2 - i;
        wire.y2 = y2 - j;
        CheckPosition();
        return wire;
    }

    public void CheckPosition()
    {
        if(x1 < 1)
            x1 = 1;
        if(x1 >= DigSim.MaxXPoints)
            x1 = DigSim.MaxXPoints - 1;
        if(y1 < 1)
            y1 = 1;
        if(y1 >= DigSim.MaxYPoints)
            y1 = DigSim.MaxYPoints - 1;
        if(x2 < 1)
            x2 = 1;
        if(x2 >= DigSim.MaxXPoints)
            x2 = DigSim.MaxXPoints - 1;
        if(y2 < 1)
            y2 = 1;
        if(y2 >= DigSim.MaxYPoints)
            y2 = DigSim.MaxYPoints - 1;
    }

    public void SimulateSetUp(int i, int j, Vector vector)
    {
        ActLevel = -1;
        if(i == x1 && j == y1)
            ConnComps1 = vector;
        if(i == x2 && j == y2)
            ConnComps2 = vector;
    }

    public void InitBeforeSimulate()
    {
        ReceivedSimulationCycleID = -1;
    }

    public void ReceivePotential(int i, int j, int k, int l)
    {
        if(i == ReceivedSimulationCycleID && ActLevel == j)
            return;
        ActLevel = j;
        ReceivedSimulationCycleID = i;
        if(x1 == k && y1 == l)
        {
            InformComponents(i, ConnComps2, x2, y2);
            return;
        }
        if(x2 == k && y2 == l)
        {
            InformComponents(i, ConnComps1, x1, y1);
            return;
        } else
        {
            ActLevel = -1;
            return;
        }
    }

    public void InformComponents(int i, Vector vector, int j, int k)
    {
        SimulateLogic();
        for(int l = 0; l < vector.size(); l++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)vector.elementAt(l);
            if(electroniccomponent != this)
                electroniccomponent.ReceivePotential(i, ActLevel, j, k);
        }

    }

    public boolean AdjustPosition(Pin apin[][], int i, int j)
    {
        int k = x1;
        int l = y1;
        int i1 = x2;
        int j1 = y2;
        if((ChangingWire & 1) == 1)
        {
            k += i;
            l += j;
        }
        if((ChangingWire & 2) == 2)
        {
            i1 += i;
            j1 += j;
        }
        if(k == i1 && l == j1)
        {
            return false;
        } else
        {
            x1 = k;
            y1 = l;
            x2 = i1;
            y2 = j1;
            return true;
        }
    }

    public void RemovePinsGrid(Pin apin[][])
    {
        RemovePin(apin[x1][y1]);
        RemovePin(apin[x2][y2]);
    }

    public void RegisterPins(Pin apin[][], int i, int j)
    {
        if(apin == null)
        {
            return;
        } else
        {
            RegisterPin(apin[x1][y1]);
            RegisterPin(apin[x2][y2]);
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
            RegisterPins(apin, 0, 0);
            return;
        }
    }

    public void Set2ndCoord(Pin apin[][], int i, int j)
    {
        x2 = i;
        y2 = j;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        int l = x1 - i;
        int i1 = x2 - i;
        int j1 = y1 - j;
        int k1 = y2 - j;
        if(Selected)
        {
            g.setStroke(MyColor.black);
            if((ChangingWire & 1) == 1)
                DrawWithOffset.strokeRect(g,l * k - 2, j1 * k - 2, k / 2, k / 2);
            if((ChangingWire & 2) == 2)
                DrawWithOffset.strokeRect(g,i1 * k - 2, k1 * k - 2, k / 2, k / 2);
        }
        if(ActLevel == 0)
        {
            g.setStroke(MyColor.blue);
            DrawWithOffset.strokeLine(g,l * k, j1 * k, i1 * k, k1 * k);
        } else
        if(ActLevel == 5)
        {
            g.setStroke(MyColor.red);
            DrawWithOffset.strokeLine(g,l * k + 1, j1 * k, i1 * k + 1, k1 * k);
            DrawWithOffset.strokeLine(g,l * k, j1 * k, i1 * k, k1 * k);
            DrawWithOffset.strokeLine(g,l * k, j1 * k - 1, i1 * k, k1 * k - 1);
        } else
        if(ActLevel == -1)
        {
            g.setStroke(MyColor.darkGray);
            DrawWithOffset.strokeLine(g,l * k, j1 * k, i1 * k, k1 * k);
        } else
        if(ActLevel == 0x6c9d8)
        {
            g.setStroke(MyColor.green);
            DrawWithOffset.strokeLine(g,l * k, j1 * k, i1 * k, k1 * k);
        } else
        if(ActLevel >= 0x6c660 && ActLevel < 0x6c760)
        {
            g.setStroke(MyColor.darkGray);
            double ai[] = {
                l * k, l * k, i1 * k, i1 * k
            };
            double ai6[] = {
                j1 * k + 4, j1 * k - 3, k1 * k - 3, k1 * k + 4
            };
            DrawWithOffset.fillPolygon(g,ai, ai6, ai.length);
            ai = (new double[] {
                l * k + 4, l * k - 3, i1 * k - 3, i1 * k + 4
            });
            ai6 = (new double[] {
                j1 * k, j1 * k, k1 * k, k1 * k
            });
            DrawWithOffset.fillPolygon(g,ai, ai6, ai.length);
        } else
        if(ActLevel == 0x6ca47)
        {
            g.setStroke(MyColor.gray);
            double ai1[] = {
                l * k, l * k, i1 * k, i1 * k
            };
            double ai7[] = {
                j1 * k + 4, j1 * k - 3, k1 * k - 3, k1 * k + 4
            };
            DrawWithOffset.fillPolygon(g,ai1, ai7, ai1.length);
            ai1 = (new double[] {
                l * k + 4, l * k - 3, i1 * k - 3, i1 * k + 4
            });
            ai7 = (new double[] {
                j1 * k, j1 * k, k1 * k, k1 * k
            });
            DrawWithOffset.fillPolygon(g,ai1, ai7, ai1.length);
        } else
        if(ActLevel == 0x21e88e)
        {
            g.setStroke(MyColor.red);
            double ai2[] = {
                l * k, l * k, i1 * k, i1 * k
            };
            double ai8[] = {
                j1 * k + 2, j1 * k - 1, k1 * k - 1, k1 * k + 2
            };
            DrawWithOffset.fillPolygon(g,ai2, ai8, ai2.length);
            ai2 = (new double[] {
                l * k + 2, l * k - 1, i1 * k - 1, i1 * k + 2
            });
            ai8 = (new double[] {
                j1 * k, j1 * k, k1 * k, k1 * k
            });
            DrawWithOffset.fillPolygon(g,ai2, ai8, ai2.length);
        } else
        if(ActLevel == 0x21e890)
        {
            g.setStroke(MyColor.yellow);
            double ai3[] = {
                l * k, l * k, i1 * k, i1 * k
            };
            double ai9[] = {
                j1 * k + 3, j1 * k - 2, k1 * k - 2, k1 * k + 3
            };
            DrawWithOffset.fillPolygon(g,ai3, ai9, ai3.length);
            ai3 = (new double[] {
                l * k + 3, l * k - 2, i1 * k - 2, i1 * k + 3
            });
            ai9 = (new double[] {
                j1 * k, j1 * k, k1 * k, k1 * k
            });
            DrawWithOffset.fillPolygon(g,ai3, ai9, ai3.length);
        } else
        if(ActLevel == 0x21e893)
        {
            g.setStroke(MyColor.magenta);
            double ai4[] = {
                l * k, l * k, i1 * k, i1 * k
            };
            double ai10[] = {
                j1 * k + 2, j1 * k - 1, k1 * k - 1, k1 * k + 2
            };
            DrawWithOffset.fillPolygon(g,ai4, ai10, ai4.length);
            ai4 = (new double[] {
                l * k + 2, l * k - 1, i1 * k - 1, i1 * k + 2
            });
            ai10 = (new double[] {
                j1 * k, j1 * k, k1 * k, k1 * k
            });
            DrawWithOffset.fillPolygon(g,ai4, ai10, ai4.length);
        } else
        if(ActLevel == 0x21e894)
        {
            g.setStroke(MyColor.gray);
            double ai5[] = {
                l * k, l * k, i1 * k, i1 * k
            };
            double ai11[] = {
                j1 * k + 2, j1 * k - 1, k1 * k - 1, k1 * k + 2
            };
            DrawWithOffset.fillPolygon(g,ai5, ai11, ai5.length);
            ai5 = (new double[] {
                l * k + 2, l * k - 1, i1 * k - 1, i1 * k + 2
            });
            ai11 = (new double[] {
                j1 * k, j1 * k, k1 * k, k1 * k
            });
            DrawWithOffset.fillPolygon(g,ai5, ai11, ai5.length);
        }
    }

    public int sgn(int i)
    {
        if(i > 0)
            return 1;
        return i >= 0 ? 0 : -1;
    }

    public boolean CheckIfPointInWire(int i, int j, int k, int l, int i1, int j1)
    {
        int k1 = k - i;
        int i2 = l - j;
        int k2 = sgn(k1);
        int l2 = sgn(i2);
        int i3 = sgn(k1);
        int j3 = 0;
        int k3 = Math.abs(k1);
        int l3 = Math.abs(i2);
        if(k3 <= l3)
        {
            i3 = 0;
            j3 = sgn(i2);
            k3 = Math.abs(i2);
            l3 = Math.abs(k1);
        }
        int l1 = k3 / 2;
        for(int j2 = 0; j2 < k3; j2++)
        {
            if(Math.abs(i1 - i) < 1 && Math.abs(j1 - j) < 1)
                return true;
            l1 += l3;
            if(l1 >= k3)
            {
                l1 -= k3;
                i += k2;
                j += l2;
            } else
            {
                i += i3;
                j += j3;
            }
        }

        return false;
    }

    public boolean CheckIfComponentClicked(int i, int j)
    {
        if(i == x1 && j == y1)
        {
            ChangingWire = 1;
            return true;
        }
        if(i == x2 && j == y2)
        {
            ChangingWire = 2;
            return true;
        }
        if(CheckIfPointInWire(x1, y1, x2, y2, i, j))
        {
            ChangingWire = 3;
            return true;
        } else
        {
            return false;
        }
    }

    public boolean CheckIfComponentInSelectBox(int i, int j, int k, int l)
    {
        Selected = false;
        ChangingWire = 0;
        if(i <= x1 && x1 <= k && j <= y1 && y1 <= l)
        {
            ChangingWire |= 1;
            Selected = true;
        }
        if(i <= x2 && x2 <= k && j <= y2 && y2 <= l)
        {
            ChangingWire |= 2;
            Selected = true;
        }
        return Selected;
    }

    public void Save(PrintStream printstream)
    {
        printstream.println("describe component Wire");
        printstream.println(" pos " + x1 + " " + y1);
        printstream.println(" pos2 " + x2 + " " + y2);
        printstream.println("end describe");
    }

    public String ExportAsScript()
    {
        String s = new String("[|Wire|pos|" + x1 + "|" + y1 + "|pos2|" + x2 + "|" + y2 + "|]\n^");
        return s;
    }

    public boolean TryPlaceJunction(Schematic schematic, Pin apin[][], int i, int j, int k)
    {
        if(i == x1 && j == y1)
            return false;
        if(i == x2 && j == y2)
            return false;
        if(CheckIfPointInWire(x1 * k, y1 * k, x2 * k, y2 * k, i * k, j * k))
        {
            schematic.addComponent(new Wire(apin, ActLevel, i, j, x1, y1));
            RemovePinsGrid(apin);
            x1 = i;
            y1 = j;
            PlacePinsHere(apin);
            return true;
        } else
        {
            return false;
        }
    }

    protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;
    protected int ChangingWire;
    int ActLevel;
    Vector ConnComps1;
    Vector ConnComps2;
    int ReceivedSimulationCycleID;
}
