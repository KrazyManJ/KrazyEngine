package me.KrazyManJ.KrazyEngine.Spigot.Item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings("UnusedReturnValue")
public final class ItemUtils {
    public static ItemStack makeShiny(ItemStack item) {
        if (!item.getType().equals(Material.BOW)) item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE,1);
        else item.addUnsafeEnchantment(Enchantment.DIG_SPEED,1);
        ItemMeta mt = item.getItemMeta();
        assert mt != null;
        mt.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(mt);
        return item;
    }
}
