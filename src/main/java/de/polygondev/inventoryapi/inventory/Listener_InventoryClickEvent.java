package de.polygondev.inventoryapi.inventory;

import de.polygondev.inventoryapi.InventoryApi;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Listener_InventoryClickEvent implements Listener
{

    @EventHandler
    public void inventoryDragEvent(InventoryDragEvent e) {
        if (e.getWhoClicked() instanceof Player)
        {
            Player p = (Player) e.getWhoClicked();

            Inventory inv = InventoryApi.INV_REGISTER.getOpenInventory(p);
            if (inv != null)
            {
                e.setCancelled(true);
                e.setResult(Event.Result.DENY);
            }
        }
    }

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent e)
    {
        System.out.println("irgendwas passiert....");
        if (e.getWhoClicked() instanceof Player)
        {
            Player p = (Player) e.getWhoClicked();
            p.sendMessage("wurde geklickt");

            Inventory inv = InventoryApi.INV_REGISTER.getOpenInventory(p);
            p.sendMessage("inv: " + inv);
            if (inv != null)
            {
                p.sendMessage("bin drinne");
                e.setCancelled(true);
                e.setResult(Event.Result.DENY);

                InventoryAction ac = e.getAction();

                if (e.getSlot() < 0) {
                    e.setCancelled(true);
                    e.setResult(Event.Result.DENY);
                    return;
                }

                boolean cancel = true;

                if (ac.equals(InventoryAction.PICKUP_ALL)) cancel = false;
                if (ac.equals(InventoryAction.PLACE_ALL)) cancel = false;

                if (inv.isAllowPlayerInventory())
                {
                    if (ac.equals(InventoryAction.COLLECT_TO_CURSOR)) cancel = false;
                    if (e.getClickedInventory() == p.getOpenInventory().getBottomInventory()) cancel = false;
                    if (ac.equals(InventoryAction.SWAP_WITH_CURSOR)) cancel = false;
                    if (ac.equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)) cancel = true;
                }
                else
                {
                    if (e.getClickedInventory() != p.getOpenInventory().getTopInventory()) cancel = true;
                }
                if (e.getClickedInventory() == p.getOpenInventory().getTopInventory())
                {
                    if (inv.isSlotProtected(e.getSlot()) || inv.getExecutor(e.getSlot()) != null) cancel = true;
                }
                if (ac.equals(InventoryAction.DROP_ALL_CURSOR)) cancel = true;
                e.setCancelled(cancel);
                if (cancel) {
                    e.setResult(Event.Result.DENY);
                };

                Executor x = null;
                if ((((x = inv.getExecutor(e.getSlot())) != null || inv.isSlotProtected(e.getSlot()))) && e.getClickedInventory() == p.getOpenInventory().getTopInventory())
                {
                    if (ac.equals(InventoryAction.SWAP_WITH_CURSOR)) {
                        e.setCancelled(true);
                        e.setResult(Event.Result.DENY);
                        return;
                    }

                    if (e.getClick() != ClickType.DOUBLE_CLICK && e.getClick() != ClickType.SHIFT_LEFT)
                    {
                        if (x != null)
                        {
                            e.setCancelled(true);
                            e.setResult(Event.Result.DENY);
                            x.exec(p, e.getClick());
                        }
                    }
                    e.setResult(Event.Result.DENY);
                }

                inv.clickEvent(e);
            }


        }


    }

}


