package fr.ninauve.renaud.adventofcode.year2024.day17;

import java.util.*;

public record Program(Map<Word, Word> values) {
    public static Program fromInputLine(String line) {
        int twoPoints = line.indexOf(':');
        String valuesString = line.substring(twoPoints + 2);
        String[] split = valuesString.split(",");
        final Map<Word, Word> values = new HashMap<>();
        for (int i = 0; i < split.length; i++) {
            Word address = new Word(i);
            Word value = new Word(Integer.parseInt(split[i]));
            values.put(address, value);
        }
        return new Program(Collections.unmodifiableMap(values));
    }

    public List<Word> asList() {
        final List<Word> dataList = new ArrayList<>();
        for(int i=0; i<values.size(); i++) {
            Word address = Word.valueOf(i);
            dataList.add(values.get(address));
        }
        return dataList;
    }

    public Word dataAt(Word address) {
        return values.getOrDefault(address, Word.ZERO);
    }

    public boolean containsDataAt(Word address) {
        return values.containsKey(address);
    }
}
