package digsim.components;

import java.io.PrintStream;

import digsim.MyColor;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.IntegratedCircuit;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            IntegratedCircuit, MyColor, Pin, ElectronicComponent

public class TraitsCie extends IntegratedCircuit
{

    public TraitsCie(Pin apin[][], int i, int j, int k, int l, int i1)
    {
        super(i, j, 1, 1, 1, 1, 0, 0, 0, 0);
        ComponentName = "Traits et Cie";
        ClassName = "TraitsCie";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
        hauteur = l;
        largeur = k;
        couleur = i1;
    }

    public TraitsCie(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        TraitsCie traitscie = new TraitsCie(this, i, j);
        traitscie.hauteur = hauteur;
        traitscie.largeur = largeur;
        traitscie.couleur = couleur;
        return traitscie;
    }

    public int getXZone()
    {
        return largeur;
    }

    public void setXZone(int i)
    {
        largeur = i;
    }

    public int getYZone()
    {
        return hauteur;
    }

    public void setYZone(int i)
    {
        hauteur = i;
    }

    public int getZZone()
    {
        return couleur;
    }

    public void setZZone(int i)
    {
        couleur = i;
    }

    public void SimulateLogic()
    {
    }

    public void InitBeforeSimulate()
    {
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        if(NbRotation == 0)
        {
            byte byte0 = 8;
            int j1 = 1;
            if(couleur >= 1000)
                g.setStroke(Color.rgb(45, 0, 130));
            else
            if(couleur < 0)
            {
                g.setStroke(Color.rgb(255, 215, 0));
            } else
            {
                R = couleur / 100;
                V = (couleur - 100 * R) / 10;
                B = couleur - 100 * R - 10 * V;
                g.setStroke(Color.rgb((R * 255) / 9, (V * 255) / 9, (B * 255) / 9));
            }
            int i2 = largeur / 10;
            int i3 = hauteur / 10;
            int i4 = largeur - 10 * i2;
            int i5 = hauteur - 10 * i3;
            if(largeur >= j1 && hauteur >= j1)
                DrawWithOffset.strokeRect(g,(l + 1) * k, (i1 + 1) * k, i2 * k + i4, i3 * k + i5);
            else
            if(largeur <= -j1 && hauteur <= -j1)
                DrawWithOffset.strokeOval(g,(l + 1) * k, (i1 + 1) * k, -i2 * k - i4, -i3 * k - i5);
            else
            if(hauteur > -byte0 && hauteur < byte0 && largeur >= byte0)
                DrawWithOffset.strokeLine(g,(l + 1) * k, (i1 + 1) * k + hauteur, (l + 1 + i2) * k + i4, (i1 + 1) * k + hauteur);
            else
            if(hauteur >= byte0 && largeur > -byte0 && largeur < byte0)
                DrawWithOffset.strokeLine(g,(l + 1) * k + largeur, (i1 + 1) * k, (l + 1) * k + largeur, (i1 + 1 + i3) * k + i5);
            else
            if(largeur >= 0 && hauteur <= 0)
                DrawWithOffset.strokeLine(g,(l + 1) * k, (i1 + 1) * k, (l + 1 + i2) * k + i4, (i1 + 1 + i3) * k + i5);
            else
            if(largeur <= 0 && hauteur >= 0)
            {
                DrawWithOffset.strokeLine(g,(l + 1) * k, (i1 + 1) * k, ((l + 1) - i2) * k - i4, (i1 + 1 + i3) * k + i5);
            } else
            {
            	g.setFill(g.getStroke());
                DrawWithOffset.fillText(g,"largeur= " + Integer.toString(largeur), l * k + 10, i1 * k + 4);
                DrawWithOffset.fillText(g,"hauteur= " + Integer.toString(hauteur), l * k + 10, (i1 + 1) * k + 4);
            }
        } else
        if(NbRotation == 1)
        {
            byte byte1 = 8;
            byte byte4 = 2;
            if(couleur >= 1000)
                g.setStroke(Color.rgb(45, 0, 130));
            else
            if(couleur < 0)
            {
                g.setStroke(Color.rgb(255, 215, 0));
            } else
            {
                R = couleur / 100;
                V = (couleur - 100 * R) / 10;
                B = couleur - 100 * R - 10 * V;
                g.setStroke(Color.rgb((R * 255) / 9, (V * 255) / 9, (B * 255) / 9));
            }
            int j2 = largeur / 10;
            int j3 = hauteur / 10;
            int j4 = largeur - 10 * j2;
            int j5 = hauteur - 10 * j3;
            if(largeur >= byte4 && hauteur >= byte4)
            {
                DrawWithOffset.strokeRect(g,(l + 1) * k, (i1 + 1) * k, j2 * k + j4, j3 * k + j5);
                DrawWithOffset.strokeRect(g,(l + 1) * k + 1, (i1 + 1) * k + 1, (j2 * k + j4) - 2, (j3 * k + j5) - 2);
            } else
            if(largeur <= -byte4 && hauteur <= -byte4)
            {
                DrawWithOffset.strokeOval(g,(l + 1) * k, (i1 + 1) * k, -j2 * k - j4, -j3 * k - j5);
                DrawWithOffset.strokeOval(g,(l + 1) * k + 1, (i1 + 1) * k + 1, -j2 * k - j4 - 2, -j3 * k - j5 - 2);
            } else
            if(hauteur > -byte1 && hauteur < byte1 && largeur >= byte1)
            {
                DrawWithOffset.strokeLine(g,(l + 1) * k, (i1 + 1) * k + hauteur, (l + 1 + j2) * k + j4, (i1 + 1) * k + hauteur);
                DrawWithOffset.strokeLine(g,(l + 1) * k, ((i1 + 1) * k + hauteur) - 1, (l + 1 + j2) * k + j4, ((i1 + 1) * k + hauteur) - 1);
            } else
            if(hauteur >= byte1 && largeur > -byte1 && largeur < byte1)
            {
                DrawWithOffset.strokeLine(g,(l + 1) * k + largeur, (i1 + 1) * k, (l + 1) * k + largeur, (i1 + 1 + j3) * k + j5);
                DrawWithOffset.strokeLine(g,(l + 1) * k + largeur + 1, (i1 + 1) * k, (l + 1) * k + largeur + 1, (i1 + 1 + j3) * k + j5);
            } else
            if(largeur >= 0 && hauteur <= 0)
            {
                DrawWithOffset.strokeLine(g,(l + 1) * k, (i1 + 1) * k, (l + 1 + j2) * k + j4, (i1 + 1 + j3) * k + j5);
                DrawWithOffset.strokeLine(g,(l + 1) * k + 1, (i1 + 1) * k + 1, (l + 1 + j2) * k + j4 + 1, (i1 + 1 + j3) * k + j5 + 1);
            } else
            if(largeur <= 0 && hauteur >= 0)
            {
                DrawWithOffset.strokeLine(g,(l + 1) * k, (i1 + 1) * k, ((l + 1) - j2) * k - j4, (i1 + 1 + j3) * k + j5);
                DrawWithOffset.strokeLine(g,(l + 1) * k - 1, (i1 + 1) * k + 1, ((l + 1) - j2) * k - j4 - 1, (i1 + 1 + j3) * k + j5 + 1);
            } else
            {
            	g.setFill(g.getStroke());
                DrawWithOffset.fillText(g,"largeur= " + Integer.toString(largeur), l * k + 10, i1 * k + 4);
                DrawWithOffset.fillText(g,"hauteur= " + Integer.toString(hauteur), l * k + 10, (i1 + 1) * k + 4);
            }
        } else
        if(NbRotation == 2)
        {
            byte byte2 = 8;
            int k1 = 1;
            if(couleur >= 1000)
                g.setStroke(Color.rgb(45, 0, 130));
            else
            if(couleur < 0)
            {
                g.setStroke(Color.rgb(255, 215, 0));
            } else
            {
                R = couleur / 100;
                V = (couleur - 100 * R) / 10;
                B = couleur - 100 * R - 10 * V;
                g.setStroke(Color.rgb((R * 255) / 9, (V * 255) / 9, (B * 255) / 9));
            }
            int k2 = largeur / 10;
            int k3 = hauteur / 10;
            int k4 = largeur - 10 * k2;
            int k5 = hauteur - 10 * k3;
            if(largeur >= k1 && hauteur >= k1)
            {
                DrawWithOffset.strokeRect(g,(l + 1) * k, (i1 + 1) * k, k2 * k + k4, k3 * k + k5);
                DrawWithOffset.strokeRect(g,(l + 1) * k + 1, (i1 + 1) * k + 1, (k2 * k + k4) - 2, (k3 * k + k5) - 2);
                DrawWithOffset.strokeRect(g,(l + 1) * k - 1, (i1 + 1) * k - 1, k2 * k + k4 + 2, k3 * k + k5 + 2);
            } else
            if(largeur <= -k1 && hauteur <= -k1)
            {
                DrawWithOffset.strokeOval(g,(l + 1) * k, (i1 + 1) * k, -k2 * k - k4, -k3 * k - k5);
                DrawWithOffset.strokeOval(g,(l + 1) * k + 1, (i1 + 1) * k + 1, -k2 * k - k4 - 2, -k3 * k - k5 - 2);
                DrawWithOffset.strokeOval(g,(l + 1) * k - 1, (i1 + 1) * k - 1, (-k2 * k - k4) + 2, (-k3 * k - k5) + 2);
            } else
            if(hauteur > -byte2 && hauteur < byte2 && largeur >= byte2)
            {
                DrawWithOffset.strokeLine(g,(l + 1) * k, (i1 + 1) * k + hauteur, (l + 1 + k2) * k + k4, (i1 + 1) * k + hauteur);
                DrawWithOffset.strokeLine(g,(l + 1) * k, ((i1 + 1) * k + hauteur) - 1, (l + 1 + k2) * k + k4, ((i1 + 1) * k + hauteur) - 1);
                DrawWithOffset.strokeLine(g,(l + 1) * k, (i1 + 1) * k + hauteur + 1, (l + 1 + k2) * k + k4, (i1 + 1) * k + hauteur + 1);
            } else
            if(hauteur >= byte2 && largeur > -byte2 && largeur < byte2)
            {
                DrawWithOffset.strokeLine(g,(l + 1) * k + largeur, (i1 + 1) * k, (l + 1) * k + largeur, (i1 + 1 + k3) * k + k5);
                DrawWithOffset.strokeLine(g,(l + 1) * k + largeur + 1, (i1 + 1) * k, (l + 1) * k + largeur + 1, (i1 + 1 + k3) * k + k5);
                DrawWithOffset.strokeLine(g,((l + 1) * k + largeur) - 1, (i1 + 1) * k, ((l + 1) * k + largeur) - 1, (i1 + 1 + k3) * k + k5);
            } else
            if(largeur >= 0 && hauteur <= 0)
            {
                double ai[] = {
                    (l + 1) * k + 1, (l + 1 + k2) * k + k4 + 1, ((l + 1 + k2) * k + k4) - 1, (l + 1) * k - 1
                };
                double ai2[] = {
                    (i1 + 1) * k + 1, (i1 + 1 + k3) * k + k5 + 1, ((i1 + 1 + k3) * k + k5) - 1, (i1 + 1) * k - 1
                };
            	g.setFill(g.getStroke());
                DrawWithOffset.fillPolygon(g,ai, ai2, 4);
            } else
            if(largeur <= 0 && hauteur >= 0)
            {
                double ai1[] = {
                    (l + 1) * k - 1, ((l + 1) - k2) * k - k4 - 1, (((l + 1) - k2) * k - k4) + 1, (l + 1) * k + 1
                };
                double ai3[] = {
                    (i1 + 1) * k + 1, (i1 + 1 + k3) * k + k5 + 1, ((i1 + 1 + k3) * k + k5) - 1, (i1 + 1) * k - 1
                };
            	g.setFill(g.getStroke());
                DrawWithOffset.fillPolygon(g,ai1, ai3, 4);
            } else
            {
            	g.setFill(g.getStroke());
                DrawWithOffset.fillText(g,"largeur= " + Integer.toString(largeur), l * k + 10, i1 * k + 4);
                DrawWithOffset.fillText(g,"hauteur= " + Integer.toString(hauteur), l * k + 10, (i1 + 1) * k + 4);
            }
        } else
        {
            byte byte3 = 9;
            int l1 = 0;
            if(couleur >= 1000)
                g.setStroke(Color.rgb(240, 240, 240));
            else
            if(couleur < 0)
            {
                g.setStroke(Color.rgb(255, 255, 102));
            } else
            {
                R = couleur / 100;
                V = (couleur - 100 * R) / 10;
                B = couleur - 100 * R - 10 * V;
                g.setStroke(Color.rgb((R * 255) / 9, (V * 255) / 9, (B * 255) / 9));
            }
            int l2 = largeur / 10;
            int l3 = hauteur / 10;
            int l4 = largeur - 10 * l2;
            int l5 = hauteur - 10 * l3;
        	g.setFill(g.getStroke());
            if(largeur > l1 && hauteur > l1)
                DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, l2 * k + l4 + 1, l3 * k + l5 + 1);
            else
            if(largeur < -l1 && hauteur < -l1)
                DrawWithOffset.fillOval(g,(l + 1) * k - 1, (i1 + 1) * k - 1, (-l2 * k - l4) + 2, (-l3 * k - l5) + 2);
            else
            if(largeur <= -byte3 && hauteur > 0)
            {
                g.setFill(MyColor.black);
                DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, (-l2 * k - l4) + 1, l3 * k + l5 + 1);
                g.setFill(Color.rgb((R * 255) / 9, (V * 255) / 9, (B * 255) / 9));
                DrawWithOffset.fillRect(g,(l + 1) * k + 4, (i1 + 1) * k + 4, -l2 * k - l4 - 7, (l3 * k + l5) - 7);
            } else
            if(largeur > 0 && hauteur <= -byte3)
            {
                g.setFill(MyColor.black);
                DrawWithOffset.fillOval(g,(l + 1) * k - 1, (i1 + 1) * k - 1, l2 * k + l4 + 2, (-l3 * k - l5) + 2);
                g.setFill(Color.rgb((R * 255) / 9, (V * 255) / 9, (B * 255) / 9));
                DrawWithOffset.fillOval(g,(l + 1) * k + 3, (i1 + 1) * k + 3, (l2 * k + l4) - 6, -l3 * k - l5 - 6);
            } else
            if(largeur > -byte3 && largeur <= l1 && hauteur > 0)
                DrawWithOffset.fillRect(g,(l + 1) * k + largeur, (i1 + 1) * k, 1 - largeur, l3 * k + l5 + 1);
            else
            if(largeur > 0 && hauteur > -byte3 && hauteur <= l1)
            {
                DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k + hauteur, l2 * k + l4 + 1, 1 - hauteur);
            } else
            {
            	g.setFill(g.getStroke());
                DrawWithOffset.fillText(g,"largeur= " + Integer.toString(largeur), l * k + 10, i1 * k + 4);
                DrawWithOffset.fillText(g,"hauteur= " + Integer.toString(hauteur), l * k + 10, (i1 + 1) * k + 4);
            }
        }
    }

    public void Save(PrintStream printstream)
    {
        printstream.println("describe component TraitsCie");
        printstream.println(" pos " + Pos.x + " " + Pos.y);
        printstream.println(" rotation " + NbRotation);
        printstream.println(" xrect " + largeur);
        printstream.println(" yrect " + hauteur);
        printstream.println(" zrect " + couleur);
        printstream.println("end describe");
    }

    public String ExportAsScript()
    {
        String s = new String("[|TraitsCie|pos|" + Pos.x + "|" + Pos.y + "|rotation|" + NbRotation + "|xrect|" + largeur + "|yrect|" + hauteur + "|zrect|" + couleur + "|]\r\n^");
        return s;
    }

    int hauteur;
    int largeur;
    int couleur;
    int R;
    int V;
    int B;
}
