package com.ewm.ways;

import com.ewm.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnloadCommandWay extends CommandWay {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String[] args) {
        if (args.length == 2) {
            String name = args[1].toUpperCase();
            World w = Bukkit.getWorld(name.toUpperCase());
            if (w == null) {
                sender.sendMessage("§cNo such world.");
                return false;
            }

            for (Player p : w.getPlayers()) {
                p.teleport(Main.getInstance().getServer().getWorlds().get(0).getSpawnLocation());
                p.sendMessage("§bYou were kicked to §6"+Main.getInstance().getServer().getWorlds().get(0).getName().toUpperCase()+"§b because the world you were in has been unloaded.");
            }

            boolean success = Bukkit.unloadWorld(name.toUpperCase(), true);
            if (success) {
                sender.sendMessage("§bWorld §6"+name.toUpperCase()+"§b was unloaded.");
                return true;
            } else {
                sender.sendMessage("§cUnable to unload the world.");
            }
        } else {
            sender.sendMessage("§bUsage:§6 /ewm unload §b...");
            for (World w : Bukkit.getWorlds()) {
                sender.sendMessage("§b     ..§6 "+w.getName().toUpperCase());
            }
        }
        return true;
    }
}
