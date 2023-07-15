package me.KrazyManJ.KrazyEngine.Spigot.File;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.StandardCopyOption;

@SuppressWarnings("unused")
public final class SpigotConfig {
    private FileConfiguration configuration;
    private final File file;
    private final JavaPlugin plugin;
    private final String resourcePath;

    public SpigotConfig(File file, JavaPlugin plugin, String resourcePath) {
        this.file = file;
        this.plugin = plugin;
        this.resourcePath = resourcePath;

        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        if (!file.exists()) copyDefaults();
        load();
    }

    public void copyDefaults() {
        SpigotSourceManager.copyFromSource(plugin, resourcePath, file, StandardCopyOption.REPLACE_EXISTING);
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    public void load() {
        configuration = YamlConfiguration.loadConfiguration(file);
        InputStream defaults = plugin.getResource(resourcePath);
        if (defaults != null)
            this.configuration.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defaults)));
    }

    public void save() {
        try {
            configuration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
