package de.polygondev.inventoryapi;

import de.polygondev.inventoryapi.inventory.InventoryRegistry;
import de.polygondev.inventoryapi.inventory.Listener_InventoryClickEvent;
import de.polygondev.inventoryapi.inventory.Listener_InventoryCloseEvent;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class InventoryApi extends JavaPlugin {

    public static JavaPlugin PLUGIN;
    public static InventoryRegistry INV_REGISTER = new InventoryRegistry();

    @Override
    public void onLoad() {
        // Plugin load logic
        PLUGIN = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getLogger().info(ChatColor.GREEN + "InventoryApi is enabled!");
        this.getServer().getPluginManager().registerEvents(new Listener_InventoryClickEvent(), this);
        this.getServer().getPluginManager().registerEvents(new Listener_InventoryCloseEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getServer().getLogger().info(ChatColor.RED + "InventoryApi is disabled!");
    }
}
