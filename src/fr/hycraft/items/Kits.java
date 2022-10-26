package fr.hycraft.items;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import fr.hycraft.Rewards;
import fr.hycraft.config.Configurations;
import fr.hycraft.cooldowns.KitsCooldown;

public class Kits
{
	private Player player;
	private Rewards instance;
	private Configurations configuration;
	private YamlConfiguration kitsConfig;
	private ItemStack i = new ItemStack(Material.COOKED_BEEF);

	public Kits(Player player,Rewards plugin)
	{
		this.configuration = new Configurations(plugin);
		this.kitsConfig = configuration.getKitsConfiguration();
		this.player = player;
		this.instance = plugin;
	}

	public void addKit(Player player)
	{

		if (KitsCooldown.checkKitsCooldown(player) == true)
		{

			if (player.hasPermission("hycraft.kit.legende"))
			{
				for (String material : kitsConfig.getStringList("Kits.grade_legende.items"))
				{
					String[] args = material.split(":");
					Integer amount = Integer.valueOf(args[1]);
					ItemStack item = new ItemStack(Material.valueOf(args[0]), amount);
					player.getInventory().addItem(item);
				}

			}

			else if (player.hasPermission("hycraft.kit.paladin"))
			{
				for (String material : kitsConfig.getStringList("Kits.grade_paladin.items"))
				{
					String[] args = material.split(";");
					Integer amount = Integer.valueOf(args[1]);
					ItemStack item = new ItemStack(Material.valueOf(args[0]), amount);
					player.getInventory().addItem(item);
				}

			}

			else if (player.hasPermission("hycraft.kit.titan"))
			{
				for (String material : kitsConfig.getStringList("Kits.grade_titan.items"))
				{
					String[] args = material.split(":");
					Integer amount = Integer.valueOf(args[1]);
					ItemStack item = new ItemStack(Material.valueOf(args[0]), amount);
					player.getInventory().addItem(item);
				}

			}

			else
			{
				for (String material : kitsConfig.getStringList("Kits.grade_default.items"))
				{
					String[] args = material.split(":");
					Integer amount = Integer.valueOf(args[1]);
					ItemStack item = new ItemStack(Material.valueOf(args[0]), amount);
					player.getInventory().addItem(item);
				}

			}

			KitsCooldown.setKitsCooldown(player, 86400);
		}

		else
		{
			player.sendMessage(Rewards.PREFIX + "§cVous avez deja recupere votre kit aujourd hui. Recuperez le a nouveau dans: §6§l" + secondsToHours(KitsCooldown.getKitsCooldown(player)));
		}

	}

	public static String secondsToHours(int value)
	{
		int hours = (int) value / 3600;
		int reste = value - hours * 3600;
		int mins = reste / 60;
		reste = reste - mins * 60;
		int seconds = reste;

		return hours + "h" + mins + "min" + seconds + "sec";
	}

}
