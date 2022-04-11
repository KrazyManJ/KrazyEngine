package me.KrazyManJ.KrazyEngine.Any;

import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public final class CommandUtils {

    @Deprecated private CommandUtils() {}

    public static List<String> suggestByInput(String input, List<String> suggestions) {
        return (!input.equals(""))
                ? suggestions.stream().filter(f -> StringUtils.containsIgnoreCase(f, input)).collect(Collectors.toList())
                : suggestions;
    }
}
