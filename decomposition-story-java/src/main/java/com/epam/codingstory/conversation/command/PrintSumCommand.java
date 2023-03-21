package com.epam.codingstory.conversation.command;

import com.epam.codingstory.rational.RationalNumber;

public class PrintSumCommand extends Command {

    private final RationalNumber first;
    private final RationalNumber second;

    public PrintSumCommand(final String tag, final RationalNumber first, final RationalNumber second) {
        super(tag);
        this.first = first;
        this.second = second;
    }

    @Override
    public void execute() {
        System.out.println("Sum is: " + first.add(second));
    }
}
