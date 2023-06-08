package me.KrazyManJ.KrazyEngine.Spigot.Recipe;

import me.KrazyManJ.KrazyEngine.Any.Merger;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.StonecuttingRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class StonecuttingRecipeHolder extends ARecipeHolder {

    private RecipeChoice input;

    public StonecuttingRecipeHolder(String id, @NotNull ItemStack result, Material input, Material... otherInputs) {
        super(id, result);
        this.input = new RecipeChoice.MaterialChoice(Merger.mergeToArray(input, otherInputs));
    }

    public StonecuttingRecipeHolder(String id, @NotNull ItemStack result, ItemStack input, ItemStack... otherInputs) {
        super(id, result);
        this.input = new RecipeChoice.ExactChoice(Merger.mergeToArray(input, otherInputs));
    }

    public RecipeChoice getInput() {
        return input;
    }

    public void setInput(RecipeChoice input) {
        this.input = input;
    }
    
    public void setInput(Material input, Material... otherInputs){
        this.input = new RecipeChoice.MaterialChoice(Merger.mergeToArray(input, otherInputs));
    }

    public void setInput(ItemStack input, ItemStack... otherInputs){
        this.input = new RecipeChoice.ExactChoice(Merger.mergeToArray(input, otherInputs));
    }

    @Override
    public @NotNull Recipe createRecipe(JavaPlugin plugin) {
        return new StonecuttingRecipe(new NamespacedKey(plugin, id), result, input);
    }
}
