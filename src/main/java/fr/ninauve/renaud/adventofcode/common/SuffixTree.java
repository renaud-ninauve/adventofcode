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
        String strWithEnding = str + ENDING_CHAR;
        Node root = Node.root();
        for (int s = strWithEnding.length() - 1; s >= 0; s--) {
            Node currentNode = root;
            for (int c = s; c < strWithEnding.length(); c++) {
                char currentChar = strWithEnding.charAt(c);
                String currentLabel = "" + currentChar;
                Node newNode = currentNode.childByLabel(currentLabel);
                if (newNode == null) {
                    newNode = Node.leaf(c);
                }
                currentNode.append(currentLabel, newNode);
                currentNode = newNode;
            }
        }
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

        public Node childByLabel(String label) {
            return children.get(label);
        }
    }
}
