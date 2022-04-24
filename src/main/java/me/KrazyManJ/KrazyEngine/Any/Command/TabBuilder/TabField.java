package me.KrazyManJ.KrazyEngine.Any.Command.TabBuilder;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public final class TabField {

    public static @NotNull TabField of(List<String> args){ return new TabField(args); }
    public static @NotNull TabField of(List<String> args, Predicate<CommandSender> predicate){ return new TabField(args, predicate); }
    public static @NotNull TabField of(String arg){ return new TabField(arg); }
    public static @NotNull TabField of(String arg, Predicate<CommandSender> predicate){ return new TabField(arg,predicate); }
    public static @NotNull TabField of(TabArgsHolder args){ return new TabField(args); }
    public static @NotNull TabField of(TabArgsHolder args, Predicate<CommandSender> predicate){ return new TabField(args,predicate); }

    private final TabArgsHolder call;
    private final List<TabField> fields = new ArrayList<>();
    private final Predicate<CommandSender> predicate;

    public TabField(@NotNull List<String> args) {
        this.call = tabPlayer -> args;
        predicate = player -> true;
    }
    public TabField(@NotNull List<String> args, @NotNull Predicate<CommandSender> predicate){
        this.call = tabPlayer -> args;
        this.predicate = predicate;
    }
    public TabField(@NotNull TabArgsHolder args) {
        this.call = args;
        predicate = player -> true;
    }
    public TabField(@NotNull TabArgsHolder args, @NotNull Predicate<CommandSender> predicate){
        this.call = args;
        this.predicate = predicate;
    }
    public TabField(@NotNull String arg){
        this.call = tabPlayer -> List.of(arg);
        predicate = player -> true;
    }
    public TabField(@NotNull String arg, @NotNull Predicate<CommandSender> predicate){
        this.call = tabPlayer -> List.of(arg);
        this.predicate = predicate;
    }

    public TabField fields(@NotNull TabField field, TabField ...fields){
        this.fields.add(field);
        this.fields.addAll(List.of(fields));
        return this;
    }

    public List<String> getArgs(@NotNull CommandSender p){ return call.get(p); }
    public boolean hasFields(){ return !fields.isEmpty(); }
    public List<TabField> getFields() { return fields; }
    public boolean meetRequirement(@NotNull CommandSender p) { return predicate.test(p); }
    public boolean contains(@NotNull CommandSender p, String arg){ return arg.length() > 0 && call.get(p).contains(arg); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TabField field = (TabField) o;
        return Objects.equals(call, field.call) && Objects.equals(fields, field.fields) && Objects.equals(predicate, field.predicate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(call, fields, predicate);
    }
}
