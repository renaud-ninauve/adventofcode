package fr.ninauve.renaud.adventofcode.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static fr.ninauve.renaud.adventofcode.common.SuffixTree.Node.*;
import static org.assertj.core.api.Assertions.assertThat;

class SuffixTreeTest {
    static final String ENDING_LABEL = "" + SuffixTree.ENDING_CHAR;
    static final String PRINT_BANANAS = """
            a
            ..na
            ....nas$1
            ....s$3
            ..s$5
            bananas$0
            na
            ..nas$2
            ..s$4
            s$6
            $7
            """;

    static final SuffixTree BANANAS;

    static {
        SuffixTree.Node root = rootNode();

        // a
        SuffixTree.Node a = intermediateNode();
        root.append("a", a);

        // a_na
        SuffixTree.Node a_na = intermediateNode();
        a.append("na", a_na);

        SuffixTree.Node a_na_nas = leafNode(1);
        a_na.append("nas$", a_na_nas);

        SuffixTree.Node a_na_s = leafNode(3);
        a_na.append("s$", a_na_s);

        // a_s
        SuffixTree.Node a_s = leafNode(5);
        a.append("s$", a_s);

        // bananas
        SuffixTree.Node bananas = leafNode(0);
        root.append("bananas$", bananas);

        // na
        SuffixTree.Node na = intermediateNode();
        root.append("na", na);

        SuffixTree.Node na_nas = leafNode(2);
        na.append("nas$", na_nas);

        SuffixTree.Node na_s = leafNode(4);
        na.append("s$", na_s);

        // s
        SuffixTree.Node s = leafNode(6);
        root.append("s$", s);

        // $
        SuffixTree.Node end = leafNode(7);
        root.append("$", end);

        BANANAS = new SuffixTree(root);
    }

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

        SuffixTree suffixTree = new SuffixTree(root);
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

    static Stream<Arguments> from() {
        return Stream.of(
                Arguments.of(
                        "",
                        ""
                ),
                Arguments.of(
                        "b",
                        """
                                b1
                                """
                ),
                Arguments.of(
                        "ba",
                        """
                                a2
                                ..ba1
                                """
                ),
                Arguments.of(
                        "bananas$",
                        PRINT_BANANAS
                )
        );
    }

    @ParameterizedTest(name = "from({0})")
    @MethodSource
    void from(String str, String expected) {
        SuffixTree actual = SuffixTree.from(str);

        assertThat(actual.print()).isEqualTo(expected);
    }

    @Test
    void pringBananas() {
        String actual = BANANAS.print();
        assertThat(actual).isEqualTo(PRINT_BANANAS);
    }
}