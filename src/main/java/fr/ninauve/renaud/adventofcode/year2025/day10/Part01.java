package fr.ninauve.renaud.adventofcode.year2025.day10;

import fr.ninauve.renaud.adventofcode.year2025.day10.Light.LightStatus;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static fr.ninauve.renaud.adventofcode.year2025.day10.Button.pushButton;

public class Part01 {
    private static final Pattern lightsPattern = Pattern.compile("\\[(.*)\\]");
    private static final Pattern buttonsPattern = Pattern.compile("\\(([,0-9]*)\\)");

    public static void main(String... args) throws Exception {
        List<String> input = Files.readAllLines(Path.of(Part01.class.getResource("/year2025/day10/input.txt").toURI()));
        System.out.println(Part01.solve(input));
    }

    public static long solve(List<String> input) {
        long result = 0L;
        for (String line : input) {
            Matcher lightsMatcher = lightsPattern.matcher(line);
            lightsMatcher.find();
            String lightsString = lightsMatcher.group(1);
            List<Light> endingStatus = IntStream.range(0, lightsString.length())
                    .mapToObj(i -> {
                        char c = lightsString.charAt(i);
                        LightStatus status = c == '#' ? LightStatus.ON : LightStatus.OFF;
                        return new Light(i, status);
                    }).toList();

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

            List<Light> initialStatus = endingStatus.stream()
                    .map(l -> new Light(l.index(), LightStatus.OFF))
                    .toList();

            result += solve(new MachineStatus(initialStatus), buttons, new MachineStatus(endingStatus));
        }
        return result;
    }

    private static long solve(MachineStatus initialStatus, List<Button> buttons, MachineStatus endStatus) {
        record Current(MachineStatus currentStatus, List<Button> currentPushs, Button nextButton) {
        }

        Map<MachineStatus, List<Button>> shortest = new HashMap<>();
        Queue<Current> toVisit = new LinkedList<>(buttons.stream()
                .map(b -> new Current(initialStatus, List.of(), b)).toList());
        while (!toVisit.isEmpty()) {
            Current current = toVisit.poll();
            Button nextButton = current.nextButton();
            MachineStatus currentStatus = current.currentStatus();
            MachineStatus newStatus = pushButton(nextButton, currentStatus);
            List<Button> currentPushs = current.currentPushs();
            List<Button> lastShortest = shortest.get(newStatus);

            List<Button> newShortest = new ArrayList<>(currentPushs);
            newShortest.add(nextButton);

            if (lastShortest == null || newShortest.size() < lastShortest.size()) {
                shortest.put(newStatus, newShortest);
                toVisit.addAll(buttons.stream().map(b -> new Current(newStatus, newShortest, b)).toList());
            }
        }
        return shortest.get(endStatus).size();
    }
}
