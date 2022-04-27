package me.KrazyManJ.KrazyEngine.Spigot.Item;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.KrazyManJ.KrazyEngine.Any.Regex.RegexList;
import me.KrazyManJ.KrazyEngine.NMS.NMSUtils;
import me.KrazyManJ.KrazyEngine.NMS.ReflectionHandler;
import me.KrazyManJ.KrazyEngine.Spigot.BukkitLog;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

@SuppressWarnings("unused")
public final class Skull {

    @Deprecated private Skull() {}

    private static Field profileField;

    static {
        try {
            profileField = ReflectionHandler.craftbukkit("inventory.CraftMetaSkull").getDeclaredField("profile");
            profileField.setAccessible(true);
        } catch (NoSuchFieldException | ClassNotFoundException e) { e.printStackTrace(); }
    }

    public static ItemStack fromValue(String base64, UUID uuid){
        if (!base64.matches(RegexList.base64.pattern()))
            try { throw new Exception(""); } catch (Exception e) { e.printStackTrace(); }
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(uuid, "");
        profile.getProperties().put("textures", new Property("textures", base64));
        try {
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
            e.printStackTrace();
        }
        head.setItemMeta(meta);
        return head;
    }
    public static ItemStack fromValue(String base64){
        return fromValue(base64,UUID.randomUUID());
    }

    public static ItemStack ofPlayer(OfflinePlayer player){
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        assert meta != null;
        meta.setOwningPlayer(player);
        head.setItemMeta(meta);
        return head;
    }

    @Deprecated
    public static ItemStack byName(String name){
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        assert meta != null;
        meta.setOwner(name);
        head.setItemMeta(meta);
        return head;
    }
}
