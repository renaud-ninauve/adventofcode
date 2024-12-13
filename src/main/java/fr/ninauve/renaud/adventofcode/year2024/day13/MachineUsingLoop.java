package fr.ninauve.renaud.adventofcode.year2024.day13;

import lombok.Data;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Data
public class MachineUsingLoop implements Machine {
    private final Collection<Button> buttons;

    @Override
    public Map<ButtonId, BigInteger> clicksFor(Location prizeLocation) {
        Location start = new Location(0, 0);
        Button buttonA = buttons.stream().filter(button -> button.id() == ButtonId.A).findFirst().orElseThrow();
        Button buttonB = buttons.stream().filter(button -> button.id() == ButtonId.B).findFirst().orElseThrow();

        long ax = (long) nbClicks(prizeLocation.x(), buttonA.delta().x()) + 1;
        long ay = (long) nbClicks(prizeLocation.y(), buttonA.delta().y()) + 1;
        long maxA = Math.min(ax, ay);

        Map<ButtonId, BigInteger> clicks = new HashMap<>();
        for (long a = 0; a < maxA; a++) {
            Location originB = start.add(buttonA.delta().mult(a));
            Location remaining = prizeLocation.substract(originB);
            if (remaining.x() < 0 || remaining.y() < 0) {
                continue;
            }
            double bx = nbClicks(remaining.x(), buttonB.delta().x());
            double by = nbClicks(remaining.y(), buttonB.delta().y());
            if (bx != by) {
                continue;
            }
            if (bx != (long) bx || by != (long) by) {
                continue;
            }
            Map<ButtonId, BigInteger> currentClicks = Map.of(ButtonId.A, BigInteger.valueOf(a), ButtonId.B, BigInteger.valueOf((long) bx));
            if (clicks.isEmpty() || price(currentClicks).compareTo(price(clicks)) < 0) {
                clicks = currentClicks;
            }
        }
        return clicks;
    }

    private double nbClicks(long target, long delta) {
        return (double) target / (double) delta;
    }
}
