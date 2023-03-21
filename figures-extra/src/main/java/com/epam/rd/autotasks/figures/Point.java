package com.epam.rd.autotasks.figures;

class Point {
    private final double x;
    private final double y;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isTheSame (Point o){
        if (o == null) return false;
        if (o == this) return true;
        return Math.abs(this.x - o.getX()) <= 0.00001 && Math.abs(this.y - o.getY()) <= 0.00001;
    }

}
