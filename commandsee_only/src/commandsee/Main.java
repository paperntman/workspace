package commandsee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	public static ArrayList<ServerCommandEvent> event = new ArrayList<ServerCommandEvent>();
	public static ArrayList<Quest> quests = new ArrayList<Quest>();
	@Override
	public void onEnable() {
		getCommand("commandsee").setExecutor(new CommandSee());
		getCommand("commandsee").setTabCompleter(new CommandSeeTabCompleter());
		getCommand("commandsee").setLabel("test");
		Bukkit.getPluginManager().registerEvents(this,  this);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Repeat(), 0, 1);
	}
	@EventHandler
	public void onServerCommandEvent(ServerCommandEvent e) {
		event.add(e);
	}
	public static String FileRead(File f) {
		try {
			String re = "";
		FileReader file_reader = new FileReader(f);
        int cur = 0;
        while((cur = file_reader.read()) != -1){
        	re += (char)cur;
        }
        file_reader.close();
        return re;
       }catch (FileNotFoundException e) {
           e.getStackTrace();
       }catch(IOException e){
           e.getStackTrace();
       }
		return "";
	}
	public static void FileWrite(File f, String s) {
		try {
			FileWriter fw = new FileWriter(f, false);
			fw.write(s);
			fw.flush();
			fw.close();
		} catch (Exception e) {
	        e.getStackTrace();
		}
	}
}
