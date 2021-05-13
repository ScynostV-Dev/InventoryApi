package de.polygondev.testplugin.commands;

import de.polygondev.inventoryapi.InventoryApi;
import de.polygondev.testplugin.inventories.inv_testpluginmain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class cmd_testplugin implements CommandExecutor
{
    String invname = "testplugin_main_SomesecretcodeBmd213jed023fhjc0";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
    {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (InventoryApi.INV_REGISTER.checkInventoryFromPlayerExisting(p, invname) == (-1)) {
                inv_testpluginmain inv = new inv_testpluginmain(ChatColor.DARK_RED + "Mechanika", invname);

                InventoryApi.INV_REGISTER.addInventoryToPlayer(p, inv);
                inv.openInventory();

                return true;
            } else {
                inv_testpluginmain inv = (inv_testpluginmain) InventoryApi.INV_REGISTER.getInventoryFromPlayer(p, invname);
                inv.openInventory();
                if (inv.getPage() == 2) {
                    inv.addItemsFromRow();
                }
            }
        }


        return false;
    }
}
