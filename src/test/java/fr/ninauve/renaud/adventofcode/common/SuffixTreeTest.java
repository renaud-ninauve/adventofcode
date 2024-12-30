package fr.ninauve.renaud.adventofcode.common;

import org.junit.jupiter.api.Test;

import static fr.ninauve.renaud.adventofcode.common.SuffixTree.Node.*;
import static org.assertj.core.api.Assertions.assertThat;

class SuffixTreeTest {
    static final String ENDING_LABEL = "" + SuffixTree.ENDING_CHAR;

    @Test
    void print() {
        // aab
        SuffixTree.Node root = rootNode();

        // ending -> 3
        root.append(ENDING_LABEL, leafNode(3));

        // b -> 2
        SuffixTree.Node b = intermediateNode();
        root.append("b", b);
        b.append(ENDING_LABEL, leafNode(2));

        // ab -> 1
        SuffixTree.Node a = intermediateNode();
        root.append("a", a);
        SuffixTree.Node a_b = intermediateNode();
        a.append("b", a_b);
        a_b.append(ENDING_LABEL, leafNode(1));

        // aab -> 0
        SuffixTree.Node a_a = intermediateNode();
        a.append("a", a_a);
        SuffixTree.Node a_a_b = intermediateNode();
        a_a.append("b", a_a_b);
        a_a_b.append(ENDING_LABEL, leafNode(0));

        SuffixTree suffixTree = new SuffixTree(SuffixTree.ENDING_CHAR, root);
        assertThat(suffixTree.print()).isEqualTo("""                
                a
                ..a
                ....b
                ......$0
                ..b
                ....$1
                b
                ..$2
                $3
                """);
    }

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

    @Test
    void from_2_different_chars() {
        SuffixTree actual = SuffixTree.from("ab");

        SuffixTree.Node root = rootNode();

        // ending -> 2
        root.append(ENDING_LABEL, leafNode(2));

        // b -> 1
        SuffixTree.Node b = intermediateNode();
        root.append("b", b);
        b.append(ENDING_LABEL, leafNode(1));

        // ab -> 0
        SuffixTree.Node a = intermediateNode();
        root.append("a", a);
        SuffixTree.Node a_b = intermediateNode();
        a.append("b", a_b);
        a_b.append(ENDING_LABEL, leafNode(0));

        assertThat(actual).isEqualTo(new SuffixTree(
                SuffixTree.ENDING_CHAR,
                root
        ));
    }

    @Test
    void from_2_same_chars() {
        SuffixTree actual = SuffixTree.from("aa");

        SuffixTree.Node root = rootNode();

        // ending -> 2
        root.append(ENDING_LABEL, leafNode(2));

        // a -> 1
        SuffixTree.Node a = intermediateNode();
        root.append("a", a);
        a.append(ENDING_LABEL, leafNode(1));

        // aa -> 0
        SuffixTree.Node a_a = intermediateNode();
        a.append("a", a_a);
        a_a.append(ENDING_LABEL, leafNode(0));

        assertThat(actual).isEqualTo(new SuffixTree(
                SuffixTree.ENDING_CHAR,
                root
        ));
    }
}