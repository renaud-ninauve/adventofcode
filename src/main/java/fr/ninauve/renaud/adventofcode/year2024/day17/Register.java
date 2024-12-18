package fr.ninauve.renaud.adventofcode.year2024.day17;

public record Register(int value) {

    public static Register fromInputLine(String line) {
        int twoPoints = line.indexOf(':');
        int value = Integer.parseInt(line.substring(twoPoints + 2));
        return new Register(value);
    }
}
