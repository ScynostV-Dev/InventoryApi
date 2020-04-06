package de.polygondev.inventoryapi.inventory;

import de.polygondev.inventoryapi.InventoryApi;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Listener_InventoryClickEvent implements Listener {

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player p = (Player) e.getWhoClicked();

            Inventory inv = InventoryApi.INV_REGISTER.getOpenInventory(p);
            if (inv != null) {
                e.setCancelled(true);

                if (e.getClick() != ClickType.DOUBLE_CLICK && e.getClick() != ClickType.SHIFT_LEFT) {

                    Executor x;
                    if ((x = inv.getExecutor(e.getSlot())) != null) {
                        x.exec(p, e.getClick());
                    }

                }

                inv.clickEvent(e);
            }


        }

    }

}


