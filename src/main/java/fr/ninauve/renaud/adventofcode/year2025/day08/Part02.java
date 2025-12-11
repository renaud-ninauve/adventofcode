package fr.ninauve.renaud.adventofcode.year2025.day08;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap.SimpleEntry;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part02 {

  public static void main(String... args) throws Exception {
    List<String> input = Files.readAllLines(Path.of(Part02.class.getResource("/year2025/day08/input.txt").toURI()));
    System.out.println(Part02.solve(input, 1000));
  }

  public static long solve(List<String> input, int nbConnections) {
    List<Coordinates3D> junctionBoxes = parse(input);
    Map<Vector3D, Double> distances = distances(junctionBoxes);
    TreeSet<Vector3D> sortedVectors = new TreeSet<>(Comparator.comparing(distances::get));
    sortedVectors.addAll(distances.keySet());
    List<Vector3D> connections = sortedVectors.stream()
        .limit(nbConnections)
        .collect(Collectors.toList());

    Set<Set<Coordinates3D>> circuits = circuits(junctionBoxes, connections);
    while (circuits.size() > 1) {
      connections.add(sortedVectors.higher(connections.getLast()));
      circuits = circuits(junctionBoxes, connections);
    }
    Vector3D last = connections.getLast();
    return last.a().x() * last.b().x();
  }

  private static List<Coordinates3D> parse(List<String> input) {
    return input.stream()
        .map(line -> line.split(","))
        .map(splitted -> {
          return new Coordinates3D(
              Long.parseLong(splitted[0]),
              Long.parseLong(splitted[1]),
              Long.parseLong(splitted[2])
          );
        }).toList();
  }

  private static Map<Vector3D, Double> distances(List<Coordinates3D> coordsList) {
    final Set<Coordinates3D> remaining = new HashSet<>(coordsList);
    final Map<Vector3D, Double> distances = new HashMap<>();
    for (Coordinates3D origin : coordsList) {
      remaining.remove(origin);
      for (Coordinates3D target : remaining) {
        double distance = Coordinates3D.distance(origin, target);
        distances.put(new Vector3D(origin, target), distance);
      }
    }
    return distances;
  }

  private static Set<Set<Coordinates3D>> circuits(List<Coordinates3D> allCoordinates, List<Vector3D> connections) {
    record ToVisit(Coordinates3D start, Coordinates3D current) {
    }

    Map<Coordinates3D, Set<Coordinates3D>> neighbours = connections.stream()
        .flatMap(e -> Stream.of(
            new SimpleEntry<>(e.a(), e.b()),
            new SimpleEntry<>(e.b(), e.a())
        )).collect(Collectors.groupingBy(Entry::getKey, Collectors.mapping(Entry::getValue, Collectors.toSet())));

    Set<Set<Coordinates3D>> circuits = new HashSet<>();
    Set<Coordinates3D> remaining = new HashSet<>(allCoordinates);
    while (!remaining.isEmpty()) {
      Coordinates3D origin = remaining.stream().findFirst().get();
      Set<Coordinates3D> circuit = circuitContaining(origin, neighbours);
      circuits.add(circuit);
      remaining.removeAll(circuit);
    }
    return circuits;
  }

  private static Set<Coordinates3D> circuitContaining(Coordinates3D origin, Map<Coordinates3D, Set<Coordinates3D>> neighbours) {
    Queue<Coordinates3D> toVisit = new LinkedList<>();
    toVisit.add(origin);
    Set<Coordinates3D> visited = new HashSet<>();
    while (!toVisit.isEmpty()) {
      Coordinates3D current = toVisit.poll();
      if (visited.contains(current)) {
        continue;
      }
      visited.add(current);
      toVisit.addAll(neighbours.getOrDefault(current, Set.of()));
    }
    return visited;
  }
}
