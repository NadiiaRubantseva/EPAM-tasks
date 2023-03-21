package com.epam.rd.autotasks.segments;

import java.util.Objects;

class Segment {

    private final Point start;
    private final Point end;

    public Segment(Point start, Point end) {
        if (start == null || end == null || start.equals(end))
            throw new IllegalArgumentException();
        this.start = start;
        this.end = end;
    }

    double length() {
        double xDistanceSquare = Math.pow(start.getX() - end.getX(), 2);
        double yDistanceSquare = Math.pow(start.getY() - end.getY(), 2);
        return Math.sqrt(xDistanceSquare + yDistanceSquare);
    }

    Point middle() {
        return new Point( (start.getX() + end.getX()) / 2,
                (start.getY() + end.getY()) / 2 );
    }

    Point intersection(Segment other) {
       return SegIntersection(start.getX(), start.getY(), end.getX(), end.getY(),
                                       other.start.getX(), other.start.getY(), other.end.getX(), other.end.getY());
    }
    public Point SegIntersection(double x11, double y11, double x12, double y12,
                                        double x21, double y21, double x22, double y22)
    {
        double dx1 = x12-x11;
        double dy1 = y12-y11;
        double dx2 = x22-x21;
        double dy2 = y22-y21;
        double dxx = x11-x21;
        double dyy = y11-y21;
        double div, t, s;

        div = dy2*dx1-dx2*dy1;
        if (Math.abs(div) < 1.0e-10) //better to compare abs(div) with small Eps
            return null;  //collinear case

        t = (dx1*dyy-dy1*dxx) / div;
        if (t < 0 || t > 1.0)
            return null; //intersection outside the first segment
        s = (dx2*dyy-dy2*dxx) / div;
        if (s < 0 || s > 1.0)
            return null;  //intersection outside the second segment
        return new Point(x11 + s * dx1, y11 + s * dy1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment segment = (Segment) o;
        return Objects.equals(start, segment.start) && Objects.equals(end, segment.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
