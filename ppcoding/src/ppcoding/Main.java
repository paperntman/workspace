package ppcoding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		System.out.println("ppap loaded");
		getDataFolder().mkdir();
		
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("ppap")) {
			if(args.length==0) {
				return false;
			}
			switch (args[0]) {
			case "compile":{
				break;
				
			}
			case "start":{
				File f = new File(getDataFolder().getPath()+File.separator+args[1]+".ppap");
				String[] cmd = FileRead(f).split("\n");
				for(String s : cmd) {
					String[] split = s.split(" ");
					for(String out : split) {
						out.replaceAll("\n", "");
						out.replaceAll("\r", "");
						sender.sendMessage(out);
					}
					if(s.startsWith("title ")){
						for(Player p : Bukkit.getOnlinePlayers()) {
							switch (split.length) {
							case 2:{
								p.sendTitle(split[1], "", 20, 40, 20);
								break;
							}
							case 3:{
								p.sendTitle(split[1], split[2], 20, 40, 20);
								break;
							}
							case 6:{
								p.sendTitle(split[1], split[2], Integer.parseInt(split[3]), Integer.parseInt(split[4]), Integer.parseInt(split[5]));
								break;
							}
							default:
								break;
							}
						}
					}else if(s.startsWith("actionbar ")) {
						for(Player p : Bukkit.getOnlinePlayers()){
					        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(split[1]));
						}
					}
				}
				break;
			}
			default:
				break;
			}
		}
		return true;
	}
	public static String FileRead(File f) {
		try {
			String re = "";
		FileReader file_reader = new FileReader(f);
        int cur = 0;
        while((cur = file_reader.read()) != -1){
        	re += (char)cur;
        }
        file_reader.close();
        return re;
       }catch (FileNotFoundException e) {
           e.getStackTrace();
       }catch(IOException e){
           e.getStackTrace();
       }
		return "";
	}
}
