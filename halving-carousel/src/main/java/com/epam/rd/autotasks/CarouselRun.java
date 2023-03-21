package com.epam.rd.autotasks;

public class CarouselRun {
    int position = 0;
    int k;

    public CarouselRun(int i) {
        k = 1;
    }

    public CarouselRun() {
    }

    public int next() {

        //Looking for not zero element
        while (DecrementingCarousel.carousel[position] == 0 && !isFinished()) {
            position = carouselReposition(position);
        }

        int element = DecrementingCarousel.carousel[position];

        if (!isFinished()) {
            int next = element;
            if (k != 1) {
                next--;
            } else next = next / 2;
            DecrementingCarousel.carousel[position] = next;
            position = carouselReposition(position);
        } else element = -1;
        return element;

    }

    public boolean isFinished() {
        boolean result;
        int j = 0;
        for (int i = 0; i <= DecrementingCarousel.carousel.length - 1; i++) {
            if (DecrementingCarousel.carousel[i] == 0) {
                j++;
            }
        }
        result = j == DecrementingCarousel.carousel.length;
        return result;
    }

    public int carouselReposition(int position) {
        if (position + 1 == DecrementingCarousel.carousel.length) {
            position = 0;
        } else position++;
        return position;
    }
}
