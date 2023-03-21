package com.epam.rd.autotasks;

import java.util.Scanner;

public class Average {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Use Scanner methods to read input

        int sum = 0;
        int currValue;
        int i = 0;

        while (true) {
            currValue = scanner.nextInt();
            if (currValue == 0) {
                break;
            }
            sum += currValue;
            i++;
        }

        int avg = sum/i;
        System.out.println(avg);
    }
}