package me.KrazyManJ.KrazyEngine.Spigot.Item;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.KrazyManJ.KrazyEngine.Any.Regex.RegexConstants;
import me.KrazyManJ.KrazyEngine.Core.ReflectionHandler;
import me.KrazyManJ.KrazyEngine.Core.ReflectionUsed;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

/**
 * Class to create skulls (player heads) from base64 string
 *
 * @author KrazyManJ
 */
@SuppressWarnings("unused")
@ReflectionUsed
public final class Skull {

    private Skull() {

    }

    private static Field profileField;

    static {
        try {
            profileField = ReflectionHandler.makeFieldAccessible(
                    ReflectionHandler.craftbukkitClass("inventory.CraftMetaSkull").getDeclaredField("profile"));
        } catch (NoSuchFieldException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param base64 Base64 string value
     * @param uuid   UUID of player
     * @return ItemStack of skull (player head)
     * @author KrazyManJ
     */
    public static ItemStack fromValue(String base64, UUID uuid) {
        if (!base64.matches(RegexConstants.base64.pattern()))
            throw new IllegalArgumentException("Argument is not base64 string");
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        if (profileField == null) return head;

        SkullMeta meta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(uuid, "");
        profile.getProperties().put("textures", new Property("textures", base64));
        ReflectionHandler.setField(profileField, meta, profile);
        head.setItemMeta(meta);
        return head;
    }

    /**
     * Gets player head from base64 value
     * Same as {@link #fromValue(String, UUID)} but it doesn't need to provide {@link UUID}
     *
     * <p><b>
     * Use this method only if you don't care, that generated player heads by this method
     * cannot stack!
     * </b></p>
     *
     * @param base64 Base64 string value
     * @return ItemStack of skull (player head)
     * @author KrazyManJ
     */
    public static ItemStack fromValue(String base64) {
        return fromValue(base64, UUID.randomUUID());
    }

    /**
     * Gets player head from offline player's instance
     *
     * @param player offline player to get skull of
     * @return ItemStack of skull (player head)
     * @author KrazyManJ
     */
    public static ItemStack ofPlayer(OfflinePlayer player) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        assert meta != null;
        meta.setOwningPlayer(player);
        head.setItemMeta(meta);
        return head;
    }

    /**
     * Gets player head from player's name
     *
     * @param name Name of player
     * @return ItemStack of skull (player head)
     * @author KrazyManJ
     * @deprecated
     */
    @Deprecated
    public static ItemStack byName(String name) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        assert meta != null;
        meta.setOwner(name);
        head.setItemMeta(meta);
        return head;
    }
}
