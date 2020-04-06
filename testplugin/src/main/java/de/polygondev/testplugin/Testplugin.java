package de.polygondev.testplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class Testplugin extends JavaPlugin {

    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        this.getCommand("invopen").setExecutor(new SimpleCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
