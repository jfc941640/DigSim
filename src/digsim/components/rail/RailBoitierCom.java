package digsim.components.rail;

import digsim.InputPin;
import digsim.MyColor;
import digsim.OutputPin;
import digsim.Pin;
import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, OutputPin, MyColor,
//            Pin

public class RailBoitierCom extends ElectronicComponent
{

    public RailBoitierCom(Pin apin[][], int i, int j)
    {
        super(i, j, 15, 3, 1, 1, 4, 1, 3, 2);
        c = -1;
        b = -1;
        a = -1;
        CTRc0 = 0;
        CTRc5 = 0;
        STOP = false;
        IPin[0] = new InputPin("a", 4, 3, 0, -1, 0, 0, 0);
        IPin[1] = new InputPin("b", 3, 3, 0, -1, 0, 0, 0);
        IPin[2] = new InputPin("s", 2, 3, 0, -1, 0, 0, 0);
        OPin[0] = new OutputPin("", 3, -1, 0, 2, 0, 0, 0);
        OPin[1] = new OutputPin("", 2, 0, 0, 1, 0, 0, 0);
        IPin[0].setLevel(-1);
        IPin[1].setLevel(-1);
        IPin[2].setLevel(-1);
        ComponentName = "Boitier de commande des rails";
        ClassName = "RailBoitierCom";
        Can_Rotate = true;
        RegisterPins(apin, i, j);
    }

    public RailBoitierCom(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        c = -1;
        b = -1;
        a = -1;
        CTRc0 = 0;
        CTRc5 = 0;
        STOP = false;
        IPin[0].setLevel(-1);
        IPin[1].setLevel(-1);
        IPin[2].setLevel(-1);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        RailBoitierCom railboitiercom = new RailBoitierCom(this, i, j);
        return railboitiercom;
    }

    public boolean SimMouseDown()
    {
        STOP = !STOP;
        return true;
    }

    public void InitBeforeSimulate()
    {
        OPin[0].InitBeforeSimulate();
        if(H0 == 0)
            H0 = 5;
        else
            H0 = 0;
    }

    public void SimulateLogic()
    {
        char c1 = '\310';
        char c2 = '\u0190';
        char c3 = '\u0258';
        if(c == 5 && b == 5 && a == 5)
        {
            if(CTRc5 < c3)
                CTRc5++;
            if(CTRc0 > 0)
                CTRc0--;
        } else
        if(c == 0 && b == 5 && a == 5)
        {
            if(CTRc0 < c3)
                CTRc0++;
            if(CTRc5 > 0)
                CTRc5--;
        } else
        if(c == 5 && b == 5 && a == 0)
        {
            if(CTRc5 < c2)
                CTRc5++;
            if(CTRc5 > c2)
                CTRc5--;
            if(CTRc0 > 0)
                CTRc0--;
        } else
        if(c == 0 && b == 5 && a == 0)
        {
            if(CTRc0 < c2)
                CTRc0++;
            if(CTRc0 > c2)
                CTRc0--;
            if(CTRc5 > 0)
                CTRc5--;
        } else
        if(c == 5 && b == 0 && a == 5)
        {
            if(CTRc5 < c1)
                CTRc5++;
            if(CTRc5 > c1)
                CTRc5--;
            if(CTRc0 > 0)
                CTRc0--;
        } else
        if(c == 0 && b == 0 && a == 5)
        {
            if(CTRc0 < c1)
                CTRc0++;
            if(CTRc0 > c1)
                CTRc0--;
            if(CTRc5 > 0)
                CTRc5--;
        } else
        {
            if(CTRc0 > 0)
                CTRc0--;
            if(CTRc5 > 0)
                CTRc5--;
        }
        if(CTRc5 >= c1 - 100 && CTRc5 < c2 && CTRc0 < c1)
        {
            OPin[0].Level = H0;
            OPin[1].Level = 0;
        } else
        if(CTRc5 >= c2 && CTRc5 < c3 && CTRc0 < c1)
        {
            OPin[0].Level = H1;
            OPin[1].Level = 0;
        } else
        if(CTRc5 >= c3 && CTRc0 < c1)
        {
            OPin[0].Level = H3;
            OPin[1].Level = 0;
        } else
        if(CTRc0 >= c1 - 100 && CTRc0 < c2 && CTRc5 < c1)
        {
            OPin[1].Level = H0;
            OPin[0].Level = 0;
        } else
        if(CTRc0 >= c2 && CTRc0 < c3 && CTRc5 < c1)
        {
            OPin[1].Level = H1;
            OPin[0].Level = 0;
        } else
        if(CTRc0 >= c3 && CTRc5 < c1)
        {
            OPin[1].Level = H3;
            OPin[0].Level = 0;
        } else
        {
            OPin[0].Level = 0;
            OPin[1].Level = 0;
        }
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        g.setTextBaseline(VPos.BOTTOM);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        DrawOutputPins(g, l, i1, k);
        if(IPin[0].PinDim.height < 0)
        {
            if(c != -1 && b != -1 && a != -1)
                if(c == 0)
                {
                    g.setFill(Color.rgb(192, 192, 255));
                    DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 4 * k, 1 * k);
                    g.setFill(Color.rgb(64, 64, 255));
                    if(b == 0 && a == 5)
                        DrawWithOffset.fillRect(g,(l + 1) * k + 22, (i1 + 1) * k, 0 * k + 11, 1 * k);
                    else
                    if(b == 5 && a == 0)
                        DrawWithOffset.fillRect(g,(l + 1) * k + 13, (i1 + 1) * k, 0 * k + 20, 1 * k);
                    else
                    if(b == 5 && a == 5)
                        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 4 * k, 1 * k);
                    g.setFill(MyColor.black);
                    if(!STOP)
                        DrawWithOffset.fillText(g,"\u2190", (l + 2) * k - 4, (i1 + 2) * k);
                } else
                if(c == 5)
                {
                    g.setFill(Color.rgb(255, 192, 192));
                    DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 4 * k, 1 * k);
                    g.setFill(Color.rgb(255, 64, 64));
                    if(b == 0 && a == 5)
                        DrawWithOffset.fillRect(g,(l + 1) * k + 22, (i1 + 1) * k, 0 * k + 11, 1 * k);
                    else
                    if(b == 5 && a == 0)
                        DrawWithOffset.fillRect(g,(l + 1) * k + 13, (i1 + 1) * k, 0 * k + 20, 1 * k);
                    else
                    if(b == 5 && a == 5)
                        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 4 * k, 1 * k);
                    g.setFill(MyColor.black);
                    if(!STOP)
                        DrawWithOffset.fillText(g,"\u2192", (l + 2) * k - 4, (i1 + 2) * k);
                }
            DrawWithOffset.strokeRect(g,(l + 1) * k, (i1 + 1) * k, 4 * k, 1 * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k - 1, i1 * k, (l + 2) * k - 1, (i1 + 1) * k);
        } else
        if(IPin[0].PinDim.width > 0)
        {
            if(c != -1 && b != -1 && a != -1)
                if(c == 0)
                {
                    g.setFill(Color.rgb(192, 192, 255));
                    DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 1 * k, 4 * k);
                    g.setFill(Color.rgb(64, 64, 255));
                    if(b == 0 && a == 5)
                        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k + 21, 1 * k, 0 * k + 12);
                    else
                    if(b == 5 && a == 0)
                        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k + 13, 1 * k, 0 * k + 20);
                    else
                    if(b == 5 && a == 5)
                        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 1 * k, 4 * k);
                    g.setFill(MyColor.black);
                    if(!STOP)
                        DrawWithOffset.fillText(g,"\u2191", (l + 1) * k + 2, (i1 + 2) * k + 4);
                } else
                if(c == 5)
                {
                    g.setFill(Color.rgb(255, 192, 192));
                    DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 1 * k, 4 * k);
                    g.setFill(Color.rgb(255, 64, 64));
                    if(b == 0 && a == 5)
                        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k + 21, 1 * k, 0 * k + 12);
                    else
                    if(b == 5 && a == 0)
                        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k + 13, 1 * k, 0 * k + 20);
                    else
                    if(b == 5 && a == 5)
                        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 1 * k, 4 * k);
                    g.setFill(MyColor.black);
                    if(!STOP)
                        DrawWithOffset.fillText(g,"\u2193", (l + 1) * k + 2, (i1 + 2) * k + 4);
                }
            DrawWithOffset.strokeRect(g,(l + 1) * k, (i1 + 1) * k, 1 * k, 4 * k);
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k - 1, (l + 3) * k, (i1 + 2) * k - 1);
        } else
        if(IPin[0].PinDim.height > 0)
        {
            if(c != -1 && b != -1 && a != -1)
                if(c == 0)
                {
                    g.setFill(Color.rgb(192, 192, 255));
                    DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 4 * k, 1 * k);
                    g.setFill(Color.rgb(64, 64, 255));
                    if(b == 0 && a == 5)
                        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 0 * k + 11, 1 * k);
                    else
                    if(b == 5 && a == 0)
                        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 0 * k + 20, 1 * k);
                    else
                    if(b == 5 && a == 5)
                        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 4 * k, 1 * k);
                    g.setFill(MyColor.black);
                    if(!STOP)
                        DrawWithOffset.fillText(g,"\u2192", (l + 4) * k - 4, (i1 + 2) * k);
                } else
                if(c == 5)
                {
                    g.setFill(Color.rgb(255, 192, 192));
                    DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 4 * k, 1 * k);
                    g.setFill(Color.rgb(255, 64, 64));
                    if(b == 0 && a == 5)
                        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 0 * k + 11, 1 * k);
                    else
                    if(b == 5 && a == 0)
                        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 0 * k + 20, 1 * k);
                    else
                    if(b == 5 && a == 5)
                        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 4 * k, 1 * k);
                    g.setFill(MyColor.black);
                    if(!STOP)
                        DrawWithOffset.fillText(g,"\u2190", (l + 4) * k - 4, (i1 + 2) * k);
                }
            DrawWithOffset.strokeRect(g,(l + 1) * k, (i1 + 1) * k, 4 * k, 1 * k);
            DrawWithOffset.strokeLine(g,(l + 4) * k + 1, (i1 + 2) * k, (l + 4) * k + 1, (i1 + 3) * k);
        } else
        if(IPin[0].PinDim.width < 0)
        {
            if(c != -1 && b != -1 && a != -1)
                if(c == 0)
                {
                    g.setFill(Color.rgb(192, 192, 255));
                    DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 1 * k, 4 * k);
                    g.setFill(Color.rgb(64, 64, 255));
                    if(b == 0 && a == 5)
                        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 1 * k, 0 * k + 12);
                    else
                    if(b == 5 && a == 0)
                        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 1 * k, 0 * k + 20);
                    else
                    if(b == 5 && a == 5)
                        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 1 * k, 4 * k);
                    g.setFill(MyColor.black);
                    if(!STOP)
                        DrawWithOffset.fillText(g,"\u2193", (l + 1) * k + 2, (i1 + 4) * k + 4);
                } else
                if(c == 5)
                {
                    g.setFill(Color.rgb(255, 192, 192));
                    DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 1 * k, 4 * k);
                    g.setFill(Color.rgb(255, 64, 64));
                    if(b == 0 && a == 5)
                        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 1 * k, 0 * k + 12);
                    else
                    if(b == 5 && a == 0)
                        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 1 * k, 0 * k + 20);
                    else
                    if(b == 5 && a == 5)
                        DrawWithOffset.fillRect(g,(l + 1) * k, (i1 + 1) * k, 1 * k, 4 * k);
                    g.setFill(MyColor.black);
                    if(!STOP)
                        DrawWithOffset.fillText(g,"\u2191", (l + 1) * k + 2, (i1 + 4) * k + 4);
                }
            DrawWithOffset.strokeRect(g,(l + 1) * k, (i1 + 1) * k, 1 * k, 4 * k);
            DrawWithOffset.strokeLine(g,l * k, (i1 + 4) * k + 1, (l + 1) * k, (i1 + 4) * k + 1);
        }
        if(STOP)
            DrawInputPins(g, l, i1, k);
    }

    public void Simulate(int i)
    {
        InformConnectedComponents(i);
        if(H0 == oldH0)
            H1 = 0;
        else
            H1 = 5;
        oldH0 = H0;
        if(H1 == oldH1)
            H2 = 0;
        else
            H2 = 5;
        oldH1 = H1;
        if(H2 == oldH2)
            H3 = 0;
        else
            H3 = 5;
        oldH2 = H2;
        if(STOP)
        {
            b = a = 0;
        } else
        {
            b = IPin[1].getLevel();
            a = IPin[0].getLevel();
        }
        c = IPin[2].getLevel();
    }

    int E;
    int H0;
    int H1;
    int H2;
    int H3;
    int oldH0;
    int oldH1;
    int oldH2;
    int c;
    int b;
    int a;
    int CTRc0;
    int CTRc5;
    boolean STOP;
}
