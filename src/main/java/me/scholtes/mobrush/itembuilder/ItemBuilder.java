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

    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
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
     * Creates a new {@link ItemStack} with the properties that were set
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

        enchantments.forEach((enchant, lvl) -> meta.addEnchant(enchant, lvl, true));
        flags.forEach(meta::addItemFlags);

        itemStack.setItemMeta(meta);
        return itemStack;
    }

}
