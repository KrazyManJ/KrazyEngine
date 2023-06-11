package me.KrazyManJ.KrazyEngine.Spigot.Messaging;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.UUID;

@SuppressWarnings({"unused", "UnusedReturnValue", "UnstableApiUsage"})
public final class BungeeMessageChannel {
    private final JavaPlugin plugin;
    private final BungeeResponseListener listener;

    public BungeeMessageChannel(JavaPlugin plugin) {
        this.plugin = plugin;
        this.listener = null;
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
    }

    public BungeeMessageChannel(JavaPlugin plugin, BungeeResponseListener responseHandler) {
        this.plugin = plugin;
        this.listener = responseHandler;
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(plugin, "BungeeCord", (channel, player, bytes) -> {
            if (!channel.equals("BungeeCord")) return;
            ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
            String subchannel = in.readUTF();
            switch (subchannel) {
                case "IP" -> listener.playerIPResponse(player, in.readUTF(), in.readInt());
                case "IPOther" -> listener.playerIPOtherResponse(player, in.readUTF(), in.readUTF(), in.readInt());
                case "PlayerCount" -> listener.playerCountResponse(player, in.readUTF(), in.readInt());
                case "PlayerList" ->
                        listener.playerListResponse(player, in.readUTF(), List.of(in.readUTF().split(", ")));
                case "GetServers" -> listener.serverListResponse(player, List.of(in.readUTF().split(", ")));
                case "GetServer" -> listener.currentServerResponse(player, in.readUTF());
                case "UUID" -> listener.uuidResponse(player, UUID.fromString(in.readUTF()));
                case "UUIDOther" -> listener.uuidOtherResponse(player, in.readUTF(), UUID.fromString(in.readUTF()));
                case "ServerIP" -> listener.serverIPResponse(player, in.readUTF(), in.readUTF(), in.readInt());
                case "Forward", "ForwardToPlayer" -> {
                    int len = in.readInt();
                    byte[] msg = new byte[len];
                    in.readFully(msg);
                    listener.forwardResponse(player, in.readUTF(), in.readInt(), ByteStreams.newDataInput(msg));
                }
            }
        });
    }


    public void connect(Player player, String serverName) {
        sendPluginMessage(player, "Connect", serverName);
    }

    public void connectOther(String player, String serverName) throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(), "ConnectOther", player, serverName);
    }

    public void sendMessage(String player, String message) throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(), "Message", player, message);
    }

    public void sendMessage(String player, BaseComponent message) throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(), "MessageRaw", player, ComponentSerializer.toString(message));
    }

    public void kickPlayer(String player, String reason) throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(), "KickPlayer", player, reason);
    }


    public void getPlayerIP(Player player) {
        sendPluginMessage(player, "IP");
    }

    public void getOtherPlayerIP(String player) throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(), "IPOther", player);
    }

    public void getPlayerCount(String server) throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(), "PlayerCount", server);
    }

    public void getPlayerList(String server) throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(), "PlayerList", server);
    }

    public void getServers() throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(), "GetServers");
    }

    public void getServer() throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(), "GetServer");
    }

    public void getUUID(Player player) {
        sendPluginMessage(player, "UUID");
    }

    public void getUUIDOther(String player) throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(), "UUIDOther", player);
    }

    public void getServerIP(String server) throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(), "ServerIP", server);
    }

    public void forward(String channel, ForwardType type, ByteArrayDataOutput data) throws NoOnlinePlayerException {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Forward");
        out.writeUTF(type.name().toUpperCase());
        out.writeUTF(channel);

        out.writeShort(data.toByteArray().length);
        out.write(data.toByteArray());
        findExecutor().sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

    public void forwardToPlayer(String channel, String player, ByteArrayDataOutput data) throws NoOnlinePlayerException {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ForwardToPlayer");
        out.writeUTF(player);
        out.writeUTF(channel);

        out.writeShort(data.toByteArray().length);
        out.write(data.toByteArray());
        findExecutor().sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

    public boolean hasResponseHandler() {
        return listener != null;
    }

    @SuppressWarnings("UnstableApiUsage")
    private void sendPluginMessage(Player player, String subchannel, String... arguments) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(subchannel);
        for (String argument : arguments) out.writeUTF(argument);
        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

    private Player findExecutor() throws NoOnlinePlayerException {
        Player exec = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        if (exec == null) throw new NoOnlinePlayerException();
        return exec;
    }

    static final class NoOnlinePlayerException extends Exception {
        public NoOnlinePlayerException() {
            super("There is no player online to execute this action!");
        }
    }

    @SuppressWarnings("unused")
    public enum ForwardType {
        ALL,
        ONLINE
    }
}
