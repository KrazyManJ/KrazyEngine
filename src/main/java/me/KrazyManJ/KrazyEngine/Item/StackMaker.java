package me.KrazyManJ.KrazyEngine.Item;

import me.KrazyManJ.KrazyEngine.Text.ColorUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public class StackMaker {
    private final ItemStack stack;

    public StackMaker(Material mat) {
        stack = new ItemStack(mat);
    }

    //Amount
    public StackMaker amount(int amount){
        stack.setAmount(amount);
        return this;
    }


    //DisplayName
    public StackMaker displayName(String name){
        ItemMeta m = stack.getItemMeta();
        assert m != null;
        m.setDisplayName(ColorUtils.colorize(name));
        stack.setItemMeta(m);
        return this;
    }

    //Lore
    public StackMaker lore(String ...lore){
        ItemMeta m = stack.getItemMeta();
        assert m != null;
        m.setLore(ColorUtils.colorize(List.of(lore)));
        stack.setItemMeta(m);
        return this;
    }
    public StackMaker lore(List<String> lore){
        ItemMeta m = stack.getItemMeta();
        assert m != null;
        m.setLore(ColorUtils.colorize(lore));
        stack.setItemMeta(m);
        return this;
    }

    //Unbreakable
    public StackMaker unbreakable(){
        ItemMeta m = stack.getItemMeta();
        assert m != null;
        m.setUnbreakable(true);
        stack.setItemMeta(m);
        return this;
    }

    //CustomModelData
    public StackMaker customModelData(int data){
        ItemMeta m = stack.getItemMeta();
        assert m != null;
        m.setCustomModelData(data);
        stack.setItemMeta(m);
        return this;
    }

    //Enchants
    public StackMaker enchant(Enchantment enchantment, int level){
        stack.addUnsafeEnchantment(enchantment,level);
        return this;
    }

    //ItemFlags
    public StackMaker itemFlags(ItemFlag ...flags){
        ItemMeta m = stack.getItemMeta();
        assert m != null;
        m.addItemFlags(flags);
        stack.setItemMeta(m);
        return this;
    }


    //MODIFIER : COMMING SOON

    public ItemStack make(){
        return stack;
    }
}
