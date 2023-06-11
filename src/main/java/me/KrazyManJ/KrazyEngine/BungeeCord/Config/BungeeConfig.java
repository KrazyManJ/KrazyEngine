package me.KrazyManJ.KrazyEngine.BungeeCord.Config;

import me.KrazyManJ.KrazyEngine.BungeeCord.BungeeSourceManager;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;

/**
 * Represents a wrapper that combines {@link File} and {@link Configuration} specialy for Bungeecord
 * <p>
 *     This class also gets original config from resource specified in resourcePath
 * </p>
 */
@SuppressWarnings({"unused", "ResultOfMethodCallIgnored"})
public final class BungeeConfig {
    private Configuration configuration;
    private final File file;
    private final Plugin plugin;
    private final String resourcePath;

    public static BungeeConfig defaultConfig(Plugin plugin){
        return new BungeeConfig(plugin,"config.yml",new File(plugin.getDataFolder(),"config.yml"));
    }

    public BungeeConfig(Plugin plugin, String resourcePath, File file, boolean init){
        this.file = file;
        this.plugin = plugin;
        this.resourcePath = resourcePath;
        if (init){
            if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
            copyDefaults();
            load();
        }
    }

    public BungeeConfig(Plugin plugin, String resourcePath, File file) {
        this.file = file;
        this.plugin = plugin;
        this.resourcePath = resourcePath;
        new BungeeConfig(plugin, resourcePath, file, true);
    }

    public void copyDefaults() {
        BungeeSourceManager.copyFromSource(plugin, resourcePath, file, StandardCopyOption.REPLACE_EXISTING);
    }

    public Configuration get() {
        return configuration;
    }

    public <T> T getKeyValue(BungeeConfigKey<T> key){
        return key.getFrom(get());
    }

    public <T> T getKeyValue(BungeeConfigKey<T> key, T defaultVal){
        return key.getFrom(get(), defaultVal);
    }

    public void load() {
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
