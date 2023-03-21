package com.epam.codingstory.rational;

import com.epam.codingstory.conversation.Conversation;
import com.epam.codingstory.conversation.command.PrintProductCommand;
import com.epam.codingstory.conversation.command.PrintQuotientCommand;
import com.epam.codingstory.conversation.command.PrintSumCommand;

public class RationalNumbers {
    public static void main(String[] args) {
        RationalNumber first = new RationalNumberInteractiveReader("first").read();
        RationalNumber second = new RationalNumberInteractiveReader("second").read();

        final Conversation conversation = new Conversation(
                new PrintSumCommand("SUM", first, second),
                new PrintProductCommand("MULT", first, second),
                new PrintQuotientCommand("DIV", first, second)
        );

        conversation.run();
    }
}

