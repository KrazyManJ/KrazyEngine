package me.KrazyManJ.KrazyEngine.Spigot.Recipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.CampfireRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public final class CampfireRecipeHolder extends ACookingRecipeHolder {

    public static final int DEFAULT_COOKING_TIME = 600;

    public CampfireRecipeHolder(@NotNull ItemStack result, @NotNull ItemStack input, ItemStack... otherInputs) {
        super(result, DEFAULT_COOKING_TIME, input, otherInputs);
    }
    public CampfireRecipeHolder(@NotNull ItemStack result, @NotNull Material input, Material... otherInputs) {
        super(result, DEFAULT_COOKING_TIME, input, otherInputs);
    }

    @Override
    public @NotNull Recipe createRecipe(JavaPlugin plugin, String key) {
        return new CampfireRecipe(new NamespacedKey(plugin,key),result,toRecipeChoice(),expReward,cookingTime);
    }
}
