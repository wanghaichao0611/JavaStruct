package 红黑树;

public class RedBlackNode<T extends Comparable<T>> {

    RedBlackNode<T> parentNode; //父节点
    RedBlackNode<T> leftNode; //左子节点
    RedBlackNode<T> rightNode; //右子节点
    T data; //值
    boolean color; //颜色

    public RedBlackNode(RedBlackNode<T> parentNode, RedBlackNode<T> leftNode, RedBlackNode<T> rightNode, T data, boolean color) {
        this.parentNode = parentNode;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.data = data;
        this.color = color;
    }

    public T getData() {
        return data;
    }
}