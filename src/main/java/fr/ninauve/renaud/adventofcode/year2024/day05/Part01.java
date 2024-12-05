package fr.ninauve.renaud.adventofcode.year2024.day05;

import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@RequiredArgsConstructor
public class Part01 {
    private final Map<Long, Collection<Long>> rules;
    private final List<List<Long>> pagesLists;

    public static Part01 parse(List<String> lines) {
        final Map<Long, Collection<Long>> rules = new HashMap<>();
        final List<List<Long>> pagesLists = new ArrayList<>();

        for (String line : lines) {
            if (line.contains("|")) {
                String[] splitted = line.split("\\|");
                long firstPage = Long.parseLong(splitted[0]);
                long secondPage = Long.parseLong(splitted[1]);

                Collection<Long> pages = rules.getOrDefault(secondPage, new ArrayList<>());
                pages.add(firstPage);
                rules.put(secondPage, pages);
            } else if (line.contains(",")) {
                String[] splitted = line.split(",");
                List<Long> pages = Arrays.stream(splitted).map(Long::parseLong).toList();
                pagesLists.add(pages);
            }
        }
        return new Part01(rules, pagesLists);
    }

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(fr.ninauve.renaud.adventofcode.year2024.day03.Part01.class
                .getResource("/year2024/day05/input.txt").toURI()), StandardCharsets.UTF_8);

        long xmas = parse(input).printQueue();
        System.out.println(xmas);
    }

    public long printQueue() {
        long sum = 0L;
        for(List<Long> pages: pagesLists) {
            if (isValid(pages)) {
                sum += middle(pages);
            }
        }
        return sum;
    }

    private long middle(List<Long> values) {
        return values.get(values.size()/2);
    }

    public boolean isValid(List<Long> values) {
        Collection<Long> forbidden = new ArrayList<>();
        for(long value: values) {
            if (forbidden.contains(value)) {
                return false;
            }
            forbidden.addAll(rules.getOrDefault(value, List.of()));
        }
        return true;
    }
}
