package fr.ninauve.renaud.adventofcode.year2025.day10;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static fr.ninauve.renaud.adventofcode.year2025.day10.Button.pushButton;

public class Part02 {
    private static final Pattern countersPattern = Pattern.compile("\\{(.*)\\}");
    private static final Pattern buttonsPattern = Pattern.compile("\\(([,0-9]*)\\)");

    public static void main(String... args) throws Exception {
        List<String> input = Files.readAllLines(Path.of(Part02.class.getResource("/year2025/day10/input.txt").toURI()));
        System.out.println(Part02.solve(input));
    }

    public static long solve(List<String> input) {
        long result = 0L;
        for (String line : input) {
            Matcher countersMatcher = countersPattern.matcher(line);
            countersMatcher.find();
            String countersString = countersMatcher.group(1);
            String[] splittedCounters = countersString.split(",");
            List<Counter> endingStatus = new ArrayList<>();
            for (int i = 0; i < splittedCounters.length; i++) {
                endingStatus.add(new Counter(i, Integer.parseInt(splittedCounters[i])));
            }
            List<Button> buttons = new ArrayList<>();
            Matcher buttonsMatcher = buttonsPattern.matcher(line);
            int buttonIndex = -1;
            while (buttonsMatcher.find()) {
                String buttonString = buttonsMatcher.group(1);
                String[] splittedButtonsString = buttonString.split(",");
                List<Integer> buttonLightsIndexes = Arrays.stream(splittedButtonsString)
                        .map(Integer::parseInt)
                        .toList();
                buttonIndex++;
                buttons.add(new Button(buttonIndex, buttonLightsIndexes));
            }

            List<Counter> initialStatus = endingStatus.stream()
                    .map(c -> new Counter(c.index(), 0))
                    .toList();

            result += solve(new Counters(initialStatus), buttons, new Counters(endingStatus));
        }
        return result;
    }

    private static long solve(Counters initialStatus, List<Button> buttons, Counters endStatus) {
        record Current(Counters currentStatus, List<Button> currentPushs, Button nextButton) {
        }

        System.out.println("trying to reach -> " + endStatus);
        Map<Counters, List<Button>> shortest = new HashMap<>();
        Queue<Current> toVisit = new LinkedList<>(buttons.stream()
                .map(b -> new Current(initialStatus, List.of(), b)).toList());
        while (!toVisit.isEmpty()) {
            Current current = toVisit.poll();
            Button nextButton = current.nextButton();
            Counters currentStatus = current.currentStatus();
            Counters newStatus = pushButton(nextButton, currentStatus);
            List<Button> currentPushs = current.currentPushs();
            List<Button> lastShortest = shortest.get(newStatus);

            List<Button> newShortest = new ArrayList<>(currentPushs);
            newShortest.add(nextButton);

            System.out.println("" + currentStatus + " -> " + newStatus);
            if (lastShortest == null || newShortest.size() < lastShortest.size()) {
                shortest.put(newStatus, newShortest);

                if (IntStream.range(0, endStatus.count())
                        .allMatch(i -> newStatus.counterAt(i).lte(endStatus.counterAt(i)))) {
                    toVisit.addAll(buttons.stream().map(b -> new Current(newStatus, newShortest, b)).toList());
                } else {
                    System.out.println("stopping because too far -> " + newStatus);
                }
            }
        }
        return shortest.get(endStatus).size();
    }
}
