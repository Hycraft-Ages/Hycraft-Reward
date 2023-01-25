package fr.hycraft.inventory;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.hycraft.Rewards;

public class InventoryRewards implements Listener
{

	private Inventory inventaire = Bukkit.createInventory(null, 54, "Menu Rewards");
	
	private String SPEED = "§3§lSpeed Tool";
	private String JUMP =  "§a§lJump Tool";
	private String FEED =  "§6§lFeed Tool";
	
	private String DESCRIPTION = "§7Clique pour acheter l'item";
	
	private ItemStack FEATHER = newItem(Material.FEATHER, SPEED, new String[] {"", DESCRIPTION});
	private ItemStack RABBIT = newItem(Material.RABBIT_FOOT, JUMP, new String[] {"", DESCRIPTION});
	private ItemStack STEACK = newItem(Material.COOKED_BEEF, FEED, new String[] {"", DESCRIPTION});
	
	private ItemStack FILLER = newItem(Material.BLUE_STAINED_GLASS_PANE, " ", null);
	private ItemStack FILLER_CARRE = newItem(Material.LIME_STAINED_GLASS_PANE, " ", null);
	
	private ItemStack NEXT_PAGE = newItem(Material.ARROW, "suivante", new String[]{"Clique pour passer", "à la page suivante."});
	//private static ItemStack BACK_PAGE = newItem(Material.ARROW, "precedente", new String[]{"Clique pour retourner", "à la page d'avant."});
	private ItemStack MONEY = newItem(Material.GOLD_INGOT, "§7Argent:", new String[] {"", "§e{le nombre de money}"});

	private int[] slots_carre = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53 };
	
	
	public void openInventory(Player player)
	{
		
		for (int fill = 0; fill < 53; fill++)
		{
			inventaire.setItem(fill, FILLER);
		}
		
		for (int slot_carre : slots_carre)
		{
			inventaire.setItem(slot_carre, FILLER_CARRE);
		}
		
		inventaire.setItem(21, FEATHER);
		inventaire.setItem(22, RABBIT);
		inventaire.setItem(23, STEACK);
		
		inventaire.setItem(48, NEXT_PAGE);
		inventaire.setItem(50, MONEY);
		
		player.openInventory(inventaire);
	}

	@EventHandler
	public void onClickInventory(InventoryClickEvent event)
	{
		ItemStack clickedItem = event.getCurrentItem();
		
		if (event.getView().getTitle() == "Menu Rewards")
		{
			Player player = (Player) event.getWhoClicked();
			
			event.setCancelled(true);
			
			if (event.getClickedInventory() == null || clickedItem == null) return;
			
			switch (event.getSlot())
			{
				case 21:
					giveItem(event, player, 21, FEATHER, SPEED, new String[] { "", "Clique pour emporter par un vent", "de vitesse !" });
					break;
					
				case 22:
					giveItem(event, player, 22, RABBIT, JUMP, new String[] { "", "clique pour pouvoir", "te projeter dans", "les airs comme", "un kangourou !" });
					break;
					
				case 23:
					giveItem(event, player, 23, STEACK, FEED, new String[] { "", "Clique pour te", "rassasier !" });
					break;
			}
		}
	}
	
	private void giveItem(InventoryClickEvent event, Player player, int slot, ItemStack item, String itemName, String[] lore)
	{
		ItemMeta meta = item.getItemMeta();
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		player.getInventory().addItem(item);
		player.sendMessage(Rewards.PREFIX + "§7Tu as acheté le " + itemName);
	}
	
	private ItemStack newItem(Material material, String name, String[] lore)
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
