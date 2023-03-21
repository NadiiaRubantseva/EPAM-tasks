package com.epam.autotasks.collections;

import java.util.*;

class IntStringCappedMap extends AbstractMap<Integer, String> {

    ArrayList<Integer> keyMap = new ArrayList<>();
    ArrayList<String> valueMap = new ArrayList<>();

    private final long capacity;

    public IntStringCappedMap(final long capacity) {
        this.capacity = capacity;
    }

    public long getCapacity() {
        return capacity;
    }

    @Override
    public Set<Entry<Integer, String>> entrySet() {
        return new AbstractSet<>() {

            @Override
            public Iterator<Entry<Integer, String>> iterator() {
                return new Iterator<>() {
                    int index = 0;

                    @Override
                    public boolean hasNext() {
                        return index < IntStringCappedMap.this.size();
                    }

                    @Override
                    public Entry<Integer, String> next() {
                        Iterator<Integer> it = keyMap.iterator();
                        if (it.hasNext()) {
                            Map.Entry<Integer, String> entry = new SimpleEntry<>(keyMap.get(index), valueMap.get(index));
                            index++;
                            return entry;
                        } else return new SimpleEntry<>(0, "0");
                    }
                };
            }

            @Override
            public int size() {
                return IntStringCappedMap.this.size();
            }
        };
    }

    @Override
    public String get(final Object key) {
        return valueMap.get(keyMap.indexOf(key));
    }

    @Override
    public String put(final Integer key, final String value) {
        if (value.length() > IntStringCappedMap.this.capacity) {
            throw new IllegalArgumentException();
        }
        if (!(keyMap.contains(key))) {
            keyMap.add(key);
            valueMap.add(value);

            while (capacity() > this.capacity) {
                this.remove(keyMap.get(0));
            }
            return null;
        }
        String str = valueMap.get(keyMap.indexOf(key));
        IntStringCappedMap.this.remove(key);
        keyMap.add(key);
        valueMap.add(value);
        while (capacity() > IntStringCappedMap.this.capacity) {
            IntStringCappedMap.this.remove(keyMap.get(0));
        }
        return str;
    }

    @Override
    public String remove(final Object key) {
        if (keyMap.contains(key)) {
            String removed = valueMap.get(keyMap.indexOf(key));
            valueMap.remove(keyMap.indexOf(key));
            keyMap.remove(key);
            return removed;
        }
        return null;
    }

    @Override
    public int size() {
        return keyMap.size();
    }

    public int capacity() {
        int capacity = 0;
        for (String item : valueMap) {
            capacity += item.length();
        }
        return capacity;
    }
}
