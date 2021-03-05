package commandsee_v5_1;

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

import ppentity.Ppentity;
import ppentity.PpentityTabCompleter;

public class Main extends JavaPlugin implements Listener{
	public static ArrayList<ServerCommandEvent> event = new ArrayList<ServerCommandEvent>();
	public static ArrayList<Quest> quests = new ArrayList<Quest>();
	public static File Hides;
	public static File Blocks;
	@Override
	public void onEnable() {
		getCommand("commandsee").setExecutor(new CommandSee());
		getCommand("commandplacer").setExecutor(new CommandPlacer());
		getCommand("commandhide").setExecutor(new CommandHide());
		getCommand("commandsee").setTabCompleter(new CommandSeeTabCompleter());
		getCommand("commandplacer").setTabCompleter(new CommandPlacerTabCompleter());
		getCommand("commandhide").setTabCompleter(new CommandHideTabCompleter());
		getCommand("ppentity").setExecutor(new Ppentity());
		getCommand("ppentity").setTabCompleter(new PpentityTabCompleter());
		Bukkit.getPluginManager().registerEvents(new CommandPlacer(), this);
		Bukkit.getPluginManager().registerEvents(this,  this);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Repeat(), 0, 1);
		Blocks = new File(getDataFolder().getAbsolutePath()+File.separator+"Blocks");
		Hides = new File(getDataFolder().getAbsolutePath()+File.separator+"Hides.txt");
		getDataFolder().mkdir();
		Blocks.mkdir();
		CommandPlacer.load();
		CommandHide.load();
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
