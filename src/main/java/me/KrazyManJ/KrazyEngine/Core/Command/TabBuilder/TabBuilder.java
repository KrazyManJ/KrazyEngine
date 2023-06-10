package me.KrazyManJ.KrazyEngine.Core.Command.TabBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings({"unused", "UnusedReturnValue", "unchecked"})
public abstract class TabBuilder<B extends TabBuilder<B, F, S>, F extends TabField<F, S>, S> {
    private final List<F> fields = new ArrayList<>();
    private final List<String> defaultList;

    public TabBuilder() {
        defaultList = emptySuggestions();
    }

    public TabBuilder(@NotNull List<String> defaultInsert) {
        defaultList = defaultInsert;
    }

    public final B fields(@NotNull F field, F... fields) {
        this.fields.add(field);
        this.fields.addAll(List.of(fields));
        return (B) this;
    }

    public final List<String> build(S sender, String[] args) {
        if (fields.isEmpty()) return defaultList;
        boolean found = false;
        List<F> currFields = fields;
        for (String arg : Arrays.copyOfRange(args, 0, args.length - 1)) {
            for (F field : currFields) {
                if (field.contains(sender, arg)) {
                    if (!field.hasFields()) return defaultList;
                    if (!field.meetRequirement(sender)) return defaultList;
                    currFields = field.getFields();
                    found = true;
                    break;
                }
            }
            if (!found) {
                return defaultList;
            }
        }

        List<String> stringList = new ArrayList<>();
        for (F field : currFields) if (field.meetRequirement(sender)) stringList.addAll(field.getArgs(sender));
        return suggestByInput(args[args.length - 1], stringList);
    }

    public static List<String> emptySuggestions() {
        return new ArrayList<>();
    }

    private static List<String> suggestByInput(@NotNull String input, List<String> suggestions) {
        return (!input.equals(""))
                ? suggestions.stream().filter(f -> f.toLowerCase().contains(input.toLowerCase())).collect(Collectors.toList())
                : suggestions;
    }
}
