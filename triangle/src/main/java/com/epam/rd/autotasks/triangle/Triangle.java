package com.epam.rd.autotasks.triangle;

class Triangle {

    private final Point a;
    private final Point b;
    private final Point c;

    public Triangle(Point a, Point b, Point c) {
        if (isNotPossible(a,b,c)) throw new IllegalArgumentException();
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double area() {
        double s = this.sum()/2;
        double a = this.length(this.a, this.b);
        double b = this.length(this.b, this.c);
        double c = this.length(this.a, this.c);
        return Math.sqrt(s*(s-a)*(s-b)*(s-c));
    }

    public Point centroid() {
        double xc= (((a.getX()+b.getX()+c.getX())/3));
        double yc= (((a.getY()+b.getY()+c.getY())/3));
        return new Point(xc, yc);
    }

    private double length(Point x1, Point x2) {
        return (Math.sqrt(Math.pow(x2.getX() - x1.getX(), 2) + Math.pow(x2.getY() - x1.getY(), 2)));
    }

    private double sum() {
        return length(a, b) + length(b, c) + length(c, a);
    }

    private boolean isPossible (Point a, Point b, Point c) {
        return ((length(a, b) + length(b, c)) > length(c, a))
                && ((length(b, c) + length(c, a)) > length(a, b))
                && ((length(c, a) + length(a, b)) > length(b, c));
    }

    private boolean isNotPossible (Point a, Point b, Point c) {
        return !isPossible(a, b, c);
    }
}
