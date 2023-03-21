package com.epam.rd.autotasks.snail;

import java.util.Scanner;

public class Snail
{
    public static void main(String[] args)
    {
        //Write a program that reads a,b and h (line by lyne in this order) and prints
        //the number of days for which the snail reach the top of the tree.
        //a - feet that snail travels up each day, b - feet that slides down each night, h - height of the tree
        Scanner scanner = new Scanner(System.in);
        int meters_per_day = scanner.nextInt();
        int meters_down_per_night = scanner.nextInt();
        int wall_height = scanner.nextInt();

        if (meters_per_day < wall_height && (meters_per_day - meters_down_per_night) <= 0) {
            System.out.println("Impossible");
            return;
        }

        if (meters_per_day >= wall_height) {
            System.out.println(1);
            return;
        }

        int current_height = 0;
        int days = 1;

        while (current_height != wall_height) {
            current_height += meters_per_day;

            if(current_height >= wall_height) {
                break;
            }

            days += 1;
            current_height -= meters_down_per_night;
            System.out.println(days);
        }
    }
}
