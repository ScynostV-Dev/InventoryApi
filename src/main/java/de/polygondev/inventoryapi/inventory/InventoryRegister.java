package de.polygondev.inventoryapi.inventory;

import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.HashMap;

public class InventoryRegister {

    private HashMap<String, Inventory> REGISTER = new HashMap<String, Inventory>();
    private HashMap<Player, String> REGPLAYER = new HashMap<Player, String>();

    /**
     *
     * @param inv
     */
    public void addInventory(Inventory inv) {
        REGISTER.put(inv.name, inv);
    }

    /**
     *
     * @param player
     * @param name
     */
    public void addViewer(Player player, String name){
        REGPLAYER.put(player, name);
    }

    /**
     *
     * @param player
     * @return
     */
    public boolean removeViewer(Player player){
        if(REGPLAYER.containsKey(player)) {
            REGPLAYER.remove(player);
            return true;
        }
        return false;
    }

    /**
     *
     * @param name
     * @return
     */
    public Player[] getViewers(String name) {
        ArrayList<Player> cplist = new ArrayList<>();
        for (HashMap.Entry<Player, String> entry: REGPLAYER.entrySet()) {
            if (entry.getValue().equals(name)){
                cplist.add(entry.getKey());
            }
        }
        Player[] cplist1 = new Player[cplist.size()];
        return cplist.toArray(cplist1);
    }

    /**
     *
     * @param player
     * @return
     */
    public Inventory getViewedInventory(Player player){
        String name = REGPLAYER.get(player);
        if(name != null)
            return REGISTER.get(name);
        return null;
    }

    /**
     *
     * @param name
     * @return
     */
    public Inventory getInventoryByName(String name) {
        return REGISTER.get(name);
    }
}
