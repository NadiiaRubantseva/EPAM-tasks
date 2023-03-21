package com.epam.rd.autotasks;

import java.math.BigInteger;
import java.util.Objects;
import java.util.regex.Pattern;

public class Factorial {
    public String factorial(String n) {
        if (Objects.isNull(n)) {
            throw new IllegalArgumentException();
        }

        String string = n.trim();

        if (isNegativeInteger(string)) {
            throw new IllegalArgumentException();
        }

        long number = Long.parseLong(string);

        BigInteger result = BigInteger.ONE;

        for (int i = 2; i <= number; i++)
            result = result.multiply(BigInteger.valueOf(i));

        return result.toString();
    }

    private static boolean isPositiveInteger(String n) {
        Pattern pattern = Pattern.compile("^\\+?\\d*$");
        return pattern.matcher(n).matches();
    }

    private static boolean isNegativeInteger(String n) {
        return !isPositiveInteger(n);
    }
}

