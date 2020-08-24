package com.github.pelledrijver.HelloWorld;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener{

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
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
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		Bukkit.getScheduler().runTaskLater(this, new Runnable() {
			@Override
            public void run() {
				player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Hi " + ChatColor.DARK_RED + "" + ChatColor.BOLD + player.getName() + ChatColor.RED + "" + ChatColor.BOLD +" en welkom op de server!");
				player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Gebruik " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "/help" + ChatColor.RED + "" + ChatColor.BOLD +" voor meer informatie over de plugin commands ;)");

            }
		}, 25);
		
	}


	
}
