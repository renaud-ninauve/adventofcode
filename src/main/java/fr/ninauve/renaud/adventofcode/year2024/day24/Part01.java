package fr.ninauve.renaud.adventofcode.year2024.day24;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part01 {

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(Part01.class
                .getResource("/year2024/day24/input.txt").toURI()), StandardCharsets.UTF_8);
        System.out.println(Part01.solve(input));
    }

    public static Map<String, String> parse(List<String> input) {
        final Map<String, String> expressions = new HashMap<>();
        int i = 0;
        String line;
        while (!(line = input.get(i)).isBlank()) {
            String[] split = line.split(": ");
            String inputWire = split[0];
            String value = split[1];
            expressions.put(inputWire, value);
            i++;
        }
        for (int j = i + 1; j < input.size(); j++) {
            line = input.get(j);
            String[] split = line.split(" -> ");
            String expression = split[0];
            String outputWire = split[1];
            expressions.put(outputWire, expression);
        }
        return expressions;
    }

    public static long solve(List<String> input) {
        List<Boolean> result = new ArrayList<>();
        Map<String, String> expressions = parse(input);
        DecimalFormat numberFormat = new DecimalFormat("00");
        for (int i = 0; i < 100; i++) {
            String outputWire = "z" + numberFormat.format(i);
            boolean digit = compute(expressions, outputWire);
            result.add(digit);
        }

        long finalResult = 0;
        for(int i=0; i<result.size(); i++) {
            Boolean digitBoolean = result.get(i);
            int digit = digitBoolean ? 1 : 0;
            long shift = Long.rotateLeft(digit, i);
            finalResult += shift;
        }
        return finalResult;
    }

    public static boolean compute(Map<String, String> expressions, String outputWire) {
        String expression = expressions.get(outputWire);
        if (expression == null) {
            return false;
        }
        if (expression.equals("0")) {
            return false;
        }
        if (expression.equals("1")) {
            return true;
        }
        String[] split = expression.split(" ");
        String a = split[0];
        String operation = split[1];
        String b = split[2];

        boolean aValue = compute(expressions, a);
        boolean bValue = compute(expressions, b);

        if (operation.equals("AND")) {
            return aValue && bValue;
        }
        if (operation.equals("OR")) {
            return aValue || bValue;
        }
        return aValue ^ bValue;
    }
}
