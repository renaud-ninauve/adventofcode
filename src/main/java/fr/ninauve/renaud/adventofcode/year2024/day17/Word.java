package fr.ninauve.renaud.adventofcode.year2024.day17;

import java.util.Comparator;

public record Word(int value) implements Comparable<Word> {
    private static final int MASK = 0b111;

    public static Word ZERO = new Word(0);
    public static Word ONE = new Word(1);
    public static Word TWO = new Word(2);
    public static Word THREE = new Word(3);
    public static Word FOUR = new Word(4);
    public static Word FIVE = new Word(5);
    public static Word SIX = new Word(6);
    public static Word SEVEN = new Word(7);

    public static Word valueOf(int value) {
        return new Word(value & MASK);
    }

    public static Word valueOf(long value) {
        return Word.valueOf((int) value);
    }

    public Word increment() {
        return Word.valueOf(value + 1);
    }

    @Override
    public int compareTo(Word other) {
        return Comparator.comparing(Word::value).compare(this, other);
    }

    public long asLong() {
        return value;
    }

    public BigWord asBigWord() {
        return BigWord.valueOf(asLong());
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}