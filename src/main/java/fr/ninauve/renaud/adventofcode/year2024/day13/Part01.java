package fr.ninauve.renaud.adventofcode.year2024.day13;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Part01 {
    static final Pattern BUTTON_LINE = Pattern.compile("Button ([AB]): X\\+(\\d+), Y\\+(\\d+)");
    static final Pattern PRIZE_LINE = Pattern.compile("Prize: X=(\\d+), Y=(\\d+)");
    static final Pattern BLANK_LINE = Pattern.compile("\\s*");

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(Part01.class
                .getResource("/year2024/day13/input.txt").toURI()), StandardCharsets.UTF_8);
        System.out.println(Part01.solve(input));
    }

    public static BigInteger solve(List<String> input) {
        List<Game> games = parse(input);
        return games.stream()
                .map(game -> {
                    Machine machine = game.machine();
                    Map<ButtonId, BigInteger> clicks = machine.clicksFor(game.prize());
                    return machine.price(clicks);
                }).reduce(BigInteger.ZERO, BigInteger::add);
    }

    public static List<Game> parse(List<String> input) {
        final List<Game> games = new ArrayList<>();
        Optional<Integer> index = Optional.of(0);
        while ((index = findNextMatching(index.get(), input, BUTTON_LINE)).isPresent()) {
            int startGame = index.get();
            Optional<Game> game = parseGame(input.subList(startGame, startGame + 3));
            games.add(game.get());
            index = findNextMatching(startGame, input, BLANK_LINE);
            if (index.isEmpty()) {
                return games;
            }
        }
        return games;
    }

    private static Optional<Game> parseGame(List<String> input) {
        Optional<Integer> aLine = findNextMatching(0, input, BUTTON_LINE);
        Optional<Integer> bLine = aLine.flatMap(a -> findNextMatching(a + 1, input, BUTTON_LINE));
        Optional<Integer> prizeLine = bLine.flatMap(a -> findNextMatching(a + 1, input, PRIZE_LINE));
        if (Stream.of(aLine, bLine, prizeLine).anyMatch(Optional::isEmpty)) {
            return Optional.empty();
        }

        String aLineString = input.get(aLine.get());
        Button buttonA = parseButton(aLineString);
        String bLineString = input.get(bLine.get());
        Button buttonB = parseButton(bLineString);
        String prizeLineString = input.get(prizeLine.get());
        Location prize = parsePrize(prizeLineString);
        return Optional.of(new Game(new MachineUsingDFS(List.of(buttonA, buttonB)), prize));
    }

    public record Game(Machine machine, Location prize) {}

    public record Parsed<T>(T value, int lastIndex) {}

    private static Button parseButton(String line) {
        Matcher aMatcher = BUTTON_LINE.matcher(line);
        aMatcher.matches();
        String buttonName = aMatcher.group(1);
        ButtonId id = ButtonId.valueOf(buttonName);
        Location delta = new Location(Integer.parseInt(aMatcher.group(2)), Integer.parseInt(aMatcher.group(3)));
        return new Button(id, delta);
    }

    private static Location parsePrize(String line) {
        Matcher matcher = PRIZE_LINE.matcher(line);
        matcher.matches();
        return new Location(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
    }
    private static Optional<Integer> findNextMatching(int index, List<String> lines, Pattern pattern) {
        for (int i = index; i < lines.size(); i++) {
            String line = lines.get(i);
            if (pattern.matcher(line).matches()) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }
}
