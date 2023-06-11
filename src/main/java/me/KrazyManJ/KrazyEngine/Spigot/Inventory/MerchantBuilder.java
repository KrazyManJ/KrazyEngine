package me.KrazyManJ.KrazyEngine.Spigot.Inventory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public final class MerchantBuilder {

    private final Merchant r;
    private final List<MerchantRecipe> recipes = new ArrayList<>();

    public MerchantBuilder(String title) {
        r = Bukkit.createMerchant(title);
    }

    public MerchantBuilder addRecipe(ItemStack result, ItemStack ingr) {
        return addRecipe(result, ingr, null, false);
    }

    public MerchantBuilder addRecipe(ItemStack result, ItemStack ingr, boolean expReward) {
        return addRecipe(result, ingr, null, expReward);
    }

    public MerchantBuilder addRecipe(ItemStack result, ItemStack ingr1, ItemStack ingr2) {
        return addRecipe(result, ingr1, ingr2, false);
    }

    public MerchantBuilder addRecipe(ItemStack result, ItemStack ingr1, ItemStack ingr2, boolean expReward) {
        MerchantRecipe r = new MerchantRecipe(result, 0, 2147483647, expReward);
        r.addIngredient(ingr1);
        if (ingr2 != null) r.addIngredient(ingr2);
        recipes.add(r);
        return this;
    }

    public Merchant build() {
        r.setRecipes(recipes);
        return r;
    }
}
