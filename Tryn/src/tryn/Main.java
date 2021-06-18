package tryn;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	double multiplier = 1.0;
	ArrayList<Tryn> tr = new ArrayList<Tryn>();
	File f = new File(getDataFolder()+File.separator+"papertryn"+File.separator);
	
	@Override
	public void onEnable() {
		getDataFolder().mkdir();
		for(Player p : Bukkit.getOnlinePlayers()) {
			tr.add(new Tryn(p));
		}
		Bukkit.getPluginManager().registerEvents(this,  this);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				for(Tryn t : tr) {
					t.removeTime();
					t.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(1+t.getDamage());
				}
			}
		}, 0, 1);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("tryn")){
			switch (args[0]) {
			case "multiplier":
				multiplier = Integer.parseInt(args[1]);
				
				break;

			default:
				break;
			}
		}
		return true;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		// TODO Auto-generated method stub
		return super.onTabComplete(sender, command, alias, args);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		tr.add(new Tryn(e.getPlayer()));
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		tr.remove(find(e.getPlayer()));
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Tryn r = find((Player) e.getEntity());
			r.setTime(100);
			r.addDamage(e.getDamage() * multiplier);
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		find(e.getEntity()).setTime(0);
	}
	
	public Tryn find(Player p) {
		Tryn r = null;
		for(Tryn t : tr) {
			if(p.equals(t.getPlayer()))
				r = t;
		}
		return r;
	}
}
