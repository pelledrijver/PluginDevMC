package com.github.pelledrijver.AncientTrident;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener{
	
	
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new TridentEvents(this), this);
	}

	@Override
	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("trident")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(player.getInventory().firstEmpty() == -1) {
					Location loc = player.getLocation();
					World world = player.getWorld();
					world.dropItemNaturally(loc, getTrident());
				}
				else {
					player.getInventory().addItem(getTrident());
				}
				player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "The MC Gods have given you the magic trident");
				
			}
			return true;
		}
		
		return false;
	}

	private ItemStack getTrident() {
		ItemStack trident = new ItemStack(Material.TRIDENT);
		ItemMeta meta = trident.getItemMeta();
		
		meta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Ancient Trident");
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.ITALIC + "Use left click " + ChatColor.RESET + "to throw a fireball");
		lore.add(ChatColor.ITALIC + "Use right click " + ChatColor.RESET + "to summon an army");
		meta.setLore(lore);
		
		meta.addEnchant(Enchantment.LOYALTY, 10, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setUnbreakable(true);
		
		trident.setItemMeta(meta);
		return trident;
	}
	
	
	
}
