package com.github.pelledrijver.Gamble;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import io.netty.util.internal.ThreadLocalRandom;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin{
	
	
	public List<Inventory> invs = new ArrayList<Inventory>();
	public static ItemStack[] contents;
	private int ItemIndex = 0;
	
	@Override
	public void onEnable() {

	}

	@Override
	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("Gamble") && sender instanceof Player){
			Player player = (Player) sender;
			ItemStack fee = new ItemStack(Material.DIAMOND);
			fee.setAmount(3);
			if(player.getInventory().getItemInMainHand().isSimilar(fee)) {
				player.getInventory().removeItem(fee);
				//spin the gui
			}
			else {
				player.sendMessage(ChatColor.DARK_RED + "You need at least 3 diamonds to gamble");
			}
			return true;
	}
		return false;
	}
	
	public void spin(final Player player) {
		Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GOLD + "" + ChatColor.BOLD + "Good Luck!");
		shuffle(inv);
		invs.add(inv);
		player.openInventory(inv);
	}
	
	public void shuffle(Inventory inv) {
		if(contents == null) {
			ItemStack[] items = new ItemStack[10];
			items[0] = new ItemStack(Material.COARSE_DIRT, 32);
			items[1] = new ItemStack(Material.DIAMOND, 3);
			items[2] = new ItemStack(Material.IRON_INGOT, 64);
			items[3] = new ItemStack(Material.DIAMOND, 6);
			items[4] = new ItemStack(Material.NETHER_STAR, 4);
			items[5] = new ItemStack(Material.DIRT, 16);
			items[6] = new ItemStack(Material.ENDER_EYE, 2);
			items[7] = new ItemStack(Material.EMERALD_BLOCK, 1);
			items[8] = new ItemStack(Material.ACACIA_LOG, 10);
			items[9] = new ItemStack(Material.NETHER_BRICK, 32);
			
			
			contents = items;
		}
		
		int startingIndex = ThreadLocalRandom.current().nextInt(contents.length);
		for(int i = 0; i < 10; i++) {
			
		}
		
		ItemStack item = new ItemStack(Material.HOPPER);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GRAY + "|");
		item.setItemMeta(meta);
		inv.setItem(3, item);
		
		
	}
}
