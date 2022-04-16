package me.KrazyManJ.KrazyEngine.Spigot;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public final class BukkitUtils {
    public static List<String> getOnlinePlayerNames(){
        List<String> pl = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) pl.add(p.getName());
        return pl;
    }
    public static boolean isAnyPlayerOnline(){
        return Bukkit.getOnlinePlayers().size() > 0;
    }
}
