package me.KrazyManJ.KrazyEngine.Spigot.Command;

import me.KrazyManJ.KrazyEngine.Any.Collection.Merger;
import me.KrazyManJ.KrazyEngine.Core.ReflectionHandler;
import me.KrazyManJ.KrazyEngine.Core.ReflectionUsed;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Method;
import java.util.HashMap;

@SuppressWarnings({"unused", "unchecked"})
@ReflectionUsed
public final class CommandMapRegistry {

    private CommandMapRegistry() {
    }

    private static SimpleCommandMap commandMap;
    private static HashMap<String, Command> knownCommands;
    private static Class<?> craftServer;
    private static Method syncCommands;

    static {
        try {
            commandMap = (SimpleCommandMap) ReflectionHandler.makeFieldAccessible(Bukkit.getServer().getClass()
                    .getDeclaredField("commandMap")).get(Bukkit.getServer());
            knownCommands = (HashMap<String, Command>) ReflectionHandler.makeFieldAccessible(
                    SimpleCommandMap.class.getDeclaredField("knownCommands")).get(commandMap);
            craftServer = ReflectionHandler.craftbukkitClass("CraftServer");
            syncCommands = craftServer.getDeclaredMethod("syncCommands");

        } catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void register(Command commandExecutor) {
        if (commandMap == null) return;
        commandMap.register(commandExecutor.getName(), commandExecutor);
        updateCommandPallete();
    }

    public static synchronized void register(String id, Command commandExecutor) {
        if (commandMap == null) return;
        commandMap.register(id, commandExecutor);
        updateCommandPallete();
    }

    public static synchronized void register(Command commandExecutor, String permission) {
        commandExecutor.setPermission(permission);
        register(commandExecutor);
    }

    public static synchronized void register(String id, Command commandExecutor, String permission) {
        commandExecutor.setPermission(permission);
        register(id, commandExecutor);
    }

    public static synchronized void register(JavaPlugin plugin, Command command){
        register(plugin.getName(),command);
    }

    public static synchronized void register(JavaPlugin plugin, Command command, String permission){
        command.setPermission(permission);
        register(plugin,command);
    }

    public static Command getCommand(String commandLabel) {
        if (commandMap == null) return null;
        return commandMap.getCommand(commandLabel);
    }

    public static boolean isValidCommand(String commandLabel) {
        if (commandMap == null) return false;
        return commandMap.getCommand(commandLabel) != null;
    }

    public static synchronized void unregister(String namespace, Command command) {
        if (commandMap == null || knownCommands == null) return;
        command.unregister(commandMap);
        for (String label : Merger.mergeToList(command.getAliases(), command.getName())) {
            if (knownCommands.containsKey(label) && knownCommands.get(label).getName().equals(command.getName())) {
                knownCommands.entrySet().removeIf(e -> e.getKey().contains(namespace + ":" + label) || e.getKey().equals(label));
                Bukkit.getHelpMap().getHelpTopics().removeIf(e -> e.getName().equals("/" + command.getName()));
            }
        }
        updateCommandPallete();
    }

    public static synchronized void unregister(Command command) {
        unregister("", command);
    }

    public static synchronized void unregister(String commandLabel) {
        unregister("", getCommand(commandLabel));
    }

    public static synchronized void unregister(String namespace, String commandLabel) {
        unregister(namespace, getCommand(commandLabel));
    }

    public static void updateCommandPallete() {
        if (syncCommands == null || craftServer == null) return;
        ReflectionHandler.invokeMethod(syncCommands, ReflectionHandler.cast(Bukkit.getServer(), craftServer));
    }
}
