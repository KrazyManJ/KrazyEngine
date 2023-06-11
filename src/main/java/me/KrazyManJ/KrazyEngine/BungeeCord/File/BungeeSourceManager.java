package me.KrazyManJ.KrazyEngine.BungeeCord.File;

import net.md_5.bungee.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;

/**
 * Manages unpacking of resources from proxy server plugin
 */
@SuppressWarnings({"unused"})
public final class BungeeSourceManager {

    private BungeeSourceManager() {
    }

    /**
     * Copy file from source of proxy plugin
     * @param plugin proxy plugin
     * @param resourcePath path to resource in jar package
     * @param file output file destination
     * @param options copy options in use for copy task
     */
    public static void copyFromSource(@NotNull Plugin plugin, String resourcePath, @NotNull File file, CopyOption... options) {
        try (InputStream in = plugin.getResourceAsStream(resourcePath)) {
            Files.copy(in, file.toPath(), options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
