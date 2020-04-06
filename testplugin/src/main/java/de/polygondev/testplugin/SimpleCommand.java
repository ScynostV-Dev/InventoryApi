package de.polygondev.testplugin;

import de.polygondev.inventoryapi.InventoryApi;
import de.polygondev.inventoryapi.inventory.Inventory;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SimpleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (InventoryApi.INV_REGISTER.checkInventoryFromPlayerExisting(p, "Katze") == (-1)) {
                SimpleInventory inv = new SimpleInventory(ChatColor.RED + "Baum", "Katze");

                InventoryApi.INV_REGISTER.addInventoryToPlayer(p, inv);
                inv.openInventory();

                p.sendMessage("Jo alles gut hier!");
                return true;
            } else {
                Inventory inv = InventoryApi.INV_REGISTER.getInventoryFromPlayer(p, "Katze");
                inv.openInventory();
            }
        }


        return false;
    }

}
