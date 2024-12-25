package fr.ninauve.renaud.adventofcode.year2024.day25;

import java.util.ArrayList;
import java.util.List;

public class Part01 {

    public static Input parse(List<String> input) {
        final List<List<Integer>> locks = new ArrayList<>();
        final List<List<Integer>> keys = new ArrayList<>();

        int line = 0;
        while (line < input.size()) {
            boolean isKey = input.get(line).matches("\\.+");
            char symbol = '#';
            List<Integer> current = new ArrayList<>();
            for (int col = 0; col < 5; col++) {
                int height = 0;
                for (int row = 1; row < 6; row++) {
                    char c = input.get(line + row).charAt(col);
                    if (c == symbol) {
                        height++;
                    }
                }
                current.add(height);
            }
            if (isKey) {
                keys.add(current);
            } else {
                locks.add(current);
            }
            line+=8;
        }
        return new Input(locks, keys);
    }

    public record Input(List<List<Integer>> locks, List<List<Integer>> keys) {
    }
}
