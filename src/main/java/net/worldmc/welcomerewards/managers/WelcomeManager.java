package net.worldmc.welcomerewards.managers;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.worldmc.morpheus.api.MorpheusAPI;
import net.worldmc.welcomerewards.WelcomeRewards;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class WelcomeManager {

    private final MorpheusAPI morpheusAPI;
    private final String triggerWord;
    private final int windowSeconds;
    private final ItemStack rewardItem;
    private final Sound rewardSound;
    private final String rewardMessage;

    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public Long latestJoinTime;
    public Set<UUID> rewardedPlayers;

    public WelcomeManager(WelcomeRewards plugin) {
        FileConfiguration config = plugin.getConfig();

        this.morpheusAPI = plugin.getMorpheusAPI();
        this.triggerWord = config.getString("trigger-word", "welcome");
        this.windowSeconds = config.getInt("window", 10);
        this.rewardItem = new ItemStack(Material.valueOf(config.getString("reward.item", "GOLD_NUGGET")),
                config.getInt("reward.amount", 5));
        this.rewardSound = Sound.valueOf(config.getString("reward.sound", "ENTITY_EXPERIENCE_ORB_PICKUP"));
        this.rewardMessage = config.getString("reward.message", "<gray>[<b><gradient:#00AA00:#FFAA00>WorldMC</gradient></b>] <green>Thanks for welcoming a new player, you've received <yellow>a reward</yellow> as a gift!");
        this.latestJoinTime = 0L;
        this.rewardedPlayers = new HashSet<>();
    }

    public void processWelcomeMessage(Player sender, String message) {
        if (!message.toLowerCase().contains(triggerWord.toLowerCase())) {
            return;
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - latestJoinTime <= windowSeconds * 1000L && !rewardedPlayers.contains(sender.getUniqueId())) {
            sender.getInventory().addItem(rewardItem);
            sender.playSound(sender.getLocation(), rewardSound, 1.0f, 1.0f);

            morpheusAPI.sendPlayerMessage(sender, rewardMessage, true);
            rewardedPlayers.add(sender.getUniqueId());
        }
    }
}