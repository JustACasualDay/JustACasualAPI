package at.justacasualday.justACasualAPI;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Class for adding QOL features for creating/managing items
 */
public class ItemUtils {
    /**
     * Creates an ItemStack
     * @param material material of the item
     * @param displayName name of the item
     * @param amount desired amount
     * @param lore the lore to apply
     * @return the item
     */
    public static ItemStack createItemStack(Material material, String displayName, int amount, List<String> lore)
    {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(displayName);
        meta.setLore(lore);
        item.setItemMeta(meta);
        item.setAmount(amount);
        return item;
    }

    /**
     * Creates an ItemStack
     * @param material material of the item
     * @param displayName name of the item
     * @param lore the lore to apply
     * @return the item
     */
    public static ItemStack createItemStack(Material material, String displayName, List<String> lore)
    {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(displayName);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * Creates an ItemStack
     * @param material material of the item
     * @param displayName name of the item
     * @return the item
     */
    public static ItemStack createItemStack(Material material, String displayName)
    {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(displayName);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * Method for updating an items lore
     * @param item the item which the lore has to be updated
     * @param lore the new lore
     * @return the updated item
     */
    public static ItemStack updateLore(ItemStack item, List<String> lore)
    {
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
