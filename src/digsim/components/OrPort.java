package digsim.components;

import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

// Referenced classes of package digsim:
//            LogicPort, InputPin, OutputPin, ElectronicComponent

public class OrPort extends LogicPort
{

    public OrPort(int i, int j, int k, int l)
    {
        super(i, j, k, l);
    }

    public OrPort(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        return this;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        g.setStroke(ComponentColor);
        if(IPin[0].PinDim.width > 0)
        {
            g.setFill(Color.rgb(255, 255, 102));
            double ai[] = {
                (l + 3) * k + 1, (l + 3) * k - 2, (l + 5) * k, (l + 6) * k, (l + 7) * k, (l + 6) * k, (l + 5) * k, (l + 3) * k - 2
            };
            double ai4[] = {
                (i1 + 3) * k, (i1 + 1) * k, (i1 + 2) * k - 5, (i1 + 2) * k, (i1 + 3) * k, (i1 + 4) * k, (i1 + 4) * k + 5, (i1 + 5) * k + 1
            };
            DrawWithOffset.fillPolygon(g,ai, ai4, ai.length);
            g.setStroke(ComponentColor);
            DrawWithOffset.strokeArc(g,(l + 2) * k, (i1 + 1) * k, k, k * 4, -90, 180, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 3) * k, (i1 + 1) * k, k * 11 + 4, k * 12, 42, 50, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 3) * k, (i1 - 7) * k, k * 11 + 4, k * 12, -42, -50, ArcType.OPEN);
        } else
        if(IPin[0].PinDim.width < 0)
        {
            g.setFill(Color.rgb(255, 255, 102));
            double ai1[] = {
                (l + 7) * k + 1, (l + 5) * k, (l + 4) * k, (l + 3) * k - 1, (l + 4) * k, (l + 5) * k, (l + 7) * k + 1
            };
            double ai5[] = {
                (i1 + 1) * k, (i1 + 2) * k - 5, (i1 + 2) * k, (i1 + 3) * k, (i1 + 4) * k, (i1 + 4) * k + 5, (i1 + 5) * k + 1
            };
            DrawWithOffset.fillPolygon(g,ai1, ai5, ai1.length);
            g.setStroke(ComponentColor);
            DrawWithOffset.strokeArc(g,(l + 7) * k, (i1 + 1) * k, k, k * 4, -90, -180, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 1) * k, (i1 - 9) * k, k * 12, k * 14, -135, 50, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 1) * k, (i1 + 1) * k, k * 12, k * 14, -224, -50, ArcType.OPEN);
        } else
        if(IPin[0].PinDim.height > 0)
        {
            g.setFill(Color.rgb(255, 255, 102));
            double ai2[] = {
                (l + 3) * k, (l + 4) * k - 6, (l + 4) * k - 1, (l + 5) * k, (l + 6) * k, (l + 6) * k + 5, (l + 7) * k + 1
            };
            double ai6[] = {
                (i1 + 1) * k, (i1 + 3) * k, (i1 + 4) * k, (i1 + 5) * k + 2, (i1 + 4) * k, (i1 + 3) * k, (i1 + 1) * k
            };
            DrawWithOffset.fillPolygon(g,ai2, ai6, ai2.length);
            g.setStroke(ComponentColor);
            DrawWithOffset.strokeArc(g,(l + 3) * k, i1 * k, k * 4, k, 0, -180, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 8) * k, (i1 - 5) * k, k * 15, k * 12, -45, 50, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 3) * k, (i1 - 4) * k, k * 15, k * 11, -137, -53, ArcType.OPEN);
        } else
        if(IPin[0].PinDim.height < 0)
        {
            g.setFill(Color.rgb(255, 255, 102));
            double ai3[] = {
                (l + 3) * k, (l + 4) * k - 4, (l + 4) * k, (l + 5) * k, (l + 6) * k, (l + 6) * k + 5, (l + 7) * k
            };
            double ai7[] = {
                (i1 + 5) * k + 1, (i1 + 3) * k, (i1 + 2) * k, (i1 + 1) * k - 2, (i1 + 2) * k, (i1 + 3) * k, (i1 + 5) * k + 1
            };
            DrawWithOffset.fillPolygon(g,ai3, ai7, ai3.length);
            g.setStroke(ComponentColor);
            DrawWithOffset.strokeArc(g,(l + 3) * k, (i1 + 5) * k, k * 4, k, 0, 180, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l + 3) * k, (i1 - 1) * k, k * 12, k * 14, -228, 45, ArcType.OPEN);
            DrawWithOffset.strokeArc(g,(l - 5) * k, (i1 - 1) * k, k * 12, k * 14, 48, -45, ArcType.OPEN);
        }
    }

    public void SimulateLogic()
    {
        boolean flag = false;
        int j = 0;
        for(int i = 0; i < Inputs; i++)
            if(IPin[i].getLevel() == 5)
                j = 5;

        OPin[0].Level = j;
    }
}
