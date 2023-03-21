package com.epam.rd.autotasks.figures;

import java.util.Locale;

class Circle extends Figure {

    private final Point center;
    private final double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public String pointsToString() {
        String pattern = "(%.1f,%.1f)";
        return String.format(Locale.US, pattern, center.getX(), center.getY());
    }

    @Override
    public String toString() {
        return "Circle[(" + center.getX() + "," + center.getY() + ")" + radius + "]";
    }

    @Override
    public Point leftmostPoint() {
        return new Point(center.getX() - radius, center.getY());
    }
}
