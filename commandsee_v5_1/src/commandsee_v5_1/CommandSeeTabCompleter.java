package commandsee_v5_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class CommandSeeTabCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		int leng = args.length %2;
		List<String> ret = new ArrayList<String>();
		if(leng == 1) {
			ret.addAll(Arrays.asList("-w", "-c", "-s", "-p", "help"));
		}else {
			switch(args[args.length-2]) {
				case "-w":{
					for(World w : Bukkit.getWorlds()) {
						if(w.getName().toLowerCase().contains(args[args.length-1].toLowerCase()))
						ret.add(w.getName());
					}
					return ret;
				}
				case "-s":{
					for(String s : Arrays.asList("Impulse", "Repeat", "Chain", "Server", "CmdBlockMinecart")) {
						if(s.toLowerCase().contains(args[args.length-1].toLowerCase())) ret.add(s);
					}
					return ret;
				}
				case "-p":{

					for(OfflinePlayer w : Bukkit.getOfflinePlayers()) {
						if(w.getName().toLowerCase().contains(args[args.length-1].toLowerCase()))
						ret.add(w.getName());
					}
					return ret;
				}
				case "help":{
					for(String s : Arrays.asList("-w", "-s", "-p")) {
						if(s.toLowerCase().contains(args[args.length-1].toLowerCase())) ret.add(s);
					}
					return ret;
				}
			}
		}
		return ret;
	}

}
