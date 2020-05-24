package com.github.frcsty.districtelixirs.commands;

import com.github.frcsty.districtelixirs.DistrictElixirs;
import com.github.frcsty.districtelixirs.elixirs.Elixir;
import com.github.frcsty.districtelixirs.elixirs.ElixirBuilder;
import com.github.frcsty.districtelixirs.elixirs.ElixirStorage;
import com.github.frcsty.districtelixirs.utils.Color;
import com.github.frcsty.districtelixirs.utils.Replace;
import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Default;
import me.mattstudios.mf.annotations.Permission;
import me.mattstudios.mf.annotations.SubCommand;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

@Command("elixir")
public class ElixirGiveCommand extends CommandBase
{

    private final DistrictElixirs   plugin;
    private final FileConfiguration config;

    public ElixirGiveCommand(final DistrictElixirs plugin)
    {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    @Default
    @Permission("elixirs.commands.reload")
    public void reloadPlugin(final CommandSender sender)
    {
        final long startTime = System.currentTimeMillis();
        final ElixirStorage elixirStorage = plugin.getElixirStorage();

        elixirStorage.getElixirHashMap().clear();
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                plugin.reloadConfig();
                elixirStorage.loadElixirs(plugin);
            }
        }.runTaskAsynchronously(plugin);

        final String estimatedTime = String.valueOf(System.currentTimeMillis() - startTime);
        sender.sendMessage(Color.colorize(Replace.replaceString(config.getString("messages.reload-plugin"), "{time}", estimatedTime)));
    }

    @SubCommand("give")
    @Permission("elixirs.commands.give")
    public void giveElixir(final CommandSender sender, final String t, final String e, final int amount)
    {
        final Player target = Bukkit.getPlayerExact(t);
        final String elixir = e.toLowerCase();

        if (target == null)
        {
            sender.sendMessage(Color.colorize(config.getString("messages.invalid-player")));
            return;
        }

        if (!target.isOnline())
        {
            sender.sendMessage(Color.colorize(Replace.replaceString(config.getString("messages.offline-player"), "{player}", target.getName())));
            return;
        }

        final Elixir elixirObject = plugin.getElixirStorage().getElixir(elixir);

        if (elixirObject == null)
        {
            sender.sendMessage(Color.colorize(Replace.replaceString(config.getString("messages.invalid-elixir"), "{elixir}", elixir)));
            return;
        }

        final ItemStack elixirItem = new ElixirBuilder(elixirObject.getMaterial(), elixirObject.getData(), elixirObject.getDisplay(), elixirObject.getLore(), elixirObject.getEffects(), elixirObject.getAmount()).getItem();

        if (target.getInventory().firstEmpty() == -1)
        {
            sender.sendMessage(Color.colorize(Replace.replaceString(config.getString("messages.target-full-inventory"))));
            return;
        }

        target.getInventory().addItem(elixirItem);
    }

}
