package 캘린더;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

import completer.CalenderTabCompleter;
import completer.NameTabCompleter;
import executor.CalenderExecutor;
import executor.Fly;
import executor.NameExecutor;
import executor.QkqExecutor;
import executor.Sun;

public class Main extends JavaPlugin{
	
	public static File getDataFolder;
    public static String servername;
    public static String prefix = ChatColor.DARK_GRAY+"["+ChatColor.DARK_RED+"tools"+ChatColor.DARK_GRAY+"] ";
    File configfile;
	
	@Override
	public void onEnable() {
	    getDataFolder = getDataFolder();
		getCommand("calender").setTabCompleter(new CalenderTabCompleter());
		getCommand("calender").setExecutor(new CalenderExecutor());
		getCommand("qkq").setExecutor(new QkqExecutor());
		getCommand("sun").setExecutor(new Sun());
		getCommand("fly").setExecutor(new Fly());
		getCommand("name").setExecutor(new NameExecutor());
		getCommand("name").setTabCompleter(new NameTabCompleter());
		new TabList().runTaskTimer(this, 0, 20);
		configfile = new File(getDataFolder.getPath()+File.separator+"config.yml");
		try {
			if(!configfile.exists() || new FileReader(configfile).read() == -1) {
				try {
					getDataFolder().mkdir();
					FileWriter fw = new FileWriter(configfile);
					fw.write("server-name: \"test\"");
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		load();
    }
    
    public void load() {
    	try { 
    		Map<String, Object> propMap = new Yaml().load(new FileReader(configfile));
    		servername = (String) propMap.get("server-name"); 
    	} catch (FileNotFoundException e) { 
    		e.printStackTrace(); 
    	}
    }
}
