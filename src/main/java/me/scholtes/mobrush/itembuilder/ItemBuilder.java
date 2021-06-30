package me.scholtes.mobrush.itembuilder;

import me.scholtes.mobrush.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class ItemBuilder {

    private Material material;
    private int amount;
    private String name;
    private List<String> lore;
    private Set<ItemFlag> flags;
    private Map<Enchantment, Integer> enchantments;

    public ItemBuilder(Material material) {
        this(material, 1);
    }

    public ItemBuilder(Material material, int amount) {
        this.material = material;
        this.amount = amount;
        this.lore = new ArrayList<>();
        this.flags = new HashSet<>();
        this.enchantments = new HashMap<>();
    }

    public ItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder withLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder withEnchantment(Enchantment enchantment, int level) {
        enchantments.put(enchantment, level);
        return this;
    }

    public ItemBuilder withFlag(ItemFlag flag) {
        flags.add(flag);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Builds the item
     *
     * @return The {@link ItemStack item}
     */
    public ItemStack build() {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta meta = itemStack.getItemMeta();
        if (!lore.isEmpty()) {
            meta.setLore(StringUtils.color(lore));
        }

        if (name != null) {
            meta.setDisplayName(StringUtils.color(name));
        }

        if (!enchantments.isEmpty()) {
            for (Enchantment enchantment : enchantments.keySet()) {
                meta.addEnchant(enchantment, enchantments.get(enchantment), true);
            }
        }

        if (!flags.isEmpty()) {
            flags.forEach(meta::addItemFlags);
        }

        itemStack.setItemMeta(meta);

        return itemStack;
    }

}
