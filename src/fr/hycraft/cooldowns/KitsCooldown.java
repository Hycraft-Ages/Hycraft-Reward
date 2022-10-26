package fr.hycraft.cooldowns;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

public class KitsCooldown
{
	public static HashMap<UUID, Double> kitsCooldowns;

	public static void setupKitsCooldown()
	{
		kitsCooldowns = new HashMap<>();
	}

	public static void setKitsCooldown(Player player, int seconds)
	{
		Double delay = (double) (System.currentTimeMillis() + (seconds * 1000));
		kitsCooldowns.put(player.getUniqueId(), delay);
	}

	public static int getKitsCooldown(Player player)
	{
		return Math.toIntExact(Math.round(kitsCooldowns.get(player.getUniqueId()) - System.currentTimeMillis() / 1000));
	}

	public static boolean checkKitsCooldown(Player player)
	{
		if (!kitsCooldowns.containsKey(player.getUniqueId()) || kitsCooldowns.get(player.getUniqueId()) <= System.currentTimeMillis())
		{
			return true;
		}
		return false;
	}
}
