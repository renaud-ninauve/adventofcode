package fr.ninauve.renaud.adventofcode.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SuffixTreeTest {

    @Test
    void from_empty() {
        SuffixTree actual = SuffixTree.from("");

        SuffixTree.Node expectedRoot = SuffixTree.Node.root();
        expectedRoot.append("" + SuffixTree.ENDING_CHAR, SuffixTree.Node.leaf(0));
        assertThat(actual).isEqualTo(new SuffixTree(
                SuffixTree.ENDING_CHAR,
                expectedRoot
        ));
    }

    @Test
    void from_one_char() {
        SuffixTree actual = SuffixTree.from("a");

        SuffixTree.Node expectedRoot = SuffixTree.Node.root();
        expectedRoot.append("" + SuffixTree.ENDING_CHAR, SuffixTree.Node.leaf(1));
        SuffixTree.Node expectedFirstChild = SuffixTree.Node.leaf(0);
        expectedRoot.append("a", expectedFirstChild);

        SuffixTree.Node expectedLeaf = SuffixTree.Node.leaf(1);
        expectedFirstChild.append("" + SuffixTree.ENDING_CHAR, expectedLeaf);

        assertThat(actual).isEqualTo(new SuffixTree(
                SuffixTree.ENDING_CHAR,
                expectedRoot
        ));
    }
}