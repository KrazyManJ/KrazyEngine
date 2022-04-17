package me.KrazyManJ.KrazyEngine.Spigot.GUI;

import org.bukkit.event.inventory.InventoryCloseEvent;

@FunctionalInterface
public interface GUIClose {
    void close(InventoryCloseEvent closeEvent);
}
