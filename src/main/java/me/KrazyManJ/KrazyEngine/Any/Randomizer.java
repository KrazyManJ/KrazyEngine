package me.KrazyManJ.KrazyEngine.Any;

import java.util.List;
import java.util.Random;

@SuppressWarnings({"unused", "unchecked"})
public final class Randomizer {

    @Deprecated
    private Randomizer() {
    }

    public static <T> T outOf(T[] elements) {
        return elements[new Random().nextInt(elements.length)];
    }

    public static <T> T outOf(List<T> elemList) {
        return elemList.get(new Random().nextInt(elemList.size()));
    }

    @SafeVarargs
    public static <T> T outOf(T element1, T element2, T... elements) {
        return outOf((List<T>) List.of(element1, element2, elements));
    }
}
