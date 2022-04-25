package me.KrazyManJ.KrazyEngine.Any;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ListMerger {
    @SafeVarargs
    public static <T> List<T> merge(List<T> list, T ...object){
        List<T> r = new ArrayList<>(list);
        r.addAll(List.of(object));
        return r;
    }
}
