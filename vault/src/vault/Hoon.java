package vault;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class Hoon {
	OfflinePlayer player = null;
	double money;
	File sourse;
	
	public Hoon(File f) throws FileNotFoundException {
		for(OfflinePlayer p : Bukkit.getOfflinePlayers())
			if(f.getName().equals(p.getUniqueId().toString())) player = p;
		Scanner sc = new Scanner(f);
		money = sc.nextDouble();
		sc.close();
	}
	
	public double getMoney() {
		return money;
	}
	
	public OfflinePlayer getPlayer() {
		return player;
	}
	
	public void save() {
		
	}
}
