package digsim;

import digsim.InputPin;
import digsim.MyColor;
import digsim.Pin;
import digsim.ElectronicComponent;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            ElectronicComponent, InputPin, MyColor, DigSim,
//            Pin

public class SoundGenerator extends ElectronicComponent
{

    public SoundGenerator(Pin apin[][], int i, int j)
    {
        super(i, j, 8, 8, 3, 3, 2, 4, 1, 0);
        oldInput = 5;
        bip = 0;
        ComponentName = "Source sonore";
        ClassName = "SoundGenerator";
        Can_Rotate = false;
        IPin[0] = new InputPin("", 1, 5, 2, 0, 0, 0, 16);
        RegisterPins(apin, i, j);
        IPin[0].setLevel(0);
    }

    public SoundGenerator(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        oldInput = 5;
        bip = 0;
        IPin[0].setLevel(0);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        SoundGenerator soundgenerator = new SoundGenerator(this, i, j);
        return soundgenerator;
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
        DrawInputPins(g, l, i1, k);
        g.setStroke(MyColor.blue);
        g.setStroke(MyColor.red);
        DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 4) * k, (l + 5) * k, (i1 + 3) * k);
        DrawWithOffset.strokeLine(g,(l + 4) * k, (i1 + 6) * k, (l + 5) * k, (i1 + 7) * k);
        DrawWithOffset.strokeLine(g,(l + 5) * k, (i1 + 3) * k, (l + 5) * k, (i1 + 7) * k);
        g.setStroke(MyColor.black);
        DrawWithOffset.strokeRect(g,(l + 3) * k, (i1 + 4) * k, 1 * k, 2 * k);
        if(IPin[0].getLevel() == 5)
        {
            g.setStroke(MyColor.red);
            DrawWithOffset.fillRect(g,(l + 3) * k, (i1 + 4) * k, 1 * k, 2 * k);
        } else
        {
            bip = 0;
        }
        g.setStroke(MyColor.blue);
        DrawWithOffset.strokeLine(g,(l + 3) * k + 5, (i1 + 6) * k, (l + 3) * k + 5, (i1 + 7) * k);
        DrawWithOffset.fillRect(g,(l + 2) * k + 5, (i1 + 7) * k, k * 2 + 1, k / 2 - 3);
        DrawWithOffset.fillRect(g,(l + 2) * k + 3 + 5, (i1 + 7) * k + 2, k * 1 + 3, k / 2 - 3);
        DrawWithOffset.fillRect(g,(l + 2) * k + 6 + 5, (i1 + 7) * k + 4, k * 1 - 3, k / 2 - 3);
    }

    public void SimulateLogic()
    {
    }

    public void InitBeforeSimulate()
    {
        if(IPin[0].getLevel() == 5 && bip <= 1)
        {
            DigSim.sound.play();
            bip++;
        } else
        if(IPin[0].getLevel() == 0 && oldInput == 5)
        {
            DigSim.sound.stop();
            bip = 0;
        }
        oldInput = IPin[0].getLevel();
    }

    int oldInput;
    int bip;
}
