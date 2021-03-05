package commandchange;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	List<Material> commands = Arrays.asList(Material.COMMAND_BLOCK, Material.REPEATING_COMMAND_BLOCK, Material.CHAIN_COMMAND_BLOCK);
	String path = getDataFolder()+File.separator; // commandchange/0_0_0
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		getDataFolder().mkdir();
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("read")) {
		}
		return true;
	}
	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && commands.contains(e.getClickedBlock().getType()) && e.getPlayer().isOp()) {
			Location l = e.getClickedBlock().getLocation();
			File f = new File(getDataFolder(), l.getX()+"_"+l.getY()+"_"+l.getZ());
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			FileWrite(f, e.getPlayer().getUniqueId().toString());
		}
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
			FileWriter fw = new FileWriter(f, true);
			fw.write(s);
			fw.flush();
			fw.close();
		} catch (Exception e) {
	        e.getStackTrace();
		}
	}
}
