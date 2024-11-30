public class RedBlackNode {
    int data;
    RedBlackNode left, right, parent;
    boolean isRed; // verdadeiro para vermelho, falso para preto

    public RedBlackNode(int data) {
        this.data = data;
        this.isRed = true; // novo nó sempre será vermelho
        this.left = this.right = this.parent = null;
    }
}

