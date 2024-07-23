package net.worldmc.welcomerewards.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.TextComponent;
import net.worldmc.welcomerewards.managers.WelcomeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerChatListener implements Listener {
    private final WelcomeManager welcomeManager;

    public PlayerChatListener(WelcomeManager welcomeManager) {
        this.welcomeManager = welcomeManager;
    }

    @EventHandler
    public void onAsyncChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        String message = ((TextComponent) event.message()).content();

        welcomeManager.processWelcomeMessage(player, message);
    }
}