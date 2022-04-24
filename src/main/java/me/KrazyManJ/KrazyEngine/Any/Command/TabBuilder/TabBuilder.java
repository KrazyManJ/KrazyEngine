package me.KrazyManJ.KrazyEngine.Any.Command.TabBuilder;

import me.KrazyManJ.KrazyEngine.Any.Command.TabCompleteUtils;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public final class TabBuilder {

    private final List<TabField> fields = new ArrayList<>();
    private final List<String> defaultList;

    public TabBuilder() {
        defaultList = new ArrayList<>();
    }
    public TabBuilder(@NotNull List<String> defaultInsert) {
        defaultList = defaultInsert;
    }

    public TabBuilder fields(@NotNull TabField field, TabField ...fields){
        this.fields.add(field);
        this.fields.addAll(List.of(fields));
        return this;
    }
    public List<String> build(CommandSender sender, String[] args){
        if (fields.isEmpty()) return defaultList;
        boolean found = false;
        List<TabField> currFields = fields;
        for (String arg : Arrays.copyOfRange(args, 0, args.length-1)){
            for (TabField field : currFields){
                if (field.contains(sender,arg)){
                    if (!field.hasFields() || !field.meetRequirement(sender)) return defaultList;
                    currFields = field.getFields();
                    found = true;
                    break;
                }
            }
            if (!found) return defaultList;
        }

        List<String> stringList = new ArrayList<>();
        for (TabField field : currFields) if (field.meetRequirement(sender)) stringList.addAll(field.getArgs(sender));
        return TabCompleteUtils.suggestByInput(args[args.length-1],stringList);
    }
}
