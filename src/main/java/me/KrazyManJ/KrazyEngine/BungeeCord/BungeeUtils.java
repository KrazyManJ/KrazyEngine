package me.KrazyManJ.KrazyEngine.BungeeCord;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.function.Predicate;

public final class BungeeUtils {

    /**
     * Broadcast to all players, that have certain permission and meets requirements
     * @param permission permission that players need to have to see broadcast message
     * @param predicate
     * @param components
     */
    public static void broadcastByPermission(String permission, Predicate<ProxiedPlayer> predicate, BaseComponent... components){
        ProxyServer.getInstance().getPlayers().stream()
                .filter(p -> p.hasPermission(permission)).filter(predicate)
                .forEach(p -> p.sendMessage(components));
    }

    public static void broadcastByPermission(String permission, BaseComponent... components){
        broadcastByPermission(permission,(p) -> true,components);
    }

}
