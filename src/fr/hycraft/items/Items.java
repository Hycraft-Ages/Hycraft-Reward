package fr.hycraft.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Items
{

	private Player player;
	
	public Items(Player player)
	{
		this.player = player;
	}
	
	public ItemStack luckyBlock()
	{
		ItemStack luckyBlock = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta luckyMeta  = (SkullMeta) luckyBlock.getItemMeta();
		luckyMeta.setOwningPlayer(player);
		luckyMeta.setDisplayName("§6Lucky Block");
		luckyBlock.setItemMeta(luckyMeta);
		return luckyBlock;
	}
	
}
