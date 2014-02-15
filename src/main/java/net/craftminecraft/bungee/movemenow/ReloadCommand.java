package net.craftminecraft.bungee.movemenow;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class ReloadCommand extends Command {
	MoveMeNow plugin;
	public ReloadCommand(MoveMeNow plugin) {
		super("mmn", "movemenow.admin");
		this.plugin = plugin;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length != 1) {
			sender.sendMessage("Derp");
		}
		switch (args[0]) {
		case "reload":
            plugin.loadConfig();
			sender.sendMessage(ChatColor.RED + "[MoveMeNow]" + ChatColor.YELLOW + " Config reloaded.");
		}
	}

}
