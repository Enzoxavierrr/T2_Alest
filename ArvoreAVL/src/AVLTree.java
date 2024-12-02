import java.util.ArrayList;
import java.util.List;



public class AVLTree {
    private AVLNode root;

    public AVLTree() {
        this.root = null;
    }
    public String printTree() {
        TreeFormatter formatter = new TreeFormatter();
        return root == null ? "Árvore vazia" : formatter.topDown(root);
    }

    public String parent(int value) {
        Integer parent = getParent(value);

        return parent != null ? parent.toString() : "Nó não encontrado";
    }

    public void remove(int value) {
        root = delete(root, value);
    }

    private AVLNode delete(AVLNode node, int value) {
        if (node == null) {
            return null; // Nó não encontrado
        }



        // Navegação pela árvore para encontrar o nó
        if (value < node.data) {
            node.left = delete(node.left, value);
        } else if (value > node.data) {
            node.right = delete(node.right, value);
        } else {
            // Caso 1: Nó folha ou com um filho
            if (node.left == null || node.right == null) {
                return (node.left != null) ? node.left : node.right;
            }

            // Caso 2: Nó com dois filhos
            AVLNode minNode = findMin(node.right);
            node.data = minNode.data;
            node.right = delete(node.right, minNode.data);
        }

        // Atualizar altura
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // Rebalancear a árvore
        return balance(node);
    }

    // Encontrar o menor valor no lado direito
    private AVLNode findMin(AVLNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    class AVLNode {
        int data;
        AVLNode left, right;
        int height;

        public AVLNode(int data) {
            this.data = data;
            this.height = 1; // Altura inicial de um nó é 1
        }
    }


    /** Adicionar elementos na árvore */
    public void add(int data) {
        root = insert(root, data);
    }

    private AVLNode insert(AVLNode node, int data) {
        if (node == null) {
            return new AVLNode(data);
        }

        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        } else {
            return node; // Valores duplicados não são permitidos
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        return balance(node);

    }

    /** Retornar o pai de um elemento */
    public Integer getParent(int data) {
        return findParent(root, null, data);
    }

    private Integer findParent(AVLNode node, AVLNode parent, int data) {
        if (node == null) {
            return null;
        }

        if (node.data == data) {
            return parent != null ? parent.data : null;
        }

        if (data < node.data) {
            return findParent(node.left, node, data);
        } else {
            return findParent(node.right, node, data);
        }
    }

    /** Limpar o conteúdo da árvore */
    public void clear() {
        root = null;
    }

    /** Verificar se um elemento está armazenado na árvore ou não */
    public boolean contains(int data) {
        return search(root, data) != null;
    }

    private AVLNode search(AVLNode node, int data) {
        if (node == null || node.data == data) {
            return node;
        }
        if (data < node.data) {
            return search(node.left, data);
        } else {
            return search(node.right, data);
        }
    }

    /** Verificar qual é a altura da árvore */
    public int height() {
        return getHeight(root);
    }

    private int getHeight(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    /** Verificar quantos elementos tem na árvore */
    public int size() {
        return countNodes(root);
    }

    private int countNodes(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    /** Verificar se a árvore está vazia ou não */
    public boolean isEmpty() {
        return root == null;
    }

    /** Retornar os elementos da árvore em uma lista usando caminhamento central */
    public List<Integer> inOrder() {
        List<Integer> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    private void inOrderTraversal(AVLNode node, List<Integer> result) {
        if (node != null) {
            inOrderTraversal(node.left, result);
            result.add(node.data);
            inOrderTraversal(node.right, result);
        }
    }

    // Métodos auxiliares para balancear a árvore
    private AVLNode balance(AVLNode node) {
        int balanceFactor = getBalance(node);

        if (balanceFactor > 1) {
            if (getBalance(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }

        if (balanceFactor < -1) {
            if (getBalance(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node;
    }

    private int getBalance(AVLNode node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T = x.right;

        x.right = y;
        y.left = T;

        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T = y.left;

        y.left = x;
        x.right = T;

        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));

        return y;
    }
}