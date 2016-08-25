package digsim;

import java.awt.Graphics;
import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

// Referenced classes of package digsim:
//            ComponentPin, MyColor

public class InputPin extends ComponentPin
{

    public InputPin(InputPin inputpin)
    {
        super(inputpin);
        AlreadyInformed = false;
        LevelChanged = 0;
        Looping = false;
    }

    public InputPin(String s, int i, int j, int k, int l, int i1, int j1,
            int k1)
    {
        super(s, i, j, k, l, i1, j1, k1);
        AlreadyInformed = false;
        LevelChanged = 0;
        Looping = false;
        if(l > 0 && (k1 & 1) == 1 || l > 0 && (k1 & 0x40) == 1)
        {
            j += l;
            PinPos = new Point(i, j);
        }
    }

    public void InitBeforeSimulate()
    {
        ReceivedSimulationCycleID = -1;
        LevelChanged = 0;
        Looping = false;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        if(Looping)
        {
            int l = PinPos.x + i;
            int i1 = PinPos.y + j;
            g.setStroke(MyColor.magenta);
            DrawWithOffset.strokeArc(g,(l - 2) * k, (int)(((double)i1 - 1.5D) * (double)k), k * 2, k * 2, 0, 270, ArcType.OPEN);
            DrawWithOffset.strokeLine(g,l * k, (int)(((double)i1 - 0.5D) * (double)k), (int)(((double)l + 0.5D) * (double)k), (i1 - 1) * k);
            DrawWithOffset.strokeLine(g,l * k, (int)(((double)i1 - 0.5D) * (double)k), (int)(((double)l - 0.5D) * (double)k), (i1 - 1) * k);
            Looping = false;
        }
    }

    boolean AlreadyInformed;
    int ReceivedSimulationCycleID;
    int LevelChanged;
    boolean Looping;
    static final int PIN_NEGATIVE = 1;
    static final int PIN_NEGATIVE_NOACTION = 64;
}
