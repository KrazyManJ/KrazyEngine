package me.KrazyManJ.KrazyEngine.Any.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public final class TabCompleteUtils {

    @Deprecated private TabCompleteUtils() {}

    public static List<String> suggestByInput(String input, List<String> suggestions) {
        return (!input.equals(""))
                ? suggestions.stream().filter(f -> f.toLowerCase().contains(input.toLowerCase())).collect(Collectors.toList())
                : suggestions;
    }

    public static List<String> clearTabComplete(){
        return new ArrayList<>();
    }
}
