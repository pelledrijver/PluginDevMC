package com.github.pelledrijver.DiscordChat;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

;

public class Main extends JavaPlugin implements Listener{    
	
	Socket client;
	PrintWriter output;
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {
		try {
			client.close();
			Bukkit.broadcastMessage(ChatColor.RED + "Lost connection with Discord server");
		}
		catch(Exception e){
			System.out.println("The connection failed to shut down!");
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("DiscordChat") || label.equalsIgnoreCase("Discord") || label.equalsIgnoreCase("dc")){
			Bukkit.getScheduler().scheduleAsyncDelayedTask(this, new Runnable() {
	            @Override
	            public void run() {
	            	try {
		            	client = new Socket("localhost", 3000);
		            	Bukkit.broadcastMessage(ChatColor.GREEN + "Server is geconnect met socket " + client.getRemoteSocketAddress());
		            	//Bukkit.broadcastMessage(client.isConnected() ? "Yes" : "No!");
		            	Scanner input = new Scanner(client.getInputStream());
		            	output = new PrintWriter(client.getOutputStream());
		                String temp = input.nextLine();
		                while(true) {
		                	Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "[Discord] " + ChatColor.WHITE + temp);
		                	temp = input.nextLine();
		                }
		                
	            	}
	            	catch(Exception e) {
	            		Bukkit.broadcastMessage("Error: " + e);
	            	}
	            }
			});
			return true;
		}

		return false;
	}
	
	@EventHandler
	public void onMessage(AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		if(!message.startsWith("/")) {
			try {
				output.println("[" + event.getPlayer().getName() + "]: " + message);
				output.flush();
			}
			catch(Exception e) {
				Bukkit.broadcastMessage(ChatColor.RED + "Could not send message to Discord");
			}
		}
	}
}
