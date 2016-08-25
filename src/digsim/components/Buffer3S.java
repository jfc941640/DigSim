package digsim.components;

import digsim.DrawWithOffset;
import digsim.ElectronicComponent;
import digsim.InputPin;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Referenced classes of package digsim:
//            LogicPort, InputPin, OutputPin, Pin,
//            ElectronicComponent

public class Buffer3S extends LogicPort
{

    public Buffer3S(Pin apin[][], int i, int j)
    {
        super(2, i, j, 0);
        ComponentName = "Buffer 1 bit a sortie 3S";
        ClassName = "Buffer3S";
        Can_Rotate = true;
        IPin[0] = new InputPin("", 1, 3, 2, 0, 0, 0, 0);
        IPin[1] = new InputPin("", 5, 6, 0, -1, 0, 0, 0);
        RegisterPins(apin, i, j);
        OPin[0].setLevel(0x6c9d8);
        IPin[1].setLevel(0);
    }

    public Buffer3S(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        OPin[0].setLevel(0x6c9d8);
        IPin[1].setLevel(0);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Buffer3S buffer3s = new Buffer3S(this, i, j);
        return buffer3s;
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
                (l + 3) * k, (l + 7) * k, (l + 3) * k
            };
            double ai4[] = {
                (i1 + 1) * k, (i1 + 3) * k, (i1 + 5) * k
            };
            DrawWithOffset.fillPolygon(g,ai, ai4, ai.length);
            g.setStroke(ComponentColor);
            DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 1) * k, (l + 3) * k, (i1 + 5) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 1) * k, (l + 7) * k, (i1 + 3) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 5) * k, (l + 7) * k, (i1 + 3) * k);
            DrawWithOffset.strokeLine(g,(l + 5) * k, (i1 + 4) * k, (l + 5) * k, (i1 + 5) * k);
        } else
        if(IPin[0].PinDim.height < 0)
        {
            g.setFill(Color.rgb(255, 255, 102));
            double ai1[] = {
                (l + 3) * k, (l + 7) * k, (l + 5) * k
            };
            double ai5[] = {
                (i1 + 5) * k, (i1 + 5) * k, (i1 + 1) * k
            };
            DrawWithOffset.fillPolygon(g,ai1, ai5, ai1.length);
            g.setStroke(ComponentColor);
            DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 5) * k, (l + 7) * k, (i1 + 5) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 5) * k, (l + 5) * k, (i1 + 1) * k);
            DrawWithOffset.strokeLine(g,(l + 7) * k, (i1 + 5) * k, (l + 5) * k, (i1 + 1) * k);
            DrawWithOffset.strokeLine(g,(l + 6) * k, (i1 + 3) * k, (l + 7) * k, (i1 + 3) * k);
        } else
        if(IPin[0].PinDim.width < 0)
        {
            g.setFill(Color.rgb(255, 255, 102));
            double ai2[] = {
                (l + 7) * k, (l + 3) * k, (l + 7) * k
            };
            double ai6[] = {
                (i1 + 1) * k, (i1 + 3) * k, (i1 + 5) * k
            };
            DrawWithOffset.fillPolygon(g,ai2, ai6, ai2.length);
            g.setStroke(ComponentColor);
            DrawWithOffset.strokeLine(g,(l + 7) * k, (i1 + 1) * k, (l + 7) * k, (i1 + 5) * k);
            DrawWithOffset.strokeLine(g,(l + 7) * k, (i1 + 1) * k, (l + 3) * k, (i1 + 3) * k);
            DrawWithOffset.strokeLine(g,(l + 7) * k, (i1 + 5) * k, (l + 3) * k, (i1 + 3) * k);
            DrawWithOffset.strokeLine(g,(l + 5) * k, (i1 + 1) * k, (l + 5) * k, (i1 + 2) * k);
        } else
        if(IPin[0].PinDim.height > 0)
        {
            g.setFill(Color.rgb(255, 255, 102));
            double ai3[] = {
                (l + 3) * k, (l + 7) * k, (l + 5) * k
            };
            double ai7[] = {
                (i1 + 1) * k, (i1 + 1) * k, (i1 + 5) * k
            };
            DrawWithOffset.fillPolygon(g,ai3, ai7, ai3.length);
            g.setStroke(ComponentColor);
            DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 1) * k, (l + 7) * k, (i1 + 1) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 1) * k, (l + 5) * k, (i1 + 5) * k);
            DrawWithOffset.strokeLine(g,(l + 7) * k, (i1 + 1) * k, (l + 5) * k, (i1 + 5) * k);
            DrawWithOffset.strokeLine(g,(l + 3) * k, (i1 + 3) * k, (l + 4) * k, (i1 + 3) * k);
        }
        g.setFill(ComponentColor);
        DrawWithOffset.fillText(g,"OE", (l + 4) * k + 1, (i1 + 3) * k + 4);
    }

    public void SimulateLogic()
    {
        if(IPin[1].getLevel() == 0)
            OPin[0].setLevel(0x6c9d8);
        else
            OPin[0].Level = IPin[0].getLevel();
    }
}
