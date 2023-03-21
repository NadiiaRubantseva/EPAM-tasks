package com.epam.rd.autotasks.figures;

import static java.lang.Math.*;

class Triangle extends  Figure {

    private final Point a;
    private final Point b;
    private final Point c;

    public Triangle(Point a, Point b, Point c) {
        if (isDegenerated(a, b, c))
            throw new IllegalArgumentException();

        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static boolean isDegenerated(Point a, Point b, Point c) {
        if (a == null || b == null || c == null)
            return true;

        double A = sqrt(pow(c.getX() - b.getX(), 2) + pow(c.getY() - b.getY(), 2));
        double B = sqrt(pow(c.getX() - a.getX(), 2) + pow(c.getY() - a.getY(), 2));
        double C = sqrt(pow(b.getX() - a.getX(), 2) + pow(b.getY() - a.getY(), 2));

        double p = (A + B + C) / 2;

        return sqrt(p * (p - A) * (p - B) * (p - C)) == 0;
    }

    @Override
    public Point centroid() {
        return new Point((a.getX() + b.getX() + c.getX()) / 3, (a.getY() + b.getY() + c.getY()) / 3);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        return figure instanceof Triangle;
    }
}
