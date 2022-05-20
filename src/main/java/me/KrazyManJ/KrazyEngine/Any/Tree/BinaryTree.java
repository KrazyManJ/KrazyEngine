package me.KrazyManJ.KrazyEngine.Any.Tree;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public final class BinaryTree<T> extends Tree<T,BinaryNode<T>>{
    public BinaryTree(T rootValue) {
        super(new BinaryNode<>(rootValue,null));
    }

    public BinaryNode<T> setRight(T value){
        return root.setRight(value);
    }
    public BinaryNode<T> setLeft(T value){
        return root.setLeft(value);
    }
    public BinaryNode<T> getRight() {
        return root.getRight();
    }
    public BinaryNode<T> getLeft() {
        return root.getLeft();
    }
    public void cutRight() {
        root.cutRight();
    }
    public void cutLeft() {
        root.cutLeft();
    }
    public void twist() {
        root.twist();
    }
}
