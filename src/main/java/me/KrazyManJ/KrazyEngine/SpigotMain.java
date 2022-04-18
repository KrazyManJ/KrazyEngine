package me.KrazyManJ.KrazyEngine;

import me.KrazyManJ.KrazyEngine.Any.Text.BracketHolder;
import me.KrazyManJ.KrazyEngine.Any.Text.PlaceholderBuilder;
import me.KrazyManJ.KrazyEngine.Spigot.CommandMapRegistry;
import me.KrazyManJ.KrazyEngine.Spigot.Item.ItemUtils;
import me.KrazyManJ.KrazyEngine.Spigot.Item.StackMaker;
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
        CommandMapRegistry.register(new Command("unstack") {
            @Override
            public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
                if (!(commandSender instanceof Player p)) return false;
                p.getInventory().addItem(new StackMaker(Material.STONE).unstackable().make());
                Bukkit.broadcastMessage(
                    "Compared: "+ItemUtils.compareUnstackable(p.getInventory().getItemInMainHand(),p.getInventory().getItemInOffHand())

                );
                return true;
            }
        });
    }

    @Override
    public void onDisable() {super.onDisable();}

    public static String getVersion(){
        return Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    }
}
