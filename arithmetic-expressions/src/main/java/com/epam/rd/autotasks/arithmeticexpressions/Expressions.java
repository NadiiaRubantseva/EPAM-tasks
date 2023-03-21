package com.epam.rd.autotasks.arithmeticexpressions;

import java.util.StringJoiner;

public class Expressions {

    public static Variable var(String name, int value) {
        return new Variable(name, value);
    }

    public static Expression val(int value) {
        return new Expression() {
            @Override
            public int evaluate() {
                return value;
            }

            @Override
            public String toExpressionString() {
                return (value >= 0) ? String.valueOf(value) : ("(" + value + ")");
            }
        };
    }

    public static Expression sum(Expression... members){
        return new Expression() {
            @Override
            public int evaluate() {
                int sum = 0;
                for (Expression number: members) {
                    sum += number.evaluate();
                }
                return sum;
            }

            @Override
            public String toExpressionString() {
                StringJoiner all = new StringJoiner("");
                for (int i = 0; i < members.length - 1; i++) {
                    all.add(members[i].toExpressionString() + " + ");
                }
                return "(" + all + members[members.length - 1].toExpressionString() + ")";
            }
        };
    }

    public static Expression product(Expression... members){
        return new Expression() {
            @Override
            public int evaluate() {
                int prod = 1;
                for (Expression number: members) {
                    prod *= number.evaluate();
                }
                return prod;
            }

            @Override
            public String toExpressionString() {
                StringJoiner all = new StringJoiner("");
                for (int i = 0; i < members.length - 1; i++) {
                    all.add(members[i].toExpressionString() + " * ");
                }
                return "(" + all + members[members.length - 1].toExpressionString() + ")";
            }
        };
    }

    public static Expression difference(Expression minuend, Expression subtrahend){
        return new Expression() {
            @Override
            public int evaluate() {
                return minuend.evaluate() - subtrahend.evaluate();
            }

            @Override
            public String toExpressionString() {
                return "(" + minuend.toExpressionString() + " - " + subtrahend.toExpressionString() + ")";
            }
        };
    }

    public static Expression fraction(Expression dividend, Expression divisor){
        return new Expression() {
            @Override
            public int evaluate() {
                return dividend.evaluate() / divisor.evaluate();
            }

            @Override
            public String toExpressionString() {
                return "(" + dividend.toExpressionString() + " / " + divisor.toExpressionString() + ")";
            }
        };
    }

}