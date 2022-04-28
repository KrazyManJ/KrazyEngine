package me.KrazyManJ.KrazyEngine;


import me.KrazyManJ.KrazyEngine.Spigot.CommandMapRegistry;
import me.KrazyManJ.KrazyEngine.Spigot.Item.StackMaker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public final class SpigotMain extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        CommandMapRegistry.register(new Command("skullify") {
            @Override
            public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                if (!(sender instanceof Player p)) return false;
                p.getInventory().addItem(StackMaker.fromSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTRlYTBkYWIwYjdmMWU1YzRlMzNhYmE2ZTk3NWU5OTkyYTg4N2Q4NzJkYzdkZDEwN2Q4ZTM5ODFkYzg3Mzk1OCJ9fX0=").make());
                CommandMapRegistry.unregister(this);
                return true;
            }
        });
    }

    @Override
    public void onDisable() {super.onDisable();}
}
