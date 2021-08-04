package 캘린더;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Loc{
    Player player;
    Location location;
    public Loc(Player p, Location l){
        player = p;
        location = l;
    }

    public Location getLocation() {
        return location;
    }

    public Player getPlayer() {
        return player;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}