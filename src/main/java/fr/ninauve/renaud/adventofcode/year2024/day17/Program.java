package fr.ninauve.renaud.adventofcode.year2024.day17;

import java.util.*;

public record Program(Map<Address, Word> values) {
    public static Program fromInputLine(String line) {
        int twoPoints = line.indexOf(':');
        String valuesString = line.substring(twoPoints + 2);
        String[] split = valuesString.split(",");
        final Map<Address, Word> values = new HashMap<>();
        for (int i = 0; i < split.length; i++) {
            Address address = Address.valueOf(i);
            Word value = Word.valueOf(Integer.parseInt(split[i]));
            values.put(address, value);
        }
        return new Program(Collections.unmodifiableMap(values));
    }

    public List<Word> asList() {
        final List<Word> dataList = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            Address address = Address.valueOf(i);
            dataList.add(values.get(address));
        }
        return dataList;
    }

    public Word dataAt(Address address) {
        return values.getOrDefault(address, Word.ZERO);
    }

    public boolean containsDataAt(Address address) {
        return values.containsKey(address);
    }
}
