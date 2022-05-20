package me.KrazyManJ.KrazyEngine.Any.Tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings({"unused", "unchecked", "UnusedReturnValue"})
public abstract class Node<T,N extends Node<T,?>> {

    T value;
    @Nullable N parent;

    public Node(T value, @Nullable N parent) {
        this.value = value;
        this.parent = parent;
    }

    @NotNull
    protected abstract List<N> getChildren();

    public final T getValue() {
        return value;
    }

    public final void setValue(T value) {
        this.value = value;
    }

    public final boolean isRoot(){
        return parent == null;
    }

    public final boolean isLeaf(){
        for (N child : getChildren()) {
            if (child != null) return false;
        }
        return true;
    }
    public final @Nullable N getParent(){
        return parent;
    }

    public final int depth(){
        N node = (N) this;
        int i = 0;
        while (true){
            if (node != null && !node.isRoot()) {
                node = (N) node.getParent();
                i++;
            }
            else break;
        }
        return i;
    }

    public final @NotNull List<N> getAllNodes(){
        List<N> r = new ArrayList<>((List<N>) List.of(this));
        for (N child : getChildren()) {
            r.addAll((List<N>) child.getAllNodes());
        }
        return r;
    }

    public boolean contains(N node){
        return getAllNodes().contains(node);
    }
    public int nodeCount(){
        return getAllNodes().size();
    }


    @Override
    public String toString() {
        return "{"+this.getClass().getSimpleName()+"="+value.toString()+",parent="+(isRoot() ? "null" : parent.toString())+"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?, ?> node = (Node<?, ?>) o;
        return Objects.equals(value, node.value) && Objects.equals(parent, node.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, parent);
    }
}
