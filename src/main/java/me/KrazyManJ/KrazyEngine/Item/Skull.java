package me.KrazyManJ.KrazyEngine.Item;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
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
            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            assert meta != null;
            profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
        } catch (NoSuchFieldException e) { e.printStackTrace(); }
    }

    public static ItemStack fromValue(String base64){
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        profile.getProperties().put("textures", new Property("textures", base64));
        try {
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
            e.printStackTrace();
        }
        head.setItemMeta(meta);
        return head;
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
