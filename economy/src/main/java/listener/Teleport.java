package listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import 캘린더.Loc;

import java.util.ArrayList;

public class Teleport implements Listener {

    public static ArrayList<Loc> locations;

    static {
        locations = new ArrayList<Loc>();
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e){
        locations.forEach(l -> {
            if(l.getPlayer().equals(e.getPlayer())){
                l.setLocation(e.getPlayer().getLocation());
            }
        });
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        locations.add(new Loc(e.getPlayer(), e.getPlayer().getLocation()));
    }
}


