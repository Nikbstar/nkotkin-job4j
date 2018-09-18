package ru.nik66.condition;

/**
 * Point class.
 */
public class Point {

    /**
     * x coordinate.
     */
    private int x;
    /**
     * y coordinate.
     */
    private int y;

    /**
     * Constructor with coordinates.
     * @param x x coordinate.
     * @param y y coordinate.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The method calculates distance from this point to another.
     * @param point another point.
     * @return distance between points.
     */
    public double distanceTo(Point point) {
        return Math.sqrt(Math.pow(this.x - point.x, 2) + Math.pow(this.y - point.y, 2));
    }

}
