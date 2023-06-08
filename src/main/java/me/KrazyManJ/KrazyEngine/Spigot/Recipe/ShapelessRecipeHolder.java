package me.KrazyManJ.KrazyEngine.Spigot.Recipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public final class ShapelessRecipeHolder extends ARecipeHolder {

    private final List<List<ItemStack>> items = new ArrayList<>();
    private final List<List<Material>> mats = new ArrayList<>();
    private CraftingBookCategory category;

    public ShapelessRecipeHolder(String id, @NotNull ItemStack result) {
        super(id, result);
        category = CraftingBookCategory.MISC;
    }
    public ShapelessRecipeHolder(String id, @NotNull ItemStack result, CraftingBookCategory category) {
        super(id, result);
        this.category = category;
    }

    public ShapelessRecipeHolder addIngredient(ItemStack i) {
        items.add(List.of(i));
        return this;
    }

    public ShapelessRecipeHolder addIngredient(Material m) {
        mats.add(List.of(m));
        return this;
    }

    public ShapelessRecipeHolder addIngredient(ItemStack... is) {
        items.add(List.of(is));
        return this;
    }

    public ShapelessRecipeHolder addIngredient(Material... ms) {
        mats.add(List.of(ms));
        return this;
    }

    public ShapelessRecipeHolder setCategory(CraftingBookCategory category) {
        this.category = category;
        return this;
    }

    @Override
    public @NotNull Recipe createRecipe(JavaPlugin plugin) {
        ShapelessRecipe r = new ShapelessRecipe(new NamespacedKey(plugin, id), result);
        r.setCategory(category);
        items.forEach(i -> r.addIngredient(new RecipeChoice.ExactChoice(i)));
        mats.forEach(m -> r.addIngredient(new RecipeChoice.MaterialChoice(m)));
        return r;
    }
}
