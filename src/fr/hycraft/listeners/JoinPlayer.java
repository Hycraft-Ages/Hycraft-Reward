package fr.hycraft.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.hycraft.Rewards;
import fr.hycraft.items.Items;

public class JoinPlayer implements Listener
{

	public JoinPlayer(Rewards main)
	{

	}

	@EventHandler
	public void onJoinPlayer(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		Items item = new Items(player);

		if (player.getInventory().containsAtLeast(item.luckyBlock(), 0))
		{
			player.getInventory().addItem(item.luckyBlock());
			player.sendMessage(Rewards.PREFIX + "§aChouette ! §7Aujourd'hui vous avez §ax1 §6Lucky Block");
		}

		else if (player.getInventory().containsAtLeast(item.luckyBlock(), 1))
		{
			player.sendMessage(Rewards.PREFIX + "§cMince ! §7Vous ne pouvez pas avoir de nouveau un §6Lucky Block §7vous en possedez deja §cx1 §7dans votre inventaire.");
			return;
		}
	}
}
