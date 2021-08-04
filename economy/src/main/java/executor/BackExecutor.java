package executor;

import listener.Teleport;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import 캘린더.Loc;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class BackExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<Loc> locations = Teleport.locations;
        AtomicReference<Location> backl = new AtomicReference<Location>();
        locations.forEach(l -> {
            if(l.getPlayer().equals((Player) sender)) backl.set(l.getLocation());
        });
        Location l = backl.get();
        if(sender.hasPermission("tools.command.back")){
            Player p = (Player) sender;
            p.teleport(l);
        }
        return false;
    }
}
