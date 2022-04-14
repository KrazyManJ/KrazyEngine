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
    public void sendRawMessage(String player, BaseComponent message) throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(),"MessageRaw",player,ComponentSerializer.toString(message));
    }
    public void kickPlayer(String player, String reason) throws NoOnlinePlayerException {
        sendPluginMessage(findExecutor(),"KickPlayer",player,reason);
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
}
