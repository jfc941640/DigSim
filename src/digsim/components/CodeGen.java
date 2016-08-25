package digsim.components;

import java.util.Calendar;

import digsim.ElectronicComponent;
import digsim.IntegratedCircuit;
import digsim.OutputPin;
import digsim.Pin;
import javafx.scene.canvas.GraphicsContext;

// Referenced classes of package digsim:
//            IntegratedCircuit, OutputPin, Pin, ElectronicComponent

public class CodeGen extends IntegratedCircuit
{

    public CodeGen(Pin apin[][], int i, int j)
    {
        super(i, j, 3, 10, 3, 1, 0, 11, 0, 10);
        OPin[0] = new OutputPin("0", 4, 2, -1, 0, 0, 0, 0);
        OPin[1] = new OutputPin("5", 4, 3, -1, 0, 0, 0, 0);
        OPin[2] = new OutputPin("444888", 4, 4, -1, 0, 0, 0, 0);
        OPin[3] = new OutputPin("444999", 4, 5, -1, 0, 0, 0, 0);
        OPin[4] = new OutputPin("2222222", 4, 6, -1, 0, 0, 0, 0);
        OPin[5] = new OutputPin("2222224", 4, 7, -1, 0, 0, 0, 0);
        OPin[6] = new OutputPin("2222227", 4, 8, -1, 0, 0, 0, 0);
        OPin[7] = new OutputPin("2222228", 4, 9, -1, 0, 0, 0, 0);
        OPin[8] = new OutputPin("-1", 4, 10, -1, 0, 0, 0, 0);
        OPin[9] = new OutputPin("444xxx", 4, 11, -1, 0, 0, 0, 0);
        ComponentName = "Generateur de codes";
        ClassName = "CodeGen";
        Can_Rotate = false;
        RegisterPins(apin, i, j);
    }

    public CodeGen(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
    }

    public ElectronicComponent Copy(int i, int j)
    {
        CodeGen codegen = new CodeGen(this, i, j);
        return codegen;
    }

    public void SimulateLogic()
    {
    }

    public void InitBeforeSimulate()
    {
        Calendar calendar = Calendar.getInstance();
        ss = calendar.get(13);
        OPin[0].setLevel(0);
        OPin[1].setLevel(5);
        OPin[2].setLevel(0x6c9d8);
        OPin[3].setLevel(0x6ca47);
        OPin[4].setLevel(0x21e88e);
        OPin[5].setLevel(0x21e890);
        OPin[6].setLevel(0x21e893);
        OPin[7].setLevel(0x21e894);
        OPin[8].setLevel(-1);
        OPin[9].setLevel(0x6c660 + ss);
    }

    public void Simulate(int i)
    {
        InformConnectedComponents(i);
    }

    public void draw(GraphicsContext g, int i, int j, int k)
    {
        super.draw(g, i, j, k);
        int l = Pos.x - i;
        int i1 = Pos.y - j;
    }

    int ss;
}
