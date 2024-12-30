package fr.ninauve.renaud.adventofcode.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SuffixTreeTest {

    @Test
    void from() {
        SuffixTree actual = SuffixTree.from("");

        SuffixTree.Node expectedRoot = SuffixTree.Node.root();
        expectedRoot.append("" + SuffixTree.ENDING_CHAR, SuffixTree.Node.leaf(0));
        assertThat(actual).isEqualTo(new SuffixTree(
                SuffixTree.ENDING_CHAR,
                expectedRoot
        ));
    }
}