package commandsee_v5_1;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class CommandPlacerTabCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List<String> ret = new ArrayList<>();
		CommandPlacer.load();
		switch (args.length){
			case 1: {
				for(Placer p : CommandPlacer.places) {
					for(Location l : p.getLocation()) {
						if(!ret.contains((int)l.getX()+""))
						ret.add((int)l.getX()+"");
					}
				}
				break;
			}
			case 2: {
				for(Placer p : CommandPlacer.places) {
					for(Location l : p.getLocation()) {
						if(!ret.contains((int)l.getY()+""))
						ret.add((int)l.getY()+"");
					}
				}
				break;
			}
			case 3: {
				for(Placer p : CommandPlacer.places) {
					for(Location l : p.getLocation()) {
						if(!ret.contains((int)l.getZ()+""))
						ret.add((int)l.getZ()+"");
					}
				}
				break;
			}
			case 4: {
			}
			for(Placer p : CommandPlacer.places) {
				for(Location l : p.getLocation()) {
					if(!ret.contains(l.getWorld().getName()))
					ret.add(l.getWorld().getName());
				}
				break;
			}
		}
		return ret;
	}

}
