package com.ewm.ways;

import com.ewm.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToworldCommandWay extends CommandWay {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String[] args) {
        if (args.length == 2) {
            String name = args[1];
            World world = Bukkit.getWorld(name);
            if (world == null) {
                sender.sendMessage("§cSuch world does not exist.");
                sender.sendMessage("§bAvailable worlds");
                for (World w : Main.getInstance().getServer().getWorlds()) {
                    sender.sendMessage("§b     .. §6" + w.getName().toUpperCase());
                }
                sender.sendMessage("§6§lTIPP§8:§b Don't see your world on the list? Use §6/ewm load <name>§b.");
                return true;
            }
            Player p = (Player) sender;
            sender.sendMessage("§bTeleporting to §6" + name + "§b ...");
            p.teleport(world.getSpawnLocation());
        } else {
            sender.sendMessage("§bUsage:§6 /ewm toworld §b...");
            for (World w : Main.getInstance().getServer().getWorlds()) {
                sender.sendMessage("§b     .. §6" + w.getName().toUpperCase());
            }
            sender.sendMessage("§6§lTIPP§8:§b Don't see your world on the list? Use §6/ewm load <name>§b.");
        }
        return true;
    }
}
