package me.KrazyManJ.KrazyEngine;


import me.KrazyManJ.KrazyEngine.Any.Command.TabCompleteUtils;
import me.KrazyManJ.KrazyEngine.Any.Text.RomanNumber;
import me.KrazyManJ.KrazyEngine.Spigot.CommandMapRegistry;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
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
            public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
                if (!(sender instanceof Player p)) return false;
                FurnaceRecipe r = new FurnaceRecipe(NamespacedKey.minecraft("test"), new ItemStack(Material.STONE),Material.STONE,1,1);




                return true;
            }
            @NotNull
            @Override
            public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) throws IllegalArgumentException {
                return TabCompleteUtils.clearTabComplete();
            }
        });
    }

    @Override
    public void onDisable() {super.onDisable();}
}
