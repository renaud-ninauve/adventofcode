package fr.ninauve.renaud.adventofcode.year2024.day14;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Part01 {
    private static final Area AREA = new Area(101L, 103L);

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(Part01.class
                .getResource("/year2024/day14/input.txt").toURI()), StandardCharsets.UTF_8);
        System.out.println(Part01.solve(input, AREA));
    }

    public static long solve(List<String> input, Area area) {
        List<Robot> robots = input.stream()
                .map(line -> Robot.fromInput(line, area))
                .toList();

        List<Robot> futureRobots = robots.stream()
                .map(robot -> robot.advanceInTime(100L))
                .toList();

        Map<Area.Quadrant, Long> countByQuadrant = futureRobots.stream()
                .map(Robot::position)
                .map(area::quadrantOf)
                .filter(quadrant -> !Area.Quadrant.NONE.equals(quadrant))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return countByQuadrant.values().stream()
                .reduce(1L, (a, b) -> a * b);
    }
}
