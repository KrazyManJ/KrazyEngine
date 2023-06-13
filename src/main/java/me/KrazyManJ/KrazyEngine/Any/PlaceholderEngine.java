package me.KrazyManJ.KrazyEngine.Any;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public final class PlaceholderEngine {

    private final String prefix;
    private final String suffix;
    private final HashMap<String, Supplier<String>> placeholderProcessors = new HashMap<>();

    public PlaceholderEngine(String prefix, String suffix){
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public PlaceholderEngine addPlaceholder(String placeholder, Supplier<String> value){
        placeholderProcessors.put(placeholder,value);
        return this;
    }

    public String apply(String text){
        for (Map.Entry<String,Supplier<String>> set : placeholderProcessors.entrySet())
            text = text.replace(prefix+set.getKey()+suffix,set.getValue().get());
        return text;
    }
}
