package me.KrazyManJ.KrazyEngine.Any.Tree;

@FunctionalInterface
public interface TreeNodeDisplayer<T> {
    String display(Node<T,?> node);
}
