package me.jomens235;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LangCommand implements CommandExecutor {

	private HashMap<String, Long> kicked = new HashMap<String, Long>();

	public void addCooldown(String player, int seconds) {
		kicked.put(player, System.currentTimeMillis() + seconds * 1000);
	}

	public boolean hasCooldown(String player) {
		return kicked.containsKey(player)
				&& kicked.get(player) >= System.currentTimeMillis();
	}

	public String cooldownTimeLeft(String player) {
		long left = (kicked.get(player) - System.currentTimeMillis()) / 1000L;
		if (left < 60) {
			return left + " seconds";
		}
		return left / 60 + " minute(s)";
	}

	String title = ChatColor.DARK_RED + "Kick" + ChatColor.DARK_GRAY
			+ ChatColor.BOLD + "> ";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (args.length < 1) {
			return false;
		}
		Player kicker = (Player) sender;
		Player target = Bukkit.getPlayerExact(args[0]);
		if (sender instanceof Player) {
			if (target == null) {
				sender.sendMessage(ChatColor.DARK_RED + "/lang [player]");
			}
		}
		if (args.length == 1) { // "/lang [player]"
			if (target != null) {
				Player player = (Player) sender;
				if (hasCooldown(target.getName())) {
					player.sendMessage(ChatColor.GREEN
							+ "This player has already been kicked for language!"
							+ " Choose a different punishment!");
					player.sendMessage(ChatColor.RED
							+ "This player can be kicked again in "
							+ cooldownTimeLeft(target.getName()));
				} else {
					addCooldown(target.getName(), 43200);
					target.kickPlayer(ChatColor.RED
							+ "You have been kicked for foul language.");
					Bukkit.broadcastMessage(title + ChatColor.RED
							+ target.getName()
							+ " has been kicked for excessive language.");
					kicker.sendMessage(ChatColor.RED + "You have kicked "
							+ target.getName() + " for foul language!");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Player not found.");
			}
		}
		return true;
	}
}
