package de.polygondev.inventoryapi.inventory;

import de.polygondev.inventoryapi.InventoryApi;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class Listener_InventoryCloseEvent implements Listener {

    @EventHandler
    public void inventoryCloseEvent(InventoryCloseEvent e) {
        if (e.getPlayer() instanceof Player)
        {
            Player p = (Player) e.getPlayer();

            Inventory inv = InventoryApi.INV_REGISTER.getOpenInventory(p);
            if (inv != null)
            {
                ItemStack is = e.getPlayer().getItemOnCursor();
                e.getPlayer().setItemOnCursor(null);

                if (!is.getType().isAir()) {
                    inv.internBukkitCloseEvent(e);
                    inv.openInventory();
                    inv.updateInventory();

                    //e.getPlayer().setItemOnCursor(is);
                    return;
                }

                //Todo: set itemstack back to where it was grabbed
                if (inv.isOpen() && !e.getReason().equals(InventoryCloseEvent.Reason.PLUGIN))
                {
                    inv.internCloseEvent(e);
                }
                else
                {
                    inv.internBukkitCloseEvent(e);
                }
            }
        }
    }

}
