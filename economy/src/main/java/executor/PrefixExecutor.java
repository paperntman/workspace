package executor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import 캘린더.Main;

import java.io.*;
import java.util.Arrays;
import java.util.UUID;

public class PrefixExecutor implements CommandExecutor, Listener {
    Inventory baseInventory;
    String title = ChatColor.BOLD + "Prefix";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            if(args.length == 0){
                Player player = (Player) sender;
                baseInventory = InventoryBuilder(new Material[]{Material.AIR}, new int[]{
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0
                }, null, title);
                player.openInventory(baseInventory);
            }else{
                if(sender.hasPermission("tools.prefix.config")){
                    switch (args[0]){
                        case "add":{
                            UUID uuid = Bukkit.getPlayer(args[1]).getUniqueId();
                            File file = new File(getPath(uuid.toString()));
                            try {
                                FileWriter fileWriter = new FileWriter(file, true);
                                fileWriter.write(args[2]+"\n");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        case "remove":{
                            UUID uuid = Bukkit.getPlayer(args[1]).getUniqueId();
                            File file = new File(getPath(uuid.toString()));
                            try {
                                FileReader reader = new FileReader(file);
                                BufferedReader bufferedReader = new BufferedReader(reader);
                                StringBuilder builder = new StringBuilder();
                                FileWriter writer = new FileWriter(file);
                                String line, output;
                                while((line = bufferedReader.readLine()) != null) {
                                    builder.append(line).append("\n");
                                }
                                output = builder.toString();
                                output.replace(args[2]+"\n", "");
                                writer.write(output);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        case "list":{
                            UUID uuid = Bukkit.getPlayer(args[1]).getUniqueId();
                            File file = new File(getPath(uuid.toString()));
                            FileReader reader = null;
                            try {
                                reader = new FileReader(file);
                                BufferedReader bufferedReader = new BufferedReader(reader);
                                StringBuilder builder = new StringBuilder();
                                String line, output;
                                while((line = bufferedReader.readLine()) != null) {
                                    builder.append(line).append("\n");
                                }
                                output = builder.toString();
                                for(String s : output.split("\n")){
                                    sender.sendMessage(s);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            }
        }else{
            sender.sendMessage("플레이어만 사용할 수 있습니다!");
        }
        return true;
    }

    public Inventory InventoryBuilder(Material[] materials, int[] ints, InventoryHolder owner, String title) {
        Inventory inventory = Bukkit.createInventory(owner, ints.length, title);
        for (int i = 0; i < inventory.getContents().length; i++) inventory.setItem(i, new ItemStack(materials[ints[i]]));
        return inventory;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(event.getView().getTitle().equals(title)){
            Player player = (Player) event.getWhoClicked();
            ItemStack itemStack = event.getCurrentItem();
            event.setCancelled(true);
            if(itemStack.getType().equals(Material.WRITABLE_BOOK)){
                player.setDisplayName(itemStack.getItemMeta().getDisplayName()+player.getName());
            }
        }
    }

    public String getPath(String s){
        return Main.getDataFolder.getPath()+File.separator+"prefixes"+File.separator+s;
    }
}
