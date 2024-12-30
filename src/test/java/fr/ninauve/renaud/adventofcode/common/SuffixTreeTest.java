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
    void expanded_empty() {
        SuffixTree actual = SuffixTree.expandedTree("");

        assertThat(actual.print()).isEqualTo("""
                $0
                """);
    }

    @Test
    void expanded_one_char() {
        SuffixTree actual = SuffixTree.expandedTree("a");

        assertThat(actual.print()).isEqualTo("""
                a
                ..$0
                $1
                """);
    }

    @Test
    void expanded_2_different_chars() {
        SuffixTree actual = SuffixTree.expandedTree("ab");

        assertThat(actual.print()).isEqualTo("""
                a
                ..b
                ....$0
                b
                ..$1
                $2
                """);
    }

    @Test
    void expanded_2_same_chars() {
        SuffixTree actual = SuffixTree.expandedTree("aa");

        assertThat(actual.print()).isEqualTo("""
                a
                ..a
                ....$0
                ..$1
                $2
                """);
    }

    @Test
    void expanded_bananas() {
        SuffixTree actual = SuffixTree.expandedTree("bananas");

        assertThat(actual.print()).isEqualTo("""
                a
                ..n
                ....a
                ......n
                ........a
                ..........s
                ............$1
                ......s
                ........$3
                ..s
                ....$5
                b
                ..a
                ....n
                ......a
                ........n
                ..........a
                ............s
                ..............$0
                n
                ..a
                ....n
                ......a
                ........s
                ..........$2
                ....s
                ......$4
                s
                ..$6
                $7
                """);
    }
}