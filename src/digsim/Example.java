package digsim;


public class Example
{

    public Example(String s, String s1, String s2)
    {
        Type = s;
        Description = s1;
        Location = s2;
    }

    public String getType()
    {
        return Type;
    }

    public String getDescription()
    {
        return Description;
    }

    public String getLocation()
    {
        return Location;
    }

    protected String Type;
    protected String Description;
    protected String Location;
}
