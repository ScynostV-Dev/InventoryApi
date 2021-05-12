package de.polygondev.inventoryapi.inventory;

import de.polygondev.inventoryapi.InventoryApi;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Listener_InventoryClickEvent implements Listener
{

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent e)
    {
        e.setCancelled(true);
        if (e.getWhoClicked() instanceof Player)
        {
            Player p = (Player) e.getWhoClicked();

            Inventory inv = InventoryApi.INV_REGISTER.getOpenInventory(p);
            if (inv != null)
            {
                /*if (e.getSlot() < 0) {
                    e.setCancelled(true);
                    return;
                }*/

                if (inv.isAllowPlayerInventory() && e.getClickedInventory() != p.getOpenInventory().getTopInventory()) {
                    e.setCancelled(false);
                }

                if (!inv.isSlotProtected(e.getSlot()) 
                {
                    e.setCancelled(false);
                }

                Executor x;
                if (inv.isSlotProtected(e.getSlot()) | ((x = inv.getExecutor(e.getSlot())) != null))
                {
                    e.setCancelled(true);
                    if (e.getClick() != ClickType.DOUBLE_CLICK && e.getClick() != ClickType.SHIFT_LEFT)
                    {
                        if (x != null)
                        {
                            x.exec(p, e.getClick());
                        }
                    }
                }

                inv.clickEvent(e);
            }



        }


    }

}


