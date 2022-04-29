package me.KrazyManJ.KrazyEngine.Spigot.Recipe;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public abstract class ARecipeHolder {

    protected ItemStack result;
    ARecipeHolder(@NotNull ItemStack result) { this.result = result; }

    public final ItemStack getResult() { return result; }
    public final ARecipeHolder setResult(@NotNull ItemStack result) { this.result = result; return this; }

    public @NotNull abstract Recipe createRecipe(JavaPlugin plugin, String key);
}
