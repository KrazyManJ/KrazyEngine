package me.KrazyManJ.KrazyEngine.Core.Command.TabBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@SuppressWarnings({"unchecked", "unused", "UnusedReturnValue"})
public abstract class TabField<F extends TabField<?, S>, S> {

    private final Function<S, List<String>> call;
    private final List<F> fields = new ArrayList<>();
    private final Predicate<S> predicate;

    public TabField(@NotNull List<String> args) {
        this.call = tabPlayer -> args;
        predicate = player -> true;
    }

    public TabField(@NotNull List<String> args, @NotNull Predicate<S> predicate) {
        this.call = tabPlayer -> args;
        this.predicate = predicate;
    }

    public TabField(@NotNull Function<S, List<String>> args) {
        this.call = args;
        predicate = player -> true;
    }

    public TabField(@NotNull Function<S, List<String>> args, @NotNull Predicate<S> predicate) {
        this.call = args;
        this.predicate = predicate;
    }

    public TabField(@NotNull String arg) {
        this.call = tabPlayer -> List.of(arg);
        predicate = player -> true;
    }

    public TabField(@NotNull String arg, @NotNull Predicate<S> predicate) {
        this.call = tabPlayer -> List.of(arg);
        this.predicate = predicate;
    }

    public TabField(@NotNull String arg, String... oArgs) {
        List<String> args = new ArrayList<>(List.of(arg));
        args.addAll(List.of(oArgs));
        this.call = tabPlayer -> args;
        predicate = player -> true;
    }

    public F fields(@NotNull F field, F... fields) {
        this.fields.add(field);
        this.fields.addAll(List.of(fields));
        return (F) this;
    }

    public final List<String> getArgs(@NotNull S p) {
        return call.apply(p);
    }

    public final boolean hasFields() {
        return !fields.isEmpty();
    }

    public final List<F> getFields() {
        return fields;
    }

    public final boolean meetRequirement(@NotNull S p) {
        return predicate.test(p);
    }

    public final boolean contains(@NotNull S p, String arg) {
        return arg.length() > 0 && call.apply(p).contains(arg);
    }
}
