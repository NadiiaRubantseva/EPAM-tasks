package com.epam.rd.java.basic.topic08.util;

import com.epam.rd.java.basic.topic08.entity.Answer;
import com.epam.rd.java.basic.topic08.entity.Question;
import com.epam.rd.java.basic.topic08.entity.Test;

import java.util.Collections;
import java.util.Comparator;

public final class Sorter {
    // //////////////////////////////////////////////////////////
    // these are comparators
    // //////////////////////////////////////////////////////////

    /**
     * Sorts questions by question text
     */
    public static Comparator<Question> SORT_QUESTIONS_BY_QUESTION_TEXT = Comparator.comparing(Question::getQuestionText);

    /**
     * Sorts questions by answers number.
     */
    public static Comparator<Question> SORT_QUESTIONS_BY_ANSWERS_NUMBER = Comparator.comparingInt(o -> o.getAnswer().size());

    /**
     * Sorts answers.
     */
    public static Comparator<Answer> SORT_ANSWERS_BY_CONTENT = Comparator.comparing(Answer::getContent);

    /**
     * Sorts answers by correct.
     */
    public static Comparator<Answer> SORT_ANSWERS_BY_CORRECT = (o1, o2) -> {
        if (o1.isCorrect() && !o2.isCorrect()) {
            return -1;
        }
        if (o2.isCorrect() && !o1.isCorrect()) {
            return 1;
        }
        return 0;
    };

    // //////////////////////////////////////////////////////////
    // these methods take Test object and sort it
    // with according comparator
    // //////////////////////////////////////////////////////////

    public static void sortQuestionsByQuestionText(Test test) {
        test.getQuestion().sort(SORT_QUESTIONS_BY_QUESTION_TEXT);
    }

    public static void sortQuestionsByAnswersNumber(Test test) {
        test.getQuestion().sort(SORT_QUESTIONS_BY_ANSWERS_NUMBER);
    }

    public static void sortAnswersByContent(Test test) {
        for (Question question : test.getQuestion()) {
            question.getAnswer().sort(SORT_ANSWERS_BY_CONTENT);
        }
    }

    public static final void sortAnswersByCorrect(Test test) {
        for (Question question : test.getQuestion()) {
            Collections.sort(question.getAnswer(), SORT_ANSWERS_BY_CORRECT);
        }
    }
}
