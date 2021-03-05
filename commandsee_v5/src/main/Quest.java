package main;

import java.util.ArrayList;

import org.bukkit.World;
import org.bukkit.command.CommandSender;

import commandsee_v5_1.Ppcmd;

public class Quest {
	CommandSender sender;
	ArrayList<Ppcmd> type;
	ArrayList<World> world;
	ArrayList<String> command;
	ArrayList<String> player;
	public Quest(CommandSender a, ArrayList<Ppcmd> b, ArrayList<World> c, ArrayList<String> d) {
		sender = a;
		type = b;
		world = c;
		command = d;
	}
	public Quest(CommandSender a, ArrayList<Ppcmd> b, ArrayList<World> c, ArrayList<String> d, ArrayList<String> player2) {
		sender = a;
		type = b;
		world = c;
		command = d;
		player = player2;
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
	public ArrayList<String> getPlayer() {
		return player;
	}
}
