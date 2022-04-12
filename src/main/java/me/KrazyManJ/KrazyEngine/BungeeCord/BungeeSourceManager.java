package me.KrazyManJ.KrazyEngine.BungeeCord;

import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.*;

@SuppressWarnings({"unused", "UnstableApiUsage"})
public final class BungeeSourceManager {

    @Deprecated private BungeeSourceManager() {}

    public static void copyFromSource(Plugin plugin, String path, File file){
        InputStream in = plugin.getResourceAsStream(path);
        OutputStream out;
        try {
            out = new FileOutputStream(file);
            ByteStreams.copy(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
