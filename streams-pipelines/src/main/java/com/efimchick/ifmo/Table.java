package com.efimchick.ifmo;

import com.efimchick.ifmo.util.CourseResult;
import com.efimchick.ifmo.util.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Table {
    private static final String STUDENT = "Student";
    private static final String SPACE = " ";
    private static final String SEPARATOR = " | ";
    private static final String TOTAL = "Total";
    private static final String MARK = "Mark";
    private static final String SEPARATOR_NEXT_LINE = " |\n";
    private static final int MARK_SPACE_REPEAT = 3;
    private static final String AVERAGE = "Average";
    private static final String END_TABLE = " |";
    private static final String NEW_LINE = "\n";
    private static final String DOUBLE_FORMAT = "%.2f";
    private static final String COMMA = ",";
    private static final String FULL_STOP = ".";
    private static final String FULL_NAME_FORMAT = "%s %s";
    private static final int SKIP_TASK_SCORE = 0;
    private final List<CourseResult> results = new ArrayList<>();
    private List<String> tasks = new ArrayList<>();
    private List<String> persons = new ArrayList<>();


    public void addCourseResult(CourseResult courseResult) {
        results.add(courseResult);
    }

    public void createTable(StringBuilder builder) {
        tasks = getTasksNames();
        persons = getPersonFullNames();

        createHeader(builder);
        createBody(builder);
        createFooter(builder);
    }

    private void createHeader(StringBuilder builder) {
        builder.append(STUDENT);
        builder.append(SPACE.repeat(calculateDifferentLength(getMaxPersonNameLength(), STUDENT)));
        builder.append(SEPARATOR);
        tasks.forEach(task -> {
            builder.append(task);
            builder.append(SEPARATOR);
        });
        builder.append(TOTAL);
        builder.append(SEPARATOR);
        builder.append(MARK);
        builder.append(SEPARATOR_NEXT_LINE);
    }

    private void createBody(StringBuilder stringBuilder) {
        results.stream()
                .map(courseResult ->
                        getPersonFullName(courseResult)
                                + SPACE.repeat(calculateDifferentLength(getMaxPersonNameLength(), getPersonFullName(courseResult)))
                                + SEPARATOR
                                + convertTasksScoresToString(tasks, getPersonFullName(courseResult))
                                + formatTotalToString(courseResult)
                                + SEPARATOR
                                + SPACE.repeat(MARK_SPACE_REPEAT)
                                + new Collecting().defineScore(getTotalScores(courseResult))
                                + END_TABLE
                )
                .sorted()
                .forEach(str -> stringBuilder.append(str).append(NEW_LINE));
    }

    private void createFooter(StringBuilder stringBuilder) {
        stringBuilder.append(AVERAGE);
        stringBuilder.append(SPACE.repeat(calculateDifferentLength(getMaxPersonNameLength(), STUDENT)));
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(convertAverageScoresPerTaskToString());
        stringBuilder.append(String.format(DOUBLE_FORMAT, averageTotalScore()).replace(COMMA, FULL_STOP));
        stringBuilder.append(SEPARATOR);
        stringBuilder.append(SPACE.repeat(MARK_SPACE_REPEAT));
        stringBuilder.append(new Collecting().defineScore(averageTotalScore()));
        stringBuilder.append(END_TABLE);
    }

    public String convertAverageScoresPerTaskToString() {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, Double> collect = new Collecting().averageScoresPerTask(results.stream());
        tasks.forEach(task -> collect.entrySet().stream()
                .filter(e -> e.getKey().equals(task))
                .mapToDouble(Map.Entry::getValue)
                .forEach(d -> stringBuilder
                        .append(SPACE.repeat(task.length() - String.format(DOUBLE_FORMAT, d).length()))
                        .append(String.format(DOUBLE_FORMAT, d).replace(COMMA, FULL_STOP))
                        .append(SEPARATOR)));
        return stringBuilder.toString();
    }

    private List<String> getTasksNames() {
        return results.stream()
                .flatMap(results -> results.getTaskResults().keySet().stream())
                .distinct()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

    private List<String> getPersonFullNames() {
        return results.stream()
                .map(CourseResult::getPerson)
                .sorted(Comparator.comparing(Person::getLastName))
                .map(person -> person.getFirstName() + SPACE + person.getLastName())
                .collect(Collectors.toList());
    }

    private Integer getMaxPersonNameLength() {
        return persons.stream()
                .mapToInt(String::length)
                .max()
                .orElse(SKIP_TASK_SCORE);
    }

    private Integer calculateDifferentLength(Integer maxLength, String columnName) {
        return maxLength - columnName.length();
    }

    private String getPersonFullName(CourseResult courseResult) {
        return String.format(FULL_NAME_FORMAT, courseResult.getPerson().getLastName(),
                courseResult.getPerson().getFirstName());
    }

    private String convertTasksScoresToString(List<String> tasks, String personName) {
        StringBuilder stringBuilder = new StringBuilder();

        tasks.forEach(t ->
                stringBuilder
                        .append(SPACE.repeat(calculateDifferentLength(t.length(), String.valueOf(getStudentScoreForTask(personName, t)))))
                        .append(getStudentScoreForTask(personName, t))
                        .append(SEPARATOR));

        return stringBuilder.toString();
    }

    private int getStudentScoreForTask(String personName, String task) {
        return results.stream()
                .filter(cr -> getPersonFullName(cr).equals(personName))
                .flatMap(cr -> cr.getTaskResults().entrySet().stream())
                .filter(e -> e.getKey().equals(task))
                .mapToInt(Map.Entry::getValue)
                .findFirst()
                .orElse(SKIP_TASK_SCORE);
    }

    private double getTotalScores(CourseResult courseResult) {
        return courseResult.getTaskResults().values()
                .stream()
                .mapToDouble(Integer::intValue)
                .sum() / tasks.size();
    }

    private String formatTotalToString(CourseResult courseResult) {
        return String.format(DOUBLE_FORMAT, getTotalScores(courseResult)).replace(COMMA, FULL_STOP);
    }

    public double averageTotalScore() {
        long allNumberOfResults = (long) persons.size() * tasks.size();

        return results.stream()
                .map(CourseResult::getTaskResults)
                .flatMapToDouble(taskResult -> taskResult.values().stream()
                        .mapToDouble(value -> value))
                .sum() / allNumberOfResults;
    }
}
