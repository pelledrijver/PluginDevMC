package com.github.pelledrijver.Launch;


import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		this.getCommand("Launch").setExecutor(new Fly());
	}
	
	@Override
	public void onDisable() {
		
	}
	

}
