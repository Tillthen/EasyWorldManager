package com.ewm;

import com.ewm.commands.EwmCommand;
import com.ewm.generator.Generator;
import com.ewm.registry.Info;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    static Main instance;

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new Generator();
    }

    @Override
    public void onEnable() {
        instance = this;

        getCommand("ewm").setExecutor(new EwmCommand());
        Info.worlds = getServer().getWorlds();
    }

    @Override
    public void onDisable() {}

    public static Main getInstance() {
        return instance;
    }

    public static File worldContainer(){
        return getInstance().getServer().getWorldContainer();
    }
}
