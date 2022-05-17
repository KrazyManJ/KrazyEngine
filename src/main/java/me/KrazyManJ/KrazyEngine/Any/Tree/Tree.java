package me.KrazyManJ.KrazyEngine.Any.Tree;

import java.util.Iterator;
import java.util.List;

@SuppressWarnings({"unused", "unchecked", "UnusedReturnValue"})
public abstract class Tree<T,N extends Node<T,?>> {

    protected N root;

    public Tree(N root) {
        this.root = root;
    }

    public final N getRoot(){
        return root;
    }

    public List<N> getAllNodes(){
        return (List<N>) root.getAllNodes();
    }
    public boolean contains(N node){
        return root.getAllNodes().contains(node);
    }
    public int nodeCount(){
        return getAllNodes().size();
    }



    @Override
    public String toString() {
        return toString(true,node -> node.getValue().toString());
    }
    public String toString(TreeNodeDisplayer<T> displayer){
        return toString(true,displayer);
    }
    public String toString(boolean showTreeType,TreeNodeDisplayer<T> displayer){
        StringBuilder s = new StringBuilder();
        if (showTreeType) s.append(this.getClass().getSimpleName()).append(": \n");
        a(s,root,displayer,"","");
        return s.toString();
    }

    @SuppressWarnings("unchecked")
    private void a(StringBuilder builder, N node, TreeNodeDisplayer<T> displayer, String p, String ch){
        builder.append(p);
        if (node == null){
            builder.append("null").append("\n");
            return;
        }
        builder.append(displayer.display(node)).append("\n");
        for (Iterator<N> it = ((List<N>) node.getChildren()).iterator(); it.hasNext();){
            N next = it.next();
            if (it.hasNext()) a(builder,next,displayer, ch + "├── ", ch + "│   ");
            else a(builder,next,displayer,ch + "└── ", ch + "    ");
        }
    }
}
