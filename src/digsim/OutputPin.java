package digsim;

import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            ComponentPin, MyColor

public class OutputPin extends ComponentPin
{

    public OutputPin(OutputPin outputpin)
    {
        super(outputpin);
        ShortCircuit = false;
    }

    public OutputPin(String s, int i, int j, int k, int l, int i1, int j1,
            int k1)
    {
        super(s, i, j, k, l, i1, j1, k1);
        ShortCircuit = false;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        if(ShortCircuit)
        {
            int l = PinPos.x + i;
            int i1 = PinPos.y + j;
            g.setStroke(MyColor.red);
            DrawWithOffset.strokeLine(g,l * k, i1 * k, (l + 1) * k, (i1 - 1) * k);
            DrawWithOffset.strokeLine(g,(l + 1) * k, (i1 - 1) * k, (l + 1) * k, (int)(((double)i1 - 0.5D) * (double)k));
            DrawWithOffset.strokeLine(g,(l + 1) * k, (int)(((double)i1 - 0.5D) * (double)k), (l + 2) * k, (int)(((double)i1 - 1.5D) * (double)k));
            DrawWithOffset.strokeLine(g,l * k, i1 * k, (int)(((double)l + 0.25D) * (double)k), i1 * k);
            DrawWithOffset.strokeLine(g,l * k, i1 * k, l * k, (int)(((double)i1 - 0.25D) * (double)k));
        }
    }

    public void InitBeforeSimulate()
    {
        ShortCircuit = false;
    }

    boolean ShortCircuit;
}
