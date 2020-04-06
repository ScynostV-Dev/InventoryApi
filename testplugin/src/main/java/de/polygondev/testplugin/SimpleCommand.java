package de.polygondev.testplugin;

import de.polygondev.inventoryapi.Inventoryapi;
import de.polygondev.inventoryapi.inventory.Inventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class SimpleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        Inventory inv = new Inventory("", "") {
            @Override
            public void clickEvent(InventoryClickEvent inventoryClickEvent) {
                
            }

            @Override
            public void closeEvent(InventoryCloseEvent inventoryCloseEvent) {

            }
        };
        inv.setItem("2:1", null);

        inv.flush();


        if (sender instanceof Player) {
            Inventoryapi.INV_REGISTER.getInventoryByName("SimpleInventory").openInventory((Player) sender);
            ((Player) sender).sendMessage("Jo alles gut hier!");
            return true;
        }


        return false;
    }

}
