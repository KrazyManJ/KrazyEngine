package me.KrazyManJ.KrazyEngine.BungeeCord;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@SuppressWarnings({"unused", "ResultOfMethodCallIgnored"})
public final class BungeeConfig {
    private Configuration configuration;
    private final File file;
    private final Plugin plugin;
    private final String resourcePath;

    public BungeeConfig(Plugin plugin, String resourcePath, File file) {
        this.file = file;
        this.plugin = plugin;
        this.resourcePath = resourcePath;
        if (!file.exists()) {
            try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
            copyDefaults();
        }
        load();
    }
    public void copyDefaults(){
        BungeeSourceManager.copyFromSource(plugin, resourcePath, file);
    }
    public Configuration get(){ return configuration; }
    public void load(){
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void save(){
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration,file);
        } catch (IOException e) { e.printStackTrace(); }
    }
}
