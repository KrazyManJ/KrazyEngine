package me.KrazyManJ.KrazyEngine.Spigot.Inventory;

import org.bukkit.event.inventory.InventoryCloseEvent;

@FunctionalInterface
public interface GUIClose {
    void close(InventoryCloseEvent closeEvent);
}
