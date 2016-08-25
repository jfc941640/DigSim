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

public class Buffer extends LogicPort
{

    public Buffer(Pin apin[][], int i, int j)
    {
        super(1, i, j, 2);
        ComponentName = "Etage tampon (buffer)";
        ClassName = "Buffer";
        Can_Rotate = true;
        IPin[0] = new InputPin("A", 1, 3, 2, 0, 0, 0, 2);
        RegisterPins(apin, i, j);
    }

    public Buffer(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Buffer buffer = new Buffer(this, i, j);
        return buffer;
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
        }
    }

    public void SimulateLogic()
    {
        OPin[0].Level = IPin[0].getLevel();
    }
}
