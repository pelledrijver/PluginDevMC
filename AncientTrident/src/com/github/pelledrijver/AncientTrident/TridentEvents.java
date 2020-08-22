package com.github.pelledrijver.AncientTrident;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class TridentEvents implements Listener{

	static Main plugin;
	public TridentEvents(Main instance) {
		plugin = instance;
	}
	
	public List<String> list = new ArrayList<String>();
	
	@EventHandler()
	public void onClick(PlayerInteractEvent event) {
		Player player = (Player) event.getPlayer();
		if(player.getInventory().getItemInMainHand().getType().equals(Material.TRIDENT)){
			if(player.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR){
					if(!list.contains(player.getName()))
						list.add(player.getName());
				
				}
				else if(event.getAction() == Action.LEFT_CLICK_AIR){
					player.launchProjectile(Fireball.class);
				}
				return;
			}
		}
		
		if(list.contains(player.getName()))
			list.remove(player.getName());
		
	} 
	
	@EventHandler()
	public void onLand(ProjectileHitEvent event) {
		if(event.getEntityType() == EntityType.TRIDENT) {
			if(event.getEntity().getShooter() instanceof Player) {
				Player player = (Player) event.getEntity().getShooter();
				if(list.contains(player.getName())) {
					Location loc = event.getEntity().getLocation();
					loc.setY(loc.getY() + 1);
					loc.getWorld().spawnEntity(loc, EntityType.DROWNED);
				}
			}
		}
	}
}
