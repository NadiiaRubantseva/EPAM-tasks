package com.epam.rd.autotasks.sequence;

import java.util.Scanner;

public class FindMaxInSeq {
    public static int max() {
        Scanner scnr = new Scanner(System.in);
        int maxSoFar = scnr.nextInt();
        int currValue;

        while (true) {
            currValue = scnr.nextInt();
            if (currValue == 0) {
                return maxSoFar;
            }
            if (currValue > maxSoFar) {
                maxSoFar = currValue;
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("Test your code here!\n");

        // Get a result of your code

        System.out.println(max());
    }
}
