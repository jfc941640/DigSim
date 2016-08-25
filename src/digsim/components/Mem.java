package digsim.components;

import java.util.StringTokenizer;

import digsim.IntegratedCircuit;
import digsim.ElectronicComponent;

// Referenced classes of package digsim:
//            IntegratedCircuit, Ram, ElectronicComponent

public class Mem extends IntegratedCircuit
{

    public Mem(int i, int j, int k, int l, int i1, int j1, int k1,
            int l1, int i2, int j2)
    {
        super(i, j, k, l, i1, j1, k1, l1, i2, j2);
        Tab = new int[32];
    }

    public Mem(ElectronicComponent electroniccomponent, int i, int j)
    {
        super(electroniccomponent, i, j);
        Tab = new int[32];
    }

    public ElectronicComponent Copy(int i, int j)
    {
        Ram ram = new Ram(this, i, j);
        ram.Tab = Tab;
        return ram;
    }

    public void UpdateMemory(String s)
    {
        StringTokenizer stringtokenizer = new StringTokenizer(s);
        boolean flag = false;
        int i = 0;
        if(s.startsWith("H"))
        {
            flag = true;
            stringtokenizer.nextToken();
        }
        for(; stringtokenizer.hasMoreElements() && i < 32; i++)
        {
            String s1 = stringtokenizer.nextToken();
            if(flag)
                Tab[i] = FromHexa(s1.charAt(0), s1.charAt(1));
            else
                Tab[i] = Integer.parseInt(s1);
        }

    }

    public static int FromHexa(char c, char c1)
    {
        int ai[] = new int[2];
        char c2 = c;
        for(int i = 0; i < 2; i++)
        {
            if(c2 == '0')
                ai[i] = 0;
            else
            if(c2 == '1')
                ai[i] = 1;
            else
            if(c2 == '2')
                ai[i] = 2;
            else
            if(c2 == '3')
                ai[i] = 3;
            else
            if(c2 == '4')
                ai[i] = 4;
            else
            if(c2 == '5')
                ai[i] = 5;
            else
            if(c2 == '6')
                ai[i] = 6;
            else
            if(c2 == '7')
                ai[i] = 7;
            else
            if(c2 == '8')
                ai[i] = 8;
            else
            if(c2 == '9')
                ai[i] = 9;
            else
            if(c2 == 'a' || c2 == 'A')
                ai[i] = 10;
            else
            if(c2 == 'b' || c2 == 'B')
                ai[i] = 11;
            else
            if(c2 == 'c' || c2 == 'C')
                ai[i] = 12;
            else
            if(c2 == 'd' || c2 == 'D')
                ai[i] = 13;
            else
            if(c2 == 'e' || c2 == 'E')
                ai[i] = 14;
            else
            if(c2 == 'f' || c2 == 'F')
                ai[i] = 15;
            else
                ai[i] = 0;
            c2 = c1;
        }

        return ai[0] * 16 + ai[1];
    }

    public static String toHexa(int i)
    {
        char ac[] = new char[3];
        ac[2] = ' ';
        int j = i & 0xf;
        int k = i / 16;
        for(int l = 0; l < 2; l++)
        {
            if(k == 10)
                ac[l] = 'A';
            else
            if(k == 11)
                ac[l] = 'B';
            else
            if(k == 12)
                ac[l] = 'C';
            else
            if(k == 13)
                ac[l] = 'D';
            else
            if(k == 14)
                ac[l] = 'E';
            else
            if(k == 15)
                ac[l] = 'F';
            else
            if(k <= 9 && k >= 0)
                ac[l] = String.valueOf(k).charAt(0);
            else
                ac[l] = 'X';
            k = j;
        }

        return new String(ac);
    }

    public static String TranslateToHexa(String s)
    {
        StringTokenizer stringtokenizer = new StringTokenizer(s);
        String s1;
        String s2;
        for(s2 = new String(); stringtokenizer.hasMoreElements(); s2 = s2 + toHexa(Integer.parseInt(s1)))
            s1 = stringtokenizer.nextToken();

        return s2;
    }

    public static String TranslateToInt(String s)
    {
        StringTokenizer stringtokenizer = new StringTokenizer(s);
        String s1;
        String s2;
        for(s2 = new String(); stringtokenizer.hasMoreElements(); s2 = s2 + FromHexa(s1.charAt(0), s1.charAt(1)) + " ")
            s1 = stringtokenizer.nextToken();

        return s2;
    }

    int Tab[];
}
