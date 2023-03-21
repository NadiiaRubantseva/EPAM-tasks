package com.efimchick.ifmo.streams.countwords;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Words {

    public String countWords(List<String> lines) {
        String result = lines.stream()
                .map(line -> line.replaceAll("[^a-zA-Zа-яА-Я]", " "))
                .flatMap(line -> Arrays.stream(line.toLowerCase().split("\\s+")))
                .filter(s -> s.length() >= 4)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() >= 10)
                .sorted(Map.Entry.comparingByKey())
                .sorted((e1, e2) -> (int) (e2.getValue() - e1.getValue()))
                .map(e -> String.format("%s - %d\n", e.getKey(), e.getValue()))
                .collect(Collectors.joining());
        return result.substring(0, result.length() - 1);
    }
}
