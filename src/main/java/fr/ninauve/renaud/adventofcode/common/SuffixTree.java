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

    private final Node root;

    public static SuffixTree from(String str) {
        if (str == null || str.isEmpty()) {
            return new SuffixTree(Node.rootNode());
        }
        Node root = Node.rootNode();
        Node leaf = Node.leafNode(1);
        root.append(str.substring(0, 1), leaf);
        SuffixTree suffixTree = new SuffixTree(root);
        for(int i=1; i<str.length(); i++) {
            suffixTree.appendCharAt(str, i);
        }
        return suffixTree;
    }

    public SuffixTree(Node root) {
        this.root = root;
    }

    public String print() {
        List<String> output = new ArrayList<>();
        print(output, root, 0);
        output.add("");
        return output.stream().collect(Collectors.joining(System.lineSeparator()));
    }

    private void appendCharAt(String str, int index) {
        for(int previousPhaseIndex=0; previousPhaseIndex<=index; previousPhaseIndex++) {
            String previousLabel = str.substring(previousPhaseIndex, index);
            FindEndNodeResult previousEndNode = findEndOfPath(previousLabel);
            //extend();
        }
    }

    public FindEndNodeResult findEndOfPath(String str) {
        Node current = root;
        int index = 0;
        FindEndNodeResult result = null;
        while(index < str.length()) {
            final String toMatch = str.substring(index);
            Map.Entry<String, Node> labelAndNode = current.children.entrySet().stream()
                    .filter(e -> {
                        String label = e.getKey();
                        if (label.length() <= toMatch.length()) {
                            return toMatch.startsWith(label);
                        }
                        return label.startsWith(toMatch);
                    }).findFirst()
                    .orElse(null);
            if (labelAndNode == null) {
                return null;
            }
            index += labelAndNode.getKey().length();
            result = new FindEndNodeResult(labelAndNode.getValue(), current, labelAndNode.getKey());
            current = labelAndNode.getValue();
        }
        return result;
    }

    private void print(List<String> result, Node currentNode, int level) {
        for (Map.Entry<String, Node> entry : currentNode.children.entrySet()) {
            String childLabel = entry.getKey();
            Node child = entry.getValue();
            String indent = ".".repeat(2 * level);
            if (child.isLeaf()) {
                result.add(indent + childLabel + child.position);
            } else {
                result.add(indent + childLabel);
                print(result, child, level + 1);
            }
        }
    }

    @Data
    @AllArgsConstructor
    public static class Node {
        private int position;
        private TreeMap<String, Node> children;

        public static Node rootNode() {
            return new Node(-1, new TreeMap<>(LABEL_COMPARATOR));
        }

        public static Node intermediateNode() {
            return rootNode();
        }

        public static Node leafNode(int position) {
            return new Node(position, new TreeMap<>(LABEL_COMPARATOR));
        }

        public void append(String label, Node child) {
            this.position = -1;
            children.put(label, child);
        }

        public Node childByLabel(String label) {
            return children.get(label);
        }

        public boolean isLeaf() {
            return children.isEmpty();
        }
    }

    private record FindEndNodeResult(Node node, Node parent, String label) {}
}
