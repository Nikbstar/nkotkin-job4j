package ru.nik66.condition;

/**
 * Triangle class
 */
public class Triangle {

    /**
     * First point.
     */
    private Point a;
    /**
     * Second point.
     */
    private Point b;
    /**
     * Third point.
     */
    private Point c;

    /**
     * Constructor with all points.
     * @param a First point.
     * @param b Second point.
     * @param c Third point.
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * The Method of calculating the semi-perimeter along the length of the the sides.
     * @param ab the length of the side ab.
     * @param bc the length of the side bc.
     * @param ca the length of the side ca.
     * @return semi-perimeter.
     */
    public double period(double ab, double bc, double ca) {
        return (ab + bc + ca) / 2;
    }

    /**
     * The method verifies the correctness of the triangle.
     * @param ab the length of the side ab.
     * @param bc the length of the side bc.
     * @param ca the length of the side ca.
     * @return true if the triangle is correct.
     */
    private boolean exist(double ab, double bc, double ca) {
        boolean result = false;
        if ((ab + bc > ca) && (bc + ca > ab) && (ca + ab > bc)) {
            result = true;
        }
        return result;
    }

    /**
     * The method calculates the area of the triangle.
     * @return the area of the triangle.
     */
    public double area() {
        double rsl = -1;
        double ab = this.a.distanceTo(this.b);
        double bc = this.b.distanceTo(this.c);
        double ca = this.c.distanceTo(this.a);

        double p = this.period(ab, bc, ca);
        if (this.exist(ab, bc, ca)) {
            rsl = Math.sqrt(p * (p - ab) * (p - bc) * (p - ca));
        }
        return rsl;
    }

}
