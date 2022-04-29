package me.KrazyManJ.KrazyEngine.Spigot.Recipe;

import me.KrazyManJ.KrazyEngine.Any.ListMerger;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings({"unchecked", "unused"})
public abstract class ACookingRecipeHolder extends ARecipeHolder {

    protected float expReward;
    protected int cookingTime;
    protected final List<?> input;

    ACookingRecipeHolder(@NotNull ItemStack result, int defCookTime, @NotNull ItemStack input, ItemStack ...otherInputs) {
        super(result);
        this.input = ListMerger.merge(input,List.of(otherInputs));
        this.cookingTime = defCookTime;
        this.expReward = 0F;
    }
    ACookingRecipeHolder(@NotNull ItemStack result, int defCookTime, @NotNull Material input, Material ...otherInputs){
        super(result);
        this.input = ListMerger.merge(input,List.of(otherInputs));
        this.cookingTime = defCookTime;
        this.expReward = 0F;
    }

    public final float getExpReward() { return expReward; }
    public final void setExpReward(Float expReward) { this.expReward = expReward; }
    public final int getCookingTime() { return cookingTime; }
    public final void setCookingTime(Integer cookingTime) { this.cookingTime = cookingTime; }

    protected final RecipeChoice toRecipeChoice(){
        return input.get(0) instanceof Material
                ? new RecipeChoice.MaterialChoice((List<Material>) input)
                : new RecipeChoice.ExactChoice((List<ItemStack>) input);
    }
}
