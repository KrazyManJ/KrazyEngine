package me.KrazyManJ.KrazyEngine.Spigot;

import com.google.common.io.ByteStreams;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

@SuppressWarnings({"unused", "UnstableApiUsage"})
public class SpigotSourceManager {

    @Deprecated private SpigotSourceManager() {}

    public static void copyFromSource(JavaPlugin plugin, String path, File file){
        InputStream in = plugin.getResource(path);
        OutputStream out;
        try {
            out = new FileOutputStream(file);
            assert in != null;
            ByteStreams.copy(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
