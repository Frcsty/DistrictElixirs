package com.github.frcsty.districtelixirs;

import com.github.frcsty.districtelixirs.commands.ElixirGiveCommand;
import com.github.frcsty.districtelixirs.elixirs.ElixirStorage;
import me.mattstudios.mf.base.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DistrictElixirs extends JavaPlugin
{

    private final ElixirStorage elixirStorage = new ElixirStorage();

    @Override
    public void onEnable()
    {
        saveDefaultConfig();

        final CommandManager commandManager = new CommandManager(this);
        commandManager.register(new ElixirGiveCommand(this));

        elixirStorage.loadElixirs(this);
    }

    @Override
    public void onDisable()
    {
        reloadConfig();

        elixirStorage.getElixirHashMap().clear();
    }

    public ElixirStorage getElixirStorage() {
        return this.elixirStorage;
    }

}
