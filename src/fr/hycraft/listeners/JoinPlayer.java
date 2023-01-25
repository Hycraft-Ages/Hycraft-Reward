package fr.hycraft.listeners;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import fr.hycraft.Rewards;
import fr.hycraft.inventory.InventoryRewards;
import fr.hycraft.items.Items;

public class JoinPlayer implements Listener
{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		
		
		if (player.getInventory().contains(new Items().luckyBlock(player)))
		{
			player.sendMessage(Rewards.PREFIX + "§cMince ! \n§7Vous ne pouvez pas avoir de nouveau un §6Lucky Block §7vous en possèdez déjà §cx1 §7dans votre inventaire.");
			return;
		}
		else
		{
			player.getInventory().addItem(new Items().luckyBlock(player));
			player.sendMessage(Rewards.PREFIX + "§aChouette ! \n§7Aujourd'hui vous avez §ax1 §6Lucky Block");
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		
		int Min = 1;
		int Max = 3;
		
		int random = Min + (int)(Math.random() * ((Max - Min) + 1));
		
		if (event.getAction() == Action.RIGHT_CLICK_AIR)
		{
			if (!(event.getItem().getType() == Material.PLAYER_HEAD)) return;
			
			SkullMeta metaSkull = (SkullMeta) event.getItem().getItemMeta();
			
			if (!metaSkull.hasOwner()) return;
			
			if (event.getItem().getType() == Material.PLAYER_HEAD && metaSkull.getOwningPlayer().equals(player))
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
				
				player.getInventory().addItem(list.get(random));
				player.getInventory().remove(new Items().luckyBlock(player));
				player.sendMessage("§7Vous avez obtenu " + list.get(random).getItemMeta().getDisplayName());
			}
		}
	}
}
