package executor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import 캘린더.Main;

public class Fly implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.hasPermission("tools.command.fly")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				p.sendMessage(p.getAllowFlight() ? Main.prefix+ChatColor.YELLOW+"이제 날 수 없습니다!" : Main.prefix+ChatColor.YELLOW+"이제 날 수 있습니다!");
				p.setAllowFlight(!p.getAllowFlight());
			}else {
				sender.sendMessage(Main.prefix+ChatColor.YELLOW+"플레이어만 사용할 수 있습니다.");
			}
		}else {
			sender.sendMessage(Main.prefix+ChatColor.YELLOW+"이런! 당신은 허겅영의 제자가 아니군요!");
		}
		
		return false;
	}

}
