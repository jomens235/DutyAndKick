package me.jomens235;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener {

	private HashMap<Player, ItemStack[]> inv = new HashMap<Player, ItemStack[]>();
	private HashMap<Player, ItemStack[]> armor = new HashMap<Player, ItemStack[]>();

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		if (DutyAndKickMain.getInstance().isOnDuty(player)
				&& player.hasPermission("dutyandkick.clear")) {
			DutyAndKickMain.getInstance().setOnDuty(player, false);
			inv.put(player, player.getInventory().getContents());
			armor.put(player, player.getInventory().getArmorContents());
			event.getDrops().clear();
			Bukkit.broadcastMessage(ChatColor.RED + "Duty"
					+ ChatColor.DARK_GRAY + ChatColor.BOLD + "> "
					+ ChatColor.RED + player.getName() + ChatColor.GRAY
					+ " has died on duty!");
		}
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		if (DutyAndKickMain.getInstance().isOnDuty(player)) {
			player.getInventory().setContents(inv.get(player));
			player.getInventory().setArmorContents(armor.get(player));
			inv.remove(player);
			armor.remove(player);
		} else {
			if (!DutyAndKickMain.getInstance().isOnDuty(player)) {
				
			}
		}
	}

	@EventHandler
	public void onDisconnect(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if (DutyAndKickMain.getInstance().isOnDuty(player)) {
			DutyAndKickMain.getInstance().setOnDuty(player, false);
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if (DutyAndKickMain.getInstance().isOnDuty(player)) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onMove(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if (DutyAndKickMain.getInstance().isOnDuty(player)) {
			if (event.getClick() != null) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPickup(PlayerPickupItemEvent event) {
		Player player = event.getPlayer();
		if (DutyAndKickMain.getInstance().isOnDuty(player)) {
			if (event.getItem().getItemStack().getType()
					.equals(Material.DIAMOND_SWORD)) {
				event.setCancelled(false);
				player.sendMessage(ChatColor.BLUE
						+ "You have picked up a diamond sword!");
			} else if (event.getItem().getItemStack().getType()
					.equals(Material.IRON_SWORD)) {
				event.setCancelled(false);
				player.sendMessage(ChatColor.GRAY
						+ "You have picked up a iron sword!");
			} else if (event.getItem().getItemStack().getType()
					.equals(Material.GOLD_SWORD)) {
				event.setCancelled(false);
				player.sendMessage(ChatColor.YELLOW
						+ "You have picked up a gold sword!");
			} else if (event.getItem().getItemStack().getType()
					.equals(Material.STONE_SWORD)) {
				event.setCancelled(false);
				player.sendMessage(ChatColor.GRAY
						+ "You have picked up a stone sword!");
			} else if (event.getItem().getItemStack().getType()
					.equals(Material.WOOD_SWORD)) {
				event.setCancelled(false);
				player.sendMessage(ChatColor.DARK_GREEN
						+ "You have picked up a wood sword!");
			} else if (event.getItem().getItemStack().getType()
					.equals(Material.BOW)) {
				event.setCancelled(false);
				player.sendMessage(ChatColor.DARK_GREEN
						+ "You have picked up a bow!");
			} else if (event.getItem().getItemStack().getType()
					.equals(Material.FIREBALL)) {
				event.setCancelled(false);
				player.sendMessage(ChatColor.RED
						+ "You have picked up a fire charge!");
			} else if (event.getItem().getItemStack().getType()
					.equals(Material.LAVA_BUCKET)) {
				event.setCancelled(false);
				player.sendMessage(ChatColor.RED
						+ "You have picked up a lava bucket!");
			} else if (event.getItem().getItemStack().getType()
					.equals(Material.LAVA)) {
				event.setCancelled(false);
				player.sendMessage(ChatColor.RED + "You have picked up lava!");
			} else if (event.getItem().getItemStack().getType()
					.equals(Material.FISHING_ROD)) {
				event.setCancelled(false);
				player.sendMessage(ChatColor.WHITE
						+ "You have picked up a fishing rod!");
			} else if (event.getItem().getItemStack().getType()
					.equals(Material.FLINT_AND_STEEL)) {
				event.setCancelled(false);
				player.sendMessage(ChatColor.DARK_GRAY
						+ "You have picked up flint and steel!");
			} else if (event.getItem().getItemStack().getType()
					.equals(Material.DIAMOND_AXE)) {
				event.setCancelled(false);
				player.sendMessage(ChatColor.AQUA
						+ "You have picked up a diamond axe!");
			} else if (event.getItem().getItemStack().getType()
					.equals(Material.IRON_AXE)) {
				event.setCancelled(false);
				player.sendMessage(ChatColor.GRAY
						+ "You have picked up a iron axe!");
			} else if (event.getItem().getItemStack().getType()
					.equals(Material.GOLD_AXE)) {
				event.setCancelled(false);
				player.sendMessage(ChatColor.YELLOW
						+ "You have picked up a gold axe!");
			} else if (event.getItem().getItemStack().getType()
					.equals(Material.STONE_AXE)) {
				event.setCancelled(false);
				player.sendMessage(ChatColor.GRAY
						+ "You have picked up a stone axe!");
			} else if (event.getItem().getItemStack().getType()
					.equals(Material.WOOD_AXE)) {
				event.setCancelled(false);
				player.sendMessage(ChatColor.DARK_GREEN
						+ "You have picked up a wooden axe!");
			} else {
				event.setCancelled(true);
			}
		}

	}
}
