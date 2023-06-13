package me.KrazyManJ.KrazyEngine.Any.Collection;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings({"unused", "unchecked"})
public final class Merger {

    private Merger() {
    }

    @SafeVarargs
    public static <T> @NotNull List<T> mergeToList(List<T> list, T object, T... objects) {
        List<T> r = new ArrayList<>(list);
        r.add(object);
        r.addAll(List.of(objects));
        return r;
    }

    public static <T> @NotNull List<T> mergeToListAtStart(T object, List<T> list) {
        List<T> r = new ArrayList<>(list);
        r.add(0, object);
        return r;
    }

    @SafeVarargs
    public static <T> T @NotNull [] mergeToArray(T object, T... objects) {
        return (T[]) Stream.concat(Stream.of(object), Arrays.stream(objects)).toArray();
    }
}
