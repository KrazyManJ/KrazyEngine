package me.KrazyManJ.KrazyEngine.Spigot;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
public final class CommandMapRegistry {

    @Deprecated private CommandMapRegistry() {}

    private static CommandMap commandMap;
    static {
        try {
            Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            commandMap = (CommandMap) f.get(Bukkit.getServer());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void register(Command commandExecutor){
        commandMap.register(commandExecutor.getName(),commandExecutor);
    }

    public static void register(String id,Command commandExecutor){
        commandMap.register(id, commandExecutor);
    }

    public static Command getCommand(String commandLabel){
        return commandMap.getCommand(commandLabel);
    }

    public static boolean isValidCommand(String commandLabel){
        return commandMap.getCommand(commandLabel) != null;
    }
}
