package com.efimchick.ifmo.collections;


import java.util.Comparator;
import java.util.TreeSet;

class SortedByAbsoluteValueIntegerSet extends TreeSet<Integer> {
    public SortedByAbsoluteValueIntegerSet() {
        super(Comparator.comparingInt(Math::abs));
    }
}
