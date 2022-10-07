package fr.hycraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import fr.hycraft.Rewards;
import fr.hycraft.inventory.InventoryRewards;

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
				InventoryRewards.openInventory(player);
				return true;
			}
		}
		
		if (command.getName().equalsIgnoreCase("dailyLuckyBlocks"))
		{
			if (args.length == 0)
			{
				ItemStack luckyBlock = new ItemStack(Material.PLAYER_HEAD);
				SkullMeta luckyMeta  = (SkullMeta) luckyBlock.getItemMeta();
				luckyMeta.setOwningPlayer(player);
				luckyMeta.setDisplayName("§6Lucky Block");
				luckyBlock.setItemMeta(luckyMeta);
				if (!player.getInventory().contains(luckyBlock))
				{
					player.getInventory().addItem(luckyBlock);
					player.sendMessage(Rewards.PREFIX + "§aChouette ! §7Aujourd'hui vous avez un §6Lucky Block");
					return true;
				}
				player.sendMessage(Rewards.PREFIX + "§cMince ! §7Vous ne pouvez pas avoir de nouveau un §6Lucky Block §7vous en possèdez déjà §c1 §7dans votre inventaire.");
				return false;
			}
		}
		return false;
	}
}
