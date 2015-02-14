package me.jomens235;

import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class DutyAndKickMain extends JavaPlugin {

	private static DutyAndKickMain instance;

	public static DutyAndKickMain getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager()
				.registerEvents(new EventListener(), this);
		this.getCommand("duty").setExecutor(new DutyCommand());
		this.getCommand("caps").setExecutor(new CapsCommand());
		this.getCommand("lang").setExecutor(new LangCommand());
		this.getCommand("spam").setExecutor(new SpamCommand());
		instance = this;
	}
	
	@Override
	public void onDisable() {
		instance = null;
	}

	private HashMap<String, Boolean> players = new HashMap<String, Boolean>();
	private HashMap<Player, ItemStack[][]> items = new HashMap<Player, ItemStack[][]>();

	public boolean isOnDuty(Player player) {
		return players.containsKey(player.getName())
				&& players.get(player.getName());
	}

	public void setOnDuty(Player player, boolean duty) {
		players.put(player.getName(), duty);
		if (isOnDuty(player)) {
			items.put(player, new ItemStack[][] {
					player.getInventory().getContents(),
					player.getInventory().getArmorContents() });
		} else {
			ItemStack[][] inv = items.get(player);
			ItemStack[] contents = inv[0];
			ItemStack[] armor = inv[1];
			player.getInventory().setContents(contents);
			player.getInventory().setArmorContents(armor);
		}
	}
}