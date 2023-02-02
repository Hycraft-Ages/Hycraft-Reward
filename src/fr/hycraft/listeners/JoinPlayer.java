package fr.hycraft.listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import fr.hycraft.Rewards;
import fr.hycraft.inventory.InventoryRewards;

public class JoinPlayer implements Listener
{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		
		ItemStack luckyBlock = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta luckyMeta  = (SkullMeta) luckyBlock.getItemMeta();
		
		luckyMeta.setOwningPlayer(player);
		luckyMeta.setDisplayName("§6Lucky Block");
		luckyBlock.getMaxStackSize();
		
		luckyBlock.setItemMeta(luckyMeta);
		
		if (player.getInventory().contains(luckyBlock))
		{
			player.sendMessage(Rewards.PREFIX + "§cMince ! \n§7Vous ne pouvez pas avoir de nouveau un §6Lucky Block §7vous en possèdez déjà §cx1 §7dans votre inventaire.");
			return;
		}
		else
		{
			player.getInventory().addItem(luckyBlock);
			player.sendMessage(Rewards.PREFIX + "§aChouette ! \n§7Aujourd'hui vous avez §ax1 §6Lucky Block");
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		
		ItemStack luckyBlock = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta luckyMeta  = (SkullMeta) luckyBlock.getItemMeta();
		
		luckyMeta.setOwningPlayer(player);
		luckyMeta.setDisplayName("§6Lucky Block");
		luckyBlock.setItemMeta(luckyMeta);
		
		if (player.getInventory().contains(luckyBlock)) player.getInventory().remove(luckyBlock);
	}
	
	@EventHandler
	public void onLockItemInventoryPlayer(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();
		
		ItemStack luckyBlock = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta luckyMeta  = (SkullMeta) luckyBlock.getItemMeta();
		luckyMeta.setOwningPlayer(player);
		luckyMeta.setDisplayName("§6Lucky Block");
		luckyBlock.setItemMeta(luckyMeta);
		
		if (event.getClickedInventory() == player.getInventory());
		{
			if (item.getType() == Material.PLAYER_HEAD && item.getItemMeta().getDisplayName().equals("§6Lucky Block"))
			{
				if (player.hasPermission("hycraft.reward.staff")) return;
				event.setCancelled(true);
				player.sendMessage(Rewards.PREFIX + "§cImpossible de déplacer cette item");
			}
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event)
	{
		Player player = event.getPlayer();
		ItemStack item = event.getItemDrop().getItemStack();
		
		ItemStack luckyBlock = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta luckyMeta  = (SkullMeta) luckyBlock.getItemMeta();
		luckyMeta.setOwningPlayer(player);
		luckyMeta.setDisplayName("§6Lucky Block");
		luckyBlock.setItemMeta(luckyMeta);
		
		if (item.getType() == Material.PLAYER_HEAD && item.getItemMeta().getDisplayName().equals("§6Lucky Block"))
		{
			if (player.hasPermission("hycraft.reward.staff")) return;
			event.setCancelled(true);
			player.sendMessage(Rewards.PREFIX + "§cImpossible de drop cette item");
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		
		if (event.getAction() == Action.RIGHT_CLICK_AIR)
		{
			if (!(event.getItem().getType() == Material.PLAYER_HEAD)) return;
			
			SkullMeta metaSkull = (SkullMeta) event.getItem().getItemMeta();
			
			if (!metaSkull.hasOwner()) return;
			
			if (event.getItem().getType() == Material.PLAYER_HEAD  && metaSkull.getOwningPlayer().equals(player) && event.getItem().getItemMeta().getDisplayName().equals("§6Lucky Block"))
			{	
				ArrayList<ItemStack> list = new ArrayList<ItemStack>();
				
				ItemStack feather = new InventoryRewards().FEATHER;
				ItemStack rabbit = new InventoryRewards().RABBIT;
				ItemStack steack = new InventoryRewards().STEACK;
				
				ItemMeta featherMeta = feather.getItemMeta();
				ItemMeta rabbitMeta = rabbit.getItemMeta();
				ItemMeta steackMeta = steack.getItemMeta();
				
				featherMeta.setLore(Arrays.asList(new String[] { "", "Clique pour emporter par un vent", "de vitesse !" }));
				rabbitMeta.setLore(Arrays.asList(new String[] { "", "clique pour pouvoir", "te projeter dans", "les airs comme", "un kangourou !" }));
				steackMeta.setLore(Arrays.asList(new String[] { "", "Clique pour te", "rassasier !" }));
				
				feather.setItemMeta(featherMeta);
				rabbit.setItemMeta(rabbitMeta);
				steack.setItemMeta(steackMeta);
				
				list.add(feather);
				list.add(rabbit);
				list.add(steack);
				
				
				ItemStack luckyBlock = new ItemStack(Material.PLAYER_HEAD);
				SkullMeta luckyMeta  = (SkullMeta) luckyBlock.getItemMeta();
				
				luckyMeta.setOwningPlayer(player);
				luckyMeta.setDisplayName("§6Lucky Block");
				luckyBlock.setItemMeta(luckyMeta);
				
				Random range = new Random();
				int random = range.nextInt(3);
				
				ItemStack item = list.get(random);
				
				player.getInventory().addItem(item);
				player.getInventory().remove(luckyBlock);
				player.sendMessage("§7Vous avez obtenu " + list.get(random).getItemMeta().getDisplayName());
			}
		}
	}
}
