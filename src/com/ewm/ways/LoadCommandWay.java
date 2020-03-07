package com.ewm.ways;

import com.ewm.Main;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.io.File;

public class LoadCommandWay extends CommandWay {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String[] args) {
        if (args.length == 2) {
            String name = args[1].toUpperCase();
            if (Bukkit.getWorld(name) != null) {
                sender.sendMessage("§cThis world is already loaded!");
                return false;
            }
            File worlds = Main.worldContainer();
            File world = new File(worlds, name.toUpperCase());
            if (!world.exists()) {
                sender.sendMessage("§cSuch world file does not exist.");
                return false;
            }
            if (!new File(world, "level.dat").exists()) {
                sender.sendMessage("§cThis file is not a world.");
                return false;
            }
            WorldCreator wc = new WorldCreator(name.toUpperCase());
            wc.createWorld();
            sender.sendMessage("§6" + name.toUpperCase() + "§b was loaded.");
        } else {
            File worlds = Main.worldContainer();
            sender.sendMessage("§bUsage:§6 /ewm load §b...");
            for (File f : worlds.listFiles()) {
                if (f.isDirectory()) {
                    if (new File(f, "level.dat").exists()) {
                        sender.sendMessage("§b     ..§6 " + f.getName().toUpperCase());
                    }
                }
            }
        }
        return true;
    }
}
