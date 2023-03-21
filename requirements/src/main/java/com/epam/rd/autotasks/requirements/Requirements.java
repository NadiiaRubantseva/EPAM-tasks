package com.epam.rd.autotasks.requirements;

import java.util.Objects;

public class Requirements {

    public static void requireNonNull(Object obj) {
        if (Objects.isNull(obj)) {
            throw new NullPointerException("NullPointerException");
        }
    }

    public static void requireNonNull(Object obj, String message) {
        if (Objects.isNull(obj)) {
            throw new NullPointerException(message);
        }
    }

    public static void checkArgument(boolean value) {
        if (!value) throw new IllegalArgumentException("IllegalArgumentException");
    }

    public static void checkArgument(boolean value, String message) {
        if (!value) throw new IllegalArgumentException(message);
    }

    public static void checkState(boolean value) {
        if (!value) throw new IllegalStateException("IllegalStateException");
    }

    public static void checkState(boolean value, String message) {
        if (!value) throw new IllegalStateException(message);
    }

    public static void checkIndex(int index, int size) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }
}
