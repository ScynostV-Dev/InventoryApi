package de.polygondev.testplugin;

import de.polygondev.inventoryapi.Inventoryapi;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SimpleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Inventoryapi.INV_REGISTER.getInventoryByName("SimpleInventory").openInventory((Player) sender);
            ((Player) sender).sendMessage("Jo alles gut hier!");
            return true;
        }


        return false;
    }

}
