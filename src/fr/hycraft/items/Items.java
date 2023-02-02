package fr.hycraft.items;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Items
{
	
	private SkullMeta SKULLMETA;

	public SkullMeta getSkullMeta()
	{
		return SKULLMETA;
	}
	
	public ItemStack newItem(Material material, String name, String[] lore)
	{
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		if (!(lore == null)) meta.setLore(Arrays.asList(lore));
		meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		item.setItemMeta(meta);
		return item;
	}
}
