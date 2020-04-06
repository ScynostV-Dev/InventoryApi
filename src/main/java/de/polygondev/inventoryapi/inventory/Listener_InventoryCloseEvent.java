package de.polygondev.inventoryapi.inventory;

import de.polygondev.inventoryapi.InventoryApi;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class Listener_InventoryCloseEvent implements Listener {

    @EventHandler
    public void inventoryCloseEvent(InventoryCloseEvent e) {
        if (e.getPlayer() instanceof Player) {
            Player p = (Player) e.getPlayer();

            Inventory inv = InventoryApi.INV_REGISTER.getOpenInventory(p);
            if (inv != null) {
                inv.internCloseEvent(e);
            }
        }
    }

}
