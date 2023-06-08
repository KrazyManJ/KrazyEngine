package me.KrazyManJ.KrazyEngine.Spigot.Recipe;

import me.KrazyManJ.KrazyEngine.Any.Merger;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings({"unchecked", "unused", "rawtypes"})
public abstract class ACookingRecipeHolder<T extends ACookingRecipeHolder> extends ARecipeHolder {

    protected float expReward;
    protected int cookingTime;
    protected final List<?> input;

    ACookingRecipeHolder(String id, @NotNull ItemStack result, int defCookTime, @NotNull ItemStack input, ItemStack... otherInputs) {
        super(id, result);
        this.input = Merger.mergeToList(input, List.of(otherInputs));
        this.cookingTime = defCookTime;
        this.expReward = 0F;
    }

    ACookingRecipeHolder(String id, @NotNull ItemStack result, int defCookTime, @NotNull Material input, Material... otherInputs) {
        super(id, result);
        this.input = Merger.mergeToList(input, List.of(otherInputs));
        this.cookingTime = defCookTime;
        this.expReward = 0F;
    }

    public final float getExpReward() {
        return expReward;
    }

    public final T setExpReward(Float expReward) {
        this.expReward = expReward;
        return (T) this;
    }

    public final int getCookingTime() {
        return cookingTime;
    }

    public final T setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
        return (T) this;
    }

    protected final RecipeChoice toRecipeChoice() {
        return input.get(0) instanceof Material
                ? new RecipeChoice.MaterialChoice((List<Material>) input)
                : new RecipeChoice.ExactChoice((List<ItemStack>) input);
    }
}
