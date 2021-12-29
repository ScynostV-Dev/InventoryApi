package de.polygondev.inventoryapi.inventory;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryRegistry {

    private HashMap<Player, ArrayList<Inventory>> InvPool = new HashMap<>();

    public void addInventoryToPlayer(Player player, Inventory inventory) {
        inventory.setPlayer(player);

        if (InvPool.containsKey(player)) {
            ArrayList<Inventory> invlist = InvPool.get(player);
            if (checkInventoryFromPlayerExisting(player, inventory) != (-1)) return;
            invlist.add(inventory);
            InvPool.put(player, invlist);
        } else {
            ArrayList<Inventory> invlist = new ArrayList<>();
            invlist.add(inventory);
            InvPool.put(player, invlist);
        }

    }

    public void removeInventoryFromPlayer(Player player, String name) {

        int x = checkInventoryFromPlayerExisting(player, name);
        if (x != (-1)) {
            ArrayList<Inventory> invlist = InvPool.get(player);
            invlist.remove(x);
        }

    }

    /**
     * Its the same as:
     * {@link InventoryRegistry#checkInventoryFromPlayerExisting(Player, String) See Here}<br>
     * Just with the change that you can give the inventory and it will get the name from it
     * @param player {@link Player}
     * @param inventory {@link Inventory}
     * @return
     */
    public int checkInventoryFromPlayerExisting(Player player, Inventory inventory) {
        return checkInventoryFromPlayerExisting(player, inventory.getName());
    }

    /**
     *
     * @param player {@link Player}
     * @param name !!!CASE SENSITIVE!!!
     * @return (-1) if nothing is found <br> returns number of the ArrayList index the item is at
     */
    public int checkInventoryFromPlayerExisting(Player player, String name) {

        if (InvPool.containsKey(player)) {
            ArrayList<Inventory> invlist = InvPool.get(player);
            for (int i = 0; i < invlist.size(); i++) {
                Inventory inv = invlist.get(i);
                if (inv.getName().equals(name)) {
                    return i;
                }
            }
        }
        return (-1);

    }

    public Inventory getOpenInventory(Player player) {

        if (InvPool.containsKey(player)) {
            for (Inventory inv : InvPool.get(player)) {
                if (inv.isOpen()) {
                    return inv;
                }
            }
        }
        return null;
    }

    public ArrayList<Inventory> getAllInventoriesFromPlayer(Player player) {
        if (InvPool.containsKey(player)) {
            return InvPool.get(player);
        }
        return null;
    }

    public Inventory getInventoryFromPlayer(Player player, String name) {

        if (InvPool.containsKey(player)) {
            ArrayList<Inventory> invlist = InvPool.get(player);
            for (Inventory inv : invlist) {
                if (inv.getName().equalsIgnoreCase(name)) {
                    return inv;
                }
            }
        }
        return null;

    }
}
