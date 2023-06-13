package me.KrazyManJ.KrazyEngine.Spigot.Recipe;

import me.KrazyManJ.KrazyEngine.Any.Collection.Merger;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.SmithingRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("unused")
public final class SmithingRecipeHolder extends ARecipeHolder {

    private RecipeChoice base;
    private RecipeChoice addition;

    SmithingRecipeHolder(String id, @NotNull ItemStack result, Material base, Material addition) {
        super(id, result);
        this.base = new RecipeChoice.MaterialChoice(base);
        this.addition = new RecipeChoice.MaterialChoice(addition);
    }

    SmithingRecipeHolder(String id, @NotNull ItemStack result, ItemStack base, ItemStack addition) {
        super(id, result);
        this.base = new RecipeChoice.ExactChoice(base);
        this.addition = new RecipeChoice.ExactChoice(addition);
    }

    public SmithingRecipeHolder setBase(@NotNull ItemStack base, ItemStack... otherBaseChoices) {
        this.base = new RecipeChoice.ExactChoice(Merger.mergeToListAtStart(base, List.of(otherBaseChoices)));
        return this;
    }

    public SmithingRecipeHolder setBase(@NotNull Material base, Material... otherBaseChoices) {
        this.base = new RecipeChoice.MaterialChoice(Merger.mergeToListAtStart(base, List.of(otherBaseChoices)));
        return this;
    }

    public SmithingRecipeHolder setAddition(@NotNull ItemStack addition, ItemStack... otherAdditionChoices) {
        this.addition = new RecipeChoice.ExactChoice(Merger.mergeToListAtStart(addition, List.of(otherAdditionChoices)));
        return this;
    }

    public SmithingRecipeHolder setAddition(@NotNull Material addition, Material... otherAdditionChoices) {
        this.addition = new RecipeChoice.MaterialChoice(Merger.mergeToListAtStart(addition, List.of(otherAdditionChoices)));
        return this;
    }

    @Override
    public @NotNull Recipe createRecipe(JavaPlugin plugin) {
        return new SmithingRecipe(new NamespacedKey(plugin, id), result, base, addition);
    }
}
