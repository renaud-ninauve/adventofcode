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

        BigDecimal targetX = BigDecimal.valueOf(prizeLocation.x());
        BigDecimal targetY = BigDecimal.valueOf(prizeLocation.y());

        BigDecimal aDeltaX = BigDecimal.valueOf(buttonA.delta().x());
        BigDecimal aDeltaY = BigDecimal.valueOf(buttonA.delta().y());

        BigDecimal bDeltaX = BigDecimal.valueOf(buttonB.delta().x());
        BigDecimal bDeltaY = BigDecimal.valueOf(buttonB.delta().y());

        return clicksFor(targetX, targetY, aDeltaX, aDeltaY, bDeltaX, bDeltaY);
    }

    private Map<ButtonId, BigInteger> clicksFor(BigDecimal targetX, BigDecimal targetY, BigDecimal aDeltaX, BigDecimal aDeltaY, BigDecimal bDeltaX, BigDecimal bDeltaY) {
        BigDecimal xMultB = aDeltaX.multiply(bDeltaY);
        BigDecimal xtTargetMultB = targetX.multiply(bDeltaY);

        BigDecimal yMultB = aDeltaY.multiply(bDeltaX);
        BigDecimal ytTargetMultB = targetY.multiply(bDeltaX);

        BigDecimal subA = xMultB.subtract(yMultB);
        BigDecimal subTarget = xtTargetMultB.subtract(ytTargetMultB);

        BigDecimal a = subTarget.divide(subA, MathContext.DECIMAL32);
        BigDecimal b = (targetX.subtract(a.multiply(aDeltaX))).divide(bDeltaX, MathContext.DECIMAL32);

        if (a.stripTrailingZeros().scale() > 0 || b.stripTrailingZeros().scale() > 0) {
            return Map.of();
        }
        return Map.of(ButtonId.A, a.toBigInteger(), ButtonId.B, b.toBigInteger());
    }
}
