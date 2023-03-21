package com.epam.rd.autotasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FactorialBadInputTesting {

    Factorial factorial = new Factorial();

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> factorial.factorial(null));
    }

    @Test
    void testNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> factorial.factorial("" + Integer.MIN_VALUE));
        assertThrows(IllegalArgumentException.class, () -> factorial.factorial("-500"));
        assertThrows(IllegalArgumentException.class, () -> factorial.factorial("   -9   "));
    }

    @Test
    void testFractionalInput() {
        assertThrows(IllegalArgumentException.class, () -> factorial.factorial("   123.4"));
        assertThrows(IllegalArgumentException.class, () -> factorial.factorial(" -34.5 "));
        assertThrows(IllegalArgumentException.class, () -> factorial.factorial("+35.7777"));
    }

    @Test
    void testNonDigitalInput() {
        assertThrows(IllegalArgumentException.class, () -> factorial.factorial(" [] "));
        assertThrows(IllegalArgumentException.class, () -> factorial.factorial("  \\  "));
        assertThrows(IllegalArgumentException.class, () -> factorial.factorial(" +32W"));
    }
}
