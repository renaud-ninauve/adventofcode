package fr.ninauve.renaud.adventofcode.year2024.day23;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class Part01Test {

    @Test
    void parse() {
        Map<String, Set<String>> actual = Part01.parse(List.of("ab-cd", "ef-gh", "cd-gh"));

        assertThat(actual).hasSize(4);
        assertThat(actual.get("ab")).containsExactlyInAnyOrder("cd");
        assertThat(actual.get("cd")).containsExactlyInAnyOrder("ab", "gh");
        assertThat(actual.get("ef")).containsExactlyInAnyOrder("gh");
        assertThat(actual.get("gh")).containsExactlyInAnyOrder("cd", "ef");
    }

    @Test
    void namesStartingWith() {
        Collection<String> actual = Part01.namesStartingWith('t');

        assertThat(actual)
                .hasSize(26)
                .contains("ta", "tb", "tc", "tx", "ty", "tz");
    }

    @Test
    void groupsStartingWith() {
        Set<Set<String>> actual = Part01.groupsStartingWith(
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
                ),
                't',
                3
        );

        assertThat(actual).containsExactlyInAnyOrder(
                Set.of("co", "de", "ta"),
                Set.of("co", "ka", "ta"),
                Set.of("de", "ka", "ta"),
                Set.of("qp", "td", "wh"),
                Set.of("tb", "vc", "wq"),
                Set.of("tc", "td", "wh"),
                Set.of("td", "wh", "yn")
        );
    }

    @Test
    void solve() {
        long actual = Part01.solve(
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

        assertThat(actual).isEqualTo(7L);
    }
}