import java.util.*;

class TreeFormatter {

    AVLNode AVLNode = new AVLNode(0);
    int padding = 2;

    private int indent(List<String> lines, int margin) {
        if (margin >= 0) return margin;
        String spaces = " ".repeat(-margin);
        for (int i = 0; i < lines.size(); i++) {
            lines.set(i, spaces + lines.get(i));
        }
        return 0;
    }

    private List<String> merge(List<String> left, List<String> right) {
        int minSize = Math.min(left.size(), right.size());
        int offset = 0;
        for (int i = 0; i < minSize; i++) {
            offset = Math.max(offset, left.get(i).length() + padding - right.get(i).replaceAll("\\S.*", "").length());
        }
        indent(right, -indent(left, offset));
        for (int i = 0; i < minSize; i++) {
            left.set(i, left.get(i) + right.get(i).substring(left.get(i).length()));
        }
        if (right.size() > minSize) {
            left.addAll(right.subList(minSize, right.size()));
        }
        return left;
    }

    private List<String> buildLines(AVLNode node) {
        if (node == null) return new ArrayList<>();
        List<String> lines = merge(buildLines(node.left), buildLines(node.right));
        int half = String.valueOf(node.data).length() / 2;
        int i = half;
        if (!lines.isEmpty()) {
            String line;
            i = lines.get(0).indexOf("*"); // Índice do primeiro nó filho
            if (node.right == null) {
                line = " ".repeat(i) + "┌─┘";
                i += 2;
            } else if (node.left == null) {
                line = " ".repeat(i = indent(lines, i - 2)) + "└─┐";
            } else {
                int dist = lines.get(0).length() - 1 - i; // Distância entre os filhos
                line = String.format("%s┌%s┴%s┐", " ".repeat(i), "─".repeat(dist / 2 - 1), "─".repeat((dist - 1) / 2));
                i += dist / 2;
            }
            lines.set(0, line);
        }
        lines.add(0, " ".repeat(indent(lines, i - half)) + node.data);
        lines.add(0, " ".repeat(i + Math.max(0, half - i)) + "*"); // Marcador para o nó
        return lines;
    }

    public String topDown(AVLNode root) {
        List<String> lines = buildLines(root);
        return String.join("\n", lines.subList(1, lines.size()));
    }
}