package economy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	@Override
	public void onEnable() {
		getDataFolder().mkdir();
		try {
			Reload();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("money")) {
			try {
				Reload();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(args.length == 0) { //money : 나의 잔액
				if(sender instanceof Player) {
					UUID u = getUUID(sender.getName());
					try {
						sender.sendMessage(new BigDecimal(fileRead(u)).toString());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					sender.sendMessage("당신은 플레이어가 아닙니다! /money [uuid] 를 사용해주세요.");
					return true;
				}
				return true;
			}
			switch (args[0]) {
			case "send":{ //money send [player] x: 자신이 그 player에게 x만큼을 송금
				if(args.length != 3){
					sender.sendMessage("명령어를 제대로 쳐주세요!");
					return true;
				}
				if(sender instanceof Player) {
					UUID u1 = getUUID(sender.getName());
					UUID u2 = getUUID(args[1]);
					if(u1.equals(u2)) {
						sender.sendMessage("자신에게 돈을 보낼 수 없습니다!");
						return true;
					}
					try {
						if(fileRead(u1) >= Double.valueOf(args[2])) {
							double value = Double.valueOf(args[2]);
							if(value <= 0) {
								sender.sendMessage("0보다 많은 금액을 보내십시오!");
								return true;
							}
							double d1 = fileRead(u1);
							double d2 = fileRead(u2);
							fileWrite(u1, d1-value);
							fileWrite(u2, d2+value);
							sender.sendMessage(getPlayer(u1).getName()+"의 잔액 : "+new BigDecimal(fileRead(u1)).toString());
							try {
								((Player) getPlayer(u2)).sendMessage(getPlayer(u1).getName()+" 에게서 "+new BigDecimal(value).toString()+" 만큼의 돈을 받았습니다!");
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
						else{
							sender.sendMessage("잔액이 부족합니다!");
						}
					} catch (NumberFormatException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			}
			case "set":{ //money set [player] x: 그 player의 잔액을 x만큼 세팅  [permission: tools.money.set]
				if(!sender.hasPermission("tools.money.set")) {
					sender.sendMessage("권한이 없습니다!");
					return true;
				}
				if(args.length != 3){
					sender.sendMessage("명령어를 제대로 쳐주세요!");
					return true;
				}
				UUID u = getUUID(args[1]);
				double vaule = Double.valueOf(args[2]);
				try {
					fileWrite(u, vaule);
					sender.sendMessage(getPlayer(u).getName()+"의 잔액 : "+new BigDecimal(fileRead(u)).toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			}
			case "reset":{ //money reset [player]: 그 player의 잔액을 초기화함  [permission: tools.money.reset]
				if(!sender.hasPermission("tools.money.reset")) {
					sender.sendMessage("권한이 없습니다!");
					return true;
				}
				if(args.length != 2){
					sender.sendMessage("명령어를 제대로 쳐주세요!");
					return true;
				}
				UUID u = getUUID(args[1]);
				try {
					fileWrite(u, 0);
					sender.sendMessage(getPlayer(u).getName()+"의 잔액 : "+new BigDecimal(fileRead(u)).toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			}
			case "take":{ //money take [player] x: 그 player의 잔액을 x만큼 차감  [permission: tools.money.take]
				if(!sender.hasPermission("tools.money.take")) {
					sender.sendMessage("권한이 없습니다!");
					return true;
				}
				if(args.length != 3){
					sender.sendMessage("명령어를 제대로 쳐주세요!");
					return true;
				}
				UUID u = getUUID(args[1]);
				double vaule = Double.valueOf(args[2]);
				try {
					double d = fileRead(u);
					fileWrite(u, d-vaule);
					sender.sendMessage(getPlayer(u).getName()+"의 잔액 : "+new BigDecimal(fileRead(u)).toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			}
			case "give":{ //money give [player] x: 그 player의 잔액을 x만큼 추가  [permission: tools.money.give]
				if(!sender.hasPermission("tools.money.give")) {
					sender.sendMessage("권한이 없습니다!");
					return true;
				}
				if(args.length != 3){
					sender.sendMessage("명령어를 제대로 쳐주세요!");
					return true;
				}
				UUID u = getUUID(args[0]);
				double vaule = Double.valueOf(args[2]);
				try {
					double d = fileRead(u);
					fileWrite(u, d+vaule);
					sender.sendMessage(getPlayer(u).getName()+"의 잔액 : "+new BigDecimal(fileRead(u)).toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			}
			case "help":{
				sender.sendMessage("/money : 나의 잔액");
				sender.sendMessage("/money send [player] x: 자신이 그 player에게 x만큼을 송금");
				sender.sendMessage("/money [player] : 그 player의 잔액  [permission: tools.money.player]");
				sender.sendMessage("/money set [player] x: 그 player의 잔액을 x만큼 세팅  [permission: tools.money.set]");
				sender.sendMessage("/money reset [player]: 그 player의 잔액을 초기화함  [permission: tools.money.reset]");
				sender.sendMessage("/money take [player] x: 그 player의 잔액을 x만큼 차감  [permission: tools.money.take]");
				sender.sendMessage("/money give [player] x: 그 player의 잔액을 x만큼 추가  [permission: tools.money.give]");
				sender.sendMessage("/baltop : 돈 순위  [permission: tools.money.baltop]");
				break;
			}
			

			default:{ //money [player] : 그 player의 잔액  [permission: tools.money.player]
				if(!sender.hasPermission("tools.money.player")) {
					sender.sendMessage("권한이 없습니다!");
					return true;
				}
				if(args.length != 1){
					sender.sendMessage("명령어를 제대로 쳐주세요!");
					return true;
				}
				UUID u = getUUID(args[0]);
				try {
					sender.sendMessage(new BigDecimal(fileRead(u)).toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			}
		}else if(command.getName().equalsIgnoreCase("baltop")) { //baltop : 돈 순위  [permission: tools.money.baltop]
			if(!sender.hasPermission("tools.money.baltop")) {
				sender.sendMessage("권한이 없습니다!");
				return true;
			}
			try {
				Reload();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			File[] list = getDataFolder().listFiles();
			for(int i=0;i <list.length;i++){
				for(int j=0;j<list.length-i-1;j++){
					try{
						if(fileRead(list[j])<fileRead(list[j+1])){
							File temp = list[j+1];
							list[j+1] = list[j];
							list[j] = temp;
						}	
					}catch(IOException e){
						e.printStackTrace();
					}
				}
			}
			for(int i=0;i <list.length;i++){
				try {
					fileRead(list[i]);
					sender.sendMessage(i+1+". "+getPlayer(UUID.fromString(list[i].getName())).getName()+": "+new BigDecimal(fileRead(list[i])).toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}else if(command.getName().equalsIgnoreCase("reload")) {
			Bukkit.reload();
		}
		return true;
	}
	
	public OfflinePlayer getPlayer(UUID u) {
		for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			if(p.getUniqueId().equals(u)) return p;
		}
		return null;
	}
	
	public UUID getUUID(String s) {
		for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			if(p.getName().equalsIgnoreCase(s)) return p.getUniqueId();
		}
		return null;
	}
	
	public void fileWrite(UUID u, double d) throws IOException {
		File f = new File(getDataFolder().getPath()+File.separator+u.toString());
		FileWriter fw = new FileWriter(f, false);
		fw.write(String.valueOf(d));
		fw.close();
	}
	public double fileRead(UUID u) throws IOException {
		File f = new File(getDataFolder().getPath()+File.separator+u.toString());
		BufferedReader br = new BufferedReader(new FileReader(f));
		String ret = br.readLine();
		br.close();
		return Double.parseDouble(ret);
	}
	public void Reload() throws IOException {
		for(Player p : Bukkit.getOnlinePlayers()) {
			UUID u = getUUID(p.getName());
			File f = new File(getDataFolder().getPath()+File.separator+u.toString());
			if(!f.exists()) {
				fileWrite(u, 0);
			}
		}
		for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			UUID u = getUUID(p.getName());
			File f = new File(getDataFolder().getPath()+File.separator+u.toString());
			if(!f.exists()) {
				fileWrite(u, 0);
			}
		}
	}
	public double fileRead(File f) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(f));
		String ret = br.readLine();
		br.close();
		return Double.parseDouble(ret);
	}

}
