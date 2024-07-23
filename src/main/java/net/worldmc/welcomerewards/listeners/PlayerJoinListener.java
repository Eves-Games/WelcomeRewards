package net.worldmc.welcomerewards.listeners;

import net.worldmc.welcomerewards.managers.WelcomeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final WelcomeManager welcomeManager;

    public PlayerJoinListener(WelcomeManager welcomeManager) {
        this.welcomeManager = welcomeManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPlayedBefore()) {
            welcomeManager.latestJoinTime = System.currentTimeMillis();
            welcomeManager.rewardedPlayers.clear();
        };
    }
}