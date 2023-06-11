package me.KrazyManJ.KrazyEngine.BungeeCord.Log;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.logging.Level;

@SuppressWarnings("unused")
public final class BungeeLog {

    @Deprecated private BungeeLog() {}

    public static void info(String message){ ProxyServer.getInstance().getLogger().log(Level.INFO,message); }
    public static void info(Plugin plugin, String message){ plugin.getLogger().log(Level.INFO,message); }

    public static void warn(String message){ ProxyServer.getInstance().getLogger().log(Level.WARNING,message); }
    public static void warn(Plugin plugin, String message){ plugin.getLogger().log(Level.WARNING,message); }

    public static void error(String message){ ProxyServer.getInstance().getLogger().log(Level.SEVERE,message); }
    public static void error(Plugin plugin, String message){ plugin.getLogger().log(Level.SEVERE,message); }
}
