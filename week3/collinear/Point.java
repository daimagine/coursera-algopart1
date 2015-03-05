package week3.collinear;

/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
    	public int compare(Point point1, Point point2) {
    		double slope1 = Point.this.slopeTo(point1);
    		double slope2 = Point.this.slopeTo(point2);
    		if (slope1 < slope2) return -1;
    		else if (slope1 > slope2) return 1;
    		return 0;
    	}
	};

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    /*
     * given by the formula (y1 − y0) / (x1 − x0)
     */
    public double slopeTo(Point that) {
    	if (this.x == that.x && this.y == that.y) {
    		return Double.NEGATIVE_INFINITY; // degenerate line, point to itself
    	} else if (this.x == that.x) {
    		return Double.POSITIVE_INFINITY; // vertical line 
    	} else if (this.y == that.y) {
    		return 0; // horizontal line
    	} else {
	        double slope = (that.y - this.x) / (that.x - this.x); 
	        return slope;
    	}
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    /**
     * compare points by their y-coordinates, breaking ties by their x-coordinates. 
     * Formally, the invoking point (x0, y0) is less than the argument point (x1, y1) 
     * if and only if either y0 < y1 or if y0 = y1 and x0 < x1
     */
    public int compareTo(Point that) {
        if (this.x == that.x && this.y == that.y) return 0;
        else if (this.y < that.y || (this.y == that.y && this.x < that.x)) return -1;
        else return 1;
    }

    // return string representation of this point
    @Override
	public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}