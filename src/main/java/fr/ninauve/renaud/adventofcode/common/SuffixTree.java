package fr.ninauve.renaud.adventofcode.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.TreeMap;

@EqualsAndHashCode
public class SuffixTree {
    public static final char ENDING_CHAR = '$';

    private final char endingChar;
    private final Node root;

    public static SuffixTree from(String str) {
        Node root = Node.root();
        root.append("" + ENDING_CHAR, Node.leaf(0));
        return new SuffixTree(ENDING_CHAR, root);
    }

    public SuffixTree() {
        this(SuffixTree.ENDING_CHAR, SuffixTree.Node.root());
    }

    public SuffixTree(char endingChar, Node root) {
        this.endingChar = endingChar;
        this.root = root;
    }

    @Data
    @AllArgsConstructor
    public static class Node {
        private int index;
        private TreeMap<String, Node> children;

        public static Node root() {
            return new Node(-1, new TreeMap<>());
        }

        public static Node leaf(int index) {
            return new Node(index, new TreeMap<>());
        }

        public void append(String label, Node child) {
            children.put(label, child);
        }
    }
}
