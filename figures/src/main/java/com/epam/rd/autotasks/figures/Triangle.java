package com.epam.rd.autotasks.figures;

import java.util.Locale;

class Triangle extends Figure {

    private final Point a;
    private final Point b;
    private final Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double area() {
        return 0.5
                * Math.abs(
                (a.getX() - c.getX())
                        * (b.getY() - c.getY())
                        - (b.getX() - c.getX())
                        * (a.getY() - c.getY()));
    }

    @Override
    public String pointsToString() {
        String pattern = "(%.1f,%.1f)(%.1f,%.1f)(%.1f,%.1f)";
        return String.format(Locale.US, pattern,    a.getX(), a.getY(),
                                                    b.getX(), b.getY(),
                                                    c.getX(), c.getY());
    }

    @Override
    public Point leftmostPoint() {
        if (a.getX() <= b.getX() && a.getX() <= c.getX())
            return a;
        if (b.getX() <= a.getX() && b.getX() <= c.getX())
            return b;
        if (c.getX() <= a.getX() && c.getX() <= b.getX())
            return c;
        return null;
    }
}
