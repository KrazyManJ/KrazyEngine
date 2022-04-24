package me.KrazyManJ.KrazyEngine.Spigot;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@SuppressWarnings("unused")
public final class CommandMapRegistry {

    @Deprecated private CommandMapRegistry() {}

    private static SimpleCommandMap commandMap;
    static {
        try {
            Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            commandMap = (SimpleCommandMap) f.get(Bukkit.getServer());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void register(Command commandExecutor){
        commandMap.register(commandExecutor.getName(),commandExecutor);
    }

    public static synchronized void register(String id,Command commandExecutor){
        commandMap.register(id, commandExecutor);
    }

    public static Command getCommand(String commandLabel){
        return commandMap.getCommand(commandLabel);
    }

    public static boolean isValidCommand(String commandLabel){
        return commandMap.getCommand(commandLabel) != null;
    }
}
