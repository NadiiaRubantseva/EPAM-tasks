package com.epam.rd.autocode.decorator;

import java.util.*;

public class EvenIndexElementsSubList extends AbstractList<String> {
    private final List<String> source;

    public EvenIndexElementsSubList(List<String> source) {
        this.source = source;
    }

    @Override
    public int size() {
        return source.size() % 2 == 0 ? source.size() / 2 : source.size() / 2 + 1;
    }

    @Override
    public String get(int index) {
        return source.get(index * 2);
    }

    @Override
    public Iterator<String> iterator() {
        return super.iterator();
    }
}