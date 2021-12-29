package de.polygondev.inventoryapi.inventory;

import de.polygondev.inventoryapi.InventoryApi;
import org.bukkit.Bukkit;
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

                //Todo: set itemstack back to where it was grabbed
                if (inv.isOpen() && !e.getReason().equals(InventoryCloseEvent.Reason.PLUGIN))
                {
                    inv.internCloseEvent(e);
                }
                else
                {
                    inv.internBukkitCloseEvent(e);
                }

                //reopens inventory if a player holds an item in cursor
                //gets current cursor item
                ItemStack is = e.getPlayer().getItemOnCursor();
                e.getPlayer().setItemOnCursor(null);

                //tests if the player inventory is unlocked and the cursor item is not air
                if (!is.getType().isAir() && inv.isAllowPlayerInventory()) {
                    //reopens the inventory with a delay of 3 ticks to prevent inventory errors
                    Bukkit.getScheduler().scheduleSyncDelayedTask(InventoryApi.PLUGIN, () -> {
                        inv.openInventory();
                        //sets the cursor item to the cursor item from the close event
                        e.getPlayer().setItemOnCursor(is);
                    }, 0L);
                }

            }
        }
    }

}
