package com.github.pelledrijver.ChangeTeam;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener{
	
	public Inventory inv;
	
	
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		createInv();
	}

	@Override
	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player && label.equalsIgnoreCase("changeteam")){
			Player player = (Player) sender;
			player.openInventory(inv); //not sure yet
		}
		return false;
	}
	
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if(event.getInventory() == inv) {
			if(event.getCurrentItem() != null && event.getCurrentItem().getItemMeta() != null && event.getCurrentItem().getItemMeta().getDisplayName() != null) {
				event.setCancelled(true); //makes sure you don't grab the item
				Player player = (Player) event.getWhoClicked();
				
				
				ItemStack[] armor;
				
				
				switch(event.getSlot()) {
					case 0: 
						
						armor = changeColor(changeOutfit(player), Color.BLUE);
						player.getEquipment().setArmorContents(armor);
						player.sendMessage(ChatColor.GOLD + "You changed your team!");
						break;						
					case 1:
						
						armor = changeColor(changeOutfit(player), Color.RED);
						player.getEquipment().setArmorContents(armor);
						player.sendMessage(ChatColor.GOLD + "You changed your team!");
						break;	
					case 2:
						
						armor = changeColor(changeOutfit(player), Color.LIME);
						player.getEquipment().setArmorContents(armor);
						player.sendMessage(ChatColor.GOLD + "You changed your team!");
						break;	
					case 3:
						
						armor = changeColor(changeOutfit(player), Color.ORANGE);
						player.getEquipment().setArmorContents(armor);
						player.sendMessage(ChatColor.GOLD + "You changed your team!");
						break;	
					case 4:
						
						armor = changeColor(changeOutfit(player), Color.PURPLE);
						player.getEquipment().setArmorContents(armor);
						player.sendMessage(ChatColor.GOLD + "You changed your team!");
						break;	
					case 5:
					
						armor = changeColor(changeOutfit(player), Color.AQUA);
						player.getEquipment().setArmorContents(armor);
						player.sendMessage(ChatColor.GOLD + "You changed your team!");
						break;	
					case 6:
						
						armor = changeColor(changeOutfit(player), Color.BLACK);
						player.getEquipment().setArmorContents(armor);
						player.sendMessage(ChatColor.GOLD + "You changed your team!");
						break;	
					case 8:
						player.closeInventory();
				}
				
			}
		}
	}
	
	
	public ItemStack[] changeOutfit(Player player) {
		for (ItemStack i : player.getEquipment().getArmorContents()){
			if(i == null || i.getType() == Material.LEATHER_BOOTS || i.getType() == Material.LEATHER_LEGGINGS || i.getType() == Material.LEATHER_CHESTPLATE || i.getType() == Material.LEATHER_HELMET) {
				continue;
			}
            player.getWorld().dropItemNaturally(player.getLocation(), i);
            player.getInventory().remove(i);
        }
		return (new ItemStack[] {new ItemStack(Material.LEATHER_BOOTS), new ItemStack(Material.LEATHER_LEGGINGS), new ItemStack(Material.LEATHER_CHESTPLATE), new ItemStack(Material.LEATHER_HELMET)});
	}
	
	public ItemStack[] changeColor(ItemStack[] a, Color color) {
		for(ItemStack item : a) {
			LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
			meta.setColor(color);
			item.setItemMeta(meta);
		}
		return a;
	}
	
	
	
	public void createInv() {
		inv = Bukkit.createInventory(null, 9, ChatColor.GOLD + "" + ChatColor.BOLD + "Select Team");
		
		ItemStack item = new ItemStack(Material.BLUE_CONCRETE);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(ChatColor.DARK_BLUE + "Blue Team");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "Click to join team");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(0, item);
		
		item.setType(Material.RED_CONCRETE);
		meta.setDisplayName(ChatColor.DARK_RED + "Red Team");
		item.setItemMeta(meta);
		inv.setItem(1, item);
		
		item.setType(Material.LIME_CONCRETE);
		meta.setDisplayName(ChatColor.GREEN + "Lime Team");
		item.setItemMeta(meta);
		inv.setItem(2, item);
		
		item.setType(Material.ORANGE_CONCRETE);
		meta.setDisplayName(ChatColor.GOLD + "Orange Team");
		item.setItemMeta(meta);
		inv.setItem(3, item);
		
		item.setType(Material.PURPLE_CONCRETE);
		meta.setDisplayName(ChatColor.DARK_PURPLE + "Purple Team");
		item.setItemMeta(meta);
		inv.setItem(4, item);
		
		item.setType(Material.CYAN_CONCRETE);
		meta.setDisplayName(ChatColor.BLUE + "Cyan Team");
		item.setItemMeta(meta);
		inv.setItem(5, item);
		
		item.setType(Material.BLACK_CONCRETE);
		meta.setDisplayName(ChatColor.DARK_GRAY + "Black Team");
		item.setItemMeta(meta);
		inv.setItem(6, item);
		
		item.setType(Material.BARRIER);
		meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Close Menu");
		lore.clear();
		item.setItemMeta(meta);
		inv.setItem(8, item);
	}
}
