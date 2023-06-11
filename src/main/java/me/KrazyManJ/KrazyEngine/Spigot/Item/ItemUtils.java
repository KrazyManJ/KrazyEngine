package me.KrazyManJ.KrazyEngine.Spigot.Item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public final class ItemUtils {

    private ItemUtils() {
    }

    public static final NamespacedKey unstackableKey = NamespacedKey.minecraft("unstackable");

    public static void makeShiny(ItemStack item) {
        if (!item.getType().equals(Material.BOW)) item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
        else item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        ItemMeta mt = item.getItemMeta();
        assert mt != null;
        mt.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(mt);
    }

    public static void makeUnstackable(ItemStack item) {
        ItemMeta m = item.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().set(unstackableKey, PersistentDataType.LONG,
                Bukkit.getServer().getWorlds().get(0).getGameTime());
        item.setItemMeta(m);
    }
}
