package digsim;

public class Point {
	    /**
	     * The X coordinate of this <code>Point2D</code>.
	     */
	    public double x;

	    /**
	     * The Y coordinate of this <code>Point2D</code>.
	     */
	    public double y;

	    /**
	     * Constructs and initializes a <code>Point2D</code> with
	     * coordinates (0,&nbsp;0).
	     */
	    public Point() { }

	    /**
	     * Constructs and initializes a <code>Point2D</code> with
	     * the specified coordinates.
	     *
	     * @param x the X coordinate of the newly
	     *          constructed <code>Point2D</code>
	     * @param y the Y coordinate of the newly
	     *          constructed <code>Point2D</code>
	     */
	    public Point(double x, double y) {
	        this.x = (int)x;
	        this.y = (int)y;
	    }

}
