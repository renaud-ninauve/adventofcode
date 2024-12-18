package fr.ninauve.renaud.adventofcode.year2024.day17;

public record BigWord(int value) {
    public static final BigWord ZERO = new BigWord(0);

    public static BigWord valueOf(int value) {
        return new BigWord(value);
    }

    public static BigWord valueOf(long value) {
        return new BigWord((int) value);
    }

    public static BigWord valueOf(Word tribit) {
        return new BigWord(tribit.value());
    }

    public static BigWord fromInputLine(String line) {
        int twoPoints = line.indexOf(':');
        int value = Integer.parseInt(line.substring(twoPoints + 2));
        return new BigWord(value);
    }

    public long asLong() {
        return value;
    }

    public int asInt() {
        return value;
    }

    public Word asWord() {
        return Word.valueOf(asInt());
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
