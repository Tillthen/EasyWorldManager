package com.ewm.commands;

import com.ewm.ways.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EwmCommand implements CommandExecutor {
    static String[] info_page =
            {
                    "§6     EasyWorldManager§b by Tillthen",
                    "§b An easy to use world management plugin",
                    "§b         For help type §6/ewm help"
            };

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ewm")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cYou can't use this command from the console.");
                return false;
            }
            if (sender.hasPermission("ewm.use")) {
                if (args.length < 1) {
                    sender.sendMessage(info_page);
                    return false;
                }
                if (args[0].equalsIgnoreCase("generate")) {
                    new GenerateCommandWay().onCommand(sender, cmd, args);
                } else if (args[0].equalsIgnoreCase("toworld")) {
                    new ToworldCommandWay().onCommand(sender, cmd, args);
                } else if (args[0].equalsIgnoreCase("load")) {
                    new LoadCommandWay().onCommand(sender, cmd, args);
                } else if (args[0].equalsIgnoreCase("unload")) {
                    new UnloadCommandWay().onCommand(sender, cmd, args);
                } else if (args[0].equalsIgnoreCase("help")) {
                    new HelpCommandWay().onCommand(sender, cmd, args);
                }
            } else {
                sender.sendMessage("§cYou aren't allowed to do this!");
            }
            return false;
        }
        return false;
    }
}
