package com.github.frcsty.districtelixirs.elixirs;

import com.github.frcsty.districtelixirs.utils.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public class ElixirBuilder
{

    private Material           material;
    private int                data;
    private String             display;
    private List<String>       lore;
    private List<PotionEffect> effects;

    public ElixirBuilder(final Material material, final int data, final String display, final List<String> lore, final List<PotionEffect> effects)
    {
        this.material = material;
        this.data = data;
        this.display = display;
        this.lore = lore;
        this.effects = effects;
    }

    public ItemStack getItem()
    {
        ItemStack item = new ItemStack(material, 1, (byte) data);
        final ItemMeta meta = item.getItemMeta();
        final PotionMeta potionMeta = (PotionMeta) meta;

        if (display != null)
        {
            potionMeta.setDisplayName(Color.colorize(display));
        }

        if (!lore.isEmpty())
        {
            potionMeta.setLore(Color.colorize(lore));
        }

        if (!effects.isEmpty())
        {
            for (PotionEffect effect : effects)
            {
                potionMeta.addCustomEffect(effect, false);
            }
        }

        item.setItemMeta(meta);

        return item;
    }

}
