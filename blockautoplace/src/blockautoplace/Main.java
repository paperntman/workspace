package blockautoplace;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	boolean BlockPlace = false;
	
	@Override
	public void onEnable() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this,  new Runnable() {
			
			@Override
			public void run() {
				if(BlockPlace) {
					for(Player p : Bukkit.getOnlinePlayers()) {
						Block l = p.getTargetBlock(null, 5);
						
					}
				}
			}
		}, 0, 1);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("bp")) {
			if(sender.isOp()) {
				BlockPlace = !BlockPlace;
				sender.sendMessage(String.valueOf(BlockPlace));
			}
		}
		return true;
	}

}
