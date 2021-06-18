package tryn;

import org.bukkit.entity.Player;

public class Tryn{
	Player p;
	int time;
	double damage = 0;
	boolean damaged = false;
	public Tryn(Player p) {
		this.p = p;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
		if(time > 0) damaged = true;
		else damaged = false;
		if(!damaged) damage=0;
	}
	public int removeTime() {
		if(time > 0) time--;
		if(time > 0) damaged = true;
		else damaged = false;
		if(!damaged) damage=0;
		return time;
	}
	public Player getPlayer() {
		return p;
	}
	public double addDamage(double damage) {
		this.damage+=damage;
		return this.damage;
	}
	public double getDamage() {
		return damage;
	}
}
