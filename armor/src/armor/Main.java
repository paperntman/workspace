package armor;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this,  this);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryDragEvent e) {
		e.getWhoClicked().sendMessage(e.getRawSlots()+"");
	}
}
