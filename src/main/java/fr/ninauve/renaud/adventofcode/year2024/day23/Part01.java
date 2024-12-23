package fr.ninauve.renaud.adventofcode.year2024.day23;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Part01 {

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(Part01.class
                .getResource("/year2024/day23/input.txt").toURI()), StandardCharsets.UTF_8);
        System.out.println(Part01.solve(input));
    }

    public static long solve(List<String> input) {
        return groupsStartingWith(input, 't', 3).size();
    }

    public static Set<Set<String>> groupsStartingWith(List<String> input, Character letter, int size) {
        Map<String, Set<String>> links = parse(input);
        Collection<String> names = namesStartingWith(letter);
        Set<Set<String>> result = new HashSet<>();
        for (String name : names) {
            Set<Set<String>> currentGroups = walk(links, name, size);
            Set<Set<String>> filtered = currentGroups.stream().filter(group -> group.size() >= size).collect(Collectors.toSet());
            result.addAll(filtered);
        }
        return result;
    }

    private static Set<Set<String>> walk(Map<String, Set<String>> links, String start, int maxSize) {
        Set<Set<String>> groups = new HashSet<>();
        List<String> startGroup = new ArrayList<>();
        startGroup.add(start);

        Queue<List<String>> queue = new LinkedList<>();
        queue.add(startGroup);
        while (!queue.isEmpty()) {
            List<String> currentGroup = queue.poll();
            if (currentGroup.size() > 1 && currentGroup.getLast().equals(start)) {
                groups.add(new HashSet<>(currentGroup));
                continue;
            }
            if (currentGroup.size() > maxSize) {
                continue;
            }
            String last = currentGroup.getLast();
            Set<String> neighbours = links.getOrDefault(last, Set.of());
            for (String neighbour : neighbours) {
                if (!neighbour.equals(start) && currentGroup.contains(neighbour)) {
                    continue;
                }
                final List<String> newGroup = new ArrayList<>(currentGroup);
                newGroup.add(neighbour);
                queue.offer(newGroup);
            }
        }
        return groups;
    }

    public static Map<String, Set<String>> parse(List<String> input) {
        return input.stream()
                .map(line -> line.split("-"))
                .flatMap(split -> Stream.of(split, new String[]{split[1], split[0]}))
                .collect(Collectors.groupingBy(split -> split[0], Collectors.mapping(split -> split[1], Collectors.toSet())));
    }

    public static Collection<String> namesStartingWith(Character firstLetter) {
        return IntStream.rangeClosed('a', 'z')
                .mapToObj(suffix -> "" + firstLetter + (char) suffix)
                .toList();
    }
}
