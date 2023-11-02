package me.proxui.battlebowl.player;

import org.bukkit.entity.Player;

public class BowlPlayerImpl implements BowlPlayer {
    private final Player player;

    BowlPlayerImpl(Player player) {
        this.player = player;
    }

    @Override
    public Player getBukkitPlayer() {
        return player;
    }
}
