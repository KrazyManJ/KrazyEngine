package me.KrazyManJ.KrazyEngine;


import me.KrazyManJ.KrazyEngine.Any.Command.TabCompleteUtils;
import me.KrazyManJ.KrazyEngine.Spigot.CommandMapRegistry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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
            @NotNull
            @Override
            public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
                return TabCompleteUtils.clearTabComplete();
            }
        });
    }

    @Override
    public void onDisable() {super.onDisable();}
}
