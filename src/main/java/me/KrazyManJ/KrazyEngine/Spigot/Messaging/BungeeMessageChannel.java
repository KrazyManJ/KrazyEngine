package me.KrazyManJ.KrazyEngine.Spigot.Messaging;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings({"unused", "UnusedReturnValue", "UnstableApiUsage"})
public final class BungeeMessageChannel {
    private final JavaPlugin plugin;

    public BungeeMessageChannel(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(plugin,"BungeeCord");
    }


    public void connect(Player player, String serverName){
        sendPluginMessage(player,"Connect",serverName);
    }
    public void connectOther(String player, String serverName) throws NoOnlinePlayerException{
        sendPluginMessage(findExecutor(),"ConnectOther",player,serverName);
    }
    public void sendMessage(String player, String message) throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(),"Message",player,message);
    }
    public void sendMessage(String player, BaseComponent message) throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(),"MessageRaw",player,ComponentSerializer.toString(message));
    }
    public void kickPlayer(String player, String reason) throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(),"KickPlayer",player,reason);
    }


    public void getPlayerIP(Player player){
        sendPluginMessage(player,"IP");
    }
    public void getOtherPlayerIP(String player) throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(),"IPOther",player);
    }
    public void getPlayerCount(String server) throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(),"PlayerCount",server);
    }
    public void getPlayerList(String server) throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(),"PlayerList",server);
    }
    public void getServers() throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(),"GetServers");
    }
    public void getServer() throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(),"GetServer");
    }
    public void getUUID(Player player) {
        sendPluginMessage(player,"UUID");
    }
    public void getUUIDOther(String player) throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(),"UUIDOther",player);
    }
    public void getServerIP(String server) throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(),"ServerIP",server);
    }

    public void forward(String channel, ForwardType type, Object ...data) throws NoOnlinePlayerException {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Forward");
        out.writeUTF(type.name().toUpperCase());
        out.writeUTF(channel);

        ByteArrayDataOutput datacol = ByteStreams.newDataOutput();
        for (Object prop : data) datacol.writeUTF(prop.toString());
        out.writeShort(datacol.toByteArray().length);
        out.write(datacol.toByteArray());
        findExecutor().sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }
    public void forward(String channel, String player, Object ...data) throws NoOnlinePlayerException {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ForwardToPlayer");
        out.writeUTF(player);
        out.writeUTF(channel);

        ByteArrayDataOutput datacol = ByteStreams.newDataOutput();
        for (Object prop : data) datacol.writeUTF(prop.toString());
        out.writeShort(datacol.toByteArray().length);
        out.write(datacol.toByteArray());
        findExecutor().sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

    @SuppressWarnings("UnstableApiUsage")
    private void sendPluginMessage(Player player, String subchannel, String ...arguments){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(subchannel);
        for (String argument : arguments) out.writeUTF(argument);
        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }
    private Player findExecutor() throws NoOnlinePlayerException {
        Player exec = Iterables.getFirst(Bukkit.getOnlinePlayers(),null);
        if (exec == null) throw new NoOnlinePlayerException();
        return exec;
    }

    private enum ForwardType{
        ALL,
        ONLINE
    }
}
