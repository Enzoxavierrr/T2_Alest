import java.util.LinkedList;
import java.util.Queue;

/**
 * CLASSE BinarySearchTree
 * Trabalhando com árvore binária de pesquisa
 * */

class BinarySearchTree {

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void add(Integer v) {

        Node prev, current;

        // cria um novo nodo
        Node node = new Node();

        // atribui o valor recebido ao item de dados do nodo
        node.element = v;
        node.right = null;
        node.left = null;

        // se a raiz está nula, a árvore está vazia
        if (root == null) {
            root = node;
        } else {
            current = root;
            // percorre a árvore
            while (true) {
                prev = current;
                // ir para esquerda
                if (v <= current.element) {
                    current = current.left;
                    if (current == null) {
                        // insere na subárvore da esquerda
                        prev.left = node;
                        return;
                    }
                }
                // ir para direita
                else {
                    current = current.right;
                    if (current == null) {
                        // insere na subárvore da direita
                        prev.right = node;
                        return;
                    }
                }
            }
        }
    }

    public Node contains(Integer v) {
        // se arvore vazia
        if (root == null)
            return null;

        // começa a procurar desde raiz
        Node current = root;
        // enquanto nao encontrou
        while (current.element != v) {
            if (v < current.element)
                current = current.left; // caminha para esquerda
            else
                current = current.right; // caminha para direita

            // encontrou uma folha -> sai
            if (current == null)
                return null;
        }

        // terminou o laço while e chegou aqui é pq encontrou item
        return current;
    }


    public boolean remove(Integer v) {
        // se arvore vazia
        if (root == null)
            return false;

        Node current = root;
        Node father = root;
        boolean child_left = true;

        // buscando o valor
        while (current.element != v) {
            // enquanto nao encontrou
            father = current;
            // caminha para esquerda
            if (v < current.element) {
                current = current.left;
                // é filho a esquerda? sim
                child_left = true;
            }
            // caminha para direita
            else {
                current = current.right;
                // é filho a esquerda? NAO
                child_left = false;
            }
            // encontrou uma folha -> sai
            if (current == null)
                return false;
        }
        // Se nao possui nenhum filho (é uma folha), elimine-o
        if (current.left == null && current.right == null) {
            // se raiz
            if (current == root)
                root = null;
                // se for filho a esquerda do pai
            else if (child_left)
                father.left = null;
                // se for filho a direita do pai
            else
                father.right = null;
        }
        // Se é pai e nao possui um filho a direita, substitui pela subarvore a direita
        else if (current.right == null) {
            // se raiz
            if (current == root)
                root = current.left;
                // se for filho a esquerda do pai
            else if (child_left)
                father.left = current.left;
                // se for filho a direita do pai
            else
                father.right = current.left;
        }
        // Se é pai e nao possui um filho a esquerda, substitui pela subarvore a esquerda
        else if (current.left == null) {
            // se raiz
            if (current == root)
                root = current.right;
                // se for filho a esquerda do pai
            else if (child_left)
                father.left = current.right;
                // se for  filho a direita do pai
            else
                father.right = current.right;
        }
        // Se possui mais de um filho, se for um avô ou outro grau maior de parentesco
        else {
            Node successor = node_successor(current);
            // Usando sucessor que seria o Nó mais a esquerda da subarvore a direita do No que deseja-se remover
            // se raiz
            if (current == root)
                root = successor;
                // se for filho a esquerda do pai
            else if (child_left)
                father.left = successor;
                // se for filho a direita do pai
            else
                father.right = successor;
            // acertando o ponteiro a esquerda do sucessor agora que ele assumiu
            successor.left = current.left;
            // a posição correta na arvore
        }
        return true;
    }

    // O sucessor é o nodo mais a esquerda da subarvore a direita do nodo que foi passado como parâmetro do método
    public Node node_successor(Node node) {
        Node father_successor = node;
        Node successor = node;
        Node current = node.left;

        // enquanto nao chegar no nodo mais a esquerda
        while (current != null) {
            father_successor = successor;
            successor = current;
            // caminha para a esquerda
            current = current.left;
        }
        // se sucessor nao é o filho a direita do Nó que deverá ser eliminado
        if (successor != node.right) {
            // pai herda os filhos do sucessor que sempre serão a direita
            father_successor.left = successor.right;
            successor.right = node.right;
        }
        return successor;
    }

    void clearTree() {
        root = null;
    }

    public void inOrder(Node current) {
        if (current != null) {
            inOrder(current.left);
            System.out.print(current.element + " ");
            inOrder(current.right);
        }
    }

    public void preOrder(Node current) {
        if (current != null) {
            System.out.print(current.element + " ");
            preOrder(current.left);
            preOrder(current.right);
        }
    }

    public void postOrder(Node current) {
        if (current != null) {
            postOrder(current.left);
            postOrder(current.right);
            System.out.print(current.element + " ");
        }
    }

    public int height(Node current) {
        if (current == null || (current.left == null && current.right == null)) {
            return 0;
        } else {
            if (height(current.left) > height(current.right))
                return (1 + height(current.left));
            else
                return (1 + height(current.right));
        }
    }

    public int countNodes(Node current) {
        if (current == null)
            return 0;
        else
            return (1 + countNodes(current.left) + countNodes(current.right));
    }

    public Node getRoot() {
        return root;
    }

    public void orders() {
        System.out.print("\n Caminhamento Central (in-order): ");
        inOrder(root);
        System.out.print("\n Exibindo em Pós-ordem (post-order): ");
        postOrder(root);
        System.out.print("\n Exibindo em Pré-ordem (pre-order): ");
        preOrder(root);

        breadthFirstOrder();
    }

    public void treeInfo() {
        System.out.println("Altura da arvore: " + height(root));
        System.out.println("Quantidade de Nós: " + countNodes(root));
        System.out.println("Média dos nodos externos: " + AverageExternalNodes(getRoot()));
        System.out.println("Nível do maior nodo: " + maxNodeLevel(getRoot()));
        System.out.println("Diferença entre maior valor e raiz: " + diffMaxRoot());

    }

    public void printTree() {
        if (root != null) {
            TreeFormatter formatter = new TreeFormatter();
            System.out.println(formatter.topDown(root));
        } else {
            System.out.println("Árvore vazia!");
        }
    }


    /**
     * Método AverageExternalNodes()
     * Calcula a média dos valores de todos os nodos externos (folhas) na árvore.
     * @param current Nodo atual da árvore (geralmente inicializado com a raiz).
     * @return valor médio dos nodos externos. Retorna 0 se a árvore não tiver nodos externos.
     */
    public int AverageExternalNodes(Node current) {
        int[] result = sumAndCountExternalNodes(current);
        if (result[1] == 0) return 0; // Evita divisão por zero
        return result[0] / result[1];
    }

    private int[] sumAndCountExternalNodes(Node current) {
        if (current == null) return new int[]{0, 0};
        if (current.left == null && current.right == null)
            return new int[]{current.element, 1};

        int[] left = sumAndCountExternalNodes(current.left);
        int[] right = sumAndCountExternalNodes(current.right);

        return new int[]{left[0] + right[0], left[1] + right[1]};
    }

    /**
     * Método maxNodeLevel()
     * Determina o nível em que está localizado o maior valor existente na árvore.
     * @param current nodo atual da árvore (geralmente inicializado com a raiz).
     * @return nível do nodo com o maior valor na árvore. Retorna -1 se a árvore estiver vazia.
     */
    public int maxNodeLevel(Node current) {
        return findMaxNodeLevel(current, 0, Integer.MIN_VALUE, -1);
    }

    private int findMaxNodeLevel(Node node, int level, int max, int maxLevel) {
        if (node == null) return maxLevel;
        if (node.element > max) {
            max = node.element;
            maxLevel = level;
        }

        maxLevel = findMaxNodeLevel(node.left, level + 1, max, maxLevel);
        return findMaxNodeLevel(node.right, level + 1, max, maxLevel);
    }
    /**
     * Método diffMaxRoot()
     * Calcula a diferença entre o maior valor presente na árvore e o valor do nodo raiz.
     * @return diferença (valor do maior nodo - valor do nodo raiz).
     */
    public int diffMaxRoot() {
        if (root == null) return 0;
        int maxValue = findMaxValue(root);
        return maxValue - root.element;
    }

    private int findMaxValue(Node node) {
        if (node.right == null) return node.element;
        return findMaxValue(node.right);
    }

    /**
     * Método sumBetween()
     * Calcula a soma dos valores de todos os nodos que estão entre dois valores especificados
     * (inclusive o nodo inicial, mas excluindo o nodo final).
     * O método percorre apenas os nodos dentro do intervalo fornecido.
     * @param start valor inicial do intervalo, incluído na soma.
     * @param end valor final do intervalo, excluído da soma.
     * @param current nodo atual da árvore (geralmente inicializado com a raiz).
     * @return soma dos valores dos nodos dentro do intervalo especificado.
     *Retorna 0 se não houver nodos no intervalo ou se a árvore estiver vazia.
     */
    public int sumBetween(int start, int end, Node current) {
        if (current == null) return 0;

        int sum = 0;
        if (current.element >= start && current.element < end)
            sum += current.element;

        sum += sumBetween(start, end, current.left);
        sum += sumBetween(start, end, current.right);

        return sum;
    }

    /**
     * Método breadthFirstOrder()
     * Realiza o caminhamento em largura (level-order traversal) e imprime os valores da árvore.
     */
    public void breadthFirstOrder() {
        if (root == null) {
            System.out.println("Árvore vazia!");
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.element + " ");

            if (current.left != null)
                queue.add(current.left);
            if (current.right != null)
                queue.add(current.right);
        }
    }
}