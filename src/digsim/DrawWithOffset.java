package digsim;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

// see http://stackoverflow.com/questions/11886230/how-to-draw-a-crisp-opaque-hairline-in-javafx-2-2
/*
(\s*)(.*).strokeOval\(
$1DrawWithOffset.strokeOval\($2,  
 */

public class DrawWithOffset {
	
    public static void strokeLine(GraphicsContext g, double i, double j, double k, double l) {
    	g.strokeLine(i - 0.5, j - 0.5, k - 0.5, l - 0.5);		
	}

	public static void fillRect(GraphicsContext g, double i, double j, double k, double l) {
		g.fillRect(i - 0.5, j - 0.5, k, l);		
	}

	public static void strokeRect(GraphicsContext g, double i, double j, double k, double l) {
		g.strokeRect(i - 0.5, j - 0.5, k, l);		
	}
	
	public static void fillArc(GraphicsContext g, double i, double j, double k, double l, double m, double n, ArcType open) {
		g.fillArc(i - 0.5, j - 0.5, k, l, m, n, open);		
	}

	public static void strokeArc(GraphicsContext g, double i, double j, double k, double l, double m, double n, ArcType open) {
		g.strokeArc(i - 0.5, j - 0.5, k, l, m, n, open);		
	}

	public static void fillOval(GraphicsContext g, double i, double j, double k, double l) {
		g.fillOval(i - 0.5, j - 0.5, k, l);		
	}

	public static void strokeOval(GraphicsContext g, double i, double j, double k, double l) {
		g.strokeOval(i - 0.5, j - 0.5, k, l);		
	}

	public static void fillPolygon(GraphicsContext g, double[] ai1, double[] ai5, int length) {
		g.fillPolygon(offset(ai1), offset(ai5), length);		
	}
	
	public static void strokePolygon(GraphicsContext g, double[] ai1, double[] ai5, int length) {
		g.strokePolygon(offset(ai1), offset(ai5), length);		
	}

	public static void fillText(GraphicsContext g, String text, double i, double j) {
		g.fillText(text, i - 0.5, j - 0.5);		
	}
	
	private static double[] offset(double[] ai1) {
		double[] result = new double[ai1.length];
		for (int i = 0; i < ai1.length; i++)
		{
			result[i] = ai1[i] - 0.5;
		}
		return result;
	}
}