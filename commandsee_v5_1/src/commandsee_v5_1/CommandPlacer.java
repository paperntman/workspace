package commandsee_v5_1;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class CommandPlacer implements Listener, CommandExecutor {
	static ArrayList<Placer> places = new ArrayList<>();
	static File path;
	List<Material> commands = Arrays.asList(Material.COMMAND_BLOCK, Material.REPEATING_COMMAND_BLOCK, Material.CHAIN_COMMAND_BLOCK);
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 0) {
			return false;
		}
		load();
		Location f = new Location(Bukkit.getWorld(args[3]), Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
		for(Placer p : places) {
			for(Location l : p.getLocation()) {
				if(l.equals(f)) {
					sender.sendMessage(p.getUUID().toString());
					try {
						sender.sendMessage(Bukkit.getOfflinePlayer(p.getUUID()).getName());
					}catch (Exception e) {
						return true;
					}
					return true;
				}
			}
		}
		sender.sendMessage("couldn't find player");
		return true;
	}
	
	@EventHandler
	public void onCommandBlockPlace(BlockPlaceEvent e) {
		Block b = e.getBlock();
		if(! commands.contains(b.getType())) return;
		File f = new File(path+File.separator+e.getPlayer().getUniqueId().toString()+".txt");
		Main.FileWrite(f, Main.FileRead(f)+b.getX()+" "+b.getY()+" "+b.getZ()+" "+b.getWorld().getName()+"\n");
	}
	
	@EventHandler
	public void onCommandBlockBreak(BlockBreakEvent e) {
		Block b = e.getBlock();
		if(! commands.contains(b.getType())) return;
		File f = new File(path+File.separator+e.getPlayer().getUniqueId().toString()+".txt");
		Main.FileWrite(f, Main.FileRead(f).replaceAll(b.getX()+" "+b.getY()+" "+b.getZ()+" "+b.getWorld().getName()+"\n", ""));
		if(Main.FileRead(f).isBlank()) f.delete();
	}
	
	public static void load() {
		places.clear();
		path = Main.Blocks;
		for(File f : path.listFiles()) {
			if(Main.FileRead(f).isBlank()) {
				f.delete();
				continue;
			}
			ArrayList<Location> l = new ArrayList<>();
			UUID u = UUID.fromString(f.getName().replace(".txt", ""));
			String[] sa = Main.FileRead(f).split("\n");
			for(String s : sa) {
				String[] sar = s.split(" ");
				try {
					l.add(new Location(Bukkit.getWorld(sar[3]), Integer.parseInt(sar[0]), Integer.parseInt(sar[1]), Integer.parseInt(sar[2])));
				} catch (Exception e) {
					System.out.println(f.getName() + " ");
					for(String sl : sar) {
						System.out.print(sl+" ");
					}
					System.out.println();
				}
			}
			places.add(new Placer(l, u));
		}
	}

}