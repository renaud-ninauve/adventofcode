package fr.ninauve.renaud.adventofcode.year2025.day09;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Part02 {

    public static void main(String... args) throws Exception {
        List<String> input = Files.readAllLines(Path.of(Part02.class.getResource("/year2025/day09/input.txt").toURI()));
        System.out.println(Part02.solve(input));
    }

    public static long solve(List<String> input) {
        final List<Point> points = parse(input);
        final Scale compactScale = compactScale(points);
        final Row maxRow = compactScale.scaleRows().getLast().scaled();
        final Col maxCol = compactScale.scaleCols().getLast().scaled();
        final List<Point> compactPoints = applyScale(compactScale, points);
        final Set<Point> lines = lines(compactPoints);
        final Set<Point> outsideArea = outsideArea(maxRow, maxCol, lines);

        print(compactPoints, lines, outsideArea, maxRow, maxCol);

        final Rectangle biggestRectangle = areas(compactPoints)
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .filter(e -> {
                    Rectangle rectangle = e.getKey();
                    return rectangle.borders()
                            .stream()
                            .noneMatch(outsideArea::contains);
                })
                .filter(e -> {
                    Rectangle rectangle = e.getKey();
                    return rectangle.inside()
                            .stream()
                            .noneMatch(outsideArea::contains);
                })
                .map(Map.Entry::getKey)
                .findFirst()
                .get();

        Scale uncompactScale = compactScale.invert();
        List<Point> uncompactedCorners = applyScale(uncompactScale, List.of(biggestRectangle.topLeft(), biggestRectangle.bottomRight()));
        Rectangle uncompactedBiggestRectangle = new Rectangle(uncompactedCorners.getFirst(), uncompactedCorners.getLast());
        return uncompactedBiggestRectangle.area();
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

    record ScaleElement<T>(T origin, T scaled) {
    }

    record Scale(List<ScaleElement<Col>> scaleCols, List<ScaleElement<Row>> scaleRows) {
        Scale invert() {
            List<ScaleElement<Col>> invertedCols = scaleCols.stream()
                    .map(s -> new ScaleElement<Col>(s.scaled(), s.origin()))
                    .toList();
            List<ScaleElement<Row>> invertedRows = scaleRows.stream()
                    .map(s -> new ScaleElement<Row>(s.scaled(), s.origin()))
                    .toList();
            return new Scale(invertedCols, invertedRows);
        }
    }

    private static Scale compactScale(List<Point> points) {
        TreeSet<Col> colsSet = points.stream()
                .map(Point::col)
                .collect(Collectors.toCollection(TreeSet::new));
        AtomicInteger colIndex = new AtomicInteger(0);
        List<ScaleElement<Col>> scaleCols = colsSet.stream()
                .map(col -> new ScaleElement<>(col, new Col(colIndex.getAndIncrement())))
                .toList();

        TreeSet<Row> rowsSet = points.stream()
                .map(Point::row)
                .collect(Collectors.toCollection(TreeSet::new));
        AtomicInteger rowIndex = new AtomicInteger(0);
        List<ScaleElement<Row>> scaleRows = rowsSet.stream()
                .map(row -> new ScaleElement<>(row, new Row(rowIndex.getAndIncrement())))
                .toList();

        return new Scale(scaleCols, scaleRows);
    }

    private static void print(List<Point> points, Set<Point> lines, Set<Point> outside, Row maxRow, Col maxCol) {
        for (long row = 0; row <= maxRow.value(); row++) {
            StringBuilder sb = new StringBuilder();
            for (long col = 0; col <= maxCol.value(); col++) {
                Point point = new Point(new Row(row), new Col(col));
                if (points.contains(point)) {
                    sb.append("#");
                } else if (lines.contains(point)) {
                    sb.append("X");
                } else if (outside.contains(point)) {
                    sb.append("O");
                } else {
                    sb.append(".");
                }
            }
            System.out.println(sb);
        }
    }

    private static List<Point> applyScale(Scale scale, List<Point> points) {
        final Map<Col, Col> scaleCols = scale.scaleCols()
                .stream()
                .collect(Collectors.toMap(ScaleElement::origin, ScaleElement::scaled));
        final Map<Row, Row> scaleRows = scale.scaleRows()
                .stream()
                .collect(Collectors.toMap(ScaleElement::origin, ScaleElement::scaled));

        return points.stream()
                .map(p -> new Point(scaleCols.get(p.col()), scaleRows.get(p.row())))
                .toList();
    }

    private static Set<Point> lines(List<Point> points) {
        List<Point> pointsAndOrigin = new ArrayList<>(points);
        pointsAndOrigin.add(points.getFirst());
        final Set<Point> lines = new HashSet<>();
        for (int i = 0; i < pointsAndOrigin.size() - 1; i++) {
            Point a = pointsAndOrigin.get(i);
            Point b = pointsAndOrigin.get(i + 1);
            if (a.row().equals(b.row())) {
                Col minCol = Stream.of(a, b).map(Point::col).min(Comparator.naturalOrder()).get();
                Col maxCol = Stream.of(a, b).map(Point::col).max(Comparator.naturalOrder()).get();
                List<Point> line = LongStream.rangeClosed(minCol.value(), maxCol.value())
                        .mapToObj(colValue -> new Point(new Col(colValue), a.row()))
                        .toList();
                lines.addAll(line);
            } else if (a.col().equals(b.col())) {
                Row minRow = Stream.of(a, b).map(Point::row).min(Comparator.naturalOrder()).get();
                Row maxRow = Stream.of(a, b).map(Point::row).max(Comparator.naturalOrder()).get();
                List<Point> line = LongStream.rangeClosed(minRow.value(), maxRow.value())
                        .mapToObj(rowValue -> new Point(new Row(rowValue), a.col()))
                        .toList();
                lines.addAll(line);
            } else {
                throw new IllegalArgumentException();
            }
        }
        return lines;
    }

    private static Set<Point> outsideArea(Row maxRow, Col maxCol, Set<Point> lines) {
        Set<Point> visited = new HashSet<>();
        Set<Point> outsideArea = new HashSet<>();
        Queue<Point> toVisit = new LinkedList<>();
        toVisit.addAll(List.of(
                new Point(new Row(0), new Col(0)),
                new Point(new Row(0), maxCol),
                new Point(maxRow, new Col(0)),
                new Point(maxRow, maxCol)));

        while (!toVisit.isEmpty()) {
            Point point = toVisit.poll();
            if (visited.contains(point)) {
                continue;
            }
            visited.add(point);
            if (!lines.contains(point)) {
                outsideArea.add(point);
                toVisit.addAll(point.upDownLeftRight(maxRow, maxCol));
            }
        }
        return outsideArea;
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
}
