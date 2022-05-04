package me.KrazyManJ.KrazyEngine.Any.DataStructures;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

@SuppressWarnings("UnusedReturnValue")
public final class Tree<T> {

    private final Branch<T> root;

    public Tree(T rootValue) { this.root = new Branch<>(rootValue,null); }

    public Branch<T> getRoot() { return root; }
    public Branch<T> addChild(T value){ return root.addChild(value); }
    @SafeVarargs public final void addChildren(T... values){
        Arrays.stream(values).forEach(this::addChild);
    }

    public List<Branch<T>> getAllBranches(){ return root.Bs(); }
    public List<Branch<T>> getAllBranches(int treeIndex){
        return getAllBranches().stream().filter(b -> b.getTreeIndex() == treeIndex).toList();
    }
    public int branchesCount(){ return getAllBranches().size(); }
    public boolean contains(T value){
        return getAllBranches().stream().map(t -> t.value).toList().contains(value);
    }
    public int getTreeHeight(){
        int size = 0;
        for (Branch<T> b : getAllBranches()) size = Math.max(b.getTreeIndex(), size);
        return size;
    }






    public static final class Branch<T> {

        private T value;
        private final Branch<T> parrent;
        private final List<Branch<T>> branches = new ArrayList<>();
        private final int treeIndex;

        public Branch(T value, @Nullable Branch<T> parrent) {
            this.value = value;
            this.parrent = parrent;
            treeIndex = parrent != null ? parrent.getTreeIndex()+1 : 0;
        }

        public T value() { return value; }
        public void setValue(T value) { this.value = value; }

        public int getTreeIndex() { return treeIndex; }

        public boolean isRoot(){ return parrent == null; }
        public boolean isLeaf(){ return branches.size() == 0; }

        public List<Branch<T>> getChildren(){ return branches; }
        public Branch<T> getParrent() { return parrent; }

        public boolean hasSiblings(){ return getSiblings().size() != 0; }
        public List<Branch<T>> getSiblings(){
            List<Branch<T>> r = new ArrayList<>();
            if (this.isRoot()) return r;
            r.addAll(getParrent().getChildren());
            r.remove(this);
            return r;
        }

        public Branch<T> addChild(T value){
            Branch<T> branch = new Branch<>(value,this);
            branches.add(branch);
            return branch;
        }
        @SafeVarargs
        public final void addChildren(T... values){
            Arrays.stream(values).forEach(this::addChild);
        }




        private List<Branch<T>> Bs(){
            List<Branch<T>> r = new ArrayList<>(branches);
            for (Branch<T> b : branches) r.addAll(b.Bs());
            return r;
        }





        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Branch<?> branch = (Branch<?>) o;
            return treeIndex == branch.treeIndex && Objects.equals(value, branch.value) && Objects.equals(parrent, branch.parrent) && Objects.equals(branches, branch.branches);
        }
    }



    public @NotNull String toTreeDiagram(){ return toTreeDiagram(node -> node.value().toString()); }
    public @NotNull String toTreeDiagram(BranchDisplayer<T> t){
        StringBuilder builder = new StringBuilder();
        d(builder, root,t, "", "");
        return builder.toString();
    }
    private void d(StringBuilder builder, Branch<T> branch, BranchDisplayer<T> t, String p, String ch) {
        builder.append(p).append(t.display(branch)).append("\n");
        for (Iterator<Branch<T>> it = branch.getChildren().iterator(); it.hasNext();) {
            Branch<T> next = it.next();
            if (it.hasNext()) d(builder,next,t, ch + "├── ", ch + "│   ");
            else d(builder,next,t,ch + "└── ", ch + "    ");
        }
    }

    @FunctionalInterface public interface BranchDisplayer<T>{ String display(Branch<T> node);}
}