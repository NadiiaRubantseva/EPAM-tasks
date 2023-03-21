package com.epam.rd.autotasks.meetstrangers;

import java.util.Scanner;

public class HelloStrangers {
    public static void main(String[] args) {
        //Write a program, asks for a number - amount of strangers to meet.
        //Then reads stranger names line by line and prints line by line "Hello, ...".
        Scanner scanner = new Scanner(System.in);
        int amount = scanner.nextInt();
        if (amount < 0) {
            System.out.println("Seriously? Why so negative?");
        } else if (amount == 0) {
            System.out.println("Oh, it looks like there is no one here");
        } else {
            String[] names = new String[amount];
            for (int i = 0; i < amount; i++) {
                String name = scanner.nextLine();
                while (name.isEmpty()) {
                    name = scanner.nextLine();
                }
                names[i] = name;
            }
            for (int i = 0; i < amount; i++) {
                System.out.println("Hello, " + names[i]);
            }
        }
    }
}