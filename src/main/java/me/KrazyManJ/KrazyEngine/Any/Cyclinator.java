package me.KrazyManJ.KrazyEngine.Any;

import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    public T current(){
        return elems[index];
    }
}
