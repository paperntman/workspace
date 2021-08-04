package completer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class CalenderTabCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender cs, Command arg1, String arg2, String[] args) {
		List<String> ret = new ArrayList<String>();
		if(arg1.getName().equalsIgnoreCase("calender")) {
			int leng = args.length;
			if(leng == 1) {
				for(String s : Arrays.asList("get", "set")) {
					if(s.toLowerCase().startsWith(args[0])) ret.add(s);
				}
			}else if(leng == 2 && args[0].equalsIgnoreCase("set")) {
				for(String s : Arrays.asList("BC", "AC")) {
					if(s.toUpperCase().startsWith(args[1])) ret.add(s);
				}
			}
		}
		return ret;
	}

}
