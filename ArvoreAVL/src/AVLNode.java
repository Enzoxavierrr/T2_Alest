public class AVLNode {
    public Integer data;
    public AVLNode left;
    public AVLNode right;
    public int height;

    public AVLNode(Integer data) {
        this.data = data;
        this.height = 1;
    }
}
