package me.KrazyManJ.KrazyEngine.Spigot.PacketReflection;

import me.KrazyManJ.KrazyEngine.Spigot.BukkitLog;
import me.KrazyManJ.KrazyEngine.Spigot.BukkitUtils;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class PacketUtils {

    @Deprecated private PacketUtils() {}

    private static Class<?> craftPlayer;
    private static Method getHandle;
    private static Field playerConnection;
    private static Method sendPacket;

    static {
        try {
            if ("v1_17_R1".equals(BukkitUtils.getVersion())) {
                craftPlayer = Class.forName("org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer");
                getHandle = craftPlayer.getDeclaredMethod("getHandle");
                playerConnection = Class.forName("net.minecraft.server.level.EntityPlayer").getField("b");
                Class<?> packet = Class.forName("net.minecraft.network.protocol.Packet");
                sendPacket = Class.forName("net.minecraft.server.network.PlayerConnection").getDeclaredMethod("sendPacket", packet);
            } else {
                BukkitLog.error("This version is not supported!");
            }
        }
        catch (ClassNotFoundException | NoSuchMethodException | NoSuchFieldException e) {
            e.printStackTrace();
        }

    }


    public static void sendPacket(Player player, Object packet){
        try {
            sendPacket.invoke(playerConnection.get(getHandle.invoke(craftPlayer.cast(player))),packet);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
