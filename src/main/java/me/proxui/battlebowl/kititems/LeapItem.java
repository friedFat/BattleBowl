package me.proxui.battlebowl.kititems;

import me.proxui.battlebowl.BattleBowl;
import me.proxui.battlebowl.items.AbstractItem;
import me.proxui.battlebowl.player.BowlPlayer;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;

public class LeapItem extends AbstractItem {
    private static final Vector multiplier = new Vector(0, 0.5, 0);

    public LeapItem(BattleBowl plugin) {
        super(plugin);
    }

    @Nonnull
    @Override
    public ItemStack getItemStack() {
        final var i = new ItemStack(Material.FEATHER);
        final var im = i.getItemMeta();
        im.setDisplayName("§f§oLeap");
        im.addEnchant(Enchantment.VANISHING_CURSE, 0, false);
        i.setItemMeta(im);
        return i;
    }

    @Override
    protected void onUse(PlayerInteractEvent e, BowlPlayer p) {
        e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(multiplier));
    }
}
