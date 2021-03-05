package commandsee_v5_1;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHide implements CommandExecutor {
	static ArrayList<Location> hides = new ArrayList<>();
	static File path;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(args.length == 0) {
			return false;
		}
		switch (args[0]) {
			case "add": {
				File f = new File(path.getPath());
				if(!Main.FileRead(f).contains(args[1]+" "+args[2]+" "+args[3]+" "+args[4]))
					Main.FileWrite(f, Main.FileRead(f)+args[1]+" "+args[2]+" "+args[3]+" "+args[4]+"\n");
				load();
				break;
			}
			case "remove":{
				File f = new File(path.getPath());
					Main.FileWrite(f, Main.FileRead(f).replaceAll(args[1]+" "+args[2]+" "+args[3]+" "+args[4]+"\n", ""));
				load();
				break;
			}
			case "list": {
				load();
				for(Location b : hides) {
					sender.sendMessage(b.getX()+" "+b.getY()+" "+b.getZ()+" "+b.getWorld().getName());
				}
				break;
			}
		}
		return true;
		
		
	}
	
	public static void load() {
		path = Main.Hides;
		hides.clear();
		if(path.exists()) {
			String[] sa = Main.FileRead(path).split("\n");
			for(String s : sa) {
				String[] sar = s.split(" ");
				hides.add(new Location(Bukkit.getWorld(sar[3]), Integer.parseInt(sar[0]), Integer.parseInt(sar[1]), Integer.parseInt(sar[2])));
			}
		}else {Main.FileWrite(path, "0 0 0 world\n");}
		
	}

}
