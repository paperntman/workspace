package 캘린더;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class TabList extends BukkitRunnable {

	@Override
	public void run() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			String header = ChatColor.DARK_GRAY+"=========="+"\n\n"+ChatColor.WHITE+Main.servername+"\n\n"+ChatColor.DARK_GRAY+"==========\n\n"+ChatColor.RED+ChatColor.BOLD+"접속자 수 : "+ChatColor.WHITE+Bukkit.getOnlinePlayers().size()+ChatColor.DARK_GRAY+"\n\n==========\n";
			String footer = "\n"+ChatColor.DARK_GRAY+"=========="+"\n\n"+ChatColor.GOLD+ChatColor.BOLD+"돈 : "+ChatColor.WHITE+"<숫자>"+"\n\n"+ChatColor.DARK_GRAY+"==========";
			p.setPlayerListHeaderFooter(header, footer);
		}
	}

}