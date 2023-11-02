package me.proxui.battlebowl.player;

import me.proxui.battlebowl.BattleBowl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager implements Listener {
    public PlayerManager(BattleBowl plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private final HashMap<UUID, BowlPlayer> playerRegistry = new HashMap<>();

    public BowlPlayer getBowlPlayer(Player player) {
        if (playerRegistry.containsKey(player.getUniqueId()))
            playerRegistry.put(player.getUniqueId(), new BowlPlayerImpl(player));
        return playerRegistry.get(player.getUniqueId());
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent e) {
        playerRegistry.remove(e.getPlayer().getUniqueId());
    }
}
