package com.ewm.ways;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class HelpCommandWay extends CommandWay {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String[] args) {
        if (args.length == 1) {
            String[] help_page =
                    {
                            "§b---------- §6EWM Help Page §b----------",
                            "§6/ewm ...§b Main command",
                            "§6/ewm help §bHelp page",
                            "§6/ewm generate ... §bGenerate a world",
                            "§6/ewm toworld ... §bTeleport to a world",
                            "§6/ewm load ... §bLoad a world from file",
                            "§6/ewm unload ... §bUnload a world"
                    };
            sender.sendMessage(help_page);
        } else {
            sender.sendMessage("§bUsage:§6 /ewm help");
        }
        return true;
    }
}
