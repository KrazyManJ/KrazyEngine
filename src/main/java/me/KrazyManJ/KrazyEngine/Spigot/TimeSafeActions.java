package me.KrazyManJ.KrazyEngine.Spigot;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

@SuppressWarnings("unused")
public final class TimeSafeActions {

    private TimeSafeActions() {
    }

    public static void teleport(Plugin plugin, Player player, Location destination) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(destination);
            }
        }.runTaskLater(plugin, 1);
    }
}
