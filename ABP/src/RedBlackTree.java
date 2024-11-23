public class RedBlackTree {
    private RedBlackNode root;

    // Rotação à esquerda
    private void rotateLeft(RedBlackNode node) {
        RedBlackNode rightChild = node.right;
        node.right = rightChild.left;

        if (rightChild.left != null)
            rightChild.left.parent = node;

        rightChild.parent = node.parent;

        if (node.parent == null)
            root = rightChild;
        else if (node == node.parent.left)
            node.parent.left = rightChild;
        else
            node.parent.right = rightChild;

        rightChild.left = node;
        node.parent = rightChild;
    }

    // Inserção
    public void insert(int data) {
        RedBlackNode node = new RedBlackNode(data);

        // Inserção BST normal
        RedBlackNode parent = null;
        RedBlackNode current = root;

        while (current != null) {
            parent = current;
            if (node.data < current.data)
                current = current.left;
            else
                current = current.right;
        }

        node.parent = parent;

        if (parent == null)
            root = node;
        else if (node.data < parent.data)
            parent.left = node;
        else
            parent.right = node;

        // Corrigir violações da árvore rubro-negra
        fixViolation(node);
    }

    private void fixViolation(RedBlackNode node) {
        RedBlackNode parent = null;
        RedBlackNode grandParent = null;

        while (node != root && node.isRed && node.parent.isRed) {
            parent = node.parent;
            grandParent = parent.parent;

            // Casos de recoloração e rotação
            // ... implementação dos casos de correção
        }

        root.isRed = false; // raiz sempre preta
    }
}
