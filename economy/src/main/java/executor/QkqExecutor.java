package executor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import 캘린더.Main;

public class QkqExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("플레이어만 사용할 수 있습니다.");
			return true;
		}
		if(sender.hasPermission("tools.command.qkq")) {
			Player p = (Player) sender;
			p.setFoodLevel(20);
			sender.sendMessage(Main.prefix+ChatColor.YELLOW+"밥먹음");
		}else {
			sender.sendMessage(Main.prefix+ChatColor.RED+"당신은 이 명령어를 수행할 권한이 없습니다.");
		}
		return true;
	}

}
