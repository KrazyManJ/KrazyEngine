package me.KrazyManJ.KrazyEngine.Spigot.Recipe;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public abstract class ARecipeHolder {

    protected String id;
    protected ItemStack result;

    ARecipeHolder(String id, @NotNull ItemStack result) {
        this.result = result;
    }

    public final ItemStack getResult() {
        return result.clone();
    }

    public final ARecipeHolder setResult(@NotNull ItemStack result) {
        this.result = result.clone();
        return this;
    }

    public final String getId() {
        return id;
    }

    public final void setId(String id) {
        this.id = id;
    }

    public @NotNull
    abstract Recipe createRecipe(JavaPlugin plugin);
}
