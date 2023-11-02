package me.proxui.battlebowl.commands;

import me.proxui.battlebowl.BattleBowl;
import me.proxui.battlebowl.items.AbstractItem;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemsOverviewCommand implements CommandExecutor {
    private final BattleBowl plugin;

    public ItemsOverviewCommand(BattleBowl plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player p)) return false;

        final var gui = Bukkit.createInventory(null, 54, "Items Overview");
        for (AbstractItem item : plugin.getItemManager().getItems()) {
            gui.addItem(item.getItemStack());
        }
        p.openInventory(gui);

        p.playSound(p, Sound.BLOCK_ENDER_CHEST_OPEN, 1f, 0f);

        return true;
    }
}
