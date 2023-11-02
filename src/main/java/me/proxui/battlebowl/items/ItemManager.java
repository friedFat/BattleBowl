package me.proxui.battlebowl.items;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.UUID;

public interface ItemManager {
    NamespacedKey getIdentifierKey();
    void register(AbstractItem item);

    void unregister(AbstractItem item);

    @Nonnull
    Collection<AbstractItem> getItems();

    @Nullable
    AbstractItem getItem(Class<? extends AbstractItem> clazz);

    @Nullable
    AbstractItem getItem(ItemStack item);

    @Nullable
    AbstractItem getItem(UUID uuid);

    @Nullable
    UUID getIdentifier(ItemStack itemStack);

    void setIdentifier(ItemStack itemStack, UUID identifier);
}
