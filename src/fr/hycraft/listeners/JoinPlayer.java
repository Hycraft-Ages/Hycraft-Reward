package fr.hycraft.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import fr.hycraft.Rewards;

public class JoinPlayer implements Listener
{

	public JoinPlayer(Rewards main)
	{
		
	}
	
	
	@EventHandler
	public void onJoinPlayer(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		ItemStack luckyBlock = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta luckyMeta  = (SkullMeta) luckyBlock.getItemMeta();
		
		luckyMeta.setOwningPlayer(player);
		luckyMeta.setDisplayName("§6Lucky Block");
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
}
