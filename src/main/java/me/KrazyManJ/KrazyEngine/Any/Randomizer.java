package me.KrazyManJ.KrazyEngine.Any;

import java.util.List;
import java.util.Random;

public final class Randomizer {

    private Randomizer(){}

    public static <T> T outOf(T[] elements){
        return elements[new Random().nextInt(elements.length)];
    }

    public static <T> T outOf(List<T> elemList){
        return elemList.get(new Random().nextInt(elemList.size()));
    }
}
