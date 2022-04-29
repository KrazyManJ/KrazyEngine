package me.KrazyManJ.KrazyEngine.Spigot.Messaging;

import com.google.common.io.ByteArrayDataInput;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
public interface BungeeResponseListener {
    default void playerIPResponse(Player p,String ip, int port){}
    default void playerIPOtherResponse(Player p,String name, String ip, int port){}
    default void playerCountResponse(Player p,String server, int count){}
    default void playerListResponse(Player p,String server, List<String> players){}
    default void serverListResponse(Player p,List<String> serverNameList){}
    default void currentServerResponse(Player p,String server){}
    default void uuidResponse(Player p,UUID uuid){}
    default void uuidOtherResponse(Player p,String playerName, UUID uuid){}
    default void serverIPResponse(Player p,String serverName, String ip, int port){}
    default void forwardResponse(Player p,String channel, int length, ByteArrayDataInput data){}
}
