package me.KrazyManJ.KrazyEngine.Spigot.Recipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unused")
public final class ShapedRecipeHolder extends ARecipeHolder {

    private final String[] shape;
    private final HashMap<Character, List<ItemStack>> items = new HashMap<>();
    private final HashMap<Character, List<Material>> mats = new HashMap<>();
    private CraftingBookCategory category;

    public ShapedRecipeHolder(String id, @NotNull ItemStack result, String... shape) {
        super(id, result);
        this.shape = shape;
        this.category = CraftingBookCategory.MISC;
    }

    public ShapedRecipeHolder(String id, @NotNull ItemStack result, CraftingBookCategory category , String... shape) {
        super(id, result);
        this.shape = shape;
        this.category = category;
    }

    public ShapedRecipeHolder setIngredient(char ch, ItemStack i) {
        items.put(ch, List.of(i));
        return this;
    }

    public ShapedRecipeHolder setIngredient(char ch, Material m) {
        mats.put(ch, List.of(m));
        return this;
    }

    public ShapedRecipeHolder setIngredient(char ch, ItemStack... is) {
        items.put(ch, Arrays.asList(is));
        return this;
    }

    public ShapedRecipeHolder setIngredient(char ch, Material... ms) {
        mats.put(ch, Arrays.asList(ms));
        return this;
    }

    public ShapedRecipeHolder setCategory(CraftingBookCategory category) {
        this.category = category;
        return this;
    }

    @Override
    public @NotNull Recipe createRecipe(JavaPlugin plugin) {
        ShapedRecipe r = new ShapedRecipe(new NamespacedKey(plugin, getId()), result);
        r.shape(shape);
        r.setCategory(category);
        items.forEach((ch, i) -> r.setIngredient(ch, new RecipeChoice.ExactChoice(i)));
        mats.forEach((ch, m) -> r.setIngredient(ch, new RecipeChoice.MaterialChoice(m)));
        return r;
    }
}
