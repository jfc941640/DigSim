package digsim;

import javafx.scene.paint.Color;

public class MyColor
{

    public MyColor(String s)
    {
        if(s.equals("monochrome"))
        {
            black = Color.BLACK;
            green = Color.GRAY;
            lightGray = Color.LIGHTGRAY;
            gray = Color.GRAY;
            white = Color.WHITE;
            red = Color.DARKGRAY;
            blue = Color.BLACK;
            yellow = Color.LIGHTGRAY;  
            pink = Color.GRAY;
            darkGray = Color.DARKGRAY;
            orange = Color.LIGHTGRAY;
            cyan = Color.DARKGRAY;
            magenta = Color.BLACK;
        }
    }

    public static Color black;
    public static Color green;
    public static Color lightGray;
    public static Color gray;
    public static Color white;
    public static Color red;
    public static Color blue;
    public static Color yellow;
    public static Color pink;
    public static Color darkGray;
    public static Color orange;
    public static Color cyan;
    public static Color magenta;

    static
    {
        black = Color.BLACK;
        green = Color.GREEN;
        lightGray = Color.LIGHTGRAY;
        gray = Color.GRAY;
        white = Color.WHITE;
        red = Color.RED;
        blue = Color.BLUE;
        yellow = Color.YELLOW;
        pink = Color.PINK;
        darkGray = Color.rgb(64, 64, 64);// Color.DARKGRAY;BFBFC0
        orange = Color.ORANGE;
        cyan = Color.CYAN;
        magenta = Color.MAGENTA;
    }

	private static Color convertAwtColor(java.awt.Color awtColor) {
		//java.awt.Color.darkGray;
        int r = awtColor.getRed();
        int g = awtColor.getGreen();
        int b = awtColor.getBlue();
        int a = awtColor.getAlpha();
        double opacity = a / 255.0 ;
        return javafx.scene.paint.Color.rgb(r, g, b, opacity);
	}
}
