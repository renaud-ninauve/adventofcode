package fr.ninauve.renaud.adventofcode.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class SuffixTree {
    public static final char ENDING_CHAR = '$';
    public static final String ENDING_LABEL = "" + ENDING_CHAR;
    private static final Comparator<String> LABEL_COMPARATOR = Comparator.<String, Integer>comparing(str -> ENDING_LABEL.equals(str) ? 1 : 0)
            .thenComparing(Function.identity());

    private final char endingChar;
    private final Node root;

    public static SuffixTree from(String str) {
        String strWithEnding = str + ENDING_CHAR;
        Node root = Node.rootNode();
        for (int s = strWithEnding.length() - 1; s >= 0; s--) {
            Node currentNode = root;
            for (int c = s; c < strWithEnding.length(); c++) {
                char currentChar = strWithEnding.charAt(c);
                String currentLabel = "" + currentChar;
                Node newNode = currentNode.childByLabel(currentLabel);
                if (newNode == null) {
                    newNode = Node.leafNode(s);
                }
                currentNode.append(currentLabel, newNode);
                currentNode = newNode;
            }
        }
        return new SuffixTree(ENDING_CHAR, root);
    }

    public SuffixTree(char endingChar, Node root) {
        this.endingChar = endingChar;
        this.root = root;
    }

    public String print() {
        List<String> output = new ArrayList<>();
        print(output, root, 0);
        output.add("");
        return output.stream().collect(Collectors.joining(System.lineSeparator()));
    }

    private void print(List<String> result, Node currentNode, int level) {
        for (Map.Entry<String, Node> entry : currentNode.children.entrySet()) {
            String childLabel = entry.getKey();
            Node child = entry.getValue();
            String indent = ".".repeat(2 * level);
            if (child.isLeaf()) {
                result.add(indent + childLabel + child.index);
            } else {
                result.add(indent + childLabel);
                print(result, child, level + 1);
            }
        }
    }

    @Data
    @AllArgsConstructor
    public static class Node {
        private int index;
        private TreeMap<String, Node> children;

        public static Node rootNode() {
            return new Node(-1, new TreeMap<>(LABEL_COMPARATOR));
        }

        public static Node intermediateNode() {
            return rootNode();
        }

        public static Node leafNode(int index) {
            return new Node(index, new TreeMap<>(LABEL_COMPARATOR));
        }

        public void append(String label, Node child) {
            this.index = -1;
            children.put(label, child);
        }

        public Node childByLabel(String label) {
            return children.get(label);
        }

        public boolean isLeaf() {
            return children.isEmpty();
        }
    }
}
