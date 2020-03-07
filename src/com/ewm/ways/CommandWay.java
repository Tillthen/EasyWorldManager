package com.ewm.ways;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class CommandWay {
    public abstract boolean onCommand(CommandSender sender, Command command, String[] args);
}
