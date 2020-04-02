package de.polygondev.inventoryapi.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public interface Executor {
    void exec(Player player, ClickType clickType);
}
