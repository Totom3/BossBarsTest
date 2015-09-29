package io.github.totom3.bossbarstest;

import io.github.totom3.bossbars.BossBarsAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Totom3
 */
public class Main extends JavaPlugin {

    public Main() {
    }

    @Override
    public void onEnable() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	if (!(sender instanceof Player)) {
	    sender.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + "only players can use this command!");
	    return true;
	}

	if (args.length > 2) {
	    return false;
	}

	Player player = (Player) sender;
	if (args.length == 0) {
	    BossBarsAPI.remove(player);
	    player.sendMessage(ChatColor.GREEN + "Removed your boss bar.");
	    return true;
	}

	String msg = ChatColor.translateAlternateColorCodes('&', args[0]);
	float percent = 100;

	if (args.length == 2) {
	    try {
		percent = Float.parseFloat(args[1]);
	    } catch (NumberFormatException ex) {
		return false;
	    }
	}

	if (percent < 0 || percent > 100) {
	    player.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + "percent must be between 0 and 100 (both inclusive)");
	    return true;
	}

	BossBarsAPI.set(player, msg, percent);
	player.sendMessage(ChatColor.GREEN + "Updated your boss bar!");
	return true;
    }
}
