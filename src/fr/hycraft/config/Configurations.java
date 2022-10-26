package fr.hycraft.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.hycraft.Rewards;

public class Configurations
{

	private File kitsFile;
	private YamlConfiguration kitsConfiguration;

	private Rewards instance;

	public Configurations(Rewards plugin)
	{
		this.instance = plugin;
	}

	public void loadKitsConfigutaion()
	{
		if (!this.instance.getDataFolder().exists())
		{
			this.instance.getDataFolder().mkdir();
		}

		kitsFile = new File(this.instance.getDataFolder(), "kits.yml");

		if (!kitsFile.exists())
		{
			try
			{
				kitsFile.createNewFile();
			}
			catch (IOException fileError)
			{
				fileError.printStackTrace();
			}
		}

		kitsConfiguration = YamlConfiguration.loadConfiguration(kitsFile);
	}

	public void saveKitsConfig()
	{
		try
		{
			kitsConfiguration.save(kitsFile);
		}
		catch (IOException fileError)
		{
			fileError.printStackTrace();
		}
	}

	public YamlConfiguration getKitsConfiguration()
	{
		return kitsConfiguration;
	}

	public File getKitsFile()
	{
		return kitsFile;
	}

	public Location parseString(String parseString)
	{
		String[] parsedLocation = parseString.split(",");

		double x = Double.valueOf(parsedLocation[0]);
		double y = Double.valueOf(parsedLocation[1]);
		double z = Double.valueOf(parsedLocation[2]);

		return new Location(Bukkit.getWorld("Lobby_Japon"), x, y, z);
	}

	public String locationToString(Location location)
	{
		return location.getX() + "," + location.getY() + "," + location.getZ();
	}
}
