package me.KrazyManJ.KrazyEngine.Spigot.Recipe;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public abstract class ARecipeHolder {
    public @NotNull abstract Recipe createRecipe(JavaPlugin plugin, String key, ItemStack result);
}
