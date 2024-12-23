package fr.ninauve.renaud.adventofcode.year2024.day23;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class Part02Test {

    @Test
    void maxConnected() {
        Set<String> actual = Part02.maxConnected(
                List.of(
                        "kh-tc",
                        "qp-kh",
                        "de-cg",
                        "ka-co",
                        "yn-aq",
                        "qp-ub",
                        "cg-tb",
                        "vc-aq",
                        "tb-ka",
                        "wh-tc",
                        "yn-cg",
                        "kh-ub",
                        "ta-co",
                        "de-co",
                        "tc-td",
                        "tb-wq",
                        "wh-td",
                        "ta-ka",
                        "td-qp",
                        "aq-cg",
                        "wq-ub",
                        "ub-vc",
                        "de-ta",
                        "wq-aq",
                        "wq-vc",
                        "wh-yn",
                        "ka-de",
                        "kh-ta",
                        "co-tc",
                        "wh-qp",
                        "tb-vc",
                        "td-yn"
                )
        );

        assertThat(actual).containsExactlyInAnyOrder(
                "co", "de", "ka", "ta"
        );
    }

    @Test
    void solve() {
        String actual = Part02.solve(
                List.of(
                        "kh-tc",
                        "qp-kh",
                        "de-cg",
                        "ka-co",
                        "yn-aq",
                        "qp-ub",
                        "cg-tb",
                        "vc-aq",
                        "tb-ka",
                        "wh-tc",
                        "yn-cg",
                        "kh-ub",
                        "ta-co",
                        "de-co",
                        "tc-td",
                        "tb-wq",
                        "wh-td",
                        "ta-ka",
                        "td-qp",
                        "aq-cg",
                        "wq-ub",
                        "ub-vc",
                        "de-ta",
                        "wq-aq",
                        "wq-vc",
                        "wh-yn",
                        "ka-de",
                        "kh-ta",
                        "co-tc",
                        "wh-qp",
                        "tb-vc",
                        "td-yn"
                )
        );

        assertThat(actual).isEqualTo(
                "co,de,ka,ta"
        );
    }
}