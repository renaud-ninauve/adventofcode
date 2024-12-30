package fr.ninauve.renaud.adventofcode.common;

import org.junit.jupiter.api.Test;

import static fr.ninauve.renaud.adventofcode.common.SuffixTree.Node.*;
import static org.assertj.core.api.Assertions.assertThat;

class SuffixTreeTest {

    @Test
    void from_empty() {
        SuffixTree actual = SuffixTree.from("");

        SuffixTree.Node expectedRoot = rootNode();
        expectedRoot.append("" + SuffixTree.ENDING_CHAR, leafNode(0));
        assertThat(actual).isEqualTo(new SuffixTree(
                SuffixTree.ENDING_CHAR,
                expectedRoot
        ));
    }

    @Test
    void from_one_char() {
        SuffixTree actual = SuffixTree.from("a");

        SuffixTree.Node expectedRoot = rootNode();
        expectedRoot.append("" + SuffixTree.ENDING_CHAR, leafNode(1));
        SuffixTree.Node expectedFirstChild = intermediateNode();
        expectedRoot.append("a", expectedFirstChild);

        SuffixTree.Node expectedLeaf = leafNode(0);
        expectedFirstChild.append("" + SuffixTree.ENDING_CHAR, expectedLeaf);

        assertThat(actual).isEqualTo(new SuffixTree(
                SuffixTree.ENDING_CHAR,
                expectedRoot
        ));
    }
}