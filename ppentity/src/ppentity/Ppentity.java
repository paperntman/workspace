package ppentity;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class Ppentity implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		ArrayList<World> world = new ArrayList<>();
		ArrayList<EntityType> entity = new ArrayList<>();
		ArrayList<String> tag = new ArrayList<>();
		boolean detail = false;
		if(args.length > 0)
			for(int i = 0; i < args.length-1; i+=2) {
				switch (args[i]) {
				case "-w":
					world.add(Bukkit.getWorld(args[i+1]));
					break;
				case "-e":
					entity.add(EntityType.valueOf(args[i+1]));
					break;
				case "-t":
					tag.add(args[i+1]);
					break;
				case "-d":
					detail = Boolean.valueOf(args[i+1]);
					break;
				default:
					return true;
				}
			}
		if(world.isEmpty()) world.addAll(Bukkit.getWorlds());
		if(entity.isEmpty()) entity.addAll(Arrays.asList(EntityType.values()));
		if(detail) {
			for(World w : world) {
				sender.sendMessage("-----"+w.getName()+"-----");
				int wcount = 0;
				for(Entity e : w.getEntities()) {
					if(entity.contains(e.getType())) {
						boolean con = false;
						if(!tag.isEmpty())
						for(String s : tag) {
							if(e.getScoreboardTags().contains(s)) con = true;
						}
						if(con) continue;
						wcount++;
						Location l = e.getLocation();
						sender.sendMessage(e.getName()+"("+e.getType()+") in "+w.getName()+" "+l.getX()+" "+l.getY()+" "+l.getZ());
					}
				}
				sender.sendMessage(wcount+" of entities detected in "+w.getName());
			}
		}else {
			for(World w : world) {
				sender.sendMessage("-----"+w.getName()+"-----");
				int wcount = 0;
				for(EntityType et : entity) {
					int count = 0;
					for(Entity e : w.getEntities()) {
						if(e.getType() != et) continue;
						boolean con = false;
						if(!tag.isEmpty())
						for(String s : tag) {
							if(!e.getScoreboardTags().contains(s)) con = true;
						}
						if(con) continue;
						count++;
					}
					if(count != 0)
					sender.sendMessage(count+" of "+et.name());
					wcount += count;
				}
				sender.sendMessage(wcount+" of entities detected in "+w.getName());
			}
		}
		return true;
	}

}
