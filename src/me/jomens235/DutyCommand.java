package me.jomens235;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DutyCommand implements CommandExecutor{
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(player.hasPermission("dutyandkick.duty")){
			// Reverses the state: OnDuty <-> OffDuty
			DutyAndKickMain.getInstance().setOnDuty(player, !DutyAndKickMain.getInstance()
					.isOnDuty(player));
			if(DutyAndKickMain.getInstance().isOnDuty(player)) {
				player.getInventory().clear();
				player.getInventory().setArmorContents(null);
				Bukkit.getServer().broadcastMessage(ChatColor.RED + "Duty" + ChatColor.DARK_GRAY
						+ ChatColor.BOLD + "> " + ChatColor.RED + player.getName()
						+ ChatColor.GRAY + " is now on duty and ready to help.");
				//This is the Sword with enchants.
				String displayName = ChatColor.GOLD + "Guard Sword";
				ItemStack item = new ItemStack (Material.DIAMOND_SWORD);
				ItemMeta im = item.getItemMeta();
				im.setDisplayName(displayName);
				item.setItemMeta(im);
				item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 6);
				item.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
				item.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 4);
				player.getInventory().addItem(item);
				//This is the Helmet with enchants.
				String displayName1 = ChatColor.GOLD + "Guard Helmet";
				ItemStack helmet = new ItemStack (Material.DIAMOND_HELMET);
				ItemMeta ime = helmet.getItemMeta();
				ime.setDisplayName(displayName1);
				helmet.setItemMeta(ime);
				helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
				helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
				player.getInventory().setHelmet(helmet);
				//This is the Chestplate with enchants.
				String displayName2 = ChatColor.GOLD + "Guard Chestplate";
				ItemStack chestplate = new ItemStack (Material.DIAMOND_CHESTPLATE);
				ItemMeta imet = chestplate.getItemMeta();
				imet.setDisplayName(displayName2);
				chestplate.setItemMeta(imet);
				chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
				chestplate.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
				player.getInventory().setChestplate(chestplate);
				//These are the Leggings with enchants.
				String displayName3 = ChatColor.GOLD + "Guard Leggings";
				ItemStack legging = new ItemStack (Material.DIAMOND_LEGGINGS);
				ItemMeta imeta = legging.getItemMeta();
				imeta.setDisplayName(displayName3);
				legging.setItemMeta(imeta);
				legging.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
				legging.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
				player.getInventory().setLeggings(legging);
				//These are the Boots with enchants.
				String displayName4 = ChatColor.GOLD + "Guard Boots";
				ItemStack boots = new ItemStack (Material.DIAMOND_BOOTS);
				ItemMeta imeta1 = boots.getItemMeta();
				imeta1.setDisplayName(displayName4);
				boots.setItemMeta(imeta1);
				boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
				boots.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
				player.getInventory().setBoots(boots);
				//This is the Bow with enchants, KB 3 stick, an arrow, and 10 OP apples and FOOD.
				String displayName5 = ChatColor.GOLD + "Guard Bow";
				ItemStack bow = new ItemStack (Material.BOW);
				ItemMeta imeta2 = bow.getItemMeta();
				imeta2.setDisplayName(displayName5);
				bow.setItemMeta(imeta2);
				bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 6);
				bow.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 3);
				bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
				bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 3);
				bow.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
				player.getInventory().addItem(bow);
				String displayName6 = ChatColor.GOLD + "Guard Stick";
				ItemStack stick = new ItemStack (Material.STICK);
				ItemMeta imeta3 = stick.getItemMeta();
				imeta3.setDisplayName(displayName6);
				stick.setItemMeta(imeta3);
				stick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
				player.getInventory().addItem(stick);
				player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 10, (short)1));
				player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 32));
				player.getInventory().addItem(new ItemStack(Material.ARROW, 1));
			} else {
				Bukkit.getServer().broadcastMessage(ChatColor.RED + "Duty" + ChatColor.DARK_GRAY
						+ ChatColor.BOLD + "> " + ChatColor.RED + player.getName()
						+ ChatColor.GRAY + " is now off duty.");
			}
		}else{
			player.sendMessage(ChatColor.RED + "You do not have permission.");
		}

		return true;
	}
}