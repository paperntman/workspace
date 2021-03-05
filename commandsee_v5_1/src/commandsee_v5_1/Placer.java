package commandsee_v5_1;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Location;

public class Placer {
	ArrayList<Location> location;
	UUID UUID;
	public Placer(ArrayList<Location> l, UUID u) {
		UUID = u;
		location = l;
	}
	public ArrayList<Location> getLocation() {
		return location;
	}
	public UUID getUUID() {
		return UUID;
	}
}
