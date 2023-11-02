package me.proxui.battlebowl.items;

import lombok.Getter;
import me.proxui.battlebowl.BattleBowl;
import me.proxui.battlebowl.player.BowlPlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.UUID;

public abstract class AbstractItem implements Listener {
    private final BattleBowl plugin;
    @Getter
    private final UUID identifier = UUID.randomUUID();

    public AbstractItem(BattleBowl plugin) {
        this.plugin = plugin;

        plugin.getItemManager().setIdentifier(getItemStack(), identifier);

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public abstract @Nonnull ItemStack getItemStack();

    protected abstract void onUse(PlayerInteractEvent e, BowlPlayer p);

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getItem() == null) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        final var otherUUID = plugin.getItemManager().getIdentifier(e.getItem());
        if (this.identifier != otherUUID) return;

        onUse(e, plugin.getPlayerManager().getBowlPlayer(e.getPlayer()));
    }
}