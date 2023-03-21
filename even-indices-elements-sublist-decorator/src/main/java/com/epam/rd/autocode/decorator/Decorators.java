package com.epam.rd.autocode.decorator;

import java.util.List;
abstract class Decorators {

    public static List<String> evenIndexElementsSubList(List<String> sourceList) {
        return new EvenIndexElementsSubList(sourceList);
    }
}
