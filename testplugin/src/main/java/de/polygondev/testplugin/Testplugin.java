package de.polygondev.testplugin;

import de.polygondev.inventoryapi.Inventoryapi;
import org.bukkit.plugin.java.JavaPlugin;

public final class Testplugin extends JavaPlugin {

    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        Inventoryapi.INV_REGISTER.addInventory(new SimpleInventory("How i got here?", "SimpleInventory"));
        this.getCommand("invopen").setExecutor(new SimpleCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
