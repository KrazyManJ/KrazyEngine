package me.KrazyManJ.KrazyEngine.NMS.PacketSender;

import me.KrazyManJ.KrazyEngine.NMS.PacketUtils;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("unused")
public final class PacketBlockBreakAnim {

    private static Constructor<?> blockBreakAnimPacket;

    static {
        try {
            blockBreakAnimPacket = Class.forName("net.minecraft.network.protocol.game.PacketPlayOutBlockBreakAnimation")
                    .getConstructor(int.class, Class.forName("net.minecraft.core.BlockPosition") ,int.class);
        } catch (ClassNotFoundException | NoSuchMethodException e) { e.printStackTrace(); }
    }

    public static void blockUnique(Player packetReceiver, Block block, int stage){
        sendAnim(packetReceiver,getBlockEntityId(block),block,stage);
    }
    public static void playerOwned(Player packetReceiver, Player animationOwner, Block block, int stage){
        sendAnim(packetReceiver,animationOwner.getEntityId(),block,stage);
    }
    public static void entityOwned(Player packetReceiver, Entity animationOwner, Block block, int stage){
        sendAnim(packetReceiver, animationOwner.getEntityId(), block, stage);
    }

    private static int getBlockEntityId(Block block) {
        return ((block.getX() & 0xFFF) << 20) | ((block.getZ() & 0xFFF) << 8) | (block.getY() & 0xFF);
    }

    private static void sendAnim(Player pR, int eId, Block b, int stg){
        try {
            PacketUtils.sendPacket(pR, blockBreakAnimPacket.newInstance(eId,PacketUtils.blockPosition(b),stg));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
