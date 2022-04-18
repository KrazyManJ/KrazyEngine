package me.KrazyManJ.KrazyEngine.Spigot;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

@SuppressWarnings("unused")
public final class BukkitLog {

    @Deprecated private BukkitLog() {}

    public static void info(String message){ Bukkit.getLogger().log(Level.INFO,message); }
    public static void info(JavaPlugin plugin, String message){ plugin.getLogger().log(Level.INFO,message); }

    public static void warn(String message){ Bukkit.getLogger().log(Level.WARNING,message); }
    public static void warn(JavaPlugin plugin, String message){ plugin.getLogger().log(Level.WARNING,message); }

    public static void error(String message){ Bukkit.getLogger().log(Level.SEVERE,message); }
    public static void error(JavaPlugin plugin, String message){ plugin.getLogger().log(Level.SEVERE,message); }
}
