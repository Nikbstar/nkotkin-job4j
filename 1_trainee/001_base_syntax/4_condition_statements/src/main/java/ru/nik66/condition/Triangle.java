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

    public double period(double ab, double bc, double ca) {
        return (ab + bc + ca) / 2;
    }

    private boolean exist(double ab, double bc, double ca) {
        boolean result = false;
        if ((ab + bc > ca) && (bc + ca > ab) && (ca + ab > bc)) {
            result = true;
        }
        return result;
    }

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
