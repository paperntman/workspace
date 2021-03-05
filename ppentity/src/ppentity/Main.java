package ppentity;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		getCommand("ppentity").setExecutor(new Ppentity());
		getCommand("ppentity").setTabCompleter(new PpentityTabCompleter());
	}
}
