package me.KrazyManJ.KrazyEngine.BungeeCord;

import net.md_5.bungee.api.plugin.Plugin;

import java.io.*;
import java.nio.file.Files;

@SuppressWarnings({"unused"})
public final class BungeeSourceManager {

    @Deprecated private BungeeSourceManager() {}

    public static void copyFromSource(Plugin plugin, String path, File file){
        InputStream in = plugin.getResourceAsStream(path);
        OutputStream out;
        try {
            Files.copy(in,file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
