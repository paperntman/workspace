package ppentity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;

public class PpentityTabCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		int leng = args.length %2;
		List<String> ret = new ArrayList<String>();
		if(leng == 1) {
			ret.addAll(Arrays.asList("-w", "-e", "-d", "-t"));
		}else {
			switch(args[args.length-2]) {
				case "-w":{
					for(World w : Bukkit.getWorlds()) {
						if(w.getName().toLowerCase().startsWith(args[args.length-1].toLowerCase()))
						ret.add(w.getName());
					}
					return ret;
				}
				case "-d":{
					ret.addAll(Arrays.asList("true", "false"));
					return ret;
				}
				case "-e":{

					for(EntityType e : EntityType.values()) {
						if(e.name().toLowerCase().contains(args[args.length-1].toLowerCase()))
						ret.add(e.name());
					}
					return ret;
				}
			}
		}
		return ret;
	}

}
