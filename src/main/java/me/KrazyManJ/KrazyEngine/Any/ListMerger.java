package me.KrazyManJ.KrazyEngine.Any;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public final class ListMerger {

    @Deprecated private ListMerger() {}

    @SafeVarargs
    public static <T> List<T> merge(List<T> list, T object, T ...objects){
        List<T> r = new ArrayList<>(list);
        r.add(object);
        r.addAll(List.of(objects));
        return r;
    }

    public static <T> List<T> merge(T object, List<T> list){
        List<T> r = new ArrayList<>(list);
        r.add(0,object);
        return r;
    }
}
