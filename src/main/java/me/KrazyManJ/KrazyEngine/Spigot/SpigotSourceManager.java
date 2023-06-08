package me.KrazyManJ.KrazyEngine.Spigot;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;

@SuppressWarnings({"unused"})
public final class SpigotSourceManager {

    private SpigotSourceManager() {
    }

    public static void copyFromSource(JavaPlugin plugin, String path, File file, CopyOption... options) {
        try (InputStream in = plugin.getResource(path)) {
            assert in != null;
            Files.copy(in, file.toPath(), options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
