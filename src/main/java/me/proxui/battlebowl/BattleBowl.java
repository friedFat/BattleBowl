package me.proxui.battlebowl;

import lombok.Getter;
import me.proxui.battlebowl.commands.ItemsOverviewCommand;
import me.proxui.battlebowl.items.ItemManager;
import me.proxui.battlebowl.items.ItemManagerImpl;
import me.proxui.battlebowl.kititems.LeapItem;
import me.proxui.battlebowl.player.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class BattleBowl extends JavaPlugin {

    private static BattleBowl instance;
    private PlayerManager playerManager;
    private ItemManager itemManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        final var startTime = System.currentTimeMillis();
        instance = this;

        initManagers();

        registerCommands();
        registerItems();

        final var difference = System.currentTimeMillis() - startTime;
        getLogger().info("Loaded "+getName()+" in "+difference+"ms");
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void initManagers() {
        playerManager = new PlayerManager(this);
        itemManager = new ItemManagerImpl(this);
    }

    private void registerCommands() {
        getCommand("itemsoverview").setExecutor(new ItemsOverviewCommand(this));
    }

    private void registerItems() {
        itemManager.register(new LeapItem(this));
    }
}
