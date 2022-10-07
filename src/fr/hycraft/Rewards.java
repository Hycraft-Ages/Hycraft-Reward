package fr.hycraft;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.hycraft.commands.Commands;
import fr.hycraft.inventory.InventoryRewards;
import fr.hycraft.listeners.RewardsEvent;

public class Rewards extends JavaPlugin
{

	public static String PREFIX = "§8[§4Hycraft-Reward§8] ";
	
	@Override
	public void onEnable()
	{
		Bukkit.getServer().getConsoleSender().sendMessage(PREFIX + "Le plugin vient de s §aallumer");
		onCommands();
		onListeners();
	}
	
	@Override
	public void onDisable()
	{
		Bukkit.getServer().getConsoleSender().sendMessage(PREFIX + "Le plugin vient de s §ceteindre");
	}

	private void onCommands()
	{
		this.getCommand("rewards").setExecutor(new Commands());
		this.getCommand("dailyLuckyBlocks").setExecutor(new Commands());
	}
	
	private void onListeners()
	{
		PluginManager pluginManager = Bukkit.getPluginManager();
		
		pluginManager.registerEvents(new RewardsEvent(this), this);
		pluginManager.registerEvents(new InventoryRewards(this), this);
	}
	
}
