package main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	public static ArrayList<ServerCommandEvent> event = new ArrayList<ServerCommandEvent>();
	public static ArrayList<Location> hides = new ArrayList<Location>();
	public static ArrayList<Quest> quests = new ArrayList<Quest>();
	@Override
	public void onEnable() {
		getCommand("commandsee").setExecutor(new CommandSee());
		getCommand("commandHide").setExecutor(new CommandHide());
		getCommand("CommandPlacer").setExecutor(new CommandPlacer());
		Bukkit.getPluginManager().registerEvents(new CommandEvent(), this);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this,  new Repeat(), 0, 1);
	}
}
