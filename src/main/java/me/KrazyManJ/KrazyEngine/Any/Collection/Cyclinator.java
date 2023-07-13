package me.KrazyManJ.KrazyEngine.Any.Collection;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Type of collection, that can iterate through all elements repeatedly.
 * That means if last element is encountered, it goes back to the first one.
 *
 * @param <T> type of elements that this collection contains
 * @author KrazyManJ
 */
@SuppressWarnings({"unchecked", "unused", "UnusedReturnValue"})
public final class Cyclinator<T> implements Iterable<T> {

    private final T[] elems;
    private int cursor = -1;

    @SafeVarargs
    public Cyclinator(T object, T @NotNull ... objects) {
        this.elems = Merger.mergeToArray(object, objects);
    }

    public Cyclinator(@NotNull Collection<T> objectCollection) {
        this.elems = objectCollection.toArray((T[]) new Object[]{});
    }

    /**
     * Moves to next element in cycle
     *
     * @return next element in cycle
     */
    public T next() {
        cursor = cursor + 1 == elems.length ? 0 : cursor + 1;
        return current();
    }

    /**
     * Moves to next nth element specified by shift argument
     *
     * @param shift shift number
     * @return nth element in iteration
     */
    public T next(int shift) {
        for (int i = 0; i < shift; i++) next();
        return current();
    }

    /**
     * Gets current item in cycle (on cursor)
     *
     * @return current item in cycle
     */
    public T current() {
        return elems[cursor];
    }

    /**
     * Size of elements = amount of elements in cycle
     *
     * @return amount of elements
     */
    public int size() {
        return elems.length;
    }

    /**
     * Gets index of input element. This index is relative to the start of cycle, not
     * current cursor.
     *
     * @param element input element to get index of
     * @return index of element in cycle
     */
    public int indexOf(T element) {
        for (int i = 0; i < elems.length; i++) {
            if (elems[i].equals(element)) return i;
        }
        return -1;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @NotNull
    @Override
    public Iterator<T> iterator() {
        return Arrays.stream(elems).iterator();
    }
}
