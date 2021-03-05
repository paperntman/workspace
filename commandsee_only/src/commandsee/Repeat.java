package commandsee;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.command.CraftBlockCommandSender;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftMinecartCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.server.ServerCommandEvent;

public class Repeat implements Runnable{
	@Override
	public void run() {
		for(Quest q : Main.quests) {
			CommandSender sender = q.getSender();
			ArrayList<Ppcmd> type = q.getType();
			ArrayList<World> world = q.getWorld();
			ArrayList<String> command = q.getCommand();
			int range = q.getRange();
			ArrayList<World> outputWorld = new ArrayList<World>();
			ArrayList<ServerCommandEvent> outputEvent = new ArrayList<ServerCommandEvent>();
			boolean isNullEventExists = false;
				for(ServerCommandEvent e : Main.event) {
					if(!type.isEmpty()) {
						Ppcmd t = getType(e);
						if(!type.contains(t)) continue;
					}
					if(!world.isEmpty()) {
						World w = getWorld(e);
						if(!(w == null)) {
							if(!world.contains(w)) continue;
						}
					}
					if(!command.isEmpty()) {
						boolean con = false;
						for(String s : command) {
							if(!e.getCommand().contains(s)) con = true;
						}
						if(con) continue;
					}
					if(range > 0 && getWorld(e) == null) continue;
					if(range > 0) {
						Player p = (Player) sender;
						if(p.getLocation().getWorld() != getLocation(e).getWorld()) continue;
						if((int) p.getLocation().distance(getLocation(e)) > range) continue;
					} 
					outputEvent.add(e);
					if(getWorld(e) != null && !outputWorld.contains(getWorld(e))) outputWorld.add(getWorld(e));
					if(getWorld(e) == null) isNullEventExists = true;
				}
				if(isNullEventExists) {
					for(ServerCommandEvent e : outputEvent) {
						if(getWorld(e) == null) {
							sender.sendMessage(output(e));
						}
					}
				}
				int count = 0;
				for(World w : outputWorld) {
					int wcount = 0;
					sender.sendMessage("-----"+w.getName()+"-----");
					for(ServerCommandEvent e : outputEvent) {
						if(getWorld(e) != null) {
							if(getWorld(e).equals(w)) {
								sender.sendMessage(output(e));
								wcount++;
							}
						}
					}
					sender.sendMessage(wcount+" of commands detected in "+w.getName());
					count += wcount;
				}
				sender.sendMessage("totally "+count+" commands detected");
			}
		Main.event.clear();
		Main.quests.clear();
		}
	
	public Ppcmd getType(ServerCommandEvent e) {
		if(e.getSender().toString().contains("CraftBlockCommandSender")) {
			CraftBlockCommandSender b = (CraftBlockCommandSender) e.getSender();
			if(b.getBlock().getType().equals(Material.REPEATING_COMMAND_BLOCK)) return Ppcmd.Repeat;
			else if(b.getBlock().getType().equals(Material.CHAIN_COMMAND_BLOCK)) return Ppcmd.Chain;
			else return Ppcmd.Impulse;
		}else if(e.getSender().toString().contains("CraftMinecartCommand")) {
			return Ppcmd.CmdBlockMinecart;
		}
		return Ppcmd.Server;
	}
	public Ppcmd getType(Location l) {
		if(l.getBlock().getType().equals(Material.REPEATING_COMMAND_BLOCK)) return Ppcmd.Repeat;
		else if(l.getBlock().getType().equals(Material.CHAIN_COMMAND_BLOCK)) return Ppcmd.Chain;
		else return Ppcmd.Impulse;
	}
	public World getWorld(ServerCommandEvent e) {
		Ppcmd type = getType(e);
		if(type.equals(Ppcmd.Server)) return null;
		else if(type.equals(Ppcmd.CmdBlockMinecart)) {
			CraftMinecartCommand b = (CraftMinecartCommand) e.getSender();
			return b.getWorld();
		}else {
			CraftBlockCommandSender b = (CraftBlockCommandSender) e.getSender();
			return b.getBlock().getWorld();
		}
	}
	public Location getLocation(ServerCommandEvent e) {

		if(getType(e).equals(Ppcmd.Server)) return null;
		else {
			if(getType(e).equals(Ppcmd.CmdBlockMinecart)) {
				CraftMinecartCommand b = (CraftMinecartCommand) e.getSender();
				return b.getLocation();
			}else {
				CraftBlockCommandSender b = (CraftBlockCommandSender) e.getSender();
				return b.getBlock().getLocation();
			}
		}
	}
	public String output(ServerCommandEvent e) {
		if(getWorld(e) == null) {
			return "["+getType(e)+"] "+e.getCommand();
		}else {
			Location l = getLocation(e);
			return "["+l.getWorld().getName()+" "+(int)l.getX()+" "+(int)l.getY()+" "+(int)l.getZ()+"] "+e.getCommand();
		}
	}
}
