package com.epam.rd.autotasks;

public class DecrementingCarousel {
    private final int capacity;
    private static int[] carousel;
    private int index;
    private boolean isRun;

    {
        index = 0;
        isRun = false;
    }

    public DecrementingCarousel(int capacity) {
        this.capacity = capacity;
        carousel = new int[capacity];
    }

    public boolean addElement(int element){
        if (element > 0 && index < capacity && !isRun) {
            carousel[index++] = element;
            return true;
        }
        return false;
    }

    public CarouselRun run() {
        if (!isRun) {
            isRun = true;
            return new CarouselRun();
        }
        return null;
    }

    public static int[] getCarousel() {
        return carousel;
    }
}
