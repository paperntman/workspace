package executor;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NameExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.hasPermission("tools.command.name")) {
			Player p = Bukkit.getPlayer(args[0]);
			String name = args[1];
			p.setDisplayName(name);
		}
		return true;
	}

}
