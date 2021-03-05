package commandsee_v5_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class CommandHideTabCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List<String> ret = new ArrayList<>();
		CommandHide.load();
		switch (args.length){
			case 1: {
				return Arrays.asList("add", "remove", "list");
			}
			case 2: {
				if(args[0].equalsIgnoreCase("remove"))
					for(Location l : CommandHide.hides) {
						if(!ret.contains((int)l.getX()+""))
							ret.add((int)l.getX()+"");
						}
				break;
			}
			case 3: {
				if(args[0].equalsIgnoreCase("remove"))
					for(Location l : CommandHide.hides) {
						if(!ret.contains((int)l.getY()+"") && (l.getX() == Integer.parseInt(args[1])))
							ret.add((int)l.getY()+"");
					}
				break;
			}
			case 4: {
				if(args[0].equalsIgnoreCase("remove"))
					for(Location l : CommandHide.hides) {
						if(!ret.contains((int)l.getY()+"") && (l.getX() == Integer.parseInt(args[1])) && (l.getY() == Integer.parseInt(args[2])))
							ret.add((int)l.getZ()+"");
					}
				break;
			}
			case 5: {
				if(args[0].equalsIgnoreCase("remove")) {
					for(Location l : CommandHide.hides) {
						if(!ret.contains(l.getWorld().getName()) && (l.getX() == Integer.parseInt(args[1])) && (l.getY() == Integer.parseInt(args[2])) && (l.getZ() == Integer.parseInt(args[3])))
							ret.add(l.getWorld().getName());
					}
				}else {
					for(World w : Bukkit.getWorlds()) {
						ret.add(w.getName());
					}
				}
				break;
			}
		}
		return ret;
	}

}
