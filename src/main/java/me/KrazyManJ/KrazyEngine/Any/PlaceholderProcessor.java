package me.KrazyManJ.KrazyEngine.Any;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Creates a processing for placeholders - replaces text with values defined by
 * placeholders.
 * <p>
 * These values are retrieved via string suppliers.
 * </p>
 *
 * @author KrazyManJ
 */
@SuppressWarnings("unused")
public final class PlaceholderProcessor {

    private final String prefix;
    private final String suffix;
    private final HashMap<String, Supplier<String>> placeholderProcessors = new HashMap<>();

    public PlaceholderProcessor(String surroundChars){
        this.prefix = surroundChars;
        this.suffix = surroundChars;
    }

    public PlaceholderProcessor(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    /**
     * Adds processor for specific placeholder
     * @param placeholder name of placeholder
     * @param valueSupplier supplier that returns value to be replaced in string
     * @return self (PlaceholderProcessor)
     */
    public PlaceholderProcessor addPlaceholder(String placeholder, Supplier<String> valueSupplier) {
        placeholderProcessors.put(placeholder, valueSupplier);
        return this;
    }

    /**
     * Process specific text - replaces all placeholders with value
     * @param text text to process
     * @return processed text with replaced placeholders
     */
    public String process(String text) {
        String r = text;
        for (Map.Entry<String, Supplier<String>> set : placeholderProcessors.entrySet())
            r = r.replace(prefix + set.getKey() + suffix, set.getValue().get());
        return r;
    }
}
