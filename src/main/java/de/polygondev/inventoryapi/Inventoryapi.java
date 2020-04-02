package de.polygondev.inventoryapi;

import de.polygondev.inventoryapi.inventory.Inventory;
import de.polygondev.inventoryapi.inventory.InventoryRegister;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

public final class Inventoryapi extends JavaPlugin {

    public static JavaPlugin PLUGIN;

    @Override
    public void onLoad() {
        // Plugin load logic
        PLUGIN = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getLogger().info(ChatColor.GREEN + "InventoryApi is enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getServer().getLogger().info(ChatColor.RED + "InventoryApi is disabled!");
    }
}
