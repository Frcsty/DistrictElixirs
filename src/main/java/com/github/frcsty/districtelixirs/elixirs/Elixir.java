package com.github.frcsty.districtelixirs.elixirs;

import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public class Elixir
{

    private String             name;
    private Material           material;
    private int                data;
    private String             display;
    private List<String>       lore;
    private List<PotionEffect> effects;

    Elixir(final String name, final Material material, final int data, final String display, final List<String> lore, final List<PotionEffect> effects)
    {
        this.name = name;
        this.material = material;
        this.data = data;
        this.display = display;
        this.lore = lore;
        this.effects = effects;
    }

    public final String getName()
    {
        return this.name;
    }

    public final void setName(final String value)
    {
        this.name = value;
    }

    public final Material getMaterial()
    {
        return this.material;
    }

    public final void setMaterial(final Material value)
    {
        this.material = value;
    }

    public final int getData()
    {
        return this.data;
    }

    public final void setData(final int value)
    {
        this.data = value;
    }

    public final String getDisplay()
    {
        return this.display;
    }

    public final void setDisplay(final String value)
    {
        this.display = value;
    }

    public final List<String> getLore()
    {
        return this.lore;
    }

    public final void setLore(final List<String> values)
    {
        this.lore = values;
    }

    public final List<PotionEffect> getEffects()
    {
        return this.effects;
    }

    public final void setEffects(final List<PotionEffect> values)
    {
        this.effects = values;
    }

}
