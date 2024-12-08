package fr.ninauve.renaud.adventofcode.year2024.day08;

import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@RequiredArgsConstructor
public class Part01 {
    private final Map<Coordinates, Character> map;
    private final Map<Character, List<Coordinates>> antennas;
    private final int nbRows;
    private final int nbCols;

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(fr.ninauve.renaud.adventofcode.year2024.day03.Part01.class
                .getResource("/year2024/day08/input.txt").toURI()), StandardCharsets.UTF_8);

        Part01 part01 = Part01.parse(input);
        System.out.println(part01.solve());
    }

    public static Part01 parse(List<String> lines) {
        final Map<Coordinates, Character> map = new HashMap<>();
        final Map<Character, List<Coordinates>> antennas = new HashMap<>();

        int nbRows = lines.size();
        int nbCols = lines.get(0).length();

        for (int row = 0; row < nbRows; row++) {
            final String line = lines.get(row);
            for (int col = 0; col < nbCols; col++) {
                char c = line.charAt(col);
                Coordinates coordinates = Coordinates.builder()
                        .col(col)
                        .row(row)
                        .build();

                if (c == '.') {
                    continue;
                }
                map.put(coordinates, c);
                List<Coordinates> currentAntennas = antennas.getOrDefault(c, new ArrayList<>());
                currentAntennas.add(coordinates);
                antennas.put(c, currentAntennas);
            }
        }
        return new Part01(map, antennas, nbRows, nbCols);
    }

    public long solve() {
        Collection<Coordinates> antiantennas = antiantennas();
        return antiantennas.stream().distinct().count();
    }

    private Collection<Coordinates> antiantennas() {
        final List<Coordinates> antiantennas = new ArrayList<>();
        for (List<Coordinates> currentAntennas : antennas.values()) {
            for (Coordinates antennaA : currentAntennas) {
                for (Coordinates antennaB : currentAntennas) {
                    if (antennaA.equals(antennaB)) {
                        continue;
                    }

                    Coordinates ab = antennaA.delta(antennaB);
                    Coordinates antiantennaAB = antennaB.moveOf(ab);
                    if (isValid(antiantennaAB)) {
                        antiantennas.add(antiantennaAB);
                    }

                    Coordinates ba = antennaB.delta(antennaA);
                    Coordinates antiantennaBA = antennaA.moveOf(ba);
                    if (isValid(antiantennaBA)) {
                        antiantennas.add(antiantennaBA);
                    }
                }
            }
        }

        return antiantennas;
    }

    private boolean isValid(Coordinates coordinates) {
        return coordinates.getRow() >= 0 && coordinates.getCol() >= 0
                && coordinates.getRow() < nbRows && coordinates.getCol() < nbCols;
    }
}
