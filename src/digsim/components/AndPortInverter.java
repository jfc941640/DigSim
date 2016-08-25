package digsim.components;

import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

// Referenced classes of package digsim:
//            LogicPortAndInverter, InputPin, OutputPin, ElectronicComponent

public class AndPortInverter extends LogicPortAndInverter
{

    public AndPortInverter(int i, int j, int k, int l)
    {
        super(i, j, k, l);
    }

    public AndPortInverter(ElectronicComponent electroniccomponent, int i, int j)
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
            DrawWithOffset.fillRect(g,(l + 3) * k, (i1 + 1) * k, k * 2, k * 4);
            DrawWithOffset.fillArc(g,(int)(((double)l + 2.7999999999999998D) * (double)k), (i1 + 1) * k, (int)((double)k * 4.5D), k * 4, -90, 180, ArcType.OPEN);
            g.setStroke(ComponentColor);
            DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 1) * k, (l + 3) * k, (i1 + 5) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 1) * k, (l + 5) * k, (i1 + 1) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 5) * k, (l + 5) * k, (i1 + 5) * k);
            DrawWithOffset.strokeArc(g,(int)(((double)l + 2.7999999999999998D) * (double)k), (i1 + 1) * k, (int)((double)k * 4.5D), k * 4, -90, 180, ArcType.OPEN);
        } else
        if(IPin[0].PinDim.width < 0)
        {
            g.setFill(Color.rgb(255, 255, 102));
            DrawWithOffset.fillRect(g,(l + 5) * k, (i1 + 1) * k, k * 2, k * 4);
            DrawWithOffset.fillArc(g,(int)(((double)l + 2.7999999999999998D) * (double)k), (i1 + 1) * k, (int)((double)k * 4.5D), k * 4, 90, 180, ArcType.OPEN);
            g.setStroke(ComponentColor);
            DrawWithOffset.strokeLine(g,(l + 7) * k, (i1 + 1) * k, (l + 7) * k, (i1 + 5) * k);
            DrawWithOffset.strokeLine(g,(l + 7) * k, (i1 + 1) * k, (l + 5) * k, (i1 + 1) * k);
            DrawWithOffset.strokeLine(g,(l + 7) * k, (i1 + 5) * k, (l + 5) * k, (i1 + 5) * k);
            DrawWithOffset.strokeArc(g,(int)(((double)l + 2.7999999999999998D) * (double)k), (i1 + 1) * k, (int)((double)k * 4.5D), k * 4, 90, 180, ArcType.OPEN);
        } else
        if(IPin[0].PinDim.height > 0)
        {
            g.setFill(Color.rgb(255, 255, 102));
            DrawWithOffset.fillRect(g,(l + 3) * k, (i1 + 1) * k, k * 4, k * 2);
            DrawWithOffset.fillArc(g,(l + 3) * k, (int)(((double)i1 + 0.80000000000000004D) * (double)k), k * 4, (int)((double)k * 4.5D), 180, 180, ArcType.OPEN);
            g.setStroke(ComponentColor);
            DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 1) * k, (l + 7) * k, (i1 + 1) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 1) * k, (l + 3) * k, (i1 + 3) * k);
            DrawWithOffset.strokeLine(g,(l + 7) * k, (i1 + 1) * k, (l + 7) * k, (i1 + 3) * k);
            DrawWithOffset.strokeArc(g,(l + 3) * k, (int)(((double)i1 + 0.80000000000000004D) * (double)k), k * 4, (int)((double)k * 4.5D), 180, 180, ArcType.OPEN);
        } else
        if(IPin[0].PinDim.height < 0)
        {
            g.setFill(Color.rgb(255, 255, 102));
            DrawWithOffset.fillRect(g,(l + 3) * k, (i1 + 3) * k, k * 4, k * 2);
            DrawWithOffset.fillArc(g,(l + 3) * k, (int)(((double)i1 + 0.80000000000000004D) * (double)k), k * 4, (int)((double)k * 4.5D), 0, 180, ArcType.OPEN);
            g.setStroke(ComponentColor);
            DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 5) * k, (l + 7) * k, (i1 + 5) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 5) * k, (l + 3) * k, (i1 + 3) * k);
            DrawWithOffset.strokeLine(g,(l + 7) * k, (i1 + 5) * k, (l + 7) * k, (i1 + 3) * k);
            DrawWithOffset.strokeArc(g,(l + 3) * k, (int)(((double)i1 + 0.80000000000000004D) * (double)k), k * 4, (int)((double)k * 4.5D), 0, 180, ArcType.OPEN);
        }
    }

    public void SimulateLogic()
    {
        boolean flag = false;
        int j = 5;
        for(int i = 0; i < Inputs; i++)
            if(IPin[i].getLevel() != 5)
                j = 0;

        OPin[0].Level = j;
        OPin[1].Level = j;
    }
}