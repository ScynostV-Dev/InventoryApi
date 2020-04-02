package de.polygondev.inventoryapi.inventory;

import de.polygondev.inventoryapi.Inventoryapi;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class Listener_InventoryCloseEvent implements Listener {

    @EventHandler
    public void inventoryCloseEvent(InventoryCloseEvent e) {
        if (e.getPlayer() instanceof Player) {
            Player p = (Player) e.getPlayer();

            try {
                Inventory inv = Inventoryapi.INV_REGISTER.getViewedInventory(p);
                inv.internCloseEvent(e);
            } catch (NullPointerException e1) {}
        }
    }

}
