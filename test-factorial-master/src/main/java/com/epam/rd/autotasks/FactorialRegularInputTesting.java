package com.epam.rd.autotasks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FactorialRegularInputTesting {

    Factorial factorial = new Factorial();

    @ParameterizedTest(name = "{index} => input={0}, output={1}")
    @CsvSource({
            "1, 1",
            "2, 2",
            "3, 6",
            "4, 24",
            "5, 120",
            "6, 720",
            "7, 5040",
            "13, 6227020800",
            "23, 25852016738884976640000",
            "24, 620448401733239439360000",
            "25, 15511210043330985984000000"
    })
    void regularInput(String input, String output) {
        assertEquals(output, factorial.factorial(input));
    }

}
