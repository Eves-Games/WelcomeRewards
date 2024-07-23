package net.worldmc.welcomerewards;

import net.worldmc.welcomerewards.listeners.PlayerChatListener;
import net.worldmc.welcomerewards.listeners.PlayerJoinListener;
import net.worldmc.welcomerewards.managers.WelcomeManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class WelcomeRewards extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        WelcomeManager welcomeManager = new WelcomeManager(this);

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerChatListener(welcomeManager), this);
        pluginManager.registerEvents(new PlayerJoinListener(welcomeManager), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
