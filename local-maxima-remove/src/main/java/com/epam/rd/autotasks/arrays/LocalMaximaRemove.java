package com.epam.rd.autotasks.arrays;

import java.util.Arrays;

public class LocalMaximaRemove {

    public static void main(String[] args) {
        int[] array = new int[]{18, 1, 3, 6, 7, -5};
        System.out.println(Arrays.toString(removeLocalMaxima(array)));
    }

    public static int[] removeLocalMaxima(int[] array) {
        int[] result = Arrays.copyOf(array, array.length);
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                if (array[i] <= array[i + 1]) {
                    result[j] = array[i];
                    j++;
                }
            }
            else if (i == array.length - 1) {
                if (array[i] <= array[i - 1]) {
                    result[j] = array[i];
                    j++;
                }
            }
            else if (array[i] <= array[i - 1] || array[i] <= array[i + 1]) {
                result[j] = array[i];
                j++;
            }
        }
        result = Arrays.copyOf(result, j);
        return result;
    }
}
