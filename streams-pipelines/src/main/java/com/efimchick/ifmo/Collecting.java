package com.efimchick.ifmo;


import com.efimchick.ifmo.util.CourseResult;
import com.efimchick.ifmo.util.Person;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Collecting {

    public int sum(IntStream intStream) {
        return intStream.sum();
    }

    public int production(IntStream intStream) {
        return intStream.reduce(1, (a, b) -> a * b);
    }

    public int oddSum(IntStream intStream) {
        return intStream.filter(a -> a % 2 != 0).sum();
    }

    public Map<Integer, Integer> sumByRemainder(int divisor, IntStream intStream) {
        return intStream.boxed()
                .collect(Collectors.groupingBy(a -> a % divisor, Collectors.summingInt(Integer::intValue)));
    }

    public Map<Person, Double> totalScores(Stream<CourseResult> courseResultStream) {
        List<CourseResult> results = courseResultStream.collect(Collectors.toList());

        long countCourses = results.stream()
                .map(s -> s.getTaskResults().keySet())
                .flatMap(Collection::stream)
                .distinct()
                .count();

        return results.stream().collect(Collectors.groupingBy(
                CourseResult::getPerson,
                Collectors.summingDouble(courseResult -> (double) courseResult
                        .getTaskResults()
                        .values()
                        .stream()
                        .reduce(Integer::sum)
                        .orElse(0) / countCourses)
        ));
    }

    public double averageTotalScore(Stream<CourseResult> courseResultStream) {
        Map<Person, Double> totalScores = totalScores(courseResultStream);
        return totalScores.values().stream().reduce(0.0, Double::sum) / totalScores.size();
    }

    public Map<String, Double> averageScoresPerTask(Stream<CourseResult> courseResultStream) {
        List<CourseResult> resultsList = courseResultStream.collect(Collectors.toList());
        long countPerson = resultsList.stream()
                .map(CourseResult::getPerson)
                .distinct()
                .count();

        return resultsList.stream()
                .flatMap(courseResult -> courseResult.getTaskResults().entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.summingDouble(pair -> pair.getValue() / (double) countPerson)));
    }

    public Map<Person, String> defineMarks(Stream<CourseResult> courseResultStream) {
        Map<Person, Double> totalScoresMap = totalScores(courseResultStream);
        return totalScoresMap
                .entrySet()
                .stream()
                .collect(
                        Collectors.toMap(Map.Entry::getKey, v -> defineScore(v.getValue()))
                );
    }
    public Collector<CourseResult, Table, String> printableStringCollector() {
        return new Collector<>() {
            @Override
            public Supplier<Table> supplier() {
                return Table::new;
            }

            @Override
            public BiConsumer<Table, CourseResult> accumulator() {
                return Table::addCourseResult;
            }

            @Override
            public BinaryOperator<Table> combiner() {
                return null;
            }

            @Override
            public Function<Table, String> finisher() {
                return Table -> {
                    StringBuilder sb = new StringBuilder();
                    Table.createTable(sb);
                    return sb.toString();
                };
            }

            @Override
            public Set<Characteristics> characteristics() {
                return Collections.emptySet();
            }
        };
    }
    public String defineScore(double score) {
        if (score > 90) {
            return "A";
        } else if (score >= 83) {
            return "B";
        } else if (score >= 75) {
            return "C";
        } else if (score >= 68) {
            return "D";
        } else if (score >= 60) {
            return "E";
        } else {
            return "F";
        }
    }

    public String easiestTask(Stream<CourseResult> courseResultStream) {
        List<CourseResult> courseResultList = courseResultStream.collect(Collectors.toList());

        return courseResultList.stream()
                .flatMap(courseResult -> courseResult.getTaskResults().entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingDouble(Map.Entry::getValue)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElseThrow(RuntimeException::new);
    }
}
