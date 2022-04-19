package me.KrazyManJ.KrazyEngine.Spigot.GUI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public final class GUI {

    private final JavaPlugin plugin;
    private final Inventory inv;
    private GUIClick click;
    private Listener clickH;
    private GUIClose close;
    private Listener closeH;
    private boolean uncloseable;

    public GUI(JavaPlugin plugin,int rows,String title) {
        inv = Bukkit.createInventory(null,rows*9,title);
        this.plugin = plugin;
    }
    public Inventory getInventory(){
        return inv;
    }
    public void setUncloseable(boolean uncloseable){
        this.uncloseable = uncloseable;
    }
    public void clickEvent(@NotNull GUIClick clickEvent){
        click = clickEvent;
        clickH = new ClickHandler();
        Bukkit.getPluginManager().registerEvents(clickH , plugin);
    }
    public void closeEvent(@NotNull GUIClose closeEvent){
        close = closeEvent;
        closeH = new CloseHandler();
        Bukkit.getPluginManager().registerEvents(closeH , plugin);
    }
    public void unregisterClickEvent(){
        if (clickH == null) return;
        HandlerList.unregisterAll(clickH);
    }
    public void unregisterCloseEvent(){
        if (closeH == null) return;
        HandlerList.unregisterAll(closeH);
    }
    public void open(@NotNull Player p){
        p.openInventory(inv);
    }

    private final class ClickHandler implements Listener {
        @EventHandler
        public void on(@NotNull InventoryClickEvent event) {
            if (event.getClickedInventory() == null) return;
            if (!event.getClickedInventory().equals(inv)) return;
            click.click(event);
        }
    }
    private final class CloseHandler implements Listener {
        @EventHandler
        public void on(@NotNull InventoryCloseEvent event) {
            if (!event.getInventory().equals(inv)) return;
            if (uncloseable) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        open((Player) event.getPlayer());
                    }
                }.runTaskLater(plugin,1);
                return;
            }

            close.close(event);
            unregisterCloseEvent();
            unregisterClickEvent();
        }
    }
}
