package me.KrazyManJ.KrazyEngine.Spigot.Recipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ShapedRecipeHolder extends ARecipeHolder {

    private final String[] shape;
    private final HashMap<Character, List<ItemStack>> items = new HashMap<>();
    private final HashMap<Character, List<Material>> mats = new HashMap<>();

    public ShapedRecipeHolder(String ...shape){
        this.shape = shape;
    }
    public ShapedRecipeHolder setIngredient(char ch, ItemStack i){
        items.put(ch, List.of(i));
        return this;
    }
    public ShapedRecipeHolder setIngredient(char ch, Material m){
        mats.put(ch, List.of(m));
        return this;
    }
    public ShapedRecipeHolder setIngredient(char ch, ItemStack ...is){
        items.put(ch, Arrays.asList(is));
        return this;
    }
    public ShapedRecipeHolder setIngredient(char ch, Material ...ms){
        mats.put(ch, Arrays.asList(ms));
        return this;
    }

    @Override
    public @NotNull Recipe createRecipe(JavaPlugin plugin, String key, ItemStack result) {
        ShapedRecipe r = new ShapedRecipe(new NamespacedKey(plugin,key),result);
        r.shape(shape);
        items.forEach((ch, i) -> r.setIngredient(ch, new RecipeChoice.ExactChoice(i)));
        mats.forEach((ch, m) -> r.setIngredient(ch, new RecipeChoice.MaterialChoice(m)));
        return r;
    }
}
