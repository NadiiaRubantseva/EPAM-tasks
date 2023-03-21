package com.epam.autotasks.collections;

import java.util.*;

class RangedOpsIntegerSet extends AbstractSet<Integer> {

    private final ArrayList<Integer> list = new ArrayList<>();

    public boolean add(int fromInclusive, int toExclusive) {
        boolean flag = false;
        for (int i = fromInclusive; i < toExclusive; i++) {
            if (add(i) && !flag)
                flag = true;
        }
        return flag;
    }

    public boolean remove(int fromInclusive, int toExclusive) {
        int count = 0;
        for (int i = fromInclusive; i < toExclusive; i++) {
            if (list.contains(i)) {
                list.remove((Integer) i);
                count++;
            }
        }
        return count > 0;
    }

    @Override
    public boolean add(final Integer integer) {
        if (!list.contains(integer)) {
            list.add(integer);
            Collections.sort(list);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(final Object o) {
        if (list.contains((Integer) o)) {
            list.remove(o);
            Collections.sort(list);
            return true;
        }
        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new ListIterator();
    }

    @Override
    public int size() {
        return list.size();
    }

    class ListIterator implements Iterator<Integer> {
        int pointer = 0;

        @Override
        public boolean hasNext() {
            return list.size() > pointer;
        }

        @Override
        public Integer next() {
            Integer[] a = list.toArray(new Integer[0]);
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return a[pointer++];
        }
    }
}
