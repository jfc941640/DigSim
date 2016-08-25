package digsim.components.rail;

import digsim.InputPin;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DigSim;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, OutputPin, DigSim,
//            MyColor, Pin

public class RailCourbe90d8x8Auto extends ElectronicComponent
{

    public RailCourbe90d8x8Auto(Pin apin[][], int i, int j)
    {
        super(i, j, 12, 12, 1, 1, 7, 7, 2, 2);
        Count = 0;
        Train = false;
        COLLISION = false;
        oldH = 0;
        oldT = 0;
        lngr = 50;
        NbrW = 3;
        IPin[0] = new InputPin("", 0, 1, 1, 0, 0, 0, 0);
        IPin[1] = new InputPin("", 7, 9, 0, -1, 0, 0, 0);
        OPin[0] = new OutputPin("", 8, 9, 0, -1, 0, 0, 0);
        OPin[1] = new OutputPin("", 0, 2, 1, 0, 0, 0, 0);
        ComposantFont = Font.font("Sans-serif", 10);
        ComponentName = "Rail courbe 90\260 auto";
        ClassName = "RailCourbe90d8x8Auto";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public RailCourbe90d8x8Auto(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Count = 0;
        Train = false;
        COLLISION = false;
        oldH = 0;
        oldT = 0;
        lngr = 50;
        NbrW = 3;
        ComposantFont = Font.font("Sans-serif", 10);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        RailCourbe90d8x8Auto railcourbe90d8x8auto = new RailCourbe90d8x8Auto(this, i, j);
        return railcourbe90d8x8auto;
    }

    public void InitBeforeSimulate()
    {
        if(Train && Horaire)
        {
            if(H == 0)
                H = 5;
            else
                H = 0;
            T = 0;
        }
        if(Train && !Horaire)
        {
            if(T == 0)
                T = 5;
            else
                T = 0;
            H = 0;
        }
    }

    public void SimulateLogic()
    {
        EH = IPin[0].getLevel();
        ET = IPin[1].getLevel();
        if(!Train)
        {
            if(EH == 5)
            {
                Count = -lngr;
                Train = true;
                Horaire = true;
            }
            if(ET == 5)
            {
                Count = lngr;
                Train = true;
                Horaire = false;
            }
        }
        if(H != oldH)
        {
            if(ET == 5)
            {
                COLLISION = true;
                DigSim.traincrash.play();
            }
            if(H == 0 && Count >= lngr && Train)
            {
                Count = 0;
                Train = false;
            }
            if(Train && Count < lngr)
                Count = Count + 4;
            if(Count == lngr)
                SH = 5;
            else
                SH = 0;
            ST = 0;
        }
        if(T != oldT)
        {
            if(EH == 5)
            {
                COLLISION = true;
                DigSim.traincrash.play();
            }
            if(T == 0 && Count <= -lngr && Train)
            {
                Count = 0;
                Train = false;
            }
            if(Train && Count > -lngr)
                Count = Count - 4;
            if(Count == -lngr)
                ST = 5;
            else
                ST = 0;
            SH = 0;
        }
        OPin[0].setLevel(SH);
        OPin[1].setLevel(ST);
        oldH = H;
        oldT = T;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setFont(ComposantFont);
        DrawInputPins(g, l, i1, k);
        DrawOutputPins(g, l, i1, k);
        if(IPin[0].PinDim.width > 0)
        {
            g.setStroke(MyColor.lightGray);
            for(int j1 = 0; j1 < 8; j1++)
            {
                for(int j3 = 2; j3 < 9; j3++)
                {
                    double d4 = Math.cos(3.1415999999999999D * (0.050000000000000003D * (double)j3 - 0.002D * (double)j1));
                    double d12 = -Math.sin(3.1415999999999999D * (0.050000000000000003D * (double)j3 - 0.002D * (double)j1));
                    DrawWithOffset.strokeLine(g,l * k + (int)(53D * d4), (i1 + 9) * k + (int)(53D * d12), l * k + (int)(68D * d4), (i1 + 9) * k + (int)(68D * d12));
                }

            }

            g.setStroke(MyColor.black);
            DrawWithOffset.strokeArc(g,(l - 8) * k + 1, (i1 + 1) * k + 1, k * 16 - 2, k * 16 - 2, 0, 90, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 8) * k, (i1 + 1) * k, k * 16, k * 16, 0, 90, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 8) * k - 1, (i1 + 1) * k - 1, k * 16 + 2, k * 16 + 2, 0, 90, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 7) * k + 1, (i1 + 2) * k + 1, k * 14 - 2, k * 14 - 2, 0, 90, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 7) * k, (i1 + 2) * k, k * 14, k * 14, 0, 90, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 7) * k - 1, (i1 + 2) * k - 1, k * 14 + 2, k * 14 + 2, 0, 90, ArcType.OPEN);
            DrawWithOffset.strokeLine(g,(l + 8) * k, (i1 + 9) * k, (l + 8) * k + 2, (i1 + 9) * k);
            DrawWithOffset.strokeLine(g,l * k, (i1 + 2) * k, l * k, (i1 + 2) * k + 2);
            if(COLLISION)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillOval(g,(l + 6) * k - 4, (i1 + 4) * k, 1 * k + 1, 1 * k + 1);
            }
            if(Train && (!Horaire || Count != lngr) && (Horaire || Count != -lngr))
            {
                for(int k1 = -NbrW; k1 <= NbrW; k1++)
                {
                    double d = Math.sin(0.78539999999999999D * (1.0D - (1.0D * (double)Count) / (double)lngr));
                    double d8 = Math.cos(0.78539999999999999D * (1.0D - (1.0D * (double)Count) / (double)lngr));
                    CX = 30D * (-1.6100000000000001D + 2.02D * Math.sin((0.78539999999999999D * (double)(Count + lngr)) / (double)lngr));
                    CY = 30D * (1.6100000000000001D - 2.02D * Math.cos((0.78539999999999999D * (double)(Count + lngr)) / (double)lngr));
                    g.setFill(MyColor.black);
                    DrawWithOffset.fillOval(g,(l + 5) * k + 1 + (int)Math.round(CX + 3D * (double)k1 * d), (i1 + 2) * k + 1 + (int)Math.round(CY + 3D * (double)k1 * d8), 2 * k, 2 * k);
                }

            }
            DrawWithOffset.fillText(g,"...", l * k, (i1 + 1) * k + 5);
        } else
        if(IPin[0].PinDim.height > 0)
        {
            g.setStroke(MyColor.lightGray);
            for(int l1 = 0; l1 < 8; l1++)
            {
                for(int k3 = 2; k3 < 9; k3++)
                {
                    double d5 = Math.cos(3.1415999999999999D * (0.050000000000000003D * (double)k3 + 0.002D * (double)l1));
                    double d13 = Math.sin(3.1415999999999999D * (0.050000000000000003D * (double)k3 + 0.002D * (double)l1));
                    DrawWithOffset.strokeLine(g,l * k + (int)(53D * d5), i1 * k + (int)(53D * d13), l * k + (int)(68D * d5), i1 * k + (int)(68D * d13));
                }

            }

            g.setStroke(MyColor.black);
            DrawWithOffset.strokeArc(g,(l - 8) * k + 1, (i1 - 8) * k + 1, k * 16 - 2, k * 16 - 2, 0, -90, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 8) * k, (i1 - 8) * k, k * 16, k * 16, 0, -90, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 8) * k - 1, (i1 - 8) * k - 1, k * 16 + 2, k * 16 + 2, 0, -90, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 7) * k + 1, (i1 - 7) * k + 1, k * 14 - 2, k * 14 - 2, 0, -90, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 7) * k, (i1 - 7) * k, k * 14, k * 14, 0, -90, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 7) * k - 1, (i1 - 7) * k - 1, k * 14 + 2, k * 14 + 2, 0, -90, ArcType.OPEN);
            DrawWithOffset.strokeLine(g,l * k, (i1 + 8) * k, l * k, (i1 + 8) * k + 2);
            DrawWithOffset.strokeLine(g,(l + 7) * k, i1 * k, (l + 7) * k - 2, i1 * k);
            if(COLLISION)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillOval(g,(l + 6) * k - 4, (i1 + 4) * k, 1 * k + 1, 1 * k + 1);
            }
            if(Train && (!Horaire || Count != lngr) && (Horaire || Count != -lngr))
            {
                for(int i2 = -NbrW; i2 <= NbrW; i2++)
                {
                    double d1 = -Math.cos(0.78539999999999999D * (1.0D - (1.0D * (double)Count) / (double)lngr));
                    double d9 = Math.sin(0.78539999999999999D * (1.0D - (1.0D * (double)Count) / (double)lngr));
                    CX = 30D * (-1.6100000000000001D + 2.02D * Math.cos((0.78539999999999999D * (double)(Count + lngr)) / (double)lngr));
                    CY = 30D * (-1.6100000000000001D + 2.02D * Math.sin((0.78539999999999999D * (double)(Count + lngr)) / (double)lngr));
                    g.setFill(MyColor.black);
                    DrawWithOffset.fillOval(g,(l + 5) * k + 1 + (int)Math.round(CX + 3D * (double)i2 * d1), (i1 + 5) * k + 1 + (int)Math.round(CY + 3D * (double)i2 * d9), 2 * k, 2 * k);
                }

            }
            DrawWithOffset.fillText(g,"...", l * k, (i1 + 7) * k + 5);
        } else
        if(IPin[0].PinDim.width < 0)
        {
            g.setStroke(MyColor.lightGray);
            for(int j2 = 0; j2 < 8; j2++)
            {
                for(int l3 = 2; l3 < 9; l3++)
                {
                    double d6 = -Math.cos(3.1415999999999999D * (0.050000000000000003D * (double)l3 + 0.002D * (double)j2));
                    double d14 = Math.sin(3.1415999999999999D * (0.050000000000000003D * (double)l3 + 0.002D * (double)j2));
                    DrawWithOffset.strokeLine(g,(l + 9) * k + (int)(53D * d6), i1 * k + (int)(53D * d14), (l + 9) * k + (int)(68D * d6), i1 * k + (int)(68D * d14));
                }

            }

            g.setStroke(MyColor.black);
            DrawWithOffset.strokeArc(g,(l + 1) * k + 1, (i1 - 8) * k + 1, k * 16 - 2, k * 16 - 2, 180, 90, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 1) * k, (i1 - 8) * k, k * 16, k * 16, 180, 90, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 1) * k - 1, (i1 - 8) * k - 1, k * 16 + 2, k * 16 + 2, 180, 90, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 2) * k + 1, (i1 - 7) * k + 1, k * 14 - 2, k * 14 - 2, 180, 90, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 2) * k, (i1 - 7) * k, k * 14, k * 14, 180, 90, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 2) * k - 1, (i1 - 7) * k - 1, k * 14 + 2, k * 14 + 2, 180, 90, ArcType.OPEN);
            DrawWithOffset.strokeLine(g,(l + 9) * k, (i1 + 7) * k, (l + 9) * k, (i1 + 7) * k - 2);
            DrawWithOffset.strokeLine(g,(l + 1) * k, i1 * k, (l + 1) * k - 2, i1 * k);
            if(COLLISION)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillOval(g,(l + 3) * k - 4, (i1 + 4) * k, 1 * k + 1, 1 * k + 1);
            }
            if(Train && (!Horaire || Count != lngr) && (Horaire || Count != -lngr))
            {
                for(int k2 = -NbrW; k2 <= NbrW; k2++)
                {
                    double d2 = -Math.sin(0.78539999999999999D * (1.0D - (1.0D * (double)Count) / (double)lngr));
                    double d10 = -Math.cos(0.78539999999999999D * (1.0D - (1.0D * (double)Count) / (double)lngr));
                    CX = 30D * (1.6100000000000001D - 2.02D * Math.sin((0.78539999999999999D * (double)(Count + lngr)) / (double)lngr));
                    CY = 30D * (-1.6100000000000001D + 2.02D * Math.cos((0.78539999999999999D * (double)(Count + lngr)) / (double)lngr));
                    g.setFill(MyColor.black);
                    DrawWithOffset.fillOval(g,(l + 2) * k + 1 + (int)Math.round(CX + 3D * (double)k2 * d2), (i1 + 5) * k + 1 + (int)Math.round(CY + 3D * (double)k2 * d10), 2 * k, 2 * k);
                }

            }
            DrawWithOffset.fillText(g,"...", (l + 8) * k, (i1 + 7) * k + 5);
        } else
        if(IPin[0].PinDim.height < 0)
        {
            g.setStroke(MyColor.lightGray);
            for(int l2 = 0; l2 < 8; l2++)
            {
                for(int i4 = 2; i4 < 9; i4++)
                {
                    double d7 = -Math.cos(3.1415999999999999D * (0.050000000000000003D * (double)i4 - 0.002D * (double)l2));
                    double d15 = -Math.sin(3.1415999999999999D * (0.050000000000000003D * (double)i4 - 0.002D * (double)l2));
                    DrawWithOffset.strokeLine(g,(l + 9) * k + (int)(53D * d7), (i1 + 9) * k + (int)(53D * d15), (l + 9) * k + (int)(68D * d7), (i1 + 9) * k + (int)(68D * d15));
                }

            }

            g.setStroke(MyColor.black);
            DrawWithOffset.strokeArc(g,(l + 1) * k + 1, (i1 + 1) * k + 1, k * 16 - 2, k * 16 - 2, 180, -90, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 1) * k, (i1 + 1) * k, k * 16, k * 16, 180, -90, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 1) * k - 1, (i1 + 1) * k - 1, k * 16 + 2, k * 16 + 2, 180, -90, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 2) * k + 1, (i1 + 2) * k + 1, k * 14 - 2, k * 14 - 2, 180, -90, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 2) * k, (i1 + 2) * k, k * 14, k * 14, 180, -90, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 2) * k - 1, (i1 + 2) * k - 1, k * 14 + 2, k * 14 + 2, 180, -90, ArcType.OPEN);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 9) * k, (l + 2) * k + 2, (i1 + 9) * k);
            DrawWithOffset.strokeLine(g,(l + 9) * k, (i1 + 1) * k, (l + 9) * k, (i1 + 1) * k - 2);
            if(COLLISION)
            {
                g.setFill(MyColor.red);
                DrawWithOffset.fillOval(g,(l + 3) * k - 4, (i1 + 4) * k, 1 * k + 1, 1 * k + 1);
            }
            if(Train && (!Horaire || Count != lngr) && (Horaire || Count != -lngr))
            {
                for(int i3 = -NbrW; i3 <= NbrW; i3++)
                {
                    double d3 = Math.cos(0.78539999999999999D * (1.0D - (1.0D * (double)Count) / (double)lngr));
                    double d11 = -Math.sin(0.78539999999999999D * (1.0D - (1.0D * (double)Count) / (double)lngr));
                    CX = 30D * (1.6100000000000001D - 2.02D * Math.cos((0.78539999999999999D * (double)(Count + lngr)) / (double)lngr));
                    CY = 30D * (1.6100000000000001D - 2.02D * Math.sin((0.78539999999999999D * (double)(Count + lngr)) / (double)lngr));
                    g.setFill(MyColor.black);
                    DrawWithOffset.fillOval(g,(l + 2) * k + 1 + (int)Math.round(CX + 3D * (double)i3 * d3), (i1 + 2) * k + 1 + (int)Math.round(CY + 3D * (double)i3 * d11), 2 * k, 2 * k);
                }

            }
            DrawWithOffset.fillText(g,"...", (l + 8) * k, (i1 + 1) * k + 5);
        }
    }

    public void Simulate(int i)
    {
        InformConnectedComponentsOldLevel(i);
    }

    int Count;
    boolean Train;
    boolean Horaire;
    boolean COLLISION;
    int EH;
    int H;
    int T;
    int ET;
    int SH;
    int ST;
    int oldH;
    int oldT;
    int lngr;
    double CX;
    double CY;
    int NbrW;
    public Font ComposantFont;
}
