package com.epam.rd.autotasks;

import java.util.Arrays;
import java.util.Objects;

class CycleSwap {

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 2, 7, 4};
        CycleSwap.cycleSwap(array);
        System.out.println(Arrays.toString(array));

    }
    static void cycleSwap(int[] array) {
        cycleSwap(array, 1);
    }

    static void cycleSwap(int[] array, int shift) {
        if (Objects.isNull(array) || array.length == 0 || shift == 0) {
            return;
        }
        int[] temp = Arrays.copyOfRange(array, array.length-shift, array.length);
        System.arraycopy(array,0, array, shift, array.length-shift);
        System.arraycopy(temp, 0, array, 0, temp.length);
    }
}
