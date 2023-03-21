package com.epam.rd.autotasks;

import java.util.Locale;
import java.util.Scanner;

public class QuadraticEquation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double x1, x2;

        // determinant (b^2 - 4ac)
        double det = b * b - 4 * a * c;

        // check if determinant is greater than 0
        if (det > 0) {

            // two real and distinct roots
            x1 = (-b + Math.sqrt(det)) / (2 * a);
            x2 = (-b - Math.sqrt(det)) / (2 * a);

            System.out.println(x1 + ", " + x2);
        }

        // check if determinant is equal to 0
        else if (det == 0) {

            // two real and equal roots
            // determinant is equal to 0
            // so -b + 0 == -b
            x1 = -b / (2 * a);

            System.out.println(x1);
        }

        // if determinant is less than zero
        else {
            // no roots
            System.out.println("no roots");
        }
    }
}