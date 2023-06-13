package me.KrazyManJ.KrazyEngine.Any.Collection;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Random;

@SuppressWarnings({"unused", "unchecked"})
public final class Randomizer {

    private Randomizer() {
    }

    public static <T> T outOf(T @NotNull [] elements) {
        return elements[new Random().nextInt(elements.length)];
    }

    public static <T> T outOf(@NotNull List<T> elemList) {
        return elemList.get(new Random().nextInt(elemList.size()));
    }

    public static <T> T outOf(@NotNull Collection<T> collection) {
        return outOf(collection.toArray((T[]) new Object[]{}));
    }

    @SafeVarargs
    public static <T> T outOf(T element1, T element2, T... elements) {
        return outOf(Merger.mergeToList(List.of(element1), element2, elements));
    }
}
