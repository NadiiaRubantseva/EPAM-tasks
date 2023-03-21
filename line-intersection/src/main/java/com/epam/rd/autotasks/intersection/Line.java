package com.epam.rd.autotasks.intersection;

public class Line {
    private final int k, b;
    public Line(int k, int b) {
        this.k = k;
        this.b = b;
    }
    public Point intersection(Line line) {
        if (this.k == line.k) {
            return null;
        }
        int x = (line.b - this.b) / (this.k - line.k);
        int y = this.k * x + this.b;
        return new Point(x, y);
    }
}
