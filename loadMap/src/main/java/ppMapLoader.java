import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.map.MapCanvas;
import org.bukkit.entity.Entity;

import java.util.List;
import java.util.Map;

public class ppMapLoader implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        List<RegionManager> managerList = container.getLoaded();
        if(args[0].equalsIgnoreCase("list")){
            for(RegionManager manager : managerList){
                Map<String, ProtectedRegion> regionMap = manager.getRegions();
                commandSender.sendMessage(regionMap.toString());
            }

            MapCanvas m = (MapCanvas) Bukkit.getWorld("test").getEntities().get(0);
        }
        return true;
    }
}
