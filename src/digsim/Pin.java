package digsim;

import java.util.Vector;

import digsim.ElectronicComponent;

// Referenced classes of package digsim:
//            ElectronicComponent

public class Pin
{

    public Pin()
    {
        Components = new Vector();
    }

    public void AddComponent(ElectronicComponent electroniccomponent)
    {
        Components.addElement(electroniccomponent);
    }

    public void RemoveComponent(ElectronicComponent electroniccomponent)
    {
        for(int i = 0; i < Components.size(); i++)
            if(Components.elementAt(i) == electroniccomponent)
            {
                Components.removeElementAt(i);
                return;
            }

    }

    public void SimulateSetUp(int i, int j)
    {
        for(int k = 0; k < Components.size(); k++)
        {
            ElectronicComponent electroniccomponent = (ElectronicComponent)Components.elementAt(k);
            electroniccomponent.SimulateSetUp(i, j, Components);
        }

    }

    Vector Components;
}
