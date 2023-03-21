package com.epam.rd.autotasks.figures;

import java.util.Locale;

class Quadrilateral extends Figure {

    private final Point a;
    private final Point b;
    private final Point c;
    private final Point d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double area() {
        double x1, y1, x2, y2, x3, y3, x4, y4;
        x1 = a.getX();
        y1 = a.getY();
        x2 = b.getX();
        y2 = b.getY();
        x3 = c.getX();
        y3 = c.getY();
        x4 = d.getX();
        y4 = d.getY();
        double p1 = (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0;
        double p2 = (x1 * (y3 - y4) + x3 * (y4 - y1) + x4 * (y1 - y3)) / 2.0;
        return Math.abs(p1 + p2);
    }

    @Override
    public String pointsToString() {
        String pattern = "(%.1f,%.1f)(%.1f,%.1f)(%.1f,%.1f)(%.1f,%.1f)";
        return String.format(Locale.US, pattern, a.getX(), a.getY(),
                b.getX(), b.getY(),
                c.getX(), c.getY(),
                d.getX(), d.getY());
    }

    @Override
    public Point leftmostPoint() {
        if (a.getX() <= b.getX() && a.getX() <= c.getX() && a.getX() <= d.getX())
            return a;
        if (b.getX() <= a.getX() && b.getX() <= c.getX() && b.getX() <= d.getX())
            return b;
        if (c.getX() <= a.getX() && c.getX() <= b.getX() && c.getX() <= d.getX())
            return c;
        if (d.getX() <= a.getX() && d.getX() <= b.getX() && d.getX() <= c.getX())
            return d;
        return null;
    }
}
