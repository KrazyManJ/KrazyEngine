package me.KrazyManJ.KrazyEngine.Spigot;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;

import java.util.List;

@SuppressWarnings("unused")
public final class BukkitUtils {

    private BukkitUtils() {
    }

    public static List<String> getOnlinePlayerNames() {
        return Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).toList();
    }

    public static boolean isAnyPlayerOnline() {
        return Bukkit.getOnlinePlayers().size() > 0;
    }
}
