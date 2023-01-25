package fr.hycraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.hycraft.Rewards;
import fr.hycraft.inventory.InventoryRewards;
import fr.hycraft.items.Items;

public class Commands implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (!(sender instanceof Player))
		{
			Bukkit.getServer().getConsoleSender().sendMessage(Rewards.PREFIX + "§cSeul un joueur peut effectuer cette commande");
			return false;
		}
		
		Player player = (Player) sender;
		
		if (command.getName().equalsIgnoreCase("rewards"))
		{
			if (args.length == 0)
			{
				InventoryRewards inventory = new InventoryRewards();
				inventory.openInventory(player);
				return true;
			}
		}
		
		if (command.getName().equalsIgnoreCase("dailyLuckyBlocks"))
		{
			if (args.length == 0)
			{
				Items item = new Items();
				if (!player.getInventory().contains(item.luckyBlock(player)))
				{
					player.getInventory().addItem(item.luckyBlock(player));
					player.sendMessage(Rewards.PREFIX + "§aChouette ! §7Aujourd'hui vous avez §ax1 §6Lucky Block");
					return true;
				}
				player.sendMessage(Rewards.PREFIX + "§cMince ! §7Vous ne pouvez pas avoir de nouveau un §6Lucky Block §7vous en possèdez déjà §cx1 §7dans votre inventaire.");
				return false;
			}
		}
		return false;
	}
}
