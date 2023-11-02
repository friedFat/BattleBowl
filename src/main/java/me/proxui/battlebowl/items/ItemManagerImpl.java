package me.proxui.battlebowl.items;

import lombok.Getter;
import me.proxui.battlebowl.BattleBowl;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class ItemManagerImpl implements ItemManager {

    @Getter
    private final NamespacedKey identifierKey;
    private final HashMap<UUID, AbstractItem> uuidItemMap = new HashMap<>();
    private final HashMap<Class<? extends AbstractItem>, AbstractItem> classItemMap = new HashMap<>();

    public ItemManagerImpl(BattleBowl plugin) {
        this.identifierKey = new NamespacedKey(plugin, "identifier");
    }

    @Override
    public void register(AbstractItem item) {
        uuidItemMap.put(item.getIdentifier(), item);
        classItemMap.put(item.getClass(), item);
    }

    @Override
    public void unregister(AbstractItem item) {
        uuidItemMap.remove(item.getIdentifier());
        classItemMap.remove(item.getClass());
    }

    @Nonnull
    @Override
    public Collection<AbstractItem> getItems() {
        return uuidItemMap.values();
    }

    @Nullable
    @Override
    public AbstractItem getItem(Class<? extends AbstractItem> clazz) {
        return classItemMap.get(clazz);
    }

    @Nullable
    @Override
    public AbstractItem getItem(ItemStack itemStack) {
        return uuidItemMap.get(getIdentifier(itemStack));
    }

    @Nullable
    @Override
    public AbstractItem getItem(UUID uuid) {
        return uuidItemMap.get(uuid);
    }

    @Nullable
    @Override
    public UUID getIdentifier(ItemStack itemStack) {
        final var dataContainer = itemStack.getItemMeta().getPersistentDataContainer();
        if (!dataContainer.has(identifierKey, PersistentDataType.STRING)) {
            return null;
        }
        return UUID.fromString(Objects.requireNonNull(dataContainer.get(identifierKey, PersistentDataType.STRING)));
    }

    @Override
    public void setIdentifier(ItemStack itemStack, UUID identifier) {
        final var im = itemStack.getItemMeta();
        im.getPersistentDataContainer().set(identifierKey, PersistentDataType.STRING, identifier.toString());
        itemStack.setItemMeta(im);
    }
}
