package fr.ninauve.renaud.adventofcode.year2024.day13;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Data
public class MachineUsingMaths implements Machine {
    private final Collection<Button> buttons;

    @Override
    public Map<ButtonId, BigInteger> clicksFor(Location prizeLocation) {
        Button buttonA = buttons.stream().filter(button -> button.id() == ButtonId.A).findFirst().orElseThrow();
        Button buttonB = buttons.stream().filter(button -> button.id() == ButtonId.B).findFirst().orElseThrow();

        BigInteger targetX = BigInteger.valueOf(prizeLocation.x());
        BigInteger targetY = BigInteger.valueOf(prizeLocation.y());

        BigInteger aDeltaX = BigInteger.valueOf(buttonA.delta().x());
        BigInteger aDeltaY = BigInteger.valueOf(buttonA.delta().y());

        BigInteger bDeltaX = BigInteger.valueOf(buttonB.delta().x());
        BigInteger bDeltaY = BigInteger.valueOf(buttonB.delta().y());

        return clicksFor(targetX, targetY, aDeltaX, aDeltaY, bDeltaX, bDeltaY);
    }

    private Map<ButtonId, BigInteger> clicksFor(BigInteger targetX, BigInteger targetY, BigInteger aDeltaX, BigInteger aDeltaY, BigInteger bDeltaX, BigInteger bDeltaY) {
        BigInteger determinant = aDeltaX.multiply(bDeltaY).subtract(aDeltaY.multiply(bDeltaX));
        BigInteger determinantA = targetX.multiply(bDeltaY).subtract(targetY.multiply(bDeltaX));
        BigInteger determinantB = aDeltaX.multiply(targetY).subtract(aDeltaY.multiply(targetX));

        if (determinant.equals(BigInteger.ZERO)) {
            return Map.of();
        }

        BigInteger[] a = determinantA.divideAndRemainder(determinant);
        BigInteger[] b = determinantB.divideAndRemainder(determinant);

        if (!(a[1].equals(BigInteger.ZERO) && b[1].equals(BigInteger.ZERO))) {
            return Map.of();
        }
        return Map.of(ButtonId.A, a[0], ButtonId.B, b[0]);
    }
}
