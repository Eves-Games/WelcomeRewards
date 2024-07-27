package net.worldmc.welcomerewards;

import net.worldmc.morpheus.Morpheus;
import net.worldmc.morpheus.api.MorpheusAPI;
import net.worldmc.welcomerewards.listeners.PlayerChatListener;
import net.worldmc.welcomerewards.listeners.PlayerJoinListener;
import net.worldmc.welcomerewards.managers.WelcomeManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class WelcomeRewards extends JavaPlugin {
    private MorpheusAPI morpheusAPI;

    @Override
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("Morpheus") instanceof Morpheus)
            morpheusAPI = Morpheus.getAPI();

        saveDefaultConfig();

        WelcomeManager welcomeManager = new WelcomeManager(this);

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerChatListener(welcomeManager), this);
        pluginManager.registerEvents(new PlayerJoinListener(welcomeManager), this);
    }

    public MorpheusAPI getMorpheusAPI() {
        return morpheusAPI;
    }
}
