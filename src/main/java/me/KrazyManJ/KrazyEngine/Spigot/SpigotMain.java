package me.KrazyManJ.KrazyEngine.Spigot;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public final class SpigotMain extends JavaPlugin {
    @Override
    public void onEnable() {super.onEnable();}

    @Override
    public void onDisable() {super.onDisable();}

    public static String getVersion(){
        return Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    }
}
