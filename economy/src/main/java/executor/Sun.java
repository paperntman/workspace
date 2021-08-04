package executor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import 캘린더.Main;

public class Sun implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.hasPermission("tools.command.sun")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				World w = p.getWorld();
				w.setStorm(false);
				w.setThundering(false);
				w.setWeatherDuration(Integer.MAX_VALUE);
				sender.sendMessage(Main.prefix+ChatColor.YELLOW+"날씨를 맑아지게 하였습니다.");
			}else {
				for(World w : Bukkit.getWorlds()) {
					w.setStorm(false);
					w.setThundering(false);
					w.setWeatherDuration(Integer.MAX_VALUE);
				}
				sender.sendMessage(Main.prefix+ChatColor.YELLOW+"모든 월드의 날씨를 맑아지게 하였습니다.");
			}
		}else {
			sender.sendMessage(Main.prefix+ChatColor.YELLOW+"당신은 이 명령어를 수행할 권한이 없습니다.");
		}
		return true;
	}

}
