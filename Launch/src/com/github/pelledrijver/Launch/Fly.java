package com.github.pelledrijver.Launch;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Fly implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("launch") || label.equalsIgnoreCase("lch")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("console goes flying");
				return true;
			}
			Player player = (Player) sender;
			
			if(args.length == 0) {
				player.setVelocity(player.getLocation().getDirection().multiply(2).setY(2));
			}
			else if (args.length == 1){
				player.setVelocity(player.getLocation().getDirection().multiply(Integer.parseInt(args[0])).setY(2));
			}
			else {
				player = Bukkit.getPlayer(args[1]);
				player.setVelocity(player.getLocation().getDirection().multiply(Integer.parseInt(args[0])).setY(2));
			}
			
			player.sendMessage(ChatColor.DARK_RED + "Woohoo!");
		}
		
		
		return false;
		
	}

}
