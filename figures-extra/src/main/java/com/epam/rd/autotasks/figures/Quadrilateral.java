package com.epam.rd.autotasks.figures;

class Quadrilateral extends Figure {

    private final Point a;
    private final Point b;
    private final Point c;
    private final Point d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {

        if (isDegenerated(a, b, c, d))
            throw new IllegalArgumentException();

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    private static double f(double x, double x0, double x1, double y, double y0, double y1) {
        return (x - x0) * (y1 - y0) - (y - y0) * (x1 - x0);
    }

    public static boolean isDegenerated(Point a, Point b, Point c, Point d) {

        if (a == null || b == null || c == null || d == null)
            return true;

        double xa = a.getX(), ya = a.getY(), xb = b.getX(), yb = b.getY(), xc = c.getX(), yc = c.getY(), xd = d.getX(), yd = d.getY();

        double l = f(xc, xb, xa, yc, yb, ya);
        double m = f(xd, xb, xa, yd, yb, ya);

        if (l * m < 0)
            return true;

        double q = f(xa, xc, xb, ya, yc, yb);
        double w = f(xd, xc, xb, yd, yc, yb);
        if (q * w < 0)
            return true;

        double p = f(xa, xd, xc, ya, yd, yc);
        double r = f(xb, xd, xc, yb, yd, yc);
        if (p * r < 0)
            return true;

        double j = f(xb, xa, xd, yb, ya, yd);
        double k = f(xc, xa, xd, yc, ya, yd);
        if (j * k < 0)
            return true;

        return Triangle.isDegenerated(a, b, c) || Triangle.isDegenerated(a, d, c) || Triangle.isDegenerated(a, b, d) || Triangle.isDegenerated(c, b, d);
    }


    @Override
    public Point centroid() {

        Point r1 = new Triangle(a, b, d).centroid();
        Point r2 = new Triangle(b, c, d).centroid();

        Point r3 = new Triangle(a, b, c).centroid();
        Point r4 = new Triangle(a, c, d).centroid();

        return Quadrilateral.intersection(r1, r2, r3, r4);
    }

    public static Point intersection(Point a, Point b, Point c, Point d) {
        double x1 = a.getX(), y1 = a.getY(), x2 = b.getX(), y2 = b.getY(), x3 = c.getX(),
                y3 = c.getY(),
                x4 = d.getX(), y4 = d.getY();

        double D = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        double xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / D;
        double yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / D;

        return new Point(xi, yi);
    }

    @Override
    public boolean isTheSame(Figure figure) {

        if (!(figure instanceof Quadrilateral))
            return false;

        double ax = a.getX() + b.getX() + c.getX() + d.getX();
        double ay = a.getY() + b.getY() + c.getY() + d.getY();

        double ax1 = ((Quadrilateral) figure).a.getX() + ((Quadrilateral) figure).b.getX() + ((Quadrilateral) figure).c.getX() + ((Quadrilateral) figure).d.getX();
        double ax2 = ((Quadrilateral) figure).a.getY() + ((Quadrilateral) figure).b.getY() + ((Quadrilateral) figure).c.getY() + ((Quadrilateral) figure).d.getY();

        return (ax == ax1) && (ay == ax2);

    }


}
