package fr.ninauve.renaud.adventofcode.year2024.day17;

public record Address(int value) {
    public static final Address ZERO = new Address(0);

    public static Address valueOf(int value) {
        return new Address(value);
    }

    public static Address valueOf(long value) {
        return new Address((int) value);
    }

    public static Address valueOf(Word tribit) {
        return new Address(tribit.value());
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

    public Address increment() {
        return Address.valueOf(value + 1);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
