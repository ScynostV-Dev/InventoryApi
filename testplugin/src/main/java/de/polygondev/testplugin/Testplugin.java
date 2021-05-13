package de.polygondev.testplugin;

import de.polygondev.testplugin.commands.cmd_testplugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Testplugin extends JavaPlugin
{

    @Override
    public void onEnable()
    {
        // Plugin startup logic
        this.getCommand("invopen").setExecutor(new cmd_testplugin());
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
    }
}
