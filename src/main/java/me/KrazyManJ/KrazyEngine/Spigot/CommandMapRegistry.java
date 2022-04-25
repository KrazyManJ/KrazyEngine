package me.KrazyManJ.KrazyEngine.Spigot;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

@SuppressWarnings({"unused", "unchecked"})
public final class CommandMapRegistry {

    @Deprecated private CommandMapRegistry() {}

    private static SimpleCommandMap commandMap;
    private static HashMap<String, Command> knownCommands;
    private static Class<?> craftServer;
    private static Method syncCommands;

    static {
        try {
            Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            commandMap = (SimpleCommandMap) f.get(Bukkit.getServer());
            Field m = SimpleCommandMap.class.getDeclaredField("knownCommands");
            m.setAccessible(true);
            knownCommands = (HashMap<String, Command>) m.get(commandMap);
            craftServer = Class.forName("org.bukkit.craftbukkit."+BukkitUtils.getVersion()+".CraftServer");
            syncCommands = craftServer.getDeclaredMethod("syncCommands");

        } catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void register(Command commandExecutor){
        commandMap.register(commandExecutor.getName(),commandExecutor);
        updateCommandPallete();
    }

    public static synchronized void register(String id,Command commandExecutor){
        commandMap.register(id, commandExecutor);
        updateCommandPallete();
    }

    public static Command getCommand(String commandLabel){
        return commandMap.getCommand(commandLabel);
    }

    public static boolean isValidCommand(String commandLabel){
        return commandMap.getCommand(commandLabel) != null;
    }

    public static synchronized void unregisterCommand(String namespace, Command command){
        command.unregister(commandMap);

        for (String alias : command.getAliases()){
            if(knownCommands.containsKey(alias) && knownCommands.get(alias).getName().equals(command.getName())){
                knownCommands.entrySet().removeIf(e -> e.getKey().contains(namespace + ":" + alias) || e.getKey().equals(alias));
                Bukkit.getHelpMap().getHelpTopics().removeIf(e -> e.getName().equals("/"+alias));
            }
        }
        knownCommands.entrySet().removeIf(e -> e.getKey().contains(namespace +":" + command.getName()) || e.getKey().equals(command.getName()));
        Bukkit.getHelpMap().getHelpTopics().removeIf(e -> e.getName().equals("/"+command.getName()));
        updateCommandPallete();
    }
    public static synchronized void unregisterCommand(Command command){
        unregisterCommand("",command);
    }
    public static synchronized void unregisterCommand(String commandLabel){
        unregisterCommand("",getCommand(commandLabel));
    }
    public static synchronized void unregisterCommand(String namespace, String commandLabel){
        unregisterCommand(namespace, getCommand(commandLabel));
    }
    public static void updateCommandPallete(){
        try {
            syncCommands.invoke(craftServer.cast(Bukkit.getServer()));
        } catch (InvocationTargetException | IllegalAccessException e) { e.printStackTrace(); }
    }
}
