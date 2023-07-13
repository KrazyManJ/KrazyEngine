package me.KrazyManJ.KrazyEngine.Any.Tree;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public final class GenericNode<T> extends Node<T,GenericNode<T>>{

    final List<GenericNode<T>> children = new ArrayList<>();

    public GenericNode(T value, GenericNode<T> parent) {
        super(value,parent);
    }

    @Override
    public @NotNull List<GenericNode<T>> getChildren() {
        return children;
    }

    public @NotNull GenericNode<T> addChild(T value){
        GenericNode<T> node = new GenericNode<>(value,this);
        children.add(node);
        return node;
    }

    @SafeVarargs
    public final void addChildren(T @NotNull ... values){
        for (T value : values) addChild(value);
    }

    public GenericNode<T> getChild(int index){
        return children.get(index);
    }

    public List<GenericNode<T>> getSiblings(){
        if (isRoot()) return new ArrayList<>();
        assert getParent().isPresent();
        return getParent().get().getChildren().stream().filter(f->!f.equals(this)).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GenericNode<?> that = (GenericNode<?>) o;
        return Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), children);
    }
}
