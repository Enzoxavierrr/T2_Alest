public class RedBlackNode {
    int data;
    RedBlackNode left, right, parent;
    boolean isRed; // true para vermelho, false para preto

    public RedBlackNode(int data) {
        this.data = data;
        this.isRed = true; // novo nó sempre é vermelho
        this.left = this.right = this.parent = null;
    }
}

