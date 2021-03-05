package commandsee_v5_1;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.command.CraftBlockCommandSender;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftMinecartCommand;
import org.bukkit.event.server.ServerCommandEvent;

public class Repeat implements Runnable{
	@Override
	public void run() {
		for(Quest q : Main.quests) {
			CommandSender sender = q.getSender();
			ArrayList<Ppcmd> type = q.getType();
			ArrayList<World> world = q.getWorld();
			ArrayList<String> command = q.getCommand();
			ArrayList<String> player = q.getPlayer();
			if(player == null) {
				int count = 0;
				int wcount = 0;
				World current = null;
				for(ServerCommandEvent e : Main.event) {
					if(current != getWorld(e)) {
						if(getWorld(e) != null) {
							if(current != null)
								sender.sendMessage(wcount+" commands detected in "+ current.getName());
							sender.sendMessage("-----"+getWorld(e).getName()+"-----");
							current = getWorld(e);
							wcount = 0;
						}
					}
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
					if (getWorld(e) == null) {
						sender.sendMessage("[Server] "+e.getCommand());
					}else {
						Location l = getLocation(e);
						if(CommandHide.hides.contains(l)) continue;
						sender.sendMessage("["+l.getWorld().getName()+" "+Math.round(l.getX())+" "+Math.round(l.getY())+" "+Math.round(l.getZ())+"] "+e.getCommand());
						wcount++;
					}
					count++;
				}
				sender.sendMessage(wcount+" commands detected in "+ current.getName());
				sender.sendMessage("totally "+count+" commands detected");
			}else {
				CommandPlacer.load();
				for(String p : player) {
					for(Placer pl : CommandPlacer.places) {
						if(Bukkit.getPlayer(pl.getUUID()) != null) {
							if(p.equalsIgnoreCase(Bukkit.getPlayer(pl.getUUID()).getName())) {
								for(Location l : pl.getLocation()) {
									CommandBlock b = (CommandBlock) l.getBlock().getState();
									if(!type.isEmpty()) {
										Ppcmd t = getType(l);
										if(!type.contains(t)) continue;
									}
									if(!world.isEmpty()) {
										World w = l.getWorld();
										if(!(w == null)) {
											if(!world.contains(w)) continue;
										}
									}
									if(!command.isEmpty()) {
										boolean con = false;
										for(String s : command) {
											if(!b.getCommand().contains(s)) con = true;
										}
										if(con) continue;
									}
									if(CommandHide.hides.contains(l)) continue;
									sender.sendMessage("["+l.getWorld().getName()+" "+Math.round(l.getX())+" "+Math.round(l.getY())+" "+Math.round(l.getZ())+"] "+b.getCommand());
								}
							}
						}else {
							sender.sendMessage("없는 플레이어입니다!");
							break;
						}
					}
				}
			}
		}
		Main.quests.clear();
		Main.event.clear();
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
}
