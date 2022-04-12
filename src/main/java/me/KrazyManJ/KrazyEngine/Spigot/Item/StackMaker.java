package me.KrazyManJ.KrazyEngine.Spigot.Item;

import me.KrazyManJ.KrazyEngine.Any.Text.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public class StackMaker {
    private final ItemStack stack;
    private static final NamespacedKey unstackableKey = new NamespacedKey("krazyengine","unstackable");

    public StackMaker(Material mat) { stack = new ItemStack(mat); }
    public StackMaker(ItemStack stack) { this.stack = stack.clone(); }

    public StackMaker amount(int amount){
        stack.setAmount(amount);
        return this;
    }

    public StackMaker displayName(String name){
        ItemMeta m = stack.getItemMeta();
        assert m != null;
        m.setDisplayName(ColorUtils.colorizeHex(name));
        stack.setItemMeta(m);
        return this;
    }

    public StackMaker lore(String ...lore){
        ItemMeta m = stack.getItemMeta();
        assert m != null;
        m.setLore(ColorUtils.colorizeHex(Arrays.asList(lore)));
        stack.setItemMeta(m);
        return this;
    }
    public StackMaker lore(List<String> lore){
        ItemMeta m = stack.getItemMeta();
        assert m != null;
        m.setLore(ColorUtils.colorizeHex(lore));
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

    public StackMaker unstackable(){
        ItemMeta m = stack.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().set(unstackableKey, PersistentDataType.LONG,
                Bukkit.getServer().getWorlds().get(0).getGameTime());
        stack.setItemMeta(m);
        return this;
    }

    public StackMaker shiny(){
        ItemUtils.makeShiny(stack);
        return this;
    }


    public ItemStack make(){
        return stack;
    }
}
