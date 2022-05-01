package me.KrazyManJ.KrazyEngine.Any;

import java.util.Collection;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public final class Cyclinator<T> {
    private final T[] elems;
    private int index = -1;

    @SafeVarargs
    public Cyclinator(T object, T ...objects) {
        this.elems = (T[]) new Object[objects.length+1];
        elems[0] = object;
        System.arraycopy(objects, 0, elems, 1, elems.length - 1);
    }
    public Cyclinator(Collection<T> objectCollection) {
        this.elems = objectCollection.toArray((T[]) new Object[]{});
    }
    public T next(){
        index = index+1 == elems.length ? 0 : index + 1;
        return current();
    }
    public T next(int shift){
        for (int i = 0; i < shift; i++) next();
        return current();
    }
    public T next(Predicate<T> predicate){
        for (int i = 0; i < elems.length; i++){
            if (predicate.test(next())) return current();
        }
        return next(2);
    }
    public T current(){
        return elems[index];
    }
    public int length(){
        return elems.length;
    }
    public int indexOf(T element){
        for (int i = 0; i < elems.length; i++) {
            if (elems[i].equals(element)) return i;
        }
        return -1;
    }
}
