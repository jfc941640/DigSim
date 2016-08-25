package digsim;



import digsim.Pin;
import digsim.components.Switch;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            Switch, Pin, ElectronicComponent

public class PushButton extends Switch
{

    public PushButton(Pin apin[][], int i, int j)
    {
        super(apin, i, j);
        ComponentName = "Bouton poussoir";
        ClassName = "PushButton";
        Can_Rotate = true;
        HitBox.y--;
        HitBoxSize.height++;
    }

    public PushButton(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        PushButton pushbutton = new PushButton(this, i, j);
        pushbutton.NbRotation = NbRotation;
        pushbutton.updatePinsPosition();
        return pushbutton;
    }

    public boolean SimMouseDown()
    {
        SwitchClosed = true;
        return true;
    }

    public boolean SimMouseUp()
    {
        SwitchClosed = false;
        return true;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        int j1 = 0;
        int k1 = 0;
        g.setStroke(ComponentColor);
        if(NbRotation == 0)
        {
            if(SwitchClosed)
            {
                DrawWithOffset.strokeLine(g,(l + 5) * k, i1 * k, (l + 5) * k, (int)(((double)i1 + 3.5D) * (double)k));
                DrawWithOffset.strokeLine(g,(l + 6) * k, i1 * k, (l + 6) * k, (int)(((double)i1 + 3.25D) * (double)k));
                j1 = 1;
            } else
            {
                DrawWithOffset.strokeLine(g,(l + 5) * k, i1 * k, (l + 5) * k, (int)(((double)i1 + 2.25D) * (double)k));
                DrawWithOffset.strokeLine(g,(l + 6) * k, i1 * k, (l + 6) * k, (int)(((double)i1 + 2.5D) * (double)k));
            }
            DrawWithOffset.strokeLine(g,(int)(((double)l + 4.5D) * (double)k), i1 * k, (int)(((double)l + 6.5D) * (double)k), i1 * k);
            DrawWithOffset.strokeLine(g,(int)(((double)l + 4.5D) * (double)k), (int)(((double)i1 + 1.5D + (double)j1) * (double)k), (int)(((double)l + 6.5D) * (double)k), (int)(((double)i1 + 1.5D + (double)j1) * (double)k));
            DrawWithOffset.strokeLine(g,(int)(((double)l + 4.5D) * (double)k), (int)(((double)i1 + 1.5D + (double)j1) * (double)k), (int)(((double)l + 5.5D) * (double)k), (int)(((double)i1 + 0.5D + (double)j1) * (double)k));
            DrawWithOffset.strokeLine(g,(int)(((double)l + 6.5D) * (double)k), (int)(((double)i1 + 1.5D + (double)j1) * (double)k), (int)(((double)l + 5.5D) * (double)k), (int)(((double)i1 + 0.5D + (double)j1) * (double)k));
        } else
        if(NbRotation == 1)
        {
            if(SwitchClosed)
            {
                DrawWithOffset.strokeLine(g,(int)(((double)l + 4.5D) * (double)k), (int)(((double)i1 + 2.5D) * (double)k), (l + 8) * k, (int)(((double)i1 + 2.5D) * (double)k));
                DrawWithOffset.strokeLine(g,(int)(((double)l + 4.75D) * (double)k), (int)(((double)i1 + 3.5D) * (double)k), (l + 8) * k, (int)(((double)i1 + 3.5D) * (double)k));
                k1 = 1;
            } else
            {
                DrawWithOffset.strokeLine(g,(int)(((double)l + 5.75D) * (double)k), (int)(((double)i1 + 2.5D) * (double)k), (l + 8) * k, (int)(((double)i1 + 2.5D) * (double)k));
                DrawWithOffset.strokeLine(g,(int)(((double)l + 5.5D) * (double)k), (int)(((double)i1 + 3.5D) * (double)k), (l + 8) * k, (int)(((double)i1 + 3.5D) * (double)k));
            }
            DrawWithOffset.strokeLine(g,(l + 8) * k, (i1 + 2) * k, (l + 8) * k, (i1 + 4) * k);
            DrawWithOffset.strokeLine(g,(int)((((double)l + 6.5D) - (double)k1) * (double)k), (i1 + 2) * k, (int)((((double)l + 6.5D) - (double)k1) * (double)k), (i1 + 4) * k);
            DrawWithOffset.strokeLine(g,(int)((((double)l + 6.5D) - (double)k1) * (double)k), (i1 + 2) * k, (int)((((double)l + 7.5D) - (double)k1) * (double)k), (i1 + 3) * k);
            DrawWithOffset.strokeLine(g,(int)((((double)l + 6.5D) - (double)k1) * (double)k), (i1 + 4) * k, (int)((((double)l + 7.5D) - (double)k1) * (double)k), (i1 + 3) * k);
        } else
        if(NbRotation == 2)
        {
            if(SwitchClosed)
            {
                DrawWithOffset.strokeLine(g,(l + 5) * k, (i1 + 6) * k, (l + 5) * k, (int)(((double)i1 + 2.5D) * (double)k));
                DrawWithOffset.strokeLine(g,(l + 6) * k, (i1 + 6) * k, (l + 6) * k, (int)(((double)i1 + 2.25D) * (double)k));
            } else
            {
                DrawWithOffset.strokeLine(g,(l + 5) * k, (i1 + 6) * k, (l + 5) * k, (int)(((double)i1 + 3.4500000000000002D) * (double)k));
                DrawWithOffset.strokeLine(g,(l + 6) * k, (i1 + 6) * k, (l + 6) * k, (int)(((double)i1 + 3.7000000000000002D) * (double)k));
                j1 = 1;
            }
            DrawWithOffset.strokeLine(g,(int)(((double)l + 4.5D) * (double)k), (i1 + 6) * k, (int)(((double)l + 6.5D) * (double)k), (i1 + 6) * k);
            DrawWithOffset.strokeLine(g,(int)(((double)l + 4.5D) * (double)k), (int)(((double)i1 + 3.5D + (double)j1) * (double)k), (int)(((double)l + 6.5D) * (double)k), (int)(((double)i1 + 3.5D + (double)j1) * (double)k));
            DrawWithOffset.strokeLine(g,(int)(((double)l + 4.5D) * (double)k), (int)(((double)i1 + 3.5D + (double)j1) * (double)k), (int)(((double)l + 5.5D) * (double)k), (int)(((double)i1 + 4.5D + (double)j1) * (double)k));
            DrawWithOffset.strokeLine(g,(int)(((double)l + 6.5D) * (double)k), (int)(((double)i1 + 3.5D + (double)j1) * (double)k), (int)(((double)l + 5.5D) * (double)k), (int)(((double)i1 + 4.5D + (double)j1) * (double)k));
        } else
        if(NbRotation == 3)
        {
            if(SwitchClosed)
            {
                DrawWithOffset.strokeLine(g,(l + 2) * k, (int)(((double)i1 + 2.5D) * (double)k), (int)(((double)l + 5.25D) * (double)k), (int)(((double)i1 + 2.5D) * (double)k));
                DrawWithOffset.strokeLine(g,(l + 2) * k, (int)(((double)i1 + 3.5D) * (double)k), (int)(((double)l + 5.5D) * (double)k), (int)(((double)i1 + 3.5D) * (double)k));
                k1 = 1;
            } else
            {
                DrawWithOffset.strokeLine(g,(l + 2) * k, (int)(((double)i1 + 2.5D) * (double)k), (int)(((double)l + 4.5D) * (double)k), (int)(((double)i1 + 2.5D) * (double)k));
                DrawWithOffset.strokeLine(g,(l + 2) * k, (int)(((double)i1 + 3.5D) * (double)k), (int)(((double)l + 4.25D) * (double)k), (int)(((double)i1 + 3.5D) * (double)k));
            }
            DrawWithOffset.strokeLine(g,(l + 2) * k, (i1 + 2) * k, (l + 2) * k, (i1 + 4) * k);
            DrawWithOffset.strokeLine(g,(int)(((double)l + 3.5D + (double)k1) * (double)k), (i1 + 2) * k, (int)(((double)l + 3.5D + (double)k1) * (double)k), (i1 + 4) * k);
            DrawWithOffset.strokeLine(g,(int)(((double)l + 3.5D + (double)k1) * (double)k), (i1 + 2) * k, (int)(((double)l + 2.5D + (double)k1) * (double)k), (i1 + 3) * k);
            DrawWithOffset.strokeLine(g,(int)(((double)l + 3.5D + (double)k1) * (double)k), (i1 + 4) * k, (int)(((double)l + 2.5D + (double)k1) * (double)k), (i1 + 3) * k);
        }
    }
}
