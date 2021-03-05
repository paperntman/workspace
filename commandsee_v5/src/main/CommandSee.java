package main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import commandsee_v5_1.Ppcmd;

public class CommandSee implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(args.length==0) {
			return help(sender);
		}
		ArrayList<String> player = new ArrayList<>();
		ArrayList<World> world = new ArrayList<>();
		ArrayList<Ppcmd> cmd = new ArrayList<>();
		ArrayList<String> text = new ArrayList<>();
		for(int i = 1; i < args.length-1; i+=2) {
			switch (args[i]) {
			case "-w":
				world.add(Bukkit.getWorld(args[i+1]));
				break;
			case "-s":
				cmd.add(Ppcmd.valueOf(args[i+1]));
				break;
			case "-c":
				text.add(args[i+1]);
				break;
			case "-p":
				player.add(args[i+1]);
			case "help":
				return help(sender);
			}
		}
		Quest q = null;
		if(player.isEmpty())
		q = new Quest(sender, cmd, world, text);
		else q = new Quest(sender, cmd, world, text, player);
		Main.quests.add(q);
		return true;
	}
	boolean help(CommandSender a) {
		a.sendMessage("");
		return true;
	}

}
