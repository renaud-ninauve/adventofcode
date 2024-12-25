package fr.ninauve.renaud.adventofcode.year2024.day24;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class Part01Test {

    private static final List<String> SIMPLE_EXAMPLE = List.of(
            "x00: 1",
            "x01: 1",
            "x02: 1",
            "y00: 0",
            "y01: 1",
            "y02: 0",
            "",
            "x00 AND y00 -> z00",
            "x01 XOR y01 -> z01",
            "x02 OR y02 -> z02"
    );

    private static final List<String> LARGER_EXAMPLE = List.of(
            "x00: 1",
            "x01: 0",
            "x02: 1",
            "x03: 1",
            "x04: 0",
            "y00: 1",
            "y01: 1",
            "y02: 1",
            "y03: 1",
            "y04: 1",
            "",
            "ntg XOR fgs -> mjb",
            "y02 OR x01 -> tnw",
            "kwq OR kpj -> z05",
            "x00 OR x03 -> fst",
            "tgd XOR rvg -> z01",
            "vdt OR tnw -> bfw",
            "bfw AND frj -> z10",
            "ffh OR nrd -> bqk",
            "y00 AND y03 -> djm",
            "y03 OR y00 -> psh",
            "bqk OR frj -> z08",
            "tnw OR fst -> frj",
            "gnj AND tgd -> z11",
            "bfw XOR mjb -> z00",
            "x03 OR x00 -> vdt",
            "gnj AND wpb -> z02",
            "x04 AND y00 -> kjc",
            "djm OR pbm -> qhw",
            "nrd AND vdt -> hwm",
            "kjc AND fst -> rvg",
            "y04 OR y02 -> fgs",
            "y01 AND x02 -> pbm",
            "ntg OR kjc -> kwq",
            "psh XOR fgs -> tgd",
            "qhw XOR tgd -> z09",
            "pbm OR djm -> kpj",
            "x03 XOR y03 -> ffh",
            "x00 XOR y04 -> ntg",
            "bfw OR bqk -> z06",
            "nrd XOR fgs -> wpb",
            "frj XOR qhw -> z04",
            "bqk OR frj -> z07",
            "y03 OR x01 -> nrd",
            "hwm AND bqk -> z03",
            "tgd XOR rvg -> z12",
            "tnw OR pbm -> gnj"
    );

    @Test
    void parse() {
        Map<String, String> actual = Part01.parse(SIMPLE_EXAMPLE);

        assertThat(actual)
                .hasSize(9)
                .containsEntry("x00", "1")
                .containsEntry("x01", "1")
                .containsEntry("y02", "0")
                .containsEntry("z00", "x00 AND y00")
                .containsEntry("z01", "x01 XOR y01")
                .containsEntry("z02", "x02 OR y02");
    }

    @Test
    void compute() {
        Map<String, String> expressions = Part01.parse(SIMPLE_EXAMPLE);
        boolean actual1 = Part01.compute(expressions, "z00");
        boolean actual2 = Part01.compute(expressions, "z01");
        boolean actual3 = Part01.compute(expressions, "z02");

        assertThat(actual1).isFalse();
        assertThat(actual2).isFalse();
        assertThat(actual3).isTrue();
    }

    @Test
    void solve() {
        long actual = Part01.solve(SIMPLE_EXAMPLE);

        assertThat(actual).isEqualTo(4);
    }

    @Test
    void solveLarger() {
        long actual = Part01.solve(LARGER_EXAMPLE);

        assertThat(actual).isEqualTo(2024);
    }
}