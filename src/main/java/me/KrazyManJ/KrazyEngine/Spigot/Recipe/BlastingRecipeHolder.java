package me.KrazyManJ.KrazyEngine.Spigot.Recipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public final class BlastingRecipeHolder extends ACookingRecipeHolder<BlastingRecipeHolder> {

    public static final int DEFAULT_COOKING_TIME = 100;

    public BlastingRecipeHolder(String id, @NotNull ItemStack result, @NotNull ItemStack input, ItemStack... otherInputs) {
        super(id, result, DEFAULT_COOKING_TIME, input, otherInputs);
    }

    public BlastingRecipeHolder(String id, @NotNull ItemStack result, @NotNull Material input, Material... otherInputs) {
        super(id, result, DEFAULT_COOKING_TIME, input, otherInputs);
    }

    @Override
    public @NotNull Recipe createRecipe(JavaPlugin plugin) {
        return new BlastingRecipe(new NamespacedKey(plugin,getId()), result, toRecipeChoice(), expReward, cookingTime);
    }
}
