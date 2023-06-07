package me.KrazyManJ.KrazyEngine.BungeeCord;

import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;

@SuppressWarnings({"unused"})
public final class BungeeSourceManager {

    @Deprecated
    private BungeeSourceManager() {
    }

    public static void copyFromSource(Plugin plugin, String path, File file, CopyOption... options) {
        InputStream in = plugin.getResourceAsStream(path);
        OutputStream out;
        try {
            Files.copy(in, file.toPath(), options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
