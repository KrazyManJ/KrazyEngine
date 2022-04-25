package me.KrazyManJ.KrazyEngine.NMS;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.network.chat.IChatBaseComponent;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class PacketUtils {

    @Deprecated private PacketUtils() {}

    //Packet sending
    private static Class<?> craftPlayer;
    private static Method getHandle;
    private static Field playerConnection;
    private static Method sendPacket;

    //BlockPosition
    private static Constructor<?> blockPosition;

    private static Method fromJson;

    static {
        try {
            craftPlayer = Class.forName("org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer");
            getHandle = craftPlayer.getDeclaredMethod("getHandle");
            playerConnection = Class.forName("net.minecraft.server.level.EntityPlayer").getField("b");
            sendPacket = Class.forName("net.minecraft.server.network.PlayerConnection").getDeclaredMethod("sendPacket",
                    Class.forName("net.minecraft.network.protocol.Packet")
            );

            blockPosition = Class.forName("net.minecraft.core.BlockPosition").getConstructor(int.class,int.class,int.class);

            //IChatBaseComponent
            Class<?> iChatBaseComponent = Class.forName("net.minecraft.network.chat.IChatBaseComponent");
            fromJson = Class.forName("net.minecraft.network.chat.IChatBaseComponent$ChatSerializer").getDeclaredMethod("a",String.class);

        } catch (ClassNotFoundException | NoSuchMethodException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void sendPacket(Player p, Object packet){
        try {
            sendPacket.invoke(playerConnection.get(getHandle.invoke(craftPlayer.cast(p))),packet);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public static @Nullable Object blockPosition(Block b){
        try {
            return blockPosition.newInstance(b.getX(),b.getY(),b.getZ());
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Object iChatBaseComponent(BaseComponent[] components){
        try {
            return fromJson.invoke(null, ComponentSerializer.toString(components));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}
