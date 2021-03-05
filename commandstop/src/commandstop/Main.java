package commandstop;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	boolean stop = false;
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("commandstop")) {
			sender.sendMessage(String.valueOf(!stop));
			stop = !stop;
		}
		return true;
	}
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this,  this);
	}
	@EventHandler
	public void onCommandEvent(ServerCommandEvent e) {
		if(!e.getSender().toString().contains("Console"))
		e.setCancelled(stop);
	}
}
