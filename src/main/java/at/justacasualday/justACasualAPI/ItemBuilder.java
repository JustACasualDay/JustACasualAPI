package at.justacasualday.justACasualAPI;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilder {
    private ItemStack item;
    private ItemMeta meta;

    public ItemBuilder(Material material) {
        item = new ItemStack(material);
        meta = item.getItemMeta();
    }

    public ItemBuilder(Material material, int amount) {
        item = new ItemStack(material, amount);
        meta = item.getItemMeta();
    }

    public ItemBuilder setDisplayName(String name) {
        meta.setDisplayName(name);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        meta.setLore(lore);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }

}
