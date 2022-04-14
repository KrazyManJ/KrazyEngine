package me.KrazyManJ.KrazyEngine.Spigot;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public final class ShapedRecipeMaker {
    private final ShapedRecipe recipe;
    public ShapedRecipeMaker(String id, ItemStack result) {
        this.recipe = new ShapedRecipe(NamespacedKey.minecraft(id),result);
    }
    public ShapedRecipeMaker(JavaPlugin plugin, String id, ItemStack result) {
        this.recipe = new ShapedRecipe(new NamespacedKey(plugin,id),result);
    }

    public ShapedRecipeMaker shape(String firstRow, String secondRow, String thirdRow){
        recipe.shape(firstRow,secondRow,thirdRow);
        return this;
    }
    public ShapedRecipeMaker ingredient(char key, Material m){
        recipe.setIngredient(key,m);
        return this;
    }
    public ShapedRecipeMaker ingredient(char key, ItemStack i){
        recipe.setIngredient(key, new RecipeChoice.ExactChoice(i));
        return this;
    }
    public void register(){
        Bukkit.addRecipe(recipe);
    }
}
