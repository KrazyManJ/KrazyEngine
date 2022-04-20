package me.KrazyManJ.KrazyEngine;

import me.KrazyManJ.KrazyEngine.Spigot.CommandMapRegistry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public final class SpigotMain extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        CommandMapRegistry.register(new Command("testengine") {
            @Override
            public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
                if (!(commandSender instanceof Player p)) return false;
                //Test code here:

                return true;
            }
        });
    }

    @Override
    public void onDisable() {super.onDisable();}
}
