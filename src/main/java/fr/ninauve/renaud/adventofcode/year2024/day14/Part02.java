package fr.ninauve.renaud.adventofcode.year2024.day14;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Part02 {
    private static final Area AREA = new Area(101L, 103L);

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(Part02.class
                .getResource("/year2024/day14/input.txt").toURI()), StandardCharsets.UTF_8);
        System.out.println(Part02.solve(input, AREA));
    }

    public static long solve(List<String> input, Area area) {
        List<Robot> robots = input.stream()
                .map(line -> Robot.fromInput(line, area))
                .toList();

        List<Robot> futureRobots = robots;
        for(long steps=1; steps<=100_000; steps++) {
            futureRobots = futureRobots.stream()
                    .map(robot -> robot.advanceInTime(1L))
                    .toList();
            Map<Location, List<Robot>> futureByPosition = futureRobots.stream()
                    .collect(Collectors.groupingBy(Robot::position));
            if (futureByPosition.size() == robots.size()) {
                return steps;
            }
        }
        return 0L;
    }
}
