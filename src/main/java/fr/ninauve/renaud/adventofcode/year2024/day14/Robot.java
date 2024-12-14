package fr.ninauve.renaud.adventofcode.year2024.day14;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Robot(Location position, Location velocity, Area area) {
    private static final Pattern INPUT = Pattern.compile("p=(-?\\d+),(-?\\d+) v=(-?\\d+),(-?\\d+)");

    public static Robot fromInput(String input, Area area) {
        Matcher matcher = INPUT.matcher(input);
        matcher.matches();
        long x = Long.parseLong(matcher.group(1));
        long y = Long.parseLong(matcher.group(2));
        long vx = Long.parseLong(matcher.group(3));
        long vy = Long.parseLong(matcher.group(4));
        return new Robot(new Location(x, y), new Location(vx, vy), area);
    }

    public Robot advanceInTime(long seconds) {
        Location newPosition = position.add(velocity.mult(seconds));
        Location teleportPosition = new Location(teleport(newPosition.x(), area.width()), teleport(newPosition.y(), area().height()));
        return new Robot(teleportPosition, velocity, area);
    }

    private long teleport(long value, long limit) {
        long modulo = value % limit;
        if (modulo < 0) {
            return limit + modulo;
        }
        return modulo;
    }
}
