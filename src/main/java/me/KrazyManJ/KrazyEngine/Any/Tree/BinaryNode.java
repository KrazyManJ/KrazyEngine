package me.KrazyManJ.KrazyEngine.Any.Tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public final class BinaryNode<T> extends Node<T,BinaryNode<T>>{

    @Nullable private BinaryNode<T> right;
    @Nullable private BinaryNode<T> left;

    public BinaryNode(T value,BinaryNode<T> parent) {
        super(value,parent);
    }
    public BinaryNode(T value, BinaryNode<T> parent, T right, T left) {
        super(value,parent);
        this.right = new BinaryNode<>(right,this);
        this.left = new BinaryNode<>(left,this);
    }

    public @Nullable BinaryNode<T> getRight() {
        return right;
    }
    public @Nullable BinaryNode<T> getLeft() {
        return left;
    }

    public BinaryNode<T> setRight(T value){
        if (value == null) return right;
        if (right != null) right.setValue(value);
        else right = new BinaryNode<>(value,this);
        return right;
    }
    public BinaryNode<T> setLeft(T value){
        if (value == null) return left;
        if (left != null) left.setValue(value);
        else left = new BinaryNode<>(value,this);
        return left;
    }
    public void cutRight(){
        if (right == null) return;
        for (BinaryNode<T> child : getChildren()) {
            if (child != null) child.parent = null;
        }
        right = null;
    }
    public void cutLeft(){
        if (left == null) return;
        for (BinaryNode<T> child : getChildren()) {
            if (child != null) child.parent = null;
        }
        left = null;
    }
    public void twist(){
        BinaryNode<T> temp = right;
        right = left;
        left = temp;
    }

    @Override
    protected @NotNull List<BinaryNode<T>> getChildren() {
        List<BinaryNode<T>> r = new ArrayList<>();
        r.add(right);
        r.add(left);
        return r;
    }
}
