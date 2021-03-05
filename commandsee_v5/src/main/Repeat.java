package main;

import java.util.ArrayList;

import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.event.server.ServerCommandEvent;

import commandsee_v5_1.Ppcmd;

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
				for(ServerCommandEvent e : Main.event) {
					
				}
			}else {
				
			}
		}
		Main.quests.clear();
		Main.event.clear();
	}
	
}
