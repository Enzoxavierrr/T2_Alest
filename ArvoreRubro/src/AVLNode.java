class AVLNode {
    int data;
    AVLNode left, right;
    int height;

    public AVLNode(int data) {
        this.data = data;
        this.height = 1; // Altura inicial de um nó é 1
    }
}