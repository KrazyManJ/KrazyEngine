package me.KrazyManJ.KrazyEngine.Item;

import me.KrazyManJ.KrazyEngine.Text.ColorUtils;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftItem;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.UUID;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public final class StackMaker {
    private final ItemStack stack;

    public StackMaker(Material mat) {
        stack = new ItemStack(mat);
    }

    public StackMaker amount(int amount){
        stack.setAmount(amount);
        return this;
    }

    public StackMaker displayName(String name){
        ItemMeta m = stack.getItemMeta();
        assert m != null;
        m.setDisplayName(ColorUtils.colorize(name));
        stack.setItemMeta(m);
        return this;
    }

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

    public StackMaker unbreakable(){
        ItemMeta m = stack.getItemMeta();
        assert m != null;
        m.setUnbreakable(true);
        stack.setItemMeta(m);
        return this;
    }

    public StackMaker customModelData(int data){
        ItemMeta m = stack.getItemMeta();
        assert m != null;
        m.setCustomModelData(data);
        stack.setItemMeta(m);
        return this;
    }

    public StackMaker enchant(Enchantment enchantment, int level){
        stack.addUnsafeEnchantment(enchantment,level);
        return this;
    }

    public StackMaker itemFlags(ItemFlag ...flags){
        ItemMeta m = stack.getItemMeta();
        assert m != null;
        m.addItemFlags(flags);
        stack.setItemMeta(m);
        return this;
    }

    public StackMaker modifier(Attribute attribute, String name, double value, AttributeModifier.Operation operation){
        ItemMeta m = stack.getItemMeta();
        assert m != null;
        m.addAttributeModifier(attribute,new AttributeModifier(UUID.randomUUID(),name,value,operation));
        stack.setItemMeta(m);
        return this;
    }

    public ItemStack make(){
        return stack;
    }
}
