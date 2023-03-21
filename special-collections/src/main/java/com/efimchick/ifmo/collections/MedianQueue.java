package com.efimchick.ifmo.collections;

import java.util.Collections;
import java.util.LinkedList;

class MedianQueue extends LinkedList<Integer> {

    @Override
    public Integer peek() {
        LinkedList<Integer> list = new LinkedList<>(this);
        Collections.sort(list);
        int index = this.size() / 2;
        if (this.size() % 2 == 0) {
            index--;
        }
        return list.get(index);
    }

    @Override
    public Integer poll() {
        LinkedList<Integer> list = new LinkedList<>(this);
        Collections.sort(list);
        int index = this.size() / 2;
        if (this.size() % 2 == 0) {
            index--;
        }
        Integer median = list.get(index);
        this.remove(median);
        return median;
    }
}
