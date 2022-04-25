package me.KrazyManJ.KrazyEngine.NMS;

import org.bukkit.Bukkit;

public final class NMSUtils {
    public static String getVersion(){
        return Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    }
}
