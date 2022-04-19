package me.KrazyManJ.KrazyEngine;

import me.KrazyManJ.KrazyEngine.Any.Component.ComponentUtils;
import me.KrazyManJ.KrazyEngine.Spigot.CommandMapRegistry;
import me.KrazyManJ.KrazyEngine.Spigot.Item.ItemUtils;
import me.KrazyManJ.KrazyEngine.Spigot.Item.StackMaker;
import me.KrazyManJ.KrazyEngine.Spigot.PacketReflection.PacketBossBar;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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

                new PacketBossBar(ComponentUtils.colored("test"), PacketBossBar.Color.RED, PacketBossBar.Style.SOLID).sendBar(p);

                return true;
            }
        });
    }

    @Override
    public void onDisable() {super.onDisable();}
}