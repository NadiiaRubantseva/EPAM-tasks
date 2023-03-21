package com.epam.rd.autotasks;

public class QuadraticEquation {
    public String solve(double a, double b, double c) {
        if (a == 0) { throw new IllegalArgumentException(a + " must not be 0!"); }
        double discriminant = calculateDiscriminant(a, b, c);
        if (discriminant > 0) { return calculateRoots(a, b, discriminant); }
        else if (discriminant == 0) { return calculateRoot(a, b); }
        else return "no roots";
    }

    private static String calculateRoot(double a, double b) {
        return String.format("%s", -b / (2 * a));
    }

    private static String calculateRoots(double a, double b, double discriminant) {
        return String.format("%s %s",
                (-b + Math.sqrt(discriminant)) / (2 * a),
                (-b - Math.sqrt(discriminant)) / (2 * a));
    }

    private static double calculateDiscriminant(double a, double b, double c) {
        return b * b - 4 * a * c;
    }
}