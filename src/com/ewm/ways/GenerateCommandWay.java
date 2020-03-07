package com.ewm.ways;

import com.ewm.generator.Generator;
import com.ewm.registry.GeneratorSettings;
import com.ewm.registry.WorldInfo;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class GenerateCommandWay extends CommandWay {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String[] args) {
        if (args.length == 6) {
            String name = args[1];
            String[] materialID = args[2].toUpperCase().split(",");
            ArrayList<Material> material = new ArrayList<Material>();
            for (String str : materialID) {
                Material m = Material.getMaterial(str);
                if (m == null) {
                    sender.sendMessage("§4No such material§c " + str);
                    return false;
                }
                material.add(m);
            }
            int max_height = Integer.parseInt(args[3]);
            if (!(args[4].equalsIgnoreCase("flat") || args[5].equalsIgnoreCase("simplex") || args[5].equalsIgnoreCase("perlin"))) {
                sender.sendMessage("§4Unknown parameter §c" + args[5]);
                return true;
            }
            double sharpness = Double.parseDouble(args[4]);
            if (sharpness < 0) {
                sender.sendMessage("§c* Sharpness was set to 1. (Invaild value " + sharpness + ")");

                sharpness = 1;
            }
            if (max_height <= 0) {
                sender.sendMessage("§c* Height was set to 1. (Invaild value " + max_height + ")");
                max_height = 1;
            }
            WorldInfo info = new WorldInfo(max_height, name, material, args[5], sharpness);
            GeneratorSettings.info.add(info);
            sender.sendMessage("§bGenerating following world: Name §6" + name + "§b, Materials (count) §6" + material.size() + "§b, Height §6" + max_height + "§b, Sharpness §6 " + sharpness + "§b, Noise §6" + args[5]);
            float timeA = System.nanoTime();
            WorldCreator creator = new WorldCreator(name);
            Bukkit.createWorld(creator.generator(new Generator()));
            float time = (System.nanoTime() - timeA) / 1000000;
            sender.sendMessage("§bDone. §6" + time + " ms");
            sender.sendMessage("§bTeleporting ...");
            Player player = (Player) sender;
            player.teleport(new Location(Bukkit.getWorld(name), 0, max_height + 20, 0));
        } else {
            sender.sendMessage("§bUsage:§6 /ewm generate <name> [<materials>] <height> <noise multiplier> <flat|simplex|perlin>");
            return false;
        }
        return true;
    }
}
