package me.KrazyManJ.KrazyEngine.Spigot;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.file.Files;

@SuppressWarnings({"unused"})
public final class SpigotSourceManager {

    @Deprecated private SpigotSourceManager() {}

    public static void copyFromSource(JavaPlugin plugin, String path, File file){
        InputStream in = plugin.getResource(path);
        OutputStream out;
        try {
            assert in != null;
            Files.copy(in,file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
