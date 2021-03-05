package commandsee;

import java.util.ArrayList;

import org.bukkit.World;
import org.bukkit.command.CommandSender;

public class Quest {
	CommandSender sender;
	ArrayList<Ppcmd> type;
	ArrayList<World> world;
	ArrayList<String> command;
	int range;
	public Quest(CommandSender a, ArrayList<Ppcmd> b, ArrayList<World> c, ArrayList<String> d, int e) {
		sender = a;
		type = b;
		world = c;
		command = d;
		range = e;
	}
	public ArrayList<String> getCommand() {
		return command;
	}
	public CommandSender getSender() {
		return sender;
	}
	public ArrayList<Ppcmd> getType() {
		return type;
	}
	public ArrayList<World> getWorld() {
		return world;
	}
	public int getRange() {
		return range;
	}
}
