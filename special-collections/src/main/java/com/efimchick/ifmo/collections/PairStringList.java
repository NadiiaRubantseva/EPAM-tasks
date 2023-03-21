package com.efimchick.ifmo.collections;

import java.util.*;

class PairStringList extends ArrayList<String> {

    @Override
    public boolean add(String s) {
        super.add(s);
        return super.add(s);
    }

    @Override
    public void add(int index, String element) {
        if (index % 2 != 0)
            index++;
        super.add(index, element);
        super.add(index, element);
    }

    @Override
    public boolean remove(Object o) {
        super.remove(o);
        return super.remove(o);
    }

    @Override
    public String remove(int index) {
        String o = super.get(index);
        super.remove(o);
        super.remove(o);
        return o;
    }

    @Override
    public String get(int index) {
        return super.get(index);
    }

    @Override
    public String set(int index, String element) {
        super.set(index, element);
        return index % 2 == 0 ?
                super.set(index + 1, element) : super.set(index - 1, element);
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        List<String> result = new ArrayList<>();
        Object[] arr = c.toArray();
        for (Object o : arr) {
            result.add((String) o);
            result.add((String) o);
        }
        return super.addAll(result);
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        List<String> result = new ArrayList<>();
        Object[] arr = c.toArray();
        for (Object o : arr) {
            result.add((String) o);
            result.add((String) o);
        }
        if (index % 2 != 0) {
            ++index;
        }
        return super.addAll(index, result);
    }
}
