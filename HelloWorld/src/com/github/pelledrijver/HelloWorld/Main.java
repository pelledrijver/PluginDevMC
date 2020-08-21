package com.github.pelledrijver.HelloWorld;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin{

	@Override
	public void onEnable() {
		//startup
		//reload
		//plugin reloads
	}
	
	@Override
	public void onDisable() {
		//shutdown
		//reloads
		//plugin reloads
	}
	
	// /hello <-- hey, welcome!
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("Hello")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("hello.use")){
					player.sendMessage(ChatColor.DARK_BLUE + "Hey, welcome to the server " + player.getDisplayName() + "!");
				}
				else {
					player.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
				}
				return true;
				
			}
			else {
				sender.sendMessage("Hey console");
			}
		}
		
		return false;
	}
		


	
}
