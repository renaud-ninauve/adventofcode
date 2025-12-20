package fr.ninauve.renaud.adventofcode.year2025.day09;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Part02 {

    public static void main(String... args) throws Exception {
        List<String> input = Files.readAllLines(Path.of(Part02.class.getResource("/year2025/day09/input.txt").toURI()));
        System.out.println(Part02.solve(input));
    }

    public static long solve(List<String> input) {
        List<Point> points = parse(input);
        Map<Rectangle, Long> areas = areas(points);
        TreeMap<Row, TreeSet<Col>> indexedPoints = indexPoints(points);
        return areas.entrySet()
                .stream()
                .filter(e -> {
                    Rectangle rectangle = e.getKey();
                    Point topLeft = rectangle.topLeft();
                    Point bottomRight = rectangle.bottomRight();
                    boolean isBottomRightAPoint = bottomRight.equals(rectangle.corner1()) || bottomRight.equals(rectangle.corner2());
                    boolean isTopLeftAPoint = topLeft.equals(rectangle.corner1()) || topLeft.equals(rectangle.corner2());
                    SortedMap<Row, TreeSet<Col>> rows = indexedPoints.subMap(topLeft.row(), true, bottomRight.row(), true);
                    if (rows.entrySet().stream()
                            .anyMatch(rowCols -> {
                                Row row = rowCols.getKey();
                                Col minCol = rowCols.getValue().getFirst();
                                boolean lastRow = row.compareTo(bottomRight.row()) == 0;
                                return (!lastRow || isBottomRightAPoint)
                                        && minCol.compareTo(topLeft.col()) > 0;
                            })) {
                        return false;
                    }
                    return rows.entrySet().stream()
                            .anyMatch(rowCols -> {
                                Row row = rowCols.getKey();
                                Col maxCol = rowCols.getValue().getLast();
                                boolean firstRow = row.compareTo(topLeft.row()) == 0;
                                return (!firstRow || isTopLeftAPoint)
                                        && maxCol.compareTo(bottomRight.col()) < 0;
                            });
                })
                .map(Entry::getValue)
                .mapToLong(l -> l)
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
