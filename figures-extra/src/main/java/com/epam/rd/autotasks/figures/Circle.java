package com.epam.rd.autotasks.figures;

class Circle extends Figure {

    private final Point center;
    private final double radius;

    public Circle(Point center, double radius) {
        if (center == null || radius <= 0) {
            throw new IllegalArgumentException();
        }
        this.center = center;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle[(" + center.getX() + "," + center.getY() + ")" + radius + "]";
    }

    @Override
    public Point centroid() {
        return center;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure == null) return false;
        if (figure == this) return true;
        if (figure instanceof Circle) {
            return center.isTheSame(((Circle) figure).center)
                    && center.isTheSame(((Circle) figure).center)
                    && Math.abs(((Circle) figure).radius - this.radius) <= 0.0001;
        }
        return false;
    }
}
