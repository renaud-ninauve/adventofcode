package fr.ninauve.renaud.adventofcode.year2025.day09;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap.SimpleEntry;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Part02 {

    public static void main(String... args) throws Exception {
        List<String> input = Files.readAllLines(Path.of(Part02.class.getResource("/year2025/day09/input.txt").toURI()));
        System.out.println(Part02.solve(input));
    }

    public static long solve(List<String> input) {
        final List<Point> points = parse(input);
        final Map<Rectangle, Long> areas = areas(points);
        final TreeMap<Row, List<Line>> lines = lines(points);
        final TreeMap<Row, List<Horizontal>> insideHorizontals = insideHorizontals(lines);

        return areas.entrySet()
                .stream()
                .filter(e -> {
                    Rectangle rectangle = e.getKey();
                    return rectangle.horizontals()
                            .allMatch(h -> {
                                List<Horizontal> insideHorizontalsRow = insideHorizontals.getOrDefault(h.row(), List.of());
                                return insideHorizontalsRow.stream()
                                        .anyMatch(i -> i.start().col().compareTo(h.start().col()) <= 0
                                                && i.end().col().compareTo(h.end().col()) >= 0);
                            });
                }).map(Entry::getValue)
                .mapToLong(Long::longValue)
                .max()
                .getAsLong();
    }

    private static List<Point> parse(List<String> input) {
        return input.stream()
                .map(line -> line.split(","))
                .map(splitted -> {
                    return new Point(
                            new Col(Long.parseLong(splitted[0])),
                            new Row(Long.parseLong(splitted[1]))
                    );
                }).toList();
    }

    private static TreeMap<Row, List<Line>> lines(List<Point> points) {
        TreeMap<Row, List<Line>> lines = new TreeMap<>();
        for (int i = 0; i < points.size() - 1; i++) {
            Point a = points.get(i);
            Point b = points.get(i + 1);
            if (a.row().equals(b.row())) {
                List<Line> current = lines.getOrDefault(a.row(), new ArrayList<>());
                current.add(new Horizontal(a, b));
                lines.put(a.row(), current);
            } else if (a.col().equals(b.col())) {
                List<Line> current = lines.getOrDefault(a.row(), new ArrayList<>());
                current.add(new Vertical(a, b));
                lines.put(a.row(), current);
            }
        }
        return lines;
    }

    private static TreeMap<Row, List<Horizontal>> insideHorizontals(TreeMap<Row, List<Line>> lines) {
        Map<Point, List<Line>> indexedLines = lines.values().stream()
                .flatMap(List::stream)
                .flatMap(l -> l.endPoints().map(end -> new SimpleEntry<>(end, l)))
                .collect(Collectors.groupingBy(Entry::getKey, Collectors.mapping(Entry::getValue, Collectors.toList())));

        Map<Row, List<Horizontal>> horizontals = lines.values().stream()
                .flatMap(List::stream)
                .filter(l -> l instanceof Horizontal)
                .map(l -> (Horizontal) l)
                .collect(Collectors.groupingBy(Horizontal::row));

        Row minRow = indexedLines.keySet().stream().map(Point::row).min(Comparator.naturalOrder()).get();
        Row maxRow = indexedLines.keySet().stream().map(Point::row).max(Comparator.naturalOrder()).get();
        TreeMap<Row, List<Horizontal>> inside = new TreeMap<>();
        List<Horizontal> lastHorizontals = List.of();
        for (long rowValue = minRow.value(); rowValue <= maxRow.value(); rowValue++) {
            Row row = new Row(rowValue);

        }
    }

    private static Map<Rectangle, Long> areas(List<Point> points) {
        final Set<Point> remaining = new HashSet<>(points);
        final Map<Rectangle, Long> areas = new HashMap<>();
        for (Point origin : points) {
            remaining.remove(origin);
            for (Point target : remaining) {
                Rectangle rectangle = new Rectangle(origin, target);
                areas.put(rectangle, rectangle.area());
            }
        }
        return areas;
    }

    private static TreeMap<Row, TreeSet<Col>> indexPoints(List<Point> points) {
        return points.stream()
                .collect(
                        Collectors.groupingBy(
                                Point::row,
                                TreeMap::new,
                                Collectors.mapping(Point::col, Collectors.toCollection(TreeSet::new))
                        ));
    }
}
