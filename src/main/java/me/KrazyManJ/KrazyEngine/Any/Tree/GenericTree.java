package me.KrazyManJ.KrazyEngine.Any.Tree;

@SuppressWarnings("unused")
public final class GenericTree<T> extends Tree<T,GenericNode<T>>{
    public GenericTree(T rootValue) {
        super(new GenericNode<>(rootValue,null));
    }

    public GenericNode<T> addChild(T value){
        return root.addChild(value);
    }
    @SafeVarargs
    public final void addChildren(T... values){
        root.addChildren(values);
    }
    public GenericNode<T> getChild(int index){
        return root.getChild(index);
    }
}
