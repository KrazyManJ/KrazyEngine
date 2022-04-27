package me.KrazyManJ.KrazyEngine.Spigot.Messaging;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
public interface BungeeResponseListener {
    default void playerIPResponse(String ip, int port){}
    default void playerIPOtherResponse(String name, String ip, int port){}
    default void playerCountResponse(String server, int count){}
    default void playerListResponse(String server, List<String> players){}
    default void serverListResponse(List<String> serverNameList){}
    default void currentServerResponse(String server){}
    default void uuidResponse(UUID uuid){}
    default void uuidOtherResponse(String playerName, UUID uuid){}
    default void serverIPResponse(String serverName, String ip, int port){}
}
